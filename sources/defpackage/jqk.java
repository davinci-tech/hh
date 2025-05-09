package defpackage;

import android.text.TextUtils;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* loaded from: classes5.dex */
class jqk {
    private static ReentrantReadWriteLock c = new ReentrantReadWriteLock();
    private static ExecutorService d = Executors.newCachedThreadPool();

    jqk() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String j(String str) {
        LogUtil.a("SwtchCldStrgUt", "getUserPreference enter");
        HiUserPreference a2 = a(str);
        if (a2 != null) {
            LogUtil.a("SwtchCldStrgUt", "getUserPreference userPreference is not null end");
            return a2.getValue();
        }
        LogUtil.a("SwtchCldStrgUt", "getUserPreference end");
        return null;
    }

    public static HiUserPreference a(String str) {
        for (int i = 0; i < 3; i++) {
            HiUserPreference userPreference = HiHealthManager.d(BaseApplication.getContext()).getUserPreference(str);
            if (userPreference != null) {
                return userPreference;
            }
            String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
            if (!TextUtils.isEmpty(accountInfo) && HiHealthManager.d(BaseApplication.getContext()).checkHiHealthLogin(accountInfo)) {
                LogUtil.a("SwtchCldStrgUt", "getHiUserPreference isHiHealthLogin is true");
                return HiHealthManager.d(BaseApplication.getContext()).getUserPreference(str);
            }
            try {
                Thread.sleep(300L);
            } catch (InterruptedException unused) {
                ReleaseLogUtil.c("DEVMGR_SwtchCldStrgUt", "getHiUserPreference InterruptedException");
            }
        }
        LogUtil.h("SwtchCldStrgUt", "getHiUserPreference HiUserPreference is null");
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static List<HiUserPreference> d(List<String> list) {
        List<HiUserPreference> list2 = null;
        if (list == null) {
            return null;
        }
        for (int i = 0; i < 3; i++) {
            list2 = HiHealthManager.d(BaseApplication.getContext()).getUserPreferenceList(list);
            if (list2 != null) {
                return list2;
            }
            String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
            if (!TextUtils.isEmpty(accountInfo) && HiHealthManager.d(BaseApplication.getContext()).checkHiHealthLogin(accountInfo)) {
                LogUtil.a("SwtchCldStrgUt", "getHiUserPreferenceList isHiHealthLogin is true");
                return HiHealthManager.d(BaseApplication.getContext()).getUserPreferenceList(list);
            }
            try {
                Thread.sleep(300L);
            } catch (InterruptedException unused) {
                ReleaseLogUtil.c("DEVMGR_SwtchCldStrgUt", "getHiUserPreferenceList InterruptedException");
            }
        }
        LogUtil.h("SwtchCldStrgUt", "getHiUserPreferenceList HiUserPreference is null");
        return list2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String h(String str) {
        String str2;
        ReleaseLogUtil.e("DEVMGR_SwtchCldStrgUt", "getSwitchCommonSetting switchKey = ", str);
        String j = j("custom.wear_common_setting");
        if (j != null) {
            HashMap<String, String> i = i(j);
            boolean containsKey = i != null ? i.containsKey(str) : false;
            ReleaseLogUtil.e("DEVMGR_SwtchCldStrgUt", "getSwitchCommonSetting isContainKeywords = ", Boolean.valueOf(containsKey));
            if (containsKey) {
                str2 = i.get(str).toString();
                ReleaseLogUtil.e("DEVMGR_SwtchCldStrgUt", "getSwitchCommonSetting result = ", str2);
                return str2;
            }
        }
        str2 = null;
        ReleaseLogUtil.e("DEVMGR_SwtchCldStrgUt", "getSwitchCommonSetting result = ", str2);
        return str2;
    }

    public static HashMap<String, String> i(String str) {
        ReleaseLogUtil.e("DEVMGR_SwtchCldStrgUt", "hashStringToHashMap inputString = ", str);
        if (str == null || str.length() < 3) {
            LogUtil.h("SwtchCldStrgUt", "hashStringToHashMap inputString is illegal");
            return null;
        }
        String[] split = str.substring(1, str.length() - 1).split(",");
        LogUtil.a("SwtchCldStrgUt", "hashStringToHashMap splitString = ", Arrays.toString(split));
        HashMap<String, String> hashMap = new HashMap<>(16);
        for (String str2 : split) {
            String[] split2 = str2.split("=");
            if (split2.length >= 2) {
                String trim = split2[0].trim();
                LogUtil.a("SwtchCldStrgUt", "hashStringToHashMap key = ", trim);
                boolean z = "bt_lost_remind".equals(trim) || "auto_light_screen".equals(trim) || "rotate_switch_screen".equals(trim) || "weather_switch_status".equals(trim);
                boolean z2 = "core_sleep_button".equals(trim) || "wlan_auto_update".equals(trim) || "left_or_right_hand_wear_status".equals(trim) || "heart_rate_button".equals(trim);
                boolean z3 = "press_auto_monitor_switch_status".equals(trim) || "left_or_right_foot_wear_status".equals(trim) || "continue_heart_rate".equals(trim) || "gps_files_switch_screen".equals(trim);
                boolean z4 = "weather_switch_unit_status".equals(trim) || "heart_rate_mode".equals(trim) || "watch_face_privacy_service_status".equals(trim) || "app_market_privacy_service_status".equals(trim);
                if (z || z2 || z3 || z4) {
                    String str3 = split2[1];
                    LogUtil.a("SwtchCldStrgUt", "hashStringToHashMap value = ", str3);
                    hashMap.put(trim, str3);
                }
            }
        }
        LogUtil.a("SwtchCldStrgUt", "hashStringToHashMap map = ", hashMap.toString());
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static HashMap<String, String> g(String str) {
        if (str == null || str.length() < 3) {
            ReleaseLogUtil.d("DEVMGR_SwtchCldStrgUt", "hashStringToHashMapIntelligent inputString is illegal");
            return null;
        }
        String[] split = str.substring(1, str.length() - 1).split(",");
        ReleaseLogUtil.e("DEVMGR_SwtchCldStrgUt", "hashStringToHashMapIntelligent splitString = ", Arrays.toString(split));
        HashMap<String, String> hashMap = new HashMap<>(16);
        for (String str2 : split) {
            String[] split2 = str2.split("=");
            if (split2.length >= 2) {
                hashMap.put(split2[0].trim(), split2[1]);
            }
        }
        ReleaseLogUtil.e("DEVMGR_SwtchCldStrgUt", "hashStringToHashMapIntelligent map = ", hashMap.toString());
        return hashMap;
    }

    public static void b(final String str, final String str2, final IBaseResponseCallback iBaseResponseCallback) {
        ReleaseLogUtil.e("DEVMGR_SwtchCldStrgUt", "setSwitchSetting switchKey = ", str);
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("SwtchCldStrgUt", "setSwitchSetting switchKey is null or empty");
        } else {
            d.execute(new Runnable() { // from class: jqk.3
                @Override // java.lang.Runnable
                public void run() {
                    jqk.c.writeLock().lock();
                    boolean userPreference = HiHealthManager.d(BaseApplication.getContext()).setUserPreference(new HiUserPreference(str, str2), true);
                    jqk.c.writeLock().unlock();
                    ReleaseLogUtil.e("DEVMGR_SwtchCldStrgUt", "setSwitchSetting isSuccess = ", Boolean.valueOf(userPreference));
                    IBaseResponseCallback iBaseResponseCallback2 = iBaseResponseCallback;
                    if (iBaseResponseCallback2 != null) {
                        if (userPreference) {
                            iBaseResponseCallback2.d(0, null);
                        } else {
                            iBaseResponseCallback2.d(-1, null);
                        }
                    }
                }
            });
        }
    }

    public static void e(final String str, final String str2, String str3, final IBaseResponseCallback iBaseResponseCallback) {
        ReleaseLogUtil.e("DEVMGR_SwtchCldStrgUt", "differentiating devices, setSwitchSetting switchKey = ", str);
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("SwtchCldStrgUt", "differentiating devices, setSwitchSetting switchKey is null or empty");
        } else if (TextUtils.isEmpty(str3)) {
            ReleaseLogUtil.d("DEVMGR_SwtchCldStrgUt", "differentiating devices, setSwitchSetting identify is null or empty");
        } else {
            final String a2 = knl.a(str3);
            d.execute(new Runnable() { // from class: jqk.5
                @Override // java.lang.Runnable
                public void run() {
                    HashMap g;
                    String j = jqk.j(str);
                    if (!jqk.f(j)) {
                        g = jqk.g(j);
                        if (g == null) {
                            g = new HashMap(16);
                        }
                        g.put(a2, str2);
                    } else {
                        g = new HashMap(16);
                        g.put(a2, str2);
                    }
                    boolean userPreference = HiHealthManager.d(BaseApplication.getContext()).setUserPreference(new HiUserPreference(str, g.toString()), false);
                    ReleaseLogUtil.e("DEVMGR_SwtchCldStrgUt", "differentiating devices, setSwitchSetting isSuccess = ", Boolean.valueOf(userPreference));
                    IBaseResponseCallback iBaseResponseCallback2 = iBaseResponseCallback;
                    if (iBaseResponseCallback2 != null) {
                        if (userPreference) {
                            iBaseResponseCallback2.d(0, null);
                        } else {
                            iBaseResponseCallback2.d(-1, null);
                        }
                    }
                }
            });
        }
    }

    public static void c(final List<String> list, final IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("SwtchCldStrgUt", "getSwitchSetting enter");
        if (list == null || iBaseResponseCallback == null) {
            LogUtil.h("SwtchCldStrgUt", "param error");
        } else {
            d.execute(new Runnable() { // from class: jqk.2
                @Override // java.lang.Runnable
                public void run() {
                    jqk.c.readLock().lock();
                    List d2 = jqk.d((List<String>) list);
                    jqk.c.readLock().unlock();
                    if (d2 == null) {
                        iBaseResponseCallback.d(-1, null);
                    } else {
                        LogUtil.a("SwtchCldStrgUt", "getSwitchSetting size is:", Integer.valueOf(d2.size()));
                        iBaseResponseCallback.d(0, d2);
                    }
                }
            });
        }
    }

    public static void a(final String str, final IBaseResponseCallback iBaseResponseCallback) {
        ReleaseLogUtil.e("DEVMGR_SwtchCldStrgUt", "getSwitchSetting switchKey = ", str);
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("SwtchCldStrgUt", "getSwitchSetting switchKey is null or empty");
        } else if (iBaseResponseCallback == null) {
            ReleaseLogUtil.d("DEVMGR_SwtchCldStrgUt", "getSwitchSetting callback is null");
        } else {
            d.execute(new Runnable() { // from class: jqk.1
                @Override // java.lang.Runnable
                public void run() {
                    String value;
                    jqk.c.readLock().lock();
                    HiUserPreference a2 = jqk.a(str);
                    if (a2 == null) {
                        ReleaseLogUtil.e("DEVMGR_SwtchCldStrgUt", "getSwitchSetting userPreference is null");
                        value = jqk.h(str);
                    } else {
                        value = a2.getValue();
                    }
                    jqk.c.readLock().unlock();
                    ReleaseLogUtil.e("DEVMGR_SwtchCldStrgUt", "getSwitchSetting switchSetting = ", value);
                    if (TextUtils.isEmpty(value)) {
                        iBaseResponseCallback.d(-1, null);
                    } else {
                        iBaseResponseCallback.d(0, value);
                    }
                }
            });
        }
    }

    public static void d(final String str, final String str2, final IBaseResponseCallback iBaseResponseCallback) {
        ReleaseLogUtil.e("DEVMGR_SwtchCldStrgUt", "differentiating devices, getSwitchSetting switchKey = ", str);
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("SwtchCldStrgUt", "differentiating devices, getSwitchSetting switchKey is null or empty");
            return;
        }
        if (TextUtils.isEmpty(str2)) {
            ReleaseLogUtil.d("DEVMGR_SwtchCldStrgUt", "differentiating devices, getSwitchSetting identify is null or empty");
        } else if (iBaseResponseCallback == null) {
            ReleaseLogUtil.d("DEVMGR_SwtchCldStrgUt", "differentiating devices, getSwitchSetting callback is null");
        } else {
            final String a2 = knl.a(str2);
            d.execute(new Runnable() { // from class: jqk.4
                @Override // java.lang.Runnable
                public void run() {
                    String j = jqk.j(str);
                    if (jqk.f(j)) {
                        LogUtil.a("SwtchCldStrgUt", "differentiating devices, getSwitchSetting userPreference = ", j);
                        iBaseResponseCallback.d(0, j);
                        return;
                    }
                    if (j != null) {
                        HashMap g = jqk.g(j);
                        if (g == null) {
                            ReleaseLogUtil.d("DEVMGR_SwtchCldStrgUt", "differentiating devices, commonSettingDataList is null");
                            iBaseResponseCallback.d(-1, null);
                            return;
                        } else if (g.containsKey(a2)) {
                            String obj = g.get(a2).toString();
                            ReleaseLogUtil.e("DEVMGR_SwtchCldStrgUt", "getSwitchSetting identifySha256 switchSetting = ", obj);
                            iBaseResponseCallback.d(0, obj);
                            return;
                        } else if (g.containsKey(str2)) {
                            String obj2 = g.get(str2).toString();
                            ReleaseLogUtil.e("DEVMGR_SwtchCldStrgUt", "getSwitchSetting identify switchSetting = ", obj2);
                            iBaseResponseCallback.d(0, obj2);
                            return;
                        }
                    } else {
                        LogUtil.h("SwtchCldStrgUt", "differentiating devices, getSwitchSetting on HiHealth is null");
                    }
                    iBaseResponseCallback.d(-1, null);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean f(String str) {
        return "true".equals(str) || "false".equals(str);
    }
}
