package defpackage;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.text.TextUtils;
import androidx.core.app.NotificationCompat;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.operation.OpAnalyticsConstants;
import com.huawei.operation.OpAnalyticsUtil;
import health.compact.a.ReleaseLogUtil;
import java.util.Calendar;
import java.util.TimeZone;

/* loaded from: classes2.dex */
public class sqa {
    private static AlarmManager eko_() {
        Object systemService = BaseApplication.e().getSystemService(NotificationCompat.CATEGORY_ALARM);
        if (systemService instanceof AlarmManager) {
            return (AlarmManager) systemService;
        }
        ReleaseLogUtil.a("R_AlarmManagerUtil", "getAlarmManager object ", systemService);
        return null;
    }

    private static PendingIntent ekt_(long j, int i, Intent intent, int i2) {
        if (intent == null) {
            ReleaseLogUtil.a("R_AlarmManagerUtil", "getPendingIntent intent is null flags ", Integer.valueOf(i2), " requestCode ", Integer.valueOf(i), " timeMillis ", Long.valueOf(j));
            return null;
        }
        intent.setPackage(BaseApplication.d());
        intent.putExtra("timeZoneId", TimeZone.getDefault().getID());
        intent.putExtra("timeMillis", j);
        return PendingIntent.getService(BaseApplication.e(), i, intent, i2);
    }

    private static PendingIntent eks_(long j, int i, Intent intent, int i2) {
        if (intent == null) {
            ReleaseLogUtil.a("R_AlarmManagerUtil", "getPendingIntent intent is null flags ", Integer.valueOf(i2), " requestCode ", Integer.valueOf(i), " timeMillis ", Long.valueOf(j));
            return null;
        }
        intent.setPackage(BaseApplication.d());
        intent.putExtra("timeZoneId", TimeZone.getDefault().getID());
        intent.putExtra("timeMillis", j);
        return PendingIntent.getBroadcast(BaseApplication.e(), i, intent, i2);
    }

    public static void ekn_(int i, Intent intent, int i2) {
        PendingIntent broadcast = PendingIntent.getBroadcast(BaseApplication.e(), i, intent, i2);
        if (broadcast == null) {
            ReleaseLogUtil.a("R_AlarmManagerUtil", "cancel pendingIntent is null flags ", Integer.valueOf(i2), " requestCode ", Integer.valueOf(i), " intent ", intent);
            return;
        }
        AlarmManager eko_ = eko_();
        if (eko_ == null) {
            ReleaseLogUtil.a("R_AlarmManagerUtil", "cancel alarmManager is null flags ", Integer.valueOf(i2), " requestCode ", Integer.valueOf(i), " intent ", intent, " pendingIntent ", broadcast);
        } else {
            broadcast.cancel();
            eko_.cancel(broadcast);
        }
    }

    public static void ekx_(int i, Intent intent, int i2, int i3, long j) {
        PendingIntent eks_ = eks_(j, i, intent, i2);
        if (eks_ == null) {
            ReleaseLogUtil.a("R_AlarmManagerUtil", "set pendingIntent is null requestCode ", Integer.valueOf(i), " flags ", Integer.valueOf(i2), " type ", Integer.valueOf(i3), " timeMillis ", Long.valueOf(j), " intent ", intent);
            return;
        }
        AlarmManager eko_ = eko_();
        if (eko_ == null) {
            ReleaseLogUtil.a("R_AlarmManagerUtil", "set alarmManager is null requestCode ", Integer.valueOf(i), " flags ", Integer.valueOf(i2), " type ", Integer.valueOf(i3), " timeMillis ", Long.valueOf(j), " intent ", intent, " pendingIntent ", eks_);
        } else {
            eko_.set(i3, j, eks_);
        }
    }

    public static void eky_(int i, Intent intent, int i2, int i3, long j) {
        PendingIntent eks_ = eks_(j, i, intent, i2);
        if (eks_ == null) {
            ReleaseLogUtil.a("R_AlarmManagerUtil", "setExact pendingIntent is null requestCode ", Integer.valueOf(i), " flags ", Integer.valueOf(i2), " type ", Integer.valueOf(i3), " timeMillis ", Long.valueOf(j), " intent ", intent);
            return;
        }
        AlarmManager eko_ = eko_();
        if (eko_ == null) {
            ReleaseLogUtil.a("R_AlarmManagerUtil", "setExact alarmManager is null requestCode ", Integer.valueOf(i), " flags ", Integer.valueOf(i2), " type ", Integer.valueOf(i3), " timeMillis ", Long.valueOf(j), " intent ", intent, " pendingIntent ", eks_);
        } else {
            ekB_(eko_, i3, j, eks_, "");
        }
    }

    public static void ekB_(AlarmManager alarmManager, int i, long j, PendingIntent pendingIntent, String str) {
        if (alarmManager == null) {
            ReleaseLogUtil.a("R_AlarmManagerUtil", "setExactForAlarmPermission alarmManager is null");
            return;
        }
        try {
            if (PermissionUtil.b()) {
                alarmManager.setExact(i, j, pendingIntent);
            } else {
                alarmManager.set(i, j, pendingIntent);
            }
        } catch (SecurityException e) {
            String str2 = str + " setExactForAlarmPermission exception " + e.getMessage();
            ReleaseLogUtil.a("R_AlarmManagerUtil", str2);
            OpAnalyticsUtil.getInstance().setProbabilityProblemEvent(OpAnalyticsConstants.OP_ALARM_PERMISSION_KEY, str2);
        }
    }

    public static void ekA_(AlarmManager alarmManager, int i, long j, PendingIntent pendingIntent, String str) {
        if (alarmManager == null) {
            ReleaseLogUtil.a("R_AlarmManagerUtil", "setExactForAlarmPermission alarmManager is null");
            return;
        }
        try {
            if (PermissionUtil.b()) {
                alarmManager.setExactAndAllowWhileIdle(i, j, pendingIntent);
            } else {
                alarmManager.setAndAllowWhileIdle(i, j, pendingIntent);
            }
        } catch (SecurityException e) {
            String str2 = str + " setExactAndAllowWhileIdle exception " + e.getMessage();
            ReleaseLogUtil.b("R_AlarmManagerUtil", str2);
            OpAnalyticsUtil.getInstance().setProbabilityProblemEvent(OpAnalyticsConstants.OP_ALARM_PERMISSION_KEY, str2);
        }
    }

    public static void ekz_(int i, Intent intent, int i2, int i3, long j) {
        PendingIntent eks_ = eks_(j, i, intent, i2);
        if (eks_ == null) {
            ReleaseLogUtil.a("R_AlarmManagerUtil", "setExactAndAllowWhileIdle pendingIntent is null requestCode ", Integer.valueOf(i), " flags ", Integer.valueOf(i2), " type ", Integer.valueOf(i3), " timeMillis ", Long.valueOf(j), " intent ", intent);
            return;
        }
        AlarmManager eko_ = eko_();
        if (eko_ == null) {
            ReleaseLogUtil.a("R_AlarmManagerUtil", "setExactAndAllowWhileIdle alarmManager is null requestCode ", Integer.valueOf(i), " flags ", Integer.valueOf(i2), " type ", Integer.valueOf(i3), " timeMillis ", Long.valueOf(j), " intent ", intent, " pendingIntent ", eks_);
        } else {
            ekA_(eko_, i3, j, eks_, "");
        }
    }

    public static void ekC_(int i, Intent intent, int i2, int i3, long j, long j2) {
        PendingIntent eks_ = eks_(j, i, intent, i2);
        if (eks_ == null) {
            ReleaseLogUtil.a("R_AlarmManagerUtil", "setRepeating pendingIntent is null requestCode ", Integer.valueOf(i), " flags ", Integer.valueOf(i2), " type ", Integer.valueOf(i3), " timeMillis ", Long.valueOf(j), " intervalTimeMillis ", Long.valueOf(j2), " intent ", intent);
            return;
        }
        AlarmManager eko_ = eko_();
        if (eko_ == null) {
            ReleaseLogUtil.a("R_AlarmManagerUtil", "setRepeating alarmManager is null requestCode ", Integer.valueOf(i), " flags ", Integer.valueOf(i2), " type ", Integer.valueOf(i3), " timeMillis ", Long.valueOf(j), " intervalTimeMillis ", Long.valueOf(j2), " intent ", intent, " pendingIntent ", eks_);
        } else {
            eko_.setRepeating(i3, j, j2, eks_);
        }
    }

    public static void ekD_(int i, Intent intent, int i2, int i3, long j, long j2) {
        PendingIntent ekt_ = ekt_(j, i, intent, i2);
        if (ekt_ == null) {
            ReleaseLogUtil.a("R_AlarmManagerUtil", "setRepeatingForService pendingIntent is null requestCode ", Integer.valueOf(i), " flags ", Integer.valueOf(i2), " type ", Integer.valueOf(i3), " timeMillis ", Long.valueOf(j), " intervalTimeMillis ", Long.valueOf(j2), " intent ", intent);
            return;
        }
        AlarmManager eko_ = eko_();
        if (eko_ == null) {
            ReleaseLogUtil.a("R_AlarmManagerUtil", "setRepeatingForService alarmManager is null requestCode ", Integer.valueOf(i), " flags ", Integer.valueOf(i2), " type ", Integer.valueOf(i3), " timeMillis ", Long.valueOf(j), " intervalTimeMillis ", Long.valueOf(j2), " intent ", intent, " pendingIntent ", ekt_);
        } else {
            eko_.setRepeating(i3, j, j2, ekt_);
        }
    }

    public static TimeZone ekv_(Intent intent) {
        if (intent == null || !intent.hasExtra("timeZoneId")) {
            ReleaseLogUtil.a("R_AlarmManagerUtil", "getTimeZoneForIntent intent ", intent);
            return null;
        }
        String stringExtra = intent.getStringExtra("timeZoneId");
        if (TextUtils.isEmpty(stringExtra)) {
            ReleaseLogUtil.a("R_AlarmManagerUtil", "getTimeZoneForIntent timeZoneId ", stringExtra, " intent ", intent);
            return null;
        }
        return TimeZone.getTimeZone(stringExtra);
    }

    public static int c(TimeZone timeZone) {
        int offset;
        long currentTimeMillis = System.currentTimeMillis();
        if (timeZone == null) {
            ReleaseLogUtil.a("R_AlarmManagerUtil", "getOffsetDifferent sourceTimeZone is null");
            offset = 0;
        } else {
            offset = timeZone.getOffset(currentTimeMillis);
        }
        return TimeZone.getDefault().getOffset(currentTimeMillis) - offset;
    }

    public static int ekr_(Intent intent) {
        TimeZone ekv_ = ekv_(intent);
        if (ekv_ == null) {
            ReleaseLogUtil.a("R_AlarmManagerUtil", "getOffsetDifferent timeZone is null intent ", intent);
            return 0;
        }
        return c(ekv_);
    }

    public static boolean ekw_(Intent intent) {
        return ekr_(intent) != 0;
    }

    public static long eku_(Intent intent, int i, int i2) {
        Calendar ekp_ = ekp_(intent);
        if (ekp_ == null) {
            ReleaseLogUtil.a("R_AlarmManagerUtil", "getTimeMillisForResetAlarmManager calendar is null");
            return 0L;
        }
        long timeInMillis = ekp_.getTimeInMillis();
        if (timeInMillis > System.currentTimeMillis()) {
            return timeInMillis;
        }
        ekp_.add(i, i2);
        return ekp_.getTimeInMillis();
    }

    public static Calendar b(TimeZone timeZone, long j) {
        if (timeZone == null || j <= 0) {
            ReleaseLogUtil.a("R_AlarmManagerUtil", "getConvertCalendar timeZone ", timeZone, " timeMillis ", Long.valueOf(j));
            return null;
        }
        Calendar calendar = Calendar.getInstance(timeZone);
        calendar.setTimeInMillis(j);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(11, calendar.get(11));
        calendar2.set(12, calendar.get(12));
        calendar2.set(13, 0);
        calendar2.set(14, 0);
        return calendar2;
    }

    public static Calendar ekp_(Intent intent) {
        if (intent == null || !intent.hasExtra("timeZoneId") || !intent.hasExtra("timeMillis")) {
            ReleaseLogUtil.a("R_AlarmManagerUtil", "getConvertCalendar intent ", intent);
            return null;
        }
        String stringExtra = intent.getStringExtra("timeZoneId");
        if (!TextUtils.isEmpty(stringExtra)) {
            return b(TimeZone.getTimeZone(stringExtra), intent.getLongExtra("timeMillis", 0L));
        }
        ReleaseLogUtil.a("R_AlarmManagerUtil", "getConvertCalendar sourceTimeZoneId ", stringExtra, " intent ", intent);
        return null;
    }

    public static long ekq_(Intent intent) {
        long timeInMillis;
        Calendar ekp_ = ekp_(intent);
        if (ekp_ == null) {
            ReleaseLogUtil.a("R_AlarmManagerUtil", "getConvertTimeMillis calendar is null intent ", intent);
            timeInMillis = 0;
        } else {
            timeInMillis = ekp_.getTimeInMillis();
        }
        if (timeInMillis > 0) {
            return timeInMillis;
        }
        long longExtra = intent.getLongExtra("timeMillis", 0L);
        ReleaseLogUtil.a("R_AlarmManagerUtil", "getConvertTimeMillis timeMillis ", Long.valueOf(longExtra), " calendar ", ekp_, " intent ", intent);
        return longExtra;
    }
}
