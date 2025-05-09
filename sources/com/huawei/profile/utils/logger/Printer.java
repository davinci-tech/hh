package com.huawei.profile.utils.logger;

/* loaded from: classes6.dex */
public interface Printer {
    void debug(String str, Object... objArr);

    void error(String str, Object... objArr);

    void error(Throwable th, String str, Object... objArr);

    void info(String str, Object... objArr);

    Settings init(String str);

    void verbose(String str, Object... objArr);

    void warn(String str, Object... objArr);
}
