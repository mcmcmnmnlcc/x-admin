package com.jeeplus.modules.lottery.controller.wf;

import com.jeeplus.common.base.PageBean;
import com.jeeplus.common.base.controller.BaseController;
import com.jeeplus.common.myResult.Result;
import com.jeeplus.common.myResult.StatusCode;
import com.jeeplus.modules.lottery.entity.wf.WfMain;
import com.jeeplus.modules.lottery.service.wf.WfMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping(value = "/lottery/wf/wfMain")
public class WfMainController extends BaseController {

    @Autowired
    private WfMainService wfMainService;

    /**
     * 玩法查询，可以条件查询
     * @param wfMain
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RolesAllowed({"lottery:wf:wfMain:list", "lottery:wf:wfMain:view"})
    @RequestMapping(value = "/list",method = RequestMethod.GET )
    public Result list(WfMain wfMain,Integer pageNo,Integer pageSize) {
//        String str=(String)ThreadAttributes.getThreadAttribute("username");
//        System.out.println("获取dddddddddddd="+str);

        PageBean<WfMain> page = wfMainService.findPage(new PageBean<WfMain>(pageNo,pageSize), wfMain);
        Result result=new Result(true, StatusCode.OK,"查询成功",page);

        return result;
    }

    /**
     * 根据玩法id查询玩法
     * @param wfId
     * @return
     */
    @RequestMapping(value = "/{wfId}",method = RequestMethod.GET )
    public Result getById(@PathVariable String wfId){
        WfMain wfMain=wfMainService.getById(wfId);
        Result result=new Result(true, StatusCode.OK,"查询成功",wfMain);
        return result;
    }

    /**
     *
     * @param wfMain
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/",method = RequestMethod.POST)
    public Result save(@RequestBody  WfMain wfMain) throws Exception{
        wfMainService.save(wfMain);//保存
        Result result=new Result(true, StatusCode.OK,"添加成功");
        return result;
    }

    /**
     * 修改玩法
     * @param wfMain
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/",method = RequestMethod.PUT)
    public Result update(@RequestBody  WfMain wfMain) throws Exception{
        wfMainService.update(wfMain);//保存
        Result result=new Result(true, StatusCode.OK,"修改成功");
        return result;
    }

    @RequestMapping(value = "/{wfId}",method = RequestMethod.DELETE )
    public Result delete(@PathVariable String wfId){
        wfMainService.delete(wfId);
        Result result=new Result(true, StatusCode.OK,"删除成功");
        return result;
    }
}
