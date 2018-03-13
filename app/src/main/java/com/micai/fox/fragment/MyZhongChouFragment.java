package com.micai.fox.fragment;

import android.content.Intent;
import android.graphics.drawable.RippleDrawable;
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
import com.micai.fox.activity.ZhongChouDetailActivity;
import com.micai.fox.activity.ZhongChouOrderDetailActivity;
import com.micai.fox.adapter.MyExpertsListAdapter;
import com.micai.fox.adapter.MyZhonChouAdapter;
import com.micai.fox.app.Config;
import com.micai.fox.app.Url;
import com.micai.fox.parambean.ParamBean;
import com.micai.fox.resultbean.MyZhongChouResultBean;
import com.micai.fox.util.LogUtil;
import com.micai.fox.util.Tools;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.MediaType;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Created by lq on 2018/1/9.
 */

/*我的--我的众筹模块*/
public class MyZhongChouFragment extends Fragment implements AbsListView.OnScrollListener {
    private ArrayList<MyZhongChouResultBean.ExecDatasBean.RecordListBean> data = new ArrayList<>();
    private ListView lv;
    private TextView tv;
    private int kind;
    private View headView;
    private MyZhongChouResultBean myZhongChouResultBean;
    MyZhonChouAdapter adapter;
    private View footer_view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine_zhongchou, container, false);
        lv = ((ListView) view.findViewById(R.id.mine_zhongchou_lv));
        tv = ((TextView) view.findViewById(R.id.fragment_mine_zhongchou_tv));
        kind = getArguments().getInt("KIND", 0);
        switch (kind) {
            case 0:
                adapter = new MyZhonChouAdapter(data, getContext(), R.layout.item_lv_mine_zhongchou);
//                tv.setText("全部");
                getMyZhongChouList(2, "0");
                break;
            case 1:
//                tv.setText("待支付");
                adapter = new MyZhonChouAdapter(data, getContext(), R.layout.item_lv_mine_zhongchou);
                getMyZhongChouList(0, "0");
                break;
            case 2:
//                tv.setText("已支付");
                adapter = new MyZhonChouAdapter(data, getContext(), R.layout.item_lv_mine_zhongchou);
                getMyZhongChouList(1, "0");
                break;
            case 3:
//                tv.setText("已兑换");
                adapter = new MyZhonChouAdapter(data, getContext(), R.layout.item_lv_mine_zhongchou);
                getMyZhongChouList(7, "0");
                break;
        }
//        data = getData();
        headView = ((LayoutInflater) getContext().getSystemService(LAYOUT_INFLATER_SERVICE)).inflate(R.layout.headview_lv, null);
        lv.addHeaderView(headView);
        footer_view = ((LayoutInflater) getContext().getSystemService(LAYOUT_INFLATER_SERVICE)).inflate(R.layout.footerview_lv, null);
        lv.addFooterView(footer_view);
        tv_foot = ((TextView) footer_view.findViewById(R.id.foot_tv));
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), ZhongChouOrderDetailActivity.class);
                intent.putExtra("orderId", data.get(i - 1).getOrderId());
                intent.putExtra("orderStatus", data.get(i - 1).getOrderStatus());
                intent.putExtra("zhongchouStatus", data.get(i - 1).getCrowdfundingStatus());
                startActivity(intent);
            }
        });
        lv.setOnScrollListener(this);
        return view;
    }

    private ParamBean paramBean;
    private ParamBean.ParamData paramData;

    private void getMyZhongChouList(int status, String curPageNum) {
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

            }

            @Override
            public void onResponse(String response, int id) throws Exception {
                LogUtil.e("yjl", "我的众筹-data" + response);
                if (Tools.isGoodJson(response)) {
                    myZhongChouResultBean = new Gson().fromJson(response, MyZhongChouResultBean.class);
                    if (myZhongChouResultBean.isExecResult()) {
//                        data.clear();
                        data.addAll(myZhongChouResultBean.getExecDatas().getRecordList());
                        adapter.notifyDataSetChanged();
                        if (tv_foot.getVisibility() == View.VISIBLE) {
                            tv_foot.setVisibility(View.GONE);
                        }
                    }
                }else {
                    //TODO 状态禁用
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
            if (++curPageNum <= myZhongChouResultBean.getExecDatas().getTotalPage()) {
                LogUtil.e("YJL", "curPageNum==" + curPageNum);
//                LogUtil.e("YJL", "total===" + walletDetailResultBean.getTotalPage());
                tv_foot.setVisibility(View.VISIBLE);
                tv_foot.setText("加载中…");
                getMyZhongChouList(kind, "" + curPageNum);
//                Toast.makeText(getContext(), "加载中…", Toast.LENGTH_SHORT).show();
            } else {
//                Toast.makeText(getContext(), "没有更多了", Toast.LENGTH_SHORT).show();
                tv_foot.setVisibility(View.VISIBLE);
                tv_foot.setText("没有更多了");
            }
        } else {
            if (tv_foot.getVisibility() == View.VISIBLE) {
                tv_foot.setVisibility(View.GONE);
            }
        }
    }

}
