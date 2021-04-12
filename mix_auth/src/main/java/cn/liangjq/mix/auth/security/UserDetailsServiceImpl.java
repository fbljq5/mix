package cn.liangjq.mix.auth.security;

import cn.liangjq.mix.common.db.dao.RoleMapper;
import cn.liangjq.mix.common.db.dao.UserMapper;
import cn.liangjq.mix.common.db.dao.UserRoleMapper;
import cn.liangjq.mix.common.db.entity.User;
import cn.liangjq.mix.common.db.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        if (s == null || "".equals(s)) {
            throw new RuntimeException("用户不能为空");
        }
        // 调用方法查询用户
        User user = userMapper.findUserByUsername(s);
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        List<UserRole> userRoles = userRoleMapper.selectByUserId(user.getId());
        if (CollectionUtils.isEmpty(userRoles)) {
            return new org.springframework.security.core.userdetails.User(user.getUserName(),
                    user.getPassword(), authorities);
        }
        userRoles.forEach(userRole -> authorities.add(new SimpleGrantedAuthority("ROLE_" +
                roleMapper.selectByPrimaryKey(userRole.getRoleId()).getRoleName())));
        return new org.springframework.security.core.userdetails.User(user.getUserName(),
                user.getPassword(), authorities);
    }
}