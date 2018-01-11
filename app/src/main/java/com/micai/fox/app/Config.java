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
}
