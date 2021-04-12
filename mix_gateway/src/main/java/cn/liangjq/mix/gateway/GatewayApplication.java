package cn.liangjq.mix.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author ： liangjianqiang
 * @description ： 网关启动类
 * @date ： 2021/3/30
 */
@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages = "cn.liangjq.mix.common.service")
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}
