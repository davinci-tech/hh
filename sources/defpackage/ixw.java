package defpackage;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.huawei.haf.common.utils.CollectionUtils;
import com.huawei.hianalytics.v2.HiAnalytics;
import com.huawei.hms.network.embedded.x2;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.OpAnalyticsConstants;
import health.compact.a.CommonUtil;
import health.compact.a.EnvironmentUtils;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.Utils;
import health.compact.a.utils.StringUtils;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class ixw {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f13653a = new Object();
    private static JSONObject d = new JSONObject();
    private static HashSet<ixz> b = new HashSet<>();

    protected static void a() {
        if (EnvironmentUtils.c() && !BaseApplication.isRunningForeground()) {
            ReleaseLogUtil.a("R_BIAnalyticsImpl", "call onReport in background");
        } else {
            HiAnalytics.onReport();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void c(String str, LinkedHashMap<String, String> linkedHashMap) {
        iyj.e("BIAnalyticsImpl", "onReport() viewName");
        HiAnalytics.onResume(str, linkedHashMap);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void c(Context context) {
        iyj.e("BIAnalyticsImpl", "onResume() context = " + context);
        HiAnalytics.onResume(context);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void b(String str, LinkedHashMap<String, String> linkedHashMap) {
        iyj.e("BIAnalyticsImpl", "onPause() viewName");
        HiAnalytics.onPause(str, linkedHashMap);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void b(Context context) {
        iyj.e("BIAnalyticsImpl", "onPause() context = " + context);
        HiAnalytics.onPause(context);
    }

    protected static int b(Context context, String str, Map<String, Object> map, String str2) {
        try {
            JSONObject jSONObject = new JSONObject(d.toString());
            jSONObject.put("MANUFACTURER", Build.MANUFACTURER);
            if (map == null) {
                jSONObject.put("CONTENT", "[]");
            } else {
                jSONObject.put("CONTENT", "[" + new JSONObject(map).toString() + "]");
            }
            jSONObject.put(x2.AB_INFO, str2);
            iyj.e("BIAnalyticsImpl", str + jSONObject.toString());
            HiAnalytics.onEvent(context, str, jSONObject.toString());
            return 0;
        } catch (Exception unused) {
            iyj.a("BIAnalyticsImpl", "setEvent Exception！");
            return -1;
        }
    }

    protected static int d(Context context, String str, iyb iybVar, String str2) {
        try {
            JSONObject jSONObject = new JSONObject(d.toString());
            jSONObject.put("MANUFACTURER", Build.MANUFACTURER);
            e(iybVar.e(), jSONObject, "CONTENT");
            e(iybVar.a(), jSONObject, "scene");
            if (!CollectionUtils.d(iybVar.d())) {
                jSONObject.put("pageProperties", moj.e(iybVar.d()));
            } else {
                jSONObject.put("pageProperties", "[]");
            }
            if (StringUtils.g(str2)) {
                e(context, iybVar);
            } else {
                jSONObject.put(x2.AB_INFO, str2);
            }
            iyj.e("BIAnalyticsImpl", str + jSONObject.toString());
            HiAnalytics.onEvent(context, str, jSONObject.toString());
            return 0;
        } catch (Exception unused) {
            iyj.a("BIAnalyticsImpl", "setEvent Exception！");
            return -1;
        }
    }

    private static void e(Map<String, Object> map, JSONObject jSONObject, String str) throws JSONException {
        if (CollectionUtils.e(map)) {
            jSONObject.put(str, "[]");
            return;
        }
        jSONObject.put(str, "[" + new JSONObject(map).toString() + "]");
    }

    private static void e(Context context, iyb iybVar) {
        if (CollectionUtils.e(iybVar.e()) && CollectionUtils.e(iybVar.a()) && CollectionUtils.d(iybVar.d())) {
            return;
        }
        d(context);
    }

    protected static int a(Context context, String str, Map<String, Object> map) {
        try {
            JSONObject jSONObject = new JSONObject(d.toString());
            jSONObject.put("MANUFACTURER", Build.MANUFACTURER);
            if (map == null) {
                jSONObject.put("CONTENT", "[]");
            } else {
                jSONObject.put("CONTENT", "[" + new JSONObject(map).toString() + "]");
                d(context);
            }
            iyj.e("BIAnalyticsImpl", str + jSONObject.toString());
            HiAnalytics.onEvent(context, str, jSONObject.toString());
            return 0;
        } catch (Exception unused) {
            iyj.a("BIAnalyticsImpl", "setEvent Exception！");
            return -1;
        }
    }

    protected static int e(Context context, String str, iyb iybVar) {
        return d(context, str, iybVar, null);
    }

    protected static void d(Context context) {
        try {
            String b2 = SharedPreferenceManager.b(context, String.valueOf(10000), "AB_TEST_BI_INFO");
            if (!TextUtils.isEmpty(b2)) {
                d(x2.AB_INFO, b2);
            }
            d("NEW_USER", SharedPreferenceManager.b(context, String.valueOf(10000), "NEW_USER"));
        } catch (JSONException unused) {
            iyj.a("BIAnalyticsImpl", "setABTestInfo Exception");
        }
    }

    private static String e() {
        String b2 = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(10000), "SP_KEY_CUR_DISPLAY");
        if (TextUtils.isEmpty(b2)) {
            LogUtil.b("BIAnalyticsImpl", "json is null");
            return "STEPCARD";
        }
        return b2.toUpperCase(Locale.ROOT);
    }

    private static void d(String str, String str2) throws JSONException {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        d.put(str, str2);
    }

    protected static void e(String str) {
        if (TextUtils.isEmpty(str)) {
            str = LoginInit.getInstance(null).getHuidOrDefault();
        }
        try {
            d.put(OpAnalyticsConstants.OPERATION_ID, str);
            b("DT");
            b("DID");
            b("DV");
            b("MAIN_VIP", "MAIN_VIP_KEY");
            b("SHARED_VIP", "SHARED_VIP_KEY");
            d.put("HEALTH_MODEL", e());
            iyj.e("BIAnalyticsImpl", "setUserInfo() userInfo = " + str);
        } catch (JSONException unused) {
            iyj.a("BIAnalyticsImpl", "setEvent Exception");
        }
    }

    protected static void d(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            if (!Utils.o()) {
                if (d.has("CC")) {
                    d.remove("CC");
                }
            } else {
                d.put("CC", str);
                iyj.e("BIAnalyticsImpl", "setCountryCode() countryCode = " + str);
            }
        } catch (JSONException unused) {
            iyj.a("BIAnalyticsImpl", "setCountryCode Exception");
        }
    }

    protected static void a(String str, String str2) {
        try {
            e("MAIN_VIP", str);
            e("SHARED_VIP", str2);
            iyj.e("BIAnalyticsImpl", "setVipInfo() MAIN_VIP = " + str + " SHARED_VIP = " + str2);
        } catch (Exception unused) {
            iyj.a("BIAnalyticsImpl", "setVipInfo Exception");
        }
    }

    protected static void e(String str, String str2) {
        try {
            d.put(str, str2);
            iyj.e("BIAnalyticsImpl", "setSingleVipInfo() vipKey = " + str + " vipValue = " + str2);
        } catch (Exception unused) {
            iyj.a("BIAnalyticsImpl", "setSingleVipInfo Exception");
        }
    }

    private static void b(String str, String str2) throws JSONException {
        Context e = com.huawei.haf.application.BaseApplication.e();
        if (d.has(str)) {
            return;
        }
        d.put(str, "");
        e(str, SharedPreferenceManager.b(e, Integer.toString(10000), str2));
    }

    private static void b(String str) throws JSONException {
        if (d.has(str)) {
            return;
        }
        d.put(str, "");
    }

    protected static void c(ixz ixzVar) {
        try {
            d.put("DT", ixzVar.d());
            d.put("DID", ixzVar.b());
            d.put("DV", ixzVar.a());
            if (CommonUtil.s(BaseApplication.getContext())) {
                String e = ixzVar.e();
                LogUtil.a("BIAnalyticsImpl", "setDeviceInfo deviceLogMark:", e);
                d.put("WEAR_DID", e);
            }
            iyj.e("BIAnalyticsImpl", "setDeviceInfo() DT = " + ixzVar.d() + " DID = " + ixzVar.b() + " DV = " + ixzVar.a());
        } catch (Exception unused) {
            iyj.a("BIAnalyticsImpl", "setEvent Exception");
        }
    }

    public static void a(ixz... ixzVarArr) {
        if (ixzVarArr.length <= 0) {
            return;
        }
        c(ixzVarArr[0]);
        synchronized (f13653a) {
            b.addAll(Arrays.asList(ixzVarArr));
        }
        g();
    }

    private static void g() {
        try {
            d.put("DEVICE_INFOS", b());
        } catch (JSONException unused) {
            iyj.a("BIAnalyticsImpl", "setDeviceInfoToMessage Exception");
        }
    }

    private static void c() {
        try {
            if (d.has("DEVICE_INFOS")) {
                iyj.b("BIAnalyticsImpl", "removeDeviceInfoFromMessage");
                d.remove("DEVICE_INFOS");
            }
            d.put("DEVICE_INFOS", b());
        } catch (JSONException unused) {
            iyj.a("BIAnalyticsImpl", "removeDeviceInfoFromMessage Exception");
        }
    }

    private static String b() {
        synchronized (f13653a) {
            if (b.isEmpty()) {
                return "";
            }
            JSONArray jSONArray = new JSONArray();
            Iterator<ixz> it = b.iterator();
            while (it.hasNext()) {
                ixz next = it.next();
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("DT", next.d());
                    jSONObject.put("DID", next.b());
                    jSONObject.put("DV", next.a());
                    if (CommonUtil.s(BaseApplication.getContext())) {
                        String e = next.e();
                        LogUtil.a("BIAnalyticsImpl", "convertSetToJsonString deviceLogMark:", e);
                        jSONObject.put("WEAR_DID", e);
                    }
                } catch (JSONException unused) {
                    iyj.a("BIAnalyticsImpl", "convertSetToJsonString Exception");
                }
                jSONArray.put(jSONObject);
            }
            return jSONArray.toString();
        }
    }

    public static void e(ixz... ixzVarArr) {
        synchronized (f13653a) {
            for (ixz ixzVar : ixzVarArr) {
                b.remove(ixzVar);
            }
        }
        c();
    }

    public static JSONObject d() {
        if (d == null) {
            return null;
        }
        try {
            return new JSONObject(d.toString());
        } catch (ConcurrentModificationException unused) {
            iyj.a("BIAnalyticsImpl", "getEnvironmentParam failed: ConcurrentModificationException");
            return null;
        } catch (JSONException unused2) {
            iyj.a("BIAnalyticsImpl", "getEnvironmentParam failed: jsonException");
            return null;
        }
    }
}
