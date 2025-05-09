package com.huawei.health.manager.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BadParcelableException;
import android.os.PowerManager;
import com.huawei.health.manager.DaemonService;
import com.huawei.health.manager.util.UploadStaticAlarmUtil;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.jdl;
import health.compact.a.LogAnonymous;

/* loaded from: classes.dex */
public class DaemonStaticLogicalReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String str;
        if (context == null || intent == null || intent.getAction() == null || !"com.huawei.health.action.UPLOAD_STATICS".equals(intent.getAction())) {
            return;
        }
        try {
            str = intent.getStringExtra("extra_user_intent");
        } catch (BadParcelableException e) {
            LogUtil.h("Step_DaemonStaticLogicalReceiver", e.getMessage());
            str = null;
        }
        if (str == null) {
            LogUtil.h("Step_DaemonStaticLogicalReceiver", "userIntent null,return");
        } else {
            b(context, str);
        }
    }

    private void b(Context context, String str) {
        if ("extra_user_intent_pre_alarm".equals(str)) {
            LogUtil.a("Step_DaemonStaticLogicalReceiver", "userIntent EXTRA_USER_INTENT_PRE_ALARM");
            a(context);
            UploadStaticAlarmUtil.b(context, jdl.y(System.currentTimeMillis()) + 84000000 + UploadStaticAlarmUtil.e);
            Intent intent = new Intent(context, (Class<?>) DaemonService.class);
            intent.setPackage(context.getPackageName());
            intent.setAction("com.huawei.health.action.UPLOAD_STATICS");
            intent.putExtra("extra_user_intent", "extra_user_intent_pre_alarm");
            try {
                context.startService(intent);
                return;
            } catch (IllegalStateException | SecurityException e) {
                LogUtil.h("Step_DaemonStaticLogicalReceiver", "dealRestartSensorMSG", LogAnonymous.b(e));
                return;
            }
        }
        if (str.equals("extra_user_intent_alarm")) {
            LogUtil.a("Step_DaemonStaticLogicalReceiver", "userIntent EXTRA_USER_INTENT_ALARM");
            a(context);
            UploadStaticAlarmUtil.d(context, jdl.y(System.currentTimeMillis()) + 86100000 + UploadStaticAlarmUtil.b);
            Intent intent2 = new Intent(context, (Class<?>) DaemonService.class);
            intent2.setPackage(context.getPackageName());
            intent2.setAction("com.huawei.health.action.UPLOAD_STATICS");
            intent2.putExtra("extra_user_intent", "extra_user_intent_alarm");
            try {
                context.startService(intent2);
                return;
            } catch (IllegalStateException | SecurityException e2) {
                LogUtil.h("Step_DaemonStaticLogicalReceiver", "extra_user_intent_alarm", LogAnonymous.b(e2));
                return;
            }
        }
        LogUtil.h("Step_DaemonStaticLogicalReceiver", "userIntent unknown");
    }

    private void a(Context context) {
        if (context == null) {
            LogUtil.h("Step_DaemonStaticLogicalReceiver", "acquireWakelock context is null.");
            return;
        }
        Object systemService = context.getSystemService("power");
        PowerManager powerManager = systemService instanceof PowerManager ? (PowerManager) systemService : null;
        PowerManager.WakeLock newWakeLock = powerManager != null ? powerManager.newWakeLock(1, "Step_DaemonStaticLogicalReceiver") : null;
        if (newWakeLock == null || newWakeLock.isHeld()) {
            return;
        }
        LogUtil.a("Step_DaemonStaticLogicalReceiver", "acquireWakelock");
        newWakeLock.acquire(3000L);
    }
}
