package com.micai.fox.resultbean;

/**
 * Created by lq on 2018/2/27.
 */

public class ZhonchouReportResultBean {

    /**
     * execResult : true
     * execDatas : {"reportId":"BG100002","title":"报告标题222333444555","createDate":1519401415000,"gameName":"日职","homeTeamName":"清水鼓动","guestTeamName":"鹿岛鹿角","homeScore":0,"guestScore":0,"matchTime":"20180225120000","issue":"20180225","totalMatchNum":2,"winMatchNum":1}
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
         * reportId : BG100002
         * title : 报告标题222333444555
         * createDate : 1519401415000
         * gameName : 日职
         * homeTeamName : 清水鼓动
         * guestTeamName : 鹿岛鹿角
         * homeScore : 0
         * guestScore : 0
         * matchTime : 20180225120000
         * issue : 20180225
         * totalMatchNum : 2
         * winMatchNum : 1
         */

        private String reportId;
        private String title;
        private long createDate;
        private String gameName;
        private String homeTeamName;
        private String guestTeamName;
        private String homeScore;
        private int guestScore;
        private String matchTime;
        private String issue;
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

        public String getHomeScore() {
            return homeScore;
        }

        public void setHomeScore(String homeScore) {
            this.homeScore = homeScore;
        }

        public int getGuestScore() {
            return guestScore;
        }

        public void setGuestScore(int guestScore) {
            this.guestScore = guestScore;
        }

        public String getMatchTime() {
            return matchTime;
        }

        public void setMatchTime(String matchTime) {
            this.matchTime = matchTime;
        }

        public String getIssue() {
            return issue;
        }

        public void setIssue(String issue) {
            this.issue = issue;
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
