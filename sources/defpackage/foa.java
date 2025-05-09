package defpackage;

import android.media.MediaPlayer;
import android.os.Handler;
import com.huawei.health.R;
import com.huawei.health.suggestion.h5pro.AudioConstant;
import com.huawei.health.suggestion.ui.fitness.helper.MediaHelper;
import com.huawei.health.suggestion.ui.fitness.module.CoachView;
import com.huawei.health.suggestion.ui.fitness.module.Motion;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;

/* loaded from: classes4.dex */
public class foa {
    private static void aBD_(CoachView coachView, MediaPlayer mediaPlayer) {
        if (mediaPlayer == null) {
            LogUtil.b("Suggestion_CoachViewHelper", "player == null");
            return;
        }
        if (mediaPlayer.isPlaying()) {
            coachView.a().e(coachView.ac() * 0.0f);
            coachView.setTag(true);
            LogUtil.a("Suggestion_CoachViewHelper", "Playing guide voice, playing beat, reduce beat voice volume");
        } else if ((coachView.getTag() instanceof Boolean) && ((Boolean) coachView.getTag()).booleanValue()) {
            coachView.a().e(coachView.ac());
            LogUtil.a("Suggestion_CoachViewHelper", "restore beat voice volume");
            coachView.setTag(false);
        }
    }

    public static void d(CoachView coachView, Motion motion, int i) {
        if (coachView == null || motion == null) {
            LogUtil.h("Suggestion_CoachViewHelper", "tempBeatAction coachView == null || currMotion == null");
            return;
        }
        if (i <= motion.acquireRepeat()) {
            if (!"hotbody".equals(motion.acquireMotionType())) {
                c(coachView, coachView.b() / 1000);
                e(coachView, motion, i);
            }
            coachView.f().c(i);
            fnz.d(coachView, motion);
            return;
        }
        if (i == motion.acquireRepeat() + 1 && coachView.n()) {
            coachView.f().c(i);
        } else {
            LogUtil.c("Suggestion_CoachViewHelper", "tempBeat unexpected");
        }
    }

    public static int e(CoachView coachView, Motion motion) {
        if (coachView == null || motion == null || motion.acquireInterval() == 0) {
            return 0;
        }
        if ("timer".equals(motion.acquireMotionType())) {
            return coachView.b() / motion.acquireInterval();
        }
        return (coachView.b() / motion.acquireInterval()) + 1;
    }

    private static void a(CoachView coachView, Motion motion, int i) {
        if ("timer".equals(motion.acquireMotionType())) {
            b(coachView, motion, i);
        } else {
            aBD_(coachView, coachView.m().aCq_());
            e(coachView);
        }
    }

    private static void e(CoachView coachView) {
        coachView.a().nextBeat();
    }

    private static void b(CoachView coachView, Motion motion, int i) {
        if (i == motion.acquireRepeat() - 7) {
            LogUtil.a("Suggestion_CoachViewHelper", "Playing hold on for 5 seconds more");
            coachView.a().e();
            return;
        }
        if (i < motion.acquireRepeat() - 6) {
            coachView.a().timer();
            return;
        }
        if (i == motion.acquireRepeat() - 5) {
            LogUtil.a("Suggestion_CoachViewHelper", "Playing last 5 seconds");
            coachView.a().c();
        } else if (i > motion.acquireRepeat() - 5) {
            coachView.a().next();
        } else {
            LogUtil.c("Suggestion_CoachViewHelper", "tempBeat unexpected");
        }
    }

    public static void e(CoachView coachView, Motion motion, int i) {
        if (coachView == null || motion == null) {
            LogUtil.h("Suggestion_CoachViewHelper", "toBroadcast coachView == null || currMotion == null");
        } else if (coachView.f().a() < i) {
            LogUtil.c("Suggestion_CoachViewHelper", "Telling beat number: ", Long.valueOf(System.currentTimeMillis()));
            LogUtil.c("Suggestion_CoachViewHelper", "ActionTrainTime:", Integer.valueOf(coachView.b()), "-----currbeat:", Integer.valueOf(coachView.f().a()), "---motionInverval:", Integer.valueOf(motion.acquireInterval()), "----clipTime", Long.valueOf(coachView.i()), " sec:", Integer.valueOf(coachView.b() / 1000));
            a(coachView, motion, i);
        }
    }

    public static boolean a(CoachView coachView, Motion motion) {
        if (coachView != null && motion != null) {
            return "hotbody".equals(motion.acquireMotionType()) && coachView.a().o();
        }
        LogUtil.h("Suggestion_CoachViewHelper", "checkIsplaying coachView == null || currMotion == null");
        return false;
    }

    private static void c(CoachView coachView, int i) {
        if (i != coachView.d()) {
            coachView.d(i);
            if (b(coachView)) {
                LogUtil.a("Suggestion_CoachViewHelper", "Playing guide voice base on the current seconds-time: ", Integer.valueOf(i));
                aBD_(coachView, coachView.m().aCq_());
                coachView.m().next();
            }
        }
    }

    private static boolean b(CoachView coachView) {
        return ffy.c(coachView, coachView.m(), coachView.m().aCq_()) && coachView.j().contains(Integer.valueOf(coachView.d())) && !coachView.m().o();
    }

    public static void e(CoachView coachView, int i, int i2) {
        if (coachView == null) {
            LogUtil.h("Suggestion_CoachViewHelper", "needChangeProgress coachView == null");
            return;
        }
        if (i2 == R.id.sug_coach_set_pb_count) {
            float f = (i * 1.0f) / 1000.0f;
            coachView.a().e(f);
            coachView.c(f);
            coachView.ad().e(f);
            coachView.ad().playBeatNum(1);
            return;
        }
        if (i2 == R.id.sug_coach_set_pb_guide) {
            float f2 = (i * 1.0f) / 1000.0f;
            coachView.m().e(f2);
            coachView.ad().e(f2);
            coachView.ad().c(AudioConstant.GUIDE_VOLUME_CHANGE_HINT_CODE);
            return;
        }
        coachView.c().e((i * 0.5f) / 1000.0f);
    }

    public static void d(MediaHelper mediaHelper, boolean z, float f) {
        if (mediaHelper == null) {
            LogUtil.h("Suggestion_CoachViewHelper", "setVoice mediaHelper == null");
        } else if (z) {
            mediaHelper.e(f);
        } else {
            mediaHelper.e(0.0f);
        }
    }

    public static boolean a(CoachView coachView) {
        return (coachView == null || coachView.m() == null || coachView.s() == null) ? false : true;
    }

    public static void aBE_(CoachView coachView, Handler handler) {
        if (coachView == null || handler == null) {
            LogUtil.h("Suggestion_CoachViewHelper", "doContinue coachView == null || handler == null");
            return;
        }
        coachView.s().videoContinue();
        if (coachView.f().c() == 193) {
            coachView.g().d().c();
            coachView.c().videoContinue();
            return;
        }
        if (coachView.f().c() == 191) {
            if (coachView.n() && !coachView.o()) {
                coachView.ag();
                coachView.e(System.currentTimeMillis() + 4000);
                handler.sendEmptyMessageDelayed(153, 4000L);
            } else if (!coachView.n()) {
                coachView.e(System.currentTimeMillis());
                handler.sendEmptyMessage(153);
            } else {
                LogUtil.c("Suggestion_CoachViewHelper", "Unexpected continue situation");
            }
            coachView.a().a();
            coachView.m().voiceGuideContinue();
            coachView.c().videoContinue();
            return;
        }
        if (coachView.f().c() == 190) {
            coachView.m().voiceGuideContinue();
            coachView.c().videoContinue();
        } else {
            coachView.c().videoContinue();
        }
    }

    public static void aBF_(CoachView coachView, Handler handler) {
        if (coachView == null || handler == null) {
            LogUtil.h("Suggestion_CoachViewHelper", "isBeating coachView == null || handler == null");
            return;
        }
        if (ffy.c(coachView.a().aCq_(), coachView.m().aCq_())) {
            if (coachView.f().c() == 191) {
                coachView.a().m();
                handler.removeMessages(153);
            } else if (coachView.f().c() == 190) {
                coachView.m().m();
            } else {
                LogUtil.c("Suggestion_CoachViewHelper", "Coach station is not BEATING or GUIDING");
            }
        }
    }

    public static void c(CoachView coachView, Motion motion) {
        if (coachView == null || motion == null) {
            LogUtil.h("Suggestion_CoachViewHelper", "onChangeListener coachView == null || finishMotion == null");
        } else if (coachView.r() != null) {
            coachView.r().onMotionChanged(motion, coachView.f().b());
        }
    }

    public static float c(Motion motion, int i) {
        if (CommonUtil.c(motion.getTotalBeats())) {
            return 0.0f;
        }
        float totalBeats = (i * 1.0f) / motion.getTotalBeats();
        if (totalBeats > 1.0f) {
            return 1.0f;
        }
        return totalBeats;
    }
}
