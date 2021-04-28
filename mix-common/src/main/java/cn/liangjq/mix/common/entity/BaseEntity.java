package cn.liangjq.mix.common.entity;

import lombok.Data;

import java.util.Date;

/**
 * @Author: liangjq
 * @Description: Entity基类
 * @Date: 2021/3/23
 */
@Data
public class BaseEntity {
    /**
     * 主键id
     */
    protected Long id;

    /**
     * 创建时间
     */
    protected Date gmtCreate;

    /**
     * 更新时间
     */
    protected Date gmtModified;
}
