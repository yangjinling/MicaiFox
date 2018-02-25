package com.micai.fox.resultbean;

import java.util.List;

/**
 * Created by louqiang on 2018/2/25.
 */

public class ExpertsZhongchouResultBean {

    /**
     * execResult : true
     * execDatas : {"length":20,"pageNo":1,"totalRow":1,"from":0,"recordList":[{"crowdfundingId":"ZC100001","title":"众筹标题","amountDown":300,"amountUp":2000,"realAmount":300,"startDate":1519315200000,"status":4,"supNum":2}],"needCount":true,"totalPage":1}
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
         * length : 20
         * pageNo : 1
         * totalRow : 1
         * from : 0
         * recordList : [{"crowdfundingId":"ZC100001","title":"众筹标题","amountDown":300,"amountUp":2000,"realAmount":300,"startDate":1519315200000,"status":4,"supNum":2}]
         * needCount : true
         * totalPage : 1
         */

        private int length;
        private int pageNo;
        private int totalRow;
        private int from;
        private boolean needCount;
        private int totalPage;
        private List<RecordListBean> recordList;

        public int getLength() {
            return length;
        }

        public void setLength(int length) {
            this.length = length;
        }

        public int getPageNo() {
            return pageNo;
        }

        public void setPageNo(int pageNo) {
            this.pageNo = pageNo;
        }

        public int getTotalRow() {
            return totalRow;
        }

        public void setTotalRow(int totalRow) {
            this.totalRow = totalRow;
        }

        public int getFrom() {
            return from;
        }

        public void setFrom(int from) {
            this.from = from;
        }

        public boolean isNeedCount() {
            return needCount;
        }

        public void setNeedCount(boolean needCount) {
            this.needCount = needCount;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public List<RecordListBean> getRecordList() {
            return recordList;
        }

        public void setRecordList(List<RecordListBean> recordList) {
            this.recordList = recordList;
        }

        public static class RecordListBean {
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
