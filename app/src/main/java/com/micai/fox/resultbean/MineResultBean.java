package com.micai.fox.resultbean;

/**
 * Created by lq on 2018/2/5.
 */

public class MineResultBean {

    /**
     * execResult : true
     * execDatas : {"userId":"749e45bc8d7b4aceb9d8851e051088d01516457894981","nickName":"ling","photo":""}
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
         * userId : 749e45bc8d7b4aceb9d8851e051088d01516457894981
         * nickName : ling
         * photo :
         */

        private String userId;
        private String nickName;
        private String photo;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }
    }
}
