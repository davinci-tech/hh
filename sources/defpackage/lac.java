package defpackage;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import androidx.exifinterface.media.ExifInterface;
import com.huawei.btsportdevice.callback.CallbackBetweenClientAndController;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.handler.ExtendHandler;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.device.base.AbstractFitnessClient;
import com.huawei.health.device.model.DeviceInformation;
import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import com.huawei.hms.mlsdk.common.internal.client.event.MonitorResult;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.indoorequip.datastruct.IndoorEquipTrainerData;
import com.huawei.indoorequip.datastruct.RawDataFromCsafeTreadmill;
import com.huawei.openalliance.ad.constant.DetailedCreativeType;
import com.huawei.ui.main.stories.nps.interactors.mode.TypeParams;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

/* loaded from: classes5.dex */
public class lac extends lal {
    private boolean aa;
    private BluetoothGattCharacteristic ab;
    private boolean ac;
    private boolean ad;
    private e ae;
    private int af;
    private final Object ag;
    private long ah;
    private volatile RawDataFromCsafeTreadmill ai;
    private volatile List<Byte> f;
    private int g;
    private laj h;
    private BluetoothGattCharacteristic i;
    private DeviceInformation k;
    private boolean l;
    private BluetoothGatt m;
    private String n;
    private ExtendHandler o;
    private IndoorEquipTrainerData p;
    private boolean q;
    private boolean r;
    private boolean s;
    private boolean t;
    private boolean u;
    private boolean v;
    private boolean w;
    private boolean x;
    private boolean y;
    private boolean z;
    private static final UUID c = UUID.fromString("226a47bc-86eb-4046-a03c-ab6804ca40c6");
    private static final UUID j = UUID.fromString("226a47bc-86ea-4046-a03c-ab6804ca40c6");
    private static final UUID b = UUID.fromString("5ddeb811-0098-45ae-9c70-dabbeb50304e");
    private static final Object e = new Object();

    public lac(BluetoothGatt bluetoothGatt, CallbackBetweenClientAndController callbackBetweenClientAndController, Object obj) {
        super(bluetoothGatt, callbackBetweenClientAndController, obj);
        this.ai = new RawDataFromCsafeTreadmill();
        this.aa = false;
        this.r = false;
        this.z = false;
        this.s = false;
        this.q = false;
        this.w = false;
        this.ac = false;
        this.l = false;
        this.ad = false;
        this.v = false;
        this.y = false;
        this.x = false;
        this.t = false;
        this.u = true;
        this.g = 0;
        this.af = 0;
        this.ah = 0L;
        this.f = new ArrayList(0);
        this.ag = new Object();
        this.o = null;
        this.k = null;
        this.h = null;
    }

    private void b(byte[] bArr) {
        if (bArr == null || bArr.length <= 0) {
            return;
        }
        synchronized (this.ag) {
            for (byte b2 : bArr) {
                this.f.add(Byte.valueOf(b2));
            }
        }
    }

    private byte[] k() {
        synchronized (this.ag) {
            if (this.f.size() <= 0) {
                return null;
            }
            byte[] bArr = new byte[this.f.size()];
            for (int i = 0; i < this.f.size(); i++) {
                bArr[i] = this.f.get(i).byteValue();
            }
            this.f.clear();
            return bArr;
        }
    }

    private void o() {
        synchronized (this.ag) {
            this.f.clear();
        }
    }

    @Override // defpackage.lal
    public void bUV_(BluetoothGatt bluetoothGatt) {
        super.bUV_(bluetoothGatt);
    }

    @Override // defpackage.lal
    public void c() {
        this.ab = null;
        this.i = null;
        this.m = null;
    }

    @Override // defpackage.lal
    public void j() {
        this.v = true;
    }

    public boolean a() {
        return this.x;
    }

    public void e() {
        this.q = false;
        this.r = false;
        this.s = false;
    }

    public void f() {
        this.q = false;
        this.r = false;
        this.s = false;
    }

    public void b() {
        LogUtil.a("Track_IDEQ_CsafeController", "set pause thread true");
        this.w = true;
    }

    @Override // defpackage.lal
    public void bVb_(BluetoothGatt bluetoothGatt, int i) {
        if (i == 0) {
            if (this.u) {
                bTQ_(bluetoothGatt, false);
                return;
            } else {
                bTP_(bluetoothGatt, false);
                return;
            }
        }
        c(AbstractFitnessClient.ACTION_SERVICE_NOT_SUPPORT);
    }

    @Override // defpackage.lal
    public void bVc_(BluetoothGatt bluetoothGatt, int i) {
        if (i == 0) {
            if (this.u) {
                bTQ_(bluetoothGatt, true);
                return;
            } else {
                bTP_(bluetoothGatt, true);
                return;
            }
        }
        c(AbstractFitnessClient.ACTION_SERVICE_NOT_SUPPORT);
    }

    @Override // defpackage.lal
    public void bUZ_(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
        byte[] value;
        if (i != 0 || bluetoothGattCharacteristic == null || !this.u || (value = bluetoothGattCharacteristic.getValue()) == null || value.length < 6) {
            return;
        }
        int i2 = 0;
        while (i2 < value.length && value[i2] != 0) {
            i2++;
        }
        byte[] bArr = new byte[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            bArr[i3] = value[i3];
        }
        try {
            this.n = new String(bArr, "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            LogUtil.b("Track_IDEQ_CsafeController", "onFitnessCharacteristicRead, UnsupportedEncodingException!");
        }
        this.af++;
    }

    @Override // defpackage.lal
    public void bUY_(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (bluetoothGattCharacteristic != null) {
            if (this.u) {
                if (this.ad) {
                    bTN_(bluetoothGattCharacteristic);
                    return;
                } else {
                    bTM_(bluetoothGattCharacteristic);
                    return;
                }
            }
            bTN_(bluetoothGattCharacteristic);
        }
    }

    private void bTM_(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        String str;
        byte[] value = bluetoothGattCharacteristic.getValue();
        if (value != null) {
            try {
                str = new String(value, "UTF-8");
            } catch (UnsupportedEncodingException unused) {
                LogUtil.b("Track_IDEQ_CsafeController", "onFitnessCharacteristicChanged, UnsupportedEncodingException");
                str = null;
            }
            if (str != null && MonitorResult.SUCCESS.equals(str.toUpperCase(Locale.ENGLISH))) {
                this.ad = true;
                LogUtil.a("Track_IDEQ_CsafeController", "UNLOCK_COMMAND is correct!");
            } else if (e(value)) {
                this.ad = true;
                LogUtil.a("Track_IDEQ_CsafeController", "UNLOCK_COMMAND is correct (has been mIsUnlocked before already)");
            } else {
                this.ad = false;
                LogUtil.b("Track_IDEQ_CsafeController", "UNLOCK_COMMAND is incorrect! reply is " + str);
            }
        }
    }

    private boolean e(byte[] bArr) {
        if (bArr != null && bArr.length > 0) {
            for (byte b2 : bArr) {
                if (b2 == -15 || b2 == -14) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // defpackage.lal
    public void bUX_(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
        this.g++;
    }

    public void bTP_(BluetoothGatt bluetoothGatt, final boolean z) {
        LogUtil.a("Track_IDEQ_CsafeController", "getService: isReconnect is ", Boolean.valueOf(z));
        if (bluetoothGatt == null) {
            LogUtil.b("Track_IDEQ_CsafeController", "cannot get service bcz gatt is null");
            c(AbstractFitnessClient.ACTION_SERVICE_NOT_SUPPORT);
            return;
        }
        BluetoothGattService service = bluetoothGatt.getService(j);
        if (service == null) {
            LogUtil.b("Track_IDEQ_CsafeController", "cannot get service bcz gattService is null");
            c(AbstractFitnessClient.ACTION_SERVICE_NOT_SUPPORT);
            return;
        }
        BluetoothGattCharacteristic characteristic = service.getCharacteristic(c);
        if (characteristic == null) {
            LogUtil.b("Track_IDEQ_CsafeController", "cannot get service bcz gattCharacteristic is null");
            c(AbstractFitnessClient.ACTION_SERVICE_NOT_SUPPORT);
            return;
        }
        LogUtil.a("Track_IDEQ_CsafeController", "getService: get gattCharacteristic success");
        if (this.d != null) {
            this.d.setBreakBySelf(false);
        }
        this.ab = characteristic;
        if (z && this.ac) {
            LogUtil.a("Track_IDEQ_CsafeController", "now will resume three work threads(A)");
            c(AbstractFitnessClient.ACTION_SERVICE_REDISCOVERIED);
        } else {
            LogUtil.a("Track_IDEQ_CsafeController", "now will start three work threads, first will reset some vars(A)");
            c(AbstractFitnessClient.ACTION_SERVICE_DISCOVERIED);
            this.ai.resetAllMembers();
            b(0);
            this.l = false;
            o();
            lah.d();
            c(901, (String) null);
            IndoorEquipTrainerData indoorEquipTrainerData = new IndoorEquipTrainerData(99);
            this.p = indoorEquipTrainerData;
            this.h = new laj(indoorEquipTrainerData, this.k, null, false);
            this.w = false;
        }
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: lac.4
            @Override // java.lang.Runnable
            public void run() {
                lac lacVar = lac.this;
                lacVar.bTL_(lacVar.ab, z);
            }
        }, 500L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bTL_(BluetoothGattCharacteristic bluetoothGattCharacteristic, boolean z) {
        bUw_(bluetoothGattCharacteristic, true, true);
        if (z && this.ac) {
            LogUtil.a("Track_IDEQ_CsafeController", "now will resume three work threads(B)");
            this.w = false;
        } else {
            LogUtil.a("Track_IDEQ_CsafeController", "now will start three work threads, first will reset some vars(B)");
            v();
        }
    }

    public void bTQ_(BluetoothGatt bluetoothGatt, final boolean z) {
        LogUtil.a("Track_IDEQ_CsafeController", "getService: isReconnect is ", Boolean.valueOf(z));
        if (bluetoothGatt == null) {
            LogUtil.b("Track_IDEQ_CsafeController", "cannot get service bcz gatt is null");
            c(AbstractFitnessClient.ACTION_SERVICE_NOT_SUPPORT);
            return;
        }
        BluetoothGattService service = bluetoothGatt.getService(j);
        if (service == null) {
            LogUtil.b("Track_IDEQ_CsafeController", "cannot get service bcz gattService is null");
            c(AbstractFitnessClient.ACTION_SERVICE_NOT_SUPPORT);
            return;
        }
        BluetoothGattCharacteristic characteristic = service.getCharacteristic(c);
        BluetoothGattCharacteristic characteristic2 = service.getCharacteristic(b);
        if (characteristic == null || characteristic2 == null) {
            LogUtil.b("Track_IDEQ_CsafeController", "cannot get service bcz gattCharacteristic or gattCharacteristicDeviceInfo is null");
            c(AbstractFitnessClient.ACTION_SERVICE_NOT_SUPPORT);
            return;
        }
        LogUtil.a("Track_IDEQ_CsafeController", "getService: get gattCharacteristic success");
        this.ab = characteristic;
        this.i = characteristic2;
        this.m = bluetoothGatt;
        ThreadPoolManager.d().execute(new Runnable() { // from class: lad
            @Override // java.lang.Runnable
            public final void run() {
                lac.this.e(z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public void e(boolean z) {
        int i;
        try {
            Thread.sleep(100L);
            this.g = 0;
            bUw_(this.ab, true, true);
            int i2 = 0;
            while (true) {
                i = this.g;
                if (i == 1 || i2 >= 10) {
                    break;
                }
                Thread.sleep(100L);
                i2++;
            }
            if (i2 >= 10 && i != 1) {
                LogUtil.a("Track_IDEQ_CsafeController", "gattCharacteristic write descriptor failed");
                c(AbstractFitnessClient.ACTION_SERVICE_NOT_SUPPORT);
                return;
            }
            this.ad = false;
            bUx_(this.i, "UK502L".getBytes(Charset.defaultCharset()));
            if (a(0)) {
                return;
            }
            DeviceInformation c2 = c(0);
            this.k = c2;
            if (c2 == null) {
                return;
            }
            BluetoothGatt bluetoothGatt = this.m;
            if (bluetoothGatt != null && bluetoothGatt.getDevice() != null) {
                LogUtil.c("Track_IDEQ_CsafeController", "bleDeviceName = ", this.m.getDevice().getName());
                this.k.setBleDeviceName(this.m.getDevice().getName());
            }
            e(this.k);
            b(z, this.k);
        } catch (InterruptedException | SecurityException e2) {
            LogUtil.b("Track_IDEQ_CsafeController", "afterFindingServiceForNewBtModule Exception:", ExceptionUtils.d(e2));
        }
    }

    private void b(boolean z, DeviceInformation deviceInformation) {
        if ("Life Fitness".equals(deviceInformation.getManufacturerString()) && (deviceInformation.getModelString() == null || !deviceInformation.getModelString().contains("-1"))) {
            this.y = true;
        } else {
            this.y = false;
        }
        if (this.d != null) {
            this.d.setBreakBySelf(false);
        }
        if (z && this.ac) {
            LogUtil.a("Track_IDEQ_CsafeController", "now will resume three work threads(A)");
            c(AbstractFitnessClient.ACTION_SERVICE_REDISCOVERIED);
        } else {
            LogUtil.a("Track_IDEQ_CsafeController", "now will start three work threads, first will reset some vars(A)");
            c(AbstractFitnessClient.ACTION_SERVICE_DISCOVERIED);
            this.ai.resetAllMembers();
            b(0);
            this.l = false;
            o();
            lah.d();
            c(901, (String) null);
            IndoorEquipTrainerData indoorEquipTrainerData = new IndoorEquipTrainerData(99);
            this.p = indoorEquipTrainerData;
            this.h = new laj(indoorEquipTrainerData, this.k, null, false);
            this.w = false;
        }
        if (z && this.ac) {
            LogUtil.a("Track_IDEQ_CsafeController", "now will resume three work threads(B)");
            this.w = false;
        } else {
            LogUtil.a("Track_IDEQ_CsafeController", "now will start three work threads, first will reset some vars(B)");
            v();
        }
    }

    private DeviceInformation c(int i) throws InterruptedException {
        int i2;
        while (true) {
            i2 = this.af;
            if (i2 == 1 || i >= 20) {
                break;
            }
            Thread.sleep(100L);
            i++;
        }
        if (i >= 20 && i2 != 1) {
            LogUtil.a("Track_IDEQ_CsafeController", "cannot read device info");
            c(AbstractFitnessClient.ACTION_INVALID_DEVICE_INFO);
            return null;
        }
        if (!d(this.n)) {
            LogUtil.c("Track_IDEQ_CsafeController", "device info ", this.n, " is invalid");
            c(AbstractFitnessClient.ACTION_INVALID_DEVICE_INFO);
            return null;
        }
        DeviceInformation deviceInformation = new DeviceInformation();
        a(this.n, deviceInformation);
        if (deviceInformation.getDeviceType() == -1) {
            LogUtil.a("Track_IDEQ_CsafeController", "device info (device type) is invalid");
            c(AbstractFitnessClient.ACTION_INVALID_DEVICE_INFO);
            return null;
        }
        if (deviceInformation.getDeviceType() != 273 && deviceInformation.getDeviceType() != 265) {
            return deviceInformation;
        }
        LogUtil.a("Track_IDEQ_CsafeController", "crosstrainer and bike in this version is not support");
        c(AbstractFitnessClient.ACTION_FAILED_UNLOCK_BT_MODULE);
        return null;
    }

    private boolean a(int i) throws InterruptedException {
        boolean z;
        BluetoothGattCharacteristic bluetoothGattCharacteristic;
        boolean z2;
        while (true) {
            z = this.ad;
            if (z || i >= 20) {
                break;
            }
            Thread.sleep(100L);
            i++;
        }
        if (i >= 20 && !z) {
            LogUtil.a("Track_IDEQ_CsafeController", "unlock BT module failed, retry");
            bUx_(this.ab, kzc.i());
            Thread.sleep(100L);
            bUx_(this.ab, kzc.i());
            int i2 = 0;
            while (true) {
                z2 = this.ad;
                if (z2 || i2 >= 20) {
                    break;
                }
                Thread.sleep(100L);
                i2++;
            }
            if (i2 >= 20 && !z2) {
                LogUtil.a("Track_IDEQ_CsafeController", "unlock BT module failed");
                c(AbstractFitnessClient.ACTION_FAILED_UNLOCK_BT_MODULE);
                return true;
            }
        }
        try {
            BluetoothGatt bluetoothGatt = this.m;
            if (bluetoothGatt != null && (bluetoothGattCharacteristic = this.i) != null) {
                this.af = 0;
                this.n = "";
                bluetoothGatt.readCharacteristic(bluetoothGattCharacteristic);
                return false;
            }
            LogUtil.h("Track_IDEQ_CsafeController", "gatt has been closed");
            return true;
        } catch (SecurityException e2) {
            LogUtil.b("Track_IDEQ_CsafeController", "isFailStatus SecurityException:", ExceptionUtils.d(e2));
            return true;
        }
    }

    private boolean d(String str) {
        LogUtil.a("Track_IDEQ_CsafeController", "in checkDeviceInfoValid, mDeviceInfo is ", str);
        if (!TextUtils.isEmpty(str)) {
            String upperCase = str.toUpperCase(Locale.ENGLISH);
            if (upperCase.startsWith("DI") && upperCase.endsWith("#") && !upperCase.contains("NULL") && upperCase.length() >= 11) {
                LogUtil.a("Track_IDEQ_CsafeController", "in checkDeviceInfoValid, mDeviceInfo is valid, return true");
                return true;
            }
            LogUtil.h("Track_IDEQ_CsafeController", "in checkDeviceInfoValid, mDeviceInfo is invalid, return false");
            return false;
        }
        LogUtil.h("Track_IDEQ_CsafeController", "in checkDeviceInfoValid, mDeviceInfo is empty, return false");
        return false;
    }

    private void a(String str, DeviceInformation deviceInformation) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        String upperCase = str.toUpperCase(Locale.ENGLISH);
        if (!upperCase.startsWith("DI") || !upperCase.endsWith("#") || upperCase.contains("NULL") || upperCase.length() < 11) {
            return;
        }
        String substring = upperCase.substring(2, 3);
        String substring2 = upperCase.substring(3, 5);
        String substring3 = upperCase.substring(5, upperCase.length() - 5);
        LogUtil.a("Track_IDEQ_CsafeController", "in analysisDataInfo, mDeviceInfo is: TYPE:", substring, " BRAND:", substring2, " MODLE:", substring3);
        if (ExifInterface.GPS_DIRECTION_TRUE.equals(substring)) {
            deviceInformation.setDeviceType(264);
        } else if (TypeParams.SEARCH_CODE.equals(substring)) {
            deviceInformation.setDeviceType(OldToNewMotionPath.SPORT_TYPE_CROSS_TRAINER);
        } else if ("B".equals(substring)) {
            deviceInformation.setDeviceType(OldToNewMotionPath.SPORT_TYPE_INDOOR_BIKE);
        } else {
            deviceInformation.setDeviceType(-1);
        }
        if ("JS".equals(substring2)) {
            deviceInformation.setManufacturerString("Johnson");
        } else if ("PC".equals(substring2)) {
            deviceInformation.setManufacturerString("Precor");
        } else if ("TG".equals(substring2)) {
            deviceInformation.setManufacturerString("Technogym");
        } else if ("LF".equals(substring2)) {
            deviceInformation.setManufacturerString("Life Fitness");
        } else if ("ST".equals(substring2)) {
            deviceInformation.setManufacturerString("Star Trac");
        } else if ("MT".equals(substring2)) {
            deviceInformation.setManufacturerString("Matrix");
        } else if ("IZ".equals(substring2)) {
            deviceInformation.setManufacturerString("Intenza");
        } else {
            deviceInformation.setManufacturerString("Matrix");
        }
        deviceInformation.setModelString(substring3);
    }

    private void v() {
        LogUtil.a("Track_IDEQ_CsafeController", "now will start three work threads");
        this.q = true;
        this.r = true;
        this.s = true;
        if (this.ae == null) {
            this.ae = new e();
        }
        ThreadPoolManager.d().execute(this.ae);
    }

    private void bTN_(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        byte[] value = bluetoothGattCharacteristic.getValue();
        if (value == null || !this.z) {
            return;
        }
        this.aa = true;
        LogUtil.c("Track_IDEQ_CsafeController", "[COMMAND RECEIVED FROM EQUIP]", lbv.e(value));
        b(value);
        this.aa = false;
    }

    private void s() {
        d(kzc.o());
    }

    private void p() {
        d(kzc.b());
    }

    private void l() {
        d(kzc.d());
    }

    private void q() {
        d(kzc.f());
    }

    private void r() {
        d(kzc.a());
    }

    private void t() {
        d(kzc.e());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i) {
        if (!this.v) {
            if (i == 1 || i == 3 || i == 5 || i == 7) {
                s();
                return;
            }
            return;
        }
        if (i == 1 || i == 4 || i == 6 || i == 9) {
            s();
            return;
        }
        if (i == 2 || i == 7) {
            p();
            return;
        }
        if (i == 5) {
            q();
            return;
        }
        if (i == 3) {
            l();
        } else if (i == 8) {
            r();
        } else if (i == 10) {
            t();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(byte[] bArr) {
        if (bArr != null) {
            LogUtil.c("Track_IDEQ_CsafeController", "[COMMAND SEND TO EQUIP]", lbv.e(bArr));
            bUx_(this.ab, bArr);
        } else {
            LogUtil.h("Track_IDEQ_CsafeController", "sendDeviceData: dataContent is null");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void y() {
        this.o.removeMessages(902);
        if (!this.s) {
            this.p = null;
            return;
        }
        if (this.w) {
            this.o.sendEmptyMessage(902, 500L);
            return;
        }
        synchronized (e) {
            if (this.ai.isThereAnyFlagsHasBeenSet()) {
                a(this.ai, this.p);
                this.ai.clearDataFlags();
                this.h.c();
                d(this.p, this.ai.getStateOfTreadmill());
            }
        }
        this.o.sendEmptyMessage(902, 500L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        byte[] k;
        this.o.removeMessages(901);
        if (!this.r) {
            this.z = false;
            return;
        }
        if (this.w) {
            this.o.sendEmptyMessage(901, 500L);
            return;
        }
        if (!this.aa && (k = k()) != null) {
            synchronized (e) {
                c(k);
            }
        }
        this.o.sendEmptyMessage(901, 200L);
    }

    private void c(byte[] bArr) {
        lah.d(bArr, this.ai);
        if (this.ai.getStateOfTreadmill() == 7) {
            LogUtil.a("Track_IDEQ_CsafeController", "caLL finishThisSession  (4)");
            if (SystemClock.elapsedRealtime() - this.ah < 3000 || !this.l) {
                return;
            }
            b(true, true, false, false);
        }
    }

    public class e implements Runnable {
        public e() {
        }

        @Override // java.lang.Runnable
        public void run() {
            int stateOfTreadmill;
            if (!lac.this.ac) {
                lac.this.ac = true;
                lac.this.l = false;
                lac.this.v = false;
                lac.this.t = false;
                LogUtil.a("IDEQ/ThreadToSendCommand", "start a WorkThreadToSendCommand, mIsAllowedToRunThreadToSendCommand is ", Boolean.valueOf(lac.this.q));
                lac.this.n();
                lac.this.x();
                boolean z = lac.this.n != null && (lac.this.n.toUpperCase(Locale.ENGLISH).contains("T7XE") || lac.this.n.toUpperCase(Locale.ENGLISH).contains("T-7XE"));
                boolean z2 = true;
                boolean z3 = true;
                int i = 0;
                int i2 = 0;
                boolean z4 = false;
                int i3 = 0;
                while (lac.this.q) {
                    boolean z5 = (i >= 10 || i2 >= 10) && z;
                    if (!lac.this.w) {
                        synchronized (lac.e) {
                            stateOfTreadmill = lac.this.ai.getStateOfTreadmill();
                        }
                        if (stateOfTreadmill == 7) {
                            z4 = lac.this.a(z4, z5);
                        } else if (stateOfTreadmill == 2 && !z5) {
                            z2 = lac.this.b(z2, kzc.c());
                        } else if (stateOfTreadmill == 3 && !z5) {
                            lac.this.d(kzc.h());
                            try {
                                Thread.sleep(200L);
                            } catch (InterruptedException unused) {
                                LogUtil.b("Track_IDEQ_CsafeController", "InterruptedException");
                            }
                        } else if (stateOfTreadmill == 8 && i < 10) {
                            i++;
                            if (!z5) {
                                z3 = lac.this.b(z3, kzc.g());
                            }
                        } else if ((stateOfTreadmill == 9 || stateOfTreadmill == 0) && i2 < 10) {
                            i2++;
                            if (!z5) {
                                lac.this.d(kzc.g());
                                try {
                                    Thread.sleep(200L);
                                } catch (InterruptedException unused2) {
                                    LogUtil.b("Track_IDEQ_CsafeController", "InterruptedException");
                                }
                            }
                        } else if (stateOfTreadmill == 1 && !z5) {
                            lac.this.d(kzc.j());
                            try {
                                Thread.sleep(200L);
                            } catch (InterruptedException unused3) {
                                LogUtil.b("Track_IDEQ_CsafeController", "InterruptedException");
                            }
                            z4 = false;
                        } else if (stateOfTreadmill == 5 || stateOfTreadmill == 6) {
                            lac.this.l = true;
                        }
                        i3++;
                        lac.this.e(i3);
                        if (i3 >= 10) {
                            i3 = 0;
                        }
                        try {
                            Thread.sleep(200L);
                        } catch (InterruptedException unused4) {
                            LogUtil.b("Track_IDEQ_CsafeController", "InterruptedException");
                        }
                    } else {
                        try {
                            Thread.sleep(500L);
                        } catch (InterruptedException unused5) {
                            LogUtil.b("Track_IDEQ_CsafeController", "InterruptedException");
                        }
                    }
                }
                LogUtil.a("IDEQ/ThreadToSendCommand", "End a WorkThreadToSendCommand");
                lac.this.ac = false;
                return;
            }
            LogUtil.a("IDEQ/ThreadToSendCommand", "a WorkThreadToSendCommand is running already, not allowed to start a new one");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean b(boolean z, byte[] bArr) {
        boolean z2;
        if (z) {
            d(bArr);
            z2 = false;
        } else {
            d(kzc.h());
            z2 = true;
        }
        try {
            Thread.sleep(200L);
        } catch (InterruptedException unused) {
            LogUtil.b("Track_IDEQ_CsafeController", "InterruptedException");
        }
        return z2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(boolean z, boolean z2) {
        long elapsedRealtime = SystemClock.elapsedRealtime() - this.ah;
        LogUtil.a("IDEQ/ThreadToSendCommand", "in WorkThreadToSendCommand and state is Finish, diff = ", Long.valueOf(elapsedRealtime), ", state Is Finish At Beginning = ", Boolean.valueOf(z));
        if (elapsedRealtime >= 3000 && !z) {
            return z;
        }
        if (!z2) {
            d(kzc.g());
        }
        try {
            Thread.sleep(200L);
        } catch (InterruptedException unused) {
            LogUtil.b("Track_IDEQ_CsafeController", "InterruptedException");
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void x() {
        while (!this.ai.isThereAnyFlagsHasBeenSet() && this.q) {
            if (this.w) {
                try {
                    Thread.sleep(500L);
                } catch (InterruptedException unused) {
                    LogUtil.b("Track_IDEQ_CsafeController", "InterruptedException");
                }
            } else {
                if (!this.t) {
                    this.t = true;
                    this.ah = SystemClock.elapsedRealtime();
                    o();
                    if (this.o == null) {
                        this.o = HandlerCenter.yt_(new Handler.Callback() { // from class: lac.3
                            @Override // android.os.Handler.Callback
                            public boolean handleMessage(Message message) {
                                int i = message.what;
                                if (i == 901) {
                                    lac.this.m();
                                    return true;
                                }
                                if (i != 902) {
                                    return false;
                                }
                                lac.this.y();
                                return true;
                            }
                        }, "Track_IDEQ_CsafeController");
                    }
                    this.o.sendEmptyMessage(901);
                    this.o.sendEmptyMessage(902);
                    this.z = true;
                }
                d(kzc.i());
                try {
                    Thread.sleep(500L);
                } catch (InterruptedException unused2) {
                    LogUtil.b("Track_IDEQ_CsafeController", "InterruptedException");
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        if (this.y) {
            LogUtil.a("IDEQ/ThreadToSendCommand", "will delay 10s to start command sending");
            this.x = true;
            c(DetailedCreativeType.SINGLE_IMG, (String) null);
            int i = 0;
            while (this.q) {
                try {
                    Thread.sleep(500L);
                } catch (InterruptedException unused) {
                    LogUtil.b("Track_IDEQ_CsafeController", "InterruptedException");
                }
                i++;
                if (i >= 20) {
                    break;
                }
            }
            c(910, (String) null);
            this.x = false;
        }
    }

    private void a(RawDataFromCsafeTreadmill rawDataFromCsafeTreadmill, IndoorEquipTrainerData indoorEquipTrainerData) {
        if (indoorEquipTrainerData == null || rawDataFromCsafeTreadmill == null) {
            return;
        }
        indoorEquipTrainerData.clearData();
        if (rawDataFromCsafeTreadmill.isItDistanceHasBeenSet()) {
            c(rawDataFromCsafeTreadmill, indoorEquipTrainerData);
        }
        if (rawDataFromCsafeTreadmill.isItCreepHasBeenSet()) {
            double e2 = lah.e(rawDataFromCsafeTreadmill.getCreep(), rawDataFromCsafeTreadmill.getCreepUnit());
            if (e2 >= 0.0d) {
                indoorEquipTrainerData.setPositiveElevationGain((int) (e2 * 10000.0d));
            }
        }
        if (rawDataFromCsafeTreadmill.isItSpeedHasBeenSet()) {
            double d = lah.d(rawDataFromCsafeTreadmill.getSpeed(), rawDataFromCsafeTreadmill.getSpeedUnit());
            if (rawDataFromCsafeTreadmill.getSpeedUnit() == 83) {
                indoorEquipTrainerData.setStepPerMinute((int) d);
            } else if (rawDataFromCsafeTreadmill.getSpeedUnit() == 82) {
                indoorEquipTrainerData.setRevsPerMinute((int) d);
            } else if (d >= 0.0d) {
                indoorEquipTrainerData.setInstantaneousSpeed((int) (d * 100.0d));
            }
        }
        if (rawDataFromCsafeTreadmill.isItTimeHasBeenSet()) {
            indoorEquipTrainerData.setElapsedTime((int) rawDataFromCsafeTreadmill.getDuringSeconds());
            indoorEquipTrainerData.setElapsedTimeStringForShow(rawDataFromCsafeTreadmill.getDuringTimeForShow());
        }
        if (rawDataFromCsafeTreadmill.isItCalorieHasBeenSet()) {
            indoorEquipTrainerData.setTotalEnergy(rawDataFromCsafeTreadmill.getCalorie());
        }
        if (rawDataFromCsafeTreadmill.isItHeartRateHasBeenSet()) {
            indoorEquipTrainerData.setHeartRate(rawDataFromCsafeTreadmill.getHeartRateFromTreadmill());
        }
        if (rawDataFromCsafeTreadmill.isItPowerHasBeenSet()) {
            if (rawDataFromCsafeTreadmill.getPowerUnit() == 86) {
                indoorEquipTrainerData.setPowerInCalPerMin(rawDataFromCsafeTreadmill.getPower());
            } else if (rawDataFromCsafeTreadmill.getPowerUnit() == 87) {
                indoorEquipTrainerData.setPowerInCalPerMin(rawDataFromCsafeTreadmill.getPower() / 60);
            } else if (rawDataFromCsafeTreadmill.getPowerUnit() == 88) {
                indoorEquipTrainerData.setInstantaneousPower(rawDataFromCsafeTreadmill.getPower());
            }
        }
        indoorEquipTrainerData.setStartTime((int) rawDataFromCsafeTreadmill.getStartSecond());
    }

    private void c(RawDataFromCsafeTreadmill rawDataFromCsafeTreadmill, IndoorEquipTrainerData indoorEquipTrainerData) {
        double e2 = lah.e(rawDataFromCsafeTreadmill.getDistance(), rawDataFromCsafeTreadmill.getDistanceUnit());
        if (e2 >= 0.0d) {
            indoorEquipTrainerData.setTotalDistance((int) (e2 * 1000.0d));
        }
        if (rawDataFromCsafeTreadmill.getDistanceUnit() == 69) {
            indoorEquipTrainerData.setStrideCount(rawDataFromCsafeTreadmill.getDistance() * 10);
        }
    }
}
