package defpackage;

import android.os.Message;
import com.huawei.health.suggestion.ui.fitness.module.LongCoachView;
import com.huawei.health.suggestion.ui.fitness.module.Motion;
import com.huawei.health.suggestion.util.StaticHandler;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.VideoSegment;
import java.text.SimpleDateFormat;

/* loaded from: classes8.dex */
public class fox extends StaticHandler<LongCoachView> {
    private int d;

    public fox(LongCoachView longCoachView) {
        super(longCoachView);
        this.d = 0;
    }

    @Override // com.huawei.health.suggestion.util.StaticHandler
    /* renamed from: aCk_, reason: merged with bridge method [inline-methods] */
    public void handleMessage(LongCoachView longCoachView, Message message) {
        if (message == null) {
            LogUtil.h("Suggestion_LongCoachHandler", "handleMessage msg == null");
            return;
        }
        if (longCoachView == null || longCoachView.b() == null || longCoachView.b().c() == -100) {
            LogUtil.h("Suggestion_LongCoachHandler", "handleMessage coachView is null");
            return;
        }
        if (message.what == 259 && longCoachView.g() != null && longCoachView.g().aCq_() != null) {
            int currentPosition = longCoachView.g().aCq_().getCurrentPosition();
            int calorieStartTime = longCoachView.getCalorieStartTime();
            longCoachView.ay();
            int duration = longCoachView.g().aCq_().getDuration();
            int abs = Math.abs(currentPosition - this.d);
            longCoachView.d(currentPosition);
            if (longCoachView.am()) {
                longCoachView.setSeekVideoFinish();
                abs = 1000;
            }
            longCoachView.f(abs);
            longCoachView.b(abs);
            if (currentPosition >= calorieStartTime * 1000) {
                longCoachView.b(abs, duration);
            }
            this.d = currentPosition;
            if (currentPosition > 0) {
                longCoachView.getLongMediaProgress().setProgress(currentPosition);
                longCoachView.q().setText(new SimpleDateFormat("mm:ss").format(Integer.valueOf(currentPosition)));
            }
            sendEmptyMessageDelayed(259, 1000L);
            if (duration != 0 && longCoachView.getLongExplanationVideoWatchTime() / (duration * 1.0f) > 0.3f) {
                longCoachView.d(true);
            }
        }
        if (message.what == 10001) {
            longCoachView.ab();
            LogUtil.a("Suggestion_LongCoachHandler", "handleMessage closeImgAction");
        } else {
            aCi_(longCoachView, message);
        }
    }

    private void aCi_(LongCoachView longCoachView, Message message) {
        if (message == null) {
            LogUtil.h("Suggestion_LongCoachHandler", "checkCoachMotion msg == null");
            return;
        }
        if (longCoachView == null || longCoachView.b() == null) {
            LogUtil.h("Suggestion_LongCoachHandler", "checkCoachMotion coachView is null");
            return;
        }
        if (longCoachView.o() != null) {
            int b = longCoachView.b().b();
            if (koq.b(longCoachView.o(), b)) {
                LogUtil.h("Suggestion_LongCoachHandler", "coachView.acquireCoachState().acquireCurrMotion() is out of Bounds");
                return;
            }
            Motion motion = longCoachView.o().get(b);
            if (motion != null) {
                aCj_(longCoachView, message, motion);
            }
        }
    }

    private void aCj_(LongCoachView longCoachView, Message message, Motion motion) {
        int i = message.what;
        if (i != 251) {
            if (i == 257) {
                longCoachView.s();
                if (longCoachView.i() != null) {
                    longCoachView.i().onMotionOver(motion, longCoachView.b().b());
                }
                fpb.a(longCoachView, motion);
                longCoachView.a().setVisibility(8);
                return;
            }
            if (i != 259) {
                LogUtil.h("Suggestion_LongCoachHandler", "MediaWhat unexpected");
                return;
            }
        }
        if (longCoachView.g() == null || longCoachView.g().aCq_() == null) {
            return;
        }
        int currentPosition = longCoachView.g().aCq_().getCurrentPosition();
        e(longCoachView, motion, currentPosition);
        longCoachView.getLongMediaProgress().setProgress(currentPosition);
        longCoachView.a().setIsShowBottomProgress(true);
    }

    private void e(LongCoachView longCoachView, Motion motion, int i) {
        if (longCoachView == null || motion == null) {
            LogUtil.h("Suggestion_LongCoachHandler", "coachView or currMotion is null");
            return;
        }
        if (koq.b(longCoachView.getVideoSegments(), 0)) {
            LogUtil.h("Suggestion_LongCoachHandler", "getVideoSegments is empty");
            return;
        }
        if (longCoachView.getVideoSegments().get(0) == null) {
            LogUtil.h("Suggestion_LongCoachHandler", "VideoSegment is null");
        } else if (i >= ((int) longCoachView.getVideoSegments().get(0).getStartTime())) {
            b(longCoachView, motion, i);
        } else {
            sendEmptyMessage(251);
        }
    }

    private void b(LongCoachView longCoachView, Motion motion, int i) {
        if (motion == null) {
            LogUtil.h("Suggestion_LongCoachHandler", "handleTrainingState currMotion == null");
            return;
        }
        longCoachView.b().c(251);
        longCoachView.j(251);
        int b = longCoachView.b().b();
        if (koq.b(longCoachView.getVideoSegments(), b)) {
            LogUtil.h("Suggestion_LongCoachHandler", "getVideoSegments is OutOfBounds");
            return;
        }
        VideoSegment videoSegment = longCoachView.getVideoSegments().get(b);
        if (videoSegment == null) {
            return;
        }
        long endTime = videoSegment.getEndTime();
        foy.d(longCoachView, motion);
        if (i >= endTime) {
            removeMessages(251);
            longCoachView.x();
        } else {
            sendEmptyMessage(251);
        }
    }
}
