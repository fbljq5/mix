package cn.liangjq.mix.common.dto.user;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * @author ： liangjq
 * @description ： 新增用户数据封装
 * @date ： 2021/4/25
 */
@Data
public class UserAddDTO {

    private Long id;
    /**
     * 用户名称 user_name
     */
    @NotBlank(message = "用户名不能为空")
    @Length(max = 50, min = 5, message = "用户名为5-50位")
    private String userName;

    /**
     * 邮箱 email
     */
    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    @Length(max = 50, message = "邮箱最多50位")
    private String email;

    /**
     * 电话号码 phone
     */
    @NotBlank(message = "手机号码不能为空")
    private String phone;

    /**
     * 密码 password
     */
    @NotBlank(message = "密码不能为空")
    @Length(min = 6, max = 50, message = "密码为6-50位")
    private String password;

    /**
     * 备注 remark
     */
    private String remark;

    @NotBlank(message = "角色不能为空")
    private String roleIds;

}
