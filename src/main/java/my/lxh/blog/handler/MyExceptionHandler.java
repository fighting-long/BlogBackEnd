package my.lxh.blog.handler;

import my.lxh.blog.exception.BlogException;
import my.lxh.blog.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lxh
 * @date 2020-12-09 21:15
 */
@RestControllerAdvice
public class MyExceptionHandler {

    private final Logger logger=LoggerFactory.getLogger(MyExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResultUtil<?> test(HttpServletRequest request,Exception e){
        logger.error("Request URL : {} , Exception : {}",request.getRequestURL(),e);
        return ResultUtil.error(e.getMessage());
    }

    /**
     * 处理一些操作不当的异常，如：查询不存在的数据，未经验证登录后台等
     * @param e
     * @return
     */
    @ExceptionHandler(BlogException.class)
    public ResultUtil<?> handleOperationException(HttpServletRequest request, BlogException e){
        logger.error("Request URL : {} , Exception : {}",request.getRequestURL(),e);
        return ResultUtil.failure(e.getMessage());
    }


}
