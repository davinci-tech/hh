package com.huawei.threecircle;

import android.content.Context;
import com.huawei.caloriecalculate.ActivityCaloriesCalculateApi;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.utils.OperationUtilsApi;
import com.huawei.ui.commonui.R$drawable;
import com.huawei.ui.commonui.R$string;
import defpackage.bdw;
import defpackage.nsf;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import java.security.SecureRandom;
import java.util.HashMap;

/* loaded from: classes6.dex */
public class ActiveTipStringUtils {
    private static final int[] c = {R$string.IDS_hw_health_active_record_tips_one_one, R$string.IDS_hw_health_active_record_tips_one_two, R$string.IDS_hw_health_active_record_tips_one_three};
    private static final int[] d = {R$string.IDS_hw_health_active_record_tips_three_one, R$string.IDS_hw_health_active_record_tips_three_two, R$string.IDS_hw_health_active_record_tips_three_three, R$string.IDS_hw_health_active_record_tips_three_four};
    private static final int[] b = {R$string.IDS_hw_health_active_record_tips_four_one, R$string.IDS_hw_health_active_record_tips_four_two, R$string.IDS_hw_health_active_record_tips_four_three};
    private static final int[] g = {R$string.IDS_hw_health_active_record_tips_five_calories_one, R$string.IDS_hw_health_active_record_tips_five_calories_two};
    private static final int[] f = {R$string.IDS_hw_health_active_record_tips_title_one, R$string.IDS_hw_health_active_record_tips_title_two, R$string.IDS_hw_health_active_record_tips_title_three, R$string.IDS_hw_health_active_record_tips_title_four};
    private static final int[] e = {R$string.IDS_active_tip_workout_1, R$string.IDS_active_tip_workout_2, R$string.IDS_active_tip_workout_3, R$string.IDS_active_tip_workout_4, R$string.IDS_active_tip_workout_5};

    /* renamed from: a, reason: collision with root package name */
    private static final int[] f8728a = {R$string.IDS_active_tip_heat_1, R$string.IDS_active_tip_heat_2, R$string.IDS_active_tip_heat_3, R$string.IDS_active_tip_heat_4};

    public enum TipType {
        STAND_HOUR,
        WORKOUT,
        CALORIES,
        TASK_SUBSCRIBE,
        TASK_BONUS_GET,
        TASK_BONUS_USE,
        TASK_STAND_HOUR,
        TASK_WORKOUT,
        TASK_CALORIES
    }

    private static int b(int i) {
        return i >= 3 ? 257 : 258;
    }

    public static HashMap<String, String> e(int i, TipType tipType) {
        LogUtil.a("SCUI_ActiveTipStringUtils", "get tip context with value: ", Integer.valueOf(i), " type: ", tipType);
        HashMap<String, String> hashMap = new HashMap<>(3);
        hashMap.put("title_key", c());
        int i2 = AnonymousClass2.e[tipType.ordinal()];
        if (i2 == 1) {
            hashMap.put("description_key", nsf.b(R$string.IDS_active_tip_hour, nsf.a(R.plurals.IDS_single_circle_active_target_desc, i, UnitUtil.e(i, 1, 0))));
            hashMap.put("button_key", d(0));
        } else if (i2 == 2) {
            a(hashMap, i);
        } else if (i2 == 3) {
            d(hashMap, i);
        }
        return hashMap;
    }

    public static HashMap<String, Object> c(int i, TipType tipType, int i2, int i3, int i4, int i5) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("icon_key", Integer.valueOf(d(tipType)));
        String b2 = b(R.plurals._2130903396_res_0x7f030164, i3);
        String b3 = b(R.plurals._2130903399_res_0x7f030167, i2);
        switch (tipType) {
            case TASK_BONUS_GET:
                hashMap.put("title_key", nsf.h(R$string.IDS_active_task_tip_get_title));
                hashMap.put("description_key", nsf.b(R$string.IDS_active_task_tip_get_text, b2));
                hashMap.put("button_key", nsf.h(R$string.IDS_active_task_tip_get_button));
                return hashMap;
            case TASK_BONUS_USE:
                hashMap.put("title_key", nsf.h(R$string.IDS_active_task_tip_use_title));
                hashMap.put("description_key", nsf.b(R$string.IDS_active_task_tip_use_text, b2));
                hashMap.put("button_key", nsf.h(R$string.IDS_active_task_tip_use_button));
                return hashMap;
            case TASK_SUBSCRIBE:
                String b4 = b(R.plurals.IDS_user_profile_achieve_num_day, i5);
                String b5 = b(R.plurals._2130903396_res_0x7f030164, i4);
                hashMap.put("title_key", nsf.h(R$string.IDS_active_task_tip_subscribe_title));
                hashMap.put("description_key", nsf.b(R$string.IDS_active_task_tip_subscribe_text, b4, b5));
                hashMap.put("button_key", nsf.h(R$string.IDS_active_task_tip_subscribe_button));
                return hashMap;
            case TASK_STAND_HOUR:
                String a2 = nsf.a(R.plurals._2130903400_res_0x7f030168, i, Integer.valueOf(i));
                hashMap.put("title_key", nsf.h(R$string.IDS_active_task_tip_hour_title));
                hashMap.put("description_key", nsf.b(R$string.IDS_active_task_tip_hour_text, b3, a2));
                hashMap.put("button_key", d(0));
                return hashMap;
            case TASK_WORKOUT:
                String b6 = b(R.plurals._2130903370_res_0x7f03014a, i);
                hashMap.put("title_key", nsf.h(R$string.IDS_active_task_tip_workout_title));
                hashMap.put("description_key", nsf.b(R$string.IDS_active_task_tip_workout_text, b6, b3));
                hashMap.put("sport_type_key", String.valueOf(258));
                hashMap.put("button_key", d(258));
                hashMap.put("sport_diff_time_key", String.valueOf(i));
                return hashMap;
            case TASK_CALORIES:
                int e2 = e(i, 258);
                String b7 = b(R.plurals._2130903370_res_0x7f03014a, e2);
                hashMap.put("title_key", nsf.h(R$string.IDS_active_task_tip_calories_title));
                hashMap.put("description_key", nsf.b(R$string.IDS_active_task_tip_calories_text, b3, b7));
                hashMap.put("sport_type_key", String.valueOf(258));
                hashMap.put("button_key", d(258));
                hashMap.put("sport_diff_time_key", String.valueOf(e2));
                return hashMap;
            default:
                hashMap.putAll(e(i, tipType));
                return hashMap;
        }
    }

    private static void a(HashMap<String, String> hashMap, int i) {
        int b2;
        String b3;
        if (LanguageUtil.m(BaseApplication.e())) {
            int[] iArr = d;
            int a2 = a(iArr.length);
            b2 = b(a2);
            String h = nsf.h(R$string.IDS_hw_health_active_record_tips_five_workout);
            int[] iArr2 = c;
            String concat = nsf.h(iArr2[a(iArr2.length)]).concat(e(i)).concat(nsf.h(iArr[a2]));
            int[] iArr3 = b;
            b3 = concat.concat(nsf.h(iArr3[a(iArr3.length)])).concat(h);
        } else {
            int[] iArr4 = e;
            int a3 = a(iArr4.length);
            b2 = b(a3);
            b3 = nsf.b(iArr4[a3], e(i));
        }
        hashMap.put("sport_diff_time_key", String.valueOf(i));
        hashMap.put("description_key", b3);
        hashMap.put("sport_type_key", String.valueOf(b2));
        hashMap.put("button_key", d(b2));
    }

    private static void d(HashMap<String, String> hashMap, int i) {
        int b2;
        int e2;
        String b3;
        if (LanguageUtil.m(BaseApplication.e())) {
            int[] iArr = d;
            int a2 = a(iArr.length);
            b2 = b(a2);
            int[] iArr2 = g;
            String h = nsf.h(iArr2[a(iArr2.length)]);
            e2 = e(i, b2);
            int[] iArr3 = c;
            String concat = nsf.h(iArr3[a(iArr3.length)]).concat(e(e2)).concat(nsf.h(iArr[a2]));
            int[] iArr4 = b;
            b3 = concat.concat(nsf.h(iArr4[a(iArr4.length)])).concat(h);
        } else {
            int[] iArr5 = f8728a;
            int a3 = a(iArr5.length);
            b2 = b(a3);
            e2 = e(i, b2);
            b3 = nsf.b(iArr5[a3], e(e2));
        }
        hashMap.put("sport_diff_time_key", String.valueOf(e2));
        hashMap.put("description_key", b3);
        hashMap.put("sport_type_key", String.valueOf(b2));
        hashMap.put("button_key", d(b2));
    }

    private static String e(int i) {
        return nsf.a(R.plurals._2130903370_res_0x7f03014a, i, UnitUtil.e(i, 1, 0));
    }

    public static int e(int i, int i2) {
        ActivityCaloriesCalculateApi activityCaloriesCalculateApi = (ActivityCaloriesCalculateApi) Services.c("BaseSportModel", ActivityCaloriesCalculateApi.class);
        activityCaloriesCalculateApi.initUserInfo(bdw.d(((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).getUserInfo()));
        int i3 = i2 == 258 ? 0 : 1;
        float f2 = i / 1000.0f;
        long calculateDurationByCalories = activityCaloriesCalculateApi.calculateDurationByCalories(f2, i2, i3);
        LogUtil.a("SCUI_ActiveTipStringUtils", "time: ", Long.valueOf(calculateDurationByCalories), "intensity: ", Integer.valueOf(i3), "mSportType:", Integer.valueOf(i2), "calories:", Float.valueOf(f2));
        return (int) calculateDurationByCalories;
    }

    public static boolean b() {
        Context e2 = BaseApplication.e();
        if (LoginInit.getInstance(e2).isKidAccount()) {
            return false;
        }
        if (!Utils.o()) {
            return true;
        }
        return ((OperationUtilsApi) Services.c("PluginOperation", OperationUtilsApi.class)).isSupportCountry(LoginInit.getInstance(e2).getAccountInfo(1010), "ai-threeCircle-001", R.array._2130968711_res_0x7f040087);
    }

    private static String c() {
        int[] iArr = f;
        return nsf.h(iArr[a(iArr.length)]);
    }

    private static String d(int i) {
        if (i == 257) {
            return nsf.h(R$string.IDS_fitness_walk);
        }
        if (i == 258) {
            return nsf.h(R$string.IDS_main_time_line_start_workout);
        }
        return nsf.h(R$string.IDS_active_mark_stand);
    }

    private static int d(TipType tipType) {
        int i = AnonymousClass2.e[tipType.ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    if (i != 7) {
                        if (i != 8) {
                            if (i != 9) {
                                if (Utils.o()) {
                                    return R$drawable.ic_record_tips_task_oversea;
                                }
                                return R$drawable.ic_record_tips_task;
                            }
                        }
                    }
                }
                return R$drawable.ic_record_tips_kcal;
            }
            return R$drawable.ic_record_tips_workout;
        }
        return R$drawable.ic_record_tips_hours;
    }

    private static String b(int i, int i2) {
        return nsf.a(i, i2, UnitUtil.e(i2, 1, 0));
    }

    private static int a(int i) {
        return new SecureRandom().nextInt(i);
    }
}
