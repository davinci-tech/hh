package com.huawei.openalliance.ad.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.provider.Settings;
import android.system.Os;
import android.system.StructStat;
import android.text.TextUtils;
import com.huawei.hms.android.SystemUtils;
import com.huawei.openalliance.ad.activity.SafeIntent;
import com.huawei.openalliance.ad.beans.inner.CountryCodeBean;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.constant.RTCMethods;
import com.huawei.openalliance.ad.constant.SystemProperties;
import com.huawei.openalliance.ad.fh;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.ipc.CallResult;
import com.huawei.openalliance.ad.mr;
import com.huawei.openalliance.ad.utils.as;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Pattern;

/* loaded from: classes5.dex */
public class x {
    public static boolean d() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String z(Context context, cg cgVar) {
        String l = cz.l(com.huawei.openalliance.ad.bz.a(context).q());
        if (TextUtils.isEmpty(l)) {
            l = Constants.NOT_FOUND;
        }
        cgVar.s(l);
        return l;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String y(Context context, cg cgVar) {
        String l = cz.l(com.huawei.openalliance.ad.bz.a(context).p());
        if (TextUtils.isEmpty(l)) {
            l = Constants.NOT_FOUND;
        }
        cgVar.r(l);
        return l;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String x(Context context, cg cgVar) {
        String g = cw.g(context);
        String a2 = !TextUtils.isEmpty(g) ? cz.a(ae.c(g)) : null;
        if (TextUtils.isEmpty(a2)) {
            a2 = Constants.NOT_FOUND;
        }
        cgVar.I(a2);
        return a2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String w(Context context, cg cgVar) {
        String f = cw.f(context);
        String a2 = !TextUtils.isEmpty(f) ? cz.a(ae.c(f)) : null;
        if (TextUtils.isEmpty(a2)) {
            a2 = Constants.NOT_FOUND;
        }
        cgVar.q(a2);
        return a2;
    }

    public static boolean v(final Context context, int i) {
        final cg a2 = cg.a(context);
        if (a2.ad() == null) {
            return L(context, a2).booleanValue();
        }
        boolean booleanValue = a2.ad().booleanValue();
        if (dh.a("isChildMode", i)) {
            m.i(new Runnable() { // from class: com.huawei.openalliance.ad.utils.x.20
                @Override // java.lang.Runnable
                public void run() {
                    x.L(context, a2);
                }
            });
        }
        return booleanValue;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String v(Context context, cg cgVar) {
        String a2 = cz.a(Long.valueOf(v(context)));
        if (TextUtils.isEmpty(a2)) {
            a2 = Constants.NOT_FOUND;
        }
        cgVar.p(a2);
        return a2;
    }

    private static long v(Context context) {
        String f = cw.f(context);
        if (TextUtils.isEmpty(f)) {
            return 0L;
        }
        return ae.d(f).longValue();
    }

    public static boolean u(final Context context, int i) {
        final cg a2 = cg.a(context);
        if (a2.ac() == null) {
            return K(context, a2).booleanValue();
        }
        boolean booleanValue = a2.ac().booleanValue();
        if (dh.a("isWelinkUser", i)) {
            m.i(new Runnable() { // from class: com.huawei.openalliance.ad.utils.x.19
                @Override // java.lang.Runnable
                public void run() {
                    x.K(context, a2);
                }
            });
        }
        return booleanValue;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String u(Context context, cg cgVar) {
        long u = u(context);
        String l = u > 0 ? Long.toString(u) : Constants.NOT_FOUND;
        cgVar.o(l);
        return l;
    }

    private static long u(Context context) {
        long g = com.huawei.openalliance.ad.bz.d(context) ? g() : 0L;
        return g <= 0 ? h() : g;
    }

    public static boolean t(final Context context, int i) {
        final cg a2 = cg.a(context);
        if (a2.R() == null) {
            return J(context, a2);
        }
        boolean booleanValue = a2.R().booleanValue();
        if (dh.a("getEmulator", i)) {
            m.i(new Runnable() { // from class: com.huawei.openalliance.ad.utils.x.17
                @Override // java.lang.Runnable
                public void run() {
                    x.J(context, a2);
                }
            });
        }
        return booleanValue;
    }

    public static boolean t(Context context) {
        String a2 = w.a(context).a();
        ho.a("DeviceUtil", "deviceType: " + a2);
        return "4".equalsIgnoreCase(a2) || "5".equalsIgnoreCase(a2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String t(Context context, cg cgVar) {
        String string = Settings.Global.getString(context.getContentResolver(), "unified_device_name");
        if (TextUtils.isEmpty(string)) {
            string = dd.a("ro.config.marketing_name");
        }
        if (TextUtils.isEmpty(string)) {
            string = dd.a("ro.product.model");
        }
        if (TextUtils.isEmpty(string)) {
            string = Constants.NOT_FOUND;
        }
        cgVar.k(string);
        return string;
    }

    public static boolean s(final Context context, int i) {
        final cg a2 = cg.a(context);
        if (a2.Q() == null) {
            return I(context, a2);
        }
        boolean booleanValue = a2.Q().booleanValue();
        if (dh.a("getUSB", i)) {
            m.i(new Runnable() { // from class: com.huawei.openalliance.ad.utils.x.16
                @Override // java.lang.Runnable
                public void run() {
                    x.I(context, a2);
                }
            });
        }
        return booleanValue;
    }

    public static int s(Context context) {
        return ((float) d.e(context)) / ((float) d.d(context)) > 1.5f ? 2 : 1;
    }

    public static boolean r(final Context context, int i) {
        final cg a2 = cg.a(context);
        if (a2.P() == null) {
            return H(context, a2);
        }
        boolean booleanValue = a2.P().booleanValue();
        if (dh.a("getDebug", i)) {
            m.i(new Runnable() { // from class: com.huawei.openalliance.ad.utils.x.15
                @Override // java.lang.Runnable
                public void run() {
                    x.H(context, a2);
                }
            });
        }
        return booleanValue;
    }

    public static boolean r(Context context) {
        int s;
        try {
            s = com.huawei.openalliance.ad.cb.a(context).c();
        } catch (Throwable th) {
            s = s(context);
            ho.c("DeviceUtil", "getFoldableStatus %s", th.getClass().getSimpleName());
        }
        return s == 1;
    }

    public static boolean q(final Context context, int i) {
        final cg a2 = cg.a(context);
        if (a2.O() == null) {
            return G(context, a2);
        }
        boolean booleanValue = a2.O().booleanValue();
        if (dh.a("getProxy", i)) {
            m.i(new Runnable() { // from class: com.huawei.openalliance.ad.utils.x.14
                @Override // java.lang.Runnable
                public void run() {
                    x.G(context, a2);
                }
            });
        }
        return booleanValue;
    }

    public static boolean q(Context context) {
        if (context == null) {
            return false;
        }
        try {
            return com.huawei.openalliance.ad.cb.a(context).d();
        } catch (Throwable th) {
            ho.c("DeviceUtil", "isFoldablePhone exception: %s", th.getClass().getSimpleName());
            return false;
        }
    }

    public static boolean p(Context context) {
        if (context == null) {
            return false;
        }
        try {
            return com.huawei.openalliance.ad.cb.a(context).g();
        } catch (Throwable th) {
            ho.c("DeviceUtil", "isEinkDevice exception: %s", th.getClass().getSimpleName());
            return false;
        }
    }

    public static Integer p(final Context context, int i) {
        final cg a2 = cg.a(context);
        String B = a2.B();
        if (TextUtils.isEmpty(B)) {
            B = F(context, a2);
        } else if (dh.a("getCharging", i)) {
            m.i(new Runnable() { // from class: com.huawei.openalliance.ad.utils.x.13
                @Override // java.lang.Runnable
                public void run() {
                    x.F(context, a2);
                }
            });
        }
        if (TextUtils.equals(Constants.NOT_FOUND, B)) {
            B = null;
        }
        return cz.h(B);
    }

    public static boolean o(Context context) {
        return "0".equalsIgnoreCase(w.a(context).a());
    }

    public static Integer o(final Context context, int i) {
        final cg a2 = cg.a(context);
        String A = a2.A();
        if (TextUtils.isEmpty(A)) {
            A = E(context, a2);
        } else if (dh.a("getBattery", i)) {
            m.i(new Runnable() { // from class: com.huawei.openalliance.ad.utils.x.11
                @Override // java.lang.Runnable
                public void run() {
                    x.E(context, a2);
                }
            });
        }
        if (TextUtils.equals(Constants.NOT_FOUND, A)) {
            A = null;
        }
        return cz.h(A);
    }

    public static boolean n(Context context) {
        return "1".equalsIgnoreCase(w.a(context).a());
    }

    public static String n(final Context context, int i) {
        final cg a2 = cg.a(context);
        String z = a2.z();
        if (TextUtils.isEmpty(z)) {
            z = D(context, a2);
        } else if (dh.a("getBaro", i)) {
            m.i(new Runnable() { // from class: com.huawei.openalliance.ad.utils.x.9
                @Override // java.lang.Runnable
                public void run() {
                    x.D(context, a2);
                }
            });
        }
        if (TextUtils.equals(Constants.NOT_FOUND, z)) {
            return null;
        }
        return z;
    }

    public static String m(final Context context, int i) {
        final cg a2 = cg.a(context);
        String y = a2.y();
        if (TextUtils.isEmpty(y)) {
            y = C(context, a2);
        } else if (dh.a("getMagnet", i)) {
            m.i(new Runnable() { // from class: com.huawei.openalliance.ad.utils.x.7
                @Override // java.lang.Runnable
                public void run() {
                    x.C(context, a2);
                }
            });
        }
        if (TextUtils.equals(Constants.NOT_FOUND, y)) {
            return null;
        }
        return y;
    }

    public static String m(Context context) {
        String f = com.huawei.openalliance.ad.bz.a(context).f();
        ho.b("DeviceUtil", "getHMVerion, ver= %s", f);
        if (TextUtils.isEmpty(f)) {
            return null;
        }
        return f;
    }

    public static boolean l(Context context) {
        cg a2 = cg.a(context);
        boolean z = true;
        try {
        } catch (Throwable th) {
            ho.c("DeviceUtil", "getHasAccAndRotate err: %s", th.getClass().getSimpleName());
        }
        if (a2.L() != null) {
            return a2.L().booleanValue();
        }
        if (((SensorManager) context.getSystemService("sensor")).getDefaultSensor(1) == null) {
            z = false;
        }
        a2.b(Boolean.valueOf(z));
        return z;
    }

    public static String l(final Context context, int i) {
        final cg a2 = cg.a(context);
        String x = a2.x();
        if (TextUtils.isEmpty(x)) {
            x = B(context, a2);
        } else if (dh.a("getAcceler", i)) {
            m.i(new Runnable() { // from class: com.huawei.openalliance.ad.utils.x.5
                @Override // java.lang.Runnable
                public void run() {
                    x.B(context, a2);
                }
            });
        }
        if (TextUtils.equals(Constants.NOT_FOUND, x)) {
            return null;
        }
        return x;
    }

    public static String k(final Context context, int i) {
        final cg a2 = cg.a(context);
        String w = a2.w();
        if (TextUtils.isEmpty(w)) {
            w = A(context, a2);
        } else if (dh.a("getGyro", i)) {
            m.i(new Runnable() { // from class: com.huawei.openalliance.ad.utils.x.3
                @Override // java.lang.Runnable
                public void run() {
                    x.A(context, a2);
                }
            });
        }
        if (TextUtils.equals(Constants.NOT_FOUND, w)) {
            return null;
        }
        return w;
    }

    public static int k(Context context) {
        cg a2 = cg.a(context);
        if (a2.F() != null) {
            return a2.F().intValue();
        }
        int c = w.a(context).c();
        a2.a(c);
        return c;
    }

    public static boolean j(Context context) {
        cg a2 = cg.a(context);
        if (a2.E() != null) {
            return a2.E().booleanValue();
        }
        boolean b2 = w.a(context).b();
        a2.c(b2);
        return b2;
    }

    public static String j(final Context context, int i) {
        final cg a2 = cg.a(context);
        String v = a2.v();
        if (TextUtils.isEmpty(v)) {
            return z(context, a2);
        }
        if (!TextUtils.equals(Constants.NOT_FOUND, v)) {
            return v;
        }
        if (dh.a("getVendCountry", i)) {
            m.i(new Runnable() { // from class: com.huawei.openalliance.ad.utils.x.2
                @Override // java.lang.Runnable
                public void run() {
                    x.z(context, a2);
                }
            });
        }
        return null;
    }

    public static String i(final Context context, int i) {
        final cg a2 = cg.a(context);
        String u = a2.u();
        if (TextUtils.isEmpty(u)) {
            return y(context, a2);
        }
        if (!TextUtils.equals(Constants.NOT_FOUND, u)) {
            return u;
        }
        if (dh.a("getVendor", i)) {
            m.i(new Runnable() { // from class: com.huawei.openalliance.ad.utils.x.27
                @Override // java.lang.Runnable
                public void run() {
                    x.y(context, a2);
                }
            });
        }
        return null;
    }

    public static Context i(Context context) {
        return d() ? context.createDeviceProtectedStorageContext() : context;
    }

    public static boolean h(Context context) {
        NetworkInfo activeNetworkInfo;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getApplicationContext().getSystemService("connectivity");
        return connectivityManager != null && (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) != null && activeNetworkInfo.isConnected() && activeNetworkInfo.getState() == NetworkInfo.State.CONNECTED;
    }

    public static Long h(final Context context, int i) {
        final cg a2 = cg.a(context);
        String Y = a2.Y();
        if (TextUtils.isEmpty(Y)) {
            Y = x(context, a2);
        } else if (dh.a("getFreeSdcard", i)) {
            m.i(new Runnable() { // from class: com.huawei.openalliance.ad.utils.x.26
                @Override // java.lang.Runnable
                public void run() {
                    x.x(context, a2);
                }
            });
        }
        if (TextUtils.equals(Constants.NOT_FOUND, Y)) {
            Y = null;
        }
        return cz.i(Y);
    }

    private static long h() {
        FileInputStream fileInputStream;
        BufferedReader bufferedReader;
        Object th;
        InputStreamReader inputStreamReader;
        File file;
        String readLine;
        long j = 0;
        try {
            file = new File("/proc/meminfo");
        } catch (Throwable th2) {
            th = th2;
            fileInputStream = null;
        }
        if (!file.exists()) {
            cy.a((Closeable) null);
            cy.a((Closeable) null);
            cy.a((Closeable) null);
            return 0L;
        }
        fileInputStream = new FileInputStream(file);
        try {
            inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            try {
                bufferedReader = new BufferedReader(inputStreamReader);
            } catch (Throwable th3) {
                bufferedReader = null;
                th = th3;
            }
        } catch (Throwable th4) {
            th = th4;
            bufferedReader = null;
            th = th;
            inputStreamReader = null;
            try {
                ho.d("DeviceUtil", "getDeviceRamNative: %s", th.getClass().getSimpleName());
                return j;
            } finally {
                cy.a(bufferedReader);
                cy.a(inputStreamReader);
                cy.a((Closeable) fileInputStream);
            }
        }
        try {
            readLine = bufferedReader.readLine();
        } catch (Throwable th5) {
            th = th5;
            ho.d("DeviceUtil", "getDeviceRamNative: %s", th.getClass().getSimpleName());
            return j;
        }
        if (cz.b(readLine)) {
            return 0L;
        }
        String[] split = readLine.split("\\s+");
        if (split.length < 2) {
            return 0L;
        }
        j = 1024 * Long.parseLong(split[1]);
        return j;
    }

    public static boolean g(Context context) {
        String str;
        if (context == null) {
            str = "loc_tag isGpsSwitchOpen Context is null";
        } else {
            try {
                int i = Settings.Secure.getInt(context.getContentResolver(), "location_mode");
                ho.b("DeviceUtil", "loc_tag isGpsSwitchOpen locationMode is " + i);
                return i == 3;
            } catch (Settings.SettingNotFoundException unused) {
                str = "loc_tag isGpsSwitchOpen SettingNotFoundException";
            }
        }
        ho.d("DeviceUtil", str);
        return false;
    }

    public static Long g(final Context context, int i) {
        final cg a2 = cg.a(context);
        String t = a2.t();
        if (TextUtils.isEmpty(t)) {
            t = w(context, a2);
        } else if (dh.a("getFreeSto", i)) {
            m.i(new Runnable() { // from class: com.huawei.openalliance.ad.utils.x.25
                @Override // java.lang.Runnable
                public void run() {
                    x.w(context, a2);
                }
            });
        }
        if (TextUtils.equals(Constants.NOT_FOUND, t)) {
            t = null;
        }
        return cz.i(t);
    }

    private static long g() {
        try {
            if ("true".equals(dd.a(SystemProperties.VICKY_DEMO_6))) {
                return 6442450944L;
            }
            Class<?> cls = Class.forName("com.huawei.android.util.SystemInfo");
            return Long.parseLong((String) cls.getMethod("getDeviceRam", new Class[0]).invoke(cls, new Object[0])) * 1024;
        } catch (Throwable th) {
            ho.d("DeviceUtil", "getDeviceRamForHw: %s", th.getClass().getSimpleName());
            return 0L;
        }
    }

    public static boolean f(Context context) {
        try {
            return g(context);
        } catch (Throwable th) {
            ho.c("DeviceUtil", "get location service switch exception: " + th.getClass().getSimpleName());
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0042 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v10, types: [java.io.FileReader, java.io.Reader] */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r2v8, types: [boolean] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String f(com.huawei.openalliance.ad.utils.cg r6) {
        /*
            java.lang.String r0 = "DeviceUtil"
            r1 = 0
            java.io.FileReader r2 = new java.io.FileReader     // Catch: java.lang.Throwable -> L26 java.io.IOException -> L29 java.io.FileNotFoundException -> L2e
            java.lang.String r3 = "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq"
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L26 java.io.IOException -> L29 java.io.FileNotFoundException -> L2e
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L1e java.io.IOException -> L22 java.io.FileNotFoundException -> L24
            r3.<init>(r2)     // Catch: java.lang.Throwable -> L1e java.io.IOException -> L22 java.io.FileNotFoundException -> L24
            java.lang.String r4 = r3.readLine()     // Catch: java.io.IOException -> L2b java.io.FileNotFoundException -> L30 java.lang.Throwable -> L62
            boolean r5 = android.text.TextUtils.isEmpty(r4)     // Catch: java.io.IOException -> L2b java.io.FileNotFoundException -> L30 java.lang.Throwable -> L62
            if (r5 != 0) goto L35
            java.lang.String r1 = r4.trim()     // Catch: java.io.IOException -> L2b java.io.FileNotFoundException -> L30 java.lang.Throwable -> L62
            goto L35
        L1e:
            r6 = move-exception
            r3 = r1
        L20:
            r1 = r2
            goto L64
        L22:
            r3 = r1
            goto L2b
        L24:
            r3 = r1
            goto L30
        L26:
            r6 = move-exception
            r3 = r1
            goto L64
        L29:
            r2 = r1
            r3 = r2
        L2b:
            java.lang.String r4 = "get CpuModel exception : IOException"
            goto L32
        L2e:
            r2 = r1
            r3 = r2
        L30:
            java.lang.String r4 = "get CpuSpeed exception : FileNotFoundException"
        L32:
            com.huawei.openalliance.ad.ho.d(r0, r4)     // Catch: java.lang.Throwable -> L62
        L35:
            com.huawei.openalliance.ad.utils.cy.a(r2)
            com.huawei.openalliance.ad.utils.cy.a(r3)
            boolean r2 = android.text.TextUtils.isEmpty(r1)
            if (r2 == 0) goto L42
            goto L5c
        L42:
            java.lang.Float r1 = java.lang.Float.valueOf(r1)     // Catch: java.lang.NumberFormatException -> L57
            float r1 = r1.floatValue()     // Catch: java.lang.NumberFormatException -> L57
            r2 = 1232348160(0x49742400, float:1000000.0)
            float r1 = r1 / r2
            java.lang.Float r1 = java.lang.Float.valueOf(r1)     // Catch: java.lang.NumberFormatException -> L57
            java.lang.String r0 = com.huawei.openalliance.ad.utils.cz.a(r1)     // Catch: java.lang.NumberFormatException -> L57
            goto L5e
        L57:
            java.lang.String r1 = "getCpuSpeed toInteger NumberFormatException"
            com.huawei.openalliance.ad.ho.d(r0, r1)
        L5c:
            java.lang.String r0 = "NOT_FOUND"
        L5e:
            r6.n(r0)
            return r0
        L62:
            r6 = move-exception
            goto L20
        L64:
            com.huawei.openalliance.ad.utils.cy.a(r1)
            com.huawei.openalliance.ad.utils.cy.a(r3)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.openalliance.ad.utils.x.f(com.huawei.openalliance.ad.utils.cg):java.lang.String");
    }

    public static String f() {
        if (Build.VERSION.SDK_INT >= 27) {
            try {
                StructStat stat = Os.stat("/data/data");
                long j = stat.st_atim.tv_nsec;
                return stat.st_atim.tv_sec + "." + j;
            } catch (Throwable th) {
                ho.d("DeviceUtil", "get update mark exception: %s", th.getClass().getSimpleName());
            }
        }
        return "";
    }

    public static Long f(final Context context, int i) {
        final cg a2 = cg.a(context);
        String s = a2.s();
        if (TextUtils.isEmpty(s)) {
            s = v(context, a2);
        } else if (TextUtils.equals(Constants.NOT_FOUND, s)) {
            if (dh.a("getTotalSto", i)) {
                m.i(new Runnable() { // from class: com.huawei.openalliance.ad.utils.x.24
                    @Override // java.lang.Runnable
                    public void run() {
                        x.v(context, a2);
                    }
                });
            }
            s = null;
        }
        return cz.i(s);
    }

    public static boolean e(Context context) {
        cg a2 = cg.a(context);
        boolean C = a2.C();
        a(a2, context);
        return C;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String e(cg cgVar) {
        String str;
        try {
            str = cz.a(Integer.valueOf(new File("/sys/devices/system/cpu/").listFiles(new a()).length));
        } catch (Throwable unused) {
            ho.d("DeviceUtil", "get CpuCoreCnt exception");
            str = null;
        }
        if (str == null) {
            str = Constants.NOT_FOUND;
        }
        cgVar.m(str);
        return str;
    }

    public static String e() {
        InputStreamReader inputStreamReader;
        BufferedReader bufferedReader;
        Object th;
        FileInputStream fileInputStream;
        String str = "";
        try {
            fileInputStream = new FileInputStream("/proc/sys/kernel/random/boot_id");
            try {
                inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
                try {
                    bufferedReader = new BufferedReader(inputStreamReader);
                    try {
                        String readLine = bufferedReader.readLine();
                        if (!TextUtils.isEmpty(readLine)) {
                            str = readLine.trim();
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        try {
                            ho.d("DeviceUtil", "get boot mark exception: %s", th.getClass().getSimpleName());
                            return str;
                        } finally {
                            cy.a(bufferedReader);
                            cy.a(inputStreamReader);
                            cy.a((Closeable) fileInputStream);
                        }
                    }
                } catch (Throwable th3) {
                    bufferedReader = null;
                    th = th3;
                }
            } catch (Throwable th4) {
                bufferedReader = null;
                th = th4;
                inputStreamReader = null;
            }
        } catch (Throwable th5) {
            inputStreamReader = null;
            bufferedReader = null;
            th = th5;
            fileInputStream = null;
        }
        return str;
    }

    public static Long e(final Context context, int i) {
        final cg a2 = cg.a(context);
        String r = a2.r();
        if (TextUtils.isEmpty(r)) {
            r = u(context, a2);
        } else if (TextUtils.equals(Constants.NOT_FOUND, r)) {
            if (dh.a("getTotalMem", i)) {
                m.i(new Runnable() { // from class: com.huawei.openalliance.ad.utils.x.23
                    @Override // java.lang.Runnable
                    public void run() {
                        x.u(context, a2);
                    }
                });
            }
            r = null;
        }
        return cz.i(r);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String d(cg cgVar) {
        String a2 = dd.a("ro.product.cpu.abi");
        if (TextUtils.isEmpty(a2)) {
            a2 = dd.a("ro.product.cpu.abilist64");
        }
        if (TextUtils.isEmpty(a2)) {
            a2 = Constants.NOT_FOUND;
        }
        cgVar.l(a2);
        return a2;
    }

    public static String d(Context context, int i) {
        final cg a2 = cg.a(context);
        String q = a2.q();
        if (TextUtils.isEmpty(q)) {
            return f(a2);
        }
        if (!TextUtils.equals(Constants.NOT_FOUND, q)) {
            return q;
        }
        if (dh.a("getCpuSpeed", i)) {
            m.i(new Runnable() { // from class: com.huawei.openalliance.ad.utils.x.22
                @Override // java.lang.Runnable
                public void run() {
                    x.f(cg.this);
                }
            });
        }
        return null;
    }

    public static String d(Context context) {
        return cz.l(com.huawei.openalliance.ad.bz.a(context).m() ? fh.b(context).bI() : new CountryCodeBean(context).a());
    }

    public static boolean c() {
        return as.a.f7593a >= 21 || as.a.b >= 33;
    }

    public static String c(Context context) {
        return ae.c(v(context));
    }

    public static Integer c(Context context, int i) {
        final cg a2 = cg.a(context);
        String p = a2.p();
        if (TextUtils.isEmpty(p)) {
            p = e(a2);
        } else if (TextUtils.equals(Constants.NOT_FOUND, p)) {
            if (dh.a("getCpuCoreCnt", i)) {
                m.i(new Runnable() { // from class: com.huawei.openalliance.ad.utils.x.21
                    @Override // java.lang.Runnable
                    public void run() {
                        x.e(cg.this);
                    }
                });
            }
            p = null;
        }
        return cz.h(p);
    }

    public static String b(Context context, int i) {
        final cg a2 = cg.a(context);
        String o = a2.o();
        if (TextUtils.isEmpty(o)) {
            return d(a2);
        }
        if (!TextUtils.equals(Constants.NOT_FOUND, o)) {
            return o;
        }
        if (dh.a("getCpuModel", i)) {
            m.i(new Runnable() { // from class: com.huawei.openalliance.ad.utils.x.12
                @Override // java.lang.Runnable
                public void run() {
                    x.d(cg.this);
                }
            });
        }
        return null;
    }

    public static String b(Context context) {
        return ae.b(u(context));
    }

    public static String b() {
        String a2 = dd.a("ro.product.model");
        return TextUtils.isEmpty(a2) ? Build.MODEL : a2;
    }

    private static void a(final cg cgVar, final Context context) {
        m.f(new Runnable() { // from class: com.huawei.openalliance.ad.utils.x.18
            /* JADX WARN: Code restructure failed: missing block: B:20:0x006c, code lost:
            
                if (r0 != null) goto L22;
             */
            /* JADX WARN: Code restructure failed: missing block: B:21:0x008e, code lost:
            
                r2.b(false);
             */
            /* JADX WARN: Code restructure failed: missing block: B:22:0x0094, code lost:
            
                return;
             */
            /* JADX WARN: Code restructure failed: missing block: B:23:0x008b, code lost:
            
                r0.close();
             */
            /* JADX WARN: Code restructure failed: missing block: B:28:0x0089, code lost:
            
                if (r0 == null) goto L23;
             */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void run() {
                /*
                    r8 = this;
                    android.net.Uri$Builder r0 = new android.net.Uri$Builder
                    r0.<init>()
                    java.lang.String r1 = "content"
                    android.net.Uri$Builder r0 = r0.scheme(r1)
                    java.lang.String r1 = "com.huawei.hwid.pps.apiprovider"
                    android.net.Uri$Builder r0 = r0.authority(r1)
                    java.lang.String r1 = "/switch/query"
                    android.net.Uri$Builder r0 = r0.path(r1)
                    android.net.Uri r2 = r0.build()
                    android.content.Context r0 = r1
                    boolean r0 = com.huawei.openalliance.ad.utils.ao.b(r0, r2)
                    java.lang.String r7 = "DeviceUtil"
                    if (r0 != 0) goto L2b
                    java.lang.String r0 = "provider uri invalid."
                    com.huawei.openalliance.ad.ho.c(r7, r0)
                    return
                L2b:
                    r0 = 0
                    android.content.Context r1 = r1     // Catch: java.lang.Throwable -> L6f
                    android.content.ContentResolver r1 = r1.getContentResolver()     // Catch: java.lang.Throwable -> L6f
                    r3 = 0
                    r4 = 0
                    r5 = 0
                    r6 = 0
                    android.database.Cursor r0 = r1.query(r2, r3, r4, r5, r6)     // Catch: java.lang.Throwable -> L6f
                    if (r0 == 0) goto L67
                    boolean r1 = r0.moveToFirst()     // Catch: java.lang.Throwable -> L6f
                    if (r1 == 0) goto L67
                    java.lang.String r1 = "isSwitchChecked"
                    int r1 = r0.getColumnIndexOrThrow(r1)     // Catch: java.lang.Exception -> L61 java.lang.Throwable -> L6f
                    java.lang.String r1 = r0.getString(r1)     // Catch: java.lang.Exception -> L61 java.lang.Throwable -> L6f
                    java.lang.Boolean r2 = java.lang.Boolean.TRUE     // Catch: java.lang.Exception -> L61 java.lang.Throwable -> L6f
                    java.lang.String r2 = r2.toString()     // Catch: java.lang.Exception -> L61 java.lang.Throwable -> L6f
                    boolean r1 = r2.equalsIgnoreCase(r1)     // Catch: java.lang.Exception -> L61 java.lang.Throwable -> L6f
                    com.huawei.openalliance.ad.utils.cg r2 = r2     // Catch: java.lang.Exception -> L61 java.lang.Throwable -> L6f
                    r2.b(r1)     // Catch: java.lang.Exception -> L61 java.lang.Throwable -> L6f
                    if (r0 == 0) goto L60
                    r0.close()
                L60:
                    return
                L61:
                    java.lang.String r1 = "loc_tag isBaseLocationSwitch Exception"
                    com.huawei.openalliance.ad.ho.d(r7, r1)     // Catch: java.lang.Throwable -> L6f
                    goto L6c
                L67:
                    java.lang.String r1 = "loc_tag isBaseLocationSwitch, cursor is null"
                    com.huawei.openalliance.ad.ho.c(r7, r1)     // Catch: java.lang.Throwable -> L6f
                L6c:
                    if (r0 == 0) goto L8e
                    goto L8b
                L6f:
                    r1 = move-exception
                    java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L95
                    java.lang.String r3 = "loc_tag isBaseLocationSwitch query error: "
                    r2.<init>(r3)     // Catch: java.lang.Throwable -> L95
                    java.lang.Class r1 = r1.getClass()     // Catch: java.lang.Throwable -> L95
                    java.lang.String r1 = r1.getSimpleName()     // Catch: java.lang.Throwable -> L95
                    r2.append(r1)     // Catch: java.lang.Throwable -> L95
                    java.lang.String r1 = r2.toString()     // Catch: java.lang.Throwable -> L95
                    com.huawei.openalliance.ad.ho.d(r7, r1)     // Catch: java.lang.Throwable -> L95
                    if (r0 == 0) goto L8e
                L8b:
                    r0.close()
                L8e:
                    com.huawei.openalliance.ad.utils.cg r0 = r2
                    r1 = 0
                    r0.b(r1)
                    return
                L95:
                    r1 = move-exception
                    if (r0 == 0) goto L9b
                    r0.close()
                L9b:
                    throw r1
                */
                throw new UnsupportedOperationException("Method not decompiled: com.huawei.openalliance.ad.utils.x.AnonymousClass18.run():void");
            }
        });
    }

    public static String a(Context context, String str) {
        try {
            PackageManager packageManager = context.getApplicationContext().getPackageManager();
            if (packageManager == null) {
                return null;
            }
            PackageInfo packageInfo = packageManager.getPackageInfo(str, 128);
            return Build.VERSION.SDK_INT >= 28 ? String.valueOf(packageInfo.getLongVersionCode()) : String.valueOf(packageInfo.versionCode);
        } catch (Throwable unused) {
            ho.c("DeviceUtil", "fail to get appVerCode");
            return null;
        }
    }

    public static String a(final Context context, int i) {
        final cg a2 = cg.a(context);
        String n = a2.n();
        if (TextUtils.isEmpty(n)) {
            return t(context, a2);
        }
        if (!TextUtils.equals(Constants.NOT_FOUND, n)) {
            return n;
        }
        if (dh.a("getPdtName", i)) {
            m.i(new Runnable() { // from class: com.huawei.openalliance.ad.utils.x.1
                @Override // java.lang.Runnable
                public void run() {
                    x.t(context, a2);
                }
            });
        }
        return null;
    }

    public static String a(Context context) {
        return cu.a(com.huawei.openalliance.ad.bz.a(context).b() + d.h(context));
    }

    public static String a() {
        return dd.a(SystemUtils.PRODUCT_BRAND);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Boolean L(Context context, cg cgVar) {
        try {
            CallResult a2 = mr.a(context).a(RTCMethods.QUERY_CHILD_MODE, "", String.class);
            if (a2 != null && 200 == a2.getCode()) {
                ho.b("DeviceUtil", "query child mode success");
                String str = (String) a2.getData();
                if (cz.b(str)) {
                    return false;
                }
                boolean parseBoolean = Boolean.parseBoolean(str);
                cgVar.g(parseBoolean);
                return Boolean.valueOf(parseBoolean);
            }
        } catch (Throwable th) {
            ho.c("DeviceUtil", "query child mode err: %s", th.getClass().getSimpleName());
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Boolean K(Context context, cg cgVar) {
        boolean a2 = i.a(context, Constants.WELINK_PKG_NAME);
        cgVar.f(a2);
        return Boolean.valueOf(a2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean J(Context context, cg cgVar) {
        int i;
        boolean z;
        boolean z2;
        ArrayList arrayList = new ArrayList();
        arrayList.add("/system/lib/libc_malloc_debug_qemu.so");
        arrayList.add("/sys/qemu_trace");
        arrayList.add("/system/bin/qemu-props");
        arrayList.add("/dev/socket/genyd");
        arrayList.add("/dev/socket/baseband_genyd");
        arrayList.add("/dev/socket/qemud");
        arrayList.add("/dev/qemu_pipe");
        Iterator it = arrayList.iterator();
        while (true) {
            z = true;
            if (!it.hasNext()) {
                z2 = false;
                break;
            }
            if (new File((String) it.next()).exists()) {
                z2 = true;
                break;
            }
        }
        String aa = fh.b(context).aa();
        if (!TextUtils.isEmpty(aa)) {
            for (String str : aa.split(",")) {
                if (new File(str).exists()) {
                    break;
                }
            }
        }
        z = z2;
        cgVar.f(Boolean.valueOf(z));
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean I(Context context, cg cgVar) {
        SafeIntent safeIntent = new SafeIntent(ao.a(context, (BroadcastReceiver) null, new IntentFilter("android.intent.action.BATTERY_CHANGED")));
        int intExtra = safeIntent.getIntExtra("status", -1);
        boolean z = false;
        boolean z2 = intExtra == 2 || intExtra == 5;
        boolean z3 = safeIntent.getIntExtra("plugged", -1) == 2;
        if (z2 && z3) {
            z = true;
        }
        cgVar.e(Boolean.valueOf(z));
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean H(Context context, cg cgVar) {
        boolean z = Settings.Secure.getInt(context.getContentResolver(), "adb_enabled", 0) > 0;
        cgVar.d(Boolean.valueOf(z));
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean G(Context context, cg cgVar) {
        String property = System.getProperty("http.proxyPort");
        String property2 = System.getProperty("http.proxyHost");
        if (property == null) {
            property = "-1";
        }
        boolean z = (TextUtils.isEmpty(property2) || Integer.parseInt(property) == -1) ? false : true;
        cgVar.c(Boolean.valueOf(z));
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String F(Context context, cg cgVar) {
        int intExtra = new SafeIntent(ao.a(context, (BroadcastReceiver) null, new IntentFilter("android.intent.action.BATTERY_CHANGED"))).getIntExtra("status", 1);
        String a2 = intExtra == 1 ? Constants.NOT_FOUND : cz.a(Integer.valueOf(intExtra));
        cgVar.y(a2);
        return a2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String E(Context context, cg cgVar) {
        SafeIntent safeIntent = new SafeIntent(ao.a(context, (BroadcastReceiver) null, new IntentFilter("android.intent.action.BATTERY_CHANGED")));
        int intExtra = safeIntent.getIntExtra("level", -1);
        int intExtra2 = safeIntent.getIntExtra("scale", -1);
        String a2 = (intExtra == -1 || intExtra2 == -1) ? Constants.NOT_FOUND : cz.a(Integer.valueOf((int) ((intExtra / intExtra2) * 100.0f)));
        cgVar.x(a2);
        return a2;
    }

    static class b implements SensorEventListener {

        /* renamed from: a, reason: collision with root package name */
        private SensorManager f7749a;
        private cg b;

        @Override // android.hardware.SensorEventListener
        public void onAccuracyChanged(Sensor sensor, int i) {
        }

        @Override // android.hardware.SensorEventListener
        public void onSensorChanged(SensorEvent sensorEvent) {
            if (sensorEvent.sensor.getType() == 4) {
                this.b.t(cz.a(Float.valueOf(sensorEvent.values[0])) + "," + cz.a(Float.valueOf(sensorEvent.values[1])) + "," + cz.a(Float.valueOf(sensorEvent.values[2])));
                this.f7749a.unregisterListener(this);
                bo.a("HIADSDK_GROY_DeviceUtil");
            }
            if (sensorEvent.sensor.getType() == 1) {
                this.b.u(cz.a(Float.valueOf(sensorEvent.values[0])) + "," + cz.a(Float.valueOf(sensorEvent.values[1])) + "," + cz.a(Float.valueOf(sensorEvent.values[2])));
                this.f7749a.unregisterListener(this);
                bo.a("HIADSDK_ACCELER_DeviceUtil");
            }
            if (sensorEvent.sensor.getType() == 2) {
                this.b.v(cz.a(Float.valueOf(sensorEvent.values[0])) + "," + cz.a(Float.valueOf(sensorEvent.values[1])) + "," + cz.a(Float.valueOf(sensorEvent.values[2])));
                this.f7749a.unregisterListener(this);
                bo.a("HIADSDK_MAGNET_DeviceUtil");
            }
            if (sensorEvent.sensor.getType() == 6) {
                this.b.w(cz.a(Float.valueOf(sensorEvent.values[0])));
                this.f7749a.unregisterListener(this);
                bo.a("HIADSDK_BARO_DeviceUtil");
            }
        }

        public b(SensorManager sensorManager, cg cgVar) {
            this.f7749a = sensorManager;
            this.b = cgVar;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String D(Context context, cg cgVar) {
        try {
            final SensorManager sensorManager = (SensorManager) context.getSystemService("sensor");
            Sensor defaultSensor = sensorManager.getDefaultSensor(6);
            if (defaultSensor == null) {
                return null;
            }
            final b bVar = new b(sensorManager, cgVar);
            sensorManager.registerListener(bVar, defaultSensor, 3);
            bo.a(new Runnable() { // from class: com.huawei.openalliance.ad.utils.x.10
                @Override // java.lang.Runnable
                public void run() {
                    sensorManager.unregisterListener(bVar);
                }
            }, 3000L);
            return cgVar.z();
        } catch (Throwable th) {
            ho.c("DeviceUtil", "getBaroInner exception: %s", th.getClass().getSimpleName());
            return null;
        }
    }

    class a implements FileFilter {
        @Override // java.io.FileFilter
        public boolean accept(File file) {
            return Pattern.matches("cpu[0-9]", file.getName());
        }

        a() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String C(Context context, cg cgVar) {
        try {
            final SensorManager sensorManager = (SensorManager) context.getSystemService("sensor");
            Sensor defaultSensor = sensorManager.getDefaultSensor(2);
            if (defaultSensor == null) {
                return null;
            }
            final b bVar = new b(sensorManager, cgVar);
            sensorManager.registerListener(bVar, defaultSensor, 3);
            bo.a(new Runnable() { // from class: com.huawei.openalliance.ad.utils.x.8
                @Override // java.lang.Runnable
                public void run() {
                    sensorManager.unregisterListener(bVar);
                }
            }, 3000L);
            return cgVar.y();
        } catch (Throwable th) {
            ho.c("DeviceUtil", "getMagnetInner exception: %s", th.getClass().getSimpleName());
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String B(Context context, cg cgVar) {
        try {
            final SensorManager sensorManager = (SensorManager) context.getSystemService("sensor");
            Sensor defaultSensor = sensorManager.getDefaultSensor(1);
            if (defaultSensor == null) {
                return null;
            }
            final b bVar = new b(sensorManager, cgVar);
            sensorManager.registerListener(bVar, defaultSensor, 3);
            bo.a(new Runnable() { // from class: com.huawei.openalliance.ad.utils.x.6
                @Override // java.lang.Runnable
                public void run() {
                    sensorManager.unregisterListener(bVar);
                }
            }, 3000L);
            return cgVar.x();
        } catch (Throwable th) {
            ho.c("DeviceUtil", "getAccelerInner exception: %s", th.getClass().getSimpleName());
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String A(Context context, cg cgVar) {
        try {
            final SensorManager sensorManager = (SensorManager) context.getSystemService("sensor");
            Sensor defaultSensor = sensorManager.getDefaultSensor(4);
            if (defaultSensor == null) {
                return null;
            }
            final b bVar = new b(sensorManager, cgVar);
            sensorManager.registerListener(bVar, defaultSensor, 3);
            bo.a(new Runnable() { // from class: com.huawei.openalliance.ad.utils.x.4
                @Override // java.lang.Runnable
                public void run() {
                    sensorManager.unregisterListener(bVar);
                }
            }, 3000L);
            return cgVar.w();
        } catch (Throwable th) {
            ho.c("DeviceUtil", "getGyroInner exception: %s", th.getClass().getSimpleName());
            return null;
        }
    }
}
