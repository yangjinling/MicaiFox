package com.micai.fox.resultbean;

/**
 * Created by louqiang on 2018/2/24.
 */

public class MyZhongChouOrderResultBean {

    /**
     * execResult : true
     * execDatas : {"orderId":"9c1531f782774f42afdd1693cd3c807b1519401914198","orderStatus":1,"purchaseAmount":200,"createDate":1519401914000,"payDate":"","cashDate":"","payType":"","cashAmount":"","title":"众筹标题","crowdfundingStatus":4,"endDate":1519488000000,"amountDown":300}
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
         * orderId : 9c1531f782774f42afdd1693cd3c807b1519401914198
         * orderStatus : 1
         * purchaseAmount : 200.0
         * createDate : 1519401914000
         * payDate :
         * cashDate :
         * payType :
         * cashAmount :
         * title : 众筹标题
         * crowdfundingStatus : 4
         * endDate : 1519488000000
         * amountDown : 300.0
         */

        private String orderId;
        private int orderStatus;
        private double purchaseAmount;
        private long createDate;
        private String payDate;
        private String cashDate;
        private String payType;
        private String cashAmount;
        private String title;
        private int crowdfundingStatus;
        private long endDate;
        private double amountDown;

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

        public String getCashDate() {
            return cashDate;
        }

        public void setCashDate(String cashDate) {
            this.cashDate = cashDate;
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
    }
}
