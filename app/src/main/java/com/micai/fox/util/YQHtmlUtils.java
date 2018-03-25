package com.micai.fox.util;

import android.os.Build;
import android.text.Html;
import android.text.Spanned;

public class YQHtmlUtils {

    private YQHtmlUtils() {
    }

    public static YQHtmlUtils getInstance() {
        return UtilsHolder.instance;
    }

    private static class UtilsHolder {
        public static final YQHtmlUtils instance = new YQHtmlUtils();
    }

    public static Spanned fromHtml(String source) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(source, Html.FROM_HTML_MODE_LEGACY);
        } else {
            return Html.fromHtml(source);
        }
    }
}