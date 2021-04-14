package cn.liangjq.mix.auth.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: liangjq
 * @Description: 引进其他包
 * @Date: 2021/4/1
 */
@Configuration
@ComponentScan(basePackages = {"cn.liangjq.mix.common", "cn.liangjq.mix.utils", "cn.liangjq.mix.service.impl", "cn.liangjq.mix.service.interfaces"})
@MapperScan(basePackages = {"cn.liangjq.mix.service.dao"})
public class PackageScanConfig {
}
