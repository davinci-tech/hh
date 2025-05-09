package defpackage;

import android.content.Context;
import android.os.Bundle;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.sport.model.data.RecordPlanInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.FitWorkout;
import health.compact.a.CommonUtils;

/* loaded from: classes4.dex */
class fiq {
    static void d(String str) {
        LogUtil.a("Suggestion_ExternalTrackUtils", "gotoTrackDetail SportId:", str);
        String[] split = str.split("_");
        if (split.length != 2) {
            LogUtil.b("Suggestion_ExternalTrackUtils", "showDetails error, can not use this sportId:", str);
            return;
        }
        long b = CommonUtils.b(split[0], -1L);
        long b2 = CommonUtils.b(split[1], -1L);
        if (b == -1 || b2 == -1) {
            LogUtil.b("Suggestion_ExternalTrackUtils", "showDetails error with start time:", Long.valueOf(b), ", end time: ", Long.valueOf(b2));
        }
        eme.b().startTrackDetail(b, b2);
    }

    static void d() {
        HandlerExecutor.e(new Runnable() { // from class: fip
            @Override // java.lang.Runnable
            public final void run() {
                eme.b().startNearTrackDetail();
            }
        });
    }

    static void d(final Context context, final int i, final int i2, final float f) {
        HandlerExecutor.e(new Runnable() { // from class: fir
            @Override // java.lang.Runnable
            public final void run() {
                eme.b().gotoSport(context, i, i2, f);
            }
        });
    }

    static int axU_(Context context, FitWorkout fitWorkout, RecordPlanInfo recordPlanInfo, Bundle bundle) {
        double acquireDistance;
        if (fitWorkout == null) {
            LogUtil.h("Suggestion_ExternalTrackUtils", "fitWorkout is null");
            return 3;
        }
        if (context == null) {
            context = BaseApplication.getContext();
        }
        Context context2 = context;
        if (fitWorkout.acquireMeasurementType() == 1) {
            acquireDistance = fitWorkout.acquireDuration();
        } else {
            acquireDistance = fitWorkout.acquireDistance();
        }
        Bundle bundle2 = new Bundle();
        if (ggs.d(fitWorkout)) {
            bundle2.putInt("runningCourse", 2);
        } else {
            bundle2.putInt("runningCourse", 1);
        }
        bundle2.putParcelable("runCourseDetail", fitWorkout);
        bundle2.putParcelable("coursePlanInfo", recordPlanInfo);
        if (bundle != null) {
            bundle2.putAll(bundle);
        }
        LogUtil.a("Suggestion_ExternalTrackUtils", "startTrackCourseSport");
        if (fitWorkout.isWalkCourse()) {
            return eme.b().startRunCourseSport(context2, 257, 4, (float) acquireDistance, bundle2);
        }
        return eme.b().startRunCourseSport(context2, 258, 4, (float) acquireDistance, bundle2);
    }

    static void b(Object obj, String str) {
        eme.b().playSound(obj, str);
    }

    static void b(String str, Object obj, String str2) {
        eme.b().playSound(str, obj, str2);
    }

    static void d(String str, int i) {
        eme.b().sendPlayerCommand(str, i);
    }

    static void a(String str, String str2, int i) {
        eme.b().startVoiceService(str, str2, i);
    }

    static void e(String str) {
        eme.b().stopVoiceService(str);
    }
}
