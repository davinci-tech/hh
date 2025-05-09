package defpackage;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.handler.ExtendHandler;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.huawei.hwbtsdk.btdatatype.callback.BtDeviceDiscoverCallback;
import com.huawei.hwbtsdk.btdatatype.datatype.BluetoothDeviceNode;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/* loaded from: classes5.dex */
public class iys {
    private BluetoothManager f;
    private String h;
    private long p;

    /* renamed from: a, reason: collision with root package name */
    private static final Object f13672a = new Object();
    private static final Object b = new Object();
    private static final Object d = new Object();
    private static Semaphore e = new Semaphore(1);
    private static iys c = null;
    private ExtendHandler m = null;
    private c o = new c(this);
    private BluetoothAdapter i = null;
    private iyh j = null;
    private iyy k = null;
    private BtDeviceDiscoverCallback g = null;
    private d s = null;
    private ConcurrentHashMap<String, Boolean> l = new ConcurrentHashMap<>();
    private BtDeviceDiscoverCallback n = new BtDeviceDiscoverCallback() { // from class: iys.1
        @Override // com.huawei.hwbtsdk.btdatatype.callback.BtDeviceDiscoverCallback
        public void onDeviceDiscoveryCanceled() {
        }

        @Override // com.huawei.hwbtsdk.btdatatype.callback.BtDeviceDiscoverCallback
        public void onDeviceDiscoveryFinished() {
        }

        @Override // com.huawei.hwbtsdk.btdatatype.callback.BtDeviceDiscoverCallback
        public void onFailure(int i, String str) {
        }

        @Override // com.huawei.hwbtsdk.btdatatype.callback.BtDeviceDiscoverCallback
        public void onDeviceDiscovered(BluetoothDeviceNode bluetoothDeviceNode, int i, byte[] bArr) {
            if (bluetoothDeviceNode != null) {
                synchronized (iys.b) {
                    if (iys.this.g != null) {
                        iys.this.g.onDeviceDiscovered(bluetoothDeviceNode, i, bArr);
                    }
                }
            }
        }
    };

    private iys() {
        this.f = null;
        Object systemService = BaseApplication.e().getSystemService("bluetooth");
        if (systemService instanceof BluetoothManager) {
            this.f = (BluetoothManager) systemService;
        } else {
            LogUtil.a("BluetoothDeviceReconnectScanUtil", "bluetoothManager is not BluetoothManager");
        }
        j();
    }

    public static iys e() {
        iys iysVar;
        synchronized (f13672a) {
            if (c == null) {
                c = new iys();
            }
            iysVar = c;
        }
        return iysVar;
    }

    public void b(final String str, final BtDeviceDiscoverCallback btDeviceDiscoverCallback) {
        try {
            e.acquire();
        } catch (InterruptedException unused) {
            LogUtil.e("BluetoothDeviceReconnectScanUtil", "startReconnectScanDiscovery InterruptedException");
        }
        Object[] objArr = new Object[2];
        objArr[0] = "Enter startReconnectScanDiscovery callback is null:";
        objArr[1] = Boolean.valueOf(btDeviceDiscoverCallback == null);
        LogUtil.c("BluetoothDeviceReconnectScanUtil", objArr);
        if (btDeviceDiscoverCallback == null) {
            return;
        }
        if (!iyl.a()) {
            LogUtil.a("BluetoothDeviceReconnectScanUtil", "startDiscoverBleDevice sdk version is not support.");
            return;
        }
        j();
        this.m.postTask(new Runnable() { // from class: iys.3
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.c("BluetoothDeviceReconnectScanUtil", "startReconnectScanDiscovery identify: ", iyl.d().e(str));
                iys.this.c(str, btDeviceDiscoverCallback);
            }
        });
        try {
            Boolean bool = true;
            this.l.put(str, true);
            int i = 0;
            while (i < 15) {
                if (!bool.booleanValue()) {
                    break;
                }
                LogUtil.d("BluetoothDeviceReconnectScanUtil", "startReconnectScanDiscovery tryAcquire isAcquired:", Boolean.valueOf(e.tryAcquire(1000L, TimeUnit.MILLISECONDS)));
                i++;
                bool = this.l.get(str);
                if (bool == null) {
                    bool = true;
                }
            }
        } catch (InterruptedException unused2) {
            LogUtil.e("BluetoothDeviceReconnectScanUtil", "startReconnectScanDiscovery InterruptedException");
        }
        e.release();
        LogUtil.c("BluetoothDeviceReconnectScanUtil", "Enter startReconnectScanDiscovery end");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str, BtDeviceDiscoverCallback btDeviceDiscoverCallback) {
        BluetoothAdapter bluetoothAdapter;
        boolean i = i();
        LogUtil.c("BluetoothDeviceReconnectScanUtil", "Enter discoverBleDevice isSupportScan:", Boolean.valueOf(i));
        if (i) {
            c();
            synchronized (b) {
                this.g = btDeviceDiscoverCallback;
            }
            try {
                b();
                this.k = new iyy(this.n);
                boolean z = false;
                for (int i2 = 0; i2 < 3 && (bluetoothAdapter = this.i) != null && bluetoothAdapter.isEnabled() && this.i.getState() == 12; i2++) {
                    try {
                        z = d(str);
                    } catch (IllegalStateException unused) {
                        LogUtil.e("BluetoothDeviceReconnectScanUtil", "discoverBleDevice IllegalStateException version :", Integer.valueOf(Build.VERSION.SDK_INT));
                    } catch (NullPointerException unused2) {
                        LogUtil.e("BluetoothDeviceReconnectScanUtil", "discoverBleDevice exception version :", Integer.valueOf(Build.VERSION.SDK_INT));
                    }
                    if (z) {
                        break;
                    }
                }
                d(z, str);
            } catch (IllegalThreadStateException | SecurityException e2) {
                LogUtil.e("BluetoothDeviceReconnectScanUtil", "discoverBleDevice Exception:", ExceptionUtils.d(e2));
            }
        }
    }

    private void d(boolean z, String str) {
        if (!z) {
            this.j = null;
            return;
        }
        j();
        d dVar = new d();
        this.s = dVar;
        this.m.postTask(dVar, 15000L);
        Message obtain = Message.obtain();
        obtain.what = 1;
        this.m.sendEmptyMessage(obtain.what, 1000L);
        this.h = str;
    }

    private boolean i() {
        if (this.i != null) {
            return true;
        }
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        this.i = defaultAdapter;
        if (defaultAdapter != null) {
            return true;
        }
        LogUtil.a("BluetoothDeviceReconnectScanUtil", "isSupportScanCondition mAdapter is null.");
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0069  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean d(java.lang.String r9) {
        /*
            r8 = this;
            java.lang.String r0 = "BluetoothDeviceReconnectScanUtil"
            android.bluetooth.BluetoothAdapter r1 = r8.i
            android.bluetooth.le.BluetoothLeScanner r1 = r1.getBluetoothLeScanner()
            java.util.ArrayList r2 = new java.util.ArrayList
            r3 = 1
            r2.<init>(r3)
            android.bluetooth.le.ScanFilter$Builder r4 = new android.bluetooth.le.ScanFilter$Builder
            r4.<init>()
            r4.setDeviceAddress(r9)
            android.bluetooth.le.ScanFilter r9 = r4.build()
            r2.add(r9)
            if (r1 == 0) goto L9b
            boolean r9 = defpackage.bky.c()
            java.lang.Boolean r9 = java.lang.Boolean.valueOf(r9)
            java.lang.String r4 = "startBleScan isEmui110: "
            java.lang.Object[] r9 = new java.lang.Object[]{r4, r9}
            java.lang.String r4 = "BTSDK_BluetoothDeviceReconnectScanUtil"
            health.compact.a.ReleaseLogUtil.b(r4, r9)
            long r4 = java.lang.System.currentTimeMillis()
            r8.p = r4
            r9 = 0
            r4 = 2
            java.lang.String r5 = "02:00:00"
            java.lang.String r6 = "05:00:00"
            boolean r5 = defpackage.bmg.a(r5, r6)     // Catch: java.text.ParseException -> L59
            if (r5 == 0) goto L47
            r5 = r9
            goto L48
        L47:
            r5 = r4
        L48:
            java.lang.Object[] r6 = new java.lang.Object[r4]     // Catch: java.text.ParseException -> L5a
            java.lang.String r7 = "scanMode: "
            r6[r9] = r7     // Catch: java.text.ParseException -> L5a
            java.lang.Integer r7 = java.lang.Integer.valueOf(r5)     // Catch: java.text.ParseException -> L5a
            r6[r3] = r7     // Catch: java.text.ParseException -> L5a
            health.compact.a.LogUtil.c(r0, r6)     // Catch: java.text.ParseException -> L5a
            goto L63
        L59:
            r5 = r4
        L5a:
            java.lang.String r6 = "Time ParseException occur."
            java.lang.Object[] r6 = new java.lang.Object[]{r6}
            health.compact.a.LogUtil.e(r0, r6)
        L63:
            boolean r6 = defpackage.bky.c()
            if (r6 == 0) goto L6a
            r5 = r9
        L6a:
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch: java.lang.SecurityException -> L8c
            java.lang.String r6 = "final scanMode: "
            r4[r9] = r6     // Catch: java.lang.SecurityException -> L8c
            java.lang.Integer r9 = java.lang.Integer.valueOf(r5)     // Catch: java.lang.SecurityException -> L8c
            r4[r3] = r9     // Catch: java.lang.SecurityException -> L8c
            health.compact.a.LogUtil.c(r0, r4)     // Catch: java.lang.SecurityException -> L8c
            android.bluetooth.le.ScanSettings$Builder r9 = new android.bluetooth.le.ScanSettings$Builder     // Catch: java.lang.SecurityException -> L8c
            r9.<init>()     // Catch: java.lang.SecurityException -> L8c
            android.bluetooth.le.ScanSettings$Builder r9 = r9.setScanMode(r5)     // Catch: java.lang.SecurityException -> L8c
            android.bluetooth.le.ScanSettings r9 = r9.build()     // Catch: java.lang.SecurityException -> L8c
            iyy r4 = r8.k     // Catch: java.lang.SecurityException -> L8c
            r1.startScan(r2, r9, r4)     // Catch: java.lang.SecurityException -> L8c
            goto L9b
        L8c:
            r9 = move-exception
            java.lang.String r1 = "startBleScan SecurityException:"
            java.lang.String r9 = defpackage.bll.a(r9)
            java.lang.Object[] r9 = new java.lang.Object[]{r1, r9}
            health.compact.a.LogUtil.e(r0, r9)
        L9b:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.iys.d(java.lang.String):boolean");
    }

    public void a(String str) {
        ConcurrentHashMap<String, Boolean> concurrentHashMap;
        Object[] objArr = new Object[6];
        objArr[0] = "Enter cancelBleDeviceDiscovery(), mCurrentDevice:";
        objArr[1] = iyl.d().e(this.h);
        objArr[2] = ",identify:";
        objArr[3] = iyl.d().e(str);
        objArr[4] = "mScanDeviceMap:";
        objArr[5] = Boolean.valueOf(this.l == null);
        LogUtil.c("BluetoothDeviceReconnectScanUtil", objArr);
        String str2 = this.h;
        if (TextUtils.isEmpty(str2) || !str2.equals(str) || (concurrentHashMap = this.l) == null) {
            return;
        }
        concurrentHashMap.put(str, false);
        LogUtil.c("BluetoothDeviceReconnectScanUtil", "cancelBleDeviceDiscovery remove");
        if (this.s != null) {
            d(1);
        }
    }

    class d extends Thread {
        d() {
            super.setName("StopBleDiscoveryThread");
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BluetoothDeviceReconnectScanUtil", "Start to stop ble discover for time arrive.");
            iys.this.d(2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i) {
        this.s = null;
        if (this.j == null) {
            this.j = new iyh(this.n);
        }
        if (this.i == null) {
            this.i = BluetoothAdapter.getDefaultAdapter();
        }
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BluetoothDeviceReconnectScanUtil", "Start to stopLeScan.");
        if (this.i.isEnabled()) {
            try {
                f();
            } catch (Exception unused) {
                LogUtil.e("BluetoothDeviceReconnectScanUtil", "unknown Exception");
            }
        }
        synchronized (b) {
            if (this.g != null) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BluetoothDeviceReconnectScanUtil", "handleBleDeviceDiscover handleBleScanType: ", Integer.valueOf(i));
                if (i == 1) {
                    this.g.onDeviceDiscoveryCanceled();
                } else if (i == 2) {
                    this.g.onDeviceDiscoveryFinished();
                } else {
                    LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BluetoothDeviceReconnectScanUtil", "Ble scan handle type is incorrect.");
                }
                this.g = null;
            }
        }
    }

    private void f() {
        Object[] objArr = new Object[2];
        objArr[0] = "stopBleScan mScanCallback:";
        objArr[1] = Boolean.valueOf(this.k != null);
        ReleaseLogUtil.b("BTSDK_BluetoothDeviceReconnectScanUtil", objArr);
        BluetoothLeScanner bluetoothLeScanner = this.i.getBluetoothLeScanner();
        if (bluetoothLeScanner != null) {
            bluetoothLeScanner.stopScan(this.k);
        }
        ExtendHandler extendHandler = this.m;
        if (extendHandler != null) {
            extendHandler.removeTasksAndMessages();
        }
        iyy iyyVar = this.k;
        if (iyyVar != null) {
            iyyVar.e();
        }
        this.h = null;
    }

    private void c() {
        if (this.i == null) {
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            this.i = defaultAdapter;
            if (defaultAdapter == null) {
                LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BluetoothDeviceReconnectScanUtil", "mAdapter is null.");
                return;
            }
        }
        if (this.s != null) {
            d(2);
        }
    }

    private void j() {
        synchronized (d) {
            this.m = HandlerCenter.yt_(this.o, "BluetoothDeviceReconnectScanUtil");
        }
    }

    private void b() {
        ArrayList a2 = a();
        synchronized (b) {
            Iterator it = a2.iterator();
            while (it.hasNext()) {
                BluetoothDeviceNode bluetoothDeviceNode = (BluetoothDeviceNode) it.next();
                if (bluetoothDeviceNode != null && this.g != null) {
                    if (bluetoothDeviceNode.getBtDevice() != null) {
                        LogUtil.d("BluetoothDeviceReconnectScanUtil", "connected device:", bluetoothDeviceNode.getBtDevice().getName());
                    }
                    this.g.onDeviceDiscovered(bluetoothDeviceNode, 0, null);
                }
            }
        }
    }

    private ArrayList a() {
        ArrayList<BluetoothDeviceNode> arrayList = new ArrayList<>(16);
        BluetoothManager bluetoothManager = this.f;
        if (bluetoothManager != null) {
            for (BluetoothDevice bluetoothDevice : bluetoothManager.getConnectedDevices(7)) {
                BluetoothDeviceNode bluetoothDeviceNode = new BluetoothDeviceNode();
                bluetoothDeviceNode.setBtDevice(bluetoothDevice);
                arrayList.add(bluetoothDeviceNode);
            }
        }
        d(arrayList);
        return arrayList;
    }

    private void d(ArrayList<BluetoothDeviceNode> arrayList) {
        Set<BluetoothDevice> set;
        boolean z;
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        try {
            try {
                Method declaredMethod = BluetoothAdapter.class.getDeclaredMethod("getConnectionState", new Class[0]);
                declaredMethod.setAccessible(true);
                try {
                    set = defaultAdapter.getBondedDevices();
                } catch (SecurityException e2) {
                    LogUtil.e("BluetoothDeviceReconnectScanUtil", "getDeviceList SecurityException", bll.a(e2));
                    set = null;
                }
                if (set == null) {
                    LogUtil.a("BluetoothDeviceReconnectScanUtil", "getConnectedDeviceList() devices is null.");
                    return;
                }
                for (BluetoothDevice bluetoothDevice : set) {
                    Method declaredMethod2 = BluetoothDevice.class.getDeclaredMethod("isConnected", new Class[0]);
                    declaredMethod.setAccessible(true);
                    Object invoke = declaredMethod2.invoke(bluetoothDevice, new Object[0]);
                    if (invoke instanceof Boolean) {
                        z = ((Boolean) invoke).booleanValue();
                    } else {
                        LogUtil.a("BluetoothDeviceReconnectScanUtil", "connectState is not Boolean");
                        z = false;
                    }
                    LogUtil.d("BluetoothDeviceReconnectScanUtil", "getConnectDevice:", bluetoothDevice.getName(), ";connect status:", Boolean.valueOf(z));
                    if (z) {
                        BluetoothDeviceNode bluetoothDeviceNode = new BluetoothDeviceNode();
                        bluetoothDeviceNode.setBtDevice(bluetoothDevice);
                        arrayList.add(bluetoothDeviceNode);
                    }
                }
            } catch (SecurityException unused) {
                LogUtil.e("0xA0200001", HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BluetoothDeviceReconnectScanUtil", "btDevicePair with exception SecurityException");
            }
        } catch (IllegalAccessException unused2) {
            LogUtil.e("0xA0200001", HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BluetoothDeviceReconnectScanUtil", "btDevicePair with exception IllegalAccessException");
        } catch (IllegalArgumentException unused3) {
            LogUtil.e("0xA0200001", HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BluetoothDeviceReconnectScanUtil", "btDevicePair with exception IllegalArgumentException");
        } catch (NoSuchMethodException unused4) {
            LogUtil.e("0xA0200001", HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BluetoothDeviceReconnectScanUtil", "btDevicePair with exception NoSuchMethodException");
        } catch (InvocationTargetException unused5) {
            LogUtil.e("0xA0200001", HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BluetoothDeviceReconnectScanUtil", "btDevicePair with exception InvocationTargetException");
        }
    }

    static class c implements Handler.Callback {
        private WeakReference<iys> c;

        c(iys iysVar) {
            this.c = new WeakReference<>(iysVar);
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            if (message == null) {
                return false;
            }
            if (message.what == 1) {
                WeakReference<iys> weakReference = this.c;
                if (weakReference == null) {
                    return false;
                }
                iys iysVar = weakReference.get();
                if (iysVar != null) {
                    long currentTimeMillis = System.currentTimeMillis() - iysVar.p;
                    if (currentTimeMillis > 15000) {
                        LogUtil.c("BluetoothDeviceReconnectScanUtil", "ScanHandler handleMessage offset:", Long.valueOf(currentTimeMillis));
                        iysVar.d(2);
                        return true;
                    }
                    Message obtain = Message.obtain();
                    obtain.what = 1;
                    if (iysVar.m != null) {
                        iysVar.m.sendEmptyMessage(obtain.what, 1000L);
                    }
                    return true;
                }
                LogUtil.a("BluetoothDeviceReconnectScanUtil", "handleMessage scanUtil is null");
                return false;
            }
            LogUtil.a("BluetoothDeviceReconnectScanUtil", "handleMessage default");
            return false;
        }
    }
}
