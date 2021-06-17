package my.lxh.blog.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

/**
 * 评论的实体类
 * 主键 、 昵称 、邮箱 、 内容 、头像 、 发表时间
 * @author lxh
 * @date 2020-07-05 15:42
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Comment implements Cloneable,Comparable<Comment>{

    @TableId
    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("昵称")
    private String nickname;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("评论内容")
    private String content;

    @ApiModelProperty("头像")
    private String avatar;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("是否是博主")
    private Boolean admin;

    @ApiModelProperty("是否被审核")
    private Boolean checked;

    @ApiModelProperty("博客，评论所属博客")
    @TableField(exist = false)
    private Blog blog;

    @ApiModelProperty("所属博客id")
    private Integer blogId;

    @ApiModelProperty("子评论")
    @TableField(exist = false)
    private List<Comment> replyComments;

    @ApiModelProperty("父评论id")
    private Integer parentCommentId;

    @ApiModelProperty("父评论")
    @TableField(exist = false)
    private Comment parentComment;

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public int compareTo(Comment o) {
        return this.createTime.compareTo(o.createTime);
    }
}
