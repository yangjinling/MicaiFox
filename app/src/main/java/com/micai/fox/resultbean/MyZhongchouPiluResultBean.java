package com.micai.fox.resultbean;

import java.util.List;

/**
 * Created by louqiang on 2018/2/25.
 */

public class MyZhongchouPiluResultBean {

    /**
     * execResult : true
     * execDatas : {"profitInfo":{"totalBetAmount":"8.00","totalRevenueAmount":"0.00","totalProfitAmount":"0.00","DepotProfitRate":"0.0000"},"betInfo":[{"match":[{"id":"c119873082944dc689d9e93f786087f81519402283823","relId":"1","moduleType":0,"issue":"20180224","seq":"086","gameName":"墨超","homeTeamName":"帕丘卡","guestTeamName":"莱昂","matchTime":"20180225090600","homeScore":2,"guestScore":1,"handicap":-1,"sp":"3:1.79;1:3.65;0:3.38","spr":"3:3.25;1:3.70;0:1.82","selections":"0","createDate":1519402284000,"createBy":"ZJ100001","updateDate":1519568101000,"updateBy":"task","delFlag":"0","result":"3","resultr":"1R"},{"id":"ed48ff7eaed441a6a449c83a798951fd1519402283826","relId":"1","moduleType":0,"issue":"20180224","seq":"087","gameName":"墨超","homeTeamName":"墨美洲","guestTeamName":"蒂华纳","matchTime":"20180225110000","homeScore":0,"guestScore":0,"handicap":-1,"sp":"3:1.56;1:3.55;0:4.85","spr":"3:2.74;1:3.35;0:2.15","selections":"0","createDate":1519402284000,"createBy":"ZJ100001","updateDate":1519568102000,"updateBy":"task","delFlag":"0","result":"1","resultr":"0R"}],"multiple":0,"betnum":0,"seqnum":0,"betAmount":2,"revenueAmount":"","status":1},{"match":[],"multiple":1,"betnum":1,"seqnum":2,"betAmount":2,"revenueAmount":"","status":2},{"match":[{"id":"5466109496eb48fab3bcfd421ebbeeac1519569640464","relId":"3","moduleType":0,"issue":"20180225","seq":"038","gameName":"荷甲","homeTeamName":"乌德勒支","guestTeamName":"特温特","matchTime":"20180225234500","handicap":-1,"sp":"3:1.47;1:4.00;0:5.05","spr":"3:2.47;1:3.45;0:2.30","selections":"0","createDate":1519569640000,"createBy":"ZJ100001","delFlag":"0"},{"id":"b7e026e7c97447b58b7cb53c55a43f621519569640465","relId":"3","moduleType":0,"issue":"20180225","seq":"039","gameName":"法甲","homeTeamName":"里昂","guestTeamName":"圣埃蒂安","matchTime":"20180226000000","handicap":-1,"sp":"3:1.37;1:4.20;0:6.20","spr":"3:2.24;1:3.45;0:2.55","selections":"0R","createDate":1519569640000,"createBy":"ZJ100001","delFlag":"0"}],"multiple":1,"betnum":1,"seqnum":2,"betAmount":2,"revenueAmount":"","status":2},{"match":[{"id":"d705fabbc4f046f5bc3acfa5dde4b4f91519614938916","relId":"980fcb4fdd064d1cad59b3313ea11eb01519614915765","moduleType":0,"issue":"20180226","seq":"001","gameName":"荷乙","homeTeamName":"阿尔青年","guestTeamName":"埃因青年","matchTime":"20180227030000","handicap":1,"sp":"3:3.00;1:3.65;0:1.92","spr":"3:1.70;1:3.90;0:3.51","selections":"3","createDate":1519614939000,"createBy":"1","delFlag":"0"},{"id":"319ff94799b745498b66c420b5ed69e31519614938919","relId":"980fcb4fdd064d1cad59b3313ea11eb01519614915765","moduleType":0,"issue":"20180226","seq":"002","gameName":"荷乙","homeTeamName":"乌德青年","guestTeamName":"阿贾青年","matchTime":"20180227030000","handicap":1,"sp":"3:6.00;1:4.75;0:1.33","spr":"3:2.79;1:4.00;0:1.92","selections":"1","createDate":1519614939000,"createBy":"1","delFlag":"0"}],"multiple":1,"betnum":1,"seqnum":2,"betAmount":2,"revenueAmount":"","status":1}]}
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
         * profitInfo : {"totalBetAmount":"8.00","totalRevenueAmount":"0.00","totalProfitAmount":"0.00","DepotProfitRate":"0.0000"}
         * betInfo : [{"match":[{"id":"c119873082944dc689d9e93f786087f81519402283823","relId":"1","moduleType":0,"issue":"20180224","seq":"086","gameName":"墨超","homeTeamName":"帕丘卡","guestTeamName":"莱昂","matchTime":"20180225090600","homeScore":2,"guestScore":1,"handicap":-1,"sp":"3:1.79;1:3.65;0:3.38","spr":"3:3.25;1:3.70;0:1.82","selections":"0","createDate":1519402284000,"createBy":"ZJ100001","updateDate":1519568101000,"updateBy":"task","delFlag":"0","result":"3","resultr":"1R"},{"id":"ed48ff7eaed441a6a449c83a798951fd1519402283826","relId":"1","moduleType":0,"issue":"20180224","seq":"087","gameName":"墨超","homeTeamName":"墨美洲","guestTeamName":"蒂华纳","matchTime":"20180225110000","homeScore":0,"guestScore":0,"handicap":-1,"sp":"3:1.56;1:3.55;0:4.85","spr":"3:2.74;1:3.35;0:2.15","selections":"0","createDate":1519402284000,"createBy":"ZJ100001","updateDate":1519568102000,"updateBy":"task","delFlag":"0","result":"1","resultr":"0R"}],"multiple":0,"betnum":0,"seqnum":0,"betAmount":2,"revenueAmount":"","status":1},{"match":[],"multiple":1,"betnum":1,"seqnum":2,"betAmount":2,"revenueAmount":"","status":2},{"match":[{"id":"5466109496eb48fab3bcfd421ebbeeac1519569640464","relId":"3","moduleType":0,"issue":"20180225","seq":"038","gameName":"荷甲","homeTeamName":"乌德勒支","guestTeamName":"特温特","matchTime":"20180225234500","handicap":-1,"sp":"3:1.47;1:4.00;0:5.05","spr":"3:2.47;1:3.45;0:2.30","selections":"0","createDate":1519569640000,"createBy":"ZJ100001","delFlag":"0"},{"id":"b7e026e7c97447b58b7cb53c55a43f621519569640465","relId":"3","moduleType":0,"issue":"20180225","seq":"039","gameName":"法甲","homeTeamName":"里昂","guestTeamName":"圣埃蒂安","matchTime":"20180226000000","handicap":-1,"sp":"3:1.37;1:4.20;0:6.20","spr":"3:2.24;1:3.45;0:2.55","selections":"0R","createDate":1519569640000,"createBy":"ZJ100001","delFlag":"0"}],"multiple":1,"betnum":1,"seqnum":2,"betAmount":2,"revenueAmount":"","status":2},{"match":[{"id":"d705fabbc4f046f5bc3acfa5dde4b4f91519614938916","relId":"980fcb4fdd064d1cad59b3313ea11eb01519614915765","moduleType":0,"issue":"20180226","seq":"001","gameName":"荷乙","homeTeamName":"阿尔青年","guestTeamName":"埃因青年","matchTime":"20180227030000","handicap":1,"sp":"3:3.00;1:3.65;0:1.92","spr":"3:1.70;1:3.90;0:3.51","selections":"3","createDate":1519614939000,"createBy":"1","delFlag":"0"},{"id":"319ff94799b745498b66c420b5ed69e31519614938919","relId":"980fcb4fdd064d1cad59b3313ea11eb01519614915765","moduleType":0,"issue":"20180226","seq":"002","gameName":"荷乙","homeTeamName":"乌德青年","guestTeamName":"阿贾青年","matchTime":"20180227030000","handicap":1,"sp":"3:6.00;1:4.75;0:1.33","spr":"3:2.79;1:4.00;0:1.92","selections":"1","createDate":1519614939000,"createBy":"1","delFlag":"0"}],"multiple":1,"betnum":1,"seqnum":2,"betAmount":2,"revenueAmount":"","status":1}]
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
             * totalBetAmount : 8.00
             * totalRevenueAmount : 0.00
             * totalProfitAmount : 0.00
             * DepotProfitRate : 0.0000
             */

            private String totalBetAmount;
            private String totalRevenueAmount;
            private String totalProfitAmount;
            private String DepotProfitRate;
            private int winStatus;
            private String cashPercent;
            private String cashProfitAmount;
            private String cashProfitRate;

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

            public int getWinStatus() {
                return winStatus;
            }

            public void setWinStatus(int winStatus) {
                this.winStatus = winStatus;
            }

            public String getCashPercent() {
                return cashPercent;
            }

            public void setCashPercent(String cashPercent) {
                this.cashPercent = cashPercent;
            }

            public String getCashProfitAmount() {
                return cashProfitAmount;
            }

            public void setCashProfitAmount(String cashProfitAmount) {
                this.cashProfitAmount = cashProfitAmount;
            }

            public String getCashProfitRate() {
                return cashProfitRate;
            }

            public void setCashProfitRate(String cashProfitRate) {
                this.cashProfitRate = cashProfitRate;
            }
        }

        public static class BetInfoBean {
            /**
             * match : [{"id":"c119873082944dc689d9e93f786087f81519402283823","relId":"1","moduleType":0,"issue":"20180224","seq":"086","gameName":"墨超","homeTeamName":"帕丘卡","guestTeamName":"莱昂","matchTime":"20180225090600","homeScore":2,"guestScore":1,"handicap":-1,"sp":"3:1.79;1:3.65;0:3.38","spr":"3:3.25;1:3.70;0:1.82","selections":"0","createDate":1519402284000,"createBy":"ZJ100001","updateDate":1519568101000,"updateBy":"task","delFlag":"0","result":"3","resultr":"1R"},{"id":"ed48ff7eaed441a6a449c83a798951fd1519402283826","relId":"1","moduleType":0,"issue":"20180224","seq":"087","gameName":"墨超","homeTeamName":"墨美洲","guestTeamName":"蒂华纳","matchTime":"20180225110000","homeScore":0,"guestScore":0,"handicap":-1,"sp":"3:1.56;1:3.55;0:4.85","spr":"3:2.74;1:3.35;0:2.15","selections":"0","createDate":1519402284000,"createBy":"ZJ100001","updateDate":1519568102000,"updateBy":"task","delFlag":"0","result":"1","resultr":"0R"}]
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
                 * homeScore : 2
                 * guestScore : 1
                 * handicap : -1
                 * sp : 3:1.79;1:3.65;0:3.38
                 * spr : 3:3.25;1:3.70;0:1.82
                 * selections : 0
                 * createDate : 1519402284000
                 * createBy : ZJ100001
                 * updateDate : 1519568101000
                 * updateBy : task
                 * delFlag : 0
                 * result : 3
                 * resultr : 1R
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
                private int homeScore;
                private int guestScore;
                private int handicap;
                private String sp;
                private String spr;
                private String selections;
                private long createDate;
                private String createBy;
                private long updateDate;
                private String updateBy;
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

                public int getHomeScore() {
                    return homeScore;
                }

                public void setHomeScore(int homeScore) {
                    this.homeScore = homeScore;
                }

                public int getGuestScore() {
                    return guestScore;
                }

                public void setGuestScore(int guestScore) {
                    this.guestScore = guestScore;
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

                public long getUpdateDate() {
                    return updateDate;
                }

                public void setUpdateDate(long updateDate) {
                    this.updateDate = updateDate;
                }

                public String getUpdateBy() {
                    return updateBy;
                }

                public void setUpdateBy(String updateBy) {
                    this.updateBy = updateBy;
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
