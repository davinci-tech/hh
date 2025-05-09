package defpackage;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.btsportdevice.callback.CallbackBetweenClientAndController;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.device.model.DeviceInformation;
import com.huawei.health.device.model.FitnessData;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.reflect.Field;
import java.util.UUID;

/* loaded from: classes5.dex */
public class lal {
    private static final UUID c = UUID.fromString(BleConstants.CLIENT_CHARACTERISTIC_CONFIG_DESCRIPTOR_UUID);

    /* renamed from: a, reason: collision with root package name */
    protected String f14725a;
    private int b = 0;
    protected CallbackBetweenClientAndController d;
    private BluetoothGatt e;
    private final Object h;

    public void a(boolean z) {
    }

    public void bUX_(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
    }

    public void bUY_(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
    }

    public void bUZ_(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
    }

    public void bVa_(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
    }

    public void bVb_(BluetoothGatt bluetoothGatt, int i) {
    }

    public void bVc_(BluetoothGatt bluetoothGatt, int i) {
    }

    public void c() {
    }

    public DeviceInformation g() {
        return null;
    }

    public void j() {
    }

    public lal(BluetoothGatt bluetoothGatt, CallbackBetweenClientAndController callbackBetweenClientAndController, Object obj) {
        this.e = bluetoothGatt;
        this.d = callbackBetweenClientAndController;
        this.h = obj;
    }

    public void e(String str) {
        this.f14725a = str;
    }

    public void bUV_(BluetoothGatt bluetoothGatt) {
        this.e = bluetoothGatt;
    }

    public void b(int i) {
        this.b = i;
    }

    public void i() {
        LogUtil.c("Track_IDEQ_FitnessController", "resetControllerData");
    }

    protected void bUw_(BluetoothGattCharacteristic bluetoothGattCharacteristic, boolean z, boolean z2) {
        if (this.e == null || bluetoothGattCharacteristic == null) {
            LogUtil.h("Track_IDEQ_FitnessController", "BluetoothAdapter not initialized");
            return;
        }
        LogUtil.h("Track_IDEQ_FitnessController", "BluetoothAdapter initialized");
        LogUtil.a("Track_IDEQ_FitnessController", "FTMP_TS setCharacteristicNotification, mBluetoothGatt.setCharacteristicNotification:", bluetoothGattCharacteristic.getUuid().toString());
        try {
            ReleaseLogUtil.e("R_EcologyDevice_Track_IDEQ_FitnessController", "isNotifySuccess ? ", Boolean.valueOf(this.e.setCharacteristicNotification(bluetoothGattCharacteristic, z)));
            if (z2) {
                LogUtil.a("Track_IDEQ_FitnessController", "FTMP_TS setCharacteristicNotification, will mBluetoothGatt.writeDescriptor");
                BluetoothGattDescriptor descriptor = bluetoothGattCharacteristic.getDescriptor(c);
                if (descriptor == null) {
                    return;
                }
                if (z) {
                    descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
                } else {
                    descriptor.setValue(BluetoothGattDescriptor.DISABLE_NOTIFICATION_VALUE);
                }
                LogUtil.a("Track_IDEQ_FitnessController", "FTMP_TS setCharacteristicNotification, mBluetoothGatt.writeDescriptor:", bluetoothGattCharacteristic.getUuid().toString(), ", and Descriptor is ", descriptor.getUuid().toString());
                ReleaseLogUtil.e("R_EcologyDevice_Track_IDEQ_FitnessController", "isWriteOk ? ", Boolean.valueOf(this.e.writeDescriptor(descriptor)));
                return;
            }
            LogUtil.h("Track_IDEQ_FitnessController", "! enableNotification");
        } catch (SecurityException e) {
            ReleaseLogUtil.c("R_EcologyDevice_Track_IDEQ_FitnessController", "setCharacteristicNotification, ", ExceptionUtils.d(e));
        }
    }

    protected void bUv_(BluetoothGattCharacteristic bluetoothGattCharacteristic, boolean z, boolean z2) {
        if (this.e == null || bluetoothGattCharacteristic == null) {
            LogUtil.h("Track_IDEQ_FitnessController", "BluetoothAdapter not initialized");
            return;
        }
        LogUtil.h("Track_IDEQ_FitnessController", "BluetoothAdapter initialized");
        try {
            this.e.setCharacteristicNotification(bluetoothGattCharacteristic, z);
            if (z2) {
                BluetoothGattDescriptor descriptor = bluetoothGattCharacteristic.getDescriptor(c);
                if (descriptor == null) {
                    return;
                }
                if (z) {
                    descriptor.setValue(BluetoothGattDescriptor.ENABLE_INDICATION_VALUE);
                } else {
                    descriptor.setValue(BluetoothGattDescriptor.DISABLE_NOTIFICATION_VALUE);
                }
                LogUtil.a("Track_IDEQ_FitnessController", "FTMP_TS setCharacteristicIndication, mBluetoothGatt.writeDescriptor:", bluetoothGattCharacteristic.getUuid().toString(), ", and Descriptor is ", descriptor.getUuid().toString());
                LogUtil.a("Track_IDEQ_FitnessController", "writeDescriptor is success? ", Boolean.valueOf(this.e.writeDescriptor(descriptor)));
                return;
            }
            LogUtil.h("Track_IDEQ_FitnessController", "! enableIndication");
        } catch (SecurityException e) {
            ReleaseLogUtil.c("R_EcologyDevice_Track_IDEQ_FitnessController", "setCharacteristicIndication, ", ExceptionUtils.d(e));
        }
    }

    protected boolean h() {
        NoSuchFieldException e;
        boolean z;
        IllegalAccessException e2;
        BluetoothGatt bluetoothGatt = this.e;
        if (bluetoothGatt == null) {
            LogUtil.h("Track_IDEQ_FitnessController", "mBluetoothGatt is null");
            return false;
        }
        try {
            Field declaredField = bluetoothGatt.getClass().getDeclaredField("mDeviceBusy");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(this.e);
            z = obj instanceof Boolean ? ((Boolean) obj).booleanValue() : false;
            try {
                LogUtil.a("Track_IDEQ_FitnessController", "isDeviceBusy:", Boolean.valueOf(z));
            } catch (IllegalAccessException e3) {
                e2 = e3;
                LogUtil.b("Track_IDEQ_FitnessController", "IllegalAccessException:", e2.getMessage());
                return z;
            } catch (NoSuchFieldException e4) {
                e = e4;
                LogUtil.b("Track_IDEQ_FitnessController", "NoSuchFieldException:", e.getMessage());
                return z;
            }
        } catch (IllegalAccessException e5) {
            e2 = e5;
            z = false;
        } catch (NoSuchFieldException e6) {
            e = e6;
            z = false;
        }
        return z;
    }

    protected boolean bUx_(BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr) {
        boolean z;
        synchronized (this.h) {
            LogUtil.a("Track_IDEQ_FitnessController", "writeCharacteristicValueBytes.");
            if (bluetoothGattCharacteristic != null && this.e != null && bArr != null) {
                bluetoothGattCharacteristic.setValue(bArr);
                try {
                    z = this.e.writeCharacteristic(bluetoothGattCharacteristic);
                } catch (SecurityException e) {
                    ReleaseLogUtil.c("R_EcologyDevice_Track_IDEQ_FitnessController", "writeCharacteristic, ", ExceptionUtils.d(e));
                    z = false;
                }
                ReleaseLogUtil.e("R_EcologyDevice_Track_IDEQ_FitnessController", "writeCharacteristicValueBytes success? = ", Boolean.valueOf(z));
                return z;
            }
            LogUtil.h("Track_IDEQ_FitnessController", "WriteValueBytes: characteristic 、BluetoothGatt or bytes is null.");
            return false;
        }
    }

    protected void c(String str) {
        if (this.d != null) {
            LogUtil.a("Track_IDEQ_FitnessController", "notifyFitnessDeviceDataOrStateCallback, intentAction is ", str);
            this.d.notifyStateChanged(str);
        }
    }

    protected void e(FitnessData fitnessData) {
        if (this.d == null || fitnessData == null) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putSerializable("com.huawei.health.fitness.KEY_MESSAGE_FOR_CALLBACK_FITNESS_DATA", fitnessData.getFitnessHashMap());
        LogUtil.a("Track_IDEQ_FitnessController", "notifyFitnessDeviceDataOrStateCallback");
        this.d.notifyDataChange(902, bundle);
    }

    protected void d(FitnessData fitnessData, int i) {
        if (this.d == null || fitnessData == null) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putSerializable("com.huawei.health.fitness.KEY_MESSAGE_FOR_CALLBACK_FITNESS_DATA", fitnessData.getFitnessHashMap());
        bundle.putInt("com.huawei.health.fitness.KEY_MESSAGE_FOR_CALLBACK_CSAFESTATE", i);
        LogUtil.c("Track_IDEQ_FitnessController", "notifyFitnessDeviceDataOrStateCallback state = " + i);
        this.d.notifyDataChange(902, bundle);
    }

    protected void c(int i, String str) {
        LogUtil.c("Track_IDEQ_FitnessController", "notifyFitnessDeviceDataOrStateCallback intentAction = " + i);
        if (this.d != null) {
            Bundle bundle = new Bundle();
            if (!TextUtils.isEmpty(str)) {
                bundle.putString("com.huawei.health.fitness.KEY_MESSAGE_FOR_CALLBACK", str);
            }
            this.d.notifyDataChange(i, bundle);
        }
    }

    protected void b(boolean z, boolean z2, boolean z3, boolean z4) {
        if (this.d != null) {
            Bundle bundle = new Bundle();
            bundle.putBoolean("com.huawei.health.fitness.KEY_MESSAGE_BOOLEAN_ALLOWED_TO_SHOW_UI", z2);
            bundle.putBoolean("com.huawei.health.fitness.KEY_MESSAGE_BOOLEAN_GOTO_START_FROM_FINISH", z3);
            bundle.putBoolean("com.huawei.health.fitness.KEY_MESSAGE_BOOLEAN_HAS_NOT_DISCONNECTED", z);
            bundle.putBoolean("com.huawei.health.fitness.KEY_MESSAGE_BOOLEAN_PRESS_ON_STOP_BTN", z4);
            LogUtil.c("Track_IDEQ_FitnessController", "notifyCallbackToFinishThisSegment");
            this.d.notifyDataChange(905, bundle);
        }
    }
}
