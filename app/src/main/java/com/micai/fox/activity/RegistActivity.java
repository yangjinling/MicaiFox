package com.micai.fox.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.micai.fox.R;
import com.micai.fox.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*注册页面*/
public class RegistActivity extends BaseActivity {


    @Bind(R.id.tv_back)
    TextView tvBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_notify)
    TextView tvNotify;
    @Bind(R.id.rl)
    RelativeLayout rl;
    @Bind(R.id.regist_et_phone)
    EditText registEtPhone;
    @Bind(R.id.regist_et_code)
    EditText registEtCode;
    @Bind(R.id.regist_btn_code)
    Button registBtnCode;
    @Bind(R.id.regist_ll_et)
    LinearLayout registLlEt;
    @Bind(R.id.regist_ck)
    CheckBox registCk;
    @Bind(R.id.regist_ll_ck)
    LinearLayout registLlCk;
    @Bind(R.id.regist_btn_next)
    Button registBtnNext;
    @Bind(R.id.regist_tv_have)
    TextView registTvHave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        ButterKnife.bind(this);
        tvTitle.setText("注册");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.tv_back, R.id.regist_btn_code, R.id.regist_btn_next, R.id.regist_tv_have})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.regist_btn_code:
                //获取验证码

                break;
            case R.id.regist_btn_next:
                //下一步
                intent = new Intent(RegistActivity.this, RegistTwoActivity.class);
                startActivity(intent);
                break;
            case R.id.regist_tv_have:
                intent = new Intent(RegistActivity.this, LoginActivity.class);
                startActivity(intent);
                //已有账号

                break;
        }
    }
}
