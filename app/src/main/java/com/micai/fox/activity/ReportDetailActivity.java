package com.micai.fox.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.micai.fox.R;
import com.micai.fox.adapter.MyReportAdapter;
import com.micai.fox.adapter.ReportDetailLvAdapter;
import com.micai.fox.view.MyListView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*报告详情页面*/
public class ReportDetailActivity extends AppCompatActivity {

    @Bind(R.id.tv_back)
    TextView tvBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.rl)
    RelativeLayout rl;
    @Bind(R.id.report_detail_tv_time_talk)
    TextView reportDetailTvTimeTalk;
    @Bind(R.id.report_detail_tv_time)
    TextView reportDetailTvTime;
    @Bind(R.id.report_detail_iv_head)
    ImageView reportDetailIvHead;
    @Bind(R.id.report_detail_tv_name)
    TextView reportDetailTvName;
    @Bind(R.id.report_detail_tv_introduce)
    TextView reportDetailTvIntroduce;
    @Bind(R.id.report_detail_tv_changci)
    TextView reportDetailTvChangci;
    @Bind(R.id.report_detail_tv_rate)
    TextView reportDetailTvRate;
    @Bind(R.id.report_detail_lv)
    MyListView reportDetailLv;
    @Bind(R.id.report_detail_ll_about)
    LinearLayout reportDetailLlAbout;
    private ArrayList<String> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_detail);
        ButterKnife.bind(this);
        rl.setVisibility(View.VISIBLE);
        tvBack.setVisibility(View.VISIBLE);
        tvTitle.setText("报告详情");
        data = getData();
        ReportDetailLvAdapter adapter = new ReportDetailLvAdapter(data, this, R.layout.item_lv_report_detail);
        reportDetailLv.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.report_detail_ll_about, R.id.tv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.report_detail_ll_about:
                Intent intent = new Intent(ReportDetailActivity.this, ZhongChouDetailActivity.class);
                startActivity(intent);
                break;
        }
    }

    private ArrayList<String> getData() {
        ArrayList<String> data = new ArrayList<>();
        String temp = " item";
        for (int i = 0; i < 2; i++) {
            data.add(i + temp);
        }

        return data;
    }
}
