package defpackage;

import android.text.TextUtils;
import com.huawei.datatype.DeviceCommand;
import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.multisimservice.model.SimInfo;
import java.util.List;

/* loaded from: classes5.dex */
public class kua {
    public static void e(String str, int i) {
        String str2;
        LogUtil.c("MultiSimSendCommandUtil", "the acCode ", str);
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(29);
        deviceCommand.setCommandID(1);
        if (str != null) {
            String c = cvx.c(str);
            int length = c.length() / 2;
            if (length <= 127) {
                str2 = cvx.e(1) + cvx.e(length) + c;
            } else {
                str2 = cvx.e(1) + cvx.e((length / 127) + 128) + cvx.e(length % 127) + c;
            }
            String str3 = str2 + cvx.e(2) + cvx.e(2) + cvx.a(i);
            deviceCommand.setDataLen(cvx.a(str3).length);
            deviceCommand.setDataContent(cvx.a(str3));
            LogUtil.a("MultiSimSendCommandUtil", "sendOpenEsimCommand ", str3);
            jfq.c().b(deviceCommand);
            return;
        }
        LogUtil.h("MultiSimSendCommandUtil", "acCode == null");
    }

    public static void c(String str, int i) {
        String str2;
        LogUtil.c("MultiSimSendCommandUtil", "the conformcode ", str);
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(29);
        deviceCommand.setCommandID(3);
        if (str != null) {
            String c = cvx.c(str);
            int length = c.length() / 2;
            if (length <= 127) {
                str2 = cvx.e(1) + cvx.e(length) + c;
            } else {
                str2 = cvx.e(1) + cvx.e((length / 127) + 128) + cvx.e(length % 127) + c;
            }
        } else {
            str2 = null;
        }
        String b = cvx.b(i);
        String str3 = cvx.e(2) + cvx.e(b.length() / 2) + b;
        if (str2 != null) {
            str3 = str2 + str3;
        }
        deviceCommand.setDataLen(cvx.a(str3).length);
        deviceCommand.setDataContent(cvx.a(str3));
        LogUtil.a("MultiSimSendCommandUtil", "sendConformCode ", str3);
        jfq.c().b(deviceCommand);
    }

    public static void b(int i, boolean z) {
        String str;
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(29);
        deviceCommand.setCommandID(i);
        if (z) {
            String b = cvx.b(100000L);
            str = cvx.e(127) + cvx.e(b.length() / 2) + b;
        } else {
            String b2 = cvx.b(100001L);
            str = cvx.e(127) + cvx.e(b2.length() / 2) + b2;
        }
        deviceCommand.setDataLen(cvx.a(str).length);
        deviceCommand.setDataContent(cvx.a(str));
        LogUtil.a("MultiSimSendCommandUtil", "sendReslut ", str);
        jfq.c().b(deviceCommand);
    }

    public static void c() {
        LogUtil.a("MultiSimSendCommandUtil", "sendSimInfoQuery enter");
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(29);
        deviceCommand.setCommandID(6);
        byte[] a2 = cvx.a(cvx.e(1) + "00");
        deviceCommand.setDataLen(a2.length);
        deviceCommand.setDataContent(a2);
        deviceCommand.setNeedAck(true);
        jfq.c().b(deviceCommand);
    }

    public static void a() {
        LogUtil.a("MultiSimSendCommandUtil", "sendBatteryThresholdQuery enter");
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(29);
        deviceCommand.setCommandID(10);
        jfq.c().b(deviceCommand);
    }

    private static byte[] e(int i, String str) {
        String str2 = "" + cvx.e(1) + cvx.e(1) + cvx.e(i);
        if (i != 0 && !TextUtils.isEmpty(str)) {
            str2 = str2 + cvx.e(2) + cvx.e(str.length()) + cvx.c(str);
        }
        return cvx.a(str2);
    }

    public static void b(int i, String str) {
        LogUtil.a("MultiSimSendCommandUtil", "sendMultiSimStatus enter");
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(29);
        deviceCommand.setCommandID(7);
        byte[] e = e(i, str);
        deviceCommand.setDataLen(e.length);
        deviceCommand.setDataContent(e);
        jfq.c().b(deviceCommand);
    }

    private static byte[] b(List<SimInfo> list) {
        StringBuffer stringBuffer = new StringBuffer(16);
        for (SimInfo simInfo : list) {
            String str = ((cvx.e(3) + cvx.e(simInfo.getIMSI().length()) + cvx.c(simInfo.getIMSI())) + cvx.e(4) + cvx.e(simInfo.getICCID().length()) + cvx.c(simInfo.getICCID())) + cvx.e(5) + cvx.e(1) + cvx.e(simInfo.isActive() ? 1 : 0);
            stringBuffer.append(cvx.e(OldToNewMotionPath.SPORT_TYPE_TENNIS) + cvx.d(str.length() / 2) + str);
        }
        String stringBuffer2 = stringBuffer.toString();
        return cvx.a(cvx.e(129) + cvx.d(stringBuffer2.length() / 2) + stringBuffer2);
    }

    public static void d(List<SimInfo> list) {
        LogUtil.a("MultiSimSendCommandUtil", "sendEsimProfileRemoveReq enter");
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(29);
        deviceCommand.setCommandID(8);
        byte[] b = b(list);
        deviceCommand.setDataLen(b.length);
        deviceCommand.setDataContent(b);
        jfq.c().b(deviceCommand);
    }

    public static void b(int i, int i2) {
        LogUtil.a("MultiSimSendCommandUtil", "sendEsimProfileRemoveReq enter");
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(29);
        deviceCommand.setCommandID(i);
        byte[] a2 = cvx.a(cvx.e(127) + cvx.e(4) + cvx.b(i2));
        deviceCommand.setDataLen(a2.length);
        deviceCommand.setDataContent(a2);
        jfq.c().b(deviceCommand);
    }
}
