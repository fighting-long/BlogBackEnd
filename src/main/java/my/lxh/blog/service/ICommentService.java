package my.lxh.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import my.lxh.blog.entity.Comment;

import java.util.List;

/**
 * @author lxh
 * @date 2020-12-26 11:15
 */
public interface ICommentService extends IService<Comment> {
    /**
     * 获取评论
     * @param id 博客id
     * @return
     */
    List<Comment> getComments(Long id);

    /**
     * 保存评论
     * @param comment
     * @return
     */
    boolean saveComment(Comment comment);

    /**
     * 获取全部评论除了admin
     * @return
     */
    List<Comment> listComments();

    /**
     * 保存博主的评论
     * @param comment
     * @return
     */
    Boolean saveCommentByAdmin(Comment comment);
}
