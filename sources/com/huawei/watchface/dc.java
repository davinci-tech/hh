package com.huawei.watchface;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.webkit.WebView;
import com.huawei.watchface.utils.CommonUtils;
import com.huawei.watchface.utils.HwLog;

/* loaded from: classes7.dex */
public class dc {
    public static boolean a(Context context) {
        if (context != null) {
            return Build.VERSION.SDK_INT > 28 && (context.getResources().getConfiguration().uiMode & 48) == 32;
        }
        HwLog.w("HealthDarkModeUtils", "isDarkMode: contxet is null");
        return false;
    }

    public static WebView a(Context context, WebView webView) {
        if (webView == null || context == null) {
            HwLog.w("HealthDarkModeUtils", "setForceDarkIfNeed() failed with null webview or context:" + webView + context);
            return webView;
        }
        if (Build.VERSION.SDK_INT <= 28) {
            return webView;
        }
        if (CommonUtils.isAndroid13()) {
            HwLog.i("HealthDarkModeUtils", "setForceDarkIfNeed isAndroid13");
            return webView;
        }
        PackageInfo currentWebViewPackage = WebView.getCurrentWebViewPackage();
        if (currentWebViewPackage == null || !"com.huawei.webview".equalsIgnoreCase(currentWebViewPackage.packageName) || CommonUtils.b(currentWebViewPackage.versionName, "10.0.0.300") < 0) {
            HwLog.i("HealthDarkModeUtils", "packageInfo is null or is not huaweiWebView");
            return webView;
        }
        if (a(context)) {
            webView.getSettings().setForceDark(2);
            HwLog.i("HealthDarkModeUtils", "setForceDarkIfNeed FORCE_DARK_ON");
        } else {
            webView.getSettings().setForceDark(1);
            HwLog.i("HealthDarkModeUtils", "setForceDarkIfNeed FORCE_DARK_AUTO");
        }
        return webView;
    }
}
