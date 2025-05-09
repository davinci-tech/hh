package com.huawei.stressgame.algorithm;

import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes6.dex */
public class BIO {

    /* renamed from: a, reason: collision with root package name */
    private static final String f8722a = "BIO";
    private static BIO e;

    public static native float[] bioFeedbackAlgorithm(short[] sArr, byte[] bArr, int i, int i2, int i3);

    static {
        try {
            System.loadLibrary("BiofeedBack");
            LogUtil.a(f8722a, "load .so success");
        } catch (UnsatisfiedLinkError e2) {
            LogUtil.b(f8722a, "load .so fail" + e2.getMessage());
        }
    }

    private BIO() {
    }

    public static BIO b() {
        BIO bio;
        synchronized (BIO.class) {
            if (e == null) {
                e = new BIO();
            }
            bio = e;
        }
        return bio;
    }
}
