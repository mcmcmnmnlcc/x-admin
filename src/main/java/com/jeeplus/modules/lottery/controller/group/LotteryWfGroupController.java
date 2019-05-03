package com.jeeplus.modules.lottery.controller.group;

import com.jeeplus.common.base.controller.BaseController;
import com.jeeplus.common.myResult.Result;
import com.jeeplus.common.myResult.StatusCode;
import com.jeeplus.modules.lottery.entity.group.LotteryWfGroup;
import com.jeeplus.modules.lottery.entity.wf.WfMain;
import com.jeeplus.modules.lottery.service.group.LotteryWfGroupService;
import com.jeeplus.modules.lottery.service.wf.WfMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/lottery/group")
public class LotteryWfGroupController extends BaseController {
    @Autowired
    private LotteryWfGroupService lotteryWfGroupService;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET )
    public Result get(@PathVariable String id){
        LotteryWfGroup  lotteryWfGroup=lotteryWfGroupService.get(id);
        Result result=new Result(true, StatusCode.OK,"查询成功",lotteryWfGroup);
        return result;
    }

    /**
     * 添加组
     * @param lotteryWfGroup
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/",method = RequestMethod.POST)
    public Result save(@RequestBody LotteryWfGroup lotteryWfGroup) throws Exception{
        lotteryWfGroupService.save(lotteryWfGroup);//保存
        Result result=new Result(true, StatusCode.OK,"添加成功");
        return result;
    }
    @RequestMapping(value = "/",method = RequestMethod.PUT)
    public Result update(@RequestBody LotteryWfGroup lotteryWfGroup) throws Exception{
        lotteryWfGroupService.update(lotteryWfGroup);//保存
        Result result=new Result(true, StatusCode.OK,"修改成功");
        return result;
    }

    //删除组
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE )
    public Result delete(@PathVariable String id){
        lotteryWfGroupService.delete(id);
        Result result=new Result(true, StatusCode.OK,"删除成功");
        return result;
    }
}
