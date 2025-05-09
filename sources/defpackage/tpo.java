package defpackage;

import android.os.RemoteException;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import com.huawei.wearengine.monitor.SwitchStatusCallback;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes7.dex */
public class tpo {
    private volatile Map<String, List<SwitchStatusCallback>> e;

    static class c {
        private static final tpo c = new tpo();
    }

    private tpo() {
        this.e = new ConcurrentHashMap(16);
        LogUtil.a("SwitchStatusManager", "enter SwitchStatusManager");
        this.e.clear();
    }

    public static tpo d() {
        return c.c;
    }

    public void d(String str, int i, SwitchStatusCallback switchStatusCallback) {
        LogUtil.a("SwitchStatusManager", "enter registerSwitchStatusListener");
        String c2 = c(str, i);
        List<SwitchStatusCallback> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        if (this.e.containsKey(c2)) {
            copyOnWriteArrayList = this.e.get(c2);
        }
        if (copyOnWriteArrayList == null) {
            LogUtil.b("SwitchStatusManager", "registerSwitchStatusListener switchStatusCallbackList is null");
        } else {
            copyOnWriteArrayList.add(switchStatusCallback);
            this.e.put(c2, copyOnWriteArrayList);
        }
    }

    private String c(String str, int i) {
        return str + "_" + i;
    }

    public void b(String str, int i, int i2) {
        String c2 = c(str, i);
        if (!this.e.containsKey(c2)) {
            LogUtil.h("SwitchStatusManager", "key does not exist in Map");
            return;
        }
        List<SwitchStatusCallback> list = this.e.get(c2);
        if (list == null || list.size() == 0) {
            LogUtil.h("SwitchStatusManager", "List is empty");
            return;
        }
        int i3 = i2 == 0 ? 1 : 0;
        ArrayList arrayList = new ArrayList();
        for (SwitchStatusCallback switchStatusCallback : list) {
            if (switchStatusCallback != null) {
                Object[] objArr = new Object[1];
                StringBuilder sb = new StringBuilder("SwitchStatusCallback => type: ");
                sb.append(str);
                sb.append(", status: ");
                sb.append(i == 1 ? JsbMapKeyNames.H5_TEXT_DOWNLOAD_OPEN : "close");
                objArr[0] = sb.toString();
                LogUtil.a("SwitchStatusManager", objArr);
                try {
                    switchStatusCallback.onResult(i3);
                    arrayList.add(switchStatusCallback);
                } catch (RemoteException unused) {
                    LogUtil.b("SwitchStatusManager", "handleNotifyCallback RemoteException");
                }
            }
        }
        list.removeAll(arrayList);
    }
}
