package defpackage;

import com.huawei.health.device.callback.IDeviceEventHandler;
import com.huawei.health.device.callback.IHealthDeviceCallback;
import com.huawei.health.device.connectivity.comm.MeasurableDevice;

/* loaded from: classes3.dex */
public class cfb extends MeasurableDevice {

    /* renamed from: a, reason: collision with root package name */
    private String f673a;
    private int c;
    private String d;

    @Override // com.huawei.health.device.connectivity.comm.MeasurableDevice
    public boolean connectAsync(IHealthDeviceCallback iHealthDeviceCallback) {
        return true;
    }

    @Override // com.huawei.health.device.connectivity.comm.MeasurableDevice
    public boolean connectSync(int i) {
        return true;
    }

    @Override // com.huawei.health.device.connectivity.comm.MeasurableDevice
    public void disconnect() {
    }

    @Override // com.huawei.health.device.connectivity.comm.MeasurableDevice
    public boolean doCreateBond(IDeviceEventHandler iDeviceEventHandler) {
        return false;
    }

    @Override // com.huawei.health.device.connectivity.comm.MeasurableDevice
    public boolean doRemoveBond() {
        return false;
    }

    public cfb(String str, String str2, int i) {
        this.f673a = str;
        this.d = str2;
        this.c = i;
    }

    @Override // com.huawei.health.device.connectivity.comm.MeasurableDevice, com.huawei.health.device.model.HealthDevice
    public String getDeviceName() {
        return this.f673a;
    }

    @Override // com.huawei.health.device.model.HealthDevice
    public String getAddress() {
        return this.d;
    }

    @Override // com.huawei.health.device.model.HealthDevice
    public String getUniqueId() {
        return this.d;
    }

    public int d() {
        return this.c;
    }
}
