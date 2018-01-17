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
import android.widget.Toast;

import com.micai.fox.R;
import com.micai.fox.util.ExitAppUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
                        Intent intent = new Intent(ResetPassActivity.this, ResetPassTwoActivity.class);
                        startActivity(intent);
                        mHandler.removeMessages(5);
                        resetBtnCode.setClickable(true);
                        resetBtnCode.setText("获取验证码");
                        resetBtnCode.setBackground(getResources().getDrawable(R.drawable.vertifystyle));
                        second = 60;
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
        mHandler.removeMessages(0);
        mHandler.removeMessages(1);
        mHandler.removeMessages(2);
        mHandler.removeMessages(3);
        mHandler.removeMessages(4);
        mHandler = null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    /**
     * 获取验证码
     */
    private void getCode() {
    }

    /**
     * 校验手机号、验证码---下一步
     */
    private void resetNnext() {
    }
}
