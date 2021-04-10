package cn.liangjq.mix.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ： liangjianqiang
 * @description ： 测试
 * @date ： 2021/3/26
 */
@RestController()
@RequestMapping("/auth")
@RefreshScope
public class TestController {

    @Value("${spring.datasource.url}")
    private String url;

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/getToken")
    public String getToken() {
        return discoveryClient.getServices().toString() + url;
    }
}
