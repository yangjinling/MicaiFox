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
import com.micai.fox.app.Config;
import com.micai.fox.app.Url;
import com.micai.fox.parambean.ParamBean;
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
    @Bind(R.id.test)
    TextView test;
    private int kind;
    //    private TextView tv;
    private ArrayList<String> data;
    private ListView lv;
    private View footer_view;
    private View headView;
    private CustomViewPager vp;

    public ZhouChouDetailReportFragment() {
    }

    @SuppressLint("ValidFragment")
    public ZhouChouDetailReportFragment(CustomViewPager vp) {
        this.vp = vp;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zhouchou_detail_report, container, false);
        ButterKnife.bind(this, view);
        kind = getArguments().getInt("KIND", 0);
        switch (0) {
            case 0:
//                tv.setText("全部");
                zhouchouDetailReportLlIng.setVisibility(View.VISIBLE);
                break;
            case 1:
//                tv.setText("盈利榜");
                test.setVisibility(View.VISIBLE);
                break;
            case 2:
//                tv.setText("命中榜");
                break;
        }
        data = getData();
        vp.setObjectForPosition(view, 1);
        return view;
    }

    private ArrayList<String> getData() {
        ArrayList<String> data = new ArrayList<>();
        String temp = " item";
        for (int i = 0; i < 50; i++) {
            data.add(i + temp);
        }

        return data;
    }

    private ParamBean paramBean;
    private ParamBean.ParamData paramData;

    private void getZhongChouReport(String reportId) {
        paramBean = new ParamBean();
        paramData = new ParamBean.ParamData();
        paramData.setReportId(reportId);
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
                Log.e("yjl", "众筹详情-data" + response);
                if (Tools.isGoodJson(response)) {

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
