package com.micai.fox.resultbean;

/**
 * Created by louqiang on 2018/1/20.
 */

public class AccountInfoResult {

    /**
     * execResult : true
     * execDatas : {"accountName":"账户名称","accountBranch":"开户支行","accountBank":"账户银行","accountNumber":"12345677888"}
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
         * accountName : 账户名称
         * accountBranch : 开户支行
         * accountBank : 账户银行
         * accountNumber : 12345677888
         */

        private String accountName;
        private String accountBranch;
        private String accountBank;
        private String accountNumber;

        public String getAccountName() {
            return accountName;
        }

        public void setAccountName(String accountName) {
            this.accountName = accountName;
        }

        public String getAccountBranch() {
            return accountBranch;
        }

        public void setAccountBranch(String accountBranch) {
            this.accountBranch = accountBranch;
        }

        public String getAccountBank() {
            return accountBank;
        }

        public void setAccountBank(String accountBank) {
            this.accountBank = accountBank;
        }

        public String getAccountNumber() {
            return accountNumber;
        }

        public void setAccountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
        }
    }
}
