package health.compact.a;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.base.BaseNotification;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import java.lang.reflect.InvocationTargetException;

/* loaded from: classes.dex */
public final class NotificationUtils extends BaseNotification {
    private static volatile NotificationUtils b;

    private NotificationUtils() {
    }

    public static NotificationUtils e() {
        if (b == null) {
            synchronized (NotificationUtils.class) {
                if (b == null) {
                    NotificationUtils notificationUtils = new NotificationUtils();
                    notificationUtils.a(null, e("IDS_hw_app_name", "IDS_app_name_health"), 3);
                    b = notificationUtils;
                }
            }
        }
        return b;
    }

    @Override // com.huawei.haf.common.base.BaseNotification
    public void bFy_(NotificationChannel notificationChannel) {
        super.bFy_(notificationChannel);
        bFw_(notificationChannel, 3);
    }

    public static void bFw_(NotificationChannel notificationChannel, int i) {
        if (!EnvironmentInfo.j() || notificationChannel == null) {
            return;
        }
        try {
            ReflectionUtils.b(NotificationChannel.class, "setChannelType", Integer.TYPE).invoke(notificationChannel, Integer.valueOf(i));
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            LogUtil.a("TimeEat_NotificationUtils", "setChannelType fail.", LogUtil.a(e));
        }
    }

    public static PendingIntent bFv_(Context context) {
        Intent intent;
        if (context == null) {
            context = BaseApplication.e();
        }
        try {
            intent = bFu_(context);
            try {
                return PendingIntent.getActivity(context, 0, intent, 201326592);
            } catch (IllegalStateException e) {
                e = e;
                LogUtil.e("TimeEat_NotificationUtils", "getPendingIntent() Exception:", e.getMessage());
                return PendingIntent.getActivity(context, 0, intent, 201326592);
            }
        } catch (IllegalStateException e2) {
            e = e2;
            intent = null;
        }
    }

    private static Intent bFu_(Context context) {
        Intent intent = new Intent();
        try {
            intent.setClassName(context.getPackageName(), context.getPackageManager().getLaunchIntentForPackage(context.getPackageName()).getComponent().getClassName());
            intent.setAction("android.intent.action.MAIN");
            intent.addCategory("android.intent.category.LAUNCHER");
            intent.setFlags(268435456);
        } catch (IllegalStateException e) {
            LogUtil.e("TimeEat_NotificationUtils", "getHealthAPPIntent() exception：", e.getMessage());
        }
        return intent;
    }

    @Override // com.huawei.haf.common.base.BaseNotification
    public void bFx_(NotificationManager notificationManager) {
        notificationManager.deleteNotificationChannel("servertoken");
        notificationManager.deleteNotificationChannel(ParsedFieldTag.GOAL);
        notificationManager.deleteNotificationChannel("step");
        notificationManager.deleteNotificationChannel("sportdata");
        notificationManager.deleteNotificationChannel("device_data_receiver");
        notificationManager.deleteNotificationChannel("health_device_data_receiver_id");
    }
}
