package defpackage;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.webkit.WebSettings;
import android.webkit.WebView;
import androidx.webkit.WebSettingsCompat;
import androidx.webkit.WebViewFeature;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;

/* loaded from: classes6.dex */
public class nrt {
    public static boolean a(Context context) {
        if (context != null) {
            return Build.VERSION.SDK_INT > 28 && (context.getResources().getConfiguration().uiMode & 48) == 32;
        }
        LogUtil.h("HealthDarkModeUtils", "isDarkMode: contxet is null");
        return false;
    }

    public static WebView cKg_(Context context, WebView webView) {
        PackageInfo currentWebViewPackage;
        if (webView == null || context == null) {
            LogUtil.h("HealthDarkModeUtils", "setForceDarkIfNeed() failed with null webview or context:", webView, context);
            return webView;
        }
        if (Build.VERSION.SDK_INT > 28 && (currentWebViewPackage = WebView.getCurrentWebViewPackage()) != null && "com.huawei.webview".equalsIgnoreCase(currentWebViewPackage.packageName) && CommonUtil.d(currentWebViewPackage.versionName, "10.0.0.300") >= 0) {
            boolean a2 = a(context);
            WebSettings settings = webView.getSettings();
            int forceDark = settings.getForceDark();
            LogUtil.a("HealthDarkModeUtils", "setForceDarkIfNeed: isDarkMode -> " + a2 + ", forceDark -> " + forceDark);
            if (a2 && forceDark != 2) {
                if (WebViewFeature.isFeatureSupported(WebViewFeature.ALGORITHMIC_DARKENING)) {
                    LogUtil.a("HealthDarkModeUtils", "setForceDarkIfNeed: setAlgorithmicDarkeningAllowed");
                    WebSettingsCompat.setAlgorithmicDarkeningAllowed(settings, true);
                }
                settings.setForceDark(2);
            } else if (!a2 && forceDark != 1) {
                LogUtil.a("HealthDarkModeUtils", "setForceDarkIfNeed: WebSettings.FORCE_DARK_AUTO");
                settings.setForceDark(1);
            } else {
                LogUtil.a("HealthDarkModeUtils", "setForceDarkIfNeed: no need change");
            }
        }
        return webView;
    }

    public static void cKh_(Context context, WebView webView) {
        if (webView == null || context == null) {
            LogUtil.h("HealthDarkModeUtils", "setWebViewDarkIfNeed() failed with null webview or context:", webView, context);
            return;
        }
        if (Build.VERSION.SDK_INT <= 28) {
            return;
        }
        boolean a2 = a(context);
        WebSettings settings = webView.getSettings();
        int forceDark = webView.getSettings().getForceDark();
        if (a2 && forceDark != 2) {
            if (WebViewFeature.isFeatureSupported(WebViewFeature.ALGORITHMIC_DARKENING)) {
                LogUtil.a("HealthDarkModeUtils", "setAlgorithmicDarkeningAllowed:");
                WebSettingsCompat.setAlgorithmicDarkeningAllowed(settings, true);
            }
            settings.setForceDark(2);
            return;
        }
        if (!a2 && forceDark != 1) {
            LogUtil.a("HealthDarkModeUtils", "changeLightMode:");
            settings.setForceDark(1);
        } else {
            LogUtil.a("HealthDarkModeUtils", "setMode no need change");
        }
    }
}
