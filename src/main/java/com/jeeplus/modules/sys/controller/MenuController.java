package com.jeeplus.modules.sys.controller;

import com.jeeplus.common.base.controller.BaseController;
import com.jeeplus.common.myResult.Result;
import com.jeeplus.common.myResult.StatusCode;
import com.jeeplus.common.utils.IdGen;
import com.jeeplus.common.utils.ThreadAttributes;
import com.jeeplus.modules.sys.dto.MenuDTO;
import com.jeeplus.modules.sys.entity.Menu;
import com.jeeplus.modules.sys.entity.User;
import com.jeeplus.modules.sys.service.MenuService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Api(value = "菜单模块")
@RestController
@RequestMapping(value = "/sys/menu")
public class MenuController extends BaseController {
    @Autowired
    MenuService menuService;

//    @RequestMapping(value = "/tt",method = RequestMethod.GET)
//    public void test(){
//        List<String> list=new ArrayList<>();
//        String str=menuService.findParentId("7ec930fe50fb41d0a9c7aeaa01804d16",list);
//        System.out.println("递归输出---------------");
//        int size=list.size();
//        for (int i=size-1;i>=0;i--){
//            System.out.print(list.get(i)+",");
//        }
//        System.out.println("递归输出完毕------------------");
//    }

    @ApiOperation(value = "根据用户获取对应的菜单", notes = "参数信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, paramType = "path", dataType = "String"),
            @ApiImplicitParam(paramType="header",name="Authorization",dataType="String",value="token放在head中",defaultValue=""),
            //@ApiImplicitParam(paramType="query",name="password",dataType="String",required=true,value="用户的密码",defaultValue="wangna")
    })
//    @ApiResponses({
//            @ApiResponse(code=StatusCode.OK,message="成功"),
//            @ApiResponse(code=StatusCode.ERROR,message="失败"),
//            @ApiResponse(code=StatusCode.ACCESSERROR,message="无权访问"),
//            @ApiResponse(code=StatusCode.TOKEN_ERROR,message="令牌错误")
//    })
    @RequestMapping(value = "/{userId}",method = RequestMethod.GET)
    public Result<Menu> getByUserId(@PathVariable String userId){
        String str=(String) ThreadAttributes.getThreadAttribute("username");
        List<Menu> menuList=menuService.getMenuListByUserid(userId);
        Result<Menu> result=new Result(true, StatusCode.OK,"查询成功",menuList);
        return result;
    }


    /**
     * 添加菜单
     * @param menuDTO
     * @param eresult
     * @return
     */
    @ApiOperation(value = "新增菜单", notes = "")
    @RequestMapping(value = "/",method = RequestMethod.POST)
    public Result insert(@RequestBody @Valid MenuDTO menuDTO,BindingResult eresult){
        if(eresult.hasErrors()){
            for (ObjectError error : eresult.getAllErrors()) {
                Result result=new Result(true, StatusCode.ERROR,"添加失败",error.getDefaultMessage());
                return result;
            }
        }

        //把dto中的数据放到实体类中
        Menu menu=new Menu();
        menu.setId(IdGen.uuid());
        menu.setName(menuDTO.getName());
        menu.setHref(menuDTO.getHref());
        menu.setTarget(menuDTO.getTarget());
        menu.setIcon(menuDTO.getIcon());
        menu.setSort(menuDTO.getSort());
        menu.setIsShow(menuDTO.getIsShow());
        menu.setPermission(menuDTO.getPermission());
        menu.setRemarks(menuDTO.getRemark());

        Menu menuPrenrent=new Menu();
        menuPrenrent.setId(menuDTO.getParentId());
        menu.setParent(menuPrenrent);

        List<String> list=new ArrayList<>();
        menuService.findParentId(menuDTO.getParentId(),list);

        //System.out.println("递归输出---------------");
        int size=list.size();
        String ids="";
        for (int i=size-1;i>=0;i--){
            ids=ids+list.get(i)+",";
            if("null,".equals(ids)){//父id为0的时候会出现这种情况
                ids="";
            }
            //System.out.print(list.get(i)+",");
        }
        if(!menuDTO.equals("0")){//只要父id不是0，就要把自己的父id也加上，
            ids=ids+menuDTO.getParentId()+",";
        }
        //System.out.println("递归输出完毕------------------");
        menu.setParentIds(ids);
        //取出当前发送令牌的用户id
        String adminId= (String) ThreadAttributes.getThreadAttribute("userid");
        if(null==adminId || "".equals(adminId)){
            Result<Menu> result=new Result(false, StatusCode.ERROR,"无效的管理员");
            return result;
        }else{
            User user=new User();
            user.setId(adminId);
            menu.setCreateBy(user);
            menu.setCreateDate(new Date());
            menu.setUpdateBy(user);
            menu.setUpdateDate(new Date());
            menuService.insert(menu);
            Result<Menu> result=new Result(true, StatusCode.OK,"插入成功");
            return result;
        }

    }

    @RequestMapping(value = "/",method = RequestMethod.PUT)
    public Result update(Menu menu){
        Result<Menu> result=new Result(true, StatusCode.OK,"更新成功");
        return result;
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public Result delete(@PathVariable String id){
        Result<Menu> result=new Result(true, StatusCode.OK,"删除成功");
        return result;
    }
}
