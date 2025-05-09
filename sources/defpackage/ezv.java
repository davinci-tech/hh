package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.application.BroadcastManager;
import health.compact.a.ReleaseLogUtil;

/* loaded from: classes.dex */
public class ezv {
    public static void c() {
        BroadcastManager.wk_(new a());
        BaseApplication.e().registerReceiver(new c(), new IntentFilter("android.intent.action.TIMEZONE_CHANGED"));
    }

    static class a extends BroadcastReceiver {
        private a() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            ReleaseLogUtil.b("SystemInfoChangedReceiverHelper", "action = ", intent.getAction());
            if ("android.intent.action.LOCALE_CHANGED".equals(intent.getAction())) {
                gov.d();
            }
        }
    }

    static class c extends BroadcastReceiver {
        private c() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            ReleaseLogUtil.b("SystemInfoChangedReceiverHelper", "action = ", intent.getAction());
            if ("android.intent.action.TIMEZONE_CHANGED".equals(intent.getAction())) {
                gov.c();
            }
        }
    }
}
