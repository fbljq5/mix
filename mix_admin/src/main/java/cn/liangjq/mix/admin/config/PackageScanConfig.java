package cn.liangjq.mix.admin.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: liangjq
 * @Description: 引进其他包
 * @Date: 2021/4/1
 */
@Configuration
@ComponentScan(basePackages = {"cn.liangjq.mix.api.fallback","cn.liangjq.mix.common.config","cn.liangjq.mix.utils"})
@EnableFeignClients("cn.liangjq.mix.api.service")
public class PackageScanConfig {
}
