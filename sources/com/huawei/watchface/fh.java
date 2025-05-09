package com.huawei.watchface;

import android.os.SystemClock;
import android.text.TextUtils;
import com.huawei.hianalytics.process.HiAnalyticsManager;
import com.huawei.watchface.api.HwWatchFaceApi;
import com.huawei.watchface.environment.Environment;
import com.huawei.watchface.utils.BackgroundTaskUtils;
import com.huawei.watchface.utils.CommonUtils;
import com.huawei.watchface.utils.HwLog;
import com.huawei.watchface.utils.MobileInfoHelper;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

/* loaded from: classes7.dex */
public class fh {
    private void a() {
        try {
            BackgroundTaskUtils.submit(new Runnable() { // from class: com.huawei.watchface.fh$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    fh.c();
                }
            });
        } catch (Exception e) {
            HwLog.e("CrashReport", "initHiAnalytics  Exception : " + HwLog.printException(e));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void c() {
        eo.a().a(Environment.getApplicationContext());
    }

    public void a(Throwable th, Boolean bool) {
        if (th == null) {
            HwLog.w("CrashReport", "cause is null");
            return;
        }
        HwLog.i("CrashReport", "reportException");
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        try {
            try {
                if (!HiAnalyticsManager.getInitFlag("theme_ha_normal_tag")) {
                    HwLog.i("CrashReport", "isSigned && !HiAnalyticsManager.getInitFlag(AnalyticsUtils.THEME_HA_NORMAL_TAG)");
                    a();
                    SystemClock.sleep(1000L);
                }
                th.printStackTrace(printWriter);
                StringBuilder sb = new StringBuilder();
                sb.append(stringWriter.toString());
                if (th.getMessage().contains("thread")) {
                    sb.append(b());
                }
                String u = CommonUtils.u();
                sb.append("|topActivity:");
                sb.append(u);
                sb.append("|intercepted:");
                sb.append(bool);
                String sb2 = sb.toString();
                if (!TextUtils.isEmpty(sb2)) {
                    LinkedHashMap linkedHashMap = new LinkedHashMap();
                    linkedHashMap.put("type", th.getClass().getName());
                    if (!HwWatchFaceApi.getInstance(Environment.getApplicationContext()).isOversea()) {
                        linkedHashMap.put("message", sb2);
                    }
                    linkedHashMap.put("occur_version", MobileInfoHelper.getVersion());
                    linkedHashMap.put("topActivity", u);
                    HwLog.i("CrashReport", "type: " + th.getClass().getName());
                    ed.a((LinkedHashMap<String, String>) linkedHashMap);
                    if (!bool.booleanValue()) {
                        SystemClock.sleep(1000L);
                    }
                    HwLog.i("CrashReport", "HiAnalytics.onReport() after");
                }
            } catch (Exception e) {
                HwLog.i("CrashReport", "reportException : " + HwLog.printException(e));
            }
        } finally {
            cq.a(stringWriter);
            cq.a(printWriter);
        }
    }

    private String b() {
        StringBuilder sb = new StringBuilder("CurThreadsNum : ");
        Set<Thread> keySet = Thread.getAllStackTraces().keySet();
        sb.append(keySet.size());
        sb.append("\nThreads list:\n");
        Iterator<Thread> it = keySet.iterator();
        while (it.hasNext()) {
            sb.append(it.next().getName());
            sb.append('\n');
        }
        return sb.toString();
    }
}
