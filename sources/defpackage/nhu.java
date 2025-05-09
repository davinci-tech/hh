package defpackage;

import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.syncmgr.SyncStressDataApi;
import com.huawei.syncmgr.alarmdata.AlarmDataSyncApi;
import com.huawei.syncmgr.basicdata.BasicDataSyncApi;
import com.huawei.syncmgr.calendarsync.CalendarSyncApi;
import com.huawei.syncmgr.coresleep.CoreSleepSyncApi;
import com.huawei.syncmgr.menstrual.MenstrualSyncApi;
import com.huawei.syncmgr.periodrri.PeriodRriSyncApi;
import health.compact.a.Services;

/* loaded from: classes6.dex */
public final class nhu {
    public static CoreSleepSyncApi c() {
        LogUtil.a("SyncApiManager", "getCoreSleepSyncApi");
        return (CoreSleepSyncApi) Services.a("SyncApiManager", CoreSleepSyncApi.class);
    }

    public static SyncStressDataApi h() {
        return (SyncStressDataApi) Services.a("SyncApiManager", SyncStressDataApi.class);
    }

    public static PeriodRriSyncApi g() {
        return (PeriodRriSyncApi) Services.a("SyncApiManager", PeriodRriSyncApi.class);
    }

    public static MenstrualSyncApi e() {
        LogUtil.a("SyncApiManager", "getMenstrualSyncApi");
        return (MenstrualSyncApi) Services.a("SyncApiManager", MenstrualSyncApi.class);
    }

    public static CalendarSyncApi b() {
        return (CalendarSyncApi) Services.a("SyncApiManager", CalendarSyncApi.class);
    }

    public static AlarmDataSyncApi d() {
        return (AlarmDataSyncApi) Services.a("SyncApiManager", AlarmDataSyncApi.class);
    }

    public static BasicDataSyncApi a() {
        return (BasicDataSyncApi) Services.a("SyncApiManager", BasicDataSyncApi.class);
    }
}
