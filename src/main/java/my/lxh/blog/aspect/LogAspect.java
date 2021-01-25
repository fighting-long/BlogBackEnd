package my.lxh.blog.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Objects;

/**
 * 日志记录 ， 使用aop
 * @author lxh
 * @date 2020-07-04 14:32
 */
@Aspect
@Component
public class LogAspect {

    private final Logger logger= LoggerFactory.getLogger(LogAspect.class);

    /**
     *      * my.lxh.blog.controller..*.*(..))
     *      切面为 所有访问权限 下的 my.lxh.blog.controller 下的所有类的所有方法
     *
     */
    @Pointcut("execution(* my.lxh.blog.controller..*.*(..))")
    public void log(){}

    /**
     * 经过切面前做的事
     */
    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        //获取 HttpServletRequest（接口） 对象
        ServletRequestAttributes attributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();
        //获取url、ip地址、访问方法还有参数
        String url = request.getRequestURL().toString();
        String ip = request.getRemoteAddr();
        String classMethod = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        RequestLog requestLog= new RequestLog(url, ip, classMethod, args);
        //将封装好的请求日志对象放入日志
        logger.info("Request : {}",requestLog);
    }

    /**
     * 经过切面后做的事
     */
    @After("log()")
    public void doAfter(){
//        logger.info("------doAfter-------");
    }

    /**
     * 经过切面后 获取到方法返回值
     * @param result
     */
    @AfterReturning(returning = "result",pointcut = "log()")
    public void doAfterReturning(Object result){
        //logger.info("Result : {}",result);
    }


    /**
     * 请求日志内部类
     */
    private static class RequestLog{
        private final String url;
        private final String ip;
        private final String classMethod;
        private final Object[] args;

        public RequestLog(String url, String ip, String classMethod, Object[] args) {
            this.url = url;
            this.ip = ip;
            this.classMethod = classMethod;
            this.args = args;
        }

        @Override
        public String toString() {
            return "{" +
                    "url='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", classMethod='" + classMethod + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }
    }

}
