package cn.liangjq.mix.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Description: 用户-角色
 * @Author: liangjq
 * @Date: 2021/03/26
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
    private Long roleId;
}