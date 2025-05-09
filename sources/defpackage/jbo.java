package defpackage;

import android.text.TextUtils;
import androidx.webkit.ProxyConfig;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.Utils;
import java.util.regex.PatternSyntaxException;

/* loaded from: classes5.dex */
public class jbo {

    /* renamed from: a, reason: collision with root package name */
    private static double f13713a = 0.0d;
    private static int b = 0;
    private static boolean c = false;
    private static double e;

    public static void e(int i) {
        b = i;
    }

    public static boolean a() {
        return c;
    }

    public static void c(boolean z) {
        c = z;
        if (z) {
            return;
        }
        b(Double.valueOf(0.0d));
        e(Double.valueOf(0.0d));
    }

    public static double b() {
        return e;
    }

    public static void b(Double d) {
        e = d.doubleValue();
    }

    public static double d() {
        return f13713a;
    }

    public static void e(Double d) {
        f13713a = d.doubleValue();
    }

    public static boolean h() {
        if (CommonUtil.bv()) {
            return false;
        }
        return !Utils.o();
    }

    public static void d(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("DeveloperWeatherManagerUtils", "isDeveloperEnableSimulated() location is empty");
            c(false);
            return;
        }
        if (!h()) {
            LogUtil.h("DeveloperWeatherManagerUtils", "isDeveloperEnableSimulated() is not DeveloperTest");
            c(false);
            return;
        }
        if ("OFF".equalsIgnoreCase(str)) {
            LogUtil.h("DeveloperWeatherManagerUtils", "getDeveloperWeatherUrl() location is default location");
            c(false);
            return;
        }
        if ("ON".equalsIgnoreCase(str)) {
            LogUtil.h("DeveloperWeatherManagerUtils", "setDeveloperEnableSimulated location is ON");
            b(Double.valueOf(0.0d));
            e(Double.valueOf(0.0d));
            c(true);
            return;
        }
        try {
            String[] split = str.split("_");
            if (split.length != 2) {
                LogUtil.h("DeveloperWeatherManagerUtils", "setDeveloperEnableSimulated() locations length != 2");
                c(false);
                return;
            }
            String str2 = split[0];
            String str3 = split[1];
            if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3)) {
                b(Double.valueOf(Double.parseDouble(str2)));
                e(Double.valueOf(Double.parseDouble(str3)));
                c(true);
                return;
            }
            LogUtil.h("DeveloperWeatherManagerUtils", "setDeveloperEnableSimulated() latitude is empty or longitude is empty");
            c(false);
        } catch (NumberFormatException | PatternSyntaxException unused) {
            LogUtil.b("DeveloperWeatherManagerUtils", "mFirstConnectReceiver() ACTION_WEATHER_LOCATION is error");
            c(false);
        }
    }

    public static String c() {
        if (!h()) {
            LogUtil.h("DeveloperWeatherManagerUtils", "getDeveloperWeatherUrl() is not DeveloperTest");
            return "";
        }
        if ((b & 1) == 0) {
            LogUtil.h("DeveloperWeatherManagerUtils", "getDeveloperWeatherUrl() mHttpCode & ACTION_WEATHER_HTTP_YES is 0");
            return "";
        }
        String noCheckUrl = GRSManager.a(BaseApplication.getContext()).getNoCheckUrl("domainHagHicloud", "");
        if (TextUtils.isEmpty(noCheckUrl)) {
            LogUtil.h("DeveloperWeatherManagerUtils", "getDeveloperWeatherUrl() url is empty");
            return "";
        }
        return noCheckUrl.replaceFirst(ProxyConfig.MATCH_HTTPS, "http");
    }

    public static String e() {
        if (!h()) {
            LogUtil.h("DeveloperWeatherManagerUtils", "getDeveloperTideUrl() is not DeveloperTest");
            return "";
        }
        if ((b & 2) == 0) {
            LogUtil.h("DeveloperWeatherManagerUtils", "getDeveloperTideUrl() mHttpCode & ACTION_TIDE_HTTP_YES is 0");
            return "";
        }
        String noCheckUrl = GRSManager.a(BaseApplication.getContext()).getNoCheckUrl("domainHagHicloud", "");
        if (TextUtils.isEmpty(noCheckUrl)) {
            LogUtil.h("DeveloperWeatherManagerUtils", "getDeveloperTideUrl() url is empty");
            return "";
        }
        return noCheckUrl.replaceFirst(ProxyConfig.MATCH_HTTPS, "http");
    }
}
