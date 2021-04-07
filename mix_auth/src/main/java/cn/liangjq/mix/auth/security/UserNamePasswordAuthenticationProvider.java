package cn.liangjq.mix.auth.security;

import cn.liangjq.mix.common.dao.RoleMapper;
import cn.liangjq.mix.common.dao.UserMapper;
import cn.liangjq.mix.common.dao.UserRoleMapper;
import cn.liangjq.mix.common.entity.User;
import cn.liangjq.mix.common.entity.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ： liangjianqiang
 * @description ： 用户名密码校验服务提供者
 * @date ： 2021/3/31
 */
public class UserNamePasswordAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = (authentication.getPrincipal() == null) ? "NONE_PROVIDED" : authentication.getName();
        // 认证用户名
        if (!StringUtils.hasText(username)) {
            throw new BadCredentialsException(null);
        }
        // 调用方法查询用户
        User user = userMapper.findUserByUsername(username);
        if (user == null) {
            throw new BadCredentialsException(null);
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        List<UserRole> userRoles = userRoleMapper.selectByUserId(user.getId());
        if (CollectionUtils.isEmpty(userRoles)) {
            return new UsernamePasswordAuthenticationToken(username,
                    authentication.getCredentials(), null);
        }
        userRoles.forEach(userRole -> authorities.add(new SimpleGrantedAuthority("ROLE_" +
                roleMapper.selectByPrimaryKey(userRole.getRoleId()).getRoleName())));

        UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(username,
                authentication.getCredentials(), authorities);

        return result;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }


}
