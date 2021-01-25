package my.lxh.blog.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import my.lxh.blog.service.ITagService;
import my.lxh.blog.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lxh
 * @date 2020-12-13 17:27
 */
@RestController
@Api(tags = "标签接口")
public class TagController {

    @Autowired
    private ITagService tagService;

    @GetMapping("/getTagNum")
    @ApiOperation("获取博客数量占前6的标签")
    public ResultUtil<?> getTagNum(){
        return ResultUtil.ok(tagService.getTagNum(6));
    }

    @GetMapping("/getTagAll")
    @ApiOperation("获取全部标签及其下博客数量")
    public ResultUtil<?> getTagAll(){
        return ResultUtil.ok(tagService.getAllTag());
    }

}
