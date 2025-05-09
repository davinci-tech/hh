package com.huawei.openalliance.ad.utils;

import android.content.Context;
import android.os.Build;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.huawei.hms.network.base.common.trans.FileBinary;
import com.huawei.openalliance.ad.constant.MetaCreativeType;
import com.huawei.openalliance.ad.constant.MimeType;
import com.huawei.openalliance.ad.ho;
import com.huawei.operation.utils.Constants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes5.dex */
public class dq {

    /* renamed from: a, reason: collision with root package name */
    static final Pattern f7702a = Pattern.compile("\\S*[?]\\S*");
    private static final ArrayList<String> b;
    private static final Map<String, String> c;

    private static String c(String str) {
        Matcher matcher = f7702a.matcher(str);
        String[] split = str.split("/");
        String str2 = split[split.length - 1];
        if (matcher.find()) {
            String[] split2 = str2.split("\\?")[0].split("\\.");
            return split2.length == 1 ? "" : split2[split2.length - 1];
        }
        String[] split3 = str2.split("\\.");
        return split3.length == 1 ? "" : split3[split3.length - 1];
    }

    public static String b(String str) {
        return c.get(c(str).toLowerCase(Locale.ENGLISH));
    }

    public static boolean a(String str) {
        return b.contains(c(str).toLowerCase(Locale.ENGLISH));
    }

    public static void a(WebView webView) {
        WebSettings settings;
        if (webView == null || (settings = webView.getSettings()) == null) {
            return;
        }
        settings.setLoadsImagesAutomatically(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setJavaScriptEnabled(true);
        settings.setAllowFileAccess(false);
        settings.setCacheMode(2);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        settings.setGeolocationEnabled(false);
        settings.setAllowContentAccess(false);
    }

    public static WebView a(Context context) {
        String str;
        try {
            ho.b("WebViewUtil", "createWebview android sdk: " + Build.VERSION.SDK_INT);
            try {
                return context.isDeviceProtectedStorage() ? new com.huawei.openalliance.ad.views.linkscroll.e((Context) ck.a(context, "createCredentialProtectedStorageContext", (Class<?>[]) null, (Object[]) null)) : new com.huawei.openalliance.ad.views.linkscroll.e(context);
            } catch (IllegalArgumentException unused) {
                str = "createWebview IllegalArgumentException";
                ho.d("WebViewUtil", str);
                return null;
            } catch (Exception unused2) {
                str = "createWebview Exception";
                ho.d("WebViewUtil", str);
                return null;
            }
        } catch (Throwable th) {
            ho.c("WebViewUtil", "fail to create webview, " + th.getClass().getSimpleName());
            return null;
        }
    }

    static {
        ArrayList<String> arrayList = new ArrayList<>();
        b = arrayList;
        HashMap hashMap = new HashMap();
        c = hashMap;
        arrayList.add(Constants.SUFFIX_HTML);
        arrayList.add("js");
        arrayList.add("png");
        arrayList.add("jpg");
        arrayList.add("svg");
        arrayList.add(MetaCreativeType.GIF);
        arrayList.add("css");
        hashMap.put(Constants.SUFFIX_HTML, "text/html");
        hashMap.put("js", "application/x-javascript");
        hashMap.put("png", "image/png");
        hashMap.put("jpg", FileBinary.HEAD_VALUE_CONTENT_TYPE_OCTET_STREAM);
        hashMap.put("svg", "image/svg+xml");
        hashMap.put(MetaCreativeType.GIF, MimeType.GIF);
        hashMap.put("css", "text/css");
    }
}
