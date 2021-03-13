package my.lxh.blog.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import my.lxh.blog.entity.vo.InfoVo;

import java.util.Date;
import java.util.List;

/**
 * 用户的实体类
 * 主键 、 昵称 、 用户名 、密码 、邮箱 、 类型 、 头像 、 创建时间 、 更新时间
 *
 * @author lxh
 * @date 2020-07-05 15:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class User {

    @TableId
    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("昵称")
    private String nickname;

    @ApiModelProperty("用户名，登录用")
    private String username;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("盐")
    private String salt;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("类型")
    private Integer type;

    @ApiModelProperty("头像")
    private String avatar;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("更新时间")
    private Date updateTime;

    @ApiModelProperty("此用户的博客列表")
    @TableField(exist = false)
    private List<Blog> blogs;

    @ApiModelProperty("首图")
    private String picture;

    @ApiModelProperty("描述")
    private String myDescription;

    @ApiModelProperty("个人标签")
    private String userTag;

    @ApiModelProperty("技术标签")
    private String techTag;

    public User(InfoVo infoVo){
        this.id=infoVo.getId();
        this.picture=infoVo.getInfoPicture();
        this.myDescription=infoVo.getInfoDescription();
        StringBuilder sb=new StringBuilder();
        infoVo.getUserTag().forEach(s->sb.append(s).append(","));
        this.userTag=sb.toString();
        StringBuilder sb1=new StringBuilder();
        infoVo.getTechTag().forEach(s->sb1.append(s).append(","));
        this.techTag=sb1.toString();
        this.nickname=infoVo.getNickname();
        this.avatar=infoVo.getAvatar();
        this.email=infoVo.getEmail();
    }

}
