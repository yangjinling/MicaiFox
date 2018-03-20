package com.micai.fox.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.igexin.sdk.PushManager;
import com.micai.fox.R;
import com.micai.fox.app.Config;
import com.micai.fox.app.Url;
import com.micai.fox.resultbean.BaseResultBean;
import com.micai.fox.util.ExitAppUtils;
import com.micai.fox.util.LogUtil;
import com.micai.fox.util.PrefUtils;
import com.micai.fox.util.Tools;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.MediaType;

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
        ExitAppUtils.getInstance().addActivity(this);
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
                showDialogExitLogin();
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
                type = 2;
                intent.putExtra("VALUE", type);
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
//                type = 4;
//                intent.putExtra("VALUE", type);
//                startActivity(intent);
                Intent intent1=new Intent(this,AgreementActivity.class);
                startActivity(intent1);
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

    private Dialog dialog;

    private void showDialogExitLogin() {
        dialog = new Dialog(this, R.style.Translucent_NoTitle);
        //自定义弹窗布局
        View view = View.inflate(SettingActivity.this, R.layout.dialog_exit_login, null);
        //设置它的ContentView
        dialog.setContentView(view);
        Button cancle = ((Button) view.findViewById(R.id.dialog_btn_cancle));
        Button sure = ((Button) view.findViewById(R.id.dialog_btn_sure));
        View hor = view.findViewById(R.id.view_HorizontalLine);
        View ver = view.findViewById(R.id.view_VerticalLine);
        hor.setVisibility(View.VISIBLE);
        ver.setVisibility(View.VISIBLE);
        sure.setOnClickListener(new View.OnClickListener() {


            //跳转 需要判断密码是否相等
            @Override
            public void onClick(View view) {
               logOut();

            }
        });

        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //隐藏弹窗
                dialog.dismiss();

            }
        });
        WindowManager m = getWindowManager();
        Display d = m.getDefaultDisplay();  //为获取屏幕宽、高
        WindowManager.LayoutParams p = dialog.getWindow().getAttributes();  //获取对话框当前的参数值
                  /* (int) (d.getHeight() * 0.3);  */ //高度设置为屏幕的0.3
        p.height = (int) (d.getHeight() * 0.3); //高度设置为屏幕-标题栏
                /*(int) (d.getWidth() * 0.5);*/
        p.width = (int) (d.getWidth() * 0.9);   //宽度设置为屏幕的0.5
        dialog.getWindow().setAttributes(p);     //设置生效
        dialog.show();


    }

    private void logOut() {
        OkHttpUtils.post()
//                .mediaType(MediaType.parse(Url.CONTENT_TYPE))
//////                .url(Url.WEB_LOGOUT)
//                .content("")
                .url(String.format(Url.WEB_LOGOUT, Config.getInstance().getSessionId()))
                .build().execute(new StringCallback() {

            private BaseResultBean baseResultBean;

            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) throws Exception {
                LogUtil.e("yjl", "set---logout>>" + response);
                if (Tools.isGoodJson(response)) {
                    baseResultBean = new Gson().fromJson(response, BaseResultBean.class);
                    if (baseResultBean.isExecResult()) {
                        dialog.dismiss();
                        Config.getInstance().setSet(false);
                        Intent intent = new Intent(SettingActivity.this, MainActivity.class);
                        ExitAppUtils.getInstance().finishAllActivities();
                        PrefUtils.setBoolean(Config.getInstance().getmContext(), "ISFIRST", false);
//                        Config.getInstance().setClientId(loginResultBean.getExecDatas().getId());
                        PrefUtils.setString(Config.getInstance().getmContext(), "SESSIONID", null);
                        PushManager.getInstance().unBindAlias(getApplicationContext(),Config.getInstance().getClientId(),false);
                        Config.getInstance().setClientId("");
                        Config.getInstance().setLoginFromBuy(false);
                        intent.putExtra("TYPE", 2);
                        startActivity(intent);
                        finish();
                    } else {
                    }
                }

            }
        });
    }
}
