package defpackage;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.device.open.data.HealthDataParser;
import com.huawei.health.device.open.data.model.HealthData;
import com.huawei.hwlogsmodel.LogUtil;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/* loaded from: classes3.dex */
public class czt implements HealthDataParser {
    private static final Object e = new Object();

    @Override // com.huawei.health.device.open.data.HealthDataParser
    public HealthData parseData(byte[] bArr) {
        if (bArr == null) {
            LogUtil.h("BloodPressureDataParser", "parseData data is null");
            return new HealthData();
        }
        e eVar = new e(bArr);
        int e2 = eVar.e(17, 0);
        boolean z = (e2 & 2) != 0;
        boolean z2 = (e2 & 4) != 0;
        float d = eVar.d(50, 1);
        float d2 = eVar.d(50, 3);
        if (d <= 0.0f || d2 <= 0.0f) {
            LogUtil.h("BloodPressureDataParser", "parseData invalid bloodPressure");
            return new HealthData();
        }
        Calendar calendar = Calendar.getInstance();
        int i = 7;
        if (z) {
            calendar.set(1, eVar.e(18, 7));
            calendar.set(2, eVar.e(17, 9) - 1);
            calendar.set(5, eVar.e(17, 10));
            calendar.set(11, eVar.e(17, 11));
            calendar.set(12, eVar.e(17, 12));
            calendar.set(13, eVar.e(17, 13));
            i = 14;
        }
        float d3 = z2 ? eVar.d(50, i) : 0.0f;
        deo deoVar = new deo();
        deoVar.setSystolic((short) d);
        deoVar.setDiastolic((short) d2);
        deoVar.setType(HealthData.BLOODPRESURE);
        if (d3 > 0.0f) {
            deoVar.setHeartRate((short) d3);
        }
        LogUtil.a("BloodPressureDataParser", "parseData date is ", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime()));
        return d(calendar, deoVar, z);
    }

    private HealthData d(Calendar calendar, deo deoVar, boolean z) {
        LogUtil.a("BloodPressureDataParser", "parseData date is ", Long.valueOf(calendar.getTimeInMillis()), " data year: ", Integer.valueOf(calendar.get(1)));
        if (calendar.get(1) < 2021 || !z) {
            LogUtil.a("BloodPressureDataParser", "time is invalid");
            deoVar.setStartTime(System.currentTimeMillis());
            deoVar.setEndTime(System.currentTimeMillis());
        } else {
            LogUtil.a("BloodPressureDataParser", "time is valid");
            deoVar.setStartTime(calendar.getTimeInMillis());
            deoVar.setEndTime(calendar.getTimeInMillis());
        }
        return deoVar;
    }

    public int b(byte[] bArr) {
        if (bArr == null) {
            LogUtil.h("BloodPressureDataParser", "parseData data is null");
            return -1;
        }
        e eVar = new e(bArr);
        int e2 = eVar.e(17, 0);
        if (e2 > 255 || e2 < 0) {
            LogUtil.a("BloodPressureDataParser", "parseRopeDataCharacteristic, invalid command!");
            return -1;
        }
        if (e2 != 1) {
            LogUtil.a("BloodPressureDataParser", "parseBleControlCharacteristic, Unrecognized Blp control Data!");
            return -1;
        }
        int e3 = eVar.e(17, 1);
        LogUtil.a("BloodPressureDataParser", "OP_COMMAND_START_MEASURING measuringData is ", Integer.valueOf(e3));
        return e3;
    }

    public boolean Sj_(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i, int i2) {
        byte[] bArr;
        LogUtil.a("BloodPressureDataParser", "setBlpControl opCode: ", Integer.valueOf(i2));
        if (i > 255 || i < 0) {
            LogUtil.a("BloodPressureDataParser", "setBloodPressureControlUtil, invalid opCommand!");
            return false;
        }
        if (i2 > 255 || i2 < 0) {
            LogUtil.a("BloodPressureDataParser", "setBloodPressureControlUtil, invalid opcode!");
            return false;
        }
        if (i == 1) {
            bArr = new byte[]{(byte) (i & 255), (byte) (i2 & 255), 0, 0};
            LogUtil.a("BloodPressureDataParser", "setBloodPressureControlUtil opCommand: ", Integer.valueOf(i));
        } else {
            bArr = null;
        }
        if (bArr == null) {
            return false;
        }
        if (bluetoothGattCharacteristic != null) {
            if (Si_(bluetoothGatt, bluetoothGattCharacteristic, bArr)) {
                LogUtil.a("BloodPressureDataParser", "writeCharacteristicValueBytes success");
                return true;
            }
            LogUtil.a("BloodPressureDataParser", "writeCharacteristicValueBytes failed");
            return false;
        }
        LogUtil.a("BloodPressureDataParser", "mMeasuringCharacteristic is null");
        return false;
    }

    private boolean Si_(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr) {
        synchronized (e) {
            if (bluetoothGattCharacteristic != null && bluetoothGatt != null && bArr != null) {
                try {
                    bluetoothGattCharacteristic.setValue(bArr);
                    return bluetoothGatt.writeCharacteristic(bluetoothGattCharacteristic);
                } catch (SecurityException e2) {
                    LogUtil.b("BloodPressureDataParser", "writeCharacteristicValueBytes SecurityException:", ExceptionUtils.d(e2));
                    return false;
                }
            }
            LogUtil.h("BloodPressureDataParser", "WriteValueBytes: something is null.");
            if (bluetoothGattCharacteristic == null) {
                LogUtil.h("BloodPressureDataParser", "WriteValueBytes: characteristic is null.");
            }
            if (bluetoothGatt == null) {
                LogUtil.h("BloodPressureDataParser", "WriteValueBytes: mBluetoothGatt is null.");
            }
            return false;
        }
    }

    static class e {

        /* renamed from: a, reason: collision with root package name */
        private byte[] f11560a;

        private int a(int i, int i2) {
            int i3 = 1 << (i2 - 1);
            return (i & i3) != 0 ? (i3 - (i & (i3 - 1))) * (-1) : i;
        }

        private int b(byte b) {
            return b & 255;
        }

        private int c(int i) {
            return i & 15;
        }

        e(byte[] bArr) {
            this.f11560a = bArr;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int e(int i, int i2) {
            int c = c(i);
            byte[] bArr = this.f11560a;
            if (c + i2 > bArr.length) {
                return -1;
            }
            if (i == 17) {
                return b(bArr[i2]);
            }
            if (i == 18) {
                return e(bArr[i2], bArr[i2 + 1]);
            }
            if (i != 20) {
                return -1;
            }
            return a(bArr[i2], bArr[i2 + 1], bArr[i2 + 2], bArr[i2 + 3]);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public float d(int i, int i2) {
            int c = c(i);
            byte[] bArr = this.f11560a;
            if (c + i2 <= bArr.length && i == 50) {
                return d(bArr[i2], bArr[i2 + 1]);
            }
            return -1.0f;
        }

        private int e(byte b, byte b2) {
            return b(b) + (b(b2) << 8);
        }

        private int a(byte b, byte b2, byte b3, byte b4) {
            return b(b) + (b(b2) << 8) + (b(b3) << 16) + (b(b4) << 24);
        }

        private float d(byte b, byte b2) {
            return (float) (a(b(b) + ((b(b2) & 15) << 8), 12) * Math.pow(10.0d, a(b(b2) >> 4, 4)));
        }
    }
}
