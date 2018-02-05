package com.micai.fox.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.micai.fox.R;
import com.micai.fox.adapter.MyReportAdapter;
import com.micai.fox.app.Config;
import com.micai.fox.app.Url;
import com.micai.fox.parambean.ParamBean;
import com.micai.fox.util.Tools;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.MediaType;

/*我的报告界面*/
public class MyReportActivity extends AppCompatActivity {

    @Bind(R.id.tv_back)
    TextView tvBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.rl)
    RelativeLayout rl;
    @Bind(R.id.lv_myreport)
    ListView lvMyreport;
    private ArrayList<String> data;
    private View headView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_report);
        ButterKnife.bind(this);
        rl.setVisibility(View.VISIBLE);
        tvBack.setVisibility(View.VISIBLE);
        tvTitle.setText("我的报告");
        data = getData();
        headView = ((LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE)).inflate(R.layout.headview_lv, null);
        lvMyreport.addHeaderView(headView);
        getReportList("0");
        MyReportAdapter adapter = new MyReportAdapter(data, this, R.layout.item_lv_myreport);
        lvMyreport.setAdapter(adapter);
        lvMyreport.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MyReportActivity.this, ReportDetailActivity.class);
                startActivity(intent);
            }
        });
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

                }
            }
        });
    }
}
