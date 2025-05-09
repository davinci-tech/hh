package defpackage;

import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.SharedPreferenceManager;

/* loaded from: classes6.dex */
public class oko {
    public static void e(final boolean z) {
        LogUtil.a("HealthHeadLinesDataUtil", "saveIsShowToCloud");
        jdx.b(new Runnable() { // from class: oko.5
            @Override // java.lang.Runnable
            public void run() {
                SharedPreferenceManager.c(Integer.toString(10000), "ShowHealthHeadLinesData", String.valueOf(z));
                HiUserPreference hiUserPreference = new HiUserPreference();
                hiUserPreference.setKey("KHealthheadLines_isShowEntrance");
                hiUserPreference.setValue(String.valueOf(z));
                HiHealthManager.d(BaseApplication.e()).setUserPreference(hiUserPreference);
            }
        });
    }

    public static boolean d() {
        LogUtil.a("HealthHeadLinesDataUtil", "getIsShowFromCloud");
        HiUserPreference userPreference = HiHealthManager.d(BaseApplication.e()).getUserPreference("KHealthheadLines_isShowEntrance");
        LogUtil.a("HealthHeadLinesDataUtil", "getIsShowFromCloud ", userPreference);
        if (userPreference != null && !TextUtils.isEmpty(userPreference.getValue())) {
            LogUtil.a("HealthHeadLinesDataUtil", "getIsShowFromCloud ", userPreference.getValue());
            SharedPreferenceManager.c(Integer.toString(10000), "ShowHealthHeadLinesData", String.valueOf(!userPreference.getValue().equals("false")));
            return !userPreference.getValue().equals("false");
        }
        SharedPreferenceManager.c(Integer.toString(10000), "ShowHealthHeadLinesData", "true");
        return true;
    }

    public static void c(final boolean z) {
        LogUtil.a("HealthHeadLinesDataUtil", "saveIsLockToCloud");
        jdx.b(new Runnable() { // from class: oko.3
            @Override // java.lang.Runnable
            public void run() {
                HiUserPreference hiUserPreference = new HiUserPreference();
                hiUserPreference.setKey("KHealthheadLines_isEntranceBeResident");
                hiUserPreference.setValue(String.valueOf(z));
                HiHealthManager.d(BaseApplication.e()).setUserPreference(hiUserPreference);
            }
        });
    }
}
