package com.micai.fox.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Selection;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
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
                    registEtNick.setHintTextColor(getResources().getColor(R.color.gray));
                    registEtNick.setHint("请输入昵称");
                    registBtnRegist.setClickable(true);
                    break;
                case 1:
                    registEtPassword.setHintTextColor(getResources().getColor(R.color.gray));
                    registEtPassword.setHint("6~16位的数字、字母或符号");
                    registBtnRegist.setClickable(true);
                    break;
                case 2:
                    registEtPasswordAgin.setHintTextColor(getResources().getColor(R.color.gray));
                    registEtPasswordAgin.setHint("请再次输入密码");
                    registBtnRegist.setClickable(true);
                    break;
                case 3:
                    registEtPassword.requestFocus();
                    registEtPassword.setText("");
                    registEtPasswordAgin.setText("");
                    registEtPassword.setHintTextColor(getResources().getColor(R.color.gray));
                    registEtPassword.setHint("6~16位的数字、字母或符号");
                    registEtPasswordAgin.setHintTextColor(getResources().getColor(R.color.gray));
                    registEtPasswordAgin.setHint("请再次输入密码");
                    registBtnRegist.setClickable(true);
                    registEtPassword.requestFocus();
                    break;
                case 4:
                    registEtPassword.requestFocus();
                    registEtPassword.setText("");
                    registEtPasswordAgin.setText("");
                    registEtPassword.setHintTextColor(getResources().getColor(R.color.gray));
                    registEtPassword.setHint("6~16位的数字、字母或符号");
                    registEtPasswordAgin.setHintTextColor(getResources().getColor(R.color.gray));
                    registEtPasswordAgin.setHint("请再次输入密码");
                    registBtnRegist.setClickable(true);
                    break;
                case 5:
                    dialog.dismiss();
//                    ExitAppUtils.getInstance().finishActivity(RegistActivity.class);
                    ExitAppUtils.getInstance().finishAllActivities();
                    Intent intent=new Intent(RegistTwoActivity.this,IndexActivity.class);
                    startActivity(intent);
//                    finish();
                    break;
            }
        }
    };
    private Dialog dialog;
    private ParamBean paramBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist_two);
        ButterKnife.bind(this);
        ExitAppUtils.getInstance().addActivity(this);
        tvTitle.setText("注册");
        paramBean = ((ParamBean) getIntent().getSerializableExtra("BEAN"));
        registEtNick.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int count, int after) {
                int mTextMaxlenght = 0;
                Editable editable = registEtNick.getText();
                String str = editable.toString().trim();
                //得到最初字段的长度大小，用于光标位置的判断
                int selEndIndex = Selection.getSelectionEnd(editable);
                // 取出每个字符进行判断，如果是字母数字和标点符号则为一个字符加1，
                //如果是汉字则为两个字符
                for (int i = 0; i < str.length(); i++) {
                    char charAt = str.charAt(i);
                    //32-122包含了空格，大小写字母，数字和一些常用的符号，
                    //如果在这个范围内则算一个字符，
                    //如果不在这个范围比如是汉字的话就是两个字符
                    if (charAt >= 32 && charAt <= 122) {
                        mTextMaxlenght++;
                    } else {
                        mTextMaxlenght += 2;
                    }
                    // 当最大字符大于40时，进行字段的截取，并进行提示字段的大小
                    if (mTextMaxlenght > 32) {
                        // 截取最大的字段
                        String newStr = str.substring(0, i);
                        registEtNick.setText(newStr);
                        // 得到新字段的长度值
                        editable = registEtNick.getText();
                        int newLen = editable.length();
                        if (selEndIndex > newLen) {
                            selEndIndex = editable.length();
                        }
                        // 设置新光标所在的位置
                        Selection.setSelection(editable, selEndIndex);
//                        Toast.makeText(MainActivity.this,"最大长度为40个字符或20个汉字！",Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

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
                        regist(registEtNick.getText().toString(), registEtPassword.getText().toString(), registEtPasswordAgin.getText().toString());
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
    protected void onStop() {
        clearMessage();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        ButterKnife.unbind(this);
        super.onDestroy();
    }

    private void clearMessage() {
        if (null!=mHandler){
        mHandler.removeMessages(0);
        mHandler.removeMessages(1);
        mHandler.removeMessages(2);
        mHandler.removeMessages(3);
        mHandler.removeMessages(4);
        mHandler.removeMessages(5);
        mHandler = null;}
    }

    /**
     * 校验昵称、密码---注册
     */
    private void regist(String nickName, String pwd, String pwd2) {
        ParamBean paramBeans = new ParamBean();
        ParamBean.ParamData paramData = paramBean.getParamData();
        paramData.setNickName(nickName);
        paramData.setPwd(pwd);
        paramData.setPwd2(pwd2);
        paramBeans.setParamData(paramData);
        OkHttpUtils.postString()
                .mediaType(MediaType.parse(Url.CONTENT_TYPE))
                .url(Url.WEB_REGIST)
                .content(new Gson().toJson(paramBeans))
                .build().execute(new StringCallback() {

            private BaseResultBean baseResultBean;

            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) throws Exception {
                LogUtil.e("yjl", "regist--result>>" + response);
                if (Tools.isGoodJson(response)) {
                    baseResultBean = new Gson().fromJson(response, BaseResultBean.class);
                    if (baseResultBean.isExecResult()) {
                        dialog = Tools.showDialog(RegistTwoActivity.this, 0,null);
                        mHandler.sendEmptyMessageDelayed(5, 1500);
                    } else {
                    }
                }

            }
        });
    }
}
