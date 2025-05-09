package com.huawei.haf.bundle;

import android.content.Context;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.base.BuildConfigReader;
import com.huawei.haf.common.utils.AppInfoUtils;
import com.huawei.haf.common.utils.CommonConstant;
import health.compact.a.ReflectionUtils;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes.dex */
public final class AppBundleBuildConfig extends BuildConfigReader {
    private static volatile Class b;
    private static String c;
    private static Set<String> e;
    private static final Object d = new Object();

    /* renamed from: a, reason: collision with root package name */
    private static final AppBundleBuildConfig f2061a = new AppBundleBuildConfig();

    static {
        n();
    }

    private AppBundleBuildConfig() {
    }

    public static Context c() {
        return BaseApplication.e();
    }

    public static long b() {
        return AppInfoUtils.d();
    }

    public static boolean m() {
        return b != null;
    }

    public static boolean o() {
        if (m()) {
            return f2061a.getBoolean("ASSEMBLE_MODE", false);
        }
        return false;
    }

    public static String l() {
        String str = c;
        if (str == null) {
            str = BaseApplication.a();
            int indexOf = str.indexOf(45);
            if (indexOf >= 0) {
                str = str.substring(0, indexOf);
            }
            c = str;
        }
        return str;
    }

    public static String f() {
        return m() ? f2061a.get("MAIN_MODULE", "unknown") : "unknown";
    }

    public static Set<String> d() {
        if (e == null) {
            String[] values = m() ? f2061a.getValues("DYNAMIC_FEATURES") : CommonConstant.e;
            if (values.length > 0) {
                HashSet hashSet = new HashSet(values.length);
                hashSet.addAll(Arrays.asList(values));
                e = Collections.unmodifiableSet(hashSet);
            } else {
                e = Collections.emptySet();
            }
        }
        return e;
    }

    public static String a() {
        return f2061a.get("APP_BUNDLE_ID", "unknown");
    }

    public static int j() {
        return f2061a.getInt("APK_LOAD_MODE", 0);
    }

    public static boolean k() {
        return f2061a.getBoolean("APK_VERIFY_SIGNATURE", false);
    }

    public static String[] h() {
        return f2061a.getValues("APK_VERIFY_SIGNATURE_LIST");
    }

    public static String[] i() {
        return f2061a.getValues("MODULE_WORK_PROCESSES");
    }

    public static String[] g() {
        return f2061a.getValues("FORBIDDEN_WORK_PROCESSES");
    }

    public static String e() {
        return f2061a.get("DEFAULT_MODULE_INFO_VERSION", "1.0.0");
    }

    @Override // com.huawei.haf.common.base.BuildConfigReader
    public Class getBuildConfigClass() {
        if (b == null) {
            n();
        }
        return b;
    }

    private static void n() {
        if (b != null) {
            return;
        }
        synchronized (d) {
            if (b != null) {
                return;
            }
            Class e2 = e(BaseApplication.d());
            if (e2 == null) {
                e2 = e(c().getClass().getPackage().getName());
            }
            b = e2;
        }
    }

    private static Class e(String str) {
        return ReflectionUtils.b(str + ".AppBundleBuildConfig");
    }
}
