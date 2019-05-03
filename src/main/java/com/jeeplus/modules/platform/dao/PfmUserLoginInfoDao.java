package com.jeeplus.modules.platform.dao;

import com.jeeplus.modules.platform.entity.PlatformUser;

import javax.validation.constraints.Null;
import java.util.List;

public interface PfmUserLoginInfoDao {

    public List<PlatformUser> findList(PlatformUser platformUser);
}
