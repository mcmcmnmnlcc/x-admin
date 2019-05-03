/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.sys.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jeeplus.common.annotation.excel.ExcelField;
import com.jeeplus.common.annotation.excel.RoleListType;
import com.jeeplus.common.base.entity.DataEntity;
import com.jeeplus.common.base.entity.NdataEntity;
import com.jeeplus.common.utils.UserUtils;
import lombok.Data;
import org.assertj.core.util.Lists;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Data
public class User extends NdataEntity {

	private Office company;	// 归属公司
	private Office office;	// 归属部门
	private String loginName;// 登录名
	private String password;// 密码
	private String no;		// 工号
	private String name;	// 姓名
	private String email;	// 邮箱
	private String phone;	// 电话
	private String mobile;	// 手机
	private String userType;// 用户类型
	private String loginIp;	// 最后登陆IP
	private Date loginDate;	// 最后登陆日期
	private String loginFlag;	// 是否允许登陆
	private String photo;	// 头像
	private String qrCode;	//二维码
	private String oldLoginName;// 原登录名
	private String newPassword;	// 新密码
	private String sign;//签名
	private String hideFlag;
	private String agentFlag;
	
	private String oldLoginIp;	// 上次登陆IP
	private Date oldLoginDate;	// 上次登陆日期
	
	//private Role role;	// 根据角色查询用户条件
	
	private List<Role> roleList = Lists.newArrayList(); // 拥有角色列表
	private List<String> permissionList=Lists.newArrayList();//拥有的权限标识

	public User() {
		super();
		this.loginFlag = "1";
	}

}