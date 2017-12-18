package com.zhy.http.okhttp.callback;

/**
 * 作者：CY on 2017/2/28 16:26
 */

public class DialogInShow {

    private boolean isShow;
    private static DialogInShow mDialogInShow;

    private DialogInShow() {
    }

    public static DialogInShow getInstance() {
        // 先检查实例是否存在，如果不存在才进入下面的同步块
        if (mDialogInShow == null) {
            // 同步块，线程安全的创建实例
            synchronized (DialogInShow.class) {
                // 再次检查实例是否存在，如果不存在才真正的创建实例
                if (mDialogInShow == null) {
                    mDialogInShow = new DialogInShow();
                }
            }
        }
        return mDialogInShow;
    }

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }
}
