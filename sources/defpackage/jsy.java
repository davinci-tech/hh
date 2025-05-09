package defpackage;

import android.text.TextUtils;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.devicemgr.business.entity.HwGetDevicesParameter;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
class jsy {

    /* renamed from: a, reason: collision with root package name */
    private static jsy f14058a;
    private static jsx d;
    private static final Object e = new Object();

    private jsy() {
        LogUtil.h("HwGetDeviceListMgr", "Init HwGetDeviceListMgr.");
        d = jsx.c();
    }

    public static jsy b() {
        jsy jsyVar;
        synchronized (e) {
            if (f14058a == null) {
                f14058a = new jsy();
            }
            jsyVar = f14058a;
        }
        return jsyVar;
    }

    public List<DeviceInfo> a() {
        ArrayList arrayList = new ArrayList(24);
        arrayList.clear();
        HashSet hashSet = new HashSet(16);
        Iterator<DeviceInfo> it = jsz.b().d().iterator();
        while (it.hasNext()) {
            DeviceInfo a2 = d.a(it.next());
            arrayList.add(a2);
            String deviceName = a2.getDeviceName();
            if (!hashSet.add(deviceName)) {
                LogUtil.a("HwGetDeviceListMgr", "return getAllDeviceList() isExist:", deviceName);
            }
        }
        if (arrayList.size() != 0) {
            LogUtil.a("HwGetDeviceListMgr", "getAllDeviceList deviceInfoListBaks size is ", Integer.valueOf(arrayList.size()));
        }
        return jta.d().d(arrayList);
    }

    public List<DeviceInfo> i() {
        ArrayList arrayList = new ArrayList(24);
        arrayList.clear();
        if (jsz.b().d() == null || jsz.b().d().isEmpty()) {
            LogUtil.h("HwGetDeviceListMgr", "return getConnectedMainDeviceList() size is 0.");
            return arrayList;
        }
        for (DeviceInfo deviceInfo : jsz.b().d()) {
            if (deviceInfo.getDeviceConnectState() == 2 && deviceInfo.getAutoDetectSwitchStatus() != 1) {
                LogUtil.a("HwGetDeviceListMgr", "getConnectedMainDeviceList Connected Device :", deviceInfo.getDeviceName());
                arrayList.add(deviceInfo);
            }
        }
        if (arrayList.size() != 0) {
            LogUtil.a("HwGetDeviceListMgr", "getConnectedMainDeviceList deviceInfoListBaks size is ", Integer.valueOf(arrayList.size()));
        }
        return jta.d().d(arrayList);
    }

    public List<DeviceInfo> e() {
        ArrayList arrayList = new ArrayList(24);
        arrayList.clear();
        if (jsz.b().d() != null && !jsz.b().d().isEmpty()) {
            for (DeviceInfo deviceInfo : jsz.b().d()) {
                if (deviceInfo.getDeviceConnectState() == 2) {
                    LogUtil.a("HwGetDeviceListMgr", "getConnectedDeviceList Connected Device :", deviceInfo.getDeviceName());
                    arrayList.add(deviceInfo);
                }
            }
        }
        if (arrayList.size() != 0) {
            LogUtil.a("HwGetDeviceListMgr", "getConnectedDeviceList deviceInfoListBaks size is ", Integer.valueOf(arrayList.size()));
        }
        return jta.d().d(arrayList);
    }

    public List<DeviceInfo> d() {
        ArrayList arrayList = new ArrayList(24);
        if (jsz.b().d() != null && !jsz.b().d().isEmpty()) {
            for (DeviceInfo deviceInfo : jsz.b().d()) {
                if (deviceInfo.getDeviceActiveState() == 1 && deviceInfo.getDeviceConnectState() == 2 && cvt.c(deviceInfo.getProductType())) {
                    LogUtil.a("HwGetDeviceListMgr", "getConnectedAw70Device device name:", deviceInfo.getDeviceName());
                    arrayList.add(deviceInfo);
                }
            }
        }
        if (arrayList.size() != 0) {
            LogUtil.a("HwGetDeviceListMgr", "getConnectedAw70DeviceList deviceInfoListBaks size is ", Integer.valueOf(arrayList.size()));
        }
        return jta.d().d(arrayList);
    }

    public List<DeviceInfo> c() {
        ArrayList arrayList = new ArrayList(24);
        if (jsz.b().d() != null && !jsz.b().d().isEmpty()) {
            for (DeviceInfo deviceInfo : jsz.b().d()) {
                if (deviceInfo.getDeviceActiveState() == 1) {
                    LogUtil.a("HwGetDeviceListMgr", "get getActiveDeviceList() name ", deviceInfo.getDeviceName());
                    arrayList.add(deviceInfo);
                }
            }
        }
        if (arrayList.size() != 0) {
            LogUtil.a("HwGetDeviceListMgr", "getActiveDeviceList deviceInfoListBaks size is ", Integer.valueOf(arrayList.size()));
        }
        return jta.d().d(arrayList);
    }

    public List<DeviceInfo> b(HwGetDevicesParameter hwGetDevicesParameter) {
        ArrayList arrayList = new ArrayList(24);
        if (hwGetDevicesParameter == null) {
            LogUtil.h("HwGetDeviceListMgr", "getSpecificDeviceList parameter is null.");
            return arrayList;
        }
        String deviceIdentify = hwGetDevicesParameter.getDeviceIdentify();
        if (TextUtils.isEmpty(deviceIdentify)) {
            LogUtil.h("HwGetDeviceListMgr", "getSpecificDeviceList identify of parameter is null.");
            return arrayList;
        }
        String replaceAll = deviceIdentify.replaceAll(":", "");
        if (jsz.b().d() != null && !jsz.b().d().isEmpty()) {
            Iterator<DeviceInfo> it = jsz.b().d().iterator();
            String str = "";
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                DeviceInfo next = it.next();
                String deviceIdentify2 = next.getDeviceIdentify();
                if (deviceIdentify2 != null) {
                    str = deviceIdentify2.replaceAll(":", "");
                }
                if (str.contains(replaceAll)) {
                    arrayList.add(next);
                    break;
                }
            }
        }
        if (arrayList.size() != 0) {
            LogUtil.a("HwGetDeviceListMgr", "getSpecificDeviceList deviceInfoListBaks size is ", Integer.valueOf(arrayList.size()));
        }
        return jta.d().d(arrayList);
    }

    public List<DeviceInfo> g() {
        ArrayList arrayList = new ArrayList(24);
        if (jsz.b().d() != null && !jsz.b().d().isEmpty()) {
            for (DeviceInfo deviceInfo : jsz.b().d()) {
                if (deviceInfo.getDeviceActiveState() == 1 && deviceInfo.getAutoDetectSwitchStatus() == 1) {
                    LogUtil.a("HwGetDeviceListMgr", "get getFollowedDeviceList() name ", deviceInfo.getDeviceName());
                    arrayList.add(deviceInfo);
                }
            }
        }
        if (arrayList.size() != 0) {
            LogUtil.a("HwGetDeviceListMgr", "getFollowedDeviceList deviceInfoListBaks size is ", Integer.valueOf(arrayList.size()));
        }
        return jta.d().d(arrayList);
    }
}
