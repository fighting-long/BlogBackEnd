package my.lxh.blog.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author lxh
 * @date 2021-01-29 14:57
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class LoginVo {

    @ApiModelProperty("登陆方式")
    private String mode;

    @ApiModelProperty("登陆明文")
    private String user;

    @ApiModelProperty("登陆密文")
    private String secret;

}
