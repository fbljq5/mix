package cn.liangjq.mix.admin.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: liangjq
 * @Description: 测试
 * @Date: 2021/3/26
 */
@RestController
@RequestMapping("/t")
@RefreshScope
public class TestController {

    @Value("${spring.datasource.url}")
    private String url;

//    @Autowired
//    private UserMapper userMapper;
//
//    @Autowired
//    private RemoteAuthService authService;

    @GetMapping("/get")
    public String testGet() {
//        User user = userMapper.selectByPrimaryKey(1L);
        return "admin";
    }
}
