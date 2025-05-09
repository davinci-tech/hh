package defpackage;

import android.webkit.WebSettings;
import android.webkit.WebView;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes6.dex */
public class nte {
    public static void cMB_(WebView webView) {
        if (webView == null) {
            LogUtil.h("WebViewUtils", "webView is null");
            return;
        }
        WebSettings settings = webView.getSettings();
        settings.setGeolocationEnabled(false);
        settings.setAllowContentAccess(false);
        settings.setAllowFileAccessFromFileURLs(false);
        settings.setAllowFileAccess(false);
    }
}
