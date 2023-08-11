package com.yupi.openapiclientsdk;

import com.yupi.openapiclientsdk.client.OpenAPIClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author zuodong
 * @create 2023-08-06 14:10
 */
@Configuration
@ConfigurationProperties("openapi.client")
@Data
@ComponentScan
public class OpenApiClientConfig {

    private String accessKey;
    private String secretKey;

    @Bean
    public OpenAPIClient openAPIClient() {
        return new OpenAPIClient(accessKey, secretKey);
    }
}
