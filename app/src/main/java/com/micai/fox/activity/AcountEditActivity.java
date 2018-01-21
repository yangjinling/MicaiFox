package com.micai.fox.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.micai.fox.R;
import com.micai.fox.app.Config;
import com.micai.fox.app.Url;
import com.micai.fox.parambean.ParamBean;
import com.micai.fox.resultbean.BaseResultBean;
import com.micai.fox.util.LogUtil;
import com.micai.fox.util.Tools;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.MediaType;

public class AcountEditActivity extends AppCompatActivity {

    @Bind(R.id.tv_back)
    TextView tvBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_notify)
    TextView tvNotify;
    @Bind(R.id.rl)
    RelativeLayout rl;
    @Bind(R.id.account_et_name)
    EditText accountEtName;
    @Bind(R.id.account_et_num)
    EditText accountEtNum;
    @Bind(R.id.account_ll_bank)
    LinearLayout accountLlBank;
    @Bind(R.id.account_et_bankname)
    EditText accountEtBankname;
    @Bind(R.id.set_ll_account)
    LinearLayout setLlAccount;
    @Bind(R.id.update_account_tv_bank)
    TextView updateAccountTvBank;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    accountEtName.setHintTextColor(getResources().getColor(R.color.gray));
                    accountEtName.setHint("请输入户名");
                    tvNotify.setClickable(true);
                    break;
                case 1:
                    accountEtNum.setHintTextColor(getResources().getColor(R.color.gray));
                    accountEtNum.setHint("请输入账户号码");
                    tvNotify.setClickable(true);
                    break;
                case 2:
                    accountEtBankname.setHintTextColor(getResources().getColor(R.color.gray));
                    accountEtBankname.setHint("请输入开户支行");
                    tvNotify.setClickable(true);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acount_edit);
        ButterKnife.bind(this);
        setLlAccount.setVisibility(View.VISIBLE);
        tvNotify.setVisibility(View.VISIBLE);
        tvNotify.setText("保存");
        tvTitle.setText("收款账户编辑");
    }

    @OnClick({R.id.tv_back, R.id.tv_notify, R.id.account_ll_bank})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                setResult(RESULT_CANCELED);
                finish();
                break;
            case R.id.tv_notify:
                switch (canSave()) {
                    case 0:
                        accountEtName.setHintTextColor(getResources().getColor(R.color.red));
                        accountEtName.setHint("请输入户名");
                        tvTitle.setClickable(false);
                        mHandler.sendEmptyMessageDelayed(0, 3000);
                        break;
                    case 1:
                        accountEtNum.setHintTextColor(getResources().getColor(R.color.red));
                        accountEtNum.setHint("请输入账户号码");
                        tvTitle.setClickable(false);
                        mHandler.sendEmptyMessageDelayed(0, 3000);
                        break;
                    case 2:
                        accountEtBankname.setHintTextColor(getResources().getColor(R.color.red));
                        accountEtBankname.setHint("请输入开户支行");
                        tvTitle.setClickable(false);
                        mHandler.sendEmptyMessageDelayed(0, 3000);
                        break;
                    case 3:
                        updateAccount(accountEtName.getText().toString(), accountEtNum.getText().toString(), updateAccountTvBank.getText().toString(), accountEtBankname.getText().toString());
                        break;
                }
                break;
            case R.id.account_ll_bank:
                break;
        }
    }

    private int canSave() {
        String name = accountEtName.getText().toString().trim();
        String cardNum = accountEtNum.getText().toString().trim();
        String bankName = accountEtBankname.getText().toString();
        if (TextUtils.isEmpty(name)) {
            return 0;
        } else if (TextUtils.isEmpty(cardNum)) {
            return 1;
        } else if (TextUtils.isEmpty(bankName)) {
            return 2;
        } else {
            return 3;
        }
    }

    @Override
    protected void onDestroy() {
        ButterKnife.unbind(tvBack);
        super.onDestroy();
    }

    ParamBean paramBean;
    ParamBean.ParamData paramData;


    /*更新收款账号*/
    private void updateAccount(String accountName, String accountNumber, String accountBank, String accountBranch) {
        paramBean = new ParamBean();
        paramData = new ParamBean.ParamData();
        paramData.setAccountName(accountName);
        paramData.setAccountNumber(accountNumber);
        paramData.setAccountBank(accountBank);
        paramData.setAccountBranch(accountBranch);
        paramBean.setParamData(paramData);
        OkHttpUtils.postString()
                .mediaType(MediaType.parse(Url.CONTENT_TYPE))
//                .url(Url.WEB_SET_ACCOUNT_UPDATE)
                .url(String.format(Url.WEB_SET_ACCOUNT_UPDATE, Config.getInstance().getSessionId()))
                .content(new Gson().toJson(paramBean))
                .build().execute(new StringCallback() {

            private BaseResultBean baseResultBean;

            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) throws Exception {
                LogUtil.e("yjl", "update--account>>" + response);
                if (Tools.isGoodJson(response)) {
                    baseResultBean = new Gson().fromJson(response, BaseResultBean.class);
                    if (baseResultBean.isExecResult()) {
                        Intent intent = new Intent(AcountEditActivity.this, SettingDetailActivity.class);
                        intent.putExtra("BEAN", paramBean);
                        setResult(RESULT_OK, intent);
                        finish();
                    } else {
                    }
                }
            }
        });
    }
}
