package cn.liangjq.mix.common.dto;

import lombok.Data;

import java.util.List;

/**
 * @author ： liangjq
 * @description ： 分页返回结果
 * @date ： 2021/4/23
 */
@Data
public class PageResponse<T> {
    /**
     * 记录总数
     */
    private long total;

    /**
     * 数据模型
     */
    private List<T> items;

}
