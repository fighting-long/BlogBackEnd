package my.lxh.blog.handler;

import my.lxh.blog.exception.BlogException;
import my.lxh.blog.utils.ResultUtil;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author lxh
 * @date 2020-12-09 21:15
 */
@RestControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResultUtil<?> test(Exception e){
        e.printStackTrace();
        return ResultUtil.error(e.getMessage());
    }

    /**
     * 处理一些操作不当的异常，如：查询不存在的数据，未经验证登录后台等
     * @param e
     * @return
     */
    @ExceptionHandler(BlogException.class)
    public ResultUtil<?> handleOperationException(BlogException e){
        e.printStackTrace();
        return ResultUtil.failure(e.getMessage());
    }


}
