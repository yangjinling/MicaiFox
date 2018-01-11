package com.micai.fox.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ScrollView;

import com.micai.fox.util.LogUtil;

/**
 * Created by lq on 2018/1/8.
 */

public class MyScrollView extends ScrollView {
    //    private OnXuanTingScrollListener onXuanTingScrollListener;
    private ViewGroup xuantingquyu, firstParent;
    private View moveView;
    private int moveViewGetTopHeight;//悬停的控件距离 scrollview 顶部的距离
    private boolean isXuanting;//是不是处于悬停
    private int moveViewHeight;//被移除的控件的高度,因为一旦移除后它的父控件内部没有内容了,但是又是包裹内容,会出现移除后控件瞬间移动的情况,需要给父控件设置和它一样高的高度
//    public void setOnXuanTingScrollListener(OnXuanTingScrollListener onXuanTingScrollListener) {
//        this.onXuanTingScrollListener = onXuanTingScrollListener;
//    }
    private int type;

    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private float lastY, currnetY, moveY;//上一次Y 轴的点, 和当前的Y, Y 轴移动的距离差
    private int currentScrollY;//当前滚动到什么位置

    /**
     * 高度这个 scrollview, 我想要把什么区域从什么地方移除后添加到什么地方
     *
     * @param xuantingquyu 悬停区域的容器
     * @param firstParent  原始位置所在的父控件
     * @param moveView     要移除的 view
     */
    public void setXuantingquyu(final ViewGroup xuantingquyu, final ViewGroup firstParent, final View moveView) {
        this.xuantingquyu = xuantingquyu;
        this.firstParent = firstParent;
        this.moveView = moveView;
//        moveViewHeight = moveView.getHeight();//获取高度
//        firstParent.getLayoutParams().height = moveViewHeight;//将原始区域的高度设置为移除 view 的高度,防止移除后发生高度变化
//        xuantingquyu.getLayoutParams().height = moveViewHeight;//将移除 view 的高度设置给后面放置区域的控件

        moveView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                moveViewGetTopHeight = firstParent.getTop();// 获取判断多少的时候应该进行悬停的高度
                //摆放完成,给原始区域设置高度
                firstParent.getLayoutParams().height = moveView.getMeasuredHeight();//将原始区域的高度设置为移除 view 的高度,防止移除后发生高度变化
                xuantingquyu.getLayoutParams().height = moveView.getMeasuredHeight();//将移除 view 的高度设置给后面放置区域的控件
                moveView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent ev) {
//        switch (ev.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                lastY = ev.getY();//将按下去的点设置为上次的点
//                break;
//            case MotionEvent.ACTION_MOVE:
//                currnetY = ev.getY();
//                moveY = currnetY - lastY;//滑动的距离差,如果是负数代表向上,如果是整数代表乡下
//                lastY = currnetY;
//                currentScrollY = getScrollY();//获取 scrollview 当前已经滚动到什么位置
////                if (onXuanTingScrollListener != null) {
////                    onXuanTingScrollListener.onXuanting(moveY, currentScrollY);
////                }
//                onXuanting(currentScrollY);//调用方法判断是悬停还是回去
//                break;
//            case MotionEvent.ACTION_UP:
//                handler.sendEmptyMessageDelayed(1, 5);
//                break;
//
//        }
//
//
//        return super.onTouchEvent(ev);
//    }

    /**
     * 这个方法是滑动变化的监听,可以监听到惯性滑动,用于替代上面的手势和 handler 集合的功能
     *
     * @param l    x轴最新的位置
     * @param t    Y轴最新的位置
     * @param oldl X轴上次
     * @param oldt
     */
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        onXuanting(t);
        super.onScrollChanged(l, t, oldl, oldt);
    }

    public void onXuanting(int currentMoveY) {
        if (currentMoveY > moveViewGetTopHeight && !isXuanting) {//代表你应该是在悬停区域
            if (null != moveView.getParent()) {
                ((ViewGroup) moveView.getParent()).removeView(moveView);//从父容器中移除
            }
            xuantingquyu.addView(moveView);//加到悬停区域
            xuantingquyu.setVisibility(View.VISIBLE);//悬停区域显示
            isXuanting = true;//处于悬停状态
            LogUtil.e("YJL","悬停");
        } else if (currentMoveY <= moveViewGetTopHeight && isXuanting) {//当前应该回到原始区域
            if (moveView.getParent() != null) {
                ((ViewGroup) moveView.getParent()).removeView(moveView);//先移除 一定要先移除 否则很有可能会抛出异常
            }
            xuantingquyu.setVisibility(View.GONE);//隐藏悬停区域
            firstParent.addView(moveView);//增加回原先的位置
            isXuanting = false;//处于滚动状态
        }
        //在这里 我已经知道移动方向和当前距离了.我只要知道当前的距离和被移除的控件距离父控件顶部的距离是否一致即可,一致则根据方向做具体处理

    }

//private Handler handler =new Handler(){
//    @Override
//    public void handleMessage(Message msg) {
//        //判断最后一次手指滑动到的位置和当前的位置是否一致,不一致则代表正在惯性滑动
//        int y = getScrollY();
//        int top = getChildAt(0).getTop();
//        Log.e("自定义标签", "类名==MyScrollView" + "方法名==handleMessage=====:" + y + "========" + top);
//        if (currentScrollY !=y) {
//            onXuanting(y);//将惯性 滚动到的位置传递进去进行判断
//            currentScrollY = y;
//            sendEmptyMessageDelayed(1,2);
//        }
//        onXuanting(y);
//    }
//};


//
//    public interface OnXuanTingScrollListener{
//        void onXuanting(float moveY, int currentMoveY);//两个参数 一个是移动的距离差,一个是当前滚动的距离
//    }
}
