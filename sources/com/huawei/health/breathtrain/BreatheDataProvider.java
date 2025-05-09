package com.huawei.health.breathtrain;

import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes3.dex */
public class BreatheDataProvider {

    /* renamed from: a, reason: collision with root package name */
    private static final String f2198a = "BreatheDataProvider";

    public static native float[] getBreatheResultFromAlgorithm(int i, int i2, int[] iArr, int[] iArr2, int i3);

    static {
        try {
            System.loadLibrary("JanusBreathJni");
            LogUtil.a(f2198a, "load .so success");
        } catch (UnsatisfiedLinkError e) {
            LogUtil.b(f2198a, "load .so fail" + e.getMessage());
        }
    }
}
