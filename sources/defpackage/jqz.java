package defpackage;

import com.huawei.datatype.DeviceCommand;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes5.dex */
public class jqz {
    public static DeviceCommand e(int i, DeviceInfo deviceInfo) {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(45);
        deviceCommand.setCommandID(1);
        String e = cvx.e(i);
        String d = cvx.d(1);
        String e2 = cvx.e(1);
        StringBuilder sb = new StringBuilder(0);
        sb.append(e2);
        sb.append(d);
        sb.append(e);
        deviceCommand.setDataLen(sb.length() / 2);
        deviceCommand.setDataContent(cvx.a(sb.toString()));
        LogUtil.a("BreatheSendCommandGenerator", "setSleepBreatheSwitch command data :", cvx.d(deviceCommand.getDataContent()));
        deviceCommand.setmIdentify(deviceInfo.getDeviceIdentify());
        return deviceCommand;
    }
}
