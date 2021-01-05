package my.lxh.blog.controller.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import my.lxh.blog.entity.Tag;
import my.lxh.blog.service.ITagService;
import my.lxh.blog.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author lxh
 * @date 2020-12-29 14:53
 */
@CrossOrigin
@RestController
@RequestMapping("/admin")
@Api(tags = "后端标签接口")
public class BackTagController {

    @Autowired
    private ITagService tagService;

    @DeleteMapping("/deleteTagById/{id}")
    @ApiOperation("删除标签")
    public ResultUtil<?> deleteTag(@PathVariable Long id){
        return tagService.removeById(id)?ResultUtil.ok():ResultUtil.error();
    }

    @PutMapping("/editTagName")
    @ApiOperation("更新标签")
    public ResultUtil<?> updateTag(@RequestBody Tag tag){
        return tagService.updateById(tag)?ResultUtil.ok():ResultUtil.error();
    }

    @PostMapping("/saveTag")
    @ApiOperation("保存标签")
    public ResultUtil<?> saveTag(@RequestBody Tag tag){
        return tagService.save(tag)?ResultUtil.ok():ResultUtil.error();
    }


}
