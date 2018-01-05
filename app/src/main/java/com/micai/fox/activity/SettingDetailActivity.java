package com.micai.fox.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.micai.fox.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingDetailActivity extends AppCompatActivity {

    @Bind(R.id.tv_back)
    TextView tvBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_notify)
    TextView tvNotify;
    @Bind(R.id.rl)
    RelativeLayout rl;
    private int type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_detail);
        ButterKnife.bind(this);
        type = getIntent().getIntExtra("VALUE", 0);
        initView(type);

    }

    private void initView(int type) {
        rl.setVisibility(View.VISIBLE);
        tvBack.setVisibility(View.VISIBLE);
        switch (type) {
            case 0:
                tvNotify.setVisibility(View.VISIBLE);
                tvNotify.setText("编辑");
                tvTitle.setText("收款账户");
                break;
            case 1:
                tvTitle.setText("修改手机号");
                break;
            case 2:
                tvTitle.setText("修改密码");
                break;
            case 3:
                tvTitle.setText("意见反馈");
                break;
            case 4:
                tvTitle.setText("用户协议");
                break;
            case 5:
                tvTitle.setText("关于我们");
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.tv_back, R.id.tv_notify})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.tv_notify:
                break;
        }
    }
}
