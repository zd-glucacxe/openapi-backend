package com.yupi.project.service;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author zuodong
 * @create 2023-08-09 18:19
 */

@SpringBootTest
public class UserInterfaceInfoServiceTest {

    @Resource
    private UserInterfaceInfoService userInterfaceInfoService;

    @Test
    public void invokeCount() {
        boolean count = userInterfaceInfoService.invokeCount(1L, 1L);
        Assertions.assertTrue(count);
    }
}