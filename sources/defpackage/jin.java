package defpackage;

import com.huawei.datatype.DeviceCommand;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcommonmodel.fitnessdatatype.MotionGoal;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.up.model.UserInfomation;
import java.util.List;

/* loaded from: classes5.dex */
public class jin {
    private static boolean c() {
        return true;
    }

    public static void c(List<MotionGoal> list) {
        LogUtil.a("FitnessSendCommandAw70Util", "sendSetMotionGoalCommand enter");
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(7);
        deviceCommand.setCommandID(1);
        byte[] c = jhn.c(list);
        deviceCommand.setDataLen(c.length);
        deviceCommand.setDataContent(c);
        deviceCommand.setmIdentify(a());
        jfq.c().b(deviceCommand);
    }

    public static void e(UserInfomation userInfomation) {
        if (jpu.e("FitnessSendCommandAw70Util") == null) {
            LogUtil.h("FitnessSendCommandAw70Util", "sendSetUserInfoCommand is not aw70");
            return;
        }
        LogUtil.a("FitnessSendCommandAw70Util", "sendSetUserInfoCommand enter");
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setCommandID(2);
        if (c()) {
            LogUtil.a("FitnessSendCommandAw70Util", "sendSetUserInfoCommand need encrypt enter");
            deviceCommand.setNeedEncrypt(true);
        } else {
            deviceCommand.setNeedEncrypt(false);
        }
        deviceCommand.setServiceID(7);
        byte[] d = jhn.d(userInfomation, jpu.d("FitnessSendCommandAw70Util"));
        deviceCommand.setDataLen(d.length);
        deviceCommand.setDataContent(d);
        deviceCommand.setmIdentify(a());
        jfq.c().b(deviceCommand);
    }

    public static void e() {
        LogUtil.a("FitnessSendCommandAw70Util", "sendGetTodayTotalCommand enter");
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(7);
        deviceCommand.setCommandID(3);
        byte[] a2 = jhn.a();
        deviceCommand.setDataLen(a2.length);
        deviceCommand.setDataContent(a2);
        deviceCommand.setNeedAck(true);
        deviceCommand.setmIdentify(a());
        jfq.c().b(deviceCommand);
    }

    public static void e(long j, long j2) {
        LogUtil.a("FitnessSendCommandAw70Util", "sendGetSampleFrameCountCommand enter start:", Long.valueOf(j), " end:", Long.valueOf(j2));
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(7);
        deviceCommand.setCommandID(10);
        byte[] e = jhn.e(j, j2);
        deviceCommand.setDataLen(e.length);
        deviceCommand.setDataContent(e);
        deviceCommand.setNeedAck(true);
        deviceCommand.setmIdentify(a());
        jfq.c().b(deviceCommand);
    }

    public static void d(int i) {
        LogUtil.a("FitnessSendCommandAw70Util", "sendGetSampleFrameCommand enter index:", Integer.valueOf(i));
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(7);
        deviceCommand.setCommandID(11);
        byte[] e = jhn.e(i);
        deviceCommand.setDataLen(e.length);
        deviceCommand.setDataContent(e);
        deviceCommand.setNeedAck(true);
        deviceCommand.setmIdentify(a());
        jfq.c().b(deviceCommand);
    }

    public static void c(long j, long j2) {
        LogUtil.a("FitnessSendCommandAw70Util", "sendGetStatusFrameCountCommand enter start:", Long.valueOf(j), " end:", Long.valueOf(j2));
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(7);
        deviceCommand.setCommandID(12);
        byte[] c = jhn.c(j, j2);
        deviceCommand.setDataLen(c.length);
        deviceCommand.setDataContent(c);
        deviceCommand.setNeedAck(true);
        deviceCommand.setmIdentify(a());
        jfq.c().b(deviceCommand);
    }

    public static void e(int i) {
        LogUtil.a("FitnessSendCommandAw70Util", "sendGetStatusFrameCommand enter index:", Integer.valueOf(i));
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(7);
        deviceCommand.setCommandID(13);
        byte[] b = jhn.b(i);
        deviceCommand.setDataLen(b.length);
        deviceCommand.setDataContent(b);
        deviceCommand.setNeedAck(true);
        deviceCommand.setmIdentify(a());
        jfq.c().b(deviceCommand);
    }

    public static void e(List<jid> list) {
        LogUtil.c("FitnessSendCommandAw70Util", " sendSetDeviceReportThreshold deviceReportThresholdList:", list);
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(7);
        deviceCommand.setCommandID(14);
        byte[] a2 = jhn.a(list);
        deviceCommand.setDataLen(a2.length);
        deviceCommand.setDataContent(a2);
        deviceCommand.setmIdentify(a());
        jfq.c().b(deviceCommand);
    }

    public static void d() {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(7);
        deviceCommand.setCommandID(21);
        byte[] c = jhn.c();
        deviceCommand.setDataLen(c.length);
        deviceCommand.setDataContent(c);
        deviceCommand.setmIdentify(a());
        jfq.c().b(deviceCommand);
    }

    public static void d(long j, long j2, int i) {
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
        deviceCommand.setmIdentify(a());
        jfq.c().b(deviceCommand);
    }

    public static void a(int i) {
        LogUtil.a("FitnessSendCommandAw70Util", "sendSamplePointDataCommand enter index:", Integer.valueOf(i));
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(7);
        deviceCommand.setCommandID(32);
        byte[] d = new bms().h(1, i).d();
        deviceCommand.setDataLen(d.length);
        deviceCommand.setDataContent(d);
        deviceCommand.setNeedAck(true);
        deviceCommand.setmIdentify(a());
        jfq.c().b(deviceCommand);
    }

    public static String a() {
        DeviceInfo e = jpu.e("FitnessSendCommandAw70Util");
        return e != null ? e.getDeviceIdentify() : "";
    }
}
