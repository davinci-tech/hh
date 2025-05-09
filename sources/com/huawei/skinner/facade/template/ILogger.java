package com.huawei.skinner.facade.template;

/* loaded from: classes6.dex */
public interface ILogger {
    void debug(String str);

    void debug(String str, String str2);

    void error(String str);

    void error(String str, String str2);

    void error(String str, String str2, Throwable th);

    void error(String str, Throwable th);

    void info(String str);

    void info(String str, String str2);

    boolean isShowDetailedStackTrace();

    boolean isShowLog();

    void showDetailedStackTrace(boolean z);

    void showLog(boolean z);

    void warn(String str);

    void warn(String str, String str2);
}
