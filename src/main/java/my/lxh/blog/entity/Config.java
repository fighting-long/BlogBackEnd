package my.lxh.blog.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import my.lxh.blog.entity.vo.InfoVo;

/**
 * @author lxh
 * @date 2020-12-30 15:10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Config {

    @TableId
    private Integer id;
    private String picture;
    private String myDescription;
    private String userTag;
    private String techTag;

    public Config(InfoVo infoVo){
        this.id=infoVo.getId();
        this.picture=infoVo.getInfoPicture();
        this.myDescription=infoVo.getInfoDescription();
        StringBuilder sb=new StringBuilder();
        infoVo.getUserTag().forEach(s->sb.append(s).append(","));
        this.userTag=sb.toString();
        StringBuilder sb1=new StringBuilder();
        infoVo.getTechTag().forEach(s->sb1.append(s).append(","));
        this.techTag=sb1.toString();
    }

}
