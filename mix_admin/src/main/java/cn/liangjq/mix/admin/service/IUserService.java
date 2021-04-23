package cn.liangjq.mix.admin.service;


import cn.liangjq.mix.common.dto.LoginVO;
import cn.liangjq.mix.common.dto.PageResponse;
import cn.liangjq.mix.common.dto.UserRequest;
import cn.liangjq.mix.common.entity.UserRole;
import cn.liangjq.mix.common.dto.UserVO;
import cn.liangjq.mix.common.result.R;

import java.util.List;

/**
 * @Author: liangjq
 * @Description: 用户接口
 * @Date: 2021/4/14
 */
public interface IUserService {

    /**
     * 登录校验
     *
     * @param loginVO
     * @return
     */
    R checkLoginVO(LoginVO loginVO);

    /**
     * 通过用户名获取用户信息
     *
     * @param username
     * @return
     */
    UserVO getUserByName(String username);

    /**
     * 通过用户ID获得用户角色关联集合
     *
     * @param id
     * @return
     */
    List<UserRole> listUserRoleByUserId(Long id);

    /**
     * 分页查找用户信息
     *
     * @param request
     * @return
     */
    R<PageResponse> pageUser(UserRequest request);
}
