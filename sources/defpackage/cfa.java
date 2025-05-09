package defpackage;

import com.huawei.health.device.callback.IDeviceEventHandler;
import com.huawei.health.device.callback.IHealthDeviceCallback;
import com.huawei.health.device.connectivity.comm.MeasurableDevice;

/* loaded from: classes3.dex */
public class cfa extends MeasurableDevice {

    /* renamed from: a, reason: collision with root package name */
    private String f672a;

    @Override // com.huawei.health.device.connectivity.comm.MeasurableDevice
    public boolean connectAsync(IHealthDeviceCallback iHealthDeviceCallback) {
        return false;
    }

    @Override // com.huawei.health.device.connectivity.comm.MeasurableDevice
    public void disconnect() {
    }

    public String b() {
        return this.f672a;
    }

    public void d(String str) {
        this.f672a = str;
    }

    @Override // com.huawei.health.device.connectivity.comm.MeasurableDevice
    public boolean doCreateBond(IDeviceEventHandler iDeviceEventHandler) {
        iDeviceEventHandler.onStateChanged(7);
        updateDeviceUsedTime(1, "DnurseGlucose");
        return false;
    }

    @Override // com.huawei.health.device.connectivity.comm.MeasurableDevice
    public boolean doRemoveBond() {
        updateDeviceUsedTime(2, "DnurseGlucose");
        return false;
    }

    @Override // com.huawei.health.device.connectivity.comm.MeasurableDevice
    public boolean connectSync(int i) {
        updateDeviceUsedTime(3, "DnurseGlucose");
        return true;
    }

    @Override // com.huawei.health.device.model.HealthDevice
    public String getUniqueId() {
        return "DnurseGlucose";
    }

    @Override // com.huawei.health.device.connectivity.comm.MeasurableDevice, com.huawei.health.device.model.HealthDevice
    public String getDeviceName() {
        return "DnurseGlucose";
    }

    @Override // com.huawei.health.device.model.HealthDevice
    public String getAddress() {
        return "DnurseGlucose";
    }
}
