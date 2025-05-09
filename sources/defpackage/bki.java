package defpackage;

import android.text.TextUtils;
import com.huawei.devicesdk.entity.ConnectMode;
import com.huawei.devicesdk.entity.DeviceInfo;
import com.huawei.devicesdk.strategy.ConnectStrategy;
import health.compact.a.LogUtil;

/* loaded from: classes3.dex */
public class bki extends ConnectStrategy {
    @Override // com.huawei.devicesdk.strategy.ConnectStrategy
    public void destroy(String str) {
    }

    @Override // com.huawei.devicesdk.strategy.ConnectStrategy
    public void onChannelEnable(DeviceInfo deviceInfo, int i) {
    }

    @Override // com.huawei.devicesdk.strategy.ConnectStrategy
    public void onDataReceived(DeviceInfo deviceInfo, biu biuVar, int i) {
    }

    @Override // com.huawei.devicesdk.strategy.ConnectStrategy
    public void connect(ConnectMode connectMode, DeviceInfo deviceInfo) {
        super.connect(connectMode, deviceInfo);
    }

    @Override // com.huawei.devicesdk.strategy.ConnectStrategy
    public void getConnectStatus(String str) {
        LogUtil.c("ConnectStrategyTransparent", "getConnectStatus start");
    }

    @Override // com.huawei.devicesdk.strategy.ConnectStrategy
    public void startHandshake(DeviceInfo deviceInfo) {
        if (this.mHandshakeStatusReporter == null) {
            LogUtil.a("ConnectStrategyTransparent", "handshake status callback is null", blt.a(deviceInfo));
        } else {
            this.mHandshakeStatusReporter.onHandshakeFinish(deviceInfo);
        }
    }

    @Override // com.huawei.devicesdk.strategy.ConnectStrategy
    public void disconnect(DeviceInfo deviceInfo) {
        if (deviceInfo == null || TextUtils.isEmpty(deviceInfo.getDeviceMac())) {
            LogUtil.a("ConnectStrategyTransparent", "disconnect error. device info is invalid.");
        } else {
            super.disconnect(deviceInfo);
        }
    }

    @Override // com.huawei.devicesdk.strategy.ConnectStrategy
    public void unPairDevice(DeviceInfo deviceInfo, ConnectMode connectMode, boolean z) {
        if (deviceInfo == null) {
            LogUtil.a("ConnectStrategyTransparent", "ConnectStrategySimple unPairDevice device null");
        } else {
            bib.a().e(deviceInfo, connectMode);
        }
    }
}
