package com.huawei.hihealth.data.constant;

/* loaded from: classes.dex */
public class HiDataUtil {
    private HiDataUtil() {
    }

    public static int c(Integer num) {
        if (num != null) {
            return num.intValue();
        }
        return 0;
    }

    public static long d(Long l) {
        if (l != null) {
            return l.longValue();
        }
        return 0L;
    }

    public static float b(Float f) {
        if (f != null) {
            return f.floatValue();
        }
        return 0.0f;
    }
}
