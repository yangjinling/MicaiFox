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

import com.micai.fox.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
                        Intent intent = new Intent(AcountEditActivity.this, SettingDetailActivity.class);
                        setResult(RESULT_OK, intent);
                        finish();
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
}
