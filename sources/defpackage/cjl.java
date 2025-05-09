package defpackage;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import androidx.versionedparcelable.VersionedParcel;
import com.huawei.devicesdk.entity.CharacterOperationType;
import com.huawei.devicesdk.entity.DeviceInfo;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.common.utils.HexUtils;
import com.huawei.health.device.callback.IHealthDeviceCallback;
import com.huawei.health.device.fatscale.multiusers.MultiUsersManager;
import com.huawei.health.device.kit.wsp.task.BleTaskQueueUtil;
import com.huawei.health.device.kit.wsp.task.IAsynBleTaskCallback;
import com.huawei.health.device.kit.wsp.task.ITaskService;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.open.data.model.HealthData;
import com.huawei.health.device.util.EventBus;
import com.huawei.health.ecologydevice.callback.ConnectStatusCallback;
import com.huawei.health.ecologydevice.callback.DataChangedCallback;
import com.huawei.health.ecologydevice.open.GattMeasureController;
import com.huawei.hihealth.HiUserInfo;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiCommonListener;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.Utils;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

/* loaded from: classes3.dex */
public class cjl extends GattMeasureController implements ITaskService, ConnectStatusCallback, DataChangedCallback {

    /* renamed from: a, reason: collision with root package name */
    private static cjl f745a;
    private static final Object c = new Object();
    private BluetoothGatt b;
    private IHealthDeviceCallback e;
    private Bundle i;
    private final a p;
    private String s;
    private ckm t;
    private IAsynBleTaskCallback u;
    private String v;
    private int y;
    private float l = 173.0f;
    private int d = 29;
    private int w = 1;
    private boolean o = false;
    private boolean q = false;
    private boolean k = false;
    private HandlerThread j = new HandlerThread("PluginDevice_WspMeasureController");
    private boolean m = false;
    private boolean r = false;
    private boolean n = false;
    private EventBus.ICallback h = new EventBus.ICallback() { // from class: cjl.5
        @Override // com.huawei.health.device.util.EventBus.ICallback
        public void onEvent(EventBus.b bVar) {
            if ("weight_measure_set_user".equals(bVar.e())) {
                cjl.this.FL_(bVar.Kl_());
            }
        }
    };
    private BluetoothGattCallback g = new BluetoothGattCallback() { // from class: cjl.4
        @Override // android.bluetooth.BluetoothGattCallback
        public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
            super.onConnectionStateChange(bluetoothGatt, i, i2);
            cjl.this.FJ_(bluetoothGatt, i, i2);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
            super.onServicesDiscovered(bluetoothGatt, i);
            LogUtil.a("PluginDevice_WspMeasureController", "onServicesDiscovered status:", Integer.valueOf(i));
            if (i == 0) {
                cjl.this.b = bluetoothGatt;
                if (cjl.this.e != null) {
                    cjl.this.e.onStatusChanged(cjl.this.mDevice, 2);
                }
                cjl.this.ad.t();
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            super.onCharacteristicWrite(bluetoothGatt, bluetoothGattCharacteristic, i);
            cjl.this.FI_(bluetoothGattCharacteristic, i);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            super.onCharacteristicChanged(bluetoothGatt, bluetoothGattCharacteristic);
            if (bluetoothGattCharacteristic == null || bluetoothGattCharacteristic.getUuid() == null) {
                return;
            }
            biu biuVar = new biu();
            biuVar.d(bluetoothGattCharacteristic.getValue());
            biuVar.a(bluetoothGattCharacteristic.getUuid().toString());
            cjl.this.a(biuVar);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
            super.onDescriptorWrite(bluetoothGatt, bluetoothGattDescriptor, i);
            LogUtil.a("PluginDevice_WspMeasureController", " onDescriptorWrite status:", Integer.valueOf(i), " descriptor:", bluetoothGattDescriptor.getCharacteristic().getUuid().toString());
            if (cjl.this.u == null) {
                return;
            }
            if (i == 0) {
                cjl.this.u.success(null);
            } else {
                cjl.this.u.failed();
            }
        }
    };
    private cjk f = new cjk();
    private BleTaskQueueUtil x = new BleTaskQueueUtil(this);
    private final cha ad = new cha(this.x);

    @Override // com.huawei.health.device.kit.wsp.task.ITaskService
    public void disable(cjq cjqVar, IAsynBleTaskCallback iAsynBleTaskCallback) {
    }

    @Override // com.huawei.health.device.open.MeasureController
    public boolean start() {
        return true;
    }

    private cjl() {
        this.j.start();
        this.p = new a(this.j.getLooper());
    }

    public static cjl c() {
        cjl cjlVar;
        synchronized (c) {
            if (f745a == null) {
                f745a = new cjl();
            }
            cjlVar = f745a;
        }
        return cjlVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void FI_(BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
        String uuid = bluetoothGattCharacteristic.getUuid().toString();
        LogUtil.a("PluginDevice_WspMeasureController", " doCharacteristicWrite status:", Integer.valueOf(i));
        IAsynBleTaskCallback iAsynBleTaskCallback = this.u;
        if (iAsynBleTaskCallback == null) {
            return;
        }
        if (i == 0) {
            iAsynBleTaskCallback.success(null);
            if (cez.i.toString().equalsIgnoreCase(uuid)) {
                if (this.y == -2) {
                    d();
                    return;
                } else {
                    this.ad.h();
                    this.ad.a();
                    return;
                }
            }
            return;
        }
        iAsynBleTaskCallback.failed();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(biu biuVar) {
        LogUtil.a("PluginDevice_WspMeasureController", "Device-->SDK:", HexUtils.d(biuVar.a()), " tag :", biuVar.b());
        UUID fromString = UUID.fromString(biuVar.b());
        if (cez.c.equals(fromString)) {
            c(biuVar);
            return;
        }
        if (cez.o.equals(fromString)) {
            d(biuVar);
            return;
        }
        if (cez.l.equals(fromString)) {
            cgn a2 = cgg.a(biuVar.a());
            LogUtil.a("PluginDevice_WspMeasureController", " doCharacteristicChanged parseResult for private protocol read ", Integer.valueOf(a2.b()));
            if (a2.b() == 3) {
                LogUtil.a("PluginDevice_WspMeasureController", " doCharacteristicChanged clean user data success ");
                this.q = true;
                this.p.removeMessages(1);
                cpa.m();
                cleanup();
                ending();
                return;
            }
            return;
        }
        LogUtil.h("PluginDevice_WspMeasureController", "doCharacteristicChanged uuid == unknown");
    }

    private void d(biu biuVar) {
        ckm ckmVar = new ckm();
        this.t = ckmVar;
        ckmVar.setEndTime(System.currentTimeMillis());
        if (this.f == null) {
            this.f = new cjk();
        }
        HealthData d = this.f.d(biuVar.a());
        if (d instanceof ckm) {
            ckm ckmVar2 = (ckm) d;
            if (this.t.getEndTime() == ckmVar2.getEndTime()) {
                if (ckmVar2.getWeight() == 0.0f || ckmVar2.getBodyFatRat() != 0.0f) {
                    return;
                }
                e(ckmVar2);
                return;
            }
            this.t.setWeight(ckmVar2.getWeight());
            this.t.setEndTime(ckmVar2.getEndTime());
            this.t.setStartTime(ckmVar2.getStartTime());
            this.k = true;
            LogUtil.a("PluginDevice_WspMeasureController", "doWeightMeasure weight is not null");
            e(ckmVar2);
            this.n = false;
        }
    }

    private void e(ckm ckmVar) {
        if (this.m) {
            LogUtil.a("PluginDevice_WspMeasureController", "sendUserInfoMsg send weight auto measure to AutoMeasureController");
            Bundle bundle = new Bundle();
            bundle.putSerializable("weight_data", ckmVar);
            EventBus.d(new EventBus.b("weight_measure_choose_user", bundle));
            return;
        }
        if (this.i != null) {
            LogUtil.a("PluginDevice_WspMeasureController", "sendUserInfoMsg mBundle != null");
            FL_(this.i);
        }
    }

    private void c(biu biuVar) {
        if (this.f == null) {
            this.f = new cjk();
        }
        if (this.t == null) {
            ckm ckmVar = new ckm();
            this.t = ckmVar;
            ckmVar.setEndTime(System.currentTimeMillis());
        }
        HealthData parseData = this.f.parseData(biuVar.a());
        if (parseData instanceof ckm) {
            ckm ckmVar2 = (ckm) parseData;
            float bodyFatRat = ckmVar2.getBodyFatRat();
            if (this.k) {
                LogUtil.a("PluginDevice_WspMeasureController", "doBodyCompositionMeasure parseData is null");
                this.k = false;
                this.t.setBodyFatRat(bodyFatRat);
                if (this.e != null) {
                    f();
                    this.e.onDataChanged(this.mDevice, this.t);
                    e();
                }
            }
            if (this.k && this.t.getEndTime() == ckmVar2.getEndTime()) {
                this.k = false;
                this.t.setBodyFatRat(ckmVar2.getBodyFatRat());
                LogUtil.a("PluginDevice_WspMeasureController", "doBodyCompositionMeasure weight = ", Float.valueOf(this.t.getWeight()), " bodyFatRat = ", Float.valueOf(this.t.getBodyFatRat()));
                if (this.e != null) {
                    f();
                    this.e.onDataChanged(this.mDevice, this.t);
                    e();
                }
            }
        }
    }

    private void f() {
        if (this.t == null) {
            return;
        }
        if (!Utils.i()) {
            SharedPreferences sharedPreferences = BaseApplication.getContext().getSharedPreferences("weightUser", 0);
            if (sharedPreferences != null) {
                this.t.e(sharedPreferences.getString("weightUser_id", ""));
                return;
            }
            return;
        }
        cfi currentUser = MultiUsersManager.INSTANCE.getCurrentUser();
        if (currentUser != null) {
            String i = currentUser.i();
            LogUtil.c("PluginDevice_WspMeasureController", "setUserIdForData userId = ", i);
            this.t.e(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void FJ_(BluetoothGatt bluetoothGatt, int i, int i2) {
        BluetoothGatt bluetoothGatt2;
        LogUtil.a("PluginDevice_WspMeasureController", "doConnectionStateChange status:", Integer.valueOf(i), " newState:", Integer.valueOf(i2));
        try {
            if (i2 == 2) {
                this.q = false;
                bluetoothGatt.discoverServices();
                IHealthDeviceCallback iHealthDeviceCallback = this.e;
                if (iHealthDeviceCallback != null) {
                    iHealthDeviceCallback.onStatusChanged(this.mDevice, 2);
                }
                this.n = true;
                return;
            }
            if (i2 == 0) {
                IHealthDeviceCallback iHealthDeviceCallback2 = this.e;
                if (iHealthDeviceCallback2 != null) {
                    iHealthDeviceCallback2.onStatusChanged(this.mDevice, 3);
                }
                if (this.n && (bluetoothGatt2 = this.b) != null) {
                    bluetoothGatt2.connect();
                    LogUtil.c("PluginDevice_WspMeasureController", "Trying to reconnect to ble device ...");
                    return;
                } else {
                    this.n = false;
                    cleanup();
                    return;
                }
            }
            LogUtil.h("PluginDevice_WspMeasureController", "doConnectionStateChange newState == unknown");
        } catch (SecurityException e) {
            LogUtil.b("PluginDevice_WspMeasureController", "doConnectionStateChange SecurityException:", ExceptionUtils.d(e));
        }
    }

    @Override // com.huawei.health.ecologydevice.callback.ConnectStatusCallback
    public void doDeviceConnected(int i, DeviceInfo deviceInfo) {
        LogUtil.a("PluginDevice_WspMeasureController", "doDeviceConnected by uds and status = ", Integer.valueOf(i));
        if (deviceInfo == null) {
            LogUtil.h("PluginDevice_WspMeasureController", "deviceInfo == null");
            return;
        }
        this.q = false;
        this.o = true;
        this.v = deviceInfo.getDeviceMac();
        IHealthDeviceCallback iHealthDeviceCallback = this.e;
        if (iHealthDeviceCallback != null) {
            iHealthDeviceCallback.onStatusChanged(this.mDevice, 2);
        }
        this.ad.t();
    }

    @Override // com.huawei.health.ecologydevice.callback.ConnectStatusCallback
    public void doDeviceDisconnect(int i) {
        LogUtil.a("PluginDevice_WspMeasureController", "doDeviceDisconnect by uds and status = ", Integer.valueOf(i));
        this.o = false;
        IHealthDeviceCallback iHealthDeviceCallback = this.e;
        if (iHealthDeviceCallback != null) {
            iHealthDeviceCallback.onStatusChanged(this.mDevice, 3);
        }
        cleanup();
    }

    @Override // com.huawei.health.ecologydevice.callback.ConnectStatusCallback
    public void doDeviceConnecting(int i) {
        LogUtil.a("PluginDevice_WspMeasureController", "doDeviceConnecting by uds and status = ", Integer.valueOf(i));
    }

    @Override // com.huawei.health.ecologydevice.callback.DataChangedCallback
    public void onDataChanged(biu biuVar) {
        LogUtil.a("PluginDevice_WspMeasureController", "onDataChanged by uds");
        if (biuVar == null) {
            LogUtil.h("PluginDevice_WspMeasureController", "dataFrame == null");
            return;
        }
        String b = biuVar.b();
        if (cez.c.toString().equalsIgnoreCase(b) || cez.o.toString().equalsIgnoreCase(b) || cez.l.toString().equalsIgnoreCase(b)) {
            b(biuVar);
            a(biuVar);
        } else if (c(b)) {
            e(biuVar);
        } else {
            LogUtil.h("PluginDevice_WspMeasureController", "not have this uuid");
        }
    }

    private boolean c(String str) {
        return (cez.i.toString().equalsIgnoreCase(str) || cez.k.toString().equalsIgnoreCase(str)) || (cez.f671a.toString().equalsIgnoreCase(str) || cez.f.toString().equalsIgnoreCase(str)) || cez.h.toString().equalsIgnoreCase(str);
    }

    private void b(biu biuVar) {
        LogUtil.a("PluginDevice_WspMeasureController", "onDescriptorWriteByUds characterUuid->", biuVar.b());
        IAsynBleTaskCallback iAsynBleTaskCallback = this.u;
        if (iAsynBleTaskCallback == null) {
            LogUtil.h("PluginDevice_WspMeasureController", "mTaskCallback == null");
        } else if (this.o) {
            iAsynBleTaskCallback.success(null);
        } else {
            iAsynBleTaskCallback.failed();
        }
    }

    private void e(biu biuVar) {
        IAsynBleTaskCallback iAsynBleTaskCallback = this.u;
        if (iAsynBleTaskCallback == null) {
            LogUtil.h("PluginDevice_WspMeasureController", "mTaskCallback == null");
            return;
        }
        if (this.o) {
            iAsynBleTaskCallback.success(null);
            if (cez.i.toString().equalsIgnoreCase(biuVar.b())) {
                if (this.y == -2) {
                    d();
                    return;
                } else {
                    this.ad.h();
                    this.ad.a();
                    return;
                }
            }
            return;
        }
        LogUtil.h("PluginDevice_WspMeasureController", "not connected");
        this.u.failed();
    }

    class a extends Handler {
        a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            LogUtil.a("PluginDevice_WspMeasureController", " handleMessage clean user data fail");
            cjl.this.b();
            EventBus.a(cjl.this.h, "weight_measure_set_user");
        }
    }

    @Override // com.huawei.health.ecologydevice.open.GattMeasureController, com.huawei.health.device.open.MeasureController
    public boolean prepare(HealthDevice healthDevice, IHealthDeviceCallback iHealthDeviceCallback, Bundle bundle) {
        this.t = null;
        EventBus.d(this.h, 0, "weight_measure_set_user");
        boolean i = cpl.c().i();
        this.r = i;
        if (i) {
            this.s = UUID.randomUUID().toString();
            cjl c2 = c();
            String name = cjl.class.getName();
            cpl.c().a(name, (String) c2);
            ddw.c().a(this.s, new cwq(name));
            ddw.c().e(this.s, new cwm(name, this.x));
        }
        CountDownLatch countDownLatch = new CountDownLatch(1);
        d(countDownLatch);
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            LogUtil.b("PluginDevice_WspMeasureController", "WspMeasureController prepare:", e.getMessage());
        }
        if (!super.prepare(healthDevice, iHealthDeviceCallback, bundle)) {
            return false;
        }
        this.e = iHealthDeviceCallback;
        if (bundle != null) {
            this.m = bundle.getBoolean("isAutoMeasure", false);
            this.i = bundle;
            this.y = bundle.getInt("type");
        } else {
            this.l = dks.d((int) this.l);
            this.w = dks.b(this.w);
            this.d = dks.a(this.d);
            this.i = new Bundle();
            j();
        }
        cgg.c(this.mDevice.getAddress());
        Object[] objArr = new Object[4];
        objArr[0] = "WspMeasureController prepare info:";
        objArr[1] = Boolean.valueOf(this.l <= 0.0f);
        objArr[2] = Boolean.valueOf(this.w == 1);
        objArr[3] = Boolean.valueOf(this.d == 29);
        LogUtil.a("PluginDevice_WspMeasureController", objArr);
        return true;
    }

    @Override // com.huawei.health.device.open.MeasureController
    public void cleanup() {
        LogUtil.a("PluginDevice_WspMeasureController", "Enter cleanup...");
        ckm ckmVar = this.t;
        if (ckmVar != null) {
            boolean z = ckmVar.getBodyFatRat() == 0.0f || cpa.c();
            if (this.y == -2 || z) {
                if (this.q) {
                    b();
                    return;
                }
                return;
            }
            b();
            return;
        }
        b();
    }

    @Override // com.huawei.health.ecologydevice.open.GattMeasureController, com.huawei.health.device.open.MeasureController
    public void ending() {
        LogUtil.a("PluginDevice_WspMeasureController", "Enter ending...");
        this.n = false;
        ckm ckmVar = this.t;
        if (ckmVar != null) {
            boolean z = ckmVar.getBodyFatRat() == 0.0f || cpa.c();
            if (this.y == -2 || z) {
                if (this.q) {
                    a();
                    return;
                }
                return;
            }
            a();
            return;
        }
        a();
    }

    private void j() {
        this.i.putInt("height", (int) this.l);
        this.i.putInt("sex", this.w);
        this.i.putInt("age", this.d);
    }

    private void a() {
        synchronized (c) {
            if (this.b != null) {
                LogUtil.a("PluginDevice_WspMeasureController", "end mBluetoothGatt is not null");
                try {
                    this.b.disconnect();
                } catch (SecurityException e) {
                    LogUtil.b("PluginDevice_WspMeasureController", "end SecurityException:", ExceptionUtils.d(e));
                }
            }
        }
        if (this.r) {
            ddw.c().a();
        }
        EventBus.a(this.h, "weight_measure_set_user");
        super.ending();
    }

    private void d(final CountDownLatch countDownLatch) {
        HiHealthManager.d(cpp.a()).fetchUserData(new HiCommonListener() { // from class: cjl.3
            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onSuccess(int i, Object obj) {
                if (obj == null) {
                    countDownLatch.countDown();
                    return;
                }
                cfi currentUser = MultiUsersManager.INSTANCE.getCurrentUser();
                if (currentUser != null) {
                    cjl.this.l = currentUser.d();
                    cjl.this.w = currentUser.c();
                    cjl.this.d = currentUser.a();
                } else if (obj instanceof List) {
                    Iterator it = ((List) obj).iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        Object next = it.next();
                        if (next instanceof HiUserInfo) {
                            HiUserInfo hiUserInfo = (HiUserInfo) next;
                            if (hiUserInfo.getRelateType() == 0) {
                                cjl.this.l = hiUserInfo.getHeight();
                                cjl.this.w = hiUserInfo.getGender();
                                cjl.this.d = hiUserInfo.getAge();
                                break;
                            }
                        }
                    }
                } else {
                    LogUtil.h("PluginDevice_WspMeasureController", "fetchUserData other data");
                }
                Object[] objArr = new Object[4];
                objArr[0] = "WspMeasureController fetchUserData info:";
                objArr[1] = Boolean.valueOf(cjl.this.l > 0.0f);
                objArr[2] = Boolean.valueOf(cjl.this.w == 1);
                objArr[3] = Boolean.valueOf(cjl.this.d == 29);
                LogUtil.a("PluginDevice_WspMeasureController", objArr);
                countDownLatch.countDown();
            }

            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onFailure(int i, Object obj) {
                LogUtil.h("PluginDevice_WspMeasureController", "ChipSeaMeasureController onFailure");
            }
        });
    }

    private void e() {
        ckm ckmVar = this.t;
        if (ckmVar != null) {
            float bodyFatRat = ckmVar.getBodyFatRat();
            LogUtil.c("PluginDevice_WspMeasureController", "sendClearData fatValue = ", Float.valueOf(bodyFatRat));
            if (bodyFatRat == 0.0f || cpa.c()) {
                d();
            }
        }
    }

    private void d() {
        this.p.removeMessages(1);
        this.p.sendEmptyMessageDelayed(1, 2000L);
        this.x.d();
        if (this.b == null) {
            LogUtil.h("PluginDevice_WspMeasureController", "cleanUserData mBluetoothGatt is null");
            return;
        }
        LogUtil.a("PluginDevice_WspMeasureController", "cleanUserData clear user info. begin");
        this.ad.e();
        this.ad.n();
        LogUtil.a("PluginDevice_WspMeasureController", "cleanUserData clear user info. end");
    }

    private void d(long j) {
        try {
            Thread.sleep(j);
        } catch (InterruptedException unused) {
            LogUtil.b("PluginDevice_WspMeasureController", "sleep delay interrupted");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        synchronized (c) {
            BluetoothGatt bluetoothGatt = this.b;
            if (bluetoothGatt != null) {
                try {
                    bluetoothGatt.disconnect();
                    d(100L);
                    this.b.close();
                } catch (SecurityException e) {
                    LogUtil.b("PluginDevice_WspMeasureController", "closeEnding SecurityException:", ExceptionUtils.d(e));
                }
                this.b = null;
            }
        }
        this.x.d();
        this.f = null;
        this.i = null;
        EventBus.a(this.h, "weight_measure_set_user");
        if (this.r) {
            ddw.c().a();
            ddw.c().a(this.s);
            ddw.c().d(this.s);
            cpl.c().a();
        }
        ending();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void FL_(Bundle bundle) {
        if (bundle == null) {
            LogUtil.h("PluginDevice_WspMeasureController", "bundle == null");
            return;
        }
        if (this.b == null && !this.r) {
            LogUtil.h("PluginDevice_WspMeasureController", "mBluetoothGatt == null && !mIsUniformDeviceManagementFlag");
            return;
        }
        try {
            int i = bundle.getInt("height");
            int i2 = bundle.getInt("sex");
            this.ad.a((byte) bundle.getInt("age"), (byte) ((i2 == 1 || i2 == 2) ? 0 : 1), (byte) i);
        } catch (VersionedParcel.ParcelException unused) {
            LogUtil.b("PluginDevice_WspMeasureController", "sendUserInfo Exception");
        }
    }

    @Override // com.huawei.health.ecologydevice.open.GattMeasureController
    public BluetoothGattCallback getGattCallbackImpl() {
        return this.g;
    }

    private boolean FK_(BluetoothGattCharacteristic bluetoothGattCharacteristic, boolean z) {
        BluetoothGatt bluetoothGatt = this.b;
        boolean z2 = false;
        if (bluetoothGatt == null) {
            LogUtil.h("PluginDevice_WspMeasureController", "enableCharacteristicNotification mBluetoothGatt is null");
            return false;
        }
        bluetoothGatt.setCharacteristicNotification(bluetoothGattCharacteristic, true);
        BluetoothGattDescriptor descriptor = bluetoothGattCharacteristic.getDescriptor(cez.n);
        if (descriptor != null) {
            descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
            descriptor.setValue(BluetoothGattDescriptor.ENABLE_INDICATION_VALUE);
            z2 = bluetoothGatt.writeDescriptor(descriptor);
        }
        LogUtil.a("PluginDevice_WspMeasureController", "enableCharacteristicNotification isWriteSuccess = ", Boolean.valueOf(z2));
        return z2;
    }

    private boolean FM_(BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr) {
        if (this.b == null) {
            LogUtil.h("PluginDevice_WspMeasureController", "writeCharacteristic mBluetoothGatt is null ");
            return false;
        }
        if (bluetoothGattCharacteristic == null) {
            LogUtil.h("PluginDevice_WspMeasureController", "writeCharacteristic characteristic is null ");
            return false;
        }
        try {
            bluetoothGattCharacteristic.setValue(bArr);
            bluetoothGattCharacteristic.setWriteType(1);
            boolean writeCharacteristic = this.b.writeCharacteristic(bluetoothGattCharacteristic);
            LogUtil.a("PluginDevice_WspMeasureController", "SDK-->Device:", HexUtils.d(bArr), " tag : ", bluetoothGattCharacteristic.getUuid().toString(), "isSuccess : ", Boolean.valueOf(writeCharacteristic));
            return writeCharacteristic;
        } catch (SecurityException e) {
            LogUtil.b("PluginDevice_WspMeasureController", "writeCharacteristic SecurityException:", ExceptionUtils.d(e));
            return false;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x00b1  */
    /* JADX WARN: Removed duplicated region for block: B:13:? A[RETURN, SYNTHETIC] */
    @Override // com.huawei.health.device.kit.wsp.task.ITaskService
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void write(defpackage.cjq r6, com.huawei.health.device.kit.wsp.task.IAsynBleTaskCallback r7) {
        /*
            r5 = this;
            java.util.UUID r0 = defpackage.cez.ad
            java.util.UUID r1 = defpackage.cez.i
            com.huawei.health.device.kit.wsp.task.BleTaskQueueUtil$TaskType r2 = r6.f()
            com.huawei.health.device.kit.wsp.task.BleTaskQueueUtil$TaskType r3 = com.huawei.health.device.kit.wsp.task.BleTaskQueueUtil.TaskType.SET_AGE
            java.lang.String r4 = "PluginDevice_WspMeasureController"
            if (r2 != r3) goto L13
            java.util.UUID r0 = defpackage.cez.ai
            java.util.UUID r1 = defpackage.cez.f671a
            goto L38
        L13:
            com.huawei.health.device.kit.wsp.task.BleTaskQueueUtil$TaskType r3 = com.huawei.health.device.kit.wsp.task.BleTaskQueueUtil.TaskType.SET_GENDER
            if (r2 != r3) goto L1c
            java.util.UUID r0 = defpackage.cez.ai
            java.util.UUID r1 = defpackage.cez.f
            goto L38
        L1c:
            com.huawei.health.device.kit.wsp.task.BleTaskQueueUtil$TaskType r3 = com.huawei.health.device.kit.wsp.task.BleTaskQueueUtil.TaskType.SET_HEIGHT
            if (r2 != r3) goto L25
            java.util.UUID r0 = defpackage.cez.ai
            java.util.UUID r1 = defpackage.cez.h
            goto L38
        L25:
            com.huawei.health.device.kit.wsp.task.BleTaskQueueUtil$TaskType r3 = com.huawei.health.device.kit.wsp.task.BleTaskQueueUtil.TaskType.CLEAR_USER_INFO
            if (r2 != r3) goto L2e
            java.util.UUID r0 = defpackage.cez.af
            java.util.UUID r1 = defpackage.cez.k
            goto L38
        L2e:
            java.lang.String r2 = "write default"
            java.lang.Object[] r2 = new java.lang.Object[]{r2}
            com.huawei.hwlogsmodel.LogUtil.a(r4, r2)
        L38:
            boolean r2 = r5.r
            if (r2 == 0) goto L58
            r5.u = r7
            java.lang.String r7 = r0.toString()
            java.lang.String r0 = r1.toString()
            boolean r6 = r5.b(r6, r7, r0)
            java.lang.String r7 = "isWriteSuccess:"
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r6)
            java.lang.Object[] r7 = new java.lang.Object[]{r7, r0}
            com.huawei.hwlogsmodel.LogUtil.a(r4, r7)
            goto L95
        L58:
            android.bluetooth.BluetoothGatt r2 = r5.b
            if (r2 != 0) goto L67
            java.lang.String r6 = "write mBluetoothGatt is null"
            java.lang.Object[] r6 = new java.lang.Object[]{r6}
            com.huawei.hwlogsmodel.LogUtil.h(r4, r6)
            return
        L67:
            android.bluetooth.BluetoothGattService r0 = r2.getService(r0)
            if (r0 == 0) goto La3
            android.bluetooth.BluetoothGattCharacteristic r0 = r0.getCharacteristic(r1)
            if (r0 == 0) goto L98
            r5.u = r7
            byte[] r7 = r6.c()
            boolean r7 = r5.FM_(r0, r7)
            com.huawei.health.device.kit.wsp.task.BleTaskQueueUtil$TaskType r6 = r6.f()
            java.lang.String r6 = r6.toString()
            java.lang.String r0 = " isSuccess:"
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r7)
            java.lang.String r2 = "Write key:"
            java.lang.Object[] r6 = new java.lang.Object[]{r2, r6, r0, r1}
            com.huawei.hwlogsmodel.LogUtil.a(r4, r6)
            r6 = r7
        L95:
            if (r6 != 0) goto Lb4
            goto Lad
        L98:
            java.lang.String r6 = "write gattCharacteristic is null"
            java.lang.Object[] r6 = new java.lang.Object[]{r6}
            com.huawei.hwlogsmodel.LogUtil.h(r4, r6)
            goto Lad
        La3:
            java.lang.String r6 = "write gattService is null"
            java.lang.Object[] r6 = new java.lang.Object[]{r6}
            com.huawei.hwlogsmodel.LogUtil.h(r4, r6)
        Lad:
            com.huawei.health.device.kit.wsp.task.IAsynBleTaskCallback r6 = r5.u
            if (r6 == 0) goto Lb4
            r6.failed()
        Lb4:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.cjl.write(cjq, com.huawei.health.device.kit.wsp.task.IAsynBleTaskCallback):void");
    }

    private boolean b(cjq cjqVar, String str, String str2) {
        if (cjqVar == null) {
            LogUtil.h("PluginDevice_WspMeasureController", "TaskData == null");
            return false;
        }
        if (!TextUtils.isEmpty(this.v)) {
            biu biuVar = new biu();
            biuVar.d(cjqVar.c());
            cpl.c().a(biuVar, str, str2, this.v, CharacterOperationType.WRITE);
            LogUtil.c("PluginDevice_WspMeasureController", "writeCommandByUds->", cvx.d(biuVar.a()));
            return true;
        }
        LogUtil.h("PluginDevice_WspMeasureController", "writeCommandByUds-> macAddress is null");
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:13:? A[RETURN, SYNTHETIC] */
    @Override // com.huawei.health.device.kit.wsp.task.ITaskService
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void enable(defpackage.cjq r6, com.huawei.health.device.kit.wsp.task.IAsynBleTaskCallback r7) {
        /*
            r5 = this;
            java.util.UUID r0 = defpackage.cez.ab
            java.util.UUID r1 = defpackage.cez.c
            com.huawei.health.device.kit.wsp.task.BleTaskQueueUtil$TaskType r2 = r6.f()
            com.huawei.health.device.kit.wsp.task.BleTaskQueueUtil$TaskType r3 = com.huawei.health.device.kit.wsp.task.BleTaskQueueUtil.TaskType.ENABLE_WEIGHT_SCALE
            java.lang.String r4 = "PluginDevice_WspMeasureController"
            if (r2 != r3) goto L13
            java.util.UUID r0 = defpackage.cez.ae
            java.util.UUID r1 = defpackage.cez.o
            goto L29
        L13:
            com.huawei.health.device.kit.wsp.task.BleTaskQueueUtil$TaskType r6 = r6.f()
            com.huawei.health.device.kit.wsp.task.BleTaskQueueUtil$TaskType r2 = com.huawei.health.device.kit.wsp.task.BleTaskQueueUtil.TaskType.ENABLE_CLEAR_USER_INFO
            if (r6 != r2) goto L20
            java.util.UUID r0 = defpackage.cez.af
            java.util.UUID r1 = defpackage.cez.l
            goto L29
        L20:
            java.lang.String r6 = "enable default"
            java.lang.Object[] r6 = new java.lang.Object[]{r6}
            com.huawei.hwlogsmodel.LogUtil.a(r4, r6)
        L29:
            boolean r6 = r5.r
            if (r6 == 0) goto L49
            r5.u = r7
            java.lang.String r6 = r0.toString()
            java.lang.String r7 = r1.toString()
            boolean r6 = r5.c(r6, r7)
            java.lang.String r7 = "isEnable:"
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r6)
            java.lang.Object[] r7 = new java.lang.Object[]{r7, r0}
            com.huawei.hwlogsmodel.LogUtil.a(r4, r7)
            goto L60
        L49:
            android.bluetooth.BluetoothGatt r6 = r5.b
            if (r6 == 0) goto L6c
            android.bluetooth.BluetoothGattService r6 = r6.getService(r0)
            if (r6 == 0) goto L63
            android.bluetooth.BluetoothGattCharacteristic r6 = r6.getCharacteristic(r1)
            if (r6 == 0) goto L6c
            r5.u = r7
            r7 = 1
            boolean r6 = r5.FK_(r6, r7)
        L60:
            if (r6 != 0) goto L73
            goto L6c
        L63:
            java.lang.String r6 = "enable BluetoothGattService is null"
            java.lang.Object[] r6 = new java.lang.Object[]{r6}
            com.huawei.hwlogsmodel.LogUtil.h(r4, r6)
        L6c:
            com.huawei.health.device.kit.wsp.task.IAsynBleTaskCallback r6 = r5.u
            if (r6 == 0) goto L73
            r6.failed()
        L73:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.cjl.enable(cjq, com.huawei.health.device.kit.wsp.task.IAsynBleTaskCallback):void");
    }

    private boolean c(String str, String str2) {
        if (!TextUtils.isEmpty(this.v)) {
            cpl.c().e(this.v, str, str2, true);
            LogUtil.a("PluginDevice_WspMeasureController", "enableNotifyByUds-> success");
            return true;
        }
        LogUtil.h("PluginDevice_WspMeasureController", "enableNotifyByUds-> fail");
        return false;
    }
}
