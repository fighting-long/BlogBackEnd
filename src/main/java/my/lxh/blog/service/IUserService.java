package my.lxh.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import my.lxh.blog.entity.User;
import my.lxh.blog.entity.vo.InfoVo;
import my.lxh.blog.entity.vo.LoginVo;
import my.lxh.blog.entity.vo.PwdVo;

/**
 * @author lxh
 * @date 2020-12-25 11:38
 */
public interface IUserService extends IService<User> {
    /**
     * 登陆后台接口
     * @param loginVo
     * @return
     */
    String loginBack(LoginVo loginVo);

    /**
     * 更新密码
     * @param pwdVo
     * @return
     */
    boolean updatePwd(PwdVo pwdVo);

    /**
     * 登陆后台接口
     * @param loginVo
     * @return
     */
    String loginBackByCode(LoginVo loginVo);

    /**
     * 发送验证码
     * @param email
     */
    void sendCode(String email);


    /**
     * 获取博主信息
     * @return
     */
    InfoVo getAdminInfo();

    /**
     * 更新信息
     * @param infoVo
     * @return
     */
    Boolean updateInfo(InfoVo infoVo);
}
