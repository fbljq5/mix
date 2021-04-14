package cn.liangjq.mix.admin.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: liangjq
 * @Description: 引进其他包
 * @Date: 2021/4/1
 */
@Configuration
@MapperScan(basePackages = "cn.liangjq.mix.service.dao")
@ComponentScan(basePackages = {"cn.liangjq.mix.api.fallback"})
@EnableFeignClients("cn.liangjq.mix.api.service")
public class PackageScanConfig {
}
