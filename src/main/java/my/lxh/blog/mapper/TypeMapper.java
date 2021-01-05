package my.lxh.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import my.lxh.blog.entity.Type;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lxh
 * @date 2020-12-13 19:14
 */
@Repository
public interface TypeMapper extends BaseMapper<Type> {

    /**
     * 获取全部分类及此分类的博客数
     * @return
     */
    List<Type> findTypeNum();

    /**
     * 插入分类，并获取id
     * @param type
     */
    void insertType(Type type);
}
