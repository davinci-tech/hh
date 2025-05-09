package com.huawei.hms.ads.identifier;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.util.Log;
import com.huawei.openalliance.ad.constant.Constants;

/* loaded from: classes9.dex */
public class d extends BroadcastReceiver {

    /* renamed from: a, reason: collision with root package name */
    private static volatile d f4322a;

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (context == null || intent == null || !"com.huawei.opendevice.open.action.REAL_OAID_RESET".equals(intent.getAction())) {
            return;
        }
        j.f4328a.execute(new Runnable() { // from class: com.huawei.hms.ads.identifier.d.1
            @Override // java.lang.Runnable
            public void run() {
                Log.d("OaidChangedReceiver", "receive oaid reset broadcast");
                final Context context2 = AdsIdentifierSdk.getContext();
                if (context2 == null) {
                    Log.w("OaidChangedReceiver", "context is null");
                } else {
                    c.a(context2, new e() { // from class: com.huawei.hms.ads.identifier.d.1.1
                        @Override // com.huawei.hms.ads.identifier.e
                        public void a() {
                            Intent intent2 = new Intent("com.huawei.opendevice.open.action.OAID_RESET");
                            intent2.setPackage(f.a(context2));
                            context2.sendBroadcast(intent2, "com.huawei.hms.permission.signatureOrSystem");
                        }
                    });
                }
            }
        });
    }

    public static void b(Context context) {
        try {
            if (f4322a != null) {
                Log.i("OaidChangedReceiver", "unregister receiver");
                context.unregisterReceiver(f4322a);
                f4322a = null;
            }
        } catch (Throwable unused) {
            Log.w("OaidChangedReceiver", "unregisterReceiver exception");
        }
    }

    public static void a(Context context) {
        if (context == null) {
            return;
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.huawei.opendevice.open.action.REAL_OAID_RESET");
        if (f4322a == null) {
            f4322a = new d();
        }
        if (Build.VERSION.SDK_INT >= 33) {
            context.registerReceiver(f4322a, intentFilter, Constants.PERMISSION_PPS_DOWNLOAD, null, 2);
        } else {
            context.registerReceiver(f4322a, intentFilter, Constants.PERMISSION_PPS_DOWNLOAD, null);
        }
    }
}
