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
import android.widget.TextView;
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
import com.micai.fox.util.LvUtil;
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

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * 作者：杨金玲 on 2017/12/27 16:45
 * 邮箱：18363820101@163.com
 */
/*专家--报告模块*/
public class ExpertsReportFragment extends Fragment {
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
        footer_view = ((LayoutInflater) getContext().getSystemService(LAYOUT_INFLATER_SERVICE)).inflate(R.layout.footerview_lv, null);
        tv_foot = ((TextView) footer_view.findViewById(R.id.foot_tv));
        lv.addFooterView(footer_view);
        reportAdapter = new MyExpertsReportAdapter(data, getContext(), R.layout.item_lv_experts_report);
        lv.setAdapter(reportAdapter);
        getExpertsReportList(proId, "0");
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i < data.size()) {
                    Intent intent = new Intent(getActivity(), ReportDetailActivity.class);
                    intent.putExtra("reportId", data.get(i).getReportId());
                    startActivity(intent);
                }
            }
        });
//        lv.setOnScrollListener(this);
//        LvUtil.setListViewHeightBasedOnChildren(lv);
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
                .url(Url.WEB_EXPERTS_REPORT)
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
                        if (tv_foot.getVisibility() == View.VISIBLE)
                            tv_foot.setVisibility(View.GONE);
//                        initLoadMoreTagOp();
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

    private int currentpage = 0;
    private boolean judgeCanLoadMore = true;
    TextView tv_foot;

    //给网络请求加缓冲小黄圈
    @Subscribe
    public void onEventMainThread(BotomBean bean) {
        LogUtil.e("YJL", "isBootom" + bean.isBootom());
     /*   if (bean.isBootom()) {
//            mDialog.show();
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
                    tv_foot = ((TextView) footer_view.findViewById(R.id.foot_tv));
                    tv_foot.setVisibility(View.VISIBLE);
                    tv_foot.setText("没有更多了");
                } else {
                    tv_foot = ((TextView) footer_view.findViewById(R.id.foot_tv));
                    tv_foot.setVisibility(View.VISIBLE);
                    tv_foot.setText("加载中...");
                    getExpertsReportList(proId, "" + currentpage);
                }
            }
            if (!judgeCanLoadMore && bean.isBootom()) {
                tv_foot = ((TextView) footer_view.findViewById(R.id.foot_tv));
                tv_foot.setVisibility(View.VISIBLE);
                tv_foot.setText("没有更多了");
            }
        } else {
            tv_foot = ((TextView) footer_view.findViewById(R.id.foot_tv));
            tv_foot.setVisibility(View.GONE);
        }
*/
        if (bean.isBootom()) {
            LogUtil.e("YJL", "总页数==" + expertsReportResultBean.getExecDatas().getTotalPage() + "--" + data.size());
            if (data.size() < expertsReportResultBean.getExecDatas().getTotalRow()) {
                tv_foot.setVisibility(View.VISIBLE);
                tv_foot.setText("加载中...");
                currentpage++;
                getExpertsReportList(proId, "" + currentpage);
            } else {
                tv_foot.setVisibility(View.VISIBLE);
                tv_foot.setText("没有更多了");
            }
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
