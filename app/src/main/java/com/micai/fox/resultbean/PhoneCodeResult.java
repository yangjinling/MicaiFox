package com.micai.fox.resultbean;

/**
 * Created by louqiang on 2018/3/25.
 */

public class PhoneCodeResult {

    /**
     * execResult : true
     * execDatas : {"code":"0","successed":true}
     * count : 1
     * num : 0
     */

    private boolean execResult;
    private ExecDatasBean execDatas;
    private int count;
    private int num;
    private String execMsg;

    public boolean isExecResult() {
        return execResult;
    }

    public void setExecResult(boolean execResult) {
        this.execResult = execResult;
    }

    public ExecDatasBean getExecDatas() {
        return execDatas;
    }

    public void setExecDatas(ExecDatasBean execDatas) {
        this.execDatas = execDatas;
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

    public String getExecMsg() {
        return execMsg;
    }

    public void setExecMsg(String execMsg) {
        this.execMsg = execMsg;
    }

    public static class ExecDatasBean {
        /**
         * code : 0
         * successed : true
         */

        private String code;
        private boolean successed;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public boolean isSuccessed() {
            return successed;
        }

        public void setSuccessed(boolean successed) {
            this.successed = successed;
        }
    }
}
