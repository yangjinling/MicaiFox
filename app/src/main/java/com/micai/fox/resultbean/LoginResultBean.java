package com.micai.fox.resultbean;

import java.io.Serializable;

/**
 * Created by louqiang on 2018/1/20.
 */

public class LoginResultBean implements Serializable {


    /**
     * execResult : true
     * execDatas : {"id":"749e45bc8d7b4aceb9d8851e051088d01516457894981","loginName":"18363820101","name":"18363820101","nickName":"ling","mobileLogin":true,"user":{"id":"749e45bc8d7b4aceb9d8851e051088d01516457894981","isNewRecord":false,"createDate":"2018-01-20 22:18:15","updateDate":"2018-01-20 22:18:15","loginName":"18363820101","no":"18363820101","name":"18363820101","nickName":"ling","phone":"18363820101","mobile":"18363820101","userType":"1","loginIp":"1.15.140.180","loginDate":"2018-01-21 11:47:48","loginFlag":"1","oldLoginIp":"1.15.140.180","oldLoginDate":"2018-01-21 11:47:48","roleNames":"","roleEnNames":"","admin":false},"sessionid":"e8128e5592ca4f3ba1a38e57c11c9e241517018027167"}
     * count : 0
     * num : 0
     */

    private boolean execResult;
    private ExecDatasBean execDatas;
    private int count;
    private int num;
    private String execMsg;

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

    public String getExecMsg() {
        return execMsg;
    }

    public void setExecMsg(String execMsg) {
        this.execMsg = execMsg;
    }

    public static class ExecDatasBean implements Serializable {
        /**
         * id : 749e45bc8d7b4aceb9d8851e051088d01516457894981
         * loginName : 18363820101
         * name : 18363820101
         * nickName : ling
         * mobileLogin : true
         * user : {"id":"749e45bc8d7b4aceb9d8851e051088d01516457894981","isNewRecord":false,"createDate":"2018-01-20 22:18:15","updateDate":"2018-01-20 22:18:15","loginName":"18363820101","no":"18363820101","name":"18363820101","nickName":"ling","phone":"18363820101","mobile":"18363820101","userType":"1","loginIp":"1.15.140.180","loginDate":"2018-01-21 11:47:48","loginFlag":"1","oldLoginIp":"1.15.140.180","oldLoginDate":"2018-01-21 11:47:48","roleNames":"","roleEnNames":"","admin":false}
         * sessionid : e8128e5592ca4f3ba1a38e57c11c9e241517018027167
         */

        private String id;
        private String loginName;
        private String name;
        private String nickName;
        private boolean mobileLogin;
        private UserBean user;
        private String sessionid;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLoginName() {
            return loginName;
        }

        public void setLoginName(String loginName) {
            this.loginName = loginName;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public boolean isMobileLogin() {
            return mobileLogin;
        }

        public void setMobileLogin(boolean mobileLogin) {
            this.mobileLogin = mobileLogin;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public String getSessionid() {
            return sessionid;
        }

        public void setSessionid(String sessionid) {
            this.sessionid = sessionid;
        }

        public static class UserBean implements Serializable {
            /**
             * id : 749e45bc8d7b4aceb9d8851e051088d01516457894981
             * isNewRecord : false
             * createDate : 2018-01-20 22:18:15
             * updateDate : 2018-01-20 22:18:15
             * loginName : 18363820101
             * no : 18363820101
             * name : 18363820101
             * nickName : ling
             * phone : 18363820101
             * mobile : 18363820101
             * userType : 1
             * loginIp : 1.15.140.180
             * loginDate : 2018-01-21 11:47:48
             * loginFlag : 1
             * oldLoginIp : 1.15.140.180
             * oldLoginDate : 2018-01-21 11:47:48
             * roleNames :
             * roleEnNames :
             * admin : false
             */

            private String id;
            private boolean isNewRecord;
            private String createDate;
            private String updateDate;
            private String loginName;
            private String no;
            private String name;
            private String nickName;
            private String phone;
            private String mobile;
            private String userType;
            private String loginIp;
            private String loginDate;
            private String loginFlag;
            private String oldLoginIp;
            private String oldLoginDate;
            private String roleNames;
            private String roleEnNames;
            private boolean admin;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public boolean isIsNewRecord() {
                return isNewRecord;
            }

            public void setIsNewRecord(boolean isNewRecord) {
                this.isNewRecord = isNewRecord;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public String getUpdateDate() {
                return updateDate;
            }

            public void setUpdateDate(String updateDate) {
                this.updateDate = updateDate;
            }

            public String getLoginName() {
                return loginName;
            }

            public void setLoginName(String loginName) {
                this.loginName = loginName;
            }

            public String getNo() {
                return no;
            }

            public void setNo(String no) {
                this.no = no;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getNickName() {
                return nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getUserType() {
                return userType;
            }

            public void setUserType(String userType) {
                this.userType = userType;
            }

            public String getLoginIp() {
                return loginIp;
            }

            public void setLoginIp(String loginIp) {
                this.loginIp = loginIp;
            }

            public String getLoginDate() {
                return loginDate;
            }

            public void setLoginDate(String loginDate) {
                this.loginDate = loginDate;
            }

            public String getLoginFlag() {
                return loginFlag;
            }

            public void setLoginFlag(String loginFlag) {
                this.loginFlag = loginFlag;
            }

            public String getOldLoginIp() {
                return oldLoginIp;
            }

            public void setOldLoginIp(String oldLoginIp) {
                this.oldLoginIp = oldLoginIp;
            }

            public String getOldLoginDate() {
                return oldLoginDate;
            }

            public void setOldLoginDate(String oldLoginDate) {
                this.oldLoginDate = oldLoginDate;
            }

            public String getRoleNames() {
                return roleNames;
            }

            public void setRoleNames(String roleNames) {
                this.roleNames = roleNames;
            }

            public String getRoleEnNames() {
                return roleEnNames;
            }

            public void setRoleEnNames(String roleEnNames) {
                this.roleEnNames = roleEnNames;
            }

            public boolean isAdmin() {
                return admin;
            }

            public void setAdmin(boolean admin) {
                this.admin = admin;
            }
        }
    }
}
