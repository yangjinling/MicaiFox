package com.micai.fox.resultbean;

import java.io.Serializable;

/**
 * Created by louqiang on 2018/2/24.
 */

public class ZhongChouDetailResultBean implements Serializable{

    /**
     * execResult : true
     * execDatas : {"crowdfundingId":"ZC100001","status":4,"title":"众筹标题","startDate":1519315200000,"endDate":1519488000000,"amountDown":300,"amountUp":2000,"realAmount":300,"cashDate":1519574400000,"remarks":"众筹说明","supNum":2,"proPhoto":"/mcfox/userfiles/1/images/photo/2018/02/20180221222610_10.jpg","proName":"proName","proAuth":"认证","proId":"ZJ100001"}
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

    public static class ExecDatasBean implements Serializable{
        /**
         * crowdfundingId : ZC100001
         * status : 4
         * title : 众筹标题
         * startDate : 1519315200000
         * endDate : 1519488000000
         * amountDown : 300.0
         * amountUp : 2000.0
         * realAmount : 300.0
         * cashDate : 1519574400000
         * remarks : 众筹说明
         * supNum : 2
         * proPhoto : /mcfox/userfiles/1/images/photo/2018/02/20180221222610_10.jpg
         * proName : proName
         * proAuth : 认证
         * proId : ZJ100001
         */

        private String crowdfundingId;
        private int status;
        private String title;
        private long startDate;
        private long endDate;
        private double amountDown;
        private double amountUp;
        private double realAmount;
        private long cashDate;
        private String remarks;
        private int supNum;
        private String proPhoto;
        private String proName;
        private String proAuth;
        private String proId;
        private float hitRate;

        public String getCrowdfundingId() {
            return crowdfundingId;
        }

        public void setCrowdfundingId(String crowdfundingId) {
            this.crowdfundingId = crowdfundingId;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public long getStartDate() {
            return startDate;
        }

        public void setStartDate(long startDate) {
            this.startDate = startDate;
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

        public double getAmountUp() {
            return amountUp;
        }

        public void setAmountUp(double amountUp) {
            this.amountUp = amountUp;
        }

        public double getRealAmount() {
            return realAmount;
        }

        public void setRealAmount(double realAmount) {
            this.realAmount = realAmount;
        }

        public long getCashDate() {
            return cashDate;
        }

        public void setCashDate(long cashDate) {
            this.cashDate = cashDate;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public int getSupNum() {
            return supNum;
        }

        public void setSupNum(int supNum) {
            this.supNum = supNum;
        }

        public String getProPhoto() {
            return proPhoto;
        }

        public void setProPhoto(String proPhoto) {
            this.proPhoto = proPhoto;
        }

        public String getProName() {
            return proName;
        }

        public void setProName(String proName) {
            this.proName = proName;
        }

        public String getProAuth() {
            return proAuth;
        }

        public void setProAuth(String proAuth) {
            this.proAuth = proAuth;
        }

        public String getProId() {
            return proId;
        }

        public void setProId(String proId) {
            this.proId = proId;
        }

        public float getHitRate() {
            return hitRate;
        }

        public void setHitRate(float hitRate) {
            this.hitRate = hitRate;
        }
    }
}
