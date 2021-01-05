package my.lxh.blog.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * t_blog和t_Tag的中间表
 * @author lxh
 * @date 2020-12-14 20:41
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@TableName("t_blog_tags")
public class BlogTags {
    @TableId
    private Integer id;
    private Long blogId;
    private Long tagId;
}
