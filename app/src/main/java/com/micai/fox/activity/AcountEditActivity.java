package com.micai.fox.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.micai.fox.R;
import com.micai.fox.app.Config;
import com.micai.fox.app.Url;
import com.micai.fox.parambean.ParamBean;
import com.micai.fox.resultbean.BankResultBean;
import com.micai.fox.resultbean.BaseResultBean;
import com.micai.fox.util.LogUtil;
import com.micai.fox.util.Tools;
import com.wx.wheelview.adapter.ArrayWheelAdapter;
import com.wx.wheelview.widget.WheelView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.MediaType;

public class AcountEditActivity extends AppCompatActivity implements View.OnClickListener {

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
                case 3:
                    updateAccountTvBank.setTextColor(getResources().getColor(R.color.gray));
                    updateAccountTvBank.setText("请选择");
                    tvNotify.setClickable(true);
                    break;
            }
        }
    };
    private int kind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acount_edit);
        ButterKnife.bind(this);
        setLlAccount.setVisibility(View.VISIBLE);
        tvNotify.setVisibility(View.VISIBLE);
        kind = getIntent().getIntExtra("KIND", 0);
        tvNotify.setText("保存");
        tvTitle.setText("收款账户编辑");
    }

    @OnClick({R.id.tv_back, R.id.tv_notify, R.id.account_ll_bank})
    public void onClick(View view) {
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
                        mHandler.sendEmptyMessageDelayed(1, 3000);
                        break;
                    case 2:
                        accountEtBankname.setHintTextColor(getResources().getColor(R.color.red));
                        accountEtBankname.setHint("请输入开户支行");
                        tvTitle.setClickable(false);
                        mHandler.sendEmptyMessageDelayed(2, 3000);
                        break;
                    case 3:
                        updateAccount(accountEtName.getText().toString(), accountEtNum.getText().toString(), bankResultBean.getExecDatas().get(selectPosition - 1).getValue(), accountEtBankname.getText().toString());
                        break;
                    case 4:
                        updateAccountTvBank.setTextColor(getResources().getColor(R.color.red));
                        updateAccountTvBank.setText("请输入开户支行");
                        tvTitle.setClickable(false);
                        mHandler.sendEmptyMessageDelayed(3, 3000);
                        break;
                }
                break;
            case R.id.account_ll_bank:
                //选择银行列表
                getBankData(view);
                break;
            case R.id.pop_cancle:
                popupWindow.dismiss();
                break;
            case R.id.pop_sure:
                popupWindow.dismiss();
                if (selectPosition == 0) {
                    updateAccountTvBank.setTextColor(getResources().getColor(R.color.gray));
                    updateAccountTvBank.setText("请选择");
                } else {
                    updateAccountTvBank.setTextColor(getResources().getColor(R.color.black));
                    updateAccountTvBank.setText(bankResultBean.getExecDatas().get(selectPosition - 1).getLabel());
                }
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
        } else if ("请选择".equals(updateAccountTvBank.getText().toString())) {
            return 4;
        } else {
            return 3;
        }
    }

    @Override
    protected void onDestroy() {
        ButterKnife.unbind(tvBack);
        super.onDestroy();
    }

    private ParamBean paramBean;
    private ParamBean.ParamData paramData;
    private BankResultBean bankResultBean;

    //获取银行列表
    private void getBankData(final View view) {
        paramBean = new ParamBean();
        paramData = new ParamBean.ParamData();
        paramData.setType("Bank");
        paramBean.setParamData(paramData);
        OkHttpUtils.postString()
                .mediaType(MediaType.parse(Url.CONTENT_TYPE))
                .url(String.format(Url.WEB_SET_BANK, Config.getInstance().getSessionId()))
                .content(new Gson().toJson(paramBean))
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
            }

            @Override
            public void onResponse(String response, int id) throws Exception {
                LogUtil.e("YJL", "bank---" + response);
                if (Tools.isGoodJson(response)) {
                    bankResultBean = new Gson().fromJson(response, BankResultBean.class);
                    if (bankResultBean.isExecResult()) {
                        showServicePopwindow(view);
                    } else {
                    }
                }
            }
        });
    }

    /*更新收款账号*/
    private void updateAccount(String accountName, String accountNumber, String accountBank, String accountBranch) {
        paramBean = new ParamBean();
        paramData = new ParamBean.ParamData();
        paramData.setAccountName(accountName);
        paramData.setAccountNumber(accountNumber);
        paramData.setAccountBank(accountBank);
        paramData.setAccountBranch(accountBranch);
        paramData.setAccountBankName(bankResultBean.getExecDatas().get(selectPosition - 1).getLabel());
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
                        intent.putExtra("KIND", kind);
                        if (kind == 0) {
                            setResult(RESULT_OK, intent);
                        } else {
                            intent.putExtra("VALUE", 0);
                            startActivity(intent);
                        }
                        finish();
                    } else {
                    }
                }
            }
        });
    }

    private PopupWindow popupWindow;
    private WheelView wheelview1;
    private WheelView wheelview2;
    private Button pop_cancle;
    private Button pop_sure;
    private View popView;
    private List<String> list;
    private int selectPosition = 0;

    //服务类型popWindow
    public void showServicePopwindow(View view) {
        LayoutInflater inflater = ((LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE));
        popView = inflater.inflate(R.layout.popwindow_bank, null);
        wheelview1 = ((WheelView) popView.findViewById(R.id.wheelview));
        pop_cancle = ((Button) popView.findViewById(R.id.pop_cancle));
        pop_cancle.setOnClickListener(this);
        pop_sure = ((Button) popView.findViewById(R.id.pop_sure));
        pop_sure.setOnClickListener(this);
        wheelview1.setWheelAdapter(new ArrayWheelAdapter(this)); // 文本数据源
        list = new ArrayList<String>();
        list.add("请选择");
        for (int i = 0; i < bankResultBean.getExecDatas().size(); i++) {
            list.add(bankResultBean.getExecDatas().get(i).getLabel());
        }

        wheelview1.setSelection(0);
        wheelview1.setWheelData(list);
//        wheelview1.setWheelSize(5);
        wheelview1.setSkin(WheelView.Skin.Holo);
        WheelView.WheelViewStyle styles = new WheelView.WheelViewStyle();
        styles.backgroundColor = getResources().getColor(R.color.gray);
        styles.holoBorderColor = getResources().getColor(R.color.text_gray);
        styles.selectedTextSize = 16;
        styles.textSize = 16;
        styles.selectedTextColor = Color.WHITE;
        styles.textColor = getResources().getColor(R.color.black);
        wheelview1.setStyle(styles);
        popupWindow = new PopupWindow(popView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        // 这个是为了点击“返回Back”也能使其消失，并且并不会影响你的背景
//        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        // 使其聚集
        popupWindow.setFocusable(true);
        // 设置背景颜色变暗
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.2f;
        getWindow().setAttributes(lp);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });
        // 设置允许在外点击不消失
        popupWindow.setOutsideTouchable(false);
        popView.setFocusableInTouchMode(true);
        popView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    if (popupWindow != null) {
                        popupWindow.dismiss();
                    }
                }
                return false;
            }
        });
        popupWindow.setAnimationStyle(R.style.mypopwindow_anim_style);
        popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
        wheelview1.setOnWheelItemSelectedListener(new WheelView.OnWheelItemSelectedListener<String>() {
            @Override
            public void onItemSelected(int position, String string) {
                Log.e("YJL--position1", "position1=====" + position);
                selectPosition = position;
            }
        });
    }


}
