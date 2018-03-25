package com.micai.fox.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.hardware.input.InputManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Html;
import android.text.Selection;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.micai.fox.R;
import com.micai.fox.app.Config;
import com.micai.fox.app.Url;
import com.micai.fox.parambean.ParamBean;
import com.micai.fox.resultbean.AccountInfoResult;
import com.micai.fox.resultbean.BaseResultBean;
import com.micai.fox.resultbean.PhoneCodeResult;
import com.micai.fox.util.LogUtil;
import com.micai.fox.util.PrefUtils;
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

/*设置详情*/
public class SettingDetailActivity extends AppCompatActivity {

    @Bind(R.id.tv_back)
    TextView tvBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_notify)
    TextView tvNotify;
    @Bind(R.id.rl)
    RelativeLayout rl;
    @Bind(R.id.set_ll_pass)
    LinearLayout setLlPass;
    @Bind(R.id.set_web)
    LinearLayout setWeb;
    @Bind(R.id.set_web_tv)
    TextView tv_web;
    @Bind(R.id.set_ll_idea)
    RelativeLayout setLlIdea;
    @Bind(R.id.set_ll_phone)
    LinearLayout setLlPhone;
    @Bind(R.id.pass_et_origin)
    EditText passEtOrigin;
    @Bind(R.id.pass_et_new)
    EditText passEtNew;
    @Bind(R.id.pass_et_again)
    EditText passEtAgain;
    @Bind(R.id.pass_btn_confirm)
    Button passBtnConfirm;
    @Bind(R.id.idea_et)
    EditText ideaEt;
    @Bind(R.id.idea_btn_submit)
    Button ideaBtnSubmit;
    @Bind(R.id.phone_et_num)
    EditText phoneEtNum;
    @Bind(R.id.phone_et_code)
    EditText phoneEtCode;
    @Bind(R.id.phone_btn_code)
    Button phoneBtnCode;
    @Bind(R.id.phone_btn_confirm)
    Button phoneBtnConfirm;
    @Bind(R.id.idea_tv)
    TextView ideaTv;
    @Bind(R.id.set_idea_ll)
    LinearLayout ll_idea;
    @Bind(R.id.account_tv_name)
    TextView accountTvName;
    @Bind(R.id.account_tv_num)
    TextView accountTvNum;
    @Bind(R.id.account_tv_bank)
    TextView accountTvBank;
    @Bind(R.id.account_tv_bankname)
    TextView accountTvBankname;
    @Bind(R.id.set_detail_ll_account)
    LinearLayout setDetailLlAccount;
    @Bind(R.id.set_phone_tv_content)
    TextView setPhoneTvContent;
    private int type;
    private int second = 120;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0://修改手机号--没有输入手机号
                    phoneEtNum.setHintTextColor(getResources().getColor(R.color.gray));
                    phoneEtNum.setHint("请输入手机号");
                    phoneBtnCode.setClickable(true);
                    break;
                case 1://修改手机号--手机号格式不对
                    phoneEtNum.setHintTextColor(getResources().getColor(R.color.gray));
                    phoneEtNum.setHint("请输入手机号");
                    phoneBtnCode.setClickable(true);
                    break;
                case 2:
                    phoneEtNum.setHintTextColor(getResources().getColor(R.color.gray));
                    phoneEtNum.setHint("请输入手机号");
                    phoneBtnCode.setClickable(true);
                    break;
                case 3://phone---验证码没有输入
                    phoneEtCode.setHintTextColor(getResources().getColor(R.color.gray));
                    phoneEtCode.setHint("请输入验证码");
                    phoneBtnConfirm.setClickable(true);
                    break;
                case 4://phone---验证码错误
                    phoneEtCode.setHintTextColor(getResources().getColor(R.color.gray));
                    phoneEtCode.setHint("请输入验证码");
                    phoneBtnConfirm.setClickable(true);
                    break;
                case 5://phone---修改成功
                    if (null != phoneDialog && phoneDialog.isShowing()) {
                        phoneDialog.dismiss();
                    }
                    finish();
                    break;
                case 6:
                    //修改手机号验证码
                    if (null != phoneBtnCode)
                        phoneBtnCode.setText(--second + "秒后可重发送");
                    if (second >= 1) {
                        mHandler.sendEmptyMessageDelayed(6, 1000);
                    } else {
                        phoneBtnCode.setBackground(getResources().getDrawable(R.drawable.vertifystyle));
                        phoneBtnCode.setClickable(true);
                        phoneBtnCode.setText("重新获取验证码");
                        second = 120;
                    }
                    break;
                case 7:
                    passEtOrigin.setHintTextColor(getResources().getColor(R.color.gray));
                    passEtOrigin.setHint("请输入原密码");
                    phoneBtnConfirm.setClickable(true);
                    break;
                case 8:
                    passEtNew.setHintTextColor(getResources().getColor(R.color.gray));
                    passEtNew.setHint("请输入原密码");
                    passBtnConfirm.setClickable(true);
                    break;
                case 9:
                    passEtAgain.setHintTextColor(getResources().getColor(R.color.gray));
                    passEtAgain.setHint("请再次输入新密码");
                    passBtnConfirm.setClickable(true);
                    break;
                case 10:
                    passEtNew.requestFocus();
                    passEtNew.setText("");
                    passEtAgain.setText("");
                    passEtNew.setHintTextColor(getResources().getColor(R.color.gray));
                    passEtNew.setHint("6~16位的数字、字母或符号");
                    passEtAgain.setHintTextColor(getResources().getColor(R.color.gray));
                    passEtAgain.setHint("请再次输入新密码");
                    phoneBtnConfirm.setClickable(true);
                    break;
                case 11:
                    passEtNew.requestFocus();
                    passEtNew.setText("");
                    passEtAgain.setText("");
                    passEtNew.setHintTextColor(getResources().getColor(R.color.gray));
                    passEtNew.setHint("6~16位的数字、字母或符号");
                    passEtAgain.setHintTextColor(getResources().getColor(R.color.gray));
                    passEtAgain.setHint("请再次输入新密码");
                    phoneBtnConfirm.setClickable(true);
                    break;
                case 12:
                    passEtOrigin.setHintTextColor(getResources().getColor(R.color.gray));
                    passEtOrigin.setHint("请输入原密码");
                    phoneBtnConfirm.setClickable(true);
                    passEtOrigin.requestFocus();
                    break;
            }

        }
    };
    private Dialog phoneDialog;
    private ParamBean paramBeanResult;
    private ParamBean.ParamData paramDataResult;
    private String content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_detail);
        ButterKnife.bind(this);
        type = getIntent().getIntExtra("VALUE", 0);
        content = "<p style=\"margin-top: 0px; margin-bottom: 0px; text-align: justify; text-indent: 21px; font-stretch: normal; font-size: 10.5px; line-height: normal; font-family: &quot;PingFang SC Semibold&quot;; -webkit-text-stroke-width: initial; -webkit-text-stroke-color: rgb(0, 0, 0);\">\n" +
                "    <span style=\"font-stretch: normal; line-height: normal; font-family: &quot;PingFang SC&quot;; -webkit-font-kerning: none;\"><strong>公司介绍：</strong></span>\n" +
                "</p>" +
                "<p style=\"margin-top: 0px; margin-bottom: 0px; text-align: justify; text-indent: 21px; font-stretch: normal; font-size: 10.5px; line-height: normal; font-family: &quot;PingFang SC&quot;; -webkit-text-stroke-width: initial; -webkit-text-stroke-color: rgb(0, 0, 0);\">\n" +
                "    <span style=\"font-kerning: none\">杭州迷彩狐网络科技有限公司是一家致力于通过大数据人工智能技术，提供投资解决方案的科技金融公司。公司成立于</span><span style=\"font-stretch: normal; line-height: normal; font-family: &quot;Trebuchet MS&quot;; -webkit-font-kerning: none;\">2017</span><span style=\"font-kerning: none\">年</span><span style=\"font-stretch: normal; line-height: normal; font-family: &quot;Trebuchet MS&quot;; -webkit-font-kerning: none;\">9</span><span style=\"font-kerning: none\">月</span><span style=\"font-stretch: normal; line-height: normal; font-family: &quot;Trebuchet MS&quot;; -webkit-font-kerning: none;\">28</span><span style=\"font-kerning: none\">日，注册资金</span><span style=\"font-stretch: normal; line-height: normal; font-family: &quot;Trebuchet MS&quot;; -webkit-font-kerning: none;\">500</span><span style=\"font-kerning: none\">万，公司以大数据分析模型为依托，精心研发出完整的投资策略模型。为用户输出优异的投资解决方案。同时，通过公司的商品及活动众筹平台，为全国</span><span style=\"font-stretch: normal; line-height: normal; font-family: &quot;Trebuchet MS&quot;; -webkit-font-kerning: none;\">3</span><span style=\"font-kerning: none\">亿用户提供科技金融服务。</span>\n" +
                "</p>" +
                "<p>" +
                "    <br/>" +
                "</p>";
        initView(type);

    }

    private void initView(int type) {
        rl.setVisibility(View.VISIBLE);
        tvBack.setVisibility(View.VISIBLE);
        switch (type) {
            case 0:
                tvNotify.setVisibility(View.VISIBLE);
                tvNotify.setText("编辑");
                tvTitle.setText("收款账户");
                setDetailLlAccount.setVisibility(View.VISIBLE);
                getAccountInfo();
                break;
            case 1:
                tvTitle.setText("修改手机号");
                setLlPhone.setVisibility(View.VISIBLE);
                setPhoneTvContent.setText(reformatPhone(Config.getInstance().getPhone()));

                break;
            case 2:
                tvTitle.setText("修改密码");
                setLlPass.setVisibility(View.VISIBLE);
                break;
            case 3:
                tvTitle.setText("意见反馈");
                setLlIdea.setVisibility(View.VISIBLE);
                initIdeaEdit();
                break;
            case 4:
                tvTitle.setText("用户协议");
                setWeb.setVisibility(View.VISIBLE);
                break;
            case 5:
                tvTitle.setText("关于我们");
                setWeb.setVisibility(View.VISIBLE);
                tv_web.setText(Html.fromHtml(content));
                break;
        }
    }

    private String reformatPhone(String phone) {
        if (null != phone && !TextUtils.isEmpty(phone)) {
            return "您当前的注册手机号为：" + phone.substring(0, 3) + "****" + phone.substring(phone.length() - 4);
        } else return "";
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    private void initIdeaEdit() {
        ideaEt.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int count, int after) {
                int mTextMaxlenght = 0;
                int counts = 0;
                Editable editable = ideaEt.getText();
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
                    if (mTextMaxlenght > 1000) {
                        // 截取最大的字段
                        String newStr = str.substring(0, i);
                        ideaEt.setText(newStr);
                        // 得到新字段的长度值
                        editable = ideaEt.getText();
                        int newLen = editable.length();
                        if (selEndIndex > newLen) {
                            selEndIndex = editable.length();
                        }
                        // 设置新光标所在的位置
                        Selection.setSelection(editable, selEndIndex);
                        Toast.makeText(SettingDetailActivity.this, "最大长度为1000个字符或500个汉字！", Toast.LENGTH_SHORT).show();
                    } else {
                        counts++;
                        ideaTv.setText(counts + "/500");
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

    @OnClick({R.id.set_idea_ll, R.id.tv_back, R.id.tv_notify, R.id.account_ll_bank, R.id.pass_btn_confirm, R.id.idea_btn_submit, R.id.phone_btn_code, R.id.phone_btn_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.set_idea_ll:
                ideaEt.setFocusable(true);
                ideaEt.setFocusableInTouchMode(true);
                ideaEt.requestFocus();
                InputMethodManager imm = (InputMethodManager) ideaEt.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
                break;
            case R.id.tv_back:
                finish();
                break;
            case R.id.tv_notify:
                //修改收款账户
                Intent intent = new Intent(SettingDetailActivity.this, AcountEditActivity.class);
                startActivityForResult(intent, 100);
                break;
            case R.id.account_ll_bank:
                break;
            case R.id.pass_btn_confirm:
                switch (canPassWord()) {
                    case 0:
                        passEtOrigin.setHintTextColor(getResources().getColor(R.color.red));
                        passEtOrigin.setHint("请输入原密码");
                        phoneBtnConfirm.setClickable(false);
                        mHandler.sendEmptyMessageDelayed(7, 3000);
//                        Toast.makeText(SettingDetailActivity.this, "原密码不能为空", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        passEtNew.setHintTextColor(getResources().getColor(R.color.red));
                        passEtNew.setHint("请输入新密码");
                        phoneBtnConfirm.setClickable(false);
                        mHandler.sendEmptyMessageDelayed(8, 3000);
//                        Toast.makeText(SettingDetailActivity.this, "新密码不能为空", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        passEtAgain.setHintTextColor(getResources().getColor(R.color.red));
                        passEtAgain.setHint("请再次输入新密码");
                        phoneBtnConfirm.setClickable(false);
                        mHandler.sendEmptyMessageDelayed(9, 3000);
//                        Toast.makeText(SettingDetailActivity.this, "确认密码不能为空", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        passEtNew.setText("");
                        passEtAgain.setText("");
                        passEtNew.setHintTextColor(getResources().getColor(R.color.red));
                        passEtNew.setHint("两次输入的新密码不一致");
                        passEtAgain.setHintTextColor(getResources().getColor(R.color.red));
                        passEtAgain.setHint("两次输入的新密码不一致");
                        phoneBtnConfirm.setClickable(false);
                        mHandler.sendEmptyMessageDelayed(10, 3000);
//                        Toast.makeText(SettingDetailActivity.this, "两次输入密码不一致", Toast.LENGTH_SHORT).show();
                        break;
                    case 4:
                        passEtNew.setText("");
                        passEtAgain.setText("");
                        passEtNew.setHintTextColor(getResources().getColor(R.color.red));
                        passEtNew.setHint("密码需为6~16位的数字、字母或符号");
                        passEtAgain.setHintTextColor(getResources().getColor(R.color.red));
                        passEtAgain.setHint("密码需为6~16位的数字、字母或符号");
                        phoneBtnConfirm.setClickable(false);
                        mHandler.sendEmptyMessageDelayed(11, 3000);
                        break;
                    case 5:
                        updatePass(passEtOrigin.getText().toString(), passEtNew.getText().toString(), passEtAgain.getText().toString());
                        break;
                    case 6:
                        passEtOrigin.setText("");
                        passEtOrigin.setHintTextColor(getResources().getColor(R.color.red));
                        passEtOrigin.setHint("原密码错误");
                        phoneBtnConfirm.setClickable(false);
                        mHandler.sendEmptyMessageDelayed(12, 3000);
                        break;
                }
                break;
            case R.id.idea_btn_submit:
                if (!TextUtils.isEmpty(ideaEt.getText().toString())) {
                    submitIdea(ideaEt.getText().toString());
                } else {
                    Toast.makeText(SettingDetailActivity.this, "意见不能为空", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.phone_btn_code:
                //获取验证码
                String phone = phoneEtNum.getText().toString().trim();
                if (TextUtils.isEmpty(phone)) {
                    phoneEtNum.setHintTextColor(getResources().getColor(R.color.red));
                    phoneEtNum.setHint("请输入手机号");
                    phoneBtnCode.setClickable(false);
                    mHandler.sendEmptyMessageDelayed(0, 3000);
                } else if (phone.length() < 11 || !phone.substring(0, 1).equals("1")) {
                    phoneEtNum.setText("");
                    phoneEtNum.setHintTextColor(getResources().getColor(R.color.red));
                    phoneEtNum.setHint("请输入正确的手机号");
                    phoneBtnCode.setClickable(false);
                    mHandler.sendEmptyMessageDelayed(1, 3000);
                } else {
                    updatePhoneCode(phoneEtNum.getText().toString().trim());
                }
                break;
            case R.id.phone_btn_confirm:
                switch (canPhone()) {
                    case 0:
                        phoneEtNum.setHintTextColor(getResources().getColor(R.color.red));
                        phoneEtNum.setHint("请输入手机号");
                        phoneBtnConfirm.setClickable(false);
                        mHandler.sendEmptyMessageDelayed(2, 3000);
                        break;
                    case 1:
                        phoneEtCode.setHintTextColor(getResources().getColor(R.color.red));
                        phoneEtCode.setHint("请输入验证码");
                        phoneBtnConfirm.setClickable(false);
                        mHandler.sendEmptyMessageDelayed(3, 3000);
                        break;
                    case 2:
                        phoneEtCode.setText("");
                        phoneEtCode.setHintTextColor(getResources().getColor(R.color.red));
                        phoneEtCode.setHint("验证码错误");
                        phoneBtnConfirm.setClickable(false);
                        mHandler.sendEmptyMessageDelayed(4, 3000);
                        break;
                    case 3:
                        updatePhone(phoneEtNum.getText().toString().trim(), phoneEtCode.getText().toString());
                        break;
                }
                break;
        }
    }

    private int canPhone() {
        String phone = phoneEtNum.getText().toString().trim();
        String code = phoneEtCode.getText().toString().trim();
        //0:手机号不能为空  1:验证码不能为空
        // 3:验证码不合法
        if (TextUtils.isEmpty(phone)) {
            return 0;
        } else if (TextUtils.isEmpty(code)) {
            return 1;
        } else if (code.length() < 4 || code.length() > 4) {
            return 2;
        } else {
            return 3;
        }
    }

    private int canPassWord() {
        String origin = passEtOrigin.getText().toString().trim();
        String newPass = passEtNew.getText().toString().trim();
        String passAgain = passEtAgain.getText().toString().trim();
        if (TextUtils.isEmpty(origin)) {
            return 0;
        } else if (TextUtils.isEmpty(newPass)) {
            return 1;
        } else if (TextUtils.isEmpty(passAgain)) {
            return 2;
        } else if (!judgePass(origin)) {
            return 6;
        } else if (!newPass.equals(passAgain)) {
            return 3;
        } else if (!judgePass(passAgain) || !judgePass(newPass)) {
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 100:
                if (resultCode == RESULT_OK) {
                    paramBeanResult = ((ParamBean) data.getSerializableExtra("BEAN"));
                    paramDataResult = paramBeanResult.getParamData();
                    accountTvName.setText(paramDataResult.getAccountName());
                    accountTvNum.setText(paramDataResult.getAccountNumber());
                    accountTvBank.setText(paramDataResult.getAccountBankName());
                    accountTvBankname.setText(paramDataResult.getAccountBranch());
                } else {
                }
                break;
        }
    }

    @Override
    protected void onStop() {
        clearMessage();
        super.onStop();
    }

    private void clearMessage() {
        if (null != mHandler) {
            if (type == 1) {
                //修改手机号
                mHandler.removeMessages(0);
                mHandler.removeMessages(1);
                mHandler.removeMessages(2);
                mHandler.removeMessages(3);
                mHandler.removeMessages(4);
                mHandler.removeMessages(5);
                mHandler.removeMessages(6);
            } else if (type == 2) {
                //修改密码
                mHandler.removeMessages(7);
                mHandler.removeMessages(8);
                mHandler.removeMessages(9);
                mHandler.removeMessages(10);
                mHandler.removeMessages(11);
                mHandler.removeMessages(12);
            }
            mHandler = null;
        }
    }

    ParamBean paramBean;
    ParamBean.ParamData paramData;

    /*获取收款账户信息*/
    private void getAccountInfo() {
        OkHttpUtils.post()
//                .mediaType(MediaType.parse(Url.CONTENT_TYPE))
//                .url(Url.WEB_SET_ACCOUNT_SEARCH)
                .url(String.format(Url.WEB_SET_ACCOUNT_SEARCH, Config.getInstance().getSessionId()))
                .build().execute(new StringCallback() {

            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) throws Exception {
                LogUtil.e("yjl", "set---account>>" + response);
                if (Tools.isGoodJson(response)) {
                    AccountInfoResult accountInfoResult = new Gson().fromJson(response, AccountInfoResult.class);
                    if (accountInfoResult.isExecResult()) {
                        AccountInfoResult.ExecDatasBean execDatas = accountInfoResult.getExecDatas();
                        if (null != execDatas) {
                            accountTvName.setText(execDatas.getAccountName());
                            accountTvNum.setText(execDatas.getAccountNumber());
                            accountTvBank.setText(execDatas.getAccountBankName());
                            accountTvBankname.setText(execDatas.getAccountBranch());
                        }
                    } else {
                    }
                }

            }
        });
    }

    /*意见反馈*/
    private void submitIdea(String content) {
        paramBean = new ParamBean();
        paramData = new ParamBean.ParamData();
        paramData.setContent(content);
        paramBean.setParamData(paramData);
        OkHttpUtils.postString()
                .mediaType(MediaType.parse(Url.CONTENT_TYPE))
//                .url(Url.WEB_SET_IDEAR)
                .url(String.format(Url.WEB_SET_IDEAR, Config.getInstance().getSessionId()))
                .content(new Gson().toJson(paramBean))
                .build().execute(new StringCallback() {

            private BaseResultBean baseResultBean;

            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) throws Exception {
                LogUtil.e("yjl", "set---idea>>" + response);
                if (Tools.isGoodJson(response)) {
                    baseResultBean = new Gson().fromJson(response, BaseResultBean.class);
                    if (baseResultBean.isExecResult()) {
                        phoneDialog = Tools.showDialog(SettingDetailActivity.this, 3);
                        mHandler.sendEmptyMessageDelayed(5, 1500);
                    } else {
                    }
                }

            }
        });
    }

    /*修改密码*/
    private void updatePass(String oldPwd, String pwd, String pwd2) {
        paramBean = new ParamBean();
        paramData = new ParamBean.ParamData();
        paramData.setOldPwd(oldPwd);
        paramData.setPwd(pwd);
        paramData.setPwd2(pwd2);
        paramBean.setParamData(paramData);
        OkHttpUtils.postString()
                .mediaType(MediaType.parse(Url.CONTENT_TYPE))
                // .url(Url.WEB_SET_PASSWORD)
                .url(String.format(Url.WEB_SET_PASSWORD, Config.getInstance().getSessionId()))
                .content(new Gson().toJson(paramBean))
                .build().execute(new StringCallback() {

            private BaseResultBean baseResultBean;

            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) throws Exception {
                LogUtil.e("yjl", "set---pass>>" + response);
                if (Tools.isGoodJson(response)) {
                    baseResultBean = new Gson().fromJson(response, BaseResultBean.class);
                    if (baseResultBean.isExecResult()) {
                        phoneDialog = Tools.showDialog(SettingDetailActivity.this, 2);
                        mHandler.sendEmptyMessageDelayed(5, 1500);
                    } else {
                        passEtOrigin.setText("");
                        passEtOrigin.setHintTextColor(getResources().getColor(R.color.red));
                        passEtOrigin.setHint("" + baseResultBean.getExecMsg());
                        phoneBtnConfirm.setClickable(false);
                        mHandler.sendEmptyMessageDelayed(12, 3000);

                    }
                }

            }
        });
    }

    /*修改手机号验证码*/
    private void updatePhoneCode(String phone) {
        paramBean = new ParamBean();
        paramData = new ParamBean.ParamData();
        paramData.setPhone(phone);
        paramBean.setParamData(paramData);
        OkHttpUtils.postString()
                .mediaType(MediaType.parse(Url.CONTENT_TYPE))
//                .url(Url.WEB_SET_PHONE_CODE)
                .url(String.format(Url.WEB_SET_PHONE_CODE, Config.getInstance().getSessionId()))
                .content(new Gson().toJson(paramBean))
                .build().execute(new StringCallback() {

            private PhoneCodeResult baseResultBean;

            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) throws Exception {
                LogUtil.e("yjl", "set---phoneCode>>" + response);
                if (Tools.isGoodJson(response)) {
                    baseResultBean = new Gson().fromJson(response, PhoneCodeResult.class);
                    if (baseResultBean.isExecResult()) {
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                phoneBtnCode.setBackground(getResources().getDrawable(R.drawable.vertifystyle2));
                                phoneBtnCode.setText(second + "秒后可重发送");
                                phoneBtnCode.setClickable(false);
                                mHandler.sendEmptyMessageDelayed(6, 1000);
                            }
                        });
                    } else {
                        phoneEtNum.setText("");
                        phoneEtNum.setHintTextColor(getResources().getColor(R.color.red));
                        phoneEtNum.setHint("" + baseResultBean.getExecMsg());
                        phoneBtnCode.setClickable(false);
                        mHandler.sendEmptyMessageDelayed(2, 3000);
                    }
                } else {
                    Config.getInstance().setJin(true);
                }
            }

        });
    }

    /*修改手机号*/
    private void updatePhone(final String phone, String code) {
        paramBean = new ParamBean();
        paramData = new ParamBean.ParamData();
        paramData.setPhone(phone);
        paramData.setCode(code);
        paramBean.setParamData(paramData);
        OkHttpUtils.postString()
                .mediaType(MediaType.parse(Url.CONTENT_TYPE))
//                .url(Url.WEB_SET_PHONE)
                .url(String.format(Url.WEB_SET_PHONE, Config.getInstance().getSessionId()))
                .content(new Gson().toJson(paramBean))
                .build().execute(new StringCallback() {

            private BaseResultBean baseResultBean;

            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) throws Exception {
                LogUtil.e("yjl", "set---phone>>" + response);
                if (Tools.isGoodJson(response)) {
                    baseResultBean = new Gson().fromJson(response, BaseResultBean.class);
                    if (baseResultBean.isExecResult()) {
                        PrefUtils.setString(Config.getInstance().getmContext(), "PHONE", phone);
                        Config.getInstance().setPhone(phone);
                        phoneDialog = Tools.showDialog(SettingDetailActivity.this, 2);
                        mHandler.sendEmptyMessageDelayed(5, 1500);
                    } else {
                        phoneEtCode.setText("");
                        phoneEtCode.setHintTextColor(getResources().getColor(R.color.red));
                        phoneEtCode.setHint("验证码错误");
                        phoneBtnConfirm.setClickable(false);
                        mHandler.sendEmptyMessageDelayed(4, 3000);
                    }
                }
            }
        });
    }
}
