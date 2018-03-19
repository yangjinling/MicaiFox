package com.micai.fox.resultbean;

import java.util.List;

/**
 * Created by louqiang on 2018/2/25.
 */

public class MyZhongchouPiluResultBean {

    /**
     * execResult : true
     * execDatas : {"profitInfo":{"winStatus":1,"totalBetAmount":"48.00","totalRevenueAmount":"0.00","totalProfitAmount":"-48.00","depotProfitRate":"-1.0000","cashPercent":0.8,"cashProfitAmount":-48,"cashProfitRate":1},"betInfo":[{"match":[{"id":"112b8967a89e4754ae6182b4282fa8f61521284210919","relId":"3c99e1498a964586afc2f146a095129f1521284035096","moduleType":0,"issue":"20180317","seq":"022","gameName":"西甲","homeTeamName":"拉科","guestTeamName":"拉帕马斯","matchTime":1521288000000,"homeScore":1,"guestScore":1,"handicap":-1,"sp":"3:1.66;1:3.60;0:4.05","spr":"3:3.00;1:3.45;0:1.98","selections":"3,3R","createDate":1521284211000,"createBy":"ZJ100010","updateDate":1521302400000,"updateBy":"task","delFlag":"0","result":"1","resultr":"0R"},{"id":"9ee75a4fb25047819ce945547a0fa5f11521284210933","relId":"3c99e1498a964586afc2f146a095129f1521284035096","moduleType":0,"issue":"20180317","seq":"023","gameName":"英足总杯","homeTeamName":"斯旺西","guestTeamName":"热刺","matchTime":1521288900000,"homeScore":0,"guestScore":3,"handicap":1,"sp":"3:7.50;1:4.20;0:1.32","spr":"3:2.86;1:3.35;0:2.08","selections":"3,3R,1R","createDate":1521284211000,"createBy":"ZJ100010","updateDate":1521302402000,"updateBy":"task","delFlag":"0","result":"0","resultr":"0R"},{"id":"2468ea6955b94e58aa2668409a1cea1d1521284210933","relId":"3c99e1498a964586afc2f146a095129f1521284035096","moduleType":0,"issue":"20180317","seq":"024","gameName":"英冠","homeTeamName":"富勒姆","guestTeamName":"女王巡游","matchTime":1521289800000,"homeScore":2,"guestScore":2,"handicap":-1,"sp":"3:1.38;1:4.40;0:5.65","spr":"3:2.17;1:3.65;0:2.54","selections":"3,0R","createDate":1521284211000,"createBy":"ZJ100010","updateDate":1521302401000,"updateBy":"task","delFlag":"0","result":"1","resultr":"0R"}],"multiple":2,"betnum":12,"seqnum":3,"betAmount":48,"revenueAmount":0,"status":5}]}
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
         * profitInfo : {"winStatus":1,"totalBetAmount":"48.00","totalRevenueAmount":"0.00","totalProfitAmount":"-48.00","depotProfitRate":"-1.0000","cashPercent":0.8,"cashProfitAmount":-48,"cashProfitRate":1}
         * betInfo : [{"match":[{"id":"112b8967a89e4754ae6182b4282fa8f61521284210919","relId":"3c99e1498a964586afc2f146a095129f1521284035096","moduleType":0,"issue":"20180317","seq":"022","gameName":"西甲","homeTeamName":"拉科","guestTeamName":"拉帕马斯","matchTime":1521288000000,"homeScore":1,"guestScore":1,"handicap":-1,"sp":"3:1.66;1:3.60;0:4.05","spr":"3:3.00;1:3.45;0:1.98","selections":"3,3R","createDate":1521284211000,"createBy":"ZJ100010","updateDate":1521302400000,"updateBy":"task","delFlag":"0","result":"1","resultr":"0R"},{"id":"9ee75a4fb25047819ce945547a0fa5f11521284210933","relId":"3c99e1498a964586afc2f146a095129f1521284035096","moduleType":0,"issue":"20180317","seq":"023","gameName":"英足总杯","homeTeamName":"斯旺西","guestTeamName":"热刺","matchTime":1521288900000,"homeScore":0,"guestScore":3,"handicap":1,"sp":"3:7.50;1:4.20;0:1.32","spr":"3:2.86;1:3.35;0:2.08","selections":"3,3R,1R","createDate":1521284211000,"createBy":"ZJ100010","updateDate":1521302402000,"updateBy":"task","delFlag":"0","result":"0","resultr":"0R"},{"id":"2468ea6955b94e58aa2668409a1cea1d1521284210933","relId":"3c99e1498a964586afc2f146a095129f1521284035096","moduleType":0,"issue":"20180317","seq":"024","gameName":"英冠","homeTeamName":"富勒姆","guestTeamName":"女王巡游","matchTime":1521289800000,"homeScore":2,"guestScore":2,"handicap":-1,"sp":"3:1.38;1:4.40;0:5.65","spr":"3:2.17;1:3.65;0:2.54","selections":"3,0R","createDate":1521284211000,"createBy":"ZJ100010","updateDate":1521302401000,"updateBy":"task","delFlag":"0","result":"1","resultr":"0R"}],"multiple":2,"betnum":12,"seqnum":3,"betAmount":48,"revenueAmount":0,"status":5}]
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
             * winStatus : 1
             * totalBetAmount : 48.00
             * totalRevenueAmount : 0.00
             * totalProfitAmount : -48.00
             * depotProfitRate : -1.0000
             * cashPercent : 0.8
             * cashProfitAmount : -48.0
             * cashProfitRate : 1.0
             */

            private int winStatus;
            private String totalBetAmount;
            private String totalRevenueAmount;
            private String totalProfitAmount;
            private String depotProfitRate;
            private double cashPercent;
            private double cashProfitAmount;
            private double cashProfitRate;

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

            public String getDepotProfitRate() {
                return depotProfitRate;
            }

            public void setDepotProfitRate(String depotProfitRate) {
                this.depotProfitRate = depotProfitRate;
            }

            public double getCashPercent() {
                return cashPercent;
            }

            public void setCashPercent(double cashPercent) {
                this.cashPercent = cashPercent;
            }

            public double getCashProfitAmount() {
                return cashProfitAmount;
            }

            public void setCashProfitAmount(double cashProfitAmount) {
                this.cashProfitAmount = cashProfitAmount;
            }

            public double getCashProfitRate() {
                return cashProfitRate;
            }

            public void setCashProfitRate(double cashProfitRate) {
                this.cashProfitRate = cashProfitRate;
            }
        }

        public static class BetInfoBean {
            /**
             * match : [{"id":"112b8967a89e4754ae6182b4282fa8f61521284210919","relId":"3c99e1498a964586afc2f146a095129f1521284035096","moduleType":0,"issue":"20180317","seq":"022","gameName":"西甲","homeTeamName":"拉科","guestTeamName":"拉帕马斯","matchTime":1521288000000,"homeScore":1,"guestScore":1,"handicap":-1,"sp":"3:1.66;1:3.60;0:4.05","spr":"3:3.00;1:3.45;0:1.98","selections":"3,3R","createDate":1521284211000,"createBy":"ZJ100010","updateDate":1521302400000,"updateBy":"task","delFlag":"0","result":"1","resultr":"0R"},{"id":"9ee75a4fb25047819ce945547a0fa5f11521284210933","relId":"3c99e1498a964586afc2f146a095129f1521284035096","moduleType":0,"issue":"20180317","seq":"023","gameName":"英足总杯","homeTeamName":"斯旺西","guestTeamName":"热刺","matchTime":1521288900000,"homeScore":0,"guestScore":3,"handicap":1,"sp":"3:7.50;1:4.20;0:1.32","spr":"3:2.86;1:3.35;0:2.08","selections":"3,3R,1R","createDate":1521284211000,"createBy":"ZJ100010","updateDate":1521302402000,"updateBy":"task","delFlag":"0","result":"0","resultr":"0R"},{"id":"2468ea6955b94e58aa2668409a1cea1d1521284210933","relId":"3c99e1498a964586afc2f146a095129f1521284035096","moduleType":0,"issue":"20180317","seq":"024","gameName":"英冠","homeTeamName":"富勒姆","guestTeamName":"女王巡游","matchTime":1521289800000,"homeScore":2,"guestScore":2,"handicap":-1,"sp":"3:1.38;1:4.40;0:5.65","spr":"3:2.17;1:3.65;0:2.54","selections":"3,0R","createDate":1521284211000,"createBy":"ZJ100010","updateDate":1521302401000,"updateBy":"task","delFlag":"0","result":"1","resultr":"0R"}]
             * multiple : 2
             * betnum : 12
             * seqnum : 3
             * betAmount : 48.0
             * revenueAmount : 0.0
             * status : 5
             */

            private int multiple;
            private int betnum;
            private int seqnum;
            private double betAmount;
            private double revenueAmount;
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

            public double getRevenueAmount() {
                return revenueAmount;
            }

            public void setRevenueAmount(double revenueAmount) {
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
                 * id : 112b8967a89e4754ae6182b4282fa8f61521284210919
                 * relId : 3c99e1498a964586afc2f146a095129f1521284035096
                 * moduleType : 0
                 * issue : 20180317
                 * seq : 022
                 * gameName : 西甲
                 * homeTeamName : 拉科
                 * guestTeamName : 拉帕马斯
                 * matchTime : 1521288000000
                 * homeScore : 1
                 * guestScore : 1
                 * handicap : -1
                 * sp : 3:1.66;1:3.60;0:4.05
                 * spr : 3:3.00;1:3.45;0:1.98
                 * selections : 3,3R
                 * createDate : 1521284211000
                 * createBy : ZJ100010
                 * updateDate : 1521302400000
                 * updateBy : task
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
