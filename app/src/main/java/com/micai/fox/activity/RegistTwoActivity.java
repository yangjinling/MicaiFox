package com.micai.fox.activity;

import android.app.Dialog;
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
import com.micai.fox.util.LogUtil;
import com.micai.fox.util.Tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*注册的下一步*/
public class RegistTwoActivity extends AppCompatActivity {

    @Bind(R.id.tv_back)
    TextView tvBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_notify)
    TextView tvNotify;
    @Bind(R.id.rl)
    RelativeLayout rl;
    @Bind(R.id.regist_et_nick)
    EditText registEtNick;
    @Bind(R.id.regist_et_password)
    EditText registEtPassword;
    @Bind(R.id.regist_et_password_agin)
    EditText registEtPasswordAgin;
    @Bind(R.id.regist_ll_et_two)
    LinearLayout registLlEtTwo;
    @Bind(R.id.regist_btn_regist)
    Button registBtnRegist;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    registEtNick.setTextColor(getResources().getColor(R.color.gray));
                    registEtNick.setHint("请输入昵称");
                    registBtnRegist.setClickable(true);
                    break;
                case 1:
                    registEtPassword.setHint(getResources().getColor(R.color.gray));
                    registEtPassword.setHint("6~16位的数字、字母或符号");
                    registBtnRegist.setClickable(true);
                    break;
                case 2:
                    registEtPasswordAgin.setHintTextColor(getResources().getColor(R.color.gray));
                    registEtPasswordAgin.setHint("请再次输入密码");
                    registBtnRegist.setClickable(true);
                    break;
                case 3:
                    registEtPassword.setHintTextColor(getResources().getColor(R.color.gray));
                    registEtPassword.setHint("6~16位的数字、字母或符号");
                    registEtPasswordAgin.setHintTextColor(getResources().getColor(R.color.gray));
                    registEtPasswordAgin.setHint("请再次输入密码");
                    registBtnRegist.setClickable(true);
                    registEtPassword.requestFocus();
                    break;
                case 4:
                    registEtPassword.setHintTextColor(getResources().getColor(R.color.gray));
                    registEtPassword.setHint("6~16位的数字、字母或符号");
                    registEtPasswordAgin.setHintTextColor(getResources().getColor(R.color.gray));
                    registEtPasswordAgin.setHint("请再次输入密码");
                    registBtnRegist.setClickable(true);
                    registEtPassword.requestFocus();
                    break;
                case 5:
                    dialog.dismiss();
                    ExitAppUtils.getInstance().finishActivity(RegistActivity.class);
                    ExitAppUtils.getInstance().finishActivity(LoginActivity.class);
                    finish();
                    break;
            }
        }
    };
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist_two);
        ButterKnife.bind(this);
        tvTitle.setText("注册");
    }

    @OnClick({R.id.tv_back, R.id.regist_btn_regist})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.regist_btn_regist:
                //确认注册
                switch (canRegist()) {
                    case 0:
                        registEtNick.setHintTextColor(getResources().getColor(R.color.red));
                        registEtNick.setHint("请输入昵称");
                        registBtnRegist.setClickable(false);
                        mHandler.sendEmptyMessageDelayed(0, 3000);
                        break;
                    case 1:
                        registEtPassword.setHintTextColor(getResources().getColor(R.color.red));
                        registEtPassword.setHint("请输入密码");
                        registBtnRegist.setClickable(false);
                        mHandler.sendEmptyMessageDelayed(1, 3000);
                        break;
                    case 2:
                        registEtPasswordAgin.setHintTextColor(getResources().getColor(R.color.red));
                        registEtPasswordAgin.setHint("请再次输入密码");
                        registBtnRegist.setClickable(false);
                        mHandler.sendEmptyMessageDelayed(2, 3000);
                        break;
                    case 3:
                        registEtPassword.setText("");
                        registEtPasswordAgin.setText("");
                        registEtPassword.setHintTextColor(getResources().getColor(R.color.red));
                        registEtPassword.setHint("两次密码不一致，请重新输入");
                        registEtPasswordAgin.setHintTextColor(getResources().getColor(R.color.red));
                        registEtPasswordAgin.setHint("两次密码不一致，请重新输入");
                        registBtnRegist.setClickable(false);
                        mHandler.sendEmptyMessageDelayed(3, 3000);
                        break;
                    case 4:
                        registEtPassword.setText("");
                        registEtPasswordAgin.setText("");
                        registEtPassword.setHintTextColor(getResources().getColor(R.color.red));
                        registEtPassword.setHint("密码需为6~16位的数字、字母或符号");
                        registEtPasswordAgin.setHintTextColor(getResources().getColor(R.color.red));
                        registEtPasswordAgin.setHint("密码需为6~16位的数字、字母或符号");
                        registBtnRegist.setClickable(false);
                        mHandler.sendEmptyMessageDelayed(4, 3000);
                        break;
                    case 5:
                        dialog = Tools.showDialog(this, 0);
                        mHandler.sendEmptyMessageDelayed(5,1500);
                        break;

                }
                break;
        }
    }

    private int canRegist() {
        String name = registEtNick.getText().toString().trim();
        String pass = registEtPassword.getText().toString().trim();
        String passAgain = registEtPasswordAgin.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            return 0;
        } else if (TextUtils.isEmpty(pass)) {
            return 1;
        } else if (TextUtils.isEmpty(passAgain)) {
            return 2;
        } else if (!pass.equals(passAgain)) {
            return 3;
        } else if (!judgePass(pass) || !judgePass(passAgain)) {
            return 4;
        } else {
            return 5;
        }
    }

    private boolean judgePass(String password) {
        String regEx = "[\\da-zA-Z]{6,16}";
        Pattern pattern = Pattern.compile(regEx);
        Matcher ExetPwd = pattern.matcher(password);    // 新密码
        return ExetPwd.matches();
    }

    @Override
    protected void onDestroy() {
        ButterKnife.unbind(this);
        super.onDestroy();
    }
}
