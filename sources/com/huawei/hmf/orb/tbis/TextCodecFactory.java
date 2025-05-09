package com.huawei.hmf.orb.tbis;

/* loaded from: classes9.dex */
public final class TextCodecFactory {
    private static Class<? extends TextCodec> defaultCodec;

    public static void registryCodec(Class<? extends TextCodec> cls) {
        defaultCodec = cls;
    }

    public static TextCodec create(String str) {
        Class<? extends TextCodec> cls = defaultCodec;
        if (cls == null) {
            throw new IllegalStateException("The default codec is not registered");
        }
        try {
            return cls.getConstructor(String.class).newInstance(str);
        } catch (Exception e) {
            throw new IllegalArgumentException("instantiate codec error:" + e.getMessage());
        }
    }

    public static TextCodec create() {
        Class<? extends TextCodec> cls = defaultCodec;
        if (cls == null) {
            throw new IllegalStateException("The default codec is not registered");
        }
        try {
            return cls.newInstance();
        } catch (Exception e) {
            throw new IllegalArgumentException("instantiate codec error: " + e.getMessage());
        }
    }
}
