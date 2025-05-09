package defpackage;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanSettings;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.hwbtsdk.btdatatype.callback.BtDeviceDiscoverCallback;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class iyr {

    /* renamed from: a, reason: collision with root package name */
    private static iyr f13671a;
    private Context f;
    private static final Object c = new Object();
    private static final Object b = new Object();
    private HandlerThread g = new HandlerThread("BluetoothDeviceAutoScanUtil");
    private Handler h = null;
    private BluetoothAdapter e = null;
    private iyh j = null;
    private iyy i = null;
    private BtDeviceDiscoverCallback d = null;

    private static int b(int i) {
        switch (i) {
            case 10:
                return 1;
            case 11:
                return 4;
            case 12:
                return 3;
            case 13:
                return 2;
            default:
                return 0;
        }
    }

    private iyr(Context context) {
        this.f = null;
        this.f = context;
        f();
    }

    public static iyr c(Context context) {
        iyr iyrVar;
        synchronized (c) {
            if (f13671a == null) {
                f13671a = new iyr(context);
            }
            iyrVar = f13671a;
        }
        return iyrVar;
    }

    public void d(final BtDeviceDiscoverCallback btDeviceDiscoverCallback) {
        LogUtil.c("BluetoothDeviceAutoScanUtil", "Enter startAutoScanDiscovery.");
        if (btDeviceDiscoverCallback == null) {
            LogUtil.a("BluetoothDeviceAutoScanUtil", "startAutoScanDiscovery callback is null.");
            return;
        }
        f();
        try {
            this.h.post(new Runnable() { // from class: iyr.1
                @Override // java.lang.Runnable
                public void run() {
                    iyr.this.b(btDeviceDiscoverCallback);
                }
            });
        } catch (IllegalStateException unused) {
            LogUtil.e("BluetoothDeviceAutoScanUtil", "startAutoScanDiscovery IllegalStateException");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(BtDeviceDiscoverCallback btDeviceDiscoverCallback) {
        if (e()) {
            e(btDeviceDiscoverCallback);
        } else {
            LogUtil.a("BluetoothDeviceAutoScanUtil", "startDiscoverBleDevice sdk version is not support.");
        }
    }

    private boolean e() {
        Context context = this.f;
        return context != null && context.getPackageManager().hasSystemFeature("android.hardware.bluetooth_le");
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x0080, code lost:
    
        r5.j = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0083, code lost:
    
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void e(com.huawei.hwbtsdk.btdatatype.callback.BtDeviceDiscoverCallback r6) {
        /*
            r5 = this;
            java.lang.String r0 = "Enter discoverBleDevice."
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            java.lang.String r1 = "BluetoothDeviceAutoScanUtil"
            health.compact.a.LogUtil.c(r1, r0)
            boolean r0 = r5.h()
            if (r0 != 0) goto L1d
            java.lang.String r6 = "discoverBleDevice is not support."
            java.lang.Object[] r6 = new java.lang.Object[]{r6}
            java.lang.String r0 = "BluetoothDeviceAutoScanUtil"
            health.compact.a.LogUtil.a(r0, r6)
            return
        L1d:
            r5.c()
            java.lang.Object r0 = defpackage.iyr.b
            monitor-enter(r0)
            r5.d = r6     // Catch: java.lang.Throwable -> L90
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L90
            iyy r6 = new iyy     // Catch: java.lang.IllegalThreadStateException -> L84
            com.huawei.hwbtsdk.btdatatype.callback.BtDeviceDiscoverCallback r0 = r5.d     // Catch: java.lang.IllegalThreadStateException -> L84
            r6.<init>(r0)     // Catch: java.lang.IllegalThreadStateException -> L84
            r5.i = r6     // Catch: java.lang.IllegalThreadStateException -> L84
            r6 = 0
            r0 = r6
            r1 = r0
        L32:
            r2 = 3
            if (r1 >= r2) goto L7e
            android.bluetooth.BluetoothAdapter r2 = r5.e     // Catch: java.lang.IllegalThreadStateException -> L84
            if (r2 == 0) goto L7e
            boolean r2 = r2.isEnabled()     // Catch: java.lang.IllegalThreadStateException -> L84
            if (r2 == 0) goto L7e
            android.bluetooth.BluetoothAdapter r2 = r5.e     // Catch: java.lang.IllegalThreadStateException -> L84
            int r2 = r2.getState()     // Catch: java.lang.IllegalThreadStateException -> L84
            r3 = 12
            if (r2 == r3) goto L4a
            goto L7e
        L4a:
            r2 = 2
            r3 = 1
            boolean r0 = r5.i()     // Catch: java.lang.IllegalStateException -> L51 java.lang.NullPointerException -> L65 java.lang.IllegalThreadStateException -> L84
            goto L78
        L51:
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch: java.lang.IllegalThreadStateException -> L84
            java.lang.String r4 = "discoverBleDevice IllegalStateException version :"
            r2[r6] = r4     // Catch: java.lang.IllegalThreadStateException -> L84
            int r4 = android.os.Build.VERSION.SDK_INT     // Catch: java.lang.IllegalThreadStateException -> L84
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch: java.lang.IllegalThreadStateException -> L84
            r2[r3] = r4     // Catch: java.lang.IllegalThreadStateException -> L84
            java.lang.String r3 = "BluetoothDeviceAutoScanUtil"
            health.compact.a.LogUtil.c(r3, r2)     // Catch: java.lang.IllegalThreadStateException -> L84
            goto L78
        L65:
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch: java.lang.IllegalThreadStateException -> L84
            java.lang.String r4 = "discoverBleDevice exception version :"
            r2[r6] = r4     // Catch: java.lang.IllegalThreadStateException -> L84
            int r4 = android.os.Build.VERSION.SDK_INT     // Catch: java.lang.IllegalThreadStateException -> L84
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch: java.lang.IllegalThreadStateException -> L84
            r2[r3] = r4     // Catch: java.lang.IllegalThreadStateException -> L84
            java.lang.String r3 = "BluetoothDeviceAutoScanUtil"
            health.compact.a.LogUtil.c(r3, r2)     // Catch: java.lang.IllegalThreadStateException -> L84
        L78:
            if (r0 == 0) goto L7b
            goto L7e
        L7b:
            int r1 = r1 + 1
            goto L32
        L7e:
            if (r0 != 0) goto L8f
            r6 = 0
            r5.j = r6     // Catch: java.lang.IllegalThreadStateException -> L84
            return
        L84:
            java.lang.String r6 = "discoverBleDevice IllegalThreadStateException."
            java.lang.Object[] r6 = new java.lang.Object[]{r6}
            java.lang.String r0 = "BluetoothDeviceAutoScanUtil"
            health.compact.a.LogUtil.e(r0, r6)
        L8f:
            return
        L90:
            r6 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L90
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.iyr.e(com.huawei.hwbtsdk.btdatatype.callback.BtDeviceDiscoverCallback):void");
    }

    private boolean h() {
        if (this.e == null) {
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            this.e = defaultAdapter;
            if (defaultAdapter == null) {
                LogUtil.a("BluetoothDeviceAutoScanUtil", "isSupportScanCondition mAdapter is null.");
                return false;
            }
        }
        a();
        return true;
    }

    private boolean i() {
        BluetoothLeScanner bluetoothLeScanner = this.e.getBluetoothLeScanner();
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new ScanFilter.Builder().build());
        if (bluetoothLeScanner != null) {
            ReleaseLogUtil.b("BTSDK_BluetoothDeviceAutoScanUtil", "startBleScan");
            try {
                bluetoothLeScanner.startScan(arrayList, new ScanSettings.Builder().setScanMode(2).build(), this.i);
            } catch (SecurityException unused) {
                LogUtil.e("BluetoothDeviceAutoScanUtil", "startBleScan SecurityException");
            }
        }
        return true;
    }

    public void b() {
        LogUtil.c("BluetoothDeviceAutoScanUtil", "Enter cancelBleDeviceDiscovery().");
        if (e()) {
            c(1);
        }
        LogUtil.c("BluetoothDeviceAutoScanUtil", "Leave cancelBleDeviceDiscovery().");
    }

    public void c(int i) {
        if (this.j == null) {
            this.j = new iyh(this.d);
        }
        if (this.e == null) {
            this.e = BluetoothAdapter.getDefaultAdapter();
        }
        LogUtil.c("BluetoothDeviceAutoScanUtil", "handleBleDeviceDiscover stop ble device scan.");
        if (this.e.isEnabled()) {
            try {
                j();
            } catch (Exception unused) {
                LogUtil.e("BluetoothDeviceAutoScanUtil", "handleBleDeviceDiscover Exception.");
            }
        }
        synchronized (b) {
            if (this.d != null) {
                if (i == 1) {
                    LogUtil.c("BluetoothDeviceAutoScanUtil", "handleBleDeviceDiscover report ble discover cancel.");
                    this.d.onDeviceDiscoveryCanceled();
                } else if (i == 2) {
                    LogUtil.c("BluetoothDeviceAutoScanUtil", "handleBleDeviceDiscover report ble discover finish.");
                    this.d.onDeviceDiscoveryFinished();
                } else {
                    LogUtil.a("BluetoothDeviceAutoScanUtil", "handleBleDeviceDiscover ble scan handle type is incorrect.");
                }
                HandlerThread handlerThread = this.g;
                if (handlerThread != null && handlerThread.getLooper() != null) {
                    this.g.getLooper().quit();
                    this.g = null;
                    this.h = null;
                }
                this.d = null;
            }
        }
    }

    private void j() {
        Object[] objArr = new Object[2];
        objArr[0] = "stopBleScan mScanCallback:";
        objArr[1] = Boolean.valueOf(this.i != null);
        ReleaseLogUtil.b("BTSDK_BluetoothDeviceAutoScanUtil", objArr);
        BluetoothLeScanner bluetoothLeScanner = this.e.getBluetoothLeScanner();
        if (bluetoothLeScanner != null) {
            bluetoothLeScanner.stopScan(this.i);
        }
        iyy iyyVar = this.i;
        if (iyyVar != null) {
            iyyVar.e();
        }
    }

    public int d() {
        BluetoothAdapter bluetoothAdapter = this.e;
        if (bluetoothAdapter == null) {
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            this.e = defaultAdapter;
            if (defaultAdapter == null) {
                return 1;
            }
            return b(defaultAdapter.getState());
        }
        return b(bluetoothAdapter.getState());
    }

    private void c() {
        if (this.e == null) {
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            this.e = defaultAdapter;
            if (defaultAdapter == null) {
                LogUtil.a("BluetoothDeviceAutoScanUtil", "mAdapter is null.");
                return;
            }
        }
        a();
        if (this.i != null) {
            c(2);
        } else {
            LogUtil.a("BluetoothDeviceAutoScanUtil", "cancelScanIfExist default");
        }
    }

    private void a() {
        try {
            if (this.e.isDiscovering()) {
                this.e.cancelDiscovery();
            }
        } catch (SecurityException e) {
            LogUtil.e("BluetoothDeviceAutoScanUtil", "cancelDiscoveryScan SecurityException:", ExceptionUtils.d(e));
        }
    }

    private void f() {
        HandlerThread handlerThread = this.g;
        if (handlerThread == null || !handlerThread.isAlive()) {
            HandlerThread handlerThread2 = new HandlerThread("BluetoothDeviceAutoScanUtil");
            this.g = handlerThread2;
            handlerThread2.start();
            this.h = new Handler(this.g.getLooper());
        }
    }
}
