package my.lxh.blog.utils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import my.lxh.blog.enums.OperateResult;

/**
 * @author lxh
 * @date 2020-12-11 22:05
 * 写上 apimodel注解 。。
 */
@Data
@ApiModel("响应结果")
public class ResultUtil<T> {

    @ApiModelProperty("响应数据")
    private T data ;

    @ApiModelProperty("响应信息")
    private String message;

    @ApiModelProperty("自定义响应状态码")
    private Integer code;


    public static ResultUtil<?> ok(){
        return fillResult(OperateResult.SUCCESS,null,null);
    }

    public static ResultUtil<?> ok(Object data){
        return fillResult(OperateResult.SUCCESS,data,null);
    }

    public static ResultUtil<?> ok(Object data, String message){
        return fillResult(OperateResult.SUCCESS,data,message);
    }

    public static ResultUtil<?> failure(String message){
        return fillResult(OperateResult.FAILURE,null,message);
    }

    public static ResultUtil<?> error(){
        return fillResult(OperateResult.ERROR,null,null);
    }
    public static ResultUtil<?> error(Object data){
        return fillResult(OperateResult.ERROR,data,null);
    }

    private static ResultUtil<?> fillResult(OperateResult operate, Object data, String message){
        ResultUtil<Object> resultUtil = new ResultUtil<>();
        resultUtil.data=data;
        resultUtil.message=message==null?operate.getMsg():message;
        resultUtil.code=operate.getCode();
        return resultUtil;
    }

}
