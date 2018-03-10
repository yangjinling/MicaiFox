package com.micai.fox.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.czy1121.view.CornerLabelView;
import com.micai.fox.R;
import com.micai.fox.resultbean.ReportDetailResultBean;
import com.micai.fox.util.DateUtil;
import com.micai.fox.util.LogUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by lq on 2018/1/10.
 */


/*报告详情里面的列表*/
public class ReportDetailLvAdapter extends MyBaseAdapter<ReportDetailResultBean.ExecDatasBean.GameBean> {
    private List<ReportDetailResultBean.ExecDatasBean.GameBean> mList;
    private Context context;

    public ReportDetailLvAdapter(List<ReportDetailResultBean.ExecDatasBean.GameBean> list, Context context, int resId) {
        super(list, context, resId);
        mList = list;
        this.context = context;
    }

    @Override
    public void setData(ViewHolder viewHolder, int position) {
        boolean all = false;
        ReportDetailResultBean.ExecDatasBean.GameBean bean = mList.get(position);
        LinearLayout popLl = ((LinearLayout) viewHolder.findViewById(R.id.report_detail_ll_pop));
        CornerLabelView pop_zhong;
        CornerLabelView pop_weizhong;
        CornerLabelView rang_zhong;
        CornerLabelView rang_weizhong;
        TextView pop_changci;
        TextView pop_title;
        TextView pop_time;
        TextView pop_homename;
        TextView pop_guestname;
        TextView pop_score;
        TextView pop_homewin;
        TextView pop_ping;
        TextView pop_ke;
        LinearLayout popRang = ((LinearLayout) viewHolder.findViewById(R.id.report_detail_ll_rang));
        String selections = bean.getSelections().replace(",", "");
        String[] split = bean.getSelections().split(",");
        if (selections.contains("R")) {
            //让球
            popLl.setVisibility(View.VISIBLE);
            pop_changci = ((TextView) viewHolder.findViewById(R.id.pop_changci));
            pop_title = ((TextView) viewHolder.findViewById(R.id.pop_title));
            pop_time = ((TextView) viewHolder.findViewById(R.id.pop_time));
            pop_homename = ((TextView) viewHolder.findViewById(R.id.pop_homename));
            pop_guestname = ((TextView) viewHolder.findViewById(R.id.pop_guestname));
            pop_score = ((TextView) viewHolder.findViewById(R.id.pop_score));
            pop_homewin = ((TextView) viewHolder.findViewById(R.id.pop_homewin));
            pop_ping = ((TextView) viewHolder.findViewById(R.id.pop_ping));
            pop_ke = ((TextView) viewHolder.findViewById(R.id.pop_ke));
            for (String s : split) {
                if (s.equals("3") || s.equals("1") || s.equals("0")) {
                    all = true;
                }
            }
            if (all) {
                //让球+胜负平
                popRang.setVisibility(View.VISIBLE);
                pop_zhong = ((CornerLabelView) viewHolder.findViewById(R.id.pop_zhong));
                pop_weizhong = ((CornerLabelView) viewHolder.findViewById(R.id.pop_weizhong));
                rang_zhong = ((CornerLabelView) viewHolder.findViewById(R.id.rang_zhong));
                rang_weizhong = ((CornerLabelView) viewHolder.findViewById(R.id.rang_weizhong));
                TextView rang_changci = ((TextView) viewHolder.findViewById(R.id.rang_changci));
                TextView rang_title = ((TextView) viewHolder.findViewById(R.id.rang_title));
                TextView rang_time = ((TextView) viewHolder.findViewById(R.id.rang_time));
                TextView rang_homename = ((TextView) viewHolder.findViewById(R.id.rang_homename));
                TextView rang_guestname = ((TextView) viewHolder.findViewById(R.id.rang_guestname));
                TextView rang_score = ((TextView) viewHolder.findViewById(R.id.rang_score));
                TextView rang_homewin = ((TextView) viewHolder.findViewById(R.id.rang_homewin));
                TextView rang_ping = ((TextView) viewHolder.findViewById(R.id.rang_ping));
                TextView rang_ke = ((TextView) viewHolder.findViewById(R.id.rang_ke));
                TextView rang_num = ((TextView) viewHolder.findViewById(R.id.rang_num));
                LogUtil.e("YJL", "让球+胜负平");
                pop_homewin.setBackground(null);
                pop_ping.setBackground(null);
                pop_ke.setBackground(null);
                rang_homewin.setBackground(null);
                rang_ping.setBackground(null);
                rang_ke.setBackground(null);
                pop_weizhong.setVisibility(View.GONE);
                pop_zhong.setVisibility(View.GONE);
                rang_weizhong.setVisibility(View.GONE);
                rang_zhong.setVisibility(View.GONE);
                String data = "";
                if (null != bean.getIssue() && !"undefined".equals(bean.getIssue())) {
                    Date dates = new Date(Integer.parseInt(bean.getIssue().substring(0, 4)), Integer.parseInt(bean.getIssue().substring(4, 6)) - 1, Integer.parseInt(bean.getIssue().substring(6)));
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        dates = format.parse(bean.getIssue());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    data = DateUtil.getWeekOfDate(dates);
                    LogUtil.e("YJL", "日期" + Integer.parseInt(bean.getIssue().substring(0, 4)) + (Integer.parseInt(bean.getIssue().substring(4, 6)) - 1) + bean.getIssue().substring(6) + new Date(Integer.parseInt(bean.getIssue().substring(0, 4)), Integer.parseInt(bean.getIssue().substring(4, 6)), Integer.parseInt(bean.getIssue().substring(6))) + data);
                    pop_changci.setText("" + data + bean.getSeq());
                }
                pop_title.setText("[" + bean.getGameName() + "]");
                pop_time.setText("" + DateUtil.getDateToMatchString(bean.getMatchTime()));
                pop_homename.setText("" + bean.getHomeTeamName());
                pop_guestname.setText("" + bean.getGuestTeamName());
                if (null != bean.getHomeScore() && null != bean.getGuestScore()) {
                    pop_score.setText("" + bean.getHomeScore() + ":" + bean.getGuestScore());
                } else {
                    pop_score.setText("VS");
                }
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
                }
                LogUtil.e("YJL", "selection" + bean.getSelections() + "result" + bean.getResult());
                if (null != selections && null != bean.getResult()) {
                    if (selections.contains(bean.getResult())) {
                        LogUtil.e("YJL", "中了");
                        pop_weizhong.setVisibility(View.GONE);
                        pop_zhong.setVisibility(View.VISIBLE);
                    } else {
                        LogUtil.e("YJL", "没中");
                        pop_weizhong.setVisibility(View.VISIBLE);
                        pop_zhong.setVisibility(View.GONE);
                    }
                }
                rang_changci.setText("" + data + bean.getSeq());
                rang_title.setText("[" + bean.getGameName() + "]");
                rang_time.setText("" + DateUtil.getDateToMatchString(bean.getMatchTime()));
                rang_homename.setText("" + bean.getHomeTeamName());
                rang_guestname.setText("" + bean.getGuestTeamName());
                if (null != bean.getHomeScore() && null != bean.getGuestScore()) {
                    rang_score.setText("" + bean.getHomeScore() + ":" + bean.getGuestScore());
                    rang_score.setText("VS");
                }
                rang_homewin.setText("主胜" + bean.getSpr().split(";")[0].split(":")[1]);
                rang_ping.setText("平" + bean.getSpr().split(";")[1].split(":")[1]);
                rang_ke.setText("客胜" + bean.getSpr().split(";")[2].split(":")[1]);
                rang_num.setText("" + bean.getHandicap());
                if (null != split) {
                    for (String content : split) {
                        if (content.equals("3")) {
                            //主胜
                            LogUtil.e("YJL", "主胜" + selections);
                            pop_homewin.setBackgroundResource(R.drawable.redbgtstyle);
                        } else if (content.equals("1")) {
                            //平
                            LogUtil.e("YJL", "平");
                            pop_ping.setBackgroundResource(R.drawable.redbgtstyle);
                        } else if (content.equals("0")) {
                            //客胜
                            LogUtil.e("YJL", "客胜");
                            pop_ke.setBackgroundResource(R.drawable.redbgtstyle);
                        } else if (content.equals("3R")) {
                            //主胜
                            LogUtil.e("YJL", "rang_主胜" + selections);
                            rang_homewin.setBackgroundResource(R.drawable.redbgtstyle);
                        } else if (content.equals("1R")) {
                            //平
                            LogUtil.e("YJL", "rang_平");
                            rang_ping.setBackgroundResource(R.drawable.redbgtstyle);
                        } else if (content.equals("0R")) {
                            //客胜
                            LogUtil.e("YJL", "rang_客胜");
                            rang_ke.setBackgroundResource(R.drawable.redbgtstyle);
                        }
                    }


                }
                if (null != bean.getResultr()) {
                    switch (bean.getResultr()) {
                        case "3R":
                            addImageSpan(context, rang_homewin);
                            break;
                        case "1R":
                            addImageSpan(context, rang_ping);
                            break;
                        case "0R":
                            addImageSpan(context, rang_ke);
                            break;
                    }
                }
                if (null != selections && null != bean.getResultr()) {
                    if (selections.contains(bean.getResultr())) {
                        rang_weizhong.setVisibility(View.GONE);
                        rang_zhong.setVisibility(View.VISIBLE);
                    } else {
                        rang_zhong.setVisibility(View.GONE);
                        rang_weizhong.setVisibility(View.VISIBLE);
                    }
                }
            } else {
                //只有让球
                LogUtil.e("YJL", "让球");
                popLl.setVisibility(View.VISIBLE);
                popRang.setVisibility(View.GONE);
                pop_zhong = ((CornerLabelView) viewHolder.findViewById(R.id.pop_zhong));
                pop_weizhong = ((CornerLabelView) viewHolder.findViewById(R.id.pop_weizhong));
                pop_changci = ((TextView) viewHolder.findViewById(R.id.pop_changci));
                pop_title = ((TextView) viewHolder.findViewById(R.id.pop_title));
                pop_time = ((TextView) viewHolder.findViewById(R.id.pop_time));
                TextView rang_num = ((TextView) viewHolder.findViewById(R.id.pop_rang));
                pop_homename = ((TextView) viewHolder.findViewById(R.id.pop_homename));
                pop_guestname = ((TextView) viewHolder.findViewById(R.id.pop_guestname));
                pop_score = ((TextView) viewHolder.findViewById(R.id.pop_score));
                pop_homewin = ((TextView) viewHolder.findViewById(R.id.pop_homewin));
                pop_ping = ((TextView) viewHolder.findViewById(R.id.pop_ping));
                pop_ke = ((TextView) viewHolder.findViewById(R.id.pop_ke));
                pop_homewin.setBackground(null);
                pop_ping.setBackground(null);
                pop_ke.setBackground(null);
                pop_weizhong.setVisibility(View.GONE);
                pop_zhong.setVisibility(View.GONE);
//            String date = DateUtil.getWeekOfDate(DateUtil.stringToDate(bean.getIssue(), DateUtil.DatePattern.ONLY_DAYS));
                LogUtil.e("YJL", "日期" + Integer.parseInt(bean.getIssue().substring(0, 4)) + (Integer.parseInt(bean.getIssue().substring(4, 6)) - 1) + bean.getIssue().substring(6) + new Date(Integer.parseInt(bean.getIssue().substring(0, 4)), Integer.parseInt(bean.getIssue().substring(4, 6)), Integer.parseInt(bean.getIssue().substring(6)))/*+date*/);
                Date date = new Date(Integer.parseInt(bean.getIssue().substring(0, 4)), Integer.parseInt(bean.getIssue().substring(4, 6)) - 1, Integer.parseInt(bean.getIssue().substring(6)));
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    date = format.parse(bean.getIssue());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                String data = DateUtil.getWeekOfDate(date);
                pop_changci.setText("" + data + bean.getSeq());
                pop_title.setText("[" + bean.getGameName() + "]");
                pop_time.setText("" + DateUtil.getDateToMatchString(bean.getMatchTime()));
                pop_homename.setText("" + bean.getHomeTeamName());
                pop_guestname.setText("" + bean.getGuestTeamName());
                rang_num.setText("" + bean.getHandicap());
                if (null != bean.getHomeScore() && null != bean.getGuestScore()) {
                    pop_score.setText("" + bean.getHomeScore() + ":" + bean.getGuestScore());
                    pop_score.setText("VS");
                }
                pop_homewin.setText("主胜" + bean.getSpr().split(";")[0].split(":")[1]);
                pop_ping.setText("平" + bean.getSpr().split(";")[1].split(":")[1]);
                pop_ke.setText("客胜" + bean.getSpr().split(";")[2].split(":")[1]);
                if (null != selections) {
                    for (String content : split) {
                        if (content.equals("3R")) {
                            //主胜
                            LogUtil.e("YJL", "主胜" + selections);
                            pop_homewin.setBackgroundResource(R.drawable.redbgtstyle);
                        } else if (content.equals("1R")) {
                            //平
                            LogUtil.e("YJL", "平");
                            pop_ping.setBackgroundResource(R.drawable.redbgtstyle);
                        } else if (content.equals("0R")) {
                            //客胜
                            LogUtil.e("YJL", "客胜");
                            pop_ke.setBackgroundResource(R.drawable.redbgtstyle);
                        }
                    }
                }
                if (null != bean.getResultr()) {
                    switch (bean.getResult()) {
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
                }
                LogUtil.e("YJL", "selection" + bean.getSelections() + "result" + bean.getResult());
                if (null != selections && null != bean.getResult()) {
                    if (selections.contains(bean.getResultr())) {
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
        } else {
            //胜负平
            LogUtil.e("YJL", "胜负平");
            popLl.setVisibility(View.VISIBLE);
            popRang.setVisibility(View.GONE);
            pop_zhong = ((CornerLabelView) viewHolder.findViewById(R.id.pop_zhong));
            pop_weizhong = ((CornerLabelView) viewHolder.findViewById(R.id.pop_weizhong));
            pop_changci = ((TextView) viewHolder.findViewById(R.id.pop_changci));
            pop_title = ((TextView) viewHolder.findViewById(R.id.pop_title));
            pop_time = ((TextView) viewHolder.findViewById(R.id.pop_time));
            pop_homename = ((TextView) viewHolder.findViewById(R.id.pop_homename));
            pop_guestname = ((TextView) viewHolder.findViewById(R.id.pop_guestname));
            pop_score = ((TextView) viewHolder.findViewById(R.id.pop_score));
            pop_homewin = ((TextView) viewHolder.findViewById(R.id.pop_homewin));
            pop_ping = ((TextView) viewHolder.findViewById(R.id.pop_ping));
            pop_ke = ((TextView) viewHolder.findViewById(R.id.pop_ke));
            pop_weizhong.setVisibility(View.GONE);
            pop_zhong.setVisibility(View.GONE);
//            String date = DateUtil.getWeekOfDate(DateUtil.stringToDate(bean.getIssue(), DateUtil.DatePattern.ONLY_DAYS));
            LogUtil.e("YJL", "日期" + Integer.parseInt(bean.getIssue().substring(0, 4)) + (Integer.parseInt(bean.getIssue().substring(4, 6)) - 1) + bean.getIssue().substring(6) + new Date(Integer.parseInt(bean.getIssue().substring(0, 4)), Integer.parseInt(bean.getIssue().substring(4, 6)), Integer.parseInt(bean.getIssue().substring(6)))/*+date*/);
            Date date = new Date(Integer.parseInt(bean.getIssue().substring(0, 4)), Integer.parseInt(bean.getIssue().substring(4, 6)) - 1, Integer.parseInt(bean.getIssue().substring(6)));
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            try {
                date = format.parse(bean.getIssue());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String data = DateUtil.getWeekOfDate(date);
            pop_changci.setText("" + data + bean.getSeq());
            pop_title.setText("[" + bean.getGameName() + "]");
            pop_time.setText("" + DateUtil.getDateToMatchString(bean.getMatchTime()));
            pop_homename.setText("" + bean.getHomeTeamName());
            pop_guestname.setText("" + bean.getGuestTeamName());
            if (null != bean.getHomeScore() && null != bean.getGuestScore()) {
                pop_score.setText("" + bean.getHomeScore() + ":" + bean.getGuestScore());
                pop_score.setText("VS");
            }
            pop_homewin.setText("主胜" + bean.getSp().split(";")[0].split(":")[1]);
            pop_ping.setText("平" + bean.getSp().split(";")[1].split(":")[1]);
            pop_ke.setText("客胜" + bean.getSp().split(";")[2].split(":")[1]);
            pop_homewin.setBackground(null);
            pop_ping.setBackground(null);
            pop_ke.setBackground(null);
            if (null != selections) {
                for (String content : split) {
                    if (content.equals("3")) {
                        //主胜
                        LogUtil.e("YJL", "主胜" + selections);
                        pop_homewin.setBackgroundResource(R.drawable.redbgtstyle);
                    } else if (content.equals("1")) {
                        //平
                        LogUtil.e("YJL", "平");
                        pop_ping.setBackgroundResource(R.drawable.redbgtstyle);
                    } else if (content.equals("0")) {
                        //客胜
                        LogUtil.e("YJL", "客胜");
                        pop_ke.setBackgroundResource(R.drawable.redbgtstyle);
                    }
                    notifyDataSetInvalidated();
                }
            }
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
            }
            LogUtil.e("YJL", "selection" + bean.getSelections() + "result" + bean.getResult());
            if (null != selections && null != bean.getResult()) {
                if (selections.contains(bean.getResult())) {
                    LogUtil.e("YJL", "中了");
                    pop_weizhong.setVisibility(View.GONE);
                    pop_zhong.setVisibility(View.VISIBLE);
                } else {
                    LogUtil.e("YJL", "没中");
                    pop_weizhong.setVisibility(View.VISIBLE);
                    pop_zhong.setVisibility(View.GONE);
                }
            }
            notifyDataSetChanged();
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
