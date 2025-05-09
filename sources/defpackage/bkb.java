package defpackage;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanRecord;
import android.bluetooth.le.ScanResult;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import androidx.core.app.NotificationCompat;
import com.huawei.devicesdk.entity.DeviceInfo;
import com.huawei.devicesdk.reconnect.BleReconnectScanCallback;
import com.huawei.devicesdk.reconnect.PeriodScanService;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.handler.ExtendHandler;
import com.huawei.haf.handler.HandlerCenter;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes3.dex */
public class bkb {
    private static final Object d = new Object();

    /* renamed from: a, reason: collision with root package name */
    private d f418a;
    private BluetoothAdapter b;
    private BluetoothManager c;
    private boolean e;
    private PendingIntent f;
    private ScanCallback g;
    private ExtendHandler h;
    private BleReconnectScanCallback i;

    private boolean c(int i) {
        return (i == 1 || i == 2) ? false : true;
    }

    private bkb() {
        this.f418a = new d(this);
        this.e = false;
        this.g = new ScanCallback() { // from class: bkb.2
            @Override // android.bluetooth.le.ScanCallback
            public void onScanResult(int i, ScanResult scanResult) {
                super.onScanResult(i, scanResult);
                ReleaseLogUtil.b("DEVMGR_BleReconnectManager", "high version onScanResult");
                sb_(scanResult);
            }

            @Override // android.bluetooth.le.ScanCallback
            public void onBatchScanResults(List<ScanResult> list) {
                super.onBatchScanResults(list);
                LogUtil.c("BleReconnectManager", "high version onBatchScanResults");
                if (list == null) {
                    return;
                }
                Iterator<ScanResult> it = list.iterator();
                while (it.hasNext()) {
                    sb_(it.next());
                }
            }

            @Override // android.bluetooth.le.ScanCallback
            public void onScanFailed(int i) {
                ReleaseLogUtil.b("DEVMGR_BleReconnectManager", "onScanFailed: ", Integer.valueOf(i));
                super.onScanFailed(i);
                bkb.this.m();
            }

            private void sb_(ScanResult scanResult) {
                if (scanResult == null) {
                    LogUtil.a("BleReconnectManager", "reportDiscoveryDevice error. scan result is invalid.");
                    return;
                }
                try {
                    Object[] objArr = new Object[2];
                    objArr[0] = "high version onDeviceDiscovered ";
                    objArr[1] = scanResult.getDevice() != null ? scanResult.getDevice().getName() : Integer.valueOf(scanResult.getRssi());
                    LogUtil.c("BleReconnectManager", objArr);
                    ScanRecord scanRecord = scanResult.getScanRecord();
                    bkb.this.rZ_(scanResult.getDevice(), scanResult.getRssi(), scanRecord == null ? null : scanRecord.getBytes());
                } catch (SecurityException e) {
                    LogUtil.e("BleReconnectManager", "mBleScanCallback SecurityException ", ExceptionUtils.d(e));
                }
            }
        };
        Object systemService = BaseApplication.e().getSystemService("bluetooth");
        if (systemService instanceof BluetoothManager) {
            this.c = (BluetoothManager) systemService;
        } else {
            LogUtil.a("BleReconnectManager", "bluetoothManager is not BluetoothManager");
        }
        this.c = (BluetoothManager) BaseApplication.e().getSystemService("bluetooth");
        this.b = BluetoothAdapter.getDefaultAdapter();
        l();
    }

    public static bkb e() {
        return a.b;
    }

    public boolean b() {
        Iterator<Map.Entry<String, DeviceInfo>> it = bjy.d().c().entrySet().iterator();
        while (it.hasNext()) {
            DeviceInfo value = it.next().getValue();
            if (c(value.getDeviceConnectState())) {
                LogUtil.c("BleReconnectManager", "find need to reconnect device.", blt.a(value));
                return true;
            }
        }
        ReleaseLogUtil.b("DEVMGR_BleReconnectManager", "not found need to reconnect device.");
        return false;
    }

    public String a(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.c("BleReconnectManager", "getGattTimeoutDeviceName deviceMac is empty.");
            return "";
        }
        Iterator<Map.Entry<String, DeviceInfo>> it = bjy.d().c().entrySet().iterator();
        while (it.hasNext()) {
            DeviceInfo value = it.next().getValue();
            if (str.equals(value.getDeviceMac())) {
                return value.getDeviceName();
            }
        }
        return "";
    }

    public void e(BleReconnectScanCallback bleReconnectScanCallback, int i) {
        if (Build.VERSION.SDK_INT >= 33 && !bma.a(BaseApplication.e(), new String[]{"android.permission.BLUETOOTH_CONNECT"})) {
            LogUtil.a("BleReconnectManager", "startReconnect failed, no permission");
            return;
        }
        this.i = bleReconnectScanCallback;
        ReleaseLogUtil.b("DEVMGR_BleReconnectManager", "Enter startReconnect.");
        if (2 != i) {
            bkc.b();
        }
        if (b()) {
            a();
            f();
        }
    }

    public void g() {
        a();
    }

    private boolean rY_(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null || TextUtils.isEmpty(bluetoothDevice.getAddress())) {
            LogUtil.a("BleReconnectManager", "isNeedReconnect device is null or address is null.");
            return false;
        }
        ReleaseLogUtil.b("DEVMGR_BleReconnectManager", "isNeedReconnect", blt.a(bluetoothDevice.getAddress()));
        Iterator<Map.Entry<String, DeviceInfo>> it = bjy.d().c().entrySet().iterator();
        while (it.hasNext()) {
            DeviceInfo value = it.next().getValue();
            if (bluetoothDevice.getAddress().equalsIgnoreCase(value.getDeviceMac())) {
                return c(value.getDeviceConnectState());
            }
        }
        return false;
    }

    private Map<String, DeviceInfo> o() {
        HashMap hashMap = new HashMap(2);
        Iterator<Map.Entry<String, DeviceInfo>> it = bjy.d().c().entrySet().iterator();
        while (it.hasNext()) {
            DeviceInfo value = it.next().getValue();
            if (c(value.getDeviceConnectState())) {
                hashMap.put(value.getDeviceMac(), value);
            }
        }
        ReleaseLogUtil.b("DEVMGR_BleReconnectManager", "getReconnectDevice. size:", Integer.valueOf(hashMap.size()));
        return hashMap;
    }

    public void f() {
        ReleaseLogUtil.b("DEVMGR_BleReconnectManager", "Enter startScanDiscovery");
        if (!bks.e()) {
            ReleaseLogUtil.a("DEVMGR_BleReconnectManager", "startDiscoverBleDevice sdk version is not support.");
        } else {
            h();
        }
    }

    private void h() {
        boolean z;
        n();
        try {
            z = k();
        } catch (IllegalStateException unused) {
            ReleaseLogUtil.c("DEVMGR_BleReconnectManager", "discoverBleDevice IllegalStateException version :", Integer.valueOf(Build.VERSION.SDK_INT));
            z = false;
        }
        LogUtil.c("BleReconnectManager", "startBleScan result: ", Boolean.valueOf(z), " isSystemSupportFreezing: ", Boolean.valueOf(bky.g()));
        if (!z) {
            m();
        } else {
            if (this.e) {
                return;
            }
            b(1, 15000L);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0096  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean k() {
        /*
            r9 = this;
            java.lang.String r0 = "BleReconnectManager"
            java.lang.String r1 = "Enter startBleScan"
            java.lang.Object[] r1 = new java.lang.Object[]{r1}
            java.lang.String r2 = "DEVMGR_BleReconnectManager"
            health.compact.a.ReleaseLogUtil.b(r2, r1)
            android.bluetooth.BluetoothAdapter r1 = r9.b
            android.bluetooth.le.BluetoothLeScanner r1 = r1.getBluetoothLeScanner()
            java.util.ArrayList r3 = new java.util.ArrayList
            r4 = 2
            r3.<init>(r4)
            java.util.Map r5 = r9.o()
            java.util.Collection r5 = r5.values()
            java.util.Iterator r5 = r5.iterator()
        L25:
            boolean r6 = r5.hasNext()
            if (r6 == 0) goto L52
            java.lang.Object r6 = r5.next()
            com.huawei.devicesdk.entity.DeviceInfo r6 = (com.huawei.devicesdk.entity.DeviceInfo) r6
            if (r6 == 0) goto L25
            java.lang.String r7 = r6.getDeviceMac()
            boolean r7 = android.text.TextUtils.isEmpty(r7)
            if (r7 == 0) goto L3e
            goto L25
        L3e:
            android.bluetooth.le.ScanFilter$Builder r7 = new android.bluetooth.le.ScanFilter$Builder
            r7.<init>()
            java.lang.String r6 = r6.getDeviceMac()
            r7.setDeviceAddress(r6)
            android.bluetooth.le.ScanFilter r6 = r7.build()
            r3.add(r6)
            goto L25
        L52:
            r5 = 1
            if (r1 == 0) goto Lb9
            boolean r6 = defpackage.bky.c()
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r6)
            java.lang.String r7 = "startBleScan isEmui110: "
            java.lang.Object[] r6 = new java.lang.Object[]{r7, r6}
            health.compact.a.ReleaseLogUtil.b(r2, r6)
            r6 = 0
            java.lang.String r7 = "02:00:00"
            java.lang.String r8 = "05:00:00"
            boolean r7 = defpackage.bmg.a(r7, r8)     // Catch: java.text.ParseException -> L86
            if (r7 == 0) goto L74
            r7 = r6
            goto L75
        L74:
            r7 = r4
        L75:
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch: java.text.ParseException -> L85
            java.lang.String r8 = "scanMode: "
            r4[r6] = r8     // Catch: java.text.ParseException -> L85
            java.lang.Integer r8 = java.lang.Integer.valueOf(r7)     // Catch: java.text.ParseException -> L85
            r4[r5] = r8     // Catch: java.text.ParseException -> L85
            health.compact.a.LogUtil.c(r0, r4)     // Catch: java.text.ParseException -> L85
            goto L90
        L85:
            r4 = r7
        L86:
            java.lang.String r7 = "Time ParseException occur."
            java.lang.Object[] r7 = new java.lang.Object[]{r7}
            health.compact.a.LogUtil.e(r0, r7)
            r7 = r4
        L90:
            boolean r4 = defpackage.bky.c()
            if (r4 == 0) goto L97
            r7 = r6
        L97:
            java.lang.String r4 = "final scanMode: "
            java.lang.Integer r8 = java.lang.Integer.valueOf(r7)
            java.lang.Object[] r4 = new java.lang.Object[]{r4, r8}
            health.compact.a.ReleaseLogUtil.b(r2, r4)
            java.lang.String r2 = "00:00:00"
            java.lang.String r4 = "06:00:00"
            boolean r6 = defpackage.bmg.a(r2, r4)     // Catch: java.text.ParseException -> Lad
            goto Lb6
        Lad:
            java.lang.String r2 = "isValidDateSix ParseException occur."
            java.lang.Object[] r2 = new java.lang.Object[]{r2}
            health.compact.a.LogUtil.e(r0, r2)
        Lb6:
            r9.sa_(r1, r3, r7, r6)
        Lb9:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.bkb.k():boolean");
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x000a, code lost:
    
        if (defpackage.bky.g() == false) goto L6;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void sa_(android.bluetooth.le.BluetoothLeScanner r5, java.util.List<android.bluetooth.le.ScanFilter> r6, int r7, boolean r8) {
        /*
            r4 = this;
            r0 = 0
            r1 = 1
            java.lang.String r2 = "DEVMGR_BleReconnectManager"
            if (r8 == 0) goto Lc
            boolean r8 = defpackage.bky.g()     // Catch: java.lang.NullPointerException -> L6c java.lang.SecurityException -> L6e
            if (r8 != 0) goto L16
        Lc:
            boolean r8 = r4.e     // Catch: java.lang.NullPointerException -> L6c java.lang.SecurityException -> L6e
            if (r8 == 0) goto L50
            boolean r8 = defpackage.bky.g()     // Catch: java.lang.NullPointerException -> L6c java.lang.SecurityException -> L6e
            if (r8 == 0) goto L50
        L16:
            java.lang.Object[] r8 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> L34 java.lang.NullPointerException -> L6c java.lang.SecurityException -> L6e
            java.lang.String r3 = "scanDevice harmony 3.0 start 1003."
            r8[r0] = r3     // Catch: java.lang.Throwable -> L34 java.lang.NullPointerException -> L6c java.lang.SecurityException -> L6e
            health.compact.a.ReleaseLogUtil.b(r2, r8)     // Catch: java.lang.Throwable -> L34 java.lang.NullPointerException -> L6c java.lang.SecurityException -> L6e
            android.bluetooth.le.ScanSettings$Builder r8 = new android.bluetooth.le.ScanSettings$Builder     // Catch: java.lang.Throwable -> L34 java.lang.NullPointerException -> L6c java.lang.SecurityException -> L6e
            r8.<init>()     // Catch: java.lang.Throwable -> L34 java.lang.NullPointerException -> L6c java.lang.SecurityException -> L6e
            r3 = 1003(0x3eb, float:1.406E-42)
            android.bluetooth.le.ScanSettings$Builder r8 = r8.setScanMode(r3)     // Catch: java.lang.Throwable -> L34 java.lang.NullPointerException -> L6c java.lang.SecurityException -> L6e
            android.bluetooth.le.ScanSettings r8 = r8.build()     // Catch: java.lang.Throwable -> L34 java.lang.NullPointerException -> L6c java.lang.SecurityException -> L6e
            android.bluetooth.le.ScanCallback r3 = r4.g     // Catch: java.lang.Throwable -> L34 java.lang.NullPointerException -> L6c java.lang.SecurityException -> L6e
            r5.startScan(r6, r8, r3)     // Catch: java.lang.Throwable -> L34 java.lang.NullPointerException -> L6c java.lang.SecurityException -> L6e
            goto L7d
        L34:
            java.lang.Object[] r8 = new java.lang.Object[r1]     // Catch: java.lang.NullPointerException -> L6c java.lang.SecurityException -> L6e
            java.lang.String r1 = "IllegalStateException or IllegalArgumentException 3.0 start."
            r8[r0] = r1     // Catch: java.lang.NullPointerException -> L6c java.lang.SecurityException -> L6e
            health.compact.a.ReleaseLogUtil.c(r2, r8)     // Catch: java.lang.NullPointerException -> L6c java.lang.SecurityException -> L6e
            android.bluetooth.le.ScanSettings$Builder r8 = new android.bluetooth.le.ScanSettings$Builder     // Catch: java.lang.NullPointerException -> L6c java.lang.SecurityException -> L6e
            r8.<init>()     // Catch: java.lang.NullPointerException -> L6c java.lang.SecurityException -> L6e
            android.bluetooth.le.ScanSettings$Builder r7 = r8.setScanMode(r7)     // Catch: java.lang.NullPointerException -> L6c java.lang.SecurityException -> L6e
            android.bluetooth.le.ScanSettings r7 = r7.build()     // Catch: java.lang.NullPointerException -> L6c java.lang.SecurityException -> L6e
            android.bluetooth.le.ScanCallback r8 = r4.g     // Catch: java.lang.NullPointerException -> L6c java.lang.SecurityException -> L6e
            r5.startScan(r6, r7, r8)     // Catch: java.lang.NullPointerException -> L6c java.lang.SecurityException -> L6e
            goto L7d
        L50:
            java.lang.Object[] r8 = new java.lang.Object[r1]     // Catch: java.lang.NullPointerException -> L6c java.lang.SecurityException -> L6e
            java.lang.String r1 = "scan device default logic."
            r8[r0] = r1     // Catch: java.lang.NullPointerException -> L6c java.lang.SecurityException -> L6e
            health.compact.a.ReleaseLogUtil.b(r2, r8)     // Catch: java.lang.NullPointerException -> L6c java.lang.SecurityException -> L6e
            android.bluetooth.le.ScanSettings$Builder r8 = new android.bluetooth.le.ScanSettings$Builder     // Catch: java.lang.NullPointerException -> L6c java.lang.SecurityException -> L6e
            r8.<init>()     // Catch: java.lang.NullPointerException -> L6c java.lang.SecurityException -> L6e
            android.bluetooth.le.ScanSettings$Builder r7 = r8.setScanMode(r7)     // Catch: java.lang.NullPointerException -> L6c java.lang.SecurityException -> L6e
            android.bluetooth.le.ScanSettings r7 = r7.build()     // Catch: java.lang.NullPointerException -> L6c java.lang.SecurityException -> L6e
            android.bluetooth.le.ScanCallback r8 = r4.g     // Catch: java.lang.NullPointerException -> L6c java.lang.SecurityException -> L6e
            r5.startScan(r6, r7, r8)     // Catch: java.lang.NullPointerException -> L6c java.lang.SecurityException -> L6e
            goto L7d
        L6c:
            r5 = move-exception
            goto L6f
        L6e:
            r5 = move-exception
        L6f:
            java.lang.String r6 = "startScanBleDevice SecurityException or NullPointerException : "
            java.lang.String r5 = com.huawei.haf.common.exception.ExceptionUtils.d(r5)
            java.lang.Object[] r5 = new java.lang.Object[]{r6, r5}
            health.compact.a.ReleaseLogUtil.b(r2, r5)
        L7d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.bkb.sa_(android.bluetooth.le.BluetoothLeScanner, java.util.List, int, boolean):void");
    }

    public void a() {
        LogUtil.c("BleReconnectManager", "Enter cancelBleReconnect");
        p();
        if (this.h == null) {
            ReleaseLogUtil.a("DEVMGR_BleReconnectManager", "mScanHandler is null");
            l();
        }
        if (this.h == null) {
            ReleaseLogUtil.a("DEVMGR_BleReconnectManager", "Failed to initialize mScanHandler");
        } else if (bky.g()) {
            a(2);
        } else {
            this.h.removeMessages(2);
        }
        a(1);
    }

    public void c() {
        ReleaseLogUtil.b("DEVMGR_BleReconnectManager", "Enter nextBleReconnect");
        b(2, -1L);
    }

    void d(long j) {
        ReleaseLogUtil.b("DEVMGR_BleReconnectManager", "Enter nextBleReconnect with period:", Long.valueOf(j));
        a();
        bkc.b();
        b(2, j);
    }

    private void b(int i, long j) {
        Context e = BaseApplication.e();
        if (j == -1) {
            j = bkc.e();
        }
        ReleaseLogUtil.b("DEVMGR_BleReconnectManager", "sendPendingIntentTask type: ", Integer.valueOf(i), ", delay: ", Long.valueOf(j));
        bmw.e(100058, "", String.valueOf(i), String.valueOf(j));
        if (i == 1) {
            Object systemService = e.getSystemService(NotificationCompat.CATEGORY_ALARM);
            if (systemService instanceof AlarmManager) {
                AlarmManager alarmManager = (AlarmManager) systemService;
                PendingIntent pendingIntent = this.f;
                if (pendingIntent != null) {
                    alarmManager.cancel(pendingIntent);
                    LogUtil.c("BleReconnectManager", "alarmTimeStop alarmManager cancel");
                }
                Intent intent = new Intent();
                intent.putExtra("type", i);
                intent.setClass(e, PeriodScanService.class);
                this.f = PendingIntent.getService(e, i, intent, 335544320);
                try {
                    alarmManager.setExactAndAllowWhileIdle(0, System.currentTimeMillis() + j, this.f);
                    return;
                } catch (SecurityException e2) {
                    ReleaseLogUtil.a("DEVMGR_BleReconnectManager", "wearLink sendPendingIntentTask exception ", e2.getMessage());
                    return;
                }
            }
            LogUtil.a("BleReconnectManager", "alarmManagerObject is not AlarmManager");
            return;
        }
        if (bky.g()) {
            LogUtil.c("BleReconnectManager", "harmony 3.0 free  start.");
            b(i, e, j);
        } else {
            LogUtil.c("BleReconnectManager", "previous start scan logic.");
            c(j);
        }
    }

    private void c(long j) {
        if (this.h == null) {
            ReleaseLogUtil.a("DEVMGR_BleReconnectManager", "mScanHandler is null");
            l();
        }
        ExtendHandler extendHandler = this.h;
        if (extendHandler == null) {
            ReleaseLogUtil.c("DEVMGR_BleReconnectManager", "Failed to initialize mScanHandler");
        } else {
            extendHandler.sendEmptyMessage(2, j);
        }
    }

    private void b(int i, Context context, long j) {
        Object systemService = context.getSystemService(NotificationCompat.CATEGORY_ALARM);
        if (systemService instanceof AlarmManager) {
            AlarmManager alarmManager = (AlarmManager) systemService;
            LogUtil.c("BleReconnectManager", "alarmTimeScan set alarmClock");
            PendingIntent pendingIntent = this.f;
            if (pendingIntent != null) {
                alarmManager.cancel(pendingIntent);
                LogUtil.c("BleReconnectManager", "alarmTimeScan alarmManager cancel");
            }
            Intent intent = new Intent();
            intent.putExtra("type", i);
            intent.setClass(context, PeriodScanService.class);
            this.f = PendingIntent.getService(context, i, intent, 335544320);
            try {
                alarmManager.setExactAndAllowWhileIdle(0, System.currentTimeMillis() + j, this.f);
                return;
            } catch (SecurityException e) {
                ReleaseLogUtil.a("DEVMGR_BleReconnectManager", "wearLink alarmTimeScan exception ", e.getMessage());
                return;
            }
        }
        LogUtil.a("BleReconnectManager", "alarmManagerObject is not AlarmManager");
    }

    private void a(int i) {
        Object systemService;
        Context e = BaseApplication.e();
        if (e == null || (systemService = e.getSystemService(NotificationCompat.CATEGORY_ALARM)) == null || !(systemService instanceof AlarmManager)) {
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("type", i);
        intent.setClass(e, PeriodScanService.class);
        ((AlarmManager) systemService).cancel(PendingIntent.getService(e, i, intent, 335544320));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        LogUtil.c("BleReconnectManager", "Enter scan failed.");
        a();
        c();
    }

    private void p() {
        LogUtil.c("BleReconnectManager", "stopBleScan");
        try {
            BluetoothLeScanner bluetoothLeScanner = this.b.getBluetoothLeScanner();
            if (bluetoothLeScanner != null) {
                bluetoothLeScanner.stopScan(this.g);
            }
        } catch (IllegalStateException e) {
            LogUtil.e("BleReconnectManager", "stopLeScan IllegalStateException: ", bll.a(e));
        } catch (NullPointerException unused) {
            LogUtil.e("BleReconnectManager", "stopLeScan NullPointerException:");
        } catch (SecurityException e2) {
            LogUtil.e("BleReconnectManager", "stopLeScan SecurityException: ", bll.a(e2));
        }
    }

    private void n() {
        LogUtil.c("BleReconnectManager", "Enter reportConnectedDevice");
        List<BluetoothDevice> j = j();
        synchronized (d) {
            Iterator<BluetoothDevice> it = j.iterator();
            while (it.hasNext()) {
                rZ_(it.next(), 0, null);
            }
        }
    }

    private List<BluetoothDevice> j() {
        Set<BluetoothDevice> set;
        boolean z;
        ArrayList arrayList = new ArrayList(16);
        try {
            try {
                BluetoothManager bluetoothManager = this.c;
                if (bluetoothManager != null) {
                    arrayList.addAll(bluetoothManager.getConnectedDevices(7));
                }
                try {
                    set = BluetoothAdapter.getDefaultAdapter().getBondedDevices();
                } catch (SecurityException e) {
                    LogUtil.e("BleReconnectManager", "getConnectedDeviceList SecurityException", bll.a(e));
                    set = null;
                }
            } catch (SecurityException e2) {
                LogUtil.e("BleReconnectManager", "getConnectedDeviceList with exception SecurityException: ", bll.a(e2));
            }
        } catch (IllegalAccessException e3) {
            LogUtil.e("BleReconnectManager", "getConnectedDeviceList with exception IllegalAccessException: ", bll.a(e3));
        } catch (IllegalArgumentException e4) {
            LogUtil.e("BleReconnectManager", "getConnectedDeviceList with exception IllegalArgumentException: ", bll.a(e4));
        } catch (NoSuchMethodException e5) {
            LogUtil.e("BleReconnectManager", "getConnectedDeviceList with exception NoSuchMethodException: ", bll.a(e5));
        } catch (InvocationTargetException e6) {
            LogUtil.e("BleReconnectManager", "getConnectedDeviceList with exception InvocationTargetException: ", bll.a(e6));
        }
        if (set == null) {
            LogUtil.a("BleReconnectManager", "get bonded devices is null.");
            return arrayList;
        }
        Method declaredMethod = BluetoothDevice.class.getDeclaredMethod("isConnected", new Class[0]);
        declaredMethod.setAccessible(true);
        for (BluetoothDevice bluetoothDevice : set) {
            Object invoke = declaredMethod.invoke(bluetoothDevice, new Object[0]);
            if (invoke instanceof Boolean) {
                z = ((Boolean) invoke).booleanValue();
            } else {
                LogUtil.a("BleReconnectManager", "connectState is not Boolean");
                z = false;
            }
            LogUtil.d("BleReconnectManager", "getBondDevice:", bluetoothDevice.getName(), ";connect status:", Boolean.valueOf(z));
            if (z) {
                arrayList.add(bluetoothDevice);
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void rZ_(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
        if (this.i == null || !rY_(bluetoothDevice)) {
            return;
        }
        this.i.onLeScan(bluetoothDevice, i, bArr);
        LogUtil.c("BleReconnectManager", "reportDiscoveryDevice mIsAllTimeScan: ", Boolean.valueOf(this.e));
        if (this.e) {
            i();
        }
    }

    public void i() {
        d(false);
        a();
    }

    public void d(boolean z) {
        this.e = z;
    }

    public boolean d() {
        return this.e;
    }

    static class a {
        private static bkb b = new bkb();
    }

    static class d implements Handler.Callback {
        private WeakReference<bkb> e;

        d(bkb bkbVar) {
            this.e = new WeakReference<>(bkbVar);
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            if (message.what == 2) {
                WeakReference<bkb> weakReference = this.e;
                if (weakReference == null) {
                    return false;
                }
                if (weakReference.get() == null) {
                    LogUtil.a("BleReconnectManager", "handleMessage bleReconnectManager is null");
                    return false;
                }
                e();
                return true;
            }
            ReleaseLogUtil.a("DEVMGR_BleReconnectManager", "handleMessage default");
            return false;
        }

        private void e() {
            ReleaseLogUtil.b("DEVMGR_BleReconnectManager", "onStartScan");
            bkb.e().a();
            if (bkb.e().b()) {
                bkb.e().f();
            }
        }
    }

    private void l() {
        if (this.h == null) {
            this.h = HandlerCenter.yt_(this.f418a, "BleReconnectManager");
        }
    }
}
