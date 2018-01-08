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

import com.micai.fox.R;
import com.micai.fox.util.ExitAppUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
            if (null != resetBtnCode)
                resetBtnCode.setText(--second + "秒后重发");
            if (second >= 1) {
                mHandler.sendEmptyMessageDelayed(1, 1000);
            } else {
                resetBtnCode.setClickable(true);
                resetBtnCode.setText("获取验证码");
                second = 60;
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
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        resetBtnCode.setText(second + "秒后重发");
                        resetBtnCode.setClickable(false);
                        mHandler.sendEmptyMessageDelayed(1, 1000);
                    }
                });
                break;
            case R.id.reset_btn_next:
                //下一步
                if (canNext()) {
                    Intent intent = new Intent(ResetPassActivity.this, ResetPassTwoActivity.class);
                    startActivity(intent);
                    mHandler.removeMessages(1);
                    resetBtnCode.setClickable(true);
                    resetBtnCode.setText("获取验证码");
                    second = 60;
                } else {
                }
                break;
        }
    }

    private boolean canNext() {
        String phone = resetEtPhone.getText().toString().trim();
        String code = resetEtCode.getText().toString().trim();
        if (!TextUtils.isEmpty(phone) && !TextUtils.isEmpty(code) && phone.length() == 11 && code.length() == 6) {
            return true;
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
