package defpackage;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import com.huawei.devicesdk.connect.physical.OpenBlePhysicalServiceBase;
import com.huawei.devicesdk.connect.retry.ExecuteActionInterface;
import com.huawei.devicesdk.connect.retry.RetryCallbackInterface;
import health.compact.a.LogUtil;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public class bic extends OpenBlePhysicalServiceBase {
    private final Object c = new Object();

    @Override // com.huawei.devicesdk.connect.physical.OpenBlePhysicalServiceBase
    public void onServiceDiscovery(final BluetoothGatt bluetoothGatt) {
        if (bluetoothGatt == null) {
            LogUtil.e("OperableBlePhysicalService", "gatt is null");
            return;
        }
        LogUtil.c("OperableBlePhysicalService", "onServiceDiscovery:", bluetoothGatt.getDevice());
        this.mServiceDiscoveryExecutor.d(new ExecuteActionInterface() { // from class: bic.5
            @Override // com.huawei.devicesdk.connect.retry.ExecuteActionInterface
            public boolean execute() {
                try {
                    return bluetoothGatt.getService(bip.d) != null;
                } catch (SecurityException e) {
                    LogUtil.e("OperableBlePhysicalService", "onServiceDiscovery SecurityException", bll.a(e));
                    return false;
                }
            }

            @Override // com.huawei.devicesdk.connect.retry.ExecuteActionInterface
            public String getActionName() {
                return "ServiceDiscovery";
            }
        }, new RetryCallbackInterface() { // from class: bic.1
            @Override // com.huawei.devicesdk.connect.retry.RetryCallbackInterface
            public void doRetryAction(int i) {
                LogUtil.a("OperableBlePhysicalService", "not found user data service", " find service num=", Integer.valueOf(bluetoothGatt.getServices().size()), blt.a(bic.this.mDeviceInfo));
                if (bic.this.mConnectHandler == null) {
                    LogUtil.a("OperableBlePhysicalService", "mConnectHandler is null", blt.a(bic.this.mDeviceInfo));
                    bic.this.updateDeviceConnectState(3, bln.e(2, 303));
                } else {
                    bic.this.mConnectHandler.c(2);
                    bic.this.mConnectHandler.d(2, 200L);
                    LogUtil.c("OperableBlePhysicalService", "send discovery message", blt.a(bic.this.mDeviceInfo));
                }
            }

            @Override // com.huawei.devicesdk.connect.retry.RetryCallbackInterface
            public void doSuccessAction() {
                bic.this.mBluetoothGatt = bluetoothGatt;
                bic.this.mConnectHandler.c(7);
                bic.this.mConnectHandler.d(7, 100L);
            }

            @Override // com.huawei.devicesdk.connect.retry.RetryCallbackInterface
            public void doFailureAction() {
                bic.this.updateDeviceConnectState(3, bln.e(2, 301));
            }
        }, true);
    }

    @Override // com.huawei.devicesdk.connect.physical.OpenBlePhysicalServiceBase
    public void onCharacteristicChange(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (bluetoothGattCharacteristic == null) {
            LogUtil.e("OperableBlePhysicalService", "characteristic is null");
            return;
        }
        String uuid = bluetoothGattCharacteristic.getUuid().toString();
        byte[] value = bluetoothGattCharacteristic.getValue();
        blt.d("OperableBlePhysicalService", value, "onCharacteristicChanged uuid:", uuid, blt.a(this.mDeviceInfo), " Device-->SDK:");
        onMessageReceived(uuid, value, 0);
    }

    @Override // com.huawei.devicesdk.connect.physical.OpenBlePhysicalServiceBase
    public boolean writeCharacteristicValue(BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr) {
        synchronized (this.c) {
            try {
                if (bluetoothGattCharacteristic == null) {
                    LogUtil.e("OperableBlePhysicalService", "characteristic is invalid.");
                    return false;
                }
                try {
                    bluetoothGattCharacteristic.setValue(bArr);
                    bluetoothGattCharacteristic.setWriteType(1);
                    BluetoothGatt bluetoothGatt = this.mBluetoothGatt;
                    if (bluetoothGatt == null) {
                        return false;
                    }
                    this.mIsLocked.set(true);
                    blt.d("OperableBlePhysicalService", bArr, "SDK-->Device:");
                    boolean writeCharacteristic = bluetoothGatt.writeCharacteristic(bluetoothGattCharacteristic);
                    LogUtil.c("OperableBlePhysicalService", "writeCharacteristicValue status: ", Boolean.valueOf(writeCharacteristic));
                    if (writeCharacteristic) {
                        lock(bluetoothGattCharacteristic.getUuid().toString());
                    } else {
                        this.mIsLocked.set(false);
                    }
                    return writeCharacteristic;
                } catch (SecurityException unused) {
                    LogUtil.e("OperableBlePhysicalService", "writeCharacteristicValue SecurityException.");
                    return false;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // com.huawei.devicesdk.connect.physical.OpenBlePhysicalServiceBase
    public void descriptorWrite(BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
        if (bluetoothGattDescriptor == null) {
            LogUtil.e("OperableBlePhysicalService", "descriptor is null");
        } else {
            LogUtil.c("OperableBlePhysicalService", "onCharacteristicWrite uuid:", bluetoothGattDescriptor.getCharacteristic().getUuid().toString(), " status:", Integer.valueOf(i), blt.a(this.mDeviceInfo));
        }
    }

    @Override // com.huawei.devicesdk.connect.physical.OpenBlePhysicalServiceBase
    public void characteristicWrite(BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
        if (bluetoothGattCharacteristic == null) {
            LogUtil.e("OperableBlePhysicalService", "characteristic is null");
        } else {
            LogUtil.c("OperableBlePhysicalService", "onCharacteristicWrite uuid:", bluetoothGattCharacteristic.getUuid().toString(), " status:", Integer.valueOf(i), blt.a(this.mDeviceInfo));
        }
    }

    @Override // com.huawei.devicesdk.connect.physical.OpenBlePhysicalServiceBase
    public void setDescriptorValue(BluetoothGattCharacteristic bluetoothGattCharacteristic, BluetoothGattDescriptor bluetoothGattDescriptor, List<byte[]> list) {
        if (bluetoothGattDescriptor == null || list == null) {
            LogUtil.e("OperableBlePhysicalService", "descriptor or descriptorValue is null");
            return;
        }
        Iterator<byte[]> it = list.iterator();
        while (it.hasNext()) {
            bluetoothGattDescriptor.setValue(it.next());
        }
    }
}
