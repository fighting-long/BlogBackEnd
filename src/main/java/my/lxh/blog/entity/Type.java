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
 * 分类表的实体类
 * 主键 、 分类名
 * @author lxh
 * @date 2020-07-05 15:35
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Type {

    @TableId
    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("类型名")
    private String name;

    @ApiModelProperty("此分类下的标签列表")
    @TableField(exist = false)
    private List<Blog> blogs;


}
