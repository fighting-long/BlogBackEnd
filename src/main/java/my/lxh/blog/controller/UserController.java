package my.lxh.blog.controller;

import io.swagger.annotations.Api;
import my.lxh.blog.entity.User;
import my.lxh.blog.service.IUserService;
import my.lxh.blog.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lxh
 * @date 2020-12-28 12:06
 */
@CrossOrigin
@RestController
@Api(tags = "用户操作接口")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/login")
    public ResultUtil<?> login(@RequestBody User user){
        return ResultUtil.ok(userService.loginBack(user));
    }

}
