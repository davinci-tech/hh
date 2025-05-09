package com.huawei.watchface.videoedit.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.WindowManager;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.watchface.utils.HwLog;

/* loaded from: classes9.dex */
public final class ScreenUtils {
    public static final String TAG = "ScreenUtils";
    private static boolean isTahitiProduct = false;

    private ScreenUtils() {
    }

    public static DisplayMetrics displayMetrics(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (context == null) {
            HwLog.i(TAG, "in displayMetrics : context is null");
            return displayMetrics;
        }
        ((WindowManager) context.getSystemService(Constants.NATIVE_WINDOW_SUB_DIR)).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    public static DisplayMetrics realDisplayMetrics(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (context == null) {
            HwLog.i(TAG, "in displayMetrics : context is null");
            return displayMetrics;
        }
        ((WindowManager) context.getSystemService(Constants.NATIVE_WINDOW_SUB_DIR)).getDefaultDisplay().getRealMetrics(displayMetrics);
        return displayMetrics;
    }

    public static int getWidth(Context context) {
        return displayMetrics(context).widthPixels;
    }

    public static int getHeight(Context context) {
        return displayMetrics(context).heightPixels;
    }

    public static int getDeviceWidth(Context context) {
        return realDisplayMetrics(context).widthPixels;
    }

    public static int getDeviceHeight(Context context) {
        return realDisplayMetrics(context).heightPixels;
    }

    public static int dp2px(int i) {
        return (int) TypedValue.applyDimension(1, i, Resources.getSystem().getDisplayMetrics());
    }

    public static boolean isTahitiProduct() {
        return isTahitiProduct;
    }
}
