package my.lxh.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import my.lxh.blog.entity.Config;
import my.lxh.blog.entity.User;
import my.lxh.blog.entity.vo.InfoVo;
import my.lxh.blog.mapper.ConfigMapper;
import my.lxh.blog.mapper.UserMapper;
import my.lxh.blog.service.IConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lxh
 * @date 2020-12-30 15:14
 */
@Service
public class ConfigServiceImpl extends ServiceImpl<ConfigMapper, Config> implements IConfigService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public InfoVo getAdminInfo() {
        Config config = baseMapper.selectById(1);
        InfoVo infoVo = new InfoVo(config);
        User user = userMapper.selectById(1);
        infoVo.setAvatar(user.getAvatar())
                .setNickname(user.getNickname())
                .setEmail(user.getEmail());
        return infoVo;
    }

    @Override
    public Boolean updateInfo(InfoVo infoVo) {
        Config config = new Config(infoVo);
        baseMapper.updateById(config);
        User user = new User();
        user.setNickname(infoVo.getNickname())
                .setAvatar(infoVo.getAvatar())
                .setEmail(infoVo.getEmail())
                .setId(1L);
        userMapper.updateById(user);
        return true;
    }

}
