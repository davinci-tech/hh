package defpackage;

import com.huawei.basefitnessadvice.model.ExerciseProfile;
import com.huawei.basefitnessadvice.model.RunWorkout;
import com.huawei.health.R;
import health.compact.a.CommonUtil;
import health.compact.a.UnitUtil;
import java.util.regex.Pattern;

/* loaded from: classes3.dex */
public class evc {
    private static boolean a(String str) {
        return str != null && Pattern.compile("[0-9]+").matcher(str).matches();
    }

    private static String e(ffe ffeVar, int i, int i2, int i3) {
        String b;
        String str;
        String b2 = ffy.b(R.plurals._2130903106_res_0x7f030042, i3, UnitUtil.e(i3, 1, 0));
        String b3 = ffy.b(ffz.c(), i2, UnitUtil.e(b(i2), 1, 2));
        int i4 = i - 9;
        int b4 = ffeVar.b(i4);
        int a2 = ffeVar.a(i4);
        if (i >= 21) {
            str = ffy.b(R.plurals._2130903107_res_0x7f030043, b4, UnitUtil.e(b4, 1, 0));
            b = ffy.b(R.plurals._2130903107_res_0x7f030043, a2, UnitUtil.e(a2, 1, 0));
        } else {
            String b5 = ffy.b(R.plurals._2130903106_res_0x7f030042, b4, UnitUtil.e(b4, 1, 0));
            b = ffy.b(R.plurals._2130903106_res_0x7f030042, a2, UnitUtil.e(a2, 1, 0));
            str = b5;
        }
        return moi.e(ffeVar.c(i), b2, b3, str, b);
    }

    private static double b(double d) {
        return UnitUtil.h() ? UnitUtil.e(d, 3) : d;
    }

    public static void a(RunWorkout[] runWorkoutArr) {
        if (runWorkoutArr == null || runWorkoutArr.length == 0) {
            return;
        }
        ffe ffeVar = new ffe(runWorkoutArr[0].getType());
        for (RunWorkout runWorkout : runWorkoutArr) {
            if (runWorkout != null) {
                e(runWorkout, ffeVar);
            }
        }
    }

    private static void e(RunWorkout runWorkout, ffe ffeVar) {
        ExerciseProfile warmup = runWorkout.getWarmup();
        ExerciseProfile rest = runWorkout.getRest();
        ExerciseProfile work = runWorkout.getWork();
        if (warmup != null) {
            warmup.setMessage(a(0, warmup.getDuration()));
        }
        if (rest != null) {
            rest.setMessage(a(1, rest.getDuration()));
        }
        if (work != null) {
            work.setMessage(a(2, work.getDuration()));
        }
        ExerciseProfile cooldown = runWorkout.getCooldown();
        if (cooldown != null) {
            cooldown.setMessage(a(3, cooldown.getDuration()));
        }
        if (a(runWorkout.acquireName())) {
            runWorkout.saveName(ffeVar.d(CommonUtil.h(runWorkout.acquireName())));
        }
        if (a(runWorkout.acquireDescription())) {
            runWorkout.saveDescription(e(ffeVar, CommonUtil.h(runWorkout.acquireDescription()), runWorkout.acquireDistance(), runWorkout.acquireDuration()));
        }
    }

    private static String a(int i, int i2) {
        if (i == 0) {
            return d(i2, R.plurals._2130903110_res_0x7f030046, R.plurals._2130903111_res_0x7f030047);
        }
        if (i == 1) {
            return d(i2, R.plurals._2130903126_res_0x7f030056, R.plurals._2130903112_res_0x7f030048);
        }
        if (i == 2) {
            return d(i2, R.plurals._2130903113_res_0x7f030049, R.plurals._2130903114_res_0x7f03004a);
        }
        return i == 3 ? d(i2, R.plurals._2130903115_res_0x7f03004b, R.plurals._2130903116_res_0x7f03004c) : "";
    }

    private static String d(int i, int i2, int i3) {
        if (i < 60) {
            return ffy.b(i2, i, UnitUtil.e(i, 1, 0));
        }
        int i4 = i / 60;
        return ffy.b(i3, i4, UnitUtil.e(i4, 1, 0));
    }
}
