package com.huawei.emotion.alg;

/* loaded from: classes8.dex */
public class EmotionAlg {
    public static native String EmotionAdviceInterface(String str);

    public static native String GetEmotionHealthVersion();

    static {
        System.loadLibrary("emotionalg");
    }
}
