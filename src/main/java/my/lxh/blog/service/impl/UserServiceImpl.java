package my.lxh.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import my.lxh.blog.entity.User;
import my.lxh.blog.entity.vo.PwdVo;
import my.lxh.blog.exception.BlogException;
import my.lxh.blog.mapper.UserMapper;
import my.lxh.blog.service.IUserService;
import my.lxh.blog.utils.JwtUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

/**
 * @author lxh
 * @date 2020-12-25 11:39
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService{


    @Override
    public String loginBack(User user) {
        User admin = baseMapper.selectOne(new QueryWrapper<User>().lambda()
                .eq(User::getUsername, user.getUsername())
                .eq(User::getPassword, user.getPassword()));
        if(Objects.isNull(admin)){
            throw new BlogException("用户名或密码错误！");
        }
        //获取token
        HashMap<String, Object> map = new HashMap<>();
        //向token中加入数据，生成为期 n 天的token
        return JwtUtil.getToken(map,5);
    }

    @Override
    public boolean updatePwd(PwdVo pwdVo) {
        User byId = this.getById(1);
        if(!byId.getPassword().equals(pwdVo.getOldPwd())){
            throw new BlogException("旧密码错误");
        }
        User user = new User();
        user.setId(byId.getId())
                .setUsername(pwdVo.getUsername())
                .setPassword(pwdVo.getNewPwd());
        return this.updateById(user);
    }

}
