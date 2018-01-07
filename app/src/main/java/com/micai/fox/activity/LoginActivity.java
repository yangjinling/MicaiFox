package com.micai.fox.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.micai.fox.R;
import com.micai.fox.base.BaseActivity;
import com.micai.fox.util.ExitAppUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*登陆页面*/
public class LoginActivity extends BaseActivity {
    @Bind(R.id.tv_back)
    TextView tvBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.rl)
    RelativeLayout rl;
    @Bind(R.id.login_et_phone)
    EditText loginEtPhone;
    @Bind(R.id.login_et_password)
    EditText loginEtPassword;
    @Bind(R.id.login_btn_login)
    Button loginBtnLogin;
    @Bind(R.id.login_tv_regist)
    TextView loginTvRegist;
    @Bind(R.id.login_tv_forgetpass)
    TextView loginTvForgetpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        tvTitle.setText("登录");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.tv_back, R.id.tv_title, R.id.rl, R.id.login_btn_login, R.id.login_tv_regist, R.id.login_tv_forgetpass})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.login_btn_login:
                //登录
                break;
            case R.id.login_tv_regist:
                intent = new Intent(LoginActivity.this, RegistActivity.class);
                startActivity(intent);
                ExitAppUtils.getInstance().addActivity(this);
                break;
            case R.id.login_tv_forgetpass:
                intent = new Intent(LoginActivity.this, ResetPassActivity.class);
                startActivity(intent);
                ExitAppUtils.getInstance().addActivity(this);
                break;
        }
    }
}
