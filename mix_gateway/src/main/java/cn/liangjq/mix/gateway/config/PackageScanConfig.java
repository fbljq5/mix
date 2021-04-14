package cn.liangjq.mix.gateway.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: liangjq
 * @Description: 引进其他包
 * @Date: 2021/4/1
 */
@Configuration
@ComponentScan(basePackages = {"cn.liangjq.mix.utils"})
//@MapperScan(basePackages = "cn.liangjq.mix.service.dao")
public class PackageScanConfig {
}
