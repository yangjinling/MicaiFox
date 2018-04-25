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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.micai.fox.R;
import com.micai.fox.app.Config;
import com.micai.fox.app.Url;
import com.micai.fox.parambean.ParamBean;
import com.micai.fox.parambean.ZhongChouBean;
import com.micai.fox.resultbean.BaseResultBean;
import com.micai.fox.resultbean.BuyResultBean;
import com.micai.fox.resultbean.ZhongChouDetailResultBean;
import com.micai.fox.util.DateUtil;
import com.micai.fox.util.LogUtil;
import com.micai.fox.util.PrefUtils;
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
    @Bind(R.id.buy_rl)
    RelativeLayout rls;
    @Bind(R.id.et_tv_money)
    TextView tv_money;
    @Bind(R.id.tv_buy_agreement)
    TextView tvBuyAgreement;
    @Bind(R.id.ll_buy)
    LinearLayout llBuy;

    private ZhongChouDetailResultBean detailResultBean;
    private BuyResultBean buyResultBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_zhong_chou);
        ButterKnife.bind(this);
        tvTitle.setText("购买众筹");
        detailResultBean = ((ZhongChouDetailResultBean) getIntent().getSerializableExtra("BEAN"));
        if (null != detailResultBean) {
            buyTvTitle.setText("                " + detailResultBean.getExecDatas().getTitle());
            buyTv1.setText("" + DateUtil.getDateToStrings(detailResultBean.getExecDatas().getEndDate()) + "或已筹金额达￥" + Tools.fomatMoney(String.valueOf(detailResultBean.getExecDatas().getAmountUp())) + "时结束众筹");
            buyTv2.setText("" + DateUtil.getDateToStrings(detailResultBean.getExecDatas().getCashDate()) + "起开始兑付");
            tv_per.setText("购买金额需为" + detailResultBean.getExecDatas().getIncrementQuota() + "元整数倍，" + detailResultBean.getExecDatas().getOneAmountDown() + "元起");
            buyEtMoney.setHint("￥" + detailResultBean.getExecDatas().getOneAmountDown() + "~" + detailResultBean.getExecDatas().getOneAmountUp());
        }
        buyEtMoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                if (charSequence.toString().trim().substring(0).equals(".")) {
//                charSequence = "￥" + charSequence;
//                buyEtMoney.setText(charSequence);
//                buyEtMoney.setSelection(charSequence.length());
//                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
//                buyEtMoney.setText("￥" + buyEtMoney.getText().toString());
                if (null == editable.toString()) {
                } else {
                    if (buyEtMoney.getText().toString().contains("￥")) {
                        tv_money.setText("" + buyEtMoney.getText().toString());
                    } else {
                        tv_money.setText("￥" + buyEtMoney.getText().toString());

                    }
                   /* if (!TextUtils.isEmpty(editable.toString())) {
                        buyIvMinus.setClickable(true);
//                    buyEtMoney.setText("￥" + editable.toString());
                    } else {
                        buyIvMinus.setClickable(false);
                    }*/
                }
            }
        });
    }

    @OnClick({R.id.tv_buy_agreement, R.id.et_tv_money, R.id.buy_pay, R.id.tv_back, R.id.buy_iv_minus, R.id.buy_iv_plus})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_buy_agreement:
                Intent intent = new Intent(BuyZhongChouActivity.this, BuyAgrementActivity.class);
                startActivity(intent);
                break;
            case R.id.et_tv_money:
                tv_money.setVisibility(View.GONE);
                buyEtMoney.setVisibility(View.VISIBLE);
                buyEtMoney.setText(tv_money.getText().toString());
                break;
            case R.id.tv_back:
                finish();
                break;
            case R.id.buy_iv_minus:
                if (TextUtils.isEmpty(buyEtMoney.getText().toString())) {
                } else {
                    int chas = new BigDecimal(tv_money.getText().toString().substring(1)).compareTo(new BigDecimal(detailResultBean.getExecDatas().getOneAmountDown()));
                    int cha = new BigDecimal(tv_money.getText().toString().substring(1)).compareTo(new BigDecimal(detailResultBean.getExecDatas().getOneAmountUp()));
                    if (chas == -1 || chas == 0 || cha == 1) {
                        String contents = "支持金额需在￥" + detailResultBean.getExecDatas().getOneAmountDown() + "~" + detailResultBean.getExecDatas().getOneAmountUp() + "之间，请重新输入";
                        Tools.showPayPopWindow(BuyZhongChouActivity.this, view, contents,0);
                    } else {
                        buyEtMoney.setVisibility(View.GONE);
                        tv_money.setVisibility(View.VISIBLE);
                        if (tv_money.getText().toString().contains(".")) {
                            String content = "支持金额需为￥" + detailResultBean.getExecDatas().getIncrementQuota() + "元的整数倍，请重新输入";
                            Tools.showPayPopWindow(BuyZhongChouActivity.this, view, content,0);
                        } else {
                            if (Integer.parseInt(tv_money.getText().toString().substring(1)) % Integer.parseInt(detailResultBean.getExecDatas().getIncrementQuota()) == 0) {
                                int minus = sub(tv_money.getText().toString().substring(1), detailResultBean.getExecDatas().getIncrementQuota());
                                tv_money.setText("￥" + minus);
                            } else {
                                String contents = "支持金额需为￥" + detailResultBean.getExecDatas().getIncrementQuota() + "元的整数倍，请重新输入";
                                Tools.showPayPopWindow(BuyZhongChouActivity.this, view, contents,0);
                            }
                        }
                    }
                }
                break;
            case R.id.buy_iv_plus:
                if (TextUtils.isEmpty(buyEtMoney.getText().toString())) {
                    int add = add("0", detailResultBean.getExecDatas().getIncrementQuota());
                    tv_money.setText("￥" + add);
                    buyEtMoney.setText("" + tv_money.getText().toString().substring(1));
                    buyEtMoney.setVisibility(View.GONE);
                    tv_money.setVisibility(View.VISIBLE);
//                    buyEtMoney.setText(tv_money.getText().toString());
                } else {
                    int cha = new BigDecimal(tv_money.getText().toString().substring(1)).compareTo(new BigDecimal(detailResultBean.getExecDatas().getOneAmountUp()));
                    if (cha == 1 || cha == 0) {
//                    支持金额需在￥XX~XXX之间，请重新输入
                        String content = "支持金额需在￥" + detailResultBean.getExecDatas().getOneAmountDown() + "~" + detailResultBean.getExecDatas().getOneAmountUp() + "之间，请重新输入";
                        Tools.showPayPopWindow(BuyZhongChouActivity.this, view, content,0);
                    } else {
                        buyEtMoney.setVisibility(View.GONE);
                        tv_money.setVisibility(View.VISIBLE);
                        if (tv_money.getText().toString().contains(".")) {
                            String content = "支持金额需为￥" + detailResultBean.getExecDatas().getIncrementQuota() + "元的整数倍，请重新输入";
                            Tools.showPayPopWindow(BuyZhongChouActivity.this, view, content,0);
                        } else {
                            if (Integer.parseInt(tv_money.getText().toString().substring(1)) % Integer.parseInt(detailResultBean.getExecDatas().getIncrementQuota()) == 0) {
                                int add = add(tv_money.getText().toString().substring(1), "" + detailResultBean.getExecDatas().getIncrementQuota());
                                tv_money.setText("￥" + add);
                            } else {
                                String content = "支持金额需为￥" + detailResultBean.getExecDatas().getIncrementQuota() + "元的整数倍，请重新输入";
                                Tools.showPayPopWindow(BuyZhongChouActivity.this, view, content,0);
                            }
                        }
                    }
                }
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
            Tools.showPayPopWindow(this, view, content,0);
            return;
        }
        if (!buyCk.isChecked()) {
            content = "购买众筹需先阅读并同意《众筹购买协议》";
            Tools.showPayPopWindow(this, view, content,0);
            return;
        }
        if (null == PrefUtils.getString(Config.getInstance().getmContext(), "SESSIONID", null) || TextUtils.isEmpty(PrefUtils.getString(Config.getInstance().getmContext(), "SESSIONID", ""))) {
            Intent intent = new Intent(this, LoginActivity.class);
            intent.putExtra("TYPE", 1);
            startActivity(intent);
        } else {
            if (Integer.parseInt(tv_money.getText().toString().substring(1)) % Integer.parseInt(detailResultBean.getExecDatas().getIncrementQuota()) == 0) {
                buyZhongChou(view);
            } else {
                String contents = "支持金额需为￥" + detailResultBean.getExecDatas().getIncrementQuota() + "元的整数倍，请重新输入";
                Tools.showPayPopWindow(BuyZhongChouActivity.this, view, contents,0);
            }

        }
    }

    private void buyZhongChou(final View view) {
        paramBean = new ParamBean();
        paramData = new ParamBean.ParamData();
        paramData.setCrowdfundingId("" + detailResultBean.getExecDatas().getCrowdfundingId());
        paramData.setPurchaseAmount("" + tv_money.getText().toString().substring(1));
        paramBean.setParamData(paramData);
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
                    buyResultBean = new Gson().fromJson(response, BuyResultBean.class);
                    if (buyResultBean.isExecResult()) {
                        ZhongChouBean bean = new ZhongChouBean(buyResultBean.getExecDatas().getOrderId(), detailResultBean.getExecDatas().getTitle(), tv_money.getText().toString().substring(1));
                        Intent intent = new Intent(BuyZhongChouActivity.this, PayActivity.class);
                        intent.putExtra("BEAN", bean);
                        startActivity(intent);
                        finish();
                    } else {
                        Tools.showPayPopWindow(BuyZhongChouActivity.this, view, "" + buyResultBean.getExecMsg(),0);
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
    public int add(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.add(b2).intValue();
    }

    /**
     * 提供精确的减法运算。
     *
     * @param v1 被减数
     * @param v2 减数
     * @return 两个参数的差
     */
    public int sub(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.subtract(b2).intValue();
    }


}
