package my.lxh.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import my.lxh.blog.entity.Blog;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lxh
 * @date 2020-12-10 21:17
 */
@Repository
public interface BlogMapper extends BaseMapper<Blog> {

    /**
     * 主页分页查询博客 及其 分类 typeId
     * @param blogPage
     * @param typeId
     * @return
     */
    IPage<Blog> getBlogsViewByTypeOrNull(Page<Blog> blogPage, @Param("tid") Integer typeId);

    /**
     * 主页分页查询博客 及其 分类
     * @param blogPage
     * @param tagId
     * @return
     */
    IPage<Blog> getBlogsViewByTagId(Page<Blog> blogPage,@Param("tid")Integer tagId);

    /**
     * 根据年份归档博客
     * @param year
     * @return
     */
    List<Blog> archiveByYear(Integer year);

    /**
     * 获取全部年份
     * @return
     */
    List<Integer> getYears();

    /**
     * 通过模糊查询获取分类
     * @param blogPage
     * @param keyword
     * @return
     */
    IPage<Blog> getBlogsViewByKeyword(Page<Blog> blogPage, String keyword);

    /**
     * 获取博客详情
     * @param id
     * @return
     */
    Blog getBlogDetail(Integer id);

    /**
     * 查询所有博客
     * @return
     */
    List<Blog> selectAll();

    /**
     * 保存博客
     * @param blog
     */
    void insertBlog(Blog blog);
}
