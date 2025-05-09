package defpackage;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.btsportdevice.callback.CallbackBetweenClientAndController;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.device.base.AbstractFitnessClient;
import com.huawei.health.device.connectivity.comm.MeasurableDevice;
import com.huawei.health.ecologydevice.fitness.dataparser.secondropecommand.AbstractCommand;
import com.huawei.health.ecologydevice.fitness.dataparser.secondropecommand.CommandArrayList;
import com.huawei.health.ecologydevice.fitness.dataparser.secondropecommand.Receiver;
import com.huawei.health.ecologydevice.fitness.datastruct.BaseRopeData;
import com.huawei.health.ecologydevice.fitness.datastruct.DeviceInformation;
import com.huawei.health.ecologydevice.fitness.datastruct.RopeRealData;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.djo;
import health.compact.a.HuaweiHealth;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Queue;
import java.util.Set;
import java.util.UUID;

/* loaded from: classes3.dex */
public class cxw extends cxy implements Receiver.CommandReceiveCallback {
    private String f;
    private CallbackBetweenClientAndController h;
    private int i;
    private boolean j;
    private BluetoothGattCharacteristic k;
    private Queue<AbstractCommand> l;
    private BluetoothGattService m;
    private cye n;
    private BluetoothGattCharacteristic o;
    private Set<Integer> p;
    private int q;
    private int t;
    private static final UUID g = UUID.fromString("0000fff0-0000-1000-8000-00805f9b34fb");
    private static final UUID d = UUID.fromString("0000fff2-0000-1000-8000-00805f9b34fb");
    private static final UUID c = UUID.fromString("0000fff1-0000-1000-8000-00805f9b34fb");
    private static final UUID b = UUID.fromString("0000fff3-0000-1000-8000-00805f9b34fb");

    private boolean d(int i) {
        return i > 255 || i < 0;
    }

    public cxw(CallbackBetweenClientAndController callbackBetweenClientAndController, Object obj) {
        this(null, callbackBetweenClientAndController, obj);
    }

    public cxw(BluetoothGatt bluetoothGatt, CallbackBetweenClientAndController callbackBetweenClientAndController, Object obj) {
        super(bluetoothGatt, obj);
        this.j = false;
        this.i = 0;
        this.q = 0;
        this.t = 0;
        LogUtil.a("PDROPE_RopeController", "RopeController constructor");
        this.n = new cye();
        this.h = callbackBetweenClientAndController;
        b();
    }

    private void b() {
        HashSet hashSet = new HashSet(10);
        this.p = hashSet;
        hashSet.add(1);
        this.p.add(2);
        this.p.add(3);
        this.p.add(10);
        this.p.add(9);
        this.p.add(11);
        this.p.add(12);
    }

    public void b(String str) {
        this.f = str;
    }

    public void RF_(BluetoothGatt bluetoothGatt, int i) {
        if (i == 0) {
            RD_(bluetoothGatt, false);
        } else {
            this.h.notifyStateChanged(AbstractFitnessClient.ACTION_SERVICE_NOT_SUPPORT);
        }
    }

    public void RD_(BluetoothGatt bluetoothGatt, boolean z) {
        if (bluetoothGatt == null) {
            LogUtil.a("PDROPE_RopeController", "cannot get service bcz gatt is null");
            this.h.notifyStateChanged(AbstractFitnessClient.ACTION_SERVICE_NOT_SUPPORT);
            return;
        }
        UUID uuid = g;
        BluetoothGattService service = bluetoothGatt.getService(uuid);
        this.m = service;
        if (service == null) {
            LogUtil.c("PDROPE_RopeController", "getService, mRopeService is null:", uuid);
        }
        if (this.m != null) {
            LogUtil.a("PDROPE_RopeController", "Rope Service found , isReconnect:", Boolean.valueOf(z));
            this.h.setBreakBySelf(false);
            if (z) {
                this.h.notifyStateChanged(AbstractFitnessClient.ACTION_SERVICE_REDISCOVERIED);
            } else {
                this.h.notifyStateChanged(AbstractFitnessClient.ACTION_SERVICE_DISCOVERIED);
                this.h.notifyDataChange(901, null);
            }
            ThreadPoolManager.d().execute(new a(bluetoothGatt, true, this.m));
            return;
        }
        this.h.notifyStateChanged(AbstractFitnessClient.ACTION_SERVICE_NOT_SUPPORT);
    }

    public void RE_(BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
        this.e.a(false);
        if (i != 0) {
            return;
        }
        LogUtil.a("PDROPE_RopeController", "ROPE onCharacteristicRead readCount:", Integer.valueOf(this.q));
        this.q++;
        if (bluetoothGattCharacteristic != null) {
            if (bluetoothGattCharacteristic.getUuid() != null) {
                LogUtil.c("PDROPE_RopeController", "onFitnessCharacteristicRead , value:", dis.d(bluetoothGattCharacteristic.getValue(), ""));
                return;
            } else {
                LogUtil.a("PDROPE_RopeController", "ROPE onCharacteristicRead, charUUID is null");
                return;
            }
        }
        LogUtil.a("PDROPE_RopeController", "ROPE onCharacteristicRead characteristic is null");
    }

    public void d(String str, byte[] bArr) {
        if (TextUtils.isEmpty(str) || bArr == null || bArr.length <= 0) {
            LogUtil.h("PDROPE_RopeController", "onFitnessCharacteristicChanged, invalid parameter");
            return;
        }
        String upperCase = str.toUpperCase(Locale.ENGLISH);
        String d2 = dis.d(bArr, "");
        if ("0000fff1-0000-1000-8000-00805f9b34fb".toUpperCase(Locale.ENGLISH).equals(upperCase)) {
            ReleaseLogUtil.e("R_EcologyDevice_PDROPE_RopeController", "onFitnessCharacteristicChanged, ROPE_DATA:", d2);
            BaseRopeData a2 = this.n.a(bArr);
            if (!a(a2) || a2 == null) {
                return;
            }
            b(a2);
            Bundle bundle = new Bundle();
            bundle.putInt("com.huawei.health.fitness.KEY_MESSAGE_FITNESS_DATA_TYPE", a2.getFitnessDataType());
            bundle.putSerializable("com.huawei.health.fitness.KEY_MESSAGE_FOR_CALLBACK_FITNESS_DATA", a2.getFitnessHashMap());
            this.h.notifyDataChange(902, bundle);
            return;
        }
        if ("0000fff3-0000-1000-8000-00805f9b34fb".toUpperCase(Locale.ENGLISH).equals(upperCase)) {
            ReleaseLogUtil.e("R_EcologyDevice_PDROPE_RopeController", "device --> app: onFitnessCharacteristicChanged, ROPE_CONFIG_DATA:", d2);
            Receiver.d().b(this);
            Receiver.d().b(bArr);
            return;
        }
        LogUtil.a("PDROPE_RopeController", "onFitnessCharacteristicChanged, value:", d2);
    }

    private void b(BaseRopeData baseRopeData) {
        if (baseRopeData.getFitnessDataType() == 23 && (baseRopeData instanceof DeviceInformation)) {
            LogUtil.a("PDROPE_RopeController", "registerDataClient");
            DeviceInformation deviceInformation = (DeviceInformation) baseRopeData;
            MeasurableDevice bondedDeviceByUniqueId = cei.b().getBondedDeviceByUniqueId(this.f, false);
            if (bondedDeviceByUniqueId == null) {
                LogUtil.h("PDROPE_RopeController", "registerDataClient device is null");
                return;
            }
            String productId = bondedDeviceByUniqueId.getProductId();
            if (dij.h(productId)) {
                LogUtil.a("PDROPE_RopeController", "registerDataClient isIntelligentDevice");
                djo djoVar = new djo();
                djoVar.a(djoVar.e(new djo.c(productId, this.f).c(deviceInformation)), null);
            }
        }
    }

    private boolean a(BaseRopeData baseRopeData) {
        if (baseRopeData == null || !baseRopeData.isAllSet()) {
            LogUtil.a("PDROPE_RopeController", "onFitnessCharacteristicChanged, data is null or not ready");
            return false;
        }
        if (baseRopeData instanceof RopeRealData) {
            cyb.b((RopeRealData) baseRopeData);
            return true;
        }
        if (!(baseRopeData instanceof DeviceInformation)) {
            return true;
        }
        this.f11538a = (DeviceInformation) baseRopeData;
        return true;
    }

    public void e() {
        this.e.a(false);
        int i = this.t + 1;
        this.t = i;
        LogUtil.a("PDROPE_RopeController", "onFitnessCharacteristicWrite writeCount:", Integer.valueOf(i));
        if (koq.c(this.l)) {
            Rg_(this.o, true, true);
        }
    }

    public void a() {
        AbstractCommand poll;
        this.e.a(false);
        this.i++;
        if (!koq.c(this.l) || (poll = this.l.poll()) == null) {
            return;
        }
        Rh_(this.o, poll.toByteArray());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void RC_(BluetoothGatt bluetoothGatt, boolean z) {
        LogUtil.a("PDROPE_RopeController", "setRopeCharacteristicNotification(), isEnabled = ", Boolean.valueOf(z));
        if (z) {
            BluetoothGattService service = bluetoothGatt.getService(g);
            if (service != null) {
                Ry_(service);
                Rz_(service);
                LogUtil.a("PDROPE_RopeController", "start ropeDataNotifyCharacteristic");
                RB_(Rx_(service, c));
                if (this.o != null) {
                    LogUtil.a("PDROPE_RopeController", "start ropeConfigNotifyCharacteristic");
                    RB_(Rx_(service, b));
                }
                if (this.k != null) {
                    int i = 0;
                    while (this.i != 1 && i < 5) {
                        d(100L);
                        i++;
                    }
                    Object[] objArr = new Object[2];
                    objArr[0] = "setRopeCharacteristicNotification() =";
                    objArr[1] = Boolean.valueOf(i < 5);
                    LogUtil.c("PDROPE_RopeController", objArr);
                    return;
                }
                return;
            }
            LogUtil.a("PDROPE_RopeController", "setRopeCharacteristicNotification(), gattService == null");
        }
    }

    private BluetoothGattCharacteristic Rx_(BluetoothGattService bluetoothGattService, UUID uuid) {
        return bluetoothGattService.getCharacteristic(uuid);
    }

    private void RB_(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (bluetoothGattCharacteristic != null) {
            this.i = 0;
            Rg_(bluetoothGattCharacteristic, true, true);
        } else {
            this.i = 1;
            LogUtil.a("PDROPE_RopeController", "setRopeCharacteristicNotification() bluetoothGattCharacteristic is null");
        }
    }

    private void Ry_(BluetoothGattService bluetoothGattService) {
        BluetoothGattCharacteristic characteristic = bluetoothGattService.getCharacteristic(d);
        Object[] objArr = new Object[2];
        objArr[0] = "setRopeCharacteristicNotification() controlPointCharacteristic is null ";
        objArr[1] = Boolean.valueOf(characteristic == null);
        LogUtil.a("PDROPE_RopeController", objArr);
        this.k = characteristic;
    }

    private void Rz_(BluetoothGattService bluetoothGattService) {
        BluetoothGattCharacteristic characteristic = bluetoothGattService.getCharacteristic(b);
        Object[] objArr = new Object[2];
        objArr[0] = "setRopeCharacteristicNotification() ropeConfigCharacteristic is null ";
        objArr[1] = Boolean.valueOf(characteristic == null);
        ReleaseLogUtil.e("R_EcologyDevice_PDROPE_RopeController", objArr);
        this.o = characteristic;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean RA_(BluetoothGattService bluetoothGattService) {
        d(200L);
        LogUtil.c("PDROPE_RopeController", "begin readCharacteristic");
        if (bluetoothGattService == null || this.k == null) {
            LogUtil.a("PDROPE_RopeController", "ropeService or mRopeControlCharacteristic is null");
            return false;
        }
        this.t = 0;
        return i();
    }

    private boolean i() {
        int currentTimeMillis = (int) (System.currentTimeMillis() / 1000);
        LogUtil.a("PDROPE_RopeController", "readCharacteristic(dataTime) timeStamp:", Integer.valueOf(currentTimeMillis));
        if (!c(1, 0, new int[]{currentTimeMillis})) {
            LogUtil.c("PDROPE_RopeController", "readCharacteristic: OP_COMMAND_SET_DATE failed");
            this.t++;
        }
        if (!c(1)) {
            return false;
        }
        LogUtil.a("PDROPE_RopeController", "readCharacteristic(OP_COMMAND_DEVICE_INFO)");
        if (!c(4, 0, null)) {
            LogUtil.c("PDROPE_RopeController", "readCharacteristic: OP_COMMAND_DEVICE_INFO failed");
            this.t++;
        }
        if (did.c().d()) {
            ReleaseLogUtil.e("R_EcologyDevice_PDROPE_RopeController", "syncHistory");
            if (!c(3, 7, new int[]{0})) {
                LogUtil.c("PDROPE_RopeController", "syncHistory failed");
            }
            did.c().c(false);
        }
        if (!c(2) || !dds.c().f()) {
            return false;
        }
        c();
        return true;
    }

    private void c() {
        LogUtil.a("PDROPE_RopeController", "ROPE ACTION_READ_SUCCESS");
        this.j = true;
        this.h.notifyStateChanged(AbstractFitnessClient.ACTION_READ_SUCCESS);
        nrh.d(HuaweiHealth.a(), HuaweiHealth.a().getResources().getString(R.string._2130845061_res_0x7f021d85));
    }

    private boolean c(int i) {
        LogUtil.a("PDROPE_RopeController", "readCharacter: writeCount = ", Integer.valueOf(this.t), ", writeType = ", Integer.valueOf(i));
        int i2 = 0;
        while (this.t != i && i2 < 10) {
            d(100L);
            i2++;
        }
        if (i2 < 10) {
            return true;
        }
        LogUtil.c("PDROPE_RopeController", "FTMP_TS before readCharacteristic(): type = ", Integer.valueOf(i), ", return false");
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(long j) {
        try {
            Thread.sleep(j);
        } catch (InterruptedException unused) {
            LogUtil.a("PDROPE_RopeController", "InterruptedException");
        }
    }

    public boolean a(int i, int[] iArr) {
        byte[] bArr;
        ReleaseLogUtil.e("R_EcologyDevice_PDROPE_RopeController", "setFitnessMachineControl opCommand : ", 3, " code ", Integer.valueOf(i), " para:", Arrays.toString(iArr));
        if (d(i)) {
            return false;
        }
        switch (i) {
            case 1:
            case 4:
            case 5:
            case 6:
            case 7:
            case 9:
                bArr = new byte[]{3, (byte) (i & 255), 0, 0};
                break;
            case 2:
            case 3:
            case 8:
                int i2 = iArr[0];
                LogUtil.c("PDROPE_RopeController", "setFitnessMachineControl: ", Integer.valueOf(i), ",parm:", Integer.valueOf(iArr[0]));
                bArr = new byte[]{3, (byte) (i & 255), (byte) (i2 & 255), (byte) ((i2 >> 8) & 255)};
                break;
            case 10:
                byte[] e = e(i, iArr);
                LogUtil.a("PDROPE_RopeController", "setFitnessMachineControl: ", Integer.valueOf(i), ",parm:", iArr);
                bArr = e;
                break;
            case 11:
            case 12:
                bArr = b(3, i, iArr[0], 0);
                break;
            default:
                bArr = null;
                break;
        }
        if (bArr == null) {
            return false;
        }
        return e(i, bArr);
    }

    private byte[] e(int i, int[] iArr) {
        byte[] bArr = new byte[9];
        cyw.a(bArr, 3, 0);
        cyw.a(bArr, i, 1);
        cyw.e(bArr, 2, iArr, 0, 3);
        cyw.a(bArr, iArr[3], 8);
        return bArr;
    }

    private byte[] b(int i, int i2, int i3, int i4) {
        byte[] bArr = new byte[4];
        cyw.a(bArr, i, i4);
        cyw.a(bArr, i2, i4 + 1);
        cyw.c(bArr, i3, i4 + 2);
        return bArr;
    }

    public boolean c(int i, int i2, int[] iArr) {
        byte[] bArr;
        ReleaseLogUtil.e("R_EcologyDevice_PDROPE_RopeController", "setFitnessMachineControl opCommand : ", Integer.valueOf(i), " code ", Integer.valueOf(i2));
        if (!d(i) && !d(i2) && dds.c().f()) {
            if (i != 1) {
                if (i != 2) {
                    if (i == 3) {
                        return a(i2, iArr);
                    }
                    if (i != 4) {
                        if (i != 5) {
                            bArr = null;
                        } else {
                            byte[] bArr2 = {(byte) (i & 255), 0, (byte) (iArr[0] & 255)};
                            dds.c().e(iArr[0]);
                            bArr = bArr2;
                        }
                    }
                }
                bArr = new byte[]{(byte) (i & 255), 0};
            } else {
                int i3 = iArr[0];
                bArr = new byte[]{(byte) (i & 255), 0, (byte) (i3 & 255), (byte) ((i3 >> 8) & 255), (byte) ((i3 >> 16) & 255), (byte) ((i3 >> 24) & 255)};
            }
            if (bArr == null) {
                return false;
            }
            if (Rh_(this.k, bArr)) {
                LogUtil.a("PDROPE_RopeController", "writeCharacteristicValueBytes success");
                return true;
            }
            LogUtil.a("PDROPE_RopeController", "writeCharacteristicValueBytes failed");
        }
        return false;
    }

    public void d(int i, int i2, int[] iArr) {
        BaseRopeData b2;
        ReleaseLogUtil.e("R_EcologyDevice_PDROPE_RopeController", "app --> device:setFitnessRopeConfig opCommand: ", Integer.valueOf(i), " opCode ", Integer.valueOf(i2));
        if (d(i) || d(i2) || (b2 = this.n.b(i)) == null) {
            return;
        }
        b2.setCode(i2);
        b2.setCommand(i);
        b2.setPara(iArr);
        cyf transmitCommand = b2.getTransmitCommand();
        if (transmitCommand == null) {
            LogUtil.a("PDROPE_RopeController", "setFitnessRopeConfig command is null");
            return;
        }
        if (this.l == null) {
            this.l = new ArrayDeque();
        }
        Iterator<AbstractCommand> it = transmitCommand.b().iterator();
        while (it.hasNext()) {
            this.l.offer(it.next());
        }
        Rg_(this.o, true, true);
    }

    private boolean e(int i, byte[] bArr) {
        if (Rh_(this.k, bArr)) {
            ReleaseLogUtil.e("R_EcologyDevice_PDROPE_RopeController", "writeCharacteristicValueBytes success");
            if (this.p.contains(Integer.valueOf(i))) {
                cyb.b();
                return true;
            }
            if (i == 6) {
                cyb.d();
                return true;
            }
            LogUtil.a("PDROPE_RopeController", "setFitnessMachineControl else");
            return true;
        }
        ReleaseLogUtil.e("R_EcologyDevice_PDROPE_RopeController", "writeCharacteristicValueBytes failed");
        return false;
    }

    @Override // com.huawei.health.ecologydevice.fitness.dataparser.secondropecommand.Receiver.CommandReceiveCallback
    public void onReceiveSuccess(CommandArrayList commandArrayList) {
        BaseRopeData b2 = this.n.b(commandArrayList);
        if (b2 == null) {
            LogUtil.a("PDROPE_RopeController", "baseRopeData is null");
            return;
        }
        Bundle bundle = new Bundle();
        LogUtil.a("PDROPE_RopeController", "onReceiveSuccess, baseRopeData.getFitnessDataType() = ", Integer.valueOf(b2.getFitnessDataType()));
        bundle.putInt("com.huawei.health.fitness.KEY_MESSAGE_FITNESS_DATA_TYPE", b2.getFitnessDataType());
        bundle.putSerializable("com.huawei.health.fitness.KEY_MESSAGE_FOR_CALLBACK_FITNESS_DATA", b2.getFitnessHashMap());
        this.h.notifyDataChange(902, bundle);
    }

    class a implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        private boolean f11536a;
        private BluetoothGattService b;
        private BluetoothGatt e;

        a(BluetoothGatt bluetoothGatt, boolean z, BluetoothGattService bluetoothGattService) {
            LogUtil.a("PDROPE_RopeController", "RopeNotificationSettingAndReadThread constructor");
            this.e = bluetoothGatt;
            this.f11536a = z;
            this.b = bluetoothGattService;
        }

        @Override // java.lang.Runnable
        public void run() {
            cxw.this.d(200L);
            cxw.this.RC_(this.e, this.f11536a);
            if (djx.b()) {
                cxw.this.j = true;
                cxw.this.h.notifyStateChanged(AbstractFitnessClient.ACTION_READ_SUCCESS);
                nrh.d(HuaweiHealth.a(), HuaweiHealth.a().getResources().getString(R.string._2130845061_res_0x7f021d85));
                return;
            }
            int i = 0;
            cxw.this.j = false;
            while (!cxw.this.RA_(this.b) && i <= 10 && dds.c().f()) {
                i++;
            }
            if (!cxw.this.j) {
                cxw.this.h.notifyStateChanged(AbstractFitnessClient.ACTION_INVALID_DEVICE_INFO);
            }
            LogUtil.c("PDROPE_RopeController", "is read completed: ", Boolean.valueOf(cxw.this.j), " count: ", Integer.valueOf(i));
        }
    }
}
