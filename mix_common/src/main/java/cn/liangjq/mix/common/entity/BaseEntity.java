package cn.liangjq.mix.common.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author ： liangjianqiang
 * @description ： Entity基类
 * @date ： 2021/3/23
 */
@Data
public class BaseEntity {
    /**
     * 主键id
     */
    private Long id;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 更新时间
     */
    private Date gmtModified;
}
