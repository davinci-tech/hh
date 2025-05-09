package defpackage;

import android.text.TextUtils;
import com.huawei.basefitnessadvice.model.Plan;
import com.huawei.health.R;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.UnitUtil;

/* loaded from: classes3.dex */
public class evf {
    public static int a(String str) {
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        return str.contains("_") ? 3 : 0;
    }

    public static mmw e(int i) {
        boolean h = UnitUtil.h();
        mmw mmwVar = new mmw();
        mmwVar.d(b(i));
        mmwVar.c(R.drawable._2131430940_res_0x7f0b0e1c);
        if (i == 0) {
            if (h) {
                mmwVar.e(arx.b().getString(R.string._2130839521_res_0x7f0207e1, UnitUtil.e(UnitUtil.e(5.0d, 3), 1, 1)));
            } else {
                mmwVar.e(arx.b().getString(R.string._2130839522_res_0x7f0207e2, 5));
            }
            mmwVar.d(0);
        } else if (i == 1) {
            if (h) {
                mmwVar.e(arx.b().getString(R.string._2130839523_res_0x7f0207e3, UnitUtil.e(UnitUtil.e(10.0d, 3), 1, 1)));
            } else {
                mmwVar.e(arx.b().getString(R.string._2130839524_res_0x7f0207e4, 10));
            }
            mmwVar.d(1);
        } else if (i == 2) {
            mmwVar.e(arx.b().getString(R.string._2130839525_res_0x7f0207e5));
            mmwVar.d(2);
        } else if (i == 3) {
            mmwVar.e(arx.b().getString(R.string._2130839526_res_0x7f0207e6));
            mmwVar.d(3);
        } else {
            LogUtil.h("PlanService_PlanServiceUtil", "getRunPlanInfo default branch.");
        }
        return mmwVar;
    }

    public static float b(float f) {
        if (f <= 100.0f) {
            return f;
        }
        LogUtil.b("PlanService_PlanServiceUtil", "truncateOverOneHundred target = ", Float.valueOf(f));
        return 100.0f;
    }

    public static int e(String str) {
        if (str == null || str.length() < 5) {
            return -1;
        }
        char charAt = str.charAt(2);
        if (str.length() == 5 || charAt == '3') {
            return 3;
        }
        if (charAt == '0') {
            return 0;
        }
        return charAt == '1' ? 1 : -1;
    }

    public static boolean d(String str, int i) {
        int e = e(str);
        if (i == 0 && e == 1) {
            return false;
        }
        return (e == 0 && i == 1) ? false : true;
    }

    public static String b(int i) {
        String e = moi.e(arx.b(), R.string._2130839527_res_0x7f0207e7, new Object[0]);
        if (i == 0) {
            return a(5);
        }
        if (i == 1) {
            return a(10);
        }
        if (i == 2) {
            if (UnitUtil.h()) {
                return moi.e(arx.b(), R.string._2130839528_res_0x7f0207e8, ffy.b(R.plurals._2130903109_res_0x7f030045, 13, UnitUtil.e(13.1d, 1, 1)));
            }
            return moi.e(arx.b(), R.string._2130839529_res_0x7f0207e9, new Object[0]);
        }
        if (i != 3) {
            return e;
        }
        if (UnitUtil.h()) {
            return moi.e(arx.b(), R.string._2130839528_res_0x7f0207e8, ffy.b(R.plurals._2130903109_res_0x7f030045, 26, UnitUtil.e(26.2d, 1, 1)));
        }
        return moi.e(arx.b(), R.string._2130839530_res_0x7f0207ea, new Object[0]);
    }

    private static String a(int i) {
        if (UnitUtil.h()) {
            double c = jed.c((int) (i * 1000.0d));
            return moi.e(arx.b(), R.string._2130839528_res_0x7f0207e8, ffy.b(R.plurals._2130903109_res_0x7f030045, (int) c, UnitUtil.e(c, 1, 1)));
        }
        return ffy.d(arx.b(), R.string._2130839528_res_0x7f0207e8, ffy.b(R.plurals._2130903108_res_0x7f030044, i, UnitUtil.e(i, 1, 0)));
    }

    public static boolean b(WorkoutRecord workoutRecord) {
        if (workoutRecord != null && !TextUtils.isEmpty(workoutRecord.acquirePlanId())) {
            String acquirePlanId = workoutRecord.acquirePlanId();
            Plan currentPlan = etr.b().getCurrentPlan();
            if (currentPlan != null && acquirePlanId.equals(currentPlan.acquireId())) {
                return currentPlan.getPlanCategory() == 0 && currentPlan.acquireType() == 0;
            }
            String acquireWorkoutId = workoutRecord.acquireWorkoutId();
            if (!TextUtils.isEmpty(acquireWorkoutId)) {
                try {
                    return Integer.parseInt(acquireWorkoutId) < 500;
                } catch (NumberFormatException unused) {
                    LogUtil.h("PlanService_PlanServiceUtil", "not number workout id.");
                }
            }
        }
        return false;
    }
}
