package defpackage;

import com.huawei.health.device.callback.IDeviceEventHandler;
import com.huawei.health.device.callback.IHealthDeviceCallback;
import com.huawei.health.device.connectivity.comm.MeasurableDevice;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes3.dex */
public class cet extends MeasurableDevice {
    private String b;
    private String c;
    private String d;

    @Override // com.huawei.health.device.connectivity.comm.MeasurableDevice
    public boolean connectAsync(IHealthDeviceCallback iHealthDeviceCallback) {
        return false;
    }

    @Override // com.huawei.health.device.connectivity.comm.MeasurableDevice
    public boolean connectSync(int i) {
        return false;
    }

    @Override // com.huawei.health.device.connectivity.comm.MeasurableDevice
    public void disconnect() {
    }

    @Override // com.huawei.health.device.connectivity.comm.MeasurableDevice
    public boolean doRemoveBond() {
        return false;
    }

    public cet(String str, String str2, String str3) {
        this.b = str;
        this.d = str2;
        this.c = str3;
    }

    @Override // com.huawei.health.device.connectivity.comm.MeasurableDevice
    public boolean doCreateBond(IDeviceEventHandler iDeviceEventHandler) {
        LogUtil.c("DeviceAutoTestDevice", "DeviceAutoTestDevice doCreateBond");
        iDeviceEventHandler.onStateChanged(7);
        return false;
    }

    @Override // com.huawei.health.device.connectivity.comm.MeasurableDevice, com.huawei.health.device.model.HealthDevice
    public String getDeviceName() {
        return this.b;
    }

    @Override // com.huawei.health.device.model.HealthDevice
    public String getAddress() {
        return this.d;
    }

    @Override // com.huawei.health.device.model.HealthDevice
    public String getUniqueId() {
        return this.c;
    }
}
