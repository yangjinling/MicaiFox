package com.micai.fox.activity;

import android.content.Intent;
import android.os.Bundle;
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
import com.micai.fox.parambean.ZhongChouBean;
import com.micai.fox.resultbean.PayResultBean;
import com.micai.fox.util.Tools;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

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
            finish();
        }
    }

}
