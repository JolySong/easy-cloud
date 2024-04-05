package cn.iomc.common.aop;

import cn.iomc.common.constant.UserInfo;
import cn.iomc.common.result.Result;
import cn.iomc.common.utils.LogUtil;
import cn.iomc.common.utils.UserInfoContext;
import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.Arrays;

/**
 * @Author song
 * @Date 2024/3/28 04:53
 * @Version 1.0
 */

@Aspect
@Component
public class UserAop {

    /**
     * 在方法调用之前，打印入参
     */
    @Before(value = "execution(public * cn.iomc.*.controller.*.*(..))")
    public void before(JoinPoint joinPoint) {

        // todo 集成权限认证再做调整
        UserInfoContext.serUserInfo("admin", "管理员");

        // 类名
        String className = joinPoint.getTarget().getClass().getName();
        // 方法名
        String methodName = ((MethodSignature) joinPoint.getSignature()).getMethod().getName();
        // 过滤部分参数
        Object[] args = joinPoint.getArgs();
        Object[] arguments = new Object[args.length];
        for(int i = 0 ; i < args.length; i++){
            if(args[i] instanceof ServletRequest
                    || args[i] instanceof ServletResponse
                    || args[i] instanceof MultipartFile){
                continue;
            }
            arguments[i] = args[i];
        }
        // 请求的参数
        String params = "";
        // 用户ID
        UserInfo user = null;
        try {
            // 请求的参数
            params = JSON.toJSONString(arguments);

            // 获取请求的用户
            user = UserInfoContext.getUserInfo();
        } catch (Exception e){
            LogUtil.debug("请求当前用户或参数异常 ： " + e.getMessage() + Arrays.toString(e.getStackTrace()));
        }

        // 记录下请求内容
        LogUtil.info("用户= " + user + ", 方法路径=" + className+methodName + ", 入参类型=" + params);
    }


    /**
     * 异常捕获并打印日志
     *
     * @param joinPoint
     * @return
     */
    @Around(value = "execution(public * cn.iomc.*.controller.*.*(..))")
    public Object catchException(ProceedingJoinPoint joinPoint) {

        try {
            return joinPoint.proceed();
        } catch (Throwable e) {
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            LogUtil.error("在" + className + "的" + methodName + "中，发生了异常："
                    + e.getMessage() + Arrays.toString(e.getStackTrace()));
            return Result.ERROR().message("服务器异常，请稍后再试");
        }
    }

    /**
     * 清除thread-local
     *
     * @param joinPoint
     */
    @After(value = "execution(public * cn.iomc.*.controller.*.*(..))")
    public void after(JoinPoint joinPoint) {

        // 清除当前线程用户
        UserInfoContext.clean();
    }
}
