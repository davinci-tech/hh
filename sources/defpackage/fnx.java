package defpackage;

import android.os.Message;
import com.huawei.health.suggestion.CoachController;
import com.huawei.health.suggestion.ui.fitness.module.CoachView;
import com.huawei.health.suggestion.ui.fitness.module.Motion;
import com.huawei.health.suggestion.ui.fitness.mvp.CaloriesConsumeHandler;
import com.huawei.health.suggestion.util.StaticHandler;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import java.text.SimpleDateFormat;

/* loaded from: classes8.dex */
public class fnx extends StaticHandler<CoachView> {
    public fnx(CoachView coachView) {
        super(coachView);
    }

    @Override // com.huawei.health.suggestion.util.StaticHandler
    /* renamed from: aBA_, reason: merged with bridge method [inline-methods] */
    public void handleMessage(CoachView coachView, Message message) {
        if (message == null) {
            LogUtil.h("Suggestion_CoachHandler", "handleMessage msg == null");
            return;
        }
        if (message.what == 103 && coachView != null) {
            LogUtil.a("Suggestion_CoachHandler", "MediaWhat.AUDIO_FINISH");
            if (coachView.r() != null) {
                LogUtil.a("Suggestion_CoachHandler", "MediaWhat.AUDIO_FINISH onTrainOver(false)");
                coachView.r().onTrainOver(false);
            }
        }
        if (coachView == null || coachView.f().c() == -100 || coachView.p() == null) {
            return;
        }
        if (koq.b(coachView.p(), coachView.f().b())) {
            LogUtil.h("Suggestion_CoachHandler", "coachView.acquireCoachState().acquireCurrMotion() is out of Bounds");
        } else {
            aBz_(coachView, message, coachView.p().get(coachView.f().b()));
        }
    }

    private void aBz_(final CoachView coachView, Message message, Motion motion) {
        if (message.what == 101) {
            coachView.ah();
            coachView.b(-1, false);
            c(coachView, motion);
            return;
        }
        if (message.what == 153) {
            i(coachView, motion);
            coachView.e().setIsShowBottomProgress(true);
            return;
        }
        if (message.what == 102) {
            g(coachView, motion);
            coachView.e().setIsShowBottomProgress(false);
            return;
        }
        if (message.what == 3) {
            coachView.b(3, true);
            fnz.a(coachView, 3);
            return;
        }
        if (message.what == 2) {
            coachView.b(2, true);
            fnz.a(coachView, 2);
            return;
        }
        if (message.what == 1) {
            coachView.b(1, true);
            fnz.a(coachView, 1);
            return;
        }
        if (message.what == 0) {
            postDelayed(new Runnable() { // from class: fnx.1
                @Override // java.lang.Runnable
                public void run() {
                    coachView.b(0, true);
                    LogUtil.a("Suggestion_CoachHandler", "postDelayed");
                }
            }, 493L);
            LogUtil.a("Suggestion_CoachHandler", "MediaWhat.COUNTDOWN_ZERO");
            fnz.a(coachView, motion);
            if (jcf.c()) {
                LogUtil.a("Suggestion_CoachHandler", "identifyMediaAndSetCoachView isTouchExplorationEnabled");
                return;
            } else {
                coachView.e().setVisibility(8);
                return;
            }
        }
        if (message.what == 104) {
            LogUtil.a("Suggestion_CoachHandler", "MediaWhat.FINISH");
            if (coachView.r() != null) {
                coachView.b(true);
                coachView.r().onTrainOver(true);
                coachView.r().onMotionOver(motion, coachView.f().b());
            }
            coachView.f().a(-100);
            return;
        }
        if (message.what == 154) {
            LogUtil.a("Suggestion_CoachHandler", "MediaWhat.MOTION_NAME");
            if (coachView.r() != null) {
                coachView.r().onMotionReady(motion, coachView.f().b());
                return;
            }
            return;
        }
        LogUtil.c("Suggestion_CoachHandler", "MediaWhat unexpected");
    }

    private void g(CoachView coachView, Motion motion) {
        if (motion == null) {
            LogUtil.h("Suggestion_CoachHandler", "stateTrainFinish currMotion == null");
            return;
        }
        LogUtil.a("Suggestion_CoachHandler", "a group of action or training completed:{}", motion.acquireName());
        if (coachView.f() == null) {
            LogUtil.h("Suggestion_CoachHandler", "stateTrainFinish coachView.acquireCoachState() == null");
            return;
        }
        coachView.f().a(190);
        coachView.e(190);
        if (coachView.f().e() + 1 == motion.acquireGroups()) {
            e(coachView, motion);
        } else {
            d(coachView, motion);
        }
    }

    private void e(CoachView coachView, Motion motion) {
        coachView.f().b(motion.acquireGroups() - 1);
        coachView.ab();
        coachView.f().d(coachView.f().b() + 1);
        if (coachView.f().b() < coachView.p().size()) {
            a(coachView);
            coachView.b(coachView.f().b());
        } else {
            b(coachView);
        }
    }

    private void a(CoachView coachView) {
        if (koq.b(coachView.p(), coachView.f().b() - 1)) {
            LogUtil.b("Suggestion_CoachHandler", "acquireMotions: isOutOfBounds");
            return;
        }
        LogUtil.a("Suggestion_CoachHandler", "resting time:", Integer.valueOf(coachView.p().get(coachView.f().b() - 1).acquireGap()));
        if (coachView.p().get(coachView.f().b() - 1).acquireGap() > 0) {
            if (coachView.r() != null) {
                coachView.r().onMotionReady(coachView.p().get(coachView.f().b()), coachView.f().b());
            }
            LogUtil.a("Suggestion_CoachHandler", "to resting");
            coachView.ax();
            coachView.m().voiceGuideRest();
        } else {
            if (coachView.f().b() == coachView.p().size() - 1) {
                coachView.m().lastMotion(coachView.p().get(coachView.f().b()));
            } else {
                coachView.m().nextMotion(coachView.p().get(coachView.f().b()));
            }
            CoachController.d().b(CoachController.StatusSource.APP, coachView.f().b());
        }
        coachView.z();
        coachView.s().next();
    }

    private void b(CoachView coachView) {
        LogUtil.a("Suggestion_CoachHandler", "training completed");
        coachView.m().wellDone();
        coachView.s().pause();
        coachView.c().pause();
        coachView.f().d(coachView.p().size() - 1);
    }

    private void d(CoachView coachView, Motion motion) {
        if (koq.b(coachView.p(), coachView.f().b())) {
            LogUtil.b("Suggestion_CoachHandler", "finishOneGroup acquireMotions: isOutOfBounds");
            return;
        }
        if (coachView.r() != null) {
            coachView.r().onMotionReady(coachView.p().get(coachView.f().b()), coachView.f().b());
        }
        coachView.ax();
        coachView.m().voiceGuideRest();
        coachView.c(motion);
        coachView.f().b(coachView.f().e() + 1);
        coachView.a().saveCurrent(-1);
    }

    private void i(CoachView coachView, Motion motion) {
        if (motion == null) {
            LogUtil.h("Suggestion_CoachHandler", "trainingState currMotion == null");
            return;
        }
        coachView.f().a(191);
        coachView.e(191);
        coachView.a((int) ((coachView.i() + System.currentTimeMillis()) - coachView.x()));
        coachView.v().setText(new SimpleDateFormat("mm:ss").format(Long.valueOf(coachView.u() + coachView.b())));
        foa.d(coachView, motion, foa.e(coachView, motion));
        int e = (coachView.f().e() * motion.acquireRepeat()) + coachView.f().a();
        int acquireProgress = motion.acquireProgress();
        int i = e - acquireProgress;
        float e2 = fno.e(motion, i, coachView.getWeight());
        float b = fno.b(motion, i, coachView.getWeight());
        if ((!CommonUtil.c(e2) && acquireProgress == 0) || e2 < 0.0f) {
            LogUtil.a("Suggestion_CoachHandler", "trainingState newProgress:", Integer.valueOf(e), "progress:", Integer.valueOf(acquireProgress), " calCalories:", Float.valueOf(e2), " actionName:", motion.acquireName(), " currentCalories:", Float.valueOf(coachView.getCaloriesConsumeHandler().b()));
        }
        motion.saveProgress(e);
        coachView.getCaloriesConsumeHandler().c(coachView.getCaloriesConsumeHandler().b() + e2, coachView.getCaloriesConsumeHandler().d() + (e2 - b), CaloriesConsumeHandler.CaloriesSource.COURSE_PRESET);
        b(coachView, motion);
    }

    private void b(CoachView coachView, Motion motion) {
        if (foa.a(coachView, motion)) {
            sendEmptyMessageDelayed(153, coachView.q());
        } else {
            a(coachView, motion);
        }
    }

    private void a(CoachView coachView, Motion motion) {
        LogUtil.c("Suggestion_CoachHandler", "mActionTrainTime:", Integer.valueOf(coachView.b()), " acquireDuration:", Float.valueOf(motion.acquireDuration()));
        if (coachView.b() <= motion.acquireDuration()) {
            if (!coachView.n()) {
                sendEmptyMessageDelayed(153, coachView.q());
                return;
            } else if (coachView.b() >= motion.acquireInterval() * motion.acquireRepeat()) {
                sendEmptyMessage(102);
                return;
            } else {
                LogUtil.c("Suggestion_CoachHandler", "Paused during action");
                return;
            }
        }
        sendEmptyMessage(102);
    }

    private void c(CoachView coachView, Motion motion) {
        if (motion == null) {
            LogUtil.h("Suggestion_CoachHandler", "guideActionFinish currMotion == null");
            return;
        }
        coachView.az();
        coachView.setCaloreHeartViewsVisibility(0);
        coachView.b(0L);
        LogUtil.a("Suggestion_CoachHandler", "321go completed, start training action, replay video. Action name:", motion.acquireName(), "Action video duration: ", Integer.valueOf(coachView.s().f()), "----motion beat interval time :", Integer.valueOf(motion.acquireInterval()));
        coachView.f().a(191);
        coachView.e(191);
        if (!coachView.o()) {
            foa.e(coachView, motion, 1);
            coachView.f().c(coachView.f().a() + 1);
        }
        coachView.c(coachView.f().b());
        if (!coachView.n()) {
            coachView.e(System.currentTimeMillis());
            sendEmptyMessage(153);
        } else if (!coachView.o()) {
            coachView.ag();
            coachView.e(System.currentTimeMillis() + 4000);
            sendEmptyMessageDelayed(153, 4000L);
        } else {
            LogUtil.c("Suggestion_CoachHandler", "Didn't get connection");
        }
        if (coachView.r() != null) {
            coachView.r().onMotionStart(motion, coachView.f().b());
        }
    }
}
