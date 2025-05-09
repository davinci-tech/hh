package defpackage;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.hwbtsdk.btdatatype.callback.BtDeviceDiscoverCallback;
import com.huawei.hwbtsdk.btdatatype.datatype.BluetoothDeviceNode;
import health.compact.a.LogUtil;

/* loaded from: classes5.dex */
public class iyh implements BluetoothAdapter.LeScanCallback {

    /* renamed from: a, reason: collision with root package name */
    private BtDeviceDiscoverCallback f13661a;

    public iyh(BtDeviceDiscoverCallback btDeviceDiscoverCallback) {
        this.f13661a = null;
        if (btDeviceDiscoverCallback != null) {
            this.f13661a = btDeviceDiscoverCallback;
        }
    }

    @Override // android.bluetooth.BluetoothAdapter.LeScanCallback
    public void onLeScan(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
        if (this.f13661a == null || bluetoothDevice == null) {
            return;
        }
        try {
            LogUtil.c("BLEScanCallback", "onLeScan found device:", bluetoothDevice.getName());
            BluetoothDeviceNode bluetoothDeviceNode = new BluetoothDeviceNode();
            iyn d = izc.d().d(bArr);
            if (d != null) {
                bluetoothDeviceNode.setRecordName(d.c());
                bluetoothDeviceNode.setDeviceType(d.b());
            }
            bluetoothDeviceNode.setBtDevice(bluetoothDevice);
            this.f13661a.onDeviceDiscovered(bluetoothDeviceNode, i, bArr);
        } catch (SecurityException e) {
            LogUtil.e("BLEScanCallback", "onLeScan SecurityException:", ExceptionUtils.d(e));
        }
    }
}
