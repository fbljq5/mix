package cn.liangjq.mix.admin.controller;

import cn.liangjq.mix.common.dao.UserMapper;
import cn.liangjq.mix.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ： liangjianqiang
 * @description ： 测试
 * @date ： 2021/3/26
 */
@RestController
@RequestMapping("/config")
@RefreshScope
public class TestController {

    @Value("${spring.datasource.url}")
    private String url;

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/get")
    public String get() {
        User user = userMapper.selectByPrimaryKey(1L);
        return url+user.toString();
    }
}
