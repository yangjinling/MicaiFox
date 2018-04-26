package com.micai.fox.parambean;

/**
 * Created by lq on 2018/4/26.
 */

public class PayRefreshBean {

    private boolean refresh;

    public PayRefreshBean(boolean refresh) {
        this.refresh = refresh;
    }

    public boolean isRefresh() {
        return refresh;
    }

    public void setRefresh(boolean refresh) {
        this.refresh = refresh;
    }

}
