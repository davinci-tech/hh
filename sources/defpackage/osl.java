package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.health.fitnessadvice.api.FitnessAdviceApi;
import com.huawei.hihealth.HiSyncOption;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtils;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes6.dex */
public class osl {
    public static void a(Context context) {
        if (context == null) {
            LogUtil.b("Track_SportRemindUtils", "readDataAndRegisterReminder() context is null");
            return;
        }
        String b = SharedPreferenceManager.b(context, Integer.toString(20002), "sport_remind_reminder_time");
        int i = 0;
        int h = !TextUtils.isEmpty(b) ? CommonUtils.h(b) : 0;
        int i2 = h / 3600;
        int i3 = (h % 3600) / 60;
        String b2 = SharedPreferenceManager.b(context, Integer.toString(20002), "sport_remind_reminder_period");
        LogUtil.a("Track_SportRemindUtils", "readDataAndRegisterReminder() remindPeriodStr: ", b2, ", remindTime: ", Integer.valueOf(h));
        if (TextUtils.isEmpty(b2)) {
            LogUtil.b("Track_SportRemindUtils", "readDataAndRegisterReminder() remindPeriodStr is null or empty");
            return;
        }
        boolean[] zArr = new boolean[7];
        if (b2.length() == 1) {
            int h2 = CommonUtils.h(b2);
            for (int i4 = 0; i4 < 7; i4++) {
                if (i4 == h2) {
                    zArr[i4] = true;
                } else {
                    zArr[i4] = false;
                }
            }
        } else {
            for (String str : b2.split("_")) {
                zArr[CommonUtils.h(str) - 1] = true;
            }
        }
        while (i < 7) {
            int i5 = i + 1;
            if (zArr[i]) {
                c(i5, i2, i3);
            } else {
                riy.c(i + 1001);
            }
            i = i5;
        }
    }

    public static void c(int i, int i2, int i3) {
        Calendar calendar = Calendar.getInstance();
        int i4 = (i + 1) % 7;
        LogUtil.a("Track_SportRemindUtils", "handleSelectedPeriodDay() selectArrayDay: ", Integer.valueOf(i), ", dayOfWeek: ", Integer.valueOf(i4));
        calendar.set(7, i4);
        calendar.set(11, i2);
        calendar.set(12, i3);
        calendar.set(13, 0);
        long timeInMillis = calendar.getTimeInMillis();
        long currentTimeMillis = System.currentTimeMillis();
        LogUtil.a("Track_SportRemindUtils", "handleSelectedPeriodDay() selectTime: ", Long.valueOf(timeInMillis), " -> ", jec.e(timeInMillis), ", currentTime: ", Long.valueOf(currentTimeMillis), " -> ", jec.e(currentTimeMillis));
        if (currentTimeMillis > timeInMillis) {
            calendar.add(5, 7);
            timeInMillis = calendar.getTimeInMillis();
        }
        int i5 = i + 1000;
        LogUtil.a("Track_SportRemindUtils", "handleSelectedPeriodDay() registerRepeatingReminder remindId: ", Integer.valueOf(i5), ", selectTime: ", jec.e(timeInMillis));
        riy.b(i5, timeInMillis, 604800000L);
    }

    public static void a(String str, boolean[] zArr) {
        for (String str2 : str.split("_")) {
            zArr[CommonUtils.e(str2, 1) - 1] = true;
        }
    }

    public static void c(String str, int i, boolean[] zArr) {
        int h = CommonUtils.h(str);
        for (int i2 = 0; i2 < i; i2++) {
            boolean z = true;
            if (i2 != h - 1) {
                z = false;
            }
            zArr[i2] = z;
        }
    }

    public static void e(final Map<String, String> map) {
        LogUtil.a("Track_SportRemindUtils", "setRemindPeriod ", map);
        jdx.b(new Runnable() { // from class: osj
            @Override // java.lang.Runnable
            public final void run() {
                osl.b(map);
            }
        });
    }

    static /* synthetic */ void b(Map map) {
        HiUserPreference hiUserPreference = new HiUserPreference();
        hiUserPreference.setKey("custom.sport_remind_config");
        hiUserPreference.setValue(moj.e(map));
        boolean userPreference = HiHealthManager.d(BaseApplication.getContext()).setUserPreference(hiUserPreference, true);
        if (userPreference) {
            HiSyncOption hiSyncOption = new HiSyncOption();
            hiSyncOption.setSyncModel(2);
            hiSyncOption.setSyncAction(0);
            hiSyncOption.setSyncDataType(20000);
            hiSyncOption.setSyncScope(1);
            hiSyncOption.setSyncMethod(2);
            HiHealthNativeApi.a(BaseApplication.getContext()).synCloud(hiSyncOption, null);
        }
        ReleaseLogUtil.e("Track_SportRemindUtils", "setRemindData sync data cloud ", Boolean.valueOf(userPreference));
    }

    public static void e(final UiCallback<Map<String, String>> uiCallback) {
        LogUtil.a("Track_SportRemindUtils", "initDialogPreference enter ");
        jdx.b(new Runnable() { // from class: osi
            @Override // java.lang.Runnable
            public final void run() {
                osl.d(UiCallback.this);
            }
        });
    }

    static /* synthetic */ void d(UiCallback uiCallback) {
        HashMap hashMap = new HashMap();
        HiUserPreference userPreference = HiHealthManager.d(BaseApplication.getContext()).getUserPreference("custom.sport_remind_config");
        if (userPreference != null) {
            Map<String, String> a2 = moj.a(userPreference.getValue());
            hashMap.put("sport_remind_switch_status", a2.get("sport_remind_switch_status"));
            hashMap.put("sport_remind_period", a2.get("sport_remind_period"));
            hashMap.put("sport_remind_time", a2.get("sport_remind_time"));
            LogUtil.a("Track_SportRemindUtils", "remindData after ", hashMap.toString());
            uiCallback.onSuccess(hashMap);
            ((FitnessAdviceApi) Services.c("PluginFitnessAdvice", FitnessAdviceApi.class)).donateBySportRemind(hashMap);
            return;
        }
        uiCallback.onFailure(0, "hiUserPreference == null");
    }
}
