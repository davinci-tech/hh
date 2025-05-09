package com.huawei.hms.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.AndroidException;
import com.huawei.hms.support.hianalytics.HiAnalyticsUtils;
import com.huawei.hms.support.log.HMSLog;
import java.sql.Timestamp;

/* loaded from: classes4.dex */
public class AnalyticsSwitchHolder {
    public static final int ANALYTICS_DISABLED = 2;
    public static final int ANALYTICS_ENABLED = 1;

    /* renamed from: a, reason: collision with root package name */
    private static volatile int f6134a;
    private static final Object b = new Object();
    private static volatile Long c = 0L;
    private static volatile boolean d = false;
    private static volatile boolean e = false;

    class a implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ Context f6135a;

        a(Context context) {
            this.f6135a = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            AnalyticsSwitchHolder.f(this.f6135a);
            HMSLog.i("AnalyticsSwitchHolder", "getStateForHmsAnalyticsProvider");
        }
    }

    class b implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ Context f6136a;

        b(Context context) {
            this.f6136a = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            HMSLog.i("AnalyticsSwitchHolder", "enter setAnalyticsStateAndTimestamp");
            AnalyticsSwitchHolder.f(this.f6136a);
            HMSLog.i("AnalyticsSwitchHolder", "quit setAnalyticsStateAndTimestamp");
        }
    }

    private static boolean b(Context context) {
        Bundle bundle;
        if (context == null) {
            HMSLog.e("AnalyticsSwitchHolder", "In getBiIsReportSetting, context is null.");
            return false;
        }
        PackageManager packageManager = context.getPackageManager();
        if (packageManager != null) {
            try {
                ApplicationInfo applicationInfo = packageManager.getPackageInfo(context.getPackageName(), 128).applicationInfo;
                if (applicationInfo != null && (bundle = applicationInfo.metaData) != null) {
                    return bundle.getBoolean("com.huawei.hms.client.bireport.setting");
                }
            } catch (AndroidException unused) {
                HMSLog.e("AnalyticsSwitchHolder", "In getBiIsReportSetting, Failed to read meta data bi report setting.");
            } catch (RuntimeException e2) {
                HMSLog.e("AnalyticsSwitchHolder", "In getBiIsReportSetting, Failed to read meta data bi report setting.", e2);
            }
        }
        HMSLog.i("AnalyticsSwitchHolder", "In getBiIsReportSetting, configuration not found for bi report setting.");
        return false;
    }

    private static void c(Context context) {
        c = Long.valueOf(new Timestamp(System.currentTimeMillis()).getTime());
        new Thread(new a(context), "Thread-getStateForHmsAnalyticsProvider").start();
    }

    private static boolean d(Context context) {
        return RegionUtils.isChinaROM(context);
    }

    private static void e(Context context) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        if (timestamp.getTime() - c.longValue() < 86400000 || c.longValue() <= 0) {
            return;
        }
        c = Long.valueOf(timestamp.getTime());
        new Thread(new b(context), "Thread-refreshOobeAnalyticsState").start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void f(Context context) {
        if (context == null) {
            HMSLog.e("AnalyticsSwitchHolder", "In setAnalyticsState、, context is null.");
            return;
        }
        if (HiAnalyticsUtils.getInstance().getOobeAnalyticsState(context) != 1) {
            synchronized (b) {
                f6134a = 2;
            }
            com.huawei.hms.stats.a.c().a();
            return;
        }
        synchronized (b) {
            f6134a = 1;
        }
        if (HiAnalyticsUtils.getInstance().getInitFlag() || d) {
            return;
        }
        HMSBIInitializer.getInstance(context).initHaSDK();
        d = true;
    }

    public static int getAndRefreshAnalyticsState(Context context) {
        int i;
        synchronized (b) {
            isAnalyticsDisabled(context);
            i = f6134a;
        }
        return i;
    }

    public static boolean getBiSetting(Context context) {
        Bundle bundle;
        if (context == null) {
            HMSLog.e("AnalyticsSwitchHolder", "In getBiSetting, context is null.");
            return false;
        }
        PackageManager packageManager = context.getPackageManager();
        if (packageManager != null) {
            try {
                ApplicationInfo applicationInfo = packageManager.getPackageInfo(context.getPackageName(), 128).applicationInfo;
                if (applicationInfo != null && (bundle = applicationInfo.metaData) != null) {
                    return bundle.getBoolean("com.huawei.hms.client.bi.setting");
                }
            } catch (AndroidException unused) {
                HMSLog.e("AnalyticsSwitchHolder", "In getBiSetting, Failed to read meta data bisetting.");
            } catch (RuntimeException e2) {
                HMSLog.e("AnalyticsSwitchHolder", "In getBiSetting, Failed to read meta data bisetting.", e2);
            }
        }
        HMSLog.i("AnalyticsSwitchHolder", "In getBiSetting, configuration not found for bisetting.");
        return false;
    }

    public static boolean isAnalyticsDisabled(Context context) {
        synchronized (b) {
            if (f6134a == 0) {
                if (context == null) {
                    return true;
                }
                if (b(context)) {
                    HMSLog.i("AnalyticsSwitchHolder", "Builder->biReportSetting :true");
                    f6134a = 1;
                } else if (getBiSetting(context)) {
                    HMSLog.i("AnalyticsSwitchHolder", "Builder->biSetting :true");
                    f6134a = 2;
                } else if (d(context)) {
                    f6134a = 1;
                } else {
                    HMSLog.i("AnalyticsSwitchHolder", "not ChinaROM");
                    f6134a = 3;
                    e = true;
                    c(context);
                }
            } else if (e) {
                e(context);
            }
            return f6134a != 1;
        }
    }
}
