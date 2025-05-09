package com.huawei.devicesdk.strategy;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanRecord;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import com.huawei.haf.common.exception.ExceptionUtils;
import defpackage.bgi;
import defpackage.bmw;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class ScanStrategyBle extends ScanStrategy {
    private bgi b;
    private boolean c = false;
    private BluetoothScanInterface d;

    /* loaded from: classes8.dex */
    interface BluetoothScanInterface {
        void startScan(BluetoothAdapter bluetoothAdapter);

        void stopScan(BluetoothAdapter bluetoothAdapter);
    }

    @Override // com.huawei.devicesdk.strategy.ScanStrategy
    public void init(bgi bgiVar) {
        if (bgiVar == null) {
            LogUtil.a("ScanStrategyBle", "init callback is null");
        }
        this.b = bgiVar;
        this.d = new d();
    }

    @Override // com.huawei.devicesdk.strategy.ScanStrategy
    public void scan() {
        ReleaseLogUtil.b("DEVMGR_ScanStrategyBle", "scan ble device.");
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter == null || !defaultAdapter.isEnabled()) {
            LogUtil.a("ScanStrategyBle", "scan bluetoothAdapter is invalid");
            a();
        } else {
            this.d.startScan(defaultAdapter);
        }
    }

    @Override // com.huawei.devicesdk.strategy.ScanStrategy
    public void stopScan(boolean z) {
        ReleaseLogUtil.b("DEVMGR_ScanStrategyBle", "stop scan device, scanFinish:", Boolean.valueOf(this.c));
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter == null || !defaultAdapter.isEnabled()) {
            LogUtil.a("ScanStrategyBle", "bluetoothAdapter is invalid");
            return;
        }
        this.d.stopScan(defaultAdapter);
        if (!this.c) {
            this.b.b(z);
        }
        this.c = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        this.c = true;
        if (this.b == null) {
            LogUtil.a("ScanStrategyBle", "mDeviceDiscoveryCallback is null");
            return;
        }
        bmw.e(100078, "", "", "");
        ReleaseLogUtil.b("DEVMGR_ScanStrategyBle", "mBleScanCallback onScanDeviceFailure ", 21);
        this.b.a(21, "");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sn_(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
        if (bluetoothDevice == null) {
            LogUtil.a("ScanStrategyBle", "onDeviceDiscovered device or records parameter exception.");
            return;
        }
        bgi bgiVar = this.b;
        if (bgiVar == null) {
            LogUtil.a("ScanStrategyBle", "mDeviceDiscoveryCallback is null");
        } else {
            bgiVar.qX_(bluetoothDevice, i, bArr, 2);
        }
    }

    /* loaded from: classes8.dex */
    class d implements BluetoothScanInterface {

        /* renamed from: a, reason: collision with root package name */
        private ScanCallback f1949a;

        d() {
            this.f1949a = new ScanCallback() { // from class: com.huawei.devicesdk.strategy.ScanStrategyBle.d.5
                @Override // android.bluetooth.le.ScanCallback
                public void onScanResult(int i, ScanResult scanResult) {
                    super.onScanResult(i, scanResult);
                    LogUtil.c("ScanStrategyBle", "onScanResult. type:", Integer.valueOf(i));
                    ScanStrategyBle.this.sn_(scanResult.getDevice(), scanResult.getRssi(), d.this.sp_(scanResult.getScanRecord()));
                }

                @Override // android.bluetooth.le.ScanCallback
                public void onBatchScanResults(List<ScanResult> list) {
                    super.onBatchScanResults(list);
                    LogUtil.c("ScanStrategyBle", "onBatchScanResults.");
                    for (ScanResult scanResult : list) {
                        ScanStrategyBle.this.sn_(scanResult.getDevice(), scanResult.getRssi(), d.this.sp_(scanResult.getScanRecord()));
                    }
                }

                @Override // android.bluetooth.le.ScanCallback
                public void onScanFailed(int i) {
                    bmw.e(100077, "", String.valueOf(i), "");
                    ReleaseLogUtil.b("DEVMGR_ScanStrategyBle", "mBleScanCallback scan device failed.", Integer.valueOf(i));
                    super.onScanFailed(i);
                    ScanStrategyBle.this.a();
                }
            };
        }

        @Override // com.huawei.devicesdk.strategy.ScanStrategyBle.BluetoothScanInterface
        public void startScan(BluetoothAdapter bluetoothAdapter) {
            BluetoothLeScanner bluetoothLeScanner = bluetoothAdapter.getBluetoothLeScanner();
            if (bluetoothLeScanner == null) {
                LogUtil.a("ScanStrategyBle", "BluetoothLeScanner is null.");
                ScanStrategyBle.this.a();
                return;
            }
            ArrayList arrayList = new ArrayList(16);
            arrayList.add(new ScanFilter.Builder().build());
            ScanSettings build = new ScanSettings.Builder().setScanMode(2).build();
            LogUtil.c("ScanStrategyBle", "startScan ble device.");
            try {
                bluetoothLeScanner.startScan(arrayList, build, this.f1949a);
            } catch (SecurityException e) {
                LogUtil.e("ScanStrategyBle", "HighVersionBluetoothScanService startScan SecurityException ", ExceptionUtils.d(e));
            }
        }

        @Override // com.huawei.devicesdk.strategy.ScanStrategyBle.BluetoothScanInterface
        public void stopScan(BluetoothAdapter bluetoothAdapter) {
            BluetoothLeScanner bluetoothLeScanner = bluetoothAdapter.getBluetoothLeScanner();
            if (bluetoothLeScanner == null) {
                LogUtil.a("ScanStrategyBle", "BluetoothLeScanner is null.");
                return;
            }
            try {
                bluetoothLeScanner.stopScan(this.f1949a);
            } catch (SecurityException e) {
                LogUtil.e("ScanStrategyBle", "HighVersionBluetoothScanService stopScan SecurityException ", ExceptionUtils.d(e));
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public byte[] sp_(ScanRecord scanRecord) {
            return scanRecord == null ? new byte[0] : scanRecord.getBytes();
        }
    }
}
