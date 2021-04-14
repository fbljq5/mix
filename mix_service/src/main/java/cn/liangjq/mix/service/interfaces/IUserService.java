package cn.liangjq.mix.service.interfaces;


import cn.liangjq.mix.common.dto.LoginVO;
import cn.liangjq.mix.common.entity.User;
import cn.liangjq.mix.common.result.R;

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
    User getUserByName(String username);

}
