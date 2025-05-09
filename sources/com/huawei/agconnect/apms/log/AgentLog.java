package com.huawei.agconnect.apms.log;

/* loaded from: classes.dex */
public interface AgentLog {
    public static final int DEBUG = 3;
    public static final int ERROR = 6;
    public static final int INFO = 4;
    public static final String TAG = "com.huawei.agc.apms";
    public static final int WARN = 5;

    void debug(String str);

    void error(String str);

    void error(String str, Throwable th);

    int getLevel();

    void info(String str);

    void setLevel(int i);

    void warn(String str);
}
