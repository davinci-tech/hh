package com.huawei.hms.network.embedded;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Process;
import com.huawei.hms.framework.common.ContextHolder;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.common.PLSharedPreferences;
import com.huawei.hms.framework.common.StringUtils;
import com.huawei.hms.framework.common.hianalytics.CrashHianalyticsData;
import com.huawei.hms.framework.common.hianalytics.HianalyticsBaseData;
import com.huawei.hms.framework.common.hianalytics.HianalyticsHelper;
import com.huawei.hms.framework.common.hianalytics.InitReport;
import java.lang.Thread;
import java.util.LinkedHashMap;
import java.util.Map;

/* loaded from: classes9.dex */
public class s2 implements Thread.UncaughtExceptionHandler {
    public static final String c = "CrashHandler";
    public static final String d = "crash_analytics";
    public static final int e = 1024;

    /* renamed from: a, reason: collision with root package name */
    public Thread.UncaughtExceptionHandler f5468a = null;
    public Context b = ContextHolder.getAppContext();

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        if (HianalyticsHelper.getInstance().isEnableReportNoSeed(this.b)) {
            Logger.i(c, "crash coming");
            LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
            linkedHashMap.put("time", "" + System.currentTimeMillis());
            linkedHashMap.put(CrashHianalyticsData.PROCESS_ID, "" + Process.myPid());
            linkedHashMap.put(CrashHianalyticsData.THREAD_ID, "" + Process.myTid());
            linkedHashMap.put(CrashHianalyticsData.THREAD_NAME, thread.getName());
            linkedHashMap.put("exception_name", th.getClass().getName());
            linkedHashMap.put("message", StringUtils.anonymizeMessage(th.getMessage()));
            linkedHashMap.put(CrashHianalyticsData.STACK_TRACE, a(th));
            a(linkedHashMap);
        }
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler = this.f5468a;
        if (uncaughtExceptionHandler != null) {
            uncaughtExceptionHandler.uncaughtException(thread, th);
        }
    }

    public void init() {
        this.f5468a = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
        InitReport.reportWhenInit(new a());
    }

    private void a(LinkedHashMap<String, String> linkedHashMap) {
        if (linkedHashMap == null) {
            return;
        }
        Logger.v(c, "data = %s", linkedHashMap);
        try {
            PLSharedPreferences pLSharedPreferences = new PLSharedPreferences(this.b, d);
            SharedPreferences.Editor edit = pLSharedPreferences.edit();
            for (Map.Entry<String, String> entry : linkedHashMap.entrySet()) {
                edit.putString(entry.getKey(), entry.getValue());
            }
            edit.apply();
            Logger.v(c, pLSharedPreferences.getString("message", null));
        } catch (InternalError unused) {
        }
    }

    public class a implements Runnable {
        @Override // java.lang.Runnable
        public void run() {
            s2.this.a();
        }

        public a() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        if (HianalyticsHelper.getInstance().isEnableReportNoSeed(this.b)) {
            PLSharedPreferences pLSharedPreferences = new PLSharedPreferences(this.b, d);
            if (pLSharedPreferences.getString("time", null) == null) {
                Logger.v(c, "not report");
                return;
            }
            LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
            linkedHashMap.put("sdk_type", "UxPP");
            linkedHashMap.put(HianalyticsBaseData.SDK_NAME, "networkkit");
            linkedHashMap.put("sdk_version", "8.0.1.307");
            linkedHashMap.put("crash_type", CrashHianalyticsData.EVENT_ID_CRASH);
            linkedHashMap.put("time", pLSharedPreferences.getString("time", ""));
            linkedHashMap.put(CrashHianalyticsData.PROCESS_ID, pLSharedPreferences.getString(CrashHianalyticsData.PROCESS_ID, ""));
            linkedHashMap.put(CrashHianalyticsData.THREAD_ID, pLSharedPreferences.getString(CrashHianalyticsData.THREAD_ID, ""));
            linkedHashMap.put(CrashHianalyticsData.THREAD_NAME, pLSharedPreferences.getString(CrashHianalyticsData.THREAD_NAME, ""));
            linkedHashMap.put("exception_name", pLSharedPreferences.getString("exception_name", ""));
            linkedHashMap.put("message", pLSharedPreferences.getString("message", ""));
            linkedHashMap.put(CrashHianalyticsData.STACK_TRACE, pLSharedPreferences.getString(CrashHianalyticsData.STACK_TRACE, ""));
            HianalyticsHelper.getInstance().onEvent(linkedHashMap, CrashHianalyticsData.EVENT_ID_CRASH);
            Logger.v(c, "%s", linkedHashMap);
            pLSharedPreferences.clear();
        }
    }

    private String a(Throwable th) {
        StackTraceElement[] stackTrace = th.getStackTrace();
        StringBuilder sb = new StringBuilder(1024);
        for (StackTraceElement stackTraceElement : stackTrace) {
            sb.append("at ");
            sb.append(stackTraceElement.toString());
            sb.append(";");
        }
        return sb.toString();
    }
}
