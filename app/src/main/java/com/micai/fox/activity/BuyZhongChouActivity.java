package com.micai.fox.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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

import java.math.BigDecimal;

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
    @Bind(R.id.tv_per)
    TextView tv_per;
    private ZhongChouDetailResultBean detailResultBean;
    private BaseResultBean baseResultBean;

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
            tv_per.setText("购买金额需为" + detailResultBean.getExecDatas().getIncrementQuota() + "元整数倍，100元起");
            buyEtMoney.setHint("￥" + detailResultBean.getExecDatas().getOneAmountDown() + "~" + detailResultBean.getExecDatas().getOneAmountUp());
        }
        buyEtMoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!TextUtils.isEmpty(editable.toString())) {
                    buyIvMinus.setClickable(true);
                } else {
                    buyIvMinus.setClickable(false);
                }
            }
        });
    }

    @OnClick({R.id.buy_pay, R.id.tv_back, R.id.buy_iv_minus, R.id.buy_iv_plus})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.buy_iv_minus:

                int chas = new BigDecimal(buyEtMoney.getText().toString()).compareTo(new BigDecimal(detailResultBean.getExecDatas().getAmountDown()));
                if (chas == -1) {
                    String contents = "支持金额需在￥" + detailResultBean.getExecDatas().getOneAmountDown() + "~" + detailResultBean.getExecDatas().getOneAmountUp() + "之间，请重新输入";
                    Tools.showPayPopWindow(BuyZhongChouActivity.this, view, contents);
                } else {
                    if (Integer.parseInt(buyEtMoney.getText().toString()) % Integer.parseInt(detailResultBean.getExecDatas().getIncrementQuota()) == 0) {
                        double minus = sub(buyEtMoney.getText().toString(), detailResultBean.getExecDatas().getIncrementQuota());
                        buyEtMoney.setText("" + minus);
                    } else {
                        String contents = "支持金额需为￥" + detailResultBean.getExecDatas().getIncrementQuota() + "元的整数倍，请重新输入";
                        Tools.showPayPopWindow(BuyZhongChouActivity.this, view, contents);

                    }
                }

                break;
            case R.id.buy_iv_plus:
                int cha = new BigDecimal(buyEtMoney.getText().toString()).compareTo(new BigDecimal(detailResultBean.getExecDatas().getOneAmountUp()));
                if (cha == 1) {
//                    支持金额需在￥XX~XXX之间，请重新输入
                    String content = "支持金额需在￥" + detailResultBean.getExecDatas().getOneAmountDown() + "~" + detailResultBean.getExecDatas().getOneAmountUp() + "之间，请重新输入";
                    Tools.showPayPopWindow(BuyZhongChouActivity.this, view, content);
                } else {
                    if (Integer.parseInt(buyEtMoney.getText().toString()) % Integer.parseInt(detailResultBean.getExecDatas().getIncrementQuota()) == 0) {
                        double add = add(buyEtMoney.getText().toString(), detailResultBean.getExecDatas().getIncrementQuota());
                        buyEtMoney.setText("" + add);
                    } else {
                        String content = "支持金额需为￥" + detailResultBean.getExecDatas().getIncrementQuota() + "元的整数倍，请重新输入";
                        Tools.showPayPopWindow(BuyZhongChouActivity.this, view, content);

                    }
                }
                break;
            case R.id.buy_pay:
                checkPay(view, 0);
                break;
        }
    }

    private void changetMoney() {
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
        buyZhongChou(view);
    }

    private void buyZhongChou(final View view) {
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
                    baseResultBean = new Gson().fromJson(response, BaseResultBean.class);
                    if (baseResultBean.isExecResult()) {
                        ZhongChouBean bean = new ZhongChouBean(detailResultBean.getExecDatas().getCrowdfundingId(), detailResultBean.getExecDatas().getTitle(), buyEtMoney.getText().toString());
                        Intent intent = new Intent(BuyZhongChouActivity.this, PayActivity.class);
                        intent.putExtra("BEAN", bean);
                        startActivity(intent);
                    } else {
                        Tools.showPayPopWindow(BuyZhongChouActivity.this, view, "" + baseResultBean.getExecMsg());
                    }

                }

            }
        });
    }

    /**
     * 提供精确的加法运算。
     *
     * @param v1 被加数
     * @param v2 加数
     * @return 两个参数的和
     */
    public double add(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.add(b2).doubleValue();
    }

    /**
     * 提供精确的减法运算。
     *
     * @param v1 被减数
     * @param v2 减数
     * @return 两个参数的差
     */
    public double sub(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.subtract(b2).doubleValue();
    }

}
