package com.micai.fox.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.micai.fox.R;
import com.micai.fox.adapter.MyNotificationAdapter;
import com.micai.fox.app.Config;
import com.micai.fox.app.Url;
import com.micai.fox.parambean.ParamBean;
import com.micai.fox.resultbean.BaseResultBean;
import com.micai.fox.resultbean.HomeResultBean;
import com.micai.fox.resultbean.MineResultBean;
import com.micai.fox.resultbean.NotificationResultBean;
import com.micai.fox.util.Tools;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.MediaType;

/*消息通知界面*/
public class NotificationActivity extends AppCompatActivity {

    @Bind(R.id.tv_back)
    TextView tvBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_notify)
    TextView tvNotify;
    @Bind(R.id.rl)
    RelativeLayout rl;
    @Bind(R.id.lv_notify)
    ListView lvNotify;
    private ArrayList<NotificationResultBean.ExecDatasBean.RecordListBean> data = new ArrayList<>();
    private MyNotificationAdapter adapter;
    private BaseResultBean baseResultBean;
    private NotificationResultBean notificationResultBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        ButterKnife.bind(this);
        tvTitle.setText("通知");
        tvNotify.setText("清空");
        tvNotify.setTextSize(16);
        rl.setVisibility(View.VISIBLE);
        tvBack.setVisibility(View.VISIBLE);
        tvNotify.setVisibility(View.VISIBLE);
        getNotList("0");
        adapter = new MyNotificationAdapter(data, this, R.layout.item_lv_notification);
        lvNotify.setAdapter(adapter);
    }

    @OnClick({R.id.tv_back, R.id.tv_notify})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.tv_notify://清空
                clearList();
                break;
        }
    }

    private ParamBean paramBean;
    private ParamBean.ParamData paramData;

    private void getNotList(String pageNum) {
        paramBean = new ParamBean();
        paramBean.setLength("20");
        paramBean.setPageNum(pageNum);
        OkHttpUtils.postString()
                .mediaType(MediaType.parse(Url.CONTENT_TYPE))
                .url(String.format(Url.WEB_MINE_NOTICE, Config.getInstance().getSessionId()))
                .content(new Gson().toJson(paramBean))
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) throws Exception {
                Log.e("yjl", "noti--data" + response);
                if (Tools.isGoodJson(response)) {
                    notificationResultBean = new Gson().fromJson(response, NotificationResultBean.class);
                    if (notificationResultBean.isExecResult()) {
                        data.addAll(notificationResultBean.getExecDatas().getRecordList());
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }

    private void clearList() {
        paramBean = new ParamBean();
        OkHttpUtils.postString()
                .mediaType(MediaType.parse(Url.CONTENT_TYPE))
                .url(String.format(Url.WEB_MINE_NOTICE_CLEAR, Config.getInstance().getSessionId()))
                .content(new Gson().toJson(paramBean))
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) throws Exception {
                Log.e("yjl", "clearno--data" + response);
                if (Tools.isGoodJson(response)) {
                    baseResultBean = new Gson().fromJson(response, BaseResultBean.class);
                    if (baseResultBean.isExecResult()) {
                        data.clear();
                        adapter.notifyDataSetChanged();
                    } else {
                    }
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        ButterKnife.unbind(this);
        super.onDestroy();
    }
}
