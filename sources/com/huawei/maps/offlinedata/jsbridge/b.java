package com.huawei.maps.offlinedata.jsbridge;

import android.os.Handler;
import android.os.Looper;
import android.webkit.WebView;
import com.huawei.maps.offlinedata.utils.d;
import com.huawei.maps.offlinedata.utils.g;
import com.huawei.operation.utils.Constants;

/* loaded from: classes5.dex */
public class b {

    /* renamed from: a, reason: collision with root package name */
    private static volatile b f6447a;
    private WebView b;

    private b() {
    }

    public static b a() {
        if (f6447a == null) {
            synchronized (b.class) {
                if (f6447a == null) {
                    f6447a = new b();
                }
            }
        }
        return f6447a;
    }

    public void a(WebView webView) {
        this.b = webView;
    }

    private String a(Object... objArr) {
        if (objArr == null || objArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < objArr.length; i++) {
            sb.append(d.a(objArr[i]));
            if (i < objArr.length - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }

    public void a(final String str) {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.huawei.maps.offlinedata.jsbridge.b$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                b.this.b(str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(String str) {
        WebView webView = this.b;
        if (webView != null) {
            webView.evaluateJavascript(str, null);
            return;
        }
        com.huawei.maps.offlinedata.service.a.a().a(false);
        g.c("WebViewHelper", "webView is null. execJs: " + str);
    }

    public void a(String str, Object... objArr) {
        a(str + Constants.LEFT_BRACKET_ONLY + a(objArr) + Constants.RIGHT_BRACKET_ONLY);
    }

    public void b(String str, Object... objArr) {
        a("window.nativeEvent('" + str + "'," + a(objArr) + ");");
    }
}
