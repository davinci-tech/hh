package defpackage;

import com.huawei.health.devicemgr.business.entity.DeviceParameter;
import com.huawei.hwdevice.phoneprocess.framework.hwdevicemgr.unitedevicemanger.HwAddDeviceInterface;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes5.dex */
public class jsv implements HwAddDeviceInterface {
    @Override // com.huawei.hwdevice.phoneprocess.framework.hwdevicemgr.unitedevicemanger.HwAddDeviceInterface
    public void addDeviceHelper(DeviceParameter deviceParameter) {
        if (deviceParameter == null) {
            LogUtil.h("HwAddAwDevice", "addBrDevice deviceParameter null.");
            return;
        }
        int g = iyl.d().g();
        LogUtil.a("HwAddAwDevice", "addBrDevice enter btSwitchState: ", Integer.valueOf(g));
        if (g == 1) {
            jte.e(1, 3, deviceParameter);
        } else if (g == 3) {
            deviceParameter.setMac("AndroidWear");
            jtd.b().c(deviceParameter);
        } else {
            LogUtil.a("HwAddAwDevice", "switch other status");
        }
    }
}
