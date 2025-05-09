package com.huawei.health.manager.reconnect;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanRecord;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Message;
import android.text.TextUtils;
import androidx.core.app.NotificationCompat;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.handler.BaseHandlerCallback;
import com.huawei.haf.handler.ExtendHandler;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.EnvironmentInfo;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
class BleReconnectManager {
    private static final Object d = new Object();

    /* renamed from: a, reason: collision with root package name */
    private ScanHandler f2793a;
    private BluetoothManager b;
    private ScanCallback c;
    private BluetoothAdapter e;
    private BleReconnectScanCallback h;
    private ExtendHandler i;

    private BleReconnectManager() {
        this.f2793a = new ScanHandler(this);
        this.c = new ScanCallback() { // from class: com.huawei.health.manager.reconnect.BleReconnectManager.1
            @Override // android.bluetooth.le.ScanCallback
            public void onScanResult(int i, ScanResult scanResult) {
                super.onScanResult(i, scanResult);
                ReleaseLogUtil.e("DEVMGR_BleReconnectManager", "high version onScanResult");
                alf_(scanResult);
            }

            @Override // android.bluetooth.le.ScanCallback
            public void onBatchScanResults(List<ScanResult> list) {
                super.onBatchScanResults(list);
                LogUtil.a("BleReconnectManager", "high version onBatchScanResults");
                if (list == null) {
                    return;
                }
                Iterator<ScanResult> it = list.iterator();
                while (it.hasNext()) {
                    alf_(it.next());
                }
            }

            @Override // android.bluetooth.le.ScanCallback
            public void onScanFailed(int i) {
                ReleaseLogUtil.e("DEVMGR_BleReconnectManager", "onScanFailed: ", Integer.valueOf(i));
                super.onScanFailed(i);
                BleReconnectManager.this.l();
            }

            private void alf_(ScanResult scanResult) {
                if (scanResult == null) {
                    LogUtil.h("BleReconnectManager", "reportDiscoveryDevice error. scan result is invalid.");
                    return;
                }
                Object[] objArr = new Object[2];
                objArr[0] = "high version onDeviceDiscovered ";
                objArr[1] = scanResult.getDevice() != null ? scanResult.getDevice().getName() : Integer.valueOf(scanResult.getRssi());
                LogUtil.a("BleReconnectManager", objArr);
                ScanRecord scanRecord = scanResult.getScanRecord();
                BleReconnectManager.this.ald_(scanResult.getDevice(), scanResult.getRssi(), scanRecord == null ? null : scanRecord.getBytes());
            }
        };
        Object systemService = BaseApplication.getContext().getSystemService("bluetooth");
        if (systemService instanceof BluetoothManager) {
            this.b = (BluetoothManager) systemService;
        } else {
            LogUtil.h("BleReconnectManager", "bluetoothManager is not BluetoothManager");
        }
        this.b = (BluetoothManager) BaseApplication.getContext().getSystemService("bluetooth");
        this.e = BluetoothAdapter.getDefaultAdapter();
        h();
    }

    static BleReconnectManager b() {
        return InstanceHolder.f2794a;
    }

    boolean c() {
        if (!ReconnectManager.c().b().isEmpty()) {
            LogUtil.a("BleReconnectManager", "find need to reconnect device.");
            return true;
        }
        ReleaseLogUtil.e("DEVMGR_BleReconnectManager", "not found need to reconnect device.");
        return false;
    }

    void c(BleReconnectScanCallback bleReconnectScanCallback) {
        if (Build.VERSION.SDK_INT >= 33 && !PermissionUtil.e(BaseApplication.getContext(), new String[]{"android.permission.BLUETOOTH_CONNECT"})) {
            LogUtil.h("BleReconnectManager", "plugin deamon startReconnect failed, no permission");
            return;
        }
        this.h = bleReconnectScanCallback;
        ReleaseLogUtil.e("DEVMGR_BleReconnectManager", "Enter startReconnect.");
        ScanTimeUtil.c();
        if (c()) {
            d();
            a();
        }
    }

    void j() {
        d();
    }

    private boolean alc_(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null || TextUtils.isEmpty(bluetoothDevice.getAddress())) {
            LogUtil.h("BleReconnectManager", "isNeedReconnect device is null or address is null.");
            return false;
        }
        ReleaseLogUtil.e("DEVMGR_BleReconnectManager", "isNeedReconnect", CommonUtil.l(bluetoothDevice.getAddress()));
        Iterator<Map.Entry<String, Map<String, String>>> it = ReconnectManager.c().b().entrySet().iterator();
        while (it.hasNext()) {
            if (bluetoothDevice.getAddress().equalsIgnoreCase(it.next().getKey())) {
                return true;
            }
        }
        return false;
    }

    void a() {
        ReleaseLogUtil.e("DEVMGR_BleReconnectManager", "Enter startScanDiscovery");
        if (!BaseApplication.getContext().getPackageManager().hasSystemFeature("android.hardware.bluetooth_le")) {
            ReleaseLogUtil.d("DEVMGR_BleReconnectManager", "startDiscoverBleDevice sdk version is not support.");
        } else {
            i();
        }
    }

    private void i() {
        boolean z;
        g();
        try {
            z = m();
        } catch (IllegalStateException unused) {
            ReleaseLogUtil.c("DEVMGR_BleReconnectManager", "discoverBleDevice IllegalStateException version :", Integer.valueOf(Build.VERSION.SDK_INT));
            z = false;
        }
        LogUtil.a("BleReconnectManager", "startBleScan result: ", Boolean.valueOf(z));
        if (!z) {
            l();
        } else {
            e(1, 15000L);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x00a1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean m() {
        /*
            r9 = this;
            java.lang.String r0 = "BleReconnectManager"
            java.lang.String r1 = "Enter startBleScan"
            java.lang.Object[] r1 = new java.lang.Object[]{r1}
            java.lang.String r2 = "DEVMGR_BleReconnectManager"
            health.compact.a.hwlogsmodel.ReleaseLogUtil.e(r2, r1)
            com.huawei.health.manager.reconnect.ReconnectManager r1 = com.huawei.health.manager.reconnect.ReconnectManager.c()
            java.util.Map r1 = r1.b()
            boolean r3 = r1.isEmpty()
            r4 = 1
            if (r3 == 0) goto L26
            java.lang.String r0 = "BleReconnectDevices isEmpty"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            health.compact.a.hwlogsmodel.ReleaseLogUtil.e(r2, r0)
            return r4
        L26:
            android.bluetooth.BluetoothAdapter r3 = r9.e
            android.bluetooth.le.BluetoothLeScanner r3 = r3.getBluetoothLeScanner()
            java.util.ArrayList r5 = new java.util.ArrayList
            r6 = 2
            r5.<init>(r6)
            java.util.Set r1 = r1.keySet()
            java.util.Iterator r1 = r1.iterator()
        L3a:
            boolean r7 = r1.hasNext()
            if (r7 == 0) goto L5d
            java.lang.Object r7 = r1.next()
            java.lang.String r7 = (java.lang.String) r7
            boolean r8 = android.text.TextUtils.isEmpty(r7)
            if (r8 == 0) goto L4d
            goto L3a
        L4d:
            android.bluetooth.le.ScanFilter$Builder r8 = new android.bluetooth.le.ScanFilter$Builder
            r8.<init>()
            r8.setDeviceAddress(r7)
            android.bluetooth.le.ScanFilter r7 = r8.build()
            r5.add(r7)
            goto L3a
        L5d:
            if (r3 == 0) goto Lc4
            boolean r1 = health.compact.a.CommonUtil.av()
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)
            java.lang.String r7 = "startBleScan isEmui110: "
            java.lang.Object[] r1 = new java.lang.Object[]{r7, r1}
            health.compact.a.hwlogsmodel.ReleaseLogUtil.e(r2, r1)
            r1 = 0
            java.lang.String r7 = "02:00:00"
            java.lang.String r8 = "05:00:00"
            boolean r7 = com.huawei.health.manager.reconnect.TimeDateFormatUtils.a(r7, r8)     // Catch: java.text.ParseException -> L91
            if (r7 == 0) goto L7e
            r7 = r1
            goto L7f
        L7e:
            r7 = r6
        L7f:
            java.lang.Object[] r6 = new java.lang.Object[r6]     // Catch: java.text.ParseException -> L90
            java.lang.String r8 = "scanMode: "
            r6[r1] = r8     // Catch: java.text.ParseException -> L90
            java.lang.Integer r8 = java.lang.Integer.valueOf(r7)     // Catch: java.text.ParseException -> L90
            r6[r4] = r8     // Catch: java.text.ParseException -> L90
            com.huawei.hwlogsmodel.LogUtil.a(r0, r6)     // Catch: java.text.ParseException -> L90
            goto L9b
        L90:
            r6 = r7
        L91:
            java.lang.String r7 = "Time ParseException occur."
            java.lang.Object[] r7 = new java.lang.Object[]{r7}
            com.huawei.hwlogsmodel.LogUtil.b(r0, r7)
            r7 = r6
        L9b:
            boolean r6 = health.compact.a.CommonUtil.av()
            if (r6 == 0) goto La2
            r7 = r1
        La2:
            java.lang.String r6 = "final scanMode: "
            java.lang.Integer r8 = java.lang.Integer.valueOf(r7)
            java.lang.Object[] r6 = new java.lang.Object[]{r6, r8}
            health.compact.a.hwlogsmodel.ReleaseLogUtil.e(r2, r6)
            java.lang.String r2 = "00:00:00"
            java.lang.String r6 = "06:00:00"
            boolean r1 = com.huawei.health.manager.reconnect.TimeDateFormatUtils.a(r2, r6)     // Catch: java.text.ParseException -> Lb8
            goto Lc1
        Lb8:
            java.lang.String r2 = "isValidDateSix ParseException occur."
            java.lang.Object[] r2 = new java.lang.Object[]{r2}
            com.huawei.hwlogsmodel.LogUtil.b(r0, r2)
        Lc1:
            r9.ale_(r3, r5, r7, r1)
        Lc4:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.health.manager.reconnect.BleReconnectManager.m():boolean");
    }

    private void ale_(BluetoothLeScanner bluetoothLeScanner, List<ScanFilter> list, int i, boolean z) {
        if (z) {
            try {
                if (EnvironmentInfo.r()) {
                    try {
                        ReleaseLogUtil.e("DEVMGR_BleReconnectManager", "scanDevice harmony 3.0 start 1003.");
                        bluetoothLeScanner.startScan(list, new ScanSettings.Builder().setScanMode(1003).build(), this.c);
                    } catch (IllegalArgumentException | IllegalStateException unused) {
                        ReleaseLogUtil.c("DEVMGR_BleReconnectManager", "IllegalStateException or IllegalArgumentException 3.0 start.");
                        bluetoothLeScanner.startScan(list, new ScanSettings.Builder().setScanMode(i).build(), this.c);
                    }
                }
            } catch (SecurityException e) {
                LogUtil.b("BleReconnectManager", "btSwitchEnable SecurityException:", ExceptionUtils.d(e));
                return;
            }
        }
        ReleaseLogUtil.e("DEVMGR_BleReconnectManager", "scan device default logic.");
        bluetoothLeScanner.startScan(list, new ScanSettings.Builder().setScanMode(i).build(), this.c);
    }

    void d() {
        LogUtil.a("BleReconnectManager", "Enter cancelBleReconnect");
        o();
        if (this.i == null) {
            ReleaseLogUtil.d("DEVMGR_BleReconnectManager", "mScanHandler is null");
            h();
        }
        if (this.i == null) {
            ReleaseLogUtil.d("DEVMGR_BleReconnectManager", "Failed to initialize mScanHandler");
        } else if (EnvironmentInfo.r()) {
            c(2);
        } else {
            this.i.removeMessages(2);
        }
        c(1);
    }

    void e() {
        ReleaseLogUtil.e("DEVMGR_BleReconnectManager", "Enter nextBleReconnect");
        e(2, -1L);
    }

    private void e(int i, long j) {
        Context context = BaseApplication.getContext();
        if (j == -1) {
            j = ScanTimeUtil.a();
        }
        ReleaseLogUtil.e("DEVMGR_BleReconnectManager", "sendPendingIntentTask type: ", Integer.valueOf(i), ", delay: ", Long.valueOf(j));
        if (i == 1) {
            Intent intent = new Intent();
            intent.putExtra("type", i);
            intent.setClass(context, PeriodScanService.class);
            PendingIntent service = PendingIntent.getService(context, i, intent, 201326592);
            long currentTimeMillis = System.currentTimeMillis();
            Object systemService = context.getSystemService(NotificationCompat.CATEGORY_ALARM);
            if (systemService instanceof AlarmManager) {
                try {
                    ((AlarmManager) systemService).setExactAndAllowWhileIdle(0, currentTimeMillis + j, service);
                    return;
                } catch (SecurityException e) {
                    ReleaseLogUtil.d("DEVMGR_BleReconnectManager", "pluginDameon sendPendingIntentTask exception ", e.getMessage());
                    return;
                }
            }
            LogUtil.h("BleReconnectManager", "alarmManagerObject is not AlarmManager");
            return;
        }
        if (EnvironmentInfo.r()) {
            LogUtil.a("BleReconnectManager", "harmony 3.0 free  start.");
            b(i, context, j);
        } else {
            LogUtil.a("BleReconnectManager", "previous start scan logic.");
            e(j);
        }
    }

    private void e(long j) {
        if (this.i == null) {
            ReleaseLogUtil.d("DEVMGR_BleReconnectManager", "mScanHandler is null");
            h();
        }
        ExtendHandler extendHandler = this.i;
        if (extendHandler == null) {
            ReleaseLogUtil.c("DEVMGR_BleReconnectManager", "Failed to initialize mScanHandler");
        } else {
            extendHandler.sendEmptyMessage(2, j);
        }
    }

    private void b(int i, Context context, long j) {
        Intent intent = new Intent();
        intent.putExtra("type", i);
        intent.setClass(context, PeriodScanService.class);
        PendingIntent service = PendingIntent.getService(context, i, intent, 201326592);
        long currentTimeMillis = System.currentTimeMillis();
        Object systemService = context.getSystemService(NotificationCompat.CATEGORY_ALARM);
        if (systemService instanceof AlarmManager) {
            AlarmManager alarmManager = (AlarmManager) systemService;
            LogUtil.a("BleReconnectManager", "alarmTimeScan set alarmClock");
            try {
                alarmManager.setExactAndAllowWhileIdle(0, currentTimeMillis + j, service);
                return;
            } catch (SecurityException e) {
                ReleaseLogUtil.d("DEVMGR_BleReconnectManager", "PluginDameon alarmTimeScan exception ", e.getMessage());
                return;
            }
        }
        LogUtil.h("BleReconnectManager", "alarmManagerObject is not AlarmManager");
    }

    private void c(int i) {
        Object systemService;
        Context context = BaseApplication.getContext();
        if (context == null || (systemService = context.getSystemService(NotificationCompat.CATEGORY_ALARM)) == null || !(systemService instanceof AlarmManager)) {
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("type", i);
        intent.setClass(context, PeriodScanService.class);
        ((AlarmManager) systemService).cancel(PendingIntent.getService(context, i, intent, 201326592));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        LogUtil.a("BleReconnectManager", "Enter scan failed.");
        d();
        e();
    }

    private void o() {
        LogUtil.a("BleReconnectManager", "stopBleScan");
        try {
            BluetoothLeScanner bluetoothLeScanner = this.e.getBluetoothLeScanner();
            if (bluetoothLeScanner != null) {
                bluetoothLeScanner.stopScan(this.c);
            }
        } catch (IllegalStateException | NullPointerException | SecurityException e) {
            LogUtil.b("BleReconnectManager", "stopLeScan Exception:", ExceptionUtils.d(e));
        }
    }

    private void g() {
        LogUtil.a("BleReconnectManager", "Enter reportConnectedDevice");
        List<BluetoothDevice> f = f();
        synchronized (d) {
            Iterator<BluetoothDevice> it = f.iterator();
            while (it.hasNext()) {
                ald_(it.next(), 0, null);
            }
        }
    }

    private List<BluetoothDevice> f() {
        Set<BluetoothDevice> bondedDevices;
        boolean z;
        ArrayList arrayList = new ArrayList(16);
        try {
            BluetoothManager bluetoothManager = this.b;
            if (bluetoothManager != null) {
                arrayList.addAll(bluetoothManager.getConnectedDevices(7));
            }
            bondedDevices = BluetoothAdapter.getDefaultAdapter().getBondedDevices();
        } catch (IllegalAccessException e) {
            LogUtil.b("BleReconnectManager", "getConnectedDeviceList with exception IllegalAccessException: ", ExceptionUtils.d(e));
        } catch (IllegalArgumentException e2) {
            LogUtil.b("BleReconnectManager", "getConnectedDeviceList with exception IllegalArgumentException: ", ExceptionUtils.d(e2));
        } catch (NoSuchMethodException e3) {
            LogUtil.b("BleReconnectManager", "getConnectedDeviceList with exception NoSuchMethodException: ", ExceptionUtils.d(e3));
        } catch (SecurityException e4) {
            LogUtil.b("BleReconnectManager", "getConnectedDeviceList with exception SecurityException: ", ExceptionUtils.d(e4));
        } catch (InvocationTargetException e5) {
            LogUtil.b("BleReconnectManager", "getConnectedDeviceList with exception InvocationTargetException: ", ExceptionUtils.d(e5));
        }
        if (bondedDevices == null) {
            LogUtil.h("BleReconnectManager", "get bonded devices is null.");
            return arrayList;
        }
        Method declaredMethod = BluetoothDevice.class.getDeclaredMethod("isConnected", new Class[0]);
        declaredMethod.setAccessible(true);
        for (BluetoothDevice bluetoothDevice : bondedDevices) {
            Object invoke = declaredMethod.invoke(bluetoothDevice, new Object[0]);
            if (invoke instanceof Boolean) {
                z = ((Boolean) invoke).booleanValue();
            } else {
                LogUtil.h("BleReconnectManager", "connectState is not Boolean");
                z = false;
            }
            LogUtil.a("BleReconnectManager", "getBondDevice:", bluetoothDevice.getName(), ";connect status:", Boolean.valueOf(z));
            if (z) {
                arrayList.add(bluetoothDevice);
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ald_(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
        if (this.h == null || !alc_(bluetoothDevice)) {
            return;
        }
        this.h.onLeScan(bluetoothDevice, i, bArr);
    }

    static class InstanceHolder {

        /* renamed from: a, reason: collision with root package name */
        private static BleReconnectManager f2794a = new BleReconnectManager();

        private InstanceHolder() {
        }
    }

    static class ScanHandler extends BaseHandlerCallback<BleReconnectManager> {
        ScanHandler(BleReconnectManager bleReconnectManager) {
            super(bleReconnectManager);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandlerCallback
        /* renamed from: alg_, reason: merged with bridge method [inline-methods] */
        public boolean handleMessageWhenReferenceNotNull(BleReconnectManager bleReconnectManager, Message message) {
            if (message.what == 2) {
                e();
                return true;
            }
            ReleaseLogUtil.d("DEVMGR_BleReconnectManager", "handleMessage default");
            return false;
        }

        private void e() {
            ReleaseLogUtil.e("DEVMGR_BleReconnectManager", "onStartScan");
            BleReconnectManager.b().d();
            if (BleReconnectManager.b().c()) {
                BleReconnectManager.b().a();
            }
        }
    }

    private void h() {
        if (this.i == null) {
            this.i = HandlerCenter.yt_(this.f2793a, "BleReconnectManager");
        }
    }
}
