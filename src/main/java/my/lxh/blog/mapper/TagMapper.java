package my.lxh.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import my.lxh.blog.entity.Tag;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lxh
 * @date 2020-12-14 16:37
 */
@Repository
public interface TagMapper extends BaseMapper<Tag> {
    /**
     * 获取博客数前n名的标签
     * @return
     */
    List<Tag> findTagNum();

    /**
     * 根据博客id查询标签
     * @param id
     * @return
     */
    List<Tag> getTagByBlogId(Long id);

    /**
     * 保存标签
     * @param save
     */
    void insertTag(Tag save);
}
