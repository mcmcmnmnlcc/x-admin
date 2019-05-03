package com.jeeplus.modules.cash.service;

import com.jeeplus.common.base.constant.MoneyFlowStatusEnum;
import com.jeeplus.common.base.service.BaseService;
import com.jeeplus.common.utils.ConstantUtil;
import com.jeeplus.common.utils.IdGen;
import com.jeeplus.modules.cash.dao.ChargeMoneyFlowDao;
import com.jeeplus.modules.cash.dao.MoneyFlowDao;
import com.jeeplus.modules.cash.dao.SysConfigDao;
import com.jeeplus.modules.cash.entity.PfmUserChargeWithDraw;
import com.jeeplus.modules.cash.entity.UserMoneyFlow;
import com.jeeplus.modules.platform.dao.PlatformUserDao;
import com.jeeplus.modules.platform.entity.PlatformUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional(readOnly=false,rollbackFor=Exception.class)
public class PfmUserService extends BaseService {
    @Autowired
    private ChargeMoneyFlowDao chargeMoneyFlowDao;

    @Autowired
    private PlatformUserDao pfmUserDao;

    @Autowired
    private MoneyFlowDao moneyFlowDao;

    @Autowired
    private SysConfigDao sysConfigDao;

    /**
     * 保存人工入款的操作
     */
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public void instUserMoneyFlow(Long changeMoney,PlatformUser pfmUser,String remark,String changeType,String changeTypeDetail){
        PlatformUser editUser=new PlatformUser();
        editUser.setUserId(pfmUser.getUserId());
        editUser.setBalanceInt(pfmUser.getBalanceInt());
        editUser.setChukDml(pfmUser.getChukDml());
        editUser.setCountRukuanTimes(pfmUser.getCountRukuanTimes());
        editUser.setCountRukuanMoney(pfmUser.getCountRukuanMoney());

        //更新存款标志
        editUser.setDepositFlag("1");

        //如果是人工入款，需要保存入款记录
        //if(MoneyFlowTypeEnum.TYPE_FLOW_10.getChangeType().equals(changeType)&&MoneyFlowStatusEnum.TYPE_SDCR_10_01.getChangeTypeStatus().equals(changeTypeDetail)){
        PfmUserChargeWithDraw chargeWithDraw=new PfmUserChargeWithDraw(pfmUser.getUserId(),MoneyFlowStatusEnum.CHONGZHI_TYPE.getChangeTypeStatus(),changeMoney,"1");
        chargeWithDraw.setRkType("3");
        chargeWithDraw.setOrderNo(IdGen.getOrderNum("MI"));
        chargeWithDraw.setCheckPerson("lcc");
        chargeWithDraw.setCheckInfo(remark);
        chargeWithDraw.setCheckDate(new Date());
        chargeWithDraw.setCreateDate(new Date());
        chargeMoneyFlowDao.instUserCharge(chargeWithDraw);

        //记录操作日志
//        String optIp = Common.toIpAddr(Servlets.getRequest());
//        LogManager.me().executeLog(LogTaskFactory.operationLog(
//                pfmUser.getUserId(), LogModularEnum.CHARGE_RENGONG.getName(),
//                String.format("类型：后台手动入款菜单；操作金额：%s", changeMoney/1000), "操作成功", optIp, 1, UserUtils.getUser().getLoginName()));
        //}
        doHandleUserCharge(changeMoney,editUser,remark,changeTypeDetail, ConstantUtil.CAI_PIAO_FLOW_FLAG,changeType);
    }


    /**
     *保存人工提款的操作
     * @param changeMoney 待提出的钱
     * @param pfmUser
     * @param remark
     * @param changeType
     * @param changeTypeDetail
     */
    public void instUserWithDrawMoney(Long changeMoney, PlatformUser pfmUser, String remark, String changeType, String changeTypeDetail){
        PlatformUser editUser=new PlatformUser();
        editUser.setUserId(pfmUser.getUserId());
        editUser.setBalanceInt(pfmUser.getBalanceInt());
        editUser.setChukDml(pfmUser.getChukDml());
        editUser.setCountChukuanMoney(pfmUser.getCountChukuanMoney());
        editUser.setCountChukuanTimes(pfmUser.getCountChukuanTimes());

        PfmUserChargeWithDraw chargeWithDraw=new PfmUserChargeWithDraw(pfmUser.getUserId(), MoneyFlowStatusEnum.TIKUAN_TYPE.getChangeTypeStatus(),changeMoney,"1");
        chargeWithDraw.setCheckPerson("lcc");//是谁操作的，把操作者的登录名传入
        chargeWithDraw.setCheckInfo(remark);
        chargeWithDraw.setOrderNo(IdGen.getOrderNum("MO")); //订单号
        //设置入款方式为人工入款
        chargeWithDraw.setRkType("3");
        chargeWithDraw.setCheckDate(new Date());
        chargeWithDraw.setCreateDate(new Date());
        //保存充值记录
        chargeMoneyFlowDao.instUserCharge(chargeWithDraw);

        //记录操作日志
//        String optIp = Common.toIpAddr(Servlets.getRequest());
//        LogManager.me().executeLog(LogTaskFactory.operationLog(
//                pfmUser.getUserId(), LogModularEnum.WITHDRAW_RENGONG.getName(),
//                String.format("类型：后台手动提款菜单；操作金额：%s", changeMoney/1000), "操作成功", optIp, 1, UserUtils.getUser().getLoginName()));

        if(("11").equals(changeType)&&("01").equals(changeTypeDetail)){
            changeMoney = changeMoney;
        }else{
            changeMoney = -changeMoney;
        }
        doHandleUserCharge(changeMoney,editUser,remark,changeTypeDetail, ConstantUtil.CAI_PIAO_FLOW_FLAG,changeType);
    }
    private void doHandleUserCharge(Long changeMoney,PlatformUser pfmUser,String remark,String changeTypeDetail,String flag,String changeType){
        pfmUserDao.editPfmUserProp(pfmUser);
        //保存用户资金流水表
        UserMoneyFlow moneyFlow=new UserMoneyFlow(pfmUser.getUserId(),changeTypeDetail,changeMoney,pfmUser.getBalanceInt(),flag,changeType);
        moneyFlow.setRemark(remark);
        moneyFlow.setOptUser("lcc");//登陆名，表示是谁改变的
        moneyFlowDao.saveMoneyFlow(moneyFlow);
    }

}
