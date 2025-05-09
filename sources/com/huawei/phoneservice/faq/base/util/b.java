package com.huawei.phoneservice.faq.base.util;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import com.huawei.openalliance.ad.constant.Constants;
import java.util.Collection;

/* loaded from: classes5.dex */
public class b {

    /* renamed from: a, reason: collision with root package name */
    private static String f8240a;

    public static boolean e(Context context) {
        return context.getResources().getConfiguration().orientation == 2;
    }

    public static boolean c() {
        synchronized (b.class) {
            if (f8240a == null) {
                f8240a = b("ro.build.characteristics");
            }
        }
        return "tablet".equals(f8240a) || "car".equals(f8240a);
    }

    public static boolean b(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean b(Context context) {
        ConnectivityManager connectivityManager;
        if (context == null || (connectivityManager = (ConnectivityManager) context.getApplicationContext().getSystemService("connectivity")) == null) {
            return false;
        }
        for (Network network : connectivityManager.getAllNetworks()) {
            NetworkInfo networkInfo = connectivityManager.getNetworkInfo(network);
            if (networkInfo != null && networkInfo.isConnected()) {
                return true;
            }
        }
        return false;
    }

    public static boolean a() {
        return d() && c("6.0.0") >= 0;
    }

    public static void ccZ_(Activity activity) {
        View peekDecorView;
        if (activity == null || (peekDecorView = activity.getWindow().peekDecorView()) == null || peekDecorView.getWindowToken() == null) {
            return;
        }
        ((InputMethodManager) activity.getSystemService("input_method")).hideSoftInputFromWindow(peekDecorView.getWindowToken(), 0);
    }

    public static int d(Context context) {
        return (e(context) ? c(context) : a(context)) / 2;
    }

    public static int a(Context context) {
        return ccY_(context).widthPixels;
    }

    public static int c(Context context) {
        return ccY_(context).heightPixels;
    }

    private static boolean d() {
        return a("com.huawei.android.os.BuildEx");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x002b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String b(java.lang.String r7) {
        /*
            java.lang.String r0 = "Exception"
            java.lang.String r1 = "FaqCommonUtils"
            r2 = 0
            java.lang.String r3 = "android.os.SystemProperties"
            java.lang.Class r3 = java.lang.Class.forName(r3)     // Catch: java.lang.Exception -> L1a java.lang.NoSuchMethodException -> L1f java.lang.ClassNotFoundException -> L23
            r4 = 1
            java.lang.Class[] r4 = new java.lang.Class[r4]     // Catch: java.lang.Exception -> L1b java.lang.NoSuchMethodException -> L20 java.lang.ClassNotFoundException -> L24
            java.lang.Class<java.lang.String> r5 = java.lang.String.class
            r6 = 0
            r4[r6] = r5     // Catch: java.lang.Exception -> L1b java.lang.NoSuchMethodException -> L20 java.lang.ClassNotFoundException -> L24
            java.lang.String r5 = "get"
            java.lang.reflect.Method r2 = r3.getDeclaredMethod(r5, r4)     // Catch: java.lang.Exception -> L1b java.lang.NoSuchMethodException -> L20 java.lang.ClassNotFoundException -> L24
            goto L29
        L1a:
            r3 = r2
        L1b:
            com.huawei.phoneservice.faq.base.util.i.c(r1, r0)
            goto L29
        L1f:
            r3 = r2
        L20:
            java.lang.String r4 = "NoSuchMethodException"
            goto L26
        L23:
            r3 = r2
        L24:
            java.lang.String r4 = "ClassNotFoundException"
        L26:
            com.huawei.phoneservice.faq.base.util.i.c(r1, r4)
        L29:
            if (r2 == 0) goto L45
            java.lang.Object[] r7 = new java.lang.Object[]{r7}     // Catch: java.lang.Exception -> L36 java.lang.reflect.InvocationTargetException -> L3a java.lang.IllegalArgumentException -> L3d java.lang.IllegalAccessException -> L40
            java.lang.Object r7 = r2.invoke(r3, r7)     // Catch: java.lang.Exception -> L36 java.lang.reflect.InvocationTargetException -> L3a java.lang.IllegalArgumentException -> L3d java.lang.IllegalAccessException -> L40
            java.lang.String r7 = (java.lang.String) r7     // Catch: java.lang.Exception -> L36 java.lang.reflect.InvocationTargetException -> L3a java.lang.IllegalArgumentException -> L3d java.lang.IllegalAccessException -> L40
            goto L47
        L36:
            com.huawei.phoneservice.faq.base.util.i.c(r1, r0)
            goto L45
        L3a:
            java.lang.String r7 = "InvocationTargetException"
            goto L42
        L3d:
            java.lang.String r7 = "IllegalArgumentException"
            goto L42
        L40:
            java.lang.String r7 = "IllegalAccessException"
        L42:
            com.huawei.phoneservice.faq.base.util.i.c(r1, r7)
        L45:
            java.lang.String r7 = ""
        L47:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.phoneservice.faq.base.util.b.b(java.lang.String):java.lang.String");
    }

    private static boolean a(String str) {
        try {
            Class.forName(str);
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    private static String b() {
        return b("ro.build.version.emui");
    }

    private static String e() {
        try {
            String b = b();
            if (TextUtils.isEmpty(b)) {
                return null;
            }
            return b.split("_")[1];
        } catch (Exception unused) {
            i.c("FaqCommonUtils", "getEmui()  Exception...");
            return null;
        }
    }

    private static DisplayMetrics ccY_(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (context != null) {
            ((WindowManager) context.getSystemService(Constants.NATIVE_WINDOW_SUB_DIR)).getDefaultDisplay().getMetrics(displayMetrics);
        }
        return displayMetrics;
    }

    private static int c(String str) {
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        String e = e();
        if (TextUtils.isEmpty(e)) {
            return -1;
        }
        if (str.equals(e)) {
            return 0;
        }
        String[] split = str.split("\\.");
        String[] split2 = e.split("\\.");
        int length = split.length;
        int length2 = split2.length;
        int min = Math.min(length, length2);
        for (int i = 0; i < min; i++) {
            int parseInt = Integer.parseInt(split[i]);
            int parseInt2 = Integer.parseInt(split2[i]);
            if (parseInt > parseInt2) {
                return -1;
            }
            if (parseInt < parseInt2) {
                return 1;
            }
        }
        return Integer.compare(length2, length);
    }
}
