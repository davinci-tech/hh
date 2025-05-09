package defpackage;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Looper;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.util.LogUtil;

/* loaded from: classes.dex */
public class sdk {

    /* renamed from: a, reason: collision with root package name */
    private static SharedPreferences f17033a;
    private static sdk b;
    private static Context c;
    private static final Object e = new Object();

    private sdk() {
    }

    public static sdk c() {
        sdk sdkVar;
        synchronized (e) {
            if (c == null) {
                c = BaseApplication.getContext();
            }
            if (b == null) {
                b = new sdk();
                f17033a = c.getSharedPreferences("sleep_shared_pref_smart_msg", 0);
            }
            sdkVar = b;
        }
        return sdkVar;
    }

    public void a(long j) {
        if (b()) {
            f17033a.edit().putLong("today_zero_clock_time", j).commit();
            LogUtil.d("SharedPreferenceUtils", "set time completed!!! time is : ", Long.valueOf(j));
        }
    }

    public long h() {
        if (b()) {
            return f17033a.getLong("today_zero_clock_time", 0L);
        }
        return 0L;
    }

    public void d(boolean z) {
        if (b()) {
            f17033a.edit().putBoolean("today_is_first_create_smart_card", z).commit();
            LogUtil.d("SharedPreferenceUtils", "set isFirst completed!!!  is : ", Boolean.valueOf(z));
        }
    }

    public boolean i() {
        if (b()) {
            return f17033a.getBoolean("today_is_first_create_smart_card", true);
        }
        return true;
    }

    public void e(boolean z) {
        if (b()) {
            f17033a.edit().putBoolean("is_first_create_questionnaire", z).commit();
            LogUtil.d("SharedPreferenceUtils", "set isFirst completed!!!  is : ", Boolean.valueOf(z));
        }
    }

    public boolean j() {
        if (b()) {
            return f17033a.getBoolean("is_first_create_questionnaire", true);
        }
        return true;
    }

    public void a(final boolean z) {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            ThreadPoolManager.d().d("setHeightIsNotSet", new Runnable() { // from class: sdk.5
                @Override // java.lang.Runnable
                public void run() {
                    sdk.this.a(z);
                }
            });
            return;
        }
        if (c == null) {
            LogUtil.c("SharedPreferenceUtils", "setHeightIsNotSet mContext is null");
            return;
        }
        HiUserPreference hiUserPreference = new HiUserPreference();
        hiUserPreference.setKey("height_is_not_set");
        hiUserPreference.setValue(String.valueOf(z));
        HiHealthManager.d(c).setUserPreference(hiUserPreference);
    }

    public boolean a() {
        Context context = c;
        if (context == null) {
            LogUtil.c("SharedPreferenceUtils", "getHeightIsNotSet mContext is null");
            return false;
        }
        HiUserPreference userPreference = HiHealthManager.d(context).getUserPreference("height_is_not_set");
        if (userPreference != null) {
            return "true".equals(userPreference.getValue());
        }
        LogUtil.c("SharedPreferenceUtils", "getHeightIsNotSet userPreference is null");
        SharedPreferences sharedPreferences = f17033a;
        if (sharedPreferences == null) {
            LogUtil.c("SharedPreferenceUtils", "getHeightIsNotSet mSharedPreferences is null");
            return false;
        }
        boolean z = sharedPreferences.getBoolean("height_is_not_set", true);
        a(z);
        return z;
    }

    public void c(boolean z) {
        if (b()) {
            f17033a.edit().putBoolean("MeasureEnd", z).commit();
            LogUtil.d("SharedPreferenceUtils", "set MeasureEnd completed!!!  is : ", Boolean.valueOf(z));
        }
    }

    public void c(String str) {
        if (c == null || str == null) {
            LogUtil.d("SharedPreferenceUtils", "mContext or resultData is null");
            return;
        }
        SharedPreferences sharedPreferences = f17033a;
        if (sharedPreferences != null) {
            sharedPreferences.edit().putString("stress_game_result_data", str).commit();
            LogUtil.d("SharedPreferenceUtils", "set STRESS_GAME_RESULT_DATA completed!!!  is : ", str);
        }
    }

    public String b(String str) {
        return !b() ? "" : f17033a.getString(str, "");
    }

    public void c(String str, String str2) {
        SharedPreferences sharedPreferences = f17033a;
        if (sharedPreferences != null) {
            sharedPreferences.edit().putString(str2, str).commit();
        } else {
            LogUtil.e("SharedPreferenceUtils", "mSharedPreferences is null");
        }
    }

    public void b(boolean z) {
        if (b()) {
            f17033a.edit().putBoolean("fasting_lite_open_notice", z).commit();
            LogUtil.d("SharedPreferenceUtils", "set fastingLiteOpenNotice completed!!!  is : ", Boolean.valueOf(z));
        }
    }

    public boolean e() {
        if (b()) {
            return f17033a.getBoolean("fasting_lite_open_notice", true);
        }
        return true;
    }

    public String d(int i) {
        if (!b()) {
            return "";
        }
        return f17033a.getString("fasting_lite_remind_last_time_" + i, "");
    }

    public void e(int i, String str) {
        if (c == null || str == null) {
            LogUtil.c("SharedPreferenceUtils", "mContext or remindLastTime is null");
            return;
        }
        SharedPreferences sharedPreferences = f17033a;
        if (sharedPreferences != null) {
            sharedPreferences.edit().putString("fasting_lite_remind_last_time_" + i, str).commit();
            LogUtil.d("SharedPreferenceUtils", "set remindLastTime is : ", str);
        }
    }

    public boolean b() {
        if (c != null && f17033a != null) {
            return true;
        }
        LogUtil.e("SharedPreferenceUtils", "mContext or mSharedPreferences is null");
        return false;
    }

    public void a(String str) {
        if (b()) {
            f17033a.edit().putString("achievement_prediction_data", str).commit();
            LogUtil.d("SharedPreferenceUtils", "set achievement prediction completed");
        }
    }

    public String d() {
        return !b() ? "" : f17033a.getString("achievement_prediction_data", "");
    }

    public static boolean a(String str, String str2, long j) {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - SharedPreferenceManager.b(str, str2, 0L) <= j) {
            return false;
        }
        SharedPreferenceManager.e(str, str2, currentTimeMillis);
        return true;
    }
}
