package cn.liangjq.mix.common.dto.role;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;

/**
 * @author ： liangjq
 * @description ： 新增角色数据封装
 * @date ： 2021/4/25
 */
@Data
public class RoleAddDTO {

    /**
     * 角色名称 role_name
     */
    @NotBlank(message = "角色名称不能为空")
    @Length(max = 30, message = "角色名称最多50位")
    private String roleName;

    /**
     * 角色编码 role_code
     */
    @NotBlank(message = "角色编码不能为空")
    @Length(max = 100, message = "角色编码最多100位")
    private String roleCode;

    /**
     * 显示顺序 role_sort
     */
    @NotBlank(message = "显示顺序不能为空")
    private Integer roleSort;

    /**
     * 备注 remark
     */
    private String remark;

}
