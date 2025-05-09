package defpackage;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Message;
import android.text.TextUtils;
import com.huawei.devicesdk.callback.BtDevicePairCallback;
import com.huawei.devicesdk.connect.physical.ConsumerHandler;
import com.huawei.devicesdk.connect.physical.PhysicalLayerBase;
import com.huawei.devicesdk.entity.DeviceInfo;
import com.huawei.devicesdk.entity.ExternalDeviceCapability;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.hms.network.embedded.y1;
import com.huawei.unitedevice.entity.UniteDevice;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.SharedPreferenceManager;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.Thread;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/* loaded from: classes3.dex */
public class bhv extends PhysicalLayerBase {
    private int d;
    private c k;
    private a l;
    private a m;
    private e n;
    private static final Object c = new Object();
    private static final Object e = new Object();

    /* renamed from: a, reason: collision with root package name */
    private static final Object f377a = new Object();
    private boolean q = false;
    private DeviceInfo f = null;
    private BluetoothDevice j = null;
    private volatile int p = 0;
    private volatile int s = 0;
    private volatile boolean o = false;
    private BtDevicePairCallback i = new BtDevicePairCallback() { // from class: bhv.4
        @Override // com.huawei.devicesdk.callback.BtDevicePairCallback
        public void onDevicePairing(BluetoothDevice bluetoothDevice) {
        }

        @Override // com.huawei.devicesdk.callback.BtDevicePairCallback
        public void onDevicePaired(BluetoothDevice bluetoothDevice) {
            ReleaseLogUtil.b("DEVMGR_InoperableBrPhysicalService", "btDevice paired.");
            try {
                if (bhv.this.rL_(bluetoothDevice)) {
                    bhv.this.b();
                    bhv.this.q = false;
                    if (!bhv.this.b.sx_(bluetoothDevice)) {
                        ReleaseLogUtil.b("DEVMGR_InoperableBrPhysicalService", "Need to connect hfp profile.");
                        bmw.e(100055, bmh.b(bluetoothDevice.getName()), "", "");
                        bhv.this.q = true;
                        bhv.this.b.sv_(bluetoothDevice);
                    }
                    LogUtil.c("InoperableBrPhysicalService", "Start to connect btDevice.");
                    bhv.this.rK_(bluetoothDevice);
                    return;
                }
                LogUtil.a("InoperableBrPhysicalService", "not current device.");
            } catch (SecurityException e2) {
                LogUtil.e("InoperableBrPhysicalService", "onDevicePaired SecurityException", ExceptionUtils.d(e2));
            }
        }

        @Override // com.huawei.devicesdk.callback.BtDevicePairCallback
        public void onDevicePairNone(BluetoothDevice bluetoothDevice) {
            try {
                if (!bhv.this.rL_(bluetoothDevice)) {
                    LogUtil.a("InoperableBrPhysicalService", "onDevicePairNone: not current device.");
                } else {
                    ReleaseLogUtil.b("DEVMGR_InoperableBrPhysicalService", " btDevice pair fail, so connect btDevice fail.");
                    bhv.this.b(3, 60032);
                }
            } catch (SecurityException e2) {
                LogUtil.e("InoperableBrPhysicalService", "onDevicePairNone SecurityException", ExceptionUtils.d(e2));
            }
        }
    };
    private ConsumerHandler<Message> g = new ConsumerHandler() { // from class: bhw
        @Override // com.huawei.devicesdk.connect.physical.ConsumerHandler
        public final void accept(Object obj) {
            bhv.this.rO_((Message) obj);
        }
    };
    private bia h = new bia(this.g);
    private bkt b = bkt.e();

    static /* synthetic */ int b(bhv bhvVar) {
        int i = bhvVar.s;
        bhvVar.s = i + 1;
        return i;
    }

    static /* synthetic */ int j(bhv bhvVar) {
        int i = bhvVar.p;
        bhvVar.p = i + 1;
        return i;
    }

    /* synthetic */ void rO_(Message message) {
        DeviceInfo deviceInfo;
        if (message == null || (deviceInfo = this.f) == null) {
            LogUtil.a("InoperableBrPhysicalService", "message or mBtDeviceInfo is null");
            return;
        }
        reportMonitorResult(message, deviceInfo, 100034);
        if (message.what == 4) {
            ReleaseLogUtil.a("DEVMGR_InoperableBrPhysicalService", "timeout fail, errorCode: ", Integer.valueOf(message.arg1));
            b(3, message.arg1);
        } else {
            LogUtil.a("InoperableBrPhysicalService", "unknown message.");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean rL_(BluetoothDevice bluetoothDevice) {
        DeviceInfo deviceInfo = this.f;
        if (deviceInfo == null || TextUtils.isEmpty(deviceInfo.getDeviceMac())) {
            LogUtil.a("InoperableBrPhysicalService", "isCurrentDevice mBtDeviceInfo is invalid");
            return false;
        }
        if (bluetoothDevice == null || TextUtils.isEmpty(bluetoothDevice.getAddress())) {
            LogUtil.a("InoperableBrPhysicalService", "isCurrentDevice btDevice is invalid");
            return false;
        }
        return this.f.getDeviceMac().equals(bluetoothDevice.getAddress());
    }

    @Override // com.huawei.devicesdk.connect.physical.PhysicalLayerBase
    public void connectDevice(DeviceInfo deviceInfo) {
        ReleaseLogUtil.b("DEVMGR_InoperableBrPhysicalService", "connectDevice.");
        if (deviceInfo == null) {
            LogUtil.a("InoperableBrPhysicalService", "btDevice is null.");
            b(3, bln.e(7, 303));
            return;
        }
        this.f = deviceInfo;
        LogUtil.c("InoperableBrPhysicalService", "Start to report connecting state.");
        synchronized (f377a) {
            if (this.d == 2) {
                ReleaseLogUtil.b("DEVMGR_InoperableBrPhysicalService", " This is connected state.");
                b(2, 100000);
                return;
            }
            b(1, 100000);
            this.mBluetoothDevice = this.mCommonAdapterUtil.sN_(deviceInfo.getDeviceMac());
            if (this.mBluetoothDevice == null) {
                LogUtil.c("InoperableBrPhysicalService", " Get bluetooth device failed.");
                b(3, bln.e(7, 303));
                return;
            }
            try {
                if (!TextUtils.isEmpty(this.mBluetoothDevice.getName())) {
                    this.f.setDeviceName(this.mBluetoothDevice.getName());
                    ReleaseLogUtil.b("DEVMGR_InoperableBrPhysicalService", "refreshDeviceName: ", this.mBluetoothDevice.getName());
                }
                e(deviceInfo);
            } catch (SecurityException unused) {
                ReleaseLogUtil.c("DEVMGR_InoperableBrPhysicalService", "BluetoothDevice getName SecurityException");
                b(3, bln.e(7, 305));
            }
        }
    }

    private void e(DeviceInfo deviceInfo) {
        try {
            if (this.mBluetoothDevice.getBondState() != 12) {
                LogUtil.c("InoperableBrPhysicalService", "Need to pair btDevice.");
                sendTimeoutMessage(this.h, 6, 60000L);
                bmw.e(100085, bmh.b(deviceInfo.getDeviceName()), "", "");
                if (!this.mCommonAdapterUtil.sJ_(this.mBluetoothDevice, this.i)) {
                    ReleaseLogUtil.a("DEVMGR_InoperableBrPhysicalService", "btDevice pair failed.");
                    b(3, 60032);
                }
            } else {
                d(deviceInfo);
                ReleaseLogUtil.b("DEVMGR_InoperableBrPhysicalService", "Device has been bonded.");
                rK_(this.mBluetoothDevice);
            }
            this.mCommonAdapterUtil.a(deviceInfo.getDeviceMac(), false);
        } catch (SecurityException e2) {
            LogUtil.e("InoperableBrPhysicalService", "bondBtDevice SecurityException:", ExceptionUtils.d(e2));
        }
    }

    private void d(DeviceInfo deviceInfo) {
        if (this.mCommonAdapterUtil.a(deviceInfo.getDeviceMac())) {
            return;
        }
        this.q = false;
        if (this.b.sx_(this.mBluetoothDevice)) {
            return;
        }
        ReleaseLogUtil.b("DEVMGR_InoperableBrPhysicalService", "mIsHidAction is false, Need to connect hfp profile.");
        bmw.e(100030, bmh.b(deviceInfo.getDeviceName()), "", "");
        this.q = true;
        this.b.sv_(this.mBluetoothDevice);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i, int i2) {
        b();
        synchronized (f377a) {
            if (i == this.d) {
                LogUtil.c("InoperableBrPhysicalService", "connectState no change");
                return;
            }
            this.d = i;
            setTriggerType(i, this.f);
            if (this.mStatusChangeCallback == null || this.f == null) {
                return;
            }
            ReleaseLogUtil.b("DEVMGR_InoperableBrPhysicalService", "report connect state: ", Integer.valueOf(i));
            this.mStatusChangeCallback.onConnectStatusChanged(this.f, i, i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        bia biaVar = this.h;
        if (biaVar == null) {
            LogUtil.a("InoperableBrPhysicalService", "mConnectHandler is null, removeTimeoutMessage fail.");
        } else {
            biaVar.c(4);
        }
    }

    @Override // com.huawei.devicesdk.connect.physical.PhysicalLayerBase
    public void disconnectDevice() {
        ReleaseLogUtil.b("DEVMGR_InoperableBrPhysicalService", "Enter disconnectDevice.");
        this.o = true;
        destroy();
        b(0, bln.b(6));
    }

    @Override // com.huawei.devicesdk.connect.physical.PhysicalLayerBase
    public boolean sendData(bim bimVar, String str) {
        if (bimVar == null || bimVar.e() == null) {
            LogUtil.a("InoperableBrPhysicalService", "date is null when sending frame");
            return false;
        }
        LogUtil.c("InoperableBrPhysicalService", "sendData socketChannel: ", Integer.valueOf(bimVar.f()));
        List<bil> e2 = bimVar.e();
        int f = bimVar.f() != 0 ? bimVar.f() : 0;
        for (bil bilVar : e2) {
            if (bilVar == null || !b(bilVar.d(), f)) {
                LogUtil.a("InoperableBrPhysicalService", "sendLinkDataCommond has write false");
                return false;
            }
            try {
                if (bilVar.a() > 0) {
                    Thread.sleep(bilVar.a());
                }
            } catch (InterruptedException unused) {
                LogUtil.e("InoperableBrPhysicalService", "send command occur InterruptedException.");
            }
        }
        return true;
    }

    private boolean b(byte[] bArr, int i) {
        if (bArr == null) {
            LogUtil.a("InoperableBrPhysicalService", "btDeviceData is null");
            return false;
        }
        synchronized (f377a) {
            if (this.d != 2) {
                LogUtil.a("InoperableBrPhysicalService", "Connect State is not connect.");
                return false;
            }
            synchronized (e) {
                if (i != 0) {
                    if (this.l != null) {
                        LogUtil.c("InoperableBrPhysicalService", "dataTransferThread2 send data.");
                        this.l.a(bArr);
                        return true;
                    }
                }
                a aVar = this.m;
                if (aVar == null) {
                    return false;
                }
                aVar.a(bArr);
                return true;
            }
        }
    }

    @Override // com.huawei.devicesdk.connect.physical.PhysicalLayerBase
    public void destroy() {
        synchronized (c) {
            e eVar = this.n;
            if (eVar != null) {
                eVar.e();
                this.n = null;
            }
        }
        synchronized (e) {
            a aVar = this.m;
            if (aVar != null) {
                aVar.c();
                this.m = null;
            }
            a aVar2 = this.l;
            if (aVar2 != null) {
                aVar2.c();
                this.l = null;
            }
        }
        this.h.a();
        this.g = null;
        this.mCommonAdapterUtil.sL_(this.mBluetoothDevice);
    }

    public void a(final int i) {
        synchronized (c) {
            c cVar = this.k;
            if (cVar != null) {
                cVar.e();
                this.k = null;
            }
            LogUtil.c("InoperableBrPhysicalService", "Start createDualSocket.channel ", Integer.valueOf(i));
            c cVar2 = new c(this.j, i);
            this.k = cVar2;
            cVar2.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() { // from class: bhv.3
                @Override // java.lang.Thread.UncaughtExceptionHandler
                public void uncaughtException(Thread thread, Throwable th) {
                    LogUtil.a("InoperableBrPhysicalService", "connect2 thread occur uncaughtException.");
                    synchronized (bhv.c) {
                        if (bhv.this.k != null) {
                            bhv.this.k.e();
                            bhv.this.k = null;
                        }
                    }
                    if (bhv.this.s < 3) {
                        bhv.this.a(i);
                    } else {
                        LogUtil.a("InoperableBrPhysicalService", "createDualSocket reconnect fail.");
                    }
                }
            });
            this.k.start();
        }
        synchronized (e) {
            a aVar = this.l;
            if (aVar != null) {
                aVar.c();
                this.l = null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void rM_(final BluetoothSocket bluetoothSocket) {
        synchronized (e) {
            a aVar = this.m;
            if (aVar != null) {
                aVar.c();
                this.m = null;
            }
            this.o = false;
            a aVar2 = new a(bluetoothSocket, 0);
            this.m = aVar2;
            aVar2.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() { // from class: bhv.1
                @Override // java.lang.Thread.UncaughtExceptionHandler
                public void uncaughtException(Thread thread, Throwable th) {
                    LogUtil.e("InoperableBrPhysicalService", "data transfer thread occur uncaughtException.");
                    synchronized (bhv.e) {
                        if (bhv.this.m != null) {
                            bhv.this.m.c();
                            bhv.this.m = null;
                        }
                        bhv.this.m = new a(bluetoothSocket, 0);
                        bhv.this.m.start();
                    }
                }
            });
            this.m.start();
        }
        LogUtil.c("InoperableBrPhysicalService", "Connect success, so report state.");
        b(2, 100000);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void rN_(final BluetoothSocket bluetoothSocket, final int i) {
        synchronized (e) {
            a aVar = this.l;
            if (aVar != null) {
                aVar.c();
                this.l = null;
            }
            LogUtil.c("InoperableBrPhysicalService", "Start startDataTransferThread2.mChannel ", Integer.valueOf(i));
            a aVar2 = new a(bluetoothSocket, i);
            this.l = aVar2;
            aVar2.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() { // from class: bhv.2
                @Override // java.lang.Thread.UncaughtExceptionHandler
                public void uncaughtException(Thread thread, Throwable th) {
                    LogUtil.a("InoperableBrPhysicalService", "data transfer thread occur uncaughtException.");
                    synchronized (bhv.e) {
                        if (bhv.this.l != null) {
                            bhv.this.l.c();
                            bhv.this.l = null;
                        }
                        bhv.this.l = new a(bluetoothSocket, i);
                        bhv.this.l.start();
                    }
                }
            });
            this.l.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        LogUtil.c("InoperableBrPhysicalService", "Connect lost.");
        b(0, 30002);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void rK_(final BluetoothDevice bluetoothDevice) {
        ReleaseLogUtil.b("DEVMGR_InoperableBrPhysicalService", "Enter connectBTDeviceThread().");
        synchronized (c) {
            e eVar = this.n;
            if (eVar != null) {
                eVar.e();
                this.n = null;
            }
            LogUtil.c("InoperableBrPhysicalService", "Start ConnectThread.");
            this.j = bluetoothDevice;
            e eVar2 = new e(bluetoothDevice);
            this.n = eVar2;
            eVar2.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() { // from class: bhv.5
                @Override // java.lang.Thread.UncaughtExceptionHandler
                public void uncaughtException(Thread thread, Throwable th) {
                    ReleaseLogUtil.c("DEVMGR_InoperableBrPhysicalService", "connect thread occur uncaughtException.");
                    synchronized (bhv.c) {
                        if (bhv.this.n != null) {
                            bhv.this.n.e();
                            bhv.this.n = null;
                        }
                    }
                    if (bhv.this.p < 3) {
                        bhv.this.b();
                        bhv.this.rK_(bluetoothDevice);
                    } else {
                        ReleaseLogUtil.a("DEVMGR_InoperableBrPhysicalService", "trigger reconnect fail.");
                        bhv.this.b(3, bln.e(7, 304));
                    }
                }
            });
            sendTimeoutMessage(this.h, 7);
            this.n.start();
        }
        synchronized (e) {
            a aVar = this.m;
            if (aVar != null) {
                aVar.c();
                this.m = null;
            }
            a aVar2 = this.l;
            if (aVar2 != null) {
                aVar2.c();
                this.l = null;
            }
        }
    }

    class e extends Thread {
        private int b;
        private final BluetoothSocket c;
        private BluetoothDevice d;
        private boolean e;
        private String f;

        private e(BluetoothDevice bluetoothDevice) {
            UUID fromString;
            this.f = y1.f5579a;
            this.e = false;
            this.b = 0;
            BluetoothSocket bluetoothSocket = null;
            this.d = null;
            LogUtil.c("InoperableBrPhysicalService", "Enter ConnectThread.");
            if (bluetoothDevice == null) {
                LogUtil.a("InoperableBrPhysicalService", "ConnectThread: device parameter is null.");
                this.c = null;
                return;
            }
            this.d = bluetoothDevice;
            try {
                if (bky.i()) {
                    fromString = UUID.fromString("82ff3820-8411-400c-b85a-55bdb32cf060");
                } else {
                    boolean a2 = SharedPreferenceManager.a(Integer.toString(1000), "wearable_emulator_connection_switch", false);
                    LogUtil.c("InoperableBrPhysicalService", "ConnectThread isEmulator:", Boolean.valueOf(a2));
                    if (a2) {
                        fromString = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
                    } else {
                        fromString = UUID.fromString("82ff3820-8411-400c-b85a-55bdb32cf060");
                    }
                }
                bluetoothSocket = bluetoothDevice.createRfcommSocketToServiceRecord(fromString);
            } catch (IOException | SecurityException e) {
                LogUtil.e("InoperableBrPhysicalService", "ConnectThread Exception:", ExceptionUtils.d(e));
            }
            this.c = bluetoothSocket;
        }

        public boolean c() {
            return this.e;
        }

        public void d(boolean z) {
            this.e = z;
        }

        /* JADX WARN: Removed duplicated region for block: B:42:0x00ff A[Catch: SecurityException -> 0x010f, TryCatch #3 {SecurityException -> 0x010f, blocks: (B:3:0x0006, B:6:0x0038, B:8:0x0043, B:10:0x0052, B:12:0x0060, B:15:0x0075, B:18:0x007c, B:21:0x009b, B:23:0x00a2, B:26:0x00ab, B:31:0x00bd, B:33:0x00d8, B:35:0x00dc, B:37:0x00e9, B:40:0x00ed, B:42:0x00ff, B:44:0x0107, B:48:0x00f4, B:50:0x0049), top: B:2:0x0006, inners: #2, #4 }] */
        /* JADX WARN: Removed duplicated region for block: B:44:0x0107 A[Catch: SecurityException -> 0x010f, TRY_LEAVE, TryCatch #3 {SecurityException -> 0x010f, blocks: (B:3:0x0006, B:6:0x0038, B:8:0x0043, B:10:0x0052, B:12:0x0060, B:15:0x0075, B:18:0x007c, B:21:0x009b, B:23:0x00a2, B:26:0x00ab, B:31:0x00bd, B:33:0x00d8, B:35:0x00dc, B:37:0x00e9, B:40:0x00ed, B:42:0x00ff, B:44:0x0107, B:48:0x00f4, B:50:0x0049), top: B:2:0x0006, inners: #2, #4 }] */
        @Override // java.lang.Thread, java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void run() {
            /*
                Method dump skipped, instructions count: 286
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: bhv.e.run():void");
        }

        private boolean c(long j) {
            BluetoothDevice bluetoothDevice;
            boolean z = System.currentTimeMillis() - j > 100;
            ReleaseLogUtil.b("DEVMGR_InoperableBrPhysicalService", "reportSocketException:", Boolean.valueOf(z));
            String bool = Boolean.toString(z);
            if (z && (bluetoothDevice = this.d) != null) {
                try {
                    Object invoke = bluetoothDevice.getClass().getMethod("isConnected", new Class[0]).invoke(this.d, new Object[0]);
                    if (invoke instanceof Boolean) {
                        Boolean bool2 = (Boolean) invoke;
                        ReleaseLogUtil.b("DEVMGR_InoperableBrPhysicalService", "isConnected reportSocketException:", bool2);
                        if (!bool2.booleanValue()) {
                            return false;
                        }
                        bmw.e(100033, bmh.b(bhv.this.f.getDeviceName()), "", bool);
                        return true;
                    }
                } catch (IllegalAccessException | IllegalArgumentException | NoSuchMethodException | InvocationTargetException unused) {
                    ReleaseLogUtil.c("InoperableBrPhysicalService", "BluetoothDevice isConnected exception;");
                    bmw.e(100033, bmh.b(bhv.this.f.getDeviceName()), "", bool);
                }
            } else {
                bmw.e(100033, bmh.b(bhv.this.f.getDeviceName()), "", bool);
            }
            return true;
        }

        private void d() {
            LogUtil.c("InoperableBrPhysicalService", "connectRefused mConnectNumber:", Integer.valueOf(this.b));
            try {
                Thread.sleep(5000L);
                bmw.e(100074, bmh.b(bhv.this.f.getDeviceName()), "", "");
                this.b++;
            } catch (InterruptedException unused) {
                LogUtil.e("InoperableBrPhysicalService", "connectRefused InterruptedException");
            }
        }

        public void e() {
            ReleaseLogUtil.b("DEVMGR_InoperableBrPhysicalService", "connect thread cancel");
            try {
                if (this.c != null) {
                    this.b = 0;
                    bhv.this.n.d(true);
                    this.c.close();
                }
                if (bhv.this.k != null) {
                    bhv.this.k.e();
                    bhv.this.k = null;
                }
            } catch (Exception unused) {
                ReleaseLogUtil.c("DEVMGR_InoperableBrPhysicalService", "Close socket occur exception");
            }
        }
    }

    class c extends Thread {

        /* renamed from: a, reason: collision with root package name */
        private int f381a;
        private final BluetoothSocket b;
        private BluetoothDevice d;
        private boolean e;
        private String i;

        private c(BluetoothDevice bluetoothDevice, int i) {
            this.i = y1.f5579a;
            this.e = false;
            this.f381a = 0;
            LogUtil.c("ConnectThread2", "Enter ConnectThread2.channel ", Integer.valueOf(i));
            BluetoothSocket bluetoothSocket = null;
            if (bluetoothDevice == null || i <= 0) {
                ReleaseLogUtil.a("DEVMGR_ConnectThread2", "ConnectThread2 device parameter is null.");
                this.b = null;
                this.f381a = 0;
                return;
            }
            this.d = bluetoothDevice;
            try {
                Object invoke = bluetoothDevice.getClass().getMethod("createRfcommSocket", Integer.TYPE).invoke(bluetoothDevice, Integer.valueOf(i));
                if (invoke instanceof BluetoothSocket) {
                    bluetoothSocket = (BluetoothSocket) invoke;
                }
            } catch (IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
                ReleaseLogUtil.c("DEVMGR_ConnectThread2", "create socket2 exception", ExceptionUtils.d(e));
            }
            this.b = bluetoothSocket;
            this.f381a = i;
            ReleaseLogUtil.b("DEVMGR_ConnectThread2", "Enter ConnectThread2. mChannel ", Integer.valueOf(i));
        }

        public boolean d() {
            return this.e;
        }

        public void c(boolean z) {
            this.e = z;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            try {
                bhv.b(bhv.this);
                ReleaseLogUtil.b("DEVMGR_ConnectThread2", " start connect thread2, time: ", Integer.valueOf(bhv.this.p));
                setName("ConnectThread" + this.i);
                bhv.this.b.c();
                if (this.b == null) {
                    LogUtil.a("InoperableBrPhysicalService", "mBTSocket2 is null");
                    return;
                }
                try {
                    if (d()) {
                        return;
                    }
                    ReleaseLogUtil.b("DEVMGR_ConnectThread2", "mBTSocket2 start connect.");
                    bmw.e(100086, bmh.b(bhv.this.f.getDeviceName()), "", "");
                    this.b.connect();
                    ReleaseLogUtil.b("DEVMGR_ConnectThread2", "Start DataTransferThread2.mChannel ", Integer.valueOf(this.f381a));
                    bhv.this.rN_(this.b, this.f381a);
                } catch (IOException e) {
                    try {
                        ReleaseLogUtil.c("DEVMGR_ConnectThread2", "mBTSocket2 in connect occur exception : ", ExceptionUtils.d(e));
                        this.b.close();
                    } catch (IOException unused) {
                        LogUtil.e("ConnectThread2", "mBTSocket2 close occur exception in catch exception");
                    }
                    bmw.e(100087, bmh.b(bhv.this.f.getDeviceName()), "", "");
                    b();
                }
            } catch (SecurityException e2) {
                LogUtil.e("InoperableBrPhysicalService", "ConnectThread2#run SecurityException:", ExceptionUtils.d(e2));
            }
        }

        private void b() {
            ExternalDeviceCapability a2 = bjx.a().a(this.d.getAddress());
            if (a2 == null) {
                ReleaseLogUtil.a("DEVMGR_ConnectThread2", "mBTSocket2 close occur exception externalDeviceCapability is null");
                return;
            }
            LogUtil.c("ConnectThread2", "mBTSocket2 close occur exception deviceIdentify: ", blt.b(this.d.getAddress()));
            UniteDevice uniteDevice = new UniteDevice();
            uniteDevice.setIdentify(this.d.getAddress());
            uniteDevice.setCapability(a2);
            boolean e = bla.e(uniteDevice, 158);
            ReleaseLogUtil.b("DEVMGR_ConnectThread2", "mBTSocket2 close occur exception isSupportDualDisconnectAp: ", Boolean.valueOf(e));
            if (e) {
                bmw.e(100075, bmh.b(bhv.this.f.getDeviceName()), "", "");
                synchronized (bhv.c) {
                    if (bhv.this.n != null) {
                        bhv.this.n.e();
                        bhv.this.n = null;
                    }
                }
                synchronized (bhv.e) {
                    if (bhv.this.m != null) {
                        bhv.this.m.c();
                        bhv.this.m = null;
                    }
                }
            }
        }

        public void e() {
            LogUtil.c("ConnectThread2", "connect thread cancel");
            try {
                if (this.b != null) {
                    bhv.this.k.c(true);
                    this.b.close();
                }
            } catch (IOException unused) {
                ReleaseLogUtil.c("DEVMGR_ConnectThread2", "Close socket occur exception");
            }
        }
    }

    class a extends Thread {

        /* renamed from: a, reason: collision with root package name */
        private final BluetoothSocket f380a;
        private boolean b;
        private final InputStream c;
        private final OutputStream d;
        private String g;
        private bkk h;
        private int i;

        private a(BluetoothSocket bluetoothSocket, int i) {
            InputStream inputStream;
            this.f380a = bluetoothSocket;
            this.i = i;
            this.g = "InoperableBrPhysicalService" + i;
            LogUtil.c("InoperableBrPhysicalService", "Start DataTransferThread2.mSocketChannel ", Integer.valueOf(this.i), "mTag: ", this.g);
            OutputStream outputStream = null;
            try {
                inputStream = bluetoothSocket.getInputStream();
                try {
                    outputStream = bluetoothSocket.getOutputStream();
                } catch (IOException unused) {
                    LogUtil.e(this.g, "Get Input Stream Handle exception");
                    this.c = inputStream;
                    this.d = outputStream;
                    bkk bkkVar = new bkk(bhv.this.mDeviceInfo, bhv.this.mMessageReceiveCallback, i);
                    this.h = bkkVar;
                    bkkVar.start();
                }
            } catch (IOException unused2) {
                inputStream = null;
            }
            this.c = inputStream;
            this.d = outputStream;
            bkk bkkVar2 = new bkk(bhv.this.mDeviceInfo, bhv.this.mMessageReceiveCallback, i);
            this.h = bkkVar2;
            bkkVar2.start();
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            if (this.c == null) {
                LogUtil.c("InoperableBrPhysicalService", "mBTInStream is null");
                this.h.e();
                bhv.this.a();
                return;
            }
            byte[] bArr = new byte[1032];
            this.b = true;
            while (this.b) {
                try {
                    Arrays.fill(bArr, (byte) 0);
                    this.h.c(Arrays.copyOfRange(bArr, 0, this.c.read(bArr)));
                } catch (IOException unused) {
                    ReleaseLogUtil.c("DEVMGR_" + this.g, "SPP Socket read occur Exception", ", mIsDisconnectRead:", Boolean.valueOf(bhv.this.o));
                    bmw.e(100035, bmh.b(bhv.this.f.getDeviceName()), "", Boolean.toString(bhv.this.o));
                    this.h.e();
                    bhv.this.a();
                    return;
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(byte[] bArr) {
            try {
                if (this.d != null) {
                    blt.b(this.g, bArr, blt.a(bhv.this.mDeviceInfo), " SDK-->Device: ");
                    this.d.write(bArr);
                    this.d.flush();
                } else {
                    LogUtil.c(this.g, "Send BT Data with mBTOutStream is null.");
                }
            } catch (Exception unused) {
                LogUtil.e(this.g, "SPP Socket send occur Exception");
                bhv.this.a();
            }
        }

        /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:10:0x0021
            	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1179)
            	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.collectHandlerRegions(ExcHandlersRegionMaker.java:53)
            	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.process(ExcHandlersRegionMaker.java:38)
            	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:27)
            */
        public void c() {
            /*
                r5 = this;
                java.lang.String r0 = r5.g
                java.lang.String r1 = "data transfer thread cancel"
                java.lang.Object[] r1 = new java.lang.Object[]{r1}
                health.compact.a.LogUtil.c(r0, r1)
                r0 = 1
                r1 = 0
                java.io.InputStream r2 = r5.c     // Catch: java.lang.Exception -> L21
                if (r2 == 0) goto L15
                r2.close()     // Catch: java.lang.Exception -> L21
                goto L2c
            L15:
                java.lang.String r2 = r5.g     // Catch: java.lang.Exception -> L21
                java.lang.Object[] r3 = new java.lang.Object[r0]     // Catch: java.lang.Exception -> L21
                java.lang.String r4 = "Cancel Spp Socket with mBTInStream is null."
                r3[r1] = r4     // Catch: java.lang.Exception -> L21
                health.compact.a.LogUtil.a(r2, r3)     // Catch: java.lang.Exception -> L21
                goto L2c
            L21:
                java.lang.String r2 = r5.g
                java.lang.String r3 = "In Stream close occur Exception"
                java.lang.Object[] r3 = new java.lang.Object[]{r3}
                health.compact.a.LogUtil.e(r2, r3)
            L2c:
                java.io.OutputStream r2 = r5.d     // Catch: java.lang.Exception -> L40
                if (r2 == 0) goto L34
                r2.close()     // Catch: java.lang.Exception -> L40
                goto L4b
            L34:
                java.lang.String r2 = r5.g     // Catch: java.lang.Exception -> L40
                java.lang.Object[] r0 = new java.lang.Object[r0]     // Catch: java.lang.Exception -> L40
                java.lang.String r3 = "Cancel Spp Socket with mBTOutStream is null."
                r0[r1] = r3     // Catch: java.lang.Exception -> L40
                health.compact.a.LogUtil.c(r2, r0)     // Catch: java.lang.Exception -> L40
                goto L4b
            L40:
                java.lang.String r0 = r5.g
                java.lang.String r2 = "Out Stream close occur Exception"
                java.lang.Object[] r2 = new java.lang.Object[]{r2}
                health.compact.a.LogUtil.e(r0, r2)
            L4b:
                android.bluetooth.BluetoothSocket r0 = r5.f380a     // Catch: java.lang.Exception -> L51
                r0.close()     // Catch: java.lang.Exception -> L51
                goto L5c
            L51:
                java.lang.String r0 = r5.g
                java.lang.String r2 = "Socket close occur Exception"
                java.lang.Object[] r2 = new java.lang.Object[]{r2}
                health.compact.a.LogUtil.e(r0, r2)
            L5c:
                r5.b = r1
                bkk r0 = r5.h
                r0.e()
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: bhv.a.c():void");
        }
    }
}
