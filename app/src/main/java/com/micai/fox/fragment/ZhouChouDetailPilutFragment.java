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
import android.widget.TextView;

import com.google.gson.Gson;
import com.micai.fox.R;
import com.micai.fox.adapter.MyZhongchouPiLuAdapter;
import com.micai.fox.app.Config;
import com.micai.fox.app.Url;
import com.micai.fox.parambean.ParamBean;
import com.micai.fox.resultbean.MyZhongchouPiluResultBean;
import com.micai.fox.util.LogUtil;
import com.micai.fox.util.Tools;
import com.micai.fox.view.CustomViewPager;
import com.micai.fox.view.MyListView;
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

/*众筹详情--披露模块*/
public class ZhouChouDetailPilutFragment extends Fragment {
    @Bind(R.id.zhouchou_detail_pilu_ll_ing)
    LinearLayout zhouchouDetailPiluLlIng;
    @Bind(R.id.zhouchou_detail_pilu_ll_ed)
    LinearLayout zhouchouDetailPiluLlEd;
    @Bind(R.id.lv_pilu)
    MyListView lvPilu;
    @Bind(R.id.zhongchou_detail_tv_touzhu)
    TextView zhongchouDetailTvTouzhu;
    @Bind(R.id.zhongchou_detail_tv_yingshou)
    TextView zhongchouDetailTvYingshou;
    @Bind(R.id.zhongchou_detail_tv_touzhuyingli)
    TextView zhongchouDetailTvTouzhuyingli;
    @Bind(R.id.zhongchou_detail_tv_touzhuyingli_rate)
    TextView zhongchouDetailTvTouzhuyingliRate;
    @Bind(R.id.zhongchou_detail_tv_duifuyingli)
    TextView zhongchouDetailTvDuifuyingli;
    @Bind(R.id.zhongchou_detail_tv_duifuyingli_rate)
    TextView zhongchouDetailTvDuifuyingliRate;
    @Bind(R.id.zhongchou_detail_iv_touzhu)
    TextView zhongchouDetailIvTouzhu;
    @Bind(R.id.zhongchou_detail_iv_yingshou)
    TextView zhongchouDetailIvYingshou;
    @Bind(R.id.zhongchou_detail_ll_iv_money)
    LinearLayout zhongchouDetailLlIvMoney;
    @Bind(R.id.zhongchou_detail_ll_money)
    LinearLayout zhongchouDetailLlMoney;
    @Bind(R.id.zhongchou_detail_ll_tv_money)
    LinearLayout zhongchouDetailLlTvMoney;
    private int kind;
    //    private TextView tv;
    private ArrayList<MyZhongchouPiluResultBean.ExecDatasBean.BetInfoBean> data = new ArrayList<>();
    private CustomViewPager vp;
    private String crowdingId;
    private MyZhongchouPiLuAdapter adapter;
    private MyZhongchouPiluResultBean myZhongchouPiluResultBean;

    public ZhouChouDetailPilutFragment() {
    }

    @SuppressLint("ValidFragment")
    public ZhouChouDetailPilutFragment(CustomViewPager vp) {
        this.vp = vp;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zhouchou_detail_pilu, container, false);
        ButterKnife.bind(this, view);
        kind = getArguments().getInt("KIND", 0);
        crowdingId = getArguments().getString("crowdingId");
        switch (kind) {
            case 0:
//                tv.setText("全部");
                //已披露之前
                zhouchouDetailPiluLlIng.setVisibility(View.VISIBLE);
                break;
            case 1:
                //已披露之后
//                tv.setText("盈利榜");
                zhouchouDetailPiluLlIng.setVisibility(View.GONE);
                zhouchouDetailPiluLlEd.setVisibility(View.VISIBLE);
                break;
        }
        lvPilu.setFocusable(false);
        adapter = new MyZhongchouPiLuAdapter(data, getContext(), R.layout.item_lv_pilu);
        lvPilu.setAdapter(adapter);
        getZhongchouPilu(crowdingId);
        vp.setObjectForPosition(view, 2);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private ParamBean paramBean;
    private ParamBean.ParamData paramData;

    private void getZhongchouPilu(String crowingId) {
        paramBean = new ParamBean();
        paramData = new ParamBean.ParamData();
        paramData.setCrowdfundingId(crowingId);
        paramBean.setParamData(paramData);
        OkHttpUtils.postString()
                .mediaType(MediaType.parse(Url.CONTENT_TYPE))
                .url(String.format(Url.WEB_HOME_ZHONGCHOU_DETAIL_PILU, Config.getInstance().getSessionId()))
                .content(new Gson().toJson(paramBean))
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) throws Exception {
                Log.e("yjl", "众筹详情-披露" + response);
                if (Tools.isGoodJson(response)) {
                    myZhongchouPiluResultBean = new Gson().fromJson(response, MyZhongchouPiluResultBean.class);
                    if (myZhongchouPiluResultBean.isExecResult()) {
                        data.addAll(myZhongchouPiluResultBean.getExecDatas().getBetInfo());
                        LogUtil.e("YJL", "data.size" + data.size());
                        adapter.notifyDataSetChanged();
                        zhongchouDetailTvTouzhu.setText("￥"+myZhongchouPiluResultBean.getExecDatas().getProfitInfo().getTotalBetAmount());
                        zhongchouDetailTvYingshou.setText("￥"+myZhongchouPiluResultBean.getExecDatas().getProfitInfo().getTotalRevenueAmount());
                        zhongchouDetailLlMoney.setVisibility(View.VISIBLE);
                        zhongchouDetailTvTouzhuyingli.setText(""+myZhongchouPiluResultBean.getExecDatas().getProfitInfo().getTotalProfitAmount());
                        zhongchouDetailTvTouzhuyingliRate.setText(""+myZhongchouPiluResultBean.getExecDatas().getProfitInfo().getDepotProfitRate());
                    }
                }
            }
        });
    }

}
