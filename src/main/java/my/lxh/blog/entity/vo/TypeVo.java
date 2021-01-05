package my.lxh.blog.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author lxh
 * @date 2020-12-24 21:31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class TypeVo {
    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("类型名")
    private String name;

    @ApiModelProperty("博客数量")
    private Integer blogCount;
}
