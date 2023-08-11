package com.yupi.openapigateway;

import com.yupi.openAPIClientsdk.utils.SignUtil;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
public class CustomGlobalFilter implements GlobalFilter, Ordered {
        private static final List<String> IP_WHITE_LIST = Arrays.asList("127.0.0.1");

    /**
     * 全局过滤
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {


        //1. 记录请求日志
        ServerHttpRequest request = exchange.getRequest();
        log.info("请求唯一标识：" + request.getId());
        log.info("请求路径：" + request.getPath().value());
        log.info("请求方法：" + request.getMethod());
        log.info("请求参数：" + request.getQueryParams());
        String sourceAddress = request.getLocalAddress().getHostString();
        log.info("请求来源地址1：" + request.getLocalAddress().getHostString());
        log.info("请求来源地址2：" + request.getRemoteAddress());

        ServerHttpResponse response = exchange.getResponse();
        //2. 访问控制 - 黑白名单
        if (!IP_WHITE_LIST.contains(sourceAddress)) {
          return handleNoAuth(response);
        }
        //4. 用户鉴权（判断ak、sk是否合法）
        HttpHeaders headers = request.getHeaders();
        String accessKey = headers.getFirst("accessKey");
        String nonce = headers.getFirst("nonce");
        String body = headers.getFirst("body");
        String timestamp = headers.getFirst("timestamp");
        String sign = headers.getFirst("sign");

        // TODO 实际情况是要去数据库中查是否已分配给用户
        if (!"yupi".equals(accessKey)) {
            return handleNoAuth(response);
        }

        if(Long.parseLong(nonce) > 10000L) {
            return handleNoAuth(response);
        }

        //TODO 时间戳和当前时间不能超过5分钟
        Long currentTime = System.currentTimeMillis() / 1000;
        final Long FIVE_MINUTES = 60 * 5L;
        if ((currentTime - Long.parseLong(timestamp)) >= FIVE_MINUTES) {
             return handleNoAuth(response);
        }


        //TODO 实际情况是从数据库中拿到 secretKey，可以通过accessKey去查
        String serverSign = SignUtil.genSign(body, "abcdefgh");

        if (!sign.equals(serverSign)) {
            return handleNoAuth(response);
        }


        //4. 请求的模拟接口是否存在？
        //todo 从数据库中查询模拟接口是否存在，以及请求方法是否匹配(还可以检验请求参数）

        //5. 请求转发，调用模拟接口
//        Mono<Void> filter = chain.filter(exchange);
        //6. 响应日志
        return handleResponse(exchange, chain);

    }


    /**
     * 处理响应
     * @param exchange
     * @param chain
     * @return
     */
    public Mono<Void> handleResponse (ServerWebExchange exchange, GatewayFilterChain chain) {
        try {
            ServerHttpResponse originalResponse = exchange.getResponse();
            // 缓冲区工厂 缓存数据
            DataBufferFactory bufferFactory = originalResponse.bufferFactory();
            // 拿到响应码
            HttpStatus statusCode = originalResponse.getStatusCode();

            if(statusCode == HttpStatus.OK){
                // 被装饰后的response对象 增强能力
                ServerHttpResponseDecorator decoratedResponse = new ServerHttpResponseDecorator(originalResponse) {

                    /**
                     *  当调用模拟接口完成之后，返回了结果
                     *  就会调用此方法
                     * @param body  模拟接口响应值
                     * @return
                     */
                    @Override
                    public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
                        log.info("body instanceof Flux: {}", (body instanceof Flux));
                        if (body instanceof Flux) {
                            Flux<? extends DataBuffer> fluxBody = Flux.from(body);
                            // 往返回值写数据
                            // 构造数据 拼接字符串
                            return super.writeWith(

                                fluxBody.map(dataBuffer -> {
                                    //7. todo 调用成功，接口调用次数 +1  invokeCount()
                                    byte[] content = new byte[dataBuffer.readableByteCount()];
                                    dataBuffer.read(content);
                                    DataBufferUtils.release(dataBuffer);//释放掉内存
                                    // 构建日志
                                    StringBuilder sb2 = new StringBuilder(200);
                                    List<Object> rspArgs = new ArrayList<>();
                                    rspArgs.add(originalResponse.getStatusCode());
                                    String data = new String(content, StandardCharsets.UTF_8);//data
                                    sb2.append(data);
                                    //打印日志
                                    log.info("响应结果：" + data);
                                    // ***
                                    return bufferFactory.wrap(content);
                                }));
                        } else {
                                //8. 调用失败，返回一个规范的错误码
                            log.error("<--- {} 响应code异常", getStatusCode());
                        }

                        return super.writeWith(body);
                    }
                };

                // 设置 response对象为装饰过的
                return chain.filter(exchange.mutate().response(decoratedResponse).build());
            }


            return chain.filter(exchange);//降级处理返回数据
        }catch (Exception e){
            log.error("网关响应处理异常" + e);
            return chain.filter(exchange);
        }

    }



    /**
     * 设置过滤器优先级
     *
     * @return
     */
    @Override
    public int getOrder() {
        return -1;
    }

    public Mono<Void> handleNoAuth(ServerHttpResponse response) {
        response.setStatusCode(HttpStatus.FORBIDDEN);
        /**
         * 调用这个方法时，它会立即终止当前请求的处理，并将响应发送给客户端。
         * 这意味着后续的过滤器将不会被执行，并且请求将不会被转发到后端服务
         */
        return response.setComplete();
    }


    public Mono<Void> handleInvokeError(ServerHttpResponse response) {
        response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
        /**
         * 调用这个方法时，它会立即终止当前请求的处理，并将响应发送给客户端。
         * 这意味着后续的过滤器将不会被执行，并且请求将不会被转发到后端服务
         */
        return response.setComplete();
    }
}