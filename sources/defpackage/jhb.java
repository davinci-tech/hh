package defpackage;

import android.text.TextUtils;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.healthdatamgr.api.HealthDataMgrApi;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.up.model.UserInfomation;
import health.compact.a.CommonUtil;
import health.compact.a.KeyValDbManager;
import health.compact.a.Services;
import health.compact.a.StorageParams;

/* loaded from: classes5.dex */
public class jhb {
    public static boolean e() {
        DeviceInfo d = jpt.d("DeviceFamilyModeUtils");
        if (d == null) {
            LogUtil.h("DeviceFamilyModeUtils", "isSupportFamilyModeHeart deviceInfo is null");
            return false;
        }
        return cwi.c(d, 49);
    }

    public static boolean c(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("DeviceFamilyModeUtils", "isSupportFamilyModeHeart deviceIdentify is empty");
            return false;
        }
        DeviceInfo b = jpt.b(str, "DeviceFamilyModeUtils");
        if (b == null) {
            LogUtil.h("DeviceFamilyModeUtils", "isSupportFamilyModeHeart target deviceInfo is null");
            return false;
        }
        return cwi.c(b, 49);
    }

    public static boolean a() {
        DeviceInfo d = jpt.d("DeviceFamilyModeUtils");
        if (d == null) {
            LogUtil.h("DeviceFamilyModeUtils", "isSupportFamilyModeUserInfo deviceInfo is null");
            return false;
        }
        return cwi.c(d, 48);
    }

    public static boolean b(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("DeviceFamilyModeUtils", "isSupportFamilyModeUserInfo deviceIdentify is empty");
            return false;
        }
        DeviceInfo b = jpt.b(str, "DeviceFamilyModeUtils");
        if (b == null) {
            LogUtil.h("DeviceFamilyModeUtils", "isSupportFamilyModeUserInfo target deviceInfo is null");
            return false;
        }
        return cwi.c(b, 48);
    }

    public static boolean d(String str) {
        return jpo.c(str) == 2;
    }

    public static void d(int i, UserInfomation userInfomation) {
        LogUtil.a("DeviceFamilyModeUtils", "Enter saveStudentInfoToStorage.");
        if (userInfomation != null) {
            LogUtil.c("DeviceFamilyModeUtils", "Enter saveStudentInfoToStorage userInformation = ", userInfomation.toString());
            if (i == 1) {
                d(userInfomation.getGender());
                return;
            }
            if (i == 2) {
                e(userInfomation.getBirthday());
                a(userInfomation.getAge());
                return;
            } else if (i == 3) {
                c(userInfomation.getHeight());
                return;
            } else if (i == 4) {
                e(userInfomation.getWeight());
                return;
            } else {
                LogUtil.h("DeviceFamilyModeUtils", "saveStudentInfoToStorage studentInfoType is unknown.");
                return;
            }
        }
        LogUtil.h("DeviceFamilyModeUtils", "saveStudentInfoToStorage userInformation is null.");
    }

    public static void a(UserInfomation userInfomation) {
        if (userInfomation == null) {
            LogUtil.h("DeviceFamilyModeUtils", "saveStudentInfoToStorage userInformation is null.");
            return;
        }
        d(userInfomation.getGender());
        e(userInfomation.getBirthday());
        c(userInfomation.getHeight());
        e(userInfomation.getWeight());
        a(userInfomation.getAge());
    }

    public static UserInfomation b() {
        LogUtil.a("DeviceFamilyModeUtils", "Enter queryStudentInfoFromStorage.");
        UserInfomation userInfomation = new UserInfomation(0);
        String d = KeyValDbManager.b(BaseApplication.getContext()).d("family_mode_student_gender_key", new StorageParams(1));
        if (!TextUtils.isEmpty(d)) {
            userInfomation.setGender(Integer.valueOf(CommonUtil.a(d, 10)));
        }
        String d2 = KeyValDbManager.b(BaseApplication.getContext()).d("family_mode_student_birthday_key", new StorageParams(1));
        if (!TextUtils.isEmpty(d2)) {
            userInfomation.setBirthday(d2);
        }
        String d3 = KeyValDbManager.b(BaseApplication.getContext()).d("family_mode_student_height_key", new StorageParams(1));
        if (!TextUtils.isEmpty(d3)) {
            userInfomation.setHeight(CommonUtil.a(d3, 10));
        }
        String d4 = KeyValDbManager.b(BaseApplication.getContext()).d("family_mode_student_weight_key", new StorageParams(1));
        if (!TextUtils.isEmpty(d4)) {
            try {
                userInfomation.setWeight(Float.parseFloat(d4));
            } catch (NumberFormatException unused) {
                LogUtil.b("DeviceFamilyModeUtils", "queryStudentInfoFromStorage NumberFormatException.");
            }
        }
        return userInfomation;
    }

    private static void d(int i) {
        KeyValDbManager.b(BaseApplication.getContext()).a("family_mode_student_gender_key", String.valueOf(i), new StorageParams(1));
    }

    private static void e(String str) {
        KeyValDbManager.b(BaseApplication.getContext()).a("family_mode_student_birthday_key", str, new StorageParams(1));
    }

    private static void c(int i) {
        KeyValDbManager.b(BaseApplication.getContext()).a("family_mode_student_height_key", String.valueOf(i), new StorageParams(1));
    }

    private static void e(float f) {
        KeyValDbManager.b(BaseApplication.getContext()).a("family_mode_student_weight_key", String.valueOf(f), new StorageParams(1));
    }

    public static void a(int i) {
        KeyValDbManager.b(BaseApplication.getContext()).a("family_mode_student_age_key", String.valueOf(i), new StorageParams(1));
    }

    public static void d() {
        ((HealthDataMgrApi) Services.a("HWHealthDataMgr", HealthDataMgrApi.class)).resetHeartZoneData();
        c();
    }

    public static void c() {
        ((HealthDataMgrApi) Services.a("HWHealthDataMgr", HealthDataMgrApi.class)).getStudentHeartRateData().setStudentHeartDataToSp();
    }
}
