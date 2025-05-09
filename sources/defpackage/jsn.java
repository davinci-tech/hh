package defpackage;

import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.devicemgr.business.entity.HwGetDevicesParameter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* loaded from: classes.dex */
public class jsn {

    /* renamed from: a, reason: collision with root package name */
    private static final Set<Integer> f14053a = new HashSet<Integer>(24) { // from class: jsn.4
        private static final long serialVersionUID = 645739058358418245L;

        {
            add(7);
            add(8);
            add(13);
            add(14);
            add(15);
            add(16);
            add(18);
            add(19);
            add(20);
            add(21);
            add(34);
            add(35);
            add(44);
            add(45);
            add(55);
            add(57);
            add(58);
            add(60);
            add(61);
            add(64);
            add(65);
            add(66);
            add(71);
            add(72);
            add(73);
        }
    };

    public static boolean c() {
        DeviceInfo deviceInfo = null;
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, "UdsHelper");
        if (deviceList.size() > 0) {
            Iterator<DeviceInfo> it = deviceList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                DeviceInfo next = it.next();
                if (!cvt.c(next.getProductType())) {
                    deviceInfo = next;
                    break;
                }
            }
        }
        return deviceInfo != null && d(deviceInfo.getProductType());
    }

    public static boolean a(String str) {
        DeviceInfo deviceInfo;
        HwGetDevicesParameter hwGetDevicesParameter = new HwGetDevicesParameter();
        hwGetDevicesParameter.setDeviceIdentify(str);
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.IDENTIFY_DEVICES, hwGetDevicesParameter, "UdsHelper");
        return deviceList.size() > 0 && (deviceInfo = deviceList.get(0)) != null && d(deviceInfo.getProductType());
    }

    public static boolean d(int i) {
        return f14053a.contains(Integer.valueOf(i)) || i >= 72;
    }
}
