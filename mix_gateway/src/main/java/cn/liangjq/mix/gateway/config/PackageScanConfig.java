package cn.liangjq.mix.gateway.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: liangjq
 * @Description: 引进其他包
 * @Date: 2021/4/1
 */
@Configuration
@ComponentScan(basePackages = {"cn.liangjq.mix.common.config","cn.liangjq.mix.utils"})
public class PackageScanConfig {
}
