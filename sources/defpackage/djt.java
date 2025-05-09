package defpackage;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import health.compact.a.util.LogUtil;

/* loaded from: classes3.dex */
public class djt {

    /* renamed from: a, reason: collision with root package name */
    private static djt f11687a;
    private BluetoothAdapter c;

    private djt() {
    }

    public static djt e() {
        if (f11687a == null) {
            f11687a = new djt();
        }
        return f11687a;
    }

    public int d(Context context) {
        if (this.c == null) {
            LogUtil.d("BluetoothHelper", "getBluetoothEnabledStatus init bluetoothAdapter is null");
            BluetoothManager bluetoothManager = context.getSystemService("bluetooth") instanceof BluetoothManager ? (BluetoothManager) context.getSystemService("bluetooth") : null;
            if (bluetoothManager == null) {
                LogUtil.c("BluetoothHelper", "getBluetoothEnabledStatus no BT Manager in this phone");
                return 0;
            }
            this.c = bluetoothManager.getAdapter();
        }
        BluetoothAdapter bluetoothAdapter = this.c;
        if (bluetoothAdapter == null) {
            LogUtil.c("BluetoothHelper", "getBluetoothEnabledStatus no BT in this phone");
            return 0;
        }
        if (bluetoothAdapter.isEnabled()) {
            return 1;
        }
        LogUtil.d("BluetoothHelper", "getBluetoothEnabledStatus is no enabled");
        return 2;
    }

    public void d() {
        f11687a = null;
    }
}
