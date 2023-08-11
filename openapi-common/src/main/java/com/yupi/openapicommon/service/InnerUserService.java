package com.yupi.openapicommon.service;


import com.yupi.openapicommon.model.entity.User;


/**
 * 用户服务
 *
 * @author yupi
 */
public interface InnerUserService {

    /**
     * 数据库是否已分配给用户密钥    User (accessKey)
     * @param accessKey
     * @return User
     */
    User getinvokeUser(String accessKey);

}
