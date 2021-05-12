package cn.liangjq.mix.common.dto;

import lombok.Data;

/**
 * @author ： liangjq
 * @description ： 分页查询基类
 * @date ： 2021/4/23
 */
@Data
public class PageBaseRequest {

    /**
     * 页大小
     */
    protected Integer pageSize;

    /**
     * 当前页
     */
    protected Integer page;

}
