package cn.liangjq.mix.api.service;

import cn.liangjq.mix.api.fallback.RemoteHelloServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(contextId = "remoteAuthService", value = "mix-auth", fallbackFactory = RemoteHelloServiceFallbackFactory.class)
public interface RemoteAuthService {

    @GetMapping("/auth/test")
    String test();

}