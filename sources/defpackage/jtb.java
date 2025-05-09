package defpackage;

import com.huawei.health.devicemgr.business.entity.DeviceParameter;
import com.huawei.hwdevice.phoneprocess.framework.hwdevicemgr.unitedevicemanger.HwAddDeviceInterface;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes5.dex */
public class jtb implements HwAddDeviceInterface {
    @Override // com.huawei.hwdevice.phoneprocess.framework.hwdevicemgr.unitedevicemanger.HwAddDeviceInterface
    public void addDeviceHelper(DeviceParameter deviceParameter) {
        if (deviceParameter == null) {
            LogUtil.h("HwAddBrDevice", "addBrDevice deviceParameter null.");
            return;
        }
        int g = iyl.d().g();
        boolean z = !jte.a(deviceParameter.getBluetoothType()) || jte.e();
        boolean d = iyl.d().d(deviceParameter.getNameFilter());
        LogUtil.a("HwAddBrDevice", "addBrDevice enter btSwitchState: ", Integer.valueOf(g), "isGpsOpen: ", Boolean.valueOf(z), "isSystermDevice:", Boolean.valueOf(d));
        if (g == 1) {
            if (z) {
                jte.e(1, 3, deviceParameter);
                return;
            } else {
                jte.e(1, 1, deviceParameter);
                return;
            }
        }
        if (g != 3) {
            LogUtil.a("HwAddBrDevice", "switch other status");
            return;
        }
        if (!z) {
            jte.e(1, 2, deviceParameter);
        } else if (d) {
            LogUtil.a("HwAddBrDevice", "Has wanted BR device.");
            jte.e(1, 4, deviceParameter);
        } else {
            jte.e(3, 3, deviceParameter);
        }
    }
}
