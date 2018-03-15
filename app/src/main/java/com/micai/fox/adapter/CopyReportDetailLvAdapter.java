package com.micai.fox.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.czy1121.view.CornerLabelView;
import com.micai.fox.R;
import com.micai.fox.resultbean.ReportDetailResultBean;
import com.micai.fox.util.DateUtil;
import com.micai.fox.util.LogUtil;

import java.util.Date;
import java.util.List;

/**
 * Created by lq on 2018/1/10.
 */


/*报告详情里面的列表*/
public class CopyReportDetailLvAdapter extends MyBaseAdapter<ReportDetailResultBean.ExecDatasBean.GameBean> {
    private Context context;
    private List<ReportDetailResultBean.ExecDatasBean.GameBean> mList;

    public CopyReportDetailLvAdapter(List<ReportDetailResultBean.ExecDatasBean.GameBean> list, Context context, int resId) {
        super(list, context, resId);
        this.context = context;
        this.mList = list;
    }

    @Override
    public void setData(ViewHolder viewHolder, int position) {
        LinearLayout ll_detail = ((LinearLayout) viewHolder.findViewById(R.id.report_detail_lls));
        viewHolder.needReInflate = true;
        ReportDetailResultBean.ExecDatasBean.GameBean bean = mList.get(position);
        String[] split = bean.getSelections().split(",");
        for (String content : split) {
            LogUtil.e("YJL", "content==" + content);
            View view = LayoutInflater.from(context).inflate(R.layout.item_lv_report_detail, null);
            ll_detail.addView(view);
            CornerLabelView pop_zhong = ((CornerLabelView) view.findViewById(R.id.pop_zhong));
            CornerLabelView pop_weizhong = ((CornerLabelView) view.findViewById(R.id.pop_weizhong));
            TextView pop_changci = ((TextView) view.findViewById(R.id.pop_changci));
            TextView pop_title = ((TextView) view.findViewById(R.id.pop_title));
            TextView pop_time = ((TextView) view.findViewById(R.id.pop_time));
            TextView pop_homename = ((TextView) view.findViewById(R.id.pop_homename));
            TextView pop_guestname = ((TextView) view.findViewById(R.id.pop_guestname));
            TextView pop_score = ((TextView) view.findViewById(R.id.pop_score));
            TextView pop_homewin = ((TextView) view.findViewById(R.id.pop_homewin));
            TextView pop_ping = ((TextView) view.findViewById(R.id.pop_ping));
            TextView pop_ke = ((TextView) view.findViewById(R.id.pop_ke));
            TextView pop_status = ((TextView) view.findViewById(R.id.pop_status));
            TextView rang_nums = ((TextView) view.findViewById(R.id.pop_rang));
            pop_weizhong.setVisibility(View.GONE);
            pop_zhong.setVisibility(View.GONE);
            if (null != bean.getIssue()) {
                String data = DateUtil.getWeekByDateStr(bean.getIssue());
                pop_changci.setText("" + data + bean.getSeq());
            }
            pop_title.setText("[" + bean.getGameName() + "]");
            pop_time.setText("" + DateUtil.getDateToMatchString(bean.getMatchTime()));
            pop_homename.setText("" + bean.getHomeTeamName());
            pop_guestname.setText("" + bean.getGuestTeamName());
            if (DateUtil.comPareToTime(DateUtil.getDateToStringToReport(System.currentTimeMillis()), DateUtil.getDateToStringToReport(bean.getMatchTime()))) {
                if (null != bean.getHomeScore() && null != bean.getGuestScore()) {
                    pop_score.setText("" + bean.getHomeScore() + ":" + bean.getGuestScore());
                    pop_status.setText("已结束");
                } else {
                    pop_score.setText("VS");
                    pop_status.setVisibility(View.GONE);
                }
            } else {
                pop_status.setText("未开始");
            }
            pop_homewin.setBackground(null);
            pop_ping.setBackground(null);
            pop_ke.setBackground(null);
            if (content.equals("3") || content.equals("3R")) {
                //主胜
                pop_homewin.setBackgroundResource(R.drawable.redbgtstyle);
            } else if (content.equals("1") || content.equals("1R")) {
                //平
                pop_ping.setBackgroundResource(R.drawable.redbgtstyle);
            } else if (content.equals("0") || content.equals("0R")) {
                //客胜
                pop_ke.setBackgroundResource(R.drawable.redbgtstyle);
            }
            if (content.contains("R")) {
                LogUtil.e("adpter", "让球胜负平");
                pop_homewin.setText("主胜" + bean.getSpr().split(";")[0].split(":")[1]);
                pop_ping.setText("平" + bean.getSpr().split(";")[1].split(":")[1]);
                pop_ke.setText("客胜" + bean.getSpr().split(";")[2].split(":")[1]);
                rang_nums.setText("" + bean.getHandicap());
                if (null != bean.getResultr()) {
                    switch (bean.getResultr()) {
                        case "3R":
                            addImageSpan(context, pop_homewin);
                            break;
                        case "1R":
                            addImageSpan(context, pop_ping);
                            break;
                        case "0R":
                            addImageSpan(context, pop_ke);
                            break;
                    }
                    if (content.equals(bean.getResultr())) {
                        LogUtil.e("YJL", "中了");
                        pop_weizhong.setVisibility(View.GONE);
                        pop_zhong.setVisibility(View.VISIBLE);
                    } else {
                        LogUtil.e("YJL", "没中");
                        pop_weizhong.setVisibility(View.VISIBLE);
                        pop_zhong.setVisibility(View.GONE);
                    }
                }
            } else {
                LogUtil.e("adpter", "胜负平");
                pop_homewin.setText("主胜" + bean.getSp().split(";")[0].split(":")[1]);
                pop_ping.setText("平" + bean.getSp().split(";")[1].split(":")[1]);
                pop_ke.setText("客胜" + bean.getSp().split(";")[2].split(":")[1]);
                if (null != bean.getResult()) {
                    switch (bean.getResult()) {
                        case "3":
                            addImageSpan(context, pop_homewin);
                            break;
                        case "1":
                            addImageSpan(context, pop_ping);
                            break;
                        case "0":
                            addImageSpan(context, pop_ke);
                            break;
                    }
                    if (content.equals(bean.getResult())) {
                        LogUtil.e("YJL", "中了");
                        pop_weizhong.setVisibility(View.GONE);
                        pop_zhong.setVisibility(View.VISIBLE);
                    } else {
                        LogUtil.e("YJL", "没中");
                        pop_weizhong.setVisibility(View.VISIBLE);
                        pop_zhong.setVisibility(View.GONE);
                    }
                }
            }
        }
    }

    private void addImageSpan(Context context, TextView tv) {
        SpannableString spanString = new SpannableString("  ");
        Drawable d = context.getResources().getDrawable(R.mipmap.right);
        d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
        ImageSpan span = new ImageSpan(d, ImageSpan.ALIGN_BASELINE);
        spanString.setSpan(span, 1, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.append(spanString);
    }
}
