package com.micai.fox.resultbean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by louqiang on 2018/1/27.
 */

public class HomeResultBean implements Serializable {

    /**
     * execResult : true
     * execDatas : {"banner":[{"imgPath":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1516032080989&di=08a4ad368e6af2586cbdd9aed58e5f0e&imgtype=0&src=http%3A%2F%2Fimg1.szhk.com%2FImage%2F2017%2F07%2F21%2F1500623627742.jpg","forwardPath":"2","forwardModule":"0","position":"0"},{"imgPath":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1516033615564&di=d70b9dcafd63ff93c9770d7fcc4fadb2&imgtype=0&src=http%3A%2F%2Fi2.hdslb.com%2Fbfs%2Farchive%2Fc432da6b2059c2b5e5bc734af426e46a156f0e1a.jpg","forwardPath":"1","forwardModule":"1","position":"0"},{"imgPath":"/admin-mcfox/userfiles/1/images/photo/2018/01/mmexport1494415444227.jpg","forwardPath":"4","forwardModule":"0","position":"0"},{"imgPath":"/admin-mcfox/userfiles/1/images/photo/2018/01/mmexport1494415379918.jpg","forwardPath":"3","forwardModule":"2","position":"0"}],"professor":[{"proId":"5edb348b25a248e185a6f12c9204fa561516523589992","proName":"zhang","proPhoto":"/admin-mcfox/userfiles/1/images/photo/2018/01/75e15cac9e69538a72bc53832cdb00d1(1).jpg","proAuth":"zhang","hitRate":0,"profitRateWeek":0,"profitRate":0},{"proId":"100001","proName":"aaa","proPhoto":"/mcfox/userfiles/5edb348b25a248e185a6f12c9204fa561516523589992/images/photo/2018/02/75e15cac9e69538a72bc53832cdb00d1.jpg","proAuth":"我是认证","hitRate":0,"profitRateWeek":0,"profitRate":0}],"crowdfunding":{"length":20,"pageNo":1,"totalRow":5,"from":0,"recordList":[{"crowdfundingId":"1","title":"世界杯","amountDown":3,"amountUp":2,"realAmount":1000,"startDate":1516329373000,"status":7,"proId":"5edb348b25a248e185a6f12c9204fa561516523589992","proName":"zhang","proPhoto":"/admin-mcfox/userfiles/1/images/photo/2018/01/75e15cac9e69538a72bc53832cdb00d1(1).jpg","proAuth":"zhang","hitRate":0,"supNum":1},{"crowdfundingId":"2","title":"欧洲杯","realAmount":1500,"startDate":1515551892000,"status":6,"proId":"5edb348b25a248e185a6f12c9204fa561516523589992","proName":"zhang","proPhoto":"/admin-mcfox/userfiles/1/images/photo/2018/01/75e15cac9e69538a72bc53832cdb00d1(1).jpg","proAuth":"zhang","hitRate":0,"supNum":0},{"crowdfundingId":"ZC100001","title":"亚洲足彩","amountDown":10000,"amountUp":1000,"realAmount":0,"startDate":1517673600000,"status":0,"proId":"5edb348b25a248e185a6f12c9204fa561516523589992","proName":"zhang","proPhoto":"/admin-mcfox/userfiles/1/images/photo/2018/01/75e15cac9e69538a72bc53832cdb00d1(1).jpg","proAuth":"zhang","hitRate":0,"supNum":0},{"crowdfundingId":"4","title":"众筹测试1","amountDown":3000,"amountUp":2000,"realAmount":0,"startDate":1517155200000,"status":0,"proId":"5edb348b25a248e185a6f12c9204fa561516523589992","proName":"zhang","proPhoto":"/admin-mcfox/userfiles/1/images/photo/2018/01/75e15cac9e69538a72bc53832cdb00d1(1).jpg","proAuth":"zhang","hitRate":0,"supNum":0},{"crowdfundingId":"3","title":"亚洲杯","realAmount":666,"status":4,"hitRate":0,"supNum":0}],"needCount":true,"totalPage":1}}
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
         * banner : [{"imgPath":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1516032080989&di=08a4ad368e6af2586cbdd9aed58e5f0e&imgtype=0&src=http%3A%2F%2Fimg1.szhk.com%2FImage%2F2017%2F07%2F21%2F1500623627742.jpg","forwardPath":"2","forwardModule":"0","position":"0"},{"imgPath":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1516033615564&di=d70b9dcafd63ff93c9770d7fcc4fadb2&imgtype=0&src=http%3A%2F%2Fi2.hdslb.com%2Fbfs%2Farchive%2Fc432da6b2059c2b5e5bc734af426e46a156f0e1a.jpg","forwardPath":"1","forwardModule":"1","position":"0"},{"imgPath":"/admin-mcfox/userfiles/1/images/photo/2018/01/mmexport1494415444227.jpg","forwardPath":"4","forwardModule":"0","position":"0"},{"imgPath":"/admin-mcfox/userfiles/1/images/photo/2018/01/mmexport1494415379918.jpg","forwardPath":"3","forwardModule":"2","position":"0"}]
         * professor : [{"proId":"5edb348b25a248e185a6f12c9204fa561516523589992","proName":"zhang","proPhoto":"/admin-mcfox/userfiles/1/images/photo/2018/01/75e15cac9e69538a72bc53832cdb00d1(1).jpg","proAuth":"zhang","hitRate":0,"profitRateWeek":0,"profitRate":0},{"proId":"100001","proName":"aaa","proPhoto":"/mcfox/userfiles/5edb348b25a248e185a6f12c9204fa561516523589992/images/photo/2018/02/75e15cac9e69538a72bc53832cdb00d1.jpg","proAuth":"我是认证","hitRate":0,"profitRateWeek":0,"profitRate":0}]
         * crowdfunding : {"length":20,"pageNo":1,"totalRow":5,"from":0,"recordList":[{"crowdfundingId":"1","title":"世界杯","amountDown":3,"amountUp":2,"realAmount":1000,"startDate":1516329373000,"status":7,"proId":"5edb348b25a248e185a6f12c9204fa561516523589992","proName":"zhang","proPhoto":"/admin-mcfox/userfiles/1/images/photo/2018/01/75e15cac9e69538a72bc53832cdb00d1(1).jpg","proAuth":"zhang","hitRate":0,"supNum":1},{"crowdfundingId":"2","title":"欧洲杯","realAmount":1500,"startDate":1515551892000,"status":6,"proId":"5edb348b25a248e185a6f12c9204fa561516523589992","proName":"zhang","proPhoto":"/admin-mcfox/userfiles/1/images/photo/2018/01/75e15cac9e69538a72bc53832cdb00d1(1).jpg","proAuth":"zhang","hitRate":0,"supNum":0},{"crowdfundingId":"ZC100001","title":"亚洲足彩","amountDown":10000,"amountUp":1000,"realAmount":0,"startDate":1517673600000,"status":0,"proId":"5edb348b25a248e185a6f12c9204fa561516523589992","proName":"zhang","proPhoto":"/admin-mcfox/userfiles/1/images/photo/2018/01/75e15cac9e69538a72bc53832cdb00d1(1).jpg","proAuth":"zhang","hitRate":0,"supNum":0},{"crowdfundingId":"4","title":"众筹测试1","amountDown":3000,"amountUp":2000,"realAmount":0,"startDate":1517155200000,"status":0,"proId":"5edb348b25a248e185a6f12c9204fa561516523589992","proName":"zhang","proPhoto":"/admin-mcfox/userfiles/1/images/photo/2018/01/75e15cac9e69538a72bc53832cdb00d1(1).jpg","proAuth":"zhang","hitRate":0,"supNum":0},{"crowdfundingId":"3","title":"亚洲杯","realAmount":666,"status":4,"hitRate":0,"supNum":0}],"needCount":true,"totalPage":1}
         */

        private CrowdfundingBean crowdfunding;
        private List<BannerBean> banner;
        private List<ProfessorBean> professor;

        public CrowdfundingBean getCrowdfunding() {
            return crowdfunding;
        }

        public void setCrowdfunding(CrowdfundingBean crowdfunding) {
            this.crowdfunding = crowdfunding;
        }

        public List<BannerBean> getBanner() {
            return banner;
        }

        public void setBanner(List<BannerBean> banner) {
            this.banner = banner;
        }

        public List<ProfessorBean> getProfessor() {
            return professor;
        }

        public void setProfessor(List<ProfessorBean> professor) {
            this.professor = professor;
        }

        public static class CrowdfundingBean {
            /**
             * length : 20
             * pageNo : 1
             * totalRow : 5
             * from : 0
             * recordList : [{"crowdfundingId":"1","title":"世界杯","amountDown":3,"amountUp":2,"realAmount":1000,"startDate":1516329373000,"status":7,"proId":"5edb348b25a248e185a6f12c9204fa561516523589992","proName":"zhang","proPhoto":"/admin-mcfox/userfiles/1/images/photo/2018/01/75e15cac9e69538a72bc53832cdb00d1(1).jpg","proAuth":"zhang","hitRate":0,"supNum":1},{"crowdfundingId":"2","title":"欧洲杯","realAmount":1500,"startDate":1515551892000,"status":6,"proId":"5edb348b25a248e185a6f12c9204fa561516523589992","proName":"zhang","proPhoto":"/admin-mcfox/userfiles/1/images/photo/2018/01/75e15cac9e69538a72bc53832cdb00d1(1).jpg","proAuth":"zhang","hitRate":0,"supNum":0},{"crowdfundingId":"ZC100001","title":"亚洲足彩","amountDown":10000,"amountUp":1000,"realAmount":0,"startDate":1517673600000,"status":0,"proId":"5edb348b25a248e185a6f12c9204fa561516523589992","proName":"zhang","proPhoto":"/admin-mcfox/userfiles/1/images/photo/2018/01/75e15cac9e69538a72bc53832cdb00d1(1).jpg","proAuth":"zhang","hitRate":0,"supNum":0},{"crowdfundingId":"4","title":"众筹测试1","amountDown":3000,"amountUp":2000,"realAmount":0,"startDate":1517155200000,"status":0,"proId":"5edb348b25a248e185a6f12c9204fa561516523589992","proName":"zhang","proPhoto":"/admin-mcfox/userfiles/1/images/photo/2018/01/75e15cac9e69538a72bc53832cdb00d1(1).jpg","proAuth":"zhang","hitRate":0,"supNum":0},{"crowdfundingId":"3","title":"亚洲杯","realAmount":666,"status":4,"hitRate":0,"supNum":0}]
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
                 * crowdfundingId : 1
                 * title : 世界杯
                 * amountDown : 3.0
                 * amountUp : 2.0
                 * realAmount : 1000.0
                 * startDate : 1516329373000
                 * status : 7
                 * proId : 5edb348b25a248e185a6f12c9204fa561516523589992
                 * proName : zhang
                 * proPhoto : /admin-mcfox/userfiles/1/images/photo/2018/01/75e15cac9e69538a72bc53832cdb00d1(1).jpg
                 * proAuth : zhang
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

        public static class BannerBean {
            /**
             * imgPath : https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1516032080989&di=08a4ad368e6af2586cbdd9aed58e5f0e&imgtype=0&src=http%3A%2F%2Fimg1.szhk.com%2FImage%2F2017%2F07%2F21%2F1500623627742.jpg
             * forwardPath : 2
             * forwardModule : 0
             * position : 0
             */

            private String imgPath;
            private String forwardPath;
            private String forwardModule;
            private String position;

            public String getImgPath() {
                return imgPath;
            }

            public void setImgPath(String imgPath) {
                this.imgPath = imgPath;
            }

            public String getForwardPath() {
                return forwardPath;
            }

            public void setForwardPath(String forwardPath) {
                this.forwardPath = forwardPath;
            }

            public String getForwardModule() {
                return forwardModule;
            }

            public void setForwardModule(String forwardModule) {
                this.forwardModule = forwardModule;
            }

            public String getPosition() {
                return position;
            }

            public void setPosition(String position) {
                this.position = position;
            }
        }

        public static class ProfessorBean {
            /**
             * proId : 5edb348b25a248e185a6f12c9204fa561516523589992
             * proName : zhang
             * proPhoto : /admin-mcfox/userfiles/1/images/photo/2018/01/75e15cac9e69538a72bc53832cdb00d1(1).jpg
             * proAuth : zhang
             * hitRate : 0.0
             * profitRateWeek : 0.0
             * profitRate : 0.0
             */

            private String proId;
            private String proName;
            private String proPhoto;
            private String proAuth;
            private double hitRate;
            private double profitRateWeek;
            private double profitRate;

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

            public double getProfitRateWeek() {
                return profitRateWeek;
            }

            public void setProfitRateWeek(double profitRateWeek) {
                this.profitRateWeek = profitRateWeek;
            }

            public double getProfitRate() {
                return profitRate;
            }

            public void setProfitRate(double profitRate) {
                this.profitRate = profitRate;
            }
        }
    }
}
