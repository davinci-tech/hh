package defpackage;

import com.huawei.health.devicemgr.business.entity.DeviceParameter;
import com.huawei.hwdevice.phoneprocess.framework.hwdevicemgr.unitedevicemanger.HwAddDeviceInterface;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes5.dex */
public class jsw implements HwAddDeviceInterface {
    @Override // com.huawei.hwdevice.phoneprocess.framework.hwdevicemgr.unitedevicemanger.HwAddDeviceInterface
    public void addDeviceHelper(DeviceParameter deviceParameter) {
        if (deviceParameter == null) {
            LogUtil.h("HwAddBleDevice", "addBrDevice deviceParameter null.");
            return;
        }
        int g = iyl.d().g();
        boolean e = jte.e();
        LogUtil.a("HwAddBleDevice", "addBleDevice enter btSwitchState: ", Integer.valueOf(g), "isGpsOpen:", Boolean.valueOf(e));
        if (g == 1) {
            if (e) {
                jte.e(1, 3, deviceParameter);
                return;
            } else {
                jte.e(1, 1, deviceParameter);
                return;
            }
        }
        if (g != 3) {
            LogUtil.a("HwAddBleDevice", "switch other status");
        } else if (e) {
            jte.e(3, 3, deviceParameter);
        } else {
            jte.e(1, 2, deviceParameter);
        }
    }
}
