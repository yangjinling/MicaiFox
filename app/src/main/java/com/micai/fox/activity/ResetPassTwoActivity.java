package com.micai.fox.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.micai.fox.R;
import com.micai.fox.util.ExitAppUtils;
import com.micai.fox.util.LogUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ResetPassTwoActivity extends AppCompatActivity {

    @Bind(R.id.tv_back)
    TextView tvBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_notify)
    TextView tvNotify;
    @Bind(R.id.rl)
    RelativeLayout rl;
    @Bind(R.id.reset_et_password)
    EditText resetEtPassword;
    @Bind(R.id.reset_et_password_agin)
    EditText resetEtPasswordAgin;
    @Bind(R.id.regset_ll_et_two)
    LinearLayout regsetLlEtTwo;
    @Bind(R.id.reset_btn_reset)
    Button resetBtnReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pass_two);
        ButterKnife.bind(this);
        tvTitle.setText("重置密码");
    }

    @OnClick({R.id.tv_back, R.id.reset_btn_reset})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.reset_btn_reset:
                int code = canConfirm();
                doAction(code);
                break;
        }
    }

    private void doAction(int code) {
        LogUtil.e("YJL", "" + code);
        switch (code) {
            case 1:
                resetEtPassword.setHintTextColor(getResources().getColor(R.color.red));
                resetEtPassword.setHint("6~16位的数字、字母或符号");
                break;
            case 2:
                resetEtPasswordAgin.setText("");
                resetEtPasswordAgin.setHintTextColor(getResources().getColor(R.color.red));
                resetEtPasswordAgin.setHint("两次密码输入不一致");
                break;
            case 3:
                resetEtPasswordAgin.setHintTextColor(getResources().getColor(R.color.red));
                resetEtPasswordAgin.setHint("6~16位的数字、字母或符号");
                break;
            case 5:
//                Intent intent = new Intent(ResetPassTwoActivity.this, MainActivity.class);
//                startActivity(intent);
                ExitAppUtils.getInstance().finishActivity(ResetPassActivity.class);
                finish();
                break;
        }
    }

    private int canConfirm() {
        String pass = resetEtPassword.getText().toString();
        String passAgain = resetEtPasswordAgin.getText().toString();
        if (TextUtils.isEmpty(pass.trim())) {
            return 1;
        } else if (pass.trim().length() < 6) {
            return 1;
        } else if (TextUtils.isEmpty(passAgain.trim())) {
            return 3;
        } else if (passAgain.trim().length() < 6) {
            return 3;
        } else if (!TextUtils.isEmpty(pass.trim()) && !TextUtils.isEmpty(passAgain.trim()) && !pass.equals(passAgain)) {
            return 2;
        } else {
            return 5;//两次密码输入一致
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
