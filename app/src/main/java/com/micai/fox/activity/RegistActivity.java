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
import android.widget.Toast;

import com.micai.fox.R;
import com.micai.fox.base.BaseActivity;
import com.micai.fox.util.ExitAppUtils;

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
                    registBtnNext.setClickable(true);
                    break;
                case 1:
                    registEtPhone.setHintTextColor(getResources().getColor(R.color.gray));
                    registEtPhone.setHint("请输入手机号");
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
                String phone = registEtPhone.getText().toString().trim();
                if (!TextUtils.isEmpty(phone) && phone.length() == 11 && "1".equals(phone.substring(0, 1))) {
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
                        mHandler.removeMessages(5);
                        registBtnCode.setClickable(true);
                        registBtnCode.setText("获取验证码");
                        registBtnCode.setBackground(getResources().getDrawable(R.drawable.vertifystyle));
                        second = 60;
                        intent = new Intent(RegistActivity.this, RegistTwoActivity.class);
                        startActivity(intent);
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
}
