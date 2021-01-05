package my.lxh.blog.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import my.lxh.blog.entity.Config;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lxh
 * @date 2020-12-30 15:11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class InfoVo {
    private Integer id;
    private String infoPicture;
    private String infoDescription;
    private List<String> userTag;
    private List<String> techTag;

    private String nickname;
    private String email;
    private String avatar;

    public InfoVo(Config config){
        this.id=config.getId();
        this.infoPicture=config.getPicture();
        this.infoDescription=config.getMyDescription();
        this.userTag=new ArrayList<>(List.of(config.getUserTag().substring(0,config.getUserTag().length()-1).split(",")));
        this.techTag=new ArrayList<>(List.of(config.getTechTag().substring(0,config.getTechTag().length()-1).split(",")));
    }

}
