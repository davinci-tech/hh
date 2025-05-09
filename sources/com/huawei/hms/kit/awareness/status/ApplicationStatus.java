package com.huawei.hms.kit.awareness.status;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes9.dex */
public interface ApplicationStatus {
    public static final int RUNNING = 1;
    public static final int SILENT = 0;
    public static final int UNKNOWN = -1;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface ApplicationStatusDef {
    }

    int getStatus();
}
