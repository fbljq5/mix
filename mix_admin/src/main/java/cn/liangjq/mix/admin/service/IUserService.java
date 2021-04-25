package cn.liangjq.mix.admin.service;


import cn.liangjq.mix.common.dto.LoginVO;
import cn.liangjq.mix.common.dto.PageResponse;
import cn.liangjq.mix.common.dto.user.*;
import cn.liangjq.mix.common.entity.UserRole;
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
    R<PageResponse> pageUser(UserListRequest request);

    /**
     * 新增用户
     *
     * @param userAddDTO
     * @return
     */
    R<String> addUser(UserAddDTO userAddDTO);

    /**
     * 删除用户信息
     *
     * @param userId
     * @return
     */
    R<String> deleteUser(Long userId);

    /**
     * 更新用户信息
     *
     * @param updateDTO
     * @return
     */
    R<String> updateUser(UserUpdateDTO updateDTO);

    /**
     * 修改用户密码
     *
     * @param modifyPwdDTO
     * @return
     */
    R<String> modifyPassword(UserModifyPwdDTO modifyPwdDTO);
}
