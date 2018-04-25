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

        private String resp_desc;
        private String ord_id;
        private String resp_code;
        private String cust_id;
        private String ord_state;

        public String getResp_desc() {
            return resp_desc;
        }

        public void setResp_desc(String resp_desc) {
            this.resp_desc = resp_desc;
        }

        public String getOrd_id() {
            return ord_id;
        }

        public void setOrd_id(String ord_id) {
            this.ord_id = ord_id;
        }

        public String getResp_code() {
            return resp_code;
        }

        public void setResp_code(String resp_code) {
            this.resp_code = resp_code;
        }

        public String getCust_id() {
            return cust_id;
        }

        public void setCust_id(String cust_id) {
            this.cust_id = cust_id;
        }

        public String getOrd_state() {
            return ord_state;
        }

        public void setOrd_state(String ord_state) {
            this.ord_state = ord_state;
        }
    }
}
