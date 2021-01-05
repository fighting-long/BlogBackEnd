package my.lxh.blog.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import my.lxh.blog.service.IConfigService;
import my.lxh.blog.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lxh
 * @date 2021-01-04 22:26
 */
@CrossOrigin
@RestController
@Api(tags = "信息接口")
public class InfoController {

    @Autowired
    private IConfigService configService;

    @GetMapping("/getInfo")
    @ApiOperation("得到博主信息")
    public ResultUtil<?> getInfo(){
        return ResultUtil.ok(configService.getAdminInfo());
    }


}
