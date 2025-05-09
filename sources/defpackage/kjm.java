package defpackage;

import android.text.TextUtils;
import com.huawei.datatype.DeviceCommand;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import com.huawei.hwcloudmodel.model.push.AlertWeather;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.SharedPreferenceManager;
import java.util.List;

/* loaded from: classes5.dex */
public class kjm {
    private static long c;

    public static void e() {
        String str = cvx.e(1) + cvx.e(0);
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(15);
        deviceCommand.setCommandID(6);
        deviceCommand.setDataContent(cvx.a(str));
        deviceCommand.setDataLen(cvx.a(str).length);
        LogUtil.a("HwWeatherCommandUtils", "sendWeatherConstraintMsgExpand 0F06 deviceCommand: ", deviceCommand.toString());
        jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
    }

    public static void c() {
        long currentTimeMillis = System.currentTimeMillis();
        if (Math.abs(currentTimeMillis - c) < 500) {
            LogUtil.h("HwWeatherCommandUtils", "sendWeatherConstraintMsg less than 500ms.");
            return;
        }
        c = currentTimeMillis;
        String str = cvx.e(1) + cvx.e(0);
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(15);
        deviceCommand.setCommandID(2);
        deviceCommand.setDataContent(cvx.a(str));
        deviceCommand.setDataLen(cvx.a(str).length);
        LogUtil.a("HwWeatherCommandUtils", "sendWeatherConstraintMsg 0F02 deviceCommand: ", deviceCommand.toString());
        jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
    }

    public static void c(int i, int i2) {
        DeviceCommand e;
        if (kjn.n()) {
            e = d(i, i2);
        } else if (kjn.m()) {
            e = e(i);
        } else {
            LogUtil.h("HwWeatherCommandUtils", "sendWeatherErrorCode has not Capability");
            return;
        }
        jsz.b(BaseApplication.getContext()).sendDeviceData(e);
    }

    private static DeviceCommand d(int i, int i2) {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(15);
        deviceCommand.setCommandID(12);
        String c2 = c(2, 1, i);
        if (kjn.d(i, i2)) {
            c2 = c2 + c(3, 1, i2);
        }
        String str = cvx.e(129) + cvx.d(c2.length() / 2) + c2;
        deviceCommand.setDataContent(cvx.a(str));
        deviceCommand.setDataLen(cvx.a(str).length);
        LogUtil.a("HwWeatherCommandUtils", "makeExpandWeatherErrorCodeCommand 0F0C is ", deviceCommand.toString());
        return deviceCommand;
    }

    private static DeviceCommand e(int i) {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(15);
        deviceCommand.setCommandID(7);
        String c2 = c(1, 1, i);
        deviceCommand.setDataContent(cvx.a(c2));
        deviceCommand.setDataLen(cvx.a(c2).length);
        LogUtil.a("HwWeatherCommandUtils", "makeWeatherErrorCodeCommand 0F07 is ", deviceCommand.toString());
        return deviceCommand;
    }

    private static String c(int i, int i2, int i3) {
        return cvx.e(i) + cvx.e(i2) + cvx.e(i3);
    }

    public static void e(int i, int i2, int i3) {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(i);
        deviceCommand.setCommandID(i2);
        byte[] a2 = a(i3);
        deviceCommand.setDataLen(a2.length);
        deviceCommand.setDataContent(a2);
        jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
    }

    public static byte[] a(int i) {
        return new bms().i(127, i).d();
    }

    private static String g(jbz jbzVar, kji kjiVar) {
        if (!kjiVar.n()) {
            return "";
        }
        String str = cvx.e(2) + cvx.e(1) + cvx.e(kjn.e(jbzVar, kjiVar));
        LogUtil.a("HwWeatherCommandUtils", "makeWeatherTlv weatherTlvHex: ", str);
        return str;
    }

    private static String h(jbz jbzVar, kji kjiVar) {
        if (!kjiVar.k()) {
            return "";
        }
        return cvx.e(3) + cvx.e(2) + cvx.e(jbzVar.x()) + cvx.e(jbzVar.w());
    }

    private static String d(jbz jbzVar, kji kjiVar) {
        if (!kjiVar.g() || jbzVar.r() == -1) {
            return "";
        }
        return cvx.e(4) + cvx.e(2) + cvx.a(jbzVar.r());
    }

    private static String c(jbz jbzVar, kji kjiVar) {
        if (!kjiVar.f()) {
            return "";
        }
        return (cvx.e(OldToNewMotionPath.SPORT_TYPE_VOLLEYBALL) + cvx.e(6)) + (cvx.e(6) + cvx.e(1) + cvx.e(jbzVar.s())) + (cvx.e(7) + cvx.e(1) + cvx.e(jbzVar.n()));
    }

    private static String a(jbz jbzVar, kji kjiVar) {
        if (!kjiVar.i() || TextUtils.isEmpty(jbzVar.i())) {
            return "";
        }
        String e = cvx.e(14);
        String c2 = cvx.c(jbzVar.i());
        return e + cvx.d(c2.length() / 2) + c2;
    }

    private static String a(jbz jbzVar) {
        LogUtil.a("HwWeatherCommandUtils", "makeUvIndexTlv enter");
        String e = cvx.e(15);
        String e2 = cvx.e(jbzVar.f());
        return e + cvx.e(1) + e2;
    }

    public static String e(jbz jbzVar, kji kjiVar) {
        String str;
        String str2;
        String str3;
        String str4 = "";
        if (jbzVar == null || kjiVar == null) {
            return "";
        }
        String g = g(jbzVar, kjiVar);
        String h = h(jbzVar, kjiVar);
        String d = cvx.d((g + h).length() / 2);
        String e = d.length() != 0 ? cvx.e(129) : "";
        if (kjiVar.d()) {
            String c2 = cvx.c(jbzVar.q());
            str = cvx.e(8) + cvx.e(c2.length() / 2) + c2;
        } else {
            str = "";
        }
        if (kjiVar.a()) {
            str2 = cvx.e(9) + cvx.e(1) + cvx.e(jbzVar.k());
        } else {
            str2 = "";
        }
        if (kjiVar.j()) {
            str3 = cvx.e(10) + cvx.e(1) + cvx.e(jbzVar.p());
            LogUtil.a("HwWeatherCommandUtils", "makeWeatherPromptMsg weatherUnitTlvHex: ", str3);
        } else {
            str3 = "";
        }
        if (kjiVar.b() && jbzVar.m() != -1) {
            str4 = cvx.e(11) + cvx.e(2) + cvx.a(jbzVar.m());
            LogUtil.a("HwWeatherCommandUtils", "makeWeatherPromptMsg aqiTlvHex: ", str4);
        }
        return (e + d) + g + h + d(jbzVar, kjiVar) + c(jbzVar, kjiVar) + str + str2 + str3 + str4;
    }

    public static String b(jbz jbzVar, kji kjiVar) {
        String str;
        if (jbzVar == null || kjiVar == null) {
            LogUtil.h("HwWeatherCommandUtils", "setWeatherPromptMsgExpand weather or dataWeatherSupport is null");
            return "";
        }
        if (kjiVar.h()) {
            str = cvx.e(12) + cvx.e(4) + cvx.b(jbzVar.t());
            LogUtil.a("HwWeatherCommandUtils", "setWeatherPromptMsgExpand observationTimeTLVHex:", str);
        } else {
            str = "";
        }
        return str + (kjiVar.i() ? a(jbzVar, kjiVar) : "") + (kjn.o() ? a(jbzVar) : "");
    }

    public static void b(int i) {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(15);
        deviceCommand.setCommandID(5);
        String str = cvx.e(1) + cvx.e(1) + cvx.e(i);
        deviceCommand.setDataContent(cvx.a(str));
        deviceCommand.setDataLen(cvx.a(str).length);
        LogUtil.a("HwWeatherCommandUtils", "pushWeatherUnitToDevice 0F05: ", deviceCommand.toString());
        jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
    }

    public static void d(String str, jbz jbzVar, boolean z) {
        jrb.b("HwWeatherCommandUtils", 15, 1);
        if (TextUtils.isEmpty(str) || jbzVar == null) {
            LogUtil.h("HwWeatherCommandUtils", "pushWeatherToDevice TextUtils.isEmpty(command) || weather == null");
            sqo.ao("pushWeatherToDevice command is empty or weather is null");
            return;
        }
        byte[] a2 = cvx.a(str);
        if (z) {
            byte[] d = d(jbzVar);
            LogUtil.a("HwWeatherCommandUtils", "pushWeatherToDevice humidityBytes : ", d);
            byte[] bArr = new byte[a2.length + d.length];
            System.arraycopy(a2, 0, bArr, 0, a2.length);
            System.arraycopy(d, 0, bArr, a2.length, d.length);
            a2 = bArr;
        }
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(15);
        deviceCommand.setCommandID(1);
        deviceCommand.setDataContent(a2);
        deviceCommand.setDataLen(a2.length);
        jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
        d(false, "");
        LogUtil.a("HwWeatherCommandUtils", "pushWeatherToDevice 0F01 command: ", str);
    }

    private static byte[] d(jbz jbzVar) {
        bms bmsVar = new bms();
        bmsVar.j(16, jds.c(jbzVar.g(), 10)).i(17, jbzVar.o()).i(18, jbzVar.h());
        List<AlertWeather> e = jbzVar.e();
        if (e != null && e.size() > 0) {
            bmsVar.b(19);
            for (AlertWeather alertWeather : e) {
                if (alertWeather != null) {
                    bmsVar.c(bmsVar.c(20).e(21, alertWeather.getAlertTitle()).e(22, alertWeather.getAlertAreaName()).d(23, alertWeather.getAlertLevel()).e(24, alertWeather.getAlertLevelName()).d(25, alertWeather.getAlertType()).e(26, alertWeather.getAlertTypeName()).e(27, "").b(28, alertWeather.getAlertPublicTime()).c());
                }
            }
            bmsVar.b(bmsVar.b());
        }
        return bmsVar.d();
    }

    public static void d(boolean z, String str) {
        DeviceCapability e = kjn.e();
        if (e == null || !e.isSupportGpsSearchStarOptimization()) {
            LogUtil.h("HwWeatherCommandUtils", "sendGpsAndTimeToDevice not is support GpsSearchStarOptimization");
            return;
        }
        if (TextUtils.isEmpty(str)) {
            str = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(15), "gps_and_time");
        }
        if (!TextUtils.isEmpty(str)) {
            String[] split = str.split("_");
            if (split.length == 3) {
                String str2 = split[0];
                String str3 = split[1];
                String str4 = split[2];
                jrb.b("HwWeatherCommandUtils", 24, 7);
                StringBuilder sb = new StringBuilder(16);
                sb.append(str2);
                sb.append(str3);
                sb.append(str4);
                byte[] a2 = cvx.a(sb.toString());
                DeviceCommand deviceCommand = new DeviceCommand();
                deviceCommand.setServiceID(24);
                deviceCommand.setCommandID(7);
                deviceCommand.setDataLen(a2.length);
                deviceCommand.setDataContent(a2);
                LogUtil.a("HwWeatherCommandUtils", "sendGpsAndTimeToDevice deviceCommand:", deviceCommand.toString());
                jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
                return;
            }
            LogUtil.h("HwWeatherCommandUtils", "sendGpsAndTimeToDevice gpsAndTime is error");
            if (z) {
                e(24, 6, 100001);
                return;
            }
            return;
        }
        LogUtil.h("HwWeatherCommandUtils", "sendGpsAndTimeToDevice gpsAndTime is null");
        if (z) {
            e(24, 6, 100001);
        }
    }

    public static void e(String str) {
        LogUtil.a("HwWeatherCommandUtils", "pushAtmosphereCommandToDevice command: ", str);
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(15);
        deviceCommand.setCommandID(3);
        deviceCommand.setDataContent(cvx.a(str));
        deviceCommand.setDataLen(cvx.a(str).length);
        jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
    }
}
