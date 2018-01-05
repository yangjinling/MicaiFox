package com.micai.fox.view;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.micai.fox.R;


/**
 * create by 杨金玲 on 2016/9/27 10:04
 * mail：18363820101@163.com
 */
public class ChooseDialog extends Dialog {

    private Button mPhoto;
    private Button mCamera;
    private Button mCancel;


    private ChooseListener mListener;

    public ChooseDialog(Context context, ChooseListener listener) {
        super(context, R.style.style_dialog);
        setContentView(R.layout.choose_dialog);
        mListener = listener;
        initView();
    }

    private void initView() {
        mPhoto = (Button) findViewById(R.id.btn_selectphoto);
        mCamera = (Button) findViewById(R.id.btn_makeaphoto);
        mCancel = (Button) findViewById(R.id.btn_photocancel);

        Window window = getWindow();
        window.setGravity(Gravity.BOTTOM);

        window.getAttributes().width = RelativeLayout.LayoutParams.MATCH_PARENT;

        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        mPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                mListener.choosePhoto();
            }
        });

        mCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                mListener.chooseCamer();
            }
        });

    }

    public interface ChooseListener {
        void choosePhoto();

        void chooseCamer();
    }

}
