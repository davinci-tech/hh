package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes3.dex */
public class eug {

    /* renamed from: a, reason: collision with root package name */
    private BroadcastReceiver f12327a;
    private LocalBroadcastManager b;
    private IntentFilter d;
    private String e;

    static class a {

        /* renamed from: a, reason: collision with root package name */
        static eug f12328a = new eug();
    }

    public static eug a() {
        return a.f12328a;
    }

    private eug() {
        this.d = new IntentFilter();
        this.e = "com.huawei.plugin.account.login";
        this.f12327a = new BroadcastReceiver() { // from class: eug.5
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                LogUtil.a("LogoutCleanManager", "receive account broadcast ");
                if (context == null || intent == null) {
                    ReleaseLogUtil.d("LogoutCleanManager", "BroadcastReceiver context == null || intent == null");
                    return;
                }
                String action = intent.getAction();
                if (action == null) {
                    return;
                }
                if ("".equals(eug.this.e) || !action.equals(eug.this.e)) {
                    eug.this.e = action;
                    if ("com.huawei.plugin.account.logout".equals(action)) {
                        ReleaseLogUtil.e("LogoutCleanManager", "receive account remove broadcast");
                        eug.this.a(true);
                    } else {
                        ReleaseLogUtil.e("LogoutCleanManager", "receive account login broadcast");
                        eug.this.a(false);
                    }
                }
            }
        };
    }

    private void d() {
        this.d.addAction("com.huawei.plugin.account.logout");
        this.d.addAction("com.huawei.plugin.account.login");
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(arx.b());
        this.b = localBroadcastManager;
        localBroadcastManager.registerReceiver(this.f12327a, this.d);
    }

    public static void c() {
        a().d();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z) {
        LogUtil.c("LogoutCleanManager", "clearLocalData");
        ety.c().c(z);
    }
}
