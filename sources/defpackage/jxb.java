package defpackage;

import com.huawei.datatype.DeviceCommand;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.List;

/* loaded from: classes5.dex */
public class jxb {
    public static void b() {
        LogUtil.a("FitnessSendCommandAw70Util", "sendGetTodayTotalCommand enter");
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(7);
        deviceCommand.setCommandID(3);
        byte[] c = jwu.c();
        deviceCommand.setDataLen(c.length);
        deviceCommand.setDataContent(c);
        deviceCommand.setNeedAck(true);
        deviceCommand.setmIdentify(e());
        jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
    }

    public static void c(long j, long j2) {
        LogUtil.a("FitnessSendCommandAw70Util", "sendGetSampleFrameCountCommand enter start:", Long.valueOf(j), " end:", Long.valueOf(j2));
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(7);
        deviceCommand.setCommandID(10);
        byte[] e = jwu.e(j, j2);
        deviceCommand.setDataLen(e.length);
        deviceCommand.setDataContent(e);
        deviceCommand.setNeedAck(true);
        deviceCommand.setmIdentify(e());
        jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
    }

    public static void b(int i) {
        LogUtil.a("FitnessSendCommandAw70Util", "sendGetSampleFrameCommand enter index:", Integer.valueOf(i));
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(7);
        deviceCommand.setCommandID(11);
        byte[] e = jwu.e(i);
        deviceCommand.setDataLen(e.length);
        deviceCommand.setDataContent(e);
        deviceCommand.setNeedAck(true);
        deviceCommand.setmIdentify(e());
        jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
    }

    public static void e(long j, long j2) {
        LogUtil.a("FitnessSendCommandAw70Util", "sendGetStatusFrameCountCommand enter start:", Long.valueOf(j), " end:", Long.valueOf(j2));
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(7);
        deviceCommand.setCommandID(12);
        byte[] d = jwu.d(j, j2);
        deviceCommand.setDataLen(d.length);
        deviceCommand.setDataContent(d);
        deviceCommand.setNeedAck(true);
        deviceCommand.setmIdentify(e());
        jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
    }

    public static void c(int i) {
        LogUtil.a("FitnessSendCommandAw70Util", "sendGetStatusFrameCommand enter index:", Integer.valueOf(i));
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(7);
        deviceCommand.setCommandID(13);
        byte[] d = jwu.d(i);
        deviceCommand.setDataLen(d.length);
        deviceCommand.setDataContent(d);
        deviceCommand.setNeedAck(true);
        deviceCommand.setmIdentify(e());
        jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
    }

    public static void a(List<jqe> list) {
        LogUtil.c("FitnessSendCommandAw70Util", " sendSetDeviceReportThreshold deviceReportThresholdList:", list);
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(7);
        deviceCommand.setCommandID(14);
        byte[] d = jwu.d(list);
        deviceCommand.setDataLen(d.length);
        deviceCommand.setDataContent(d);
        deviceCommand.setmIdentify(e());
        jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
    }

    public static void b(long j, long j2, int i) {
        LogUtil.a("FitnessSendCommandAw70Util", "sendDataFrameCommand enter start:", Long.valueOf(j), " end:", Long.valueOf(j2));
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(7);
        deviceCommand.setCommandID(31);
        StringBuffer stringBuffer = new StringBuffer(16);
        stringBuffer.append(cvx.e(2)).append(cvx.e(4)).append(cvx.b(j));
        stringBuffer.append(cvx.e(3)).append(cvx.e(4)).append(cvx.b(j2));
        stringBuffer.append(cvx.e(4)).append(cvx.e(1)).append(cvx.e(i));
        deviceCommand.setDataLen(stringBuffer.length() / 2);
        deviceCommand.setDataContent(cvx.a(stringBuffer.toString()));
        deviceCommand.setNeedAck(true);
        deviceCommand.setmIdentify(e());
        jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
    }

    public static void d(int i) {
        LogUtil.a("FitnessSendCommandAw70Util", "sendSamplePointDataCommand enter index:", Integer.valueOf(i));
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(7);
        deviceCommand.setCommandID(32);
        StringBuffer stringBuffer = new StringBuffer(16);
        stringBuffer.append(cvx.e(1)).append(cvx.e(2)).append(cvx.a(i));
        deviceCommand.setDataLen(stringBuffer.length() / 2);
        deviceCommand.setDataContent(cvx.a(stringBuffer.toString()));
        deviceCommand.setNeedAck(true);
        deviceCommand.setmIdentify(e());
        jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
    }

    public static String e() {
        DeviceInfo b = jxi.b("FitnessSendCommandAw70Util");
        return b != null ? b.getDeviceIdentify() : "";
    }
}
