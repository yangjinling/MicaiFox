package com.micai.fox.resultbean;

import java.util.List;

/**
 * Created by louqiang on 2018/2/25.
 */

public class MyZhongchouPiluResultBean {

    /**
     * execResult : true
     * execDatas : {"profitInfo":{"winStatus":0,"totalBetAmount":"84.00","totalRevenueAmount":"0.00","totalProfitAmount":"0.00","depotProfitRate":"0.0000","cashProfitRate":"0.0000","cashPercent":0.9,"cashProfitAmount":0},"betInfo":[{"match":[{"id":"6d1cd7d82fbb4bb0a32f3e39ecf95c7c1521382844702","relId":"13","moduleType":0,"issue":"20180318","seq":"037","gameName":"荷甲","homeTeamName":"兹沃勒","guestTeamName":"费耶诺德","matchTime":1521387900000,"homeScore":3,"guestScore":4,"handicap":1,"sp":"3:4.35;1:3.70;0:1.59","spr":"3:2.04;1:3.50;0:2.83","selections":"0","createDate":1521382845000,"createBy":"ZJ100006","updateDate":1521424800000,"updateBy":"task","delFlag":"0","result":"0","resultr":"1R"},{"id":"5f03b37f2ffd41d3b28456ac128d35ef1521382844702","relId":"13","moduleType":0,"issue":"20180318","seq":"038","gameName":"法甲","homeTeamName":"圣埃蒂安","guestTeamName":"甘冈","matchTime":1521388800000,"homeScore":2,"guestScore":0,"handicap":-1,"sp":"3:1.52;1:3.55;0:5.26","spr":"3:2.80;1:3.30;0:2.13","selections":"3","createDate":1521382845000,"createBy":"ZJ100006","updateDate":1521417600000,"updateBy":"task","delFlag":"0","result":"3","resultr":"3R"}],"multiple":5,"betnum":1,"seqnum":2,"betAmount":10,"revenueAmount":"","status":4},{"match":[{"id":"dd1ccac46c2c494bb02946e39ebcb49a1521382430279","relId":"14","moduleType":0,"issue":"20180318","seq":"035","gameName":"智利甲","homeTeamName":"康塞大学","guestTeamName":"科洛科洛","matchTime":1521385200000,"homeScore":2,"guestScore":1,"handicap":1,"sp":"3:3.06;1:3.30;0:2.00","spr":"3:1.63;1:3.70;0:4.08","selections":"3R","createDate":1521382430000,"createBy":"1","updateDate":1521417601000,"updateBy":"task","delFlag":"0","result":"3","resultr":"3R"},{"id":"4376c45e200e4511911f0586d99b14111521382430295","relId":"14","moduleType":0,"issue":"20180318","seq":"036","gameName":"西甲","homeTeamName":"巴萨","guestTeamName":"毕尔巴鄂","matchTime":1521386100000,"homeScore":2,"guestScore":0,"handicap":-2,"spr":"3:2.06;1:3.95;0:2.56","selections":"3R","createDate":1521382430000,"createBy":"1","updateDate":1521417600000,"updateBy":"task","delFlag":"0","result":"3","resultr":"1R"}],"multiple":5,"betnum":1,"seqnum":2,"betAmount":10,"revenueAmount":"","status":4},{"match":[{"id":"ef60d990860748189115988112c213ee1521381187159","relId":"2fe3302dfa14490693054d6dfeb202f81521381057170","moduleType":0,"issue":"20180318","seq":"034","gameName":"德甲","homeTeamName":"科隆","guestTeamName":"勒沃库森","matchTime":1521383400000,"homeScore":2,"guestScore":0,"handicap":1,"sp":"3:4.50;1:3.95;0:1.53","spr":"3:2.16;1:3.45;0:2.66","selections":"0,3R","createDate":1521381187000,"createBy":"1","updateDate":1521396000000,"updateBy":"task","delFlag":"0","result":"3","resultr":"3R"},{"id":"ca9af4ed30b448a5978dfc76b3f0b3fc1521381187164","relId":"2fe3302dfa14490693054d6dfeb202f81521381057170","moduleType":0,"issue":"20180318","seq":"035","gameName":"智利甲","homeTeamName":"康塞大学","guestTeamName":"科洛科洛","matchTime":1521385200000,"homeScore":2,"guestScore":1,"handicap":1,"sp":"3:3.06;1:3.30;0:2.00","spr":"3:1.63;1:3.70;0:4.08","selections":"0,3R","createDate":1521381187000,"createBy":"1","updateDate":1521417601000,"updateBy":"task","delFlag":"0","result":"3","resultr":"3R"}],"multiple":8,"betnum":4,"seqnum":2,"betAmount":64,"revenueAmount":"","status":4}]}
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
         * profitInfo : {"winStatus":0,"totalBetAmount":"84.00","totalRevenueAmount":"0.00","totalProfitAmount":"0.00","depotProfitRate":"0.0000","cashProfitRate":"0.0000","cashPercent":0.9,"cashProfitAmount":0}
         * betInfo : [{"match":[{"id":"6d1cd7d82fbb4bb0a32f3e39ecf95c7c1521382844702","relId":"13","moduleType":0,"issue":"20180318","seq":"037","gameName":"荷甲","homeTeamName":"兹沃勒","guestTeamName":"费耶诺德","matchTime":1521387900000,"homeScore":3,"guestScore":4,"handicap":1,"sp":"3:4.35;1:3.70;0:1.59","spr":"3:2.04;1:3.50;0:2.83","selections":"0","createDate":1521382845000,"createBy":"ZJ100006","updateDate":1521424800000,"updateBy":"task","delFlag":"0","result":"0","resultr":"1R"},{"id":"5f03b37f2ffd41d3b28456ac128d35ef1521382844702","relId":"13","moduleType":0,"issue":"20180318","seq":"038","gameName":"法甲","homeTeamName":"圣埃蒂安","guestTeamName":"甘冈","matchTime":1521388800000,"homeScore":2,"guestScore":0,"handicap":-1,"sp":"3:1.52;1:3.55;0:5.26","spr":"3:2.80;1:3.30;0:2.13","selections":"3","createDate":1521382845000,"createBy":"ZJ100006","updateDate":1521417600000,"updateBy":"task","delFlag":"0","result":"3","resultr":"3R"}],"multiple":5,"betnum":1,"seqnum":2,"betAmount":10,"revenueAmount":"","status":4},{"match":[{"id":"dd1ccac46c2c494bb02946e39ebcb49a1521382430279","relId":"14","moduleType":0,"issue":"20180318","seq":"035","gameName":"智利甲","homeTeamName":"康塞大学","guestTeamName":"科洛科洛","matchTime":1521385200000,"homeScore":2,"guestScore":1,"handicap":1,"sp":"3:3.06;1:3.30;0:2.00","spr":"3:1.63;1:3.70;0:4.08","selections":"3R","createDate":1521382430000,"createBy":"1","updateDate":1521417601000,"updateBy":"task","delFlag":"0","result":"3","resultr":"3R"},{"id":"4376c45e200e4511911f0586d99b14111521382430295","relId":"14","moduleType":0,"issue":"20180318","seq":"036","gameName":"西甲","homeTeamName":"巴萨","guestTeamName":"毕尔巴鄂","matchTime":1521386100000,"homeScore":2,"guestScore":0,"handicap":-2,"spr":"3:2.06;1:3.95;0:2.56","selections":"3R","createDate":1521382430000,"createBy":"1","updateDate":1521417600000,"updateBy":"task","delFlag":"0","result":"3","resultr":"1R"}],"multiple":5,"betnum":1,"seqnum":2,"betAmount":10,"revenueAmount":"","status":4},{"match":[{"id":"ef60d990860748189115988112c213ee1521381187159","relId":"2fe3302dfa14490693054d6dfeb202f81521381057170","moduleType":0,"issue":"20180318","seq":"034","gameName":"德甲","homeTeamName":"科隆","guestTeamName":"勒沃库森","matchTime":1521383400000,"homeScore":2,"guestScore":0,"handicap":1,"sp":"3:4.50;1:3.95;0:1.53","spr":"3:2.16;1:3.45;0:2.66","selections":"0,3R","createDate":1521381187000,"createBy":"1","updateDate":1521396000000,"updateBy":"task","delFlag":"0","result":"3","resultr":"3R"},{"id":"ca9af4ed30b448a5978dfc76b3f0b3fc1521381187164","relId":"2fe3302dfa14490693054d6dfeb202f81521381057170","moduleType":0,"issue":"20180318","seq":"035","gameName":"智利甲","homeTeamName":"康塞大学","guestTeamName":"科洛科洛","matchTime":1521385200000,"homeScore":2,"guestScore":1,"handicap":1,"sp":"3:3.06;1:3.30;0:2.00","spr":"3:1.63;1:3.70;0:4.08","selections":"0,3R","createDate":1521381187000,"createBy":"1","updateDate":1521417601000,"updateBy":"task","delFlag":"0","result":"3","resultr":"3R"}],"multiple":8,"betnum":4,"seqnum":2,"betAmount":64,"revenueAmount":"","status":4}]
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
             * winStatus : 0
             * totalBetAmount : 84.00
             * totalRevenueAmount : 0.00
             * totalProfitAmount : 0.00
             * depotProfitRate : 0.0000
             * cashProfitRate : 0.0000
             * cashPercent : 0.9
             * cashProfitAmount : 0
             */

            private int winStatus;
            private String totalBetAmount;
            private String totalRevenueAmount;
            private String totalProfitAmount;
            private double depotProfitRate;
            private double cashProfitRate;
            private String cashPercent;
            private String cashProfitAmount;

            public int getWinStatus() {
                return winStatus;
            }

            public void setWinStatus(int winStatus) {
                this.winStatus = winStatus;
            }

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

            public double getDepotProfitRate() {
                return depotProfitRate;
            }

            public void setDepotProfitRate(double depotProfitRate) {
                this.depotProfitRate = depotProfitRate;
            }

            public double getCashProfitRate() {
                return cashProfitRate;
            }

            public void setCashProfitRate(double cashProfitRate) {
                this.cashProfitRate = cashProfitRate;
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
        }

        public static class BetInfoBean {
            /**
             * match : [{"id":"6d1cd7d82fbb4bb0a32f3e39ecf95c7c1521382844702","relId":"13","moduleType":0,"issue":"20180318","seq":"037","gameName":"荷甲","homeTeamName":"兹沃勒","guestTeamName":"费耶诺德","matchTime":1521387900000,"homeScore":3,"guestScore":4,"handicap":1,"sp":"3:4.35;1:3.70;0:1.59","spr":"3:2.04;1:3.50;0:2.83","selections":"0","createDate":1521382845000,"createBy":"ZJ100006","updateDate":1521424800000,"updateBy":"task","delFlag":"0","result":"0","resultr":"1R"},{"id":"5f03b37f2ffd41d3b28456ac128d35ef1521382844702","relId":"13","moduleType":0,"issue":"20180318","seq":"038","gameName":"法甲","homeTeamName":"圣埃蒂安","guestTeamName":"甘冈","matchTime":1521388800000,"homeScore":2,"guestScore":0,"handicap":-1,"sp":"3:1.52;1:3.55;0:5.26","spr":"3:2.80;1:3.30;0:2.13","selections":"3","createDate":1521382845000,"createBy":"ZJ100006","updateDate":1521417600000,"updateBy":"task","delFlag":"0","result":"3","resultr":"3R"}]
             * multiple : 5
             * betnum : 1
             * seqnum : 2
             * betAmount : 10
             * revenueAmount :
             * status : 4
             */

            private int multiple;
            private int betnum;
            private int seqnum;
            private String betAmount;
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

            public String getBetAmount() {
                return betAmount;
            }

            public void setBetAmount(String betAmount) {
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
                 * id : 6d1cd7d82fbb4bb0a32f3e39ecf95c7c1521382844702
                 * relId : 13
                 * moduleType : 0
                 * issue : 20180318
                 * seq : 037
                 * gameName : 荷甲
                 * homeTeamName : 兹沃勒
                 * guestTeamName : 费耶诺德
                 * matchTime : 1521387900000
                 * homeScore : 3
                 * guestScore : 4
                 * handicap : 1
                 * sp : 3:4.35;1:3.70;0:1.59
                 * spr : 3:2.04;1:3.50;0:2.83
                 * selections : 0
                 * createDate : 1521382845000
                 * createBy : ZJ100006
                 * updateDate : 1521424800000
                 * updateBy : task
                 * delFlag : 0
                 * result : 0
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
                private long matchTime;
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

                public long getMatchTime() {
                    return matchTime;
                }

                public void setMatchTime(long matchTime) {
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