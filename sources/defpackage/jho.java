package defpackage;

import android.text.TextUtils;
import com.huawei.datatype.DeviceCommand;
import com.huawei.datatype.RunPaceZoneConfig;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcommonmodel.fitnessdatatype.HeartZoneConf;
import com.huawei.hwcommonmodel.fitnessdatatype.MotionGoal;
import com.huawei.hwcommonmodel.fitnessdatatype.StudentHeartRateZoneMgr;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.up.model.UserInfomation;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class jho {
    public static void d(List<MotionGoal> list) {
        LogUtil.a("FitnessSendCommandUtil", "sendSetMotionGoalCmd enter");
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(7);
        deviceCommand.setCommandID(1);
        byte[] c = jhn.c(list);
        deviceCommand.setDataLen(c.length);
        deviceCommand.setDataContent(c);
        jfq.c().b(deviceCommand);
    }

    private static boolean a(DeviceInfo deviceInfo) {
        if (deviceInfo != null) {
            LogUtil.a("FitnessSendCommandUtil", "isNeedEncrypt Encrypt type:", Integer.valueOf(deviceInfo.getEncryptType()));
            return deviceInfo.getEncryptType() == 1;
        }
        LogUtil.h("FitnessSendCommandUtil", "deviceInfo is null");
        return false;
    }

    public static void c(UserInfomation userInfomation, DeviceInfo deviceInfo) {
        LogUtil.a("FitnessSendCommandUtil", "sendSetUserInfoCmd enter");
        if (deviceInfo == null || userInfomation == null) {
            LogUtil.h("FitnessSendCommandUtil", "sendSetUserInfoCmd params error");
        } else if (jhb.a() && jhb.d(deviceInfo.getDeviceIdentify())) {
            LogUtil.a("FitnessSendCommandUtil", "sendSetUserInfoCmd pair mode is family.");
        } else {
            a(userInfomation, deviceInfo);
        }
    }

    public static void a(UserInfomation userInfomation, DeviceInfo deviceInfo) {
        LogUtil.a("FitnessSendCommandUtil", "sendSetUserInfoCommandDetail enter");
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setCommandID(2);
        if (a(deviceInfo)) {
            LogUtil.a("FitnessSendCommandUtil", "sendSetUserInfoCommandDetail need encrypt enter");
            deviceCommand.setNeedEncrypt(true);
        } else {
            deviceCommand.setNeedEncrypt(false);
        }
        deviceCommand.setServiceID(7);
        byte[] d = jhn.d(userInfomation, deviceInfo);
        deviceCommand.setDataLen(d.length);
        deviceCommand.setDataContent(d);
        if (deviceInfo != null) {
            deviceCommand.setmIdentify(deviceInfo.getDeviceIdentify());
        }
        jfq.c().b(deviceCommand);
    }

    public static void e(DeviceInfo deviceInfo) {
        LogUtil.a("FitnessSendCommandUtil", "queryStudentInfoCommand enter");
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(7);
        deviceCommand.setCommandID(2);
        if (a(deviceInfo)) {
            LogUtil.a("FitnessSendCommandUtil", "queryStudentInfoCommand need encrypt enter");
            deviceCommand.setNeedEncrypt(true);
        } else {
            deviceCommand.setNeedEncrypt(false);
        }
        byte[] b = jhn.b();
        deviceCommand.setDataLen(b.length);
        deviceCommand.setDataContent(b);
        if (deviceInfo != null) {
            deviceCommand.setmIdentify(deviceInfo.getDeviceIdentify());
        }
        jfq.c().b(deviceCommand);
    }

    public static void d(DeviceInfo deviceInfo) {
        ReleaseLogUtil.e("DEVMGR_FitnessSendCommandUtil", "sendGetTodayTotalCmd enter");
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(7);
        deviceCommand.setCommandID(3);
        byte[] a2 = jhn.a();
        deviceCommand.setDataLen(a2.length);
        deviceCommand.setDataContent(a2);
        deviceCommand.setNeedAck(true);
        if (deviceInfo != null) {
            deviceCommand.setmIdentify(deviceInfo.getDeviceIdentify());
        }
        jrb.b("FitnessSendCommandUtil", deviceCommand.getServiceID(), deviceCommand.getCommandID());
        jfq.c().b(deviceCommand);
    }

    public static void b(long j, long j2, DeviceInfo deviceInfo) {
        LogUtil.a("FitnessSendCommandUtil", "sendGetHealthDataByFrameCountCmd enter start:", Long.valueOf(j), " end:", Long.valueOf(j2));
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(7);
        deviceCommand.setCommandID(4);
        byte[] a2 = jhn.a(j, j2);
        deviceCommand.setDataLen(a2.length);
        deviceCommand.setDataContent(a2);
        deviceCommand.setNeedAck(true);
        if (deviceInfo != null) {
            deviceCommand.setmIdentify(deviceInfo.getDeviceIdentify());
        }
        jfq.c().b(deviceCommand);
    }

    public static void e(int i, DeviceInfo deviceInfo) {
        LogUtil.a("FitnessSendCommandUtil", "sendGetHealthDataByFrameCompressedCmd enter index:", Integer.valueOf(i));
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(7);
        deviceCommand.setCommandID(5);
        byte[] d = jhn.d(i);
        deviceCommand.setDataLen(d.length);
        deviceCommand.setDataContent(d);
        deviceCommand.setNeedAck(true);
        if (deviceInfo != null) {
            deviceCommand.setmIdentify(deviceInfo.getDeviceIdentify());
        }
        jfq.c().b(deviceCommand);
    }

    public static void a(int i, DeviceInfo deviceInfo) {
        LogUtil.a("FitnessSendCommandUtil", "sendGetHealthDataByFrameCmd enter index:", Integer.valueOf(i));
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(7);
        deviceCommand.setCommandID(8);
        byte[] e = jhn.e(i, i);
        deviceCommand.setDataLen(e.length);
        deviceCommand.setDataContent(e);
        deviceCommand.setNeedAck(true);
        if (deviceInfo != null) {
            deviceCommand.setmIdentify(deviceInfo.getDeviceIdentify());
        }
        jfq.c().b(deviceCommand);
    }

    public static void a(long j, long j2) {
        ReleaseLogUtil.e("DEVMGR_FitnessSendCommandUtil", "sendGetSampleFrameCountCmd enter startTime:", Long.valueOf(j), ", endTime:", Long.valueOf(j2));
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(7);
        deviceCommand.setCommandID(10);
        byte[] e = jhn.e(j, j2);
        deviceCommand.setDataLen(e.length);
        deviceCommand.setDataContent(e);
        deviceCommand.setNeedAck(true);
        jfq.c().b(deviceCommand);
    }

    public static void d(int i, DeviceInfo deviceInfo) {
        LogUtil.a("FitnessSendCommandUtil", "sendGetSampleFrameCmd enter index:", Integer.valueOf(i));
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(7);
        deviceCommand.setCommandID(11);
        byte[] e = jhn.e(i);
        deviceCommand.setDataLen(e.length);
        deviceCommand.setDataContent(e);
        deviceCommand.setNeedAck(true);
        if (deviceInfo != null) {
            deviceCommand.setmIdentify(deviceInfo.getDeviceIdentify());
        }
        jfq.c().b(deviceCommand);
    }

    public static void b(long j, long j2) {
        LogUtil.a("FitnessSendCommandUtil", "sendGetStatusFrameCountCmd enter start:", Long.valueOf(j), " end:", Long.valueOf(j2));
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(7);
        deviceCommand.setCommandID(12);
        byte[] c = jhn.c(j, j2);
        deviceCommand.setDataLen(c.length);
        deviceCommand.setDataContent(c);
        deviceCommand.setNeedAck(true);
        jfq.c().b(deviceCommand);
    }

    public static void c(int i, DeviceInfo deviceInfo) {
        LogUtil.a("FitnessSendCommandUtil", "sendGetStatusFrameCmd enter index:", Integer.valueOf(i));
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(7);
        deviceCommand.setCommandID(13);
        byte[] b = jhn.b(i);
        deviceCommand.setDataLen(b.length);
        deviceCommand.setDataContent(b);
        deviceCommand.setNeedAck(true);
        if (deviceInfo != null) {
            deviceCommand.setmIdentify(deviceInfo.getDeviceIdentify());
        }
        jfq.c().b(deviceCommand);
    }

    public static void b(List<jid> list) {
        LogUtil.a("FitnessSendCommandUtil", "sendSetDeviceReportThreshold thresholdList: ", list);
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(7);
        deviceCommand.setCommandID(14);
        byte[] a2 = jhn.a(list);
        deviceCommand.setDataLen(a2.length);
        deviceCommand.setDataContent(a2);
        jfq.c().b(deviceCommand);
    }

    public static void e(boolean z) {
        LogUtil.a("FitnessSendCommandUtil", "sendSetDeviceCouldSummaryEnable enter isEnable:", Boolean.valueOf(z));
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(7);
        deviceCommand.setCommandID(16);
        byte[] a2 = cvx.a(cvx.e(1) + cvx.e(1) + cvx.e(z ? 1 : 0));
        deviceCommand.setDataLen(a2.length);
        deviceCommand.setDataContent(a2);
        jfq.c().b(deviceCommand);
    }

    public static void c(HeartZoneConf heartZoneConf, StudentHeartRateZoneMgr studentHeartRateZoneMgr, boolean z) {
        int classifyMethod;
        LogUtil.a("FitnessSendCommandUtil", "sendSetHeartZoneConfigCmd enter ", heartZoneConf);
        if (eme.b().sentHeartZoneSplicingMessages()) {
            return;
        }
        DeviceInfo a2 = jpt.a("FitnessSendCommandUtil");
        DeviceCapability d = cvs.d();
        if (a2 == null) {
            LogUtil.h("FitnessSendCommandUtil", "sendSetHeartZoneConfigCmd deviceInfo is null.");
            return;
        }
        if (heartZoneConf != null && heartZoneConf.getClassifyMethod() == 3) {
            heartZoneConf.setClassifyMethod(0);
        }
        if (d != null && d.getIsSupportHrrHeartRateCapability()) {
            b(heartZoneConf, studentHeartRateZoneMgr, z, a2);
            return;
        }
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(7);
        deviceCommand.setCommandID(19);
        if (a2.getProductType() == 20 || a2.getProductType() == 21) {
            classifyMethod = heartZoneConf.getClassifyMethod();
            LogUtil.a("FitnessSendCommandUtil", "sendSetHeartZoneConfigCmd classifyMethod: ", Integer.valueOf(classifyMethod));
        } else {
            classifyMethod = 0;
        }
        byte[] b = jhn.b(heartZoneConf, classifyMethod, false);
        deviceCommand.setDataLen(b.length);
        deviceCommand.setDataContent(b);
        deviceCommand.setmIdentify(a2.getDeviceIdentify());
        LogUtil.a("FitnessSendCommandUtil", "sendSetHeartZoneConfigCmd deviceCommand: ", deviceCommand.toString());
        jfq.c().b(deviceCommand);
    }

    private static void b(HeartZoneConf heartZoneConf, StudentHeartRateZoneMgr studentHeartRateZoneMgr, boolean z, DeviceInfo deviceInfo) {
        byte[] b;
        LogUtil.a("FitnessSendCommandUtil", "sendSetMaxAndReserveHeartZoneConfigCmd enter ", heartZoneConf);
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(7);
        deviceCommand.setCommandID(33);
        if (z) {
            b = jhn.c(studentHeartRateZoneMgr.getStudentHeartRateThresholdData());
        } else {
            b = jhn.b(heartZoneConf, 0, true);
        }
        deviceCommand.setDataLen(b.length);
        deviceCommand.setDataContent(b);
        deviceCommand.setmIdentify(deviceInfo.getDeviceIdentify());
        LogUtil.a("FitnessSendCommandUtil", "sendSetMaxAndReserveHeartZoneConfigCmd deviceCommand: ", deviceCommand.toString());
        jfq.c().b(deviceCommand);
    }

    public static void b(DeviceInfo deviceInfo) {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(7);
        deviceCommand.setCommandID(33);
        byte[] d = jhn.d();
        deviceCommand.setDataLen(d.length);
        deviceCommand.setDataContent(d);
        if (deviceInfo != null) {
            deviceCommand.setmIdentify(deviceInfo.getDeviceIdentify());
        }
        LogUtil.a("FitnessSendCommandUtil", "queryHeartZoneConfigCmd deviceCommand: ", deviceCommand.toString());
        jfq.c().b(deviceCommand);
    }

    public static void a(RunPaceZoneConfig runPaceZoneConfig) {
        LogUtil.a("FitnessSendCommandUtil", "sendRunPaceConfigCommand enter ", runPaceZoneConfig);
        if (jpt.a("FitnessSendCommandUtil") == null) {
            LogUtil.h("FitnessSendCommandUtil", "sendSetHeartZoneConfigCmd deviceInfo is null.");
            return;
        }
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(7);
        deviceCommand.setCommandID(40);
        byte[] e = jhn.e(runPaceZoneConfig);
        deviceCommand.setDataLen(e.length);
        deviceCommand.setDataContent(e);
        LogUtil.a("FitnessSendCommandUtil", "sendRunPaceConfigCommand deviceCommand: ", deviceCommand.toString());
        jfq.c().b(deviceCommand);
    }

    public static void f(int i, DeviceInfo deviceInfo) {
        DeviceCommand e = jqz.e(i, deviceInfo);
        LogUtil.a("FitnessSendCommandUtil", "setSleepBreatheSwitch openOrClose: ", Integer.valueOf(i));
        jfq.c().b(e);
    }

    public static void e(int i) {
        ReleaseLogUtil.e("DEVMGR_FitnessSendCommandUtil", "sendSetCoreSleepEnableCmd enabled:", Integer.valueOf(i));
        byte[] a2 = jhn.a(i);
        LogUtil.a("FitnessSendCommandUtil", "sendSetCoreSleepEnableCmd dataInfos :", Arrays.toString(a2));
        jqi.a().sendSetSwitchSettingCmd(a2, "", 7, 22);
    }

    public static void a(int i) {
        ReleaseLogUtil.e("DEVMGR_FitnessSendCommandUtil", "sendSetHeartRateEnableCmd enabled:", Integer.valueOf(i));
        byte[] f = jhn.f(i);
        LogUtil.a("FitnessSendCommandUtil", "sendSetHeartRateEnableCmd dataInfos :", Arrays.toString(f));
        jqi.a().sendSetSwitchSettingCmd(f, "", 7, 23);
    }

    public static void c(int i, Map<Integer, String> map, DeviceInfo deviceInfo) {
        if (map == null || map.size() > 1) {
            LogUtil.h("FitnessSendCommandUtil", "sendSleepDeviceData parameter is error.");
            return;
        }
        Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();
        String str = "";
        while (it.hasNext()) {
            str = it.next().getValue();
        }
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("FitnessSendCommandUtil", "sendSleepDeviceData parameter value is null");
            return;
        }
        if (i == 24) {
            d(Integer.parseInt(str));
        } else if (i == 25) {
            g(Integer.parseInt(str), deviceInfo);
        } else {
            LogUtil.h("FitnessSendCommandUtil", "sendSleepDeviceData enter other, commandId is :", Integer.valueOf(i));
        }
    }

    public static void d(int i) {
        LogUtil.a("FitnessSendCommandUtil", "sendSleepStateCmd enabled:", Integer.valueOf(i));
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(7);
        deviceCommand.setCommandID(24);
        byte[] c = jhn.c(i);
        deviceCommand.setDataLen(c.length);
        deviceCommand.setDataContent(c);
        LogUtil.a("FitnessSendCommandUtil", "sendSleepStateCmd dataInfos :", Arrays.toString(c));
        jfq.c().b(deviceCommand);
    }

    public static void b(int i) {
        ReleaseLogUtil.e("DEVMGR_FitnessSendCommandUtil", "sendSetContinueMeasureHeartRateEnableCmd status:", Integer.valueOf(i));
        StringBuilder sb = new StringBuilder(16);
        sb.append(cvx.e(1));
        sb.append(cvx.e(1));
        sb.append(cvx.e(i));
        byte[] a2 = cvx.a(sb.toString());
        LogUtil.a("FitnessSendCommandUtil", "sendSetContinueMeasureHeartRateEnableCmd dataInfos :", Arrays.toString(a2));
        jqi.a().sendSetSwitchSettingCmd(a2, "", 7, 28);
    }

    public static void b(int i, int i2) {
        ReleaseLogUtil.e("DEVMGR_FitnessSendCommandUtil", "sendHeartRateRemindCmd enable:", Integer.valueOf(i));
        StringBuilder sb = new StringBuilder(16);
        StringBuilder sb2 = new StringBuilder(16);
        sb.append(cvx.e(1));
        sb.append(cvx.e(1));
        sb.append(cvx.e(i));
        if (i == 1) {
            sb2.append(cvx.e(2));
            sb2.append(cvx.e(1));
            sb2.append(cvx.e(i2));
        }
        byte[] a2 = cvx.a(sb.toString() + sb2.toString());
        LogUtil.a("FitnessSendCommandUtil", "sendHeartRateRemindCmd dataInfos:", Arrays.toString(a2));
        jqi.a().sendSetSwitchSettingCmd(a2, "", 7, 29);
    }

    public static void a(int i, int i2) {
        ReleaseLogUtil.e("DEVMGR_FitnessSendCommandUtil", "sendHeartRateRemindCmdDown enable:", Integer.valueOf(i));
        StringBuilder sb = new StringBuilder(16);
        StringBuilder sb2 = new StringBuilder(16);
        sb.append(cvx.e(1));
        sb.append(cvx.e(1));
        sb.append(cvx.e(i));
        if (i == 1) {
            sb2.append(cvx.e(2));
            sb2.append(cvx.e(1));
            sb2.append(cvx.e(i2));
        }
        byte[] a2 = cvx.a(sb.toString() + sb2.toString());
        LogUtil.a("FitnessSendCommandUtil", "sendHeartRateRemindCommandDown dataInfos:", Arrays.toString(a2));
        jqi.a().sendSetSwitchSettingCmd(a2, "", 7, 34);
    }

    public static void c(int i) {
        ReleaseLogUtil.e("DEVMGR_FitnessSendCommandUtil", "sendCycleBloodOxygenEnableCmd status:", Integer.valueOf(i));
        StringBuilder sb = new StringBuilder(16);
        sb.append(cvx.e(1));
        sb.append(cvx.e(1));
        sb.append(cvx.e(i));
        byte[] a2 = cvx.a(sb.toString());
        LogUtil.a("FitnessSendCommandUtil", "sendCycleBloodOxygenEnableCommand dataInfos :", cvx.d(a2));
        jqi.a().sendSetSwitchSettingCmd(a2, "", 7, 36);
    }

    public static void c(int i, int i2) {
        ReleaseLogUtil.e("DEVMGR_FitnessSendCommandUtil", "sendBloodOxygenDownRemindCmd enable:", Integer.valueOf(i));
        StringBuilder sb = new StringBuilder(16);
        StringBuilder sb2 = new StringBuilder(16);
        sb.append(cvx.e(1));
        sb.append(cvx.e(1));
        sb.append(cvx.e(i));
        if (i == 1) {
            sb2.append(cvx.e(2));
            sb2.append(cvx.e(1));
            sb2.append(cvx.e(i2));
        }
        byte[] a2 = cvx.a(sb.toString() + sb2.toString());
        LogUtil.a("FitnessSendCommandUtil", "sendBloodOxygenDownRemindCommand dataInfos:", cvx.d(a2));
        jqi.a().sendSetSwitchSettingCmd(a2, "", 7, 37);
    }

    public static void c(DeviceInfo deviceInfo) {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(7);
        deviceCommand.setCommandID(21);
        byte[] c = jhn.c();
        deviceCommand.setDataLen(c.length);
        deviceCommand.setDataContent(c);
        if (deviceInfo != null) {
            deviceCommand.setmIdentify(deviceInfo.getDeviceIdentify());
        }
        jfq.c().b(deviceCommand);
    }

    public static void d(int i, UserInfomation userInfomation, int i2) {
        LogUtil.a("FitnessSendCommandUtil", "sendV0SetUserInfoFirstCmd enter");
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(7);
        deviceCommand.setCommandID(i);
        byte[] c = jhn.c(userInfomation, i2);
        deviceCommand.setDataLen(c.length);
        deviceCommand.setDataContent(c);
        jfq.c().b(deviceCommand);
    }

    public static void j(int i, DeviceInfo deviceInfo) {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(7);
        deviceCommand.setCommandID(26);
        ByteBuffer allocate = ByteBuffer.allocate(3);
        allocate.put((byte) 1);
        allocate.put((byte) 1);
        allocate.put(cvx.a(cvx.e(i)));
        deviceCommand.setDataLen(allocate.array().length);
        deviceCommand.setDataContent(allocate.array());
        if (deviceInfo != null) {
            deviceCommand.setmIdentify(deviceInfo.getDeviceIdentify());
        }
        jfq.c().b(deviceCommand);
    }

    public static void g(int i, DeviceInfo deviceInfo) {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(7);
        deviceCommand.setCommandID(25);
        ByteBuffer allocate = ByteBuffer.allocate(3);
        allocate.put((byte) 1);
        allocate.put((byte) 1);
        allocate.put(cvx.a(cvx.e(i)));
        deviceCommand.setDataLen(allocate.array().length);
        deviceCommand.setDataContent(allocate.array());
        if (deviceInfo != null) {
            deviceCommand.setmIdentify(deviceInfo.getDeviceIdentify());
        }
        jfq.c().b(deviceCommand);
    }

    public static void b(long j, long j2, int i) {
        LogUtil.a("FitnessSendCommandUtil", "sendGetDesFrameCountCmd enter startTime:", Long.valueOf(j), " endTime:", Long.valueOf(j2));
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
        jfq.c().b(deviceCommand);
    }

    public static void b(int i, DeviceInfo deviceInfo) {
        LogUtil.a("FitnessSendCommandUtil", "sendGetDesFrameCmd enter index:", Integer.valueOf(i));
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(7);
        deviceCommand.setCommandID(32);
        byte[] d = new bms().h(1, i).d();
        deviceCommand.setDataLen(d.length);
        deviceCommand.setDataContent(d);
        deviceCommand.setNeedAck(true);
        if (deviceInfo != null) {
            deviceCommand.setmIdentify(deviceInfo.getDeviceIdentify());
        }
        jfq.c().b(deviceCommand);
    }

    public static void e(int i, int i2, int i3) {
        LogUtil.a("FitnessSendCommandUtil", "sendTempSwitchInfo enter");
        StringBuffer stringBuffer = new StringBuffer(16);
        stringBuffer.append(cvx.e(1)).append(cvx.e(1)).append(cvx.e(i));
        stringBuffer.append(cvx.e(2)).append(cvx.e(1)).append(cvx.e(i2));
        String a2 = cvx.a(i3);
        if (i3 != 0) {
            stringBuffer.append(cvx.e(3)).append(cvx.e(a2.length() / 2)).append(a2);
        }
        jqi.a().sendSetSwitchSettingCmd(cvx.a(stringBuffer.toString()), "", 7, 42);
    }
}
