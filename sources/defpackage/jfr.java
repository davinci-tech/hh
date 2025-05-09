package defpackage;

import com.huawei.callback.BluetoothDataReceiveCallback;
import com.huawei.callback.DeviceStatusCallback;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.ReleaseLogUtil;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class jfr {
    private static IBaseResponseCallback e;
    private static final Object d = new Object();

    /* renamed from: a, reason: collision with root package name */
    private static Map<Integer, BluetoothDataReceiveCallback> f13795a = new HashMap(16);
    private static Map<String, DeviceStatusCallback> b = new HashMap(16);

    public static IBaseResponseCallback c() {
        return e;
    }

    public static BluetoothDataReceiveCallback d(int i) {
        BluetoothDataReceiveCallback bluetoothDataReceiveCallback;
        ReleaseLogUtil.b("DEVMGR_ServiceCallback", "Enter getManagerCallback method. moduleId=" + i);
        synchronized (d) {
            bluetoothDataReceiveCallback = f13795a.containsKey(Integer.valueOf(i)) ? f13795a.get(Integer.valueOf(i)) : null;
        }
        return bluetoothDataReceiveCallback;
    }

    public static void e(int i, BluetoothDataReceiveCallback bluetoothDataReceiveCallback) {
        LogUtil.a("ServiceCallback", "Enter registerManagerCallback method. serviceId=" + i);
        if (jfq.c().e(i) || jfq.c().c(i)) {
            ReleaseLogUtil.b("DEVMGR_ServiceCallback", "registerManagerCallback is,", Integer.valueOf(i));
        }
        synchronized (d) {
            if (f13795a.get(Integer.valueOf(i)) != null) {
                LogUtil.b("ServiceCallback", "sManagersCallbackMap.get(serviceId) != null");
                f13795a.remove(Integer.valueOf(i));
            }
            f13795a.put(Integer.valueOf(i), bluetoothDataReceiveCallback);
        }
    }

    public static void c(int i) {
        LogUtil.a("ServiceCallback", "Enter unregisterBaseResponseCallback method. serviceId=" + i);
        if (jfq.c().e(i) || jfq.c().c(i)) {
            ReleaseLogUtil.b("DEVMGR_ServiceCallback", "unregisterManagerResponseCallback is,", Integer.valueOf(i));
        }
        synchronized (d) {
            f13795a.remove(Integer.valueOf(i));
        }
    }

    public static void d(String str, DeviceStatusCallback deviceStatusCallback) {
        LogUtil.a("ServiceCallback", "Enter registerDeviceStatusCallback method. moduleName: ", str);
        synchronized (d) {
            if (b.get(str) != null) {
                LogUtil.a("ServiceCallback", "sDeviceConnectCallbackMap.get(moduleName) != null");
                b.remove(str);
            }
            b.put(str, deviceStatusCallback);
        }
    }

    public static void b(String str) {
        LogUtil.a("ServiceCallback", "Enter unregisterDeviceConnectedCallback method. moduleName: ", str);
        synchronized (d) {
            b.remove(str);
        }
    }

    public static Map<String, DeviceStatusCallback> d() {
        return b;
    }
}
