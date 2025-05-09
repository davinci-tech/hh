package com.huawei.haf.common.log;

/* loaded from: classes.dex */
public interface Logger {
    void d(String str, boolean z, Object... objArr);

    void e(String str, boolean z, Object... objArr);

    void i(String str, boolean z, Object... objArr);

    boolean isBetaVersion();

    boolean isDebugVersion();

    boolean isReleaseVersion();

    String toString(Throwable th);

    void v(String str, boolean z, Object... objArr);

    void w(String str, boolean z, Object... objArr);
}
