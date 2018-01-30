package com.micai.fox.resultbean;

import java.util.List;

/**
 * Created by louqiang on 2018/1/27.
 */

public class HomeResultBean {

    /**
     * execResult : true
     * execDatas : {"banner":[{"imgPath":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1516032080989&di=08a4ad368e6af2586cbdd9aed58e5f0e&imgtype=0&src=http%3A%2F%2Fimg1.szhk.com%2FImage%2F2017%2F07%2F21%2F1500623627742.jpg","forwardPath":"2","forwardModule":"0","position":"0"},{"imgPath":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1516033615564&di=d70b9dcafd63ff93c9770d7fcc4fadb2&imgtype=0&src=http%3A%2F%2Fi2.hdslb.com%2Fbfs%2Farchive%2Fc432da6b2059c2b5e5bc734af426e46a156f0e1a.jpg","forwardPath":"1","forwardModule":"1","position":"0"},{"imgPath":"/admin-mcfox/userfiles/1/images/photo/2018/01/mmexport1494415444227.jpg","forwardPath":"4","forwardModule":"0","position":"0"},{"imgPath":"/admin-mcfox/userfiles/1/images/photo/2018/01/mmexport1494415379918.jpg","forwardPath":"3","forwardModule":"2","position":"0"}],"professor":[{"id":"5edb348b25a248e185a6f12c9204fa561516523589992","name":"zhang","photo":"/admin-mcfox/userfiles/1/images/photo/2018/01/75e15cac9e69538a72bc53832cdb00d1(1).jpg","auth":"zhang"}],"crowdfunding":[{"crowdfundingId":"1","title":"世界杯","amountDown":3,"amountUp":2,"realAmount":1000,"startDate":1516329373000,"userId":"5edb348b25a248e185a6f12c9204fa561516523589992","name":"zhang","photo":"/admin-mcfox/userfiles/1/images/photo/2018/01/75e15cac9e69538a72bc53832cdb00d1(1).jpg","auth":"zhang"},{"crowdfundingId":"2","title":"欧洲杯","startDate":1515551892000,"userId":"5edb348b25a248e185a6f12c9204fa561516523589992","name":"zhang","photo":"/admin-mcfox/userfiles/1/images/photo/2018/01/75e15cac9e69538a72bc53832cdb00d1(1).jpg","auth":"zhang"}]}
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
        private List<BannerBean> banner;
        private List<ProfessorBean> professor;
        private List<CrowdfundingBean> crowdfunding;

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

        public List<CrowdfundingBean> getCrowdfunding() {
            return crowdfunding;
        }

        public void setCrowdfunding(List<CrowdfundingBean> crowdfunding) {
            this.crowdfunding = crowdfunding;
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
             * id : 5edb348b25a248e185a6f12c9204fa561516523589992
             * name : zhang
             * photo : /admin-mcfox/userfiles/1/images/photo/2018/01/75e15cac9e69538a72bc53832cdb00d1(1).jpg
             * auth : zhang
             */

            private String id;
            private String name;
            private String photo;
            private String auth;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
            }

            public String getAuth() {
                return auth;
            }

            public void setAuth(String auth) {
                this.auth = auth;
            }
        }

        public static class CrowdfundingBean {
            /**
             * crowdfundingId : 1
             * title : 世界杯
             * amountDown : 3.0
             * amountUp : 2.0
             * realAmount : 1000.0
             * startDate : 1516329373000
             * userId : 5edb348b25a248e185a6f12c9204fa561516523589992
             * name : zhang
             * photo : /admin-mcfox/userfiles/1/images/photo/2018/01/75e15cac9e69538a72bc53832cdb00d1(1).jpg
             * auth : zhang
             */

            private String crowdfundingId;
            private String title;
            private double amountDown;
            private double amountUp;
            private double realAmount;
            private long startDate;
            private String userId;
            private String name;
            private String photo;
            private String auth;
            private String status;
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

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
            }

            public String getAuth() {
                return auth;
            }

            public void setAuth(String auth) {
                this.auth = auth;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }
        }
    }
}
