package com.huawei.hms.network.file.core;

import android.text.TextUtils;
import android.util.Pair;
import com.huawei.hms.network.NetworkKit;
import com.huawei.hms.network.file.core.util.FLogger;

/* loaded from: classes4.dex */
public class b {

    /* renamed from: a, reason: collision with root package name */
    private static final a f5624a = new a("file_manager|filemanager_slice_threshold", 2097152, new Pair(1024, 1073741824));
    private static final a b = new a("file_manager|filemanager_slice_num", 2, new Pair(0, 11));
    private static final a c;

    public static int e() {
        return ((Integer) b(f5624a)).intValue();
    }

    public static int d() {
        return ((Integer) b(b)).intValue();
    }

    public static int c() {
        return ((Integer) b(c)).intValue();
    }

    private static Object b(String str) {
        return NetworkKit.getInstance().getOption(str);
    }

    private static Object b(a aVar) {
        if (aVar == null) {
            return null;
        }
        T t = aVar.b;
        Object b2 = b(aVar.f5625a);
        if (b2 == null || !(b2 instanceof String)) {
            return t;
        }
        if (aVar.c == null) {
            return b2;
        }
        try {
            int parseInt = Integer.parseInt((String) b2);
            return (parseInt <= ((Integer) aVar.c.first).intValue() || parseInt >= ((Integer) aVar.c.second).intValue()) ? t : Integer.valueOf(parseInt);
        } catch (NumberFormatException unused) {
            FLogger.w("DynamicConfigManager", "getNumericalConfigValue NumberFormatException", new Object[0]);
            return t;
        }
    }

    public static int b() {
        Object b2 = b(b.f5625a);
        if (b2 == null) {
            return 0;
        }
        if (b2 instanceof String) {
            try {
                return Integer.parseInt((String) b2);
            } catch (NumberFormatException unused) {
                FLogger.w("DynamicConfigManager", "getNumericalConfigValue NumberFormatException", new Object[0]);
                return 0;
            }
        }
        if (b2 instanceof Integer) {
            return ((Integer) b2).intValue();
        }
        return 0;
    }

    public static boolean a(String str, boolean z) {
        return a(new a(str, Boolean.valueOf(z), new Pair(0, 0)));
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static boolean a(a aVar) {
        if (aVar == null) {
            return false;
        }
        boolean booleanValue = ((Boolean) aVar.b).booleanValue();
        Object b2 = b(aVar.f5625a);
        return (b2 == null || !(b2 instanceof String)) ? booleanValue : Boolean.parseBoolean((String) b2);
    }

    public static String a(String str) {
        Object b2;
        return (TextUtils.isEmpty(str) || (b2 = b(str)) == null || !(b2 instanceof String)) ? "" : (String) b2;
    }

    static class a<T> {

        /* renamed from: a, reason: collision with root package name */
        String f5625a;
        T b;
        Pair<Integer, Integer> c;

        public a(String str, T t, Pair<Integer, Integer> pair) {
            this.f5625a = str;
            this.b = t;
            this.c = pair;
        }
    }

    public static String a() {
        return a("file_manager|ABTest_dyFrag_groupid");
    }

    static {
        new a("file_manager|filemanager_auto_slice", false, new Pair(0, 0));
        c = new a("file_manager|filemanager_executor_num", -100, new Pair(0, 101));
    }
}
