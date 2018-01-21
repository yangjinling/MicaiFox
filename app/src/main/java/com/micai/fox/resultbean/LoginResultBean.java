package com.micai.fox.resultbean;

/**
 * Created by louqiang on 2018/1/20.
 */

public class LoginResultBean {


    /**
     * id : 3aa135cf225049d9b579340bb0f6a8d01516193937423
     * loginName : 18612241134
     * name : 18612241134
     * mobileLogin : true
     * user : {"id":"3aa135cf225049d9b579340bb0f6a8d01516193937423","isNewRecord":false,"createDate":"2018-01-17 20:58:57","updateDate":"2018-01-17 20:58:57","loginName":"18612241134","no":"18612241134","name":"18612241134","phone":"18612241134","mobile":"18612241134","userType":"1","loginIp":"127.0.0.1","loginDate":"2018-01-19 00:01:49","loginFlag":"1","oldLoginIp":"127.0.0.1","oldLoginDate":"2018-01-19 00:01:49","admin":false,"roleNames":"","roleEnNames":""}
     * sessionid : 97b7bf6f1da4472d8f86be77fbea733b1516359749001
     */

    private String id;
    private String loginName;
    private String name;
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

    public static class UserBean {
        /**
         * id : 3aa135cf225049d9b579340bb0f6a8d01516193937423
         * isNewRecord : false
         * createDate : 2018-01-17 20:58:57
         * updateDate : 2018-01-17 20:58:57
         * loginName : 18612241134
         * no : 18612241134
         * name : 18612241134
         * phone : 18612241134
         * mobile : 18612241134
         * userType : 1
         * loginIp : 127.0.0.1
         * loginDate : 2018-01-19 00:01:49
         * loginFlag : 1
         * oldLoginIp : 127.0.0.1
         * oldLoginDate : 2018-01-19 00:01:49
         * admin : false
         * roleNames :
         * roleEnNames :
         */

        private String id;
        private boolean isNewRecord;
        private String createDate;
        private String updateDate;
        private String loginName;
        private String no;
        private String name;
        private String phone;
        private String mobile;
        private String userType;
        private String loginIp;
        private String loginDate;
        private String loginFlag;
        private String oldLoginIp;
        private String oldLoginDate;
        private boolean admin;
        private String roleNames;
        private String roleEnNames;

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

        public boolean isAdmin() {
            return admin;
        }

        public void setAdmin(boolean admin) {
            this.admin = admin;
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
    }
}
