package com.micai.fox.resultbean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by louqiang on 2018/1/27.
 */

public class BankResultBean implements Serializable {
    /**
     * execResult : true
     * execMsg : 查询成功!
     * execDatas : [{"label":"北京银行","value":"0"},{"label":"招商银行","value":"1"},{"label":"中国建设银行","value":"2"},{"label":"中国农业银行","value":"3"}]
     * count : 4
     * num : 0
     */

    private boolean execResult;
    private String execMsg;
    private int count;
    private int num;
    private List<ExecDatasBean> execDatas;

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

    public List<ExecDatasBean> getExecDatas() {
        return execDatas;
    }

    public void setExecDatas(List<ExecDatasBean> execDatas) {
        this.execDatas = execDatas;
    }

    public static class ExecDatasBean implements Serializable{
        /**
         * label : 北京银行
         * value : 0
         */

        private String label;
        private String value;

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
