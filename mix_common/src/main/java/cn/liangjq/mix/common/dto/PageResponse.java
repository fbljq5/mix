package cn.liangjq.mix.common.dto;

import lombok.Data;

import java.util.List;

/**
 * @author ： liangjq
 * @description ： 分页返回结果
 * @date ： 2021/4/23
 */
@Data
public class PageResponse {

    /**
     * 当前页码
     */
    private int page;
    /**
     * 每页数量
     */
    private int size;
    /**
     * 记录总数
     */
    private long totalSize;
    /**
     * 页码总数
     */
    private int totalPages;
    /**
     * 数据模型
     */
    private List<?> list;

}
