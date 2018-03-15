package com.micai.fox.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.micai.fox.R;
import com.micai.fox.adapter.CopyReportDetailLvAdapter;
import com.micai.fox.adapter.ReportDetailLvAdapter;
import com.micai.fox.app.Config;
import com.micai.fox.app.Url;
import com.micai.fox.parambean.ParamBean;
import com.micai.fox.resultbean.ReportDetailResultBean;
import com.micai.fox.util.DateUtil;
import com.micai.fox.util.LogUtil;
import com.micai.fox.util.Tools;
import com.micai.fox.util.URLImageParser;
import com.micai.fox.view.MyListView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.MediaType;

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
    ImageView head;
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
    @Bind(R.id.report_detail_tv_zhongchou_title)
    TextView reportDetailTvZhongchouTitle;
    @Bind(R.id.tv_fu)
    TextView tvFu;
    private ArrayList<ReportDetailResultBean.ExecDatasBean.GameBean> data = new ArrayList<>();
    private String reportId;
    private ReportDetailResultBean reportDetailResultBean;
    ReportDetailLvAdapter adapter;
    CopyReportDetailLvAdapter adapters;
    private DisplayImageOptions options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_detail);
        ButterKnife.bind(this);
        rl.setVisibility(View.VISIBLE);
        tvBack.setVisibility(View.VISIBLE);
        reportDetailLv.setFocusable(false);
        tvTitle.setText("报告详情");
        reportId = getIntent().getStringExtra("reportId");
        getReportDetail(reportId);
//        adapter = new ReportDetailLvAdapter(data, ReportDetailActivity.this, R.layout.item_lv_report_detail);
//        reportDetailLv.setAdapter(adapter);
        adapters = new CopyReportDetailLvAdapter(data, ReportDetailActivity.this, R.layout.copy_item_lv_report_detail);
        reportDetailLv.setAdapter(adapters);
        initOption();
    }

    private void initOption() {
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.ic_launcher)
                .showImageForEmptyUri(R.mipmap.ic_launcher)
                .showImageOnFail(R.mipmap.ic_launcher)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .displayer(new FadeInBitmapDisplayer(300))
                .build();
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
                intent.putExtra("crowdingId", reportDetailResultBean.getExecDatas().getReport().getCrowdfundingId());
                startActivity(intent);
                break;
        }
    }


    private ParamBean paramBean;
    private ParamBean.ParamData paramData;

    private void getReportDetail(String reportId) {
        paramBean = new ParamBean();
        paramData = new ParamBean.ParamData();
        paramData.setReportId(reportId);
        paramBean.setParamData(paramData);
        OkHttpUtils.postString()
                .mediaType(MediaType.parse(Url.CONTENT_TYPE))
                .url(Url.WEB_REPORT_DETAIL)
                .content(new Gson().toJson(paramBean))
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) throws Exception {
                Log.e("yjl", "mine--report" + response);
                if (Tools.isGoodJson(response)) {
                    reportDetailResultBean = new Gson().fromJson(response, ReportDetailResultBean.class);
                    if (reportDetailResultBean.isExecResult()) {
                        reportDetailTvTimeTalk.setText(reportDetailResultBean.getExecDatas().getReport().getTitle());
                        reportDetailTvName.setText(reportDetailResultBean.getExecDatas().getReport().getProName());
                        reportDetailTvIntroduce.setText(reportDetailResultBean.getExecDatas().getReport().getProAuth());
                        reportDetailTvRate.setText("" + reportDetailResultBean.getExecDatas().getReport().getHitRate());
                        reportDetailTvTime.setText("" + DateUtil.getDistanceTimes(reportDetailResultBean.getExecDatas().getReport().getCreateDate(), System.currentTimeMillis()) + "发布");
                        Glide.with(ReportDetailActivity.this).load(Url.WEB_BASE_IP + reportDetailResultBean.getExecDatas().getReport().getProPhoto()).asBitmap().placeholder(R.mipmap.ic_launcher_round).error(R.mipmap.ic_launcher_round).into(head);
          /*              CharSequence charSequence = Html.fromHtml(reportDetailResultBean.getExecDatas().getReport().getContent(), new Html.ImageGetter() {
                            @Override
                            public Drawable getDrawable(String arg0) {
                                Drawable d = null;
                                try {
                                    LogUtil.e("YJL", "arg0===" + arg0);
                                    InputStream is = new DefaultHttpClient().execute(new HttpGet(Url.WEB_BASE_IP + arg0)).getEntity().getContent();
                                    Bitmap bm = BitmapFactory.decodeStream(is);
                                    d = new BitmapDrawable(bm);
                                    d.setBounds(0, 0, bm.getWidth(), bm.getHeight());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                return d;
                            }
                        }, null);
                        tvFu.setText(charSequence);
                        tvFu.getPaint().setAntiAlias(true);
                     */
                        if (null != reportDetailResultBean.getExecDatas().getReport().getStatus()) {
                            if ("0".equals(reportDetailResultBean.getExecDatas().getReport().getStatus())) {
                                reportDetailLlAbout.setVisibility(View.GONE);
                            }else {
                                reportDetailLlAbout.setVisibility(View.VISIBLE);
                                reportDetailTvZhongchouTitle.setText("" + reportDetailResultBean.getExecDatas().getReport().getCrowdfundingTitle());
                            }
                        }
                        tvFu.setText(Html.fromHtml(reportDetailResultBean.getExecDatas().getReport().getContent(), new URLImageParser(reportDetailResultBean.getExecDatas().getReport().getContent(), ReportDetailActivity.this, tvFu, options), null));
                        data.addAll(reportDetailResultBean.getExecDatas().getGame());
                        adapters.notifyDataSetChanged();
                    }
                }
            }
        });
    }
}
