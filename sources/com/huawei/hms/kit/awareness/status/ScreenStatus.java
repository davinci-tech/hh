package com.huawei.hms.kit.awareness.status;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes9.dex */
public interface ScreenStatus {
    public static final int SCREEN_OFF = 0;
    public static final int SCREEN_ON = 1;
    public static final int UNKNOWN = -1;
    public static final int UNLOCK = 2;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface ScreenStatusDef {
    }

    int getStatus();
}
