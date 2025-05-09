package defpackage;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.TextUtils;
import androidx.webkit.ProxyConfig;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$string;
import health.compact.a.CommonUtil;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class nsa {
    private static boolean d() {
        return false;
    }

    public static String e(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("MarketRouterUtil", "getUrlType linkUrl is null");
            return "0";
        }
        try {
            String queryParameter = Uri.parse(g(str)).getQueryParameter("urlType");
            return TextUtils.isEmpty(queryParameter) ? "0" : queryParameter;
        } catch (UnsupportedOperationException unused) {
            Map<String, String> c = c(str);
            if (c == null) {
                return "0";
            }
            String str2 = c.get("urlType");
            return TextUtils.isEmpty(str2) ? "0" : str2;
        }
    }

    public static Map<String, String> c(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("MarketRouterUtil", "getUrlParam linkUrl is null");
            return null;
        }
        String[] split = str.split("\\?");
        if (split.length <= 1) {
            LogUtil.h("MarketRouterUtil", "getUrlParam urlDivided is error");
            return null;
        }
        HashMap hashMap = new HashMap();
        for (String str2 : split[1].split("&")) {
            String[] split2 = str2.split("=");
            if (split2.length > 1) {
                hashMap.put(split2[0], split2[1]);
            }
        }
        return hashMap;
    }

    public static boolean f(String str) {
        if (!TextUtils.isEmpty(str)) {
            return str.startsWith("http") || str.startsWith(ProxyConfig.MATCH_HTTPS) || str.startsWith("file");
        }
        LogUtil.h("MarketRouterUtil", "isUrlScheme() serviceDetailUrl is empty.");
        return false;
    }

    public static String d(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("MarketRouterUtil", "decodeUrl url is empty ");
            return "";
        }
        try {
            return URLDecoder.decode(str, "UTF-8");
        } catch (UnsupportedEncodingException | IllegalArgumentException e) {
            LogUtil.b("MarketRouterUtil", "decodeUrl exception ", ExceptionUtils.d(e));
            return "";
        }
    }

    public static String a(String str) {
        int indexOf;
        if (TextUtils.isEmpty(str) || !str.contains("downloadUrl=") || (indexOf = str.indexOf("downloadUrl=")) <= 0) {
            return "";
        }
        String substring = str.substring(indexOf + 12, str.length());
        try {
            String string = new JSONObject(substring).getString("url");
            LogUtil.a("MarketRouterUtil", "downloadUrl is json");
            return string;
        } catch (JSONException unused) {
            LogUtil.a("MarketRouterUtil", "downloadUrl is string");
            return substring;
        }
    }

    public static String c(String[] strArr) {
        if (strArr == null) {
            return "";
        }
        for (String str : strArr) {
            String b = b(str);
            if (jdm.b(BaseApplication.e(), b)) {
                return b;
            }
        }
        return "";
    }

    public static String b(String str) {
        return ("com.huawei.health".equals(str) && d()) ? com.huawei.hwcommonmodel.application.BaseApplication.APP_PACKAGE_GOOGLE_HEALTH : str;
    }

    public static void b(String str, String str2) {
        if (!d()) {
            e(str, str2);
        } else {
            LogUtil.h("MarketRouterUtil", "GOOGLE_PLAY_OEM_DISABLE not jump to Market.");
            nrh.d(BaseApplication.e(), BaseApplication.e().getResources().getString(R$string.IDS_main_sns_app_store_content));
        }
    }

    private static void e(String str, String str2) {
        if (jdm.b(BaseApplication.e(), "com.huawei.appmarket")) {
            if (CommonUtil.bf()) {
                LogUtil.a("MarketRouterUtil", "new Honor,jump to downloadUrl.");
                i(str2);
                return;
            }
            try {
                if (TextUtils.isEmpty(str)) {
                    LogUtil.h("MarketRouterUtil", "jumpToMarket packageName is empty");
                    return;
                }
                Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse("market://details?id=" + str));
                intent.addFlags(268435456);
                intent.setPackage("com.huawei.appmarket");
                cKk_(intent);
                return;
            } catch (ActivityNotFoundException unused) {
                LogUtil.b("MarketRouterUtil", "jumpToMarket ActivityNotFoundException");
                i(str2);
                return;
            }
        }
        i(str2);
    }

    public static void i(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("MarketRouterUtil", "jumpBrowsers webUrl is null");
            return;
        }
        try {
            Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse(str));
            intent.setFlags(268435456);
            PackageManager packageManager = BaseApplication.e().getPackageManager();
            if (packageManager != null) {
                LogUtil.a("MarketRouterUtil", "jumpToBrowsers packageManager is not null");
                ResolveInfo resolveActivity = packageManager.resolveActivity(intent, 65536);
                if (resolveActivity != null) {
                    LogUtil.a("MarketRouterUtil", "jumpToBrowsers resolveInfo is not null");
                    ComponentName componentName = new ComponentName(resolveActivity.activityInfo.packageName, resolveActivity.activityInfo.name);
                    intent.setComponent(componentName);
                    nsn.cLM_(intent, componentName.getPackageName(), BaseApplication.wa_(), BaseApplication.e().getString(R$string.IDS_browser_app_name));
                }
            }
        } catch (ActivityNotFoundException | UnsupportedOperationException e) {
            LogUtil.b("MarketRouterUtil", "jumpBrowsers exception" + e.getMessage());
        }
    }

    private static void cKk_(Intent intent) {
        ResolveInfo resolveActivity;
        PackageManager packageManager = BaseApplication.e().getPackageManager();
        if (packageManager == null || (resolveActivity = packageManager.resolveActivity(intent, 65536)) == null) {
            return;
        }
        intent.setComponent(new ComponentName(resolveActivity.activityInfo.packageName, resolveActivity.activityInfo.name));
        try {
            nsn.cLM_(intent, "com.huawei.appmarket", BaseApplication.wa_(), "");
        } catch (ActivityNotFoundException e) {
            LogUtil.b("MarketRouterUtil", "startActivity is error" + e.getMessage());
        }
    }

    public static String g(String str) {
        if (!TextUtils.isEmpty(str)) {
            return str.contains("#") ? str.replace("#", "") : str;
        }
        LogUtil.a("MarketRouterUtil", "getUrl url is null");
        return str;
    }
}
