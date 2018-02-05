package com.micai.fox.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.micai.fox.R;
import com.micai.fox.adapter.MyExpertsListAdapter;
import com.micai.fox.app.Config;
import com.micai.fox.app.Url;
import com.micai.fox.parambean.ParamBean;
import com.micai.fox.resultbean.BaseResultBean;
import com.micai.fox.resultbean.ExpertsResultBean;
import com.micai.fox.util.Tools;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.MediaType;

/*修改昵称界面*/
public class ModifyNiChengActivity extends AppCompatActivity {

    @Bind(R.id.tv_back)
    TextView tvBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_notify)
    TextView tvNotify;
    @Bind(R.id.rl)
    RelativeLayout rl;
    @Bind(R.id.modify_tv_nicheng)
    EditText modifyTvNicheng;
    @Bind(R.id.iv_tv_nicheng)
    ImageView ivTvNicheng;
    private String name;
    private BaseResultBean resultBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_ni_cheng);
        ButterKnife.bind(this);
        rl.setVisibility(View.VISIBLE);
        tvBack.setVisibility(View.VISIBLE);
        tvNotify.setVisibility(View.VISIBLE);
        tvNotify.setText("完成");
        tvTitle.setText("修改昵称");
        name = getIntent().getStringExtra("NAME");
        modifyTvNicheng.setHint("" + name);
//        ivTvNicheng.setVisibility(View.VISIBLE);
    }

    @OnClick({R.id.tv_back, R.id.tv_notify, R.id.iv_tv_nicheng})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.tv_notify:
                String name = modifyTvNicheng.getText().toString().trim();
                if (TextUtils.isEmpty(name)) {

                } else {
                    modifyNickName(name);
                }
                break;
            case R.id.iv_tv_nicheng:
                modifyTvNicheng.setHint("");
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    private ParamBean paramBean;
    private ParamBean.ParamData paramData;

    private void modifyNickName(String name) {
        paramBean = new ParamBean();
        paramData = new ParamBean.ParamData();
        paramData.setNickName(name);
        paramBean.setParamData(paramData);
        OkHttpUtils.postString()
                .mediaType(MediaType.parse(Url.CONTENT_TYPE))
                .url(String.format(Url.WEB_MINE_NICK, Config.getInstance().getSessionId()))
                .content(new Gson().toJson(paramBean))
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) throws Exception {
                Log.e("yjl", "nickname--data" + response);
                if (Tools.isGoodJson(response)) {
                    resultBean = new Gson().fromJson(response, BaseResultBean.class);
                    if (resultBean.isExecResult()) {
                        Intent intent = new Intent();
                        intent.putExtra("NAME", modifyTvNicheng.getText().toString());
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                }
            }
        });
    }

}
