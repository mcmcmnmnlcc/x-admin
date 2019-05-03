package com.jeeplus.modules.platform.controller;

import com.jeeplus.common.base.PageBean;
import com.jeeplus.common.base.controller.BaseController;
import com.jeeplus.common.myResult.Result;
import com.jeeplus.common.myResult.StatusCode;
import com.jeeplus.modules.platform.entity.PlatformUser;
import com.jeeplus.modules.platform.service.PfmUserLoginInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/platform/user_login_info")
public class PfmUserLoginInfoController extends BaseController {
    @Autowired
    PfmUserLoginInfoService pfmUserLoginInfoService;

    /**
     * 查询用户的登陆信息
     * @param platformUser
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET )
    public Result list(PlatformUser platformUser, Integer pageNo, Integer pageSize){

        PageBean<PlatformUser> page = pfmUserLoginInfoService.findList(new PageBean<PlatformUser>(pageNo,pageSize), platformUser);
        Result result=new Result(true, StatusCode.OK,"查询成功",page);
        return result;

    }
}
