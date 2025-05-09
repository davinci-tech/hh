package defpackage;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanRecord;
import android.bluetooth.le.ScanResult;
import android.os.Handler;
import android.os.HandlerThread;
import com.huawei.hwbtsdk.btdatatype.callback.BtDeviceDiscoverCallback;
import com.huawei.hwbtsdk.btdatatype.datatype.BluetoothDeviceNode;
import health.compact.a.LogUtil;
import java.util.List;

/* loaded from: classes5.dex */
public class iyy extends ScanCallback {
    private HandlerThread b;
    private Handler c;
    private BtDeviceDiscoverCallback e;

    public iyy(BtDeviceDiscoverCallback btDeviceDiscoverCallback) {
        this.e = null;
        this.b = null;
        this.c = null;
        if (btDeviceDiscoverCallback != null) {
            this.e = btDeviceDiscoverCallback;
        }
        HandlerThread handlerThread = new HandlerThread("LeScanCallback");
        this.b = handlerThread;
        handlerThread.start();
        this.c = new Handler(this.b.getLooper());
    }

    @Override // android.bluetooth.le.ScanCallback
    public void onScanResult(int i, ScanResult scanResult) {
        super.onScanResult(i, scanResult);
        bDs_(scanResult.getDevice(), scanResult.getRssi(), scanResult.getScanRecord());
    }

    @Override // android.bluetooth.le.ScanCallback
    public void onBatchScanResults(List<ScanResult> list) {
        super.onBatchScanResults(list);
        LogUtil.c("LeScanCallback", "onBatchScanResults");
        for (ScanResult scanResult : list) {
            if (scanResult != null) {
                bDs_(scanResult.getDevice(), scanResult.getRssi(), scanResult.getScanRecord());
            }
        }
    }

    @Override // android.bluetooth.le.ScanCallback
    public void onScanFailed(int i) {
        LogUtil.e("LeScanCallback", "onScanFailed:", Integer.valueOf(i));
        super.onScanFailed(i);
    }

    private void bDs_(final BluetoothDevice bluetoothDevice, final int i, final ScanRecord scanRecord) {
        if (this.e == null || bluetoothDevice == null) {
            LogUtil.a("LeScanCallback", "reportDeviceScanResult mDiscoveryCallback or device is null.");
            return;
        }
        LogUtil.c("LeScanCallback", "onLeScan found device :", bluetoothDevice.getName());
        if (this.b == null) {
            LogUtil.a("LeScanCallback", "mHandlerThread is dead");
        } else {
            this.c.post(new Runnable() { // from class: iyy.5
                @Override // java.lang.Runnable
                public void run() {
                    BluetoothDeviceNode bluetoothDeviceNode = new BluetoothDeviceNode();
                    iyn d = izc.d().d(iyy.this.bDr_(scanRecord));
                    if (d != null) {
                        bluetoothDeviceNode.setRecordName(d.c());
                        bluetoothDeviceNode.setDeviceType(d.b());
                    }
                    bluetoothDeviceNode.setBtDevice(bluetoothDevice);
                    iyy.this.e.onDeviceDiscovered(bluetoothDeviceNode, i, iyy.this.bDr_(scanRecord));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public byte[] bDr_(ScanRecord scanRecord) {
        if (scanRecord == null) {
            return null;
        }
        return scanRecord.getBytes();
    }

    public void e() {
        HandlerThread handlerThread = this.b;
        if (handlerThread == null || handlerThread.getLooper() == null) {
            return;
        }
        LogUtil.c("LeScanCallback", "Enter quitHandlerThread.");
        this.b.getLooper().quit();
        this.b = null;
    }
}
