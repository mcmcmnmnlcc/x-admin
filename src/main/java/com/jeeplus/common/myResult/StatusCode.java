package com.jeeplus.common.myResult;

public class StatusCode {
    public static final int OK=20000;//成功
    public static final int ERROR =20001;//失败
    public static final int LONGIN_SUCCESS =20002;//登陆成功
    public static final int LOGINERROR =20003;//用户名或密码错误
    public static final int ACCESSERROR =20004;//权限不足
    public static final int LONGIN_TIMEOUT =20005;//登陆超时
    public static final int TOKEN_ERROR =20006;//登陆超时
    public static final int EXCEPTION_ERROR =20007;//有异常产生

}