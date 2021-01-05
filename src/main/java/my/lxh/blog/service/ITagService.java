package my.lxh.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import my.lxh.blog.entity.Tag;
import my.lxh.blog.entity.vo.TagVo;

import java.util.List;

/**
 * @author lxh
 * @date 2020-12-14 16:35
 */
public interface ITagService extends IService<Tag> {

    /**
     * 获取博客数量前6的标签
     * @param n
     * @return
     */
    List<TagVo> getTagNum(Integer n);

    /**
     * 获取全部标签及其下博客数量
     * @return
     */
    List<TagVo> getAllTag();
}
