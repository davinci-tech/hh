package health.compact.a;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

/* loaded from: classes.dex */
public class HiBroadcastManager {
    private HiBroadcastManager() {
    }

    public static void bwl_(Context context, Intent intent) {
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(context);
        if (localBroadcastManager == null) {
            health.compact.a.util.LogUtil.c("Debug_HiBroadcastManager", "sendLocalBroadcast localBroadcastManager == null");
        } else {
            localBroadcastManager.sendBroadcast(intent);
        }
    }

    public static void bwk_(Context context, Intent intent) {
        context.sendBroadcast(intent, "com.huawei.hihealth.DEFAULT_PERMISSION");
    }

    public static final void bwj_(Context context, BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        try {
            if (Build.VERSION.SDK_INT >= 33) {
                context.registerReceiver(broadcastReceiver, intentFilter, "com.huawei.hihealth.DEFAULT_PERMISSION", null, 4);
            } else {
                context.registerReceiver(broadcastReceiver, intentFilter, "com.huawei.hihealth.DEFAULT_PERMISSION", null);
            }
        } catch (IllegalArgumentException e) {
            health.compact.a.util.LogUtil.e("Debug_HiBroadcastManager", "registerReceiver IllegalArgumentException = ", e.getMessage());
        } catch (Exception unused) {
            health.compact.a.util.LogUtil.e("Debug_HiBroadcastManager", "registerReceiver Exception");
        }
    }

    public static final void bwm_(Context context, BroadcastReceiver broadcastReceiver) {
        try {
            context.unregisterReceiver(broadcastReceiver);
        } catch (IllegalArgumentException e) {
            health.compact.a.util.LogUtil.e("Debug_HiBroadcastManager", "unregisterReceiver IllegalArgumentException = ", e.getMessage());
        } catch (Exception unused) {
            health.compact.a.util.LogUtil.e("Debug_HiBroadcastManager", "unregisterReceiver Exception");
        }
    }
}
