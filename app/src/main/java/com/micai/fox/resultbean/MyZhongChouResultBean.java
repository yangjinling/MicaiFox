package com.micai.fox.resultbean;

import java.util.List;

/**
 * Created by louqiang on 2018/2/24.
 */

public class MyZhongChouResultBean {

    /**
     * execResult : true
     * execDatas : {"length":20,"pageNo":1,"totalRow":2,"from":0,"recordList":[{"orderId":"23459453a7514a53b68eff75ff381cec1519401930691","crowdfundingId":"ZC100001","purchaseAmount":100,"createDate":1519401931000,"orderStatus":1,"title":"众筹标题","crowdfundingStatus":4},{"orderId":"9c1531f782774f42afdd1693cd3c807b1519401914198","crowdfundingId":"ZC100001","purchaseAmount":200,"createDate":1519401914000,"orderStatus":1,"title":"众筹标题","crowdfundingStatus":4}],"needCount":true,"totalPage":1}
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
         * totalRow : 2
         * from : 0
         * recordList : [{"orderId":"23459453a7514a53b68eff75ff381cec1519401930691","crowdfundingId":"ZC100001","purchaseAmount":100,"createDate":1519401931000,"orderStatus":1,"title":"众筹标题","crowdfundingStatus":4},{"orderId":"9c1531f782774f42afdd1693cd3c807b1519401914198","crowdfundingId":"ZC100001","purchaseAmount":200,"createDate":1519401914000,"orderStatus":1,"title":"众筹标题","crowdfundingStatus":4}]
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
             * orderId : 23459453a7514a53b68eff75ff381cec1519401930691
             * crowdfundingId : ZC100001
             * purchaseAmount : 100.0
             * createDate : 1519401931000
             * orderStatus : 1
             * title : 众筹标题
             * crowdfundingStatus : 4
             */

            private String orderId;
            private String crowdfundingId;
            private String purchaseAmount;
            private long createDate;
            private int orderStatus;
            private String title;
            private int crowdfundingStatus;

            public String getOrderId() {
                return orderId;
            }

            public void setOrderId(String orderId) {
                this.orderId = orderId;
            }

            public String getCrowdfundingId() {
                return crowdfundingId;
            }

            public void setCrowdfundingId(String crowdfundingId) {
                this.crowdfundingId = crowdfundingId;
            }

            public String getPurchaseAmount() {
                return purchaseAmount;
            }

            public void setPurchaseAmount(String purchaseAmount) {
                this.purchaseAmount = purchaseAmount;
            }

            public long getCreateDate() {
                return createDate;
            }

            public void setCreateDate(long createDate) {
                this.createDate = createDate;
            }

            public int getOrderStatus() {
                return orderStatus;
            }

            public void setOrderStatus(int orderStatus) {
                this.orderStatus = orderStatus;
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
        }
    }
}
