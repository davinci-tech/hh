package com.huawei.health.manager.migration;

import android.content.Context;
import android.content.SharedPreferences;
import com.huawei.health.icommon.BaseSyncManager;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.up.model.UserInfomation;
import health.compact.a.SharedPerferenceUtils;

/* loaded from: classes.dex */
public class MigrationAtheneToAtheneTwo implements InterfaceMigration {
    @Override // com.huawei.health.manager.migration.InterfaceMigration
    public boolean filter(String str) {
        return "Athene".equals(str);
    }

    @Override // com.huawei.health.manager.migration.InterfaceMigration
    public void migration(Context context) {
        if (context == null) {
            LogUtil.a("Step_MigrationAtheneToAtheneTwo", "context is null");
            return;
        }
        a(context);
        e(context);
        SharedPerferenceUtils.b(context, "Athene2");
    }

    private void e(final Context context) {
        final String b = b(context);
        if (a(b)) {
            return;
        }
        BaseSyncManager.getExecutor().execute(new Runnable() { // from class: com.huawei.health.manager.migration.MigrationAtheneToAtheneTwo.1
            @Override // java.lang.Runnable
            public void run() {
                MigrationAtheneToAtheneTwo.this.b(context, Boolean.parseBoolean(b));
            }
        });
    }

    private void a(final Context context) {
        final String d = d(context);
        if (a(d)) {
            return;
        }
        BaseSyncManager.getExecutor().execute(new Runnable() { // from class: com.huawei.health.manager.migration.MigrationAtheneToAtheneTwo.2
            @Override // java.lang.Runnable
            public void run() {
                MigrationAtheneToAtheneTwo.this.d(context, Boolean.parseBoolean(d));
            }
        });
    }

    private boolean a(String str) {
        return UserInfomation.BIRTHDAY_UNSETED.equals(str) || "".equals(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(Context context, boolean z) {
        LogUtil.a("Step_MigrationAtheneToAtheneTwo", "writeToDataBaseStepsNotificationStatus:", Boolean.valueOf(z));
        HiUserPreference hiUserPreference = new HiUserPreference();
        hiUserPreference.setKey("custom.steps_notification_status");
        hiUserPreference.setValue(Boolean.toString(z));
        HiHealthManager.d(context).setUserPreference(hiUserPreference);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Context context, boolean z) {
        LogUtil.a("Step_MigrationAtheneToAtheneTwo", "writeToDataBaseGoalNotificationStatus:", Boolean.valueOf(z));
        HiUserPreference hiUserPreference = new HiUserPreference();
        hiUserPreference.setKey("custom.goal_notification_status");
        hiUserPreference.setValue(Boolean.toString(z));
        HiHealthManager.d(context).setUserPreference(hiUserPreference);
    }

    private static String d(Context context) {
        LogUtil.a("Step_MigrationAtheneToAtheneTwo", "getStepsNotificationShowStatus");
        SharedPreferences sharedPreferences = context.getSharedPreferences(SharedPerferenceUtils.d() + "daemonService_perference", 0);
        if (sharedPreferences == null) {
            return UserInfomation.BIRTHDAY_UNSETED;
        }
        String string = sharedPreferences.getString("steps_notification_status", UserInfomation.BIRTHDAY_UNSETED);
        LogUtil.a("Step_MigrationAtheneToAtheneTwo", " getStepsNotificationShowStatus= ", string);
        return string;
    }

    private static String b(Context context) {
        LogUtil.a("Step_MigrationAtheneToAtheneTwo", "getGoalNotificationShowStatus");
        SharedPreferences sharedPreferences = context.getSharedPreferences(SharedPerferenceUtils.d() + "daemonService_perference", 0);
        if (sharedPreferences == null) {
            return UserInfomation.BIRTHDAY_UNSETED;
        }
        String string = sharedPreferences.getString("goal_notification_status", UserInfomation.BIRTHDAY_UNSETED);
        LogUtil.a("Step_MigrationAtheneToAtheneTwo", " getGoalNotificationShowStatus= ", string);
        return string;
    }
}
