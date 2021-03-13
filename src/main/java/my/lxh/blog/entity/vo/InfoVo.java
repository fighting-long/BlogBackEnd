package my.lxh.blog.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import my.lxh.blog.entity.User;

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

    public InfoVo(User user){
        this.id= user.getId();
        this.infoPicture= user.getPicture();
        this.infoDescription= user.getMyDescription();
        this.userTag=new ArrayList<>(List.of(user.getUserTag().substring(0, user.getUserTag().length()-1).split(",")));
        this.techTag=new ArrayList<>(List.of(user.getTechTag().substring(0, user.getTechTag().length()-1).split(",")));
        this.avatar=user.getAvatar();
        this.nickname=user.getNickname();
        this.email=user.getEmail();
    }

}
