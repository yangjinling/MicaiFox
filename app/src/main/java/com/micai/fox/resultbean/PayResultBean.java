package com.micai.fox.resultbean;

/**
 * Created by lq on 2018/4/10.
 */

public class PayResultBean {

    /**
     * execResult : true
     * execDatas : {"payHtml":"<html><head><meta http-equiv='Content-Type' content='text/html; charset=utf-8'/><\/head><body><form id = 'pay_form' action='http://spay.ic-pay.com/icpay-sea/netrecv/merchant/recvMerchant.html' method='post'><input type='hidden' name='extension' value=''/><input type='hidden' name='check_value' value='1d696087f0aa997311d064737792ceb4'/><input type='hidden' name='ord_id' value='1523323475051423034'/><input type='hidden' name='subject' value='%E4%BC%97%E7%AD%B9%E6%94%AF%E4%BB%98'/><input type='hidden' name='mobile_no' value=''/><input type='hidden' name='trans_amt' value='1.00'/><input type='hidden' name='card_type' value='DT01'/><input type='hidden' name='version' value='01'/><input type='hidden' name='card_id' value=''/><input type='hidden' name='bg_ret_url' value='http%3A%2F%2Fspay.ic-pay.com%2Ficpay-sea%2Fnetrecv%2Fmerchant%2FwebAsynMessage'/><input type='hidden' name='gate_id' value='1001'/><input type='hidden' name='bank_id' value='01050000'/><input type='hidden' name='card_name' value=''/><input type='hidden' name='mer_priv' value=''/><input type='hidden' name='cust_id' value='4001061867'/><input type='hidden' name='ret_url' value=''/><\/form><\/body><script type='text/javascript'>document.all.pay_form.submit();<\/script><\/html>"}
     * count : 0
     * num : 0
     */


    private String execMsg;

    public String getExecMsg() {
        return execMsg;
    }

    public void setExecMsg(String execMsg) {
        this.execMsg = execMsg;
    }

    /**
     * execResult : true
     * execDatas : {"amount":"0.10","create_time":"1527164506","err_msg":"SUCCESS","expires_time":"1527164626","mch_id":"7020004294","order_sn":"201805242021466957132","out_order_sn":"180524186658084","pay_url":"https://pay.x314.cn/wxpay?order_sn=201805242021466957132","qrcode_url":"https://pay.x314.cn/public/qrcode/dEcsKmpfEm9C.jpg","result_code":"0","sign":"317f58a1995d3c76645996c2fbccd82d"}
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
         * amount : 0.10
         * create_time : 1527164506
         * err_msg : SUCCESS
         * expires_time : 1527164626
         * mch_id : 7020004294
         * order_sn : 201805242021466957132
         * out_order_sn : 180524186658084
         * pay_url : https://pay.x314.cn/wxpay?order_sn=201805242021466957132
         * qrcode_url : https://pay.x314.cn/public/qrcode/dEcsKmpfEm9C.jpg
         * result_code : 0
         * sign : 317f58a1995d3c76645996c2fbccd82d
         */

        private String amount;
        private String create_time;
        private String err_msg;
        private String expires_time;
        private String mch_id;
        private String order_sn;
        private String out_order_sn;
        private String pay_url;
        private String qrcode_url;
        private String result_code;
        private String sign;

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getErr_msg() {
            return err_msg;
        }

        public void setErr_msg(String err_msg) {
            this.err_msg = err_msg;
        }

        public String getExpires_time() {
            return expires_time;
        }

        public void setExpires_time(String expires_time) {
            this.expires_time = expires_time;
        }

        public String getMch_id() {
            return mch_id;
        }

        public void setMch_id(String mch_id) {
            this.mch_id = mch_id;
        }

        public String getOrder_sn() {
            return order_sn;
        }

        public void setOrder_sn(String order_sn) {
            this.order_sn = order_sn;
        }

        public String getOut_order_sn() {
            return out_order_sn;
        }

        public void setOut_order_sn(String out_order_sn) {
            this.out_order_sn = out_order_sn;
        }

        public String getPay_url() {
            return pay_url;
        }

        public void setPay_url(String pay_url) {
            this.pay_url = pay_url;
        }

        public String getQrcode_url() {
            return qrcode_url;
        }

        public void setQrcode_url(String qrcode_url) {
            this.qrcode_url = qrcode_url;
        }

        public String getResult_code() {
            return result_code;
        }

        public void setResult_code(String result_code) {
            this.result_code = result_code;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }
    }
}
