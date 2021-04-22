package cn.liangjq.mix.auth.security;

import cn.liangjq.mix.api.service.RemoteAdminService;
import cn.liangjq.mix.common.entity.User;
import cn.liangjq.mix.common.entity.UserRole;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private RemoteAdminService remoteAdminService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        if (s == null || "".equals(s)) {
            throw new RuntimeException("用户不能为空");
        }
        // 调用方法查询用户
        User user = remoteAdminService.findUserByUsername(s);
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        List<UserRole> userRoles = remoteAdminService.listUserRoleByUserId(user.getId());
        if (CollectionUtils.isEmpty(userRoles)) {
            return new org.springframework.security.core.userdetails.User(user.getUserName(),
                    user.getPassword(), authorities);
        }
        userRoles.forEach(userRole -> authorities.add(new SimpleGrantedAuthority("ROLE_" +
                remoteAdminService.findRoleByRoleId(userRole.getRoleId()).getRoleName())));
        return new org.springframework.security.core.userdetails.User(user.getUserName(),
                user.getPassword(), authorities);
    }
}