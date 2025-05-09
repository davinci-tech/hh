package com.huawei.watchface;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import com.huawei.uikit.hwcolumnsystem.widget.HwColumnSystem;
import com.huawei.watchface.environment.Environment;
import com.huawei.watchface.utils.DensityUtil;
import com.huawei.watchface.utils.HwLog;

/* loaded from: classes7.dex */
public class dd {

    /* renamed from: a, reason: collision with root package name */
    private static HwColumnSystem f10983a;
    private static int b;
    private static int c;

    public static void a(Configuration configuration, String str) {
        if (f10983a == null || configuration == null) {
            HwLog.e("HwColumnSystemHelper", "updateColumnSystem the method params is null return ");
            return;
        }
        Application applicationContext = Environment.getApplicationContext();
        int dip2px = DensityUtil.dip2px(configuration.screenWidthDp);
        int dip2px2 = DensityUtil.dip2px(configuration.screenHeightDp);
        float f = applicationContext.getResources().getDisplayMetrics().density;
        if (b == dip2px && c == dip2px2) {
            HwLog.i("HwColumnSystemHelper", "updateColumnSystem the same params return");
            return;
        }
        HwLog.i("HwColumnSystemHelper", "updateColumnSystem ,caller = " + str + ",width =" + dip2px + ",height = " + dip2px2);
        b = dip2px;
        c = dip2px2;
        f10983a.d(applicationContext, dip2px, dip2px2, f);
    }

    public static void a(Context context) {
        if (f10983a != null) {
            return;
        }
        HwLog.i("HwColumnSystemHelper", "init column system");
        f10983a = new HwColumnSystem(context);
    }

    public static int a() {
        HwColumnSystem hwColumnSystem = f10983a;
        if (hwColumnSystem == null) {
            HwLog.e("HwColumnSystemHelper", "HwColumnSystemHelper, getColumnNum: hwColumnSystem = null");
            return 4;
        }
        return hwColumnSystem.f();
    }

    public static int b() {
        HwColumnSystem hwColumnSystem = f10983a;
        if (hwColumnSystem == null) {
            HwLog.e("HwColumnSystemHelper", "getGutter: mHwColumnSystem == null");
            return 0;
        }
        return hwColumnSystem.a();
    }

    public static float c() {
        HwColumnSystem hwColumnSystem = f10983a;
        if (hwColumnSystem == null) {
            HwLog.e("HwColumnSystemHelper", "getGutter: mHwColumnSystem == null");
            return 0.0f;
        }
        return hwColumnSystem.g();
    }

    public static float a(int i) {
        HwColumnSystem hwColumnSystem = f10983a;
        if (hwColumnSystem == null) {
            HwLog.e("HwColumnSystemHelper", "getGutter: mHwColumnSystem == null");
            return 0.0f;
        }
        return hwColumnSystem.d(i);
    }

    public static int d() {
        HwColumnSystem hwColumnSystem = f10983a;
        if (hwColumnSystem == null) {
            HwLog.e("HwColumnSystemHelper", "HwColumnSystemHelper, getScreenMode: hwColumnSystem = null");
            return 0;
        }
        int f = hwColumnSystem.f();
        if (f != 8) {
            return f != 12 ? 0 : 2;
        }
        return 1;
    }

    public static int e() {
        int round;
        HwLog.i("HwColumnSystemHelper", "getSingleButtonWidth: columnNum: " + a());
        int d = d();
        HwLog.i("HwColumnSystemHelper", "getSingleButtonWidth: screenMode: " + d);
        int b2 = b();
        float c2 = c();
        HwLog.i("HwColumnSystemHelper", "getSingleButtonWidth: gutter: " + b2 + " colu: " + c2 + " density: " + Environment.getApplicationContext().getResources().getDisplayMetrics().density);
        if (d == 1) {
            round = Math.round(a(6));
        } else if (d == 2) {
            round = Math.round(a(8));
        } else {
            round = Math.round(c2 * 4.0f) + (b2 * 3);
        }
        HwLog.i("HwColumnSystemHelper", "getSingleButtonWidth: width: " + round);
        return round;
    }
}
