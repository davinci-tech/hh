package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import com.huawei.caloriecalculate.ActivityCaloriesCalculateApi;
import com.huawei.health.R;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.activity.step.FitnessStepDetailActivity;
import health.compact.a.CalculateDistanceUtils;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import health.compact.a.util.LogUtil;
import java.util.Locale;

/* loaded from: classes6.dex */
public class pvm {
    public static CharSequence e(Context context, int i, pvj pvjVar) {
        int round = Math.round(i / 1000.0f);
        int e = e(round);
        Resources resources = context.getResources();
        if (e == -1) {
            if (pvjVar != null && pvjVar.a()) {
                pvjVar.e(e);
            }
            return resources.getString(R$string.IDS_hw_common_ui_have_step_less);
        }
        if (pvjVar != null && pvjVar.a()) {
            pvjVar.e(e);
        }
        String e2 = UnitUtil.e(round, 1, 0);
        a aVar = new a();
        duk_(e, round, resources, aVar, pvjVar);
        if (aVar.b == null) {
            if (pvjVar != null) {
                pvjVar.e(-1);
            }
            return resources.getString(R$string.IDS_hw_common_ui_have_step_less);
        }
        String format = String.format(Locale.ROOT, context.getResources().getString(R$string.IDS_hw_common_ui_have_consumed), e2, aVar.e);
        int indexOf = aVar.e.indexOf(aVar.b);
        if (indexOf == -1) {
            return format;
        }
        SpannableString spannableString = new SpannableString(format);
        int indexOf2 = format.indexOf(aVar.e) + indexOf;
        nsi.cKG_(spannableString, indexOf2, aVar.b.length() + indexOf2, new ForegroundColorSpan(Color.parseColor("#FB6522")));
        return spannableString;
    }

    private static int e(int i) {
        if (i < 25) {
            return -1;
        }
        int i2 = i % 300;
        if (i2 < 25 || i2 >= 275) {
            return 3;
        }
        int i3 = i % 250;
        if (i3 < 25 || i3 >= 225) {
            return 2;
        }
        int i4 = i % 150;
        return (i4 < 25 || i4 >= 125) ? 1 : 0;
    }

    private static void duk_(int i, int i2, Resources resources, a aVar, pvj pvjVar) {
        float f = i2 * 1.0f;
        int round = Math.round(f / 50.0f);
        int round2 = Math.round(f / 150.0f);
        int round3 = Math.round(f / 250.0f);
        int round4 = Math.round(f / 300.0f);
        if (i == 0) {
            aVar.b = UnitUtil.e(round, 1, 0);
            aVar.e = resources.getQuantityString(R.plurals._2130903234_res_0x7f0300c2, round, aVar.b);
        } else if (i == 1) {
            aVar.b = UnitUtil.e(round2, 1, 0);
            aVar.e = resources.getQuantityString(R.plurals._2130903235_res_0x7f0300c3, round2, aVar.b);
            round = round2;
        } else if (i == 2) {
            aVar.b = UnitUtil.e(round3, 1, 0);
            aVar.e = resources.getQuantityString(R.plurals._2130903236_res_0x7f0300c4, round3, aVar.b);
            round = round3;
        } else if (i == 3) {
            aVar.b = UnitUtil.e(round4, 1, 0);
            aVar.e = resources.getQuantityString(R.plurals._2130903237_res_0x7f0300c5, round4, aVar.b);
            round = round4;
        } else {
            LogUtil.d("SCUI_StepUnitUtil", "wrong indexOfArray");
            round = 0;
        }
        if (pvjVar != null) {
            pvjVar.c(round);
        }
    }

    public static double d(float f) {
        LogUtil.d("SCUI_StepUnitUtil", "getCalculateDistance totalStep ", Float.valueOf(f));
        if (((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).getUserInfo() != null) {
            return CalculateDistanceUtils.a((int) f, r0.getHeightOrDefaultValue()) * 1000.0d;
        }
        return 0.0d;
    }

    public static double c(float f) {
        ActivityCaloriesCalculateApi activityCaloriesCalculateApi = (ActivityCaloriesCalculateApi) Services.c("BaseSportModel", ActivityCaloriesCalculateApi.class);
        activityCaloriesCalculateApi.initUserInfo(bdw.d(((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).getUserInfo()));
        return activityCaloriesCalculateApi != null ? activityCaloriesCalculateApi.calculateCaloriesOnlyStep((int) f) * 1000.0f : 0.0f;
    }

    public static int b(float f, int i, int i2) {
        long j;
        ActivityCaloriesCalculateApi activityCaloriesCalculateApi = (ActivityCaloriesCalculateApi) Services.c("BaseSportModel", ActivityCaloriesCalculateApi.class);
        UserProfileMgrApi userProfileMgrApi = (UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class);
        if (activityCaloriesCalculateApi == null || userProfileMgrApi == null) {
            j = 0;
        } else {
            activityCaloriesCalculateApi.initUserInfo(bdw.d(userProfileMgrApi.getUserInfo()));
            j = activityCaloriesCalculateApi.calculateDurationByCalories(f, i, i2);
        }
        return (int) j;
    }

    public static String b(int i) {
        int i2;
        int i3;
        int e = e(i);
        if (e == 0) {
            i2 = R.plurals._2130903297_res_0x7f030101;
            i3 = 50;
        } else if (e == 1) {
            i2 = R.plurals._2130903298_res_0x7f030102;
            i3 = 150;
        } else if (e == 2) {
            i2 = R.plurals._2130903299_res_0x7f030103;
            i3 = 250;
        } else if (e != 3) {
            i2 = R$string.IDS_hw_common_ui_have_step_less;
            i3 = 1;
        } else {
            i2 = R.plurals._2130903300_res_0x7f030104;
            i3 = 300;
        }
        LogUtil.d("SCUI_StepUnitUtil", "foodType ", Integer.valueOf(e), "transferCalorie ", Integer.valueOf(i3));
        if (e < 0) {
            return BaseApplication.getContext().getResources().getString(i2);
        }
        float f = (i * 1.0f) / i3;
        String e2 = UnitUtil.e(Math.round(f), 1, 0);
        if (TextUtils.isEmpty(e2)) {
            return null;
        }
        return BaseApplication.getContext().getResources().getQuantityString(i2, Math.round(f), e2);
    }

    public static void b(Context context) {
        if (context == null) {
            context = BaseApplication.getContext();
        }
        if (context instanceof FitnessStepDetailActivity) {
            ((FitnessStepDetailActivity) context).b(context.getResources().getString(R$string.IDS_fitness_goal_achieved));
        }
    }

    public static void e(Context context, float f, float f2) {
        if (context == null) {
            context = BaseApplication.getContext();
        }
        int round = Math.round(f2 - f);
        if (context instanceof FitnessStepDetailActivity) {
            ((FitnessStepDetailActivity) context).b(context.getResources().getQuantityString(R.plurals.IDS_fitness_completed_goal, round, UnitUtil.e(round, 1, 0)));
        }
    }

    public static void e(Context context, int i) {
        if (context == null) {
            context = BaseApplication.getContext();
        }
        if (context instanceof FitnessStepDetailActivity) {
            ((FitnessStepDetailActivity) context).e(i);
        }
    }

    public static int d(float f, int i) {
        return Math.max(Math.round((f * 100.0f) / i), 1);
    }

    /* loaded from: classes9.dex */
    static class a {
        String b;
        String e;

        private a() {
        }
    }
}
