package my.lxh.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import my.lxh.blog.entity.User;
import my.lxh.blog.entity.vo.PwdVo;
import my.lxh.blog.exception.BlogException;
import my.lxh.blog.mapper.UserMapper;
import my.lxh.blog.service.IUserService;
import my.lxh.blog.utils.CodeUtil;
import my.lxh.blog.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

/**
 * @author lxh
 * @date 2020-12-25 11:39
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService{


    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private JavaMailSender mailSender;

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
        map.put("nickname",admin.getNickname());
        //向token中加入数据，生成为期 n 天的token
        return JwtUtil.getToken(map,5);
    }

    @Override
    public String loginBackByCode(User user) {
        User admin = baseMapper.selectById(1);
        String code = stringRedisTemplate.opsForValue().get(user.getUsername());
        if(!user.getPassword().equals(code)){
            throw new BlogException("验证码错误！");
        }else if(!admin.getUsername().equals(user.getEmail())){
            throw new BlogException("还不支持游客登陆。");
        }
        //获取token
        HashMap<String, Object> map = new HashMap<>();
        map.put("nickname",admin.getNickname());
        //向token中加入数据，生成为期 n 天的token
        return JwtUtil.getToken(map,5);
    }

    @Async
    @Override
    public void sendCode(String username) {
        SimpleMailMessage simpleMessage = new SimpleMailMessage();
        String code = CodeUtil.getCode();
        simpleMessage.setText("邮箱验证码为："+code);
        simpleMessage.setFrom("343932572@qq.com");
        simpleMessage.setTo(username);
        mailSender.send(simpleMessage);
        stringRedisTemplate.opsForValue().set("username",code);
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
