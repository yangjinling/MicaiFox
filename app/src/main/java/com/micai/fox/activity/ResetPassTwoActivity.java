package com.micai.fox.activity;

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

public class ResetPassTwoActivity extends AppCompatActivity {

    @Bind(R.id.tv_back)
    TextView tvBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_notify)
    TextView tvNotify;
    @Bind(R.id.rl)
    RelativeLayout rl;
    @Bind(R.id.reset_et_password)
    EditText resetEtPassword;
    @Bind(R.id.reset_et_password_agin)
    EditText resetEtPasswordAgin;
    @Bind(R.id.regset_ll_et_two)
    LinearLayout regsetLlEtTwo;
    @Bind(R.id.reset_btn_reset)
    Button resetBtnReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pass_two);
        ButterKnife.bind(this);
        tvTitle.setText("重置密码");
    }

    @OnClick({R.id.tv_back, R.id.reset_btn_reset})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.reset_btn_reset:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
