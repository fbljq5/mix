package cn.liangjq.mix.admin.controller;

import cn.liangjq.mix.admin.service.IRoleService;
import cn.liangjq.mix.admin.service.IUserService;
import cn.liangjq.mix.common.dto.LoginVO;
import cn.liangjq.mix.common.entity.Role;
import cn.liangjq.mix.common.entity.User;
import cn.liangjq.mix.common.entity.UserRole;
import cn.liangjq.mix.common.result.R;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Logger logger = LoggerFactory.getLogger(UserAPI.class);

    private final IUserService userService;
    private final IRoleService roleService;


    @PostMapping("/checkLogin")
    @ResponseBody
    public R checkLoginVO(@RequestBody LoginVO loginVO) {
        return userService.checkLoginVO(loginVO);
    }

    @GetMapping("/findUserByUsername")
    public User findUserByUsername(String username) {
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
