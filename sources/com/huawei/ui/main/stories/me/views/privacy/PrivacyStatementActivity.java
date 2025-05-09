package com.huawei.ui.main.stories.me.views.privacy;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hianalytics.visual.autocollect.instrument.WebViewInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.stories.me.js.JsInteraction;
import com.huawei.ui.main.stories.me.views.privacy.PrivacyStatementActivity;
import defpackage.rhy;
import health.compact.a.CloudImpl;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

/* loaded from: classes9.dex */
public class PrivacyStatementActivity extends ServiceItemActivity {
    private static final ArrayList<String> c = new ArrayList<>(Arrays.asList("file:///android_asset/defaultPrivacyStatement/privacy-statement-zh-hk.htm", "file:///android_asset/defaultPrivacyStatement/privacy-statement-vi-vn.htm", "file:///android_asset/defaultPrivacyStatement/privacy-statement-th-th.htm", "file:///android_asset/defaultPrivacyStatement/privacy-statement-ko-kr.htm", "file:///android_asset/defaultPrivacyStatement/privacy-statement-ja-jp.htm", "file:///android_asset/defaultPrivacyStatement/privacy-statement-in-id.htm", "file:///android_asset/defaultPrivacyStatement/privacy-statement-es-us.htm", "file:///android_asset/defaultPrivacyStatement/privacy-statement-en-us.htm", "file:///android_asset/defaultPrivacyStatement/privacy-statement-ar-eg.htm", "file:///android_asset/defaultPrivacyStatement/privacy-statement-uk-ua.htm", "file:///android_asset/defaultPrivacyStatement/privacy-statement-tr-tr.htm", "file:///android_asset/defaultPrivacyStatement/privacy-statement-ro-ro.htm", "file:///android_asset/defaultPrivacyStatement/privacy-statement-pl-pl.htm", "file:///android_asset/defaultPrivacyStatement/privacy-statement-it-it.htm", "file:///android_asset/defaultPrivacyStatement/privacy-statement-fr-fr.htm", "file:///android_asset/defaultPrivacyStatement/privacy-statement-es-es.htm", "file:///android_asset/defaultPrivacyStatement/privacy-statement-en-gb.htm", "file:///android_asset/defaultPrivacyStatement/privacy-statement-de-de.htm", "file:///android_asset/defaultPrivacyStatement/privacy-statement-cs-cz.htm", "file:///android_asset/defaultPrivacyStatement/privacy-statement-ru-ru.htm", "file:///android_asset/defaultPrivacyStatement/privacy-statement-zh-cn.htm", "file:///android_asset/defaultPrivacyStatement/privacy-statement-en-cn.htm", "file:///android_asset/defaultPrivacyStatement/privacy-statement-bo-cn.htm", "file:///android_asset/defaultPrivacyStatement/privacy-statement-ug-cn.htm", "file:///android_asset/defaultPrivacyStatement/privacy-statement-zh-cht.htm"));

    /* renamed from: a, reason: collision with root package name */
    private Context f10381a;

    @Override // com.huawei.ui.main.stories.me.views.privacy.ServiceItemActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        if (!CommonUtil.v(this)) {
            SystemBarHelper.dPf_(getWindow());
        }
        this.f10381a = this;
        super.onCreate(bundle);
        a();
    }

    @Override // com.huawei.ui.main.stories.me.views.privacy.ServiceItemActivity
    protected void dPa_(final WebView webView) {
        LogUtil.a("PrivacyStatementActivity", "loadWebViewUrl");
        if (webView == null) {
            LogUtil.h("PrivacyStatementActivity", "loadWebViewUrl webView is null");
            return;
        }
        if (CommonUtil.v(this.f10381a)) {
            JsInteraction jsInteraction = new JsInteraction(this.f10381a);
            webView.addJavascriptInterface(jsInteraction, "JsInteraction");
            webView.addJavascriptInterface(jsInteraction, "checkMore");
            ThreadPoolManager.d().execute(new Runnable() { // from class: ril
                @Override // java.lang.Runnable
                public final void run() {
                    PrivacyStatementActivity.this.dOS_(webView);
                }
            });
            return;
        }
        String c2 = c();
        WebSettings settings = webView.getSettings();
        if (settings != null) {
            settings.setGeolocationEnabled(false);
            settings.setAllowFileAccess(false);
            settings.setAllowContentAccess(false);
        }
        webView.loadUrl(c2);
        WebViewInstrumentation.loadUrl(webView, c2);
    }

    public /* synthetic */ void dOS_(final WebView webView) {
        this.b = GRSManager.a(this.f10381a).getNoCheckUrl("domainConsumerHuawei", b());
        if (this.d == null) {
            LogUtil.h("PrivacyStatementActivity", "loadWebViewUrl mHandler is null");
        } else {
            this.d.post(new Runnable() { // from class: rin
                @Override // java.lang.Runnable
                public final void run() {
                    PrivacyStatementActivity.this.dOR_(webView);
                }
            });
        }
    }

    public /* synthetic */ void dOR_(WebView webView) {
        String e = e();
        WebSettings settings = webView.getSettings();
        if (settings != null) {
            settings.setGeolocationEnabled(false);
            settings.setAllowFileAccess(false);
            settings.setAllowContentAccess(false);
            settings.setJavaScriptEnabled(rhy.a(e));
        }
        webView.loadUrl(e);
        WebViewInstrumentation.loadUrl(webView, e);
    }

    protected String c() {
        String u = CommonUtil.u();
        String str = "file:///android_asset/defaultPrivacyStatement/privacy-statement-" + u.toLowerCase(Locale.ENGLISH) + ".htm";
        LogUtil.a("PrivacyStatementActivity", "getLocalHealthPrivacyPath countryAndLanguage is ", u);
        if (!c.contains(str)) {
            String country = Locale.getDefault().getCountry();
            int siteIdByCountry = CloudImpl.c(this.f10381a).getSiteIdByCountry(country);
            LogUtil.a("PrivacyStatementActivity", "getLocalHealthPrivacyPath country is ", country, ", siteId is ", Integer.valueOf(siteIdByCountry));
            str = siteIdByCountry != 1 ? siteIdByCountry != 5 ? siteIdByCountry != 8 ? "file:///android_asset/defaultPrivacyStatement/privacy-statement-en-gb.htm" : "file:///android_asset/defaultPrivacyStatement/privacy-statement-ru-ru.htm" : "file:///android_asset/defaultPrivacyStatement/privacy-statement-en-us.htm" : "file:///android_asset/defaultPrivacyStatement/privacy-statement-zh-cn.htm";
        }
        LogUtil.a("PrivacyStatementActivity", "getLocalHealthPrivacyPath url is ", str);
        return str;
    }

    @Override // com.huawei.ui.main.stories.me.views.privacy.ServiceItemActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
