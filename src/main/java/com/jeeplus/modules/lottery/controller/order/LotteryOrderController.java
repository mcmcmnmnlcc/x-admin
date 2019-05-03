package com.jeeplus.modules.lottery.controller.order;

import com.jeeplus.common.base.PageBean;
import com.jeeplus.common.base.controller.BaseController;
import com.jeeplus.common.myResult.Result;
import com.jeeplus.common.myResult.StatusCode;
import com.jeeplus.common.utils.StringUtils;
import com.jeeplus.modules.lottery.entity.order.LotteryOrder;
import com.jeeplus.modules.lottery.service.order.LotteryOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/lottery/order/lotteryOrder")
public class LotteryOrderController extends BaseController {

    @Autowired
    private LotteryOrderService lotteryOrderService;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET )
    public Result get(@PathVariable String id) {
        LotteryOrder entity = null;
        if (StringUtils.isNotBlank(id)){
            entity = lotteryOrderService.get(id);
            Result result=new Result(true, StatusCode.OK,"查询成功",entity);
            return result;
        }else{
            Result result=new Result(false, StatusCode.ERROR,"查询失败");
            return result;
        }
    }

    /**
     * 多条件分页查询
     * @param lotteryOrder
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET )
    public Result list(LotteryOrder lotteryOrder,Integer pageNo,Integer pageSize) {
        PageBean<LotteryOrder> page = lotteryOrderService.findList(new PageBean<LotteryOrder>(pageNo,pageSize), lotteryOrder);
        Result result=new Result(true, StatusCode.OK,"查询成功",page);
        return result;
    }

}
