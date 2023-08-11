package com.yupi.project.model;

import com.yupi.openapicommon.model.entity.InterfaceInfo;
import com.yupi.project.model.entity.Post;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 接口信息封装统计视图
 *
 * @author yupi
 * @TableName product
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class InterfaceInfoVO extends InterfaceInfo {

    /**
     * 是否已点赞
     */
    private Integer totalNum;

    private static final long serialVersionUID = 1L;
}