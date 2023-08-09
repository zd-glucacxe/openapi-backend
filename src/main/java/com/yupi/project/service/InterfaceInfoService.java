package com.yupi.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yupi.project.model.entity.InterfaceInfo;

/**
* @author DaVinci
* @description 针对表【interface_info(接口信息)】的数据库操作Service
* @createDate 2023-08-04 23:21:15
*/
public interface InterfaceInfoService extends IService<InterfaceInfo> {

    void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add);

}
