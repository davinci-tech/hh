package com.huawei.health.manager.migration;

import android.content.Context;
import android.content.SharedPreferences;
import com.huawei.health.connectivity.standstepcounter.StandStepCounterManager;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.SharedPerferenceUtils;

/* loaded from: classes.dex */
public class MigrationAthenePreviewToAthene implements InterfaceMigration {
    @Override // com.huawei.health.manager.migration.InterfaceMigration
    public boolean filter(String str) {
        return "v2_preview".equals(str);
    }

    @Override // com.huawei.health.manager.migration.InterfaceMigration
    public void migration(Context context) {
        if (context == null) {
            LogUtil.a("MigrationAthenePreviewToAthene", "context is null");
            return;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences(SharedPerferenceUtils.d() + "daemonService_perference", 0);
        if (sharedPreferences == null) {
            LogUtil.a("MigrationAthenePreviewToAthene", "sharedPreferences is null");
            return;
        }
        boolean z = sharedPreferences.getBoolean("notification_status", !CommonUtil.aw());
        sharedPreferences.edit().remove("notification_status");
        if (new StandStepCounterManager(context).e(1) != null) {
            ala_(sharedPreferences, z);
            akZ_(sharedPreferences, !z);
        }
        SharedPerferenceUtils.b(context, "Athene");
    }

    private static void ala_(SharedPreferences sharedPreferences, boolean z) {
        LogUtil.a("MigrationAthenePreviewToAthene", "setStepsNotificationShowStatus=", Boolean.valueOf(z));
        SharedPreferences.Editor edit = sharedPreferences.edit();
        String bool = Boolean.toString(z);
        if (edit != null) {
            edit.putString("steps_notification_status", bool);
            edit.commit();
            LogUtil.a("MigrationAthenePreviewToAthene", "setStepsNotificationShowStatus success");
        }
    }

    private static void akZ_(SharedPreferences sharedPreferences, boolean z) {
        LogUtil.a("MigrationAthenePreviewToAthene", "setGoalNotificationShowStatus=", Boolean.valueOf(z));
        SharedPreferences.Editor edit = sharedPreferences.edit();
        String bool = Boolean.toString(z);
        if (edit != null) {
            edit.putString("goal_notification_status", bool);
            edit.commit();
            LogUtil.a("MigrationAthenePreviewToAthene", "setGoalNotificationShowStatus success");
        }
    }
}
