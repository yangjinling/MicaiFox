package com.micai.fox.app;

import android.content.Context;

/**
 * Created by yangjinling on 2017/12/18.
 */

public class Config {
    private Context mContext;
    private static Config mAppConfig;
    private String appId;//微信支付的appid
    private String photoUrl;
    private String sessionId;
    private String phone;
    private String clientId;
    private String content;
    private boolean noti = false;
    private boolean isSet = false;
    private boolean isJin = false;
    private boolean loginAndNo = false;
    private boolean isLoginFromBuy;
    private String payId;
    private boolean check=false;
    public String getPayId() {
        return payId;
    }

    public void setPayId(String payId) {
        this.payId = payId;
    }
    private Config() {
    }

    public static Config getInstance() {
        // 先检查实例是否存在，如果不存在才进入下面的同步块
        if (mAppConfig == null) {
            // 同步块，线程安全的创建实例
            synchronized (Config.class) {
                // 再次检查实例是否存在，如果不存在才真正的创建实例
                if (mAppConfig == null) {
                    mAppConfig = new Config();
                }
            }
        }
        return mAppConfig;
    }

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public boolean isNoti() {
        return noti;
    }

    public void setNoti(boolean noti) {
        this.noti = noti;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isSet() {
        return isSet;
    }

    public void setSet(boolean set) {
        isSet = set;
    }

    public boolean isJin() {
        return isJin;
    }

    public void setJin(boolean jin) {
        isJin = jin;
    }

    public boolean isLoginAndNo() {
        return loginAndNo;
    }

    public void setLoginAndNo(boolean loginAndNo) {
        this.loginAndNo = loginAndNo;
    }

    public boolean isLoginFromBuy() {
        return isLoginFromBuy;
    }

    public void setLoginFromBuy(boolean loginFromBuy) {
        isLoginFromBuy = loginFromBuy;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
}
