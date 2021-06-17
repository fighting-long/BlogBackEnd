package my.lxh.blog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import my.lxh.blog.entity.Blog;
import my.lxh.blog.entity.vo.ArchiveVo;

import java.util.List;

/**
 * @author lxh
 * @date 2020-12-10 21:16
 */
public interface IBlogService extends IService<Blog> {

    /**
     * 概览 ： 主页分页查询博客 及其 分类
     * @param startPage
     * @param size
     * @param typeId
     * @return
     */
    IPage<Blog> getBlogsView(Integer startPage, Integer size,Integer typeId);

    /**
     * 概览 ： 主页分页查询博客 及其 分类 通过tagid
     * @param startPage
     * @param size
     * @param tagId
     * @return
     */
    IPage<Blog> getBlogsViewByTagId(Integer startPage, Integer size, Integer tagId);

    /**
     * 获取归档博客
     * @return
     */
    List<ArchiveVo> getBlogsArchive();

    /**
     *  分页概览博客查询
     * @param startPage
     * @param size
     * @param keyword
     * @return
     */
    IPage<Blog> getBlogsViewBySearch(Integer startPage, Integer size, String keyword);

    /**
     * 获取博客详情
     * @param id
     * @return
     */
    Blog getBlogDetailById(Integer id);

    /**
     * 列出所有博客
     * @return
     */
    List<Blog> listAll();

    /**
     * 保存博客
     * @param blog
     * @return
     */
    Boolean saveBlog(Blog blog);

    /**
     * 更新博客时获取博客
     * @param id
     * @return
     */
    Blog selectBlogInfo(Long id);

    /**
     * 更新博客
     * @param blog
     * @return
     */
    Boolean updateBlog(Blog blog);

    /**
     * 删除博客
     * @param id
     * @return
     */
    boolean deleteBlogById(Long id);
}
