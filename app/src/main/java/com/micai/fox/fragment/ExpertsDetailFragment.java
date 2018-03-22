package com.micai.fox.fragment;

import android.content.Intent;
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
import com.micai.fox.adapter.MyExpertsListAdapter;
import com.micai.fox.app.Config;
import com.micai.fox.app.Url;
import com.micai.fox.parambean.ParamBean;
import com.micai.fox.resultbean.ExpertsResultBean;
import com.micai.fox.resultbean.HomeResultBean;
import com.micai.fox.util.LogUtil;
import com.micai.fox.util.Tools;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.MediaType;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * 作者：杨金玲 on 2017/12/27 16:45
 * 邮箱：18363820101@163.com
 */

public class ExpertsDetailFragment extends Fragment implements AbsListView.OnScrollListener, SwipeRefreshLayout.OnRefreshListener {
    private int kind;
    //    private TextView tv;
    private List<ExpertsResultBean.ExecDatasBean.RecordListBean> resultBeanList = new ArrayList<>();
    private ListView lv;
    private View footer_view;
    private View headView;
    private ExpertsResultBean expertsResultBean;
    private MyExpertsListAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TextView tv_foot;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_experts, container, false);
        kind = getArguments().getInt("KIND", 0);
        lv = ((ListView) view.findViewById(R.id.experts_fragment_lv));
        swipeRefreshLayout = ((SwipeRefreshLayout) view.findViewById(R.id.experts_swp));
        resultBeanList = new ArrayList<ExpertsResultBean.ExecDatasBean.RecordListBean>();
        headView = ((LayoutInflater) getContext().getSystemService(LAYOUT_INFLATER_SERVICE)).inflate(R.layout.headview_lv, null);
        lv.addHeaderView(headView);
        footer_view = ((LayoutInflater) getContext().getSystemService(LAYOUT_INFLATER_SERVICE)).inflate(R.layout.footerview_lv_home_zhongchou, null);
        tv_foot = ((TextView) footer_view.findViewById(R.id.foot_tv));
        lv.addFooterView(footer_view);
        adapter = new MyExpertsListAdapter(resultBeanList, getContext(), R.layout.item_lv_experts);
        lv.setAdapter(adapter);
        switch (kind) {
            case 0:
//                tv.setText("全部");
                getExpertsList(kind, "1", 1);
                break;
            case 1:
//                tv.setText("盈利榜");
                getExpertsList(kind, "1", 1);
                break;
            case 2:
//                tv.setText("命中榜");
                getExpertsList(kind, "1", 1);
                break;
        }

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i != 0) {
                    Intent intent = new Intent(getActivity(), ExpertsDetailActivity.class);
                    intent.putExtra("proId", expertsResultBean.getExecDatas().getRecordList().get(i - 1).getProId());
                    startActivity(intent);
                }
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

    private void getExpertsList(final int kind, String pageNnum, final int type) {
        paramBean = new ParamBean();
        paramData = new ParamBean.ParamData();
        paramData.setType(("" + kind));
        paramBean.setParamData(paramData);
        paramBean.setLength("" + 20);
        paramBean.setPageNum(pageNnum);
        OkHttpUtils.postString()
                .mediaType(MediaType.parse(Url.CONTENT_TYPE))
                .url(Url.WEB_EXPERTS)
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
                Log.e("yjl", "experts--data" + response);
                if (Tools.isGoodJson(response)) {
                    expertsResultBean = new Gson().fromJson(response, ExpertsResultBean.class);
                    if (expertsResultBean.isExecResult()) {
                        if (type == 0) {
                            resultBeanList.clear();
                            resultBeanList.addAll(expertsResultBean.getExecDatas().getRecordList());
                            adapter.notifyDataSetChanged();
                            swipeRefreshLayout.setRefreshing(false);
                        } else {
//                        resultBeanList.clear();
                            resultBeanList.addAll(expertsResultBean.getExecDatas().getRecordList());
                            adapter.notifyDataSetChanged();
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
                }
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private boolean isBottom = false;//是否到第20条数据了
    private int curPageNum = 1;

    // 下拉刷新
    public interface OnSwipeIsValid {
        void setSwipeEnabledTrue();

        void setSwipeEnabledFalse();
    }

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
        if (absListView.getLastVisiblePosition() >= 20 + ((curPageNum - 1) * 20)) {
            LogUtil.e("YJL---", "absListView.getLastVisiblePosition()==" + absListView.getLastVisiblePosition() + ",,,," + (20 + ((curPageNum - 1) * 25)));
            if (++curPageNum <= expertsResultBean.getExecDatas().getTotalPage()) {
                LogUtil.e("YJL", "curPageNum==" + curPageNum);
//                LogUtil.e("YJL", "total===" + walletDetailResultBean.getTotalPage());
                tv_foot.setVisibility(View.VISIBLE);
                tv_foot.setText("加载中...");
                getExpertsList(kind, "" + curPageNum, 1);
//                Toast.makeText(getContext(), "加载中…", Toast.LENGTH_SHORT).show();
            } else {
                tv_foot.setVisibility(View.VISIBLE);
                tv_foot.setText("没有更多了");
//                Toast.makeText(getContext(), "没有更多了", Toast.LENGTH_SHORT).show();
//                ToolsC.CenterToast(getContext(), "没有更多数据");
            }
        }
    }

    @Override
    public void onRefresh() {
        getExpertsList(kind, "1", 0);
    }
}
