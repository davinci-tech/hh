package com.huawei.ui.main.stories.me.activity;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.WebViewInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import defpackage.nrt;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes7.dex */
public class OpensourceNoticeActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private WebView f10340a;
    private WebSettings b;
    private Context c;
    private ExecutorService d;
    private Handler e = new a(this);
    private LinearLayout j;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.a("OpensourceNoticeActivity", "onCreate()");
        this.c = this;
        this.d = Executors.newSingleThreadExecutor();
        d();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity
    public void initViewTahiti() {
        d();
    }

    private void d() {
        LogUtil.a("OpensourceNoticeActivity", "initView()");
        setContentView(R.layout.activity_user_profile_opensource_notice);
        WebView webView = new WebView(this.c);
        this.f10340a = webView;
        webView.setBackgroundColor(0);
        nrt.cKh_(this.c, this.f10340a);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.opensource_item_linear);
        this.j = linearLayout;
        setViewSafeRegion(false, linearLayout);
        this.j.addView(this.f10340a);
        this.f10340a.setVerticalScrollBarEnabled(false);
        WebSettings settings = this.f10340a.getSettings();
        this.b = settings;
        settings.setSupportZoom(true);
        this.b.setTextSize(WebSettings.TextSize.SMALLER);
        this.b.setAllowFileAccessFromFileURLs(false);
        this.b.setJavaScriptEnabled(false);
        this.b.setAllowFileAccess(false);
        this.b.setGeolocationEnabled(false);
        this.b.setAllowContentAccess(false);
        e();
    }

    private void e() {
        ExecutorService executorService = this.d;
        if (executorService != null) {
            executorService.execute(new Runnable() { // from class: com.huawei.ui.main.stories.me.activity.OpensourceNoticeActivity.4
                @Override // java.lang.Runnable
                public void run() {
                    OpensourceNoticeActivity.this.e.sendMessage(OpensourceNoticeActivity.this.e.obtainMessage(1000, OpensourceNoticeActivity.this.b("hwOpenSource.html") ? "file:///android_asset/opensourcenotice/hwOpenSource.html" : null));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str) {
        WebView webView = this.f10340a;
        if (webView != null) {
            webView.loadUrl(str);
            WebViewInstrumentation.loadUrl(webView, str);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity
    public void loadApplicationTheme() {
        loadTheme("OpensourceNoticeTheme");
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        a();
        LogUtil.a("OpensourceNoticeActivity", "onDestroy");
        ExecutorService executorService = this.d;
        if (executorService != null) {
            executorService.shutdown();
        }
    }

    private void a() {
        LogUtil.a("OpensourceNoticeActivity", "destroyWebView");
        if (this.f10340a != null) {
            LogUtil.a("OpensourceNoticeActivity", "onDestroy destroyWebView");
            ViewParent parent = this.f10340a.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(this.f10340a);
            }
            try {
                this.f10340a.destroy();
            } catch (Throwable unused) {
                LogUtil.b("OpensourceNoticeActivity", "destroyWebView error");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean b(String str) {
        try {
            return Arrays.asList(getResources().getAssets().list("opensourcenotice")).contains(str);
        } catch (IOException unused) {
            LogUtil.b("OpensourceNoticeActivity", "isFileExists IOException");
            return false;
        }
    }

    static class a extends BaseHandler<OpensourceNoticeActivity> {
        a(OpensourceNoticeActivity opensourceNoticeActivity) {
            super(opensourceNoticeActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dNy_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(OpensourceNoticeActivity opensourceNoticeActivity, Message message) {
            if (message.what == 1000 && (message.obj instanceof String)) {
                opensourceNoticeActivity.c((String) message.obj);
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
