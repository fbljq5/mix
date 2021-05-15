package cn.liangjq.mix.admin.aspect;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.spring.PropertyPreFilters;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * @author ： liangjianqiang
 * @description ： 日志切面
 * @date ： 2021/5/15
 */
@Aspect
@Component
@Slf4j
public class LogAspect {

    @Pointcut("execution(public * cn.liangjq.mix..*Controller.*(..))")
    public void controllerPointcut() {
    }

    @Before("controllerPointcut()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        Signature signature = joinPoint.getSignature();
        String name = signature.getName();

        // 开始打印日志
        log.info("----LogAspect打印日志-----");
        log.info("请求地址：{}{}", request.getRequestURL(), request.getMethod());
        log.info("类名方法：{}.{}", signature.getDeclaringTypeName(), name);
        log.info("远程地址：{}", request.getRemoteAddr());

        // 打印请求参数
        Object[] args = joinPoint.getArgs();

        Object[] arguments = new Object[args.length];
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof ServletRequest || args[i] instanceof ServletResponse || args[i] instanceof MultipartFile) {
                continue;
            }
            arguments[i] = args[i];
        }

        // 过滤敏感参数
        String[] excludeProperties = {"password", "file"};
        PropertyPreFilters filters = new PropertyPreFilters();
        PropertyPreFilters.MySimplePropertyPreFilter mySimplePropertyPreFilter = filters.addFilter();
        mySimplePropertyPreFilter.addExcludes(excludeProperties);

        log.info("请求参数：{}" + JSONObject.toJSONString(arguments, mySimplePropertyPreFilter));
    }

    @Around("controllerPointcut()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        // 过滤敏感参数
        String[] excludeProperties = {"password", "file"};
        PropertyPreFilters filters = new PropertyPreFilters();
        PropertyPreFilters.MySimplePropertyPreFilter mySimplePropertyPreFilter = filters.addFilter();
        mySimplePropertyPreFilter.addExcludes(excludeProperties);
        log.info("返回结果：{}", JSONObject.toJSONString(result, mySimplePropertyPreFilter));
        log.info("结束，耗时：{}ms", System.currentTimeMillis() - startTime);
        return result;
    }


}
