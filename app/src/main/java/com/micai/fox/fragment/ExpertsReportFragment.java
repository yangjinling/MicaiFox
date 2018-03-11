package com.micai.fox.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.micai.fox.R;
import com.micai.fox.activity.ReportDetailActivity;
import com.micai.fox.activity.ZhongChouDetailActivity;
import com.micai.fox.adapter.MyExpertsReportAdapter;
import com.micai.fox.adapter.MyExpertsZhonChouAdapter;
import com.micai.fox.app.Config;
import com.micai.fox.app.Url;
import com.micai.fox.parambean.BotomBean;
import com.micai.fox.parambean.ParamBean;
import com.micai.fox.resultbean.ExpertsReportResultBean;
import com.micai.fox.util.LogUtil;
import com.micai.fox.util.Tools;
import com.micai.fox.view.CustomViewPager;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.DialogInShow;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.MediaType;

/**
 * 作者：杨金玲 on 2017/12/27 16:45
 * 邮箱：18363820101@163.com
 */
/*专家--报告模块*/
public class ExpertsReportFragment extends Fragment implements AbsListView.OnScrollListener {
    private int kind;
    //    private TextView tv;
    private ArrayList<ExpertsReportResultBean.ExecDatasBean.RecordListBean> data = new ArrayList<>();
    private ListView lv;
    private View footer_view;
    private CustomViewPager vp;
    private String proId;
    private ExpertsReportResultBean expertsReportResultBean;
    MyExpertsReportAdapter reportAdapter;
    private boolean toast = false;

    public ExpertsReportFragment() {
    }

    @SuppressLint("ValidFragment")
    public ExpertsReportFragment(CustomViewPager vp) {
        this.vp = vp;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zhong_report_detail_experts, container, false);
//        kind = getArguments().getInt("KIND", 0);
        lv = ((ListView) view.findViewById(R.id.experts_detail_zhong_report_lv));
        lv.setFocusable(false);
        EventBus.getDefault().register(this);
        proId = getArguments().getString("proId");
        getExpertsReportList(proId, "0");
        reportAdapter = new MyExpertsReportAdapter(data, getContext(), R.layout.item_lv_experts_report);
        lv.setAdapter(reportAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), ReportDetailActivity.class);
                intent.putExtra("reportId", data.get(i).getReportId());
                startActivity(intent);
            }
        });
//        lv.setOnScrollListener(this);
        vp.setObjectForPosition(view, 1);
        return view;
    }


    private ParamBean paramBean;
    private ParamBean.ParamData paramData;

    private void getExpertsReportList(String proId, String pageNum) {
        paramBean = new ParamBean();
        paramData = new ParamBean.ParamData();
        paramData.setProId((proId));
        paramBean.setParamData(paramData);
        paramBean.setLength("" + 20);
        paramBean.setPageNum(pageNum);
        OkHttpUtils.postString()
                .mediaType(MediaType.parse(Url.CONTENT_TYPE))
                .url(String.format(Url.WEB_EXPERTS_REPORT, Config.getInstance().getSessionId()))
                .content(new Gson().toJson(paramBean))
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) throws Exception {
                Log.e("yjl", "experts-报告-data" + response);
                if (Tools.isGoodJson(response)) {
                    expertsReportResultBean = new Gson().fromJson(response, ExpertsReportResultBean.class);
                    if (expertsReportResultBean.isExecResult()) {
                        data.addAll(expertsReportResultBean.getExecDatas().getRecordList());
                        reportAdapter.notifyDataSetChanged();
                        initLoadMoreTagOp();
//                        currentpage++;
                    }
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        EventBus.getDefault().unregister(this);
        super.onDestroyView();
    }

    public interface OnSwipeIsValid {
        public void setSwipeEnabledTrue();

        public void setSwipeEnabledFalse();
    }

    private ExpertsReportFragment.OnSwipeIsValid isValid = new ExpertsReportFragment.OnSwipeIsValid() {
        @Override
        public void setSwipeEnabledTrue() {
//            walletSwiperefresh.setEnabled(true);//让swipe起作用，能够刷新
//            isCanRefresh = true;
        }

        @Override
        public void setSwipeEnabledFalse() {
//            walletSwiperefresh.setEnabled(false);//让swipe不能够刷新
//            isCanRefresh = false;
        }
    };
    private int lastItem;
    private int totalItem;
    private boolean isBottom = false;//是否到第20条数据了
    private int curPageNum = 0;

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
        //判断ListView是否滑动到第一个Item的顶部
        if (isValid != null && lv.getChildCount() > 0 && lv.getFirstVisiblePosition() == 0
                && lv.getChildAt(0).getTop() >= lv.getPaddingTop()) {
            //解决滑动冲突，当滑动到第一个item，下拉刷新才起作用
            isValid.setSwipeEnabledTrue();
        } else {
            isValid.setSwipeEnabledFalse();
        }
    }

    @Override
    public void onScroll(AbsListView absListView, int i, int i1, int i2) {
        this.lastItem = i + i1;
        this.totalItem = i2;
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
        if (absListView.getLastVisiblePosition() >= 20 + (curPageNum * 20)) {
            LogUtil.e("YJL---", "absListView.getLastVisiblePosition()==" + absListView.getLastVisiblePosition() + ",,,," + (20 + ((curPageNum - 1) * 25)));
            if (++curPageNum <= expertsReportResultBean.getExecDatas().getTotalPage()) {
                LogUtil.e("YJL", "curPageNum==" + curPageNum);
//                LogUtil.e("YJL", "total===" + walletDetailResultBean.getTotalPage());
                getExpertsReportList(proId, "" + curPageNum);
                Toast.makeText(getContext(), "加载中…", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "没有更多了", Toast.LENGTH_SHORT).show();
//                ToolsC.CenterToast(getContext(), "没有更多数据");
            }
        }
    }

    private int pagesize = 20;
    private int currentpage = 1;
    private boolean judgeCanLoadMore = true;
    private int totalCount = 20;//设置本次加载的数据的总数

    //给网络请求加缓冲小黄圈
    @Subscribe
    public void onEventMainThread(BotomBean bean) {
        LogUtil.e("YJL", "isBootom" + bean.isBootom());
        if (bean.isBootom()) {
//            mDialog.show();

        } else {
//            mDialog.dismiss();
            toast=false;

        }
        //模拟进行数据的分页加载
        if (judgeCanLoadMore && bean.isBootom()) {
//            commentLv.startLoading();
//            if (currentpage == 0) {
//                Toast.makeText(getContext(), "没有更多数据了", Toast.LENGTH_SHORT).show();
//            } else {
//                Toast.makeText(getContext(), "正在加载中", Toast.LENGTH_SHORT).show();
//                getZhongChouList(currentpage);
//            }
            if (++currentpage >= expertsReportResultBean.getExecDatas().getTotalPage()) {
                if (!toast) {
                    Toast.makeText(getContext(), "没有更多数据了", Toast.LENGTH_SHORT).show();
                    toast = true;
                }
            } else {
                Toast.makeText(getContext(), "正在加载中", Toast.LENGTH_SHORT).show();
                getExpertsReportList(proId, "" + currentpage);
            }
        }
        if (!judgeCanLoadMore && bean.isBootom()) {
            if (!toast) {
            Toast.makeText(getContext(), "没有更多数据了", Toast.LENGTH_SHORT).show();
            toast=true;}
        }

    }

    private void initLoadMoreTagOp() {
        if (data.size() == 0 || data.size() <= 20 + ((currentpage - 1) * 20)) {//当前获取的数目大于等于总共的数目时表示数据加载完毕，禁止滑动
            judgeCanLoadMore = false;
//            commentLv.loadComplete();
//            Toast.makeText(getContext(), "没有更多数据了", Toast.LENGTH_SHORT).show();
            return;
        }
    }

}
