package defpackage;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanRecord;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.handler.ExtendHandler;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.health.device.callback.IDeviceEventHandler;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.ecologydevice.connectivity.ScanFilter;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.cxa;
import health.compact.a.CommonUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ScheduledFuture;

/* loaded from: classes3.dex */
public class cxa {
    private Handler b;
    private ExtendHandler d;
    private ScanCallback e;
    private IDeviceEventHandler f;
    private ScanFilter i;
    private int j;
    private ScheduledFuture n;
    private final Object g = new Object();
    private boolean c = false;
    private Runnable h = new Runnable() { // from class: cwx
        @Override // java.lang.Runnable
        public final void run() {
            cxa.this.d();
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private BroadcastReceiver f11515a = new BroadcastReceiver() { // from class: cxa.4
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (!"android.bluetooth.device.action.FOUND".equals(intent.getAction())) {
                LogUtil.h("DeviceScanner", "onReceive intent action is not FOUND");
                return;
            }
            try {
                Parcelable parcelableExtra = intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                if (!(parcelableExtra instanceof BluetoothDevice)) {
                    LogUtil.h("DeviceScanner", "onReceive intent ParcelableExtra is null");
                    return;
                }
                BluetoothDevice bluetoothDevice = (BluetoothDevice) parcelableExtra;
                if (cxa.this.i == null || cxa.this.i.QY_(bluetoothDevice)) {
                    cxa.this.d(ceu.Ej_(bluetoothDevice));
                }
            } catch (RuntimeException e) {
                LogUtil.b("DeviceScanner", "onReceive ", e.getMessage());
            }
        }
    };

    /* synthetic */ void d() {
        LogUtil.a("DeviceScanner", "ScheduledStopTask");
        g();
        this.b.post(new Runnable() { // from class: cxe
            @Override // java.lang.Runnable
            public final void run() {
                cxa.this.b();
            }
        });
    }

    /* synthetic */ void b() {
        IDeviceEventHandler iDeviceEventHandler = this.f;
        if (iDeviceEventHandler != null) {
            iDeviceEventHandler.onScanFailed(1);
            this.f = null;
        }
    }

    cxa() {
        synchronized (this) {
            if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
                this.b = new Handler(Looper.getMainLooper());
            } else {
                if (Looper.myLooper() == null) {
                    Looper.prepare();
                    Looper.loop();
                }
                this.b = new Handler(Looper.myLooper());
            }
            this.d = HandlerCenter.e("DeviceScanner");
        }
    }

    public boolean b(cev cevVar, ScanFilter scanFilter, IDeviceEventHandler iDeviceEventHandler) {
        this.i = scanFilter;
        synchronized (this.g) {
            this.f = iDeviceEventHandler;
            int c = cevVar.c();
            this.j = c;
            this.c = true;
            if (c == 1) {
                h();
            } else if (c == 2) {
                o();
            } else if (c != 4) {
                LogUtil.h("DeviceScanner", "scanDevice other scan mode = ", Integer.valueOf(c));
            } else if (iDeviceEventHandler != null) {
                iDeviceEventHandler.onDeviceFound(new cfa());
            }
        }
        ScheduledFuture scheduledFuture = this.n;
        if (scheduledFuture != null) {
            LogUtil.a("DeviceScanner", "scanDevice cancel the existing task result is ", Boolean.valueOf(scheduledFuture.cancel(true)));
        }
        if (cevVar.e() > 0) {
            this.n = cpp.e(this.h, cevVar.e());
        }
        return true;
    }

    public void j() {
        synchronized (this) {
            LogUtil.a("DeviceScanner", "stopScan");
            g();
            cpp.d(this.h);
            this.b.post(new Runnable() { // from class: cxd
                @Override // java.lang.Runnable
                public final void run() {
                    cxa.this.a();
                }
            });
            ExtendHandler extendHandler = this.d;
            if (extendHandler != null) {
                extendHandler.removeTasksAndMessages();
                this.d.quit(true);
            }
        }
    }

    /* synthetic */ void a() {
        IDeviceEventHandler iDeviceEventHandler = this.f;
        if (iDeviceEventHandler != null) {
            iDeviceEventHandler.onScanFailed(5);
            this.f = null;
        }
    }

    private void g() {
        synchronized (this.g) {
            if (this.c) {
                this.c = false;
                this.i = null;
                int i = this.j;
                if (i == 1) {
                    k();
                } else if (i == 2) {
                    m();
                } else {
                    LogUtil.h("DeviceScanner", "cancelScan other scan mode = ", Integer.valueOf(i));
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(final HealthDevice healthDevice) {
        if (healthDevice == null) {
            return;
        }
        LogUtil.a("DeviceScanner", "DeviceScanner onDeviceFound ", healthDevice.getDeviceName());
        this.b.post(new Runnable() { // from class: cxb
            @Override // java.lang.Runnable
            public final void run() {
                cxa.this.a(healthDevice);
            }
        });
    }

    /* synthetic */ void a(HealthDevice healthDevice) {
        IDeviceEventHandler iDeviceEventHandler = this.f;
        if (iDeviceEventHandler != null) {
            iDeviceEventHandler.onDeviceFound(healthDevice);
        }
    }

    private void h() {
        List<BluetoothDevice> connectedDevices;
        try {
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            if (defaultAdapter.isEnabled()) {
                LogUtil.a("DeviceScanner", "scanBleDevice DeviceScanner begin scan");
                Object systemService = cpp.a().getSystemService("bluetooth");
                if ((systemService instanceof BluetoothManager) && (connectedDevices = ((BluetoothManager) systemService).getConnectedDevices(7)) != null) {
                    LogUtil.a("DeviceScanner", "scanBleDevice getConnectedDevices = ", Integer.valueOf(connectedDevices.size()));
                    Iterator<BluetoothDevice> it = connectedDevices.iterator();
                    while (it.hasNext()) {
                        QV_(it.next());
                    }
                }
                BluetoothLeScanner bluetoothLeScanner = defaultAdapter.getBluetoothLeScanner();
                ArrayList arrayList = new ArrayList(16);
                arrayList.add(new ScanFilter.Builder().build());
                if (bluetoothLeScanner != null) {
                    bluetoothLeScanner.startScan(arrayList, new ScanSettings.Builder().setScanMode(2).build(), QR_());
                }
            }
        } catch (SecurityException e) {
            LogUtil.b("DeviceScanner", "scanBleDevice SecurityException:", ExceptionUtils.d(e));
        }
    }

    private void QV_(BluetoothDevice bluetoothDevice) {
        com.huawei.health.ecologydevice.connectivity.ScanFilter scanFilter;
        if (bluetoothDevice == null || (scanFilter = this.i) == null || !scanFilter.QY_(bluetoothDevice)) {
            return;
        }
        cxh Ra_ = cxh.Ra_(bluetoothDevice);
        if (Ra_.getDeviceName() != null) {
            d(Ra_);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void QS_(ScanResult scanResult) {
        if (scanResult != null) {
            QW_(scanResult.getDevice(), scanResult.getScanRecord());
        } else {
            LogUtil.h("DeviceScanner", "the scan result is null ");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Object obj) {
        if (!(obj instanceof List)) {
            LogUtil.h("DeviceScanner", "the results is not a list");
            return;
        }
        List list = (List) obj;
        if (koq.b(list)) {
            LogUtil.h("DeviceScanner", "the result is empty");
            return;
        }
        for (Object obj2 : list) {
            if (obj2 instanceof ScanResult) {
                ScanResult scanResult = (ScanResult) obj2;
                QW_(scanResult.getDevice(), scanResult.getScanRecord());
            } else {
                LogUtil.h("DeviceScanner", "onBatchScanResult is ", obj2.toString());
            }
        }
    }

    private ScanCallback QR_() {
        ScanCallback scanCallback = this.e;
        if (scanCallback != null) {
            return scanCallback;
        }
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        this.e = anonymousClass1;
        return anonymousClass1;
    }

    /* renamed from: cxa$1, reason: invalid class name */
    class AnonymousClass1 extends ScanCallback {
        AnonymousClass1() {
        }

        @Override // android.bluetooth.le.ScanCallback
        public void onScanResult(int i, final ScanResult scanResult) {
            super.onScanResult(i, scanResult);
            if (cxa.this.d == null) {
                return;
            }
            cxa.this.d.postTask(new Runnable() { // from class: cxk
                @Override // java.lang.Runnable
                public final void run() {
                    cxa.AnonymousClass1.this.QX_(scanResult);
                }
            });
        }

        /* synthetic */ void QX_(ScanResult scanResult) {
            cxa.this.QS_(scanResult);
        }

        @Override // android.bluetooth.le.ScanCallback
        public void onBatchScanResults(final List<ScanResult> list) {
            super.onBatchScanResults(list);
            if (list != null) {
                if (cxa.this.d == null) {
                    return;
                }
                cxa.this.d.postTask(new Runnable() { // from class: cxg
                    @Override // java.lang.Runnable
                    public final void run() {
                        cxa.AnonymousClass1.this.a(list);
                    }
                });
                return;
            }
            LogUtil.a("DeviceScanner", "the results is null");
        }

        /* synthetic */ void a(List list) {
            cxa.this.b(list);
        }

        @Override // android.bluetooth.le.ScanCallback
        public void onScanFailed(int i) {
            super.onScanFailed(i);
            LogUtil.h("DeviceScanner", "scan fail", "the system error code is ", Integer.valueOf(i));
        }
    }

    private void o() {
        try {
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            if (defaultAdapter.isEnabled()) {
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.bluetooth.device.action.FOUND");
                LogUtil.a("DeviceScanner", "scanClassicDevice registerReceiver");
                cpp.a().registerReceiver(this.f11515a, intentFilter);
                cpp.a(new Runnable() { // from class: cxf
                    @Override // java.lang.Runnable
                    public final void run() {
                        cxa.this.e();
                    }
                });
                defaultAdapter.startDiscovery();
            }
        } catch (SecurityException e) {
            LogUtil.b("DeviceScanner", "scanClassicDevice SecurityException:", ExceptionUtils.d(e));
        }
    }

    /* synthetic */ void e() {
        com.huawei.health.ecologydevice.connectivity.ScanFilter scanFilter;
        Set<BluetoothDevice> bondedDevices = BluetoothAdapter.getDefaultAdapter().getBondedDevices();
        if (bondedDevices != null) {
            for (BluetoothDevice bluetoothDevice : bondedDevices) {
                if (bluetoothDevice != null && ((scanFilter = this.i) == null || scanFilter.QY_(bluetoothDevice))) {
                    d(ceu.Ej_(bluetoothDevice));
                }
            }
        }
    }

    private void k() {
        try {
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            if (defaultAdapter.isEnabled()) {
                BluetoothLeScanner bluetoothLeScanner = defaultAdapter.getBluetoothLeScanner();
                if (bluetoothLeScanner != null) {
                    bluetoothLeScanner.stopScan(this.e);
                }
            } else {
                LogUtil.h("DeviceScanner", "stopBleScan bluetoothAdapter is not enable");
                this.b.post(new Runnable() { // from class: cxc
                    @Override // java.lang.Runnable
                    public final void run() {
                        cxa.this.c();
                    }
                });
            }
        } catch (SecurityException e) {
            LogUtil.b("DeviceScanner", "stopBleScan SecurityException:", ExceptionUtils.d(e));
        }
    }

    /* synthetic */ void c() {
        IDeviceEventHandler iDeviceEventHandler = this.f;
        if (iDeviceEventHandler != null) {
            iDeviceEventHandler.onScanFailed(1);
            this.f = null;
        }
    }

    private void m() {
        try {
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            if (defaultAdapter.isDiscovering()) {
                LogUtil.a("DeviceScanner", "stopScanClassic scanClassicDevice unregisterReceiver");
                defaultAdapter.cancelDiscovery();
                cpp.a().unregisterReceiver(this.f11515a);
            } else {
                LogUtil.h("DeviceScanner", "stopScanClassic bluetoothAdapter is not discover");
                this.b.post(new Runnable() { // from class: cwy
                    @Override // java.lang.Runnable
                    public final void run() {
                        cxa.this.i();
                    }
                });
            }
        } catch (SecurityException e) {
            LogUtil.b("DeviceScanner", "stopScanClassic SecurityException:", ExceptionUtils.d(e));
        }
    }

    /* synthetic */ void i() {
        IDeviceEventHandler iDeviceEventHandler = this.f;
        if (iDeviceEventHandler != null) {
            iDeviceEventHandler.onScanFailed(1);
            this.f = null;
        }
    }

    private void QW_(BluetoothDevice bluetoothDevice, ScanRecord scanRecord) {
        if (bluetoothDevice == null) {
            LogUtil.h("DeviceScanner", "reportDeviceScanResult device is null.");
            return;
        }
        try {
            LogUtil.a("DeviceScanner", "reportDeviceScanResult onLeScan found device ", bluetoothDevice.getName());
        } catch (SecurityException e) {
            LogUtil.b("DeviceScanner", "reportDeviceScanResult SecurityException:", ExceptionUtils.d(e));
        }
        QU_(bluetoothDevice, QT_(scanRecord));
    }

    private void QU_(BluetoothDevice bluetoothDevice, byte[] bArr) {
        com.huawei.health.ecologydevice.connectivity.ScanFilter scanFilter = this.i;
        cxh Ra_ = cxh.Ra_(bluetoothDevice);
        if (Ra_ == null) {
            return;
        }
        try {
            if (bluetoothDevice.getName() == null && CommonUtil.ar()) {
                String c = izc.d().d(bArr).c();
                LogUtil.a("DeviceScanner", "parseScanResult onLeScan found node ", c);
                Ra_.setDeviceName(c);
                if (this.c) {
                    if (scanFilter == null || scanFilter.e(bArr) || scanFilter.d(Ra_)) {
                        d(Ra_);
                        return;
                    }
                    return;
                }
                return;
            }
            if (this.c) {
                if (scanFilter == null || scanFilter.e(bArr) || scanFilter.QY_(bluetoothDevice)) {
                    if (Ra_.getDeviceName() != null) {
                        d(Ra_);
                    } else {
                        LogUtil.h("DeviceScanner", "parseScanResult onLeScan deviceName is null");
                    }
                }
            }
        } catch (SecurityException e) {
            LogUtil.b("DeviceScanner", "parseScanResult SecurityException:", ExceptionUtils.d(e));
        }
    }

    private byte[] QT_(ScanRecord scanRecord) {
        if (scanRecord == null) {
            LogUtil.h("DeviceScanner", "parseScanRecord scanRecord is null");
            return null;
        }
        return scanRecord.getBytes();
    }
}
