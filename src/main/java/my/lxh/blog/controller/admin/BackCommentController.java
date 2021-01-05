package my.lxh.blog.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import my.lxh.blog.entity.Comment;
import my.lxh.blog.service.ICommentService;
import my.lxh.blog.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author lxh
 * @date 2020-12-30 9:40
 */
@CrossOrigin
@RestController
@Api(tags = "后台评论接口")
@RequestMapping("/admin")
public class BackCommentController {

    @Autowired
    private ICommentService commentService;

    @GetMapping("/listComments")
    @ApiOperation("获取全部评论除了admin")
    public ResultUtil<?> getAllComment(){
        return ResultUtil.ok(commentService.listComments());
    }

    @GetMapping("/listUnCheck")
    @ApiOperation("获取未读评论条数")
    public ResultUtil<?> getCommentInfo(){
        return ResultUtil.ok(commentService.list(new QueryWrapper<Comment>().lambda()
                .eq(Comment::getChecked,false)));
    }

    @GetMapping("/checkedComment/{id}")
    @ApiOperation("读一条评论")
    public ResultUtil<?> checkComment(@PathVariable Long id){
        return commentService.lambdaUpdate()
                .set(Comment::getChecked,true)
                .eq(Comment::getId,id)
                .eq(Comment::getChecked,false)
                .update()?ResultUtil.ok():ResultUtil.failure("无需已读！");
    }

    @GetMapping("/checkedComments")
    @ApiOperation("读全部评论")
    public ResultUtil<?> checkComments(){
        System.out.println("执行了");
        return commentService.lambdaUpdate()
                .set(Comment::getChecked,true)
                .eq(Comment::getChecked,false)
                .update()?ResultUtil.ok():ResultUtil.failure("暂无评论需要已读！");
    }

    @DeleteMapping("/deleteComment/{id}")
    @ApiOperation("删除一条评论")
    public ResultUtil<?> deleteComment(@PathVariable Long id){
        commentService.remove(new QueryWrapper<Comment>()
                .lambda()
                .eq(Comment::getParentCommentId,id));
        return commentService.removeById(id)?ResultUtil.ok():ResultUtil.error();
    }

    @PostMapping("/replyByAdmin")
    @ApiOperation("博主回复")
    public ResultUtil<?> adminReply(@RequestBody Comment comment){
        return commentService.saveCommentByAdmin(comment)?ResultUtil.ok():ResultUtil.error();
    }


}
