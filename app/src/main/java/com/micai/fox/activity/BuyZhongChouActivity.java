package com.micai.fox.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.micai.fox.R;
import com.micai.fox.app.Config;
import com.micai.fox.app.Url;
import com.micai.fox.parambean.ParamBean;
import com.micai.fox.parambean.ZhongChouBean;
import com.micai.fox.resultbean.BaseResultBean;
import com.micai.fox.resultbean.ZhongChouDetailResultBean;
import com.micai.fox.util.DateUtil;
import com.micai.fox.util.LogUtil;
import com.micai.fox.util.Tools;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.MediaType;

public class BuyZhongChouActivity extends AppCompatActivity {

    @Bind(R.id.tv_back)
    TextView tvBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.rl)
    RelativeLayout rl;
    @Bind(R.id.buy_tv_title)
    TextView buyTvTitle;
    @Bind(R.id.buy_tv1)
    TextView buyTv1;
    @Bind(R.id.buy_tv2)
    TextView buyTv2;
    @Bind(R.id.buy_iv_minus)
    ImageView buyIvMinus;
    @Bind(R.id.buy_et_money)
    EditText buyEtMoney;
    @Bind(R.id.buy_iv_plus)
    ImageView buyIvPlus;
    @Bind(R.id.buy_ck)
    CheckBox buyCk;
    @Bind(R.id.buy_pay)
    Button buyPay;
    private ZhongChouDetailResultBean detailResultBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_zhong_chou);
        ButterKnife.bind(this);
        tvTitle.setText("购买众筹");
        detailResultBean = ((ZhongChouDetailResultBean) getIntent().getSerializableExtra("BEAN"));
        if (null != detailResultBean) {
            buyTv1.setText("" + DateUtil.getDateToStrings(detailResultBean.getExecDatas().getEndDate()) + "或已筹金额达￥" + detailResultBean.getExecDatas().getAmountDown() + "时结束众筹");
            buyTv1.setText("" + DateUtil.getDateToStrings(detailResultBean.getExecDatas().getCashDate()) + "起开始兑付");
        }
    }

    @OnClick({R.id.buy_pay, R.id.tv_back, R.id.buy_iv_minus, R.id.buy_iv_plus})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.buy_iv_minus:
                break;
            case R.id.buy_iv_plus:
                break;
            case R.id.buy_pay:
                checkPay(view, 0);
                break;
        }
    }

    private ParamBean paramBean;
    private ParamBean.ParamData paramData;

    private void checkPay(View view, int type) {
        String content = null;
        if (TextUtils.isEmpty(buyEtMoney.getText().toString())) {
            content = "请输入您要支持的金额";
            Tools.showPayPopWindow(this, view, content);
            return;
        }
        if (!buyCk.isChecked()) {
            content = "购买众筹需先阅读并同意《众筹购买协议》";
            Tools.showPayPopWindow(this, view, content);
            return;
        }
        buyZhongChou();
    }

    private void buyZhongChou() {
        paramBean = new ParamBean();
        paramData = new ParamBean.ParamData();
        paramData.setCrowdfundingId("" + detailResultBean.getExecDatas().getCrowdfundingId());
        paramData.setPurchaseAmount("" + buyEtMoney.getText().toString());
        OkHttpUtils.postString()
                .mediaType(MediaType.parse(Url.CONTENT_TYPE))
                // .url(Url.WEB_SET_PASSWORD)
                .url(String.format(Url.WEB_HOME_ZHONGCHOU_DETAIL_BUY, Config.getInstance().getSessionId()))
                .content(new Gson().toJson(paramBean))
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) throws Exception {
                LogUtil.e("yjl", "buy--->>" + response);
                if (Tools.isGoodJson(response)) {
                    // content ="抱歉，该众筹已满标，请选择其他众筹";
                    // content ="抱歉，该众筹支持人数已达200人，无法继续购买，请选择其他众筹";

                    //  content ="抱歉，该众筹已结束，请选择其他众筹";
                    ZhongChouBean bean=new ZhongChouBean(detailResultBean.getExecDatas().getCrowdfundingId(),detailResultBean.getExecDatas().getTitle(),buyEtMoney.getText().toString());
                    Intent intent = new Intent(BuyZhongChouActivity.this, PayActivity.class);
                    intent.putExtra("BEAN", bean);
                    startActivity(intent);
                }

            }
        });
    }

}
