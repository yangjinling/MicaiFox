package com.micai.fox.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
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
    @Bind(R.id.regist_tv_agreement)
    TextView registTvAgreement;
    private int second = 60;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (null != registBtnCode)
                registBtnCode.setText(--second + "秒后重发");
            if (second >= 1) {
                mHandler.sendEmptyMessageDelayed(1, 1000);
            } else {
                registBtnCode.setClickable(true);
                registBtnCode.setText("获取验证码");
                second = 60;
            }
        }
    };

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

    @OnClick({R.id.regist_tv_agreement, R.id.tv_back, R.id.regist_btn_code, R.id.regist_btn_next, R.id.regist_tv_have})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.regist_btn_code:
                //获取验证码
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        registBtnCode.setText(second + "秒后重发");
                        registBtnCode.setClickable(false);
                        mHandler.sendEmptyMessageDelayed(1, 1000);
                    }
                });
                break;
            case R.id.regist_btn_next:
                //下一步
                if (canNext()) {
                    intent = new Intent(RegistActivity.this, RegistTwoActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.regist_tv_have:
                intent = new Intent(RegistActivity.this, LoginActivity.class);
                startActivity(intent);
                //已有账号
                break;
            case R.id.regist_tv_agreement:
                //用户协议
                intent = new Intent(RegistActivity.this, AgreementActivity.class);
                startActivity(intent);
                break;
        }

    }

    private boolean canNext() {
        String phone = registEtPhone.getText().toString().trim();
        String code = registEtCode.getText().toString().trim();
        if (!TextUtils.isEmpty(phone) && !TextUtils.isEmpty(code) && phone.length() == 11 && code.length() == 6 && registCk.isChecked()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }
}
