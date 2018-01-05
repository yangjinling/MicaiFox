package com.micai.fox.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.micai.fox.R;
import com.micai.fox.activity.ModifyNiChengActivity;
import com.micai.fox.activity.MyReportActivity;
import com.micai.fox.activity.MyZhongChouActivity;
import com.micai.fox.activity.NotificationActivity;
import com.micai.fox.activity.SettingActivity;
import com.micai.fox.util.Bimp;
import com.micai.fox.util.Tools;
import com.micai.fox.view.ChooseDialog;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.micai.fox.R.id.iv_mine_head;

/**
 * Created by lq on 2017/12/19.
 */

public class MineFragmnet extends Fragment {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.rl)
    RelativeLayout rl;
    @Bind(R.id.tv_notify)
    TextView tvNotify;
    @Bind(iv_mine_head)
    ImageView ivMineHead;
    @Bind(R.id.tv_mine_nicheng)
    TextView tvMineNicheng;
    @Bind(R.id.ll_mine_zhongchou)
    LinearLayout llMineZhongchou;
    @Bind(R.id.ll_mine_report)
    LinearLayout llMineReport;
    @Bind(R.id.ll_mine_set)
    LinearLayout llMineSet;
    @Bind(R.id.tv_back)
    TextView tvBack;

    private final int TAKE_PICTURE = 1;//拍照
    private final int PHOTO_REQUEST_GALLERY = 2;//从相册选择
    private File file;
    private Uri photoUri = null;
    private static String fileName;
    private String imageName;
    private String fileDir;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        ButterKnife.bind(this, view);
        rl.setVisibility(View.VISIBLE);
        tvNotify.setVisibility(View.VISIBLE);
        tvTitle.setText("我的");
        Drawable drawable = getResources().getDrawable(R.mipmap.nav_icon_notice);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());// 设置边界
        // param 左上右下
        tvNotify.setCompoundDrawables(null, null, drawable, null);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.tv_notify, iv_mine_head, R.id.tv_mine_nicheng, R.id.ll_mine_zhongchou, R.id.ll_mine_report, R.id.ll_mine_set})
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.tv_notify:
                //消息通知
                intent = new Intent(getActivity(), NotificationActivity.class);
                startActivity(intent);
                break;
            case iv_mine_head:
                //修改头像
                showDialog(getActivity());
                break;
            case R.id.tv_mine_nicheng:
                //修改昵称
                intent = new Intent(getActivity(), ModifyNiChengActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_mine_zhongchou:
                //我的众筹
                intent = new Intent(getActivity(), MyZhongChouActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_mine_report:
                //我的报告
                intent = new Intent(getActivity(), MyReportActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_mine_set:
                //设置
                intent = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent);
                break;
        }
    }

    public void showDialog(final Context context) {
        new ChooseDialog(context, new ChooseDialog.ChooseListener() {
            Intent intent;

            @Override
            public void choosePhoto() {
//                Toast.makeText(PersonInformationActivity.this, "点击了选择相册", Toast.LENGTH_SHORT).show();
                intent = new Intent(Intent.ACTION_PICK, null);
                intent.setDataAndType(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
            }

            @Override
            public void chooseCamer() {
//                Toast.makeText(PersonInformationActivity.this, "点击了选择相机", Toast.LENGTH_SHORT).show();
                imageName = getStringToday() + ".jpg";
             /*   if (SDCardUtils.isExistSDCard()) {
                    photoPath = SDCardUtils.createAppDir();
                } else {
                    photoPath = context.getFilesDir().getPath();
                }
                photoPath = photoPath + "/" + FileUtils.getPhotoFileName();
                cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                Uri uri = Uri.fromFile(new File(photoPath));
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                startActivityForResult(cameraIntent, TAKE_PICTURE);*/

                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                    Log.e("YJL", "" + Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED));
                    String folderStr = context.getExternalFilesDir(null) + "/micaifox/Camera";
                    File folderName = new File(folderStr);
                    if (!folderName.exists()) {
                        Log.e("YJL", "" + folderName.exists());
                        folderName.mkdirs();
                    }
                    fileDir = folderName + File.separator + imageName;
                    Log.e("YJL", "" + fileDir);
                    file = new File(fileDir);
                    Uri imageUri = Uri.fromFile(file);
                    intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                    startActivityForResult(intent, TAKE_PICTURE);
                }
            }
        }).show();
    }

    private String getStringToday() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault());
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case TAKE_PICTURE://拍照
                try {
                    Bitmap bitmap = Bimp.revitionImageSize(fileDir);
                    String path;
                    if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                        Log.e("YJL", "" + Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED));
                        path = getActivity().getExternalFilesDir(null) + "/masterwork/Camera";
                        File folderName = new File(path);
                        if (!folderName.exists()) {
                            Log.e("YJL", "" + folderName.exists());
                            folderName.mkdirs();
                        }
                        path = folderName + File.separator + imageName;
                        Log.e("YJL", "" + fileDir);
                        File photo = new File(path);
                        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(photo));
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 60, bos);
                        bos.flush();
                        bos.close();
                        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);//给系统发送广播更新图库
                        Uri uri = Uri.fromFile(photo);
                        intent.setData(uri);
                        getActivity().sendBroadcast(intent);
                        Bitmap bitmaps = Tools.zoomImage(bitmap, Tools.dip2px(getActivity(), 120), Tools.dip2px(getActivity(), 65));
                        if (bitmaps != null) {
                            ivMineHead.setImageBitmap(bitmaps);
                        } else {

                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case PHOTO_REQUEST_GALLERY://相册获取
                if (data != null) {
                    Log.e("YJL", "" + PHOTO_REQUEST_GALLERY);
                    if (null != data.getData()) {
                        Log.e("YJL", "" + data.getData());
                        setPicToView(data.getData(), getActivity());
                    }
                } else {
                    return;
                }
                break;
        }
    }

    public void setPicToView(Uri uri, Activity activity) {
        photoUri = uri;
        Log.e("YJL", "" + photoUri);
        fileName = activity.getExternalFilesDir(null) + "/micaifox/Camera";
        File folderName = new File(fileName);
        if (!folderName.exists()) {
            Log.e("YJL", "" + folderName.exists());
            folderName.mkdirs();
        }
        fileName = folderName + File.separator + System.currentTimeMillis() + ".jpg";
        Log.e("YJL", "" + fileName);
        file = new File(fileName);
        FileOutputStream fos = null;
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = false;
            options.inSampleSize = 2;
            Bitmap bitmap = BitmapFactory.decodeStream(activity.getContentResolver().openInputStream(photoUri), null, options);
            fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 85, fos);
            if (bitmap != null) {
                Log.e("YJL", "bitmap" + bitmap);
                ivMineHead.setImageBitmap(bitmap);
            } else {
                Log.e("YJL", "bitmap为空");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.e("YJL", "FileNotFoundException" + e.getMessage());

        } catch (IOException e) {
            Log.e("YJL", "IOException-----" + e.getMessage());
            e.printStackTrace();

        } finally {
            try {
                if (fos != null)
                    fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
