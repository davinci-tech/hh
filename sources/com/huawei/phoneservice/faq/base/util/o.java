package com.huawei.phoneservice.faq.base.util;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import com.huawei.android.fsm.HwFoldScreenManagerEx;
import com.huawei.openalliance.ad.constant.Constants;

/* loaded from: classes5.dex */
public class o {
    public static void cdn_(View view, int i) {
        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            marginLayoutParams.width = i;
            view.setLayoutParams(marginLayoutParams);
        }
    }

    public static void cdm_(View view, int i, int i2) {
        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            if (marginLayoutParams.getMarginStart() != 0) {
                marginLayoutParams.setMarginStart(i);
            }
            if (marginLayoutParams.getMarginEnd() != 0) {
                marginLayoutParams.setMarginEnd(i2);
            }
            view.setLayoutParams(marginLayoutParams);
        }
    }

    public static void cdl_(Activity activity, int[] iArr, int i) {
        if (iArr == null || activity == null) {
            return;
        }
        int dimension = (int) activity.getResources().getDimension(i);
        for (int i2 : iArr) {
            View findViewById = activity.findViewById(i2);
            if (findViewById != null) {
                cdm_(findViewById, dimension, dimension);
            }
        }
    }

    public static boolean e(Context context) {
        try {
            if (!a()) {
                return false;
            }
            if (HwFoldScreenManagerEx.getDisplayMode() != 1) {
                if (HwFoldScreenManagerEx.getDisplayMode() != 7) {
                    return false;
                }
            }
            return true;
        } catch (Throwable unused) {
            boolean z = context == null || context.getResources() == null || context.getResources().getConfiguration() == null;
            i.c("FaqTahitiUtils", "isPadOrTahitiDevice isNull:" + z);
            if (z) {
                return false;
            }
            float f = cdk_(context).widthPixels;
            float f2 = cdk_(context).heightPixels;
            return ((double) (Math.max(f, f2) / Math.min(f, f2))) < 1.33d && context.getResources().getConfiguration().smallestScreenWidthDp > 533;
        }
    }

    public static boolean c(Context context) {
        if (e(context)) {
            return true;
        }
        return b.c();
    }

    private static boolean a() {
        try {
            return HwFoldScreenManagerEx.isFoldable();
        } catch (Throwable unused) {
            i.c("FaqTahitiUtils", "get device foldAble error");
            return false;
        }
    }

    private static DisplayMetrics cdk_(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (context != null) {
            ((WindowManager) context.getSystemService(Constants.NATIVE_WINDOW_SUB_DIR)).getDefaultDisplay().getMetrics(displayMetrics);
        }
        return displayMetrics;
    }
}
