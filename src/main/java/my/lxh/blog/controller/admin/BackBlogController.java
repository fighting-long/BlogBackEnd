package my.lxh.blog.controller.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import my.lxh.blog.entity.Blog;
import my.lxh.blog.service.IBlogService;
import my.lxh.blog.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author lxh
 * @date 2020-12-31 14:14
 */
@CrossOrigin
@RestController
@Api(tags = "后台博客接口")
@RequestMapping("/admin")
public class BackBlogController {

    @Autowired
    private IBlogService blogService;

    @GetMapping("/getBlogs")
    @ApiOperation("获取全部博客信息")
    public ResultUtil<?> getBlogs(){
        return ResultUtil.ok(blogService.listAll());
    }

    @DeleteMapping("/delBlog/{id}")
    @ApiOperation("删除博客")
    public ResultUtil<?> delBlog(@PathVariable Long id){
        System.out.println("删除成功==》"+id);
        return blogService.removeById(id)?ResultUtil.ok():ResultUtil.error();
    }

    @PostMapping("/saveBlog")
    @ApiOperation("保存博客")
    public ResultUtil<?> saveBlog(@RequestBody Blog blog){
        return blogService.saveBlog(blog)?ResultUtil.ok():ResultUtil.error();
    }

    @GetMapping("/getBlogInfo/{id}")
    @ApiOperation("更新博客时获取博客")
    public ResultUtil<?> getBlogInfo(@PathVariable Long id){
        return ResultUtil.ok(blogService.selectBlogInfo(id));
    }

    @PostMapping("/updateBlog")
    @ApiOperation("更新博客")
    public ResultUtil<?> updateBlog(@RequestBody Blog blog){
        return blogService.updateBlog(blog)?ResultUtil.ok():ResultUtil.error();
    }

}
