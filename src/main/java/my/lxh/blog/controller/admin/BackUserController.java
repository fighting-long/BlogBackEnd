package my.lxh.blog.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import my.lxh.blog.entity.User;
import my.lxh.blog.entity.vo.InfoVo;
import my.lxh.blog.entity.vo.PwdVo;
import my.lxh.blog.service.IUserService;
import my.lxh.blog.utils.JwtUtil;
import my.lxh.blog.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * @author lxh
 * @date 2020-12-28 21:28
 */
@RestController
@RequestMapping("/admin")
@Api(tags = "后端用户接口")
public class BackUserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/getUser")
    @ApiOperation("获取用户")
    public ResultUtil<?> getUser(HttpServletRequest request){
        HashMap<String, Object> map = new HashMap<>();
        map.put("nickname", JwtUtil.verify(request.getHeader("token")).getClaim("nickname").asString());
        return ResultUtil.ok(map);
    }

    @GetMapping("/getUsername")
    @ApiOperation("获取username")
    public ResultUtil<?> getUsername(){
        return ResultUtil.ok(userService.getMap(new QueryWrapper<User>().select("username")));
    }

    @PutMapping("/updatePwd")
    @ApiOperation("更新密码")
    public ResultUtil<?> updatePwd(@RequestBody PwdVo pwdVo){
        return userService.updatePwd(pwdVo)?ResultUtil.ok():ResultUtil.error();
    }

    @PutMapping("/updateInfo")
    @ApiOperation("更新博主信息")
    public ResultUtil<?> updateInfo(@RequestBody InfoVo infoVo){
        return ResultUtil.ok(userService.updateInfo(infoVo));
    }

}
