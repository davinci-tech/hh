package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.huawei.haf.common.security.SecurityConstant;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes4.dex */
public class gut {
    private static final String d = SecurityConstant.d;

    public static final void aUn_(Context context, Intent intent) {
        if (intent == null || context == null) {
            LogUtil.b("Track_BroadcastManagerUtil", "intent is null");
            return;
        }
        LogUtil.a("Track_BroadcastManagerUtil", "broadcast", intent.getAction());
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(context);
        intent.setPackage(context.getPackageName());
        if (localBroadcastManager != null) {
            localBroadcastManager.sendBroadcast(intent);
        }
    }

    public static final void aUm_(Context context, BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        if (context == null) {
            LogUtil.b("Track_BroadcastManagerUtil", "context is null");
            return;
        }
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(context);
        if (localBroadcastManager != null) {
            localBroadcastManager.registerReceiver(broadcastReceiver, intentFilter);
        }
    }

    public static final void aUp_(Context context, BroadcastReceiver broadcastReceiver) {
        if (context == null) {
            LogUtil.b("Track_BroadcastManagerUtil", "context is null");
            return;
        }
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(context);
        if (localBroadcastManager != null) {
            localBroadcastManager.unregisterReceiver(broadcastReceiver);
        }
    }

    public static void aUo_(Context context, Intent intent) {
        if (intent == null || context == null) {
            LogUtil.b("Track_BroadcastManagerUtil", "intent or context is null");
            return;
        }
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(context);
        if (localBroadcastManager != null) {
            localBroadcastManager.sendBroadcast(intent);
        }
    }
}
