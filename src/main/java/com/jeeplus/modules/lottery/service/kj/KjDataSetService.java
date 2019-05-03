package com.jeeplus.modules.lottery.service.kj;

import com.jeeplus.common.base.service.BaseService;
import com.jeeplus.modules.lottery.dao.kj.KjDataSetDao;
import com.jeeplus.modules.lottery.entity.kj.KjDataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class KjDataSetService extends BaseService {
    @Autowired
    KjDataSetDao kjDataSetDao;

    public int getDayLastLotteryQh(String lotteryId){
        return  kjDataSetDao.getDayLastLotteryQh(lotteryId);

    }

    /**
     * 插入彩种
     * @param list
     * @throws Exception
     */
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public void instBatchKjData(List<Map<String,Object>> list) throws Exception{
        for (int i = 0; i <list.size() ; i++) {
            kjDataSetDao.instBatchKjData(list.get(i));
        }

    }

    /**
     * 插入开奖的数据
     * @param list
     * @throws Exception
     */
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public void instKjData(List<KjDataSet> list) throws Exception{
        for (int i = 0; i <list.size() ; i++) {
            kjDataSetDao.saveKjData(list.get(i));
        }
    }

    /**
     * 获取最近的一个PK10的期号
     * @return
     */
    public String getPk10LotteryQh(String lotteryId,String lastDay){
        return kjDataSetDao.getPk10LotteryQh(lotteryId,lastDay);
    }

    public KjDataSet getLhcLastEntity(){
        return kjDataSetDao.getLhcLastEntity();
    }

}
