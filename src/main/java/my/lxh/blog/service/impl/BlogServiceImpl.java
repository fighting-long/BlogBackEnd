package my.lxh.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import my.lxh.blog.entity.Blog;
import my.lxh.blog.entity.BlogTags;
import my.lxh.blog.entity.Tag;
import my.lxh.blog.entity.Type;
import my.lxh.blog.entity.vo.ArchiveVo;
import my.lxh.blog.mapper.BlogMapper;
import my.lxh.blog.mapper.BlogTagsMapper;
import my.lxh.blog.mapper.TagMapper;
import my.lxh.blog.mapper.TypeMapper;
import my.lxh.blog.service.IBlogService;
import my.lxh.blog.utils.MarkdownUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author lxh
 * @date 2020-12-10 21:17
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements IBlogService {

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private TypeMapper typeMapper;

    @Autowired
    private BlogTagsMapper blogTagsMapper;

    @Override
    public IPage<Blog> getBlogsView(Integer startPage, Integer size,Integer typeId) {
        return baseMapper.getBlogsViewByTypeOrNull(new Page<>(startPage, size),typeId);
    }

    @Override
    public IPage<Blog> getBlogsViewByTagId(Integer startPage, Integer size, Integer tagId) {
        IPage<Blog> blogs = baseMapper.getBlogsViewByTagId(new Page<>(startPage, size), tagId);
        blogs.getRecords().forEach(blog -> {
            List<Tag> tags=tagMapper.getTagByBlogId(blog.getId());
            blog.setTags(tags);
        });
        return blogs;
    }

    @Override
    public List<ArchiveVo> getBlogsArchive() {
        List<Integer> years=baseMapper.getYears();
        List<ArchiveVo> list = new ArrayList<>();
        years.forEach(year->list.add(new ArchiveVo(year,baseMapper.archiveByYear(year))));
        return list;
    }

    @Override
    public IPage<Blog> getBlogsViewBySearch(Integer startPage, Integer size, String keyword) {
        return baseMapper.getBlogsViewByKeyword(new Page<>(startPage,size),"%"+keyword+"%");
    }

    @Override
    @Transactional
    public Blog getBlogDetailById(Integer id) {
        Blog blog = baseMapper.getBlogDetail(id);
        blog.setViews(blog.getViews()+1);
        this.lambdaUpdate()
                .eq(Blog::getId,blog.getId())
                .set(Blog::getViews,blog.getViews())
                .update();
        blog.setContent(MarkdownUtil.markdownToHtmlExtensions(blog.getContent()));
        return blog;
    }

    @Override
    public List<Blog> listAll() {
        return baseMapper.selectAll();
    }

    @Override
    public Boolean saveBlog(Blog blog) {
        blog.setViews(0)
                .setCreateTime(new Date())
                .setUpdateTime(new Date())
                .setUserId(1L);
        //保存分类 和 分类id
        settingType(blog);
        //保存博客
        baseMapper.insertBlog(blog);
        //保存标签和标签id
        if(Objects.nonNull(blog.getTagsName()) &&  blog.getTagsName().size()>0) {
            settingTags(blog);
            blog.getTags().forEach(tag -> blogTagsMapper.insert(new BlogTags().setBlogId(blog.getId()).setTagId(tag.getId())));
        }
        return true;
    }

    @Override
    public Blog selectBlogInfo(Long id) {
        Blog blog = baseMapper.selectById(id);
        blog.setType(typeMapper.selectById(blog.getTypeId()));
        List<BlogTags> blogTags = blogTagsMapper.selectList(new QueryWrapper<BlogTags>().lambda().eq(BlogTags::getBlogId, id));
        if(blogTags.size()!=0){
            List<Integer> temp= new ArrayList<>();
            blogTags.forEach(blogTag -> temp.add(blogTag.getTagId()));
            List<Tag> tags = tagMapper.selectBatchIds(temp);
            List<String> list=new ArrayList<>();
            tags.forEach(tag -> list.add(tag.getName()));
            blog.setTagsName(list);
        }
        return blog;
    }

    @Override
    public Boolean updateBlog(Blog blog){
        blog.setUpdateTime(new Date());
        //保存分类 和 分类id
        settingType(blog);
        //保存新增标签
        if(Objects.nonNull(blog.getTagsName()) &&  blog.getTagsName().size()>0) {
            settingTags(blog);
        }
        //更新博客标签的中间表，先查询该博客下的所有标签id
        List<BlogTags> blogTags = blogTagsMapper.selectList(new QueryWrapper<BlogTags>().lambda().eq(BlogTags::getBlogId, blog.getId()));
        List<Integer> list = new ArrayList<>();
        blogTags.forEach(blogTag -> list.add(blogTag.getTagId()));
        List<Integer> copy=new ArrayList<>(list);
        //找出该博客现在应该有的标签id
        List<Integer> tagIds=new ArrayList<>();
        if(Objects.nonNull(blog.getTags())){
            blog.getTags().forEach(tag -> tagIds.add(tag.getId()));
        }
        //找出中间表应该删除的数据，即以前有的，现在没有的
        list.removeAll(tagIds);
        list.forEach(id-> blogTagsMapper.delete(new QueryWrapper<BlogTags>().lambda()
                .eq(BlogTags::getBlogId,blog.getId())
                .eq(BlogTags::getTagId,id)));
        //找出中间表应该插入的数据，即以前没有的，现在有的
        tagIds.removeAll(copy);
        tagIds.forEach(id-> blogTagsMapper.insert(new BlogTags().setBlogId(blog.getId()).setTagId(id)));

        //更新博客
        baseMapper.updateById(blog);
        return true;
    }

    /**
     * 对不存在的标签进行保存
     * @param blog
     */
    private void settingTags(Blog blog) {
        ArrayList<Tag> tags = new ArrayList<>();
        blog.getTagsName().forEach(name->{
            Tag tag = tagMapper.selectOne(new QueryWrapper<Tag>().lambda()
                    .eq(Tag::getName, name));
            if(Objects.isNull(tag)){
                tag = new Tag().setName(name);
                tagMapper.insertTag(tag);
            }
            tags.add(tag);
        });
        blog.setTags(tags);
    }


    /**
     * 将分类id设置进blog
     */
    private void settingType(Blog blog){
        //先查一次这个type是否存在于数据库中
        Type type = typeMapper.selectOne(new QueryWrapper<Type>().lambda()
                .select(Type::getId)
                .eq(Type::getName, blog.getType().getName()));
        // 如果不存在则插入，将返回的id插入blog
        if(Objects.isNull(type)){
            typeMapper.insertType(blog.getType());
            blog.setTypeId(blog.getType().getId());
        }else {
            // 存在则直接插入blog
            blog.setTypeId(type.getId());
        }
    }

}
