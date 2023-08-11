package com.yupi.openapiinterface.controller;



import com.yupi.openapiclientsdk.model.User;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


/**
 *  名称API
 *
 * @author zuodong
 * @create 2023-08-05 21:15
 */
@RestController
@RequestMapping("/name")
public class NameController {

    @GetMapping("/get")
    public String getNameByGet(String name, HttpServletRequest request) {
        System.out.println(request.getHeader("yupi"));
        return "GET 你的名字是" + name;
    }

    @PostMapping("/post")
    public String getNameByPost(@RequestParam String name) {
        return "POST 你的名字是" + name;
    }

    @PostMapping("/user")
    public String getUsernameByPost(@RequestBody User user, HttpServletRequest request) {
//        String accessKey = request.getHeader("accessKey");
//        String nonce = request.getHeader("nonce");
//        String body = request.getHeader("body");
//        String timestamp = request.getHeader("timestamp");
//        String sign = request.getHeader("sign");
//
//          // TODO 实际情况是要去数据库中查是否已分配给用户
//          if (!accessKey.equals("yupi")) {
//             throw new RuntimeException("无权限！");
//          }
//
//          if(Long.parseLong(nonce) > 10000) {
//              throw new RuntimeException("无权限！");
//          }
//
//          //TODO 时间戳和当前时间不能超过5分钟
//
//          //TODO 实际情况是从数据库中拿到 secretKey，可以通过accessKey去查
//          String serverSign = SignUtil.genSign(body, "abcdefgh");
//
//        if (!sign.equals(serverSign)) {
//            throw new RuntimeException("无权限！");
//        }

        String result = "POST 用户名是" + user.getUsername();

        // 调用成功后，次数 +1
        return result;
    }

}
