package defpackage;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.huawei.devicesdk.entity.ConnectMode;
import com.huawei.devicesdk.entity.DeviceInfo;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.handler.ExtendHandler;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.health.device.api.DeviceInfoUtilsApi;
import com.huawei.health.device.open.data.model.HealthData;
import com.huawei.health.ecologydevice.callback.NfcMeasureCallback;
import com.huawei.health.ecologydevice.fitness.util.RopeStateMonitor;
import com.huawei.health.ecologydevice.kit.blp.BaseBloodPressureManager;
import com.huawei.health.ecologydevice.manager.BloodPressureStartFromDeviceHelper;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OpAnalyticsConstants;
import com.huawei.unitedevice.entity.UniteDevice;
import health.compact.a.Services;
import java.util.UUID;

/* loaded from: classes3.dex */
public class czu extends BaseBloodPressureManager implements RopeStateMonitor.StateChangeListener {
    private static czu d;
    private static final Object e = new Object();

    /* renamed from: a, reason: collision with root package name */
    private RopeStateMonitor f11561a;
    private NfcMeasureCallback c;
    private BluetoothManager f;
    private BluetoothGattCharacteristic g;
    private BluetoothAdapter h;
    private BluetoothGatt i;
    private BluetoothDevice j;
    private BluetoothGattCharacteristic k;
    private cxh l;
    private BluetoothGattCharacteristic o;
    private int p;
    private BluetoothGattCharacteristic s;
    private czt t;
    private String v;
    private String n = "";
    private boolean r = false;
    private int w = 0;
    private int b = 3;
    private ExtendHandler m = null;
    private BluetoothGattCallback q = new BluetoothGattCallback() { // from class: czu.3
        @Override // android.bluetooth.BluetoothGattCallback
        public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
            super.onConnectionStateChange(bluetoothGatt, i, i2);
            czu.this.Sp_(bluetoothGatt, i, i2);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
            super.onServicesDiscovered(bluetoothGatt, i);
            czu.this.Sq_(bluetoothGatt, i);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            super.onCharacteristicChanged(bluetoothGatt, bluetoothGattCharacteristic);
            UUID uuid = bluetoothGattCharacteristic.getUuid();
            LogUtil.a("BloodPressureManager", "onCharacteristicChanged mBaseResponseCallback:", czu.this.c, ",parser:", czu.this.t, ",data = ", dis.d(bluetoothGattCharacteristic.getValue(), ""));
            if (cez.e.equals(uuid)) {
                czu.this.f();
                HealthData parseData = czu.this.t.parseData(bluetoothGattCharacteristic.getValue());
                if (parseData instanceof deo) {
                    LogUtil.a("BloodPressureManager", "onCharacteristicChanged HeartRateAndBloodPressure");
                    if (czu.this.c != null) {
                        LogUtil.a("BloodPressureManager", "onCharacteristicChanged mBaseResponseCallback != null");
                        czu.this.c.onDataChanged(czu.this.l, parseData);
                        return;
                    }
                    return;
                }
                return;
            }
            if (cez.p.equals(uuid)) {
                dks.WA_(bluetoothGatt, czu.this.k, czu.this.o);
            } else if (cez.ah.equals(uuid)) {
                czu.this.f();
                czu.this.e(bluetoothGattCharacteristic.getValue(), czu.this.t);
            } else {
                LogUtil.a("BloodPressureManager", "onCharacteristicChanged other uuid ");
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
            super.onDescriptorWrite(bluetoothGatt, bluetoothGattDescriptor, i);
            LogUtil.a("BloodPressureManager", "onDescriptorWrite status:", Integer.valueOf(i));
            if (i != 0) {
                return;
            }
            if (bluetoothGattDescriptor == null || bluetoothGattDescriptor.getCharacteristic() == null) {
                LogUtil.h("BloodPressureManager", "descriptor == null || descriptor.getCharacteristic == null");
                return;
            }
            if (cez.e.equals(bluetoothGattDescriptor.getCharacteristic().getUuid())) {
                if (czu.this.o == null && czu.this.k == null) {
                    return;
                }
                LogUtil.a("BloodPressureManager", "enter setDateTime");
                dks.WA_(bluetoothGatt, czu.this.k, czu.this.o);
            }
        }
    };

    @Override // com.huawei.health.ecologydevice.fitness.util.RopeStateMonitor.StateChangeListener
    public void onBondStateChanged(int i, BluetoothDevice bluetoothDevice) {
    }

    private czu() {
        f();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        if (this.t == null) {
            this.t = new czt();
        }
    }

    public void a(String str) {
        this.v = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(byte[] bArr, czt cztVar) {
        if (cztVar == null) {
            LogUtil.a("BloodPressureManager", "bloodPressureDataParser is null");
            return;
        }
        int b = cztVar.b(bArr);
        LogUtil.a("BloodPressureManager", "Click Start on the device.controlType is ", Integer.valueOf(b));
        if (b == 0) {
            LogUtil.a("BloodPressureManager", "BloodPressureControl.MEASUREMENT_STATE_START");
            NfcMeasureCallback nfcMeasureCallback = this.c;
            if (nfcMeasureCallback != null) {
                nfcMeasureCallback.onStartMeasuring();
                return;
            }
            return;
        }
        LogUtil.a("BloodPressureManager", "controlType is ", Integer.valueOf(b));
    }

    public static czu e() {
        czu czuVar;
        synchronized (e) {
            if (d == null) {
                d = new czu();
            }
            czuVar = d;
        }
        return czuVar;
    }

    @Override // com.huawei.health.ecologydevice.kit.blp.BaseBloodPressureManager
    public boolean init(NfcMeasureCallback nfcMeasureCallback) {
        super.init(nfcMeasureCallback);
        e(nfcMeasureCallback);
        n();
        m();
        if (this.h != null) {
            LogUtil.a("BloodPressureManager", "Init already.");
            return true;
        }
        if (this.f == null && (BaseApplication.getContext().getSystemService("bluetooth") instanceof BluetoothManager)) {
            this.f = (BluetoothManager) BaseApplication.getContext().getSystemService("bluetooth");
        }
        BluetoothManager bluetoothManager = this.f;
        if (bluetoothManager == null) {
            LogUtil.b("BloodPressureManager", "Unable to initAdapter BluetoothManager.");
            return false;
        }
        BluetoothAdapter adapter = bluetoothManager.getAdapter();
        this.h = adapter;
        if (adapter != null) {
            return true;
        }
        LogUtil.b("BloodPressureManager", "Unable to obtain BluetoothAdapter.");
        return false;
    }

    private void e(NfcMeasureCallback nfcMeasureCallback) {
        this.c = nfcMeasureCallback;
    }

    private void t() {
        this.c = null;
    }

    private void n() {
        if (this.m == null) {
            this.m = HandlerCenter.yt_(new d(), "BloodPressureManager");
        }
    }

    class d implements Handler.Callback {
        private d() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            int i = message.what;
            if (i == 1) {
                LogUtil.a("BloodPressureManager", "MSG_RECONNECT: now will reconnect");
                czu.this.a();
            } else if (i == 2) {
                LogUtil.a("BloodPressureManager", "DISCONNECT_WHEN_TIMEOUT");
                czu.this.l();
            } else if (i == 3) {
                LogUtil.a("BloodPressureManager", "TIMER_OUT");
                if (czu.this.c != null) {
                    czu.this.c.onStatusChanged(11, czu.this.p, czu.this.w);
                }
                czu.this.b();
            } else if (i == 4) {
                LogUtil.a("BloodPressureManager", "BLUETOOTH_TURN_OFF");
                if (czu.this.c != null) {
                    czu.this.c.onStatusChanged(12, czu.this.p, czu.this.w);
                }
            } else {
                LogUtil.a("BloodPressureManager", "default case");
                return false;
            }
            return true;
        }
    }

    private void m() {
        RopeStateMonitor ropeStateMonitor = this.f11561a;
        if (ropeStateMonitor != null) {
            ropeStateMonitor.d();
        }
        RopeStateMonitor ropeStateMonitor2 = new RopeStateMonitor(BaseApplication.getContext(), this);
        this.f11561a = ropeStateMonitor2;
        ropeStateMonitor2.c();
    }

    private void s() {
        if (this.m != null) {
            LogUtil.a("BloodPressureManager", "releaseHandler mExtendHandler will removeCallbacksAndMessages");
            this.m.removeTasksAndMessages();
            this.m.quit(false);
            this.m = null;
        }
    }

    private void o() {
        RopeStateMonitor ropeStateMonitor = this.f11561a;
        if (ropeStateMonitor != null) {
            ropeStateMonitor.d();
            this.f11561a = null;
        }
    }

    @Override // com.huawei.health.ecologydevice.kit.blp.BaseBloodPressureManager
    public void releaseResource() {
        super.releaseResource();
        LogUtil.a("BloodPressureManager", "Enter release");
        b();
        t();
        s();
        o();
        j();
    }

    private static void j() {
        synchronized (e) {
            d = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        LogUtil.a("BloodPressureManager", "meet the reconnect conditions");
        int i = this.w + 1;
        this.w = i;
        if (i == 1) {
            LogUtil.a("BloodPressureManager", "first reconnect zoro");
            NfcMeasureCallback nfcMeasureCallback = this.c;
            if (nfcMeasureCallback != null) {
                nfcMeasureCallback.onStatusChanged(10, this.p, this.w);
            }
        }
        i();
        c(1, 2000L);
    }

    private void i() {
        synchronized (e) {
            if (this.i != null) {
                LogUtil.a("BloodPressureManager", "start to close gatt...");
                try {
                    this.i.close();
                    this.i = null;
                } catch (SecurityException e2) {
                    LogUtil.b("BloodPressureManager", "closeBluetoothGattBeforeReconnect SecurityException:", ExceptionUtils.d(e2));
                }
            }
        }
    }

    public boolean a() {
        LogUtil.a("BloodPressureManager", "reconnect!");
        c(2, 5000L);
        try {
            if (!TextUtils.isEmpty(this.n)) {
                this.j = this.h.getRemoteDevice(this.n);
            } else {
                LogUtil.h("BloodPressureManager", "in reConnect, mac addr is empty");
                this.j = null;
            }
            if (this.j == null) {
                LogUtil.h("BloodPressureManager", "Device not found. Unable to connect.");
                return false;
            }
            BluetoothGatt bluetoothGatt = this.i;
            if (bluetoothGatt != null) {
                bluetoothGatt.close();
                this.i = null;
            }
            if (dkq.c().d()) {
                h();
            } else {
                this.i = this.j.connectGatt(cpp.a(), false, this.q);
            }
            LogUtil.a("BloodPressureManager", "Trying to create new connection (in reConnect).");
            return true;
        } catch (SecurityException e2) {
            LogUtil.b("BloodPressureManager", "reconnection SecurityException:", ExceptionUtils.d(e2));
            return false;
        }
    }

    private void c(int i, long j) {
        ExtendHandler extendHandler = this.m;
        if (extendHandler != null) {
            extendHandler.sendEmptyMessage(i, j);
        }
    }

    private void c(int i) {
        ExtendHandler extendHandler = this.m;
        if (extendHandler != null) {
            extendHandler.sendEmptyMessage(i);
        }
    }

    public void b(String str) {
        LogUtil.a("BloodPressureManager", "in connectByMac");
        if (this.m == null) {
            LogUtil.h("BloodPressureManager", "connectByMac,mExtendHandler is null,create");
            this.m = HandlerCenter.yt_(new d(), "BloodPressureManager");
        }
        c(3, OpAnalyticsConstants.H5_LOADING_DELAY);
        c(2, 5000L);
        if (this.h == null || TextUtils.isEmpty(str)) {
            LogUtil.h("BloodPressureManager", "BluetoothAdapter not initialized or unspecified address.");
            this.j = null;
            return;
        }
        if (str.equals(this.n) && this.i != null) {
            LogUtil.a("BloodPressureManager", "Trying to use an existing mBluetoothGatt for connection.");
            return;
        }
        try {
            BluetoothDevice remoteDevice = this.h.getRemoteDevice(str);
            this.j = remoteDevice;
            if (remoteDevice == null) {
                return;
            }
            this.l = cxh.Ra_(remoteDevice);
            ExtendHandler extendHandler = this.m;
            if (extendHandler != null) {
                extendHandler.removeMessages(2);
            }
            So_(this.j);
        } catch (IllegalArgumentException unused) {
            LogUtil.b("BloodPressureManager", "mac IllegalArgumentException");
        }
    }

    private void So_(BluetoothDevice bluetoothDevice) {
        String address;
        LogUtil.a("BloodPressureManager", "in connectDevice");
        c(2, 5000L);
        if (bluetoothDevice == null) {
            LogUtil.h("BloodPressureManager", "connectDevice btDevice not initialized");
            return;
        }
        if (this.m == null) {
            this.m = HandlerCenter.yt_(new d(), "BloodPressureManager");
        }
        try {
            address = bluetoothDevice.getAddress();
        } catch (SecurityException e2) {
            LogUtil.b("BloodPressureManager", "connectDevice SecurityException:", ExceptionUtils.d(e2));
        }
        if (address.equals(this.n) && this.i != null) {
            LogUtil.a("BloodPressureManager", "Trying to use an existing BluetoothGatt for connection");
            return;
        }
        this.n = address;
        this.r = false;
        BluetoothGatt bluetoothGatt = this.i;
        if (bluetoothGatt != null) {
            bluetoothGatt.close();
            this.i = null;
        }
        if (dkq.c().d()) {
            h();
        } else {
            this.i = bluetoothDevice.connectGatt(cpp.a(), false, this.q);
        }
        LogUtil.a("BloodPressureManager", "Trying to create new connection");
    }

    private void h() {
        UniteDevice d2 = dkq.c().d(this.n, 2);
        if (d2 == null) {
            LogUtil.h("BloodPressureManager", "udsDevice is null");
        } else {
            ddw.c().e(d2, ConnectMode.TRANSPARENT, 2, false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Sp_(BluetoothGatt bluetoothGatt, int i, int i2) {
        LogUtil.a("BloodPressureManager", "doConnectionStateChange, oldStatus=", Integer.valueOf(i), " NewStates=", Integer.valueOf(i2));
        this.p = i2;
        if (i2 == 2) {
            LogUtil.a("BloodPressureManager", "doConnectionStateChange STATUS_CONNECTED.");
            this.i = bluetoothGatt;
            if (BloodPressureStartFromDeviceHelper.c()) {
                LogUtil.a("BloodPressureManager", "NewMeasurementProcedure discoverService");
                c();
                return;
            }
            this.r = true;
            ExtendHandler extendHandler = this.m;
            if (extendHandler != null) {
                extendHandler.removeMessages(3);
                this.m.removeMessages(2);
            }
            this.b = 2;
            this.w = 0;
            NfcMeasureCallback nfcMeasureCallback = this.c;
            if (nfcMeasureCallback != null) {
                nfcMeasureCallback.onStatusChanged(2, i2, 0);
                return;
            }
            return;
        }
        if (i2 != 0) {
            if (i2 == 1) {
                LogUtil.c("BloodPressureManager", "doConnectionStateChange connecting and newState =", Integer.valueOf(i2));
                this.b = 1;
                NfcMeasureCallback nfcMeasureCallback2 = this.c;
                if (nfcMeasureCallback2 != null) {
                    nfcMeasureCallback2.onStatusChanged(1, i2, this.w);
                    return;
                }
                return;
            }
            LogUtil.h("BloodPressureManager", "doConnectionStateChange other State = ", Integer.valueOf(i2));
            return;
        }
        LogUtil.a("BloodPressureManager", "doConnectionStateChange STATE_DISCONNECTED.");
        ExtendHandler extendHandler2 = this.m;
        if (extendHandler2 != null) {
            extendHandler2.removeMessages(2);
        }
        this.b = 3;
        NfcMeasureCallback nfcMeasureCallback3 = this.c;
        if (nfcMeasureCallback3 != null) {
            nfcMeasureCallback3.onStatusChanged(3, i2, this.w);
        }
        if (this.r) {
            b();
            LogUtil.a("BloodPressureManager", "doConnectionStateChange ConnectedBefore, releaseBleSource.");
        } else {
            l();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Sq_(BluetoothGatt bluetoothGatt, int i) {
        LogUtil.a("BloodPressureManager", "Enter doServiceDiscover, status:", Integer.valueOf(i));
        if (i != 0) {
            LogUtil.h("BloodPressureManager", "doServiceDiscover failed");
            return;
        }
        try {
            BluetoothGattService service = bluetoothGatt.getService(cez.aa);
            if (service == null) {
                LogUtil.h("BloodPressureManager", "gattService is null");
                return;
            }
            this.g = service.getCharacteristic(cez.e);
            this.k = service.getCharacteristic(cez.s);
            BluetoothGattCharacteristic characteristic = service.getCharacteristic(cez.ah);
            this.s = characteristic;
            if (characteristic != null) {
                LogUtil.a("BloodPressureManager", " doServiceDiscover setCharacteristicNotification isEnable:", Boolean.valueOf(bluetoothGatt.setCharacteristicNotification(characteristic, true)));
            }
            if (this.g != null) {
                try {
                    Thread.sleep(500L);
                } catch (InterruptedException unused) {
                    LogUtil.b("BloodPressureManager", "InterruptedException");
                }
                LogUtil.a("BloodPressureManager", "doServiceDiscover enableIndication isEnable: ", Boolean.valueOf(Sr_(bluetoothGatt, this.g)));
            }
            try {
                Thread.sleep(600L);
            } catch (InterruptedException unused2) {
                LogUtil.b("BloodPressureManager", "InterruptedException");
            }
            BluetoothGattService service2 = bluetoothGatt.getService(cez.r);
            if (service2 != null) {
                BluetoothGattCharacteristic characteristic2 = service2.getCharacteristic(cez.p);
                this.o = characteristic2;
                if (characteristic2 == null) {
                    return;
                }
                LogUtil.a("BloodPressureManager", "mCurrentTimeCharacteristic setCharacteristicNotification: ", Boolean.valueOf(bluetoothGatt.setCharacteristicNotification(characteristic2, true)));
                BluetoothGattDescriptor descriptor = this.o.getDescriptor(cez.n);
                if (descriptor != null) {
                    descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
                    bluetoothGatt.writeDescriptor(descriptor);
                }
            }
            b(i);
        } catch (SecurityException e2) {
            LogUtil.b("BloodPressureManager", "doServiceDiscover SecurityException:", ExceptionUtils.d(e2));
        }
    }

    private void b(int i) {
        LogUtil.a("BloodPressureManager", "gatt service discover success");
        if (BloodPressureStartFromDeviceHelper.c()) {
            LogUtil.a("BloodPressureManager", "NewMeasurementProcedure connect success");
            this.r = true;
            ExtendHandler extendHandler = this.m;
            if (extendHandler != null) {
                extendHandler.removeMessages(3);
                this.m.removeMessages(2);
            }
            this.b = 2;
            this.w = 0;
            NfcMeasureCallback nfcMeasureCallback = this.c;
            if (nfcMeasureCallback != null) {
                nfcMeasureCallback.onStatusChanged(2, i, 0);
            }
        }
    }

    public boolean Sr_(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        LogUtil.a("BloodPressureManager", "Enter enableIndications");
        if (bluetoothGatt != null && bluetoothGattCharacteristic != null) {
            try {
            } catch (SecurityException e2) {
                LogUtil.b("BloodPressureManager", "enableIndications SecurityException:", ExceptionUtils.d(e2));
            }
            if ((bluetoothGattCharacteristic.getProperties() & 32) == 0) {
                return false;
            }
            bluetoothGatt.setCharacteristicNotification(bluetoothGattCharacteristic, true);
            BluetoothGattDescriptor descriptor = bluetoothGattCharacteristic.getDescriptor(cez.n);
            if (descriptor != null) {
                if (BloodPressureStartFromDeviceHelper.c()) {
                    LogUtil.a("BloodPressureManager", "set indication value BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE");
                    descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
                } else {
                    descriptor.setValue(BluetoothGattDescriptor.ENABLE_INDICATION_VALUE);
                }
                return bluetoothGatt.writeDescriptor(descriptor);
            }
            LogUtil.h("BloodPressureManager", "descriptor is null");
            return false;
        }
        LogUtil.h("BloodPressureManager", "enableIndications gatt or characteristic is null");
        return false;
    }

    public void b() {
        LogUtil.a("BloodPressureManager", "releaseBleSource");
        synchronized (e) {
            if (this.i != null) {
                LogUtil.a("BloodPressureManager", "start to close gatt...");
                try {
                    this.i.close();
                    this.i = null;
                } catch (SecurityException e2) {
                    LogUtil.b("BloodPressureManager", "releaseBleSource SecurityException:", ExceptionUtils.d(e2));
                    return;
                }
            }
            if (dkq.c().d()) {
                k();
            }
            s();
            this.b = 3;
            this.w = 0;
            this.n = null;
            this.j = null;
            this.g = null;
            this.k = null;
            this.o = null;
            this.t = null;
        }
    }

    private void k() {
        UniteDevice d2 = dkq.c().d(this.n, 2);
        if (d2 == null) {
            d2 = null;
        }
        if (d2 == null) {
            LogUtil.h("BloodPressureManager", "udsDevice is null");
        } else {
            ddw.c().b(d2);
        }
    }

    public int d() {
        return this.b;
    }

    public void c() {
        DeviceInfoUtilsApi deviceInfoUtilsApi = (DeviceInfoUtilsApi) Services.c("PluginDevice", DeviceInfoUtilsApi.class);
        if (dkq.c().d()) {
            deviceInfoUtilsApi.setCharacteristicNotifyByUds(this.n, cez.aa.toString(), cez.e.toString(), true);
            try {
                Thread.sleep(600L);
            } catch (InterruptedException unused) {
                LogUtil.b("BloodPressureManager", "discoverService InterruptedException");
            }
            deviceInfoUtilsApi.setCharacteristicNotifyByUds(this.n, cez.r.toString(), cez.p.toString(), true);
            return;
        }
        BluetoothGatt bluetoothGatt = this.i;
        if (bluetoothGatt == null) {
            LogUtil.h("BloodPressureManager", "mBluetoothGatt == null");
            return;
        }
        try {
            bluetoothGatt.discoverServices();
        } catch (SecurityException e2) {
            LogUtil.b("BloodPressureManager", "discoverService SecurityException:", ExceptionUtils.d(e2));
        }
    }

    public void g() {
        if (this.s != null) {
            f();
            LogUtil.a("BloodPressureManager", "setBlpControl ", Boolean.valueOf(this.t.Sj_(this.i, this.s, 1, 0)));
        } else {
            LogUtil.a("BloodPressureManager", "mMeasuringCharacteristic is null");
        }
    }

    @Override // com.huawei.health.ecologydevice.fitness.util.RopeStateMonitor.StateChangeListener
    public void onSwitchStateChanged(int i) {
        if (i != 3) {
            if (i == 4) {
                LogUtil.a("BloodPressureManager", "BLUETOOTH_SWITCH_ON");
                return;
            } else {
                LogUtil.a("BloodPressureManager", "BluetoothSwitchMonitorReceiver other blueState: ", Integer.valueOf(i));
                return;
            }
        }
        LogUtil.a("BloodPressureManager", "BLUETOOTH_SWITCH_OFF");
        if (!this.r) {
            LogUtil.a("BloodPressureManager", "mIsBrokenButHasConnectedBefore is false");
            c(4);
        } else {
            Sp_(null, 2, 0);
        }
    }

    @Override // com.huawei.health.ecologydevice.callback.ConnectStatusCallback
    public void doDeviceConnected(int i, DeviceInfo deviceInfo) {
        if (deviceInfo == null || deviceInfo.getDeviceMac() == null) {
            LogUtil.h("BloodPressureManager", "deviceInfo == null || deviceMac == null");
            return;
        }
        LogUtil.a("BloodPressureManager", "doConnectionStateChange STATUS_CONNECTED.");
        this.p = i;
        this.r = true;
        ExtendHandler extendHandler = this.m;
        if (extendHandler != null) {
            extendHandler.removeMessages(3);
            this.m.removeMessages(2);
        }
        this.b = 2;
        this.w = 0;
        NfcMeasureCallback nfcMeasureCallback = this.c;
        if (nfcMeasureCallback != null) {
            nfcMeasureCallback.onStatusChanged(2, this.p, 0);
        }
    }

    @Override // com.huawei.health.ecologydevice.callback.ConnectStatusCallback
    public void doDeviceDisconnect(int i) {
        LogUtil.a("BloodPressureManager", "doConnectionStateChange STATE_DISCONNECTED.");
        ExtendHandler extendHandler = this.m;
        if (extendHandler != null) {
            extendHandler.removeMessages(2);
        }
        this.b = 3;
        NfcMeasureCallback nfcMeasureCallback = this.c;
        if (nfcMeasureCallback != null) {
            nfcMeasureCallback.onStatusChanged(3, this.p, this.w);
        }
        if (this.r) {
            b();
            LogUtil.a("BloodPressureManager", "doConnectionStateChange ConnectedBefore, releaseBleSource.");
        } else {
            l();
        }
    }

    @Override // com.huawei.health.ecologydevice.callback.ConnectStatusCallback
    public void doDeviceConnecting(int i) {
        this.p = i;
        LogUtil.a("BloodPressureManager", "doConnectionStateChange connecting and newState =", Integer.valueOf(i));
        this.b = 1;
        NfcMeasureCallback nfcMeasureCallback = this.c;
        if (nfcMeasureCallback != null) {
            nfcMeasureCallback.onStatusChanged(1, this.p, this.w);
        }
    }

    @Override // com.huawei.health.ecologydevice.callback.DataChangedCallback
    public void onDataChanged(biu biuVar) {
        if (biuVar == null) {
            LogUtil.h("BloodPressureManager", "dataContents == null");
            return;
        }
        String b = biuVar.b();
        if (b == null) {
            LogUtil.h("BloodPressureManager", "characterUuid == null");
            return;
        }
        if (cez.e.toString().equals(b)) {
            f();
            HealthData parseData = this.t.parseData(biuVar.a());
            if (parseData instanceof deo) {
                LogUtil.a("BloodPressureManager", "onCharacteristicChanged HeartRateAndBloodPressure");
                if (this.c != null) {
                    LogUtil.a("BloodPressureManager", "onCharacteristicChanged mBaseResponseCallback != null");
                    this.c.onDataChanged(this.l, parseData);
                }
            }
            dkq.c().e(cez.aa.toString(), cez.e.toString(), this.n);
            return;
        }
        if (cez.p.toString().equals(b)) {
            dkq.c().e(cez.r.toString(), cez.s.toString(), this.n);
        } else {
            LogUtil.a("BloodPressureManager", "onCharacteristicChanged other uuid ");
        }
    }
}
