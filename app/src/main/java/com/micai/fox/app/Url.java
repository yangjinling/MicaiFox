package com.micai.fox.app;

/**
 * Created by Yangjinling on 2017/12/18.
 */

public class Url {
    public static final String CONTENT_TYPE = "application/json; charset=utf-8";
    public static final String WEB_BASE_URL = "http://39.106.29.117:18888/mcfox/a/";
    public static final String WEB_BASE_SESSION = ";JSESSIONID=%s?__ajax=true";
    /*2.1.1注册短信验证码*/
    public static final String WEB_VALIDATE_CODE_REGIST = WEB_BASE_URL + "sys/sms/send-code/m-reg";//自芳
    /*2.1.2注册校验验证码*/
    public static final String WEB_CHECK_VALIDATE_CODE_REGIST = WEB_BASE_URL + "sys/sms/chk-code/m-reg";//自芳
    /*2.1.3注册手机号*/
    public static final String WEB_REGIST=WEB_BASE_URL+"sys/register/phone";
    /*2.1.4重置密码短信验证码*/
    public static final String WEB_VALIDATE_CODE_RESET = WEB_BASE_URL + "sys/sms/send-code/m-resetpwd";//自芳
    /*2.1.5重置校验验证码*/
    public static final String WEB_CHECK_VALIDATE_CODE_RESET = WEB_BASE_URL + "sys/sms/chk-code/m-resetpwd";//自芳
    /*2.1.6重置密码---未登录*/
    public static final String WEB_RESET=WEB_BASE_URL+"sys/user/resetpwdLogout";
    /*2.1.7登录*/
    public static final String WEB_LOGIN=WEB_BASE_URL+"login";
    /*2.1.8退出登录*/
    public static final String WEB_LOGOUT=WEB_BASE_URL+"app/logout"+WEB_BASE_SESSION;
    /*2.2.1收款账号查询*/
    public static final String WEB_SET_ACCOUNT_SEARCH=WEB_BASE_URL+"bd/account/search"+WEB_BASE_SESSION;
    /*2.2.2收款账号修改*/
    public static final String WEB_SET_ACCOUNT_UPDATE=WEB_BASE_URL+"bd/account/update"+WEB_BASE_SESSION;
    /*2.2.3意见反馈添加*/
    public static final String WEB_SET_IDEAR=WEB_BASE_URL+"bd/feedback/save"+WEB_BASE_SESSION;
    /*2.2.4修改密码--登录*/
    public static final String WEB_SET_PASSWORD=WEB_BASE_URL+"sys/user/resetpwdLogin"+WEB_BASE_SESSION;
    /*2.2.5修改手机号短信验证码*/
    public static final String WEB_SET_PHONE_CODE=WEB_BASE_URL+"sys/sms/send-code/m-reg"+WEB_BASE_SESSION;
    /*2.2.6修改手机号*/
    public static final String WEB_SET_PHONE=WEB_BASE_URL+"sys/user/updateMobile"+WEB_BASE_SESSION;



}
