package com.micai.fox.parambean;

import java.io.Serializable;

/**
 * Created by lq on 2018/3/5.
 */

public class ZhongChouBean implements Serializable{
    private String crowdingId;
    private String title;
    private String money;

    public ZhongChouBean(String crowdingId, String title, String money) {
        this.crowdingId = crowdingId;
        this.title = title;
        this.money = money;
    }

    public String getCrowdingId() {
        return crowdingId;
    }

    public void setCrowdingId(String crowdingId) {
        this.crowdingId = crowdingId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}
