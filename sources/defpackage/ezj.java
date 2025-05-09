package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import com.huawei.health.MainActivity;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.BroadcastManagerUtil;
import java.lang.ref.WeakReference;

/* loaded from: classes3.dex */
public class ezj {
    private static c e;

    public static void aug_(Context context, Handler handler) {
        LogUtil.a("DataMigrateReceiverHelper", "register BroadReceiver");
        e = new c(handler);
        BroadcastManagerUtil.bFA_(context, e, new IntentFilter("com.huawei.hihealth.action_change_to_cloud_version"), "com.huawei.hihealth.DEFAULT_PERMISSION", null);
    }

    public static void b(Context context) {
        if (e != null) {
            try {
                LogUtil.a("DataMigrateReceiverHelper", "Enter unRegisterPrivicyChange");
                context.unregisterReceiver(e);
                e = null;
            } catch (IllegalArgumentException e2) {
                LogUtil.a("DataMigrateReceiverHelper", "unRegisterPrivicyChange IllegalArgumentException e = ", e2.getMessage());
            } catch (RuntimeException e3) {
                LogUtil.a("DataMigrateReceiverHelper", "unRegisterPrivicyChange RuntimeException e = ", e3.getMessage());
            }
        }
    }

    static class c extends BroadcastReceiver {
        private final WeakReference<Handler> e;

        public c(Handler handler) {
            this.e = new WeakReference<>(handler);
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                LogUtil.h("DataMigrateBeginBroadcastReceiver", "DataMigrateBeginBroadcastReceiver onReceive intent is null");
                return;
            }
            if (intent.getAction() == null) {
                LogUtil.h("DataMigrateBeginBroadcastReceiver", "DataMigrateBeginBroadcastReceiver onReceive getAction is null");
                return;
            }
            LogUtil.a("DataMigrateBeginBroadcastReceiver", "DataMigrateBeginBroadcastReceiver onReceive getAction : ", intent.getAction());
            if ("com.huawei.hihealth.action_change_to_cloud_version".equals(intent.getAction()) && (this.e.get() instanceof MainActivity.h) && this.e.get() != null) {
                LogUtil.a("DataMigrateBeginBroadcastReceiver", "DataMigrateBeginBroadcastReceiver enter");
                if (dzn.e()) {
                    this.e.get().sendEmptyMessage(10086);
                }
            }
        }
    }
}
