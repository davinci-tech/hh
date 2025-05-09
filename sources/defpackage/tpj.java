package defpackage;

import android.text.TextUtils;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.wearengine.device.Device;
import com.huawei.wearengine.monitor.MonitorMessage;
import com.huawei.wearengine.monitor.QueryDataCallback;
import health.compact.a.utils.StringUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes7.dex */
public class tpj {
    private static final Object b = new Object();
    private tpm c;
    private Map<String, List<QueryDataCallback>> e;

    public int b(int i, int i2) {
        return (i < 0 || i > 100) ? i2 : i;
    }

    static class c {
        private static final tpj e = new tpj();
    }

    private tpj() {
        HashMap hashMap = new HashMap(16);
        this.e = hashMap;
        hashMap.clear();
        this.c = new tpm(50);
    }

    public static tpj c() {
        return c.e;
    }

    public void a(String str, QueryDataCallback queryDataCallback) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        synchronized (b) {
            List<QueryDataCallback> list = this.e.get(str);
            if (list == null) {
                list = new ArrayList<>();
            }
            list.add(queryDataCallback);
            this.e.put(str, list);
        }
        LogUtil.a("PowerStatusHandleManager", "addPowerStatusListener callback ok");
    }

    public void c(DeviceInfo deviceInfo, byte[] bArr) {
        List<QueryDataCallback> remove;
        if (bArr == null || bArr.length == 0) {
            LogUtil.h("PowerStatusHandleManager", "Received message is null");
            return;
        }
        if (deviceInfo == null || StringUtils.g(deviceInfo.getDeviceIdentify())) {
            LogUtil.h("PowerStatusHandleManager", "Received deviceInfo is null or deviceIdentify is empty");
            return;
        }
        if (bArr.length >= 2) {
            if (bArr[0] == 1 && bArr[1] == 8) {
                int b2 = b(tqu.e(bArr, -1), -1);
                LogUtil.a("PowerStatusHandleManager", "handlePowerStatusValue is: ", Integer.valueOf(b2));
                if (b2 != -1) {
                    d(deviceInfo.getDeviceIdentify(), "powerStatus", String.valueOf(b2));
                }
                try {
                    synchronized (b) {
                        remove = this.e.remove(deviceInfo.getDeviceIdentify());
                    }
                    if (remove == null) {
                        LogUtil.h("PowerStatusHandleManager", "onResponsePowerStatus QueryDataCallback is null");
                        return;
                    }
                    Device j = tqy.j(deviceInfo);
                    if (j == null) {
                        LogUtil.h("PowerStatusHandleManager", "onResponsePowerStatus hiWearDevice is null");
                        return;
                    }
                    int i = b2 == -1 ? 12 : 0;
                    Iterator<QueryDataCallback> it = remove.iterator();
                    while (it.hasNext()) {
                        it.next().onDataReceived(i, new MonitorMessage("powerStatus", j.getUuid(), b2));
                    }
                } catch (Exception unused) {
                    LogUtil.b("PowerStatusHandleManager", "onResponsePowerStatus RemoteException");
                }
            }
        }
    }

    public void d(String str, String str2, String str3) {
        if (StringUtils.g(str) || !"powerStatus".equals(str2) || str3 == null) {
            return;
        }
        this.c.b(str, str2, str3);
    }
}
