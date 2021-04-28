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
     * 新增
     *
     * @return
     */
    public final Role add() {
        this.isDelete = false;
        this.status = true;
        this.setGmtCreate(new Date());
        return this;
    }

    /**
     * 更新
     *
     * @return
     */
    public final Role update() {
        this.setGmtModified(new Date());
        return this;
    }

    /**
     * 删除
     *
     * @return
     */
    public final Role delete() {
        this.isDelete = true;
        this.status = false;
        this.setGmtModified(new Date());
        return this;
    }
}