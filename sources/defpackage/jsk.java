package defpackage;

import android.text.TextUtils;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.KeyValDbManager;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes5.dex */
public class jsk {

    /* renamed from: a, reason: collision with root package name */
    private static AtomicBoolean f14048a = new AtomicBoolean(false);

    public void a(List<String> list) {
        if (f14048a.get()) {
            return;
        }
        d(list);
        String e = KeyValDbManager.b(BaseApplication.getContext()).e("relationShipMap");
        LogUtil.a("CompatibilityFitter", "CompatibilityFitter isUpdated is ", e);
        if (!TextUtils.isEmpty(e)) {
            f14048a.set(true);
            return;
        }
        jta d = jta.d();
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, "CompatibilityFitter");
        for (DeviceInfo deviceInfo : deviceList) {
            LogUtil.a("CompatibilityFitter", "CompatibilityFitter deviceInfo is ", deviceInfo.getDeviceName());
            if (deviceInfo.getDeviceActiveState() == 1) {
                if (deviceInfo.getAutoDetectSwitchStatus() == 1) {
                    deviceInfo.setRelationship("followed_relationship");
                } else if (d.c(deviceInfo.getProductType())) {
                    deviceInfo.setRelationship("followed_relationship");
                } else {
                    deviceInfo.setRelationship("main_relationship");
                }
            } else if (!deviceInfo.getRelationship().equals("assistant_relationship")) {
                deviceInfo.setRelationship("unactive_relationship");
            } else {
                LogUtil.a("CompatibilityFitter", "device is assistant relationship:", blt.a(deviceInfo.getDeviceIdentify()));
            }
        }
        d.c(deviceList, "CompatibilityFitter");
        KeyValDbManager.b(BaseApplication.getContext()).e("relationShipMap", "true");
        f14048a.set(true);
        LogUtil.a("CompatibilityFitter", "CompatibilityFitter end, with deviceInfoList size is ", Integer.valueOf(deviceList.size()));
    }

    private void d(List<String> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        LogUtil.a("CompatibilityFitter", "refreshAssistantRelationship enter");
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, "CompatibilityFitter");
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            a(deviceList, it.next());
        }
        kcw.a().c(deviceList);
        jta.d().c(deviceList, "CompatibilityFitter");
    }

    private void a(List<DeviceInfo> list, String str) {
        if (TextUtils.isEmpty(str) || list == null) {
            return;
        }
        for (DeviceInfo deviceInfo : list) {
            if (deviceInfo != null && str.equals(deviceInfo.getDeviceIdentify())) {
                LogUtil.a("CompatibilityFitter", "refreshAssistantRelationship: ", blt.a(str));
                deviceInfo.setRelationship("assistant_relationship");
            }
        }
    }
}
