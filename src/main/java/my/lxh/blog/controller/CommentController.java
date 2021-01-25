package my.lxh.blog.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import my.lxh.blog.entity.Comment;
import my.lxh.blog.service.ICommentService;
import my.lxh.blog.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author lxh
 * @date 2020-12-26 11:11
 */
@RestController
@Api(tags = "评论接口")
public class CommentController {

    @Autowired
    private ICommentService commentService;

    /**
     * 根据博客id获取评论
     * @param id
     * @return
     */
    @GetMapping("/getComment/{id}")
    @ApiOperation("获取某博客下的评论")
    public ResultUtil<?> getCommentById(@PathVariable Long id){
            return ResultUtil.ok(commentService.getComments(id));
    }

    @PostMapping("/saveComment")
    @ApiOperation("保存博客")
    public ResultUtil<?> saveComment(@RequestBody Comment comment){
        return commentService.saveComment(comment)?ResultUtil.ok():ResultUtil.error();
    }



}
