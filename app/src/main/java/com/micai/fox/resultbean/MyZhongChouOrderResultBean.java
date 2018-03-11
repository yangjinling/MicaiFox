package com.micai.fox.resultbean;

/**
 * Created by louqiang on 2018/2/24.
 */

public class MyZhongChouOrderResultBean {

    /**
     * execResult : true
     * execDatas : {"orderId":"f25322e548514c4b875ea319c8ad28411520683867501","orderStatus":0,"purchaseAmount":10,"createDate":1520683868000,"payDate":"","payType":"","cashAmount":"","title":"test031001","crowdfundingStatus":1,"endDate":1520784000000,"amountDown":100,"cashDate":1520870400000}
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
         * orderId : f25322e548514c4b875ea319c8ad28411520683867501
         * orderStatus : 0
         * purchaseAmount : 10.0
         * createDate : 1520683868000
         * payDate :
         * payType :
         * cashAmount :
         * title : test031001
         * crowdfundingStatus : 1
         * endDate : 1520784000000
         * amountDown : 100.0
         * cashDate : 1520870400000
         */

        private String orderId;
        private int orderStatus;
        private double purchaseAmount;
        private long createDate;
        private String payDate;
        private String payType;
        private String cashAmount;
        private String title;
        private int crowdfundingStatus;
        private long endDate;
        private double amountDown;
        private long cashDate;

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public int getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(int orderStatus) {
            this.orderStatus = orderStatus;
        }

        public double getPurchaseAmount() {
            return purchaseAmount;
        }

        public void setPurchaseAmount(double purchaseAmount) {
            this.purchaseAmount = purchaseAmount;
        }

        public long getCreateDate() {
            return createDate;
        }

        public void setCreateDate(long createDate) {
            this.createDate = createDate;
        }

        public String getPayDate() {
            return payDate;
        }

        public void setPayDate(String payDate) {
            this.payDate = payDate;
        }

        public String getPayType() {
            return payType;
        }

        public void setPayType(String payType) {
            this.payType = payType;
        }

        public String getCashAmount() {
            return cashAmount;
        }

        public void setCashAmount(String cashAmount) {
            this.cashAmount = cashAmount;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getCrowdfundingStatus() {
            return crowdfundingStatus;
        }

        public void setCrowdfundingStatus(int crowdfundingStatus) {
            this.crowdfundingStatus = crowdfundingStatus;
        }

        public long getEndDate() {
            return endDate;
        }

        public void setEndDate(long endDate) {
            this.endDate = endDate;
        }

        public double getAmountDown() {
            return amountDown;
        }

        public void setAmountDown(double amountDown) {
            this.amountDown = amountDown;
        }

        public long getCashDate() {
            return cashDate;
        }

        public void setCashDate(long cashDate) {
            this.cashDate = cashDate;
        }
    }
}
