package com.jeeplus.modules.platform.service;

import com.github.pagehelper.PageHelper;
import com.jeeplus.common.base.PageBean;
import com.jeeplus.common.base.constant.MoneyFlowStatusEnum;
import com.jeeplus.common.myResult.Result;
import com.jeeplus.common.utils.ConstantUtil;
import com.jeeplus.common.utils.IdGen;
import com.jeeplus.common.utils.StringUtils;
import com.jeeplus.modules.cash.dao.ChargeMoneyFlowDao;
import com.jeeplus.modules.cash.dao.MoneyFlowDao;
import com.jeeplus.modules.cash.dao.SysConfigDao;
import com.jeeplus.modules.cash.entity.PfmUserChargeWithDraw;
import com.jeeplus.modules.cash.entity.SysConfigEntity;
import com.jeeplus.modules.cash.entity.UserMoneyFlow;
import com.jeeplus.modules.cash.service.ChargeMoneyService;
import com.jeeplus.modules.lottery.entity.wf.WfMain;
import com.jeeplus.modules.platform.dao.PlatformUserDao;
import com.jeeplus.modules.platform.dto.RechargeDTO;
import com.jeeplus.modules.platform.entity.PlatformUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class PlatformUserService {
    @Autowired
    private ChargeMoneyFlowDao chargeMoneyFlowDao;

    @Autowired
    PlatformUserDao platformUserDao;

    @Autowired
    private SysConfigDao sysConfigDao;

    @Autowired
    private ChargeMoneyService chargeMoneyService;



    /**
     * 多条件查询分页
     * @return
     */
    public PageBean<PlatformUser> findList(PageBean<PlatformUser> pageBean, PlatformUser platformUser){
        //用插件分页
        com.github.pagehelper.Page p= PageHelper.startPage(pageBean.getPageNo(), pageBean.getPageSize(),true);
        List<PlatformUser> list=platformUserDao.findList(platformUser);
        pageBean.setCount(p.getTotal());//设置总记录数
        pageBean.setList(list);
        return pageBean;
    }



    /**
     * 用户人工充值
     * @return
     */
    public void recharge(RechargeDTO rechargeDTO) throws Exception {
        Double chuk_dml;
        //没有填写打码量，则计算出来
        if (StringUtils.isBlank(rechargeDTO.getChuk_dml())){
            //查询出充值的打码率，用于打码量的计算
            List<SysConfigEntity> sysConfigEntities = sysConfigDao.findSysConfigByType("charge_ckdml_rate");
            if (sysConfigEntities.size()>0 && sysConfigEntities.get(0).getValue() != null){
                Double rate = Double.parseDouble(sysConfigEntities.get(0).getValue());
                //钱都要乘以1000
                Double cd = Math.abs(new BigDecimal(rechargeDTO.getMoney()).multiply(new BigDecimal(1000)).doubleValue());
                chuk_dml = Math.abs(new BigDecimal(cd).multiply(new BigDecimal(rate)).doubleValue());
            }else {
                throw new Exception("打码量计算比率没有配置");
            }
        }else {
            chuk_dml=Math.abs(new BigDecimal(rechargeDTO.getChuk_dml()).multiply(new BigDecimal(1000)).doubleValue());
        }

        PlatformUser pfmUser=platformUserDao.findPfmUserByUserId(rechargeDTO.getUserId());
        if(pfmUser==null){
            throw new Exception("此用户不存在");
        }

        //保存用户充值的记录和资金流的记录
        Long changeMoney=Math.abs(new BigDecimal(rechargeDTO.getMoney()).multiply(new BigDecimal(1000)).longValue());
        String changeType="10";
        String changeTypeDetail="01";
        pfmUser.setChukDml(pfmUser.getChukDml()+chuk_dml);
        //现有余额加上充值金额
        pfmUser.setBalanceInt(pfmUser.getBalanceInt()+changeMoney);
        pfmUser.setCountRukuanMoney(pfmUser.getCountRukuanMoney()+changeMoney);              //用户总入款金额
        pfmUser.setCountRukuanTimes(pfmUser.getCountRukuanTimes()+1);                        // 用户总入款次数
        chargeMoneyService.instUserMoneyFlow(changeMoney,pfmUser,rechargeDTO.getRemark(),changeType,changeTypeDetail);


    }

    /**
     * 人工提款
     * @param rechargeDTO
     * @throws Exception
     */
    public void drawing(RechargeDTO rechargeDTO) throws Exception{
        if(StringUtils.isEmpty(rechargeDTO.getUserId())||StringUtils.isEmpty(rechargeDTO.getMoney())){
            throw new Exception("用户id或者金额不合法");
        }

        PlatformUser pfmUser=platformUserDao.findPfmUserByUserId(rechargeDTO.getUserId());
        if(pfmUser==null){
            throw new Exception("此用户不存在");
        }

        Long changeMoney=Math.abs(new BigDecimal(rechargeDTO.getMoney()).multiply(new BigDecimal(1000)).longValue());
        Double chuk_dml=Math.abs(new BigDecimal(rechargeDTO.getChuk_dml()).multiply(new BigDecimal(1000)).doubleValue());
        String changeType="11";
        String changeTypeDetail="01";
        pfmUser.setChukDml(pfmUser.getChukDml()-chuk_dml);
        pfmUser.setBalanceInt(pfmUser.getBalanceInt()-changeMoney);
        pfmUser.setCountChukuanTimes(pfmUser.getCountChukuanTimes()+1);
        pfmUser.setCountChukuanMoney(pfmUser.getCountChukuanMoney()+changeMoney);

        chargeMoneyService.instUserWithDrawMoney(changeMoney,pfmUser,rechargeDTO.getRemark(),changeType,changeTypeDetail);

    }
}
