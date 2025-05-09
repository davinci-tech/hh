package com.huawei.watchface;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.huawei.watchface.api.HwWatchFaceApi;
import com.huawei.watchface.environment.Environment;
import com.huawei.watchface.utils.ArrayUtils;
import com.huawei.watchface.utils.BackgroundTaskUtils;
import com.huawei.watchface.utils.HwLog;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes7.dex */
public class dp {

    /* renamed from: a, reason: collision with root package name */
    private static Map<String, SharedPreferences> f10993a = new HashMap();
    private static Comparator<Map.Entry<String, Long>> b = new Comparator<Map.Entry<String, Long>>() { // from class: com.huawei.watchface.dp.1
        @Override // java.util.Comparator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(Map.Entry<String, Long> entry, Map.Entry<String, Long> entry2) {
            return entry2.getValue().compareTo(entry.getValue());
        }
    };

    public static void a(String str, String str2, String str3) {
        SharedPreferences sharedPreferences = f10993a.get(str3);
        if (sharedPreferences == null) {
            sharedPreferences = a(Environment.getApplicationContext(), str3);
            if (sharedPreferences == null) {
                HwLog.i("SharepreferenceUtils", str3 + "sp is null");
                return;
            }
            f10993a.put(str3, sharedPreferences);
        }
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(str, str2);
        edit.apply();
    }

    public static void a(String str, Set<String> set, String str2) {
        SharedPreferences a2 = a(Environment.getApplicationContext(), str2);
        if (a2 == null) {
            return;
        }
        SharedPreferences.Editor edit = a2.edit();
        edit.putStringSet(str, set);
        edit.apply();
    }

    public static Set<String> a(String str, String str2, Set<String> set) {
        SharedPreferences a2 = a(Environment.getApplicationContext(), str2);
        return a2 != null ? a2.getStringSet(str, set) : set;
    }

    public static boolean a(String str, boolean z) {
        return a(str, "themename", z);
    }

    public static boolean a(String str, String str2, boolean z) {
        SharedPreferences a2 = a(Environment.getApplicationContext(), str2);
        if (a2 == null) {
            return false;
        }
        return a2.getBoolean(str, z);
    }

    public static String a(String str, String str2) {
        SharedPreferences a2 = a(Environment.getApplicationContext(), str2);
        if (a2 == null) {
            return null;
        }
        return a2.getString(str, null);
    }

    public static String b(String str, String str2, String str3) {
        SharedPreferences a2 = a(Environment.getApplicationContext(), str2);
        if (a2 == null) {
            return null;
        }
        return a2.getString(str, str3);
    }

    public static void a(String str, long j) {
        SharedPreferences sharedPreferences = f10993a.get("themename");
        if (sharedPreferences == null) {
            sharedPreferences = a(Environment.getApplicationContext(), "themename");
            if (sharedPreferences == null) {
                HwLog.i("SharepreferenceUtils", "theme_name sp is null");
                return;
            }
            f10993a.put("themename", sharedPreferences);
        }
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putLong(str, j);
        edit.apply();
    }

    public static long b(String str, long j) {
        SharedPreferences a2 = a(Environment.getApplicationContext(), "themename");
        if (a2 == null) {
            return 0L;
        }
        return a2.getLong(str, j);
    }

    public static void b(String str, boolean z) {
        a(str, z, "themename");
    }

    public static void a(String str, boolean z, String str2) {
        SharedPreferences a2 = a(Environment.getApplicationContext(), str2);
        if (a2 == null) {
            return;
        }
        SharedPreferences.Editor edit = a2.edit();
        edit.putBoolean(str, z);
        edit.apply();
    }

    public static SharedPreferences a(Context context, String str) {
        try {
            return cs.a(context).getSharedPreferences(str, 0);
        } catch (IllegalArgumentException e) {
            HwLog.e("SharepreferenceUtils", "getSharedPreferences IllegalArgumentException " + HwLog.printException((Exception) e));
            return null;
        } catch (Exception e2) {
            HwLog.e("SharepreferenceUtils", "getSharedPreferences Exception " + HwLog.printException(e2));
            return null;
        }
    }

    public static SharedPreferences b(Context context, String str) {
        if (context == null) {
            return null;
        }
        try {
            return context.getSharedPreferences(str, 0);
        } catch (IllegalArgumentException e) {
            HwLog.e("SharepreferenceUtils", "getCeSharedPreferences IllegalArgumentException " + HwLog.printException((Exception) e));
            return null;
        } catch (Exception e2) {
            HwLog.e("SharepreferenceUtils", "getCeSharedPreferences Exception " + HwLog.printException(e2));
            return null;
        }
    }

    public static void a(String str, int i) {
        SharedPreferences a2 = a(Environment.getApplicationContext(), "themename");
        if (a2 == null) {
            return;
        }
        SharedPreferences.Editor edit = a2.edit();
        edit.putInt(str, i);
        edit.apply();
    }

    public static void a(ConcurrentHashMap<String, ?> concurrentHashMap, String str) {
        if (ArrayUtils.a(concurrentHashMap)) {
            HwLog.e("SharepreferenceUtils", "writeMap map is null");
            return;
        }
        SharedPreferences sharedPreferences = f10993a.get(str);
        if (sharedPreferences == null) {
            sharedPreferences = a(Environment.getApplicationContext(), str);
            if (sharedPreferences == null) {
                HwLog.e("SharepreferenceUtils", "writeMap sharedPreferences is null");
                return;
            }
            f10993a.put(str, sharedPreferences);
        }
        SharedPreferences.Editor edit = sharedPreferences.edit();
        Iterator<Map.Entry<String, ?>> it = concurrentHashMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, ?> next = it.next();
            String key = next.getKey();
            Object value = next.getValue();
            if (value instanceof Boolean) {
                edit.putBoolean(key, ((Boolean) value).booleanValue());
            } else if (value instanceof Long) {
                edit.putLong(key, ((Long) value).longValue());
            } else if (value instanceof String) {
                edit.putString(key, (String) value);
            } else if (value instanceof Set) {
                edit.putStringSet(key, (Set) value);
            }
            it.remove();
        }
        edit.apply();
    }

    public static int b(String str, int i) {
        SharedPreferences a2 = a(Environment.getApplicationContext(), "themename");
        if (a2 == null) {
            return 0;
        }
        return a2.getInt(str, i);
    }

    public static String b(String str, String str2) {
        SharedPreferences a2 = a(Environment.getApplicationContext(), "themename");
        return a2 != null ? a2.getString(str, str2) : str2;
    }

    public static void c(String str, String str2) {
        SharedPreferences a2 = a(Environment.getApplicationContext(), "themename");
        if (a2 == null) {
            return;
        }
        SharedPreferences.Editor edit = a2.edit();
        edit.putString(str, str2);
        edit.apply();
    }

    public static String a(Context context) {
        if (context == null) {
            HwLog.d("SharepreferenceUtils", "getWearBtName context is null");
            return "";
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("goal_steps_perference", 0);
        return sharedPreferences != null ? sharedPreferences.getString("watch_bt_info", "") : "";
    }

    public static void c(Context context, String str) {
        if (context == null) {
            HwLog.d("SharepreferenceUtils", "setWearBtName context is null");
            return;
        }
        SharedPreferences.Editor edit = context.getSharedPreferences("goal_steps_perference", 0).edit();
        if (edit != null) {
            edit.putString("watch_bt_info", str);
            edit.commit();
        }
    }

    public static String b(Context context) {
        if (context == null) {
            HwLog.w("SharepreferenceUtils", "getWatchFaceVersion context is null");
            return "";
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("goal_steps_perference", 0);
        String string = sharedPreferences != null ? sharedPreferences.getString("watch_face_version", "") : "";
        HwLog.i("SharepreferenceUtils", "getWatchFaceVersion:" + string);
        return string;
    }

    public static void d(Context context, String str) {
        HwLog.i("SharepreferenceUtils", "setWatchFaceVersion:" + str);
        if (context == null) {
            HwLog.w("SharepreferenceUtils", "setWatchFaceVersion context is null");
            return;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("goal_steps_perference", 0);
        if (sharedPreferences == null) {
            HwLog.w("SharepreferenceUtils", "setWatchFaceVersion sp is null");
            return;
        }
        SharedPreferences.Editor edit = sharedPreferences.edit();
        if (edit != null) {
            edit.putString("watch_face_version", str);
            edit.commit();
        }
    }

    public static boolean c(Context context) {
        if (context == null) {
            HwLog.d("SharepreferenceUtils", "isSaveUpDataWatchFaceUrlData context is null");
            return false;
        }
        String format = new SimpleDateFormat("yyyyMMdd").format(new Date());
        HwLog.i("SharepreferenceUtils", "isSaveUpDataWatchFaceUrlData currentData=" + format);
        SharedPreferences sharedPreferences = context.getSharedPreferences("goal_steps_perference", 0);
        String str = "";
        if (sharedPreferences != null) {
            str = sharedPreferences.getString("watch_face_updata_data", "");
            HwLog.i("SharepreferenceUtils", "isSaveUpDataWatchFaceUrlData data=" + str);
        }
        return format.equals(str);
    }

    public static void e(Context context, String str) {
        HwLog.i("SharepreferenceUtils", "setSaveUpDataWatchFaceUrlData=" + str);
        if (context == null) {
            HwLog.d("SharepreferenceUtils", "setSaveUpDataWatchFaceUrlData context is null");
            return;
        }
        SharedPreferences.Editor edit = context.getSharedPreferences("goal_steps_perference", 0).edit();
        if (edit != null) {
            edit.putString("watch_face_updata_data", str);
            edit.commit();
        }
    }

    public static boolean f(Context context, String str) {
        if (context == null) {
            HwLog.d("SharepreferenceUtils", "judgeCurrentLanguage context is null");
            return false;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("goal_steps_perference", 0);
        String string = sharedPreferences != null ? sharedPreferences.getString("save_devices_current_language", "") : "";
        HwLog.w("SharepreferenceUtils", "Last time:" + string);
        HwLog.w("SharepreferenceUtils", "current time:" + str);
        return string.equals(str);
    }

    public static void g(Context context, String str) {
        if (context == null) {
            HwLog.d("SharepreferenceUtils", "saveCurrentLanguage context is null");
            return;
        }
        SharedPreferences.Editor edit = context.getSharedPreferences("goal_steps_perference", 0).edit();
        if (edit != null) {
            edit.putString("save_devices_current_language", str);
            edit.commit();
        }
    }

    public static String d(Context context) {
        if (context == null) {
            HwLog.d("SharepreferenceUtils", "getWatchFaceInfo context is null");
            return "";
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("goal_steps_perference", 0);
        return sharedPreferences != null ? sharedPreferences.getString("watch_face_url", "") : "";
    }

    public static void h(Context context, String str) {
        if (context == null) {
            HwLog.d("SharepreferenceUtils", "setWatchFaceInfo context is null");
            return;
        }
        SharedPreferences.Editor edit = context.getSharedPreferences("goal_steps_perference", 0).edit();
        if (edit != null) {
            edit.putString("watch_face_url", str);
            edit.apply();
        }
    }

    public static String i(Context context, String str) {
        String str2 = "";
        if (context == null) {
            HwLog.e("SharepreferenceUtils", "getWatchFaceUrl context is null.");
            return "";
        }
        if (TextUtils.isEmpty(str)) {
            HwLog.e("SharepreferenceUtils", "getWatchFaceUrl countryCode is empty.");
            return "";
        }
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences("goal_steps_perference", 0);
            if (sharedPreferences != null) {
                str2 = sharedPreferences.getString("watchFace" + str, "");
            }
            return !TextUtils.isEmpty(str2) ? ao.b(str2, "storagePw") : str2;
        } catch (Exception e) {
            HwLog.e("SharepreferenceUtils", "getWatchFaceUrl Exception:" + HwLog.printException(e));
            return "";
        }
    }

    public static void e(final Context context) {
        if (context == null) {
            HwLog.d("SharepreferenceUtils", "setWatchFaceUrl context is null");
            return;
        }
        final String commonCountryCode = HwWatchFaceApi.getInstance(context).getCommonCountryCode();
        if (TextUtils.isEmpty(commonCountryCode)) {
            HwLog.e("SharepreferenceUtils", "setWatchFaceUrl countryCode is empty.");
        } else {
            BackgroundTaskUtils.submit(new Runnable() { // from class: com.huawei.watchface.dp$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    dp.b(commonCountryCode, context);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void b(String str, Context context) {
        a(context, str, b.a().a("watchFace", str));
    }

    public static void a(Context context, String str, String str2) {
        if (context == null) {
            HwLog.d("SharepreferenceUtils", "setWatchFaceUrl context is null");
            return;
        }
        if (TextUtils.isEmpty(str)) {
            HwLog.e("SharepreferenceUtils", "setWatchFaceUrl countryCode is empty.");
            return;
        }
        try {
            SharedPreferences.Editor edit = context.getSharedPreferences("goal_steps_perference", 0).edit();
            if (edit != null) {
                edit.putString("watchFace" + str, ao.a(str2, "storagePw"));
                edit.apply();
            }
        } catch (Exception e) {
            HwLog.e("SharepreferenceUtils", "setWatchFaceUrl Exception:" + HwLog.printException(e));
        }
    }

    public static String j(Context context, String str) {
        String str2 = "";
        if (context == null) {
            HwLog.e("SharepreferenceUtils", "getWatchFaceH5Url context is null.");
            return "";
        }
        if (TextUtils.isEmpty(str)) {
            HwLog.e("SharepreferenceUtils", "getWatchFaceH5Url countryCode is empty.");
            return "";
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("goal_steps_perference", 0);
        if (sharedPreferences != null) {
            str2 = sharedPreferences.getString("watchFaceH5" + str, "");
        }
        return !TextUtils.isEmpty(str2) ? ao.b(str2, "storagePw") : str2;
    }

    public static void f(final Context context) {
        if (context == null) {
            HwLog.d("SharepreferenceUtils", "setWatchFaceH5Url context is null");
            return;
        }
        final String commonCountryCode = HwWatchFaceApi.getInstance(context).getCommonCountryCode();
        if (TextUtils.isEmpty(commonCountryCode)) {
            HwLog.e("SharepreferenceUtils", "setWatchFaceH5Url countryCode is empty.");
        } else {
            BackgroundTaskUtils.submit(new Runnable() { // from class: com.huawei.watchface.dp$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    dp.a(commonCountryCode, context);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void a(String str, Context context) {
        b(context, str, b.a().a("watchFaceH5", str));
    }

    public static void b(Context context, String str, String str2) {
        if (context == null) {
            HwLog.d("SharepreferenceUtils", "setWatchFaceH5Url context is null");
            return;
        }
        if (TextUtils.isEmpty(str)) {
            HwLog.e("SharepreferenceUtils", "setWatchFaceH5Url countryCode is empty.");
            return;
        }
        try {
            SharedPreferences.Editor edit = context.getSharedPreferences("goal_steps_perference", 0).edit();
            if (edit != null) {
                edit.putString("watchFaceH5" + str, ao.a(str2, "storagePw"));
                edit.apply();
            }
        } catch (Exception e) {
            HwLog.e("SharepreferenceUtils", "setWatchFaceH5Url Exception:" + HwLog.printException(e));
        }
    }

    public static boolean g(Context context) {
        if (context == null) {
            HwLog.d("SharepreferenceUtils", "getPersonalizedStatus context is null");
            return false;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("goal_steps_perference", 0);
        if (sharedPreferences != null) {
            return sharedPreferences.getBoolean("current_personalized_status", false);
        }
        return false;
    }

    public static void a(Context context, boolean z) {
        if (context == null) {
            HwLog.d("SharepreferenceUtils", "savePersonalizedStatus context is null");
            return;
        }
        HwLog.i("SharepreferenceUtils", "savePersonalizedStatus personalizedChange:" + z);
        SharedPreferences.Editor edit = context.getSharedPreferences("goal_steps_perference", 0).edit();
        if (edit != null) {
            edit.putBoolean("current_personalized_status", z);
            edit.apply();
        }
    }

    public static boolean h(Context context) {
        if (context == null) {
            HwLog.d("SharepreferenceUtils", "getNeedRefreshWatchFace context is null");
            return false;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("goal_steps_perference", 0);
        if (sharedPreferences != null) {
            return sharedPreferences.getBoolean("need_refresh_watch_face_date", false);
        }
        return false;
    }

    public static void b(Context context, boolean z) {
        if (context == null) {
            HwLog.d("SharepreferenceUtils", "saveNeedRefreshWatchFace context is null");
            return;
        }
        HwLog.i("SharepreferenceUtils", "saveNeedRefreshWatchFace needRefresh:" + z);
        SharedPreferences.Editor edit = context.getSharedPreferences("goal_steps_perference", 0).edit();
        if (edit != null) {
            edit.putBoolean("need_refresh_watch_face_date", z);
            edit.apply();
        }
    }

    public static boolean a(String str) {
        if (an.b(str)) {
            HwLog.i("SharepreferenceUtils", "checkDeviceIsChange deviceName is null");
            return false;
        }
        if (str.equalsIgnoreCase(b("cache_device_id", ""))) {
            return false;
        }
        HwLog.i("SharepreferenceUtils", "checkDeviceIsChange deviceName is change");
        c("cache_device_id", str);
        return true;
    }
}
