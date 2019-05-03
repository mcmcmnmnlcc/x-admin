package com.jeeplus.modules.cash.service;

import com.github.pagehelper.PageHelper;
import com.jeeplus.common.base.PageBean;
import com.jeeplus.common.base.constant.LogModularEnum;
import com.jeeplus.common.base.constant.MoneyFlowStatusEnum;
import com.jeeplus.common.base.service.BaseService;
import com.jeeplus.common.log.LogManager;
import com.jeeplus.common.log.factory.LogTaskFactory;
import com.jeeplus.common.utils.CommonUtil;
import com.jeeplus.common.utils.ConstantUtil;
import com.jeeplus.common.utils.IdGen;
import com.jeeplus.common.utils.ThreadAttributes;
import com.jeeplus.modules.cash.dao.ChargeMoneyFlowDao;
import com.jeeplus.modules.cash.dao.MoneyFlowDao;
import com.jeeplus.modules.cash.entity.PfmUserChargeWithDraw;
import com.jeeplus.modules.cash.entity.UserMoneyFlow;
import com.jeeplus.modules.platform.dao.PlatformUserDao;
import com.jeeplus.modules.platform.entity.PlatformUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ChargeMoneyService extends BaseService {
    @Autowired
    ChargeMoneyFlowDao chargeMoneyFlowDao;

    @Autowired
    PlatformUserDao platformUserDao;

    /**
     * 用户资金流水的dao
     */
    @Autowired
    private MoneyFlowDao moneyFlowDao;

    public PfmUserChargeWithDraw getById(String id){
        return chargeMoneyFlowDao.get(id);
    }

    /**
     * 分条件列表查询
     * @param pageBean
     * @param pfmUserChargeWithDraw
     * @return
     */
    public PageBean<PfmUserChargeWithDraw> findList(PageBean<PfmUserChargeWithDraw> pageBean, PfmUserChargeWithDraw pfmUserChargeWithDraw){
        //用插件分页
        com.github.pagehelper.Page p= PageHelper.startPage(pageBean.getPageNo(), pageBean.getPageSize(),true);
        List<PfmUserChargeWithDraw> list=chargeMoneyFlowDao.findList(pfmUserChargeWithDraw);
        pageBean.setCount(p.getTotal());//设置总记录数
        pageBean.setList(list);
        return pageBean;

    }

    /**
     * 保存人工入款的操作，用户充值的记录和资金流的记录
     * changeType 10
     * changeTypeDetail 01
     * 以上2个组合表示人工存款
     */
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public void instUserMoneyFlow(Long changeMoney, PlatformUser pfmUser, String remark, String changeType, String changeTypeDetail){
        //这个PlatformUser对应要传入资金流水操作方法doHandleUserCharge，只想改下面几个属性，所以新new了一个对象
        PlatformUser editUser=new PlatformUser();
        editUser.setUserId(pfmUser.getUserId());
        editUser.setBalanceInt(pfmUser.getBalanceInt());
        editUser.setChukDml(pfmUser.getChukDml());
        editUser.setCountRukuanTimes(pfmUser.getCountRukuanTimes());
        editUser.setCountRukuanMoney(pfmUser.getCountRukuanMoney());

        //更新存款标志
        editUser.setDepositFlag("1");

        //保存入款记录
        PfmUserChargeWithDraw chargeWithDraw=new PfmUserChargeWithDraw(pfmUser.getUserId(), MoneyFlowStatusEnum.CHONGZHI_TYPE.getChangeTypeStatus(),changeMoney,"1");
        chargeWithDraw.setRkType("3");
        chargeWithDraw.setOrderNo(IdGen.getOrderNum("MI"));
        String username=(String) ThreadAttributes.getThreadAttribute("username");
        if(null==username){
            chargeWithDraw.setCheckPerson("未知");
        }else{
            chargeWithDraw.setCheckPerson(username);
        }

        chargeWithDraw.setCheckInfo(remark);
        chargeWithDraw.setCheckDate(new Date());
        chargeWithDraw.setCreateDate(new Date());
        chargeMoneyFlowDao.instUserCharge(chargeWithDraw);

        //记录操作日志
        String optIp = CommonUtil.toIpAddr();
        LogManager.me().executeLog(LogTaskFactory.operationLog(
                pfmUser.getUserId(), LogModularEnum.CHARGE_RENGONG.getName(),
                String.format("类型：后台手动入款菜单；操作金额：%s", changeMoney/1000), "操作成功", optIp, 1, username));

        doHandleUserCharge(changeMoney,editUser,remark,changeTypeDetail, ConstantUtil.CAI_PIAO_FLOW_FLAG,changeType);
        //moneyFlowDao.editPfmUserMoney(pfmUser.getUserId(),changeMoney);
    }

    private void doHandleUserCharge(Long changeMoney,PlatformUser pfmUser,String remark,String changeTypeDetail,String flag,String changeType){
        //修改会员余额及相关字段信息
        platformUserDao.editPfmUserProp(pfmUser);
        //保存用户资金流水表
        UserMoneyFlow moneyFlow=new UserMoneyFlow(pfmUser.getUserId(),changeTypeDetail,changeMoney,pfmUser.getBalanceInt(),flag,changeType);
        moneyFlow.setRemark(remark);
        String username=(String) ThreadAttributes.getThreadAttribute("username");
        if(null==username){
            moneyFlow.setOptUser("未知");

        }else{
            moneyFlow.setOptUser(username);

        }

        moneyFlowDao.saveMoneyFlow(moneyFlow);
    }

    /**
     * 保存人工提款的操作
     */
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public void instUserWithDrawMoney(Long changeMoney,PlatformUser pfmUser,String remark,String changeType,String changeTypeDetail){
        PlatformUser editUser=new PlatformUser();
        editUser.setUserId(pfmUser.getUserId());
        editUser.setBalanceInt(pfmUser.getBalanceInt());
        editUser.setChukDml(pfmUser.getChukDml());
        editUser.setCountChukuanMoney(pfmUser.getCountChukuanMoney());
        editUser.setCountChukuanTimes(pfmUser.getCountChukuanTimes());

        PfmUserChargeWithDraw chargeWithDraw=new PfmUserChargeWithDraw(pfmUser.getUserId(),MoneyFlowStatusEnum.TIKUAN_TYPE.getChangeTypeStatus(),changeMoney,"1");
        String username=(String) ThreadAttributes.getThreadAttribute("username");
        if(null==username){
            chargeWithDraw.setCheckPerson("未知");
        }else{
            chargeWithDraw.setCheckPerson(username);
        }
        chargeWithDraw.setCheckInfo(remark);
        //设置入款方式为人工入款
        chargeWithDraw.setOrderNo(IdGen.getOrderNum("MO")); //订单号
        chargeWithDraw.setRkType("3");
        chargeWithDraw.setCheckDate(new Date());
        chargeWithDraw.setCreateDate(new Date());
        chargeMoneyFlowDao.instUserCharge(chargeWithDraw);

        //记录操作日志
        String optIp = CommonUtil.toIpAddr();
        LogManager.me().executeLog(LogTaskFactory.operationLog(
                pfmUser.getUserId(), LogModularEnum.WITHDRAW_RENGONG.getName(),
                String.format("类型：后台手动提款菜单；操作金额：%s", changeMoney/1000), "操作成功", optIp, 1,username));

        doHandleUserCharge(changeMoney,editUser,remark,changeTypeDetail, ConstantUtil.CAI_PIAO_FLOW_FLAG,changeType);
    }
}
