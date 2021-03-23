package cn.liangjq.mix.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* @Description: 
* @Author: liangjianqiang
* @Date: 2021/03/23
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRole extends BaseEntity {
    /**
     * 用户ID user_id
     */
    private Long userId;

    /**
     * 角色ID role_id
     */
    private Integer roleId;
}