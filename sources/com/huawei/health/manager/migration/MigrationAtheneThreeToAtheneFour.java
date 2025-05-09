package com.huawei.health.manager.migration;

import android.content.Context;
import android.content.SharedPreferences;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.up.model.UserInfomation;
import health.compact.a.SharedPerferenceUtils;

/* loaded from: classes.dex */
public class MigrationAtheneThreeToAtheneFour implements InterfaceMigration {
    @Override // com.huawei.health.manager.migration.InterfaceMigration
    public boolean filter(String str) {
        return "Athene3".equals(str);
    }

    @Override // com.huawei.health.manager.migration.InterfaceMigration
    public void migration(Context context) {
        if (context == null) {
            LogUtil.a("MigrationAtheneThreeToAtheneFour", "context is null");
            return;
        }
        e(context, d(context));
        c(context, c(context));
        SharedPerferenceUtils.b(context, "Athene4");
    }

    private static String d(Context context) {
        LogUtil.a("MigrationAtheneThreeToAtheneFour", "getStepsNotificationShowStatus");
        SharedPreferences sharedPreferences = context.getSharedPreferences(SharedPerferenceUtils.d() + "daemonService_perference", 0);
        if (sharedPreferences == null) {
            return UserInfomation.BIRTHDAY_UNSETED;
        }
        String string = sharedPreferences.getString("steps_notification_status", UserInfomation.BIRTHDAY_UNSETED);
        LogUtil.a("MigrationAtheneThreeToAtheneFour", " getStepsNotificationShowStatus= ", string);
        return string;
    }

    private static String c(Context context) {
        LogUtil.a("MigrationAtheneThreeToAtheneFour", "getGoalNotificationShowStatus");
        SharedPreferences sharedPreferences = context.getSharedPreferences(SharedPerferenceUtils.d() + "daemonService_perference", 0);
        if (sharedPreferences == null) {
            return UserInfomation.BIRTHDAY_UNSETED;
        }
        String string = sharedPreferences.getString("goal_notification_status", UserInfomation.BIRTHDAY_UNSETED);
        LogUtil.a("MigrationAtheneThreeToAtheneFour", " getGoalNotificationShowStatus= ", string);
        return string;
    }

    private static void e(Context context, String str) {
        LogUtil.a("MigrationAtheneThreeToAtheneFour", "setStepsNotificationShowStatusCommon=", str);
        SharedPreferences.Editor edit = context.getSharedPreferences("daemonService_perference_newFile", 0).edit();
        if (edit != null) {
            edit.putString("steps_notification_status", str);
            edit.commit();
            LogUtil.a("MigrationAtheneThreeToAtheneFour", "setStepsNotificationShowStatus success");
        }
    }

    private static void c(Context context, String str) {
        LogUtil.a("MigrationAtheneThreeToAtheneFour", "setGoalNotificationShowStatusCommon=", str);
        SharedPreferences.Editor edit = context.getSharedPreferences("daemonService_perference_newFile", 0).edit();
        if (edit != null) {
            edit.putString("goal_notification_status", str);
            edit.commit();
            LogUtil.a("MigrationAtheneThreeToAtheneFour", "setGoalNotificationShowStatus success");
        }
    }
}
