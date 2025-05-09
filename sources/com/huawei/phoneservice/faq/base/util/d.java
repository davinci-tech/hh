package com.huawei.phoneservice.faq.base.util;

import android.app.Activity;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import com.huawei.hms.android.SystemUtils;
import com.huawei.phoneservice.faq.base.util.FaqHwFrameworkUtil;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes5.dex */
public class d {
    private static Pattern d = Pattern.compile("\\d");
    private static int c = 0;

    public static void cdc_(Activity activity, int[] iArr, int i) {
        int g = g();
        if (g == 0) {
            FaqHwFrameworkUtil.cde_(activity, new C0228d(activity, iArr, i));
        } else if (3 == g) {
            cdb_(activity, iArr, i);
        }
    }

    public static String h() {
        String f = f();
        if (!TextUtils.isEmpty(f)) {
            return "MagicUI_" + f;
        }
        String i = i();
        if (TextUtils.isEmpty(i)) {
            return d();
        }
        Matcher matcher = d.matcher(i);
        return matcher.find() ? i.substring(matcher.start()) : i;
    }

    public static String e() {
        return Build.MODEL;
    }

    public static String b() {
        return b.c() ? "TABLET" : "PHONE";
    }

    public static String c() {
        try {
            String i = i();
            if (!TextUtils.isEmpty(i)) {
                if (i.contains("_")) {
                    return "EMUI" + i.split("_")[1];
                }
                return "EMUI" + i;
            }
        } catch (Exception e) {
            i.c("DeviceUtils", "getEmui()  Exception..." + e.getMessage());
        }
        return d();
    }

    public static String a() {
        return com.huawei.phoneservice.faq.base.d.a(SystemUtils.PRODUCT_BRAND);
    }

    public static String d() {
        return "Android " + Build.VERSION.RELEASE;
    }

    private static int g() {
        return c;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void cdb_(Activity activity, int[] iArr, int i) {
        int dimensionPixelSize = activity.getResources().getDimensionPixelSize(i);
        for (int i2 : iArr) {
            View findViewById = activity.findViewById(i2);
            if (findViewById != null) {
                findViewById.setPadding(dimensionPixelSize, findViewById.getPaddingTop(), dimensionPixelSize, findViewById.getPaddingBottom());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void a(int i) {
        c = i;
    }

    private static String f() {
        return com.huawei.phoneservice.faq.base.d.a("ro.build.version.magic");
    }

    /* renamed from: com.huawei.phoneservice.faq.base.util.d$d, reason: collision with other inner class name */
    class C0228d implements FaqHwFrameworkUtil.b {
        final /* synthetic */ int b;
        final /* synthetic */ int[] d;
        final /* synthetic */ Activity e;

        @Override // com.huawei.phoneservice.faq.base.util.FaqHwFrameworkUtil.b
        public void a(int i) {
            d.a(i);
            if (3 == i) {
                d.cdb_(this.e, this.d, this.b);
            }
        }

        C0228d(Activity activity, int[] iArr, int i) {
            this.e = activity;
            this.d = iArr;
            this.b = i;
        }
    }

    private static String i() {
        return com.huawei.phoneservice.faq.base.d.a("ro.build.version.emui");
    }
}
