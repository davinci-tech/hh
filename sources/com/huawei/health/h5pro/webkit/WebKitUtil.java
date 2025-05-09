package com.huawei.health.h5pro.webkit;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.AndroidRuntimeException;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import androidx.webkit.WebSettingsCompat;
import androidx.webkit.WebViewFeature;
import com.huawei.health.h5pro.utils.EnvironmentHelper;
import com.huawei.health.h5pro.utils.GeneralUtil;
import com.huawei.health.h5pro.utils.LogUtil;
import com.huawei.operation.utils.Constants;
import java.io.File;
import java.io.IOException;

/* loaded from: classes3.dex */
public class WebKitUtil {
    public static void setDarkMode(Context context, WebView webView, int i) {
        WebSettings settings = webView.getSettings();
        if ((context.getResources().getConfiguration().uiMode & 48) == 32) {
            boolean z = i != 1;
            if (WebViewFeature.isFeatureSupported(WebViewFeature.ALGORITHMIC_DARKENING)) {
                LogUtil.i("H5PRO_WebKitUtil", "setDarkMode: isAllowAlgorithmicDarkening -> " + z);
                WebSettingsCompat.setAlgorithmicDarkeningAllowed(settings, z);
            }
            if (WebViewFeature.isFeatureSupported(WebViewFeature.FORCE_DARK_STRATEGY)) {
                if (WebViewFeature.isFeatureSupported(WebViewFeature.FORCE_DARK)) {
                    WebSettingsCompat.setForceDark(settings, 2);
                } else if (Build.VERSION.SDK_INT >= 29) {
                    webView.getSettings().setForceDark(2);
                }
                WebSettingsCompat.setForceDarkStrategy(settings, i);
            }
        }
    }

    public static void setBasicSecurity(WebView webView) {
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setGeolocationEnabled(false);
        settings.setAllowContentAccess(false);
        settings.setAllowFileAccess(false);
        settings.setAllowFileAccessFromFileURLs(false);
        settings.setAllowUniversalAccessFromFileURLs(false);
        settings.setMediaPlaybackRequiresUserGesture(false);
        settings.setSavePassword(false);
    }

    public static boolean isUrlTrusted(Context context, String str) {
        if (!GeneralUtil.isSafePath(str)) {
            LogUtil.w("H5PRO_WebKitUtil", "isUrlTrusted: invalid path");
            return false;
        }
        if (!isStrictMode() || !isLocalUrl(str) || isLocalUrlTrusted(context, str)) {
            return true;
        }
        LogUtil.w("H5PRO_WebKitUtil", "isUrlTrusted: untrusted(local) -> " + str);
        return false;
    }

    public static boolean isStrictMode() {
        EnvironmentHelper.BuildType buildType = EnvironmentHelper.getInstance().getBuildType();
        return buildType == EnvironmentHelper.BuildType.RELEASE || buildType == EnvironmentHelper.BuildType.BETA || buildType == EnvironmentHelper.BuildType.GREEN;
    }

    public static boolean isRemoteUrl(String str) {
        return isHttpUrl(str) || isHttpsUrl(str);
    }

    public static boolean isLocalUrlTrusted(Context context, String str) {
        if (!GeneralUtil.isSafePath(str)) {
            LogUtil.w("H5PRO_WebKitUtil", "isLocalUrlTrusted: invalid path");
            return false;
        }
        if (str.startsWith("file:///android_asset/")) {
            return true;
        }
        return isInPrivateDir(context, str);
    }

    public static boolean isLocalUrl(String str) {
        return str.startsWith(Constants.PREFIX_FILE);
    }

    public static boolean isInPrivateDir(Context context, String str) {
        if (context == null) {
            LogUtil.w("H5PRO_WebKitUtil", "get sLocalTrustedDir context null");
            return false;
        }
        try {
            return new File(str.replaceFirst("file:", "")).getCanonicalPath().startsWith(context.getFilesDir().getCanonicalPath() + "/h5pro/");
        } catch (IOException unused) {
            LogUtil.w("H5PRO_WebKitUtil", "get sLocalTrustedDir meet io exception");
            return false;
        }
    }

    public static boolean isHttpsUrl(String str) {
        return !TextUtils.isEmpty(str) && str.startsWith("https://");
    }

    public static boolean isHttpUrl(String str) {
        return !TextUtils.isEmpty(str) && str.startsWith("http://");
    }

    public static void initWebSetting(Context context, WebView webView) {
        WebSettings settings = webView.getSettings();
        settings.setCacheMode(-1);
        settings.setDomStorageEnabled(true);
        settings.setSupportZoom(false);
        settings.setBuiltInZoomControls(false);
        settings.setDisplayZoomControls(false);
        webView.setVerticalScrollBarEnabled(false);
        webView.setHorizontalScrollBarEnabled(false);
        settings.setUseWideViewPort(false);
        settings.setTextZoom(100);
        settings.setLoadWithOverviewMode(true);
        if (Build.VERSION.SDK_INT >= 29) {
            settings.setForceDark(0);
        }
        settings.setMixedContentMode(2);
        CookieManager.getInstance().setAcceptThirdPartyCookies(webView, true);
        settings.setUserAgentString(settings.getUserAgentString() + ";HuaweiHealth");
        try {
            webView.setOverScrollMode(2);
        } catch (AndroidRuntimeException e) {
            LogUtil.w("H5PRO_WebKitUtil", "initWebSetting: " + e.getMessage());
        }
    }

    public static void closeWebViewSettings(final WebView webView) {
        if (Looper.getMainLooper().getThread() != Thread.currentThread()) {
            LogUtil.i("H5PRO_WebKitUtil", "closeWebViewSettings: subThread");
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.huawei.health.h5pro.webkit.WebKitUtil.1
                @Override // java.lang.Runnable
                public void run() {
                    WebKitUtil.closeWebViewSettings(webView);
                }
            });
        } else if (webView == null) {
            LogUtil.w("H5PRO_WebKitUtil", "closeWebViewSettings: webView is null");
        } else {
            webView.getSettings().setJavaScriptEnabled(false);
        }
    }
}
