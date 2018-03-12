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
import com.micai.fox.adapter.MyReportAdapter;
import com.micai.fox.app.Config;
import com.micai.fox.app.Url;
import com.micai.fox.parambean.ParamBean;
import com.micai.fox.resultbean.MyReportResultBean;
import com.micai.fox.util.LogUtil;
import com.micai.fox.util.Tools;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.MediaType;

/*我的报告界面*/
public class MyReportActivity extends AppCompatActivity implements AbsListView.OnScrollListener {

    @Bind(R.id.tv_back)
    TextView tvBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.rl)
    RelativeLayout rl;
    @Bind(R.id.lv_myreport)
    ListView lvMyreport;
    private ArrayList<MyReportResultBean.ExecDatasBean.RecordListBean> data = new ArrayList<>();
    private View headView;
    MyReportAdapter adapter;
    private MyReportResultBean myReportResultBean;
    private List<MyReportResultBean.ExecDatasBean.RecordListBean> recordList;
    private View footer_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_report);
        ButterKnife.bind(this);
        rl.setVisibility(View.VISIBLE);
        tvBack.setVisibility(View.VISIBLE);
        tvTitle.setText("我的报告");
        headView = ((LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE)).inflate(R.layout.headview_lv, null);
        lvMyreport.addHeaderView(headView);
        footer_view = ((LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE)).inflate(R.layout.footerview_lv, null);
        lvMyreport.addFooterView(footer_view);
        tv_foot = ((TextView) footer_view.findViewById(R.id.foot_tv));
        getReportList("0");
        adapter = new MyReportAdapter(data, this, R.layout.item_lv_myreport);
        lvMyreport.setAdapter(adapter);
        lvMyreport.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MyReportActivity.this, ReportDetailActivity.class);
                LogUtil.e("YJl", "size==" + data.size() + "position==" + i);
                intent.putExtra("reportId", data.get(i - 1).getReportId());
                startActivity(intent);
            }
        });
        lvMyreport.setOnScrollListener(this);
    }

    @OnClick(R.id.tv_back)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
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

    private void getReportList(String pageNum) {
        paramBean = new ParamBean();
        paramBean.setLength("20");
        paramBean.setPageNum(pageNum);
        OkHttpUtils.postString()
                .mediaType(MediaType.parse(Url.CONTENT_TYPE))
                .url(String.format(Url.WEB_MINE_REPORT, Config.getInstance().getSessionId()))
                .content(new Gson().toJson(paramBean))
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) throws Exception {
                Log.e("yjl", "mine--report" + response);
                if (Tools.isGoodJson(response)) {
                    myReportResultBean = new Gson().fromJson(response, MyReportResultBean.class);
                    if (myReportResultBean.isExecResult()) {
                        recordList = myReportResultBean.getExecDatas().getRecordList();
                        data.addAll(recordList);
                        adapter.notifyDataSetChanged();
                        if (tv_foot.getVisibility() == View.VISIBLE) {
                            tv_foot.setVisibility(View.GONE);
                        }
                    }
                }
            }
        });
    }

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

    private boolean isBottom = false;//是否到第20条数据了
    private int curPageNum = 1;
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
        if (absListView.getLastVisiblePosition() >= 20 + ((curPageNum - 1) * 20)) {
            LogUtil.e("YJL---", "absListView.getLastVisiblePosition()==" + absListView.getLastVisiblePosition() + ",,,," + (20 + ((curPageNum - 1) * 25)));
            if (++curPageNum <= myReportResultBean.getExecDatas().getTotalPage()) {
                LogUtil.e("YJL", "curPageNum==" + curPageNum);
//                LogUtil.e("YJL", "total===" + walletDetailResultBean.getTotalPage());
                tv_foot.setVisibility(View.VISIBLE);
                tv_foot.setText("加载中…");
                getReportList("" + curPageNum);
//                Toast.makeText(this, "加载中…", Toast.LENGTH_SHORT).show();
            } else {
                tv_foot.setVisibility(View.VISIBLE);
                tv_foot.setText("没有更多了");//                ToolsC.CenterToast(getContext(), "没有更多数据");
            }
        } else {
            if (tv_foot.getVisibility() == View.VISIBLE) {
                tv_foot.setVisibility(View.GONE);
            }
        }
    }
}
