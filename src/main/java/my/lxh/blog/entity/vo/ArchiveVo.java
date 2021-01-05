package my.lxh.blog.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import my.lxh.blog.entity.Blog;

import java.util.List;

/**
 * @author lxh
 * @date 2020-12-24 16:08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ApiModel("归档vo")
public class ArchiveVo {
    @ApiModelProperty("年份")
    private Integer year;
    @ApiModelProperty("当前年份的博客")
    private List<Blog> blogs;
}
