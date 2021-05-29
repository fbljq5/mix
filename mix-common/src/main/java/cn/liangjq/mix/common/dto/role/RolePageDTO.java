package cn.liangjq.mix.common.dto.role;

import lombok.Data;

import java.util.Date;

/**
 * @author ： liangjq
 * @description ： 用户展示信息
 * @date ： 2021/4/23
 */
@Data
public class RolePageDTO {

    /**
     * 用户ID id
     */
    private Long id;

    /**
     * 角色名称 role_name
     */
    private String roleName;

    /**
     * 角色编码 role_code
     */
    private String roleCode;

    /**
     * 显示顺序 role_sort
     */
    private Integer roleSort;

    /**
     * 角色状态（0正常 1停用） status
     */
    private Boolean status;

    /**
     * 是否删除（0代表存在 1代表删除） is_delete
     */
    private Boolean isDelete;

    /**
     * 备注 remark
     */
    private String remark;

    /**
     * 创建时间 gmt_create
     */
    private String gmtCreate;

    /**
     * 更新时间 gmt_modified
     */
    private String gmtModified;
}
