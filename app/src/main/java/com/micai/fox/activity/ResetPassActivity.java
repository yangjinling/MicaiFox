package com.micai.fox.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.micai.fox.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ResetPassActivity extends AppCompatActivity {

    @Bind(R.id.tv_back)
    TextView tvBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_notify)
    TextView tvNotify;
    @Bind(R.id.rl)
    RelativeLayout rl;
    @Bind(R.id.reset_et_phone)
    EditText resetEtPhone;
    @Bind(R.id.reset_et_code)
    EditText resetEtCode;
    @Bind(R.id.reset_btn_code)
    Button resetBtnCode;
    @Bind(R.id.reset_ll_etone)
    LinearLayout resetLlEtone;
    @Bind(R.id.reset_btn_next)
    Button resetBtnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pass);
        ButterKnife.bind(this);
        tvTitle.setText("重置密码");
    }

    @OnClick({R.id.tv_back, R.id.reset_btn_code, R.id.reset_btn_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.reset_btn_code:
                //获取验证码
                break;
            case R.id.reset_btn_next:
                //下一步
                Intent intent = new Intent(ResetPassActivity.this, ResetPassTwoActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
