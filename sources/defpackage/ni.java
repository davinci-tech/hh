package defpackage;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import com.careforeyou.library.bt.scanner.BluetoothLeScannerCompat;

/* loaded from: classes2.dex */
public class ni extends BluetoothLeScannerCompat {
    private BluetoothAdapter.LeScanCallback e = new BluetoothAdapter.LeScanCallback() { // from class: ni.3
        @Override // android.bluetooth.BluetoothAdapter.LeScanCallback
        public void onLeScan(final BluetoothDevice bluetoothDevice, final int i, final byte[] bArr) {
            if (bluetoothDevice == null || bluetoothDevice.getAddress() == null) {
                return;
            }
            ob.e(new Runnable() { // from class: ni.3.2
                @Override // java.lang.Runnable
                public void run() {
                    synchronized (this) {
                        ni.this.handleBroadcastInfo(bluetoothDevice, i, bArr);
                    }
                }
            });
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private BluetoothAdapter f15296a = BluetoothAdapter.getDefaultAdapter();

    @Override // com.careforeyou.library.bt.scanner.BluetoothLeScannerCompat
    public boolean startLeScanInternal() {
        BluetoothAdapter bluetoothAdapter = this.f15296a;
        if (bluetoothAdapter != null) {
            return bluetoothAdapter.startLeScan(this.e);
        }
        return false;
    }

    @Override // com.careforeyou.library.bt.scanner.BluetoothLeScannerCompat
    public void stopLeScanInternal() {
        try {
            BluetoothAdapter bluetoothAdapter = this.f15296a;
            if (bluetoothAdapter != null) {
                bluetoothAdapter.stopLeScan(this.e);
            }
        } catch (Exception e) {
            oa.c("CsBtUtil_v11", e.getMessage());
        }
    }
}
