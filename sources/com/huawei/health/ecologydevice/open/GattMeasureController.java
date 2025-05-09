package com.huawei.health.ecologydevice.open;

import android.bluetooth.BluetoothGattCallback;
import android.os.Bundle;
import com.huawei.health.device.callback.IHealthDeviceCallback;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.open.BleMeasureController;
import defpackage.cxh;
import defpackage.cxj;

/* loaded from: classes3.dex */
public abstract class GattMeasureController extends BleMeasureController {
    public cxh mDevice;

    protected abstract BluetoothGattCallback getGattCallbackImpl();

    @Override // com.huawei.health.device.open.MeasureController
    public boolean prepare(HealthDevice healthDevice, IHealthDeviceCallback iHealthDeviceCallback, Bundle bundle) {
        if (!(healthDevice instanceof cxh) || iHealthDeviceCallback == null) {
            return false;
        }
        cxh cxhVar = (cxh) healthDevice;
        this.mDevice = cxhVar;
        cxhVar.Rc_(getGattCallbackImpl());
        return true;
    }

    @Override // com.huawei.health.device.open.MeasureController
    public void ending() {
        cxh cxhVar = this.mDevice;
        if (cxhVar != null) {
            cxhVar.disconnect();
            this.mDevice.Rc_(null);
            cxj.c().a(this.mDevice.getAddress());
        }
    }
}
