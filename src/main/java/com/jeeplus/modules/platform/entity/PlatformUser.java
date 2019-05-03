package com.jeeplus.modules.platform.entity;

import com.jeeplus.common.base.entity.NdataEntity;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class PlatformUser extends NdataEntity {
    private String userId;		//用户名
    //private String user_id;		//用来用缓存中获取
    private String nickName;				//用户别名

    private String password;		//密码
    private String imageUrl;		//用户头像
    private Long balanceInt;		// 总金额 以里为单位
    private Double chukDml;			//出款打码量
    private Double currentDml;         // 用户当前打码量
    private String status;      //1为启动  0为禁用
    private String onlineStatus;      //1为在线  0为不在线
    private String bankStatus;         //是否绑定银行卡   1：绑定   0未绑定
    private String bankPasswd;         //提现密码
    private String phone;					//手机号
    private Integer userLevel;		//用户层级
    private String userPermission;		//用户权限组
    private Integer countRukuanTimes;		//累计入款次数
    private Long countRukuanMoney;		//'累计入款总额，以千为单位',
    private Integer countChukuanTimes;	//'累计出款次数',
    private Long countChukuanMoney;		//'累计出款金额，千为单位',
    private Long countDml;					// '已达打码量累计',
    private Long countTouzhuMoney;		//'投注总额，千为单位',
    private Long countZhjiangMoney;		//'中奖总额，千为单位',
    private Long countHuodongMoney;		//'活动总额,千为单位',
    private String loginIp;				//登录IP
    private String registerIp;			//注册ip
    private String wechat;				//微信
    private String qq;						//QQ
    private String agent;					//代理
    private String paichuCeshi;			//测试账号
    private String remark;

    private String platformType;  //设备类型1为安卓2为ios
    private String platformFlag;  //android获取的是imei,ios获取的是idfv
    private String deviceModel;   //设备型号
    private String loginFlag; //1为登陆，0为注册
    private String logIp;		//	操作ip

    //返回所用
    private String  totalMoney;    //统计金额
    private Long    freezeMoney;   //冻结金额

    private String beginDate;		//开始时间
    private String endDate;		//结束时间
    private String[] usersForLevel; //level的层级标识
    private Integer userLevelLock; //level的层级和锁标识
    private List<String> onlineUser; //在线用户
    //查询条件
    private String realName;			//真实姓名
    private String accountNo;			//卡号

    private String levelName;			//用户等级

    private String agentBy;   //代理者

    private String  searchPersonInfo; // 选择搜索用户类型

    private String  searchContext; // 搜索内容

    private  String  selectIp;  // 选择搜索ip类型

    private  String  ipContext; // 搜索内容

    private String  searchTime;  // 搜索时间类型

    private String  parentAgent ; // 直接上级

    private String  firstChargeFlag ; // 是否首次存入

    private Date lastLoginDate;  // 最后登录时间


    private  String  bankId;  //银行id

    private  String  bankBranch; //银行分支

    private String  agentCode; // 邀请码

    private String  countRukuanMoneyStr; //'累计入款总额，以千为单位',

    private String  countChukuanMoneyStr; //累计出款总额，以千为单位',

    private String chukDmlStr;			//出款打码量

    private String countDmlStr;			//累计打码量

    private String  chukDmlCondiction ;    //出款打码量要求

    private String  operlogFlag;  // 操作日志标志

    private  String  depositFlag; // 是否存款标志

    private  String  isPersistUser; // 是否为存留用户

    private String  betTime; //存留用户下注时间

    private String  betEndTime; //存留用户下注时间

    private String	  roomId ;;//彩票房间

    private String	 levelAlias;//等级别名

    private String	 inviteCode;//邀请码

}
