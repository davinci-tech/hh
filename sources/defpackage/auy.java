package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Parcelable;
import android.text.TextUtils;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.unitedevice.constant.ConnectState;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.LocalBroadcast;

/* loaded from: classes3.dex */
public class auy {
    private static volatile auy c;
    private BroadcastReceiver b;

    private auy() {
    }

    public static auy d() {
        if (c == null) {
            synchronized (auy.class) {
                if (c == null) {
                    c = new auy();
                }
            }
        }
        return c;
    }

    public void b() {
        LogUtil.a("HealthLife_DeviceConnectionManager", "registerDeviceConnectReceiver mDeviceStatusReceiver ", this.b);
        if (this.b != null) {
            return;
        }
        a();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.huawei.bone.action.CONNECTION_STATE_CHANGED");
        BroadcastManagerUtil.bFC_(BaseApplication.getContext(), this.b, intentFilter, LocalBroadcast.c, null);
    }

    private void a() {
        this.b = new BroadcastReceiver() { // from class: auy.3
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                if (intent == null) {
                    LogUtil.h("HealthLife_DeviceConnectionManager", "createDeviceStatusReceiver onReceive intent is null");
                    return;
                }
                String action = intent.getAction();
                LogUtil.a("HealthLife_DeviceConnectionManager", "createDeviceStatusReceiver onReceive action ", action);
                if (TextUtils.isEmpty(action) || !"com.huawei.bone.action.CONNECTION_STATE_CHANGED".equals(action)) {
                    return;
                }
                Parcelable parcelableExtra = intent.getParcelableExtra("deviceinfo");
                DeviceInfo deviceInfo = parcelableExtra instanceof DeviceInfo ? (DeviceInfo) parcelableExtra : null;
                LogUtil.h("HealthLife_DeviceConnectionManager", "createDeviceStatusReceiver onReceive deviceInfo ", deviceInfo);
                if (deviceInfo == null) {
                    return;
                }
                auy.this.a(deviceInfo);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(DeviceInfo deviceInfo) {
        ConnectState connectState;
        int deviceConnectState = deviceInfo.getDeviceConnectState();
        if (deviceConnectState == 1) {
            connectState = ConnectState.CONNECTING;
        } else if (deviceConnectState == 2) {
            connectState = ConnectState.CONNECTED;
        } else if (deviceConnectState == 3 || deviceConnectState == 4) {
            connectState = ConnectState.DISCONNECTED;
        } else {
            LogUtil.h("HealthLife_DeviceConnectionManager", "handleDeviceConnectState deviceInfo ", deviceInfo);
            connectState = null;
        }
        LogUtil.a("HealthLife_DeviceConnectionManager", "handleDeviceConnectState state ", connectState);
        if (connectState == null) {
            return;
        }
        if (connectState == ConnectState.CONNECTED) {
            e(deviceInfo);
        } else if (connectState == ConnectState.DISCONNECTED) {
            d(deviceInfo);
        }
    }

    public void e(DeviceInfo deviceInfo) {
        boolean c2 = cwi.c(deviceInfo, 37);
        LogUtil.a("HealthLife_DeviceConnectionManager", "registerHealthLife isSupportCapability ", Boolean.valueOf(c2));
        avm.a().b(c2);
        if (c2) {
            avc.e();
            avm.a().g();
        }
    }

    private void d(DeviceInfo deviceInfo) {
        boolean c2 = cwi.c(deviceInfo, 37);
        LogUtil.a("HealthLife_DeviceConnectionManager", "unregisterHealthLife isSupportCapability ", Boolean.valueOf(c2));
        if (c2) {
            avc.e().c("HealthLife_DeviceConnectionManager");
        }
    }
}
