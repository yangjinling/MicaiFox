package com.micai.fox.resultbean;

/**
 * Created by lq on 2018/4/25.
 */

public class CheckPayResultBean {

    /**
     * execResult : true
     * execDatas : {"resp_desc":"验签失败！","ord_id":"1524548477853571609","resp_code":"1002","cust_id":"4001061867"}
     * count : 0
     * num : 0
     */

    private boolean execResult;
    private ExecDatasBean execDatas;
    private int count;
    private int num;

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

    public static class ExecDatasBean {
        /**
         * resp_desc : 验签失败！
         * ord_id : 1524548477853571609
         * resp_code : 1002
         * cust_id : 4001061867
         */

        private String orderId;
        private String status;

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}