package my.lxh.blog.controller.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import my.lxh.blog.entity.vo.InfoVo;
import my.lxh.blog.service.IConfigService;
import my.lxh.blog.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author lxh
 * @date 2020-12-30 15:07
 */
@CrossOrigin
@RestController
@Api(tags = "后台信息接口")
@RequestMapping("/admin")
public class BackInfoController {

    @Autowired
    private IConfigService configService;

    @PutMapping("/updateInfo")
    @ApiOperation("更新博主信息")
    public ResultUtil<?> updateInfo(@RequestBody InfoVo infoVo){
        return ResultUtil.ok(configService.updateInfo(infoVo));
    }

}
