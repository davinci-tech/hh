package defpackage;

import android.text.TextUtils;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.devicemgr.business.entity.HwGetDevicesParameter;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class jpx {
    protected jpx() {
    }

    public static List<DeviceInfo> b(String str) {
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_DEVICES, null, str);
        if (deviceList == null) {
            LogUtil.h("HwGetDeviceInfoBase", "getAllConnectDeviceList() deviceInfoList is null.", str);
            return deviceList;
        }
        LogUtil.a("HwGetDeviceInfoBase", "getAllConnectDeviceList deviceInfoList size ", Integer.valueOf(deviceList.size()));
        ArrayList arrayList = new ArrayList(10);
        for (DeviceInfo deviceInfo : deviceList) {
            if ("main_relationship".equals(deviceInfo.getRelationship()) || cvt.c(deviceInfo.getProductType())) {
                arrayList.add(deviceInfo);
            }
        }
        LogUtil.a("HwGetDeviceInfoBase", "getAllConnectDeviceList connectDeviceList size ", Integer.valueOf(arrayList.size()), " , tag is ", str);
        return arrayList;
    }

    public static DeviceInfo e(String str, String str2) {
        DeviceInfo deviceInfo = null;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.ACTIVE_DEVICES, null, str2);
        if (deviceList == null || deviceList.isEmpty()) {
            LogUtil.h("HwGetDeviceInfoBase", "getDefiniteActiveDeviceInfo deviceInfoList is null.", str2);
            return null;
        }
        Iterator<DeviceInfo> it = deviceList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            DeviceInfo next = it.next();
            if (next.getDeviceActiveState() == 1 && str.equals(next.getDeviceIdentify())) {
                deviceInfo = next;
                break;
            }
        }
        LogUtil.a("HwGetDeviceInfoBase", "getCertainDeviceInfo deviceInfo ", deviceInfo, " , tag is ", str2);
        return deviceInfo;
    }

    public static DeviceInfo b(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        HwGetDevicesParameter hwGetDevicesParameter = new HwGetDevicesParameter();
        hwGetDevicesParameter.setDeviceIdentify(str);
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.IDENTIFY_DEVICES, hwGetDevicesParameter, str2);
        if (deviceList == null || deviceList.isEmpty()) {
            LogUtil.h("HwGetDeviceInfoBase", "getDefiniteDeviceInfo deviceInfoList is null", str2);
            return null;
        }
        return deviceList.get(0);
    }
}
