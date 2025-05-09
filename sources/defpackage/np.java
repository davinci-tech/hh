package defpackage;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import com.careforeyou.library.bt.scanner.BluetoothLeScannerCompat;
import java.util.List;

/* loaded from: classes2.dex */
public class np extends BluetoothLeScannerCompat {
    private ScanCallback d = new ScanCallback() { // from class: np.5
        @Override // android.bluetooth.le.ScanCallback
        public void onScanResult(int i, final ScanResult scanResult) {
            super.onScanResult(i, scanResult);
            final BluetoothDevice device = scanResult.getDevice();
            if (device.getAddress() == null) {
                return;
            }
            ob.e(new Runnable() { // from class: np.5.2
                @Override // java.lang.Runnable
                public void run() {
                    synchronized (this) {
                        np.this.handleBroadcastInfo(device, scanResult.getRssi(), scanResult.getScanRecord().getBytes());
                    }
                }
            });
        }

        @Override // android.bluetooth.le.ScanCallback
        public void onBatchScanResults(List<ScanResult> list) {
            super.onBatchScanResults(list);
            for (final ScanResult scanResult : list) {
                final BluetoothDevice device = scanResult.getDevice();
                if (device.getAddress() == null) {
                    return;
                } else {
                    ob.e(new Runnable() { // from class: np.5.1
                        @Override // java.lang.Runnable
                        public void run() {
                            synchronized (this) {
                                np.this.handleBroadcastInfo(device, scanResult.getRssi(), scanResult.getScanRecord().getBytes());
                            }
                        }
                    });
                }
            }
        }

        @Override // android.bluetooth.le.ScanCallback
        public void onScanFailed(int i) {
            super.onScanFailed(i);
        }
    };
    private BluetoothAdapter e = BluetoothAdapter.getDefaultAdapter();

    @Override // com.careforeyou.library.bt.scanner.BluetoothLeScannerCompat
    public boolean startLeScanInternal() {
        BluetoothLeScanner bluetoothLeScanner;
        ScanSettings.Builder builder = new ScanSettings.Builder();
        builder.setScanMode(2);
        BluetoothAdapter bluetoothAdapter = this.e;
        if (bluetoothAdapter == null || (bluetoothLeScanner = bluetoothAdapter.getBluetoothLeScanner()) == null) {
            return false;
        }
        oa.e("CsBtUtil_v11", "localBluetoothLeScanner.startScan ");
        bluetoothLeScanner.startScan((List<ScanFilter>) null, builder.build(), this.d);
        return true;
    }

    @Override // com.careforeyou.library.bt.scanner.BluetoothLeScannerCompat
    public void stopLeScanInternal() {
        BluetoothLeScanner bluetoothLeScanner;
        try {
            BluetoothAdapter bluetoothAdapter = this.e;
            if (bluetoothAdapter == null || (bluetoothLeScanner = bluetoothAdapter.getBluetoothLeScanner()) == null) {
                return;
            }
            bluetoothLeScanner.stopScan(this.d);
        } catch (Exception e) {
            oa.c("CsBtUtil_v11", e.getMessage());
        }
    }
}
