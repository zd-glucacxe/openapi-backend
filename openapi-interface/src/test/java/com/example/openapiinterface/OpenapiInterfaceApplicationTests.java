package com.example.openapiinterface;

import com.example.openapiclientsdk.client.OpenAPIClient;
import com.example.openapiclientsdk.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class OpenapiInterfaceApplicationTests {

    @Resource
    private OpenAPIClient openAPIClient;

    @Test
    void contextLoads() {
        String result = openAPIClient.getNameByGet("yupi");
        User user = new User();
        user.setUsername("Axxxx");
        String usernameByPost = openAPIClient.getUsernameByPost(user);
        System.out.println(result);
        System.out.println(usernameByPost);
    }

}
