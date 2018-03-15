package com.micai.fox.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.micai.fox.R;
import com.micai.fox.adapter.MyNotificationAdapter;
import com.micai.fox.app.Config;
import com.micai.fox.app.Url;
import com.micai.fox.parambean.NotiBean;
import com.micai.fox.parambean.ParamBean;
import com.micai.fox.resultbean.BaseResultBean;
import com.micai.fox.resultbean.HomeResultBean;
import com.micai.fox.resultbean.MineResultBean;
import com.micai.fox.resultbean.NotificationResultBean;
import com.micai.fox.util.LogUtil;
import com.micai.fox.util.Tools;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.MediaType;

/*消息通知界面*/
public class NotificationActivity extends AppCompatActivity implements AbsListView.OnScrollListener {

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
    private View footer_view;
    private TextView tv_foot;
    private boolean isFirst = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        ButterKnife.bind(this);
        NotiBean bean = new NotiBean();
        bean.setHaveN(false);
        EventBus.getDefault().post(bean);
        lvNotify.setFocusable(false);
        tvTitle.setText("通知");
        tvNotify.setText("清空");
        tvNotify.setTextSize(16);
        rl.setVisibility(View.VISIBLE);
        tvBack.setVisibility(View.VISIBLE);
        tvNotify.setVisibility(View.VISIBLE);
        footer_view = ((LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE)).inflate(R.layout.footerview_lv_home_zhongchou, null);
        lvNotify.addFooterView(footer_view);
        tv_foot = ((TextView) footer_view.findViewById(R.id.foot_tv));
        adapter = new MyNotificationAdapter(data, this, R.layout.item_lv_notification);
        lvNotify.setAdapter(adapter);
        getNotList("0");
        lvNotify.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent;
                //将当前选中的position设置为选中的id
                adapter.setSelectedId(i);
                //千万不要忘了刷新数据
                adapter.notifyDataSetInvalidated();
                if (4 == data.get(i).getType()) {
                    intent = new Intent(NotificationActivity.this, ReportDetailActivity.class);
                    intent.putExtra("reportId", data.get(i).getRelId());
                } else {
                    intent = new Intent(NotificationActivity.this, ZhongChouOrderDetailActivity.class);
                    intent.putExtra("orderId", data.get(i).getRelId());
                }
                startActivity(intent);
            }
        });
        lvNotify.setOnScrollListener(this);
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
                } else {
                    //TODO 状态禁用
                    Config.getInstance().setJin(true);
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

    private boolean isBottom = false;//是否到第20条数据了
    private int curPageNum = 1;

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {
        LogUtil.e("YJL---", "+进来了没有");
        switch (i) {
            case SCROLL_STATE_IDLE://空闲状态
                LogUtil.e("YJL", "+进来了没有空闲");
                break;
            case SCROLL_STATE_TOUCH_SCROLL://滚状态
                LogUtil.e("YJL", "+进来了没有滚");
                break;
            case SCROLL_STATE_FLING://飞状态
                LogUtil.e("YJL", "+进来了没有飞");
                break;
        }
    }

    @Override
    public void onScroll(AbsListView absListView, int i, int i1, int i2) {
              /*i:屏幕中第一个可见item的下标
              * i1:可见item数量
            * i2:itme的总数量*/
        if (i2 != 0 && i + i1 == i2) {
            isBottom = true;
            LogUtil.e("YJL", "isBottom111===" + isBottom);
        } else {
            isBottom = false;
            tv_foot.setVisibility(View.GONE);
            LogUtil.e("YJL", "isBottom222===" + isBottom);
        }
        if (isBottom) {
            if (absListView.getLastVisiblePosition() >= 20 + ((curPageNum - 1) * 20)) {
                LogUtil.e("YJL---", "absListView.getLastVisiblePosition()==" + absListView.getLastVisiblePosition() + ",,,," + (20 + ((curPageNum - 1) * 25)));
                if (++curPageNum <= notificationResultBean.getExecDatas().getTotalPage()) {
                    LogUtil.e("YJL", "curPageNum==" + curPageNum);
//                LogUtil.e("YJL", "total===" + walletDetailResultBean.getTotalPage());
                    getNotList("" + curPageNum);
                    tv_foot.setVisibility(View.VISIBLE);
                    tv_foot.setText("加载中…");
//                Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
                } else {
                    tv_foot.setVisibility(View.VISIBLE);
                    tv_foot.setText("没有更多了");
//                Toast.makeText(this, "没有更多了", Toast.LENGTH_SHORT).show();
//                ToolsC.CenterToast(getContext(), "没有更多数据");
                }
            }
        }
    }

}
