package com.micai.fox.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.micai.fox.R;
import com.micai.fox.util.LogUtil;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;

/**
 * 作者：CY on 2017/3/3 16:21
 */

public class UploadHeader extends FrameLayout implements PtrUIHandler{

    private TextView mProgressText;
    private LinearLayout mCyPro;
    private RotateAnimation mFlipAnimation;
    private RotateAnimation mReverseFlipAnimation;
    private int mRotateAniTime = 150;

    public UploadHeader(Context context) {
        super(context);
        init();
    }


    public UploadHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public UploadHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    @SuppressLint("NewApi")
    public UploadHeader(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();

    }
    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.upload_progress,this);
        LogUtil.e("YJL","进来了么");
        mCyPro = (LinearLayout) view.findViewById(R.id.progressHead);
        mProgressText = (TextView) view.findViewById(R.id.progressHeadTitle);
    }


    @Override
    public void onUIReset(PtrFrameLayout frame) {

    }

    @Override
    public void onUIRefreshPrepare(PtrFrameLayout frame) {

    }

    @Override
    public void onUIRefreshBegin(PtrFrameLayout frame) {

    }

    @Override
    public void onUIRefreshComplete(PtrFrameLayout frame) {

    }

    @Override
    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {
//        final int mOffsetToRefresh = frame.getOffsetToRefresh();
//        final int currentPos = ptrIndicator.getCurrentPosY();
//        final int lastPos = ptrIndicator.getLastPosY();
//
//        if (currentPos < mOffsetToRefresh && lastPos >= mOffsetToRefresh) {
//            if (isUnderTouch && status == PtrFrameLayout.PTR_STATUS_PREPARE) {
//                crossRotateLineFromBottomUnderTouch(frame);
//                if (mCyPro != null) {
//                    mCyPro.clearAnimation();
//                    mCyPro.startAnimation(mReverseFlipAnimation);
//                }
//            }
//        } else if (currentPos > mOffsetToRefresh && lastPos <= mOffsetToRefresh) {
//            if (isUnderTouch && status == PtrFrameLayout.PTR_STATUS_PREPARE) {
//                crossRotateLineFromTopUnderTouch(frame);
//                if (mCyPro != null) {
//                    mCyPro.clearAnimation();
//                    buildAnimation();
//                    mCyPro.startAnimation(mFlipAnimation);
//                }
//            }
//        }

    }
    //这段动画暂时没什么用
    private void crossRotateLineFromTopUnderTouch(PtrFrameLayout frame) {
        if (!frame.isPullToRefresh()) {
            mProgressText.setVisibility(VISIBLE);
            mProgressText.setText(in.srain.cube.views.ptr.R.string.cube_ptr_release_to_refresh);
        }
    }
    private void buildAnimation() {
        mFlipAnimation = new RotateAnimation(0, -180, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        mFlipAnimation.setInterpolator(new LinearInterpolator());
        mFlipAnimation.setDuration(mRotateAniTime);
        mFlipAnimation.setFillAfter(true);

        mReverseFlipAnimation = new RotateAnimation(-180, 0, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        mReverseFlipAnimation.setInterpolator(new LinearInterpolator());
        mReverseFlipAnimation.setDuration(mRotateAniTime);
        mReverseFlipAnimation.setFillAfter(true);
    }
    private void crossRotateLineFromBottomUnderTouch(PtrFrameLayout frame) {
        mProgressText.setVisibility(VISIBLE);
        if (frame.isPullToRefresh()) {
            mProgressText.setText(getResources().getString(in.srain.cube.views.ptr.R.string.cube_ptr_pull_down_to_refresh));
        } else {
            mProgressText.setText(getResources().getString(in.srain.cube.views.ptr.R.string.cube_ptr_pull_down));
        }
    }
}
