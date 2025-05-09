package defpackage;

import android.content.Context;
import android.content.IntentFilter;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.huawei.watchface.api.HwWatchFaceApi;
import com.huawei.wear.wallet.proxy.broadcaster.ForegroundStatusChangeReceiver;

/* loaded from: classes9.dex */
public class tee {
    private static final Object b = new Object();
    private static volatile tee c;

    /* renamed from: a, reason: collision with root package name */
    private boolean f17260a = false;
    private final ForegroundStatusChangeReceiver d = new ForegroundStatusChangeReceiver();

    public static tee a() {
        if (c == null) {
            synchronized (b) {
                if (c == null) {
                    c = new tee();
                }
            }
        }
        return c;
    }

    public void b(Context context) {
        if (this.f17260a) {
            stq.b("BroadcastReceiverRegManager", "hasRegisterForegroundStatusChanged");
        } else {
            if (context == null) {
                stq.e("BroadcastReceiverRegManager", "invalid context");
                return;
            }
            LocalBroadcastManager.getInstance(context).registerReceiver(this.d, new IntentFilter(HwWatchFaceApi.ACTION_FOREGROUND_STATUS));
            this.f17260a = true;
            stq.b("BroadcastReceiverRegManager", "registerForegroundStatusChange complete");
        }
    }
}
