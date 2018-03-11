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

import com.micai.fox.R;
import com.micai.fox.resultbean.MyZhongchouPiluResultBean;
import com.micai.fox.util.DateUtil;
import com.micai.fox.util.LogUtil;

import java.util.Date;
import java.util.List;

/**
 * Created by lq on 2018/1/8.
 */

/*众筹详情---披露*/
public class MyZhongchouPiLuAdapter extends MyBaseAdapter<MyZhongchouPiluResultBean.ExecDatasBean.BetInfoBean> {
    private List<MyZhongchouPiluResultBean.ExecDatasBean.BetInfoBean> mList;
    private Context context;

    public MyZhongchouPiLuAdapter(List<MyZhongchouPiluResultBean.ExecDatasBean.BetInfoBean> list, Context context, int resId) {
        super(list, context, resId);
        this.mList = list;
        this.context = context;
    }

    @Override
    public void setData(ViewHolder viewHolder, int position) {
        LogUtil.e("YJL", "" + mList.size());
        MyZhongchouPiluResultBean.ExecDatasBean.BetInfoBean infoBean = mList.get(position);
        LinearLayout ll_parent = ((LinearLayout) viewHolder.findViewById(R.id.pilu_ll));
        TextView pilu_tv_touzhu = ((TextView) viewHolder.findViewById(R.id.pilu_tv_touzhu));
        TextView pilu_tv_status = ((TextView) viewHolder.findViewById(R.id.pilu_tv_status));
        TextView pilu_tv_touzhu_money = ((TextView) viewHolder.findViewById(R.id.pilu_tv_touzhu_money));
        TextView pilu_tv_yingshou_money = ((TextView) viewHolder.findViewById(R.id.pilu_tv_yingshou_money));
        pilu_tv_touzhu.setText("" + infoBean.getBetnum() + "注 " + infoBean.getSeqnum() + "场 " + infoBean.getSeqnum() + "串1 方案" + infoBean.getMultiple() + "倍");
        pilu_tv_touzhu_money.setText("" + infoBean.getBetAmount());
        pilu_tv_yingshou_money.setText("" + infoBean.getRevenueAmount());
        List<MyZhongchouPiluResultBean.ExecDatasBean.BetInfoBean.MatchBean> matchBeanList = infoBean.getMatch();
        LogUtil.e("YJL", "size" + matchBeanList.size());
        LinearLayout pilu_ll_yingshou = ((LinearLayout) viewHolder.findViewById(R.id.pilu_ll_yingshou));
        for (MyZhongchouPiluResultBean.ExecDatasBean.BetInfoBean.MatchBean matchBean : matchBeanList) {
            View view = LayoutInflater.from(context).inflate(R.layout.pilu, null);
            ll_parent.addView(view);
            ((TextView) view.findViewById(R.id.pilu_tv_country1)).setText("" + matchBean.getHomeTeamName() + " ");
            TextView pilu_tv_score = ((TextView) view.findViewById(R.id.pilu_tv_score));
            if (infoBean.getStatus() == 6) {
                pilu_tv_score.setText("" + matchBean.getHomeScore() + ":" + matchBean.getGuestScore());
            } else {
                pilu_tv_score.setText("VS");
            }
            ((TextView) view.findViewById(R.id.pilu_tv_country2)).setText(" " + matchBean.getGuestTeamName());
            ((TextView) view.findViewById(R.id.pilu_tv_country2)).setText(" " + matchBean.getGuestTeamName());
            TextView changci = ((TextView) viewHolder.findViewById(R.id.pilu_tv_changci));
            LinearLayout rang_ll = ((LinearLayout) view.findViewById(R.id.pilu_ll_rang));
            View pilu_view_rang = ((View) view.findViewById(R.id.pilu_view_rang));
            TextView tv_wanfa1 = ((TextView) view.findViewById(R.id.pilu_content_wanfa1));
            TextView tv_wanfa1_result = ((TextView) view.findViewById(R.id.pilu_content_wanfa1_result));
            TextView pilu_content_touzhu1 = ((TextView) view.findViewById(R.id.pilu_content_touzhu1));
            TextView pilu_content_touzhu2 = ((TextView) view.findViewById(R.id.pilu_content_touzhu2));
            TextView pilu_content_touzhu3 = ((TextView) view.findViewById(R.id.pilu_content_touzhu3));
            TextView tv_wanfa2 = ((TextView) view.findViewById(R.id.pilu_content_wanfa2));
            TextView tv_wanfa2_result = ((TextView) view.findViewById(R.id.pilu_content_wanfa2_result));
            TextView pilu_content_rang_touzhu1 = ((TextView) view.findViewById(R.id.pilu_content_rang_touzhu1));
            TextView pilu_content_rang_touzhu2 = ((TextView) view.findViewById(R.id.pilu_content_rang_touzhu2));
            TextView pilu_content_rang_touzhu3 = ((TextView) view.findViewById(R.id.pilu_content_rang_touzhu3));
            Date date = new Date(Integer.parseInt(matchBean.getIssue().substring(0, 4)), Integer.parseInt(matchBean.getIssue().substring(4, 6)) - 1, Integer.parseInt(matchBean.getIssue().substring(6)));
            String data = DateUtil.getWeekOfDate(date);
            changci.setText("" + data + matchBean.getSeq());
            String[] split = matchBean.getSelections().split(",");
            boolean all = false;
            if (matchBean.getSelections().contains("R")) {
                for (String s : split) {
                    if (s.equals("3") || s.equals("1") || s.equals("0")) {
                        all = true;
                    }
                }
                if (all) {
                    //让球+胜负平
                    viewHolder.needReInflate=true;
                    rang_ll.setVisibility(View.VISIBLE);
                    pilu_view_rang.setVisibility(View.VISIBLE);
                    pilu_content_rang_touzhu1.setVisibility(View.GONE);
                    pilu_content_rang_touzhu2.setVisibility(View.GONE);
                    pilu_content_rang_touzhu3.setVisibility(View.GONE);
                    pilu_content_touzhu1.setVisibility(View.GONE);
                    pilu_content_touzhu2.setVisibility(View.GONE);
                    pilu_content_touzhu2.setVisibility(View.GONE);
                    tv_wanfa1.setText("胜负平");
                    tv_wanfa2.setText("让球(" + matchBean.getHandicap() + ")");
                    if (null != split) {
                        for (String content : split) {
                            if (content.equals("3R")) {
                                pilu_content_rang_touzhu1.setVisibility(View.VISIBLE);
                                pilu_content_rang_touzhu1.setText("让球胜" + matchBean.getSpr().split(";")[0].substring(2));
                            } else if (content.equals("1R")) {
                                pilu_content_rang_touzhu2.setVisibility(View.VISIBLE);
                                pilu_content_rang_touzhu2.setText("让球平" + matchBean.getSpr().split(";")[1].substring(2));
                            } else if (content.equals("0R")) {
                                pilu_content_rang_touzhu3.setVisibility(View.VISIBLE);
                                pilu_content_rang_touzhu3.setText("让球负" + matchBean.getSpr().split(";")[2].substring(2));
                            } else if (content.equals("3")) {
                                pilu_content_touzhu1.setVisibility(View.VISIBLE);
                                pilu_content_touzhu1.setText("主胜" + matchBean.getSp().split(";")[0].substring(2));
                            } else if (content.equals("1")) {
                                pilu_content_touzhu2.setVisibility(View.VISIBLE);
                                pilu_content_touzhu2.setText("平" + matchBean.getSp().split(";")[1].substring(2));
                            } else if (content.equals("0")) {
                                pilu_content_touzhu3.setVisibility(View.VISIBLE);
                                pilu_content_touzhu3.setText("主负" + matchBean.getSp().split(";")[2].substring(2));
                            }
                        }
                    }

                    if (null != matchBean.getResult()) {
                        if (matchBean.getResult().contains("3")) {
                            tv_wanfa1_result.setVisibility(View.VISIBLE);
                            tv_wanfa1_result.setText("主胜");
                            if (5 == infoBean.getStatus())
                                addImageSpan(context, pilu_content_touzhu1);
                        }

                        if (matchBean.getResult().contains("1")) {
                            tv_wanfa1_result.setVisibility(View.VISIBLE);
                            tv_wanfa1_result.setText("平");
                            if (5 == infoBean.getStatus())
                                addImageSpan(context, pilu_content_touzhu2);
                        }
                        if (matchBean.getResult().contains("0")) {
                            tv_wanfa1_result.setVisibility(View.VISIBLE);
                            tv_wanfa1_result.setText("主负");
                            if (5 == infoBean.getStatus())
                                addImageSpan(context, pilu_content_touzhu3);
                        }
                    }
                    if (null != matchBean.getResultr()) {
                        if (matchBean.getResultr().contains("3R")) {
                            tv_wanfa2_result.setVisibility(View.VISIBLE);
                            tv_wanfa2_result.setText("让球胜");
                            if (5 == infoBean.getStatus())
                                addImageSpan(context, pilu_content_rang_touzhu1);
                        }

                        if (matchBean.getResultr().contains("1R")) {
                            tv_wanfa2_result.setVisibility(View.VISIBLE);
                            tv_wanfa2_result.setText("让球平");
                            if (5 == infoBean.getStatus())
                                addImageSpan(context, pilu_content_rang_touzhu2);
                        }
                        if (matchBean.getResultr().contains("0R")) {
                            tv_wanfa2_result.setVisibility(View.VISIBLE);
                            tv_wanfa2_result.setText("让球负");
                            if (5 == infoBean.getStatus())
                                addImageSpan(context, pilu_content_rang_touzhu3);
                        }
                    }
                } else {
                    //让球
                    viewHolder.needReInflate=true;
                    rang_ll.setVisibility(View.GONE);
                    pilu_view_rang.setVisibility(View.GONE);
                    pilu_content_touzhu1.setVisibility(View.GONE);
                    pilu_content_touzhu2.setVisibility(View.GONE);
                    pilu_content_touzhu2.setVisibility(View.GONE);
                    tv_wanfa1.setText("让球(" + matchBean.getHandicap() + ")");
                    if (matchBean.getSelections().contains("3R")) {
                        pilu_content_touzhu1.setVisibility(View.VISIBLE);
                        pilu_content_touzhu1.setText("让球胜" + matchBean.getSpr().split(";")[0].substring(2));
                    }
                    if (matchBean.getSelections().contains("1R")) {
                        pilu_content_touzhu2.setVisibility(View.VISIBLE);
                        pilu_content_touzhu2.setText("让球平" + matchBean.getSpr().split(";")[1].substring(2));
                    }
                    if (matchBean.getSelections().contains("0R")) {
                        pilu_content_touzhu3.setVisibility(View.VISIBLE);
                        pilu_content_touzhu3.setText("让球负" + matchBean.getSpr().split(";")[2].substring(2));
                    }
                    if (matchBean.getResultr().contains("3R")) {
                        tv_wanfa1_result.setVisibility(View.VISIBLE);
                        tv_wanfa1_result.setText("让球胜");
                        if (5 == infoBean.getStatus())
                            addImageSpan(context, pilu_content_touzhu1);
                    }

                    if (matchBean.getResultr().contains("1R")) {
                        tv_wanfa1_result.setVisibility(View.VISIBLE);
                        tv_wanfa1_result.setText("让球平");
                        if (5 == infoBean.getStatus())
                            addImageSpan(context, pilu_content_touzhu2);
                    }
                    if (matchBean.getResultr().contains("0R")) {
                        tv_wanfa1_result.setVisibility(View.VISIBLE);
                        tv_wanfa1_result.setText("让球负");
                        if (5 == infoBean.getStatus())
                            addImageSpan(context, pilu_content_touzhu3);
                    }
                }
            } else {
                //胜负平
                viewHolder.needReInflate=true;
                rang_ll.setVisibility(View.GONE);
                pilu_view_rang.setVisibility(View.GONE);
                pilu_content_touzhu1.setVisibility(View.GONE);
                pilu_content_touzhu2.setVisibility(View.GONE);
                pilu_content_touzhu2.setVisibility(View.GONE);
                tv_wanfa1.setText("胜负平");
                if (matchBean.getSelections().contains("3")) {
                    pilu_content_touzhu1.setVisibility(View.VISIBLE);
                    pilu_content_touzhu1.setText("主胜" + matchBean.getSp().split(";")[0].substring(2));
                }

                if (matchBean.getSelections().contains("1")) {
                    pilu_content_touzhu2.setVisibility(View.VISIBLE);
                    pilu_content_touzhu2.setText("平" + matchBean.getSp().split(";")[1].substring(2));
                }
                if (matchBean.getSelections().contains("0")) {
                    pilu_content_touzhu3.setVisibility(View.VISIBLE);
                    pilu_content_touzhu3.setText("主负" + matchBean.getSp().split(";")[2].substring(2));
                }
                if (matchBean.getResult().contains("3")) {
                    tv_wanfa1_result.setVisibility(View.VISIBLE);
                    tv_wanfa1_result.setText("主胜");
                    if (5 == infoBean.getStatus())
                        addImageSpan(context, pilu_content_touzhu1);
                }

                if (matchBean.getResult().contains("1")) {
                    tv_wanfa1_result.setVisibility(View.VISIBLE);
                    tv_wanfa1_result.setText("平");
                    if (5 == infoBean.getStatus())
                        addImageSpan(context, pilu_content_touzhu2);
                }
                if (matchBean.getResult().contains("0")) {
                    tv_wanfa1_result.setVisibility(View.VISIBLE);
                    tv_wanfa1_result.setText("主负");
                    if (5 == infoBean.getStatus())
                        addImageSpan(context, pilu_content_touzhu3);
                }
            }
            switch (infoBean.getStatus()) {
                case 4:
                    //在售
                    pilu_tv_status.setText("待开奖");
                    tv_wanfa1_result.setText("-");
                    tv_wanfa2_result.setText("-");
                    pilu_ll_yingshou.setVisibility(View.GONE);
                    break;
                case 5:
                    //已开奖
                    pilu_tv_status.setText("已开奖");
                    break;
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
