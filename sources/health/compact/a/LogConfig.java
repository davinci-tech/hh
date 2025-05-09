package health.compact.a;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.huawei.haf.application.BroadcastManager;
import health.compact.a.LogConfig;

/* loaded from: classes.dex */
public final class LogConfig {

    /* renamed from: a, reason: collision with root package name */
    private static boolean f13126a = true;
    private static boolean b = false;
    private static boolean c = true;
    private static boolean d = false;
    private static int e = 2;
    private static BroadcastReceiver i;

    private LogConfig() {
    }

    static void b(boolean z, int i2, boolean z2, boolean z3) {
        f13126a = z;
        c = z3;
        if (i2 < 0 || i2 > 4) {
            i2 = 2;
        }
        e = i2;
        b = false;
        d = false;
        if (!z2) {
            BroadcastReceiver broadcastReceiver = i;
            if (broadcastReceiver != null) {
                BroadcastManager.ww_(broadcastReceiver);
                i = null;
                return;
            }
            return;
        }
        if (i != null) {
            return;
        }
        BroadcastReceiver broadcastReceiver2 = new BroadcastReceiver() { // from class: com.huawei.haf.common.log.LogConfig$1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                if ("com.huawei.haf.intent.action.DEBUG_SWITCH_LOG".equals(intent.getAction())) {
                    int unused = LogConfig.e = intent.getIntExtra("level", 2);
                    boolean unused2 = LogConfig.b = intent.getBooleanExtra("force", false);
                    boolean unused3 = LogConfig.d = intent.getBooleanExtra("origina", false);
                }
            }
        };
        i = broadcastReceiver2;
        BroadcastManager.wi_(broadcastReceiver2);
    }

    static boolean c(int i2, String str) {
        if (c || b || !c(i2) || TextUtils.isEmpty(str)) {
            return c;
        }
        return str.startsWith("HAF_", 0) || str.startsWith("Bundle_", 0);
    }

    public static boolean c(int i2) {
        return f13126a && i2 >= e;
    }

    public static int e() {
        return e;
    }

    public static boolean d() {
        return b;
    }

    public static boolean a() {
        return d;
    }
}
