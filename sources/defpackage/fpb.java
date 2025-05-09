package defpackage;

import com.huawei.health.suggestion.ui.fitness.module.LongCoachView;
import com.huawei.health.suggestion.ui.fitness.module.Motion;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes4.dex */
public class fpb {
    public static void a(LongCoachView longCoachView, Motion motion) {
        if (longCoachView == null || motion == null) {
            LogUtil.h("Suggestion_LongViewGoHelper", "stateGo coachView == null || currMotion == null");
            return;
        }
        LogUtil.a("Suggestion_LongViewGoHelper", "state go");
        longCoachView.b().c(257);
        longCoachView.j(257);
        if (koq.b(motion.getVideoSegments(), 0)) {
            LogUtil.h("Suggestion_LongViewGoHelper", "stateGo motion getVideoSegment is empty");
            return;
        }
        int duration = motion.getVideoSegments().get(0).getDuration();
        if ("timer".equals(motion.acquireMotionType())) {
            longCoachView.setTimeView(0, duration, true);
        } else {
            longCoachView.setTimeView(0, motion.acquireRepeat(), false);
        }
    }
}
