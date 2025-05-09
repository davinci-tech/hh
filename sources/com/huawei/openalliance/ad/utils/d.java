package com.huawei.openalliance.ad.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.AndroidRuntimeException;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Pair;
import android.view.Display;
import android.view.WindowManager;
import com.hihonor.android.os.Build;
import com.huawei.appgallery.markethomecountrysdk.api.HomeCountryApi;
import com.huawei.hmf.tasks.Tasks;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.constant.IntervalMethods;
import com.huawei.openalliance.ad.constant.RTCMethods;
import com.huawei.openalliance.ad.ey;
import com.huawei.openalliance.ad.fd;
import com.huawei.openalliance.ad.fh;
import com.huawei.openalliance.ad.fw;
import com.huawei.openalliance.ad.gc;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.inter.HiAd;
import com.huawei.openalliance.ad.inter.data.IAd;
import com.huawei.openalliance.ad.ipc.CallResult;
import com.huawei.openalliance.ad.ipc.RemoteCallResultCallback;
import com.huawei.openalliance.ad.ms;
import com.huawei.openalliance.ad.opendeviceidentifier.OAIDServiceManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes5.dex */
public abstract class d {

    /* renamed from: a, reason: collision with root package name */
    private static String f7671a;

    public static String z(final Context context) {
        String V = cg.a(context).V();
        if (TextUtils.isEmpty(V)) {
            m.i(new Runnable() { // from class: com.huawei.openalliance.ad.utils.d.4
                @Override // java.lang.Runnable
                public void run() {
                    cg.a(context).H(x.c(context));
                }
            });
        }
        return V;
    }

    public static String y(final Context context) {
        String U = cg.a(context).U();
        if (TextUtils.isEmpty(U)) {
            m.i(new Runnable() { // from class: com.huawei.openalliance.ad.utils.d.3
                @Override // java.lang.Runnable
                public void run() {
                    cg.a(context).G(x.b(context));
                }
            });
        }
        return U;
    }

    public static int x(Context context) {
        try {
            return Integer.parseInt(ey.a(context).b());
        } catch (NumberFormatException unused) {
            ho.c("AdInfoUtil", "EncodingMode fromString Exception");
            return 1;
        }
    }

    public static List<String> w(Context context) {
        String str;
        ArrayList arrayList = new ArrayList();
        String a2 = ey.a(context).a();
        if (TextUtils.equals(a2, "")) {
            return arrayList;
        }
        try {
            return new ArrayList(Arrays.asList(a2.split(",")));
        } catch (RuntimeException unused) {
            str = "fromString RuntimeException";
            ho.c("AdInfoUtil", str);
            return arrayList;
        } catch (Exception unused2) {
            str = "fromString Exception";
            ho.c("AdInfoUtil", str);
            return arrayList;
        }
    }

    public static String v(final Context context) {
        final cg a2 = cg.a(context);
        String D = a2.D();
        if (TextUtils.isEmpty(D)) {
            D = e(a2, context);
        } else if (dh.a("getBrandCust")) {
            m.f(new Runnable() { // from class: com.huawei.openalliance.ad.utils.d.2
                @Override // java.lang.Runnable
                public void run() {
                    d.e(cg.this, context);
                }
            });
        }
        if (TextUtils.equals(Constants.NOT_FOUND, D)) {
            return null;
        }
        return D;
    }

    public static int u(Context context) {
        try {
            return Settings.Secure.getInt(context.getContentResolver(), "secure_gesture_navigation", 0);
        } catch (Throwable th) {
            ho.c("AdInfoUtil", "exception happen: " + th.getClass().getSimpleName());
            return 0;
        }
    }

    public static boolean t(Context context) {
        if (context != null) {
            String packageName = context.getPackageName();
            if (!TextUtils.isEmpty(packageName)) {
                try {
                    String string = Settings.Global.getString(context.getContentResolver(), "ex_splash_block_list");
                    if ((!TextUtils.isEmpty(string) && Arrays.asList(string.split(";")).contains(packageName)) || Settings.Global.getInt(context.getContentResolver(), "ex_splash_func_status", 0) == 0) {
                        return false;
                    }
                    String string2 = Settings.Global.getString(context.getContentResolver(), "ex_splash_list");
                    if (TextUtils.isEmpty(string2)) {
                        return false;
                    }
                    return Arrays.asList(string2.split(";")).contains(packageName);
                } catch (Throwable th) {
                    ho.c("AdInfoUtil", "exception happen: " + th.getClass().getSimpleName());
                }
            }
        }
        return false;
    }

    public static int s(Context context) {
        long currentTimeMillis = System.currentTimeMillis();
        int L = L(context);
        if (ho.a()) {
            ho.a("AdInfoUtil", "getScreenReaderStatus end duration: %d", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
        }
        return L;
    }

    public static int r(Context context) {
        String str;
        if (context == null) {
            return 1;
        }
        try {
            return Settings.Global.getInt(context.getContentResolver(), Constants.NAVIGATIONBAR_IS_MIN) == 1 ? 1 : 0;
        } catch (Settings.SettingNotFoundException unused) {
            str = "get navigation status error, setting not found.";
            ho.d("AdInfoUtil", str);
            return 1;
        } catch (Throwable unused2) {
            str = "get navigation status error.";
            ho.d("AdInfoUtil", str);
            return 1;
        }
    }

    public static int q(Context context) {
        if (context != null && p(context)) {
            try {
                int i = Settings.Secure.getInt(context.getContentResolver(), Constants.PATH_CHILDMODE_STATUS);
                int i2 = Settings.Secure.getInt(context.getContentResolver(), Constants.PATH_PARENTMODE_STATUS);
                if (i == 0) {
                    return i2 == 0 ? -1 : 0;
                }
                return 1;
            } catch (Settings.SettingNotFoundException unused) {
                ho.d("AdInfoUtil", "get childMode and parentMode error, setting not found.");
            } catch (Throwable unused2) {
                ho.d("AdInfoUtil", "get childMode and parentMode error.");
                return 1;
            }
        }
        return -1;
    }

    public static boolean p(Context context) {
        if (com.huawei.openalliance.ad.bz.a(context).d() && (ao.a(context) >= 10 || com.huawei.openalliance.ad.bz.e(context))) {
            if (context == null) {
                return true;
            }
            try {
                try {
                    Object obj = context.getPackageManager().getApplicationInfo(Constants.PARENT_CONTROL_PACKAGE_NAME, 128).metaData.get(Constants.CONTENT_SWITCH_SUPPORT_KEY);
                    if (obj == null) {
                        return false;
                    }
                    return "1".equals(obj.toString());
                } catch (Throwable unused) {
                    return true;
                }
            } catch (PackageManager.NameNotFoundException | Exception unused2) {
            }
        }
        return false;
    }

    public static String o(final Context context) {
        if (!com.huawei.openalliance.ad.bz.b(context)) {
            return null;
        }
        final cg a2 = cg.a(context);
        String m = a2.m();
        if (ho.a()) {
            ho.a("AdInfoUtil", "cached agCountryCode: %s", m);
        }
        if (TextUtils.isEmpty(m) || dh.a("getAgCountryCode")) {
            m.f(new Runnable() { // from class: com.huawei.openalliance.ad.utils.d.13
                @Override // java.lang.Runnable
                public void run() {
                    d.i(context, a2);
                }
            });
        }
        if (TextUtils.equals(Constants.NOT_FOUND, m)) {
            return null;
        }
        return m;
    }

    public static String n(final Context context) {
        final cg a2 = cg.a(context);
        String l = a2.l();
        if (TextUtils.isEmpty(l)) {
            l = k(context, a2);
        } else if (dh.a("getAgVersionCode")) {
            m.f(new Runnable() { // from class: com.huawei.openalliance.ad.utils.d.12
                @Override // java.lang.Runnable
                public void run() {
                    d.k(context, a2);
                }
            });
        }
        if (TextUtils.equals(Constants.NOT_FOUND, l)) {
            return null;
        }
        return l;
    }

    public static String m(final Context context) {
        final cg a2 = cg.a(context);
        String e = a2.e();
        if (TextUtils.isEmpty(e)) {
            e = j(context, a2);
        } else if (dh.a("getHmsVersionCode")) {
            m.f(new Runnable() { // from class: com.huawei.openalliance.ad.utils.d.11
                @Override // java.lang.Runnable
                public void run() {
                    d.j(context, a2);
                }
            });
        }
        if (TextUtils.equals(Constants.NOT_FOUND, e)) {
            return null;
        }
        return e;
    }

    public static String l(final Context context) {
        final cg a2 = cg.a(context);
        String d = a2.d();
        if (TextUtils.isEmpty(d)) {
            d = h(context, a2);
        } else if (dh.a("getHsfVersionCode")) {
            m.f(new Runnable() { // from class: com.huawei.openalliance.ad.utils.d.10
                @Override // java.lang.Runnable
                public void run() {
                    d.h(context, a2);
                }
            });
        }
        if (TextUtils.equals(Constants.NOT_FOUND, d)) {
            return null;
        }
        return d;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String k(Context context, cg cgVar) {
        String a2 = a(context, "com.huawei.appmarket");
        if (a2 == null) {
            a2 = Constants.NOT_FOUND;
        }
        cgVar.i(a2);
        return a2;
    }

    public static String k(Context context) {
        if (context == null) {
            return null;
        }
        cg a2 = cg.a(context);
        String b = a2.b();
        if (TextUtils.isEmpty(b)) {
            b = J(context);
            a2.a(b);
        } else if (!Constants.HW_INTELLIEGNT_PACKAGE.equalsIgnoreCase(context.getPackageName())) {
            g(context, a2);
        }
        if (TextUtils.equals(Constants.NOT_FOUND, b)) {
            return null;
        }
        return b;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String j(Context context, cg cgVar) {
        String a2 = a(context, i.e(context));
        if (a2 == null) {
            a2 = Constants.NOT_FOUND;
        }
        cgVar.c(a2);
        return a2;
    }

    public static float j(Context context) {
        try {
            DisplayMetrics I = I(context);
            if (I != null) {
                return I.density;
            }
            return 0.0f;
        } catch (RuntimeException | Exception unused) {
            ho.c("AdInfoUtil", "getDensity fail");
            return 0.0f;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String i(Context context, cg cgVar) {
        String K = K(context);
        if (K == null) {
            K = Constants.NOT_FOUND;
        }
        if (!TextUtils.equals(Constants.NOT_FOUND, K)) {
            cgVar.j(K);
        }
        return K;
    }

    public static int i(Context context) {
        try {
            DisplayMetrics I = I(context);
            if (I != null) {
                return I.densityDpi;
            }
            return 0;
        } catch (RuntimeException | Exception unused) {
            ho.c("AdInfoUtil", "getDensityDpi fail");
            return 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String h(Context context, cg cgVar) {
        String a2 = a(context, Constants.REMOTE_APP_INSTALL_HOST_HSF_PACKAGE);
        if (a2 == null) {
            a2 = a(context, Constants.REMOTE_APP_INSTALL_HOST_HSF_PACKAGE_NEW);
        }
        if (a2 == null) {
            a2 = Constants.NOT_FOUND;
        }
        cgVar.b(a2);
        return a2;
    }

    public static String h(Context context) {
        if (!HiAd.getInstance(context).isEnableUserInfo()) {
            ho.b("AdInfoUtil", "not enable userinfo, skip udid.");
            return null;
        }
        cg a2 = cg.a(context);
        String H = a2.H();
        if (TextUtils.isEmpty(H)) {
            return f(context, a2);
        }
        ho.b("AdInfoUtil", "use cached udid");
        if (Constants.NOT_FOUND.equalsIgnoreCase(H)) {
            return null;
        }
        return H;
    }

    private static void g(final Context context, final cg cgVar) {
        long bG = fh.b(context).bG();
        final long c = ao.c();
        if (c - bG >= Constants.TWO_WEEK) {
            m.f(new Runnable() { // from class: com.huawei.openalliance.ad.utils.d.9
                @Override // java.lang.Runnable
                public void run() {
                    cgVar.a(d.J(context));
                    fh.b(context).i(c);
                }
            });
        } else {
            ho.b("AdInfoUtil", "query ua once 2 week");
        }
    }

    public static String g(Context context) {
        if (context == null) {
            return null;
        }
        cg a2 = cg.a(context);
        String G = a2.G();
        if (TextUtils.isEmpty(G)) {
            return e(context, a2);
        }
        boolean equalsIgnoreCase = Constants.NOT_FOUND.equalsIgnoreCase(G);
        if (ho.a() && !equalsIgnoreCase) {
            ho.a("AdInfoUtil", "use cached androidID.");
        }
        if (equalsIgnoreCase) {
            return null;
        }
        return G;
    }

    private static String f(Context context, cg cgVar) {
        StringBuilder sb;
        Object invoke;
        if (!HiAd.getInstance(context).isEnableUserInfo()) {
            ho.b("AdInfoUtil", "not enable userinfo, skip udid.");
            return null;
        }
        if (fh.b(context).b("udid")) {
            ho.a("AdInfoUtil", "within udid call time interval");
            return null;
        }
        try {
            if (ho.a()) {
                ho.a("AdInfoUtil", "no cached udid, direct get.");
            }
            fh.b(context).t("udid");
            Class<?> cls = Class.forName(com.huawei.openalliance.ad.cb.a(context).f());
            invoke = cls.getDeclaredMethod("getUDID", new Class[0]).invoke(cls, new Object[0]);
        } catch (RuntimeException e) {
            e = e;
            sb = new StringBuilder("getUDID RuntimeException:");
            sb.append(e.getClass().getSimpleName());
            ho.d("AdInfoUtil", sb.toString());
            return null;
        } catch (Throwable th) {
            e = th;
            sb = new StringBuilder("getUDID Exception:");
            sb.append(e.getClass().getSimpleName());
            ho.d("AdInfoUtil", sb.toString());
            return null;
        }
        if (invoke instanceof String) {
            cgVar.C((String) invoke);
            return (String) invoke;
        }
        cgVar.C(Constants.NOT_FOUND);
        return null;
    }

    public static DisplayMetrics f(Context context) {
        WindowManager windowManager;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (context == null || (windowManager = (WindowManager) context.getSystemService(Constants.NATIVE_WINDOW_SUB_DIR)) == null) {
            return displayMetrics;
        }
        windowManager.getDefaultDisplay().getRealMetrics(displayMetrics);
        return displayMetrics;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String e(cg cgVar, Context context) {
        String a2 = dd.a("hw_sc.product.useBrandCust");
        if (a2 == null) {
            a2 = Constants.NOT_FOUND;
        }
        cgVar.A(a2);
        return a2;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0058  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x005b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String e(android.content.Context r4, com.huawei.openalliance.ad.utils.cg r5) {
        /*
            if (r4 != 0) goto L5
            java.lang.String r4 = ""
            return r4
        L5:
            boolean r0 = com.huawei.openalliance.ad.ho.a()
            java.lang.String r1 = "AdInfoUtil"
            if (r0 == 0) goto L12
            java.lang.String r0 = "no cached androidID, direct get."
            com.huawei.openalliance.ad.ho.a(r1, r0)
        L12:
            java.lang.String r0 = com.huawei.openalliance.ad.utils.d.f7671a
            boolean r2 = android.text.TextUtils.isEmpty(r0)
            if (r2 != 0) goto L1b
            return r0
        L1b:
            android.content.ContentResolver r0 = r4.getContentResolver()     // Catch: java.lang.Exception -> L26 java.lang.RuntimeException -> L2f
            java.lang.String r2 = "android_id"
            java.lang.String r0 = android.provider.Settings.Secure.getString(r0, r2)     // Catch: java.lang.Exception -> L26 java.lang.RuntimeException -> L2f
            goto L4a
        L26:
            r0 = move-exception
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "getAndroidID Exception:"
            r2.<init>(r3)
            goto L37
        L2f:
            r0 = move-exception
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "getAndroidID RuntimeException:"
            r2.<init>(r3)
        L37:
            java.lang.Class r0 = r0.getClass()
            java.lang.String r0 = r0.getSimpleName()
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            com.huawei.openalliance.ad.ho.c(r1, r0)
            r0 = 0
        L4a:
            if (r5 != 0) goto L50
            com.huawei.openalliance.ad.utils.cg r5 = com.huawei.openalliance.ad.utils.cg.a(r4)
        L50:
            com.huawei.openalliance.ad.utils.d.f7671a = r0
            boolean r4 = android.text.TextUtils.isEmpty(r0)
            if (r4 == 0) goto L5b
            java.lang.String r4 = "NOT_FOUND"
            goto L5d
        L5b:
            java.lang.String r4 = com.huawei.openalliance.ad.utils.d.f7671a
        L5d:
            r5.B(r4)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.openalliance.ad.utils.d.e(android.content.Context, com.huawei.openalliance.ad.utils.cg):java.lang.String");
    }

    private static String e() {
        String str;
        try {
            return System.getProperty("http.agent");
        } catch (IllegalArgumentException unused) {
            str = "getSystemUserAgent fail";
            ho.c("AdInfoUtil", str);
            return null;
        } catch (Exception unused2) {
            str = "getSystemUserAgent Exception";
            ho.c("AdInfoUtil", str);
            return null;
        }
    }

    public static int e(Context context) {
        if (context == null) {
            return 0;
        }
        int a2 = a(context);
        int b = b(context);
        return a2 > b ? a2 : b;
    }

    public static boolean d() {
        try {
            if (Build.MANUFACTURER.equalsIgnoreCase("HONOR") && Build.VERSION.SDK_INT >= 31) {
                if (Build.VERSION.MAGIC_SDK_INT >= 33) {
                    return true;
                }
            }
        } catch (Throwable th) {
            Log.e("AdInfoUtil", "isHonor6UpPhone Error:" + th.getClass().getSimpleName());
        }
        return false;
    }

    private static void d(final cg cgVar, final Context context) {
        m.f(new Runnable() { // from class: com.huawei.openalliance.ad.utils.d.8
            @Override // java.lang.Runnable
            public void run() {
                if (com.huawei.openalliance.ad.bz.h(context)) {
                    OAIDServiceManager.getInstance(context).requireOaid(new OAIDServiceManager.OaidResultCallback() { // from class: com.huawei.openalliance.ad.utils.d.8.1
                        @Override // com.huawei.openalliance.ad.opendeviceidentifier.OAIDServiceManager.OaidResultCallback
                        public void a(String str, boolean z) {
                            ho.b("AdInfoUtil", "onOaidAcquired");
                            if (cgVar != null) {
                                cgVar.a(str, Boolean.valueOf(z));
                            }
                        }

                        @Override // com.huawei.openalliance.ad.opendeviceidentifier.OAIDServiceManager.OaidResultCallback
                        public void a() {
                            ho.b("AdInfoUtil", "onOaidAcquireFailed");
                            if (cgVar != null) {
                                cgVar.a((String) null, (Boolean) null);
                            }
                        }
                    });
                    return;
                }
                ho.b("AdInfoUtil", "thirdDevice, get oaid.");
                Pair<String, Boolean> a2 = dg.a(context);
                if (a2 == null || cgVar == null) {
                    cgVar.a((String) null, (Boolean) false);
                    ho.b("AdInfoUtil", "get oaid fail.");
                } else {
                    ho.b("AdInfoUtil", "oaid acquired.");
                    cgVar.a((String) a2.first, (Boolean) a2.second);
                }
            }
        });
    }

    public static int d(Context context) {
        if (context == null) {
            return 0;
        }
        int a2 = a(context);
        int b = b(context);
        return a2 > b ? b : a2;
    }

    public static boolean c(Context context, String str) {
        int a2;
        return TextUtils.isEmpty(str) || 1 == (a2 = fd.a(context).a(str)) || a2 == 0;
    }

    public static boolean c(Context context, int i) {
        if (i != 1) {
            ho.a("AdInfoUtil", "ad type %s not support kit preload", Integer.valueOf(i));
            return false;
        }
        if (!x.n(context) && !x.o(context)) {
            ho.a("AdInfoUtil", "kit preload only support phone or pad");
            return false;
        }
        if (!com.huawei.openalliance.ad.bz.b(context)) {
            ho.a("AdInfoUtil", "kit preload only support inner device");
            return false;
        }
        if (x.p(context)) {
            ho.a("AdInfoUtil", "kit preload not support eink");
            return false;
        }
        if (i.c(context)) {
            return true;
        }
        ho.a("AdInfoUtil", "hms not installed");
        return false;
    }

    private static void c(final cg cgVar, final Context context) {
        m.f(new Runnable() { // from class: com.huawei.openalliance.ad.utils.d.7
            @Override // java.lang.Runnable
            public void run() {
                if (com.huawei.openalliance.ad.bz.b(context)) {
                    final AtomicInteger atomicInteger = new AtomicInteger(0);
                    ms.a(context).a(RTCMethods.QUERY_UUID, "", new RemoteCallResultCallback<String>() { // from class: com.huawei.openalliance.ad.utils.d.7.1
                        @Override // com.huawei.openalliance.ad.ipc.RemoteCallResultCallback
                        public void onRemoteCallResult(String str, CallResult<String> callResult) {
                            atomicInteger.incrementAndGet();
                            if (callResult.getCode() != 200) {
                                ho.b("AdInfoUtil", "requestUuid failed");
                                return;
                            }
                            ho.b("AdInfoUtil", "requestUuid success");
                            cgVar.h(callResult.getData());
                            d.b(atomicInteger, cgVar, context);
                        }
                    }, String.class);
                    OAIDServiceManager.getInstance(context).requireOaid(new OAIDServiceManager.OaidResultCallback() { // from class: com.huawei.openalliance.ad.utils.d.7.2
                        @Override // com.huawei.openalliance.ad.opendeviceidentifier.OAIDServiceManager.OaidResultCallback
                        public void a(String str, boolean z) {
                            ho.b("AdInfoUtil", "onOaidAcquired");
                            cgVar.a(str, Boolean.valueOf(z));
                            atomicInteger.incrementAndGet();
                            d.b(atomicInteger, cgVar, context);
                        }

                        @Override // com.huawei.openalliance.ad.opendeviceidentifier.OAIDServiceManager.OaidResultCallback
                        public void a() {
                            ho.b("AdInfoUtil", "onOaidAcquireFailed");
                            cgVar.a((String) null, (Boolean) null);
                        }
                    });
                }
            }
        });
    }

    public static String c() {
        return Locale.getDefault().getLanguage() + Constants.LINK + Locale.getDefault().getCountry();
    }

    public static int c(Context context) {
        if (context == null) {
            return 0;
        }
        return ao.b(context, d(context));
    }

    public static boolean b(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(str, 0);
            if (applicationInfo != null) {
                return (applicationInfo.flags & 1) > 0;
            }
            return false;
        } catch (PackageManager.NameNotFoundException | Exception unused) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(AtomicInteger atomicInteger, cg cgVar, Context context) {
        if (atomicInteger.get() >= 2) {
            cgVar.g(x.a(context));
        }
    }

    private static void b(final cg cgVar, final Context context) {
        if (HiAd.getInstance(context).isEnableUserInfo()) {
            m.f(new Runnable() { // from class: com.huawei.openalliance.ad.utils.d.1
                @Override // java.lang.Runnable
                public void run() {
                    if (com.huawei.openalliance.ad.bz.b(context)) {
                        if (d.F(context)) {
                            ho.a("AdInfoUtil", "within uuid request interval, skip query uuid.");
                        } else {
                            ho.b("AdInfoUtil", "start to request UUID");
                            ms.a(context).a(RTCMethods.QUERY_UUID, "", new RemoteCallResultCallback<String>() { // from class: com.huawei.openalliance.ad.utils.d.1.1
                                @Override // com.huawei.openalliance.ad.ipc.RemoteCallResultCallback
                                public void onRemoteCallResult(String str, CallResult<String> callResult) {
                                    if (callResult.getCode() != 200) {
                                        ho.b("AdInfoUtil", "requestUuid failed");
                                    } else {
                                        ho.b("AdInfoUtil", "requestUuid success");
                                        cgVar.h(callResult.getData());
                                    }
                                }
                            }, String.class);
                        }
                    }
                }
            });
        } else {
            ho.b("AdInfoUtil", "not enable userinfo, skip uuid.");
        }
    }

    public static String b() {
        return Locale.getDefault().getScript();
    }

    public static Pair<String, Boolean> b(Context context, boolean z) {
        if (context == null) {
            return null;
        }
        if (!HiAd.getInstance(context).isEnableUserInfo()) {
            ho.b("AdInfoUtil", "not enable user info, skip oaid");
            return null;
        }
        cg a2 = cg.a(context);
        ho.b("AdInfoUtil", "query oaid");
        if (com.huawei.openalliance.ad.bz.h(context)) {
            Pair<String, Boolean> a3 = by.a(context);
            if (a3 != null && !G(context)) {
                ho.b("AdInfoUtil", "read from setting");
                d(com.huawei.openalliance.ad.bz.a(context).d() ? a2 : null, context.getApplicationContext());
            }
            if (a3 != null) {
                return a3;
            }
        } else {
            Pair<String, Boolean> H = H(context);
            if (H != null) {
                return H;
            }
        }
        if (!com.huawei.openalliance.ad.bz.a(context).d()) {
            return null;
        }
        String a4 = x.a(context);
        if (!TextUtils.isEmpty(a4) && !a4.equalsIgnoreCase(a2.j())) {
            c(a2, context.getApplicationContext());
            return null;
        }
        if (z && com.huawei.openalliance.ad.bz.h(context) && !fh.b(context).a(IntervalMethods.DIRECT_REQ_OAID, 2)) {
            ho.b("AdInfoUtil", "start to request oaid");
            fh.b(context).t(IntervalMethods.DIRECT_REQ_OAID);
            d(a2, context.getApplicationContext());
        }
        ho.b("AdInfoUtil", "read from cache");
        Pair<String, Boolean> i = a2.i();
        return (android.os.Build.BRAND.equalsIgnoreCase("HONOR") && i == null) ? aq.a(context) : i;
    }

    public static int b(Context context, int i) {
        return i == 0 ? d(context) : e(context);
    }

    public static int b(Context context) {
        if (context == null) {
            return 0;
        }
        return f(context).widthPixels;
    }

    public static boolean a(Context context, IAd iAd) {
        if (iAd == null) {
            return false;
        }
        return ao.a(context, iAd);
    }

    public static void a(Context context, List<String> list) {
        String a2 = cz.a(list, ",");
        fw a3 = ey.a(context);
        if (bg.a(list)) {
            a3.a("");
        } else {
            a3.a(a2);
        }
    }

    public static void a(Context context, String str, boolean z) {
        m.f(new a(context, str, z));
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static String a(String str) {
        char c;
        if (cz.b(str)) {
            return "0";
        }
        switch (str.hashCode()) {
            case 50:
                if (str.equals("2")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 51:
                if (str.equals("3")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 52:
                if (str.equals("4")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 53:
                if (str.equals("5")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        return c != 0 ? (c == 1 || c == 2) ? "2" : "0" : "1";
    }

    public static String a(Context context, boolean z) {
        cg a2 = cg.a(context);
        String a3 = x.a(context);
        if (!TextUtils.isEmpty(a3) && !a3.equalsIgnoreCase(a2.j())) {
            c(a2, context.getApplicationContext());
            return null;
        }
        if (z && context != null) {
            b(a2, context.getApplicationContext());
        }
        return a2.k();
    }

    public static String a(Context context, String str) {
        try {
            PackageInfo b = i.b(context, str);
            if (b == null) {
                return null;
            }
            return String.valueOf(b.versionCode);
        } catch (AndroidRuntimeException | Exception unused) {
            ho.c("AdInfoUtil", "getVersionCode fail");
            return null;
        }
    }

    public static String a(int i) {
        return i != 1 ? i != 2 ? i != 3 ? "2" : "5" : "4" : "3";
    }

    public static String a() {
        return Locale.getDefault().getLanguage();
    }

    public static int a(Context context, int i) {
        return i == 0 ? e(context) : d(context);
    }

    public static int a(Context context) {
        if (context == null) {
            return 0;
        }
        return f(context).heightPixels;
    }

    private static int L(Context context) {
        String string;
        if (context == null) {
            return -1;
        }
        if (ao.a(context) >= 10 || com.huawei.openalliance.ad.bz.e(context)) {
            try {
                int i = Settings.Secure.getInt(context.getContentResolver(), "accessibility_screenreader_enabled");
                if (i == -1) {
                    i = com.huawei.openalliance.ad.cb.a(context).a(context);
                }
                if (i == 1) {
                    return 1;
                }
                return i == 0 ? 0 : -1;
            } catch (Settings.SettingNotFoundException unused) {
                ho.d("AdInfoUtil", "get ScreenReader status error, setting not found.");
                return -1;
            } catch (Throwable unused2) {
                ho.d("AdInfoUtil", "get ScreenReader status error.");
                return -1;
            }
        }
        try {
            if (Settings.Secure.getInt(context.getContentResolver(), "accessibility_enabled") != 1 || (string = Settings.Secure.getString(context.getContentResolver(), "enabled_accessibility_services")) == null) {
                return 0;
            }
            return (string.contains(new ComponentName("com.google.android.marvin.talkback", "com.google.android.marvin.talkback.TalkBackService").flattenToString()) || string.contains(new ComponentName("com.bjbyhd.screenreader_huawei", "com.bjbyhd.screenreader_huawei.ScreenReaderService").flattenToString()) || string.contains(new ComponentName("com.google.android.marvin.talkback", ".TalkBackService").flattenToString()) || string.contains(new ComponentName("com.samsung.accessibility", "com.samsung.android.app.talkback.TalkBackService").flattenToString())) ? 1 : 0;
        } catch (Settings.SettingNotFoundException unused3) {
            ho.d("AdInfoUtil", "get ScreenReader status error, setting not found.");
            return -1;
        } catch (Throwable unused4) {
            ho.d("AdInfoUtil", "get ScreenReader status error.");
            return -1;
        }
    }

    private static String K(final Context context) {
        ho.b("AdInfoUtil", "getAgCountryCodeFromAg");
        String str = (String) dc.a(new Callable<String>() { // from class: com.huawei.openalliance.ad.utils.d.14
            @Override // java.util.concurrent.Callable
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public String call() {
                try {
                    return (String) Tasks.await(HomeCountryApi.getHomeCountry(context, "PPSSDK", false));
                } catch (Throwable unused) {
                    ho.d("AdInfoUtil", "getAgCountryCodeFromAg Throwable ");
                    return Constants.NOT_FOUND;
                }
            }
        }, 100L, Constants.NOT_FOUND);
        ho.a("AdInfoUtil", "ag country code=" + str);
        return str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:12:0x004a A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:14:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String J(android.content.Context r3) {
        /*
            java.lang.String r0 = "AdInfoUtil"
            com.huawei.openalliance.ad.utils.dd.h(r3)     // Catch: java.lang.Throwable -> L23 android.util.AndroidRuntimeException -> L3b
            java.lang.String r3 = android.webkit.WebSettings.getDefaultUserAgent(r3)     // Catch: java.lang.Throwable -> L23 android.util.AndroidRuntimeException -> L3b
            boolean r1 = com.huawei.openalliance.ad.ho.a()     // Catch: java.lang.Throwable -> L23 android.util.AndroidRuntimeException -> L3b
            if (r1 == 0) goto L18
            java.lang.Object[] r1 = new java.lang.Object[]{r3}     // Catch: java.lang.Throwable -> L23 android.util.AndroidRuntimeException -> L3b
            java.lang.String r2 = "get ua:%s"
            com.huawei.openalliance.ad.ho.a(r0, r2, r1)     // Catch: java.lang.Throwable -> L23 android.util.AndroidRuntimeException -> L3b
        L18:
            boolean r1 = android.text.TextUtils.isEmpty(r3)     // Catch: java.lang.Throwable -> L23 android.util.AndroidRuntimeException -> L3b
            if (r1 == 0) goto L44
            java.lang.String r3 = e()     // Catch: java.lang.Throwable -> L23 android.util.AndroidRuntimeException -> L3b
            goto L44
        L23:
            r3 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "getUserAgent fail: "
            r1.<init>(r2)
            java.lang.Class r3 = r3.getClass()
            java.lang.String r3 = r3.getSimpleName()
            r1.append(r3)
            java.lang.String r3 = r1.toString()
            goto L3d
        L3b:
            java.lang.String r3 = "getUserAgent fail"
        L3d:
            com.huawei.openalliance.ad.ho.c(r0, r3)
            java.lang.String r3 = e()
        L44:
            boolean r0 = android.text.TextUtils.isEmpty(r3)
            if (r0 == 0) goto L4c
            java.lang.String r3 = "NOT_FOUND"
        L4c:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.openalliance.ad.utils.d.J(android.content.Context):java.lang.String");
    }

    private static DisplayMetrics I(Context context) {
        WindowManager windowManager;
        Display defaultDisplay;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (context == null || (windowManager = (WindowManager) context.getSystemService(Constants.NATIVE_WINDOW_SUB_DIR)) == null || (defaultDisplay = windowManager.getDefaultDisplay()) == null) {
            return null;
        }
        defaultDisplay.getMetrics(displayMetrics);
        return displayMetrics;
    }

    private static Pair<String, Boolean> H(Context context) {
        cg a2 = cg.a(context);
        ho.b("AdInfoUtil", "thirdDevice, get oaid.");
        Pair<String, Boolean> i = a2.i();
        if (G(context)) {
            return i;
        }
        Pair<String, Boolean> a3 = dg.a(context);
        if (a3 != null) {
            ho.b("AdInfoUtil", "oaid acquired.");
            a2.a((String) a3.first, (Boolean) a3.second);
            return a3;
        }
        ho.b("AdInfoUtil", "derectOaid is null.");
        a2.a((String) null, (Boolean) null);
        return null;
    }

    private static boolean G(Context context) {
        String packageName = context.getPackageName();
        gc b = fh.b(context);
        Long valueOf = Long.valueOf(b.bT());
        long R = b.R() * 60000;
        long longValue = valueOf != null ? valueOf.longValue() : 0L;
        if (System.currentTimeMillis() - longValue >= R) {
            b.j(System.currentTimeMillis());
            return false;
        }
        ho.a("AdInfoUtil", "request OAID time limit, timeInter=" + R + ", lastTime=" + longValue + " callerPkg: " + packageName);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean F(Context context) {
        gc b = fh.b(context);
        long bU = b.bU();
        long S = b.S() * 60000;
        long c = ao.c();
        if (c < bU + S) {
            ho.a("AdInfoUtil", "request UUID time limit, timeInterval= %s min, lastTime= %s", Long.valueOf(S / 60000), ao.a(Constants.TIME_FORMAT_WITHOUT_MILLS).format(Long.valueOf(bU)));
            return true;
        }
        b.k(c);
        return false;
    }

    static class a implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        private final Context f7690a;
        private final String b;
        private final boolean c;

        @Override // java.lang.Runnable
        public void run() {
            cg.a(this.f7690a).a(this.b, Boolean.valueOf(this.c));
        }

        a(Context context, String str, boolean z) {
            this.f7690a = context;
            this.b = str;
            this.c = z;
        }
    }

    public static void C(final Context context) {
        m.a(new Runnable() { // from class: com.huawei.openalliance.ad.utils.d.6
            @Override // java.lang.Runnable
            public void run() {
                ho.a("AdInfoUtil", "adid_double_frame start to getAdid from hms");
                if (!dd.g()) {
                    ho.a("AdInfoUtil", "adid_double_frame not inside device");
                    return;
                }
                boolean a2 = fh.b(context).a("adid", 180);
                ho.a("AdInfoUtil", "adid_double_frame getAdid in Interval : %s", Boolean.valueOf(a2));
                if (a2) {
                    return;
                }
                long c = ao.c();
                ho.a("AdInfoUtil", "adid_double_frame get adid start time : %s", Long.valueOf(c));
                Pair<Integer, String> a3 = bz.a(context, "/adid/sync", "");
                fh.b(context).t("adid");
                if (a3 == null || ((Integer) a3.first).intValue() != 200) {
                    ho.a("AdInfoUtil", "adid_double_frame get adid error");
                    return;
                }
                long c2 = ao.c();
                ho.a("AdInfoUtil", "adid_double_frame get adid end time : %s, diff : %s, adid is : %s", Long.valueOf(c2), Long.valueOf(c2 - c), cz.g((String) a3.second));
                fh.b(context).D(a3.second != null ? (String) a3.second : "");
            }
        });
    }

    public static Integer B(final Context context) {
        final Integer g = ao.g(context);
        m.i(new Runnable() { // from class: com.huawei.openalliance.ad.utils.d.5
            @Override // java.lang.Runnable
            public void run() {
                cg.a(context).a(g);
            }
        });
        return g;
    }

    public static Integer A(Context context) {
        Integer Z = cg.a(context).Z();
        return Z == null ? B(context) : Z;
    }
}
