package com.huawei.health.ecologydevice.fitness;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.handler.ExtendHandler;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.ecologydevice.fitness.RopeDataQueues;
import com.huawei.health.ecologydevice.fitness.RopeFittingsClient;
import com.huawei.health.ecologydevice.fitness.ScannerController;
import com.huawei.health.ecologydevice.fitness.dataparser.secondropecommand.CommandArrayList;
import com.huawei.health.ecologydevice.fitness.dataparser.secondropecommand.Receiver;
import com.huawei.health.ecologydevice.fitness.datastruct.BaseRopeData;
import com.huawei.health.ecologydevice.ui.util.PermissionDialogHelper;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OpAnalyticsConstants;
import com.huawei.operation.ble.BleConstants;
import defpackage.cye;
import defpackage.dis;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Locale;
import java.util.UUID;

/* loaded from: classes3.dex */
public class RopeFittingsClient implements Receiver.CommandReceiveCallback {

    /* renamed from: a, reason: collision with root package name */
    private BluetoothAdapter f2284a;
    private int f;
    private BluetoothDevice g;
    private BluetoothManager h;
    private BluetoothGatt i;
    private ExtendHandler l;
    private String n;
    private FittingsMessageOrStateCallback o;
    private BluetoothGattCharacteristic p;
    private BluetoothGattCharacteristic q;
    private ScannerController v;
    private RopeDataQueues x;
    private static final UUID e = UUID.fromString("0000fff0-0000-1000-8000-00805f9b34fb");
    private static final UUID b = UUID.fromString("0000fff2-0000-1000-8000-00805f9b34fb");
    private static final UUID c = UUID.fromString("0000fff3-0000-1000-8000-00805f9b34fb");
    private static final UUID d = UUID.fromString(BleConstants.CLIENT_CHARACTERISTIC_CONFIG_DESCRIPTOR_UUID);
    private long j = 0;
    private int s = 0;
    private boolean t = false;
    private boolean m = false;
    private int u = 0;
    private final BluetoothGattCallback k = new BluetoothGattCallback() { // from class: com.huawei.health.ecologydevice.fitness.RopeFittingsClient.3
        @Override // android.bluetooth.BluetoothGattCallback
        public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
            RopeFittingsClient.this.RV_(bluetoothGatt, i, i2);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
            super.onServicesDiscovered(bluetoothGatt, i);
            RopeFittingsClient.this.RO_(bluetoothGatt, i);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            super.onCharacteristicChanged(bluetoothGatt, bluetoothGattCharacteristic);
            RopeFittingsClient.this.RS_(bluetoothGattCharacteristic);
        }
    };
    private cye r = new cye();

    public interface FittingsMessageOrStateCallback {
        void onNewMessage(String str, int i, Bundle bundle);

        void onStateChange(String str, String str2);
    }

    public RopeFittingsClient(String str) {
        this.n = str;
        g();
        j();
        i();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void RS_(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (bluetoothGattCharacteristic == null || this.o == null) {
            Object[] objArr = new Object[4];
            objArr[0] = "characteristic == null?";
            objArr[1] = Boolean.valueOf(bluetoothGattCharacteristic == null);
            objArr[2] = " mFittingsMessageOrStateCallback == null ?";
            objArr[3] = Boolean.valueOf(this.o == null);
            LogUtil.a("RopeFittingsClient", objArr);
            return;
        }
        UUID uuid = bluetoothGattCharacteristic.getUuid();
        if (uuid == null) {
            LogUtil.a("RopeFittingsClient", "onCharacteristicChanged, charUUID is null");
            return;
        }
        String upperCase = uuid.toString().toUpperCase(Locale.ENGLISH);
        LogUtil.a("RopeFittingsClient", "onFitnessCharacteristicChanged, charUUID is not null:" + upperCase);
        String d2 = dis.d(bluetoothGattCharacteristic.getValue(), "");
        LogUtil.a("RopeFittingsClient", "onFitnessCharacteristicChanged, DATA:", d2);
        if ("0000fff3-0000-1000-8000-00805f9b34fb".toUpperCase(Locale.ENGLISH).equals(upperCase)) {
            Receiver.d().b(this);
            Receiver.d().b(bluetoothGattCharacteristic.getValue());
        } else {
            LogUtil.a("RopeFittingsClient", "onFitnessCharacteristicChanged, value:" + d2);
        }
    }

    private void i() {
        RopeDataQueues ropeDataQueues = new RopeDataQueues(1000L, 500L);
        this.x = ropeDataQueues;
        ropeDataQueues.e(new RopeDataQueues.RopeDataListener() { // from class: cyd
            @Override // com.huawei.health.ecologydevice.fitness.RopeDataQueues.RopeDataListener
            public final void onResult(byte[] bArr, boolean z) {
                RopeFittingsClient.this.e(bArr, z);
            }
        });
    }

    public /* synthetic */ void e(byte[] bArr, boolean z) {
        RW_(z ? this.q : this.p, bArr);
    }

    private void j() {
        if (this.l == null) {
            this.l = HandlerCenter.yt_(new b(), "RopeFittingsClient");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void RV_(BluetoothGatt bluetoothGatt, int i, int i2) {
        LogUtil.a("RopeFittingsClient", "oldStatus= ", Integer.valueOf(i), " NewStates= ", Integer.valueOf(i2));
        this.f = i2;
        ExtendHandler extendHandler = this.l;
        if (extendHandler == null) {
            e("STATUS_CONNECT_FAIL");
            return;
        }
        if (i2 == 2) {
            extendHandler.removeTasksAndMessages();
            RU_(bluetoothGatt);
            return;
        }
        if (i2 == 0) {
            f();
            return;
        }
        if (i2 == 1) {
            e("STATUS_GATT_STATE_CONNECTING");
        } else if (i2 == 3) {
            e("STATUS_GATT_STATE_DISCONNECTING");
        } else {
            LogUtil.c("RopeFittingsClient", "other gatt error code");
            f();
        }
    }

    private void f() {
        BluetoothAdapter bluetoothAdapter;
        LogUtil.a("RopeFittingsClient", "stateIsDisconnect isNewConnect:", Boolean.valueOf(this.t), " ,scanTimes:", Integer.valueOf(this.u));
        if (this.t) {
            if (this.u == 1) {
                d();
                return;
            } else {
                LogUtil.a("RopeFittingsClient", "The reconnection mechanism is not triggered for new connection.");
                e("REQUEST_RECONNECT_DEVICE");
                return;
            }
        }
        LogUtil.a("RopeFittingsClient", "Disconnected from GATT server. mReconnectTimes:", Integer.valueOf(this.s));
        if (this.s <= 2) {
            try {
                bluetoothAdapter = this.f2284a;
            } catch (SecurityException e2) {
                LogUtil.b("RopeFittingsClient", "handleDisConnectStatus SecurityException:", ExceptionUtils.d(e2));
            }
            if (bluetoothAdapter != null && bluetoothAdapter.isEnabled() && !TextUtils.isEmpty(this.n)) {
                e("REQUEST_RECONNECT_DEVICE");
                BluetoothGatt bluetoothGatt = this.i;
                if (bluetoothGatt != null) {
                    bluetoothGatt.close();
                    this.i = null;
                }
                this.l.sendEmptyMessage(3, 1000L);
                return;
            }
            this.l.removeTasksAndMessages();
            e("STATUS_CONNECT_FAIL");
            return;
        }
        d();
    }

    private void d() {
        e("STATUS_CONNECT_FAIL");
        this.l.removeTasksAndMessages();
    }

    private void RU_(BluetoothGatt bluetoothGatt) {
        this.i = bluetoothGatt;
        this.s = 0;
        this.t = false;
        this.u = 0;
        LogUtil.a("RopeFittingsClient", "Connected to GATT server. Time-consuming: ", Long.valueOf(SystemClock.elapsedRealtime() - this.j));
        e("STATUS_CONNECT_SUCCESS");
        this.l.sendEmptyMessage(2, 1000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void RO_(BluetoothGatt bluetoothGatt, int i) {
        ExtendHandler extendHandler = this.l;
        if (extendHandler != null) {
            extendHandler.removeTasksAndMessages();
        }
        LogUtil.a("RopeFittingsClient", "onServicesDiscovered status: ", Integer.valueOf(i));
        if (i != 0) {
            LogUtil.h("RopeFittingsClient", "onServicesDiscovered received: ", Integer.valueOf(i));
            e("GATT_ERROR");
        } else {
            RP_(bluetoothGatt);
        }
    }

    private void RP_(BluetoothGatt bluetoothGatt) {
        if (bluetoothGatt == null) {
            LogUtil.a("RopeFittingsClient", "cannot get service bcz gatt is null");
            e("GATT_ERROR");
            return;
        }
        UUID uuid = e;
        BluetoothGattService service = bluetoothGatt.getService(uuid);
        if (service == null) {
            LogUtil.c("RopeFittingsClient", "getService, mRopeService is null:", uuid);
            e("GATT_ERROR");
            return;
        }
        LogUtil.a("RopeFittingsClient", "Rope Service found");
        e("GET_SERVICE_SUCCESS");
        RQ_(service);
        RR_(service);
        RT_(this.q, true);
    }

    private void RT_(BluetoothGattCharacteristic bluetoothGattCharacteristic, boolean z) {
        try {
            if (this.i != null && bluetoothGattCharacteristic != null) {
                LogUtil.a("RopeFittingsClient", "setCharacteristicNotification isEnable:", Boolean.valueOf(z));
                this.i.setCharacteristicNotification(bluetoothGattCharacteristic, z);
                LogUtil.a("RopeFittingsClient", "setCharacteristicNotification, will mBluetoothGatt.writeDescriptor");
                BluetoothGattDescriptor descriptor = bluetoothGattCharacteristic.getDescriptor(d);
                if (descriptor == null) {
                    LogUtil.h("RopeFittingsClient", "setCharacteristicNotification descriptor is null");
                    return;
                }
                if (z) {
                    if ((bluetoothGattCharacteristic.getProperties() & 16) != 0) {
                        descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
                    } else if ((bluetoothGattCharacteristic.getProperties() & 32) != 0) {
                        descriptor.setValue(BluetoothGattDescriptor.ENABLE_INDICATION_VALUE);
                    }
                } else {
                    descriptor.setValue(BluetoothGattDescriptor.DISABLE_NOTIFICATION_VALUE);
                }
                LogUtil.a("RopeFittingsClient", "setCharacteristicNotification, mBluetoothGatt.writeDescriptor:", bluetoothGattCharacteristic.getUuid().toString(), ", and Descriptor is:", descriptor.getUuid().toString());
                LogUtil.a("RopeFittingsClient", "setCharacteristicNotification isSuccess:", Boolean.valueOf(this.i.writeDescriptor(descriptor)));
                return;
            }
            LogUtil.h("RopeFittingsClient", "BluetoothAdapter not initialized");
        } catch (SecurityException e2) {
            LogUtil.b("RopeFittingsClient", "setCharacteristicNotification SecurityException:", ExceptionUtils.d(e2));
        }
    }

    private void RQ_(BluetoothGattService bluetoothGattService) {
        BluetoothGattCharacteristic characteristic = bluetoothGattService.getCharacteristic(b);
        Object[] objArr = new Object[2];
        objArr[0] = "setRopeCharacteristicNotification() controlPointCharacteristic is null ";
        objArr[1] = Boolean.valueOf(characteristic == null);
        LogUtil.a("RopeFittingsClient", objArr);
        this.p = characteristic;
    }

    private void RR_(BluetoothGattService bluetoothGattService) {
        BluetoothGattCharacteristic characteristic = bluetoothGattService.getCharacteristic(c);
        Object[] objArr = new Object[2];
        objArr[0] = "setRopeCharacteristicNotification() ropeConfigCharacteristic is null ";
        objArr[1] = Boolean.valueOf(characteristic == null);
        LogUtil.a("RopeFittingsClient", objArr);
        this.q = characteristic;
    }

    private void g() {
        if (this.f2284a != null) {
            LogUtil.a("RopeFittingsClient", "Init already.");
            return;
        }
        if (this.h == null && (BaseApplication.e().getSystemService("bluetooth") instanceof BluetoothManager)) {
            this.h = (BluetoothManager) BaseApplication.e().getSystemService("bluetooth");
        }
        BluetoothManager bluetoothManager = this.h;
        if (bluetoothManager == null) {
            LogUtil.b("RopeFittingsClient", "Unable to initAdapter BluetoothManager.");
            return;
        }
        BluetoothAdapter adapter = bluetoothManager.getAdapter();
        this.f2284a = adapter;
        if (adapter == null) {
            LogUtil.b("RopeFittingsClient", "Unable to obtain BluetoothAdapter.");
        }
    }

    public boolean b() {
        return this.f == 2;
    }

    public void c() {
        BluetoothAdapter bluetoothAdapter;
        try {
            bluetoothAdapter = this.f2284a;
        } catch (SecurityException e2) {
            LogUtil.b("RopeFittingsClient", "connectFittingsByMac SecurityException:", ExceptionUtils.d(e2));
        }
        if (bluetoothAdapter != null && bluetoothAdapter.isEnabled() && !TextUtils.isEmpty(this.n)) {
            this.j = SystemClock.elapsedRealtime();
            LogUtil.c("RopeFittingsClient", "in connectFittingsByMac,mac:", this.n);
            this.g = this.f2284a.getRemoteDevice(this.n);
            BluetoothGatt bluetoothGatt = this.i;
            if (bluetoothGatt != null) {
                bluetoothGatt.close();
                this.i = null;
            }
            this.i = this.g.connectGatt(BaseApplication.e(), false, this.k);
            this.s = 0;
            this.t = true;
            this.m = false;
            this.l.sendEmptyMessage(1, PreConnectManager.CONNECT_INTERNAL);
            LogUtil.c("RopeFittingsClient", "start connectFittings");
            return;
        }
        LogUtil.h("RopeFittingsClient", "BluetoothAdapter not initialized or unspecified address.");
        e("STATUS_CONNECT_FAIL");
    }

    public void b(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("RopeFittingsClient", "mac is error");
            e("STATUS_CONNECT_FAIL");
            return;
        }
        LogUtil.a("RopeFittingsClient", "reConnectDevice!");
        try {
            BluetoothAdapter bluetoothAdapter = this.f2284a;
            if (bluetoothAdapter != null && bluetoothAdapter.isEnabled() && !TextUtils.isEmpty(str)) {
                if (this.m) {
                    LogUtil.a("RopeFittingsClient", "Active disconnection without triggering reconnection");
                    e("STATUS_CONNECT_FAIL");
                    return;
                }
                this.j = SystemClock.elapsedRealtime();
                BluetoothDevice remoteDevice = this.f2284a.getRemoteDevice(str);
                this.g = remoteDevice;
                if (remoteDevice == null) {
                    e("STATUS_CONNECT_FAIL");
                    return;
                }
                LogUtil.a("RopeFittingsClient", "Record the number of reConnections : ", Integer.valueOf(this.s));
                this.i = this.g.connectGatt(BaseApplication.e(), false, this.k);
                if (this.l != null && this.s == 0) {
                    LogUtil.a("RopeFittingsClient", "Start counting down 30 seconds.");
                    this.l.sendEmptyMessage(3, OpAnalyticsConstants.H5_LOADING_DELAY);
                }
                this.s++;
                return;
            }
            LogUtil.h("RopeFittingsClient", "BluetoothAdapter not initialized or unspecified address.");
            e("STATUS_CONNECT_FAIL");
        } catch (SecurityException e2) {
            LogUtil.b("RopeFittingsClient", "reConnectDevice SecurityException:", ExceptionUtils.d(e2));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(String str) {
        if (this.o != null) {
            LogUtil.c("RopeFittingsClient", "status = ", str);
            this.o.onStateChange(this.n, str);
        }
    }

    public void a() {
        BluetoothGatt bluetoothGatt = this.i;
        if (bluetoothGatt != null) {
            try {
                LogUtil.a("RopeFittingsClient", "Attempting to start service discovery:", Boolean.valueOf(bluetoothGatt.discoverServices()));
            } catch (SecurityException e2) {
                LogUtil.b("RopeFittingsClient", "discoverServices SecurityException:", ExceptionUtils.d(e2));
            }
            this.l.sendEmptyMessage(4, PreConnectManager.CONNECT_INTERNAL);
            return;
        }
        e("GATT_ERROR");
    }

    public void e() {
        if (this.i == null) {
            LogUtil.h("RopeFittingsClient", "mBluetoothGatt is null");
            return;
        }
        this.o = null;
        this.m = true;
        LogUtil.a("RopeFittingsClient", "disconnect has been called");
        try {
            this.i.disconnect();
            o();
            this.x.b();
            this.s = 0;
            this.t = false;
            this.u = 0;
        } catch (SecurityException e2) {
            LogUtil.b("RopeFittingsClient", "disconnectFittings SecurityException:", ExceptionUtils.d(e2));
        }
    }

    private void o() {
        if (this.l != null) {
            LogUtil.a("RopeFittingsClient", "msgHandler will removeCallbacksAndMessages");
            this.l.removeTasksAndMessages();
            this.l.quit(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        LogUtil.a("RopeFittingsClient", "startScannerDevice");
        if (!h()) {
            LogUtil.h("RopeFittingsClient", "No Scan permission.");
            return;
        }
        ScannerController scannerController = new ScannerController(this.n);
        this.v = scannerController;
        scannerController.a(new ScannerController.ScanResultCallback() { // from class: cyc
            @Override // com.huawei.health.ecologydevice.fitness.ScannerController.ScanResultCallback
            public final void onResult(int i, HealthDevice healthDevice) {
                RopeFittingsClient.this.a(i, healthDevice);
            }
        });
        this.v.c();
    }

    public /* synthetic */ void a(int i, HealthDevice healthDevice) {
        ScannerController scannerController = this.v;
        if (scannerController != null) {
            scannerController.a();
        }
        ReleaseLogUtil.e("DEVMGR_EcologyDevice_RopeFittingsClient", "ScannerController code = ", Integer.valueOf(i));
        if (i == 1 && healthDevice != null) {
            this.u = 1;
            this.n = healthDevice.getAddress();
            c();
        } else if (i == 2 || i == 3) {
            d();
        } else {
            LogUtil.c("RopeFittingsClient", "other branch");
            d();
        }
    }

    private boolean h() {
        return PermissionUtil.e(BaseApplication.wa_(), PermissionDialogHelper.d()) == PermissionUtil.PermissionResult.GRANTED;
    }

    public void e(FittingsMessageOrStateCallback fittingsMessageOrStateCallback) {
        this.o = fittingsMessageOrStateCallback;
    }

    public void c(int[] iArr) {
        LogUtil.a("RopeFittingsClient", "sendDataToFittings");
        this.x.a(iArr, "RopeControlSubPackage");
    }

    public void d(int i, int i2) {
        LogUtil.a("RopeFittingsClient", "sendBeatSoundMessage");
        this.x.b(2, 4, new int[]{i, i2}, "RopeConfigSubPackage");
    }

    public void a(int i) {
        LogUtil.a("RopeFittingsClient", "sendHeartRateMessage");
        this.x.b(5, 0, new int[]{i}, "RopeControlSingle");
    }

    private void RW_(BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr) {
        LogUtil.a("RopeFittingsClient", "writeCharacteristic:", dis.d(bArr, ""));
        if (bArr == null || this.i == null || bluetoothGattCharacteristic == null) {
            Object[] objArr = new Object[4];
            objArr[0] = "writeCharacteristic: characteristic is null?";
            objArr[1] = Boolean.valueOf(bluetoothGattCharacteristic == null);
            objArr[2] = "mBluetoothGatt is null?";
            objArr[3] = Boolean.valueOf(this.i == null);
            LogUtil.a("RopeFittingsClient", objArr);
            return;
        }
        try {
            bluetoothGattCharacteristic.setValue(bArr);
            LogUtil.a("RopeFittingsClient", "writeCharacteristic isSuccess? ", Boolean.valueOf(this.i.writeCharacteristic(bluetoothGattCharacteristic)));
        } catch (SecurityException e2) {
            LogUtil.b("RopeFittingsClient", "handlewriteCharacteristic SecurityException:", ExceptionUtils.d(e2));
        }
    }

    @Override // com.huawei.health.ecologydevice.fitness.dataparser.secondropecommand.Receiver.CommandReceiveCallback
    public void onReceiveSuccess(CommandArrayList commandArrayList) {
        BaseRopeData b2 = this.r.b(commandArrayList);
        if (b2 == null) {
            LogUtil.a("RopeFittingsClient", "baseRopeData is null");
            return;
        }
        LogUtil.a("RopeFittingsClient", "onReceiveSuccess, fitting FitnessDataType = ", Integer.valueOf(b2.getFitnessDataType()));
        if (this.o != null) {
            Bundle bundle = new Bundle();
            bundle.putSerializable("FITTINGS_MESSAGE_KEY", b2.getFitnessHashMap());
            this.o.onNewMessage(this.n, 10000, bundle);
        }
    }

    class b implements Handler.Callback {
        private b() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            int i = message.what;
            if (i == 1) {
                LogUtil.a("RopeFittingsClient", "MSG_CONNECT_GATT_TIMEOUT");
                if (RopeFittingsClient.this.i != null) {
                    try {
                        RopeFittingsClient.this.i.disconnect();
                        RopeFittingsClient.this.i.close();
                        RopeFittingsClient.this.i = null;
                        RopeFittingsClient.this.n();
                    } catch (SecurityException e) {
                        LogUtil.b("RopeFittingsClient", "FittingsMassageHandler handleMessage SecurityException:", ExceptionUtils.d(e));
                    }
                }
                RopeFittingsClient.this.l.removeTasksAndMessages();
                return true;
            }
            if (i == 2) {
                RopeFittingsClient.this.a();
                return true;
            }
            if (i == 3) {
                RopeFittingsClient ropeFittingsClient = RopeFittingsClient.this;
                ropeFittingsClient.b(ropeFittingsClient.n);
                return true;
            }
            if (i != 4) {
                return false;
            }
            RopeFittingsClient.this.e("STATUS_DISCOVERY_SERVICE_FAIL");
            return true;
        }
    }
}
