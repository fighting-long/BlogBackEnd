package my.lxh.blog.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 这里的“/*” 表示的是需要拦截的请求路径
 * 使用filter 解决跨域请求
 * @author lxh
 */
@Component
@WebFilter(urlPatterns = "/*", filterName = "myCorsFilter")
public class MyCorsFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        // 允许跨域的地址为所有
        httpResponse.setHeader("Access-Control-Allow-Origin", "*");
        // 非简单请求，只要第一次通过OPTIONS检查 在1小时之内不会在调用OPTIONS进行检测
        httpResponse.addHeader("Access-Control-Max-Age","3600");
        // 带有Cookie的跨域请求，此值必须设置为true。
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpResponse.setHeader("Access-Control-Allow-Headers", "*");
        httpResponse.setHeader("Access-Control-Allow-Methods", "*");
        filterChain.doFilter(servletRequest, httpResponse);
    }

    @Override
    public void destroy() {
    }

}