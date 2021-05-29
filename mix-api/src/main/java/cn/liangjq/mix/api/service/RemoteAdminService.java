package cn.liangjq.mix.api.service;

import cn.liangjq.mix.api.fallback.RemoteAdminServiceFallbackFactory;
import cn.liangjq.mix.common.dto.LoginDTO;
import cn.liangjq.mix.common.entity.Role;
import cn.liangjq.mix.common.entity.User;
import cn.liangjq.mix.common.entity.UserRole;
import cn.liangjq.mix.common.result.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @Description: 远程管理数据服务
 * @Author: liangjq
 * @Date: 2021/4/22
 */
@FeignClient(contextId = "remoteAdminService", value = "mix-admin", fallbackFactory = RemoteAdminServiceFallbackFactory.class)
public interface RemoteAdminService {

    @PostMapping("/api/user/checkLogin")
    R checkLoginVO(LoginDTO loginDTO);

    @GetMapping("/api/user/findUserByUsername")
    User findUserByUsername(String username);

    @GetMapping("/api/user/listUserRoleByUserId")
    List<UserRole> listUserRoleByUserId(Long id);

    @GetMapping("/api/user/findRoleByRoleId")
    Role findRoleByRoleId(Long roleId);
}