package my.lxh.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import my.lxh.blog.entity.User;
import my.lxh.blog.entity.vo.PwdVo;

/**
 * @author lxh
 * @date 2020-12-25 11:38
 */
public interface IUserService extends IService<User> {
    /**
     * 登陆后台接口
     * @param user
     * @return
     */
    String loginBack(User user);

    /**
     * 更新密码
     * @param pwdVo
     * @return
     */
    boolean updatePwd(PwdVo pwdVo);

    /**
     * 登陆后台接口
     * @param user
     * @return
     */
    String loginBackByCode(User user);

    /**
     * 发送验证码
     * @param username
     */
    void sendCode(String username);
}
