package health.compact.a;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.security.SecurityConstant;
import com.huawei.hwcommonmodel.utils.PermissionUtil;

/* loaded from: classes.dex */
public class BroadcastManagerUtil {

    /* renamed from: a, reason: collision with root package name */
    public static final String f13106a = SecurityConstant.d;

    private BroadcastManagerUtil() {
    }

    public static final void bFH_(Context context, Intent intent, String str, boolean z) {
        if (context == null || str == null || intent == null) {
            return;
        }
        if (z) {
            intent.setPackage(context.getPackageName());
        }
        intent.setPackage(context.getPackageName());
        com.huawei.hwlogsmodel.LogUtil.c("BroadcastManagerUtil", "broadcast", intent.getAction());
        context.sendBroadcast(intent, str);
    }

    public static final void bFG_(Context context, Intent intent) {
        if (context == null || intent == null) {
            return;
        }
        intent.setPackage(context.getPackageName());
        com.huawei.hwlogsmodel.LogUtil.a("BroadcastManagerUtil", "broadcast: ", intent.getAction());
        context.sendBroadcast(intent, f13106a);
    }

    public static final void bFE_(Context context, BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        if (context == null || broadcastReceiver == null || intentFilter == null) {
            return;
        }
        bFA_(context, broadcastReceiver, intentFilter, f13106a, null);
    }

    public static final void bFK_(Context context, BroadcastReceiver broadcastReceiver) {
        if (context == null || broadcastReceiver == null) {
            return;
        }
        context.unregisterReceiver(broadcastReceiver);
    }

    public static final void bFF_(Context context, Intent intent) {
        if (intent == null || context == null) {
            return;
        }
        com.huawei.hwlogsmodel.LogUtil.a("BroadcastManagerUtil", "broadcast", intent.getAction());
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(context);
        intent.setPackage(context.getPackageName());
        if (localBroadcastManager != null) {
            localBroadcastManager.sendBroadcast(intent);
        }
    }

    public static final void bFz_(Context context, BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(context);
        if (localBroadcastManager != null) {
            localBroadcastManager.registerReceiver(broadcastReceiver, intentFilter);
        }
    }

    public static void bFA_(Context context, BroadcastReceiver broadcastReceiver, IntentFilter intentFilter, String str, Handler handler) {
        if (context == null) {
            context = BaseApplication.e();
        }
        Context context2 = context;
        if (PermissionUtil.g()) {
            context2.registerReceiver(broadcastReceiver, intentFilter, str, handler, 4);
        } else {
            context2.registerReceiver(broadcastReceiver, intentFilter, str, handler);
        }
    }

    public static void bFC_(Context context, BroadcastReceiver broadcastReceiver, IntentFilter intentFilter, String str, Handler handler) {
        if (context == null) {
            context = BaseApplication.e();
        }
        Context context2 = context;
        if (PermissionUtil.g()) {
            context2.registerReceiver(broadcastReceiver, intentFilter, str, handler, 2);
        } else {
            context2.registerReceiver(broadcastReceiver, intentFilter, str, handler);
        }
    }

    public static void bFD_(Context context, BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        if (context == null) {
            context = BaseApplication.e();
        }
        if (PermissionUtil.g()) {
            context.registerReceiver(broadcastReceiver, intentFilter, 4);
        } else {
            context.registerReceiver(broadcastReceiver, intentFilter);
        }
    }

    public static void bFB_(Context context, BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        if (context == null) {
            context = BaseApplication.e();
        }
        if (PermissionUtil.g()) {
            context.registerReceiver(broadcastReceiver, intentFilter, 2);
        } else {
            context.registerReceiver(broadcastReceiver, intentFilter);
        }
    }

    public static final void bFJ_(Context context, BroadcastReceiver broadcastReceiver) {
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(context);
        if (localBroadcastManager != null) {
            localBroadcastManager.unregisterReceiver(broadcastReceiver);
        }
    }

    public static void bFI_(Context context, Intent intent) {
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(context);
        if (localBroadcastManager != null) {
            localBroadcastManager.sendBroadcast(intent);
        }
    }
}
