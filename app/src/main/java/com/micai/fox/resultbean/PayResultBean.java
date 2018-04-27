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

    public static class ExecDatasBean {
        /**
         * payHtml : <html><head><meta http-equiv='Content-Type' content='text/html; charset=utf-8'/></head><body><form id = 'pay_form' action='http://spay.ic-pay.com/icpay-sea/netrecv/merchant/recvMerchant.html' method='post'><input type='hidden' name='extension' value=''/><input type='hidden' name='check_value' value='1d696087f0aa997311d064737792ceb4'/><input type='hidden' name='ord_id' value='1523323475051423034'/><input type='hidden' name='subject' value='%E4%BC%97%E7%AD%B9%E6%94%AF%E4%BB%98'/><input type='hidden' name='mobile_no' value=''/><input type='hidden' name='trans_amt' value='1.00'/><input type='hidden' name='card_type' value='DT01'/><input type='hidden' name='version' value='01'/><input type='hidden' name='card_id' value=''/><input type='hidden' name='bg_ret_url' value='http%3A%2F%2Fspay.ic-pay.com%2Ficpay-sea%2Fnetrecv%2Fmerchant%2FwebAsynMessage'/><input type='hidden' name='gate_id' value='1001'/><input type='hidden' name='bank_id' value='01050000'/><input type='hidden' name='card_name' value=''/><input type='hidden' name='mer_priv' value=''/><input type='hidden' name='cust_id' value='4001061867'/><input type='hidden' name='ret_url' value=''/></form></body><script type='text/javascript'>document.all.pay_form.submit();</script></html>
         */

        private String payHtml;
        private String payId;

        public String getPayHtml() {
            return payHtml;
        }

        public void setPayHtml(String payHtml) {
            this.payHtml = payHtml;
        }

        public String getPayId() {
            return payId;
        }

        public void setPayId(String payId) {
            this.payId = payId;
        }
    }
}
