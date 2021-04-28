package cn.liangjq.mix.monitor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 测试
 * @Author: liangjq
 * @Date: 2021/3/26
 */
@RestController()
@RequestMapping("/monitor")
@RefreshScope
public class TestController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/test")
    public String test() {
        return discoveryClient.getServices().toString() + "21321321";
    }
}
