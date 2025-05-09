package defpackage;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.huawei.btsportdevice.callback.CallbackBetweenClientAndController;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.health.device.api.IndoorEquipManagerApi;
import com.huawei.health.device.base.AbstractFitnessClient;
import com.huawei.health.device.model.DeviceInformation;
import com.huawei.health.device.model.FitnessData;
import com.huawei.hms.kit.awareness.status.CapabilityStatus;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.indoorequip.datastruct.MachineControlPointResponse;
import com.huawei.indoorequip.datastruct.MachineStatus;
import com.huawei.indoorequip.datastruct.SupportDataRange;
import com.huawei.indoorequip.datastruct.TrainingStatus;
import com.huawei.operation.ble.BleConstants;
import com.huawei.operation.utils.Constants;
import health.compact.a.CommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.UUID;

/* loaded from: classes5.dex */
public class lai extends lal {
    private int aa;
    private BluetoothGattCharacteristic ab;
    private int ac;
    private lam ad;
    private BluetoothGattCharacteristic ae;
    private Handler af;
    private e ag;
    private lap ah;
    private boolean ai;
    private int[] aj;
    private Set<Integer> ak;
    private boolean al;
    private int am;
    private int an;
    private int ap;
    private SupportDataRange as;
    private laj z;
    private static final UUID o = UUID.fromString("00002ACC-0000-1000-8000-00805f9b34fb");
    private static final UUID s = UUID.fromString("00002ACD-0000-1000-8000-00805f9b34fb");
    private static final UUID l = UUID.fromString("00002AD1-0000-1000-8000-00805f9b34fb");
    private static final UUID m = UUID.fromString("00002AD2-0000-1000-8000-00805f9b34fb");
    private static final UUID e = UUID.fromString("00002ACE-0000-1000-8000-00805f9b34fb");
    private static final UUID t = UUID.fromString("00002AD3-0000-1000-8000-00805f9b34fb");
    private static final UUID n = UUID.fromString("00002AD9-0000-1000-8000-00805f9b34fb");
    private static final UUID k = UUID.fromString("00002ADA-0000-1000-8000-00805f9b34fb");
    private static final UUID x = UUID.fromString("00002AD4-0000-1000-8000-00805f9b34fb");
    private static final UUID w = UUID.fromString("00002AD5-0000-1000-8000-00805f9b34fb");
    private static final UUID y = UUID.fromString("00002AD6-0000-1000-8000-00805f9b34fb");
    private static final UUID u = UUID.fromString("00002AD7-0000-1000-8000-00805f9b34fb");
    private static final UUID v = UUID.fromString("00002AD8-0000-1000-8000-00805f9b34fb");
    private static final UUID j = UUID.fromString("00002A29-0000-1000-8000-00805f9b34fb");
    private static final UUID i = UUID.fromString("00002A24-0000-1000-8000-00805f9b34fb");
    private static final UUID h = UUID.fromString("00002A25-0000-1000-8000-00805f9b34fb");
    private static final UUID b = UUID.fromString("00002A27-0000-1000-8000-00805f9b34fb");
    private static final UUID c = UUID.fromString("00002A26-0000-1000-8000-00805f9b34fb");
    private static final UUID g = UUID.fromString("00002A28-0000-1000-8000-00805f9b34fb");
    private static final UUID f = UUID.fromString("00002A23-0000-1000-8000-00805f9b34fb");
    private static final UUID p = UUID.fromString("00001826-0000-1000-8000-00805f9b34fb");
    private static final UUID r = UUID.fromString("0000180a-0000-1000-8000-00805f9b34fb");
    private static final UUID q = UUID.fromString("d18d2c10-c44c-11e8-a355-529269fb1459");

    public lai(BluetoothGatt bluetoothGatt, CallbackBetweenClientAndController callbackBetweenClientAndController, Object obj) {
        super(bluetoothGatt, callbackBetweenClientAndController, obj);
        this.af = new Handler(Looper.getMainLooper()) { // from class: lai.4
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                Bundle data = message.getData();
                if (data == null) {
                    return;
                }
                byte[] byteArray = data.getByteArray("speed");
                byte[] byteArray2 = data.getByteArray("power");
                byte[] byteArray3 = data.getByteArray("cadence");
                int i2 = data.getInt("opCode");
                int[] intArray = data.getIntArray("para");
                int i3 = message.what;
                if (i3 == 1) {
                    lai.this.d(byteArray, byteArray2, byteArray3);
                    return;
                }
                if (i3 == 2) {
                    lai.this.bUR_(byteArray, message);
                    return;
                }
                if (i3 == 3) {
                    lai.this.bUQ_(byteArray2, message);
                    return;
                }
                if (i3 == 4) {
                    lai.this.bUP_(byteArray3, message);
                } else {
                    if (i3 != 5) {
                        return;
                    }
                    LogUtil.a("Track_IDEQ_FtmpController", "start reSend");
                    lai.this.b(i2, intArray);
                }
            }
        };
        this.ai = false;
        this.aa = -1;
        this.al = false;
        this.ac = 0;
        this.an = -1;
        this.ap = 1;
        this.ah = new lap();
        this.ad = new lam();
        this.z = new laj(this.ah.a(), this.ad.e(), this.ah, true);
        this.ab = null;
        HashSet hashSet = new HashSet();
        this.ak = hashSet;
        hashSet.add(12);
        this.ak.add(13);
        this.as = new SupportDataRange();
    }

    @Override // defpackage.lal
    public void bUV_(BluetoothGatt bluetoothGatt) {
        super.bUV_(bluetoothGatt);
    }

    public void d() {
        this.aa = -1;
        this.ap = 1;
    }

    public void d(boolean z) {
        this.ai = z;
    }

    @Override // defpackage.lal
    public DeviceInformation g() {
        return this.ad.e();
    }

    public void a() {
        LogUtil.a("Track_IDEQ_FtmpController", "sendResitanceRange MIN_LEVEL ", Integer.valueOf(this.as.getMinLevel()), ", MAX_LEVEL ", Integer.valueOf(this.as.getMaxLevel()), ", INCREMENT_LEVEL ", Integer.valueOf(this.as.getMinIncrementLevel()));
        e(this.as);
    }

    public void b(byte[] bArr) {
        LogUtil.a("Track_IDEQ_FtmpController", "sendByteToEquip For FTMP, will writeCharacteristicValueBytes");
        bUx_(this.ab, bArr);
    }

    @Override // defpackage.lal
    public void i() {
        ReleaseLogUtil.e("R_EcologyDevice_Track_IDEQ_FtmpController", "resetControllerData");
        this.ai = false;
        this.z.b();
    }

    @Override // defpackage.lal
    public void a(boolean z) {
        e eVar = this.ag;
        if (eVar != null) {
            eVar.d(z);
        }
    }

    @Override // defpackage.lal
    public void bVb_(BluetoothGatt bluetoothGatt, int i2) {
        super.bVb_(bluetoothGatt, i2);
        if (i2 == 0) {
            bUW_(bluetoothGatt, false);
        } else {
            c(AbstractFitnessClient.ACTION_SERVICE_NOT_SUPPORT);
        }
    }

    @Override // defpackage.lal
    public void bVc_(BluetoothGatt bluetoothGatt, int i2) {
        if (i2 == 0) {
            bUW_(bluetoothGatt, true);
        } else {
            c(AbstractFitnessClient.ACTION_SERVICE_NOT_SUPPORT);
        }
    }

    public void bUW_(BluetoothGatt bluetoothGatt, boolean z) {
        if (bluetoothGatt == null) {
            LogUtil.h("Track_IDEQ_FtmpController", "cannot get service bcz mGatt is null");
            c(AbstractFitnessClient.ACTION_SERVICE_NOT_SUPPORT);
            return;
        }
        BluetoothGattService service = bluetoothGatt.getService(p);
        BluetoothGattService service2 = bluetoothGatt.getService(r);
        if (service != null) {
            LogUtil.a("Track_IDEQ_FtmpController", "FTMP_TS mFtmsService found");
            if (this.d != null) {
                this.d.setBreakBySelf(false);
            }
            LogUtil.a("Track_IDEQ_FtmpController", "isReconnect:", Boolean.valueOf(z));
            d();
            if (z) {
                c(AbstractFitnessClient.ACTION_SERVICE_REDISCOVERIED);
            } else {
                c(AbstractFitnessClient.ACTION_SERVICE_DISCOVERIED);
                b(0);
                c(901, (String) null);
                this.ai = false;
            }
            e eVar = new e(bluetoothGatt, true, service, service2);
            this.ag = eVar;
            jdx.b(eVar);
            return;
        }
        c(AbstractFitnessClient.ACTION_SERVICE_NOT_SUPPORT);
        LogUtil.h("Track_IDEQ_FtmpController", "FTMP_TS mFtmsService not found");
    }

    @Override // defpackage.lal
    public void bUZ_(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i2) {
        int i3 = this.am + 1;
        this.am = i3;
        LogUtil.a("Track_IDEQ_FtmpController", "onFitnessCharacteristicRead mReadCount ", Integer.valueOf(i3));
        super.bUZ_(bluetoothGatt, bluetoothGattCharacteristic, i2);
        if (bluetoothGattCharacteristic != null) {
            try {
                UUID uuid = bluetoothGattCharacteristic.getUuid();
                if (uuid != null) {
                    String upperCase = uuid.toString().toUpperCase(Locale.ENGLISH);
                    String e2 = lbv.e(bluetoothGattCharacteristic.getValue());
                    ReleaseLogUtil.e("R_EcologyDevice_Track_IDEQ_FtmpController", "onFitnessCharacteristicRead value ", e2);
                    if ("00002A29-0000-1000-8000-00805f9b34fb".toUpperCase(Locale.ENGLISH).equals(upperCase)) {
                        LogUtil.c("Track_IDEQ_FtmpController", "FTMP_TS onCharacteristicRead charUUID is", "CHARACTERISTIC_DIS_MANUFACTURER_NAME:", e2);
                        LogUtil.c("Track_IDEQ_FtmpController", "FTMP_TS onCharacteristicRead charUUID is", "CHARACTERISTIC_DIS_BLE_DEVICE_NAME:", bluetoothGatt.getDevice().getName());
                        this.ad.d(bluetoothGatt.getDevice().getName(), 20014);
                        bUI_(bluetoothGattCharacteristic, 20006);
                    } else if ("00002A24-0000-1000-8000-00805f9b34fb".toUpperCase(Locale.ENGLISH).equals(upperCase)) {
                        LogUtil.c("Track_IDEQ_FtmpController", "FTMP_TS onCharacteristicRead charUUID is", "CHARACTERISTIC_DIS_MODEL_NUMBER:", e2);
                        bUI_(bluetoothGattCharacteristic, 20007);
                    } else if ("00002A25-0000-1000-8000-00805f9b34fb".toUpperCase(Locale.ENGLISH).equals(upperCase)) {
                        LogUtil.c("Track_IDEQ_FtmpController", "FTMP_TS onCharacteristicRead charUUID is", "CHARACTERISTIC_DIS_SERIAL_NUMBER:", e2);
                        bUI_(bluetoothGattCharacteristic, 20010);
                    } else if ("00002A27-0000-1000-8000-00805f9b34fb".toUpperCase(Locale.ENGLISH).equals(upperCase)) {
                        LogUtil.c("Track_IDEQ_FtmpController", "FTMP_TS onCharacteristicRead charUUID is", "CHARACTERISTIC_DIS_HARDWARE_REVISION:", e2);
                        bUI_(bluetoothGattCharacteristic, CapabilityStatus.AWA_CAP_CODE_WIFI);
                    } else if ("00002A26-0000-1000-8000-00805f9b34fb".toUpperCase(Locale.ENGLISH).equals(upperCase)) {
                        LogUtil.c("Track_IDEQ_FtmpController", "FTMP_TS onCharacteristicRead charUUID is", "CHARACTERISTIC_DIS_FIRMWARE_REVISION:", e2);
                        bUI_(bluetoothGattCharacteristic, 20015);
                    } else if ("00002A28-0000-1000-8000-00805f9b34fb".toUpperCase(Locale.ENGLISH).equals(upperCase)) {
                        LogUtil.c("Track_IDEQ_FtmpController", "FTMP_TS onCharacteristicRead charUUID is", "CHARACTERISTIC_DIS_SOFTWARE_REVISION:", e2);
                        bUI_(bluetoothGattCharacteristic, CapabilityStatus.AWA_CAP_CODE_APPLICATION);
                    } else if ("00002A23-0000-1000-8000-00805f9b34fb".toUpperCase(Locale.ENGLISH).equals(upperCase)) {
                        LogUtil.c("Track_IDEQ_FtmpController", "FTMP_TS onCharacteristicRead charUUID is", "CHARACTERISTIC_DIS_SYSTEM_ID:", e2);
                        bUI_(bluetoothGattCharacteristic, 20016);
                    } else if ("00002ACC-0000-1000-8000-00805f9b34fb".toUpperCase(Locale.ENGLISH).equals(upperCase)) {
                        LogUtil.c("Track_IDEQ_FtmpController", "FTMP_TS onCharacteristicRead charUUID is", "CHARACTERISTIC_MACHINE_FEATURE:", e2);
                        e(this.ah.e(bluetoothGattCharacteristic.getValue()));
                    } else {
                        bUF_(bluetoothGattCharacteristic, upperCase);
                    }
                } else {
                    LogUtil.h("Track_IDEQ_FtmpController", "FTMP_TS onCharacteristicRead, charUUID is null");
                }
            } catch (SecurityException e3) {
                LogUtil.b("Track_IDEQ_FtmpController", "onFitnessCharacteristicRead SecurityException:", ExceptionUtils.d(e3));
            }
        }
    }

    private void bUI_(BluetoothGattCharacteristic bluetoothGattCharacteristic, int i2) {
        DeviceInformation b2 = this.ad.b(bluetoothGattCharacteristic.getValue(), i2);
        b2.setDeviceType(lbv.a(this.f14725a));
        e(b2);
    }

    /* JADX WARN: Removed duplicated region for block: B:6:0x0141  */
    /* JADX WARN: Removed duplicated region for block: B:9:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void bUF_(android.bluetooth.BluetoothGattCharacteristic r6, java.lang.String r7) {
        /*
            Method dump skipped, instructions count: 325
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.lai.bUF_(android.bluetooth.BluetoothGattCharacteristic, java.lang.String):void");
    }

    private boolean a(String str) {
        return "00002ACD-0000-1000-8000-00805f9b34fb".toUpperCase(Locale.ENGLISH).equals(str) || "00002AD1-0000-1000-8000-00805f9b34fb".toUpperCase(Locale.ENGLISH).equals(str) || "00002AD2-0000-1000-8000-00805f9b34fb".toUpperCase(Locale.ENGLISH).equals(str) || "00002ACE-0000-1000-8000-00805f9b34fb".toUpperCase(Locale.ENGLISH).equals(str);
    }

    @Override // defpackage.lal
    public void bUY_(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        FitnessData bUG_;
        LogUtil.a("Track_IDEQ_FtmpController", "FTMP_TS onFitnessCharacteristicChanged");
        super.bUY_(bluetoothGatt, bluetoothGattCharacteristic);
        if (bluetoothGattCharacteristic == null) {
            LogUtil.h("Track_IDEQ_FtmpController", "characteristic is null");
            return;
        }
        UUID uuid = bluetoothGattCharacteristic.getUuid();
        LogUtil.c("R_EcologyDevice_Track_IDEQ_FtmpController", "onFitnessCharacteristicChanged charUuid = ", uuid);
        if (uuid != null) {
            String upperCase = uuid.toString().toUpperCase(Locale.ENGLISH);
            String e2 = lbv.e(bluetoothGattCharacteristic.getValue());
            ReleaseLogUtil.e("R_EcologyDevice_Track_IDEQ_FtmpController", "onFitnessCharacteristicChanged value = ", e2);
            if (a(upperCase)) {
                LogUtil.c("Track_IDEQ_FtmpController", "FTMP_TS onCharacteristicRead charUUID is", "CHARACTERISTIC_IndoorEquipment_DATA:", e2);
                bUG_ = this.ah.b(bluetoothGattCharacteristic.getValue(), upperCase);
                this.z.c();
            } else if ("00002AD3-0000-1000-8000-00805f9b34fb".toUpperCase(Locale.ENGLISH).equals(upperCase)) {
                LogUtil.c("Track_IDEQ_FtmpController", "FTMP_TS onCharacteristicRead charUUID is", "CHARACTERISTIC_TRAINING_STATUS:", e2);
                bUG_ = this.ah.h(bluetoothGattCharacteristic.getValue());
                this.z.c();
            } else if ("00002ADA-0000-1000-8000-00805f9b34fb".toUpperCase(Locale.ENGLISH).equals(upperCase)) {
                LogUtil.c("Track_IDEQ_FtmpController", "FTMP_TS onCharacteristicRead charUUID is", "CHARACTERISTIC_MACHINE_STATUS:", e2);
                bUG_ = bUG_(bluetoothGattCharacteristic);
                if (bUG_ == null) {
                    return;
                }
            } else {
                bUE_(bluetoothGattCharacteristic, upperCase, e2);
                return;
            }
            e(bUG_);
            return;
        }
        LogUtil.h("Track_IDEQ_FtmpController", "FTMP_TS onCharacteristicChanged, charUUID is null");
    }

    private FitnessData bUG_(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        MachineStatus b2 = this.ah.b(bluetoothGattCharacteristic.getValue());
        if (b2 == null) {
            return b2;
        }
        this.z.c();
        if (b(b2)) {
            LogUtil.a("Track_IDEQ_FtmpController", "FTMP_TS onCharacteristicChanged get start signal!");
            this.ai = true;
        }
        if (b2.getOpCode() == 2 && b2.getMachineStatusCharacteristic() == 2) {
            LogUtil.a("Track_IDEQ_FtmpController", "FTMP_TS onCharacteristicChanged get pause signal, ", "set mIsExperiencedStateOfStart true as well");
            this.ai = true;
        }
        boolean c2 = c(b2);
        if (c2) {
            this.d.onStopByUser();
        }
        if (this.ai && c2) {
            LogUtil.a("Track_IDEQ_FtmpController", "FTMP_TS onCharacteristicChanged, get finish signal!");
            b(true, true, false, false);
            this.ai = false;
        }
        if (b2.getOpCode() == 255) {
            LogUtil.a("Track_IDEQ_FtmpController", "FTMP_TS onCharacteristicChanged, get lost signal!");
            b(true, true, false, false);
        }
        return b2;
    }

    public boolean b(MachineStatus machineStatus) {
        return machineStatus.getOpCode() == 4;
    }

    public boolean c(MachineStatus machineStatus) {
        if (machineStatus.getOpCode() != 3) {
            return machineStatus.getOpCode() == 2 && machineStatus.getMachineStatusCharacteristic() == 1;
        }
        return true;
    }

    private void bUE_(BluetoothGattCharacteristic bluetoothGattCharacteristic, String str, String str2) {
        if ("00002AD9-0000-1000-8000-00805f9b34fb".toUpperCase(Locale.ENGLISH).equals(str)) {
            LogUtil.c("Track_IDEQ_FtmpController", "FTMP_TS onCharacteristicChanged, charUUID is ", "CHARACTERISTIC_MACHINE_CONTROL_POINT:", str2);
            FitnessData a2 = this.ah.a(bluetoothGattCharacteristic.getValue());
            if (!(a2 instanceof MachineControlPointResponse)) {
                LogUtil.b("Track_IDEQ_FtmpController", " fitnessData instanceof MachineControlPointResponse is false");
                return;
            }
            MachineControlPointResponse machineControlPointResponse = (MachineControlPointResponse) a2;
            b(machineControlPointResponse);
            LogUtil.c("Track_IDEQ_FtmpController", "FTMP_TS onCharacteristicChanged, charUUID is CHARACTERISTIC_MACHINE_CONTROL_POINT:", "RequestOpCode is", Integer.valueOf(machineControlPointResponse.getRequestOpCode()), ", ResultCode is ", Integer.valueOf(machineControlPointResponse.getResultCode()));
            if (machineControlPointResponse.getRequestOpCode() == 7 && machineControlPointResponse.getResultCode() == 1) {
                LogUtil.a("Track_IDEQ_FtmpController", "FTMP_TS onCharacteristicChanged, get start signal!");
                this.ai = true;
            }
            if (this.ai && machineControlPointResponse.getRequestOpCode() == 8 && machineControlPointResponse.getResultCode() == 1 && machineControlPointResponse.getResultIntParameter() == 1) {
                LogUtil.a("Track_IDEQ_FtmpController", "FTMP_TS onCharacteristicChanged, get finish signal!");
                b(true, true, false, false);
                return;
            }
            return;
        }
        LogUtil.h("Track_IDEQ_FtmpController", "FTMP_TS onCharacteristicChanged, charUUID is NOT SUPPORT:", lbv.e(bluetoothGattCharacteristic.getValue()));
    }

    @Override // defpackage.lal
    public void bVa_(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i2) {
        super.bVa_(bluetoothGatt, bluetoothGattCharacteristic, i2);
        if (bluetoothGattCharacteristic == null) {
            LogUtil.b("Track_IDEQ_FtmpController", "onCharacteristicWrite: characteristic is null");
            return;
        }
        UUID uuid = bluetoothGattCharacteristic.getUuid();
        ReleaseLogUtil.e("R_EcologyDevice_Track_IDEQ_FtmpController", "onFitnessCharacteristicWrite value ", lbv.e(bluetoothGattCharacteristic.getValue()));
        if (n.equals(uuid)) {
            LogUtil.a("Track_IDEQ_FtmpController", "onCharacteristicWrite: characteristic ", uuid.toString());
        }
    }

    @Override // defpackage.lal
    public void bUX_(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i2) {
        int i3 = this.ac + 1;
        this.ac = i3;
        LogUtil.a("Track_IDEQ_FtmpController", "onDescriptorWrite: mDescriptorWriteCount:", Integer.valueOf(i3));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bUS_(BluetoothGatt bluetoothGatt, boolean z) {
        LogUtil.a("Track_IDEQ_FtmpController", "FTMP_TS setFtmpCharacteristicNotification(), mIsEnabled = ", Boolean.valueOf(z));
        BluetoothGattService service = bluetoothGatt.getService(UUID.fromString("00001826-0000-1000-8000-00805f9b34fb"));
        if (service != null) {
            BluetoothGattCharacteristic characteristic = service.getCharacteristic(n);
            if (characteristic != null) {
                LogUtil.a("Track_IDEQ_FtmpController", "FTMP_TS controlPointCharacteristic is not null");
                this.ab = characteristic;
            } else {
                LogUtil.h("Track_IDEQ_FtmpController", "FTMP_TS setFtmpCharacteristicNotification() controlPointCharacteristic is null");
                this.ab = null;
            }
            BluetoothGattCharacteristic characteristic2 = service.getCharacteristic(k);
            if (characteristic2 != null) {
                this.ac = 0;
                try {
                    Thread.sleep(150L);
                } catch (InterruptedException unused) {
                    LogUtil.b("Track_IDEQ_FtmpController", "InterruptedException");
                }
                LogUtil.a("Track_IDEQ_FtmpController", "FTMP_TS setFtmpCharacteristicNotification() machineStatusCharacteristic is not null");
                bUw_(characteristic2, true, true);
            } else {
                this.ac = 1;
                LogUtil.h("Track_IDEQ_FtmpController", "FTMP_TS setFtmpCharacteristicNotification() machineStatusCharacteristic is null");
            }
            BluetoothGattCharacteristic characteristic3 = service.getCharacteristic(t);
            if (characteristic3 != null) {
                if (c(1)) {
                    LogUtil.a("Track_IDEQ_FtmpController", "FTMP_TS checkWriteCount TRAINING_STATUS_WRITE_COUNT success");
                } else {
                    this.ac++;
                }
                LogUtil.a("Track_IDEQ_FtmpController", "FTMP_TS setFtmpCharacteristicNotification() trainingStatusCharacteristic is not null");
                bUw_(characteristic3, true, true);
            } else {
                this.ac++;
                LogUtil.h("Track_IDEQ_FtmpController", "FTMP_TS setFtmpCharacteristicNotification() trainingStatusCharacteristic is null");
            }
            bUT_(service);
        }
    }

    private boolean c(int i2) {
        LogUtil.a("Track_IDEQ_FtmpController", "checkWriteCount, writeCount:", Integer.valueOf(this.ac));
        int i3 = 0;
        while (this.ac != i2 && i3 < 5) {
            try {
                Thread.sleep(100L);
                i3++;
            } catch (InterruptedException unused) {
                LogUtil.b("Track_IDEQ_FtmpController", "InterruptedException");
            }
        }
        if (i3 < 5) {
            return true;
        }
        LogUtil.a("Track_IDEQ_FtmpController", "checkWriteCount type = ", Integer.valueOf(i2), ", return false");
        return false;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private UUID b() {
        char c2;
        UUID uuid = s;
        String str = this.f14725a;
        str.hashCode();
        switch (str.hashCode()) {
            case 1630:
                if (str.equals("31")) {
                    c2 = 0;
                    break;
                }
                c2 = 65535;
                break;
            case 49750:
                if (str.equals(BleConstants.SPORT_TYPE_BIKE)) {
                    c2 = 1;
                    break;
                }
                c2 = 65535;
                break;
            case 49772:
                if (str.equals("260")) {
                    c2 = 2;
                    break;
                }
                c2 = 65535;
                break;
            case 49773:
                if (str.equals("261")) {
                    c2 = 3;
                    break;
                }
                c2 = 65535;
                break;
            default:
                c2 = 65535;
                break;
        }
        if (c2 == 1) {
            return m;
        }
        if (c2 != 2) {
            return c2 != 3 ? uuid : l;
        }
        return e;
    }

    private void bUT_(BluetoothGattService bluetoothGattService) {
        UUID b2 = b();
        BluetoothGattCharacteristic characteristic = bluetoothGattService.getCharacteristic(b2);
        if (characteristic != null) {
            if (c(2)) {
                LogUtil.a("Track_IDEQ_FtmpController", "checkWriteCount TRAINER_DATA_WRITE_COUNT success");
            } else {
                this.ac++;
            }
            LogUtil.a("Track_IDEQ_FtmpController", "FTMP_TS setFtmpCharacteristicNotification() dataCharacteristic is not null", b2);
            bUw_(characteristic, true, true);
        } else {
            this.ac++;
            LogUtil.h("Track_IDEQ_FtmpController", "FTMP_TS setFtmpCharacteristicNotification() dataCharacteristic is null", b2);
        }
        if (this.ab != null) {
            if (c(3)) {
                LogUtil.a("Track_IDEQ_FtmpController", "checkWriteCount CONTROL_POINT_WRITE_COUNT success");
            } else {
                this.ac++;
            }
            LogUtil.a("Track_IDEQ_FtmpController", "FTMP_TS setFtmpCharacteristicNotification() mControlPointCharacteristic is not null");
            bUv_(this.ab, true, true);
            if (c(4)) {
                LogUtil.a("Track_IDEQ_FtmpController", "checkWriteCount REQUEST_CONTROL_WRITE_COUNT success");
            } else {
                this.ac++;
            }
            LogUtil.a("Track_IDEQ_FtmpController", "FTMP_TS setFtmpCharacteristicNotification(), now setFitnessMachineControl(write)");
            b(0, (int[]) null);
        } else {
            LogUtil.h("Track_IDEQ_FtmpController", "FTMP_TS setFtmpCharacteristicNotification() mControlPointCharacteristic is null");
            this.ac++;
        }
        this.ae = bluetoothGattService.getCharacteristic(q);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean bUK_(BluetoothGatt bluetoothGatt, BluetoothGattService bluetoothGattService, BluetoothGattService bluetoothGattService2) {
        if (bluetoothGatt == null || bluetoothGattService == null) {
            return false;
        }
        try {
            Thread.sleep(300L);
        } catch (InterruptedException unused) {
            LogUtil.b("Track_IDEQ_FtmpController", "InterruptedException");
        }
        this.am = -1;
        if (!bUM_(bluetoothGatt, bluetoothGattService) || !bUN_(bluetoothGatt, bluetoothGattService) || !bUL_(bluetoothGatt, bluetoothGattService2)) {
            return false;
        }
        this.al = true;
        c(AbstractFitnessClient.ACTION_READ_SUCCESS);
        return true;
    }

    private boolean bUM_(BluetoothGatt bluetoothGatt, BluetoothGattService bluetoothGattService) {
        LogUtil.a("Track_IDEQ_FtmpController", "readFtmpCharacter");
        if (!bUJ_(bluetoothGatt, bluetoothGattService, 0, o, "machineFeatureChar")) {
            LogUtil.a("Track_IDEQ_FtmpController", "read machineFeatureChar fail");
            return false;
        }
        if (bUJ_(bluetoothGatt, bluetoothGattService, 1, t, "trainingState")) {
            return true;
        }
        LogUtil.a("Track_IDEQ_FtmpController", "read trainingState fail");
        return false;
    }

    private boolean bUN_(BluetoothGatt bluetoothGatt, BluetoothGattService bluetoothGattService) {
        if (!bUO_(bluetoothGatt, bluetoothGattService, 2, x, "supportSpeedRange")) {
            LogUtil.a("Track_IDEQ_FtmpController", "read supportSpeedRange fail");
            return false;
        }
        if (!bUO_(bluetoothGatt, bluetoothGattService, 3, w, "supportInclinationRange")) {
            LogUtil.a("Track_IDEQ_FtmpController", "read supportInclinationRange fail");
            return false;
        }
        if (!bUO_(bluetoothGatt, bluetoothGattService, 4, y, "supportResistanceLevelRange")) {
            LogUtil.a("Track_IDEQ_FtmpController", "read supportResistanceLevelRange fail");
            return false;
        }
        if (!bUO_(bluetoothGatt, bluetoothGattService, 5, u, "supportHeartRateRange")) {
            LogUtil.a("Track_IDEQ_FtmpController", "read supportHeartRateRange fail");
            return false;
        }
        if (bUO_(bluetoothGatt, bluetoothGattService, 6, v, "supportPowerRange")) {
            return true;
        }
        LogUtil.a("Track_IDEQ_FtmpController", "read supportPowerRange fail");
        return false;
    }

    private boolean bUL_(BluetoothGatt bluetoothGatt, BluetoothGattService bluetoothGattService) {
        if (bluetoothGattService == null) {
            LogUtil.h("Track_IDEQ_FtmpController", "FTMP_TS mDisService not found");
            return false;
        }
        if (!bUJ_(bluetoothGatt, bluetoothGattService, 7, j, "Manufacturer Name String")) {
            LogUtil.a("Track_IDEQ_FtmpController", "read Manufacturer Name String fail");
            return false;
        }
        if (!bUJ_(bluetoothGatt, bluetoothGattService, 8, i, "Model Number String")) {
            LogUtil.a("Track_IDEQ_FtmpController", "read Model Number String fail");
            return false;
        }
        if (!bUJ_(bluetoothGatt, bluetoothGattService, 9, h, "Serial Number String")) {
            LogUtil.a("Track_IDEQ_FtmpController", "read Serial Number String fail");
            return false;
        }
        if (!bUJ_(bluetoothGatt, bluetoothGattService, 10, b, "Hardware Revision String")) {
            LogUtil.a("Track_IDEQ_FtmpController", "read Hardware Revision String fail");
            return false;
        }
        if (!bUJ_(bluetoothGatt, bluetoothGattService, 11, c, "Firmware Revision String")) {
            LogUtil.a("Track_IDEQ_FtmpController", "read Firmware Revision String fail");
            return false;
        }
        if (!bUJ_(bluetoothGatt, bluetoothGattService, 12, g, "Software Revision String")) {
            LogUtil.a("Track_IDEQ_FtmpController", "read Software Revision String fail");
            return false;
        }
        if (bUJ_(bluetoothGatt, bluetoothGattService, 13, f, "System ID")) {
            return true;
        }
        LogUtil.a("Track_IDEQ_FtmpController", "read System ID fail");
        return false;
    }

    private boolean bUJ_(BluetoothGatt bluetoothGatt, BluetoothGattService bluetoothGattService, int i2, UUID uuid, String str) {
        BluetoothGattCharacteristic characteristic = bluetoothGattService.getCharacteristic(uuid);
        LogUtil.a("Track_IDEQ_FtmpController", "enter isReadCharacteristicSuccess, tag is : ", str);
        if (characteristic == null) {
            LogUtil.h("Track_IDEQ_FtmpController", "FTMP_TS readCharacteristic : ", str, " is null");
            return false;
        }
        return bUU_(bluetoothGatt, i2, str, characteristic);
    }

    private boolean bUO_(BluetoothGatt bluetoothGatt, BluetoothGattService bluetoothGattService, int i2, UUID uuid, String str) {
        BluetoothGattCharacteristic characteristic = bluetoothGattService.getCharacteristic(uuid);
        LogUtil.a("Track_IDEQ_FtmpController", "enter isReadSupportRangeCharacterSuccess, tag is ：", str);
        if (characteristic == null) {
            LogUtil.h("Track_IDEQ_FtmpController", "FTMP_TS readCharacteristic: ", str, " is null");
            this.am++;
            return true;
        }
        return bUU_(bluetoothGatt, i2, str, characteristic);
    }

    private boolean bUU_(BluetoothGatt bluetoothGatt, int i2, String str, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        boolean z;
        LogUtil.a("Track_IDEQ_FtmpController", " readCount = ", Integer.valueOf(i2), " CurrentReadCount = ", Integer.valueOf(this.am));
        try {
            z = bluetoothGatt.readCharacteristic(bluetoothGattCharacteristic);
        } catch (SecurityException e2) {
            ReleaseLogUtil.c("R_EcologyDevice_Track_IDEQ_FtmpController", "waitingReadResult, ", ExceptionUtils.d(e2));
            z = false;
        }
        LogUtil.a("Track_IDEQ_FtmpController", "FTMP readCharacteristic: ", str, " isSuccess = ", Boolean.valueOf(z));
        if (!z) {
            return false;
        }
        int i3 = 0;
        while (d(i2, i3)) {
            try {
                Thread.sleep(100L);
            } catch (InterruptedException unused) {
                LogUtil.b("Track_IDEQ_FtmpController", "InterruptedException");
            }
            i3++;
        }
        if (i3 < 10 && lau.d().j()) {
            return true;
        }
        LogUtil.a("Track_IDEQ_FtmpController", "readCharacteristic(", str, Constants.RIGHT_BRACKET_ONLY, "loop larger than 10 times, fail");
        return false;
    }

    private boolean d(int i2, int i3) {
        boolean z = this.am != i2 && i3 < 10 && lau.d().j();
        LogUtil.a("Track_IDEQ_FtmpController", "isReadSuccess ", Boolean.valueOf(z), " mReadCount ", Integer.valueOf(this.am));
        return z;
    }

    public void d(int[] iArr) {
        d(MachineControlPointResponse.OP_CODE_EXTENSION_SET_UNLOCK_CODE, iArr, false);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public void b(Map<String, String> map) {
        char c2;
        if (map == null) {
            LogUtil.h("Track_IDEQ_FtmpController", "map is null");
            return;
        }
        TreeMap<Integer, byte[]> treeMap = new TreeMap<>();
        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
        int i2 = 0;
        int i3 = 0;
        while (true) {
            int i4 = 1;
            if (!it.hasNext()) {
                if (i2 == 0) {
                    LogUtil.h("Track_IDEQ_FtmpController", "valueLength is null");
                    return;
                }
                byte[] bArr = new byte[i2 + 2];
                bArr[0] = (byte) (i3 & 255);
                bArr[1] = (byte) ((i3 >> 8) & 255);
                for (Map.Entry<Integer, byte[]> entry : treeMap.entrySet()) {
                    for (byte b2 : entry.getValue()) {
                        i4++;
                        bArr[i4] = b2;
                    }
                    LogUtil.a("Track_IDEQ_FtmpController", "allValueMap key is ", entry.getKey());
                }
                d(bArr);
                return;
            }
            Map.Entry<String, String> next = it.next();
            String value = next.getValue();
            LogUtil.a("Track_IDEQ_FtmpController", next.getKey(), ":", value);
            String key = next.getKey();
            key.hashCode();
            switch (key.hashCode()) {
                case -1591317067:
                    if (key.equals(IndoorEquipManagerApi.KEY_TARGETED_EXPENDED_ENERGY)) {
                        c2 = 0;
                        break;
                    }
                    c2 = 65535;
                    break;
                case -1142262036:
                    if (key.equals(IndoorEquipManagerApi.KEY_TOTAL_ENERGY)) {
                        c2 = 1;
                        break;
                    }
                    c2 = 65535;
                    break;
                case -859914415:
                    if (key.equals(IndoorEquipManagerApi.KEY_UNLOCK_CODE)) {
                        c2 = 2;
                        break;
                    }
                    c2 = 65535;
                    break;
                case 96605095:
                    if (key.equals(IndoorEquipManagerApi.KEY_DYNAMIC_ENERGY)) {
                        c2 = 3;
                        break;
                    }
                    c2 = 65535;
                    break;
                case 200416838:
                    if (key.equals(IndoorEquipManagerApi.KEY_HEART_RATE)) {
                        c2 = 4;
                        break;
                    }
                    c2 = 65535;
                    break;
                case 767869522:
                    if (key.equals(IndoorEquipManagerApi.KEY_SUPPRESS_AUTO_PAUSE)) {
                        c2 = 5;
                        break;
                    }
                    c2 = 65535;
                    break;
                case 1319025059:
                    if (key.equals(IndoorEquipManagerApi.KEY_STEP_COUNT)) {
                        c2 = 6;
                        break;
                    }
                    c2 = 65535;
                    break;
                default:
                    c2 = 65535;
                    break;
            }
            switch (c2) {
                case 0:
                    if (d(treeMap, value, 6)) {
                        i3 += 64;
                        i2 += 2;
                        break;
                    } else {
                        break;
                    }
                case 1:
                    if (d(treeMap, value, 2)) {
                        i3 += 4;
                        i2 += 2;
                        break;
                    } else {
                        break;
                    }
                case 2:
                    if (b(treeMap, value)) {
                        i3++;
                        i2 += 6;
                        break;
                    } else {
                        break;
                    }
                case 3:
                    if (d(treeMap, value, 3)) {
                        i3 += 8;
                        i2 += 2;
                        break;
                    } else {
                        break;
                    }
                case 4:
                    if (a(treeMap, value, 1)) {
                        i3 += 2;
                        i2++;
                        break;
                    } else {
                        break;
                    }
                case 5:
                    if (a(treeMap, value, 5)) {
                        i3 += 32;
                        i2++;
                        break;
                    } else {
                        break;
                    }
                case 6:
                    if (d(treeMap, value, 4)) {
                        i3 += 16;
                        i2 += 2;
                        break;
                    } else {
                        break;
                    }
            }
        }
    }

    private boolean b(TreeMap<Integer, byte[]> treeMap, String str) {
        if (TextUtils.isEmpty(str) || str.length() != 6) {
            LogUtil.a("Track_IDEQ_FtmpController", "unlock code is error");
            return false;
        }
        byte[] bArr = new byte[6];
        for (int i2 = 0; i2 < str.length(); i2++) {
            bArr[i2] = (byte) (CommonUtil.a(String.valueOf(str.charAt(i2)), 10) & 255);
            LogUtil.a("Track_IDEQ_FtmpController", "addUnlockCode unlockCode: " + ((int) bArr[i2]));
        }
        treeMap.put(0, bArr);
        return true;
    }

    private boolean a(TreeMap<Integer, byte[]> treeMap, String str, int i2) {
        int a2 = CommonUtil.a(str, 10);
        if (a2 < 0 || a2 > 255) {
            return false;
        }
        treeMap.put(Integer.valueOf(i2), new byte[]{(byte) (a2 & 255)});
        return true;
    }

    private boolean d(TreeMap<Integer, byte[]> treeMap, String str, int i2) {
        int a2 = CommonUtil.a(str, 10);
        if (a2 < 0 || a2 > 65535) {
            return false;
        }
        treeMap.put(Integer.valueOf(i2), new byte[]{(byte) (a2 & 255), (byte) ((a2 >> 8) & 255)});
        return true;
    }

    public void a(int i2, int[] iArr) {
        b(lbv.e(i2), iArr);
    }

    public void b(int i2, int[] iArr) {
        byte[] bArr = null;
        if (this.aa != 1 && this.ak.contains(Integer.valueOf(i2))) {
            LogUtil.a("Track_IDEQ_FtmpController", "setFitnessMachineControl request control");
            b(0, (int[]) null);
            this.an = i2;
            this.aj = iArr;
            return;
        }
        ReleaseLogUtil.e("R_EcologyDevice_Track_IDEQ_FtmpController", "setFitnessMachineControl opCode = ", Integer.valueOf(i2), " para = ", Arrays.toString(iArr));
        if (e(i2, iArr)) {
            return;
        }
        if (i2 > 255 || i2 < 0) {
            LogUtil.c("Track_IDEQ_FtmpController", "setFitnessMachineControl, invalid opcode!");
            return;
        }
        if (i2 != 0 && i2 != 1) {
            if (i2 != 2 && i2 != 3 && i2 != 5) {
                if (i2 != 21) {
                    switch (i2) {
                        case 7:
                            break;
                        case 8:
                            bArr = g(i2, iArr);
                            break;
                        case 9:
                        case 10:
                        case 11:
                        case 13:
                            break;
                        case 12:
                            bArr = i(i2, iArr);
                            break;
                        default:
                            a(i2);
                            break;
                    }
                } else {
                    bArr = f(i2, iArr);
                }
                LogUtil.c("Track_IDEQ_FtmpController", "setControlPoint, value is  ", Arrays.toString(bArr));
                d(i2, iArr, bUx_(this.ab, bArr));
            }
            bArr = h(i2, iArr);
            LogUtil.c("Track_IDEQ_FtmpController", "setControlPoint, value is  ", Arrays.toString(bArr));
            d(i2, iArr, bUx_(this.ab, bArr));
        }
        bArr = new byte[]{(byte) (i2 & 255)};
        LogUtil.c("Track_IDEQ_FtmpController", "setControlPoint, value is  ", Arrays.toString(bArr));
        d(i2, iArr, bUx_(this.ab, bArr));
    }

    private void d(int i2, int[] iArr, boolean z) {
        LogUtil.c("Track_IDEQ_FtmpController", "reSendMessage, isSuccess is  ", Boolean.valueOf(z));
        if (z) {
            return;
        }
        LogUtil.a("Track_IDEQ_FtmpController", "reSend");
        Bundle bundle = new Bundle();
        bundle.putInt("opCode", i2);
        bundle.putIntArray("para", iArr);
        Message obtainMessage = this.af.obtainMessage();
        obtainMessage.what = 5;
        obtainMessage.setData(bundle);
        this.af.sendMessageDelayed(obtainMessage, 500L);
        LogUtil.a("Track_IDEQ_FtmpController", "reSend end");
    }

    private void a(int i2) {
        if (i2 != 4 && i2 != 6) {
            switch (i2) {
            }
        }
        LogUtil.a("Track_IDEQ_FtmpController", "op code ", Integer.valueOf(i2), "do not support yet!");
    }

    private byte[] i(int i2, int[] iArr) {
        if (iArr == null) {
            LogUtil.b("Track_IDEQ_FtmpController", "setFitnessMachineControl, para is null");
            return null;
        }
        int i3 = iArr[0];
        if (i3 <= 16777215 && i3 >= 0) {
            return new byte[]{(byte) (i2 & 255), (byte) (i3 & 255), (byte) ((i3 >> 8) & 255), (byte) ((i3 >> 16) & 255)};
        }
        LogUtil.b("Track_IDEQ_FtmpController", "setFitnessMachineControl, para is invalid");
        return null;
    }

    private byte[] g(int i2, int[] iArr) {
        if (iArr == null) {
            LogUtil.b("Track_IDEQ_FtmpController", "setFitnessMachineControl, para is null");
            return null;
        }
        int i3 = iArr[0];
        if (i3 <= 255 && i3 >= 0) {
            return new byte[]{(byte) (i2 & 255), (byte) (i3 & 255)};
        }
        LogUtil.b("Track_IDEQ_FtmpController", "setFitnessMachineControl, para is invalid");
        return null;
    }

    private byte[] h(int i2, int[] iArr) {
        if (iArr == null) {
            LogUtil.b("Track_IDEQ_FtmpController", "setFitnessMachineControl para is null");
            return null;
        }
        int i3 = iArr[0];
        if (i3 <= 65535 && i3 >= 0) {
            return new byte[]{(byte) (i2 & 255), (byte) (i3 & 255), (byte) ((i3 >> 8) & 255)};
        }
        LogUtil.b("Track_IDEQ_FtmpController", "setFitnessMachineControl para is invalid");
        return null;
    }

    private byte[] f(int i2, int[] iArr) {
        if (iArr == null) {
            LogUtil.b("Track_IDEQ_FtmpController", "setFitnessMachineControl para is null");
            return null;
        }
        int i3 = iArr[0];
        if (i3 <= 65535 && i3 >= 0) {
            return new byte[]{(byte) (i2 & 255), (byte) (i3 & 255)};
        }
        LogUtil.b("Track_IDEQ_FtmpController", "setFitnessMachineControl para is invalid");
        return null;
    }

    private boolean e(int i2, int[] iArr) {
        if (i2 > 65535 || i2 < 0) {
            LogUtil.b("Track_IDEQ_FtmpController", "handleHuaweiExtensionMachineControl para is invalid");
            return true;
        }
        return d(i2, iArr);
    }

    private boolean d(int i2, int[] iArr) {
        byte[] b2;
        switch (i2) {
            case 160:
                if (!a(iArr)) {
                    b2 = b(iArr, 2);
                    break;
                } else {
                    return true;
                }
            case MachineControlPointResponse.OP_CODE_EXTENSION_SET_TOTAL_ENERGY /* 161 */:
                if (!e(iArr)) {
                    b2 = c(iArr, 4);
                    break;
                } else {
                    return true;
                }
            case MachineControlPointResponse.OP_CODE_EXTENSION_SET_DYNAMIC_ENERGY /* 162 */:
                if (!e(iArr)) {
                    b2 = c(iArr, 8);
                    break;
                } else {
                    return true;
                }
            case MachineControlPointResponse.OP_CODE_EXTENSION_SET_STEP_COUNT /* 163 */:
                if (!e(iArr)) {
                    b2 = c(iArr, 16);
                    break;
                } else {
                    return true;
                }
            case MachineControlPointResponse.OP_CODE_EXTENSION_MULTI_DATA /* 164 */:
                if (!c(iArr)) {
                    b2 = a(iArr, 18);
                    break;
                } else {
                    return true;
                }
            case MachineControlPointResponse.OP_CODE_EXTENSION_SET_UNLOCK_CODE /* 165 */:
            case MachineControlPointResponse.OP_CODE_EXTENSION_SET_SUPPRESS_AUTO_PAUSE /* 166 */:
            case MachineControlPointResponse.OP_CODE_EXTENSION_SET_TARGETED_EXPENDED_ENERGY /* 167 */:
                return c(i2, iArr);
            default:
                return false;
        }
        return d(b2);
    }

    private boolean c(int i2, int[] iArr) {
        byte[] e2;
        int i3;
        switch (i2) {
            case MachineControlPointResponse.OP_CODE_EXTENSION_SET_UNLOCK_CODE /* 165 */:
                if (b(iArr)) {
                    LogUtil.a("Track_IDEQ_FtmpController", "para is null or unlock code is error");
                    return true;
                }
                e2 = e(iArr, 1);
                break;
            case MachineControlPointResponse.OP_CODE_EXTENSION_SET_SUPPRESS_AUTO_PAUSE /* 166 */:
                if (iArr == null || (i3 = iArr[0]) > 255 || i3 < 0) {
                    LogUtil.a("Track_IDEQ_FtmpController", "para is null or para[0] is invalid");
                    return true;
                }
                e2 = d(iArr, 32);
                break;
            case MachineControlPointResponse.OP_CODE_EXTENSION_SET_TARGETED_EXPENDED_ENERGY /* 167 */:
                if (!e(iArr)) {
                    e2 = c(iArr, 64);
                    break;
                } else {
                    return true;
                }
            default:
                e2 = null;
                break;
        }
        return d(e2);
    }

    private byte[] d(int[] iArr, int i2) {
        byte[] bArr = new byte[3];
        bArr[c(bArr, i2)] = (byte) (iArr[0] & 255);
        return bArr;
    }

    private int c(byte[] bArr, int i2) {
        bArr[0] = (byte) (i2 & 255);
        bArr[1] = (byte) ((i2 >> 8) & 255);
        return 2;
    }

    private boolean b(int[] iArr) {
        return iArr == null || iArr.length > 6;
    }

    public boolean e(int i2, int i3) {
        byte[] h2 = h(2, new int[]{i2 * 100});
        byte[] h3 = h(5, new int[]{i3});
        byte[] h4 = h(20, new int[]{i2 * 2});
        if (h3 == null || h2 == null || h4 == null) {
            LogUtil.a("Track_IDEQ_FtmpController", "cadence or power is null");
            return false;
        }
        d(h2, h3, h4);
        return true;
    }

    public void e(boolean z) {
        b(MachineControlPointResponse.OP_CODE_EXTENSION_SET_SUPPRESS_AUTO_PAUSE, new int[]{z ? 1 : 0});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        LogUtil.a("Track_IDEQ_FtmpController", "handleResistanceCommand");
        if (h()) {
            this.af.sendMessageDelayed(bUH_(bArr, bArr2, bArr3, 1), 100L);
        } else {
            b(bArr, bArr2, bArr3);
        }
    }

    private Message bUH_(byte[] bArr, byte[] bArr2, byte[] bArr3, int i2) {
        Bundle bundle = new Bundle();
        bundle.putByteArray("speed", bArr);
        bundle.putByteArray("power", bArr2);
        bundle.putByteArray("cadence", bArr3);
        Message obtainMessage = this.af.obtainMessage();
        obtainMessage.what = i2;
        obtainMessage.setData(bundle);
        return obtainMessage;
    }

    private void b(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        LogUtil.a("Track_IDEQ_FtmpController", "sendIndoorBikeResistance");
        boolean bUx_ = bUx_(this.ab, bArr);
        ReleaseLogUtil.e("R_EcologyDevice_Track_IDEQ_FtmpController", "sendIndoorBikeResistance sendSpeed result ", Boolean.valueOf(bUx_));
        if (!bUx_) {
            this.af.sendMessageDelayed(bUH_(bArr, bArr2, bArr3, 2), 100L);
            return;
        }
        boolean bUx_2 = bUx_(this.ab, bArr2);
        ReleaseLogUtil.e("R_EcologyDevice_Track_IDEQ_FtmpController", "sendIndoorBikeResistance sendPower result ", Boolean.valueOf(bUx_2));
        if (!bUx_2) {
            this.af.sendMessageDelayed(bUH_(bArr, bArr2, bArr3, 3), 100L);
            return;
        }
        boolean bUx_3 = bUx_(this.ab, bArr3);
        ReleaseLogUtil.e("R_EcologyDevice_Track_IDEQ_FtmpController", "sendIndoorBikeResistance sendCadence result = ", Boolean.valueOf(bUx_3));
        if (!bUx_3) {
            this.af.sendMessageDelayed(bUH_(bArr, bArr2, bArr3, 4), 100L);
        } else {
            ObserverManagerUtil.c("DEVICE_CONTROL", "INDOOR_BIKE_RESISTANCE_CONTROL", 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bUR_(byte[] bArr, Message message) {
        LogUtil.a("Track_IDEQ_FtmpController", "sendSpeed");
        boolean bUx_ = bUx_(this.ab, bArr);
        ReleaseLogUtil.e("R_EcologyDevice_Track_IDEQ_FtmpController", "sendSpeed result ", Boolean.valueOf(bUx_));
        if (!bUx_) {
            this.af.sendMessageDelayed(Message.obtain(message), 100L);
            return;
        }
        Message obtain = Message.obtain(message);
        obtain.what = 3;
        this.af.sendMessageDelayed(obtain, 100L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bUQ_(byte[] bArr, Message message) {
        LogUtil.a("Track_IDEQ_FtmpController", "sendPower");
        boolean bUx_ = bUx_(this.ab, bArr);
        ReleaseLogUtil.e("R_EcologyDevice_Track_IDEQ_FtmpController", "sendPower result ", Boolean.valueOf(bUx_));
        if (!bUx_) {
            this.af.sendMessageDelayed(Message.obtain(message), 100L);
            return;
        }
        Message obtain = Message.obtain(message);
        obtain.what = 4;
        this.af.sendMessageDelayed(obtain, 100L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bUP_(byte[] bArr, Message message) {
        LogUtil.a("Track_IDEQ_FtmpController", "sendCadence");
        boolean bUx_ = bUx_(this.ab, bArr);
        ReleaseLogUtil.e("R_EcologyDevice_Track_IDEQ_FtmpController", "sendCadence result ", Boolean.valueOf(bUx_));
        if (!bUx_) {
            this.af.sendMessageDelayed(Message.obtain(message), 100L);
        } else {
            ObserverManagerUtil.c("DEVICE_CONTROL", "INDOOR_BIKE_RESISTANCE_CONTROL", 1);
        }
    }

    private byte[] e(int[] iArr, int i2) {
        byte[] bArr = new byte[8];
        for (int c2 = c(bArr, i2); c2 < iArr.length + 2; c2++) {
            bArr[c2] = (byte) (iArr[c2 - 2] & 255);
            LogUtil.a("Track_IDEQ_FtmpController", "parseParaOfExtensionMultiData: " + ((int) bArr[c2]));
        }
        LogUtil.a("Track_IDEQ_FtmpController", "parseParaOfExtensionMultiData value : " + Arrays.toString(bArr));
        return bArr;
    }

    private boolean d(byte[] bArr) {
        if (this.ae == null) {
            LogUtil.a("Track_IDEQ_FtmpController", "HuaWeiExtensionCharacteristic is null");
            return false;
        }
        LogUtil.a("Track_IDEQ_FtmpController", "handleHuaWeiExtensionMachineControl, value is ", Arrays.toString(bArr));
        bUx_(this.ae, bArr);
        return true;
    }

    private void b(MachineControlPointResponse machineControlPointResponse) {
        int responseOpCode = machineControlPointResponse.getResponseOpCode();
        LogUtil.a("Track_IDEQ_FtmpController", "(FTMP_TS) in processMachineControlPointIndication, responseOpCode is ", Integer.valueOf(responseOpCode));
        if (responseOpCode != 128) {
            LogUtil.c("Track_IDEQ_FtmpController", "processMachineControlPointIndication error");
            return;
        }
        int requestOpCode = machineControlPointResponse.getRequestOpCode();
        int resultCode = machineControlPointResponse.getResultCode();
        ReleaseLogUtil.e("R_EcologyDevice_Track_IDEQ_FtmpController", "requestOpCode is ", Integer.valueOf(requestOpCode), " resultCode is ", Integer.valueOf(resultCode));
        switch (requestOpCode) {
            case 0:
                if (this.an == -1) {
                    e(resultCode);
                    break;
                } else {
                    d(resultCode);
                    break;
                }
            case 1:
                this.aa = 0;
                if (this.an != -1) {
                    if (resultCode == 1) {
                        b(0, (int[]) null);
                        break;
                    } else {
                        d("set_target_failed");
                        break;
                    }
                } else {
                    c(912, "failed");
                    break;
                }
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 21:
                if (this.ak.contains(Integer.valueOf(requestOpCode))) {
                    d(resultCode == 1 ? "set_target_success" : "set_target_failed");
                    break;
                } else {
                    j(resultCode);
                    break;
                }
        }
        LogUtil.a("Track_IDEQ_FtmpController", "control result(mIsControlAllowed):", Integer.valueOf(this.aa));
    }

    private void j(int i2) {
        if (i2 == 1) {
            c(912, "success");
        } else {
            c(912, "failed");
        }
    }

    private void e(int i2) {
        if (i2 == 1) {
            this.aa = 1;
            LogUtil.a("Track_IDEQ_FtmpController", "FTMP_TS onCharacteristicChanged, AbstractFitnessClient.ACTION_REQUEST_MACHINE_CONTROL_SUCCESS");
            c(AbstractFitnessClient.ACTION_REQUEST_MACHINE_CONTROL_SUCCESS);
        } else {
            this.aa = 0;
            c(912, "failed");
        }
    }

    private void d(int i2) {
        LogUtil.a("Track_IDEQ_FtmpController", "responseControlForSetTarget resultCode is ", Integer.valueOf(i2));
        if (i2 == 1) {
            this.aa = 1;
            b(this.an, this.aj);
            return;
        }
        if (i2 == 4) {
            this.aa = 0;
            int i3 = this.ap;
            if (i3 > 0) {
                this.ap = i3 - 1;
                b(1, (int[]) null);
                return;
            } else {
                d("set_target_failed");
                return;
            }
        }
        this.aa = 0;
        d("set_target_failed");
    }

    private void d(String str) {
        LogUtil.a("Track_IDEQ_FtmpController", "setTargetNotify message is ", str);
        c(912, str);
        this.an = -1;
    }

    private boolean d(TrainingStatus trainingStatus) {
        return trainingStatus.getTrainingStatus() != 1;
    }

    private byte[] b(int[] iArr, int i2) {
        byte[] bArr = new byte[3];
        bArr[c(bArr, i2)] = (byte) (iArr[0] & 255);
        return bArr;
    }

    private byte[] c(int[] iArr, int i2) {
        int i3 = iArr[0];
        return new byte[]{(byte) (i2 & 255), (byte) ((i2 >> 8) & 255), (byte) (i3 & 255), (byte) ((i3 >> 8) & 255)};
    }

    private byte[] a(int[] iArr, int i2) {
        byte b2 = (byte) (iArr[0] & 255);
        int i3 = iArr[1];
        return new byte[]{(byte) (i2 & 255), (byte) ((i2 >> 8) & 255), b2, (byte) (i3 & 255), (byte) ((i3 >> 8) & 255)};
    }

    private boolean a(int[] iArr) {
        if (iArr == null) {
            LogUtil.b("Track_IDEQ_FtmpController", "handleHuaweiExtensionMachineControl para is null");
            return true;
        }
        int i2 = iArr[0];
        if (i2 <= 255 && i2 >= 0) {
            return false;
        }
        LogUtil.b("Track_IDEQ_FtmpController", "handleHuaweiExtensionMachineControl, para is invalid");
        return true;
    }

    private boolean e(int[] iArr) {
        if (iArr == null) {
            LogUtil.b("Track_IDEQ_FtmpController", "handleHuaweiExtensionMachineControl, para is null");
            return true;
        }
        int i2 = iArr[0];
        if (i2 <= 65535 && i2 >= 0) {
            return false;
        }
        LogUtil.b("Track_IDEQ_FtmpController", "handleHuaweiExtensionMachineControl, para is invalid");
        return true;
    }

    private boolean c(int[] iArr) {
        if (iArr == null) {
            LogUtil.b("Track_IDEQ_FtmpController", "handleHuaweiExtensionMachineControl, para is null");
            return true;
        }
        if (iArr.length < 2) {
            LogUtil.h("Track_IDEQ_FtmpController", "handleHuaweiExtensionMachineControl, para length is ", Integer.valueOf(iArr.length));
            return true;
        }
        int i2 = iArr[0];
        if (i2 > 255 || i2 < 0) {
            LogUtil.b("Track_IDEQ_FtmpController", "handleHuaweiExtensionMachineControl, para[0] is invalid");
            return true;
        }
        int i3 = iArr[1];
        if (i3 <= 65535 && i3 >= 0) {
            return false;
        }
        LogUtil.b("Track_IDEQ_FtmpController", "handleHuaweiExtensionMachineControl, para[1] is invalid");
        return true;
    }

    class e implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        private boolean f14722a = true;
        private BluetoothGattService b;
        private BluetoothGatt c;
        private BluetoothGattService e;
        private boolean g;

        e(BluetoothGatt bluetoothGatt, boolean z, BluetoothGattService bluetoothGattService, BluetoothGattService bluetoothGattService2) {
            this.c = bluetoothGatt;
            this.g = z;
            this.e = bluetoothGattService;
            this.b = bluetoothGattService2;
        }

        public void d(boolean z) {
            this.f14722a = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                Thread.sleep(200L);
            } catch (InterruptedException unused) {
                LogUtil.b("Track_IDEQ_FtmpController", "InterruptedException");
            }
            lai.this.bUS_(this.c, this.g);
            if (this.f14722a) {
                lai.this.al = false;
                int i = 0;
                while (!lai.this.bUK_(this.c, this.e, this.b) && i <= 5) {
                    if (!this.f14722a) {
                        return;
                    } else {
                        i++;
                    }
                }
                if (i > 5 || !lau.d().j()) {
                    lai.this.al = false;
                    LogUtil.a("Track_IDEQ_FtmpController", "initCount large than LOOP_FIVE");
                    lai.this.c(AbstractFitnessClient.ACTION_INVALID_DEVICE_INFO);
                }
                LogUtil.a("Track_IDEQ_FtmpController", "is read completed: ", Boolean.valueOf(lai.this.al));
            }
        }
    }

    public void e() {
        if (this.af != null) {
            LogUtil.a("Track_IDEQ_FtmpController", "mHandler will removeHandleMassage");
            this.af.removeCallbacksAndMessages(null);
        }
    }
}
