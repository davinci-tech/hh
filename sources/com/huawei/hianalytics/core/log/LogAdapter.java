package com.huawei.hianalytics.core.log;

/* loaded from: classes4.dex */
public interface LogAdapter {
    void init(int i, String str);

    boolean isLoggable(int i);

    void println(int i, String str, String str2);
}
