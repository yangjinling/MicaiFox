package com.micai.fox.fragment;

import android.content.Intent;
import android.graphics.drawable.RippleDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.micai.fox.R;
import com.micai.fox.activity.ExpertsDetailActivity;
import com.micai.fox.activity.NotificationActivity;
import com.micai.fox.activity.ZhongChouDetailActivity;
import com.micai.fox.activity.ZhongChouOrderDetailActivity;
import com.micai.fox.adapter.MyExpertsListAdapter;
import com.micai.fox.adapter.MyZhonChouAdapter;
import com.micai.fox.app.Config;
import com.micai.fox.app.Url;
import com.micai.fox.parambean.ParamBean;
import com.micai.fox.parambean.ZhongChouRefreshBean;
import com.micai.fox.resultbean.MyZhongChouResultBean;
import com.micai.fox.util.LogUtil;
import com.micai.fox.util.Tools;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.MediaType;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Created by lq on 2018/1/9.
 */

/*我的--我的众筹模块*/
public class MyZhongChouFragment extends Fragment implements AbsListView.OnScrollListener, SwipeRefreshLayout.OnRefreshListener {
    private ArrayList<MyZhongChouResultBean.ExecDatasBean.RecordListBean> data = new ArrayList<>();
    private ListView lv;
    private TextView tv;
    private int kind;
    private View headView;
    private MyZhongChouResultBean myZhongChouResultBean;
    MyZhonChouAdapter adapter;
    private View footer_view;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine_zhongchou, container, false);
        lv = ((ListView) view.findViewById(R.id.mine_zhongchou_lv));
        tv = ((TextView) view.findViewById(R.id.fragment_mine_zhongchou_tv));
        EventBus.getDefault().register(this);
        swipeRefreshLayout = ((SwipeRefreshLayout) view.findViewById(R.id.mine_zhongchou_swp));
        kind = getArguments().getInt("KIND", 0);
        headView = ((LayoutInflater) getContext().getSystemService(LAYOUT_INFLATER_SERVICE)).inflate(R.layout.headview_lv, null);
        lv.addHeaderView(headView);
        footer_view = ((LayoutInflater) getContext().getSystemService(LAYOUT_INFLATER_SERVICE)).inflate(R.layout.footerview_lv_home_zhongchou, null);
        tv_foot = ((TextView) footer_view.findViewById(R.id.foot_tv));
        lv.addFooterView(footer_view);
        adapter = new MyZhonChouAdapter(data, getContext(), R.layout.item_lv_mine_zhongchou);
        lv.setAdapter(adapter);
        switch (kind) {
            case 0:
//                tv.setText("全部");
                getMyZhongChouList(2, "1", 1);
                break;
            case 1:
//                tv.setText("待支付");
                getMyZhongChouList(0, "1", 1);
                break;
            case 2:
//                tv.setText("已支付");
                getMyZhongChouList(1, "1", 1);
                break;
            case 3:
//                tv.setText("已兑换");
                getMyZhongChouList(7, "1", 1);
                break;
        }
//        data = getData();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), ZhongChouOrderDetailActivity.class);
                if (i != 0) {
                    intent.putExtra("orderId", data.get(i - 1).getOrderId());
                    intent.putExtra("orderStatus", data.get(i - 1).getOrderStatus());
                    intent.putExtra("zhongchouStatus", data.get(i - 1).getCrowdfundingStatus());
                }
                startActivity(intent);
            }
        });
        lv.setOnScrollListener(this);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
        swipeRefreshLayout.setOnRefreshListener(this);
        return view;
    }

    private ParamBean paramBean;
    private ParamBean.ParamData paramData;

    private void getMyZhongChouList(int status, final String curPageNum, final int type) {
        Log.e("YJL", "status==" + status);
        paramBean = new ParamBean();
        paramBean.setLength("20");
        paramBean.setPageNum("" + curPageNum);
        if (2 != status) {
            paramData = new ParamBean.ParamData();
            paramData.setStatus("" + status);
        } else {
//            paramData.setStatus("");
        }
        paramBean.setParamData(paramData);
        OkHttpUtils.postString()
                .mediaType(MediaType.parse(Url.CONTENT_TYPE))
                .url(String.format(Url.WEB_MINE_ZHONGCHOU, Config.getInstance().getSessionId()))
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
                LogUtil.e("yjl", "我的众筹-data" + response);
                if (Tools.isGoodJson(response)) {
                    myZhongChouResultBean = new Gson().fromJson(response, MyZhongChouResultBean.class);
                    if (myZhongChouResultBean.isExecResult()) {
                        if (type == 0) {
                            data.clear();
                            if (null != myZhongChouResultBean.getExecDatas().getRecordList()) {
                                data.addAll(myZhongChouResultBean.getExecDatas().getRecordList());
                            }
                            adapter.notifyDataSetChanged();
                            swipeRefreshLayout.setRefreshing(false);
                        } else {
                            if (null != myZhongChouResultBean.getExecDatas().getRecordList()) {
                                data.addAll(myZhongChouResultBean.getExecDatas().getRecordList());
                            }
                            adapter.notifyDataSetChanged();
                        }
                        if (curPageNums < myZhongChouResultBean.getExecDatas().getTotalPage()) {
                            tv_foot.setVisibility(View.VISIBLE);
                        } else {
                            tv_foot.setVisibility(View.GONE);
                        }
                    } else {
                        if (type == 0) {
                            swipeRefreshLayout.setRefreshing(false);
                        }
                    }
                } else {
                    if (type == 0) {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                    //TODO 状态禁用
                    Config.getInstance().setJin(true);
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private boolean isBottom = false;//是否到第20条数据了
    private int curPageNums = 1;


    private NotificationActivity.OnSwipeIsValid isValid = new NotificationActivity.OnSwipeIsValid() {
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
        if (isValid != null && lv.getChildCount() > 0 && lv.getFirstVisiblePosition() == 0
                && lv.getChildAt(0).getTop() >= lv.getPaddingTop()) {
            //解决滑动冲突，当滑动到第一个item，下拉刷新才起作用
            LogUtil.e("YJL", "刷新啊");
            isValid.setSwipeEnabledTrue();
//            isCanRefresh = true;
        } else {
            isValid.setSwipeEnabledFalse();
//            isCanRefresh = false;
        }

    }

    TextView tv_foot;

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
            LogUtil.e("YJL", "isBottom222===" + isBottom);
        }
        if (absListView.getLastVisiblePosition() - 1 >= 20 + ((curPageNums - 1) * 20)) {
            LogUtil.e("YJL---", "absListView.getLastVisiblePosition()==" + absListView.getLastVisiblePosition() + ",,,," + (20 + ((curPageNums - 1) * 25)));
            if (++curPageNums <= myZhongChouResultBean.getExecDatas().getTotalPage()) {
                LogUtil.e("YJL", "curPageNum==" + curPageNums);
//                LogUtil.e("YJL", "total===" + walletDetailResultBean.getTotalPage());
                tv_foot.setVisibility(View.VISIBLE);
                tv_foot.setText("加载中...");
                switch (kind) {
                    case 0:
//                tv.setText("全部");
                        getMyZhongChouList(2, "" + curPageNums, 1);
                        break;
                    case 1:
//                tv.setText("待支付");
                        getMyZhongChouList(0, "" + curPageNums, 1);
                        break;
                    case 2:
//                tv.setText("已支付");
                        getMyZhongChouList(1, "" + curPageNums, 1);
                        break;
                    case 3:
//                tv.setText("已兑换");
                        getMyZhongChouList(7, "" + curPageNums, 1);
                        break;
                }
//                Toast.makeText(getContext(), "加载中…", Toast.LENGTH_SHORT).show();
            } else {
//                Toast.makeText(getContext(), "没有更多了", Toast.LENGTH_SHORT).show();
                tv_foot.setVisibility(View.VISIBLE);
                tv_foot.setText("没有更多了");
            }
        } /*else {
            if (tv_foot.getVisibility() == View.VISIBLE) {
                tv_foot.setVisibility(View.GONE);
            }
        }*/
    }

    @Override
    public void onRefresh() {
        curPageNums = 1;
        switch (kind) {
            case 0:
//                tv.setText("全部");
                getMyZhongChouList(2, "1", 0);
                break;
            case 1:
//                tv.setText("待支付");
                getMyZhongChouList(0, "1", 0);
                break;
            case 2:
//                tv.setText("已支付");
                getMyZhongChouList(1, "1", 0);
                break;
            case 3:
//                tv.setText("已兑换");
                getMyZhongChouList(7, "1", 0);
                break;
        }
    }

    //给网络请求加缓冲小黄圈
    @Subscribe
    public void onEventMainThread(ZhongChouRefreshBean bean) {
        if (bean.isRefresh()) {
            onRefresh();
        }
    }

}
