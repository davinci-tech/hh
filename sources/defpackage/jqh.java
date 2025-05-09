package defpackage;

import android.text.TextUtils;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.Utils;
import java.util.HashMap;
import org.slf4j.Marker;

/* loaded from: classes5.dex */
public class jqh {
    public static ixz d(DeviceInfo deviceInfo) {
        ixz ixzVar = new ixz();
        if (deviceInfo == null) {
            return ixzVar;
        }
        ixzVar.c(deviceInfo.getSoftVersion());
        String udidFromDevice = deviceInfo.getUdidFromDevice();
        if (!TextUtils.isEmpty(udidFromDevice)) {
            ixzVar.b(udidFromDevice);
        } else {
            if (Utils.o()) {
                String replace = knl.d(deviceInfo.getSecurityDeviceId()).replace(Marker.ANY_NON_NULL_MARKER, "A").replace("=", "A").replace("/", "A");
                if (replace.length() >= 24) {
                    replace = replace.substring(0, 24);
                }
                ixzVar.b(replace);
            } else if (deviceInfo.getDeviceIdType() == 1) {
                String replace2 = knl.d(deviceInfo.getSecurityDeviceId()).replace(Marker.ANY_NON_NULL_MARKER, "A").replace("=", "A").replace("/", "A");
                if (replace2.length() >= 24) {
                    replace2 = replace2.substring(0, 24);
                }
                ixzVar.b(replace2);
            } else {
                ixzVar.b(deviceInfo.getDeviceIdentify());
            }
            if (deviceInfo.getProductType() >= 58) {
                ixzVar.b(deviceInfo.getDeviceUdid());
            }
        }
        e(deviceInfo, ixzVar);
        if (deviceInfo.getProductType() == 3 || deviceInfo.getProductType() == 10) {
            ixzVar.b(deviceInfo.getUuid());
        }
        ixzVar.h(deviceInfo.getUdidFromDevice());
        ixzVar.e(deviceInfo.getSecurityDeviceId());
        ixzVar.d(deviceInfo.getDeviceIdentify());
        ixzVar.b(deviceInfo.getProductType());
        return ixzVar;
    }

    private static void e(DeviceInfo deviceInfo, ixz ixzVar) {
        if (11 == deviceInfo.getProductType() && "HUAWEI CM-R1P".equals(deviceInfo.getDeviceName())) {
            ixzVar.a("R1-PRO");
            return;
        }
        if (deviceInfo.getProductType() == 10 && (TextUtils.equals(deviceInfo.getDeviceName(), "PORSCHE DESIGN") || TextUtils.equals(deviceInfo.getDeviceModel(), "PORSCHE DESIGN"))) {
            ixzVar.a("PORSCHE DESIGN");
        } else if (deviceInfo.getProductType() >= 34) {
            ixzVar.a(deviceInfo.getDeviceModel());
        } else {
            ixzVar.a(jpp.c(deviceInfo));
        }
    }

    public static void c(DeviceInfo deviceInfo) {
        LogUtil.a("WearDeviceBiUtil", "setBiAnalyticsData enter");
        ixz d = d(deviceInfo);
        if (deviceInfo.getDeviceConnectState() == 2) {
            ixx.d().b(d);
        } else if (deviceInfo.getDeviceConnectState() == 3) {
            ixx.d().a(d);
        } else {
            LogUtil.h("WearDeviceBiUtil", "setBiAnalyticsData other state");
        }
    }

    public static void a() {
        LogUtil.a("WearDeviceBiUtil", "setMainBiAnalyticsData enter");
        DeviceInfo a2 = jpt.a("WearDeviceBiUtil");
        if (a2 == null) {
            a2 = jpu.d("WearDeviceBiUtil");
        }
        if (a2 != null) {
            c(a2);
        }
    }

    public static void c(String str) {
        LogUtil.a("WearDeviceBiUtil", "entryMyWatchFaceBiEvent from：", str);
        HashMap hashMap = new HashMap(4);
        hashMap.put("event", 0);
        hashMap.put(FunctionSetBeanReader.BI_ELEMENT, "m0");
        hashMap.put("entrance", str);
        hashMap.put("moduleName", "main_page");
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.ENTRY_MY_WATCH_FACE_H9000001.value(), hashMap, 0);
    }

    public static void a(String str, String str2, String str3) {
        LogUtil.a("WearDeviceBiUtil", "operateWatchFaceBiEvent operate：", str, " operateStatus：", str2);
        HashMap hashMap = new HashMap(6);
        hashMap.put("event", 1);
        hashMap.put(FunctionSetBeanReader.BI_ELEMENT, str);
        hashMap.put("value", str2);
        hashMap.put("resourceInfo", str3);
        hashMap.put("entrance", "e1");
        hashMap.put("moduleName", "main_page");
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.INSTALL_UPDATE_DELETE_APPLY_WATCH_FACE_H9000005.value(), hashMap, 0);
    }

    public static void e(int i, String str, String str2) {
        LogUtil.a("WearDeviceBiUtil", "installAndApplyWatchFaceBiEvent operate：", Integer.valueOf(i), " operateStatus：", str);
        if (1 == i) {
            a("m1", str, str2);
        } else if (4 == i) {
            a("m2", str, str2);
        } else {
            LogUtil.a("WearDeviceBiUtil", "installAndApplyWatchFaceBiEvent  operate is other");
        }
    }
}
