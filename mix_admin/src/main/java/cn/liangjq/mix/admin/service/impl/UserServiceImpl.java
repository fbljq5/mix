package cn.liangjq.mix.admin.service.impl;

import cn.liangjq.mix.admin.dao.RoleMapper;
import cn.liangjq.mix.admin.dao.UserMapper;
import cn.liangjq.mix.admin.dao.UserRoleMapper;
import cn.liangjq.mix.admin.service.IUserService;
import cn.liangjq.mix.common.config.JwtConfig;
import cn.liangjq.mix.common.dto.LoginVO;
import cn.liangjq.mix.common.entity.Role;
import cn.liangjq.mix.common.entity.User;
import cn.liangjq.mix.common.entity.UserRole;
import cn.liangjq.mix.common.result.R;
import cn.liangjq.mix.utils.JWTUtils;
import cn.liangjq.mix.utils.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @Author: liangjq
 * @Description: 用户接口实现类
 * @Date: 2021/4/14
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final UserMapper userMapper;
    private final RoleMapper roleMapper;
    private final UserRoleMapper userRoleMapper;
    private final RedisUtil redisUtil;
    private final JwtConfig jwtConfig;

    @Override
    public R checkLoginVO(LoginVO loginVO) {
        if (null == loginVO || StringUtils.isBlank(loginVO.getUsername())
                || StringUtils.isBlank(loginVO.getPassword())) {
            return R.fail();
        }
        User user = userMapper.findUserByUsername(loginVO.getUsername());
        if (!user.getPassword().equals(loginVO.getPassword())) {
            return R.fail();
        }
        // 获得角色列表
        List<Role> roleList = roleMapper.getRoleListByUserId(user.getId());
        List<String> roleNameList = roleList.stream().map(Role::getRoleName).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(roleNameList)) {
            return R.fail();
        }
        // 账号密码校验成功
        // 生成token
        String token = JWTUtils.createToken(user.getUserName(), StringUtils.join(roleNameList, ","),
                jwtConfig.getExpiresSecond(), jwtConfig.getSecret());

        if (StringUtils.isBlank(token)) {
            return R.fail();
        }
        redisUtil.setEx(token, token, jwtConfig.getExpiresSecond(), TimeUnit.SECONDS);
        return R.ok(token);
    }

    @Override
    public User getUserByName(String username) {
        return userMapper.findUserByUsername(username);
    }

    @Override
    public List<UserRole> listUserRoleByUserId(Long id) {
        return userRoleMapper.selectByUserId(id);
    }
}
