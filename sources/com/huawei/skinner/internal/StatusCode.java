package com.huawei.skinner.internal;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes9.dex */
public interface StatusCode {
    public static final int ERROR_UNKNOWN = 30000;
    public static final int FORCE_LOAD = 10006;
    public static final int LOAD_CUSTOM_SKIN = 10001;
    public static final int LOAD_DAY_NIGHT_SKIN = 10002;
    public static final int LOAD_SAME = 20001;
    public static final int LOAD_SAVED_DEF = 20008;
    public static final int LOAD_SAVED_SKIN = 10003;
    public static final int LOAD_SKIN = 10000;
    public static final int LOAD_SUCCESS = 20000;
    public static final int RESTORE_DEF_SKIN = 10004;
    public static final int RESTORE_SAME = 20003;
    public static final int RESTORE_SUCCESS = 20002;
    public static final int TASK_REJECTED = 20004;
    public static final int TOGGLE_DAY_NIGHT = 10005;
    public static final int TOGGLE_SAME = 20006;
    public static final int TOGGLE_SUCCESS = 20005;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface ErrorCode {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface ResultCode {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface StartCode {
    }
}
