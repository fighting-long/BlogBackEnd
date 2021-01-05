package my.lxh.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import my.lxh.blog.entity.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lxh
 * @date 2020-12-26 11:16
 */
@Repository
public interface CommentsMapper extends BaseMapper<Comment> {
    /**
     * 获取全部评论
     * @return
     */
    List<Comment> getAllComments();
}
