package com.micai.fox.bean;

/**
 * Created by lq on 2018/1/19.
 */

public class LoginParamBean  {
    private ParamData paramData;


    public ParamData getParamData() {
        return paramData;
    }

    public void setParamData(ParamData paramData) {
        this.paramData = paramData;
    }

   public static class ParamData {
       static   String phone;
       static  String code;

        public ParamData(String phone, String code) {
            this.phone = phone;
            this.code = code;
        }
    }
}
