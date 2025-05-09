package defpackage;

import com.huawei.health.suggestion.ui.fitness.module.Motion;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* loaded from: classes4.dex */
public class fno {
    private static final long c = TimeUnit.HOURS.toSeconds(1);

    public static float e(Motion motion, int i, float f) {
        float c2 = foa.c(motion, i);
        if (c2 < 0.0f) {
            c2 = 0.0f;
        }
        return ((((((c2 * motion.acquireCalorie()) * motion.acquireDuration()) * motion.acquireGroups()) * f) / 1.0f) / 1000.0f) + 0.0f;
    }

    public static float b(Motion motion, int i, float f) {
        float c2 = foa.c(motion, i);
        if (c2 < 0.0f) {
            c2 = 0.0f;
        }
        return e(((c2 * motion.acquireDuration()) * motion.acquireGroups()) / 1000.0f, 1.3f, f);
    }

    public static float e(float f, float f2, float f3) {
        return (((f * f2) * 1000.0f) * f3) / c;
    }

    public static float d(int i, Motion motion, float f) {
        if (CommonUtil.c(motion.acquireDuration())) {
            return 0.0f;
        }
        return (i / motion.acquireDuration()) * c(motion, f);
    }

    public static float e(int i, Motion motion, float f) {
        if (CommonUtil.c(motion.acquireDuration())) {
            return 0.0f;
        }
        return (i / motion.acquireDuration()) * e(motion.getVideoSegments().get(0).getDuration(), 1.3f, f);
    }

    public static float d(List<Motion> list, float f, boolean z) {
        float f2 = 0.0f;
        if (koq.b(list)) {
            return 0.0f;
        }
        for (Motion motion : list) {
            if (z) {
                f2 += c(motion, f);
            } else {
                f2 += ((((motion.acquireCalorie() * motion.acquireDuration()) * motion.acquireGroups()) * f) / 1.0f) / 1000.0f;
            }
        }
        LogUtil.a("Suggestion_CaloriesCountHelper", "getTotalCalories ", Float.valueOf(f2));
        return f2;
    }

    public static float c(Motion motion, float f) {
        if (motion == null) {
            return 0.0f;
        }
        if (koq.b(motion.getVideoSegments(), 0)) {
            LogUtil.h("Suggestion_CaloriesCountHelper", "calCalories() motion videoSegment is empty");
            return 0.0f;
        }
        float acquireCalorie = (motion.acquireCalorie() * motion.getVideoSegments().get(0).getDuration() * f) + 0.0f;
        LogUtil.a("Suggestion_CaloriesCountHelper", "getCurrentMotionCalCalories ", Float.valueOf(acquireCalorie), " ", Integer.valueOf(motion.getVideoSegments().get(0).getDuration()), "motion calorie:", Float.valueOf(motion.acquireCalorie()));
        return acquireCalorie;
    }

    public static float a(List<Motion> list, float f) {
        LogUtil.a("Suggestion_CaloriesCountHelper", "getShouldBurnCalories weight", Float.valueOf(f), " motions:", moj.e(list));
        float f2 = 0.0f;
        for (Motion motion : list) {
            if (motion != null) {
                f2 += (((motion.acquireCalorie() * motion.acquireDuration()) * Math.max(motion.acquireGroups(), 1)) * f) / 1000.0f;
            }
        }
        return f2;
    }
}
