package com.micai.fox.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.micai.fox.R;
import com.micai.fox.app.Config;
import com.micai.fox.app.Url;
import com.micai.fox.parambean.ParamBean;
import com.micai.fox.parambean.ZhongChouBean;
import com.micai.fox.resultbean.MyZhongChouOrderResultBean;
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

/*众筹订单详情界面*/
public class ZhongChouOrderDetailActivity extends AppCompatActivity {

    @Bind(R.id.tv_back)
    TextView tvBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.rl)
    RelativeLayout rl;
    @Bind(R.id.btn_zhongchou_detail_order_pay)
    Button btnZhongchouDetailOrderPay;
    @Bind(R.id.item_zhongchou_tv_orderid)
    TextView detailZhongchouTvOrderid;
    @Bind(R.id.detail_zhongchou_tv_orderstate)
    TextView detailZhongchouTvOrderstate;
    @Bind(R.id.zhongchou_detail_tv_talk)
    TextView zhongchouDetailTvTalk;
    @Bind(R.id.zhongchou_order_detail_tv1)
    TextView zhongchouDetailTv1;
    @Bind(R.id.zhongchou_order_detail_tv2)
    TextView zhongchouDetailTv2;
    @Bind(R.id.zhongchou_detail_tv_status)
    TextView zhongchouDetailTvStatus;
    @Bind(R.id.orderdetail_money_pay)
    LinearLayout orderdetailMoneyPay;
    @Bind(R.id.orderdetail_money_benifate)
    LinearLayout orderdetailMoneyBenifate;
    @Bind(R.id.orderdetail_money_duifu)
    LinearLayout orderdetailMoneyDuifu;
    @Bind(R.id.orderdetail_ll_xiadan)
    LinearLayout orderdetailLlXiadan;
    @Bind(R.id.orderdetail_ll_pay)
    LinearLayout orderdetailLlPay;
    @Bind(R.id.orderdetail_ll_bank)
    LinearLayout orderdetailLlBank;
    @Bind(R.id.orderdetail_ll_way)
    LinearLayout orderdetailLlWay;
    @Bind(R.id.order_tv_money_pay)
    TextView orderTvMoneyPay;
    @Bind(R.id.order_tv_money_benifate)
    TextView orderTvMoneyBenifate;
    @Bind(R.id.order_tv_money_duifu)
    TextView orderTvMoneyDuifu;
    @Bind(R.id.order_tv_time_xiadan)
    TextView orderTvTimeXiadan;
    @Bind(R.id.order_tv_time_pay)
    TextView orderTvTimePay;
    @Bind(R.id.order_tv_money_pay_title)
    TextView orderTvMoneyPayTitle;
    private View view;
    private String orderId;
    private int orderStatus;
    private MyZhongChouOrderResultBean myZhongChouOrderResultBean;
    private int zhongchouStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhong_chou_order_detail);
        ButterKnife.bind(this);
        tvTitle.setText("众筹订单详情");
        orderId = getIntent().getStringExtra("orderId");
        orderStatus = getIntent().getIntExtra("orderStatus", 0);
        zhongchouStatus = getIntent().getIntExtra("zhongchouStatus", 0);
        getZhongChouOrderDetail(orderId);
    }

    private void initCrowdingStatus() {
        switch (zhongchouStatus) {
            case 1://众筹中
                zhongchouDetailTvStatus.setText("众筹中");
                break;
            case 2:
            case 3:
            case 4://已结束
                zhongchouDetailTvStatus.setText("已结束");
                break;
            case 5://已披露
            case 6:
                zhongchouDetailTvStatus.setText("已披露");
                break;
            case 7:
                //已兑付
                zhongchouDetailTvStatus.setText("已兑付");
                break;
            case 9:
                //流标
                zhongchouDetailTvStatus.setText("流标");
                break;
        }

    }

    private void initStatus() {
        switch (orderStatus) {
            case 0:
                //待支付
                btnZhongchouDetailOrderPay.setVisibility(View.VISIBLE);
                orderTvMoneyPayTitle.setText("需支付金额");
                zhongchouDetailTv1.setText("" + DateUtil.getDateToStrings(myZhongChouOrderResultBean.getExecDatas().getEndDate()) + "或已筹金额达￥" + Tools.fomatMoney(String.valueOf(myZhongChouOrderResultBean.getExecDatas().getAmountDown())) + "时结束众筹");
                zhongchouDetailTv2.setText("" + DateUtil.getDateToStrings(myZhongChouOrderResultBean.getExecDatas().getCashDate()) + "起开始兑付");

                break;
            case 1:
                //已支付
                btnZhongchouDetailOrderPay.setVisibility(View.GONE);
                detailZhongchouTvOrderstate.setText("已支付");
                orderTvMoneyPayTitle.setText("支付金额");
                orderdetailLlXiadan.setVisibility(View.VISIBLE);
                orderdetailLlPay.setVisibility(View.VISIBLE);
                orderdetailLlBank.setVisibility(View.VISIBLE);
                zhongchouDetailTv1.setText("" + DateUtil.getDateToStrings(myZhongChouOrderResultBean.getExecDatas().getEndDate()) + "或已筹金额达￥" + Tools.fomatMoney(String.valueOf(myZhongChouOrderResultBean.getExecDatas().getAmountDown())) + "时结束众筹");
                zhongchouDetailTv2.setText("" + DateUtil.getDateToStrings(myZhongChouOrderResultBean.getExecDatas().getCashDate()) + "起开始兑付");

                break;
            case 3:
                btnZhongchouDetailOrderPay.setVisibility(View.GONE);
                detailZhongchouTvOrderstate.setText("已退款");
                orderTvMoneyPayTitle.setText("支付金额");
                orderdetailLlXiadan.setVisibility(View.VISIBLE);
                orderdetailLlPay.setVisibility(View.VISIBLE);
                orderdetailLlBank.setVisibility(View.VISIBLE);
                zhongchouDetailTv1.setText("已于" + DateUtil.getDateToStrings(myZhongChouOrderResultBean.getExecDatas().getEndDate()) + "结束众筹");
                zhongchouDetailTv2.setText("项目未达成，支付金额原路退回。");
                break;
            case 4:
                btnZhongchouDetailOrderPay.setVisibility(View.GONE);
                orderTvMoneyPayTitle.setText("需支付金额");
                detailZhongchouTvOrderstate.setText("已过期");
                zhongchouDetailTv1.setText("已于" + DateUtil.getDateToStrings(myZhongChouOrderResultBean.getExecDatas().getEndDate()) + "结束众筹");
                zhongchouDetailTv2.setText("" + DateUtil.getDateToStrings(myZhongChouOrderResultBean.getExecDatas().getCashDate()) + "起已兑付");

                break;
            case 7:
                //已兑换
                btnZhongchouDetailOrderPay.setVisibility(View.GONE);
                detailZhongchouTvOrderstate.setText("已兑付");
                orderTvMoneyPayTitle.setText("支付金额");
                orderdetailLlXiadan.setVisibility(View.VISIBLE);
                orderdetailMoneyBenifate.setVisibility(View.VISIBLE);
                orderdetailMoneyDuifu.setVisibility(View.VISIBLE);
                orderdetailLlPay.setVisibility(View.VISIBLE);
                orderdetailLlWay.setVisibility(View.VISIBLE);
                orderdetailLlBank.setVisibility(View.VISIBLE);
                zhongchouDetailTv1.setText("已于" + DateUtil.getDateToStrings(myZhongChouOrderResultBean.getExecDatas().getEndDate()) + "结束众筹");
                zhongchouDetailTv2.setText("" + DateUtil.getDateToStrings(myZhongChouOrderResultBean.getExecDatas().getCashDate()) + "起已兑付");
                BigDecimal cha = new BigDecimal("0" + myZhongChouOrderResultBean.getExecDatas().getCashAmount()).subtract(new BigDecimal("0" + myZhongChouOrderResultBean.getExecDatas().getPurchaseAmount())).setScale(2);
                if (cha.compareTo(new BigDecimal(0)) == 1) {
                    orderTvMoneyBenifate.setText("+ ¥ " + cha);
                } else if ((cha.compareTo(new BigDecimal(0)) == -1)){
                    orderTvMoneyBenifate.setText("- ¥ " +cha.toString().substring(1));
                }else {
                    orderTvMoneyBenifate.setText("¥ " +cha);
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    private ParamBean paramBean;
    private ParamBean.ParamData paramData;

    private void getZhongChouOrderDetail(String orderId) {
        paramBean = new ParamBean();
        paramData = new ParamBean.ParamData();
        paramData.setOrderId(orderId);
        paramBean.setParamData(paramData);
        OkHttpUtils.postString()
                .mediaType(MediaType.parse(Url.CONTENT_TYPE))
                .url(String.format(Url.WEB_MINE_ZHONGCHOU_DETAIL, Config.getInstance().getSessionId()))
                .content(new Gson().toJson(paramBean))
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) throws Exception {
                LogUtil.e("yjl", "我的众筹订单-data" + response);
                if (Tools.isGoodJson(response)) {
                    myZhongChouOrderResultBean = new Gson().fromJson(response, MyZhongChouOrderResultBean.class);
                    if (myZhongChouOrderResultBean.isExecResult()) {
                        detailZhongchouTvOrderid.setText("" + myZhongChouOrderResultBean.getExecDatas().getOrderId());
                        zhongchouDetailTvTalk.setText("                " + myZhongChouOrderResultBean.getExecDatas().getTitle());
                        orderStatus = myZhongChouOrderResultBean.getExecDatas().getOrderStatus();
                        zhongchouStatus = myZhongChouOrderResultBean.getExecDatas().getCrowdfundingStatus();
                        initCrowdingStatus();
                        initStatus();
                        String date = DateUtil.getDateToString(myZhongChouOrderResultBean.getExecDatas().getCreateDate());
                        orderTvTimeXiadan.setText("" + date);
                        orderTvMoneyPay.setText("￥" + myZhongChouOrderResultBean.getExecDatas().getPurchaseAmount());
                        orderTvMoneyDuifu.setText("￥" + myZhongChouOrderResultBean.getExecDatas().getCashAmount());
                        if (null != myZhongChouOrderResultBean.getExecDatas().getPayDate() && !TextUtils.isEmpty(myZhongChouOrderResultBean.getExecDatas().getPayDate())) {
                            String datePay = DateUtil.getDateToString(Long.parseLong(myZhongChouOrderResultBean.getExecDatas().getPayDate()));
                            orderTvTimePay.setText("" + datePay);
                        }


                    }
                } else {
                    //TODO 状态禁用
                    Config.getInstance().setJin(true);
                }
            }
        });

    }

    @OnClick({R.id.btn_zhongchou_detail_order_pay, R.id.tv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.btn_zhongchou_detail_order_pay:
                ZhongChouBean bean = new ZhongChouBean(myZhongChouOrderResultBean.getExecDatas().getOrderId(), myZhongChouOrderResultBean.getExecDatas().getTitle(), "" + myZhongChouOrderResultBean.getExecDatas().getPurchaseAmount());
                Intent intent = new Intent(ZhongChouOrderDetailActivity.this, PayActivity.class);
                intent.putExtra("BEAN", bean);
                startActivity(intent);
                break;
        }
    }
}
