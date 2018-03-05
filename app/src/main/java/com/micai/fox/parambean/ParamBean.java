package com.micai.fox.parambean;

import java.io.Serializable;

/**
 * Created by louqiang on 2018/1/19.
 */

public class ParamBean implements Serializable {
    private String pageNum;
    private String length;
    private ParamData paramData;

    public String getPageNum() {
        return pageNum;
    }

    public void setPageNum(String pageNum) {
        this.pageNum = pageNum;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public ParamData getParamData() {
        return paramData;
    }

    public void setParamData(ParamData paramData) {
        this.paramData = paramData;
    }

    public static class ParamData implements Serializable {
        String phone;//手机号
        String code;//验证码
        String nickName;
        String oldPwd;
        String pwd;
        String pwd2;
        String content;
        String accountName;
        String accountNumber;
        String accountBank;
        String accountBankName;
        String accountBranch;
        String type;
        String crowdfundingId;
        String proId;
        String reportId;
        String orderId;
        String status;
        String photo;
        String purchaseAmount;

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getPwd() {
            return pwd;
        }

        public void setPwd(String pwd) {
            this.pwd = pwd;
        }

        public String getPwd2() {
            return pwd2;
        }

        public void setPwd2(String pwd2) {
            this.pwd2 = pwd2;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getOldPwd() {
            return oldPwd;
        }

        public void setOldPwd(String oldPwd) {
            this.oldPwd = oldPwd;
        }

        public String getAccountName() {
            return accountName;
        }

        public void setAccountName(String accountName) {
            this.accountName = accountName;
        }

        public String getAccountNumber() {
            return accountNumber;
        }

        public void setAccountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
        }

        public String getAccountBank() {
            return accountBank;
        }

        public void setAccountBank(String accountBank) {
            this.accountBank = accountBank;
        }

        public String getAccountBranch() {
            return accountBranch;
        }

        public void setAccountBranch(String accountBranch) {
            this.accountBranch = accountBranch;
        }

        public String getAccountBankName() {
            return accountBankName;
        }

        public void setAccountBankName(String accountBankName) {
            this.accountBankName = accountBankName;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getCrowdfundingId() {
            return crowdfundingId;
        }

        public void setCrowdfundingId(String crowdfundingId) {
            this.crowdfundingId = crowdfundingId;
        }

        public String getProId() {
            return proId;
        }

        public void setProId(String proId) {
            this.proId = proId;
        }

        public String getReportId() {
            return reportId;
        }

        public void setReportId(String reportId) {
            this.reportId = reportId;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getPurchaseAmount() {
            return purchaseAmount;
        }

        public void setPurchaseAmount(String purchaseAmount) {
            this.purchaseAmount = purchaseAmount;
        }
    }
}
