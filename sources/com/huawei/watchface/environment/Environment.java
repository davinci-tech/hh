package com.huawei.watchface.environment;

import android.R;
import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.view.ContextThemeWrapper;
import com.huawei.watchface.cv;
import com.huawei.watchface.utils.HwLog;

/* loaded from: classes7.dex */
public final class Environment {

    /* renamed from: a, reason: collision with root package name */
    private static Application f11010a = null;
    public static volatile boolean sIsAarInTheme = false;

    private Environment() {
    }

    public static void setApplication(Application application) {
        if (f11010a == null) {
            f11010a = application;
        }
        c();
    }

    public static Context a() {
        Application application = f11010a;
        if (application == null) {
            return null;
        }
        Resources resources = application.getResources();
        int identifier = resources == null ? 0 : resources.getIdentifier("androidhwext:style/Theme.Emui", null, null);
        if (identifier == 0) {
            return new ContextThemeWrapper(f11010a, R.style.Theme.Material);
        }
        return new ContextThemeWrapper(f11010a, identifier);
    }

    public static Application getApplicationContext() {
        return f11010a;
    }

    private static void c() {
        String b = b();
        if (TextUtils.isEmpty(b)) {
            sIsAarInTheme = false;
        } else {
            sIsAarInTheme = b.equals(cv.f());
        }
    }

    public static Object a(String str) {
        Application application;
        if (TextUtils.isEmpty(str) || (application = f11010a) == null) {
            return null;
        }
        try {
            return application.getSystemService(str);
        } catch (Exception e) {
            HwLog.e(HwLog.TAG, "getSystemService error " + HwLog.printException(e));
            return null;
        }
    }

    public static String b() {
        Application application = f11010a;
        return application == null ? "" : application.getApplicationInfo().packageName;
    }
}
