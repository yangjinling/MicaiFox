package com.micai.fox.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.micai.fox.R;
import com.micai.fox.util.ExitAppUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*设置界面*/
public class SettingActivity extends AppCompatActivity {
    @Bind(R.id.tv_back)
    TextView tvBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.rl)
    RelativeLayout rl;
    @Bind(R.id.tv_notify)
    TextView tvNotify;
    @Bind(R.id.ll_set_account)
    LinearLayout llSetAccount;
    @Bind(R.id.ll_set_phone)
    LinearLayout llSetPhone;
    @Bind(R.id.ll_set_pass)
    LinearLayout llSetPass;
    @Bind(R.id.ll_set_idea)
    LinearLayout llSetIdea;
    @Bind(R.id.ll_set_aggrement)
    LinearLayout llSetAggrement;
    @Bind(R.id.ll_set_about)
    LinearLayout llSetAbout;
    @Bind(R.id.set_btn_exit)
    Button setBtnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        rl.setVisibility(View.VISIBLE);
        tvBack.setVisibility(View.VISIBLE);
        tvTitle.setText("设置");
    }


    @OnClick({R.id.set_btn_exit, R.id.tv_back, R.id.ll_set_account, R.id.ll_set_phone, R.id.ll_set_pass, R.id.ll_set_idea, R.id.ll_set_aggrement, R.id.ll_set_about})
    public void onClick(View view) {
        Intent intent = new Intent(SettingActivity.this, SettingDetailActivity.class);
        int type = 0;
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.set_btn_exit:
                intent = new Intent(SettingActivity.this, LoginActivity.class);
                ExitAppUtils.getInstance().finishAllActivities();
                startActivity(intent);
//                ExitAppUtils.getInstance().finishAllActivities();
                break;
            case R.id.ll_set_account:
                //收款账号
                type = 0;
                intent.putExtra("VALUE", type);
                startActivity(intent);
                break;

            case R.id.ll_set_phone:
                //手机号
                type = 1;
                intent.putExtra("VALUE", type);
                startActivity(intent);
                break;

            case R.id.ll_set_pass:
                //密码
//                type = 2;
//                intent.putExtra("VALUE", type);
//                startActivity(intent);
                intent = new Intent(SettingActivity.this, ResetPassActivity.class);
                startActivity(intent);
                break;

            case R.id.ll_set_idea:
                //意见反馈
                type = 3;
                intent.putExtra("VALUE", type);
                startActivity(intent);
                break;

            case R.id.ll_set_aggrement:
                //用户协议
                type = 4;
                intent.putExtra("VALUE", type);
                startActivity(intent);
                break;
            case R.id.ll_set_about:
                //关于我们
                type = 5;
                intent.putExtra("VALUE", type);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }


}
