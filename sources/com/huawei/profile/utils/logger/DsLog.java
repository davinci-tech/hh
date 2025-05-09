package com.huawei.profile.utils.logger;

import com.huawei.profile.trace.TraceIdManager;

/* loaded from: classes6.dex */
public class DsLog {
    private static final Object LOCK = new Object();
    public static final int SHOW_LINE_NUMBER = 4;
    public static final int SHOW_METHOD = 2;
    public static final int SHOW_NOTHING = 0;
    public static final int SHOW_THREAD = 1;
    private static boolean isInitialized = false;
    private static boolean isLogD;
    private static boolean isLogV;

    private DsLog() {
    }

    public static void init(String str) {
        init(str, 0);
    }

    public static void init(String str, int i) {
        synchronized (LOCK) {
            if (!isInitialized) {
                isInitialized = true;
                Settings init = AndroidLogger.init(str);
                init.logAdapter(new AndroidLogAdapter());
                init.methodOffset(1);
                init.methodCount(1);
                if ((i & 1) == 0) {
                    init.hideThreadInfo();
                }
                if ((i & 2) == 0) {
                    init.hideMethodInfo();
                }
                if ((i & 4) == 0) {
                    init.hideLineNumber();
                }
                initLogPrintLevel();
            }
        }
    }

    public static void setSecondLogger(LogAdapter logAdapter) {
        AndroidLogger.init(null).setSecondLogAdapter(logAdapter);
    }

    public static void dt(String str, String str2, Object... objArr) {
        if (isLogD) {
            AndroidLogger.debug(buildMessage(str, str2), objArr);
        }
    }

    public static void et(String str, String str2, Object... objArr) {
        AndroidLogger.error(buildMessage(str, str2), objArr);
    }

    public static void et(String str, Throwable th, String str2, Object... objArr) {
        AndroidLogger.error(th, buildMessage(str, str2), objArr);
    }

    public static void wt(String str, String str2, Object... objArr) {
        AndroidLogger.warn(buildMessage(str, str2), objArr);
    }

    public static void it(String str, String str2, Object... objArr) {
        AndroidLogger.info(buildMessage(str, str2), objArr);
    }

    public static void vt(String str, String str2, Object... objArr) {
        if (isLogV) {
            AndroidLogger.verbose(buildMessage(str, str2), objArr);
        }
    }

    private static String buildMessage(String str, String str2) {
        String str3;
        StringBuilder sb;
        String traceId = TraceIdManager.getTraceId();
        if (traceId == null) {
            str3 = "";
        } else {
            str3 = "[" + traceId + "]";
        }
        if (str == null) {
            sb = new StringBuilder();
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(str3);
            sb2.append(str);
            str3 = ":";
            sb = sb2;
        }
        sb.append(str3);
        sb.append(str2);
        return sb.toString();
    }

    private static void initLogPrintLevel() {
        boolean isDebugOn = SystemPropertiesUtil.isDebugOn();
        isLogV = isDebugOn;
        isLogD = isDebugOn;
    }
}
