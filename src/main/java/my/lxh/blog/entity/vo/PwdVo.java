package my.lxh.blog.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author lxh
 * @date 2020-12-30 17:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class PwdVo {
    private String username;
    private String oldPwd;
    private String newPwd;
}
