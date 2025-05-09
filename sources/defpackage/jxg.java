package defpackage;

import com.huawei.datatype.DeviceCommand;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.HEXUtils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.List;

/* loaded from: classes5.dex */
public class jxg {
    public static DeviceCommand e(DeviceInfo deviceInfo) {
        ReleaseLogUtil.e("Step_HwBasicDataCommandUtil", "sendGetTodayTotalCmd enter");
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(7);
        deviceCommand.setCommandID(3);
        byte[] c = jwu.c();
        deviceCommand.setDataLen(c.length);
        deviceCommand.setDataContent(c);
        deviceCommand.setNeedAck(true);
        deviceCommand.setmIdentify(deviceInfo.getDeviceIdentify());
        return deviceCommand;
    }

    public static DeviceCommand a(long j, long j2, DeviceInfo deviceInfo) {
        LogUtil.a("HwBasicDataCommandUtil", "sendGetHealthDataByFrameCountCmd enter start:", Long.valueOf(j), " end:", Long.valueOf(j2));
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(7);
        deviceCommand.setCommandID(4);
        byte[] b = jwu.b(j, j2);
        deviceCommand.setDataLen(b.length);
        deviceCommand.setDataContent(b);
        deviceCommand.setNeedAck(true);
        if (deviceInfo != null) {
            deviceCommand.setmIdentify(deviceInfo.getDeviceIdentify());
        }
        return deviceCommand;
    }

    public static DeviceCommand d(int i, DeviceInfo deviceInfo) {
        LogUtil.a("HwBasicDataCommandUtil", "sendGetHealthDataByFrameCompressedCmd enter index:", Integer.valueOf(i));
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(7);
        deviceCommand.setCommandID(5);
        byte[] a2 = jwu.a(i);
        deviceCommand.setDataLen(a2.length);
        deviceCommand.setDataContent(a2);
        deviceCommand.setNeedAck(true);
        if (deviceInfo != null) {
            deviceCommand.setmIdentify(deviceInfo.getDeviceIdentify());
        }
        return deviceCommand;
    }

    public static DeviceCommand c(int i, DeviceInfo deviceInfo) {
        LogUtil.a("HwBasicDataCommandUtil", "sendGetHealthDataByFrameCmd enter index:", Integer.valueOf(i));
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(7);
        deviceCommand.setCommandID(8);
        byte[] e = jwu.e(i, i);
        deviceCommand.setDataLen(e.length);
        deviceCommand.setDataContent(e);
        deviceCommand.setNeedAck(true);
        if (deviceInfo != null) {
            deviceCommand.setmIdentify(deviceInfo.getDeviceIdentify());
        }
        return deviceCommand;
    }

    public static DeviceCommand c(long j, long j2) {
        ReleaseLogUtil.e("Step_HwBasicDataCommandUtil", "sendGetSampleFrameCountCmd enter startTime:", Long.valueOf(j), ", endTime:", Long.valueOf(j2));
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(7);
        deviceCommand.setCommandID(10);
        byte[] e = jwu.e(j, j2);
        deviceCommand.setDataLen(e.length);
        deviceCommand.setDataContent(e);
        deviceCommand.setNeedAck(true);
        return deviceCommand;
    }

    public static DeviceCommand a(int i, DeviceInfo deviceInfo) {
        LogUtil.a("HwBasicDataCommandUtil", "sendGetSampleFrameCmd enter index:", Integer.valueOf(i));
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(7);
        deviceCommand.setCommandID(11);
        byte[] e = jwu.e(i);
        deviceCommand.setDataLen(e.length);
        deviceCommand.setDataContent(e);
        deviceCommand.setNeedAck(true);
        if (deviceInfo != null) {
            deviceCommand.setmIdentify(deviceInfo.getDeviceIdentify());
        }
        return deviceCommand;
    }

    public static DeviceCommand b(long j, long j2) {
        LogUtil.a("HwBasicDataCommandUtil", "sendGetStatusFrameCountCmd enter start:", Long.valueOf(j), " end:", Long.valueOf(j2));
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(7);
        deviceCommand.setCommandID(12);
        byte[] d = jwu.d(j, j2);
        deviceCommand.setDataLen(d.length);
        deviceCommand.setDataContent(d);
        deviceCommand.setNeedAck(true);
        return deviceCommand;
    }

    public static DeviceCommand e(int i, DeviceInfo deviceInfo) {
        LogUtil.a("HwBasicDataCommandUtil", "sendGetStatusFrameCmd enter index:", Integer.valueOf(i));
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(7);
        deviceCommand.setCommandID(13);
        byte[] d = jwu.d(i);
        deviceCommand.setDataLen(d.length);
        deviceCommand.setDataContent(d);
        deviceCommand.setNeedAck(true);
        if (deviceInfo != null) {
            deviceCommand.setmIdentify(deviceInfo.getDeviceIdentify());
        }
        return deviceCommand;
    }

    public static DeviceCommand b(List<jqe> list) {
        LogUtil.a("HwBasicDataCommandUtil", "sendSetDeviceReportThreshold thresholdList: ", list);
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(7);
        deviceCommand.setCommandID(14);
        byte[] d = jwu.d(list);
        deviceCommand.setDataLen(d.length);
        deviceCommand.setDataContent(d);
        return deviceCommand;
    }

    public static DeviceCommand a() {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(7);
        deviceCommand.setCommandID(15);
        byte[] e = jwu.e();
        deviceCommand.setDataLen(e.length);
        deviceCommand.setDataContent(e);
        return deviceCommand;
    }

    public static DeviceCommand a(long j, long j2, int i) {
        LogUtil.a("HwBasicDataCommandUtil", "sendGetDesFrameCountCmd enter startTime:", Long.valueOf(j), " endTime:", Long.valueOf(j2));
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(7);
        deviceCommand.setCommandID(31);
        StringBuffer stringBuffer = new StringBuffer(16);
        stringBuffer.append(HEXUtils.e(2)).append(HEXUtils.e(4)).append(HEXUtils.e(j));
        stringBuffer.append(HEXUtils.e(3)).append(HEXUtils.e(4)).append(HEXUtils.e(j2));
        stringBuffer.append(HEXUtils.e(4)).append(HEXUtils.e(1)).append(HEXUtils.e(i));
        deviceCommand.setDataLen(stringBuffer.length() / 2);
        deviceCommand.setDataContent(HEXUtils.c(stringBuffer.toString()));
        deviceCommand.setNeedAck(true);
        return deviceCommand;
    }

    public static DeviceCommand b(int i, DeviceInfo deviceInfo) {
        LogUtil.a("HwBasicDataCommandUtil", "sendGetDesFrameCmd enter index:", Integer.valueOf(i));
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(7);
        deviceCommand.setCommandID(32);
        StringBuffer stringBuffer = new StringBuffer(16);
        stringBuffer.append(HEXUtils.e(1)).append(HEXUtils.e(2)).append(HEXUtils.d(i));
        deviceCommand.setDataLen(stringBuffer.length() / 2);
        deviceCommand.setDataContent(HEXUtils.c(stringBuffer.toString()));
        deviceCommand.setNeedAck(true);
        if (deviceInfo != null) {
            deviceCommand.setmIdentify(deviceInfo.getDeviceIdentify());
        }
        return deviceCommand;
    }
}
