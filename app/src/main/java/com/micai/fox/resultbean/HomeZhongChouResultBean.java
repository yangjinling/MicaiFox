package com.micai.fox.resultbean;

import java.util.List;

/**
 * Created by louqiang on 2018/3/4.
 */

public class HomeZhongChouResultBean {

    /**
     * execResult : true
     * execDatas : {"length":20,"pageNo":1,"totalRow":5,"from":0,"recordList":[{"crowdfundingId":"ZC100005","title":"2.28测试二","amountDown":1000,"amountUp":2000,"realAmount":50,"startDate":1519747200000,"status":3,"proId":"ZJ100002","proName":"张三","proPhoto":"/mcfox/userfiles/1/images/photo/2018/02/mmexport1494415441640.jpg","proAuth":"zhangsan","hitRate":0,"supNum":1},{"crowdfundingId":"ZC100004","title":"2.28测试","amountDown":1000,"amountUp":1600,"realAmount":40,"startDate":1519747200000,"status":5,"proId":"ZJ100002","proName":"张三","proPhoto":"/mcfox/userfiles/1/images/photo/2018/02/mmexport1494415441640.jpg","proAuth":"zhangsan","hitRate":0,"supNum":1},{"crowdfundingId":"ZC100003","title":"我的第一次测试","amountDown":13,"amountUp":24,"realAmount":0,"startDate":1519660800000,"status":9,"proId":"ZJ100002","proName":"张三","proPhoto":"/mcfox/userfiles/1/images/photo/2018/02/mmexport1494415441640.jpg","proAuth":"zhangsan","hitRate":0,"supNum":0},{"crowdfundingId":"ZC100002","title":"20180226","amountDown":1000,"amountUp":2000,"realAmount":200,"startDate":1519574400000,"status":5,"proId":"ZJ100001","proName":"proName","proPhoto":"/mcfox/userfiles/1/images/photo/2018/02/20180221222610_10.jpg","proAuth":"认证","hitRate":0,"supNum":1},{"crowdfundingId":"ZC100001","title":"众筹标题","amountDown":300,"amountUp":2000,"realAmount":300,"startDate":1519315200000,"status":4,"proId":"ZJ100001","proName":"proName","proPhoto":"/mcfox/userfiles/1/images/photo/2018/02/20180221222610_10.jpg","proAuth":"认证","hitRate":0,"supNum":2}],"needCount":true,"totalPage":1}
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
         * totalRow : 5
         * from : 0
         * recordList : [{"crowdfundingId":"ZC100005","title":"2.28测试二","amountDown":1000,"amountUp":2000,"realAmount":50,"startDate":1519747200000,"status":3,"proId":"ZJ100002","proName":"张三","proPhoto":"/mcfox/userfiles/1/images/photo/2018/02/mmexport1494415441640.jpg","proAuth":"zhangsan","hitRate":0,"supNum":1},{"crowdfundingId":"ZC100004","title":"2.28测试","amountDown":1000,"amountUp":1600,"realAmount":40,"startDate":1519747200000,"status":5,"proId":"ZJ100002","proName":"张三","proPhoto":"/mcfox/userfiles/1/images/photo/2018/02/mmexport1494415441640.jpg","proAuth":"zhangsan","hitRate":0,"supNum":1},{"crowdfundingId":"ZC100003","title":"我的第一次测试","amountDown":13,"amountUp":24,"realAmount":0,"startDate":1519660800000,"status":9,"proId":"ZJ100002","proName":"张三","proPhoto":"/mcfox/userfiles/1/images/photo/2018/02/mmexport1494415441640.jpg","proAuth":"zhangsan","hitRate":0,"supNum":0},{"crowdfundingId":"ZC100002","title":"20180226","amountDown":1000,"amountUp":2000,"realAmount":200,"startDate":1519574400000,"status":5,"proId":"ZJ100001","proName":"proName","proPhoto":"/mcfox/userfiles/1/images/photo/2018/02/20180221222610_10.jpg","proAuth":"认证","hitRate":0,"supNum":1},{"crowdfundingId":"ZC100001","title":"众筹标题","amountDown":300,"amountUp":2000,"realAmount":300,"startDate":1519315200000,"status":4,"proId":"ZJ100001","proName":"proName","proPhoto":"/mcfox/userfiles/1/images/photo/2018/02/20180221222610_10.jpg","proAuth":"认证","hitRate":0,"supNum":2}]
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
             * crowdfundingId : ZC100005
             * title : 2.28测试二
             * amountDown : 1000.0
             * amountUp : 2000.0
             * realAmount : 50.0
             * startDate : 1519747200000
             * status : 3
             * proId : ZJ100002
             * proName : 张三
             * proPhoto : /mcfox/userfiles/1/images/photo/2018/02/mmexport1494415441640.jpg
             * proAuth : zhangsan
             * hitRate : 0.0
             * supNum : 1
             */

            private String crowdfundingId;
            private String title;
            private double amountDown;
            private double amountUp;
            private double realAmount;
            private long startDate;
            private String status;
            private String proId;
            private String proName;
            private String proPhoto;
            private String proAuth;
            private double hitRate;
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

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getProId() {
                return proId;
            }

            public void setProId(String proId) {
                this.proId = proId;
            }

            public String getProName() {
                return proName;
            }

            public void setProName(String proName) {
                this.proName = proName;
            }

            public String getProPhoto() {
                return proPhoto;
            }

            public void setProPhoto(String proPhoto) {
                this.proPhoto = proPhoto;
            }

            public String getProAuth() {
                return proAuth;
            }

            public void setProAuth(String proAuth) {
                this.proAuth = proAuth;
            }

            public double getHitRate() {
                return hitRate;
            }

            public void setHitRate(double hitRate) {
                this.hitRate = hitRate;
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
