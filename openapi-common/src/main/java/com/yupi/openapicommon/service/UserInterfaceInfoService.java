package com.yupi.openapicommon.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yupi.openapicommon.model.entity.UserInterfaceInfo;

/**
* @author DaVinci
* @description 针对表【user_interface_info(接口信息)】的数据库操作Service
* @createDate 2023-08-09 17:17:52
*/
public interface UserInterfaceInfoService extends IService<UserInterfaceInfo> {
    


    /**
     * 接口调用次数统计
     * @param interfaceInfoId
     * @param userId
     * @return
     */
    boolean invokeCount(long interfaceInfoId, long userId);

    void validUserInterfaceInfo(UserInterfaceInfo userInterfaceInfo, boolean add);
}
