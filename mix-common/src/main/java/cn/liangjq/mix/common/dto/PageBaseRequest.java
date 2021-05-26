package cn.liangjq.mix.common.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
    @NotNull(message = "页大小不能为空")
    private Integer pageSize;

    /**
     * 当前页
     */
    @NotNull(message = "页码不能为空")
    private Integer page;

}
