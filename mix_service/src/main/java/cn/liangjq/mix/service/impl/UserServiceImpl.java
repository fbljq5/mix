package cn.liangjq.mix.service.impl;

import cn.liangjq.mix.common.constant.Constants;
import cn.liangjq.mix.common.dto.LoginVO;
import cn.liangjq.mix.common.entity.User;
import cn.liangjq.mix.common.result.R;
import cn.liangjq.mix.service.dao.UserMapper;
import cn.liangjq.mix.service.interfaces.IUserService;
import cn.liangjq.mix.utils.JWTUtils;
import cn.liangjq.mix.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

/**
 * @Author: liangjq
 * @Description: 用户接口实现类
 * @Date: 2021/4/14
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public R checkLoginVO(LoginVO loginVO) {
        if (null == loginVO || !StringUtils.hasText(loginVO.getUsername())
                || !StringUtils.hasText(loginVO.getPassword())) {
            return R.fail();
        }
        User user = userMapper.findUserByUsername(loginVO.getUsername());
        if (!user.getPassword().equals(loginVO.getPassword())) {
            return R.fail();
        }
        // 账号密码校验成功
        // 生成token
        String token = JWTUtils.createToken(user.getUserName());
        if (!StringUtils.hasText(token)) {
            return R.fail();
        }
        redisUtil.setEx(token, token, Constants.EXPIRED_PERIOD, TimeUnit.MILLISECONDS);
        return R.ok(token);
    }

    @Override
    public User getUserByName(String username) {
        return userMapper.findUserByUsername(username);
    }
}
