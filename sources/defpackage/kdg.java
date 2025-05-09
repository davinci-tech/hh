package defpackage;

import com.huawei.datatype.DeviceCommand;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes5.dex */
public class kdg {
    public static void c(int i, DeviceInfo deviceInfo) {
        jsz.b(BaseApplication.getContext()).sendDeviceData(jqz.e(i, deviceInfo));
    }

    public static void b(DeviceInfo deviceInfo) {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(45);
        deviceCommand.setCommandID(2);
        String e = cvx.e(1);
        String d = cvx.d(1);
        String e2 = cvx.e(1);
        StringBuilder sb = new StringBuilder(0);
        sb.append(e2);
        sb.append(d);
        sb.append(e);
        deviceCommand.setDataLen(sb.length() / 2);
        deviceCommand.setDataContent(cvx.a(sb.toString()));
        deviceCommand.setmIdentify(deviceInfo.getDeviceIdentify());
        LogUtil.a("BreathingSendCommandUtil", "setClearSleepBreatheCommand command data :", cvx.d(deviceCommand.getDataContent()));
        jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
    }
}
