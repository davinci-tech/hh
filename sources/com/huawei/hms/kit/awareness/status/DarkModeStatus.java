package com.huawei.hms.kit.awareness.status;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes9.dex */
public interface DarkModeStatus {
    public static final int DARK_MODE_OFF = 0;
    public static final int DARK_MODE_ON = 1;
    public static final int DARK_MODE_UNKNOWN = -1;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface DarkModeStatusDef {
    }

    boolean isDarkModeOn();
}
