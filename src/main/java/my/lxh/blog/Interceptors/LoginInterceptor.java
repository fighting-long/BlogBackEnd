package my.lxh.blog.Interceptors;

import my.lxh.blog.exception.BlogException;
import my.lxh.blog.utils.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * @author lxh
 * @date 2020-12-09 21:11
 */
public class LoginInterceptor implements HandlerInterceptor {

    private final Logger logger= LoggerFactory.getLogger(LoginInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 放行所有OPTIONS请求？
        if("OPTIONS".equals(request.getMethod())){
            return true;
        }
        //预检请求option，设置它的可以放置数据的格式
        response.setHeader("Access-Control-Allow-Origin","*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, PATCH");
        response.setHeader("Access-Control-Allow-Headers","Origin, X-Requested-With, Content-Type, Accept, Authorization");
        // 前台接口不需要保证安全？，后台管理接口需要
        String token = request.getHeader("token");
        if(Objects.isNull(token)){
            logger.error("非法访问，ip:{}",request.getRemoteAddr());
            throw new BlogException("请先登录！");
        }
        try {
            JwtUtil.verify(token);
        } catch (Exception e) {
            logger.error("非法访问，ip:{}",request.getRemoteAddr());
            throw new BlogException("身份过期，请重新登录！");
        }
        return true;
    }

}
