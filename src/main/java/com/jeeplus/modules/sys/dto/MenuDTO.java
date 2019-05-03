package com.jeeplus.modules.sys.dto;

import com.google.gson.Gson;
import com.jeeplus.common.utils.IdGen;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@ApiModel(value="Menu",description="菜单模型")
@Data
public class MenuDTO {
    private String id;
    @ApiModelProperty(value="菜单名称",name="name",example="功能菜单")
    @NotBlank(message="菜单名称不能为空")
    private String name; 	// 名称
    private String href; 	// 链接
    private String target; 	// 目标（ mainFrame、_blank、_self、_parent、_top）
    private String icon; 	// 图标
    private Integer sort; 	// 排序
    private String isShow; 	// 是否在菜单中显示（1：显示；0：不显示）
    private String permission; // 权限标识
    private String remark;//备注
    @NotBlank(message="父节点菜单不能为空")
    private String parentId;//父节点的

//    public static void main(String[] args) {
//        Gson gson=new Gson();
//        MenuDTO menuDTO=new MenuDTO();
//        menuDTO.setId(IdGen.uuid());
//        menuDTO.setHref("www.baidu.com");
//        menuDTO.setTarget("_blank");
//        menuDTO.setIcon("http://abc.com");
//        menuDTO.setSort(30);
//        menuDTO.setIsShow("1");
//        menuDTO.setParentId("1");
//        menuDTO.setRemark("这是一个测试菜单");
//        menuDTO.setPermission("aa:bb:cc:11");
//        String str=gson.toJson(menuDTO);
//        System.out.println(str);
//    }
}


