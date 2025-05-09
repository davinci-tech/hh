package com.huawei.phoneservice.faq.base.util;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import androidx.core.text.TextUtilsCompat;

/* loaded from: classes5.dex */
public class t {
    public static void cdt_(View view, int i) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = i;
        view.setLayoutParams(layoutParams);
    }

    public static void cds_(Activity activity, boolean z, int i) {
        if (activity != null && z) {
            int i2 = Build.VERSION.SDK_INT;
            Window window = activity.getWindow();
            if (window == null) {
                return;
            }
            window.addFlags(Integer.MIN_VALUE);
            window.setStatusBarColor(i);
            View decorView = window.getDecorView();
            if (decorView == null) {
                return;
            }
            int systemUiVisibility = decorView.getSystemUiVisibility();
            decorView.setSystemUiVisibility((i2 >= 29 && cdo_(activity)) ? systemUiVisibility & (-8193) : systemUiVisibility | 8192);
        }
    }

    public static void cdr_(Activity activity) {
        View findViewById;
        if (activity == null || (findViewById = activity.findViewById(R.id.content)) == null) {
            return;
        }
        View childAt = ((ViewGroup) findViewById).getChildAt(0);
        if (childAt == null) {
            i.c("FaqUiUtils", "root view is null");
            return;
        }
        childAt.setFocusable(true);
        childAt.setFocusableInTouchMode(true);
        childAt.requestFocus();
        childAt.requestFocusFromTouch();
        childAt.setDefaultFocusHighlightEnabled(false);
    }

    public static void cdq_(Activity activity, int[] iArr) {
        if (iArr == null || iArr.length <= 0 || cdp_(activity) || !b.c()) {
            return;
        }
        for (int i : iArr) {
            View findViewById = activity.findViewById(i);
            if (findViewById != null) {
                findViewById.setPadding(0, findViewById.getPaddingTop(), 0, findViewById.getPaddingBottom());
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:6:0x003e A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:8:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean cdp_(android.app.Activity r4) {
        /*
            r0 = 0
            java.lang.String r1 = "android.content.Intent"
            java.lang.Class r1 = java.lang.Class.forName(r1)     // Catch: java.lang.Exception -> L20 java.lang.RuntimeException -> L23 java.lang.reflect.InvocationTargetException -> L26 java.lang.IllegalArgumentException -> L29 java.lang.IllegalAccessException -> L2c java.lang.NoSuchMethodException -> L2f java.lang.ClassNotFoundException -> L32
            java.lang.String r2 = "getHwFlags"
            java.lang.Class[] r3 = new java.lang.Class[r0]     // Catch: java.lang.Exception -> L20 java.lang.RuntimeException -> L23 java.lang.reflect.InvocationTargetException -> L26 java.lang.IllegalArgumentException -> L29 java.lang.IllegalAccessException -> L2c java.lang.NoSuchMethodException -> L2f java.lang.ClassNotFoundException -> L32
            java.lang.reflect.Method r1 = r1.getMethod(r2, r3)     // Catch: java.lang.Exception -> L20 java.lang.RuntimeException -> L23 java.lang.reflect.InvocationTargetException -> L26 java.lang.IllegalArgumentException -> L29 java.lang.IllegalAccessException -> L2c java.lang.NoSuchMethodException -> L2f java.lang.ClassNotFoundException -> L32
            android.content.Intent r4 = r4.getIntent()     // Catch: java.lang.Exception -> L20 java.lang.RuntimeException -> L23 java.lang.reflect.InvocationTargetException -> L26 java.lang.IllegalArgumentException -> L29 java.lang.IllegalAccessException -> L2c java.lang.NoSuchMethodException -> L2f java.lang.ClassNotFoundException -> L32
            java.lang.Object[] r2 = new java.lang.Object[r0]     // Catch: java.lang.Exception -> L20 java.lang.RuntimeException -> L23 java.lang.reflect.InvocationTargetException -> L26 java.lang.IllegalArgumentException -> L29 java.lang.IllegalAccessException -> L2c java.lang.NoSuchMethodException -> L2f java.lang.ClassNotFoundException -> L32
            java.lang.Object r4 = r1.invoke(r4, r2)     // Catch: java.lang.Exception -> L20 java.lang.RuntimeException -> L23 java.lang.reflect.InvocationTargetException -> L26 java.lang.IllegalArgumentException -> L29 java.lang.IllegalAccessException -> L2c java.lang.NoSuchMethodException -> L2f java.lang.ClassNotFoundException -> L32
            java.lang.Integer r4 = (java.lang.Integer) r4     // Catch: java.lang.Exception -> L20 java.lang.RuntimeException -> L23 java.lang.reflect.InvocationTargetException -> L26 java.lang.IllegalArgumentException -> L29 java.lang.IllegalAccessException -> L2c java.lang.NoSuchMethodException -> L2f java.lang.ClassNotFoundException -> L32
            int r4 = r4.intValue()     // Catch: java.lang.Exception -> L20 java.lang.RuntimeException -> L23 java.lang.reflect.InvocationTargetException -> L26 java.lang.IllegalArgumentException -> L29 java.lang.IllegalAccessException -> L2c java.lang.NoSuchMethodException -> L2f java.lang.ClassNotFoundException -> L32
            goto L3a
        L20:
            java.lang.String r4 = "isSplitActivity Exception"
            goto L34
        L23:
            java.lang.String r4 = "isSplitActivity RuntimeException"
            goto L34
        L26:
            java.lang.String r4 = "isSplitActivity InvocationTargetException"
            goto L34
        L29:
            java.lang.String r4 = "isSplitActivity IllegalArgumentException"
            goto L34
        L2c:
            java.lang.String r4 = "isSplitActivity IllegalAccessException"
            goto L34
        L2f:
            java.lang.String r4 = "isSplitActivity NoSuchMethodException"
            goto L34
        L32:
            java.lang.String r4 = "isSplitActivity ClassNotFoundException"
        L34:
            java.lang.String r1 = "FaqUiUtils"
            com.huawei.phoneservice.faq.base.util.i.b(r1, r4)
            r4 = r0
        L3a:
            r4 = r4 & 4
            if (r4 == 0) goto L3f
            r0 = 1
        L3f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.phoneservice.faq.base.util.t.cdp_(android.app.Activity):boolean");
    }

    public static boolean c(Context context) {
        return TextUtilsCompat.getLayoutDirectionFromLocale(context.getResources().getConfiguration().locale) == 1;
    }

    private static boolean cdo_(Activity activity) {
        return (activity == null || activity.getResources() == null || activity.getResources().getConfiguration() == null || (activity.getResources().getConfiguration().uiMode & 48) != 32) ? false : true;
    }
}
