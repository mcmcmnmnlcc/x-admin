package com.jeeplus.modules.sys.service;

import com.jeeplus.common.base.service.BaseService;
import com.jeeplus.common.utils.IdGen;
import com.jeeplus.common.utils.StringUtils;
import com.jeeplus.modules.sys.dao.MenuDao;
import com.jeeplus.modules.sys.dao.UserDao;
import com.jeeplus.modules.sys.entity.Menu;
import com.jeeplus.modules.sys.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class SystemService extends BaseService {
    @Autowired
    private MenuDao menuDao;

    @Autowired
    private UserDao userDao;







}
