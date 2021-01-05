package my.lxh.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import my.lxh.blog.entity.Type;
import my.lxh.blog.entity.vo.TypeVo;
import my.lxh.blog.mapper.TypeMapper;
import my.lxh.blog.service.ITypeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author lxh
 * @date 2020-12-13 19:13
 */
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type> implements ITypeService {

    @Override
    public List<TypeVo> getTypeNum(Integer n) {
        List<Type> types=baseMapper.findTypeNum();
        ArrayList<TypeVo> res = new ArrayList<>();
        types.stream()
                    .sorted(Comparator.comparingInt(t -> -t.getBlogs().size()))
                    // 限制前 n 名
                    .limit(n)
                    .forEach(type -> res.add(new TypeVo(type.getId(),type.getName(),type.getBlogs().size())));
        return res;
    }

    @Override
    public List<TypeVo> getAllType() {
        List<Type> types = baseMapper.findTypeNum();
        ArrayList<TypeVo> res = new ArrayList<>();
        types.sort(Comparator.comparingInt(t -> -t.getBlogs().size()));
        types .forEach(type -> res.add(new TypeVo(type.getId(),type.getName(),type.getBlogs().size())));
        return res;
    }


}
