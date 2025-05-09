package defpackage;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.os.Message;
import android.text.TextUtils;
import com.huawei.devicesdk.connect.physical.ConsumerHandler;
import com.huawei.devicesdk.connect.physical.PhysicalLayerBase;
import com.huawei.devicesdk.connect.retry.ExecuteActionInterface;
import com.huawei.devicesdk.connect.retry.RetryCallbackInterface;
import com.huawei.devicesdk.entity.DeviceInfo;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.profile.profile.ProfileExtendConstants;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/* loaded from: classes3.dex */
public class bhq extends PhysicalLayerBase {

    /* renamed from: a, reason: collision with root package name */
    private final Condition f375a;
    private int b;
    private final ReentrantLock c;
    private b d;
    private BluetoothGatt e;
    private ConsumerHandler<Message> f;
    private ConsumerHandler<Message> g;
    private bia h;
    private int i;
    private bia j;
    private AtomicBoolean k;
    private final Object l;
    private AtomicBoolean m;
    private BluetoothGattCharacteristic n;
    private int o;
    private final Object p;
    private int q;
    private bij r;
    private BluetoothGattCharacteristic s;
    private int t;

    /* synthetic */ void rE_(Message message) {
        int i = message.what;
        if (i == 2) {
            g();
            return;
        }
        if (i == 3) {
            e();
            return;
        }
        if (i != 4) {
            if (i == 5) {
                c(d());
                return;
            }
            if (i == 6) {
                c(b());
                return;
            } else if (i == 10) {
                a(message.arg1, message.arg2);
                return;
            } else {
                LogUtil.a("InoperableBlePhysicalService", "mConnectHandlerMessage default");
                return;
            }
        }
        if (message.arg1 == 20304) {
            if (this.q >= 1) {
                LogUtil.a("InoperableBlePhysicalService", "service times reach, report fail");
                this.q = 0;
                rw_(message);
                return;
            } else {
                LogUtil.c("InoperableBlePhysicalService", "service time out, again.");
                this.q++;
                sendTimeoutMessageForService(this.h);
                this.h.d(2, 1000L);
                return;
            }
        }
        if (!bky.i() && message.arg1 == 10304) {
            LogUtil.c("InoperableBlePhysicalService", "gatt timeout retry start.");
            if (this.i >= 1) {
                LogUtil.a("InoperableBlePhysicalService", "gatt retries exceeded.");
                this.i = 0;
                rw_(message);
                return;
            } else {
                LogUtil.c("InoperableBlePhysicalService", "gatt first timeout, result reported.");
                bmw.e(100110, this.mDeviceInfo.getDeviceName(), "", "");
                this.i++;
                c(b());
                return;
            }
        }
        rw_(message);
    }

    /* synthetic */ void rF_(Message message) {
        if (message.what == 9) {
            int i = message.arg1;
            Object obj = message.obj;
            if (obj instanceof biu) {
                b((biu) obj, i);
                return;
            }
            return;
        }
        LogUtil.a("InoperableBlePhysicalService", "mDataHandlerMessage default");
    }

    public bhq() {
        ReentrantLock reentrantLock = new ReentrantLock();
        this.c = reentrantLock;
        this.f375a = reentrantLock.newCondition();
        this.l = new Object();
        this.p = new Object();
        this.o = 0;
        this.d = new b();
        this.k = new AtomicBoolean(false);
        this.m = new AtomicBoolean(false);
        this.t = 0;
        this.q = 0;
        this.i = 0;
        this.g = new ConsumerHandler() { // from class: bhx
            @Override // com.huawei.devicesdk.connect.physical.ConsumerHandler
            public final void accept(Object obj) {
                bhq.this.rE_((Message) obj);
            }
        };
        this.f = new ConsumerHandler() { // from class: bhy
            @Override // com.huawei.devicesdk.connect.physical.ConsumerHandler
            public final void accept(Object obj) {
                bhq.this.rF_((Message) obj);
            }
        };
        this.h = new bia(this.g);
        this.j = new bia(this.f);
        this.r = new bij(3);
    }

    private void rw_(Message message) {
        reportMonitorResult(message, this.mDeviceInfo, 100038);
        setTriggerType(3, this.mDeviceInfo);
        this.mStatusChangeCallback.onConnectStatusChanged(this.mDeviceInfo, 3, message.arg1);
        e();
    }

    private void c(boolean z) {
        if (z) {
            return;
        }
        b(3, bln.e(1, 301));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i, int i2) {
        bia biaVar = this.h;
        if (biaVar == null || this.j == null) {
            LogUtil.a("InoperableBlePhysicalService", "updateDeviceConnectState Handler is null");
            return;
        }
        if (i == 2 || i == 3) {
            biaVar.c((Object) null);
            this.j.c((Object) null);
        }
        Message rP_ = this.h.rP_(10);
        rP_.arg1 = i;
        rP_.arg2 = i2;
        this.h.rQ_(rP_);
    }

    private void a(int i, int i2) {
        ReleaseLogUtil.b("DEVMGR_InoperableBlePhysicalService", "notifyDeviceStatusToApp status ", Integer.valueOf(i), blt.b(this.mDeviceInfo));
        synchronized (this.p) {
            if (i == this.b) {
                LogUtil.c("InoperableBlePhysicalService", "connectState no change");
                return;
            }
            this.b = i;
            setTriggerType(i, this.mDeviceInfo);
            if (this.mStatusChangeCallback != null && this.mDeviceInfo != null) {
                ReleaseLogUtil.b("DEVMGR_InoperableBlePhysicalService", "report connect state: ", Integer.valueOf(i));
                this.mStatusChangeCallback.onConnectStatusChanged(this.mDeviceInfo, i, i2);
            } else {
                LogUtil.a("InoperableBlePhysicalService", "device status call back is null ", blt.a(this.mDeviceInfo));
            }
        }
    }

    private void b(biu biuVar, int i) {
        if (this.mMessageReceiveCallback != null) {
            this.mMessageReceiveCallback.onDataReceived(this.mDeviceInfo, biuVar, i);
        } else {
            LogUtil.a("InoperableBlePhysicalService", "device message call back is null", blt.a(this.mDeviceInfo));
        }
    }

    private boolean g() {
        try {
            this.h.c(2);
            BluetoothGatt bluetoothGatt = this.e;
            if (bluetoothGatt != null && this.mDeviceInfo != null) {
                ReleaseLogUtil.b("DEVMGR_InoperableBlePhysicalService", "start service discovery ", blt.b(this.mDeviceInfo));
                bmw.e(100040, bmh.b(this.mDeviceInfo.getDeviceName()), "", "");
                return bluetoothGatt.discoverServices();
            }
            ReleaseLogUtil.a("DEVMGR_InoperableBlePhysicalService", "startServiceDiscovery error. mBluetoothGatt or mDeviceInfo is null ", blt.a(this.mDeviceInfo));
            return false;
        } catch (SecurityException e) {
            LogUtil.e("InoperableBlePhysicalService", "startServiceDiscovery SecurityException:", ExceptionUtils.d(e));
            return false;
        }
    }

    private void e() {
        this.q = 0;
        this.h.c(4);
        ReleaseLogUtil.b("DEVMGR_InoperableBlePhysicalService", "release gatt start. current bluetooth state: ", Integer.valueOf(this.b), blt.a(this.mDeviceInfo));
        synchronized (this.l) {
            BluetoothGatt bluetoothGatt = this.e;
            if (bluetoothGatt != null) {
                ReleaseLogUtil.b("DEVMGR_InoperableBlePhysicalService", "start close gatt.", blt.b(this.mDeviceInfo));
                try {
                    bluetoothGatt.disconnect();
                    bluetoothGatt.close();
                } catch (Exception unused) {
                    ReleaseLogUtil.c("DEVMGR_InoperableBlePhysicalService", "bluetoothGatt SecurityException");
                }
                this.e = null;
            }
        }
        this.h.c((Object) null);
        this.j.c((Object) null);
        bij bijVar = this.r;
        if (bijVar != null) {
            bijVar.d();
        }
    }

    private boolean d() {
        ReleaseLogUtil.b("DEVMGR_InoperableBlePhysicalService", "initGattService start", blt.b(this.mDeviceInfo));
        if (!bky.i()) {
            LogUtil.c("InoperableBlePhysicalService", "not release version, set the beta gatt timeout threshold.");
            sendTimeoutMessage(this.h, 1, PreConnectManager.CONNECT_INTERNAL);
        } else {
            sendTimeoutMessage(this.h, 1);
        }
        if (this.mDeviceInfo == null) {
            LogUtil.a("InoperableBlePhysicalService", "initGattService mDeviceInfo is null");
            return false;
        }
        try {
            this.mBluetoothDevice = BluetoothAdapter.getDefaultAdapter().getRemoteDevice(this.mDeviceInfo.getDeviceMac());
        } catch (Exception unused) {
            LogUtil.e("InoperableBlePhysicalService", "initGattService occur exception");
        }
        if (this.mBluetoothDevice == null) {
            LogUtil.a("InoperableBlePhysicalService", "mBluetoothDevice is null");
            return false;
        }
        String name = this.mBluetoothDevice.getName();
        ReleaseLogUtil.b("DEVMGR_InoperableBlePhysicalService", "BluetoothDevice deviceName: ", name);
        if (!TextUtils.isEmpty(name)) {
            this.mDeviceInfo.setDeviceName(name);
        }
        bmw.e(100036, bmh.b(this.mDeviceInfo.getDeviceName()), "", "");
        this.e = this.mBluetoothDevice.connectGatt(BaseApplication.e(), false, this.d, 2);
        synchronized (this.p) {
            this.b = 1;
        }
        return this.e != null;
    }

    private boolean b() {
        LogUtil.c("InoperableBlePhysicalService", "reInitGatt start.", blt.a(this.mDeviceInfo));
        try {
            BluetoothGatt bluetoothGatt = this.e;
            if (bluetoothGatt == null) {
                LogUtil.a("InoperableBlePhysicalService", "reInitGatt error. mBluetoothGatt is null.", blt.a(this.mDeviceInfo));
                return false;
            }
            bluetoothGatt.close();
            this.h.d(5, 1000L);
            return true;
        } catch (SecurityException e) {
            LogUtil.e("InoperableBlePhysicalService", "reInitGattService SecurityException:", ExceptionUtils.d(e));
            return false;
        }
    }

    @Override // com.huawei.devicesdk.connect.physical.PhysicalLayerBase
    public void connectDevice(DeviceInfo deviceInfo) {
        try {
            if (this.mDeviceInfo != null && deviceInfo != null && !TextUtils.isEmpty(deviceInfo.getDeviceMac())) {
                b(1, 100000);
                ReleaseLogUtil.b("DEVMGR_InoperableBlePhysicalService", "connectDevice start. ", blt.b(this.mDeviceInfo));
                if (this.b == 2) {
                    ReleaseLogUtil.b("DEVMGR_InoperableBlePhysicalService", "device has connected. ", blt.b(deviceInfo));
                    b(2, 100000);
                    return;
                }
                BluetoothGatt bluetoothGatt = this.e;
                if (bluetoothGatt != null) {
                    ReleaseLogUtil.b("DEVMGR_InoperableBlePhysicalService", "mBluetooth exit, start release gatt.", blt.b(this.mDeviceInfo));
                    bluetoothGatt.disconnect();
                    this.h.d(6, 5000L);
                    return;
                }
                this.h.b(5);
                return;
            }
            LogUtil.a("InoperableBlePhysicalService", "connect device failed. device is invalid.");
            b(3, bln.e(7, 303));
        } catch (SecurityException e) {
            LogUtil.e("InoperableBlePhysicalService", "cancelBRDiscoveryForConnect SecurityException:", ExceptionUtils.d(e));
        }
    }

    private boolean ry_(final BluetoothGattCharacteristic bluetoothGattCharacteristic, bil bilVar, final String str) {
        final byte[] d = bilVar.d();
        final int a2 = bilVar.a();
        final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        new bij(2).d(new ExecuteActionInterface() { // from class: bhq.1
            @Override // com.huawei.devicesdk.connect.retry.ExecuteActionInterface
            public boolean execute() {
                return bhq.this.rD_(bluetoothGattCharacteristic, d);
            }

            @Override // com.huawei.devicesdk.connect.retry.ExecuteActionInterface
            public String getActionName() {
                return "WriteCharacteristic";
            }
        }, new RetryCallbackInterface() { // from class: bhq.3
            @Override // com.huawei.devicesdk.connect.retry.RetryCallbackInterface
            public void doRetryAction(int i) {
                LogUtil.c("InoperableBlePhysicalService", "doRetryAction enter: ", Integer.valueOf(i));
                if (i == 0) {
                    bhq.this.b(100L);
                } else if (i == 1) {
                    bhq.this.b(300L);
                } else {
                    LogUtil.c("InoperableBlePhysicalService", "currentRetry else");
                }
            }

            @Override // com.huawei.devicesdk.connect.retry.RetryCallbackInterface
            public void doSuccessAction() {
                atomicBoolean.set(true);
                int i = a2;
                if (i >= 0) {
                    bhq.this.b(i);
                } else {
                    LogUtil.c("InoperableBlePhysicalService", "doSuccessAction sendInterval: ", Integer.valueOf(i));
                }
            }

            @Override // com.huawei.devicesdk.connect.retry.RetryCallbackInterface
            public void doFailureAction() {
                LogUtil.c("InoperableBlePhysicalService", "doFailureAction() enter");
                bgm.d(str);
            }
        }, false);
        return atomicBoolean.get();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public boolean rD_(BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr) {
        SecurityException e;
        boolean z;
        if (bluetoothGattCharacteristic == null) {
            LogUtil.a("InoperableBlePhysicalService", "characteristic is invalid.");
            return false;
        }
        this.c.lock();
        try {
            try {
                bluetoothGattCharacteristic.setValue(bArr);
                BluetoothGatt bluetoothGatt = this.e;
                try {
                    if (bluetoothGatt != null) {
                        boolean writeCharacteristic = bluetoothGatt.writeCharacteristic(bluetoothGattCharacteristic);
                        if (writeCharacteristic != 0) {
                            try {
                                this.k.set(true);
                                LogUtil.d("InoperableBlePhysicalService", "isAwaitSuccess ", Boolean.valueOf(this.f375a.await(ProfileExtendConstants.TIME_OUT, TimeUnit.MILLISECONDS)));
                                bluetoothGattCharacteristic = writeCharacteristic;
                            } catch (InterruptedException unused) {
                                LogUtil.e("InoperableBlePhysicalService", "wait for characteristic error.");
                                bluetoothGattCharacteristic = writeCharacteristic;
                            }
                        } else {
                            blt.b("InoperableBlePhysicalService", bArr, blt.a(this.mDeviceInfo), " SDK-->Device[retryTime]: ");
                            bluetoothGattCharacteristic = writeCharacteristic;
                        }
                    } else {
                        bluetoothGattCharacteristic = null;
                    }
                    this.k.set(false);
                    z = bluetoothGattCharacteristic;
                } catch (SecurityException e2) {
                    e = e2;
                    LogUtil.e("InoperableBlePhysicalService", "writeCharacteristicValue SecurityException:", ExceptionUtils.d(e));
                    z = bluetoothGattCharacteristic;
                    return z;
                }
            } finally {
                this.c.unlock();
            }
        } catch (SecurityException e3) {
            e = e3;
            bluetoothGattCharacteristic = null;
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(long j) {
        try {
            Thread.sleep(j);
        } catch (InterruptedException unused) {
            LogUtil.e("InoperableBlePhysicalService", "sleep delay interrupted");
        }
    }

    @Override // com.huawei.devicesdk.connect.physical.PhysicalLayerBase
    public void disconnectDevice() {
        ReleaseLogUtil.b("DEVMGR_InoperableBlePhysicalService", "disconnectDevice start");
        try {
            synchronized (this.l) {
                BluetoothGatt bluetoothGatt = this.e;
                if (bluetoothGatt != null) {
                    bluetoothGatt.disconnect();
                    ReleaseLogUtil.b("DEVMGR_InoperableBlePhysicalService", "disconnectGatt success ", blt.b(this.mDeviceInfo));
                    b(0, bln.b(6));
                } else {
                    LogUtil.a("InoperableBlePhysicalService", "disconnectGatt fail because mBluetoothGatt is invalid.", blt.b(this.mDeviceInfo));
                    b(0, bln.e(6, 303));
                }
            }
            this.h.d(3, 200L);
        } catch (SecurityException e) {
            LogUtil.e("InoperableBlePhysicalService", "disconnectDevice SecurityException:", ExceptionUtils.d(e));
        }
    }

    @Override // com.huawei.devicesdk.connect.physical.PhysicalLayerBase
    public boolean sendData(bim bimVar, String str) {
        if (bimVar == null) {
            LogUtil.a("InoperableBlePhysicalService", "sendData error. bluetooth frame data is null");
            return false;
        }
        LogUtil.c("InoperableBlePhysicalService", "sendData start.", blt.a(this.mDeviceInfo));
        if (this.s == null) {
            d(new byte[0], 1);
        }
        List<bil> e = bimVar.e();
        if (bkz.e(e)) {
            LogUtil.a("InoperableBlePhysicalService", "the packages getted is empty ", blt.a(this.mDeviceInfo));
            return true;
        }
        for (bil bilVar : e) {
            this.m.set(false);
            blt.b("InoperableBlePhysicalService", bilVar.d(), blt.a(this.mDeviceInfo), " SDK-->Device: ");
            boolean ry_ = ry_(this.s, bilVar, str);
            if (!ry_ || !this.m.get()) {
                LogUtil.a("InoperableBlePhysicalService", "sendData isExecuteSuccess: ", Boolean.valueOf(ry_), ",mIsCharacteristicRespSuccess.get: ", Boolean.valueOf(this.m.get()), ",", blt.a(this.mDeviceInfo));
                d(new byte[0], 1);
                break;
            }
        }
        LogUtil.c("InoperableBlePhysicalService", "sendData finish.", blt.a(this.mDeviceInfo));
        return true;
    }

    @Override // com.huawei.devicesdk.connect.physical.PhysicalLayerBase
    public void destroy() {
        e();
        this.h.a();
        this.g = null;
        this.mBluetoothDevice = null;
        this.r = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        this.c.lock();
        if (this.k.get()) {
            LogUtil.c("InoperableBlePhysicalService", "release mCharacterLock.");
            this.k.set(false);
            this.f375a.signalAll();
        } else {
            LogUtil.c("InoperableBlePhysicalService", "release mCharacterLock skip. lock is false.");
        }
        this.c.unlock();
    }

    class b extends BluetoothGattCallback {
        private b() {
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
            ReleaseLogUtil.b("DEVMGR_InoperableBlePhysicalService", "onConnectionStateChange status: ", Integer.valueOf(i), ", newState: ", Integer.valueOf(i2), blt.b(bhq.this.mDeviceInfo));
            if (bhq.this.mDeviceInfo == null) {
                LogUtil.e("InoperableBlePhysicalService", "mDeviceInfo is null");
                return;
            }
            bmw.e(100039, bmh.b(bhq.this.mDeviceInfo.getDeviceName()), bmh.b(Integer.valueOf(i)), bmh.b(Integer.valueOf(i2)));
            if (bluetoothGatt != null) {
                if (bhq.this.e == null) {
                    LogUtil.a("InoperableBlePhysicalService", "onConnectionStateChange set mBluetoothGatt ", blt.a(bhq.this.mDeviceInfo));
                    bhq.this.e = bluetoothGatt;
                }
                bhq.this.rB_(i, i2, bluetoothGatt);
                return;
            }
            ReleaseLogUtil.c("DEVMGR_InoperableBlePhysicalService", "onConnectionStateChange gatt is null ", blt.b(bhq.this.mDeviceInfo));
            bhq.this.b(3, bln.e(1, i));
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
            if (bhq.this.mDeviceInfo == null) {
                LogUtil.e("InoperableBlePhysicalService", "mDeviceInfo is null");
                return;
            }
            bmw.e(100041, bmh.b(bhq.this.mDeviceInfo.getDeviceName()), bmh.b(Integer.valueOf(i)), "");
            bhq.this.h.c((Object) null);
            bhq.this.j.c((Object) null);
            if (bluetoothGatt == null) {
                LogUtil.a("InoperableBlePhysicalService", "onServicesDiscovered gatt is null ", blt.a(bhq.this.mDeviceInfo));
                bhq.this.b(3, bln.e(2, i));
            } else {
                ReleaseLogUtil.b("DEVMGR_InoperableBlePhysicalService", "onServicesDiscovered status: ", Integer.valueOf(i), blt.b(bhq.this.mDeviceInfo));
                bhq.this.q = 0;
                bhq.this.rC_(bluetoothGatt);
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            super.onCharacteristicChanged(bluetoothGatt, bluetoothGattCharacteristic);
            byte[] value = bluetoothGattCharacteristic.getValue();
            blt.d("InoperableBlePhysicalService", value, "onCharacteristicChanged: ", blt.a(bhq.this.mDeviceInfo), " Device-->SDK: ");
            bhq.this.d(value, 0);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicRead(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            if (bluetoothGattCharacteristic == null) {
                LogUtil.e("InoperableBlePhysicalService", "characteristic is null");
            } else {
                super.onCharacteristicRead(bluetoothGatt, bluetoothGattCharacteristic, i);
                blt.d("InoperableBlePhysicalService", bluetoothGattCharacteristic.getValue(), "onCharacteristicRead: ", blt.a(bhq.this.mDeviceInfo), " data: ");
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            super.onCharacteristicWrite(bluetoothGatt, bluetoothGattCharacteristic, i);
            LogUtil.c("InoperableBlePhysicalService", "onCharacteristicWrite status: ", Integer.valueOf(i), blt.a(bhq.this.mDeviceInfo));
            bhq.this.m.set(i == 0);
            bhq.this.h();
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
            super.onDescriptorWrite(bluetoothGatt, bluetoothGattDescriptor, i);
            LogUtil.c("InoperableBlePhysicalService", "onDescriptorWrite status: ", Integer.valueOf(i), blt.a(bhq.this.mDeviceInfo));
            if (i == 0) {
                bhq.this.b(2, 100000);
                bhq.this.o = 0;
                return;
            }
            bhq bhqVar = bhq.this;
            boolean rA_ = bhqVar.rA_(bhqVar.e);
            LogUtil.c("InoperableBlePhysicalService", "refreshResult: ", Boolean.valueOf(rA_));
            if (rA_) {
                bhq.this.c();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        this.h.d(2, 1000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean rA_(BluetoothGatt bluetoothGatt) {
        if (bluetoothGatt == null) {
            LogUtil.c("InoperableBlePhysicalService", "Enter isRefreshServiceDiscovery BluetoothGatt parameter is false ");
            return false;
        }
        try {
            int i = this.o;
            if (i <= 1) {
                this.o = i + 1;
                Method method = bluetoothGatt.getClass().getMethod("refresh", new Class[0]);
                LogUtil.c("InoperableBlePhysicalService", "Start to refresh service discovery.");
                Object invoke = method.invoke(bluetoothGatt, new Object[0]);
                if (invoke instanceof Boolean) {
                    boolean booleanValue = ((Boolean) invoke).booleanValue();
                    LogUtil.c("InoperableBlePhysicalService", "refresh device service discovery result :", Boolean.valueOf(booleanValue));
                    return booleanValue;
                }
            } else {
                LogUtil.e("InoperableBlePhysicalService", "call over times");
                b(3, bln.e(2, 302));
                this.h.d(3, 1000L);
            }
        } catch (IllegalAccessException unused) {
            LogUtil.e("InoperableBlePhysicalService", "An exception occur while refreshing device:IllegalAccessException");
        } catch (NoSuchMethodException unused2) {
            LogUtil.e("InoperableBlePhysicalService", "An exception occur while refreshing device:NoSuchMethodException");
        } catch (InvocationTargetException unused3) {
            LogUtil.e("InoperableBlePhysicalService", "An exception occur while refreshing device:InvocationTargetException");
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(byte[] bArr, int i) {
        biu biuVar = new biu();
        biuVar.d(bArr);
        if (this.j.d()) {
            Message rP_ = this.j.rP_(9);
            rP_.arg1 = i;
            rP_.obj = biuVar;
            this.j.rQ_(rP_);
            return;
        }
        blt.d("InoperableBlePhysicalService", bArr, "mDataHandler is null. response data:");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void rB_(int i, int i2, BluetoothGatt bluetoothGatt) {
        if (i == 133) {
            LogUtil.e("InoperableBlePhysicalService", "onConnectionStateChange bluetooth 133 error", blt.a(this.mDeviceInfo), " current retry times: ", Integer.valueOf(this.t));
            if (this.t < 3) {
                rx_(bluetoothGatt);
                this.t++;
                return;
            }
        }
        this.t = 0;
        if (i == 133) {
            LogUtil.e("InoperableBlePhysicalService", "onConnectionStateChange bluetooth 133 error", blt.a(this.mDeviceInfo));
            b(3, bln.e(6, i));
        }
        if (i2 == 2) {
            LogUtil.c("InoperableBlePhysicalService", "device connect success. ", blt.a(this.mDeviceInfo));
            this.h.c(4);
            sendTimeoutMessageForService(this.h);
            this.h.d(2, 1000L);
            return;
        }
        if (i2 == 0) {
            LogUtil.c("InoperableBlePhysicalService", "device disconnect success.", blt.a(this.mDeviceInfo));
            b(0, bln.e(6, i));
            disconnectDevice();
            return;
        }
        LogUtil.a("InoperableBlePhysicalService", "onStateChange unknown", blt.a(this.mDeviceInfo));
    }

    private void rx_(BluetoothGatt bluetoothGatt) {
        this.h.c(4);
        if (bluetoothGatt != null) {
            try {
                LogUtil.c("InoperableBlePhysicalService", "mBluetooth exit, start release gatt.", blt.a(this.mDeviceInfo));
                bluetoothGatt.disconnect();
                this.h.d(6, 5000L);
            } catch (SecurityException e) {
                LogUtil.e("InoperableBlePhysicalService", "dealWithGatt133 SecurityException:", ExceptionUtils.d(e));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void rC_(final BluetoothGatt bluetoothGatt) {
        this.r.d(new ExecuteActionInterface() { // from class: bhq.5
            @Override // com.huawei.devicesdk.connect.retry.ExecuteActionInterface
            public boolean execute() {
                return bhq.this.rz_(bluetoothGatt) || !bhq.this.rA_(bluetoothGatt);
            }

            @Override // com.huawei.devicesdk.connect.retry.ExecuteActionInterface
            public String getActionName() {
                return "ServiceDiscovery";
            }
        }, new RetryCallbackInterface() { // from class: bhq.4
            @Override // com.huawei.devicesdk.connect.retry.RetryCallbackInterface
            public void doRetryAction(int i) {
                BluetoothGatt bluetoothGatt2 = bluetoothGatt;
                if (bluetoothGatt2 == null) {
                    LogUtil.e("InoperableBlePhysicalService", "gatt is null");
                    return;
                }
                LogUtil.a("InoperableBlePhysicalService", "not found user data service find service num=", Integer.valueOf(bluetoothGatt2.getServices().size()), blt.a(bhq.this.mDeviceInfo));
                if (bhq.this.h.d()) {
                    bhq.this.h.c(2);
                    bhq.this.h.d(2, 1000L);
                    LogUtil.c("InoperableBlePhysicalService", "send discovery message", blt.a(bhq.this.mDeviceInfo));
                } else {
                    LogUtil.a("InoperableBlePhysicalService", "mConnectHandler is null", blt.a(bhq.this.mDeviceInfo));
                    bhq.this.b(3, bln.e(2, 303));
                }
            }

            @Override // com.huawei.devicesdk.connect.retry.RetryCallbackInterface
            public void doSuccessAction() {
                bhq.this.e = bluetoothGatt;
                LogUtil.c("InoperableBlePhysicalService", "service discover and notification set success");
            }

            @Override // com.huawei.devicesdk.connect.retry.RetryCallbackInterface
            public void doFailureAction() {
                bhq.this.b(3, bln.e(2, 301));
            }
        }, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean rz_(BluetoothGatt bluetoothGatt) {
        BluetoothGattService bluetoothGattService;
        try {
            bluetoothGattService = bluetoothGatt.getService(bip.f389a);
        } catch (SecurityException e) {
            LogUtil.e("InoperableBlePhysicalService", "isObtainServiceSuccess SecurityException : ", bll.a(e));
            bluetoothGattService = null;
        }
        if (bluetoothGattService == null) {
            return false;
        }
        ReleaseLogUtil.b("DEVMGR_InoperableBlePhysicalService", "BLE GATT Service UUID find success.");
        this.s = bluetoothGattService.getCharacteristic(bip.i);
        this.n = bluetoothGattService.getCharacteristic(bip.g);
        return a();
    }

    private boolean a() {
        LogUtil.c("InoperableBlePhysicalService", "Enter setCharacteristicMessage().");
        try {
            BluetoothGattCharacteristic bluetoothGattCharacteristic = this.n;
            if (bluetoothGattCharacteristic != null && this.e != null && (bluetoothGattCharacteristic.getProperties() | 16) > 0) {
                this.e.setCharacteristicNotification(this.n, true);
                BluetoothGattDescriptor descriptor = this.n.getDescriptor(bip.e);
                if (descriptor == null) {
                    try {
                        Thread.sleep(30L);
                        descriptor = this.n.getDescriptor(bip.e);
                    } catch (InterruptedException unused) {
                        LogUtil.e("InoperableBlePhysicalService", "setCharacteristicMessage InterruptedException");
                    }
                }
                if (descriptor != null) {
                    descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
                    LogUtil.c("InoperableBlePhysicalService", "writeDescriptorResult: ", Boolean.valueOf(this.e.writeDescriptor(descriptor)));
                    return true;
                }
                bmw.e(100095, bmh.b(this.mDeviceInfo.getDeviceName()), "", "");
            }
        } catch (SecurityException e) {
            LogUtil.e("InoperableBlePhysicalService", "setCharacteristicMessage SecurityException:", ExceptionUtils.d(e));
        }
        return false;
    }
}
