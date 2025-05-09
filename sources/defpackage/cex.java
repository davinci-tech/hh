package defpackage;

import android.text.TextUtils;
import com.huawei.health.device.callback.IDeviceEventHandler;
import com.huawei.health.device.callback.IHealthDeviceCallback;
import com.huawei.health.device.connectivity.comm.MeasurableDevice;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes3.dex */
public class cex extends MeasurableDevice {

    /* renamed from: a, reason: collision with root package name */
    private String f670a;
    private String b;
    private String c;
    private String d;
    private String e;

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

    public cex() {
        this("", "", "", "");
    }

    public cex(String str, String str2, String str3, String str4) {
        this.d = "";
        this.c = str;
        this.e = str2;
        this.f670a = str3;
        this.b = str4;
    }

    @Override // com.huawei.health.device.connectivity.comm.MeasurableDevice
    public boolean doCreateBond(IDeviceEventHandler iDeviceEventHandler) {
        LogUtil.c("ManualBleDevice", "ManualBleDevice doCreateBond");
        if (iDeviceEventHandler == null) {
            return false;
        }
        iDeviceEventHandler.onStateChanged(7);
        return false;
    }

    @Override // com.huawei.health.device.connectivity.comm.MeasurableDevice, com.huawei.health.device.model.HealthDevice
    public String getDeviceName() {
        return this.c;
    }

    @Override // com.huawei.health.device.connectivity.comm.MeasurableDevice
    public void setDeviceName(String str) {
        this.c = str;
    }

    @Override // com.huawei.health.device.model.HealthDevice
    public String getAddress() {
        return !TextUtils.isEmpty(this.d) ? this.d : this.f670a;
    }

    @Override // com.huawei.health.device.model.HealthDevice
    public String getUniqueId() {
        return this.f670a;
    }

    public void d(String str) {
        this.f670a = str;
    }

    @Override // com.huawei.health.device.connectivity.comm.MeasurableDevice
    public String getProductId() {
        return this.e;
    }

    @Override // com.huawei.health.device.connectivity.comm.MeasurableDevice
    public String getSerialNumber() {
        return this.b;
    }
}
