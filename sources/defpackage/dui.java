package defpackage;

import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.sport.utils.SportSupportUtil;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.gsy;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class dui {
    public static List<DeviceInfo> a(int i) {
        ArrayList arrayList = new ArrayList();
        a(arrayList);
        b(arrayList, i);
        return arrayList;
    }

    private static void a(List<DeviceInfo> list) {
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, "HWhealthLinkage_LinkedDeviceInteractor");
        if (koq.b(deviceList)) {
            return;
        }
        for (DeviceInfo deviceInfo : deviceList) {
            if (deviceInfo == null) {
                LogUtil.h("HWhealthLinkage_LinkedDeviceInteractor", "deviceInfo is null in getDeviceTypeIdList");
            } else if (dum.d().h() || deviceInfo.getProductType() == 11) {
                list.add(deviceInfo);
            }
        }
    }

    private static void b(List<DeviceInfo> list, int i) {
        if (SportSupportUtil.f(i)) {
            List<gsy.b> e = gsy.b().e(i);
            if (koq.c(e)) {
                for (gsy.b bVar : e) {
                    if (bVar == null || bVar.e() == null) {
                        LogUtil.h("HWhealthLinkage_LinkedDeviceInteractor", "deviceInfo is null in setBoltDeviceId");
                    } else {
                        list.add(bVar.e());
                    }
                }
                return;
            }
            List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_AW70_DEVICES, null, "HWhealthLinkage_LinkedDeviceInteractor");
            if (koq.b(deviceList)) {
                return;
            }
            for (DeviceInfo deviceInfo : deviceList) {
                if (deviceInfo == null || !cvt.a(deviceInfo.getProductType(), deviceInfo.getAutoDetectSwitchStatus())) {
                    LogUtil.h("HWhealthLinkage_LinkedDeviceInteractor", "deviceInfo is null or not run posture.");
                } else {
                    list.add(deviceInfo);
                }
            }
        }
    }
}
