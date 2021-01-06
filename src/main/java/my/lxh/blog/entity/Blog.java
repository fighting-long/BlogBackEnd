package my.lxh.blog.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

/**
 * 博客主体的实体类
 * 主键 、 标题 、 内容 、 首图 、 标志（转载、原创） 、 浏览次数 、
 * 是否开启赞赏 、 是否开启转载声明 、 是否开启评论 、 是否发布（保存） 、 是否被推荐 、 创建时间 、 更新时间
 * @author lxh
 * @date 2020-07-05 15:24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ApiModel("博客实体类")
public class Blog {

    @ApiModelProperty("主键")
    @TableId
    private Integer id;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("内容")
    private String content;

    @ApiModelProperty("首图")
    private String firstPicture;

    @ApiModelProperty("博客类型：转载？原创")
    private String flag;

    @ApiModelProperty("浏览量")
    private Integer views;

    @ApiModelProperty("是否开启赞赏")
    private Boolean appreciation;

    @ApiModelProperty("是否开启分享声明")
    private Boolean shareStatement;

    @ApiModelProperty("是否开启评论")
    private Boolean commentable;

    @ApiModelProperty("是否发布？可以保存为草稿")
    private Boolean published;

    @ApiModelProperty("是否推荐")
    private Boolean recommend;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("更新时间")
    private Date updateTime;

    @ApiModelProperty("博客描述")
    private String description;

    @ApiModelProperty("博客类型id")
    private Integer typeId;

    @ApiModelProperty("博客类型")
    @TableField(exist = false)
    private Type type;

    @ApiModelProperty("博客标签")
    @TableField(exist = false)
    private List<Tag> tags;

    @ApiModelProperty("博客标签名字")
    @TableField(exist = false)
    private List<String> tagsName;

    @ApiModelProperty("博客评论")
    @TableField(exist = false)
    private List<Comment> comments;

    @ApiModelProperty("博客作者")
    @TableField(exist = false)
    private User user;

    @ApiModelProperty("博客作者id")
    private Long userId;



}
