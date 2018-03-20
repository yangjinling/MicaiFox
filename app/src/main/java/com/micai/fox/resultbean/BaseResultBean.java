package com.micai.fox.resultbean;

import java.io.Serializable;

/**
 * Created by louqiang on 2018/1/20.
 */

public class BaseResultBean implements Serializable {

    /**
     * execResult : true
     * execMsg : 更新成功！
     * count : 0
     * num : 0
     */

    private boolean execResult;
    private String execMsg;
    private int count;
    private int num;
    private String execDatas;

    public boolean isExecResult() {
        return execResult;
    }

    public void setExecResult(boolean execResult) {
        this.execResult = execResult;
    }

    public String getExecMsg() {
        return execMsg;
    }

    public void setExecMsg(String execMsg) {
        this.execMsg = execMsg;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getExecDatas() {
        return execDatas;
    }

    public void setExecDatas(String execDatas) {
        this.execDatas = execDatas;
    }
}
