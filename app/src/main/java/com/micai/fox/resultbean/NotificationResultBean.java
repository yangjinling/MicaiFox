package com.micai.fox.resultbean;

import java.util.List;

/**
 * Created by lq on 2018/2/27.
 */

public class NotificationResultBean {

    /**
     * execResult : true
     * execDatas : {"length":20,"pageNo":1,"totalRow":11,"from":0,"recordList":[{"id":"fbdfe8af7aa34e84963811886a98cace1519568101139","relId":"b40c280376284b71bf2ee91813386a0f1519401943457","content":"您有一个众筹订单还未支付，请尽快支付，以免满标后无法购买。","type":3,"reviewFlag":0,"toUserId":"3aa135cf225049d9b579340bb0f6a8d01516193937423","createBy":"sys","createDate":1519568100000,"delFlag":"0"},{"id":"f10e2c4fe80a43ebb072e19c6d3bc9f91519567202889","relId":"b40c280376284b71bf2ee91813386a0f1519401943457","content":"您有一个众筹订单还未支付，请尽快支付，以免满标后无法购买。","type":3,"reviewFlag":0,"toUserId":"3aa135cf225049d9b579340bb0f6a8d01516193937423","createBy":"sys","createDate":1519567200000,"delFlag":"0"},{"id":"934afac644c24d0a936d46b81381d91d1519566300555","relId":"b40c280376284b71bf2ee91813386a0f1519401943457","content":"您有一个众筹订单还未支付，请尽快支付，以免满标后无法购买。","type":3,"reviewFlag":0,"toUserId":"3aa135cf225049d9b579340bb0f6a8d01516193937423","createBy":"sys","createDate":1519566300000,"delFlag":"0"},{"id":"5d97de3a8aa442bc869186df86f3c8f11519564500111","relId":"b40c280376284b71bf2ee91813386a0f1519401943457","content":"您有一个众筹订单还未支付，请尽快支付，以免满标后无法购买。","type":3,"reviewFlag":0,"toUserId":"3aa135cf225049d9b579340bb0f6a8d01516193937423","createBy":"sys","createDate":1519564500000,"delFlag":"0"},{"id":"616b227d85ce44e7b3f80c75b3ce22ab1519563600462","relId":"b40c280376284b71bf2ee91813386a0f1519401943457","content":"您有一个众筹订单还未支付，请尽快支付，以免满标后无法购买。","type":3,"reviewFlag":0,"toUserId":"3aa135cf225049d9b579340bb0f6a8d01516193937423","createBy":"sys","createDate":1519563600000,"delFlag":"0"},{"id":"c63ae4f79980498897863d2b42554b891519540202217","relId":"b40c280376284b71bf2ee91813386a0f1519401943457","content":"您有一个众筹订单还未支付，请尽快支付，以免满标后无法购买。","type":3,"reviewFlag":0,"toUserId":"3aa135cf225049d9b579340bb0f6a8d01516193937423","createBy":"sys","createDate":1519540200000,"delFlag":"0"},{"id":"e158172c7c2f415bb7469d0a83144fe91519539300360","relId":"b40c280376284b71bf2ee91813386a0f1519401943457","content":"您有一个众筹订单还未支付，请尽快支付，以免满标后无法购买。","type":3,"reviewFlag":0,"toUserId":"3aa135cf225049d9b579340bb0f6a8d01516193937423","createBy":"sys","createDate":1519539300000,"delFlag":"0"},{"id":"5363d34efe6f4363891a87d62a1f2cbc1519538400591","relId":"b40c280376284b71bf2ee91813386a0f1519401943457","content":"您有一个众筹订单还未支付，请尽快支付，以免满标后无法购买。","type":3,"reviewFlag":0,"toUserId":"3aa135cf225049d9b579340bb0f6a8d01516193937423","createBy":"sys","createDate":1519538400000,"delFlag":"0"},{"id":"d7330ce2f6404a3981c216975b96de561519537500113","relId":"b40c280376284b71bf2ee91813386a0f1519401943457","content":"您有一个众筹订单还未支付，请尽快支付，以免满标后无法购买。","type":3,"reviewFlag":0,"toUserId":"3aa135cf225049d9b579340bb0f6a8d01516193937423","createBy":"sys","createDate":1519537500000,"delFlag":"0"},{"id":"3b457aebf92f4978ab51154df312b7fd1519536601300","relId":"b40c280376284b71bf2ee91813386a0f1519401943457","content":"您有一个众筹订单还未支付，请尽快支付，以免满标后无法购买。","type":3,"reviewFlag":0,"toUserId":"3aa135cf225049d9b579340bb0f6a8d01516193937423","createBy":"sys","createDate":1519536600000,"delFlag":"0"},{"id":"1","relId":"ZC100001","content":"您有一个众筹订单未支付，请尽快支付","type":3,"reviewFlag":0,"toUserId":"3aa135cf225049d9b579340bb0f6a8d01516193937423","createBy":"1","createDate":1519402912000,"delFlag":"0"}],"needCount":true,"totalPage":1}
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
         * totalRow : 11
         * from : 0
         * recordList : [{"id":"fbdfe8af7aa34e84963811886a98cace1519568101139","relId":"b40c280376284b71bf2ee91813386a0f1519401943457","content":"您有一个众筹订单还未支付，请尽快支付，以免满标后无法购买。","type":3,"reviewFlag":0,"toUserId":"3aa135cf225049d9b579340bb0f6a8d01516193937423","createBy":"sys","createDate":1519568100000,"delFlag":"0"},{"id":"f10e2c4fe80a43ebb072e19c6d3bc9f91519567202889","relId":"b40c280376284b71bf2ee91813386a0f1519401943457","content":"您有一个众筹订单还未支付，请尽快支付，以免满标后无法购买。","type":3,"reviewFlag":0,"toUserId":"3aa135cf225049d9b579340bb0f6a8d01516193937423","createBy":"sys","createDate":1519567200000,"delFlag":"0"},{"id":"934afac644c24d0a936d46b81381d91d1519566300555","relId":"b40c280376284b71bf2ee91813386a0f1519401943457","content":"您有一个众筹订单还未支付，请尽快支付，以免满标后无法购买。","type":3,"reviewFlag":0,"toUserId":"3aa135cf225049d9b579340bb0f6a8d01516193937423","createBy":"sys","createDate":1519566300000,"delFlag":"0"},{"id":"5d97de3a8aa442bc869186df86f3c8f11519564500111","relId":"b40c280376284b71bf2ee91813386a0f1519401943457","content":"您有一个众筹订单还未支付，请尽快支付，以免满标后无法购买。","type":3,"reviewFlag":0,"toUserId":"3aa135cf225049d9b579340bb0f6a8d01516193937423","createBy":"sys","createDate":1519564500000,"delFlag":"0"},{"id":"616b227d85ce44e7b3f80c75b3ce22ab1519563600462","relId":"b40c280376284b71bf2ee91813386a0f1519401943457","content":"您有一个众筹订单还未支付，请尽快支付，以免满标后无法购买。","type":3,"reviewFlag":0,"toUserId":"3aa135cf225049d9b579340bb0f6a8d01516193937423","createBy":"sys","createDate":1519563600000,"delFlag":"0"},{"id":"c63ae4f79980498897863d2b42554b891519540202217","relId":"b40c280376284b71bf2ee91813386a0f1519401943457","content":"您有一个众筹订单还未支付，请尽快支付，以免满标后无法购买。","type":3,"reviewFlag":0,"toUserId":"3aa135cf225049d9b579340bb0f6a8d01516193937423","createBy":"sys","createDate":1519540200000,"delFlag":"0"},{"id":"e158172c7c2f415bb7469d0a83144fe91519539300360","relId":"b40c280376284b71bf2ee91813386a0f1519401943457","content":"您有一个众筹订单还未支付，请尽快支付，以免满标后无法购买。","type":3,"reviewFlag":0,"toUserId":"3aa135cf225049d9b579340bb0f6a8d01516193937423","createBy":"sys","createDate":1519539300000,"delFlag":"0"},{"id":"5363d34efe6f4363891a87d62a1f2cbc1519538400591","relId":"b40c280376284b71bf2ee91813386a0f1519401943457","content":"您有一个众筹订单还未支付，请尽快支付，以免满标后无法购买。","type":3,"reviewFlag":0,"toUserId":"3aa135cf225049d9b579340bb0f6a8d01516193937423","createBy":"sys","createDate":1519538400000,"delFlag":"0"},{"id":"d7330ce2f6404a3981c216975b96de561519537500113","relId":"b40c280376284b71bf2ee91813386a0f1519401943457","content":"您有一个众筹订单还未支付，请尽快支付，以免满标后无法购买。","type":3,"reviewFlag":0,"toUserId":"3aa135cf225049d9b579340bb0f6a8d01516193937423","createBy":"sys","createDate":1519537500000,"delFlag":"0"},{"id":"3b457aebf92f4978ab51154df312b7fd1519536601300","relId":"b40c280376284b71bf2ee91813386a0f1519401943457","content":"您有一个众筹订单还未支付，请尽快支付，以免满标后无法购买。","type":3,"reviewFlag":0,"toUserId":"3aa135cf225049d9b579340bb0f6a8d01516193937423","createBy":"sys","createDate":1519536600000,"delFlag":"0"},{"id":"1","relId":"ZC100001","content":"您有一个众筹订单未支付，请尽快支付","type":3,"reviewFlag":0,"toUserId":"3aa135cf225049d9b579340bb0f6a8d01516193937423","createBy":"1","createDate":1519402912000,"delFlag":"0"}]
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
             * id : fbdfe8af7aa34e84963811886a98cace1519568101139
             * relId : b40c280376284b71bf2ee91813386a0f1519401943457
             * content : 您有一个众筹订单还未支付，请尽快支付，以免满标后无法购买。
             * type : 3
             * reviewFlag : 0
             * toUserId : 3aa135cf225049d9b579340bb0f6a8d01516193937423
             * createBy : sys
             * createDate : 1519568100000
             * delFlag : 0
             */

            private String id;
            private String relId;
            private String content;
            private int type;
            private int reviewFlag;
            private String toUserId;
            private String createBy;
            private long createDate;
            private String delFlag;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getRelId() {
                return relId;
            }

            public void setRelId(String relId) {
                this.relId = relId;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getReviewFlag() {
                return reviewFlag;
            }

            public void setReviewFlag(int reviewFlag) {
                this.reviewFlag = reviewFlag;
            }

            public String getToUserId() {
                return toUserId;
            }

            public void setToUserId(String toUserId) {
                this.toUserId = toUserId;
            }

            public String getCreateBy() {
                return createBy;
            }

            public void setCreateBy(String createBy) {
                this.createBy = createBy;
            }

            public long getCreateDate() {
                return createDate;
            }

            public void setCreateDate(long createDate) {
                this.createDate = createDate;
            }

            public String getDelFlag() {
                return delFlag;
            }

            public void setDelFlag(String delFlag) {
                this.delFlag = delFlag;
            }
        }
    }
}
