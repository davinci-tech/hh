package com.huawei.health.manager.util;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import androidx.core.app.NotificationCompat;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import defpackage.sqa;
import java.security.SecureRandom;

/* loaded from: classes.dex */
public class UploadStaticAlarmUtil {
    public static final long b = new SecureRandom().nextInt(270000);
    public static final long e = new SecureRandom().nextInt(Constants.HALF_HOUR);

    private UploadStaticAlarmUtil() {
    }

    public static void b(Context context, long j) {
        if (context == null) {
            LogUtil.a("Step_UploadStaticAlarmUtil", "setActionRestartSensorPreAlarm context is null");
            return;
        }
        LogUtil.a("Step_UploadStaticAlarmUtil", "setActionRestartSensorPreAlarm=", Long.valueOf(j));
        Intent intent = new Intent();
        intent.setAction("com.huawei.health.action.UPLOAD_STATICS");
        intent.setPackage(context.getPackageName());
        intent.putExtra("extra_user_intent", "extra_user_intent_pre_alarm");
        PendingIntent broadcast = PendingIntent.getBroadcast(context, 2018, intent, 201326592);
        Object systemService = context.getSystemService(NotificationCompat.CATEGORY_ALARM);
        AlarmManager alarmManager = systemService instanceof AlarmManager ? (AlarmManager) systemService : null;
        if (alarmManager != null) {
            alarmManager.cancel(broadcast);
            alarmManager.set(0, j, broadcast);
        }
    }

    public static void d(Context context, long j) {
        if (context == null) {
            LogUtil.a("Step_UploadStaticAlarmUtil", "setActionRestartSensorAlarm context is null");
            return;
        }
        LogUtil.a("Step_UploadStaticAlarmUtil", "setActionRestartSensorAlarm=", Long.valueOf(j));
        Intent intent = new Intent();
        intent.setAction("com.huawei.health.action.UPLOAD_STATICS");
        intent.setPackage(context.getPackageName());
        intent.putExtra("extra_user_intent", "extra_user_intent_alarm");
        PendingIntent broadcast = PendingIntent.getBroadcast(context, 2017, intent, 201326592);
        Object systemService = context.getSystemService(NotificationCompat.CATEGORY_ALARM);
        if (!(systemService instanceof AlarmManager)) {
            LogUtil.a("Step_UploadStaticAlarmUtil", "setActionRestartSensorAlarm object is not instanceof AlarmManager");
            return;
        }
        AlarmManager alarmManager = (AlarmManager) systemService;
        alarmManager.cancel(broadcast);
        sqa.ekB_(alarmManager, 0, j, broadcast, "Step_UploadStaticAlarmUtilsetActionRestartSensorAlarm");
    }
}
