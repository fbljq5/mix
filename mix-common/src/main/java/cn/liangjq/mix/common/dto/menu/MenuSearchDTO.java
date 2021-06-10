package cn.liangjq.mix.common.dto.menu;

import cn.liangjq.mix.common.dto.PageBaseRequest;
import lombok.Data;

/**
 * @Description: 菜单分页查询条件封装
 * @Author: liangjq
 * @Date: 2021/5/29
 */
@Data
public class MenuSearchDTO {

    /**
     * 菜单名称 menu_name
     */
    private String menuName;

    /**
     * 菜单状态（0正常 1停用） status
     */
    private Boolean status;

}
