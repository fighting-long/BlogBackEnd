package my.lxh.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import my.lxh.blog.entity.Comment;
import my.lxh.blog.mapper.CommentsMapper;
import my.lxh.blog.service.ICommentService;
import my.lxh.blog.ws.CommentEndpoint;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.websocket.EncodeException;
import java.io.IOException;
import java.util.*;

/**
 * @author lxh
 * @date 2020-12-26 11:16
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentsMapper, Comment> implements ICommentService {

    private final List<Comment> comments = new ArrayList<>();

    @Override
    public List<Comment> getComments(Long id) {
        List<Comment> all = baseMapper.selectList(new QueryWrapper<Comment>()
                .eq("blog_id", id)
                .orderByDesc("admin")
                .orderByDesc("create_time"));
        List<Comment> parent = new ArrayList<>();
        List<Comment> child = new ArrayList<>();
        all.forEach(comment -> {
            if (Objects.isNull(comment.getParentCommentId())) {
                parent.add(comment);
            } else {
                child.add(comment);
            }
        });
        // 对评论进行合并
        for (Comment c : parent) {
            mergeComment(child, c);
            c.setReplyComments(new ArrayList<>(this.comments));
            this.comments.clear();
        }
        return parent;
    }

    /**
     * 对每级评论进行合并
     *
     * @param child
     * @param comment
     */
    private void mergeComment(List<Comment> child, Comment comment) {
        List<Comment> temp = new ArrayList<>();
        for (Comment c : child) {
            if (comment.getId().compareTo(c.getParentCommentId()) == 0) {
                try {
                    c.setParentComment((Comment) comment.clone());
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
                temp.add(c);
            }
        }
        if (temp.size() == 0) {
            return;
        }
        for (Comment c : temp) {
            mergeComment(child, c);
        }
        comments.addAll(temp);
    }

    @Override
    @Transactional
    public boolean saveComment(Comment comment) {
        // 设置一些默认值
        comment.setCreateTime(new Date())
                .setChecked(false)
                // 随机分配，和博主头像不一样  == 1~4 ==
                .setAvatar("http://studywithu.cn/static/images/avatar/liuli_" + (new Random().nextInt(4) + 1) + ".jpg");
        // 判断博主 ， 设置一些默认值
        String content = comment.getContent();
        if (content.startsWith("<lxh>") && content.endsWith("</lxh>")) {
            comment.setContent(content.substring(5, content.length() - 6))
                    .setAdmin(true)
                    .setChecked(true)
                    .setNickname("博主");
        }
        boolean save = this.save(comment);
        //评论成功并且不是博主 才向前端发送数据
        if (save && Objects.isNull(comment.getAdmin())) {
            notifyFront();
        }
        return save;
    }

    @Override
    public List<Comment> listComments() {
        return baseMapper.getAllComments();
    }

    @Override
    public Boolean saveCommentByAdmin(Comment comment) {
        comment.setAdmin(true)
                .setChecked(true)
                .setNickname("博主")
                .setCreateTime(new Date())
                .setEmail("lxh.ac@outlook.com")
                //固定一个
                .setAvatar("http://studywithu.cn/static/images/avatar/liuli_5.jpg");
        return this.save(comment);
    }

    private void notifyFront() {
        CommentEndpoint.sessions.forEach(session -> {
            try {
                session.getBasicRemote().sendObject(true);
            } catch (IOException | EncodeException e) {
                e.printStackTrace();
            }
        });
    }
}
