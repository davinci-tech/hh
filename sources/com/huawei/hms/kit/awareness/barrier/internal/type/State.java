package com.huawei.hms.kit.awareness.barrier.internal.type;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes9.dex */
public interface State {
    public static final int FALSE = 0;
    public static final int TRUE = 1;
    public static final int UNKNOWN = 2;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface a {
    }

    static int and(int i, int i2) {
        if (i == 0 || i2 == 0) {
            return 0;
        }
        return (i == 2 || i2 == 2) ? 2 : 1;
    }

    static int not(int i) {
        if (i == 2) {
            return 2;
        }
        return i == 1 ? 0 : 1;
    }

    static int or(int i, int i2) {
        int i3 = 1;
        if (i != 1 && i2 != 1) {
            i3 = 2;
            if (i != 2 && i2 != 2) {
                return 0;
            }
        }
        return i3;
    }
}
