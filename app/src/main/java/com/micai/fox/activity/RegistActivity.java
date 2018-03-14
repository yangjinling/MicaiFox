package com.micai.fox.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.micai.fox.R;
import com.micai.fox.app.Url;
import com.micai.fox.base.BaseActivity;
import com.micai.fox.parambean.ParamBean;
import com.micai.fox.resultbean.BaseResultBean;
import com.micai.fox.util.ExitAppUtils;
import com.micai.fox.util.LogUtil;
import com.micai.fox.util.Tools;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.MediaType;

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
    @Bind(R.id.regist_tv_agreement)
    TextView registTvAgreement;
    private int second = 60;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 5://验证码倒计时
                    if (null != registBtnCode)
                        registBtnCode.setText(--second + "秒后可重发送");
                    if (second >= 1) {
                        mHandler.sendEmptyMessageDelayed(5, 1000);
                    } else {
                        registBtnCode.setBackground(getResources().getDrawable(R.drawable.vertifystyle));
                        registBtnCode.setClickable(true);
                        registBtnCode.setText("重新获取验证码");
                        second = 60;
                    }
                    break;
                case 0:
                    registEtPhone.setHintTextColor(getResources().getColor(R.color.gray));
                    registEtPhone.setHint("请输入手机号");
                    registBtnCode.setClickable(true);
                    registBtnNext.setClickable(true);
                    break;
                case 1:
                    registEtPhone.requestFocus();
                    registEtPhone.setHintTextColor(getResources().getColor(R.color.gray));
                    registEtPhone.setHint("请输入手机号");
                    registBtnCode.setClickable(true);
                    registBtnNext.setClickable(true);
                    break;
                case 2:
                    registEtCode.setHintTextColor(getResources().getColor(R.color.gray));
                    registEtCode.setHint("请输入验证码");
                    registBtnNext.setClickable(true);
                    break;
                case 3:
                    registEtCode.setHintTextColor(getResources().getColor(R.color.gray));
                    registEtCode.setHint("请输入验证码");
                    registBtnNext.setClickable(true);
                    break;
                case 4:
                    registEtPhone.setHintTextColor(getResources().getColor(R.color.gray));
                    registEtPhone.setHint("请输入手机号");
                    registBtnCode.setClickable(true);
                    break;
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        ButterKnife.bind(this);
        ExitAppUtils.getInstance().addActivity(this);
        tvTitle.setText("注册");

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
        if (null!=mHandler){
        mHandler.removeMessages(0);
        mHandler.removeMessages(1);
        mHandler.removeMessages(2);
        mHandler.removeMessages(3);
        mHandler.removeMessages(4);
        mHandler = null;}
    }

    @OnClick({R.id.regist_tv_agreement, R.id.tv_back, R.id.regist_btn_code, R.id.regist_btn_next, R.id.regist_tv_have})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.regist_btn_code:
                //获取验证码
                String phone = registEtPhone.getText().toString().trim();
                if (!TextUtils.isEmpty(phone) && phone.length() == 11 && "1".equals(phone.substring(0, 1))) {
                    getCode(phone);
                } else {
                    if (TextUtils.isEmpty(phone)) {
                        registEtPhone.setHintTextColor(getResources().getColor(R.color.red));
                        registEtPhone.setHint("请输入手机号");
                        registBtnCode.setClickable(false);
                        mHandler.sendEmptyMessageDelayed(4, 3000);
                    } else {
                        registEtPhone.setText("");
                        registEtPhone.setHintTextColor(getResources().getColor(R.color.red));
                        registEtPhone.setHint("请输入正确的手机号");
                        registBtnCode.setClickable(false);
                        mHandler.sendEmptyMessageDelayed(4, 3000);
                    }
                }
                break;
            case R.id.regist_btn_next:
                //下一步
                switch (canNext()) {
                    case 0:
                        registEtPhone.setHintTextColor(getResources().getColor(R.color.red));
                        registEtPhone.setHint("请输入手机号");
                        registBtnNext.setClickable(false);
                        mHandler.sendEmptyMessageDelayed(0, 3000);
                        break;
                    case 1:
                        registEtPhone.setText("");
                        registEtPhone.setHintTextColor(getResources().getColor(R.color.red));
                        registEtPhone.setHint("请输入正确的手机号");
                        registBtnNext.setClickable(false);
                        mHandler.sendEmptyMessageDelayed(1, 3000);
                        break;
                    case 2:
                        registEtCode.setHintTextColor(getResources().getColor(R.color.red));
                        registEtCode.setHint("请输入验证码");
                        registBtnNext.setClickable(false);
                        mHandler.sendEmptyMessageDelayed(2, 3000);
                        break;
                    case 3:
                        registEtCode.setText("");
                        registEtCode.setHintTextColor(getResources().getColor(R.color.red));
                        registEtCode.setHint("验证码错误");
                        registBtnNext.setClickable(false);
                        mHandler.sendEmptyMessageDelayed(3, 3000);
                        break;
                    case 5:
                        registNext(registEtPhone.getText().toString().trim(), registEtCode.getText().toString().trim());
                        break;
                    case 4:
                        Toast.makeText(RegistActivity.this, "请先阅读并同意《用户协议》", Toast.LENGTH_SHORT).show();
                        break;
                }


                break;
            case R.id.regist_tv_have:
                //已有账号
                intent = new Intent(RegistActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.regist_tv_agreement:
                //用户协议
                intent = new Intent(RegistActivity.this, AgreementActivity.class);
                startActivity(intent);
                break;
        }

    }

    private int canNext() {
        String phone = registEtPhone.getText().toString().trim();
        String code = registEtCode.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            return 0;
        } else if (phone.length() != 11 || !phone.substring(0, 1).equals("1")) {
            return 1;
        } else if (TextUtils.isEmpty(code)) {
            return 2;
        } else if (code.length() < 4 || code.length() > 4) {
            return 3;
        } else if (!registCk.isChecked()) {
            return 4;
        } else {
            return 5;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }

    ParamBean paramBean;
    ParamBean.ParamData paramData;

    /**
     * 获取验证码
     */
    private void getCode(String phone) {
        paramBean = new ParamBean();
        paramData = new ParamBean.ParamData();
        paramData.setPhone(phone);
        paramBean.setParamData(paramData);
        OkHttpUtils.postString()
                .mediaType(MediaType.parse(Url.CONTENT_TYPE))
                .url(Url.WEB_VALIDATE_CODE_REGIST)
                .content(new Gson().toJson(paramBean))
                .build().execute(new StringCallback() {

            private BaseResultBean baseResultBean;

            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) throws Exception {
                Log.e("yjl","regist--code"+response);
                if (Tools.isGoodJson(response)) {
                    baseResultBean = new Gson().fromJson(response, BaseResultBean.class);
                    if (baseResultBean.isExecResult()) {
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                registBtnCode.setBackground(getResources().getDrawable(R.drawable.vertifystyle2));
                                registBtnCode.setText(second + "秒后可重发送");
                                registBtnCode.setClickable(false);
                                mHandler.sendEmptyMessageDelayed(5, 1000);
                            }
                        });
                    } else {
                        registEtPhone.setText("");
                        registEtPhone.setHintTextColor(getResources().getColor(R.color.red));
                        registEtPhone.setHint("" + baseResultBean.getExecMsg());
                        registBtnCode.setClickable(false);
                        mHandler.sendEmptyMessageDelayed(1, 3000);
                    }
                }
            }
        });
    }

    /**
     * 校验手机与验证码---下一步
     */
    private void registNext(String phone, String code) {
        paramBean = new ParamBean();
        paramData = new ParamBean.ParamData();
        paramData.setPhone(phone);
        paramData.setCode(code);
        paramBean.setParamData(paramData);
        OkHttpUtils.postString()
                .mediaType(MediaType.parse(Url.CONTENT_TYPE))
                .url(Url.WEB_CHECK_VALIDATE_CODE_REGIST)
                .content(new Gson().toJson(paramBean))
                .build().execute(new StringCallback() {

            private BaseResultBean baseResultBean;

            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) throws Exception {
                LogUtil.e("yjl", "regist---response>>" + response);
                if (Tools.isGoodJson(response)) {
                    baseResultBean = new Gson().fromJson(response, BaseResultBean.class);
                    if (baseResultBean.isExecResult()) {
                        mHandler.removeMessages(5);
                        registBtnCode.setClickable(true);
                        registBtnCode.setText("获取验证码");
                        registBtnCode.setBackground(getResources().getDrawable(R.drawable.vertifystyle));
                        second = 60;
                        Intent intent = new Intent(RegistActivity.this, RegistTwoActivity.class);
                        intent.putExtra("BEAN", paramBean);
                        startActivity(intent);
                    }else {
//                        {"execResult":false,"execErrCode":"u-000030","execMsg":"验证码错误","count":0,"num":0}
                        registEtCode.setText("");
                        registEtCode.setHintTextColor(getResources().getColor(R.color.red));
                        registEtCode.setHint("验证码错误");
                        registBtnNext.setClickable(false);
                        mHandler.sendEmptyMessageDelayed(3, 3000);
                    }
                }
            }
        });
    }
}
