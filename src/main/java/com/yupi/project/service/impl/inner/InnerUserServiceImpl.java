package com.yupi.project.service.impl.inner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yupi.openapicommon.model.entity.User;
import com.yupi.openapicommon.service.InnerUserService;
import com.yupi.project.common.ErrorCode;
import com.yupi.project.exception.BusinessException;
import com.yupi.project.mapper.UserMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

/**
 * @author zuodong
 * @create 2023-08-11 22:02
 */
@DubboService
public class InnerUserServiceImpl implements InnerUserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User getinvokeUser(String accessKey) {

        if (StringUtils.isAnyBlank(accessKey)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("accessKey", accessKey);
        return userMapper.selectOne(queryWrapper);

    }
}
