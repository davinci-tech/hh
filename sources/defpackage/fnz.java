package defpackage;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import com.huawei.health.R;
import com.huawei.health.suggestion.ui.fitness.module.CoachView;
import com.huawei.health.suggestion.ui.fitness.module.Motion;
import com.huawei.health.suggestion.util.CourseEquipmentConnectionTipsUtil;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.up.model.UserInfomation;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.Services;
import health.compact.a.UnitUtil;

/* loaded from: classes.dex */
public class fnz {
    public static boolean a(boolean z, int i, boolean z2) {
        return z && !z2 && (i != -100 && i != 192);
    }

    public static boolean d(int i) {
        return i == R.id.sug_coach_set_iv_continue || i == R.id.sug_coach_set_voice_ok;
    }

    public static void d(CoachView coachView, Motion motion) {
        if (coachView == null || motion == null) {
            LogUtil.h("Suggestion_CoachViewUIHelper", "changeProgress coachView == null || currMotion == null");
            return;
        }
        if ("timer".equals(motion.acquireMotionType())) {
            coachView.setTimeView(coachView.f().a(), -1, true);
        } else if (coachView.n()) {
            coachView.setTimeView(coachView.f().a() - 1, -1, false);
        } else {
            coachView.setTimeView(coachView.f().a(), -1, false);
        }
        float f = "hotbody".equals(motion.acquireMotionType()) ? coachView.a().f() : motion.acquireDuration();
        if ("hotbody".equals(motion.acquireMotionType())) {
            coachView.t().setProgress(coachView.f().b(), 0, (coachView.b() * coachView.t().d(coachView.f().b())) / f);
        } else {
            coachView.t().setProgress(coachView.f().b(), coachView.f().e(), coachView.b());
        }
        coachView.setTimeProgress((int) ((coachView.b() / f) * coachView.getTimeProgressMax()));
        e(coachView);
    }

    public static void e(CoachView coachView) {
        if (CourseEquipmentConnectionTipsUtil.a()) {
            float f = 0.0f;
            for (int i = 0; i < coachView.f().b(); i++) {
                f += coachView.t().d(i);
            }
            final int b = (int) (((f + coachView.b()) / coachView.t().getTotalTime()) * 100.0f);
            jdx.b(new Runnable() { // from class: foc
                @Override // java.lang.Runnable
                public final void run() {
                    cei.b().setFitnessRopeConfig(5, 1, new int[]{b});
                }
            });
        }
    }

    public static void a(CoachView coachView, int i) {
        if (coachView == null) {
            LogUtil.h("Suggestion_CoachViewUIHelper", "state321 coachView == null");
            return;
        }
        LogUtil.a("Suggestion_CoachViewUIHelper", "state:", Integer.valueOf(i));
        coachView.f().a(190);
        coachView.e(190);
        coachView.setTimeProgress(i);
    }

    public static void a(CoachView coachView, Motion motion) {
        if (coachView == null || motion == null) {
            LogUtil.h("Suggestion_CoachViewUIHelper", "stateGo coachView == null || currMotion == null");
            return;
        }
        LogUtil.a("Suggestion_CoachViewUIHelper", "state go");
        coachView.b(0L);
        coachView.f().a(190);
        coachView.e(190);
        coachView.setTimeProgress(0);
        if ("timer".equals(motion.acquireMotionType())) {
            coachView.setTimeView(0, motion.acquireRepeat(), true);
        } else {
            coachView.setTimeView(0, motion.acquireRepeat(), false);
        }
        coachView.setTimeProgressMax((int) motion.acquireDuration());
    }

    public static void a(CoachView coachView, int i, int i2) {
        if (coachView == null) {
            LogUtil.h("Suggestion_CoachViewUIHelper", "refreshButton coachView == null");
            return;
        }
        if (i == 0) {
            coachView.h().getPreAction().setVisibility(4);
            coachView.h().getNextAction().setVisibility(0);
        } else if (i == i2) {
            coachView.h().getPreAction().setVisibility(0);
            coachView.h().getNextAction().setVisibility(4);
        } else {
            coachView.h().getPreAction().setVisibility(0);
            coachView.h().getNextAction().setVisibility(0);
        }
    }

    public static String e(Motion motion, String str) {
        if (motion != null) {
            return (motion.acquireCommentaryGap() == null || motion.acquireCommentaryGap().size() <= 0) ? str : motion.acquireCommentaryGap().get(0).acquireContent();
        }
        LogUtil.h("Suggestion_CoachViewUIHelper", "getContent motion == null");
        return str;
    }

    public static void a(CoachView coachView, int i, Motion motion, int i2) {
        if (coachView == null || motion == null) {
            LogUtil.h("Suggestion_CoachViewUIHelper", "restActionFinish coachView == null || motion == null");
        } else if (coachView.f().b() == i2) {
            coachView.m().b(false, motion);
        } else {
            coachView.m().b(true, motion);
        }
    }

    public static String b(String str) {
        String b = ash.b(str);
        return TextUtils.isEmpty(b) ? "1000" : b;
    }

    private static UserInfomation c() {
        UserProfileMgrApi userProfileMgrApi = (UserProfileMgrApi) Services.a("HWUserProfileMgr", UserProfileMgrApi.class);
        if (userProfileMgrApi == null) {
            LogUtil.h("Suggestion_CoachViewUIHelper", "getUserInformation api is null");
            return new UserInfomation(UnitUtil.h() ? 1 : 0);
        }
        UserInfomation userInfo = userProfileMgrApi.getUserInfo();
        if (userInfo != null) {
            return userInfo;
        }
        LogUtil.h("Suggestion_CoachViewUIHelper", "getUserInformation userInformation is null");
        return new UserInfomation(UnitUtil.h() ? 1 : 0);
    }

    public static int b() {
        return c().getGenderOrDefaultValue();
    }

    public static float e() {
        return c().getWeightOrDefaultValue();
    }

    public static void c(CoachView coachView, float f) {
        if (coachView == null) {
            LogUtil.h("Suggestion_CoachViewUIHelper", "rateEffective coachView == null");
            return;
        }
        if (f <= 0.6d || !(coachView.aDb_().getTag() instanceof Boolean)) {
            return;
        }
        if (((Boolean) coachView.aDb_().getTag()).booleanValue()) {
            coachView.b(true);
        } else {
            coachView.aDb_().setTag(true);
        }
    }

    public static boolean e(CoachView coachView, int i) {
        if (coachView != null) {
            return coachView.r() != null && i == R.id.sug_coach_set_iv_stop;
        }
        LogUtil.h("Suggestion_CoachViewUIHelper", "isFinish coachView == null");
        return false;
    }

    public static void e(CoachView coachView, Motion motion) {
        if (coachView == null || motion == null) {
            LogUtil.h("Suggestion_CoachViewUIHelper", "changeListener coachView == null || currMotion == null");
        } else if (coachView.r() != null) {
            coachView.r().onGroupFinish(motion, coachView.f().e(), motion.acquireGroups());
        }
    }

    public static void b(CoachView coachView, int i) {
        if (coachView == null) {
            LogUtil.h("Suggestion_CoachViewUIHelper", "preNextChange coachView == null");
            return;
        }
        if (coachView.f().b() == 0) {
            aBH_(coachView.aDa_(), 0.4f, 2);
            return;
        }
        if (coachView.f().b() == 1) {
            aBH_(coachView.aDa_(), 1.0f, 0);
            return;
        }
        if (coachView.f().b() == i - 1) {
            aBH_(coachView.aCZ_(), 0.4f, 2);
        } else if (coachView.f().b() == i - 2) {
            aBH_(coachView.aCZ_(), 1.0f, 0);
        } else {
            LogUtil.c("Suggestion_CoachViewUIHelper", "CurrMotion unexpected");
        }
    }

    public static void aBH_(ImageView imageView, float f, int i) {
        if (imageView == null) {
            ReleaseLogUtil.a("Suggestion_CoachViewUIHelper", "setAlpha imageView is null");
        } else {
            imageView.setAlpha(f);
            jcf.bEE_(imageView, i);
        }
    }

    public static int aBG_(View view) {
        if (view != null) {
            return view.getVisibility() == 0 ? 8 : 0;
        }
        LogUtil.h("Suggestion_CoachViewUIHelper", "controlLockVisitable mLockLayout == null");
        return 8;
    }
}
