package cn.liangjq.mix.common.entity;

import cn.liangjq.mix.common.utils.MD5Utils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Description: 用户
 * @Author: liangjq
 * @Date: 2021/03/26
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {

    /**
     * 用户名称 user_name
     */
    private String userName;

    /**
     * 邮箱 email
     */
    private String email;

    /**
     * 电话号码 phone
     */
    private String phone;

    /**
     * 密码 password
     */
    private String password;

    /**
     * 帐号状态（1正常 0停用） status
     */
    private Boolean status;

    /**
     * 是否已删除（0正常 1已删除） is_delete
     */
    private Boolean isDelete;

    /**
     * 最后登录时间 login_date
     */
    private Date loginDate;

    /**
     * 备注 remark
     */
    private String remark;

    /**
     * 新增
     *
     * @return
     */
    public final User add() {
        this.isDelete = false;
        this.status = true;
        this.setGmtCreate(new Date());
        this.setPassword((MD5Utils.getMd5(this.getPassword())));
        return this;
    }

    /**
     * 更新
     *
     * @return
     */
    public final User update() {
        this.setGmtModified(new Date());
        return this;
    }

    /**
     * 用户登录逻辑
     *
     * @return
     */
    public final User login() {
        this.setLoginDate(new Date());
        return this;
    }

    /**
     * 修改密码
     *
     * @return
     */
    public final User modifyPwd() {
        this.setGmtModified(new Date());
        this.setPassword((MD5Utils.getMd5(this.getPassword())));
        return this;
    }

    /**
     * 删除
     *
     * @return
     */
    public final User delete() {
        this.isDelete = true;
        this.status = false;
        this.setGmtModified(new Date());
        return this;
    }
}