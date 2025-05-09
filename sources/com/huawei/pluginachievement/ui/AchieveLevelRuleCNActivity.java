package com.huawei.pluginachievement.ui;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.WebViewInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.LevelConstants;
import com.huawei.ui.commonui.base.BaseActivity;
import defpackage.nrt;

/* loaded from: classes9.dex */
public class AchieveLevelRuleCNActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private WebView f8390a;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.achieve_level_cn_rule);
        clearBackgroundDrawable();
        cancelAdaptRingRegion();
        d();
    }

    private void d() {
        LogUtil.a("PLGACHIEVE_AchieveLevelRuleCNActivity", "initView()");
        WebView webView = new WebView(getApplicationContext());
        this.f8390a = webView;
        nrt.cKg_(this, webView);
        this.f8390a.setVerticalScrollBarEnabled(false);
        this.f8390a.setHorizontalScrollBarEnabled(false);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.service_item_linear);
        setViewSafeRegion(false, linearLayout);
        linearLayout.addView(this.f8390a);
        WebSettings settings = this.f8390a.getSettings();
        settings.setSupportZoom(true);
        settings.setTextSize(WebSettings.TextSize.SMALLER);
        settings.setAllowFileAccessFromFileURLs(false);
        settings.setAllowContentAccess(false);
        settings.setGeolocationEnabled(false);
        settings.setAllowFileAccess(false);
        settings.setJavaScriptEnabled(false);
        WebView webView2 = this.f8390a;
        webView2.loadUrl(LevelConstants.URL);
        WebViewInstrumentation.loadUrl(webView2, LevelConstants.URL);
    }

    @Override // android.app.Activity
    protected void onRestart() {
        super.onRestart();
        LogUtil.a("PLGACHIEVE_AchieveLevelRuleCNActivity", "AchieveLevelRuleCNActivity onRestart()");
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        LogUtil.a("PLGACHIEVE_AchieveLevelRuleCNActivity", "AchieveLevelRuleCNActivity onResume()");
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        LogUtil.a("PLGACHIEVE_AchieveLevelRuleCNActivity", "AchieveLevelRuleCNActivity onPause()");
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        a();
        LogUtil.a("PLGACHIEVE_AchieveLevelRuleCNActivity", "AchieveLevelRuleCNActivity onDestroy");
    }

    private void a() {
        LogUtil.a("PLGACHIEVE_AchieveLevelRuleCNActivity", "destroyWebView");
        if (this.f8390a != null) {
            LogUtil.a("PLGACHIEVE_AchieveLevelRuleCNActivity", "onDestroy destroyWebView");
            ViewParent parent = this.f8390a.getParent();
            if (parent != null) {
                ((ViewGroup) parent).removeView(this.f8390a);
            }
            this.f8390a.destroy();
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
