package defpackage;

import android.bluetooth.BluetoothDevice;
import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes3.dex */
public class cxj {
    private static volatile cxj c;
    private static final Object d = new Object();

    /* renamed from: a, reason: collision with root package name */
    private final Map<String, WeakReference<cxh>> f11525a = new ConcurrentHashMap();

    private cxj() {
    }

    public static cxj c() {
        if (c == null) {
            synchronized (d) {
                if (c == null) {
                    c = new cxj();
                }
            }
        }
        return c;
    }

    public cxh Rd_(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null || TextUtils.isEmpty(bluetoothDevice.getAddress())) {
            LogUtil.h("BleDeviceManager", "device or mac address is null");
            return null;
        }
        WeakReference<cxh> weakReference = this.f11525a.get(bluetoothDevice.getAddress());
        if (weakReference == null || weakReference.get() == null) {
            weakReference = new WeakReference<>(new cxh(bluetoothDevice));
        }
        LogUtil.a("BleDeviceManager", "getBleDevice, device:", Integer.valueOf(System.identityHashCode(weakReference.get())));
        this.f11525a.put(bluetoothDevice.getAddress(), weakReference);
        return weakReference.get();
    }

    public void a(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("BleDeviceManager", "mac address is null");
            return;
        }
        WeakReference<cxh> remove = this.f11525a.remove(str);
        if (remove != null) {
            ReleaseLogUtil.e("R_BleDeviceManager", "release device:", Integer.valueOf(System.identityHashCode(remove.get())));
        }
    }
}
