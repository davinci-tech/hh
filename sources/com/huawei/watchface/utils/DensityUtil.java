package com.huawei.watchface.utils;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import com.huawei.watchface.environment.Environment;

/* loaded from: classes7.dex */
public class DensityUtil {
    public static int dip2px(float f) {
        return (int) ((f * ((Environment.getApplicationContext() == null || Environment.getApplicationContext().getResources() == null) ? 2.0f : Environment.getApplicationContext().getResources().getDisplayMetrics().density)) + 0.5f);
    }

    public static int a(float f) {
        return (int) ((f / Environment.getApplicationContext().getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int a(int i) {
        try {
            return Environment.getApplicationContext().getResources().getDimensionPixelSize(i);
        } catch (Resources.NotFoundException e) {
            HwLog.e("DensityUtil", "getDimen Resources.NotFoundException: " + HwLog.printException((Exception) e));
            return 0;
        }
    }

    public static String getStringById(int i) {
        Context applicationContext;
        try {
            Application applicationContext2 = Environment.getApplicationContext();
            if (applicationContext2 == null || (applicationContext = applicationContext2.getApplicationContext()) == null) {
                return "";
            }
            return applicationContext.getResources().getString(i);
        } catch (Resources.NotFoundException e) {
            HwLog.e("DensityUtil", "getStringById Resources.NotFoundException: " + HwLog.printException((Exception) e));
            return "";
        }
    }

    public static String getStringById(int i, Object... objArr) {
        try {
            return Environment.getApplicationContext().getString(i, objArr);
        } catch (Exception e) {
            HwLog.e("DensityUtil", "getStringById Resources.NotFoundException: " + HwLog.printException(e));
            return "";
        }
    }

    public static String getQuantityStringById(int i, int i2, Object... objArr) {
        try {
            if (objArr.length == 0) {
                return Environment.getApplicationContext().getResources().getQuantityString(i, i2);
            }
            return Environment.getApplicationContext().getResources().getQuantityString(i, i2, objArr);
        } catch (Resources.NotFoundException e) {
            HwLog.e("DensityUtil", "getQuantityStringById Resources.NotFoundException: " + HwLog.printException((Exception) e));
            return "";
        }
    }

    public static String[] b(int i) {
        try {
            return Environment.getApplicationContext().getResources().getStringArray(i);
        } catch (Resources.NotFoundException e) {
            HwLog.e("DensityUtil", "getStringArrayByID Resources.NotFoundException: " + HwLog.printException((Exception) e));
            return new String[0];
        }
    }

    public static int c(int i) {
        try {
            return Environment.getApplicationContext().getResources().getColor(i);
        } catch (Resources.NotFoundException e) {
            HwLog.e("DensityUtil", "getColor Resources.NotFoundException: " + HwLog.printException((Exception) e));
            return 0;
        }
    }

    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }
}
