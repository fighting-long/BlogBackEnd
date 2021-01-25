package my.lxh.blog.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import my.lxh.blog.service.ITypeService;
import my.lxh.blog.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lxh
 * @date 2020-12-13 17:27
 */
@RestController
@Api(tags = "分类接口")
public class TypeController {

    @Autowired
    private ITypeService typeService;

    @GetMapping("/getTypeNum")
    @ApiOperation("获取博客数量占前6的分类")
    public ResultUtil<?> getTypeNum(){
        return ResultUtil.ok(typeService.getTypeNum(6));
    }

    @GetMapping("/getTypeAll")
    @ApiOperation("获取全部分类及此分类的博客数")
    public ResultUtil<?> getTypeAll(){
        return ResultUtil.ok(typeService.getAllType());
    }

}
