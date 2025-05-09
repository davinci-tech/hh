package com.huawei.health.manager.migration;

import android.content.Context;
import android.content.SharedPreferences;
import com.huawei.health.connectivity.standstepcounter.StandStepCounterManager;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.SharedPerferenceUtils;

/* loaded from: classes.dex */
public class MigrationVersionOneToAthene implements InterfaceMigration {
    @Override // com.huawei.health.manager.migration.InterfaceMigration
    public boolean filter(String str) {
        return "v1".equals(str);
    }

    @Override // com.huawei.health.manager.migration.InterfaceMigration
    public void migration(Context context) {
        if (context == null) {
            LogUtil.a("MigrationVersionOneToAthene", "context is null");
            return;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("plugin_device_preferences_settings", 0);
        sharedPreferences.edit().remove("plugin_device_phonecounter_standardstep").commit();
        sharedPreferences.edit().remove("plugin_device_last_time_and_this_time").commit();
        boolean z = sharedPreferences.getBoolean("KEY_PHONE_NOTIFICATION_COUNTER_ENABLED", true);
        if (new StandStepCounterManager(context).e(1) != null) {
            b(context, z);
            d(context, !z);
        }
        SharedPerferenceUtils.b(context, "Athene");
    }

    private static void b(Context context, boolean z) {
        LogUtil.a("MigrationVersionOneToAthene", "setStepsNotificationShowStatus=", Boolean.valueOf(z));
        SharedPreferences sharedPreferences = context.getSharedPreferences(SharedPerferenceUtils.d() + "daemonService_perference", 0);
        if (sharedPreferences == null) {
            LogUtil.a("MigrationVersionOneToAthene", "sharedPreferences is null");
            return;
        }
        SharedPreferences.Editor edit = sharedPreferences.edit();
        String bool = Boolean.toString(z);
        if (edit != null) {
            edit.putString("steps_notification_status", bool);
            edit.commit();
            LogUtil.a("MigrationVersionOneToAthene", "setStepsNotificationShowStatus success");
        }
    }

    private static void d(Context context, boolean z) {
        LogUtil.a("MigrationVersionOneToAthene", "setGoalNotificationShowStatus=", Boolean.valueOf(z));
        SharedPreferences sharedPreferences = context.getSharedPreferences(SharedPerferenceUtils.d() + "daemonService_perference", 0);
        if (sharedPreferences == null) {
            LogUtil.a("MigrationVersionOneToAthene", "sharedPreferences is null");
            return;
        }
        SharedPreferences.Editor edit = sharedPreferences.edit();
        String bool = Boolean.toString(z);
        if (edit != null) {
            edit.putString("goal_notification_status", bool);
            edit.commit();
            LogUtil.a("MigrationVersionOneToAthene", "setGoalNotificationShowStatus success");
        }
    }
}
