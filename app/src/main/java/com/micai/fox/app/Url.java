package com.micai.fox.app;

/**
 * Created by Yangjinling on 2017/12/18.
 */

public class Url {
    public static final String CONTENT_TYPE = "application/json; charset=utf-8";
    public static final String WEB_BASE_URL = "http://127.0.0.1:8080/website/a/";
    public static final String WEB_BASE_SESSION = ";JSESSIONID=%s?__ajax=true";
    /*短信验证码*/
    public static final String WEB_VALIDATE_CODE = WEB_BASE_URL + "sys/sms/send-code/m-req";//自芳
    /*校验验证码*/
    public static final String WEB_CHECK_VALIDATE_CODE = WEB_BASE_URL + "sys/sms/chk-code/m-req";//自芳
}
