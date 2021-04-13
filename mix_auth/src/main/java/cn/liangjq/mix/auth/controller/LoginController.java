package cn.liangjq.mix.auth.controller;

import cn.liangjq.mix.common.redis.util.RedisUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ： liangjianqiang
 * @description ： 登录接口
 * @date ： 2021/4/13
 */
@RestController
@RequestMapping("/auth")
@Slf4j
public class LoginController {

    @Autowired
    private RedisUtil redisUtil;

    @ApiOperation(value = "用户登录", notes = "用户登录")
    @PostMapping("/login")
    public String login(HttpServletRequest request,
                        @ApiParam(name = "username", value = "用户名") @RequestParam(name = "username", required = false) String username,
                        @ApiParam(name = "password", value = "密码") @RequestParam(name = "password", required = false) String password) {
        //TODO 登录逻辑
        return null;
    }
}
