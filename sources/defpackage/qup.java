package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Parcelable;
import android.os.SystemClock;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.DeviceUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.LogUtil;

/* loaded from: classes7.dex */
public class qup {
    private static volatile qup b;
    private BroadcastReceiver d;

    private qup() {
    }

    public static qup e() {
        if (b == null) {
            synchronized (qup.class) {
                if (b == null) {
                    b = new qup();
                }
            }
        }
        return b;
    }

    public void b() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (this.d != null) {
            LogUtil.c("FastLiteDeviceConnectionManager", "mDeviceStatusReceiver has registered");
            return;
        }
        this.d = new BroadcastReceiver() { // from class: qup.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                if (intent == null) {
                    LogUtil.a("FastLiteDeviceConnectionManager", "mDeviceStatusReceiver onReceive intent is null.");
                    return;
                }
                if (intent.getAction() == null) {
                    LogUtil.a("FastLiteDeviceConnectionManager", "mDeviceStatusReceiver onReceive action is null");
                    return;
                }
                if ("com.huawei.bone.action.CONNECTION_STATE_CHANGED".equals(intent.getAction())) {
                    Parcelable parcelableExtra = intent.getParcelableExtra("deviceinfo");
                    DeviceInfo deviceInfo = parcelableExtra instanceof DeviceInfo ? (DeviceInfo) parcelableExtra : null;
                    if (deviceInfo != null) {
                        qup.this.d(deviceInfo);
                    } else {
                        LogUtil.a("FastLiteDeviceConnectionManager", "mDeviceStatusReceiver onReceive deviceInfo is null");
                    }
                }
            }
        };
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.huawei.bone.action.CONNECTION_STATE_CHANGED");
        BroadcastManagerUtil.bFC_(BaseApplication.getContext(), this.d, intentFilter, LocalBroadcast.c, null);
        ThreadPoolManager.d().execute(new Runnable() { // from class: quo
            @Override // java.lang.Runnable
            public final void run() {
                qup.this.d();
            }
        });
        LogUtil.c("FastLiteDeviceConnectionManager", "registerDeviceConnectReceiver finished, time cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void d() {
        if (DeviceUtil.a()) {
            quq.a().b("hasDevice", null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(DeviceInfo deviceInfo) {
        int deviceConnectState = deviceInfo.getDeviceConnectState();
        if (deviceConnectState == 1) {
            LogUtil.c("FastLiteDeviceConnectionManager", "handleDeviceConnectState, state: DEVICE_CONNECTING");
            return;
        }
        if (deviceConnectState == 2) {
            quq.a();
        } else if (deviceConnectState == 3 || deviceConnectState == 4) {
            quq.c();
        }
    }
}
