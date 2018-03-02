package com.micai.fox.resultbean;

import java.util.List;

/**
 * Created by louqiang on 2018/2/25.
 */

public class MyZhongchouPiluResultBean {

    /**
     * execResult : true
     * execDatas : {"profitInfo":{"totalBetAmount":"2.00","totalRevenueAmount":"0.00","totalProfitAmount":"0.00","depotProfitRate":"0.0000"},"betInfo":[{"match":[{"id":"02de0b95743b470db339d2f75f457a3c1519739990667","relId":"5","moduleType":0,"issue":"20180227","seq":"001","gameName":"西甲","homeTeamName":"西班牙人","guestTeamName":"皇马","matchTime":"20180228030000","handicap":1,"sp":"3:6.20;1:4.50;0:1.34","spr":"3:2.61;1:3.70;0:2.10","selections":"1","createDate":1519739991000,"createBy":"ZJ100001","delFlag":"0"},{"id":"adbae6146e01440da717982bfdbaa5511519739990668","relId":"5","moduleType":0,"issue":"20180227","seq":"002","gameName":"英冠","homeTeamName":"赫尔城","guestTeamName":"巴恩斯利","matchTime":"20180228034500","handicap":-1,"sp":"3:1.72;1:3.35;0:4.05","spr":"3:3.30;1:3.40;0:1.88","selections":"3R","createDate":1519739991000,"createBy":"ZJ100001","delFlag":"0"}],"multiple":1,"betnum":1,"seqnum":2,"betAmount":2,"revenueAmount":"","status":4}]}
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
         * profitInfo : {"totalBetAmount":"2.00","totalRevenueAmount":"0.00","totalProfitAmount":"0.00","depotProfitRate":"0.0000"}
         * betInfo : [{"match":[{"id":"02de0b95743b470db339d2f75f457a3c1519739990667","relId":"5","moduleType":0,"issue":"20180227","seq":"001","gameName":"西甲","homeTeamName":"西班牙人","guestTeamName":"皇马","matchTime":"20180228030000","handicap":1,"sp":"3:6.20;1:4.50;0:1.34","spr":"3:2.61;1:3.70;0:2.10","selections":"1","createDate":1519739991000,"createBy":"ZJ100001","delFlag":"0"},{"id":"adbae6146e01440da717982bfdbaa5511519739990668","relId":"5","moduleType":0,"issue":"20180227","seq":"002","gameName":"英冠","homeTeamName":"赫尔城","guestTeamName":"巴恩斯利","matchTime":"20180228034500","handicap":-1,"sp":"3:1.72;1:3.35;0:4.05","spr":"3:3.30;1:3.40;0:1.88","selections":"3R","createDate":1519739991000,"createBy":"ZJ100001","delFlag":"0"}],"multiple":1,"betnum":1,"seqnum":2,"betAmount":2,"revenueAmount":"","status":4}]
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
             * depotProfitRate : 0.0000
             */

            private String totalBetAmount;
            private String totalRevenueAmount;
            private String totalProfitAmount;
            private String depotProfitRate;

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
        }

        public static class BetInfoBean {
            /**
             * match : [{"id":"02de0b95743b470db339d2f75f457a3c1519739990667","relId":"5","moduleType":0,"issue":"20180227","seq":"001","gameName":"西甲","homeTeamName":"西班牙人","guestTeamName":"皇马","matchTime":"20180228030000","handicap":1,"sp":"3:6.20;1:4.50;0:1.34","spr":"3:2.61;1:3.70;0:2.10","selections":"1","createDate":1519739991000,"createBy":"ZJ100001","delFlag":"0"},{"id":"adbae6146e01440da717982bfdbaa5511519739990668","relId":"5","moduleType":0,"issue":"20180227","seq":"002","gameName":"英冠","homeTeamName":"赫尔城","guestTeamName":"巴恩斯利","matchTime":"20180228034500","handicap":-1,"sp":"3:1.72;1:3.35;0:4.05","spr":"3:3.30;1:3.40;0:1.88","selections":"3R","createDate":1519739991000,"createBy":"ZJ100001","delFlag":"0"}]
             * multiple : 1
             * betnum : 1
             * seqnum : 2
             * betAmount : 2.0
             * revenueAmount :
             * status : 4
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
                 * id : 02de0b95743b470db339d2f75f457a3c1519739990667
                 * relId : 5
                 * moduleType : 0
                 * issue : 20180227
                 * seq : 001
                 * gameName : 西甲
                 * homeTeamName : 西班牙人
                 * guestTeamName : 皇马
                 * matchTime : 20180228030000
                 * handicap : 1
                 * sp : 3:6.20;1:4.50;0:1.34
                 * spr : 3:2.61;1:3.70;0:2.10
                 * selections : 1
                 * createDate : 1519739991000
                 * createBy : ZJ100001
                 * delFlag : 0
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
            }
        }
    }
}
