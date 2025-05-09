package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.utils.CollectionUtils;
import com.huawei.health.R;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwdevice.outofprocess.mgr.device.ProfileAgent;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.profile.client.connect.ServiceConnectCallback;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.LogAnonymous;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/* loaded from: classes6.dex */
public class owp {
    public static int f(Context context) {
        String b = SharedPreferenceManager.b(context, Integer.toString(20002), "map_tracking_sport_type");
        if (a(b)) {
            SharedPreferenceManager.e(context, Integer.toString(20002), "map_tracking_sport_type_sportting", "", new StorageParams());
            return 0;
        }
        try {
            SharedPreferenceManager.e(context, Integer.toString(20002), "map_tracking_sport_type_sportting", b, new StorageParams());
            return Integer.parseInt(b);
        } catch (NumberFormatException e) {
            LogUtil.b("PluginUtil", "getSportTypeFromSharePreference NumberFormatException", LogAnonymous.b((Throwable) e));
            return 0;
        }
    }

    public static int h(Context context) {
        if (context == null) {
            LogUtil.h("PluginUtil", "getTargetTypeFromSharePreference context is null");
            return -1;
        }
        String b = SharedPreferenceManager.b(context, Integer.toString(20002), "sport_target_type");
        if (a(b)) {
            SharedPreferenceManager.e(context, Integer.toString(20002), "sport_target_type_sportting", "", new StorageParams());
        } else {
            try {
                SharedPreferenceManager.e(context, Integer.toString(20002), "sport_target_type_sportting", b, new StorageParams());
                return Integer.parseInt(b);
            } catch (NumberFormatException unused) {
                LogUtil.b("PluginUtil", "getTargetTypeFromSharePreference NumberFormatException");
            }
        }
        return -1;
    }

    public static int e(Context context, int i) {
        int h = h(context);
        if (h != -1) {
            c(context, i, h);
            h(context, -1);
            return h;
        }
        if (i == 258) {
            return b(context, "sport_target_type_outdoor_running");
        }
        if (i == 264) {
            return b(context, "sport_target_type_indoor_running");
        }
        if (i == 257) {
            return b(context, "sport_target_type_outdoor_walk");
        }
        if (i == 259) {
            return b(context, "sport_target_type_outdoor_bike");
        }
        if (i == 282) {
            return b(context, "sport_target_type_outdoor_hiking");
        }
        if (i == 260) {
            return b(context, "sport_target_type_outdoor_climb_hill");
        }
        if (i == 283) {
            return b(context, "sport_target_type_skip");
        }
        return -1;
    }

    private static int b(Context context, String str) {
        String b = SharedPreferenceManager.b(context, Integer.toString(20002), str);
        if (a(b)) {
            return -1;
        }
        try {
            return Integer.parseInt(b);
        } catch (NumberFormatException unused) {
            LogUtil.b("PluginUtil", "getDifferentSportTargetType NumberFormatException");
            return -1;
        }
    }

    public static float j(Context context) {
        String b = SharedPreferenceManager.b(context, Integer.toString(20002), "sport_target_value");
        if (a(b)) {
            SharedPreferenceManager.e(context, Integer.toString(20002), "sport_target_value_sportting", "", new StorageParams());
            return -1.0f;
        }
        SharedPreferenceManager.e(context, Integer.toString(20002), "sport_target_value_sportting", b, new StorageParams());
        try {
            return Float.parseFloat(b);
        } catch (NumberFormatException unused) {
            LogUtil.b("PluginUtil", "getTargetValueFromSharePreference NumberFormatException");
            return -1.0f;
        }
    }

    public static float a(Context context, int i) {
        float j = j(context);
        if (Math.abs(1.0f + j) >= 1.0E-6d) {
            c(context, j, i);
            a(context, -1.0f);
            return j;
        }
        if (i == 258) {
            return d(context, "sport_target_value_outdoor_running");
        }
        if (i == 264) {
            return d(context, "sport_target_value_indoor_running");
        }
        if (i == 257) {
            return d(context, "sport_target_value_outdoor_walk");
        }
        if (i == 259) {
            return d(context, "sport_target_value_outdoor_bike");
        }
        if (i == 282) {
            return d(context, "sport_target_value_outdoor_hiking");
        }
        if (i == 260) {
            return d(context, "sport_target_value_outdoor_climb_hill");
        }
        if (i == 283) {
            return d(context, "sport_target_value_skip");
        }
        return -1.0f;
    }

    public static float a(Context context, int i, int i2) {
        Object obj;
        String b = b(i);
        String b2 = SharedPreferenceManager.b(context, Integer.toString(20002), b);
        LogUtil.a("PluginUtil", "getCustomTargetValueFromSharePreference:", "customSportType:", b, "targetValueStr", b2);
        Map map = (Map) gvv.a(b2, new TypeToken<Map<String, Float>>() { // from class: owp.1
        });
        if (map == null || map.isEmpty() || (obj = map.get(b(i, i2))) == null) {
            return -1.0f;
        }
        return ((Float) obj).floatValue();
    }

    private static float d(Context context, String str) {
        String b = SharedPreferenceManager.b(context, Integer.toString(20002), str);
        if (a(b)) {
            return -1.0f;
        }
        return CommonUtil.j(b);
    }

    private static boolean a(String str) {
        return str == null || "".equals(str) || "-1".equals(str);
    }

    public static void j(Context context, int i) {
        SharedPreferenceManager.e(context, Integer.toString(20002), "map_tracking_sport_type", Integer.toString(i), new StorageParams());
        SharedPreferenceManager.e(context, Integer.toString(20002), "map_tracking_sport_type_sportting", Integer.toString(i), new StorageParams());
    }

    private static void h(Context context, int i) {
        SharedPreferenceManager.e(context, Integer.toString(20002), "sport_target_type", Integer.toString(i), new StorageParams());
        SharedPreferenceManager.e(context, Integer.toString(20002), "sport_target_type_sportting", Integer.toString(i), new StorageParams());
    }

    public static void c(Context context, int i, int i2) {
        if (i == 258) {
            SharedPreferenceManager.e(context, Integer.toString(20002), "sport_target_type_outdoor_running", Integer.toString(i2), new StorageParams());
            return;
        }
        if (i == 264) {
            SharedPreferenceManager.e(context, Integer.toString(20002), "sport_target_type_indoor_running", Integer.toString(i2), new StorageParams());
            return;
        }
        if (i == 257) {
            SharedPreferenceManager.e(context, Integer.toString(20002), "sport_target_type_outdoor_walk", Integer.toString(i2), new StorageParams());
            return;
        }
        if (i == 259) {
            SharedPreferenceManager.e(context, Integer.toString(20002), "sport_target_type_outdoor_bike", Integer.toString(i2), new StorageParams());
            return;
        }
        if (i == 282) {
            SharedPreferenceManager.e(context, Integer.toString(20002), "sport_target_type_outdoor_hiking", Integer.toString(i2), new StorageParams());
        } else if (i == 260) {
            SharedPreferenceManager.e(context, Integer.toString(20002), "sport_target_type_outdoor_climb_hill", Integer.toString(i2), new StorageParams());
        } else if (i == 283) {
            SharedPreferenceManager.e(context, Integer.toString(20002), "sport_target_type_skip", Integer.toString(i2), new StorageParams());
        }
    }

    private static void a(Context context, float f) {
        SharedPreferenceManager.e(context, Integer.toString(20002), "sport_target_value", Float.toString(f), new StorageParams());
        SharedPreferenceManager.e(context, Integer.toString(20002), "sport_target_value_sportting", Float.toString(f), new StorageParams());
    }

    public static void c(Context context, float f, int i) {
        if (i == 258) {
            SharedPreferenceManager.e(context, Integer.toString(20002), "sport_target_value_outdoor_running", Float.toString(f), new StorageParams());
            return;
        }
        if (i == 264) {
            SharedPreferenceManager.e(context, Integer.toString(20002), "sport_target_value_indoor_running", Float.toString(f), new StorageParams());
            return;
        }
        if (i == 257) {
            SharedPreferenceManager.e(context, Integer.toString(20002), "sport_target_value_outdoor_walk", Float.toString(f), new StorageParams());
            return;
        }
        if (i == 259) {
            SharedPreferenceManager.e(context, Integer.toString(20002), "sport_target_value_outdoor_bike", Float.toString(f), new StorageParams());
            return;
        }
        if (i == 282) {
            SharedPreferenceManager.e(context, Integer.toString(20002), "sport_target_value_outdoor_hiking", Float.toString(f), new StorageParams());
        } else if (i == 260) {
            SharedPreferenceManager.e(context, Integer.toString(20002), "sport_target_value_outdoor_climb_hill", Float.toString(f), new StorageParams());
        } else if (i == 283) {
            SharedPreferenceManager.e(context, Integer.toString(20002), "sport_target_value_skip", Float.toString(f), new StorageParams());
        }
    }

    public static void b(Context context, int i, int i2, float f) {
        Map<String, Float> e = e(i, i2, f);
        SharedPreferenceManager.e(context, Integer.toString(20002), b(i), new Gson().toJson(e), new StorageParams());
    }

    private static Map<String, Float> e(int i, int i2, float f) {
        HashMap hashMap = new HashMap(4);
        String b = b(i, 0);
        Float valueOf = Float.valueOf(-1.0f);
        hashMap.put(b, valueOf);
        hashMap.put(b(i, 1), valueOf);
        hashMap.put(b(i, 2), valueOf);
        hashMap.put(b(i, 5), valueOf);
        hashMap.put(b(i, i2), Float.valueOf(f));
        return hashMap;
    }

    private static String b(int i, int i2) {
        return "custom_target_map_key_" + i + "_" + i2;
    }

    public static void e(Context context, int i, boolean z) {
        SharedPreferenceManager.e(context, Integer.toString(20002), d(i), Boolean.toString(z), new StorageParams());
    }

    private static String d(int i) {
        if (i != 264 && i != 283) {
            switch (i) {
                case 257:
                case 258:
                case 259:
                    break;
                default:
                    return "custom_target_default_flag";
            }
        }
        return "custom_target_flag_" + i;
    }

    private static String b(int i) {
        if (i != 264 && i != 283) {
            switch (i) {
                case 257:
                case 258:
                case 259:
                    break;
                default:
                    return "custom_target_default";
            }
        }
        return "custom_target_sport_type_" + i;
    }

    public static boolean c(Context context, String str) {
        String b = SharedPreferenceManager.b(context, Integer.toString(20002), str);
        if (TextUtils.isEmpty(b)) {
            if ("smart_coach_enable_type".equals(str)) {
                SharedPreferenceManager.e(context, Integer.toString(20002), str, Integer.toString(0), new StorageParams());
                return false;
            }
            SharedPreferenceManager.e(context, Integer.toString(20002), str, Integer.toString(1), new StorageParams());
            return true;
        }
        try {
            return Integer.parseInt(b) == 1;
        } catch (NumberFormatException e) {
            LogUtil.b("PluginUtil", "getVoiceEnable NumberFormatException = ", e.getMessage());
            return false;
        }
    }

    public static void a(Context context, String str, boolean z) {
        if (z) {
            SharedPreferenceManager.e(context, Integer.toString(20002), str, Integer.toString(1), new StorageParams());
        } else {
            SharedPreferenceManager.e(context, Integer.toString(20002), str, Integer.toString(0), new StorageParams());
        }
    }

    public static int d(Context context) {
        String b = SharedPreferenceManager.b(context, Integer.toString(20002), "voice_broadcast_interval_type");
        if (!TextUtils.isEmpty(b)) {
            try {
                return Integer.parseInt(b);
            } catch (NumberFormatException unused) {
                LogUtil.b("PluginUtil", "acquireVoiceIntervalSettingType NumberFormatException");
                return 0;
            }
        }
        LogUtil.h("PluginUtil", "acquireVoiceIntervalSettingType you should set it before get");
        return 0;
    }

    public static void i(Context context, int i) {
        SharedPreferenceManager.e(context, Integer.toString(20002), "voice_broadcast_interval_type", String.valueOf(i), new StorageParams());
    }

    public static int i(Context context) {
        String b = SharedPreferenceManager.b(context, Integer.toString(20002), "voice_broadcast_time_interval_value");
        if (!TextUtils.isEmpty(b)) {
            try {
                return Integer.parseInt(b);
            } catch (NumberFormatException unused) {
                LogUtil.b("PluginUtil", "acquireVoiceTimeIntervalValue NumberFormatException");
                return 0;
            }
        }
        LogUtil.h("PluginUtil", "acquireVoiceTimeIntervalValue you should set it before get");
        return 0;
    }

    public static int a(Context context) {
        String b = SharedPreferenceManager.b(context, Integer.toString(20002), "voice_broadcast_distance_interval_value");
        if (!TextUtils.isEmpty(b)) {
            try {
                return Integer.parseInt(b);
            } catch (NumberFormatException unused) {
                LogUtil.b("PluginUtil", "acquireVoiceDistanceIntervalValue NumberFormatException");
                return 0;
            }
        }
        LogUtil.h("PluginUtil", "acquireVoiceDistanceIntervalValue you should set it before get");
        return 0;
    }

    public static void g(Context context, int i) {
        SharedPreferenceManager.e(context, Integer.toString(20002), "voice_broadcast_time_interval_value", String.valueOf(i), new StorageParams());
    }

    public static void c(Context context, int i) {
        SharedPreferenceManager.e(context, Integer.toString(20002), "voice_broadcast_distance_interval_value", String.valueOf(i), new StorageParams());
    }

    public static boolean c(Context context) {
        String b = SharedPreferenceManager.b(context, Integer.toString(20002), "auto_pause_enable_status");
        if (b != null && !"".equals(b)) {
            return Boolean.parseBoolean(b);
        }
        LogUtil.h("PluginUtil", "acquireAutoPauseEnableStatus you should set it before get");
        return false;
    }

    public static void d(Context context, boolean z) {
        SharedPreferenceManager.e(context, Integer.toString(20002), "auto_pause_enable_status", String.valueOf(z), new StorageParams());
    }

    public static boolean k(Context context) {
        String b;
        return (context == null || (b = SharedPreferenceManager.b(context, Integer.toString(20002), "HAS_SHOW_PLAN_TIP")) == null || !"true".equals(b)) ? false : true;
    }

    public static void a(Context context, boolean z) {
        SharedPreferenceManager.e(context, Integer.toString(20002), "HAS_SHOW_PLAN_TIP", String.valueOf(z), new StorageParams());
    }

    public static int e(Context context) {
        if (context == null) {
            return 0;
        }
        String b = SharedPreferenceManager.b(context, Integer.toString(20002), "sport_listen_type");
        if (TextUtils.isEmpty(b)) {
            return 0;
        }
        try {
            return Integer.parseInt(b);
        } catch (NumberFormatException e) {
            LogUtil.b("PluginUtil", "acquireSportListenerType NumberFormatException", LogAnonymous.b((Throwable) e));
            return 0;
        }
    }

    public static void d(Context context, int i) {
        if (context != null) {
            SharedPreferenceManager.e(context, Integer.toString(20002), "sport_listen_type", String.valueOf(i), new StorageParams());
            return;
        }
        LogUtil.h("PluginUtil", "saveSportListenerType  context is null");
    }

    public static int b(Context context) {
        if (context != null) {
            return CommonUtil.e(SharedPreferenceManager.b(context, Integer.toString(20002), "sport_music_control_type"), 0);
        }
        return 0;
    }

    public static void b(Context context, int i) {
        if (context != null) {
            SharedPreferenceManager.e(context, Integer.toString(20002), "sport_music_control_type", String.valueOf(i), new StorageParams());
            return;
        }
        LogUtil.h("PluginUtil", "saveSportMusicControlType  context is null");
    }

    public static boolean d(String str) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:sss");
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            int i = calendar.get(1);
            int i2 = calendar.get(2);
            int i3 = calendar.get(5);
            calendar.setTime(simpleDateFormat.parse(str));
            int i4 = calendar.get(1);
            int i5 = calendar.get(2);
            return i >= i4 && i2 >= i5 && (i > i4 || i2 > i5 || i3 > calendar.get(5));
        } catch (ParseException e) {
            LogUtil.h("PluginUtil", "diffDate:", LogAnonymous.b((Throwable) e));
            return false;
        }
    }

    public static String g(Context context) {
        HiUserPreference userPreference = HiHealthManager.d(context).getUserPreference("com.huawei.health.mc");
        if (userPreference == null) {
            LogUtil.b("PluginUtil", "setPhysicalCycleSwitch userPreference is null.");
            return null;
        }
        return userPreference.getValue();
    }

    public static void c(final String str) {
        LogUtil.c("PluginUtil", "removeDeviceFromProfile:", str);
        if (Utils.o() || TextUtils.isEmpty(str)) {
            return;
        }
        ProfileAgent.PROFILE_AGENT.connectProfile(new ServiceConnectCallback() { // from class: owp.4
            @Override // com.huawei.profile.client.connect.ServiceConnectCallback
            public void onConnect() {
                LogUtil.a("PluginUtil", "profile connected");
                jdx.b(new Runnable() { // from class: owp.4.2
                    @Override // java.lang.Runnable
                    public void run() {
                        new jqf().a(str);
                        ProfileAgent.PROFILE_AGENT.disconnectProfile();
                    }
                });
                ProfileAgent.PROFILE_AGENT.setConnected(true);
            }

            @Override // com.huawei.profile.client.connect.ServiceConnectCallback
            public void onDisconnect() {
                ProfileAgent.PROFILE_AGENT.setConnected(false);
                LogUtil.a("PluginUtil", "profile disconnected");
            }
        });
    }

    public static boolean l(Context context) {
        if (context == null) {
            return false;
        }
        boolean c = jdi.c(context, new String[]{"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"});
        LogUtil.a("PluginUtil", "get Location Permissions:", Boolean.valueOf(c));
        return c;
    }

    public static boolean o(Context context) {
        if (context == null) {
            return false;
        }
        if (Build.VERSION.SDK_INT > 30) {
            return PermissionUtil.e(context, PermissionUtil.PermissionType.SCAN) == PermissionUtil.PermissionResult.GRANTED;
        }
        return l(context);
    }

    public static boolean m(Context context) {
        if (context == null) {
            return false;
        }
        String b = SharedPreferenceManager.b(context, String.valueOf(10008), "auto_scan_device_time");
        if (!TextUtils.isEmpty(b)) {
            long currentTimeMillis = System.currentTimeMillis();
            LogUtil.a("PluginUtil", "nowTime:", Long.valueOf(currentTimeMillis), " lastScanTime:", b);
            long n = CommonUtil.n(context, b);
            if (n != 0 && currentTimeMillis - n > 86400000) {
                SharedPreferenceManager.e(context, String.valueOf(10008), "auto_scan_device_time", String.valueOf(currentTimeMillis), (StorageParams) null);
                LogUtil.a("PluginUtil", "The interval was more than one day");
                return true;
            }
            LogUtil.h("PluginUtil", "The interval was less than one day");
            return false;
        }
        long currentTimeMillis2 = System.currentTimeMillis();
        LogUtil.h("PluginUtil", "first update nowTime:", Long.valueOf(currentTimeMillis2));
        SharedPreferenceManager.e(context, String.valueOf(10008), "auto_scan_device_time", String.valueOf(currentTimeMillis2), (StorageParams) null);
        return true;
    }

    public static boolean c(int i) {
        if (i != 258) {
            return false;
        }
        if (!Utils.o()) {
            return true;
        }
        String countryCode = GRSManager.a(BaseApplication.e()).getCountryCode();
        String[] c = c();
        if (countryCode == null || CollectionUtils.b(c)) {
            ReleaseLogUtil.b("PluginUtil", "isShowRouteDraw countryCode = ", countryCode);
            return false;
        }
        boolean contains = ((List) Arrays.stream(c).collect(Collectors.toList())).contains(countryCode);
        ReleaseLogUtil.b("PluginUtil", "isShowRouteDraw countryCode = ", countryCode);
        return contains;
    }

    private static String[] c() {
        try {
            return BaseApplication.e().getResources().getStringArray(R.array._2130968694_res_0x7f040076);
        } catch (Resources.NotFoundException e) {
            ReleaseLogUtil.c("PluginUtil", "getSupportCountry meet exception: ", e.getMessage());
            return new String[0];
        }
    }
}
