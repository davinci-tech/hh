package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class jzp {
    public void a() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: jzo
            @Override // java.lang.Runnable
            public final void run() {
                jzp.this.e();
            }
        });
    }

    private void b(List<String> list) {
        if (list == null || list.isEmpty()) {
            LogUtil.h("DeviceListChangeHandler", "onDeviceRemoved: removed device list is null or empty.");
        } else {
            LogUtil.a("DeviceListChangeHandler", "onDeviceRemoved: removed device list's size:", Integer.valueOf(list.size()));
            jzy.a(list);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        Context context = BaseApplication.getContext();
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, "DeviceListChangeHandler");
        d(c(deviceList), jzu.d(context).c());
    }

    private void d(List<String> list, List<String> list2) {
        boolean z = list == null || list.isEmpty();
        boolean z2 = list2 == null || list2.isEmpty();
        if (z && z2) {
            LogUtil.a("DeviceListChangeHandler", "calculateChanged: SCENARIO 1.");
            return;
        }
        if (z) {
            LogUtil.a("DeviceListChangeHandler", "calculateChanged: SCENARIO 2.");
            b(list2);
            return;
        }
        if (z2) {
            LogUtil.a("DeviceListChangeHandler", "calculateChanged: SCENARIO 3.");
            return;
        }
        LogUtil.a("DeviceListChangeHandler", "calculateChanged: SCENARIO 4.");
        ArrayList arrayList = new ArrayList(list);
        ArrayList arrayList2 = new ArrayList(list);
        List<String> arrayList3 = new ArrayList<>(list2);
        if (arrayList.retainAll(list2)) {
            LogUtil.a("DeviceListChangeHandler", "calculateChanged: both of this list is same.");
            return;
        }
        if (arrayList2.removeAll(arrayList)) {
            LogUtil.a("DeviceListChangeHandler", "removeAll ok");
        }
        if (arrayList3.removeAll(arrayList)) {
            b(arrayList3);
        }
    }

    private List<String> c(List<DeviceInfo> list) {
        if (list == null || list.isEmpty()) {
            return new ArrayList(0);
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (DeviceInfo deviceInfo : list) {
            if (deviceInfo != null) {
                String deviceIdentify = deviceInfo.getDeviceIdentify();
                if (!TextUtils.isEmpty(deviceIdentify)) {
                    arrayList.add(kak.b(deviceIdentify));
                }
            }
        }
        return arrayList;
    }
}
