package my.lxh.blog.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import my.lxh.blog.entity.Blog;
import my.lxh.blog.entity.vo.ArchiveVo;
import my.lxh.blog.service.IBlogService;
import my.lxh.blog.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author lxh
 * @date 2020-12-10 21:05
 */
@RestController
@Api(tags = "博客接口")
public class BlogController {

    @Autowired
    private IBlogService blogService;

    /**
     * 概览 ： 主页分页查询博客 及其 分类
     * @param startPage
     * @param size
     * @return
     */
    @GetMapping("/getAllView/{startPage}/{size}")
    @ApiOperation("概览 ： 主页分页查询博客 及其 分类")
    public ResultUtil<?> getAll(@PathVariable Integer startPage,
                                @PathVariable Integer size){
        return ResultUtil.ok(blogService.getBlogsView(startPage, size,null));
    }

    /**
     * 概览 ： 主页分页查询博客 及其 分类
     * @param startPage
     * @param size
     * @return
     */
    @GetMapping("/searchAllView/{startPage}/{size}")
    @ApiOperation("概览 ： 主页分页用关键字查询博客 及其 分类")
    public ResultUtil<?> getAllBySearch(@PathVariable Integer startPage,
                                        @PathVariable Integer size,
                                        @RequestParam(required = false) String keyword){
        return ResultUtil.ok(blogService.getBlogsViewBySearch(startPage, size,keyword));
    }

    /**
     * 概览 ： 主页分页查询博客 及其 分类 通过typeId
     * @param startPage
     * @param size
     * @param typeId
     * @return
     */
    @GetMapping("/getAllByTypeId/{startPage}/{size}/{typeId}")
    @ApiOperation("概览 ： 主页分页查询博客 及其 分类 通过typeId")
    public ResultUtil<?> getAllByType(@PathVariable Integer startPage,
                                      @PathVariable Integer size,
                                      @PathVariable Integer typeId){
        return ResultUtil.ok(blogService.getBlogsView(startPage,size,typeId));
    }

    /**
     * 概览 ： 主页分页查询博客 及其 分类 通过tagid
     * @param startPage
     * @param size
     * @param tagId
     * @return
     */
    @GetMapping("/getAllByTagId/{startPage}/{size}/{tagId}")
    @ApiOperation("概览 ： 主页分页查询博客 及其 分类和标签 通过tagid")
    public ResultUtil<?> getAllByTagId(@PathVariable Integer startPage,
                                       @PathVariable Integer size,
                                       @PathVariable Integer tagId){
        return ResultUtil.ok(blogService.getBlogsViewByTagId(startPage,size,tagId));
    }


    /**
     * 获取最新的4个博客
     * @return
     */
    @GetMapping("/getNewB")
    @ApiOperation("获取最新的4个博客")
    public ResultUtil<?> getNewBlog(){
        // 最新4条博客
        return ResultUtil.ok(blogService.list(new QueryWrapper<Blog>()
                .lambda()
                .select(Blog::getId,Blog::getTitle)
                .eq(Blog::getPublished,true)
                .orderByDesc(Blog::getUpdateTime)).stream().limit(4).toArray());
    }

    /**
     * 获取最热的4条博客
     * @return
     */
    @GetMapping("/getHotB")
    @ApiOperation("获取最热的4条博客")
    public ResultUtil<?> getHotBlog(){
        // 最热4条博客
        return ResultUtil.ok(blogService.list(new QueryWrapper<Blog>()
                .lambda()
                .select(Blog::getId,Blog::getTitle)
                .eq(Blog::getPublished,true)
                .orderByDesc(Blog::getViews)).stream().limit(4).toArray());
    }


    @GetMapping("/getArchive")
    @ApiOperation("获取博客归档")
    public ResultUtil<?> getBlogArchive(){
        List<ArchiveVo> archive = blogService.getBlogsArchive();
        return ResultUtil.ok(archive,String.valueOf(archive.stream().mapToInt(vo -> vo.getBlogs().size()).sum()));
    }


    @GetMapping("/getBlogDetail/{id}")
    @ApiOperation("获取博客详情")
    public ResultUtil<?> getBlogDetail(@PathVariable Integer id){
        return ResultUtil.ok(blogService.getBlogDetailById(id));
    }


}
