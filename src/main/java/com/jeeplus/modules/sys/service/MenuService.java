package com.jeeplus.modules.sys.service;

import com.jeeplus.common.base.controller.BaseController;
import com.jeeplus.common.base.service.BaseService;
import com.jeeplus.modules.sys.dao.MenuDao;
import com.jeeplus.modules.sys.entity.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MenuService extends BaseService {
    @Autowired
    private MenuDao menuDao;

    /**
     * 根据用户id获取该用户菜单列表
     * @param userId
     * @return
     */
    public List<Menu> getMenuListByUserid(String userId){
        Menu menu=new Menu();
        menu.setUserId(userId);
        List<Menu> menuList=menuDao.findByUserId(menu);
        return menuList;
    }

    /**
     * 这是一个递归方法，依次返回父节点id
     * @param id
     * @param list
     * @return
     */
    public String findParentId(String id,List<String> list){

        String str=menuDao.findParentId(id);
        list.add(str);
        if(null==str || "0".equals(str)){
            return "0";
        }else{
            return findParentId(str,list);
        }
    }

    public int insert(Menu menu){
        return menuDao.insert(menu);
    }

    public int update(Menu menu){
        return menuDao.update(menu);
    }
}
