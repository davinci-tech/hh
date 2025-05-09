package com.huawei.hms.kit.awareness.status;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes9.dex */
public interface HeadsetStatus {
    public static final int CONNECTED = 1;
    public static final int DISCONNECTED = 0;
    public static final int UNKNOWN = -1;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface HeadsetStatusDef {
    }

    int getStatus();
}
