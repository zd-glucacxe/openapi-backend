package com.yupi.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yupi.openapicommon.model.entity.InterfaceInfo;
import com.yupi.openapicommon.model.entity.UserInterfaceInfo;

import java.util.List;

/**
 * @Entity com.yupi.project.model.entity.UserInterfaceInfo
 */
public interface UserInterfaceInfoMapper extends BaseMapper<UserInterfaceInfo> {


    List<UserInterfaceInfo> ListTopInterfaceinfoInvoke(int limit);

}




