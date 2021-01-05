package my.lxh.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import my.lxh.blog.entity.Tag;
import my.lxh.blog.entity.vo.TagVo;
import my.lxh.blog.mapper.TagMapper;
import my.lxh.blog.service.ITagService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author lxh
 * @date 2020-12-14 16:37
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements ITagService {

    @Override
    public List<TagVo> getTagNum(Integer n) {
        List<Tag> tags=baseMapper.findTagNum();
        List<TagVo> res = new ArrayList<>();
        tags.stream()
                .sorted(Comparator.comparingInt(t -> -t.getBlogs().size()))
                // 限制前 n 名
                .limit(n)
                .forEach(tag -> res.add(new TagVo(tag.getId(),tag.getName(),tag.getBlogs().size())));
        return res;
    }

    @Override
    public List<TagVo> getAllTag() {
        List<Tag> tags=baseMapper.findTagNum();
        List<TagVo> res = new ArrayList<>();
        tags.sort(Comparator.comparingInt(t -> -t.getBlogs().size()));
        tags.forEach(tag -> res.add(new TagVo(tag.getId(),tag.getName(),tag.getBlogs().size())));
        return res;
    }

}
