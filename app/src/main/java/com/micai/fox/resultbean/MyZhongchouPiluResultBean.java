package com.micai.fox.resultbean;

import java.util.List;

/**
 * Created by louqiang on 2018/2/25.
 */

public class MyZhongchouPiluResultBean {

    /**
     * execResult : true
     * execDatas : {"profitInfo":{"totalBetAmount":"2.00","totalRevenueAmount":"0.00","totalProfitAmount":"0.00","DepotProfitRate":"0.0000"},"betInfo":[{"match":[{"id":"c119873082944dc689d9e93f786087f81519402283823","relId":"1","moduleType":0,"issue":"20180224","seq":"086","gameName":"墨超","homeTeamName":"帕丘卡","guestTeamName":"莱昂","matchTime":"20180225090600","handicap":-1,"sp":"3:1.79;1:3.65;0:3.38","spr":"3:3.25;1:3.70;0:1.82","selections":"0","createDate":1519402284000,"createBy":"ZJ100001","delFlag":"0","result":"1","resultr":"0R"},{"id":"ed48ff7eaed441a6a449c83a798951fd1519402283826","relId":"1","moduleType":0,"issue":"20180224","seq":"087","gameName":"墨超","homeTeamName":"墨美洲","guestTeamName":"蒂华纳","matchTime":"20180225110000","handicap":-1,"sp":"3:1.56;1:3.55;0:4.85","spr":"3:2.74;1:3.35;0:2.15","selections":"0","createDate":1519402284000,"createBy":"ZJ100001","delFlag":"0","result":"1","resultr":"3R"}],"multiple":0,"betnum":0,"seqnum":0,"betAmount":2,"revenueAmount":"","status":1}]}
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
         * profitInfo : {"totalBetAmount":"2.00","totalRevenueAmount":"0.00","totalProfitAmount":"0.00","DepotProfitRate":"0.0000"}
         * betInfo : [{"match":[{"id":"c119873082944dc689d9e93f786087f81519402283823","relId":"1","moduleType":0,"issue":"20180224","seq":"086","gameName":"墨超","homeTeamName":"帕丘卡","guestTeamName":"莱昂","matchTime":"20180225090600","handicap":-1,"sp":"3:1.79;1:3.65;0:3.38","spr":"3:3.25;1:3.70;0:1.82","selections":"0","createDate":1519402284000,"createBy":"ZJ100001","delFlag":"0","result":"1","resultr":"0R"},{"id":"ed48ff7eaed441a6a449c83a798951fd1519402283826","relId":"1","moduleType":0,"issue":"20180224","seq":"087","gameName":"墨超","homeTeamName":"墨美洲","guestTeamName":"蒂华纳","matchTime":"20180225110000","handicap":-1,"sp":"3:1.56;1:3.55;0:4.85","spr":"3:2.74;1:3.35;0:2.15","selections":"0","createDate":1519402284000,"createBy":"ZJ100001","delFlag":"0","result":"1","resultr":"3R"}],"multiple":0,"betnum":0,"seqnum":0,"betAmount":2,"revenueAmount":"","status":1}]
         */

        private ProfitInfoBean profitInfo;
        private List<BetInfoBean> betInfo;

        public ProfitInfoBean getProfitInfo() {
            return profitInfo;
        }

        public void setProfitInfo(ProfitInfoBean profitInfo) {
            this.profitInfo = profitInfo;
        }

        public List<BetInfoBean> getBetInfo() {
            return betInfo;
        }

        public void setBetInfo(List<BetInfoBean> betInfo) {
            this.betInfo = betInfo;
        }

        public static class ProfitInfoBean {
            /**
             * totalBetAmount : 2.00
             * totalRevenueAmount : 0.00
             * totalProfitAmount : 0.00
             * DepotProfitRate : 0.0000
             */

            private String totalBetAmount;
            private String totalRevenueAmount;
            private String totalProfitAmount;
            private String DepotProfitRate;

            public String getTotalBetAmount() {
                return totalBetAmount;
            }

            public void setTotalBetAmount(String totalBetAmount) {
                this.totalBetAmount = totalBetAmount;
            }

            public String getTotalRevenueAmount() {
                return totalRevenueAmount;
            }

            public void setTotalRevenueAmount(String totalRevenueAmount) {
                this.totalRevenueAmount = totalRevenueAmount;
            }

            public String getTotalProfitAmount() {
                return totalProfitAmount;
            }

            public void setTotalProfitAmount(String totalProfitAmount) {
                this.totalProfitAmount = totalProfitAmount;
            }

            public String getDepotProfitRate() {
                return DepotProfitRate;
            }

            public void setDepotProfitRate(String DepotProfitRate) {
                this.DepotProfitRate = DepotProfitRate;
            }
        }

        public static class BetInfoBean {
            /**
             * match : [{"id":"c119873082944dc689d9e93f786087f81519402283823","relId":"1","moduleType":0,"issue":"20180224","seq":"086","gameName":"墨超","homeTeamName":"帕丘卡","guestTeamName":"莱昂","matchTime":"20180225090600","handicap":-1,"sp":"3:1.79;1:3.65;0:3.38","spr":"3:3.25;1:3.70;0:1.82","selections":"0","createDate":1519402284000,"createBy":"ZJ100001","delFlag":"0","result":"1","resultr":"0R"},{"id":"ed48ff7eaed441a6a449c83a798951fd1519402283826","relId":"1","moduleType":0,"issue":"20180224","seq":"087","gameName":"墨超","homeTeamName":"墨美洲","guestTeamName":"蒂华纳","matchTime":"20180225110000","handicap":-1,"sp":"3:1.56;1:3.55;0:4.85","spr":"3:2.74;1:3.35;0:2.15","selections":"0","createDate":1519402284000,"createBy":"ZJ100001","delFlag":"0","result":"1","resultr":"3R"}]
             * multiple : 0
             * betnum : 0
             * seqnum : 0
             * betAmount : 2.0
             * revenueAmount :
             * status : 1
             */

            private int multiple;
            private int betnum;
            private int seqnum;
            private double betAmount;
            private String revenueAmount;
            private int status;
            private List<MatchBean> match;

            public int getMultiple() {
                return multiple;
            }

            public void setMultiple(int multiple) {
                this.multiple = multiple;
            }

            public int getBetnum() {
                return betnum;
            }

            public void setBetnum(int betnum) {
                this.betnum = betnum;
            }

            public int getSeqnum() {
                return seqnum;
            }

            public void setSeqnum(int seqnum) {
                this.seqnum = seqnum;
            }

            public double getBetAmount() {
                return betAmount;
            }

            public void setBetAmount(double betAmount) {
                this.betAmount = betAmount;
            }

            public String getRevenueAmount() {
                return revenueAmount;
            }

            public void setRevenueAmount(String revenueAmount) {
                this.revenueAmount = revenueAmount;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public List<MatchBean> getMatch() {
                return match;
            }

            public void setMatch(List<MatchBean> match) {
                this.match = match;
            }

            public static class MatchBean {
                /**
                 * id : c119873082944dc689d9e93f786087f81519402283823
                 * relId : 1
                 * moduleType : 0
                 * issue : 20180224
                 * seq : 086
                 * gameName : 墨超
                 * homeTeamName : 帕丘卡
                 * guestTeamName : 莱昂
                 * matchTime : 20180225090600
                 * handicap : -1
                 * sp : 3:1.79;1:3.65;0:3.38
                 * spr : 3:3.25;1:3.70;0:1.82
                 * selections : 0
                 * createDate : 1519402284000
                 * createBy : ZJ100001
                 * delFlag : 0
                 * result : 1
                 * resultr : 0R
                 */

                private String id;
                private String relId;
                private int moduleType;
                private String issue;
                private String seq;
                private String gameName;
                private String homeTeamName;
                private String guestTeamName;
                private String matchTime;
                private int handicap;
                private String sp;
                private String spr;
                private String selections;
                private long createDate;
                private String createBy;
                private String delFlag;
                private String result;
                private String resultr;

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

                public int getModuleType() {
                    return moduleType;
                }

                public void setModuleType(int moduleType) {
                    this.moduleType = moduleType;
                }

                public String getIssue() {
                    return issue;
                }

                public void setIssue(String issue) {
                    this.issue = issue;
                }

                public String getSeq() {
                    return seq;
                }

                public void setSeq(String seq) {
                    this.seq = seq;
                }

                public String getGameName() {
                    return gameName;
                }

                public void setGameName(String gameName) {
                    this.gameName = gameName;
                }

                public String getHomeTeamName() {
                    return homeTeamName;
                }

                public void setHomeTeamName(String homeTeamName) {
                    this.homeTeamName = homeTeamName;
                }

                public String getGuestTeamName() {
                    return guestTeamName;
                }

                public void setGuestTeamName(String guestTeamName) {
                    this.guestTeamName = guestTeamName;
                }

                public String getMatchTime() {
                    return matchTime;
                }

                public void setMatchTime(String matchTime) {
                    this.matchTime = matchTime;
                }

                public int getHandicap() {
                    return handicap;
                }

                public void setHandicap(int handicap) {
                    this.handicap = handicap;
                }

                public String getSp() {
                    return sp;
                }

                public void setSp(String sp) {
                    this.sp = sp;
                }

                public String getSpr() {
                    return spr;
                }

                public void setSpr(String spr) {
                    this.spr = spr;
                }

                public String getSelections() {
                    return selections;
                }

                public void setSelections(String selections) {
                    this.selections = selections;
                }

                public long getCreateDate() {
                    return createDate;
                }

                public void setCreateDate(long createDate) {
                    this.createDate = createDate;
                }

                public String getCreateBy() {
                    return createBy;
                }

                public void setCreateBy(String createBy) {
                    this.createBy = createBy;
                }

                public String getDelFlag() {
                    return delFlag;
                }

                public void setDelFlag(String delFlag) {
                    this.delFlag = delFlag;
                }

                public String getResult() {
                    return result;
                }

                public void setResult(String result) {
                    this.result = result;
                }

                public String getResultr() {
                    return resultr;
                }

                public void setResultr(String resultr) {
                    this.resultr = resultr;
                }
            }
        }
    }
}
