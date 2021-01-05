package my.lxh.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import my.lxh.blog.entity.Type;
import my.lxh.blog.entity.vo.TypeVo;

import java.util.List;

/**
 * @author lxh
 * @date 2020-12-13 19:12
 */
public interface ITypeService extends IService<Type> {
    /**
     * 获取博客数量前 n 的分类
     * @param n
     * @return
     */
    List<TypeVo> getTypeNum(Integer n);

    /**
     * 获取全部分类及此分类的博客数
     * @return
     */
    List<TypeVo> getAllType();
}
