package my.lxh.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import my.lxh.blog.entity.Config;
import my.lxh.blog.entity.vo.InfoVo;

/**
 * @author lxh
 * @date 2020-12-30 15:13
 */
public interface IConfigService extends IService<Config> {
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
