package defpackage;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonParseException;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hianalytics.visual.autocollect.HAWebViewInterface;
import com.huawei.hwcloudmodel.model.push.WeatherForecastDay;
import com.huawei.hwcloudmodel.model.push.WeatherForecastHour;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import health.compact.a.CommonUtil;
import health.compact.a.HarmonyBuild;
import health.compact.a.LocalBroadcast;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class kjn {
    private static final String[] c = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"};

    /* renamed from: a, reason: collision with root package name */
    private static kjj f14419a = null;

    public static int e(jbz jbzVar, kji kjiVar) {
        if (kjiVar.e() && jbu.c()) {
            return jbzVar.d();
        }
        return b(jbzVar.v(), kjiVar);
    }

    public static int a(WeatherForecastDay weatherForecastDay, kji kjiVar) {
        if (kjiVar.e() && jbu.c()) {
            return weatherForecastDay.getCnWeatherIcon();
        }
        return b(weatherForecastDay.getWeatherIcon(), kjiVar);
    }

    public static int c(WeatherForecastHour weatherForecastHour, kji kjiVar) {
        if (kjiVar.e() && jbu.c()) {
            return weatherForecastHour.getCnWeatherIcon();
        }
        return b(weatherForecastHour.getWeatherIcon(), kjiVar);
    }

    private static int b(int i, kji kjiVar) {
        if (kjiVar.o()) {
            LogUtil.a("HwWeatherManagerUtils", "convertWeatherIcon() is Support Weather Icon Expand weather:", Integer.valueOf(i));
            return i;
        }
        switch (i) {
            case 32:
            case 33:
            case 34:
            case 35:
                LogUtil.a("HwWeatherManagerUtils", "convertWeatherIcon() weatherIcon:", Integer.valueOf(i));
                break;
        }
        return i;
    }

    public static boolean g() {
        boolean z;
        boolean z2;
        LogUtil.a("HwWeatherManagerUtils", "isGpsSwitch()");
        LocationManager locationManager = (LocationManager) BaseApplication.getContext().getSystemService("location");
        if (locationManager != null) {
            z2 = locationManager.isProviderEnabled(GeocodeSearch.GPS);
            z = locationManager.isProviderEnabled(HAWebViewInterface.NETWORK);
        } else {
            z = false;
            z2 = false;
        }
        LogUtil.a("HwWeatherManagerUtils", "isGpsSwitch() isNetworkEnable:", Boolean.valueOf(z), ",isGpsEnable:", Boolean.valueOf(z2));
        return z2 || z;
    }

    public static boolean h() {
        boolean c2 = jdi.c(BaseApplication.getContext(), c);
        LogUtil.a("HwWeatherManagerUtils", "isGpsPermissions() isHasPermissions:", Boolean.valueOf(c2));
        return c2;
    }

    public static boolean f() {
        return g() && h();
    }

    public static boolean e(jbz jbzVar) {
        if (jbzVar == null) {
            LogUtil.h("HwWeatherManagerUtils", "isPushServer503Error dataWeather is null");
            return false;
        }
        int a2 = jbzVar.a();
        LogUtil.a("HwWeatherManagerUtils", "isPushServer503Error errorType:", Integer.valueOf(a2));
        return a2 == 503;
    }

    public static boolean b(jbz jbzVar) {
        if (jbzVar == null) {
            LogUtil.h("HwWeatherManagerUtils", "isPushServerError dataWeather is null");
            return false;
        }
        int a2 = jbzVar.a();
        LogUtil.a("HwWeatherManagerUtils", "isPushServerError errorType:", Integer.valueOf(a2));
        return a2 == 503 || a2 == 602 || a2 == 603 || a2 == 604;
    }

    public static boolean d(int i, int i2) {
        if (i != 0) {
            LogUtil.h("HwWeatherManagerUtils", "isServiceError() baseErrorType is not network Error");
            return false;
        }
        if (i2 == 0) {
            return true;
        }
        LogUtil.h("HwWeatherManagerUtils", "isServiceError() errorType is not service Error");
        return false;
    }

    public static boolean n() {
        DeviceCapability e = e();
        if (e == null) {
            LogUtil.h("HwWeatherManagerUtils", "isSupportWeatherError Capability is null");
            return false;
        }
        if (e.isSupportWeatherErrorCode()) {
            return true;
        }
        LogUtil.h("HwWeatherManagerUtils", "isSupportWeatherError has not 5.15.12 Capability");
        return false;
    }

    public static boolean m() {
        DeviceCapability e = e();
        if (e == null) {
            LogUtil.h("HwWeatherManagerUtils", "isSupportWeatherError Capability is null");
            return false;
        }
        if (e.isWeatherSupportErrorCode()) {
            return true;
        }
        LogUtil.h("HwWeatherManagerUtils", "isSupportWeatherError has not 5.15.7 Capability");
        return false;
    }

    private static DeviceInfo r() {
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, "HwWeatherManagerUtils");
        if (deviceList.size() > 0) {
            return deviceList.get(0);
        }
        return null;
    }

    public static boolean o() {
        DeviceInfo r = r();
        if (r == null) {
            LogUtil.h("HwWeatherManagerUtils", "isSupportUVIndex deviceInfo is null");
            return false;
        }
        return cwi.c(r, 47);
    }

    public static DeviceCapability e() {
        Map<String, DeviceCapability> queryDeviceCapability = jsz.b(BaseApplication.getContext()).queryDeviceCapability(4, "", "HwWeatherManagerUtils");
        DeviceCapability deviceCapability = null;
        if (queryDeviceCapability != null && !queryDeviceCapability.isEmpty()) {
            Iterator<Map.Entry<String, DeviceCapability>> it = queryDeviceCapability.entrySet().iterator();
            while (it.hasNext() && (deviceCapability = it.next().getValue()) == null) {
            }
        }
        return deviceCapability;
    }

    public static boolean l() {
        DeviceInfo r = r();
        if (r == null) {
            return false;
        }
        return cwi.c(r, 193);
    }

    public static boolean k() {
        DeviceInfo r = r();
        if (r == null) {
            return false;
        }
        return cwi.c(r, 192);
    }

    public static DeviceCapability e(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.h("HwWeatherManagerUtils", "getCapability deviceInfo is null");
            return e();
        }
        Map<String, DeviceCapability> queryDeviceCapability = jsz.b(BaseApplication.getContext()).queryDeviceCapability(1, deviceInfo.getDeviceIdentify(), "HwWeatherManagerUtils");
        if (queryDeviceCapability == null || queryDeviceCapability.isEmpty()) {
            return null;
        }
        return queryDeviceCapability.get(deviceInfo.getDeviceIdentify());
    }

    public static String bOM_(Location location) {
        String str;
        double d;
        double d2;
        String b = cvx.b(0L);
        if (location != null) {
            d2 = location.getLatitude();
            d = location.getLongitude();
            str = cvx.b(location.getTime() / 1000);
        } else {
            str = b;
            d = 0.0d;
            d2 = 0.0d;
        }
        BigDecimal a2 = a(d2);
        BigDecimal a3 = a(d);
        if (a2 == null || a3 == null) {
            LogUtil.h("HwWeatherManagerUtils", "saveGpsAndTime (sevenDecimalLatitude == null) || (sevenDecimalLongitude == null)");
            return "";
        }
        double doubleValue = a2.setScale(7, 4).doubleValue();
        double doubleValue2 = a3.setScale(7, 4).doubleValue();
        String c2 = cvx.c(doubleValue);
        String c3 = cvx.c(doubleValue2);
        String str2 = cvx.e(1) + cvx.d(str.length() / 2) + str;
        String str3 = cvx.e(2) + cvx.d(c2.length() / 2) + c2;
        String str4 = cvx.e(3) + cvx.d(c3.length() / 2) + c3;
        StringBuilder sb = new StringBuilder(16);
        sb.append(str2);
        sb.append("_");
        sb.append(str4);
        sb.append("_");
        sb.append(str3);
        LogUtil.a("HwWeatherManagerUtils", "saveGpsAndTime status:", Integer.valueOf(SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(15), "gps_and_time", sb.toString(), new StorageParams(0))), " stringBuilder.toString() ", sb.toString());
        return sb.toString();
    }

    public static BigDecimal a(double d) {
        try {
            return new BigDecimal(d);
        } catch (NumberFormatException unused) {
            LogUtil.b("HwWeatherManagerUtils", "getBigDecimal NumberFormatException");
            return null;
        }
    }

    public static kji b(byte b) {
        boolean z;
        String c2 = c(b);
        LogUtil.a("HwWeatherManagerUtils", "parseWeatherSupport valueString:", c2);
        String str = c2.charAt(7) + "";
        String str2 = c2.charAt(6) + "";
        String str3 = c2.charAt(5) + "";
        String str4 = c2.charAt(4) + "";
        String str5 = c2.charAt(3) + "";
        String str6 = c2.charAt(2) + "";
        String str7 = c2.charAt(1) + "";
        String str8 = c2.charAt(0) + "";
        LogUtil.a("HwWeatherManagerUtils", "parseWeatherSupport bitWeather: ", str, "bitWind: ", str2, "bitPm25: ", str3, "bitTemperature: ", str4, "bitLocationName: ", str5, "bitTimeTemperature: ", str6, "bitUnit: ", str7, "bitAqi: ", str8);
        Bundle bundle = new Bundle();
        if ("1".equals(str)) {
            z = true;
            bundle.putBoolean("weather_support", true);
        } else {
            z = true;
        }
        if ("1".equals(str2)) {
            bundle.putBoolean("wind_support", z);
        }
        if ("1".equals(str3)) {
            bundle.putBoolean("pm2_5_support", z);
        }
        if ("1".equals(str4)) {
            bundle.putBoolean("temperature_support", z);
        }
        if ("1".equals(str5)) {
            bundle.putBoolean("location_name_support", z);
        }
        if ("1".equals(str6)) {
            bundle.putBoolean("temperature_current_support", z);
        }
        if ("1".equals(str7)) {
            bundle.putBoolean("unit_support", z);
        }
        if ("1".equals(str8)) {
            bundle.putBoolean("aqi_support", z);
        }
        kji kjiVar = new kji();
        kjiVar.bOD_(bundle);
        return kjiVar;
    }

    public static kji c(byte b, byte b2) {
        String c2 = c(b);
        String c3 = c(b2);
        LogUtil.a("HwWeatherManagerUtils", "parseWeatherSupportExpand highValueString:", c2, " lowValueString:", c3);
        String str = c3.charAt(7) + "";
        String str2 = c3.charAt(6) + "";
        String str3 = c3.charAt(5) + "";
        String str4 = c3.charAt(4) + "";
        LogUtil.a("HwWeatherManagerUtils", "parseWeatherSupportExpand bitTime:", str, " bitSource:", str2, " bitCnWeatherIcon:", str3, " bitWeatherIconExpand:", str4);
        Bundle bundle = new Bundle();
        if ("1".equals(str)) {
            bundle.putBoolean("time_support", true);
        }
        if ("1".equals(str2)) {
            bundle.putBoolean("source_support", true);
        }
        if ("1".equals(str3)) {
            bundle.putBoolean("cn_weather_icon_support", true);
        }
        if ("1".equals(str4)) {
            bundle.putBoolean("weather_icon_expand_support", true);
        }
        kji kjiVar = new kji();
        kjiVar.bOD_(bundle);
        return kjiVar;
    }

    public static String c(byte b) {
        StringBuilder sb = new StringBuilder(16);
        sb.append((int) ((byte) ((b >> 7) & 1)));
        sb.append((int) ((byte) ((b >> 6) & 1)));
        sb.append((int) ((byte) ((b >> 5) & 1)));
        sb.append((int) ((byte) ((b >> 4) & 1)));
        sb.append((int) ((byte) ((b >> 3) & 1)));
        sb.append((int) ((byte) ((b >> 2) & 1)));
        sb.append((int) ((byte) ((b >> 1) & 1)));
        sb.append((int) ((byte) (b & 1)));
        return sb.toString();
    }

    public static boolean a(int i, long j) {
        int currentTimeMillis = (int) (((System.currentTimeMillis() - j) / 1000) / 60);
        LogUtil.a("HwWeatherManagerUtils", "last gap long is : ", Integer.valueOf(currentTimeMillis), "minutes");
        return currentTimeMillis > i;
    }

    public static int[] c(double d) {
        double d2;
        int[] iArr = new int[2];
        try {
            d2 = Double.parseDouble(new DecimalFormat("#.####", new DecimalFormatSymbols(Locale.US)).format(d));
        } catch (NumberFormatException unused) {
            LogUtil.b("HwWeatherManagerUtils", "splitDecimal is not number");
            d2 = 0.0d;
        }
        String[] split = Double.toString(d2).split("\\.");
        for (int i = 0; i < split.length; i++) {
            try {
                iArr[i] = Integer.parseInt(split[i]);
            } catch (NumberFormatException unused2) {
                LogUtil.b("HwWeatherManagerUtils", "splitDecimal is not number");
            }
        }
        return iArr;
    }

    public static void a(jca jcaVar) {
        StringBuilder sb = new StringBuilder(0);
        sb.append(System.currentTimeMillis());
        sb.append("_");
        sb.append(jcaVar.b());
        sb.append("_");
        sb.append(jcaVar.d());
        LogUtil.a("HwWeatherManagerUtils", "savePressureOnServiceProvider Pressure : ", sb.toString(), " pressure: ", Integer.valueOf(SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(15), "get_atmosphere_auto_press", sb.toString(), new StorageParams(0))));
    }

    public static kjh c(int i) {
        kjh kjhVar = new kjh();
        kjhVar.d((i & 1) == 1);
        return kjhVar;
    }

    public static void e(int i) {
        if (j()) {
            LogUtil.h("HwWeatherManagerUtils", "reportBi middle night not report BI");
            return;
        }
        LogUtil.a("HwWeatherManagerUtils", "reportBi type:", Integer.valueOf(i));
        Intent intent = new Intent("com.huawei.health.action.ACTION_HWWEATHER_BI_CHANGE");
        intent.putExtra("BIStatus", i);
        intent.setPackage(BaseApplication.getAppPackage());
        BaseApplication.getContext().sendBroadcast(intent, LocalBroadcast.c);
    }

    public static boolean j() {
        int hours = new Date(System.currentTimeMillis()).getHours();
        LogUtil.a("HwWeatherManagerUtils", "dateHour:", Integer.valueOf(hours));
        return hours <= 7 || hours >= 23;
    }

    public static boolean d(jbz jbzVar) {
        DeviceCapability e = e();
        if (e == null || !e.isWeatherSupportFutureInfo()) {
            LogUtil.h("HwWeatherManagerUtils", "isRetryRequestFuture not support futureInfo");
            return false;
        }
        List<WeatherForecastHour> l = jbzVar.l();
        List<WeatherForecastDay> j = jbzVar.j();
        return l == null || l.isEmpty() || j == null || j.isEmpty();
    }

    public static kjg a(byte[] bArr) {
        kjg kjgVar = new kjg();
        if (bArr == null || bArr.length < 8) {
            LogUtil.h("HwWeatherManagerUtils", "parseFutureWeatherSupport() is not Support future Weather");
            return kjgVar;
        }
        String c2 = c(bArr[7]);
        String str = c2.charAt(7) + "";
        String str2 = c2.charAt(6) + "";
        LogUtil.a("HwWeatherManagerUtils", "bitSunriseSunset:", str, " bitMoonPhase:", str2);
        if ("1".equals(str)) {
            kjgVar.c(true);
        }
        if ("1".equals(str2)) {
            kjgVar.e(true);
        }
        return kjgVar;
    }

    public static final int b(Context context) {
        if (context == null) {
            LogUtil.h("HwWeatherManagerUtils", "getNetWorkStates context is null");
            return -1;
        }
        Object systemService = context.getSystemService("connectivity");
        if (!(systemService instanceof ConnectivityManager)) {
            LogUtil.h("HwWeatherManagerUtils", "getNetWorkStates ConnectivityManager is null");
            return -1;
        }
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) systemService).getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            LogUtil.h("HwWeatherManagerUtils", "getNetWorkStates activeNetworkInfo is null");
            return -1;
        }
        int type = activeNetworkInfo.getType();
        if (type == 0) {
            return 0;
        }
        if (type == 1) {
            return 1;
        }
        LogUtil.h("HwWeatherManagerUtils", "getNetWorkStates default type TYPE_NONE");
        return -1;
    }

    public static Location bOK_() {
        kjj kjjVar = f14419a;
        Object[] objArr = new Object[2];
        objArr[0] = "getDeviceLocation weather is ";
        objArr[1] = Boolean.valueOf(kjjVar != null);
        LogUtil.a("HwWeatherManagerUtils", objArr);
        if (!l() || kjjVar == null) {
            return null;
        }
        Location location = new Location(GeocodeSearch.GPS);
        location.setLatitude(kjjVar.a());
        location.setLongitude(kjjVar.b());
        location.setTime(kjjVar.d());
        LogUtil.c("HwWeatherManagerUtils", "getDeviceLocation location is ", location.toString());
        return location;
    }

    public static void b(byte[] bArr) {
        if (!l() || bArr == null || bArr.length < 2) {
            LogUtil.h("HwWeatherManagerUtils", "saveDeviceLocation data == null || deviceInfo == null");
            f14419a = null;
            return;
        }
        try {
            int length = bArr.length - 2;
            byte[] bArr2 = new byte[length];
            System.arraycopy(bArr, 2, bArr2, 0, length);
            bmq b = new bmt().b(bArr2);
            if (b == null) {
                LogUtil.h("HwWeatherManagerUtils", "saveDeviceLocation tlvFather is null");
                f14419a = null;
                return;
            }
            List<bmu> d = b.d();
            kjj kjjVar = new kjj();
            for (int i = 0; i < d.size(); i++) {
                b(d, kjjVar, i);
            }
            long d2 = kjjVar.d();
            LogUtil.a("HwWeatherManagerUtils", "saveDeviceLocation getTime ", Long.valueOf(d2));
            if (d2 == 0) {
                f14419a = null;
            } else {
                f14419a = kjjVar;
            }
            Object[] objArr = new Object[2];
            objArr[0] = "saveDeviceLocation locationWeather ";
            kjj kjjVar2 = f14419a;
            objArr[1] = kjjVar2 != null ? kjjVar2.toString() : "false";
            LogUtil.c("HwWeatherManagerUtils", objArr);
        } catch (bmk e) {
            LogUtil.b("HwWeatherManagerUtils", "tlvFather error:", ExceptionUtils.d(e));
        }
    }

    private static void b(List<bmu> list, kjj kjjVar, int i) {
        bmu bmuVar = list.get(i);
        if (bmuVar == null) {
            return;
        }
        byte a2 = bmuVar.a();
        if (a2 == 3) {
            long e = blq.e(bmuVar.c(), 0L) * 1000;
            kjjVar.b(e);
            LogUtil.c("HwWeatherManagerUtils", "saveDeviceLocation time ", Long.valueOf(e));
        } else if (a2 == 4) {
            double a3 = blq.a(bmuVar.c(), 0.0d);
            kjjVar.c(a3);
            LogUtil.c("HwWeatherManagerUtils", "saveDeviceLocation longitude ", Double.valueOf(a3));
        } else {
            if (a2 == 5) {
                double a4 = blq.a(bmuVar.c(), 0.0d);
                kjjVar.b(a4);
                LogUtil.c("HwWeatherManagerUtils", "saveDeviceLocation Latitude ", Double.valueOf(a4));
                return;
            }
            LogUtil.h("HwWeatherManagerUtils", "tlvFather not switch");
        }
    }

    public static void bON_(Location location) {
        if (location == null) {
            LogUtil.h("HwWeatherManagerUtils", "saveLocationToSp hwLocation is null");
            return;
        }
        if (System.currentTimeMillis() - location.getTime() >= 3600000) {
            LogUtil.h("HwWeatherManagerUtils", "saveLocationToSp hwLocation over 1 hour");
            return;
        }
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        long time = location.getTime();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(JsbMapKeyNames.H5_LOC_LAT, latitude);
            jSONObject.put(JsbMapKeyNames.H5_LOC_LON, longitude);
            jSONObject.put("time", time);
        } catch (JSONException unused) {
            LogUtil.b("HwWeatherManagerUtils", "saveLocationToSp JSONException");
        }
        String jSONObject2 = jSONObject.toString();
        if (TextUtils.isEmpty(jSONObject2)) {
            LogUtil.h("HwWeatherManagerUtils", "saveLocationToSp result is empty");
            return;
        }
        LogUtil.a("HwWeatherManagerUtils", "saveGpsAndTimeToSp delete ", Boolean.valueOf(SharedPreferenceManager.d(String.valueOf(10039), "key_weather_gps_and_time")), " errorCode ", Integer.valueOf(SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10039), "key_weather_gps_and_time", jSONObject2, new StorageParams(1))));
    }

    public static Location bOL_() {
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(10039), "key_weather_gps_and_time");
        Location location = null;
        if (TextUtils.isEmpty(b)) {
            LogUtil.h("HwWeatherManagerUtils", "getLocationWeatherBySp result is result");
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(b);
            double d = jSONObject.getDouble(JsbMapKeyNames.H5_LOC_LAT);
            double d2 = jSONObject.getDouble(JsbMapKeyNames.H5_LOC_LON);
            long j = jSONObject.getLong("time");
            Location location2 = new Location(GeocodeSearch.GPS);
            try {
                location2.setLatitude(d);
                location2.setLongitude(d2);
                location2.setTime(j);
                return location2;
            } catch (JSONException unused) {
                location = location2;
                LogUtil.b("HwWeatherManagerUtils", "getLocationWeatherBySp JSONException");
                return location;
            }
        } catch (JSONException unused2) {
        }
    }

    public static boolean d() {
        boolean x = CommonUtil.x(BaseApplication.getContext());
        boolean c2 = jeg.d().c(BaseApplication.getContext(), "android.permission.ACCESS_BACKGROUND_LOCATION");
        LogUtil.a("HwWeatherManagerUtils", "isBackgroundAndNoPermission background_location_permission:", Boolean.valueOf(c2), ", isBackground:", Boolean.valueOf(x));
        return x && !c2;
    }

    public static void a(jbz jbzVar) {
        if (jbzVar == null) {
            LogUtil.h("HwWeatherManagerUtils", "updateWeatherDataOnServiceProvider dataWeather is null");
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            String json = new Gson().toJson(jbzVar);
            long currentTimeMillis = System.currentTimeMillis();
            try {
                jSONObject.put("DataWeather", json);
                jSONObject.put("time", currentTimeMillis);
                String jSONObject2 = jSONObject.toString();
                if (TextUtils.isEmpty(jSONObject2)) {
                    LogUtil.b("HwWeatherManagerUtils", "updateWeatherDataOnServiceProvider cacheDataWeather is empty");
                    return;
                }
                LogUtil.a("HwWeatherManagerUtils", "updateWeatherDataOnServiceProvider, cache delete result:", Boolean.valueOf(SharedPreferenceManager.d(String.valueOf(15), "key_weather_cache_data")), ", cache update errorCode result:", Integer.valueOf(SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(15), "key_weather_cache_data", jSONObject2, new StorageParams(1))));
            } catch (JSONException unused) {
                LogUtil.b("HwWeatherManagerUtils", "updateWeatherDataOnServiceProvider JSONException");
            }
        } catch (JsonIOException unused2) {
            LogUtil.b("HwWeatherManagerUtils", "updateWeatherDataOnServiceProvider JsonIOException");
        }
    }

    public static jbz a() {
        long currentTimeMillis = System.currentTimeMillis();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(currentTimeMillis);
        calendar.set(11, 0);
        calendar.set(13, 0);
        calendar.set(12, 0);
        calendar.set(14, 0);
        long timeInMillis = calendar.getTimeInMillis();
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(15), "key_weather_cache_data");
        if (TextUtils.isEmpty(b)) {
            LogUtil.h("HwWeatherManagerUtils", "getCacheDataWeatherBySharedPreference result is empty");
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(b);
            Gson gson = new Gson();
            long j = jSONObject.getLong("time");
            if (currentTimeMillis - j <= 900000 && j >= timeInMillis) {
                jbz jbzVar = (jbz) gson.fromJson(jSONObject.getString("DataWeather"), jbz.class);
                LogUtil.c("HwWeatherManagerUtils", "getCacheDataWeatherBySharedPreference cache is valid, dataWeather", jbzVar.toString(), " nowTime is ", Long.valueOf(currentTimeMillis), "cacheTime is", Long.valueOf(j));
                return jbzVar;
            }
            LogUtil.a("HwWeatherManagerUtils", "getCacheDataWeatherBySharedPreference cache is not valid, nowTime is ", Long.valueOf(currentTimeMillis), "cacheTime is", Long.valueOf(j));
            return null;
        } catch (JsonParseException | IllegalStateException | NumberFormatException | JSONException e) {
            LogUtil.b("HwWeatherManagerUtils", "getCacheDataWeatherBySharedPreference catch Exception : ", ExceptionUtils.d(e));
            return null;
        }
    }

    public static boolean i() {
        String str;
        return HarmonyBuild.d && (str = HarmonyBuild.b) != null && str.length() > 0 && Character.isDigit(str.charAt(0)) && str.charAt(0) >= "3.0.0".charAt(0);
    }
}
