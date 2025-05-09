package defpackage;

import com.huawei.datatype.DeviceCommand;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwdevice.phoneprocess.mgr.hwmenstrualmanager.bean.MenstrualSwitchStatus;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes6.dex */
public class omz {
    private static omz c;
    private static final Object e = new Object();

    private omz() {
    }

    public static omz a() {
        omz omzVar;
        synchronized (e) {
            if (c == null) {
                c = new omz();
            }
            omzVar = c;
        }
        return omzVar;
    }

    public void b(MenstrualSwitchStatus menstrualSwitchStatus) {
        jfq.c().e(menstrualSwitchStatus);
    }

    public void e(DeviceInfo deviceInfo) {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(50);
        deviceCommand.setCommandID(5);
        StringBuffer stringBuffer = new StringBuffer(16);
        stringBuffer.append(cvx.e(1)).append(cvx.e(1)).append(cvx.e(cwi.c(deviceInfo, 174) ? 3 : 2));
        deviceCommand.setDataContent(cvx.a(stringBuffer.toString()));
        deviceCommand.setDataLen(cvx.a(stringBuffer.toString()).length);
        if (deviceInfo != null) {
            deviceCommand.setmIdentify(deviceInfo.getDeviceIdentify());
        }
        LogUtil.a("HwMenstrualInteractor", "sendCapability, deviceCommand:", deviceCommand.toString());
        jfq.c().b(deviceCommand);
    }

    public void c() {
        b();
    }

    private static void b() {
        synchronized (e) {
            c = null;
        }
    }
}
