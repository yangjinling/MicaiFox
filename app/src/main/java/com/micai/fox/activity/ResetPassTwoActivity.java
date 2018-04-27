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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.MediaType;

/*重置密码下一步*/
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
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    resetEtPassword.setHintTextColor(getResources().getColor(R.color.gray));
                    resetEtPassword.setHint("请输入密码");
                    resetBtnReset.setClickable(true);
                    break;
                case 1:
                    resetEtPasswordAgin.setHintTextColor(getResources().getColor(R.color.gray));
                    resetEtPasswordAgin.setHint("请再次输入密码");
                    resetEtPasswordAgin.setClickable(true);
                    break;
                case 2:
                    resetEtPassword.requestFocus();
                    resetEtPassword.setText("");
                    resetEtPasswordAgin.setText("");
                    resetEtPassword.setHintTextColor(getResources().getColor(R.color.gray));
                    resetEtPassword.setHint("请输入密码");
                    resetEtPasswordAgin.setHintTextColor(getResources().getColor(R.color.gray));
                    resetEtPasswordAgin.setHint("请再次输入密码");
                    resetBtnReset.setClickable(true);
                    break;
                case 3:
                    resetEtPassword.requestFocus();
                    resetEtPassword.setText("");
                    resetEtPasswordAgin.setText("");
                    resetEtPassword.setHintTextColor(getResources().getColor(R.color.gray));
                    resetEtPassword.setHint("请输入密码");
                    resetEtPasswordAgin.setHintTextColor(getResources().getColor(R.color.gray));
                    resetEtPasswordAgin.setHint("请再次输入密码");
                    resetBtnReset.setClickable(true);
                    break;
                case 4:
                    dialog.dismiss();
                    ExitAppUtils.getInstance().finishActivity(ResetPassActivity.class);
                    finish();
                    break;
            }
        }
    };
    private Dialog dialog;
    private ParamBean paramBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pass_two);
        ButterKnife.bind(this);
        paramBean = ((ParamBean) getIntent().getSerializableExtra("BEAN"));
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
            case 0:
                resetEtPassword.setHintTextColor(getResources().getColor(R.color.red));
                resetEtPassword.setHint("请输入密码");
                resetBtnReset.setClickable(false);
                mHandler.sendEmptyMessageDelayed(0, 3000);
                break;
            case 1:
                resetEtPasswordAgin.setHintTextColor(getResources().getColor(R.color.red));
                resetEtPasswordAgin.setHint("请重复输入密码");
                resetBtnReset.setClickable(false);
                mHandler.sendEmptyMessageDelayed(1, 3000);
                break;
            case 2:
                resetEtPassword.setText("");
                resetEtPasswordAgin.setText("");
                resetEtPassword.setHintTextColor(getResources().getColor(R.color.red));
                resetEtPassword.setHint("两次密码不一致，请重新输入");
                resetEtPasswordAgin.setHintTextColor(getResources().getColor(R.color.red));
                resetEtPasswordAgin.setHint("两次密码不一致，请重新输入");
                resetBtnReset.setClickable(false);
                mHandler.sendEmptyMessageDelayed(2, 3000);
                break;
            case 3:
                resetEtPassword.setText("");
                resetEtPasswordAgin.setText("");
                resetEtPassword.setHintTextColor(getResources().getColor(R.color.red));
                resetEtPassword.setHint("密码需为6~16位的数字、字母或符号");
                resetEtPasswordAgin.setHintTextColor(getResources().getColor(R.color.red));
                resetEtPasswordAgin.setHint("密码需为6~16位的数字、字母或符号");
                resetBtnReset.setClickable(false);
                mHandler.sendEmptyMessageDelayed(3, 3000);
                break;
            case 4:
//                Intent intent = new Intent(ResetPassTwoActivity.this, MainActivity.class);
//                startActivity(intent);
                reset(resetEtPassword.getText().toString(), resetEtPasswordAgin.getText().toString());
                break;
        }
    }

    private int canConfirm() {
        String pass = resetEtPassword.getText().toString();
        String passAgain = resetEtPasswordAgin.getText().toString();
        if (TextUtils.isEmpty(pass.trim())) {
            return 0;
        } else if (TextUtils.isEmpty(passAgain.trim())) {
            return 1;
        } else if (!pass.equals(passAgain)) {
            return 2;
        } else if (!judgePass(pass) || !judgePass(passAgain)) {
            return 3;//两次密码输入一致
        } else {
            return 4;
        }
    }

    private boolean judgePass(String password) {
        String regEx = "[\\da-zA-Z]{6,16}";
        Pattern pattern = Pattern.compile(regEx);
        Matcher ExetPwd = pattern.matcher(password);    // 新密码
        return ExetPwd.matches();
    }

    @Override
    protected void onStop() {
        clearMessage();
        super.onStop();
    }

    private void clearMessage() {
        if (null != mHandler) {
            mHandler.removeMessages(0);
            mHandler.removeMessages(1);
            mHandler.removeMessages(2);
            mHandler.removeMessages(3);
            mHandler.removeMessages(4);
            mHandler = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    /**
     * 校验两次密码---重置
     */
    private void reset(String pwd, String pwd2) {
        ParamBean paramBeans = new ParamBean();
        ParamBean.ParamData paramData = paramBean.getParamData();
        paramData.setPwd(pwd);
        paramData.setPwd2(pwd2);
        paramBeans.setParamData(paramData);
        OkHttpUtils.postString()
                .mediaType(MediaType.parse(Url.CONTENT_TYPE))
                .url(Url.WEB_RESET)
                .content(new Gson().toJson(paramBeans))
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
                        dialog = Tools.showDialog(ResetPassTwoActivity.this, 1,null);
                        mHandler.sendEmptyMessageDelayed(4, 1500);
                    } else {
                    }
                }
            }
        });
    }
}
