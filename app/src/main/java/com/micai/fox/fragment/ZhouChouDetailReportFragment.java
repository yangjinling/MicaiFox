package com.micai.fox.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.micai.fox.R;
import com.micai.fox.adapter.MyZhongchouReportAdapter;
import com.micai.fox.app.Config;
import com.micai.fox.app.Url;
import com.micai.fox.parambean.ParamBean;
import com.micai.fox.resultbean.ZhonchouReportResultBean;
import com.micai.fox.util.Tools;
import com.micai.fox.view.CustomViewPager;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.MediaType;

/**
 * 作者：杨金玲 on 2017/12/27 16:45
 * 邮箱：18363820101@163.com
 */
/*众筹详情--报告模块*/
public class ZhouChouDetailReportFragment extends Fragment {
    @Bind(R.id.zhouchou_detail_report_ll_ing)
    LinearLayout zhouchouDetailReportLlIng;
    @Bind(R.id.zhouchou_detail_report_ll_ed)
    LinearLayout zhouchouDetailReportLlEd;
    @Bind(R.id.lv_report)
    ListView lv_report;
    private int kind;
    //    private TextView tv;
    private ArrayList<ZhonchouReportResultBean.ExecDatasBean> data = new ArrayList<>();
    private ListView lv;
    private View footer_view;
    private View headView;
    private CustomViewPager vp;
    private String crowdingId;
    private ZhonchouReportResultBean zhonchouReportResultBean;

    public ZhouChouDetailReportFragment() {
    }

    @SuppressLint("ValidFragment")
    public ZhouChouDetailReportFragment(CustomViewPager vp) {
        this.vp = vp;
    }

    MyZhongchouReportAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zhouchou_detail_report, container, false);
        ButterKnife.bind(this, view);
        kind = getArguments().getInt("KIND", 0);
        crowdingId = getArguments().getString("crowdingId");
        switch (kind) {
            case 0:
//                tv.setText("全部");
                zhouchouDetailReportLlIng.setVisibility(View.VISIBLE);
                break;
            case 1:
//                tv.setText("盈利榜");
                zhouchouDetailReportLlIng.setVisibility(View.GONE);
                zhouchouDetailReportLlEd.setVisibility(View.VISIBLE);
                break;
        }
        lv_report.setFocusable(false);
        adapter = new MyZhongchouReportAdapter(data, getContext(), R.layout.item_lv_report);
        lv_report.setAdapter(adapter);
        getZhongChouReport(crowdingId);
        vp.setObjectForPosition(view, 1);
        return view;
    }

    private ParamBean paramBean;
    private ParamBean.ParamData paramData;

    private void getZhongChouReport(String crowingId) {
        paramBean = new ParamBean();
        paramData = new ParamBean.ParamData();
        paramData.setCrowdfundingId(crowingId);
        paramBean.setParamData(paramData);
        OkHttpUtils.postString()
                .mediaType(MediaType.parse(Url.CONTENT_TYPE))
                .url(String.format(Url.WEB_HOME_ZHONGCHOU_DETAIL_REPORT, Config.getInstance().getSessionId()))
                .content(new Gson().toJson(paramBean))
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) throws Exception {
                Log.e("yjl", "众筹详情-报告" + response);
                if (Tools.isGoodJson(response)) {
                    zhonchouReportResultBean = new Gson().fromJson(response, ZhonchouReportResultBean.class);
                    if (zhonchouReportResultBean.isExecResult()) {
                        data.add(zhonchouReportResultBean.getExecDatas());
                        adapter.notifyDataSetChanged();

                    }
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
