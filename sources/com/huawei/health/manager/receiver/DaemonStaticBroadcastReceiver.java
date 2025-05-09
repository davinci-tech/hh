package com.huawei.health.manager.receiver;

import android.app.ActivityManager;
import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import androidx.core.content.ContextCompat;
import com.huawei.health.R;
import com.huawei.health.manager.DaemonService;
import com.huawei.health.manager.PreDaemonService;
import com.huawei.health.manager.util.Consts;
import com.huawei.health.manager.util.DelayStartHelper;
import defpackage.jdh;
import health.compact.a.AuthorizationUtils;
import health.compact.a.DaemonServiceSpUtils;
import health.compact.a.DeviceUtil;
import health.compact.a.EmuiBuild;
import health.compact.a.EnvironmentInfo;
import health.compact.a.HarmonyBuild;
import health.compact.a.LogUtil;
import health.compact.a.ProcessAliveMonitor;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.SharedPerferenceUtils;
import health.compact.a.StepCounterSupportUtils;
import java.util.List;

/* loaded from: classes.dex */
public class DaemonStaticBroadcastReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Intent intent2;
        if (intent == null || context == null) {
            return;
        }
        String action = intent.getAction();
        ReleaseLogUtil.b("Step_StaticReceiver", "onReceive action: ", action);
        if ("android.intent.action.BOOT_COMPLETED".equals(action)) {
            SharedPerferenceUtils.c(context, 0L);
            if (SharedPerferenceUtils.ag(context)) {
                ProcessAliveMonitor.a(context).b();
                intent2 = new Intent(context, (Class<?>) DaemonService.class);
            } else {
                intent2 = new Intent(context, (Class<?>) PreDaemonService.class);
            }
            intent2.setPackage(context.getPackageName());
            try {
                context.startService(intent2);
                b(context);
                c(context);
                return;
            } catch (IllegalStateException | SecurityException e) {
                LogUtil.a("Step_StaticReceiver", "ACTION_BOOT_COMPLETED IllegalStateException", e.getMessage());
                new DelayStartHelper(context).alx_(intent2);
                return;
            }
        }
        if ("android.intent.action.ACTION_SHUTDOWN".equals(action)) {
            SharedPerferenceUtils.c(context, System.currentTimeMillis());
            ProcessAliveMonitor a2 = ProcessAliveMonitor.a(context);
            if (a2 != null) {
                a2.c();
            } else {
                LogUtil.a("Step_StaticReceiver", "onReceive processAliveMonitor == null");
            }
            if (SharedPerferenceUtils.ag(context)) {
                Intent intent3 = new Intent(context, (Class<?>) DaemonService.class);
                intent3.setPackage(context.getPackageName());
                intent3.setAction("android.intent.action.ACTION_SHUTDOWN");
                try {
                    context.startService(intent3);
                    return;
                } catch (IllegalStateException | SecurityException e2) {
                    LogUtil.a("Step_StaticReceiver", "ACTION_SHUTDOWN IllegalStateException", e2.getMessage());
                    return;
                }
            }
            return;
        }
        if ("android.intent.action.MY_PACKAGE_REPLACED".equals(action)) {
            LogUtil.c("Step_StaticReceiver", "onReceive() update package.");
            j(context);
            f(context);
            DeviceUtil.fbV_(context, true, null);
            e(context);
            c(context);
            return;
        }
        LogUtil.a("Step_StaticReceiver", "onReceive() no action deal.");
    }

    private void e(Context context) {
        boolean a2 = AuthorizationUtils.a(context);
        ReleaseLogUtil.b("Step_StaticReceiver", "checkShowNotificationOnHarmony4 hasAuth:", Boolean.valueOf(a2));
        if (!a2 || Build.VERSION.SDK_INT <= 28 || EmuiBuild.d || HarmonyBuild.d || EmuiBuild.e()) {
            return;
        }
        d(context, R.string.IDS_step_remind_open_permission);
    }

    private void c(Context context) {
        boolean a2 = AuthorizationUtils.a(context);
        ReleaseLogUtil.b("Step_StaticReceiver", "checkShowNotificationOnHarmony4 hasAuth:", Boolean.valueOf(a2));
        if (a2) {
            if (EnvironmentInfo.j() && !DaemonServiceSpUtils.d(context)) {
                d(context, R.string.IDS_step_open_permission_harmony);
            } else if (DaemonServiceSpUtils.d(context) && StepCounterSupportUtils.b(context)) {
                d(context, R.string.IDS_step_open_permission_harmony);
            }
        }
    }

    private void d(Context context, int i) {
        boolean z = ContextCompat.checkSelfPermission(context, "android.permission.ACTIVITY_RECOGNITION") == 0;
        LogUtil.c("Step_StaticReceiver", " permission is granted ", Boolean.valueOf(z));
        jdh.c().a(20220112);
        if (z) {
            return;
        }
        Notification.Builder xf_ = jdh.c().xf_();
        xf_.setSmallIcon(R.drawable.healthlogo_ic_notification);
        xf_.setContentTitle(context.getString(i));
        xf_.setContentIntent(jdh.bFr_(context));
        xf_.setAutoCancel(true);
        xf_.setDefaults(1);
        xf_.setOngoing(false);
        xf_.setOnlyAlertOnce(true);
        jdh.c().xh_(20220112, xf_.build());
    }

    private void j(Context context) {
        Intent intent = new Intent(context, (Class<?>) DaemonService.class);
        intent.setPackage(context.getPackageName());
        try {
            context.startService(intent);
        } catch (IllegalStateException e) {
            LogUtil.a("Step_StaticReceiver", "ACTION_MY_PACKAGE_REPLACED IllegalStateException", e.getMessage());
        }
    }

    private void f(Context context) {
        if (!DeviceUtil.a()) {
            ReleaseLogUtil.b("Step_StaticReceiver", "handleUpdateModules no device.");
            return;
        }
        Intent intent = new Intent("com.huawei.health.track.broadcast");
        intent.setPackage(context.getPackageName());
        intent.putExtra("command_type", "MY_PACKAGE_REPLACED_UPDATE_MODULES");
        context.sendBroadcast(intent, Consts.f2803a);
        ReleaseLogUtil.b("Step_StaticReceiver", "handleUpdateModules goto.");
    }

    private void b(Context context) {
        LogUtil.c("Step_StaticReceiver", "check notification");
        if (d(context)) {
            LogUtil.c("Step_StaticReceiver", "check notification is enable ");
        } else {
            LogUtil.c("Step_StaticReceiver", "check notification is disabled ");
            a(context);
        }
    }

    public static void a(Context context) {
        if (context == null) {
            LogUtil.a("Step_StaticReceiver", "toggleNotificationListenerService null");
            return;
        }
        LogUtil.c("Step_StaticReceiver", "toggleNotificationListenerService enter");
        PackageManager packageManager = context.getPackageManager();
        packageManager.setComponentEnabledSetting(new ComponentName(context, "com.huawei.bone.ui.setting.NotificationPushListener"), 2, 1);
        packageManager.setComponentEnabledSetting(new ComponentName(context, "com.huawei.bone.ui.setting.NotificationPushListener"), 1, 1);
    }

    public static boolean d(Context context) {
        if (context == null) {
            LogUtil.a("Step_StaticReceiver", "isNotificationListenerRunning null");
            return false;
        }
        List<ActivityManager.RunningServiceInfo> runningServices = ((ActivityManager) context.getSystemService("activity")).getRunningServices(30);
        if (runningServices != null) {
            for (ActivityManager.RunningServiceInfo runningServiceInfo : runningServices) {
                LogUtil.c("Step_StaticReceiver", "serivce is :", runningServiceInfo.service.getClassName());
                if ("com.huawei.bone.ui.setting.NotificationPushListener".equals(runningServiceInfo.service.getClassName())) {
                    LogUtil.c("Step_StaticReceiver", "serivce is running!!!");
                    return true;
                }
            }
        }
        return false;
    }
}
