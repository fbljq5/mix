package cn.liangjq.mix.common.entity;

import java.util.Date;
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
public class Role extends BaseEntity {
    /**
     * 角色id uid
     */
    private String uid;

    /**
     * 角色名 role_name
     */
    private String roleName;

    /**
     * 创建时间 create_time
     */
    private Date createTime;

    /**
     * 更新时间 update_time
     */
    private Date updateTime;

    /**
     * 状态 status
     */
    private Byte status;

    /**
     * 角色介绍 summary
     */
    private String summary;

    /**
     * 角色管辖的菜单的UID category_menu_uids
     */
    private String categoryMenuUids;
}