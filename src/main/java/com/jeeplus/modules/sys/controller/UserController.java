package com.jeeplus.modules.sys.controller;

import com.jeeplus.common.base.controller.BaseController;
import com.jeeplus.common.myResult.Result;
import com.jeeplus.common.myResult.StatusCode;
import com.jeeplus.modules.sys.entity.Menu;
import com.jeeplus.modules.sys.entity.User;
import com.jeeplus.modules.sys.service.SystemService;
import com.jeeplus.modules.sys.service.UserService;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value = "用户模块")
@RestController
@RequestMapping(value = "/sys/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;
    /**
     * 查询一个用户根据用户id
     * @param userId
     * @return
     */
    @RequestMapping(value = "/get/{userId}", method = RequestMethod.GET)
    public Result getUserById(@PathVariable String userId) {
        //System.out.println(ToStringBuilder.reflectionToString(user));
        User user= userService.getById(userId);
        Result result=new Result(true, StatusCode.OK,"查询成功",user);
        return result;
    }

    /**
     * 添加一个用户
     * @param user
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result addUser(@RequestBody User user) {
        //System.out.println(ToStringBuilder.reflectionToString(user));
        userService.saveUser(user);
        Result result=new Result(true, StatusCode.OK,"添加用户成功");
        return result;
    }

    /**
     * 删除一个用户
     * @param userId
     * @return
     */
    @RequestMapping(value = "/delete/{userId}", method = RequestMethod.DELETE)
    public Result deleteUserById(@PathVariable String userId) {
        userService.deleteUser(userId);
        Result result=new Result(true, StatusCode.OK,"删除用户成功");
        return result;
    }




}
