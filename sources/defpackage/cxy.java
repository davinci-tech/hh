package defpackage;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.ecologydevice.fitness.datastruct.DeviceInformation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.UUID;

/* loaded from: classes3.dex */
public class cxy {
    private static final UUID b = UUID.fromString(BleConstants.CLIENT_CHARACTERISTIC_CONFIG_DESCRIPTOR_UUID);

    /* renamed from: a, reason: collision with root package name */
    protected DeviceInformation f11538a;
    private final Object c;
    private BluetoothGatt d;
    protected cyv e = new cyv();

    public cxy(BluetoothGatt bluetoothGatt, Object obj) {
        this.d = bluetoothGatt;
        this.c = obj;
    }

    public void Rf_(BluetoothGatt bluetoothGatt) {
        if (bluetoothGatt == null) {
            LogUtil.b("PDROPE_BaseRopeController", "gatt is null");
        }
        this.d = bluetoothGatt;
    }

    public void Re_(BluetoothGatt bluetoothGatt) {
        this.d = bluetoothGatt;
    }

    protected void Rg_(BluetoothGattCharacteristic bluetoothGattCharacteristic, boolean z, boolean z2) {
        if (this.d == null || bluetoothGattCharacteristic == null || !dds.c().f()) {
            LogUtil.h("PDROPE_BaseRopeController", "BluetoothAdapter not initialized");
            return;
        }
        LogUtil.h("PDROPE_BaseRopeController", "BluetoothAdapter initialized");
        try {
            this.d.setCharacteristicNotification(bluetoothGattCharacteristic, z);
            if (z2) {
                BluetoothGattDescriptor descriptor = bluetoothGattCharacteristic.getDescriptor(b);
                if (descriptor == null) {
                    LogUtil.h("PDROPE_BaseRopeController", "setCharacteristicNotification, get descriptor is null");
                    return;
                }
                if (z) {
                    descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
                } else {
                    descriptor.setValue(BluetoothGattDescriptor.DISABLE_NOTIFICATION_VALUE);
                }
                ReleaseLogUtil.e("DEVMGR_EcologyDevice_PDROPE_BaseRopeController", "setCharacteristicNotification, writeDescriptor:", bluetoothGattCharacteristic.getUuid(), ", and Descriptor is ", descriptor.getUuid());
                ReleaseLogUtil.e("DEVMGR_EcologyDevice_PDROPE_BaseRopeController", "setCharacteristicNotification, writeDescriptor:", Boolean.valueOf(this.e.Sd_(this.d, descriptor)));
                return;
            }
            LogUtil.h("PDROPE_BaseRopeController", "! enableNotification");
        } catch (SecurityException e) {
            ReleaseLogUtil.c("DEVMGR_EcologyDevice_PDROPE_BaseRopeController", "setCharacteristicNotification, ", ExceptionUtils.d(e));
        }
    }

    protected boolean Rh_(BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr) {
        synchronized (this.c) {
            if (bluetoothGattCharacteristic != null) {
                if (this.d != null && bArr != null) {
                    ReleaseLogUtil.e("DEVMGR_EcologyDevice_PDROPE_BaseRopeController", "app --> device: writeCharacteristicValueBytes :", dis.d(bArr, ""));
                    bluetoothGattCharacteristic.setValue(bArr);
                    return this.e.Sc_(this.d, bluetoothGattCharacteristic, bArr);
                }
            }
            LogUtil.h("PDROPE_BaseRopeController", "WriteValueBytes: something is null.");
            if (bluetoothGattCharacteristic == null) {
                LogUtil.h("PDROPE_BaseRopeController", "WriteValueBytes: characteristic is null.");
            }
            if (this.d == null) {
                LogUtil.h("PDROPE_BaseRopeController", "WriteValueBytes: mBluetoothGatt is null.");
            }
            return false;
        }
    }

    public DeviceInformation d() {
        return this.f11538a;
    }
}
