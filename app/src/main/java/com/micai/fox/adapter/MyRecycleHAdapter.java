package com.micai.fox.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.micai.fox.R;
import com.micai.fox.app.Config;
import com.micai.fox.app.Url;
import com.micai.fox.resultbean.HomeResultBean;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 作者：杨金玲 on 2017/12/27 08:45
 * 邮箱：18363820101@163.com
 */

/*首页---横向专家列表*/
public class MyRecycleHAdapter extends RecyclerView.Adapter<MyRecycleHAdapter.ViewHolder> {
    private List<HomeResultBean.ExecDatasBean.ProfessorBean> mData;
    /**
     * 事件回调监听
     */
    private MyRecycleHAdapter.OnItemClickListener onItemClickListener;
    private Context mContext;

    public MyRecycleHAdapter(List<HomeResultBean.ExecDatasBean.ProfessorBean> data,Context context) {
        this.mData = data;
        this.mContext=context;
    }

    public void updateData(List<HomeResultBean.ExecDatasBean.ProfessorBean> data) {
        this.mData = data;
        notifyDataSetChanged();
    }

    /**
     * 设置回调监听
     *
     * @param listener
     */
    public void setOnItemClickListener(MyRecycleHAdapter.OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 实例化展示的view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_h_recycle, parent, false);
        // 实例化viewholder
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        // 绑定数据
        holder.mTv.setText(mData.get(position).getProName());
        Glide.with(mContext).load(Url.WEB_BASE_IP+ mData.get(position).getProPhoto()).asBitmap().placeholder(R.drawable.circle).error(R.drawable.circle).into(holder.head);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if (onItemClickListener != null) {
                    int pos = holder.getLayoutPosition();
                    onItemClickListener.onItemClick(holder.itemView, pos);
                }
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (onItemClickListener != null) {
                    int pos = holder.getLayoutPosition();
                    onItemClickListener.onItemLongClick(holder.itemView, pos);
                }
                //表示此事件已经消费，不会触发单击事件
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView head;
        TextView mTv;

        public ViewHolder(View itemView) {
            super(itemView);
            mTv = (TextView) itemView.findViewById(R.id.home_experts_name);
            head = ((CircleImageView) itemView.findViewById(R.id.home_experts_head));
        }


    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

}
