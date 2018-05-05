package com.micai.fox.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.micai.fox.R;
import com.micai.fox.app.Config;
import com.micai.fox.app.Url;
import com.micai.fox.parambean.ParamBean;
import com.micai.fox.parambean.PayRefreshBean;
import com.micai.fox.parambean.ZhongChouBean;
import com.micai.fox.parambean.ZhongChouRefreshBean;
import com.micai.fox.resultbean.CheckPayResultBean;
import com.micai.fox.resultbean.PayResultBean;
import com.micai.fox.util.LogUtil;
import com.micai.fox.util.Tools;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.MediaType;

/*众筹支付界面*/
public class PayActivity extends AppCompatActivity {

    @Bind(R.id.tv_back)
    TextView tvBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.rl)
    RelativeLayout rl;
    @Bind(R.id.pay_tv_title)
    TextView payTvTitle;
    @Bind(R.id.pay_tv_money)
    TextView payTvMoney;
    @Bind(R.id.pay_btn_pay)
    Button payBtnPay;
    @Bind(R.id.pay_ll_payway)
    LinearLayout payLlPayway;
    @Bind(R.id.pay_ll_wechat)
    LinearLayout payLlWechat;
    @Bind(R.id.pay_ll_ali)
    LinearLayout payLlAli;
    @Bind(R.id.iv_wangyin)
    ImageView ivWangyin;
    @Bind(R.id.iv_wechat)
    ImageView ivWechat;
    @Bind(R.id.iv_ali)
    ImageView ivAli;
    private ZhongChouBean bean;
    private int kind = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        EventBus.getDefault().register(this);
        ButterKnife.bind(this);
        tvTitle.setText("支付");
        bean = ((ZhongChouBean) getIntent().getSerializableExtra("BEAN"));
        payTvTitle.setText("" + bean.getTitle());
        payTvMoney.setText("￥" + bean.getMoney());
        payBtnPay.setText("支付￥" + bean.getMoney());
    }

    @OnClick({R.id.tv_back, R.id.pay_btn_pay, R.id.pay_ll_payway, R.id.pay_ll_wechat, R.id.pay_ll_ali})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.pay_btn_pay:
                pay(kind);
                break;
            case R.id.pay_ll_payway:
                ivWangyin.setBackgroundResource(R.drawable.pointedselect);
                ivWechat.setBackgroundResource(R.drawable.point);
                ivAli.setBackgroundResource(R.drawable.point);
                kind = 0;
                break;
            case R.id.pay_ll_wechat:
                ivWangyin.setBackgroundResource(R.drawable.point);
                ivWechat.setBackgroundResource(R.drawable.pointedselect);
                ivAli.setBackgroundResource(R.drawable.point);
                kind = 1;
                break;
            case R.id.pay_ll_ali:
                ivWangyin.setBackgroundResource(R.drawable.point);
                ivWechat.setBackgroundResource(R.drawable.point);
                ivAli.setBackgroundResource(R.drawable.pointedselect);
                kind = 2;
                break;
        }
    }

    private void pay(int kind) {
        if (kind == 0) {
            ZhongChouBean beans = new ZhongChouBean(bean.getOrderId(), bean.getTitle(), bean.getMoney());
            Intent intent = new Intent(PayActivity.this, ChooseBankActivity.class);
            intent.putExtra("BEAN", beans);
            startActivity(intent);
//            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Config.getInstance().isCheck()) {
            //查询状态
            checkPay();
        }
    }

    private ParamBean paramBean;
    private ParamBean.ParamData paramData;

    private void checkPay() {
        paramBean = new ParamBean();
        paramData = new ParamBean.ParamData();
        paramData.setPayId("" + Config.getInstance().getPayId());
        paramBean.setParamData(paramData);
        OkHttpUtils.postString()
                .mediaType(MediaType.parse(Url.CONTENT_TYPE))
                .url(String.format(Url.WEB_CHECK_PAY, Config.getInstance().getSessionId()))
                .content(new Gson().toJson(paramBean))
                .build().execute(new StringCallback() {

            private CheckPayResultBean payResultBean;

            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) throws Exception {
                Log.e("yjl", "pay--data" + response);
                if (Tools.isGoodJson(response)) {
                    Config.getInstance().setJin(false);
                    payResultBean = new Gson().fromJson(response, CheckPayResultBean.class);
                    if (payResultBean.isExecResult()) {
                        if ("0000".equals(payResultBean.getExecDatas().getResp_code())) {
                            if (null == payResultBean.getExecDatas().getOrd_state()) {
                                checkPayState(2);
                            } else {
                                switch (payResultBean.getExecDatas().getOrd_state()) {
                                    case "0"://交易处理中
                                        checkPayState(3);
                                        break;
                                    case "1"://交易成功
                                        checkPayState(0);
                                        break;
                                    case "2"://交易失败
                                        checkPayState(1);
                                        break;
                                }
                            }
                        } else {
                            checkPayState(2);
                        }
                    } else {
                        checkPayState(2);
                    }

                } else {
                    Config.getInstance().setJin(true);
                }

            }
        });
    }

    private Dialog dialog;

    private void checkPayState(int type) {
        Config.getInstance().setCheck(false);
//        Config.getInstance().setPayId("");
        if (type == 0) {
            //支付成功
            dialog = Tools.showDialog(this, 4, null);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (null != dialog && dialog.isShowing()) {
                        dialog.dismiss();
                        Intent intent = new Intent(PayActivity.this, ZhongChouOrderDetailActivity.class);
                        intent.putExtra("orderId", bean.getOrderId());
                        intent.putExtra("ACCOUNT", true);
                        startActivity(intent);
                        finish();
                    }
                }
            }, 3000);

        } else if (type == 1) {
            //支付失败
            Tools.showPayPopWindow(this, payBtnPay, null, 1);
        } else if (type == 2) {
            //没查询到结果
            Tools.showPayPopWindow(this, payBtnPay, null, 2);
        }else if (type==3){
            Tools.showPayPopWindow(this, payBtnPay, null, 4);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    //给网络请求加缓冲小黄圈
    @Subscribe
    public void onEventMainThread(PayRefreshBean bean) {
        if (bean.isRefresh()) {
            checkPay();
        }
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        ButterKnife.unbind(this);
        super.onDestroy();
    }
}
