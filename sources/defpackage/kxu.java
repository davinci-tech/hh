package defpackage;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Proxy;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.OperationKey;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.params.ConnRouteParams;
import org.apache.http.params.HttpParams;

/* loaded from: classes5.dex */
public class kxu {
    private static String c;
    private static String d;
    private static String f;
    private static String m;
    private static String o;
    private static kxl e = new kxl();

    /* renamed from: a, reason: collision with root package name */
    private static kxl f14689a = new kxl();
    private static Map<String, kxl> j = new HashMap(16);
    private static kxl b = new kxl();
    private static kxl l = new kxl();
    private static String k = "https://query.hicloud.com/accessory/v2/checkEx.action";
    private static int i = -1;
    private static int n = 0;
    private static long h = -1;
    private static boolean g = false;

    public static String e(boolean z) {
        return d(z, false);
    }

    public static String d(boolean z, boolean z2) {
        GRSManager a2 = GRSManager.a(BaseApplication.getContext());
        LogUtil.a("HwSelfUpdateUtility", "getUpdateUrl isHonor: ", Boolean.valueOf(z));
        if (z) {
            return kxz.e() + "/ring2/v2/" + c(z2);
        }
        return a2.getNoCheckUrl("ROOT", CommonUtil.cc() ? "com.huawei.cloud.hotaTest" : "com.huawei.cloud.hota", a2.getCommonCountryCode()) + "/ring2/v2/" + c(z2);
    }

    public static String f() {
        return c;
    }

    public static void a(String str) {
        c = str;
    }

    public static String c() {
        return k;
    }

    public static void d(String str) {
        k = str;
    }

    public static String m() {
        return o;
    }

    public static long i() {
        return h;
    }

    public static void d(long j2) {
        h = j2;
    }

    public static boolean g() {
        return g;
    }

    public static void a(boolean z) {
        g = z;
    }

    public static int h() {
        return i;
    }

    public static void e(int i2) {
        i = i2;
    }

    public static int o() {
        return n;
    }

    public static void a(int i2) {
        n = i2;
    }

    public static void d(Context context, String str, String str2) {
        if (context == null) {
            LogUtil.h("HwSelfUpdateUtility", "installApk context is null");
        } else {
            new kyn(context, str, str2).e();
        }
    }

    private static boolean g(String str) {
        return new File(str).exists();
    }

    public static boolean d(Context context) {
        if (context == null) {
            LogUtil.h("HwSelfUpdateUtility", "isSoFileExist context is null");
            return false;
        }
        return g(context.getApplicationInfo().dataDir + "/files/libbspatchforselfupdate.so");
    }

    public static String j() {
        return f;
    }

    public static void f(String str) {
        f = str;
    }

    public static String a() {
        return d;
    }

    public static void e(String str) {
        d = str;
    }

    public static String n() {
        return m;
    }

    public static void h(String str) {
        m = str;
    }

    public static boolean b(String str) {
        if (str == null) {
            return false;
        }
        File file = new File(str);
        if (file.exists()) {
            return file.delete();
        }
        return false;
    }

    public static boolean b(Context context) {
        if (context == null) {
            LogUtil.h("HwSelfUpdateUtility", "isNetworkAvailable context is null");
            return false;
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        return connectivityManager != null && bSA_(connectivityManager.getAllNetworkInfo());
    }

    private static boolean bSA_(NetworkInfo[] networkInfoArr) {
        if (networkInfoArr != null) {
            for (NetworkInfo networkInfo : networkInfoArr) {
                if (networkInfo.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void a(HttpRequest httpRequest, HttpClient httpClient, Context context) {
        if (httpRequest == null || httpClient == null || context == null) {
            LogUtil.h("HwSelfUpdateUtility", "setHttpProxy param is null");
            return;
        }
        String host = Proxy.getHost(context);
        int port = Proxy.getPort(context);
        NetworkInfo bSz_ = bSz_(context);
        if (bSz_ != null && bSz_.getType() == 1) {
            LogUtil.c("HwSelfUpdateUtility", "setHttpProxy networkInfo is not null and networkInfo type is wifi");
            return;
        }
        if (host == null || host.length() <= 0 || port == -1) {
            return;
        }
        HttpParams params = httpClient.getParams();
        ConnRouteParams.setDefaultProxy(params, new HttpHost(host, port));
        httpRequest.setParams(params);
    }

    private static NetworkInfo bSz_(Context context) {
        ConnectivityManager connectivityManager = context.getSystemService("connectivity") instanceof ConnectivityManager ? (ConnectivityManager) context.getSystemService("connectivity") : null;
        if (connectivityManager != null) {
            return connectivityManager.getActiveNetworkInfo();
        }
        return null;
    }

    public static kxl e() {
        return e;
    }

    public static void a(kxl kxlVar) {
        e = kxlVar;
    }

    public static kxl b() {
        return f14689a;
    }

    public static void e(kxl kxlVar) {
        f14689a = kxlVar;
    }

    public static void d(String str, kxl kxlVar) {
        j.put(str, kxlVar);
    }

    public static kxl d() {
        return b;
    }

    public static kxl l() {
        return l;
    }

    public static void b(kxl kxlVar) {
        l = kxlVar;
    }

    public static String a(String str, Context context) {
        PackageInfo packageInfo;
        if (context == null) {
            LogUtil.h("HwSelfUpdateUtility", "getPackageVersionCode context is null");
            return null;
        }
        try {
            packageInfo = context.getPackageManager().getPackageInfo(str, 0);
        } catch (PackageManager.NameNotFoundException unused) {
            LogUtil.b("HwSelfUpdateUtility", "getPackageVersionCode exception.");
            packageInfo = null;
        }
        if (packageInfo != null) {
            return String.valueOf(packageInfo.versionCode);
        }
        return null;
    }

    public static void a(int i2, boolean z, boolean z2, boolean z3) {
        if (z3) {
            i2 += 10000;
        } else if (z2) {
            i2 += 1000;
        } else if (z) {
            i2 += 100;
        }
        LogUtil.a("HwSelfUpdateUtility", "eventErrorCode = ", Integer.valueOf(i2));
        OpAnalyticsUtil.getInstance().setEventOneErrorCode(OperationKey.HEALTH_APP_OTA_DOWNLOAD_85070033.value(), i2);
    }

    public static kxl c(String str) {
        if (j.containsKey(str)) {
            return j.get(str);
        }
        return null;
    }

    public static String c(boolean z) {
        return z ? "onestopCheck.action" : "CheckEx.action?ruleAttr=true";
    }
}
