package defpackage;

import com.huawei.devicesdk.callback.DeviceStatusChangeCallback;
import com.huawei.devicesdk.entity.DeviceInfo;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.unitedevice.constant.ConnectState;

/* loaded from: classes3.dex */
public class cem implements DeviceStatusChangeCallback {
    private cgt e;

    public cem() {
        if (this.e == null) {
            this.e = cgt.e();
        }
    }

    @Override // com.huawei.devicesdk.callback.DeviceStatusChangeCallback
    public void onConnectStatusChanged(DeviceInfo deviceInfo, int i, int i2) {
        LogUtil.a("PluginDevice_UdsConnectCallback", "onUdsConnectStatusChanged status:", Integer.valueOf(i), " errCode:", Integer.valueOf(i2));
        if (this.e == null) {
            LogUtil.h("PluginDevice_UdsConnectCallback", "onConnectStatusChanged mHwWspMeasureController is null");
            return;
        }
        if (deviceInfo == null) {
            LogUtil.h("onConnectStatusChanged deviceInfo is null", new Object[0]);
            return;
        }
        if (deviceInfo.getDeviceBtType() > 0) {
            LogUtil.h("onConnectStatusChanged DeviceBtType:", Integer.valueOf(deviceInfo.getDeviceBtType()));
            return;
        }
        ConnectState connectState = ConnectState.getConnectState(i);
        if (connectState == ConnectState.CONNECTED) {
            this.e.e(deviceInfo.getDeviceMac());
            dew.b(deviceInfo.getDeviceMac(), 1);
        } else if (connectState == ConnectState.DISCONNECTED) {
            this.e.c();
            dew.b(deviceInfo.getDeviceMac(), 0);
        } else if (connectState == ConnectState.CONNECTING) {
            this.e.d();
            LogUtil.a("PluginDevice_UdsConnectCallback", "onUdsConnectStatusChanged State == CONNECTING");
        } else {
            LogUtil.h("PluginDevice_UdsConnectCallback", "onUdsConnectStatusChanged State is unknown");
        }
    }
}
