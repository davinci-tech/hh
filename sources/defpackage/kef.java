package defpackage;

import com.huawei.datatype.DeviceCommand;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.HEXUtils;

/* loaded from: classes5.dex */
public class kef {
    public static DeviceCommand b(long j, long j2, boolean z) {
        LogUtil.a("HwBasicDataCommandUtil", "getHeartRateRaise enter.supportDownRate: ", Boolean.valueOf(z));
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(7);
        deviceCommand.setCommandID(30);
        byte[] d = new bms().i(1, (int) j).i(2, (int) j2).j(13, 1 ^ (z ? 1 : 0)).d();
        deviceCommand.setDataLen(d.length);
        deviceCommand.setDataContent(d);
        return deviceCommand;
    }

    public static DeviceCommand a(long j, long j2) {
        LogUtil.a("HwBasicDataCommandUtil", "getBloodOxygenDownRemindCount enter");
        DeviceCommand deviceCommand = new DeviceCommand();
        if (j >= j2) {
            LogUtil.h("HwBasicDataCommandUtil", "getBloodOxygenDownRemindCount time is error");
            return deviceCommand;
        }
        deviceCommand.setServiceID(7);
        deviceCommand.setCommandID(38);
        StringBuilder sb = new StringBuilder(16);
        StringBuilder sb2 = new StringBuilder(16);
        sb.append(HEXUtils.e(1));
        sb.append(HEXUtils.e(4));
        sb.append(HEXUtils.e(j));
        sb2.append(HEXUtils.e(2));
        sb2.append(HEXUtils.e(4));
        sb2.append(HEXUtils.e(j2));
        byte[] c = HEXUtils.c(sb.toString() + sb2.toString());
        deviceCommand.setDataLen(c.length);
        deviceCommand.setDataContent(c);
        LogUtil.a("HwBasicDataCommandUtil", "getBloodOxygenDownRemindCount dataInfos: ", HEXUtils.a(c));
        return deviceCommand;
    }

    public static DeviceCommand c(int i, DeviceInfo deviceInfo) {
        LogUtil.a("HwBasicDataCommandUtil", "getBloodOxygenDownRemindDetail enter");
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(7);
        deviceCommand.setCommandID(39);
        byte[] d = new bms().h(1, i).d();
        deviceCommand.setDataLen(d.length);
        deviceCommand.setDataContent(d);
        if (deviceInfo != null) {
            deviceCommand.setmIdentify(deviceInfo.getDeviceIdentify());
        }
        blt.d("HwBasicDataCommandUtil", d, "getBloodOxygenDownRemindDetail dataInfos: ");
        return deviceCommand;
    }
}
