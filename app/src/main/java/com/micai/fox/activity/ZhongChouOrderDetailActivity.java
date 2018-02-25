package com.micai.fox.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.micai.fox.R;
import com.micai.fox.app.Config;
import com.micai.fox.app.Url;
import com.micai.fox.parambean.ParamBean;
import com.micai.fox.resultbean.MyZhongChouOrderResultBean;
import com.micai.fox.resultbean.ZhongChouDetailResultBean;
import com.micai.fox.util.LogUtil;
import com.micai.fox.util.Tools;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

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
    @Bind(R.id.zhongchou_detail_tv1)
    TextView zhongchouDetailTv1;
    @Bind(R.id.zhongchou_detail_tv2)
    TextView zhongchouDetailTv2;
    private View view;
    private String orderId;
    private int type;
    private MyZhongChouOrderResultBean myZhongChouOrderResultBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhong_chou_order_detail);
        ButterKnife.bind(this);
        tvTitle.setText("众筹订单详情");
        orderId = getIntent().getStringExtra("orderId");
        type = getIntent().getIntExtra("type", 0);
        switch (type) {
            case 0:
                //全部
                btnZhongchouDetailOrderPay.setVisibility(View.GONE);
                break;
            case 1:
                //待支付
                btnZhongchouDetailOrderPay.setVisibility(View.VISIBLE);
                break;
            case 2:
                //已支付
                btnZhongchouDetailOrderPay.setVisibility(View.GONE);
                detailZhongchouTvOrderstate.setText("已支付");
                break;
            case 3:
                //已兑换
                btnZhongchouDetailOrderPay.setVisibility(View.GONE);
                detailZhongchouTvOrderstate.setText("已兑换");

                break;
        }
        getZhongChouOrderDetail(orderId);
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
                    detailZhongchouTvOrderid.setText(""+myZhongChouOrderResultBean.getExecDatas().getOrderId());
                    zhongchouDetailTvTalk.setText(""+ myZhongChouOrderResultBean.getExecDatas().getTitle());
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
                Intent intent = new Intent(ZhongChouOrderDetailActivity.this, PayActivity.class);
                startActivity(intent);
                break;
        }
    }
}
