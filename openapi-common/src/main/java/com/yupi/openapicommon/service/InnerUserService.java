package com.yupi.openapicommon.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.yupi.openapicommon.model.entity.User;


/**
 * 用户服务
 *
 * @author yupi
 */
public interface InnerUserService extends IService<User> {

    /**
     * 数据库是否已分配给用户密钥    User (accessKey、secretKey)
     * @param accessKey
     * @param secretKey
     * @return User
     */
    User getinvokeUser(String accessKey, String secretKey);

}
