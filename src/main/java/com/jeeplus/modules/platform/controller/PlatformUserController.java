package com.jeeplus.modules.platform.controller;

import com.jeeplus.common.base.PageBean;
import com.jeeplus.common.base.controller.BaseController;
import com.jeeplus.common.myResult.Result;
import com.jeeplus.common.myResult.StatusCode;
import com.jeeplus.common.utils.StringUtils;
import com.jeeplus.modules.cash.dao.SysConfigDao;
import com.jeeplus.modules.cash.entity.SysConfigEntity;
import com.jeeplus.modules.platform.dto.RechargeDTO;
import com.jeeplus.modules.platform.entity.PlatformUser;
import com.jeeplus.modules.platform.service.PlatformUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

/**
 * 平台会员管理
 */
@RestController
@RequestMapping(value = "/platform/user/")
public class PlatformUserController extends BaseController{
    @Autowired
    PlatformUserService platformUserService;



    @RequestMapping(value = "/list",method = RequestMethod.GET )
    public Result list(PlatformUser platformUser,Integer pageNo,Integer pageSize){

        PageBean<PlatformUser> page = platformUserService.findList(new PageBean<PlatformUser>(pageNo,pageSize), platformUser);
        Result result=new Result(true, StatusCode.OK,"查询成功",page);
        return result;

    }

    /**
     * 人工充值
     * @param rechargeDTO
     * @return
     */
    @RequestMapping(value = "/recharge",method = RequestMethod.PUT)
    public Result recharge(@RequestBody RechargeDTO rechargeDTO) throws Exception {

        platformUserService.recharge(rechargeDTO);
        Result result=new Result(true, StatusCode.OK,"充值成功");
        return result;
    }

    /**
     * 人工提款
     * @param rechargeDTO
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/drawing",method = RequestMethod.PUT)
    public Result drawing(@RequestBody @Valid RechargeDTO rechargeDTO,BindingResult eresult) throws Exception {
        if(eresult.hasErrors()){
            for (ObjectError error : eresult.getAllErrors()) {
                //System.out.println(error.getDefaultMessage());
                Result result=new Result(true, StatusCode.ERROR,"提款失败",error.getDefaultMessage());
                return result;
            }
        }

        platformUserService.drawing(rechargeDTO);
        Result result=new Result(true, StatusCode.OK,"提款成功");
        return result;
    }



}
