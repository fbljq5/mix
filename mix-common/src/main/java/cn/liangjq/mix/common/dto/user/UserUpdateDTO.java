package cn.liangjq.mix.common.dto.user;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author ： liangjq
 * @description ：  更新用户数据封装
 * @date ： 2021/4/25
 */
@Data
public class UserUpdateDTO {

    /**
     * 主键id
     */
    @Valid
    @NotNull(message = "id不能为空")
    private Long id;

    /**
     * 用户名称 username
     */
    @Valid
    @NotBlank(message = "用户名不能为空")
    @Length(max = 50, min = 5, message = "用户名为5-50位")
    private String username;

    /**
     * 姓名 realName
     */
    @NotBlank(message = "姓名不能为空")
    @Length(max = 50, min = 5, message = "姓名为5-50位")
    private String realName;

    /**
     * 头像 avatar
     */
    private String avatar;

    /**
     * 邮箱 email
     */
    @Valid
    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    @Length(max = 50, message = "邮箱最多50位")
    private String email;

    /**
     * 电话号码 phone
     */
    @Valid
    @NotBlank(message = "手机号码不能为空")
    private String phone;


    /**
     * 介绍 remark
     */
    private String remark;

    @NotNull(message = "角色不能为空")
    private Long[] roleIds;

}
