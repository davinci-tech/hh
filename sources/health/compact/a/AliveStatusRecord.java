package health.compact.a;

import android.content.Context;
import android.os.SystemClock;

/* loaded from: classes.dex */
public class AliveStatusRecord {
    private static AliveStatusRecord e = new AliveStatusRecord(0, 0);

    /* renamed from: a, reason: collision with root package name */
    private long f13099a;
    private long d;

    private AliveStatusRecord(long j, long j2) {
        this.f13099a = j;
        this.d = j2;
    }

    public long a() {
        long currentTimeMillis = System.currentTimeMillis();
        long elapsedRealtime = SystemClock.elapsedRealtime();
        long c = c(currentTimeMillis);
        long a2 = a(elapsedRealtime);
        if (c <= 0 || a2 <= 0) {
            com.huawei.hwlogsmodel.LogUtil.h("Step_AliveStatusRecord", "intervalMillsByTimeStamp <=0 or intervalMillsByElapsedRealTime <= 0");
            return 0L;
        }
        if (c <= a2) {
            com.huawei.hwlogsmodel.LogUtil.a("Step_AliveStatusRecord", "computeIntervalMillsFromNow end value:", Long.valueOf(c));
            return c;
        }
        com.huawei.hwlogsmodel.LogUtil.a("Step_AliveStatusRecord", "computeIntervalMillsFromNow end value:", Long.valueOf(a2));
        return a2;
    }

    private long c(long j) {
        long j2 = this.f13099a;
        if (j2 == 0) {
            com.huawei.hwlogsmodel.LogUtil.a("Step_AliveStatusRecord", "timeStamp zero,no data isValidate,return NO_DEATH_MILLS");
            return 0L;
        }
        if (j <= j2) {
            com.huawei.hwlogsmodel.LogUtil.h("Step_AliveStatusRecord", "maybe modify time,return death mills zero, timeStampNow = ", Long.valueOf(j), " mTimestamp = ", Long.valueOf(this.f13099a));
            return 0L;
        }
        long j3 = j - j2;
        if (j3 > 2592000000L) {
            com.huawei.hwlogsmodel.LogUtil.h("Step_AliveStatusRecord", "interval larger than 30 days,drop death time, intervalMills = ", Long.valueOf(j3));
            return 0L;
        }
        com.huawei.hwlogsmodel.LogUtil.a("Step_AliveStatusRecord", "computeIntervalByTimeStamp:", Long.valueOf(j3));
        return j3;
    }

    private long a(long j) {
        long j2 = this.d;
        if (j <= j2) {
            com.huawei.hwlogsmodel.LogUtil.h("Step_AliveStatusRecord", "maybe reboot,return death mills zero, elapsedRealTimeNow = ", Long.valueOf(j), "mElapsedRealTime = ", Long.valueOf(this.d));
            return 0L;
        }
        long j3 = j - j2;
        if (j3 > 2592000000L) {
            com.huawei.hwlogsmodel.LogUtil.h("Step_AliveStatusRecord", "interval larger than 30 days,drop death time, intervalMills = ", Long.valueOf(j3));
            return 0L;
        }
        com.huawei.hwlogsmodel.LogUtil.a("Step_AliveStatusRecord", "computeIntervalByElapsedRealTime:", Long.valueOf(j3));
        return j3;
    }

    public static void c(Context context) {
        if (context == null) {
            return;
        }
        SharedPerferenceUtils.c(context, System.currentTimeMillis(), SystemClock.elapsedRealtime());
    }

    public static void d(Context context) {
        if (context == null) {
            com.huawei.hwlogsmodel.LogUtil.h("Step_AliveStatusRecord", "deleteAliveRecord context is null.");
        } else {
            SharedPerferenceUtils.b(context);
        }
    }

    public static AliveStatusRecord b(Context context) {
        if (context == null) {
            com.huawei.hwlogsmodel.LogUtil.h("Step_AliveStatusRecord", "deserialize context is null.");
            return e;
        }
        try {
            String[] a2 = SharedPerferenceUtils.a(context);
            if (a2 != null && a2.length >= 2) {
                long parseLong = Long.parseLong(a2[0]);
                long parseLong2 = Long.parseLong(a2[1]);
                AliveStatusRecord aliveStatusRecord = new AliveStatusRecord(parseLong, parseLong2);
                com.huawei.hwlogsmodel.LogUtil.a("Step_AliveStatusRecord", "Deserialize timestamp = ", Long.valueOf(parseLong), " elapsedRealTimeArg = ", Long.valueOf(parseLong2));
                return aliveStatusRecord;
            }
            com.huawei.hwlogsmodel.LogUtil.a("Step_AliveStatusRecord", "alive record empty or data bad,return unset record");
            return e;
        } catch (NumberFormatException e2) {
            com.huawei.hwlogsmodel.LogUtil.b("Step_AliveStatusRecord", "deserialize exception = ", e2.getMessage());
            return e;
        }
    }

    public boolean b() {
        long j = this.f13099a;
        if (j > 0 && this.d > 0) {
            return false;
        }
        com.huawei.hwlogsmodel.LogUtil.a("Step_AliveStatusRecord", "isValidTime mTimestamp = ", Long.valueOf(j), " mElapsedRealTime = ", Long.valueOf(this.d), " data not can use,return unset!!!");
        return true;
    }
}
