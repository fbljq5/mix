package cn.liangjq.mix.admin.dao;


import cn.liangjq.mix.common.dto.user.UserSearchDTO;
import cn.liangjq.mix.common.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Description: 用户信息DAO
 * @Author: liangjq
 * @Date: 2021/4/25
 */
@Mapper
public interface UserMapper {
    /**
     * 新增用户信息
     *
     * @param record
     * @return
     */
    int insert(User record);

    /**
     * 新增用户信息
     *
     * @param record
     * @return
     */
    int insertSelective(User record);

    /**
     * 查找用户信息
     *
     * @param id
     * @return
     */
    User selectByPrimaryKey(Long id);

    /**
     * 更新用户信息
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * 更新用户信息
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(User record);

    /**
     * 通过用户名查找用户
     *
     * @param userName
     * @return
     */
    User findUserByUsername(String userName);

    /**
     * 通过请求参数查询用户信息
     *
     * @param request
     * @return
     */
    List<User> selectByUserRequest(UserSearchDTO request);

    /**
     * 通过用户名判断用户是否存在
     *
     * @param username
     * @return
     */
    boolean checkUserExistByUsername(String username);

    /**
     * 通过用户id判断用户是否存在
     *
     * @param userId
     * @return
     */
    boolean checkUserExistById(Long userId);

    /**
     * 判断该用户名是否被其他用户使用
     *
     * @param id
     * @param username
     * @return
     */
    boolean checkUserExistByIdAndName(Long id, String username);

    /**
     * 删除用户信息
     * @param userId
     * @return
     */
    int deleteById(Long userId);
}