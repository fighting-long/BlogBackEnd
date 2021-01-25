package my.lxh.blog.controller.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import my.lxh.blog.entity.Type;
import my.lxh.blog.service.ITypeService;
import my.lxh.blog.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author lxh
 * @date 2020-12-29 14:53
 */
@RestController
@RequestMapping("/admin")
@Api(tags = "后端分类接口")
public class BackTypeController {

    @Autowired
    private ITypeService typeService;

    @DeleteMapping("/deleteTypeById/{id}")
    @ApiOperation("删除分类")
    public ResultUtil<?> deleteType(@PathVariable Long id){
        return typeService.removeById(id)?ResultUtil.ok():ResultUtil.error();
    }

    @PutMapping("/editTypeName")
    @ApiOperation("更新分类")
    public ResultUtil<?> updateType(@RequestBody Type type){
        return typeService.updateById(type)?ResultUtil.ok():ResultUtil.error();
    }

    @PostMapping("/saveType")
    @ApiOperation("保存分类")
    public ResultUtil<?> saveType(@RequestBody Type type){
        return typeService.save(type)?ResultUtil.ok():ResultUtil.error();
    }


}
