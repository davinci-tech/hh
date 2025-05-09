package defpackage;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.hwlogsmodel.LogUtil;
import java.lang.reflect.Field;

/* loaded from: classes3.dex */
public class cyv {

    /* renamed from: a, reason: collision with root package name */
    private boolean f11545a = false;

    public boolean Sc_(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr) {
        if (bluetoothGatt == null || bluetoothGattCharacteristic == null) {
            return false;
        }
        boolean Sa_ = Sa_(1, bluetoothGatt, bArr, bluetoothGattCharacteristic);
        if (Sa_) {
            LogUtil.c("Ecology_BluetoothGattUtils", "writeCharacteristic succeed");
        } else {
            LogUtil.a("Ecology_BluetoothGattUtils", "writeCharacteristic failed");
        }
        return Sa_;
    }

    public boolean Sb_(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return Sc_(bluetoothGatt, bluetoothGattCharacteristic, null);
    }

    private boolean Sa_(int i, BluetoothGatt bluetoothGatt, byte[] bArr, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        long currentTimeMillis = System.currentTimeMillis();
        while (System.currentTimeMillis() - currentTimeMillis < 2000 && RZ_(bluetoothGatt)) {
            try {
                Thread.sleep(20L);
            } catch (InterruptedException e) {
                LogUtil.b("Ecology_BluetoothGattUtils", "InterruptedException:", e.getMessage());
            }
        }
        try {
            if (i == 1) {
                LogUtil.a("Ecology_BluetoothGattUtils", "writeCharacteristic ");
                this.f11545a = true;
                return bluetoothGatt.writeCharacteristic(bluetoothGattCharacteristic);
            }
            if (i != 2) {
                return false;
            }
            LogUtil.a("Ecology_BluetoothGattUtils", "readCharacteristic ");
            this.f11545a = true;
            return bluetoothGatt.readCharacteristic(bluetoothGattCharacteristic);
        } catch (SecurityException e2) {
            LogUtil.b("Ecology_BluetoothGattUtils", "optionCharcteristic SecurityException:", ExceptionUtils.d(e2));
            return false;
        }
    }

    public boolean Sd_(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor) {
        if (bluetoothGatt != null && bluetoothGattDescriptor != null) {
            long currentTimeMillis = System.currentTimeMillis();
            while (System.currentTimeMillis() - currentTimeMillis < 2000 && RZ_(bluetoothGatt)) {
                try {
                    Thread.sleep(20L);
                } catch (InterruptedException e) {
                    LogUtil.b("Ecology_BluetoothGattUtils", "InterruptedException:", e.getMessage());
                }
            }
            try {
                LogUtil.a("Ecology_BluetoothGattUtils", "writeDescriptor ");
                this.f11545a = true;
                boolean writeDescriptor = bluetoothGatt.writeDescriptor(bluetoothGattDescriptor);
                if (writeDescriptor) {
                    LogUtil.a("Ecology_BluetoothGattUtils", "writeDescriptor succeed");
                } else {
                    LogUtil.a("Ecology_BluetoothGattUtils", "writeDescriptor failed");
                }
                return writeDescriptor;
            } catch (SecurityException e2) {
                LogUtil.b("Ecology_BluetoothGattUtils", "writeDescriptor SecurityException:", ExceptionUtils.d(e2));
            }
        }
        return false;
    }

    private boolean RZ_(BluetoothGatt bluetoothGatt) {
        try {
            Field declaredField = bluetoothGatt.getClass().getDeclaredField("mDeviceBusy");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(bluetoothGatt);
            boolean booleanValue = obj instanceof Boolean ? ((Boolean) obj).booleanValue() : false;
            LogUtil.a("Ecology_BluetoothGattUtils", "isDeviceBusy:", Boolean.valueOf(booleanValue));
            return booleanValue;
        } catch (IllegalAccessException e) {
            LogUtil.b("Ecology_BluetoothGattUtils", Boolean.valueOf(this.f11545a), ":IllegalAccessException:", e.getMessage());
            return this.f11545a;
        } catch (NoSuchFieldException e2) {
            LogUtil.b("Ecology_BluetoothGattUtils", Boolean.valueOf(this.f11545a), ":NoSuchFieldException:", e2.getMessage());
            return this.f11545a;
        }
    }

    public void a(boolean z) {
        this.f11545a = z;
    }
}
