package com.huawei.watchface.utils;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.WebView;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.openalliance.ad.constant.ClickDestination;
import com.huawei.operation.utils.Constants;
import com.huawei.pluginachievement.manager.model.KakaConstants;
import com.huawei.secure.android.common.util.SafeString;
import com.huawei.secure.android.common.webview.SafeWebView;
import com.huawei.watchface.dc;
import com.huawei.watchface.dn;
import com.huawei.watchface.mvp.model.helper.JsSafeUrlSystemParamManager;
import com.huawei.watchface.utils.callback.PluginOperationAdapter;
import com.huawei.watchface.videoedit.utils.Utils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* loaded from: classes7.dex */
public class WebViewUtils {

    /* renamed from: a, reason: collision with root package name */
    private static final String f11199a = "WebViewUtils";
    private static List<String> b = new ArrayList(Arrays.asList("huaweihealth", "huaweischeme", "geo:", "hiapplink:", ClickDestination.APPMARKET, "hwt:", "hwmediacenter:", "himovie:", "mailto:", KakaConstants.SCHEME_TEL));

    private WebViewUtils() {
    }

    public static boolean a(Context context, PluginOperationAdapter pluginOperationAdapter, String str) {
        if (context == null || pluginOperationAdapter == null || TextUtils.isEmpty(str)) {
            HwLog.i(f11199a, "mAdapter is null ");
            return false;
        }
        if (str.startsWith("alipays:") || str.startsWith("alipay")) {
            HwLog.i(f11199a, "checkInstalledWeChatOrAlipay alipays and is install alipay is = " + CommonUtils.c(context, "com.eg.android.AlipayGphone"));
            a(context, str);
            return true;
        }
        if (e(str)) {
            a(context, str);
            return true;
        }
        if (str.startsWith("weixin://wap/pay?")) {
            String str2 = f11199a;
            HwLog.i(str2, "checkInstalledWeChatOrAlipay weixin");
            if (!CommonUtils.c(context, "com.tencent.mm")) {
                HwLog.i(str2, "not install weixin");
                CommonUtils.d(context, "com.tencent.mm");
            } else {
                a(context, str);
            }
            return true;
        }
        if (str.startsWith("cmblife://pay?")) {
            HwLog.i(f11199a, "schemeHandle CMBLIFE_PAY");
            a(context, str);
            return true;
        }
        HwLog.i(f11199a, "scheme not in the list return false.");
        return false;
    }

    private static boolean e(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        Iterator<String> it = b.iterator();
        while (it.hasNext()) {
            if (str.startsWith(it.next())) {
                return true;
            }
        }
        return false;
    }

    public static void a(Context context, String str) {
        String str2 = f11199a;
        HwLog.i(str2, "jumpToActivity");
        try {
            Intent intent = new Intent();
            intent.setAction(CommonConstant.ACTION.HWID_SCHEME_URL);
            intent.setFlags(268435456);
            intent.setData(Uri.parse(str));
            intent.addCategory("android.intent.category.BROWSABLE");
            intent.setSelector(null);
            if (CommonUtils.b(context, intent)) {
                context.startActivity(intent);
            }
            HwLog.i(str2, "jump to scheme view");
        } catch (ActivityNotFoundException unused) {
            HwLog.e(f11199a, "jumpToActivity ActivityNotFoundException");
        } catch (Exception unused2) {
            HwLog.e(f11199a, "jumpToActivity exception");
        }
    }

    public static void b(Context context, String str) {
        try {
            HwLog.i(f11199a, "jumpToHuaweiMarketDetailActivity enter");
            Intent intent = new Intent();
            intent.setAction("com.huawei.appmarket.appmarket.intent.action.AppDetail.withid");
            intent.setFlags(268435456);
            intent.putExtra("appId", str);
            intent.setSelector(null);
            intent.setPackage("com.huawei.appmarket");
            CommonUtils.a(context, intent);
        } catch (ActivityNotFoundException unused) {
            HwLog.e(f11199a, "jumpToActivity ActivityNotFoundException");
        } catch (Exception unused2) {
            HwLog.e(f11199a, "jumpToActivity exception");
        }
    }

    public static boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String lowerCase = str.toLowerCase(Locale.ENGLISH);
        return lowerCase.startsWith("https://") || lowerCase.startsWith("http://") || lowerCase.startsWith(Constants.PREFIX_FILE);
    }

    public static String b(String str) {
        return TextUtils.isEmpty(str) ? "" : SafeString.replace(str, " ", Constants.PERCENT_20);
    }

    public static String c(String str) {
        return Constants.JAVA_SCRIPT + str;
    }

    public static String getUrl(String str, String str2) {
        return Constants.JAVA_SCRIPT + str + Constants.LEFT_BRACKET + str2 + Constants.RIGHT_BRACKET;
    }

    public static void a(SslError sslError) {
        switch (sslError.getPrimaryError()) {
            case 0:
                HwLog.i(f11199a, "onReceivedSslError SSL_NOTYETVALID");
                break;
            case 1:
                HwLog.i(f11199a, "onReceivedSslError SSL_EXPIRED");
                break;
            case 2:
                HwLog.i(f11199a, "onReceivedSslError SSL_IDMISMATCH");
                break;
            case 3:
                HwLog.i(f11199a, "onReceivedSslError SSL_UNTRUSTED");
                break;
            case 4:
                HwLog.i(f11199a, "onReceivedSslError SSL_DATE_INVALID");
                break;
            case 5:
                HwLog.i(f11199a, "onReceivedSslError SSL_INVALID");
                break;
            case 6:
                HwLog.i(f11199a, "onReceivedSslError SSL_MAX_ERROR");
                break;
        }
    }

    public static String d(String str) {
        String path = Uri.parse(str).getPath();
        String str2 = "text/html";
        if (path == null) {
            HwLog.i(f11199a, "path == null");
            return "text/html";
        }
        if (path.endsWith(".css")) {
            str2 = "text/css";
        } else if (path.endsWith(".js")) {
            str2 = "application/x-javascript";
        } else if (path.endsWith(".jpg") || path.endsWith(Utils.SUFFIX_GIF) || path.endsWith(".png") || path.endsWith(".jpeg") || path.endsWith(".webp") || path.endsWith(".bmp")) {
            str2 = Constants.IMAGE_TYPE;
        }
        HwLog.i(f11199a, "getMime ".concat(str2));
        return str2;
    }

    public static String a(WebView webView) {
        try {
            dn dnVar = new dn(webView);
            if (webView != null) {
                Object tag = webView.getTag();
                if (tag != null && (tag instanceof Integer) && tag == "0") {
                    return "";
                }
                return dnVar.a();
            }
        } catch (Throwable th) {
            HwLog.i(f11199a, "getUrlMethod error: " + HwLog.printException(th));
        }
        return "";
    }

    public static void a(Activity activity, WebView webView, String str) {
        if (activity == null || webView == null) {
            HwLog.e(f11199a, "setDarkMode params is null");
            return;
        }
        if (Build.VERSION.SDK_INT <= 28 || CommonUtils.isAndroid13()) {
            HwLog.e(f11199a, "setDarkMode Build.VERSION.SDK_INT <= Build.VERSION_CODES.P or isAndroid13 ");
            return;
        }
        try {
            if (JsSafeUrlSystemParamManager.getInstance().b(str)) {
                webView.getSettings().setForceDark(0);
            } else {
                dc.a(activity, webView);
            }
        } catch (Exception e) {
            HwLog.e(f11199a, "setDarkMode error = " + HwLog.printException(e));
        }
    }

    public static void a(SafeWebView safeWebView) {
        if (safeWebView != null) {
            HwLog.i(f11199a, "onDestroy destroyWebView");
            ViewParent parent = safeWebView.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(safeWebView);
            }
            safeWebView.stopLoading();
            safeWebView.getSettings().setJavaScriptEnabled(false);
            safeWebView.clearHistory();
            safeWebView.clearView();
            safeWebView.removeAllViews();
            safeWebView.destroy();
        }
    }
}
