package my.lxh.blog.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 标签表的实体类
 * 主键 、 标签名
 * @author lxh
 * @date 2020-07-05 15:40
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Tag {

    @TableId
    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("标签名")
    private String name;

    @ApiModelProperty("此标签下的博客列表")
    @TableField(exist = false)
    private List<Blog> blogs;



}
