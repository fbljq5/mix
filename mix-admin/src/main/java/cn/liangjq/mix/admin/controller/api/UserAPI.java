package cn.liangjq.mix.admin.controller.api;

import cn.liangjq.mix.admin.service.IRoleService;
import cn.liangjq.mix.admin.service.IUserService;
import cn.liangjq.mix.common.dto.LoginDTO;
import cn.liangjq.mix.common.dto.user.UserInfoDTO;
import cn.liangjq.mix.common.entity.Role;
import cn.liangjq.mix.common.entity.UserRole;
import cn.liangjq.mix.common.result.R;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ： liangjq
 * @description ： 用户内部接口
 * @date ： 2021/4/22
 */
@Controller
@RequestMapping("/api/user")
@Slf4j
@RequiredArgsConstructor
public class UserAPI {

    private final IUserService userService;
    private final IRoleService roleService;


    @PostMapping("/checkLogin")
    @ResponseBody
    public R checkLoginVO(@RequestBody LoginDTO loginDTO) {
        return userService.checkLoginVO(loginDTO);
    }

    @GetMapping("/findUserByUsername")
    public UserInfoDTO findUserByUsername(String username) {
        return userService.getUserByName(username);
    }

    @GetMapping("/listUserRoleByUserId")
    public List<UserRole> listUserRoleByUserId(Long id) {
        return userService.listUserRoleByUserId(id);
    }

    @GetMapping("/findRoleByRoleId")
    public Role findRoleByRoleId(Long roleId) {
        return roleService.findRoleByRoleId(roleId);
    }
}
