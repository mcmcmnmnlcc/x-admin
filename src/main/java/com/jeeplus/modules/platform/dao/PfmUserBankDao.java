package com.jeeplus.modules.platform.dao;

import com.jeeplus.modules.platform.entity.PfmUserBank;

import java.util.List;

public interface PfmUserBankDao {
    public List<PfmUserBank> findList(PfmUserBank pfmUserBank);
}
