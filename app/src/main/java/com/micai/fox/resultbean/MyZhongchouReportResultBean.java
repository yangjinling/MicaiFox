package com.micai.fox.resultbean;

/**
 * Created by louqiang on 2018/2/25.
 */

public class MyZhongchouReportResultBean {

    /**
     * execResult : true
     * execDatas : {"reportId":"BG100001","title":"报告标题","createDate":1519401415000,"gameName":"墨超","homeTeamName":"普埃布拉","guestTeamName":"内卡萨","matchTime":"20180224110000","totalMatchNum":2,"winMatchNum":2}
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
         * reportId : BG100001
         * title : 报告标题
         * createDate : 1519401415000
         * gameName : 墨超
         * homeTeamName : 普埃布拉
         * guestTeamName : 内卡萨
         * matchTime : 20180224110000
         * totalMatchNum : 2
         * winMatchNum : 2
         */

        private String reportId;
        private String title;
        private long createDate;
        private String gameName;
        private String homeTeamName;
        private String guestTeamName;
        private String matchTime;
        private int totalMatchNum;
        private int winMatchNum;

        public String getReportId() {
            return reportId;
        }

        public void setReportId(String reportId) {
            this.reportId = reportId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public long getCreateDate() {
            return createDate;
        }

        public void setCreateDate(long createDate) {
            this.createDate = createDate;
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

        public int getTotalMatchNum() {
            return totalMatchNum;
        }

        public void setTotalMatchNum(int totalMatchNum) {
            this.totalMatchNum = totalMatchNum;
        }

        public int getWinMatchNum() {
            return winMatchNum;
        }

        public void setWinMatchNum(int winMatchNum) {
            this.winMatchNum = winMatchNum;
        }
    }
}
