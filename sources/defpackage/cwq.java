package defpackage;

import com.huawei.devicesdk.callback.DeviceStatusChangeCallback;
import com.huawei.devicesdk.entity.DeviceInfo;
import com.huawei.health.ecologydevice.callback.ConnectStatusCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.unitedevice.constant.ConnectState;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public class cwq implements DeviceStatusChangeCallback {
    private static Map<String, ConnectStatusCallback> c = new HashMap();

    /* renamed from: a, reason: collision with root package name */
    private String f11512a;

    public cwq(String str) {
        this.f11512a = str;
    }

    @Override // com.huawei.devicesdk.callback.DeviceStatusChangeCallback
    public void onConnectStatusChanged(DeviceInfo deviceInfo, int i, int i2) {
        LogUtil.a("EcologyDevice_ThirdPartyDeviceStatusChangeCallback", "onUdsConnectStatusChanged status:", Integer.valueOf(i), " errCode:", Integer.valueOf(i2));
        ddw.c().e(i);
        ConnectState connectState = ConnectState.getConnectState(i);
        ConnectStatusCallback connectStatusCallback = c.get(this.f11512a);
        if (connectStatusCallback != null) {
            if (connectState == ConnectState.CONNECTED) {
                connectStatusCallback.doDeviceConnected(i, deviceInfo);
                dew.b(deviceInfo.getDeviceMac(), 1);
                return;
            } else if (connectState == ConnectState.DISCONNECTED) {
                connectStatusCallback.doDeviceDisconnect(i);
                dew.b(deviceInfo.getDeviceMac(), 0);
                return;
            } else if (connectState == ConnectState.CONNECTING) {
                connectStatusCallback.doDeviceConnecting(i);
                return;
            } else {
                LogUtil.h("EcologyDevice_ThirdPartyDeviceStatusChangeCallback", "onConnectStatusChanged State is unknown");
                return;
            }
        }
        LogUtil.h("EcologyDevice_ThirdPartyDeviceStatusChangeCallback", "ConnectStatusCallback is null");
    }

    public static void e(String str, ConnectStatusCallback connectStatusCallback) {
        c.put(str, connectStatusCallback);
    }

    public static void d() {
        c.clear();
    }

    public static int e() {
        Map<String, ConnectStatusCallback> map = c;
        if (map == null) {
            return 0;
        }
        return map.size();
    }
}
