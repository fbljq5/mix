package cn.liangjq.mix.api.fallback;

import cn.liangjq.mix.api.service.RemoteAuthService;
import org.springframework.stereotype.Component;

/**
 * @Description: 远程校验接口实现
 * @Author: liangjq
 * @Date: 2021/4/1
 */
@Component
public class RemoteAuthServiceFallbackImpl implements RemoteAuthService {

    private Throwable cause;

    @Override
    public String test() {
        System.out.println("失败了");
        return null;
    }

    public Throwable getCause() {
        return cause;
    }

    public void setCause(Throwable cause) {
        this.cause = cause;
    }
}
