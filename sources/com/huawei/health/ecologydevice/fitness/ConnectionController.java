package com.huawei.health.ecologydevice.fitness;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.handler.ExtendHandler;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.health.R;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OpAnalyticsConstants;
import defpackage.cpp;
import defpackage.cxw;
import defpackage.dis;
import defpackage.nrh;
import health.compact.a.HuaweiHealth;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.UUID;

/* loaded from: classes3.dex */
public class ConnectionController {
    private BluetoothAdapter b;
    private BluetoothGatt c;
    private BluetoothManager d;
    private BluetoothDevice e;
    private ConnectResultCallback h;
    private int i;
    private String j;
    private cxw k;
    private final Object o;
    private long g = 0;
    private int n = 0;
    private ExtendHandler f = null;

    /* renamed from: a, reason: collision with root package name */
    private boolean f2282a = false;
    private int m = 0;
    private final BluetoothGattCallback l = new BluetoothGattCallback() { // from class: com.huawei.health.ecologydevice.fitness.ConnectionController.5
        @Override // android.bluetooth.BluetoothGattCallback
        public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
            ConnectionController.this.Rs_(bluetoothGatt, i, i2);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
            super.onServicesDiscovered(bluetoothGatt, i);
            ConnectionController.this.Rq_(bluetoothGatt, i);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicRead(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            LogUtil.a("PDROPE_ConnectionController", "onCharacteristicRead, status：", Integer.valueOf(i));
            ConnectionController.this.k.RE_(bluetoothGattCharacteristic, i);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            if (bluetoothGattCharacteristic == null) {
                LogUtil.b("PDROPE_ConnectionController", "characteristic == null");
                return;
            }
            UUID uuid = bluetoothGattCharacteristic.getUuid();
            if (uuid == null) {
                LogUtil.a("PDROPE_ConnectionController", "onCharacteristicChanged, charUUID is null");
                return;
            }
            byte[] value = bluetoothGattCharacteristic.getValue();
            if (value == null || value.length <= 0) {
                LogUtil.a("PDROPE_ConnectionController", "onCharacteristicChanged, value is invalid");
                return;
            }
            LogUtil.a("PDROPE_ConnectionController", "onFitnessCharacteristicChanged, original data:", dis.d(value, ""));
            Message obtain = Message.obtain();
            obtain.what = 30;
            Bundle bundle = new Bundle();
            bundle.putString("CHARACTERISTIC_UUID", uuid.toString());
            bundle.putByteArray("CHARACTERISTIC_VALUE", value);
            obtain.setData(bundle);
            ConnectionController.this.f.sendMessage(obtain);
        }

        private void Ru_(BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr) {
            UUID uuid = bluetoothGattCharacteristic.getUuid();
            if (uuid == null) {
                LogUtil.a("PDROPE_ConnectionController", "onCharacteristicChanged, charUUID is null");
                return;
            }
            LogUtil.a("PDROPE_ConnectionController", "onFitnessCharacteristicChanged, original data:", dis.d(bArr, ""));
            Message obtain = Message.obtain();
            obtain.what = 30;
            Bundle bundle = new Bundle();
            bundle.putString("CHARACTERISTIC_UUID", uuid.toString());
            bundle.putByteArray("CHARACTERISTIC_VALUE", bArr);
            obtain.setData(bundle);
            ConnectionController.this.f.sendMessage(obtain);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr) {
            if (bArr.length == 0) {
                LogUtil.a("PDROPE_ConnectionController", "onCharacteristicChanged, value is invalid");
            } else {
                Ru_(bluetoothGattCharacteristic, bArr);
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            LogUtil.a("PDROPE_ConnectionController", "onCharacteristicWrite, status：", Integer.valueOf(i));
            ConnectionController.this.k.e();
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
            LogUtil.a("PDROPE_ConnectionController", "onDescriptorWrite, status：", Integer.valueOf(i));
            ConnectionController.this.k.a();
            super.onDescriptorWrite(bluetoothGatt, bluetoothGattDescriptor, i);
        }
    };

    public interface ConnectResultCallback {
        void onResult(int i);
    }

    public ConnectionController(cxw cxwVar, Object obj) {
        this.k = cxwVar;
        this.o = obj;
        j();
        g();
    }

    public void e(int i) {
        this.m = i;
    }

    public void e(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("PDROPE_ConnectionController", "mac is error");
            return;
        }
        LogUtil.a("PDROPE_ConnectionController", "reConnectDevice!");
        try {
            BluetoothAdapter bluetoothAdapter = this.b;
            if (bluetoothAdapter != null && bluetoothAdapter.isEnabled() && !TextUtils.isEmpty(str)) {
                this.g = SystemClock.elapsedRealtime();
                BluetoothDevice remoteDevice = this.b.getRemoteDevice(str);
                this.e = remoteDevice;
                if (remoteDevice != null) {
                    ReleaseLogUtil.e("DEVMGR_EcologyDevice_PDROPE_ConnectionController", "Record the number of reConnections : ", Integer.valueOf(this.n));
                    this.c = this.e.connectGatt(cpp.a(), false, this.l);
                    if (this.f != null && this.n == 0) {
                        ReleaseLogUtil.e("DEVMGR_EcologyDevice_PDROPE_ConnectionController", "Start counting down 30 seconds.");
                        this.f.sendEmptyMessage(21, OpAnalyticsConstants.H5_LOADING_DELAY);
                    }
                    this.n++;
                    return;
                }
                c(4);
                return;
            }
            LogUtil.h("PDROPE_ConnectionController", "BluetoothAdapter not initialized or unspecified address.");
            c(4);
        } catch (SecurityException e) {
            LogUtil.b("PDROPE_ConnectionController", "reConnectDevice SecurityException:", ExceptionUtils.d(e));
        }
    }

    private void g() {
        if (this.f == null) {
            this.f = HandlerCenter.yt_(new a(), "PDROPE_ConnectionController");
        }
    }

    private void j() {
        if (this.b != null) {
            LogUtil.a("PDROPE_ConnectionController", "Init already.");
            return;
        }
        if (this.d == null && (BaseApplication.getContext().getSystemService("bluetooth") instanceof BluetoothManager)) {
            this.d = (BluetoothManager) BaseApplication.getContext().getSystemService("bluetooth");
        }
        BluetoothManager bluetoothManager = this.d;
        if (bluetoothManager == null) {
            LogUtil.b("PDROPE_ConnectionController", "Unable to initAdapter BluetoothManager.");
            return;
        }
        BluetoothAdapter adapter = bluetoothManager.getAdapter();
        this.b = adapter;
        if (adapter == null) {
            LogUtil.b("PDROPE_ConnectionController", "Unable to obtain BluetoothAdapter.");
        }
    }

    public BluetoothGatt Rt_() {
        return this.c;
    }

    public void c() {
        boolean z;
        synchronized (this.o) {
            BluetoothGatt bluetoothGatt = this.c;
            if (bluetoothGatt == null) {
                return;
            }
            try {
                z = bluetoothGatt.discoverServices();
            } catch (SecurityException e) {
                ReleaseLogUtil.c("DEVMGR_EcologyDevice_PDROPE_ConnectionController", "discoverServices, ", ExceptionUtils.d(e));
                z = false;
            }
            LogUtil.a("PDROPE_ConnectionController", "Attempting to start service discovery:", Boolean.valueOf(z));
            this.f.sendEmptyMessage(23, PreConnectManager.CONNECT_INTERNAL);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Rs_(BluetoothGatt bluetoothGatt, int i, int i2) {
        LogUtil.a("PDROPE_ConnectionController", "oldStatus= ", Integer.valueOf(i), " NewStates= ", Integer.valueOf(i2), " mCurrentDeviceStatus = ", Integer.valueOf(this.i));
        ExtendHandler extendHandler = this.f;
        if (extendHandler == null) {
            c(1);
            return;
        }
        if (i2 == 2) {
            this.i = 6;
            extendHandler.removeTasksAndMessages();
            Rr_(bluetoothGatt);
        } else if (i2 == 0) {
            this.i = 8;
            i();
        } else if (i2 == 1) {
            this.i = 5;
            c(8);
        } else if (i2 == 3) {
            this.i = 7;
            c(9);
        } else {
            LogUtil.c("PDROPE_ConnectionController", "other gatt error code");
            i();
        }
    }

    private void i() {
        BluetoothAdapter bluetoothAdapter;
        ReleaseLogUtil.e("DEVMGR_EcologyDevice_PDROPE_ConnectionController", "stateIsDisconnect isNewConnect = ", Boolean.valueOf(this.f2282a), " ,scanTimes = ", Integer.valueOf(this.m));
        if (this.f2282a) {
            if (this.m == 1) {
                f();
                return;
            } else {
                LogUtil.a("PDROPE_ConnectionController", "The reconnection mechanism is not triggered for new connection.");
                c(12);
                return;
            }
        }
        ReleaseLogUtil.e("DEVMGR_EcologyDevice_PDROPE_ConnectionController", "Disconnected from GATT server. mReconnectTimes = ", Integer.valueOf(this.n));
        if (this.n <= 2) {
            try {
                bluetoothAdapter = this.b;
            } catch (SecurityException e) {
                LogUtil.b("PDROPE_ConnectionController", "handleDisConnectStatus SecurityException:", ExceptionUtils.d(e));
            }
            if (bluetoothAdapter != null && bluetoothAdapter.isEnabled() && !TextUtils.isEmpty(this.j)) {
                c(12);
                synchronized (this.o) {
                    BluetoothGatt bluetoothGatt = this.c;
                    if (bluetoothGatt != null) {
                        bluetoothGatt.close();
                        this.c = null;
                    }
                }
                this.f.sendEmptyMessage(12, 1000L);
                return;
            }
            this.f.removeTasksAndMessages();
            c(1);
            return;
        }
        f();
    }

    private void f() {
        nrh.d(HuaweiHealth.a(), HuaweiHealth.a().getResources().getString(R.string._2130845060_res_0x7f021d84));
        c(1);
        this.f.removeTasksAndMessages();
    }

    private void Rr_(BluetoothGatt bluetoothGatt) {
        cxw cxwVar = this.k;
        if (cxwVar != null) {
            cxwVar.Re_(bluetoothGatt);
        }
        this.c = bluetoothGatt;
        this.n = 0;
        this.f2282a = false;
        this.m = 0;
        LogUtil.a("PDROPE_ConnectionController", "Connected to GATT server. Time-cosuming: " + (SystemClock.elapsedRealtime() - this.g));
        c(3);
        this.f.sendEmptyMessage(22, 1000L);
    }

    public void a(String str) {
        LogUtil.a("PDROPE_ConnectionController", "in connectByMac");
        if (this.f == null) {
            LogUtil.b("PDROPE_ConnectionController", "connectByMac,mExtendHandler is null,create");
            this.f = HandlerCenter.yt_(new a(), "PDROPE_ConnectionController");
        }
        try {
            BluetoothAdapter bluetoothAdapter = this.b;
            if (bluetoothAdapter != null && bluetoothAdapter.isEnabled() && !TextUtils.isEmpty(str)) {
                if (str.equals(this.j) && this.c != null) {
                    c(3);
                    LogUtil.c("PDROPE_ConnectionController", "Trying to use an existing mBluetoothGatt for connection.");
                    return;
                }
                this.g = SystemClock.elapsedRealtime();
                LogUtil.c("PDROPE_ConnectionController", "in connectByMac,mac:", str);
                try {
                    this.e = this.b.getRemoteDevice(str);
                    synchronized (this.o) {
                        BluetoothGatt bluetoothGatt = this.c;
                        if (bluetoothGatt != null) {
                            bluetoothGatt.close();
                            this.c = null;
                        }
                        this.c = this.e.connectGatt(cpp.a(), false, this.l);
                        this.f.sendEmptyMessage(20, PreConnectManager.CONNECT_INTERNAL);
                        this.j = str;
                        this.n = 0;
                        this.f2282a = true;
                        LogUtil.c("PDROPE_ConnectionController", "Trying to create new connection (in connectDevice)");
                    }
                    return;
                } catch (IllegalArgumentException unused) {
                    LogUtil.b("PDROPE_ConnectionController", "mac IllegalArgumentException");
                    c(1);
                    return;
                }
            }
            LogUtil.h("PDROPE_ConnectionController", "BluetoothAdapter not initialized or unspecified address.");
            c(1);
        } catch (SecurityException e) {
            LogUtil.b("PDROPE_ConnectionController", "connectDevice SecurityException:", ExceptionUtils.d(e));
            c(1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Rq_(BluetoothGatt bluetoothGatt, int i) {
        ExtendHandler extendHandler = this.f;
        if (extendHandler != null) {
            extendHandler.removeTasksAndMessages();
        }
        LogUtil.a("PDROPE_ConnectionController", "onServicesDiscovered status: ", Integer.valueOf(i));
        if (i == 0) {
            c(10);
            this.k.RF_(bluetoothGatt, i);
        } else {
            LogUtil.h("PDROPE_ConnectionController", "onServicesDiscovered received: ", Integer.valueOf(i));
        }
    }

    class a implements Handler.Callback {
        private a() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            int i = message.what;
            if (i == 12) {
                ConnectionController connectionController = ConnectionController.this;
                connectionController.e(connectionController.j);
                return true;
            }
            if (i != 30) {
                switch (i) {
                    case 20:
                        LogUtil.a("PDROPE_ConnectionController", "MSG_CONNECT_GATT_TIMEOUT");
                        synchronized (ConnectionController.this.o) {
                            if (ConnectionController.this.c != null) {
                                ConnectionController.this.b();
                                try {
                                    ConnectionController.this.c.close();
                                    ConnectionController.this.c = null;
                                } catch (SecurityException e) {
                                    LogUtil.b("PDROPE_ConnectionController", "ExtendHandlerCallback handleMessage SecurityException:", ExceptionUtils.d(e));
                                }
                            }
                        }
                        ConnectionController.this.j = "";
                        ConnectionController.this.f.removeTasksAndMessages();
                        ConnectionController.this.c(2);
                        return true;
                    case 21:
                        LogUtil.a("PDROPE_ConnectionController", "MSG_RECONNECT_GATT_TIMEOUT");
                        ConnectionController.this.c(13);
                        ConnectionController.this.n = 0;
                        ConnectionController.this.f.removeTasksAndMessages();
                        return true;
                    case 22:
                        ConnectionController.this.c();
                        return true;
                    case 23:
                        ConnectionController.this.c(11);
                        return true;
                    default:
                        return false;
                }
            }
            Bundle data = message.getData();
            if (data != null) {
                ConnectionController.this.k.d(data.getString("CHARACTERISTIC_UUID"), data.getByteArray("CHARACTERISTIC_VALUE"));
                return true;
            }
            LogUtil.h("PDROPE_ConnectionController", "ConnectionController MSG_GET_DATA_SUCCESS bundle is null");
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        try {
            this.c.disconnect();
        } catch (SecurityException e) {
            ReleaseLogUtil.c("DEVMGR_EcologyDevice_PDROPE_ConnectionController", "disconnectGatt, ", ExceptionUtils.d(e));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i) {
        if (this.h != null) {
            LogUtil.c("PDROPE_ConnectionController", "code = ", Integer.valueOf(i));
            this.h.onResult(i);
        }
    }

    public void e() {
        LogUtil.a("PDROPE_ConnectionController", "releaseGattResource");
        a();
        h();
        this.n = 0;
        this.f2282a = false;
        this.m = 0;
    }

    private void a() {
        synchronized (this.o) {
            try {
                if (this.c != null) {
                    LogUtil.a("PDROPE_ConnectionController", "start to close gatt...");
                    b();
                    this.c.close();
                    this.j = null;
                    this.c = null;
                }
                this.e = null;
            } catch (SecurityException e) {
                LogUtil.b("PDROPE_ConnectionController", "closeBluetoothGatt SecurityException:", ExceptionUtils.d(e));
            }
        }
    }

    private void h() {
        if (this.f != null) {
            LogUtil.a("PDROPE_ConnectionController", "msgHandler will removeCallbacksAndMessages");
            this.f.removeTasksAndMessages();
            this.f.quit(false);
        }
    }

    public void c(ConnectResultCallback connectResultCallback) {
        this.h = connectResultCallback;
    }
}
