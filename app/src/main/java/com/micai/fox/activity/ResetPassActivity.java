package com.micai.fox.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.micai.fox.R;
import com.micai.fox.app.Url;
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

/*重置密码*/
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
    private int second = 60;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 5://验证码倒计时
                    if (null != resetBtnCode)
                        resetBtnCode.setText(--second + "秒后可重发送");
                    if (second >= 1) {
                        mHandler.sendEmptyMessageDelayed(1, 1000);
                    } else {
                        resetBtnCode.setClickable(true);
                        resetBtnCode.setText("重新获取验证码");
                        second = 60;
                    }
                    break;
                case 0:
                    resetEtPhone.setHintTextColor(getResources().getColor(R.color.gray));
                    resetEtPhone.setHint("请输入手机号");
                    resetBtnNext.setClickable(true);
                    break;
                case 1:
                    resetEtPhone.setHintTextColor(getResources().getColor(R.color.gray));
                    resetEtPhone.setHint("请输入手机号");
                    resetBtnNext.setClickable(true);
                    break;
                case 2:
                    resetEtCode.setHintTextColor(getResources().getColor(R.color.gray));
                    resetEtCode.setHint("请输入验证码");
                    resetBtnNext.setClickable(true);
                    break;
                case 3:
                    resetEtCode.setHintTextColor(getResources().getColor(R.color.gray));
                    resetEtCode.setHint("请输入验证码");
                    resetBtnNext.setClickable(true);
                    break;
                case 4:
                    resetEtPhone.setHintTextColor(getResources().getColor(R.color.gray));
                    resetEtPhone.setHint("请输入手机号");
                    resetBtnCode.setClickable(true);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pass);
        ButterKnife.bind(this);
        ExitAppUtils.getInstance().addActivity(this);
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
                String phone = resetEtPhone.getText().toString().trim();
                if (!TextUtils.isEmpty(phone) && phone.length() == 11 && "1".equals(phone.substring(0, 1))) {
                    getCode(phone);
                } else {
                    if (TextUtils.isEmpty(phone)) {
                        resetEtPhone.setHintTextColor(getResources().getColor(R.color.red));
                        resetEtPhone.setHint("请输入手机号");
                        resetBtnCode.setClickable(false);
                        mHandler.sendEmptyMessageDelayed(4, 3000);
                    } else {
                        resetEtPhone.setText("");
                        resetEtPhone.setHintTextColor(getResources().getColor(R.color.red));
                        resetEtPhone.setHint("请输入正确的手机号");
                        resetBtnCode.setClickable(false);
                        mHandler.sendEmptyMessageDelayed(4, 3000);
                    }
                }
                break;
            case R.id.reset_btn_next:
                //下一步
                switch (canNext()) {
                    case 0:
                        resetEtPhone.setHintTextColor(getResources().getColor(R.color.red));
                        resetEtPhone.setHint("请输入手机号");
                        resetBtnNext.setClickable(false);
                        mHandler.sendEmptyMessageDelayed(0, 3000);
                        break;
                    case 1:
                        resetEtPhone.setText("");
                        resetEtPhone.setHintTextColor(getResources().getColor(R.color.red));
                        resetEtPhone.setHint("请输入正确的手机号");
                        resetBtnNext.setClickable(false);
                        mHandler.sendEmptyMessageDelayed(1, 3000);
                        break;
                    case 2:
                        resetEtCode.setHintTextColor(getResources().getColor(R.color.red));
                        resetEtCode.setHint("请输入验证码");
                        resetBtnNext.setClickable(false);
                        mHandler.sendEmptyMessageDelayed(2, 3000);
                        break;
                    case 3:
                        resetEtCode.setText("");
                        resetEtCode.setHintTextColor(getResources().getColor(R.color.red));
                        resetEtCode.setHint("验证码错误");
                        resetBtnNext.setClickable(false);
                        mHandler.sendEmptyMessageDelayed(3, 3000);
                        break;
                    case 4:
                        resetNext(resetEtPhone.getText().toString(), resetEtCode.getText().toString());
                        break;
                }
                break;
        }
    }

    private int canNext() {
        String phone = resetEtPhone.getText().toString().trim();
        String code = resetEtCode.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            return 0;
        } else if (phone.length() != 11 || !phone.substring(0, 1).equals("1")) {
            return 1;
        } else if (TextUtils.isEmpty(code)) {
            return 2;
        } else if (code.length() < 4 || code.length() > 4) {
            return 3;
        } else {
            return 4;
        }
    }

    @Override
    protected void onStop() {
        clearMessage();
        super.onStop();
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
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
                .url(Url.WEB_VALIDATE_CODE_RESET)
                .content(new Gson().toJson(paramBean))
                .build().execute(new StringCallback() {

            private BaseResultBean baseResultBean;

            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) throws Exception {
                if (Tools.isGoodJson(response)) {
                    baseResultBean = new Gson().fromJson(response, BaseResultBean.class);
                    if (baseResultBean.isExecResult()) {
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                resetBtnCode.setBackground(getResources().getDrawable(R.drawable.vertifystyle2));
                                resetBtnCode.setText(second + "秒后可重发送");
                                resetBtnCode.setClickable(false);
                                mHandler.sendEmptyMessageDelayed(5, 1000);
                            }
                        });
                    } else {
                    }
                }
            }
        });
    }

    /**
     * 校验手机号、验证码---下一步
     */
    private void resetNext(String phone, String code) {
        paramBean = new ParamBean();
        paramData = new ParamBean.ParamData();
        paramData.setPhone(phone);
        paramData.setCode(code);
        paramBean.setParamData(paramData);
        OkHttpUtils.postString()
                .mediaType(MediaType.parse(Url.CONTENT_TYPE))
                .url(Url.WEB_CHECK_VALIDATE_CODE_RESET)
                .content(new Gson().toJson(paramBean))
                .build().execute(new StringCallback() {

            private BaseResultBean baseResultBean;

            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) throws Exception {
                LogUtil.e("yjl", "reset---response>>" + response);
                if (Tools.isGoodJson(response)) {
                    baseResultBean = new Gson().fromJson(response, BaseResultBean.class);
                    if (baseResultBean.isExecResult()) {
                        mHandler.removeMessages(5);
                        resetBtnCode.setClickable(true);
                        resetBtnCode.setText("获取验证码");
                        resetBtnCode.setBackground(getResources().getDrawable(R.drawable.vertifystyle));
                        second = 60;
                        Intent intent = new Intent(ResetPassActivity.this, ResetPassTwoActivity.class);
                        intent.putExtra("BEAN", paramBean);
                        startActivity(intent);
                    } else {
                    }
                }
            }
        });
    }
}
