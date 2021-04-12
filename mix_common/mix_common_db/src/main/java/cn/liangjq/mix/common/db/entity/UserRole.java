package cn.liangjq.mix.common.db.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
* @Description: 
* @Author: liangjianqiang
* @Date: 2021/03/26
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRole extends BaseEntity {
    /**
     * 主键ID id
     */
    private Long id;

    /**
     * 用户ID user_id
     */
    private Long userId;

    /**
     * 角色ID role_id
     */
    private Long roleId;

    /**
     * 创建时间 gmt_create
     */
    private Date gmtCreate;

    /**
     * 修改时间 gmt_modified
     */
    private Date gmtModified;
}