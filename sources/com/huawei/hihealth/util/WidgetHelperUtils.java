package com.huawei.hihealth.util;

import com.huawei.hihealth.data.listener.FitnessListener;
import health.compact.a.util.LogUtil;

/* loaded from: classes.dex */
public class WidgetHelperUtils {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f4144a = new Object();
    private static volatile WidgetHelperUtils e;
    private FitnessListener d;

    public static WidgetHelperUtils e() {
        if (e == null) {
            synchronized (f4144a) {
                if (e == null) {
                    e = new WidgetHelperUtils();
                }
            }
        }
        return e;
    }

    public void e(FitnessListener fitnessListener) {
        this.d = fitnessListener;
    }

    public void e(String str) {
        LogUtil.d("WidgetHelperUtils", "enter insertFitnessData");
        FitnessListener fitnessListener = this.d;
        if (fitnessListener != null) {
            fitnessListener.onResult(str);
        }
    }
}
