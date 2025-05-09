package defpackage;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.hwidauth.api.ResultCallBack;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.constant.ParamConstants;
import com.huawei.secure.android.common.util.SafeString;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Locale;

/* loaded from: classes5.dex */
public class ksi {

    /* renamed from: a, reason: collision with root package name */
    private static ResultCallBack f14570a = null;
    private static ResultCallBack b = null;
    private static ResultCallBack c = null;
    private static ResultCallBack d = null;
    private static ResultCallBack e = null;
    private static ResultCallBack f = null;
    private static ResultCallBack g = null;
    private static ResultCallBack h = null;
    private static ResultCallBack i = null;
    private static String j = "";
    private static ArrayList<String> m = new ArrayList<>();
    private static String o;

    public static String d() {
        return Build.VERSION.SDK_INT >= 33 ? "android.permission.READ_MEDIA_IMAGES" : "android.permission.WRITE_EXTERNAL_STORAGE";
    }

    private static String i(Context context) {
        return context.getResources().getConfiguration().locale.getLanguage().toLowerCase(Locale.getDefault());
    }

    public static String a(Context context) {
        return context.getResources().getConfiguration().locale.getCountry().toLowerCase(Locale.getDefault());
    }

    public static void a(Activity activity) {
        if (kst.e()) {
            try {
                activity.getWindow().getAttributes().getClass().getDeclaredField(ParamConstants.Param.LAYOUT_IN_DISPLAY_CUTOUT_MODE).set(activity.getWindow().getAttributes(), 1);
            } catch (RuntimeException e2) {
                ksy.c("BaseUtil", "RuntimeException: " + e2.getClass().getSimpleName(), true);
            } catch (Exception e3) {
                ksy.c("BaseUtil", "Exception: " + e3.getClass().getSimpleName(), true);
            }
        }
    }

    public static boolean b() {
        return kst.c() || c();
    }

    public static boolean c() {
        return kst.a() || a();
    }

    public static boolean a() {
        return kst.b();
    }

    public static String e(Context context) {
        if (context == null) {
            return "";
        }
        if (TextUtils.isEmpty(b(context))) {
            return i(context) + Constants.LINK + a(context).toUpperCase(Locale.getDefault());
        }
        return i(context) + Constants.LINK + b(context) + Constants.LINK + a(context).toUpperCase(Locale.getDefault());
    }

    public static String b(Context context) {
        Configuration configuration = context.getResources().getConfiguration();
        if (configuration == null || configuration.locale == null) {
            return null;
        }
        return configuration.locale.getScript();
    }

    public static byte[] b(String str) {
        if (TextUtils.isEmpty(str)) {
            ksy.c("BaseUtil", "getUTF8Bytes, str is empty", true);
            return new byte[0];
        }
        try {
            return str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException unused) {
            ksy.c("BaseUtil", "getBytes error", true);
            return new byte[0];
        }
    }

    public static boolean d(Context context) {
        try {
            context.getPackageManager().getPackageInfo("com.tencent.mm", 0);
            ksy.b("BaseUtil", "WXApp Installed", true);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public static int c(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException unused) {
            ksy.c("BaseUtil", "NameNotFoundException getVersionTag error", true);
            return 0;
        }
    }

    public static int e(String str) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e2) {
            ksy.c("BaseUtil", "parseInt error " + e2.getClass().getSimpleName(), true);
            return -1;
        }
    }

    public static boolean c(String str) {
        try {
            Class.forName(str);
            ksy.b("BaseUtil", "isExsitOfClass", true);
            return true;
        } catch (ClassNotFoundException unused) {
            ksy.c("BaseUtil", "The class is not existing: ", true);
            return false;
        }
    }

    public static Bundle d(String str) {
        Bundle bundle;
        if (!TextUtils.isEmpty(str)) {
            int indexOf = str.indexOf(63);
            if (indexOf < 0) {
                indexOf = str.indexOf(35);
            }
            if (indexOf > 0) {
                bundle = h(SafeString.substring(str, indexOf + 1));
                ksy.b("BaseUtil", "parseUrl", true);
                return bundle;
            }
        }
        bundle = null;
        ksy.b("BaseUtil", "parseUrl", true);
        return bundle;
    }

    private static Bundle h(String str) {
        Bundle bundle = new Bundle();
        if (str != null) {
            for (String str2 : str.split("&")) {
                String[] split = str2.split("=");
                if (split.length == 2) {
                    String str3 = split[0];
                    String str4 = split[1];
                    if (!TextUtils.isEmpty(str3) && !TextUtils.isEmpty(str4)) {
                        bundle.putString(i(str3), i(str4));
                    }
                }
            }
        }
        return bundle;
    }

    private static String i(String str) {
        try {
            return URLDecoder.decode(str, "UTF-8");
        } catch (UnsupportedEncodingException e2) {
            e = e2;
            ksy.c("BaseUtil", "Exception:" + e.getClass().getSimpleName(), true);
            return str;
        } catch (IllegalArgumentException e3) {
            e = e3;
            ksy.c("BaseUtil", "Exception:" + e.getClass().getSimpleName(), true);
            return str;
        } catch (Exception e4) {
            ksy.c("BaseUtil", "Exception:" + e4.getClass().getSimpleName(), true);
            return str;
        }
    }

    public static ResultCallBack e() {
        return b;
    }

    public static ResultCallBack h() {
        return f14570a;
    }

    public static ResultCallBack g() {
        return c;
    }

    public static ResultCallBack j() {
        return d;
    }

    public static ResultCallBack i() {
        return e;
    }

    public static void b(ResultCallBack resultCallBack) {
        synchronized (ksi.class) {
            d = resultCallBack;
        }
    }

    public static void c(ResultCallBack resultCallBack) {
        synchronized (ksi.class) {
            b = resultCallBack;
        }
    }

    public static void d(ResultCallBack resultCallBack) {
        synchronized (ksi.class) {
            c = resultCallBack;
        }
    }

    public static ResultCallBack f() {
        return f;
    }

    public static ResultCallBack l() {
        return i;
    }

    public static void a(ResultCallBack resultCallBack) {
        synchronized (ksi.class) {
            h = resultCallBack;
        }
    }

    public static ResultCallBack m() {
        return h;
    }

    public static void e(ResultCallBack resultCallBack) {
        synchronized (ksi.class) {
            e = resultCallBack;
        }
    }

    public static ResultCallBack n() {
        return g;
    }

    public static void h(ResultCallBack resultCallBack) {
        g = resultCallBack;
    }

    public static String k() {
        return j;
    }

    public static void a(String str) {
        synchronized (ksi.class) {
            j = str;
        }
    }

    public static boolean g(Context context) {
        ksy.b("BaseUtil", "enter networkIsAvaiable", true);
        if (context == null) {
            return false;
        }
        Object systemService = context.getApplicationContext().getSystemService("connectivity");
        if (systemService == null) {
            ksy.b("BaseUtil", "connectivity is null,so networkIsAvaiable is unaviable", true);
            return false;
        }
        NetworkInfo[] allNetworkInfo = ((ConnectivityManager) systemService).getAllNetworkInfo();
        if (allNetworkInfo == null || allNetworkInfo.length == 0) {
            ksy.b("BaseUtil", "NetworkInfo is null,so networkIsAvaiable is unaviable", true);
            return false;
        }
        for (NetworkInfo networkInfo : allNetworkInfo) {
            if (networkInfo != null && networkInfo.getState() == NetworkInfo.State.CONNECTED) {
                return true;
            }
        }
        ksy.b("BaseUtil", "NetworkInfo state is unaviable", true);
        return false;
    }

    public static boolean a(Bundle bundle) {
        return (bundle == null || TextUtils.isEmpty(bundle.getString("code", null))) ? false : true;
    }

    public static String o() {
        try {
            Object d2 = ksx.d("android.os.SystemProperties", "get", new Class[]{String.class}, new Object[]{"ro.config.marketing_name"});
            if (d2 != null) {
                return (String) d2;
            }
        } catch (Exception e2) {
            ksy.d("BaseUtil", e2.getClass().getSimpleName(), true);
        }
        return "";
    }

    public static void g(String str) {
        o = str;
    }

    public static String p() {
        return o;
    }

    public static ArrayList<String> r() {
        return m;
    }

    public static void e(ArrayList<String> arrayList) {
        m = arrayList;
    }

    public static void bQE_(Activity activity) {
        if (activity == null || activity.isFinishing()) {
            return;
        }
        e(activity.getWindow(), "addPrivateFlags", new Object[]{524288}, new Class[]{Integer.TYPE});
    }

    public static String s() {
        return System.currentTimeMillis() + t();
    }

    public static String t() {
        SecureRandom a2 = ktc.a();
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < 19; i2++) {
            sb.append(a2.nextInt(10));
        }
        return sb.toString();
    }

    public static Object e(final Object obj, final String str, final Object[] objArr, final Class[] clsArr) {
        if (obj == null) {
            return null;
        }
        try {
            AccessController.doPrivileged(new PrivilegedAction<Object>() { // from class: ksi.2
                @Override // java.security.PrivilegedAction
                public Object run() {
                    Method b2 = ksi.b(obj, str, objArr, clsArr);
                    if (b2 != null) {
                        b2.setAccessible(true);
                        try {
                            return b2.invoke(obj, objArr);
                        } catch (IllegalAccessException e2) {
                            ksy.b("BaseUtil", e2.getClass().getSimpleName(), true);
                        } catch (IllegalArgumentException e3) {
                            ksy.b("BaseUtil", e3.getClass().getSimpleName(), true);
                        } catch (InvocationTargetException e4) {
                            ksy.b("BaseUtil", e4.getClass().getSimpleName(), true);
                        }
                    }
                    return null;
                }
            });
        } catch (Exception e2) {
            ksy.c("BaseUtil", "Exception: " + e2.getClass().getSimpleName(), true);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Method b(Object obj, String str, Object[] objArr, Class[] clsArr) {
        for (Class<?> cls = obj.getClass(); cls != Object.class; cls = cls.getSuperclass()) {
            try {
                return cls.getDeclaredMethod(str, clsArr);
            } catch (NoSuchMethodException e2) {
                ksy.b("BaseUtil", e2.getClass().getSimpleName(), true);
            } catch (SecurityException e3) {
                ksy.b("BaseUtil", e3.getClass().getSimpleName(), true);
            }
        }
        return null;
    }

    public static String a(String[] strArr) {
        if (strArr == null || strArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (String str : strArr) {
            sb.append(str);
            sb.append(" ");
        }
        return sb.toString();
    }

    public static String q() {
        try {
            Object d2 = ksx.d("android.os.SystemProperties", "get", new Class[]{String.class, String.class}, new Object[]{"ro.build.version.emui", ""});
            return d2 != null ? (String) d2 : "";
        } catch (Exception e2) {
            ksy.d("BaseUtil", e2.getClass().getSimpleName(), true);
            return "";
        }
    }

    public static String f(String str) {
        return str != null ? str.replaceAll("\\s", "") : "";
    }
}
