package defpackage;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import androidx.core.app.NotificationCompat;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.phoneprocess.service.HandleIntentService;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.time.Instant;
import java.time.ZoneId;
import java.time.zone.ZoneOffsetTransition;
import java.util.TimeZone;

/* loaded from: classes5.dex */
public class kkn {

    /* renamed from: a, reason: collision with root package name */
    private static long f14433a;

    public static long c() {
        long j;
        LogUtil.a("UpdateTimeFreezeUtils", "getChange start.");
        ZoneId of = ZoneId.of(TimeZone.getDefault().getID());
        ZoneOffsetTransition nextTransition = of.getRules().nextTransition(Instant.now());
        LogUtil.a("UpdateTimeFreezeUtils", "madridZoneId: ", of);
        if (nextTransition != null) {
            j = nextTransition.toEpochSecond();
        } else {
            LogUtil.h("UpdateTimeFreezeUtils", "nextTransition is null.");
            j = 0;
        }
        long currentTimeMillis = j - (System.currentTimeMillis() / 1000);
        LogUtil.a("UpdateTimeFreezeUtils", "TIME change seconds: ", Long.valueOf(currentTimeMillis));
        return currentTimeMillis;
    }

    public static void b(long j) {
        Context context = BaseApplication.getContext();
        Intent intent = new Intent(context, (Class<?>) HandleIntentService.class);
        intent.putExtra("type_time_change", 1000);
        long currentTimeMillis = System.currentTimeMillis() + (j * 1000) + 300000;
        if (Math.abs(currentTimeMillis - f14433a) < 120000) {
            LogUtil.h("UpdateTimeFreezeUtils", "AlarmManager cannot be triggered, the interval is less than 2 minutes.");
            return;
        }
        PendingIntent service = PendingIntent.getService(context, 1000, intent, 201326592);
        Object systemService = context.getSystemService(NotificationCompat.CATEGORY_ALARM);
        if (!(systemService instanceof AlarmManager)) {
            LogUtil.h("UpdateTimeFreezeUtils", "alarmManagerObject is not AlarmManager");
            return;
        }
        try {
            ((AlarmManager) systemService).setExactAndAllowWhileIdle(0, currentTimeMillis, service);
        } catch (SecurityException e) {
            ReleaseLogUtil.d("DEVMGR_UpdateTimeFreezeUtils", "Summer Camp enableAlarmManger exception ", e.getMessage());
        }
        f14433a = currentTimeMillis;
    }
}
