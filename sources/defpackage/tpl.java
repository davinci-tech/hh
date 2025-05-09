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
public class tpl {
    private static final Object e = new Object();
    private tpm b;
    private Map<String, List<QueryDataCallback>> c;

    public int c(int i, int i2) {
        return (i == 1 || i == 2) ? i : i2;
    }

    static class e {
        private static final tpl e = new tpl();
    }

    private tpl() {
        HashMap hashMap = new HashMap(16);
        this.c = hashMap;
        hashMap.clear();
        this.b = new tpm(50);
    }

    public static tpl d() {
        return e.e;
    }

    public void b(String str, QueryDataCallback queryDataCallback) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        synchronized (e) {
            List<QueryDataCallback> list = this.c.get(str);
            if (list == null) {
                list = new ArrayList<>();
            }
            list.add(queryDataCallback);
            this.c.put(str, list);
        }
        LogUtil.a("WearStatusHandleManager", "addWearStatusListener callback ok");
    }

    public void e(DeviceInfo deviceInfo, byte[] bArr) {
        List<QueryDataCallback> remove;
        if (bArr == null || bArr.length == 0) {
            LogUtil.h("WearStatusHandleManager", "Received message is null");
            return;
        }
        if (deviceInfo == null || StringUtils.g(deviceInfo.getDeviceIdentify())) {
            LogUtil.h("WearStatusHandleManager", "Received deviceInfo is null or deviceIdentify is empty");
            return;
        }
        if (bArr.length >= 2) {
            if (bArr[0] == 46 && bArr[1] == 3) {
                int c = c(tqu.e(bArr, -1), -1);
                LogUtil.a("WearStatusHandleManager", "handleWearStatusValue is: ", Integer.valueOf(c));
                if (c != -1) {
                    e(deviceInfo.getDeviceIdentify(), "wearStatus", String.valueOf(c));
                }
                try {
                    synchronized (e) {
                        remove = this.c.remove(deviceInfo.getDeviceIdentify());
                    }
                    if (remove == null) {
                        LogUtil.h("WearStatusHandleManager", "onResponseWearStatus QueryDataCallback is null");
                        return;
                    }
                    Device j = tqy.j(deviceInfo);
                    if (j == null) {
                        LogUtil.h("WearStatusHandleManager", "onResponseWearStatus hiWearDevice is null");
                        return;
                    }
                    int i = c == -1 ? 12 : 0;
                    Iterator<QueryDataCallback> it = remove.iterator();
                    while (it.hasNext()) {
                        it.next().onDataReceived(i, new MonitorMessage("wearStatus", j.getUuid(), c));
                    }
                } catch (Exception unused) {
                    LogUtil.b("WearStatusHandleManager", "onResponseWearStatus RemoteException");
                }
            }
        }
    }

    public void e(String str, String str2, String str3) {
        if (StringUtils.g(str) || !"wearStatus".equals(str2) || str3 == null) {
            return;
        }
        this.b.b(str, str2, str3);
    }
}
