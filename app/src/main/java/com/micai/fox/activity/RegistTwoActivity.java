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

public class RegistTwoActivity extends AppCompatActivity {

    @Bind(R.id.tv_back)
    TextView tvBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_notify)
    TextView tvNotify;
    @Bind(R.id.rl)
    RelativeLayout rl;
    @Bind(R.id.regist_et_nick)
    EditText registEtNick;
    @Bind(R.id.regist_et_password)
    EditText registEtPassword;
    @Bind(R.id.regist_et_password_agin)
    EditText registEtPasswordAgin;
    @Bind(R.id.regist_ll_et_two)
    LinearLayout registLlEtTwo;
    @Bind(R.id.regist_btn_regist)
    Button registBtnRegist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist_two);
        ButterKnife.bind(this);
        tvTitle.setText("注册");
    }

    @OnClick({R.id.tv_back, R.id.regist_btn_regist})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.regist_btn_regist:
                //确认注册
                break;
        }
    }

    @Override
    protected void onDestroy() {
        ButterKnife.unbind(this);
        super.onDestroy();
    }
}
