package com.micai.fox.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.micai.fox.R;
import com.micai.fox.parambean.ZhongChouBean;
import com.micai.fox.resultbean.ZhongChouDetailResultBean;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
        payTvMoney.setText(""+bean.getMoney());
        payBtnPay.setText("支付￥"+bean.getMoney());
    }

    @OnClick({R.id.tv_back, R.id.pay_btn_pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.pay_btn_pay:
                break;
        }
    }

}
