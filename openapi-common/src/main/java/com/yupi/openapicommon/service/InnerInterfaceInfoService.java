package com.yupi.openapicommon.service;


import com.yupi.openapicommon.model.entity.InterfaceInfo;

/**
* @author DaVinci
* @description 针对表【interface_info(接口信息)】的数据库操作Service
* @createDate 2023-08-04 23:21:15
*/
public interface InnerInterfaceInfoService  {

    /**
     * 从数据库中查询模拟接口是否存在  InterfaceInfo (请求路径、请求方法、请求参数)
     * @param path
     * @param method
     * @return InterfaceInfo
     */
    InterfaceInfo getInterfaceInfo(String path, String method);

}
