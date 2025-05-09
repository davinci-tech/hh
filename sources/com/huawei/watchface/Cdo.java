package com.huawei.watchface;

import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.watchface.environment.Environment;
import com.huawei.watchface.utils.HwLog;
import com.huawei.watchface.utils.constants.WatchFaceConstant;
import com.huawei.watchface.videoedit.utils.ScreenUtils;

/* renamed from: com.huawei.watchface.do, reason: invalid class name */
/* loaded from: classes7.dex */
public class Cdo {

    /* renamed from: a, reason: collision with root package name */
    private static final boolean f10992a;
    private static final boolean b;
    private static final String c;
    private static final String d;

    static {
        f10992a = ((cu.a(WatchFaceConstant.b, "").isEmpty() && cu.a("persist.sys.fold.disp.size", "").isEmpty()) || d()) ? false : true;
        b = cu.a("ro.build.characteristics", "").equals("tablet");
        c = cu.a("persist.sys.default.res.yres", "");
        d = cu.a("persist.sys.default.res.xres", "");
    }

    private static boolean d() {
        String a2 = cu.a("ro.config.hw_fold_disp");
        HwLog.i(ScreenUtils.TAG, "isDoubleDisplayProduct screenSize = " + a2);
        if (TextUtils.isEmpty(a2)) {
            return false;
        }
        String[] split = a2.split(",");
        return split.length >= 9 && dh.a(split[8], 0) == 1;
    }

    public static String a() {
        if (f10992a) {
            return "2480*2200";
        }
        String str = c;
        if (!TextUtils.isEmpty(str)) {
            String str2 = d;
            if (!TextUtils.isEmpty(str2)) {
                return str + "*" + str2;
            }
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        try {
            Display e = e();
            if (e != null) {
                e.getRealMetrics(displayMetrics);
            }
        } catch (Exception unused) {
            displayMetrics = Environment.getApplicationContext().getResources().getDisplayMetrics();
        }
        return String.valueOf(displayMetrics.heightPixels) + "*" + String.valueOf(displayMetrics.widthPixels);
    }

    public static String b() {
        if (f10992a) {
            return "2480*2200";
        }
        if (c()) {
            return "1920*1200";
        }
        String a2 = a();
        return (a(a2) || b(a2) || c(a2)) ? "1920*1080" : ("2560*1600".equals(a2) || "1600*2560".equals(a2)) ? "1920*1200" : a2;
    }

    private static boolean a(String str) {
        return "2560*1440".equals(str) || "1520*720".equals(str) || "2880*1440".equals(str) || "1480*720".equals(str);
    }

    private static boolean b(String str) {
        return "1280*720".equals(str) || "1496*720".equals(str) || "1493*720".equals(str);
    }

    private static boolean c(String str) {
        return "2240*1080".equals(str) || "2280*1080".equals(str) || "2340*1080".equals(str) || "2220*1080".equals(str);
    }

    private static Display e() {
        Object a2 = Environment.a(Constants.NATIVE_WINDOW_SUB_DIR);
        WindowManager windowManager = a2 instanceof WindowManager ? (WindowManager) a2 : null;
        if (windowManager == null) {
            return null;
        }
        try {
            return windowManager.getDefaultDisplay();
        } catch (Exception e) {
            HwLog.e(ScreenUtils.TAG, HwLog.printException(e));
            return null;
        }
    }

    public static boolean c() {
        return b;
    }
}
