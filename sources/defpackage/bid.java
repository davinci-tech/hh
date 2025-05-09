package defpackage;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.os.Message;
import com.huawei.devicesdk.connect.physical.OpenBlePhysicalServiceBase;
import health.compact.a.LogUtil;
import java.util.List;

/* loaded from: classes3.dex */
public class bid extends OpenBlePhysicalServiceBase {
    private final Object b = new Object();

    @Override // com.huawei.devicesdk.connect.physical.OpenBlePhysicalServiceBase
    public void onServiceDiscovery(BluetoothGatt bluetoothGatt) {
        if (bluetoothGatt == null) {
            LogUtil.e("TransparentBlePhysicalService", "gatt is null");
            return;
        }
        LogUtil.c("TransparentBlePhysicalService", "onServiceDiscovery:", bluetoothGatt.getDevice());
        this.mBluetoothGatt = bluetoothGatt;
        updateDeviceConnectState(2, 100000);
    }

    @Override // com.huawei.devicesdk.connect.physical.OpenBlePhysicalServiceBase
    public void onCharacteristicChange(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (bluetoothGattCharacteristic == null || bluetoothGattCharacteristic.getUuid() == null) {
            LogUtil.e("TransparentBlePhysicalService", "characteristic is null");
            return;
        }
        String uuid = bluetoothGattCharacteristic.getUuid().toString();
        byte[] value = bluetoothGattCharacteristic.getValue();
        int properties = bluetoothGattCharacteristic.getProperties();
        blt.d("TransparentBlePhysicalService", value, "onCharacteristicChanged uuid:", uuid, " properties:", Integer.valueOf(properties), blt.a(this.mDeviceInfo), " Device-->SDK:");
        d(uuid, value, properties);
    }

    @Override // com.huawei.devicesdk.connect.physical.OpenBlePhysicalServiceBase
    public boolean writeCharacteristicValue(BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr) {
        synchronized (this.b) {
            if (bluetoothGattCharacteristic == null) {
                LogUtil.e("TransparentBlePhysicalService", "characteristic is invalid.");
                return false;
            }
            bluetoothGattCharacteristic.setValue(bArr);
            if (this.mBluetoothGatt == null) {
                return false;
            }
            this.mIsLocked.set(true);
            blt.d("TransparentBlePhysicalService", bArr, "SDK-->Device:");
            boolean writeCharacteristic = this.mBluetoothGatt.writeCharacteristic(bluetoothGattCharacteristic);
            LogUtil.c("TransparentBlePhysicalService", "writeCharacteristicValue status:", Boolean.valueOf(writeCharacteristic));
            if (writeCharacteristic) {
                lock(bluetoothGattCharacteristic.getUuid().toString());
            } else {
                this.mIsLocked.set(false);
            }
            return writeCharacteristic;
        }
    }

    @Override // com.huawei.devicesdk.connect.physical.OpenBlePhysicalServiceBase
    public void descriptorWrite(BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
        if (bluetoothGattDescriptor == null || bluetoothGattDescriptor.getCharacteristic() == null || bluetoothGattDescriptor.getCharacteristic().getUuid() == null) {
            LogUtil.e("TransparentBlePhysicalService", "descriptor is null");
        } else {
            LogUtil.c("TransparentBlePhysicalService", "descriptorWrite characterUuid:", bluetoothGattDescriptor.getCharacteristic().getUuid().toString(), "status:", Integer.valueOf(i));
        }
    }

    @Override // com.huawei.devicesdk.connect.physical.OpenBlePhysicalServiceBase
    public void characteristicWrite(BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
        if (bluetoothGattCharacteristic == null || bluetoothGattCharacteristic.getUuid() == null) {
            LogUtil.e("TransparentBlePhysicalService", "characteristic is null");
            return;
        }
        blt.d("TransparentBlePhysicalService", bluetoothGattCharacteristic.getValue(), "onCharacteristicRead uuid:", bluetoothGattCharacteristic.getUuid(), blt.a(this.mDeviceInfo), " data:");
        String uuid = bluetoothGattCharacteristic.getUuid().toString();
        byte[] value = bluetoothGattCharacteristic.getValue();
        if (i != 0) {
            onMessageReceived(uuid, value, 1);
        } else {
            onMessageReceived(uuid, value, 0);
        }
    }

    @Override // com.huawei.devicesdk.connect.physical.OpenBlePhysicalServiceBase
    public void setDescriptorValue(BluetoothGattCharacteristic bluetoothGattCharacteristic, BluetoothGattDescriptor bluetoothGattDescriptor, List<byte[]> list) {
        if (bluetoothGattCharacteristic == null || bluetoothGattDescriptor == null) {
            LogUtil.e("TransparentBlePhysicalService", "characteristic or descriptor is null");
        } else if ((bluetoothGattCharacteristic.getProperties() & 16) == 0) {
            bluetoothGattDescriptor.setValue(BluetoothGattDescriptor.ENABLE_INDICATION_VALUE);
        } else {
            bluetoothGattDescriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
        }
    }

    private void d(String str, byte[] bArr, int i) {
        biu biuVar = new biu();
        biuVar.a(str);
        biuVar.d(bArr);
        biuVar.a(i);
        Message rP_ = this.mConnectHandler.rP_(9);
        rP_.arg1 = 0;
        rP_.obj = biuVar;
        this.mConnectHandler.rQ_(rP_);
    }
}
