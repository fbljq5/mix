package cn.liangjq.mix.auth.controller;

import cn.liangjq.mix.common.dto.LoginVO;
import cn.liangjq.mix.common.result.R;
import cn.liangjq.mix.service.interfaces.IUserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: liangjq
 * @Description: 登录接口
 * @Date: 2021/4/13
 */
@RestController
@RequestMapping("/auth")
@Slf4j
@RequiredArgsConstructor
public class LoginController {

    private final IUserService userService;

    @ApiOperation(value = "用户登录", notes = "用户登录")
    @PostMapping("/login")
    public R login(@RequestBody LoginVO loginVO) {
        return userService.checkLoginVO(loginVO);
    }
}
