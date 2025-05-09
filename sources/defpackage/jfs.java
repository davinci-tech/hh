package defpackage;

import com.huawei.callback.BluetoothDataReceiveCallback;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes5.dex */
public class jfs implements BluetoothDataReceiveCallback {
    private static final Object c = new Object();
    private static volatile jfs d;

    /* renamed from: a, reason: collision with root package name */
    private final Map<String, BluetoothDataReceiveCallback> f13796a = new HashMap();

    private jfs() {
        jfq.c().e(39, this);
    }

    public void b(String str, BluetoothDataReceiveCallback bluetoothDataReceiveCallback) {
        this.f13796a.put(str, bluetoothDataReceiveCallback);
    }

    public static jfs d() {
        jfs jfsVar;
        synchronized (c) {
            if (d == null) {
                d = new jfs();
            }
            jfsVar = d;
        }
        return jfsVar;
    }

    @Override // com.huawei.callback.BluetoothDataReceiveCallback
    public void onDataReceived(int i, DeviceInfo deviceInfo, byte[] bArr) {
        Iterator<String> it = this.f13796a.keySet().iterator();
        while (it.hasNext()) {
            BluetoothDataReceiveCallback bluetoothDataReceiveCallback = this.f13796a.get(it.next());
            if (bluetoothDataReceiveCallback != null) {
                try {
                    bluetoothDataReceiveCallback.onDataReceived(i, deviceInfo, bArr);
                } catch (Exception e) {
                    LogUtil.a("WatchFaceBtDataManager", ExceptionUtils.d(e));
                }
            }
        }
    }
}
