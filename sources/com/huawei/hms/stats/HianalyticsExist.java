package com.huawei.hms.stats;

import com.huawei.hms.support.log.HMSLog;

/* loaded from: classes4.dex */
public class HianalyticsExist {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f5940a = new Object();
    private static boolean b = false;
    private static boolean c = false;

    public static boolean isHianalyticsExist() {
        boolean z;
        synchronized (f5940a) {
            if (!b) {
                boolean z2 = false;
                try {
                    Class.forName("com.huawei.hianalytics.process.HiAnalyticsInstance");
                    z = true;
                } catch (ClassNotFoundException unused) {
                    HMSLog.i("HianalyticsExist", "In isHianalyticsExist, Failed to find class HiAnalyticsConfig.");
                    z = false;
                }
                try {
                    Class.forName("com.huawei.hms.hatool.HmsHiAnalyticsUtils");
                    z2 = true;
                } catch (ClassNotFoundException unused2) {
                    HMSLog.i("HianalyticsExist", "In isHianalyticsExist, Failed to find class HmsHiAnalyticsUtils.");
                }
                if (z && !z2) {
                    c = true;
                }
                b = true;
                HMSLog.i("HianalyticsExist", "hianalytics exist: " + c);
            }
        }
        return c;
    }
}
