package defpackage;

import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.utils.CollectionUtils;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.KeyValDbManager;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* loaded from: classes5.dex */
public class jfa {
    private static String b;

    public static void b() {
        b = "";
        ReleaseLogUtil.b("R_SharedPreferencesUtil", "reset");
    }

    public static String d() {
        if (TextUtils.isEmpty(b)) {
            String e = KeyValDbManager.b(BaseApplication.e()).e("user_id");
            LogUtil.a("SharedPreferencesUtil", "getUserId userId ", e, " mUserId ", b);
            b = e;
        }
        return b;
    }

    public static String e(String str) {
        return knl.e(str);
    }

    public static Set<String> c(String str) {
        return SharedPreferenceManager.d(BaseApplication.e(), str);
    }

    public static void d(String str, List<String> list) {
        if (CollectionUtils.d(list)) {
            ReleaseLogUtil.a("R_SharedPreferencesUtil", "deleteKeyList keyList ", list);
        } else {
            SharedPreferenceManager.e(str, (String[]) list.toArray(new String[0]));
        }
    }

    public static void c(String str, List<String> list) {
        if (CollectionUtils.d(list)) {
            ReleaseLogUtil.a("R_SharedPreferencesUtil", "deleteKeyListByUserId keyList ", list);
            return;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(e(it.next()));
        }
        d(str, arrayList);
    }

    public static boolean b(String str, String str2) {
        return SharedPreferenceManager.d(str, str2);
    }

    public static boolean e(String str, String str2) {
        return b(str, e(str2));
    }

    public static boolean d(String str, String str2) {
        return SharedPreferenceManager.e(str, str2);
    }

    public static boolean c(String str, String str2) {
        return d(str, e(str2));
    }

    public static String a(String str, String str2) {
        return SharedPreferenceManager.b(BaseApplication.e(), str, str2);
    }

    public static String h(String str, String str2) {
        return a(str, e(str2));
    }

    public static void d(String str, String str2, String str3, StorageParams storageParams) {
        SharedPreferenceManager.e(BaseApplication.e(), str, str2, str3, storageParams);
    }

    public static void e(String str, String str2, String str3) {
        d(str, str2, str3, null);
    }

    public static void a(String str, String str2, String str3) {
        e(str, e(str2), str3);
    }

    public static String c(String str, String str2, String str3) {
        return SharedPreferenceManager.e(str, str2, str3);
    }

    public static String d(String str, String str2, String str3) {
        return c(str, e(str2), str3);
    }

    public static void b(String str, String str2, String str3) {
        SharedPreferenceManager.c(str, str2, str3);
    }

    public static void f(String str, String str2, String str3) {
        b(str, e(str2), str3);
    }

    public static boolean b(String str, String str2, boolean z) {
        return SharedPreferenceManager.a(str, str2, z);
    }

    public static boolean d(String str, String str2, boolean z) {
        return b(str, e(str2), z);
    }

    public static void e(String str, String str2, boolean z) {
        SharedPreferenceManager.e(str, str2, z);
    }

    public static void a(String str, String str2, boolean z) {
        e(str, e(str2), z);
    }

    public static int b(String str, String str2, int i) {
        return SharedPreferenceManager.a(str, str2, i);
    }

    public static int e(String str, String str2, int i) {
        return b(str, e(str2), i);
    }

    public static void a(String str, String str2, int i) {
        SharedPreferenceManager.b(str, str2, i);
    }

    public static void c(String str, String str2, int i) {
        a(str, e(str2), i);
    }

    public static long c(String str, String str2, long j) {
        return SharedPreferenceManager.b(str, str2, j);
    }

    public static long d(String str, String str2, long j) {
        return c(str, e(str2), j);
    }

    public static void e(String str, String str2, long j) {
        SharedPreferenceManager.e(str, str2, j);
    }

    public static void b(String str, String str2, long j) {
        e(str, e(str2), j);
    }

    public static float b(String str, String str2, float f) {
        return SharedPreferenceManager.b(str, str2, f);
    }

    public static float c(String str, String str2, float f) {
        return b(str, e(str2), f);
    }

    public static void a(String str, String str2, float f) {
        SharedPreferenceManager.e(str, str2, f);
    }

    public static void d(String str, String str2, float f) {
        a(str, e(str2), f);
    }

    public static boolean a() {
        return b("HiHealthService", "pullAllStatus", false);
    }
}
