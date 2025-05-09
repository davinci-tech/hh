package defpackage;

import android.content.Context;
import android.content.res.Configuration;
import android.text.TextUtils;
import com.huawei.health.devicemgr.api.constant.GetDeviceInfoMode;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.devicemgr.business.entity.HwGetDevicesParameter;
import com.huawei.hms.mlsdk.asr.MLAsrConstants;
import com.huawei.hms.network.embedded.w9;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwversionmgr.manager.HwVersionManager;
import com.huawei.openalliance.ad.constant.AiCoreSdkConstant;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.OperationKey;
import com.huawei.wisecloud.drmclient.license.HwDrmConstant;
import health.compact.a.CommonUtil;
import health.compact.a.EnvironmentUtils;
import health.compact.a.GRSManager;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import health.compact.a.WhiteBoxManager;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Marker;

/* loaded from: classes5.dex */
public class kxz {
    private static final HashMap<Integer, Long> b = new HashMap() { // from class: kxz.4
        private static final long serialVersionUID = -3166334915769251418L;

        {
            try {
                put(18, Long.valueOf(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse("2018/09/20 00:00:00").getTime()));
                put(19, Long.valueOf(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse("2018/09/30 00:00:00").getTime()));
                put(20, Long.valueOf(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse("2018/10/1 00:00:00").getTime()));
                put(21, Long.valueOf(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse("2018/10/1 00:00:00").getTime()));
                put(23, Long.valueOf(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse("2018/09/20 00:00:00").getTime()));
                put(24, Long.valueOf(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse("2018/10/1 00:00:00").getTime()));
            } catch (ParseException e2) {
                LogUtil.b("UpdateUtil", "DEVICE_SUPPORT_START ParseException e:", e2.getMessage());
            }
        }
    };
    private static final HashMap<Integer, Long> e = new HashMap() { // from class: kxz.1
        private static final long serialVersionUID = 2263858593329879485L;

        {
            try {
                put(18, Long.valueOf(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse("2019/02/19 23:59:59").getTime()));
                put(19, Long.valueOf(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse("2019/02/28 23:59:59").getTime()));
                put(20, Long.valueOf(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse("2019/02/28 23:59:59").getTime()));
                put(21, Long.valueOf(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse("2019/02/28 23:59:59").getTime()));
                put(23, Long.valueOf(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse("2019/02/19 23:59:59").getTime()));
                put(24, Long.valueOf(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse("2019/02/28 23:59:59").getTime()));
            } catch (ParseException e2) {
                LogUtil.b("UpdateUtil", "DEVICE_SUPPORT_END ParseException e:", e2.getMessage());
            }
        }
    };
    private static boolean j = false;
    private static String d = "";

    /* renamed from: a, reason: collision with root package name */
    private static String f14691a = "";
    private static String c = "；";

    public static String g(String str) {
        LogUtil.a("UpdateUtil", "stringTransfer string = ", str);
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        String[] split = str.split(str.contains(c) ? c : System.lineSeparator());
        StringBuffer stringBuffer = new StringBuffer(str.length());
        boolean z = false;
        for (int i = 0; i < split.length; i++) {
            if (!"".equals(split[i])) {
                if (z) {
                    stringBuffer.append(System.lineSeparator());
                }
                stringBuffer.append(split[i]);
                z = true;
            }
        }
        return stringBuffer.toString();
    }

    public static void d(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str2)) {
            LogUtil.h("UpdateUtil", "upElecCardOpAnalytics key is null");
            return;
        }
        if (!str2.equals("recElectronic") && !str2.equals("saveElectronic") && !i(str)) {
            LogUtil.h("UpdateUtil", "is not receive Electronic, key = ", str2);
            return;
        }
        String a2 = knl.a(str);
        j(BaseApplication.getContext(), str2 + a2, str3);
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(16);
        linkedHashMap.put(str2, str3);
        HwGetDevicesParameter hwGetDevicesParameter = new HwGetDevicesParameter();
        hwGetDevicesParameter.setDeviceIdentify(str);
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.IDENTIFY_DEVICES, hwGetDevicesParameter, "UpdateUtil");
        if (deviceList != null && deviceList.size() > 0) {
            linkedHashMap.put("deviceModel", deviceList.get(0).getDeviceModel());
        }
        LogUtil.a("UpdateUtil", "upElecCardOpAnalytics map = ", linkedHashMap.toString());
        OpAnalyticsUtil.getInstance().setEvent(OperationKey.ELECTRONIC_CARD_PROCESS_80070012.value(), linkedHashMap);
    }

    public static void d(String str, String str2, Boolean bool) {
        if (!i(str)) {
            LogUtil.h("UpdateUtil", "summaryElecCardOpAnalytics, is not receive Electronic");
            return;
        }
        String a2 = knl.a(str);
        Context context = BaseApplication.getContext();
        LinkedHashMap linkedHashMap = new LinkedHashMap(16);
        linkedHashMap.put("recElectronic", w(context, "recElectronic" + a2));
        linkedHashMap.put("saveElectronic", w(context, "saveElectronic" + a2));
        linkedHashMap.put(w9.o, w(context, w9.o + a2));
        linkedHashMap.put("checkElectronic", w(context, "checkElectronic" + a2));
        linkedHashMap.put("hotaElectronic", w(context, "hotaElectronic" + a2));
        linkedHashMap.put("upLoadServer", w(context, "upLoadServer" + a2));
        LinkedHashMap<String, String> linkedHashMap2 = new LinkedHashMap<>(16);
        linkedHashMap2.put("electronicAll", linkedHashMap.toString());
        linkedHashMap2.put("summaryType", str2);
        HwGetDevicesParameter hwGetDevicesParameter = new HwGetDevicesParameter();
        hwGetDevicesParameter.setDeviceIdentify(str);
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.IDENTIFY_DEVICES, hwGetDevicesParameter, "UpdateUtil");
        if (deviceList != null && deviceList.size() > 0) {
            linkedHashMap2.put("deviceModel", deviceList.get(0).getDeviceModel());
        }
        LogUtil.a("UpdateUtil", "upElecCardOpAnalytics map = ", linkedHashMap2.toString());
        OpAnalyticsUtil.getInstance().setEvent(OperationKey.ELECTRONIC_CARD_PROCESS_80070012.value(), linkedHashMap2);
        if (bool.booleanValue()) {
            j(context, "recElectronic" + a2, "");
            j(context, "saveElectronic" + a2, "");
            j(context, w9.o + a2, "");
            j(context, "checkElectronic" + a2, "");
            j(context, "hotaElectronic" + a2, "");
            j(context, "upLoadServer" + a2, "");
        }
    }

    private static boolean i(String str) {
        String a2 = knl.a(str);
        return "true".equals(w(BaseApplication.getContext(), "recElectronic" + a2));
    }

    public static boolean a(String str, int i) {
        Date b2;
        LogUtil.a("UpdateUtil", "isAlreadyUpdated : lastTime = ", str, ",deviceType = ", Integer.valueOf(i));
        if (TextUtils.isEmpty(str) || (b2 = b(str)) == null) {
            return false;
        }
        long time = b2.getTime();
        LogUtil.a("UpdateUtil", "isAlreadyUpdated last = ", Long.valueOf(time));
        if (!CommonUtil.as() || !TextUtils.equals(g(BaseApplication.getContext()), "test_mode")) {
            return (e(i) || HwVersionManager.c(BaseApplication.getContext()).g()) ? Math.abs(System.currentTimeMillis() - time) <= 86400000 : CommonUtil.as() ? Math.abs(System.currentTimeMillis() - time) <= 86400000 : Math.abs(System.currentTimeMillis() - time) <= 259200000;
        }
        LogUtil.a("UpdateUtil", "isAlreadyUpdated return 2 hours ");
        return Math.abs(System.currentTimeMillis() - time) <= AiCoreSdkConstant.CHECK_SUPPORT_INTERVAL;
    }

    public static boolean c(String str) {
        Date b2;
        LogUtil.a("UpdateUtil", "isAlreadyAutoDownload: lastTime", str);
        if (TextUtils.isEmpty(str) || (b2 = b(str)) == null) {
            return false;
        }
        long time = b2.getTime();
        LogUtil.a("UpdateUtil", "isAlreadyAutoDownload: System.currentTimeMillis() ", Long.valueOf(System.currentTimeMillis()), " last ", Long.valueOf(time));
        return Math.abs(System.currentTimeMillis() - time) <= 86400000;
    }

    public static Date a(String str) {
        try {
            return new SimpleDateFormat("yyyyMMdd").parse(str);
        } catch (ParseException e2) {
            LogUtil.b("UpdateUtil", "getDateTime ParseException e = ", e2.getMessage());
            return null;
        }
    }

    public static Date b(String str) {
        try {
            return new SimpleDateFormat(HwDrmConstant.TIME_FORMAT).parse(str);
        } catch (ParseException e2) {
            LogUtil.b("UpdateUtil", "getDateTime ParseException e = ", e2.getMessage());
            return null;
        }
    }

    public static String c() {
        try {
            String h = h(HwDrmConstant.TIME_FORMAT);
            LogUtil.a("UpdateUtil", "getCurrentTimeMinutes: currentTime is ", h);
            return h;
        } catch (Exception unused) {
            LogUtil.b("UpdateUtil", "getCurrentTimeMinutes exception");
            return "";
        }
    }

    public static String d() {
        try {
            String h = h("yyyyMMdd");
            LogUtil.a("UpdateUtil", "getCurrentTime: currentTime = ", h);
            return h;
        } catch (Exception unused) {
            LogUtil.b("UpdateUtil", "getCurrentTime Exception");
            return null;
        }
    }

    private static String h(String str) {
        return new SimpleDateFormat(str, Locale.ENGLISH).format(Calendar.getInstance().getTime());
    }

    public static String c(Context context, String str) {
        String str2 = "update_key_band_auto_check_time";
        if (!TextUtils.isEmpty(str)) {
            str2 = knl.a(str) + "update_key_band_auto_check_time";
        }
        String w = w(context, str2);
        LogUtil.a("UpdateUtil", "getBandAutoCheckTime, autoCheckTime ", w);
        return w;
    }

    public static void s(Context context) {
        long currentTimeMillis = System.currentTimeMillis();
        LogUtil.a("UpdateUtil", "setRegisterLocationTime, time ", Long.valueOf(currentTimeMillis));
        j(context, "update_key_register_location_time", String.valueOf(currentTimeMillis));
    }

    private static String p(Context context) {
        String w = w(context, "update_key_register_location_time");
        LogUtil.a("UpdateUtil", "getRegisterLocationTime, lastTime ", w);
        return w;
    }

    public static boolean j() {
        String p = p(BaseApplication.getContext());
        if (TextUtils.isEmpty(p)) {
            return true;
        }
        LogUtil.a("UpdateUtil", "isRegisterLocationDuration lastTime ", p, ",currentTimeMillis ", Long.valueOf(System.currentTimeMillis()));
        return Math.abs(System.currentTimeMillis() - CommonUtil.g(p)) > Constants.ANALYSIS_EVENT_KEEP_TIME;
    }

    public static void d(long j2, String str, Context context) {
        String str2 = "update_key_psi_upload_time";
        if (!TextUtils.isEmpty(str)) {
            str2 = knl.a(str) + "update_key_psi_upload_time";
        }
        LogUtil.a("UpdateUtil", "setPsiUploadTime, key ", str2, " time ", Long.valueOf(j2));
        j(context, str2, String.valueOf(j2));
    }

    public static long a(String str, Context context) {
        String str2 = "update_key_psi_upload_time";
        if (!TextUtils.isEmpty(str)) {
            str2 = knl.a(str) + "update_key_psi_upload_time";
        }
        String w = w(context, str2);
        LogUtil.a("UpdateUtil", "getPsiUploadTime, key ", str2, " time ", w);
        return jds.c(w);
    }

    public static void a(int i, String str, Context context) {
        String str2 = "update_key_psi_upload_fail_count";
        if (!TextUtils.isEmpty(str)) {
            str2 = knl.a(str) + "update_key_psi_upload_fail_count";
        }
        LogUtil.a("UpdateUtil", "setPsiUploadFailCount, key ", str2, " count ", Integer.valueOf(i));
        j(context, str2, String.valueOf(i));
    }

    public static int c(String str, Context context) {
        String str2 = "update_key_psi_upload_fail_count";
        if (!TextUtils.isEmpty(str)) {
            str2 = knl.a(str) + "update_key_psi_upload_fail_count";
        }
        String w = w(context, str2);
        LogUtil.a("UpdateUtil", "getPsiUploadFailCount, key ", str2, " count ", w);
        return jds.c(w, 10);
    }

    public static void c(int i, String str, Context context) {
        String str2 = "update_key_psi_upload_count";
        if (!TextUtils.isEmpty(str)) {
            str2 = knl.a(str) + "update_key_psi_upload_count";
        }
        LogUtil.a("UpdateUtil", "setPsiUploadCount, key ", str2, " count ", Integer.valueOf(i));
        j(context, str2, String.valueOf(i));
    }

    public static int b(String str, Context context) {
        String str2 = "update_key_psi_upload_count";
        if (!TextUtils.isEmpty(str)) {
            str2 = knl.a(str) + "update_key_psi_upload_count";
        }
        String w = w(context, str2);
        LogUtil.a("UpdateUtil", "getPsiUploadCount, key ", str2, " count ", w);
        return jds.c(w, 10);
    }

    public static void b(String str, String str2, Context context) {
        LogUtil.a("UpdateUtil", "setBandAutoCheckTime, time ", str);
        String str3 = "update_key_band_auto_check_time";
        if (!TextUtils.isEmpty(str2)) {
            str3 = knl.a(str2) + "update_key_band_auto_check_time";
        }
        j(context, str3, str);
    }

    public static void j(String str, Context context) {
        LogUtil.a("UpdateUtil", "setDeviceNewVersion, newVersion ", str);
        j(context, "update_key_band_auto_new_version", str);
    }

    public static String f(Context context) {
        String e2 = e(context, "", "");
        LogUtil.a("UpdateUtil", "getDeviceNewSize, size ", e2);
        return e2;
    }

    public static int f(Context context, String str, String str2) {
        String str3 = "update_key_band_auto_new_size_int";
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            StringBuilder sb = new StringBuilder();
            sb.append(knl.a(str + str2));
            sb.append("update_key_band_auto_new_size_int");
            str3 = sb.toString();
        }
        int c2 = jds.c(w(context, str3), 10);
        LogUtil.a("UpdateUtil", "getDeviceNewSize, size ", Integer.valueOf(c2), " key ", CommonUtil.l(str3));
        return c2;
    }

    public static String e(Context context, String str, String str2) {
        String str3 = "update_key_band_auto_new_size";
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            StringBuilder sb = new StringBuilder();
            sb.append(knl.a(str + str2));
            sb.append("update_key_band_auto_new_size");
            str3 = sb.toString();
        }
        String w = w(context, str3);
        LogUtil.a("UpdateUtil", "getDeviceNewSize, size ", w, " key ", CommonUtil.l(str3));
        return w;
    }

    public static void h(String str, Context context) {
        LogUtil.a("UpdateUtil", "setDeviceNewSize, size ", str);
        a(str, context, "", "");
    }

    public static void a(String str, Context context, String str2, String str3) {
        String str4 = "update_key_band_auto_new_size";
        if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3)) {
            StringBuilder sb = new StringBuilder();
            sb.append(knl.a(str2 + str3));
            sb.append("update_key_band_auto_new_size");
            str4 = sb.toString();
        }
        LogUtil.a("UpdateUtil", "setDeviceNewSize, size ", str, " key ", CommonUtil.l(str4));
        j(context, str4, str);
    }

    public static void e(int i, Context context, String str, String str2) {
        String str3 = "update_key_band_auto_new_size_int";
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            StringBuilder sb = new StringBuilder();
            sb.append(knl.a(str + str2));
            sb.append("update_key_band_auto_new_size_int");
            str3 = sb.toString();
        }
        LogUtil.a("UpdateUtil", "setDeviceNewSize, size ", Integer.valueOf(i), " key ", CommonUtil.l(str3));
        j(context, str3, String.valueOf(i));
    }

    public static String a(Context context, String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append(knl.a(str + str2));
        sb.append("update_key_band_auto_new_content");
        sb.append(f());
        String sb2 = sb.toString();
        String w = w(context, sb2);
        LogUtil.a("UpdateUtil", "getDeviceNewSize, content ", w, " key ", sb2);
        return w;
    }

    public static void e(String str, Context context, String str2, String str3) {
        StringBuilder sb = new StringBuilder();
        sb.append(knl.a(str2 + str3));
        sb.append("update_key_band_auto_new_content");
        sb.append(f());
        String sb2 = sb.toString();
        LogUtil.a("UpdateUtil", "setDeviceNewSize, content ", str, " key ", sb2);
        j(context, sb2, str);
    }

    public static String c(Context context, String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append(knl.a(str + str2));
        sb.append("update_key_band_auto_new_size");
        sb.append(f());
        String sb2 = sb.toString();
        String w = w(context, sb2);
        LogUtil.a("UpdateUtil", "getDeviceNewSize, size ", w, " key ", sb2);
        return w;
    }

    public static void b(String str, Context context, String str2, String str3) {
        StringBuilder sb = new StringBuilder();
        sb.append(knl.a(str2 + str3));
        sb.append("update_key_band_auto_new_size");
        sb.append(f());
        String sb2 = sb.toString();
        LogUtil.a("UpdateUtil", "setDeviceNewSize, size ", str, " key ", sb2);
        j(context, sb2, str);
    }

    public static String f() {
        String e2 = mtj.e(null);
        return e2 == null ? "" : e2.toLowerCase(Locale.ENGLISH);
    }

    public static String h() {
        Configuration configuration = BaseApplication.getContext().getResources().getConfiguration();
        String language = configuration.locale.getLanguage();
        String country = configuration.locale.getCountry();
        String script = Locale.getDefault().getScript();
        String str = language + '-' + country;
        return MLAsrConstants.LAN_ZH.equals(language) ? ("Hans".equals(script) || (TextUtils.isEmpty(script) && "zh-CN".equals(str))) ? "zh-CN" : !"zh-HK".equals(str) ? "zh-TW" : "zh-HK" : str;
    }

    public static String j(Context context) {
        String b2 = b(context, "", "");
        LogUtil.a("UpdateUtil", "getDeviceNewContent, content ", b2);
        return b2;
    }

    public static String b(Context context, String str, String str2) {
        String str3 = "update_key_band_auto_new_content";
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            StringBuilder sb = new StringBuilder();
            sb.append(knl.a(str + str2));
            sb.append("update_key_band_auto_new_content");
            str3 = sb.toString();
        }
        String w = w(context, str3);
        LogUtil.a("UpdateUtil", "getDeviceNewContent, content ", w, " key ", CommonUtil.l(str3));
        return w;
    }

    public static void g(String str, Context context) {
        LogUtil.a("UpdateUtil", "setDeviceNewContent, content ", str);
        d(str, context, "", "");
    }

    public static void d(String str, Context context, String str2, String str3) {
        String str4 = "update_key_band_auto_new_content";
        if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3)) {
            StringBuilder sb = new StringBuilder();
            sb.append(knl.a(str2 + str3));
            sb.append("update_key_band_auto_new_content");
            str4 = sb.toString();
        }
        LogUtil.a("UpdateUtil", "setDeviceNewContent, content ", str, " key ", CommonUtil.l(str4));
        j(context, str4, g(str));
    }

    public static void q(Context context) {
        String str = "";
        try {
            str = h("yyyyMMddHH");
            LogUtil.a("UpdateUtil", "setBandAutoCheckTimeLater: time = ", str);
        } catch (Exception unused) {
            LogUtil.b("UpdateUtil", "setBandAutoCheckTimeLater Exception");
        }
        j(context, "update_key_band_new_version_later", str);
    }

    public static void c(Context context) {
        j(context, "update_key_band_new_version_later", "");
    }

    public static void d(String str, Context context) {
        LogUtil.a("UpdateUtil", "setAppCheckNewVersionCode, versionCode ", str);
        j(context, "update_key_app_new_version_code", str);
    }

    public static String a(Context context) {
        String w = w(context, "update_key_app_new_version_code");
        LogUtil.a("UpdateUtil", "getAppCheckNewVersionCode, versionCode ", w);
        return w;
    }

    public static void f(String str, Context context) {
        LogUtil.a("UpdateUtil", "setAppCheckNewVersionCode, versionCode ", str);
        j(context, "update_key_app_new_version_name", str);
    }

    public static String d(Context context) {
        String w = w(context, "update_key_app_new_version_name");
        LogUtil.a("UpdateUtil", "getAppCheckNewVersionCode, versionName ", w);
        return w;
    }

    public static String b(Context context) {
        String w = w(context, "update_key_app_store_path");
        LogUtil.a("UpdateUtil", "getAppStorePath, storePath ", w);
        return w;
    }

    public static void i(String str, Context context) {
        LogUtil.a("UpdateUtil", "setAppStorePath, storePath ", str);
        j(context, "update_key_app_store_path", str);
    }

    public static void a(String str, String str2, Context context) {
        LogUtil.a("UpdateUtil", "setBandCheckEnterpriseID, enterpriseId ", CommonUtil.l(str));
        j(context, knl.d(str2) + "update_key_band_enterprise_id", str);
    }

    public static String b(Context context, String str) {
        String w = w(context, knl.d(str) + "update_key_band_enterprise_id");
        LogUtil.a("UpdateUtil", "getBandCheckEnterpriseID, enterpriseId ", CommonUtil.l(w));
        return w;
    }

    public static void j(String str, String str2, Context context) {
        LogUtil.a("UpdateUtil", "setBandOsNewVersion, version ", str);
        j(context, knl.d(str2) + "update_key_band_os_new_version", str);
    }

    public static String n(Context context, String str) {
        String w = w(context, knl.d(str) + "update_key_band_os_new_version");
        LogUtil.a("UpdateUtil", "getBandOsNewVersion, version ", w);
        return w;
    }

    public static void d(String str, String str2, Context context) {
        LogUtil.a("UpdateUtil", "setBandCheckNewVersion, version ", str);
        j(context, knl.d(str2) + "update_key_band_new_version", str);
    }

    public static String j(Context context, String str) {
        String w = w(context, knl.d(str) + "update_key_band_new_version");
        LogUtil.a("UpdateUtil", "getBandCheckNewVersion, version ", w);
        return w;
    }

    public static void c(String str, String str2, Context context) {
        LogUtil.a("UpdateUtil", "setBandCheckVersionType, versionType ", str);
        j(context, knl.d(str2) + "update_key_band_version_type", str);
    }

    public static String f(Context context, String str) {
        String w = w(context, knl.d(str) + "update_key_band_version_type");
        LogUtil.a("UpdateUtil", "getBandCheckVersionType, versionType ", w);
        return w;
    }

    public static void h(String str, String str2, Context context) {
        LogUtil.a("UpdateUtil", "setBandEnterpriseAgreeing, agreeing ", str);
        j(context, knl.d(str2) + "update_key_band_enterprise_agreeing", str);
    }

    public static String i(Context context, String str) {
        String w = w(context, knl.d(str) + "update_key_band_enterprise_agreeing");
        LogUtil.a("UpdateUtil", "getBandEnterpriseAgreeing, agreeing ", w);
        return w;
    }

    public static void a(String str, String str2, String str3, Context context) {
        String str4 = knl.d(str) + "update_key_band_byte_size" + str3;
        LogUtil.a("UpdateUtil", "setBandByteSize, bandByteSize ", str2, " key ", str4);
        j(context, str4, str2);
    }

    public static String d(Context context, String str, String str2) {
        String str3 = knl.d(str) + "update_key_band_byte_size" + str2;
        String w = w(context, str3);
        LogUtil.a("UpdateUtil", "getBandByteSize, bandByteSize ", w, " key ", str3);
        return w;
    }

    public static void a(Context context, boolean z) {
        LogUtil.a("UpdateUtil", "setUpdateShowing, isShowing ", String.valueOf(z), " key ", "update_key_is_update_showing");
        j(context, "update_key_is_update_showing", String.valueOf(z));
    }

    public static boolean o(Context context) {
        String w = w(context, "update_key_is_update_showing");
        LogUtil.a("UpdateUtil", "isUpdateShowing, isShowing ", w, " key ", "update_key_is_update_showing");
        return Boolean.parseBoolean(w);
    }

    public static void e(String str, String str2, Context context) {
        LogUtil.a("UpdateUtil", "setBandCheckNewVersion, versionId:", str);
        j(context, knl.a(str2) + "update_key_band_new_version_id", str);
    }

    public static String h(Context context, String str) {
        String w = w(context, knl.a(str) + "update_key_band_new_version_id");
        LogUtil.a("UpdateUtil", "setBandCheckNewVersion, versionId:", w);
        return w;
    }

    public static void i(String str, String str2, Context context) {
        LogUtil.a("UpdateUtil", "setBandLastVersionCode, lastVersionCode ", str2);
        j(context, knl.a(str) + "update_key_band_last_version_code", str2);
    }

    public static String o(Context context, String str) {
        String w = w(context, knl.d(str) + "update_key_band_store_path");
        LogUtil.a("UpdateUtil", "getBandStorePath, storePath ", w);
        return w;
    }

    public static void f(String str, String str2, Context context) {
        LogUtil.a("UpdateUtil", "setBandStorePath, storePath ", str2);
        j(context, knl.d(str) + "update_key_band_store_path", str2);
    }

    public static String g(Context context, String str) {
        String w = w(context, knl.d(str) + "update_key_band_device_version");
        LogUtil.a("UpdateUtil", "getBandDeviceVersion, version ", w);
        return w;
    }

    public static void g(String str, String str2, Context context) {
        LogUtil.a("UpdateUtil", "setBandDeviceVersion, version ", str2);
        j(context, knl.d(str) + "update_key_band_device_version", str2);
    }

    public static String n(Context context) {
        String w = w(context, "update_key_ota_device_support");
        LogUtil.a("UpdateUtil", "getOtaVersionSupport, otaVersion ", w);
        return w;
    }

    public static void l(String str, Context context) {
        LogUtil.a("UpdateUtil", "setOtaVersionSupport, otaVersion ", str);
        j(context, "update_key_ota_device_support", str);
    }

    public static String e(String str, Context context) {
        String str2 = knl.d(str) + "update_key_band_device_package_name";
        String w = w(context, str2);
        LogUtil.a("UpdateUtil", "getDeviceOtaPackageName, packageName ", w, " key ", str2);
        return w;
    }

    public static void k(String str, String str2, Context context) {
        String str3 = knl.d(str) + "update_key_band_device_package_name";
        LogUtil.a("UpdateUtil", "setDeviceOtaPackageName, packageName ", str2, " key ", str3);
        j(context, str3, str2);
    }

    public static void d(Context context, String str) {
        String o = o(context, str);
        LogUtil.a("UpdateUtil", "deleteUpdateDfu: path = ", o);
        String w = w(context, knl.d(str) + "save_upgrade_package");
        if (CommonUtil.as() && "true".equals(w)) {
            LogUtil.h("UpdateUtil", "deleteUpdateDfu: SavePackageSwitch is open do not delete");
            return;
        }
        if (TextUtils.isEmpty(o)) {
            return;
        }
        File file = new File(o);
        if (!file.exists() || file.delete()) {
            return;
        }
        LogUtil.b("UpdateUtil", "deleteUpdateDfu: path = ", o, " failed!");
    }

    public static void e(Context context, boolean z) {
        LogUtil.a("UpdateUtil", "setHaveNewBandVersion, haveNewVersion ", Boolean.valueOf(z));
        j(context, "update_key_band_new_version_tip", "" + z);
    }

    public static void b(boolean z) {
        LogUtil.a("UpdateUtil", "setBandOtaStatus, isOta ", Boolean.valueOf(z));
        j = z;
    }

    public static boolean i(Context context) {
        LogUtil.a("UpdateUtil", "getBandOtaStatus, mTransfer ", Boolean.valueOf(j));
        return j;
    }

    public static void j(String str) {
        d = str;
    }

    public static String a() {
        return d;
    }

    public static void f(String str) {
        f14691a = str;
    }

    public static String b() {
        return f14691a;
    }

    public static void c(Context context, kxl kxlVar, String str) {
        if (kxlVar != null) {
            LogUtil.a("UpdateUtil", "saveDownloadInfo, applicationInfo:", kxlVar.toString());
            String a2 = knl.a(str);
            j(context, "update_key_band_download_spath" + a2, kxlVar.r());
            j(context, "update_key_band_download_byteSize" + a2, String.valueOf(kxlVar.c()));
            j(context, "update_key_band_download_md5" + a2, kxlVar.h());
            j(context, "update_key_band_download_sha256" + a2, kxlVar.s());
            j(context, "update_key_band_download_downloadurl" + a2, kxlVar.i());
            j(context, "update_key_band_download_macorsn" + a2, str);
            j(context, "update_key_band_download_versionname" + a2, kxlVar.v());
            j(context, "update_key_band_download_version_id" + a2, kxlVar.w());
        }
    }

    public static kxl k(Context context, String str) {
        String a2 = knl.a(str);
        kxl kxlVar = new kxl();
        try {
            kxlVar.k(w(context, "update_key_band_download_spath" + a2));
            kxlVar.e(Long.parseLong(w(context, "update_key_band_download_byteSize" + a2)));
            kxlVar.f(w(context, "update_key_band_download_md5" + a2));
            kxlVar.o(w(context, "update_key_band_download_sha256" + a2));
            kxlVar.a(w(context, "update_key_band_download_downloadurl" + a2));
            kxlVar.s(w(context, "update_key_band_download_versionname" + a2));
            kxlVar.t(w(context, "update_key_band_download_version_id" + a2));
        } catch (NumberFormatException e2) {
            LogUtil.b("UpdateUtil", "getDownloadInfo NumberFormatException ", e2.getMessage());
        }
        LogUtil.a("UpdateUtil", "getDownloadInfo, applicationInfo:", kxlVar.toString());
        return kxlVar;
    }

    public static void u(Context context, String str) {
        String a2 = knl.a(str);
        LogUtil.a("UpdateUtil", "setBandCheckBindTime: timekey ", CommonUtil.l(a2));
        String w = w(context, a2);
        if (!TextUtils.isEmpty(w)) {
            LogUtil.h("UpdateUtil", "setBandCheckBindTime: time existent ", w);
            return;
        }
        try {
            String h = h("yyyyMMddHH");
            LogUtil.a("UpdateUtil", "setBandCheckBindTime: time ", h);
            j(context, a2, h);
        } catch (Exception unused) {
            LogUtil.b("UpdateUtil", "setBandCheckBindTime Exception");
        }
    }

    private static String v(Context context, String str) {
        String a2 = knl.a(str);
        String w = w(context, a2);
        LogUtil.a("UpdateUtil", "getBandCheckBindTime, bindTime ", w, " ,key ", CommonUtil.l(a2));
        return w;
    }

    public static boolean d(String str) {
        Date parse;
        String v = v(BaseApplication.getContext(), str);
        if (TextUtils.isEmpty(v)) {
            return true;
        }
        LogUtil.a("UpdateUtil", "isBandCheckBindingDuring bindTime ", v);
        try {
            parse = new SimpleDateFormat("yyyyMMddHH").parse(v);
            LogUtil.a("UpdateUtil", "isBandCheckBindingDuring bindDate.getTime() ", Long.valueOf(parse.getTime()), ",currentTimeMillis ", Long.valueOf(System.currentTimeMillis()));
        } catch (ParseException unused) {
            LogUtil.b("UpdateUtil", "isBandCheckBindingDuring Exception e");
        }
        return Math.abs(System.currentTimeMillis() - parse.getTime()) < 2592000000L;
    }

    public static void a(Context context, DeviceInfo deviceInfo) {
        if (Utils.o()) {
            LogUtil.h("UpdateUtil", "setDeviceIdentify version is oversea");
            return;
        }
        if (context == null || deviceInfo == null) {
            LogUtil.h("UpdateUtil", "setDeviceIdentify context or deviceInfo is null");
            return;
        }
        String str = knl.a(deviceInfo.getSecurityDeviceId()) + "update_key_band_identify_mac";
        LogUtil.a("UpdateUtil", "setDeviceIdentify key: ", CommonUtil.l(str));
        if (!TextUtils.isEmpty(w(context, str))) {
            LogUtil.a("UpdateUtil", "setDeviceIdentify has exist");
            return;
        }
        String deviceIdentify = deviceInfo.getDeviceIdentify();
        if (TextUtils.isEmpty(deviceIdentify)) {
            return;
        }
        j(context, str, cvx.d(WhiteBoxManager.d().b(deviceIdentify)));
    }

    public static String m(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            LogUtil.h("UpdateUtil", "getDeviceIdentify context or deviceSn is null");
            return "";
        }
        String str2 = knl.a(str) + "update_key_band_identify_mac";
        LogUtil.a("UpdateUtil", "getDeviceIdentify key: ", CommonUtil.l(str2));
        String w = w(context, str2);
        if (TextUtils.isEmpty(w)) {
            LogUtil.h("UpdateUtil", "getDeviceIdentify encryptDeviceIdentify is null");
            return "";
        }
        byte[] a2 = WhiteBoxManager.d().a(cvx.a(w));
        if (a2 == null || a2.length == 0) {
            LogUtil.h("UpdateUtil", "getDeviceIdentify deviceIdentifyBytes is null");
            return "";
        }
        try {
            return new String(a2, "utf-8");
        } catch (UnsupportedEncodingException unused) {
            LogUtil.h("UpdateUtil", "getDeviceIdentify UnsupportedEncodingException");
            return "";
        }
    }

    public static void c(Context context, DeviceInfo deviceInfo) {
        String str = knl.a(deviceInfo.getSecurityDeviceId()) + "update_key_band_check_address";
        String w = w(context, str);
        LogUtil.a("UpdateUtil", "setPhdDeviceUdid key ", CommonUtil.l(str));
        if (TextUtils.isEmpty(w)) {
            j(context, str, a(deviceInfo));
        }
    }

    private static String a(DeviceInfo deviceInfo) {
        String replace;
        if (deviceInfo == null) {
            return "";
        }
        if (Utils.o() || deviceInfo.getDeviceIdType() == 1) {
            replace = knl.d(deviceInfo.getSecurityDeviceId()).replace(Marker.ANY_NON_NULL_MARKER, "A").replace("=", "A").replace("/", "A");
            if (replace.length() >= 24) {
                replace = replace.substring(0, 24);
            }
        } else {
            replace = deviceInfo.getDeviceIdentify();
        }
        return deviceInfo.getProductType() < 58 ? replace : d(deviceInfo);
    }

    private static String d(DeviceInfo deviceInfo) {
        String deviceIdentify = deviceInfo.getDeviceIdentify();
        if (!TextUtils.isEmpty(deviceIdentify)) {
            deviceIdentify = deviceIdentify.toUpperCase(Locale.ENGLISH).replace(":", "");
        }
        String securityDeviceId = deviceInfo.getSecurityDeviceId();
        if (!TextUtils.isEmpty(securityDeviceId)) {
            securityDeviceId = securityDeviceId.toUpperCase(Locale.ENGLISH).replace(":", "");
        }
        if (deviceIdentify != null && deviceIdentify.equals(securityDeviceId)) {
            return knl.a(deviceIdentify);
        }
        return knl.a(securityDeviceId + deviceIdentify);
    }

    public static String t(Context context, String str) {
        String str2 = knl.a(str) + "update_key_band_check_address";
        String w = w(context, str2);
        LogUtil.a("UpdateUtil", "getPhdDeviceUdid key ", CommonUtil.l(str2));
        return w;
    }

    public static void q(Context context, String str) {
        LogUtil.a("UpdateUtil", "resetBandUpdate");
        e(context, false);
        b("", str, context);
        j("", str, context);
        d("", str, context);
        e("", str, context);
        i(str, "", context);
        c(context, new kxl(), "");
        c(context);
        l("", context);
    }

    public static boolean e(String str) {
        int indexOf;
        if (TextUtils.isEmpty(str) || (indexOf = str.indexOf(":")) < 0) {
            return false;
        }
        String substring = str.substring(0, indexOf);
        LogUtil.a("UpdateUtil", "isHttpProtocol protocol:", substring);
        return "http".equalsIgnoreCase(substring);
    }

    private static boolean e(int i) {
        switch (i) {
            case 18:
            case 19:
            case 20:
            case 21:
            case 23:
            case 24:
                long currentTimeMillis = System.currentTimeMillis();
                HashMap<Integer, Long> hashMap = b;
                String l = hashMap.get(Integer.valueOf(i)).toString();
                Long valueOf = Long.valueOf(currentTimeMillis);
                HashMap<Integer, Long> hashMap2 = e;
                LogUtil.a("UpdateUtil", "DEVICE_SUPPORT_START = ", l, ",time = ", valueOf, ",DEVICE_SUPPORT_END = ", hashMap2.get(Integer.valueOf(i)).toString());
                return Long.parseLong(hashMap.get(Integer.valueOf(i)).toString()) <= currentTimeMillis && Long.parseLong(hashMap2.get(Integer.valueOf(i)).toString()) >= currentTimeMillis;
            case 22:
            default:
                return cwc.c(i);
        }
    }

    public static void x(Context context, String str) {
        LogUtil.a("UpdateUtil", "setBandLocationInfo");
        h(context, "update_key_psi_upload_location", str);
    }

    public static String k(Context context) {
        return w(context, "update_key_psi_upload_location");
    }

    public static void y(Context context, String str) {
        LogUtil.a("UpdateUtil", "setBandLocationInfo");
        h(context, "update_key_ota_device_upload_location", str);
    }

    public static String h(Context context) {
        return w(context, "update_key_ota_device_upload_location");
    }

    public static String r(Context context, String str) {
        String t = t(context, str);
        String valueOf = String.valueOf(CommonUtil.d(context));
        JSONObject jSONObject = new JSONObject();
        try {
            if (!TextUtils.isEmpty(t)) {
                jSONObject.put("phdDeviceUdid", t);
            }
            if (!TextUtils.isEmpty(valueOf)) {
                jSONObject.put("appVersion", valueOf);
            }
        } catch (JSONException unused) {
            LogUtil.b("UpdateUtil", "getPhdDeviceUdidAndAppVersion JSONException");
        }
        return jSONObject.toString();
    }

    public static String l(Context context, String str) {
        String h = !HwVersionManager.c(BaseApplication.getContext()).i() ? h(context) : "";
        String t = t(context, str);
        String valueOf = String.valueOf(CommonUtil.d(context));
        JSONObject jSONObject = new JSONObject();
        try {
            if (!TextUtils.isEmpty(h)) {
                jSONObject = new JSONObject(h);
            }
            if (!TextUtils.isEmpty(t)) {
                jSONObject.put("phdDeviceUdid", t);
            }
            if (!TextUtils.isEmpty(valueOf)) {
                jSONObject.put("appVersion", valueOf);
            }
        } catch (JSONException unused) {
            LogUtil.b("UpdateUtil", "setBandCheckNewVersionAndUploadLocation JSONException");
        }
        return jSONObject.toString();
    }

    public static String g(Context context) {
        String w = w(context, "update_key_band_check_url");
        LogUtil.a("UpdateUtil", "getCheckUrlMode value:", w);
        return w;
    }

    public static String m(Context context) {
        DeviceInfo i = i();
        if (i == null) {
            LogUtil.h("UpdateUtil", "getSaveUpgradePackageMode deviceInfo == null");
            return "";
        }
        String w = w(context, knl.d(i.getDeviceIdentify()) + "save_upgrade_package");
        LogUtil.a("UpdateUtil", "getSaveUpgradePackageMode value = ", w);
        return w;
    }

    public static List<String> l(Context context) {
        DeviceInfo i = i();
        if (i == null) {
            LogUtil.h("UpdateUtil", "getLoopUpgradePackagePaths deviceInfo == null");
            return new ArrayList();
        }
        String w = w(context, knl.d(i.getDeviceIdentify()) + "loop_upgrade_package_path_list");
        LogUtil.a("UpdateUtil", "getLoopUpgradePackagePaths: ", w);
        if (TextUtils.isEmpty(w)) {
            return new ArrayList();
        }
        return new ArrayList(Arrays.asList(w.split(",")));
    }

    public static void e(List<String> list, Context context) {
        if (list == null) {
            LogUtil.h("UpdateUtil", "setLoopUpgradePackagePaths paths == null");
            return;
        }
        DeviceInfo i = i();
        if (i == null) {
            LogUtil.h("UpdateUtil", "setLoopUpgradePackagePaths deviceInfo == null");
            return;
        }
        String str = knl.d(i.getDeviceIdentify()) + "loop_upgrade_package_path_list";
        String join = String.join(",", list);
        j(context, str, join);
        LogUtil.a("UpdateUtil", "setLoopUpgradePackagePaths: ", join);
    }

    private static DeviceInfo i() {
        if (EnvironmentUtils.e()) {
            List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, "UpdateUtil");
            if (koq.b(deviceList)) {
                return null;
            }
            return deviceList.get(0);
        }
        return cun.c().getDeviceInfo(GetDeviceInfoMode.CONNECTED_MAIN_DEVICES_WITHOUT_AW70, null, "UpdateUtil");
    }

    public static void c(Context context, boolean z) {
        LogUtil.a("UpdateUtil", "setAutoNotRemind isChecked:", Boolean.valueOf(z));
        j(context, "update_key_auto_update_not_remind", String.valueOf(z));
    }

    public static boolean e(Context context) {
        return "true".equals(w(context, "update_key_auto_update_not_remind"));
    }

    private static String w(Context context, String str) {
        return SharedPreferenceManager.b(context, String.valueOf(1003), str);
    }

    private static int j(Context context, String str, String str2) {
        return SharedPreferenceManager.e(context, String.valueOf(1003), str, str2, (StorageParams) null);
    }

    private static int h(Context context, String str, String str2) {
        StorageParams storageParams = new StorageParams();
        storageParams.d(1);
        return SharedPreferenceManager.e(context, String.valueOf(1003), str, str2, storageParams);
    }

    public static int e(Context context, String str, String str2, boolean z) {
        String str3 = knl.d(str) + str2;
        int j2 = j(context, str3, String.valueOf(z));
        LogUtil.a("UpdateUtil", "saveOtaUpdateState key:", str3, " value:", Boolean.valueOf(z), " result:", Integer.valueOf(j2));
        return j2;
    }

    public static String e() {
        String commonCountryCode = GRSManager.a(BaseApplication.getContext()).getCommonCountryCode();
        String e2 = jah.c().e("domain_honor_ota_privacy");
        String e3 = jah.c().e("domain_honor_country");
        LogUtil.a("UpdateUtil", "countryCode: ", commonCountryCode, "honorUpdateUrl: ", e2, " honorCountrysString: ", e3);
        if (e2 == null || e3 == null) {
            return null;
        }
        if ("ALL".equalsIgnoreCase(e3)) {
            return e2;
        }
        for (String str : e3.split(",")) {
            if (str.equalsIgnoreCase(commonCountryCode)) {
                return e2;
            }
        }
        return null;
    }

    public static String e(Context context, String str) {
        String w = w(context, str);
        LogUtil.a("UpdateUtil", "getAppchangeLogPath, changeLogPath ", w);
        return w;
    }

    public static void i(Context context, String str, String str2) {
        LogUtil.a("UpdateUtil", "setAppChangeLogPath, changeLogPath ", str2, "changeLogName", str);
        j(context, str, str2);
    }

    public static String a(Context context, String str) {
        String w = w(context, str);
        LogUtil.a("UpdateUtil", "getAppChangeLogUri, changeLogPath ", w);
        return w;
    }

    public static void g(Context context, String str, String str2) {
        LogUtil.a("UpdateUtil", "setAppChangeLogUri, changeLogUriKey ", str, "changeLogUri", str2);
        j(context, str, str2);
    }

    public static boolean s(Context context, String str) {
        String f = f(context, str);
        String i = i(context, str);
        return (TextUtils.isEmpty(f) || f.equals("1") || (!TextUtils.isEmpty(i) && !i.equals("0"))) ? false : true;
    }

    public static boolean p(Context context, String str) {
        String f = f(context, str);
        String i = i(context, str);
        return TextUtils.isEmpty(f) || f.equals("1") || (!TextUtils.isEmpty(i) && i.equals("1"));
    }

    public static boolean a(DeviceInfo deviceInfo, int i) {
        if (i == 97 && Utils.o()) {
            LogUtil.a("UpdateUtil", "The current version is Oversea");
            return false;
        }
        boolean c2 = cwi.c(deviceInfo, i);
        LogUtil.a("UpdateUtil", "checkSupportCapability is = ", Boolean.valueOf(c2), " =index= ", Integer.valueOf(i));
        return c2;
    }
}
