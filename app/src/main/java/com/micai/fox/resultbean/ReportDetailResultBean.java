package com.micai.fox.resultbean;

import java.util.List;

/**
 * Created by louqiang on 2018/2/24.
 */

public class ReportDetailResultBean {


    /**
     * execResult : true
     * execDatas : {"report":{"reportId":"BG100001","title":"报告标题","createDate":1519401415000,"content":"<p>报告内容富文本<img src=\"/mcfox/userfiles/1/images/photo/2018/02/20180223235641_950.jpg\"><br><\/p>","proId":"ZJ100001","proPhoto":"/mcfox/userfiles/1/images/photo/2018/02/20180221222610_10.jpg","proName":"proName","proAuth":"认证","hitRate":0,"crowdfundingId":"ZC100001","crowdfundingTitle":"众筹标题"},"game":[{"id":"874040326fe74f7eb270ab04dff873a61519401415614","relId":"BG100001","moduleType":1,"issue":"20180223","seq":"032","gameName":"墨超","homeTeamName":"普埃布拉","guestTeamName":"内卡萨","matchTime":"20180224110000","homeScore":1,"guestScore":1,"handicap":-1,"sp":"3:2.07;1:3.00;0:3.20","spr":"3:4.55;1:3.60;0:1.59","selections":"3,1","createDate":1519401416000,"createBy":"ZJ100001","updateDate":1519564204000,"updateBy":"task","delFlag":"0","result":"1","resultr":"0R"}]}
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
         * report : {"reportId":"BG100001","title":"报告标题","createDate":1519401415000,"content":"<p>报告内容富文本<img src=\"/mcfox/userfiles/1/images/photo/2018/02/20180223235641_950.jpg\"><br><\/p>","proId":"ZJ100001","proPhoto":"/mcfox/userfiles/1/images/photo/2018/02/20180221222610_10.jpg","proName":"proName","proAuth":"认证","hitRate":0,"crowdfundingId":"ZC100001","crowdfundingTitle":"众筹标题"}
         * game : [{"id":"874040326fe74f7eb270ab04dff873a61519401415614","relId":"BG100001","moduleType":1,"issue":"20180223","seq":"032","gameName":"墨超","homeTeamName":"普埃布拉","guestTeamName":"内卡萨","matchTime":"20180224110000","homeScore":1,"guestScore":1,"handicap":-1,"sp":"3:2.07;1:3.00;0:3.20","spr":"3:4.55;1:3.60;0:1.59","selections":"3,1","createDate":1519401416000,"createBy":"ZJ100001","updateDate":1519564204000,"updateBy":"task","delFlag":"0","result":"1","resultr":"0R"}]
         */

        private ReportBean report;
        private List<GameBean> game;

        public ReportBean getReport() {
            return report;
        }

        public void setReport(ReportBean report) {
            this.report = report;
        }

        public List<GameBean> getGame() {
            return game;
        }

        public void setGame(List<GameBean> game) {
            this.game = game;
        }

        public static class ReportBean {
            /**
             * reportId : BG100001
             * title : 报告标题
             * createDate : 1519401415000
             * content : <p>报告内容富文本<img src="/mcfox/userfiles/1/images/photo/2018/02/20180223235641_950.jpg"><br></p>
             * proId : ZJ100001
             * proPhoto : /mcfox/userfiles/1/images/photo/2018/02/20180221222610_10.jpg
             * proName : proName
             * proAuth : 认证
             * hitRate : 0.0
             * crowdfundingId : ZC100001
             * crowdfundingTitle : 众筹标题
             */

            private String reportId;
            private String title;
            private long createDate;
            private String content;
            private String proId;
            private String proPhoto;
            private String proName;
            private String proAuth;
            private double hitRate;
            private String crowdfundingId;
            private String crowdfundingTitle;

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

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getProId() {
                return proId;
            }

            public void setProId(String proId) {
                this.proId = proId;
            }

            public String getProPhoto() {
                return proPhoto;
            }

            public void setProPhoto(String proPhoto) {
                this.proPhoto = proPhoto;
            }

            public String getProName() {
                return proName;
            }

            public void setProName(String proName) {
                this.proName = proName;
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

            public String getCrowdfundingId() {
                return crowdfundingId;
            }

            public void setCrowdfundingId(String crowdfundingId) {
                this.crowdfundingId = crowdfundingId;
            }

            public String getCrowdfundingTitle() {
                return crowdfundingTitle;
            }

            public void setCrowdfundingTitle(String crowdfundingTitle) {
                this.crowdfundingTitle = crowdfundingTitle;
            }
        }

        public static class GameBean {
            /**
             * id : 874040326fe74f7eb270ab04dff873a61519401415614
             * relId : BG100001
             * moduleType : 1
             * issue : 20180223
             * seq : 032
             * gameName : 墨超
             * homeTeamName : 普埃布拉
             * guestTeamName : 内卡萨
             * matchTime : 20180224110000
             * homeScore : 1
             * guestScore : 1
             * handicap : -1
             * sp : 3:2.07;1:3.00;0:3.20
             * spr : 3:4.55;1:3.60;0:1.59
             * selections : 3,1
             * createDate : 1519401416000
             * createBy : ZJ100001
             * updateDate : 1519564204000
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
            private String homeScore;
            private String guestScore;
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

            public String getHomeScore() {
                return homeScore;
            }

            public void setHomeScore(String homeScore) {
                this.homeScore = homeScore;
            }

            public String getGuestScore() {
                return guestScore;
            }

            public void setGuestScore(String guestScore) {
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
