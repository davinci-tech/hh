package health.compact.a;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.store.SharedStoreManager;
import com.huawei.hiai.awareness.AwarenessConstants;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdataaccessmodel.sharedpreference.SharedPreferenceModel;
import com.huawei.hwdataaccessmodel.utils.StorageDataCallback;
import com.tencent.mmkv.MMKV;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
public class SharedPreferenceManager {
    private static final String d = String.valueOf(10000);

    private SharedPreferenceManager() {
    }

    public static int a(String str, String str2, int i) {
        MMKV e = MmkvUtil.e(str, null);
        e(str, e);
        return e.decodeInt(str2, i);
    }

    public static void b(String str, String str2, int i) {
        if (MmkvUtil.e(str, null).encode(str2, i)) {
            return;
        }
        health.compact.a.hwlogsmodel.ReleaseLogUtil.e("SharedPreferenceManager", "setInt the key ", str2, " in moduleId ", str, " save failed");
    }

    public static String e(String str, String str2, String str3) {
        MMKV e = MmkvUtil.e(str, null);
        e(str, e);
        return e.decodeString(str2, str3);
    }

    public static void c(String str, String str2, String str3) {
        if (MmkvUtil.e(str, null).encode(str2, str3)) {
            return;
        }
        health.compact.a.hwlogsmodel.ReleaseLogUtil.e("SharedPreferenceManager", "setString the key ", str2, " in moduleId ", str, " save failed");
    }

    public static float b(String str, String str2, float f) {
        MMKV e = MmkvUtil.e(str, null);
        e(str, e);
        return e.decodeFloat(str2, f);
    }

    public static void e(String str, String str2, float f) {
        if (MmkvUtil.e(str, null).encode(str2, f)) {
            return;
        }
        health.compact.a.hwlogsmodel.ReleaseLogUtil.e("SharedPreferenceManager", "the key ", str2, " in moduleId ", str, " save failed");
    }

    public static boolean a(String str, String str2, boolean z) {
        MMKV e = MmkvUtil.e(str, null);
        e(str, e);
        return e.decodeBool(str2, z);
    }

    public static void e(String str, String str2, boolean z) {
        if (MmkvUtil.e(str, null).encode(str2, z)) {
            return;
        }
        health.compact.a.hwlogsmodel.ReleaseLogUtil.e("SharedPreferenceManager", "the key ", str2, " in moduleId ", str, " save failed");
    }

    public static long b(String str, String str2, long j) {
        MMKV e = MmkvUtil.e(str, null);
        e(str, e);
        return e.decodeLong(str2, j);
    }

    public static void e(String str, String str2, long j) {
        if (MmkvUtil.e(str, null).encode(str2, j)) {
            return;
        }
        health.compact.a.hwlogsmodel.ReleaseLogUtil.e("SharedPreferenceManager", "the key ", str2, " in moduleId ", str, " save failed");
    }

    public static int e(Context context, String str, String str2, String str3, StorageParams storageParams) {
        SharedPreferenceModel sharedPreferenceModel;
        if (context == null) {
            com.huawei.hwlogsmodel.LogUtil.h("SharedPreferenceManager", "setSharedPreference context is null");
            return AwarenessConstants.ERROR_UNKNOWN_CODE;
        }
        String str4 = d;
        if (str4.equals(str) && !EnvironmentUtils.c()) {
            health.compact.a.hwlogsmodel.ReleaseLogUtil.d("SharedPreferenceManager", "setSharedPreference UI_ID_HOME error. key=", str2, ", processName=", EnvironmentUtils.d());
        }
        if (str4.equals(str) && "functionSetCardConfig".equals(str2) && TextUtils.isEmpty(str3)) {
            a(str2, str3);
            return AwarenessConstants.ERROR_UNKNOWN_CODE;
        }
        try {
            MMKV e = MmkvUtil.e(str, null);
            String string = e.getString(str2, "");
            if (TextUtils.isEmpty(string)) {
                sharedPreferenceModel = new SharedPreferenceModel();
                c(str3, storageParams, sharedPreferenceModel);
            } else {
                try {
                    SharedPreferenceModel sharedPreferenceModel2 = (SharedPreferenceModel) new Gson().fromJson(string, SharedPreferenceModel.class);
                    if (sharedPreferenceModel2 == null) {
                        return 201000;
                    }
                    if (storageParams == null && sharedPreferenceModel2.getEncryptType() != 0) {
                        return AwarenessConstants.ERROR_UNKNOWN_CODE;
                    }
                    if (storageParams != null && storageParams.e() != sharedPreferenceModel2.getEncryptType()) {
                        return AwarenessConstants.ERROR_UNKNOWN_CODE;
                    }
                    c(str3, sharedPreferenceModel2);
                    sharedPreferenceModel = sharedPreferenceModel2;
                } catch (JsonSyntaxException unused) {
                    com.huawei.hwlogsmodel.LogUtil.b("SharedPreferenceManager", "setSharedPreference JsonSyntaxException, ", "key = ", str2, " content = ", string);
                    e.remove(str2);
                    sharedPreferenceModel = new SharedPreferenceModel();
                    c(str3, storageParams, sharedPreferenceModel);
                }
            }
            e.putString(str2, new Gson().toJson(sharedPreferenceModel));
            return 0;
        } catch (GeneralSecurityException unused2) {
            com.huawei.hwlogsmodel.LogUtil.b("SharedPreferenceManager", "setSharedPreference GeneralSecurityException");
            return 201000;
        } catch (Exception e2) {
            com.huawei.hwlogsmodel.LogUtil.b("SharedPreferenceManager", "setSharedPreference Exception: ", ExceptionUtils.d(e2));
            return 201000;
        }
    }

    @Deprecated
    public static void e(String str, String str2, String str3, StorageParams storageParams, StorageDataCallback storageDataCallback) {
        if (BaseApplication.getContext() == null) {
            com.huawei.hwlogsmodel.LogUtil.h("SharedPreferenceManager", "setSharedPreference context is null");
            return;
        }
        try {
            MMKV e = MmkvUtil.e(str, null);
            String string = e.getString(str2, "");
            SharedPreferenceModel sharedPreferenceModel = new SharedPreferenceModel();
            if (TextUtils.isEmpty(string)) {
                c(str3, storageParams, sharedPreferenceModel);
            } else {
                try {
                    sharedPreferenceModel = (SharedPreferenceModel) new Gson().fromJson(string, SharedPreferenceModel.class);
                    d(sharedPreferenceModel, storageParams, str2, storageDataCallback, str3);
                } catch (JsonSyntaxException unused) {
                    com.huawei.hwlogsmodel.LogUtil.b("SharedPreferenceManager", "setSharedPreference JsonSyntaxException, key = ", str2, " content = ", string);
                    e.remove(str2);
                    sharedPreferenceModel = new SharedPreferenceModel();
                    c(str3, storageParams, sharedPreferenceModel);
                }
            }
            e.putString(str2, new Gson().toJson(sharedPreferenceModel));
            d(0, storageDataCallback);
        } catch (GeneralSecurityException e2) {
            d(201000, storageDataCallback);
            com.huawei.hwlogsmodel.LogUtil.b("SharedPreferenceManager", "setSharedPreference exception ", e2.toString());
        } catch (Exception unused2) {
            com.huawei.hwlogsmodel.LogUtil.b("SharedPreferenceManager", "setSharedPreference Exception");
            d(201000, storageDataCallback);
        }
    }

    private static void c(String str, StorageParams storageParams, SharedPreferenceModel sharedPreferenceModel) throws GeneralSecurityException {
        if (storageParams != null && storageParams.e() != 0) {
            sharedPreferenceModel.setValue(HwEncryptUtil.c(BaseApplication.getContext()).b(storageParams.e(), str));
            sharedPreferenceModel.setEncryptType(storageParams.e());
        } else {
            sharedPreferenceModel.setValue(str);
            sharedPreferenceModel.setEncryptType(0);
        }
    }

    private static void a(String str, String str2) {
        if (CommonUtil.as()) {
            com.huawei.hwlogsmodel.LogUtil.h("SharedPreferenceManager", "setSharedPreference UI_ID_HOME error. key=", str, " value=", str2, "StackTrace=", Arrays.toString(Thread.currentThread().getStackTrace()));
        }
        health.compact.a.hwlogsmodel.ReleaseLogUtil.d("SharedPreferenceManager", "setSharedPreference UI_ID_HOME error. key=", str, " value=", str2);
    }

    private static void c(String str, SharedPreferenceModel sharedPreferenceModel) throws GeneralSecurityException {
        if (sharedPreferenceModel.getEncryptType() == 0) {
            sharedPreferenceModel.setValue(str);
        } else {
            sharedPreferenceModel.setValue(HwEncryptUtil.c(BaseApplication.getContext()).b(sharedPreferenceModel.getEncryptType(), str));
            com.huawei.hwlogsmodel.LogUtil.a("SharedPreferenceManager", "setSharedPreference need to encrypt");
        }
    }

    private static void d(SharedPreferenceModel sharedPreferenceModel, StorageParams storageParams, String str, StorageDataCallback storageDataCallback, String str2) {
        Context context = BaseApplication.getContext();
        if (context == null) {
            com.huawei.hwlogsmodel.LogUtil.h("SharedPreferenceManager", "setSharedPreferenceImpl context is null");
            return;
        }
        if (sharedPreferenceModel == null) {
            d(201000, storageDataCallback);
            return;
        }
        if (storageParams == null && sharedPreferenceModel.getEncryptType() != 0 && !"server_token".equals(str) && !"lite_access_token".equals(str)) {
            com.huawei.hwlogsmodel.LogUtil.h("SharedPreferenceManager", "setSharedPreferenceImpl not same");
            d(AwarenessConstants.ERROR_UNKNOWN_CODE, storageDataCallback);
            return;
        }
        if (storageParams != null && storageParams.e() != sharedPreferenceModel.getEncryptType()) {
            d(AwarenessConstants.ERROR_UNKNOWN_CODE, storageDataCallback);
            return;
        }
        if (storageParams == null) {
            sharedPreferenceModel.setValue(str2);
            return;
        }
        try {
            sharedPreferenceModel.setValue(HwEncryptUtil.c(context).b(sharedPreferenceModel.getEncryptType(), str2));
        } catch (GeneralSecurityException e) {
            d(201000, storageDataCallback);
            com.huawei.hwlogsmodel.LogUtil.b("SharedPreferenceManager", "setSharedPreferenceImpl exception ", e.toString());
        }
    }

    private static void d(int i, StorageDataCallback storageDataCallback) {
        StorageResult storageResult = new StorageResult();
        storageResult.a(i);
        b(storageDataCallback, storageResult);
    }

    public static Set<String> d(Context context, String str) {
        HashSet hashSet = new HashSet();
        if (context == null) {
            com.huawei.hwlogsmodel.LogUtil.h("SharedPreferenceManager", "getAllKeysById context is null");
            return hashSet;
        }
        MMKV e = MmkvUtil.e(str, null);
        e(str, e);
        String[] allKeys = e.allKeys();
        if (allKeys == null) {
            return hashSet;
        }
        for (int i = 0; i < allKeys.length; i++) {
            if (!allKeys[i].equals("have_moved")) {
                hashSet.add(allKeys[i]);
            }
        }
        return hashSet;
    }

    public static boolean e(String str, String str2) {
        MMKV e = MmkvUtil.e(str, null);
        e(str, e);
        return e.containsKey(str2);
    }

    public static String b(Context context, String str, String str2) {
        if (context == null) {
            com.huawei.hwlogsmodel.LogUtil.h("SharedPreferenceManager", "getSharedPreference context is null");
            return null;
        }
        try {
            MMKV e = MmkvUtil.e(str, null);
            SharedPreferenceModel sharedPreferenceModel = (SharedPreferenceModel) new Gson().fromJson(e.getString(str2, ""), SharedPreferenceModel.class);
            if (sharedPreferenceModel == null) {
                e(str, e);
                sharedPreferenceModel = (SharedPreferenceModel) new Gson().fromJson(e.getString(str2, ""), SharedPreferenceModel.class);
            }
            if (sharedPreferenceModel == null) {
                return "";
            }
            if (sharedPreferenceModel.getEncryptType() == 0) {
                return sharedPreferenceModel.getValue();
            }
            return HwEncryptUtil.c(BaseApplication.getContext()).a(sharedPreferenceModel.getEncryptType(), sharedPreferenceModel.getValue());
        } catch (JsonSyntaxException e2) {
            com.huawei.hwlogsmodel.LogUtil.h("SharedPreferenceManager", "getSharedPreference, storageKey = ", str2, ", value = ", MmkvUtil.e(str, null).getString(str2, ""), ", JsonSyntaxException e = ", e2);
            health.compact.a.hwlogsmodel.ReleaseLogUtil.c("SharedPreferenceManager", "getSharedPreference parse json error, key:", str2);
            return null;
        } catch (IllegalArgumentException | GeneralSecurityException unused) {
            com.huawei.hwlogsmodel.LogUtil.b("SharedPreferenceManager", "getSharedPreference GeneralSecurityException | IllegalArgumentException");
            return null;
        } catch (Exception unused2) {
            com.huawei.hwlogsmodel.LogUtil.b("SharedPreferenceManager", "getSharedPreference Exception");
            return null;
        }
    }

    public static Map<String, String> a(Context context, String str) {
        SharedPreferenceModel sharedPreferenceModel;
        if (context == null) {
            com.huawei.hwlogsmodel.LogUtil.h("SharedPreferenceManager", "getAllSharedPreferencesById context is null");
            return null;
        }
        try {
            MMKV e = MmkvUtil.e(str, null);
            e(str, e);
            HashMap hashMap = new HashMap();
            String[] allKeys = e.allKeys();
            if (allKeys == null) {
                return hashMap;
            }
            for (String str2 : allKeys) {
                if (!str2.equals("have_moved") && (sharedPreferenceModel = (SharedPreferenceModel) new Gson().fromJson(e.getString(str2, ""), SharedPreferenceModel.class)) != null) {
                    if (sharedPreferenceModel.getEncryptType() == 0) {
                        hashMap.put(str2, sharedPreferenceModel.getValue());
                    } else {
                        com.huawei.hwlogsmodel.LogUtil.a("SharedPreferenceManager", "getAllSharedPreferencesById need to encrypt");
                        hashMap.put(str2, HwEncryptUtil.c(BaseApplication.getContext()).a(sharedPreferenceModel.getEncryptType(), sharedPreferenceModel.getValue()));
                    }
                }
            }
            return hashMap;
        } catch (Exception unused) {
            com.huawei.hwlogsmodel.LogUtil.b("SharedPreferenceManager", "getAllSharedPreferencesById exception");
            return null;
        }
    }

    private static void e(String str, MMKV mmkv) {
        if (mmkv.getString("have_moved", "").equals("done")) {
            return;
        }
        mmkv.putString("have_moved", "done");
        if (SharedStoreManager.e(str)) {
            com.huawei.hwlogsmodel.LogUtil.c("SharedPreferenceManager", "Enter moveDataToMMKV, moduleId = ", str);
            Map<String, ?> all = BaseApplication.getContext().getSharedPreferences(str, 0).getAll();
            if (all != null && all.size() > 0) {
                for (Map.Entry<String, ?> entry : all.entrySet()) {
                    String key = entry.getKey();
                    Object value = entry.getValue();
                    if (key != null && value != null && !mmkv.contains(key)) {
                        if (value instanceof String) {
                            mmkv.encode(key, (String) value);
                        } else if (value instanceof Boolean) {
                            mmkv.encode(key, ((Boolean) value).booleanValue());
                        } else if (value instanceof Long) {
                            mmkv.encode(key, ((Long) value).longValue());
                        } else if (value instanceof Integer) {
                            mmkv.encode(key, ((Integer) value).intValue());
                        } else if (value instanceof Float) {
                            mmkv.encode(key, ((Float) value).floatValue());
                        } else if (!(value instanceof Set)) {
                            com.huawei.hwlogsmodel.LogUtil.h("SharedPreferenceManager", "Unknown Type, key = ", key, " value = ", value.toString());
                        } else {
                            mmkv.encode(key, (Set<String>) value);
                        }
                    }
                }
                com.huawei.hwlogsmodel.LogUtil.a("SharedPreferenceManager", "After traverse data");
            }
            com.huawei.hwlogsmodel.LogUtil.c("SharedPreferenceManager", "Leave moveDataToMMKV, moduleId = ", str);
        }
    }

    public static int c(Context context, String str, String str2) {
        if (context == null) {
            com.huawei.hwlogsmodel.LogUtil.h("SharedPreferenceManager", "deleteSharedPreference context is null");
            return AwarenessConstants.ERROR_UNKNOWN_CODE;
        }
        MMKV e = MmkvUtil.e(str, null);
        e(str, e);
        e.putString(str2, "");
        return 0;
    }

    public static int j(Context context, String str) {
        if (context == null) {
            com.huawei.hwlogsmodel.LogUtil.h("SharedPreferenceManager", "resetSharedPreference context is null");
            return AwarenessConstants.ERROR_UNKNOWN_CODE;
        }
        context.getSharedPreferences(str, 0).edit().clear().commit();
        MmkvUtil.e(str, null).clear();
        return 0;
    }

    public static boolean d(String str, String str2) {
        MMKV e = MmkvUtil.e(str, null);
        e(str, e);
        e.remove(str2);
        boolean containsKey = e.containsKey(str2);
        if (containsKey) {
            health.compact.a.hwlogsmodel.ReleaseLogUtil.e("SharedPreferenceManager", "deleteKey this key ", str2, "failed");
        }
        return !containsKey;
    }

    public static void e(String str, String[] strArr) {
        MMKV e = MmkvUtil.e(str, null);
        e(str, e);
        e.removeValuesForKeys(strArr);
    }

    private static void b(StorageDataCallback storageDataCallback, StorageResult storageResult) {
        if (storageDataCallback != null) {
            storageDataCallback.onProcessed(storageResult);
        }
    }

    public static String i(Context context) {
        if (context == null) {
            com.huawei.hwlogsmodel.LogUtil.h("SharedPreferenceManager", "getWearBtName context is null");
            return "";
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("goal_steps_perference", 0);
        return sharedPreferences != null ? sharedPreferences.getString("watch_bt_info", "") : "";
    }

    public static String j(Context context) {
        if (context == null) {
            com.huawei.hwlogsmodel.LogUtil.h("SharedPreferenceManager", "getWearFaceUrl context is null");
            return "";
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("goal_steps_perference", 0);
        String string = sharedPreferences != null ? sharedPreferences.getString("watch_face_url", "") : "";
        com.huawei.hwlogsmodel.LogUtil.a("SharedPreferenceManager", " getWearFaceUrl=", string);
        return string;
    }

    public static boolean f(Context context) {
        if (context == null) {
            com.huawei.hwlogsmodel.LogUtil.h("SharedPreferenceManager", "isSaveUpDataWatchFaceUrlData context is null");
            return false;
        }
        String format = new SimpleDateFormat("yyyyMMdd").format(new Date());
        com.huawei.hwlogsmodel.LogUtil.a("SharedPreferenceManager", "isSaveUpDataWatchFaceUrlData currentData=", format);
        SharedPreferences sharedPreferences = context.getSharedPreferences("goal_steps_perference", 0);
        String str = "";
        if (sharedPreferences != null) {
            str = sharedPreferences.getString("watch_face_updata_data", "");
            com.huawei.hwlogsmodel.LogUtil.a("SharedPreferenceManager", "isSaveUpDataWatchFaceUrlData date=", str);
        }
        return format.equals(str);
    }

    public static void d(Context context, long j, String str) {
        if (context == null) {
            com.huawei.hwlogsmodel.LogUtil.h("SharedPreferenceManager", "setUploadLogTime context is null");
            return;
        }
        SharedPreferences.Editor edit = context.getSharedPreferences("goal_steps_perference", 0).edit();
        if (edit != null) {
            edit.putLong(str, j);
            edit.commit();
        }
    }

    public static long c(Context context, String str) {
        if (context == null) {
            com.huawei.hwlogsmodel.LogUtil.h("SharedPreferenceManager", "getUploadLogTime context is null");
            return 0L;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("goal_steps_perference", 0);
        if (sharedPreferences != null) {
            return sharedPreferences.getLong(str, 0L);
        }
        return 0L;
    }

    public static void h(Context context, String str) {
        SharedPreferences.Editor edit;
        if (context == null) {
            com.huawei.hwlogsmodel.LogUtil.h("SharedPreferenceManager", "setQuestionNumber context is null");
            return;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("goal_steps_perference", 0);
        if (sharedPreferences == null || (edit = sharedPreferences.edit()) == null) {
            return;
        }
        edit.putString("BETA_CLUB_QUESTION_NUMBER", str);
        edit.commit();
    }

    public static String c(Context context) {
        if (context == null) {
            com.huawei.hwlogsmodel.LogUtil.h("SharedPreferenceManager", "getQuestionNumber context is null");
            return "";
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("goal_steps_perference", 0);
        return sharedPreferences != null ? sharedPreferences.getString("BETA_CLUB_QUESTION_NUMBER", "") : "";
    }

    public static void d(Context context, String str, String str2) {
        if (context == null) {
            com.huawei.hwlogsmodel.LogUtil.h("SharedPreferenceManager", "setUploadResult context is null");
            return;
        }
        SharedPreferences.Editor edit = context.getSharedPreferences("goal_steps_perference", 0).edit();
        if (edit != null) {
            edit.putString(str2, str);
            edit.commit();
        }
    }

    public static String b(Context context, String str) {
        if (context == null) {
            com.huawei.hwlogsmodel.LogUtil.h("SharedPreferenceManager", "getUploadResult context is null");
            return "";
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("goal_steps_perference", 0);
        return sharedPreferences != null ? sharedPreferences.getString(str, "") : "";
    }

    public static void b(Context context, int i) {
        if (context == null) {
            com.huawei.hwlogsmodel.LogUtil.h("SharedPreferenceManager", "setUploadSize context is null");
            return;
        }
        SharedPreferences.Editor edit = context.getSharedPreferences("goal_steps_perference", 0).edit();
        if (edit != null) {
            edit.putInt("update_log_size", i);
            edit.commit();
        }
    }

    public static int e(Context context) {
        if (context == null) {
            com.huawei.hwlogsmodel.LogUtil.h("SharedPreferenceManager", "getUploadSize context is null");
            return 0;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("goal_steps_perference", 0);
        if (sharedPreferences != null) {
            return sharedPreferences.getInt("update_log_size", 0);
        }
        return 0;
    }

    public static boolean g(Context context, String str) {
        if (context == null) {
            com.huawei.hwlogsmodel.LogUtil.h("SharedPreferenceManager", "judgeCurrentLanguage context is null");
            return false;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("goal_steps_perference", 0);
        String string = sharedPreferences != null ? sharedPreferences.getString("save_devices_current_language", "") : "";
        com.huawei.hwlogsmodel.LogUtil.a("SharedPreferenceManager", "judgeCurrentLanguage Last language:", string, "current language:", str);
        return string.equals(str);
    }

    public static void b(Context context, boolean z) {
        if (context == null) {
            com.huawei.hwlogsmodel.LogUtil.h("SharedPreferenceManager", "setWatchFacePrivacyIsAgree context is null");
            return;
        }
        SharedPreferences.Editor edit = context.getSharedPreferences("goal_steps_perference", 0).edit();
        if (edit != null) {
            edit.putBoolean("watch_face_privacy_is_agree", z);
            edit.commit();
        }
    }

    public static void a(Context context, boolean z) {
        if (context == null) {
            com.huawei.hwlogsmodel.LogUtil.h("SharedPreferenceManager", "setCurrentUserIsAppMarketState context is null");
            return;
        }
        SharedPreferences.Editor edit = context.getSharedPreferences("APP_MARKET", 0).edit();
        if (edit != null) {
            edit.putBoolean("SHOW_APP_MARKET_SERVICE_AGREE", z);
            edit.commit();
        }
    }

    public static boolean b(Context context) {
        if (context == null) {
            com.huawei.hwlogsmodel.LogUtil.h("SharedPreferenceManager", "getAppMarketPrivacyIsAgree context is null");
            return false;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("APP_MARKET", 0);
        boolean z = sharedPreferences != null ? sharedPreferences.getBoolean("SHOW_APP_MARKET_SERVICE_AGREE", false) : false;
        com.huawei.hwlogsmodel.LogUtil.a("SharedPreferenceManager", "getAppMarketPrivacyIsAgree :", Boolean.valueOf(z));
        return z;
    }

    public static boolean e(Context context, String str) {
        if (context == null) {
            com.huawei.hwlogsmodel.LogUtil.h("SharedPreferenceManager", "getPrivacyIsAgree context is null");
            return false;
        }
        if (TextUtils.isEmpty(str)) {
            str = "HONOR_NOT_CLOUD_USE_ID_KEY";
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("goal_steps_perference", 0);
        boolean z = sharedPreferences != null ? sharedPreferences.getBoolean(str, false) : false;
        com.huawei.hwlogsmodel.LogUtil.a("SharedPreferenceManager", " getPrivacyIsAgree = ", Boolean.valueOf(z));
        return z;
    }

    public static void a(Context context, String str, boolean z) {
        if (context == null) {
            com.huawei.hwlogsmodel.LogUtil.h("SharedPreferenceManager", "setPrivacyIsAgree context is null");
            return;
        }
        if (TextUtils.isEmpty(str)) {
            str = "HONOR_NOT_CLOUD_USE_ID_KEY";
        }
        SharedPreferences.Editor edit = context.getSharedPreferences("goal_steps_perference", 0).edit();
        if (edit != null) {
            edit.putBoolean(str, z);
            edit.commit();
        }
    }

    public static int a(Context context) {
        if (context == null) {
            com.huawei.hwlogsmodel.LogUtil.h("SharedPreferenceManager", "getHonorDeviceCount context is null");
            return 0;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("goal_steps_perference", 0);
        int i = sharedPreferences != null ? sharedPreferences.getInt("HONOR_WEAR_DEVICE_COUNT", 0) : 0;
        com.huawei.hwlogsmodel.LogUtil.a("SharedPreferenceManager", " getHonorDeviceCount = ", Integer.valueOf(i));
        return i;
    }

    public static void e(Context context, int i) {
        if (context == null) {
            com.huawei.hwlogsmodel.LogUtil.h("SharedPreferenceManager", "setHonorDeviceCount context is null");
            return;
        }
        SharedPreferences.Editor edit = context.getSharedPreferences("goal_steps_perference", 0).edit();
        if (edit != null) {
            edit.putInt("HONOR_WEAR_DEVICE_COUNT", i);
            edit.commit();
        }
    }

    public static void c(Context context, boolean z) {
        if (context == null) {
            com.huawei.hwlogsmodel.LogUtil.h("SharedPreferenceManager", "setHasSleepData context is null");
            return;
        }
        SharedPreferences.Editor edit = context.getSharedPreferences("goal_steps_perference", 0).edit();
        if (edit != null) {
            edit.putBoolean("HAS_SLEEP_DATA", z);
            edit.apply();
        }
    }

    public static boolean d(Context context) {
        if (context == null) {
            com.huawei.hwlogsmodel.LogUtil.h("SharedPreferenceManager", "getHasSleepData context is null");
            return false;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("goal_steps_perference", 0);
        if (sharedPreferences != null) {
            return sharedPreferences.getBoolean("HAS_SLEEP_DATA", false);
        }
        return false;
    }

    public static boolean f(Context context, String str) {
        if (context == null) {
            com.huawei.hwlogsmodel.LogUtil.h("SharedPreferenceManager", "isThirdKitAuth context is null");
            return true;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("THIRD_KIT_AUTH", 4);
        if (sharedPreferences != null) {
            return sharedPreferences.getBoolean(str, true);
        }
        com.huawei.hwlogsmodel.LogUtil.h("SharedPreferenceManager", "isThirdKitAuth preferences is null");
        return true;
    }

    public static boolean i(Context context, String str) {
        if (context == null) {
            com.huawei.hwlogsmodel.LogUtil.h("SharedPreferenceManager", "isUseNewPkgNameInBetaEnviroment context is null");
            return false;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("USE_IN_BETA_ENVIROMENT", 0);
        if (sharedPreferences != null) {
            return sharedPreferences.getBoolean(str, false);
        }
        return false;
    }

    @Deprecated
    public static void d(String str, String str2, boolean z) {
        SharedPreferences.Editor edit = BaseApplication.getContext().getSharedPreferences(str, 0).edit();
        if (edit != null) {
            edit.putBoolean(str2, z);
            edit.commit();
        }
    }

    @Deprecated
    public static boolean b(String str, String str2) {
        SharedPreferences sharedPreferences = BaseApplication.getContext().getSharedPreferences(str, 0);
        boolean z = sharedPreferences != null ? sharedPreferences.getBoolean(str2, false) : false;
        com.huawei.hwlogsmodel.LogUtil.a("SharedPreferenceManager", " getBooleanSharedPreference moduleId = ", str, Boolean.valueOf(z));
        return z;
    }

    public static String e(String str) {
        SharedPreferences sharedPreferences = BaseApplication.getContext().getSharedPreferences("my_watch_face", 0);
        if (sharedPreferences == null) {
            return "";
        }
        String string = sharedPreferences.getString(str, "");
        com.huawei.hwlogsmodel.LogUtil.a("SharedPreferenceManager", "getMyWatchFaceJson, storageKey: ", str, " json: ", string);
        return string;
    }

    public static void c(String str, String str2) {
        SharedPreferences.Editor edit = BaseApplication.getContext().getSharedPreferences("my_watch_face", 0).edit();
        if (edit != null) {
            edit.putString(str, str2);
            edit.apply();
        }
    }
}
