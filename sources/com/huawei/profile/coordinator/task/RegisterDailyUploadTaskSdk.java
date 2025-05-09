package com.huawei.profile.coordinator.task;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import androidx.core.app.NotificationCompat;
import com.huawei.profile.coordinator.RequestUtilsSdk;
import com.huawei.profile.utils.ClassUtil;
import com.huawei.profile.utils.logger.DsLog;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Locale;

/* loaded from: classes6.dex */
public class RegisterDailyUploadTaskSdk extends ProfileTask {
    private static final int ALARM_REGISTER_CODE = 0;
    private static final int AMOUNT_MILL_SECONDS_OF_A_DAY = 86400000;
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final int GMT_BEIJING = 28800000;
    private static final int MAX_INTERVAL_TIME = 300000;
    private static final String TAG = "RegisterDailyUploadTaskSdk";
    private static boolean sIsRegistered = false;
    private static long sLastReceiveTime;
    private Context context;
    private static final Object REGISTER_LOCK = new Object();
    private static final Object LAST_RECEIVE_TIME_LOCK = new Object();

    @Override // com.huawei.profile.coordinator.task.ProfileTask
    public void setContext(Context context) {
        this.context = context;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (!isNeedRegister()) {
            DsLog.dt(TAG, "Don't need to register.", new Object[0]);
            return;
        }
        AlarmManager alarmManager = (AlarmManager) ClassUtil.cast(this.context.getSystemService(NotificationCompat.CATEGORY_ALARM), AlarmManager.class);
        if (alarmManager == null) {
            DsLog.et(TAG, "Failed to get Alarm Manager.", new Object[0]);
            return;
        }
        if (alarmManager.getNextAlarmClock() != null) {
            DsLog.dt(TAG, "already register an alarm, not to register another one.", new Object[0]);
            return;
        }
        long generateTriggerTime = generateTriggerTime();
        Intent intent = new Intent(this.context, (Class<?>) ProfileAlarmReceiverSdk.class);
        intent.setClass(this.context, ProfileAlarmReceiverSdk.class);
        intent.addFlags(32);
        alarmManager.setExactAndAllowWhileIdle(0, generateTriggerTime, PendingIntent.getBroadcast(this.context, 0, intent, 268435456));
        DsLog.dt(TAG, "register an AlarmManager, triggered at = " + getTriggerTime(generateTriggerTime), new Object[0]);
        setIsRegistered(true);
    }

    private String getTriggerTime(long j) {
        return new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH).format(Long.valueOf(j));
    }

    private boolean isNeedRegister() {
        Context context = this.context;
        if (context == null) {
            DsLog.et(TAG, "Failed to get Alarm Manager, reason is context is null.", new Object[0]);
            return false;
        }
        if (!RequestUtilsSdk.isNeedUpload(context)) {
            DsLog.dt(TAG, "There is no profile records to be upload.", new Object[0]);
            return false;
        }
        if (!getIsRegistered()) {
            return true;
        }
        DsLog.dt(TAG, "Already registered an AlarmManager.", new Object[0]);
        return false;
    }

    private long generateTriggerTime() {
        long currentTimeMillis = System.currentTimeMillis();
        int nextInt = new SecureRandom().nextInt(300000);
        if (isTriggerMustTomorrow(currentTimeMillis)) {
            DsLog.dt(TAG, "today has received an alarm, arrange it tomorrow.", new Object[0]);
            currentTimeMillis = getTodayZeroPointTimestamps(currentTimeMillis) + 86400000;
        }
        return currentTimeMillis + nextInt;
    }

    private boolean isTriggerMustTomorrow(long j) {
        long lastReceiveTime = getLastReceiveTime();
        long todayZeroPointTimestamps = getTodayZeroPointTimestamps(j);
        DsLog.dt(TAG, "last time is " + getLastReceiveTime() + " today's 00:00 is " + getTodayZeroPointTimestamps(j), new Object[0]);
        return j - lastReceiveTime < j - todayZeroPointTimestamps;
    }

    private long getTodayZeroPointTimestamps(long j) {
        return j - ((28800000 + j) % 86400000);
    }

    private static boolean getIsRegistered() {
        boolean z;
        synchronized (REGISTER_LOCK) {
            z = sIsRegistered;
        }
        return z;
    }

    public static void setIsRegistered(boolean z) {
        synchronized (REGISTER_LOCK) {
            sIsRegistered = z;
        }
    }

    private static long getLastReceiveTime() {
        long j;
        synchronized (LAST_RECEIVE_TIME_LOCK) {
            j = sLastReceiveTime;
        }
        return j;
    }

    public static void setLastReceiveTime(long j) {
        synchronized (LAST_RECEIVE_TIME_LOCK) {
            sLastReceiveTime = j;
        }
    }

    @Override // com.huawei.profile.coordinator.task.ProfileTask
    public String getName() {
        return TAG;
    }
}
