package com.micai.fox.parambean;

import java.io.Serializable;

/**
 * Created by lq on 2018/3/5.
 */

public class ZhongChouBean implements Serializable{
    private String orderId;
    private String title;
    private String money;

    public ZhongChouBean(String orderId, String title, String money) {
        this.orderId = orderId;
        this.title = title;
        this.money = money;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String crowdingId) {
        this.orderId = crowdingId;
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
