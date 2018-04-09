package com.micai.fox.app;

/**
 * Created by Yangjinling on 2017/12/18.
 */

public class Url {
    public static final String CONTENT_TYPE = "application/json; charset=utf-8";
    public static final String WEB_BASE_IP = "http://39.106.29.117:18888";
    public static final String WEB_BASE_URL = "http://39.106.29.117:18888/mcfox/a/";
    //    public static final String WEB_BASE_IP="http://192.168.43.1:18888";
//    public static final String WEB_BASE_URL = "http://192.168.43.1:18888/mcfox/a/";
    public static final String WEB_BASE_SESSION = ";JSESSIONID=%s?__ajax=true";
    /*2.1.1注册短信验证码*/
    public static final String WEB_VALIDATE_CODE_REGIST = WEB_BASE_URL + "sys/sms/send-code/m-reg";//自芳
    /*2.1.2注册校验验证码*/
    public static final String WEB_CHECK_VALIDATE_CODE_REGIST = WEB_BASE_URL + "sys/sms/chk-code/m-reg";//自芳
    /*2.1.3注册手机号*/
    public static final String WEB_REGIST = WEB_BASE_URL + "sys/register/phone";
    /*2.1.4重置密码短信验证码*/
    public static final String WEB_VALIDATE_CODE_RESET = WEB_BASE_URL + "sys/sms/send-code/m-resetpwd";//自芳
    /*2.1.5重置校验验证码*/
    public static final String WEB_CHECK_VALIDATE_CODE_RESET = WEB_BASE_URL + "sys/sms/chk-code/m-resetpwd";//自芳
    /*2.1.6重置密码---未登录*/
    public static final String WEB_RESET = WEB_BASE_URL + "sys/user/resetpwdLogout";
    /*2.1.7登录*/
    public static final String WEB_LOGIN = WEB_BASE_URL + "login";
    /*2.1.8退出登录*/
    public static final String WEB_LOGOUT = WEB_BASE_URL + "app/logout" + WEB_BASE_SESSION;
    /*2.2.1收款账号查询*/
    public static final String WEB_SET_ACCOUNT_SEARCH = WEB_BASE_URL + "bd/account/search" + WEB_BASE_SESSION;
    /*2.2.2收款账号修改*/
    public static final String WEB_SET_ACCOUNT_UPDATE = WEB_BASE_URL + "bd/account/update" + WEB_BASE_SESSION;
    /*2.2.3意见反馈添加*/
    public static final String WEB_SET_IDEAR = WEB_BASE_URL + "bd/feedback/save" + WEB_BASE_SESSION;
    /*2.2.4修改密码--登录*/
    public static final String WEB_SET_PASSWORD = WEB_BASE_URL + "sys/user/resetpwdLogin" + WEB_BASE_SESSION;
    /*2.2.5修改手机号短信验证码*/
    public static final String WEB_SET_PHONE_CODE = WEB_BASE_URL + "sys/sms/send-code/m-reg" + WEB_BASE_SESSION;
    /*2.2.6修改手机号*/
    public static final String WEB_SET_PHONE = WEB_BASE_URL + "sys/user/updateMobile" + WEB_BASE_SESSION;
    /*2.2.7字典查询银行*/
    public static final String WEB_SET_BANK = WEB_BASE_URL + "sys/dict/queryDict" + WEB_BASE_SESSION;
    /*2.3.1首页--首页接口*/
    public static final String WEB_HOME = WEB_BASE_URL + "bd/home/index";
    /*2.3.2首页--众筹项目列表*/
    public static final String WEB_HOME_ZHONGCHOU = WEB_BASE_URL + "crowdfunding/listAll";
    /*2.4.1专家--专家列表*/
    public static final String WEB_EXPERTS = WEB_BASE_URL + "sys/pro/list";
    /*2.4.2专家--专家详情*/
    public static final String WEB_EXPERTS_DETAIL = WEB_BASE_URL + "sys/pro/info";
    /*2.4.3专家--众筹列表*/
    public static final String WEB_EXPERTS_ZHONCHOU = WEB_BASE_URL + "crowdfunding/listByPro";
    /*2.4.4专家--报告列表*/
    public static final String WEB_EXPERTS_REPORT = WEB_BASE_URL + "report/listByPro";
    /*2.4.5众筹--众筹详情*/
    public static final String WEB_HOME_ZHONGCHOU_DETAIL = WEB_BASE_URL + "crowdfunding/info";
    /*2.4.6众筹--众筹详情-报告Tab*/
    public static final String WEB_HOME_ZHONGCHOU_DETAIL_REPORT = WEB_BASE_URL + "crowdfunding/report";
    /*2.4.6众筹--众筹详情-披露Tab*/
    public static final String WEB_HOME_ZHONGCHOU_DETAIL_PILU = WEB_BASE_URL + "crowdfunding/lotteryTicket";
    /*2.4.8报告详情*/
    public static final String WEB_REPORT_DETAIL = WEB_BASE_URL + "report/info";
    /*2.4.7众筹--众筹详情-众筹下单*/
    public static final String WEB_HOME_ZHONGCHOU_DETAIL_BUY = WEB_BASE_URL + "crowdfunding/orderSave" + WEB_BASE_SESSION;
    /*2.5.1我的*/
    public static final String WEB_MINE = WEB_BASE_URL + "sys/user/index" + WEB_BASE_SESSION;
    /*2.5.2我的--昵称修改（完成--未测试）*/
    public static final String WEB_MINE_NICK = WEB_BASE_URL + "sys/user/updateNickName" + WEB_BASE_SESSION;
    /*2.5.3我的--头像修改*/
    public static final String WEB_MINE_HEAD_MODYFY = WEB_BASE_URL + "sys/user/updatePhoto" + WEB_BASE_SESSION;
    /*2.5.4我的--我的众筹*/
    public static final String WEB_MINE_ZHONGCHOU = WEB_BASE_URL + "crowdfunding/orderList" + WEB_BASE_SESSION;
    /*2.5.5我的--我的众筹-详情*/
    public static final String WEB_MINE_ZHONGCHOU_DETAIL = WEB_BASE_URL + "crowdfunding/orderInfo" + WEB_BASE_SESSION;
    /*2.5.6我的--我的报告*/
    public static final String WEB_MINE_REPORT = WEB_BASE_URL + "/report/listByUser" + WEB_BASE_SESSION;
    /*2.5.7我的--通知列表*/
    public static final String WEB_MINE_NOTICE = WEB_BASE_URL + "bd/notice/list" + WEB_BASE_SESSION;
    /*2.5.8我的--清空通知列表（完成）*/
    public static final String WEB_MINE_NOTICE_CLEAR = WEB_BASE_URL + "bd/notice/delete" + WEB_BASE_SESSION;
    /*2.5.9头像上传*/
    public static final String WEB_MINE_HEAD = WEB_BASE_URL + "sys/user/file/upload" + WEB_BASE_SESSION;
    /*2.5.10 登录时查询是否有未读消息*/
    public static final String WEN_CHECK_NOTICE = WEB_BASE_URL + "bd/notice/unReview" + WEB_BASE_SESSION;
    /*2.5.10 支付*/
    public static final String WEB_PAY = WEB_BASE_URL + "pay/xinfu/submit" + WEB_BASE_SESSION;
}
