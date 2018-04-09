package com.micai.fox.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.igexin.sdk.PushManager;
import com.micai.fox.R;
import com.micai.fox.app.Config;
import com.micai.fox.app.Url;
import com.micai.fox.parambean.NotiBean;
import com.micai.fox.parambean.ParamBean;
import com.micai.fox.parambean.ZhongChouBean;
import com.micai.fox.resultbean.MineResultBean;
import com.micai.fox.resultbean.ZhongChouDetailResultBean;
import com.micai.fox.util.PrefUtils;
import com.micai.fox.util.Tools;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;

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
    private ZhongChouBean bean;

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

    @OnClick({R.id.tv_back, R.id.pay_btn_pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.pay_btn_pay:
                pay();
                break;
        }
    }


    private ParamBean paramBean;
    private ParamBean.ParamData paramData;

    private void pay() {
        paramBean = new ParamBean();
        paramData = new ParamBean.ParamData();
        paramData.setOrderId("" + bean.getOrderId());
        paramBean.setParamData(paramData);
        OkHttpUtils.postString()
                .mediaType(MediaType.parse(Url.CONTENT_TYPE))
                .url(String.format(Url.WEB_PAY, Config.getInstance().getSessionId()))
                .content(new Gson().toJson(paramBean))
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) throws Exception {
                Log.e("yjl", "pay--data" + response);
                /*if (Tools.isGoodJson(response)) {
                    Config.getInstance().setJin(false);
                } else {
                    Config.getInstance().setJin(true);
                }*/
                Intent intent = new Intent(PayActivity.this, PayResultActivity.class);
                intent.putExtra("URL", response);
                startActivity(intent);
            }
        });
    }

}
