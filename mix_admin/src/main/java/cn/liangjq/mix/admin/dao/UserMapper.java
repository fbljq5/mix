package cn.liangjq.mix.admin.dao;


import cn.liangjq.mix.common.dto.UserRequest;
import cn.liangjq.mix.common.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    /**
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * @mbg.generated
     */
    int insert(User record);

    /**
     * @mbg.generated
     */
    int insertSelective(User record);

    /**
     * @mbg.generated
     */
    User selectByPrimaryKey(Long id);

    /**
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * @mbg.generated
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
    List<User> selectByUserRequest(UserRequest request);

}