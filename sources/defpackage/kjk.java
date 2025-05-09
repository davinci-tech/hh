package defpackage;

import android.text.TextUtils;
import com.huawei.datatype.DeviceCommand;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import com.huawei.hwcloudmodel.hwwear.hag.model.outdoor.FutureWeatherSunMoonData;
import com.huawei.hwcloudmodel.model.push.WeatherForecastDay;
import com.huawei.hwcloudmodel.model.push.WeatherForecastHour;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class kjk {
    private static String e(jbz jbzVar, kjg kjgVar, kji kjiVar) {
        if (jbzVar == null) {
            LogUtil.h("FutureWeatherPushUtils", "pushWeatherForecast() weather is null");
            return "";
        }
        String b = b(jbzVar, kjiVar);
        String b2 = b(jbzVar, kjgVar, kjiVar);
        LogUtil.a("FutureWeatherPushUtils", "message :", b, b2);
        return b + b2;
    }

    private static String b(jbz jbzVar, kjg kjgVar, kji kjiVar) {
        List<WeatherForecastDay> j = jbzVar.j();
        if (j == null || j.isEmpty()) {
            LogUtil.h("FutureWeatherPushUtils", "pushWeatherForecast() weatherForecastDays is null or nothing");
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < j.size(); i++) {
            WeatherForecastDay weatherForecastDay = j.get(i);
            sb.append(cvx.e(145));
            String d = d(weatherForecastDay.getTime(), kjn.a(weatherForecastDay, kjiVar), weatherForecastDay.getHighestTemperature(), weatherForecastDay.getLowestTemperature());
            String c = c(weatherForecastDay.getFutureWeatherSunMoonData(), kjgVar);
            sb.append(cvx.e((d.length() + c.length()) / 2));
            sb.append(d);
            sb.append(c);
        }
        return cvx.e(144) + cvx.d(sb.toString().length() / 2) + sb.toString();
    }

    private static String b(jbz jbzVar, kji kjiVar) {
        List<WeatherForecastHour> l = jbzVar.l();
        if (l == null || l.isEmpty()) {
            LogUtil.h("FutureWeatherPushUtils", "pushWeatherForecast() Hours is null or nothing");
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < l.size(); i++) {
            sb.append(cvx.e(OldToNewMotionPath.SPORT_TYPE_TENNIS));
            String c = c(l.get(i).getTime(), kjn.c(l.get(i), kjiVar), l.get(i).getTemperature(), l.get(i).getPrecipitation(), kjiVar);
            sb.append(cvx.e(c.length() / 2));
            sb.append(c);
        }
        return cvx.e(129) + cvx.d(sb.toString().length() / 2) + sb.toString();
    }

    private static String c(long j, int i, double d, int i2, kji kjiVar) {
        String b = cvx.b(j);
        String str = "" + (cvx.e(3) + cvx.d(b.length() / 2) + b);
        String e = cvx.e(i);
        String str2 = str + (cvx.e(4) + cvx.d(e.length() / 2) + e);
        String e2 = cvx.e((int) d);
        String str3 = str2 + (cvx.e(5) + cvx.d(e2.length() / 2) + e2);
        if (!kjiVar.c()) {
            return str3;
        }
        String e3 = cvx.e(i2);
        return str3 + (cvx.e(6) + cvx.d(e3.length() / 2) + e3);
    }

    private static String d(long j, int i, double d, double d2) {
        String b = cvx.b(j);
        String str = "" + (cvx.e(18) + cvx.d(b.length() / 2) + b);
        String e = cvx.e(i);
        String str2 = str + (cvx.e(19) + cvx.d(e.length() / 2) + e);
        String e2 = cvx.e((int) d);
        String str3 = str2 + (cvx.e(20) + cvx.d(e2.length() / 2) + e2);
        String e3 = cvx.e((int) d2);
        return str3 + (cvx.e(21) + cvx.d(e3.length() / 2) + e3);
    }

    private static String c(FutureWeatherSunMoonData futureWeatherSunMoonData, kjg kjgVar) {
        String str = "";
        if (futureWeatherSunMoonData == null || kjgVar == null) {
            LogUtil.h("FutureWeatherPushUtils", "not support hagSunriseSunset");
            return "";
        }
        LogUtil.a("FutureWeatherPushUtils", "daySunriseSunset() support hagSunriseSunset");
        if (kjgVar.c()) {
            if (futureWeatherSunMoonData.getSunriseTime() != 0) {
                String b = cvx.b(futureWeatherSunMoonData.getSunriseTime());
                str = "" + (cvx.e(22) + cvx.d(b.length() / 2) + b);
            }
            if (futureWeatherSunMoonData.getSunsetTime() != 0) {
                String b2 = cvx.b(futureWeatherSunMoonData.getSunsetTime());
                str = str + (cvx.e(23) + cvx.d(b2.length() / 2) + b2);
            }
            if (futureWeatherSunMoonData.getSecondSunriseTime() != 0) {
                String b3 = cvx.b(futureWeatherSunMoonData.getSecondSunriseTime());
                str = str + (cvx.e(24) + cvx.d(b3.length() / 2) + b3);
            }
            if (futureWeatherSunMoonData.getSecondSunsetTime() != 0) {
                String b4 = cvx.b(futureWeatherSunMoonData.getSecondSunsetTime());
                str = str + (cvx.e(25) + cvx.d(b4.length() / 2) + b4);
            }
        }
        return str + a(futureWeatherSunMoonData, kjgVar);
    }

    private static String a(FutureWeatherSunMoonData futureWeatherSunMoonData, kjg kjgVar) {
        String str = "";
        if (kjgVar.c()) {
            if (futureWeatherSunMoonData.getMoonRise() != 0) {
                String b = cvx.b(futureWeatherSunMoonData.getMoonRise());
                str = "" + (cvx.e(26) + cvx.d(b.length() / 2) + b);
            }
            if (futureWeatherSunMoonData.getMoonSet() != 0) {
                String b2 = cvx.b(futureWeatherSunMoonData.getMoonSet());
                str = str + (cvx.e(27) + cvx.d(b2.length() / 2) + b2);
            }
            if (futureWeatherSunMoonData.getSecondMoonRise() != 0) {
                String b3 = cvx.b(futureWeatherSunMoonData.getSecondMoonRise());
                str = str + (cvx.e(28) + cvx.d(b3.length() / 2) + b3);
            }
            if (futureWeatherSunMoonData.getSecondMoonSet() != 0) {
                String b4 = cvx.b(futureWeatherSunMoonData.getSecondMoonSet());
                str = str + (cvx.e(29) + cvx.d(b4.length() / 2) + b4);
            }
        }
        String str2 = str + e(futureWeatherSunMoonData, kjgVar);
        LogUtil.a("FutureWeatherPushUtils", "dayMoonRiseMoonSet() COMMAND:", str2);
        return str2;
    }

    private static String e(FutureWeatherSunMoonData futureWeatherSunMoonData, kjg kjgVar) {
        String str = "";
        if (kjgVar.b() && futureWeatherSunMoonData.getMoonPhase() != 0) {
            String e = cvx.e(futureWeatherSunMoonData.getMoonPhase());
            str = "" + (cvx.e(30) + cvx.d(e.length() / 2) + e);
        }
        LogUtil.a("FutureWeatherPushUtils", "dayMoonPhase() COMMAND:", str);
        return str;
    }

    public static void e(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.h("FutureWeatherPushUtils", "notifyDeviceWeatherCapability DeviceInfo is null");
            return;
        }
        if (deviceInfo.getDeviceConnectState() != 2) {
            LogUtil.h("FutureWeatherPushUtils", "notifyDeviceWeatherCapability device disconnect");
            return;
        }
        String e = cvx.e(3);
        String str = cvx.e(1) + cvx.d(e.length() / 2) + e;
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(15);
        deviceCommand.setCommandID(9);
        deviceCommand.setDataContent(cvx.a(str));
        deviceCommand.setDataLen(cvx.a(str).length);
        deviceCommand.setPriority(1);
        deviceCommand.setmIdentify(deviceInfo.getDeviceIdentify());
        LogUtil.a("notifyDeviceWeatherCapability", "sendDeviceData command", deviceCommand.toString());
        jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
    }

    public static void a(DeviceInfo deviceInfo) {
        String str = cvx.e(1) + cvx.e(0);
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(15);
        deviceCommand.setCommandID(10);
        deviceCommand.setDataContent(cvx.a(str));
        deviceCommand.setDataLen(cvx.a(str).length);
        deviceCommand.setmIdentify(deviceInfo.getDeviceIdentify());
        jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
    }

    private static boolean d(DeviceInfo deviceInfo) {
        DeviceCapability b = b(deviceInfo);
        if (b == null) {
            LogUtil.h("FutureWeatherPushUtils", "isSupportFutureCapability() Capability is null");
            return false;
        }
        if (b.isWeatherSupportFutureInfo()) {
            return true;
        }
        LogUtil.h("FutureWeatherPushUtils", "isSupportFutureCapability() has not Capability");
        return false;
    }

    public static boolean c(DeviceInfo deviceInfo) {
        if (!d(deviceInfo)) {
            LogUtil.h("FutureWeatherPushUtils", "isSupportDeviceFutureWeatherCapability() has 5.15.8 Capability");
            return false;
        }
        DeviceCapability b = b(deviceInfo);
        if (b == null || !b.isSupportDeviceFutureWeatherCapability()) {
            return false;
        }
        LogUtil.h("FutureWeatherPushUtils", "isSupportDeviceFutureWeatherCapability() has 5.15.10 Capability");
        return true;
    }

    public static void d(jbz jbzVar, kjg kjgVar, kji kjiVar, DeviceInfo deviceInfo) {
        if (!d(deviceInfo)) {
            LogUtil.h("FutureWeatherPushUtils", "pushFutureWeatherToDevice() has not Capability");
            return;
        }
        String e = e(jbzVar, kjgVar, kjiVar);
        LogUtil.h("FutureWeatherPushUtils", "pushFutureWeatherToDevice, command:", e);
        if (TextUtils.isEmpty(e)) {
            return;
        }
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(15);
        deviceCommand.setCommandID(8);
        deviceCommand.setDataContent(cvx.a(e));
        deviceCommand.setDataLen(cvx.a(e).length);
        deviceCommand.setmIdentify(deviceInfo.getDeviceIdentify());
        jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
    }

    private static DeviceCapability b(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.h("FutureWeatherPushUtils", "getCapability deviceInfo is null");
            return null;
        }
        Map<String, DeviceCapability> queryDeviceCapability = jsz.b(BaseApplication.getContext()).queryDeviceCapability(1, deviceInfo.getDeviceIdentify(), "FutureWeatherPushUtils");
        if (queryDeviceCapability == null || queryDeviceCapability.isEmpty()) {
            return null;
        }
        return queryDeviceCapability.get(deviceInfo.getDeviceIdentify());
    }
}
