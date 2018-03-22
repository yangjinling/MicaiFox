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
import com.micai.fox.activity.ExpertsDetailActivity;
import com.micai.fox.activity.ReportDetailActivity;
import com.micai.fox.activity.ZhongChouDetailActivity;
import com.micai.fox.adapter.MyExpertsListAdapter;
import com.micai.fox.adapter.MyExpertsReportAdapter;
import com.micai.fox.adapter.MyExpertsZhonChouAdapter;
import com.micai.fox.app.Config;
import com.micai.fox.app.Url;
import com.micai.fox.parambean.BotomBean;
import com.micai.fox.parambean.ParamBean;
import com.micai.fox.parambean.RreshBean;
import com.micai.fox.resultbean.ExpertsResultBean;
import com.micai.fox.resultbean.ExpertsZhongchouResultBean;
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

/*专家--众筹模块*/
public class ExpertsZhongChouFragment extends Fragment {
    private int kind;
    //    private TextView tv;
    private ArrayList<ExpertsZhongchouResultBean.ExecDatasBean.RecordListBean> data = new ArrayList<>();
    private ListView lv;
    private View footer_view;
    private CustomViewPager vp;
    private String proId;
    private ExpertsZhongchouResultBean expertsZhongchouResultBean;
    MyExpertsZhonChouAdapter adapter;

    public ExpertsZhongChouFragment() {
    }

    @SuppressLint("ValidFragment")
    public ExpertsZhongChouFragment(CustomViewPager customViewPager) {
        this.vp = customViewPager;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zhong_report_detail_experts, container, false);
        lv = ((ListView) view.findViewById(R.id.experts_detail_zhong_report_lv));
        lv.setFocusable(false);
        EventBus.getDefault().register(this);
        proId = getArguments().getString("proId");
//        LvUtil.setListViewHeightBasedOnChildren(lv);
        footer_view = ((LayoutInflater) getContext().getSystemService(LAYOUT_INFLATER_SERVICE)).inflate(R.layout.footerview_lv, null);
        tv_foot = ((TextView) footer_view.findViewById(R.id.foot_tv));
        lv.addFooterView(footer_view);
        adapter = new MyExpertsZhonChouAdapter(data, getContext(), R.layout.item_lv_experts_zhongchou);
        lv.setAdapter(adapter);
        getExpertsZhongChouList(proId, "1", 1);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i < data.size()) {
                    Intent intent = new Intent(getActivity(), ZhongChouDetailActivity.class);
                    intent.putExtra("crowdingId", expertsZhongchouResultBean.getExecDatas().getRecordList().get(i).getCrowdfundingId());
                    startActivity(intent);
                }
            }
        });
//        lv.setOnScrollListener(this);
        vp.setObjectForPosition(view, 0);
//        footer_view = ((LayoutInflater) getContext().getSystemService(LAYOUT_INFLATER_SERVICE)).inflate(R.layout.footerview_lv_home_zhongchou, null);
//        lv.addFooterView(footer_view);
        return view;
    }


    private ParamBean paramBean;
    private ParamBean.ParamData paramData;

    private void getExpertsZhongChouList(String proId, String pageNnum, final int type) {
        paramBean = new ParamBean();
        paramData = new ParamBean.ParamData();
        paramData.setProId((proId));
        paramBean.setParamData(paramData);
        paramBean.setLength("" + 20);
        paramBean.setPageNum(pageNnum);
        OkHttpUtils.postString()
                .mediaType(MediaType.parse(Url.CONTENT_TYPE))
                .url(Url.WEB_EXPERTS_ZHONCHOU)
                .content(new Gson().toJson(paramBean))
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) throws Exception {
                Log.e("yjl", "experts-众筹-data" + response);
                if (Tools.isGoodJson(response)) {
                    expertsZhongchouResultBean = new Gson().fromJson(response, ExpertsZhongchouResultBean.class);
                    if (expertsZhongchouResultBean.isExecResult()) {
                        if (type == 0) {
                            data.clear();
                            currentpage = 1;
                        }
                        data.addAll(expertsZhongchouResultBean.getExecDatas().getRecordList());
                        adapter.notifyDataSetChanged();
                        if (currentpage < expertsZhongchouResultBean.getExecDatas().getTotalPage()) {
                            tv_foot.setVisibility(View.VISIBLE);
                        } else {
                            tv_foot.setVisibility(View.GONE);
                        }
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
    TextView tv_foot;

    //给网络请求加缓冲小黄圈
    @Subscribe
    public void onEventMainThread(BotomBean bean) {
        LogUtil.e("YJL", "isBootom" + bean.isBootom());
        if (bean.isBootom()) {
            LogUtil.e("YJL", "总页数==" + expertsZhongchouResultBean.getExecDatas().getTotalPage() + "--" + data.size());
            if (data.size() < expertsZhongchouResultBean.getExecDatas().getTotalRow()) {
                tv_foot.setVisibility(View.VISIBLE);
                tv_foot.setText("加载中...");
                currentpage++;
                getExpertsZhongChouList(proId, "" + currentpage, 1);
            } else {
                tv_foot.setVisibility(View.VISIBLE);
                tv_foot.setText("没有更多了");
            }
        }
    }

    //给网络请求加缓冲小黄圈
    @Subscribe
    public void onEventMainThread(RreshBean bean) {
        if (bean.isRefresh()) {
            getExpertsZhongChouList(proId, "1", 0);
        }
    }

}
