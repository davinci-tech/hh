package defpackage;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Build;
import android.text.TextUtils;
import com.huawei.agconnect.apms.instrument.apacheclient.ApacheClientInstrumentation;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.util.Locale;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class kyp {

    /* renamed from: a, reason: collision with root package name */
    private static DeviceInfo f14705a = null;
    private static boolean e = false;

    public static String e() {
        return Build.FINGERPRINT;
    }

    public static String a() {
        return Build.MODEL;
    }

    public static String b(Context context) {
        String str;
        String str2;
        if (context != null) {
            Configuration configuration = context.getResources().getConfiguration();
            str = configuration.locale.getLanguage();
            str2 = configuration.locale.getCountry();
        } else {
            str = "";
            str2 = "";
        }
        return (str + '-' + str2).toLowerCase(Locale.ENGLISH);
    }

    public static String b() {
        return "Android " + Build.VERSION.RELEASE;
    }

    public static String d(String str, Context context) {
        PackageInfo packageInfo;
        if (context == null) {
            LogUtil.h("checkNewVersionThreadUtil", "getPackageVersionName context is null");
            return null;
        }
        try {
            packageInfo = context.getPackageManager().getPackageInfo(str, 0);
        } catch (PackageManager.NameNotFoundException unused) {
            LogUtil.b("checkNewVersionThreadUtil", "getPackageVersionName NameNotFoundException ", str, " does not found");
            packageInfo = null;
        }
        if (packageInfo != null) {
            return String.valueOf(packageInfo.versionName);
        }
        return null;
    }

    public static void e(DeviceInfo deviceInfo) {
        f14705a = deviceInfo;
    }

    public static void d(boolean z) {
        e = z;
    }

    public static String e(String str, boolean z, boolean z2) {
        LogUtil.a("checkNewVersionThreadUtil", "getResponse url = ", kxu.f());
        if (cvz.c(f14705a) && !z && kxz.e() == null) {
            LogUtil.h("checkNewVersionThreadUtil", "It is honor device but is not config ota");
            return "{\"status\":\"1\"}";
        }
        String d = d(kxu.f(), str);
        return z2 ? d : c(d, str, false, z, true);
    }

    public static String d(String str) {
        LogUtil.a("checkNewVersionThreadUtil", "getResponse url = ", kxu.c());
        String e2 = jah.c().e("domain_honor_ota_unavailable");
        if (e && e2 != null && e2.equals("YES")) {
            LogUtil.h("checkNewVersionThreadUtil", "getScaleResponse not detected");
            return "{\"status\":\"1\"}";
        }
        if (e && kxz.e() == null) {
            LogUtil.h("checkNewVersionThreadUtil", "getScaleResponse It is honor device but is not config ota");
            return "{\"status\":\"1\"}";
        }
        return c(d(kxu.c(), str), str, true, false, false);
    }

    private static String d(String str, String str2) {
        LogUtil.c("checkNewVersionThreadUtil", "postReq, urlString:", str);
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        if (kxz.e(str)) {
            return jeb.d(str, str2);
        }
        return jee.d(str, str2, true);
    }

    public static kxg d(Context context, String str) {
        kxg kxgVar = new kxg();
        kxgVar.e(e());
        kxgVar.a(a());
        kxgVar.d(c());
        kxgVar.i("");
        kxgVar.f("");
        kxgVar.g(b(context));
        kxgVar.h(b());
        kxgVar.c("ro.product.CustCVersion");
        kxgVar.b("ro.product.CustDVersion");
        kxg b = b(context, str, kxgVar);
        if (kxu.d(context)) {
            b.j("1.1.3");
        }
        return b;
    }

    private static kxg b(Context context, String str, kxg kxgVar) {
        LogUtil.a("checkNewVersionThreadUtil", "getComponent packageName = ", str, ",versionFilter = ", kxgVar);
        try {
            String a2 = kxv.a(context.getPackageManager().getPackageInfo(str, 64).signatures[0].toCharsString());
            kxgVar.m(str);
            kxgVar.n(kxu.a(str, context));
            kxgVar.l(d(str, context));
            kxgVar.o(a2);
        } catch (PackageManager.NameNotFoundException unused) {
            LogUtil.b("checkNewVersionThreadUtil", "getComponent packageName = ", str, ",exception = NameNotFoundException");
        } catch (Exception unused2) {
            LogUtil.b("checkNewVersionThreadUtil", "getComponent Exception packageName = ", str);
        }
        return kxgVar;
    }

    public static kxj e(kxl kxlVar) {
        kxj kxjVar = new kxj();
        kxjVar.f(kxlVar.m());
        kxjVar.m(kxlVar.v());
        kxjVar.j(kxlVar.k());
        kxjVar.l(kxlVar.ac());
        kxjVar.a(kxlVar.c());
        kxjVar.b(kxlVar.d());
        kxjVar.d(kxlVar.a());
        kxjVar.k(kxlVar.x());
        kxjVar.e(kxlVar.g());
        kxjVar.i(kxlVar.h());
        kxjVar.h(kxlVar.y());
        kxjVar.g(kxlVar.u());
        kxjVar.n(kxlVar.w());
        kxjVar.e(kxlVar.f());
        kxjVar.c(kxlVar.b());
        kxjVar.d(kxlVar.n());
        kxjVar.a(kxlVar.e());
        kxjVar.c(kxlVar.j());
        return kxjVar;
    }

    public static int b(Context context, String str, OutputStream outputStream) throws IOException, URISyntaxException {
        HttpClient defaultHttpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(str);
        kxu.a(httpGet, defaultHttpClient, context);
        HttpParams params = httpGet.getParams();
        params.setIntParameter("http.socket.timeout", 20000);
        params.setIntParameter("http.connection.timeout", 20000);
        HttpProtocolParams.setUserAgent(params, kxu.m());
        HttpResponse execute = !(defaultHttpClient instanceof HttpClient) ? defaultHttpClient.execute(httpGet) : ApacheClientInstrumentation.execute(defaultHttpClient, httpGet);
        int statusCode = execute.getStatusLine().getStatusCode();
        if (outputStream != null) {
            execute.getEntity().writeTo(outputStream);
        }
        httpGet.abort();
        return statusCode;
    }

    private static int b(String str) {
        int i = -1;
        try {
            i = Integer.parseInt(new JSONObject(str).getString("status"));
            LogUtil.a("checkNewVersionThreadUtil", "getNewVersionStatus check new version status = ", Integer.valueOf(i));
            return i;
        } catch (NumberFormatException unused) {
            LogUtil.b("checkNewVersionThreadUtil", "getNewVersionStatus NumberFormatException");
            return i;
        } catch (JSONException unused2) {
            LogUtil.b("checkNewVersionThreadUtil", "getNewVersionStatus JSONException");
            return i;
        }
    }

    private static String c(String str, String str2, boolean z, boolean z2, boolean z3) {
        String str3;
        if (str != null && b(str) == 0) {
            return str;
        }
        GRSManager a2 = GRSManager.a(BaseApplication.getContext());
        LogUtil.a("checkNewVersionThreadUtil", "getFinalReceive isHonorDevice: ", Boolean.valueOf(cvz.c(f14705a)), " sIsWeightHonor ", Boolean.valueOf(e));
        if ((e && !z2 && !z3) || (cvz.c(f14705a) && !z && !z2)) {
            str3 = kxz.e() + "/Ring/v2/CheckEx.action?ruleAttr=true";
        } else if (CommonUtil.as() && TextUtils.equals(kxz.g(BaseApplication.getContext()), "test_mode")) {
            str3 = "/ring/v2/CheckEx.action?ruleAttr=true";
        } else {
            str3 = a2.getNoCheckUrl("ROOT", CommonUtil.cc() ? "com.huawei.cloud.hotaTest" : "com.huawei.cloud.hota", a2.getCommonCountryCode()) + "/Ring/v2/CheckEx.action?ruleAttr=true";
        }
        LogUtil.a("checkNewVersionThreadUtil", "getFinalReceive url =", str3);
        return d(str3, str2);
    }

    public static String c() {
        return "ro.build.display.id";
    }
}
