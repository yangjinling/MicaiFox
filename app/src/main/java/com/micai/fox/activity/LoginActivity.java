package com.micai.fox.activity;

import android.content.Intent;
import android.media.TimedText;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.InputType;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.micai.fox.R;
import com.micai.fox.app.Config;
import com.micai.fox.base.BaseActivity;
import com.micai.fox.util.ExitAppUtils;
import com.micai.fox.util.PrefUtils;

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
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    loginEtPhone.setHintTextColor(getResources().getColor(R.color.gray));
                    loginEtPhone.setHint("请输入手机号");
                    loginBtnLogin.setClickable(true);
                    break;
                case 1:
                    loginEtPassword.setHintTextColor(getResources().getColor(R.color.gray));
                    loginEtPassword.setHint("请输入密码");
                    loginEtPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    loginBtnLogin.setClickable(true);
                    break;
                case 2:
                    loginEtPhone.setHintTextColor(getResources().getColor(R.color.gray));
                    loginEtPhone.setHint("请输入手机号");
                    loginEtPassword.setHintTextColor(getResources().getColor(R.color.gray));
                    loginEtPassword.setHint("请输入密码");
                    loginEtPhone.requestFocus();
                    loginBtnLogin.setClickable(true);
                    break;

            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ExitAppUtils.getInstance().addActivity(this);
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
                switch (canLogin()) {
                    case 0:
                        loginEtPhone.setHintTextColor(getResources().getColor(R.color.red));
                        loginEtPhone.setHint("请输入手机号");
                        loginBtnLogin.setClickable(false);
                        mHandler.sendEmptyMessageDelayed(0, 3000);
                        break;
                    case 1:
                        loginEtPassword.setHintTextColor(getResources().getColor(R.color.red));
                        loginEtPassword.setHint("请输入密码");
                        loginBtnLogin.setClickable(false);
                        mHandler.sendEmptyMessageDelayed(1, 3000);
                        break;
                    case 2:
                        //输入类型为普通文本
                        loginEtPhone.setText("");
                        loginEtPassword.setText("");
                        loginEtPhone.setHintTextColor(getResources().getColor(R.color.red));
                        loginEtPassword.setHintTextColor(getResources().getColor(R.color.red));
                        loginEtPhone.setHint("手机号或密码错误，请重新输入！");
                        loginEtPassword.setHint("手机号或密码错误，请重新输入！");
                        loginBtnLogin.setClickable(false);
                        mHandler.sendEmptyMessageDelayed(2, 3000);
                        break;
                    case 3:
                        intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        PrefUtils.setBoolean(Config.getInstance().getmContext(), "ISFIRST", true);
                        break;
                }
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

    private int canLogin() {
        String phone = loginEtPhone.getText().toString().trim();
        String pass = loginEtPassword.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            return 0;
        } else if (TextUtils.isEmpty(pass)) {
            return 1;
        } else if (phone.length() != 11 || pass.length() < 6) {
            return 2;
        } else {
            return 3;
        }

    }
}
