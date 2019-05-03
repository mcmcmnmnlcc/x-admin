package com.jeeplus.test.wf;

import com.google.gson.Gson;
import com.jeeplus.modules.lottery.entity.group.LotteryWfGroup;
import com.jeeplus.modules.lottery.entity.wf.WfMain;
import com.jeeplus.modules.lottery.entity.wf.WfPlChild;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class testWf {
    @Autowired
    Gson gson;
    @Test
    public void test1(){
        LotteryWfGroup lotteryWfGroup=new LotteryWfGroup();
        lotteryWfGroup.setId("2");
        WfMain wfMain=new WfMain();
        wfMain.setWfGroup(lotteryWfGroup);
        wfMain.setName("测试玩法");
        wfMain.setWfFlag("cbt");
        wfMain.setNumFlag("1000201hncc");
        wfMain.setLotteryTpye("1");
        wfMain.setExample("测试列子");
        wfMain.setWfExplain("测试玩法说明");
        wfMain.setStatus("1");
        wfMain.setMethodHelp("测试帮助");
        wfMain.setMaxZhus(0);
        //子玩法列表
        List<WfPlChild> wfPlChildList= new ArrayList<WfPlChild>();
        WfPlChild wfPlChild1=new WfPlChild();
        wfPlChild1.setName("默认赔率(测试)");
        wfPlChild1.setPlFlag("mr_test1");
        wfPlChild1.setNumFlag("1");
        wfPlChild1.setAwardMoney(18000D);
        wfPlChild1.setMaxReturn("80");
        wfPlChild1.setRebateConvertNum(201D);
        wfPlChild1.setBaseAwardMoney(1979.7D);
        wfPlChild1.setAgentRebateMoney(178181.818D);
        wfPlChild1.setSort(1);
        wfPlChildList.add(wfPlChild1);

        WfPlChild wfPlChild2=new WfPlChild();
        wfPlChild2.setName("默认赔率(测试2)");
        wfPlChild2.setPlFlag("mr_test2");
        wfPlChild2.setNumFlag("2");
        wfPlChild2.setAwardMoney(18001D);
        wfPlChild2.setMaxReturn("81");
        wfPlChild2.setRebateConvertNum(201D);
        wfPlChild2.setBaseAwardMoney(1979.7D);
        wfPlChild2.setAgentRebateMoney(178181.818D);
        wfPlChild2.setSort(2);
        wfPlChildList.add(wfPlChild2);
        wfMain.setWfPlChildList(wfPlChildList);

        String str=gson.toJson(wfMain);
        System.out.println(str);
    }
}
