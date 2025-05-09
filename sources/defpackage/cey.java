package defpackage;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.device.callback.IHealthDeviceCallback;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes3.dex */
public class cey extends cxh {
    @Override // defpackage.cxh, com.huawei.health.device.connectivity.comm.MeasurableDevice
    public boolean connectAsync(IHealthDeviceCallback iHealthDeviceCallback) {
        return false;
    }

    private cey(BluetoothDevice bluetoothDevice) {
        try {
            LogUtil.a("PartnerBleDevice", bluetoothDevice.getName(), " PartnerBleDevice is constructed");
            this.f11518a = BluetoothAdapter.getDefaultAdapter().getRemoteDevice(bluetoothDevice.getAddress());
            super.setDeviceName(this.f11518a.getName());
        } catch (SecurityException e) {
            LogUtil.b("PartnerBleDevice", "PartnerBleDevice SecurityException:", ExceptionUtils.d(e));
        }
    }

    private cey() {
    }

    @Override // defpackage.cxh, com.huawei.health.device.model.HealthDevice
    public String getUniqueId() {
        return getAddress();
    }

    @Override // defpackage.cxh, com.huawei.health.device.connectivity.comm.MeasurableDevice, com.huawei.health.device.model.HealthDevice
    public String getDeviceName() {
        if (this.f11518a == null) {
            return "";
        }
        String name = this.f11518a.getName();
        if (name != null) {
            LogUtil.c("PartnerBleDevice", "PartnerBleDevice name : ", name);
            return name;
        }
        return super.getDeviceName();
    }

    @Override // defpackage.cxh, com.huawei.health.device.model.HealthDevice
    public String getAddress() {
        return this.f11518a != null ? this.f11518a.getAddress() : "";
    }

    public static cey Eq_(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice != null) {
            return new cey(bluetoothDevice);
        }
        return new cey();
    }

    @Override // defpackage.cxh, com.huawei.health.device.connectivity.comm.MeasurableDevice
    public boolean doRemoveBond() {
        if (this.f11518a == null) {
            return false;
        }
        updateDeviceUsedTime(2, this.f11518a.getAddress());
        return false;
    }

    @Override // defpackage.cxh, com.huawei.health.device.connectivity.comm.MeasurableDevice
    public boolean connectSync(int i) {
        updateDeviceUsedTime(3, this.f11518a.getAddress());
        return true;
    }
}
