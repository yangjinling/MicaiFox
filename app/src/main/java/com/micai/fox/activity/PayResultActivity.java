package com.micai.fox.activity;

import android.annotation.SuppressLint;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.micai.fox.R;
import com.micai.fox.app.Url;
import com.micai.fox.util.LogUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PayResultActivity extends AppCompatActivity {

    @Bind(R.id.pay_web)
    WebView payWeb;
    private String customHtml;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_result);
        ButterKnife.bind(this);
//        tvTitle.setText("支付结果界面");
//        rl.setVisibility(View.GONE);
        customHtml = getIntent().getStringExtra("URL");
        LogUtil.e("YJLS", "customHtml==" + customHtml);
        WebSettings websettings = payWeb.getSettings();
        websettings.setSupportZoom(true);
        websettings.setBuiltInZoomControls(true);
        websettings.setSupportMultipleWindows(true);
        websettings.setJavaScriptEnabled(true);
        websettings.setSaveFormData(false);
        websettings.setSavePassword(false);
        websettings.setJavaScriptCanOpenWindowsAutomatically(true);
        websettings.setAllowFileAccess(false);
        payWeb.requestFocus();
        payWeb.setVerticalScrollbarOverlay(true);
//        payWeb.setWebChromeClient(new MyAndroidWebClient());
////        payWeb.loadDataWithBaseURL("", customHtml, "text/html", "utf-8", "");
//        payWeb.addJavascriptInterface(new WebEvent(), "document.all.pay_form");
        payWeb.setWebViewClient(new WebViewClient() {
            @Override

            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {

                handler.proceed();  // 接受信任所有网站的证书

                // handler.cancel();   // 默认操作 不处理

                // handler.handleMessage(null);  // 可做其他处理
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                LogUtil.e("YJLS", url);
                view.loadUrl(url);

                return true;

            }
        });
//        payWeb.loadData(customHtml, "text/html", "UTF-8");
        payWeb.loadDataWithBaseURL("", customHtml, "text/html", "utf-8", "");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    protected final class WebEvent {
        public WebEvent() {
        }

        @JavascriptInterface
        public void submit() {
            LogUtil.e("YJLS", "submit");
        }
    }

    /*  private final class MyAndroidWebClient extends WebChromeClient {
          @Override
          public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
              LogUtil.e("YJLS", "123--" + url + "457--" + message + "789==" + result.toString());
              payWeb.loadUrl(url);
              return true;
          }
      }*/
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (payWeb.canGoBack() && event.getKeyCode() == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            payWeb.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);

    }
}
