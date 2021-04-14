package cn.liangjq.mix.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Description: 角色
 * @Author: liangjq
 * @Date: 2021/03/26
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role extends BaseEntity {
    /**
     * 角色ID id
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
    private Byte status;

    /**
     * 是否删除（0代表存在 1代表删除） is_delete
     */
    private Byte isDelete;

    /**
     * 备注 remark
     */
    private String remark;

    /**
     * 创建时间 gmt_create
     */
    private Date gmtCreate;

    /**
     * 更新时间 gmt_modified
     */
    private Date gmtModified;
}