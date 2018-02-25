package com.micai.fox.resultbean;

import java.util.List;

/**
 * Created by louqiang on 2018/2/24.
 */

public class ExpertsDetailResultBean {

    /**
     * execResult : true
     * execDatas : {"professor":{"proId":"ZJ100001","proAuth":"认证","proPhoto":"/mcfox/userfiles/1/images/photo/2018/02/20180221222610_10.jpg","selfIntro":"自我介绍","proName":"proName","hitRate":0,"profitRate":0,"maxProfitRate":0,"totalNum":1},"crowdfunding":[{"crowdfundingId":"ZC100001","title":"众筹标题","amountDown":300,"amountUp":2000,"realAmount":300,"startDate":1519315200000,"status":4,"supNum":2}]}
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
         * professor : {"proId":"ZJ100001","proAuth":"认证","proPhoto":"/mcfox/userfiles/1/images/photo/2018/02/20180221222610_10.jpg","selfIntro":"自我介绍","proName":"proName","hitRate":0,"profitRate":0,"maxProfitRate":0,"totalNum":1}
         * crowdfunding : [{"crowdfundingId":"ZC100001","title":"众筹标题","amountDown":300,"amountUp":2000,"realAmount":300,"startDate":1519315200000,"status":4,"supNum":2}]
         */

        private ProfessorBean professor;
        private List<CrowdfundingBean> crowdfunding;

        public ProfessorBean getProfessor() {
            return professor;
        }

        public void setProfessor(ProfessorBean professor) {
            this.professor = professor;
        }

        public List<CrowdfundingBean> getCrowdfunding() {
            return crowdfunding;
        }

        public void setCrowdfunding(List<CrowdfundingBean> crowdfunding) {
            this.crowdfunding = crowdfunding;
        }

        public static class ProfessorBean {
            /**
             * proId : ZJ100001
             * proAuth : 认证
             * proPhoto : /mcfox/userfiles/1/images/photo/2018/02/20180221222610_10.jpg
             * selfIntro : 自我介绍
             * proName : proName
             * hitRate : 0.0
             * profitRate : 0.0
             * maxProfitRate : 0.0
             * totalNum : 1
             */

            private String proId;
            private String proAuth;
            private String proPhoto;
            private String selfIntro;
            private String proName;
            private double hitRate;
            private double profitRate;
            private double maxProfitRate;
            private int totalNum;

            public String getProId() {
                return proId;
            }

            public void setProId(String proId) {
                this.proId = proId;
            }

            public String getProAuth() {
                return proAuth;
            }

            public void setProAuth(String proAuth) {
                this.proAuth = proAuth;
            }

            public String getProPhoto() {
                return proPhoto;
            }

            public void setProPhoto(String proPhoto) {
                this.proPhoto = proPhoto;
            }

            public String getSelfIntro() {
                return selfIntro;
            }

            public void setSelfIntro(String selfIntro) {
                this.selfIntro = selfIntro;
            }

            public String getProName() {
                return proName;
            }

            public void setProName(String proName) {
                this.proName = proName;
            }

            public double getHitRate() {
                return hitRate;
            }

            public void setHitRate(double hitRate) {
                this.hitRate = hitRate;
            }

            public double getProfitRate() {
                return profitRate;
            }

            public void setProfitRate(double profitRate) {
                this.profitRate = profitRate;
            }

            public double getMaxProfitRate() {
                return maxProfitRate;
            }

            public void setMaxProfitRate(double maxProfitRate) {
                this.maxProfitRate = maxProfitRate;
            }

            public int getTotalNum() {
                return totalNum;
            }

            public void setTotalNum(int totalNum) {
                this.totalNum = totalNum;
            }
        }

        public static class CrowdfundingBean {
            /**
             * crowdfundingId : ZC100001
             * title : 众筹标题
             * amountDown : 300.0
             * amountUp : 2000.0
             * realAmount : 300.0
             * startDate : 1519315200000
             * status : 4
             * supNum : 2
             */

            private String crowdfundingId;
            private String title;
            private double amountDown;
            private double amountUp;
            private double realAmount;
            private long startDate;
            private int status;
            private int supNum;

            public String getCrowdfundingId() {
                return crowdfundingId;
            }

            public void setCrowdfundingId(String crowdfundingId) {
                this.crowdfundingId = crowdfundingId;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
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

            public long getStartDate() {
                return startDate;
            }

            public void setStartDate(long startDate) {
                this.startDate = startDate;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getSupNum() {
                return supNum;
            }

            public void setSupNum(int supNum) {
                this.supNum = supNum;
            }
        }
    }
}
