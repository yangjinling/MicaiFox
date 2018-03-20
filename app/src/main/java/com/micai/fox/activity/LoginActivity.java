package com.micai.fox.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.igexin.sdk.PushManager;
import com.micai.fox.R;
import com.micai.fox.app.Config;
import com.micai.fox.app.Url;
import com.micai.fox.base.BaseActivity;
import com.micai.fox.parambean.NotiBean;
import com.micai.fox.resultbean.BaseResultBean;
import com.micai.fox.resultbean.LoginResultBean;
import com.micai.fox.util.ExitAppUtils;
import com.micai.fox.util.LogUtil;
import com.micai.fox.util.PrefUtils;
import com.micai.fox.util.Tools;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

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
//                    loginEtPhone.setFilters(new InputFilter[]{new InputFilter.LengthFilter(15)});//20
//                    loginEtPassword.setFilters(new InputFilter[]{new InputFilter.LengthFilter(15)});//20
                    loginEtPhone.requestFocus();
                    hideSoftInput(loginEtPhone);
                    hideSoftInput(loginEtPassword);
                    loginEtPassword.setText("");
                    loginEtPhone.setText("");
                    loginEtPhone.setHintTextColor(getResources().getColor(R.color.gray));
                    loginEtPhone.setHint("请输入手机号");
                    loginEtPassword.setHintTextColor(getResources().getColor(R.color.gray));
                    loginEtPassword.setHint("请输入密码");
                    loginBtnLogin.setClickable(true);
                    break;

            }
        }
    };
    private int type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ExitAppUtils.getInstance().addActivity(this);
        ButterKnife.bind(this);
        tvTitle.setText("登录");
        type = getIntent().getIntExtra("TYPE", 0);
    }

    @Override
    protected void onStop() {
        clearMessage();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    private void clearMessage() {
        if (null != mHandler) {
            mHandler.removeMessages(0);
            mHandler.removeMessages(1);
            mHandler.removeMessages(2);
            mHandler = null;
        }
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
//                        loginEtPhone.setFilters(new InputFilter[]{new InputFilter.LengthFilter(15)});//20
//                        loginEtPassword.setFilters(new InputFilter[]{new InputFilter.LengthFilter(15)});//20
                        loginEtPhone.setHintTextColor(getResources().getColor(R.color.red));
                        loginEtPassword.setHintTextColor(getResources().getColor(R.color.red));
                        loginEtPhone.setHint("手机号或密码错误!");
                        loginEtPassword.setHint("手机号或密码错误!");
                        loginBtnLogin.setClickable(false);
                        mHandler.sendEmptyMessageDelayed(2, 3000);
                        break;
                    case 3:
                        login(loginEtPhone.getText().toString().trim(), loginEtPassword.getText().toString().trim());
                        break;
                }
                break;
            case R.id.login_tv_regist:
                finish();
                intent = new Intent(LoginActivity.this, RegistActivity.class);
                startActivity(intent);
                break;
            case R.id.login_tv_forgetpass:
                intent = new Intent(LoginActivity.this, ResetPassActivity.class);
                startActivity(intent);
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

    /**
     * 校验手机号与密码----登录
     */
    private void login(String username, String password) {
//        url(String.format(Url.WEB_CONDITION_QUERY, sessionId))
        OkHttpUtils.post().url(Url.WEB_LOGIN)
                .addParams("username", username)
                .addParams("password", password)
                .addParams("deviceSys", "安卓")
                .addParams("mobileLogin", "true").build()
                .execute(new StringCallback() {

                    private LoginResultBean loginResultBean;

                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) throws Exception {
                        LogUtil.e("yjl", "login---response>>" + response);
                        if (Tools.isGoodJson(response)) {
                            loginResultBean = new Gson().fromJson(response, LoginResultBean.class);
                            if (loginResultBean.isExecResult()) {
                                Config.getInstance().setSessionId(loginResultBean.getExecDatas().getSessionid());
                                Config.getInstance().setPhone(loginResultBean.getExecDatas().getUser().getPhone());
                                PrefUtils.setBoolean(Config.getInstance().getmContext(), "ISFIRST", true);
                                PrefUtils.setString(Config.getInstance().getmContext(), "SESSIONID", loginResultBean.getExecDatas().getSessionid());
                                PrefUtils.setString(Config.getInstance().getmContext(), "PHONE", loginResultBean.getExecDatas().getUser().getPhone());
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                Config.getInstance().setClientId(loginResultBean.getExecDatas().getId());
                                PushManager.getInstance().bindAlias(getApplicationContext(), loginResultBean.getExecDatas().getId());
//                                startActivity(intent);
//                                ExitAppUtils.getInstance().finishActivity(IndexActivity.class);
//                                ExitAppUtils.getInstance().finishActivity(MainActivity.class);
                                checkNotice();
                                if (type == 0) {
                                    ExitAppUtils.getInstance().finishAllActivities();
                                    intent.putExtra("TYPE", 1);
                                    startActivity(intent);
                                    Config.getInstance().setSet(false);
                                    Config.getInstance().setLoginFromBuy(false);
                                }
//                                众筹过来
                                Config.getInstance().setLoginFromBuy(true);
                                Config.getInstance().setSet(true);
                                Config.getInstance().setJin(false);
                                finish();
                            } else {
                                loginEtPassword.setText("");
                                loginEtPhone.setText("");
                                hideSoftInput(loginEtPhone);
                                hideSoftInput(loginEtPassword);
                                loginEtPhone.setHintTextColor(getResources().getColor(R.color.red));
                                loginEtPassword.setHintTextColor(getResources().getColor(R.color.red));
                                loginEtPhone.setHint("" + loginResultBean.getExecMsg());
                                loginEtPassword.setHint("" + loginResultBean.getExecMsg());
                                loginBtnLogin.setClickable(false);
                                mHandler.sendEmptyMessageDelayed(2, 3000);
                            }
                        } else {
                            Config.getInstance().setJin(true);
                        }
                    }
                });
    }

    /**
     * 隐藏软键盘输入法
     *
     * @param v
     */
    private void hideSoftInput(View v) {
//        if(TextUtils.isEmpty(writeComment.getText())){//清空写回复信息，准备评论楼主
//            pid="";
//            writeComment.setHint(getResources().getString(R.string.hint_write_comment));
//        }
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    /**
     * 显示软键盘输入法
     *
     * @param v
     */
    private void showSoftInput(View v) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(v, InputMethodManager.SHOW_FORCED);
    }

    private void checkNotice() {
        OkHttpUtils.post().
                url(String.format(Url.WEN_CHECK_NOTICE, Config.getInstance().getSessionId()))
                .build().execute(new StringCallback() {

            private BaseResultBean noticeResultBean;

            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) throws Exception {
                LogUtil.e("YJL", "checkNoti==" + response);
                noticeResultBean = new Gson().fromJson(response, BaseResultBean.class);
                if (noticeResultBean.isExecResult()) {
                    NotiBean bean = new NotiBean();
                    if (Integer.parseInt(noticeResultBean.getExecDatas()) > 0) {
                        LogUtil.e("YJL", "checkNoti==" + Integer.parseInt(noticeResultBean.getExecDatas()));
                        bean.setHaveN(true);
                        EventBus.getDefault().post(bean);
                    } else {
                        bean.setHaveN(false);
                        EventBus.getDefault().post(bean);
                    }
                }
            }
        });
    }
}
