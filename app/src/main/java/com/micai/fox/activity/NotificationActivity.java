package com.micai.fox.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
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
import com.micai.fox.app.Constant;
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
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import okhttp3.Call;
import okhttp3.MediaType;

/*消息通知界面*/
public class NotificationActivity extends AppCompatActivity implements AbsListView.OnScrollListener, SwipeRefreshLayout.OnRefreshListener {

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
    @Bind(R.id.notice_sw)
    SwipeRefreshLayout swipeRefreshLayout;
    //    @Bind(R.id.ptr)
//    PtrFrameLayout mPtr;
    private ArrayList<NotificationResultBean.ExecDatasBean.RecordListBean> data = new ArrayList<>();
    private MyNotificationAdapter adapter;
    private BaseResultBean baseResultBean;
    private NotificationResultBean notificationResultBean;
    private View footer_view;
    private TextView tv_foot;

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
        getNotList("1", 1);
        lvNotify.setOnScrollListener(this);
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
                } else if (3 == data.get(i).getType()) {
                    intent = new Intent(NotificationActivity.this, ZhongChouOrderDetailActivity.class);
                    intent.putExtra("orderId", data.get(i).getRelId());
                } else {
                    intent = new Intent(NotificationActivity.this, ZhongChouDetailActivity.class);
                    intent.putExtra("crowdingId", data.get(i).getRelId());
                }
                startActivity(intent);
            }
        });
        //刷新
//        Tools.upLoadGagrProgress(Constant.HOME_BACKGROUND_COLOR, mPtr, this, new PtrHandler() {
//            @Override
//            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
//                LogUtil.e("YJL", "" + isCanRefresh);
//                return isCanRefresh;
//            }
//
//            @Override
//            public void onRefreshBegin(PtrFrameLayout frame) {
//                LogUtil.e("YJL", "的点点滴滴" + isCanRefresh);
//                getNotList("0", 0);
//                //进行刷新
////                frame.autoRefresh();
//                frame.refreshComplete();
//
//            }
//        });
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
        swipeRefreshLayout.setOnRefreshListener(this);

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

    private void getNotList(String pageNum, final int type) {
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
                if (type == 0) {
                    swipeRefreshLayout.setRefreshing(false);
                }
            }

            @Override
            public void onResponse(String response, int id) throws Exception {
                Log.e("yjl", "noti--data" + response);
                if (Tools.isGoodJson(response)) {
                    notificationResultBean = new Gson().fromJson(response, NotificationResultBean.class);
                    if (notificationResultBean.isExecResult()) {
                        if (null != notificationResultBean.getExecDatas()) {
                            if (type == 0) {
                                //下拉刷新
                                data.clear();
                                data.addAll(notificationResultBean.getExecDatas().getRecordList());
                                adapter.notifyDataSetChanged();
                                swipeRefreshLayout.setRefreshing(false);
                            } else {
                                data.addAll(notificationResultBean.getExecDatas().getRecordList());
                                adapter.notifyDataSetChanged();
                            }
                            if (curPageNum < notificationResultBean.getExecDatas().getTotalPage()) {
                                tv_foot.setVisibility(View.VISIBLE);
                            } else {
                                tv_foot.setVisibility(View.GONE);
                            }
                        } else {
                            if (type == 0) {
                                swipeRefreshLayout.setRefreshing(false);
                            }
                        }
                    }
                } else {
                    //TODO 状态禁用
                    if (type == 0) {
                        swipeRefreshLayout.setRefreshing(false);
                    }
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
    public void onRefresh() {
        curPageNum = 1;
        getNotList("1", 0);
    }

    // 下拉刷新
    public interface OnSwipeIsValid {
        void setSwipeEnabledTrue();

        void setSwipeEnabledFalse();
    }

    private OnSwipeIsValid isValid = new OnSwipeIsValid() {
        @Override
        public void setSwipeEnabledTrue() {
            swipeRefreshLayout.setEnabled(true);//让swipe起作用，能够刷新
        }

        @Override
        public void setSwipeEnabledFalse() {
            swipeRefreshLayout.setEnabled(false);//让swipe不能够刷新
        }
    };

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {
        LogUtil.e("YJL---", "+进来了没有");
        //判断ListView是否滑动到第一个Item的顶部
        if (isValid != null && lvNotify.getChildCount() > 0 && lvNotify.getFirstVisiblePosition() == 0
                && lvNotify.getChildAt(0).getTop() >= lvNotify.getPaddingTop()) {
            //解决滑动冲突，当滑动到第一个item，下拉刷新才起作用
            LogUtil.e("YJL", "刷新啊");
            isValid.setSwipeEnabledTrue();
//            isCanRefresh = true;
        } else {
            isValid.setSwipeEnabledFalse();
//            isCanRefresh = false;
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
                    getNotList("" + curPageNum, 1);
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
