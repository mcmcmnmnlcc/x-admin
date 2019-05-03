package com.jeeplus.modules.lottery.service.group;

import com.jeeplus.modules.lottery.dao.group.LotteryWfGroupDao;
import com.jeeplus.modules.lottery.entity.group.LotteryWfGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class LotteryWfGroupService {
    @Autowired
    LotteryWfGroupDao lotteryWfGroupDao;

    public LotteryWfGroup get(String id){
        return lotteryWfGroupDao.get(id);
    }

    public void save(LotteryWfGroup lotteryWfGroup){
        lotteryWfGroup.setCreateDate(new Date());
        lotteryWfGroup.setUpdateDate(new Date());
        lotteryWfGroupDao.insert(lotteryWfGroup);
    }

    public int update(LotteryWfGroup lotteryWfGroup){
        lotteryWfGroup.setUpdateDate(new Date());
        return lotteryWfGroupDao.update(lotteryWfGroup);
    }

    public int delete(String id){
        return lotteryWfGroupDao.delete(id);
    }
}
