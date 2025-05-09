package defpackage;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.ImageView;
import com.huawei.health.R;
import com.huawei.health.suggestion.ui.fitness.module.LongCoachView;
import com.huawei.health.suggestion.ui.fitness.module.Motion;
import com.huawei.health.suggestion.ui.view.BrightnessOrVolumeProgressPlus;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.watchface.videoedit.presenter.PresenterUtils;
import health.compact.a.EnvironmentInfo;

/* loaded from: classes4.dex */
public class foy {

    /* renamed from: a, reason: collision with root package name */
    private static int f12591a;

    public static boolean d(int i) {
        return i == R.id.sug_coach_set_iv_continue || i == R.id.sug_coach_set_voice_ok;
    }

    public static boolean e(boolean z, int i, boolean z2) {
        return z && !z2 && (i != -100 && i != 192);
    }

    public static void d(LongCoachView longCoachView, Motion motion) {
        if (longCoachView == null || motion == null) {
            LogUtil.h("Suggestion_LongCoachViewUIHelper", "changeProgress coachView == null || currMotion == null");
            return;
        }
        if (koq.b(motion.getVideoSegments(), 0)) {
            LogUtil.h("Suggestion_LongCoachViewUIHelper", "getVideoSegments is empty");
        } else if (motion.getVideoSegments().get(0) == null) {
            LogUtil.h("Suggestion_LongCoachViewUIHelper", "getVideoSegments(0) is null");
        } else {
            if (motion.getVideoSegments().get(0).getDuration() < longCoachView.e() / 1000) {
                return;
            }
            b(longCoachView, motion);
        }
    }

    private static void b(LongCoachView longCoachView, Motion motion) {
        if ("timer".equals(motion.acquireMotionType())) {
            longCoachView.setTimeView((int) (longCoachView.e() / 1000), -1, true);
        } else if (longCoachView.c()) {
            longCoachView.setTimeView((int) ((longCoachView.e() / 1000) - 1), -1, false);
        } else {
            longCoachView.setTimeView((int) (longCoachView.e() / 1000), -1, false);
        }
    }

    public static void a(LongCoachView longCoachView, float f) {
        if (longCoachView == null) {
            LogUtil.h("Suggestion_LongCoachViewUIHelper", "rateEffective coachView == null");
            return;
        }
        if (f <= 0.6d || !(longCoachView.aDs_().getTag() instanceof Boolean)) {
            return;
        }
        if (((Boolean) longCoachView.aDs_().getTag()).booleanValue()) {
            longCoachView.d(true);
        } else {
            longCoachView.aDs_().setTag(true);
        }
    }

    public static boolean c(LongCoachView longCoachView, int i) {
        if (longCoachView != null) {
            return longCoachView.i() != null && i == R.id.sug_coach_set_iv_stop;
        }
        LogUtil.h("Suggestion_LongCoachViewUIHelper", "isFinish coachView == null");
        return false;
    }

    public static void e(LongCoachView longCoachView, int i) {
        if (longCoachView == null) {
            LogUtil.h("Suggestion_LongCoachViewUIHelper", "preNextChange coachView == null");
            return;
        }
        if (longCoachView.b().b() == 0) {
            fnz.aBH_(longCoachView.aDr_(), 0.4f, 2);
            return;
        }
        if (longCoachView.b().b() == 1) {
            fnz.aBH_(longCoachView.aDr_(), 1.0f, 0);
            return;
        }
        if (longCoachView.b().b() == i - 1) {
            fnz.aBH_(longCoachView.aDq_(), 0.4f, 2);
        } else if (longCoachView.b().b() == i - 2) {
            longCoachView.aDq_().setAlpha(1.0f);
            fnz.aBH_(longCoachView.aDq_(), 1.0f, 0);
        } else {
            LogUtil.c("Suggestion_LongCoachViewUIHelper", "CurrMotion unexpected");
        }
    }

    public static void d(LongCoachView longCoachView, float f, Context context) {
        if (longCoachView == null || context == null) {
            LogUtil.h("Suggestion_LongCoachViewUIHelper", "coachView or context is null");
            return;
        }
        if (!(context instanceof Activity)) {
            LogUtil.h("Suggestion_LongCoachViewUIHelper", "setBrightness getContext() not instanceof Activity");
            return;
        }
        Activity activity = (Activity) context;
        WindowManager.LayoutParams attributes = activity.getWindow().getAttributes();
        if (attributes.screenBrightness == -1.0f) {
            attributes.screenBrightness = fpf.c(context) / 255.0f;
        }
        attributes.screenBrightness += f;
        if (attributes.screenBrightness > 1.0d) {
            attributes.screenBrightness = 1.0f;
        } else if (attributes.screenBrightness < 0.01d) {
            attributes.screenBrightness = 0.01f;
        } else {
            LogUtil.c("Suggestion_LongCoachViewUIHelper", "screenBrightness is normal value");
        }
        BrightnessOrVolumeProgressPlus brightOrVolumeProgressPlus = longCoachView.getBrightOrVolumeProgressPlus();
        if (brightOrVolumeProgressPlus == null) {
            LogUtil.h("Suggestion_LongCoachViewUIHelper", "setBrightness brightnessOrVolume is null");
            return;
        }
        brightOrVolumeProgressPlus.setProgressMax(1.0f);
        brightOrVolumeProgressPlus.setProgress(attributes.screenBrightness);
        brightOrVolumeProgressPlus.a(R.drawable._2131430555_res_0x7f0b0c9b);
        brightOrVolumeProgressPlus.d(context.getResources().getString(R.string._2130845621_res_0x7f021fb5));
        activity.getWindow().setAttributes(attributes);
    }

    public static void aCo_(Context context, ImageView imageView) {
        if (context == null || imageView == null) {
            LogUtil.h("Suggestion_LongCoachViewUIHelper", "context or voiceImageView is null");
            return;
        }
        if (!(context.getSystemService(PresenterUtils.AUDIO) instanceof AudioManager)) {
            LogUtil.h("Suggestion_LongCoachViewUIHelper", "toMuteOrOpenSound getContext().getSystemService(AUDIO_SERVICE) not instanceof AudioManager");
            return;
        }
        AudioManager audioManager = (AudioManager) context.getSystemService(PresenterUtils.AUDIO);
        if (audioManager == null) {
            LogUtil.h("Suggestion_LongCoachViewUIHelper", "muteOrOpenSound audioManager is null");
            return;
        }
        int streamVolume = audioManager.getStreamVolume(3);
        if (streamVolume <= 0) {
            audioManager.setStreamVolume(3, f12591a, 0);
            imageView.setImageResource(R.drawable._2131431660_res_0x7f0b10ec);
            jcf.bEz_(imageView, nsf.j(R.string._2130850643_res_0x7f023353));
        } else {
            audioManager.setStreamVolume(3, 0, 0);
            imageView.setImageResource(R.drawable._2131431659_res_0x7f0b10eb);
            f12591a = streamVolume;
            jcf.bEz_(imageView, nsf.j(R.string._2130850644_res_0x7f023354));
        }
    }

    public static void aCn_(Context context, ImageView imageView) {
        if (context == null || imageView == null) {
            LogUtil.h("Suggestion_LongCoachViewUIHelper", "setDefaultVolume context or voiceImageView is null");
            return;
        }
        if (EnvironmentInfo.k()) {
            imageView.setVisibility(8);
            return;
        }
        if (!(context.getSystemService(PresenterUtils.AUDIO) instanceof AudioManager)) {
            LogUtil.h("Suggestion_LongCoachViewUIHelper", "setDefaultVolume getContext().getSystemService(AUDIO_SERVICE) not instanceof AudioManager");
            return;
        }
        AudioManager audioManager = (AudioManager) context.getSystemService(PresenterUtils.AUDIO);
        if (audioManager == null) {
            LogUtil.h("Suggestion_LongCoachViewUIHelper", "setDefaultVolume audioManager is null");
        } else if (audioManager.getStreamVolume(3) <= 0) {
            imageView.setImageResource(R.drawable._2131431659_res_0x7f0b10eb);
            jcf.bEz_(imageView, nsf.j(R.string._2130850644_res_0x7f023354));
        } else {
            imageView.setImageResource(R.drawable._2131431660_res_0x7f0b10ec);
            jcf.bEz_(imageView, nsf.j(R.string._2130850643_res_0x7f023353));
        }
    }

    public static void aCl_(LongCoachView longCoachView, Handler handler) {
        if (longCoachView == null || handler == null) {
            LogUtil.h("Suggestion_LongCoachViewUIHelper", "doContinue coachView == null || handler == null");
            return;
        }
        longCoachView.g().videoContinue();
        if (longCoachView.b().c() == 251) {
            if (longCoachView.c() && !longCoachView.f()) {
                longCoachView.aa();
                aCm_(longCoachView, handler, true);
                return;
            } else if (!longCoachView.c()) {
                aCm_(longCoachView, handler, false);
                return;
            } else {
                LogUtil.h("Suggestion_LongCoachViewUIHelper", "Unexpected continue situation");
                return;
            }
        }
        LogUtil.h("Suggestion_LongCoachViewUIHelper", "Unexpected train station");
    }

    public static void c(LongCoachView longCoachView, Motion motion) {
        if (longCoachView == null || motion == null) {
            LogUtil.h("Suggestion_LongCoachViewUIHelper", "onChangeListener coachView == null || finishMotion == null");
        } else if (longCoachView.i() != null) {
            longCoachView.i().onMotionChanged(motion, longCoachView.b().b());
        }
    }

    private static void aCm_(LongCoachView longCoachView, Handler handler, boolean z) {
        if (longCoachView == null || handler == null) {
            LogUtil.h("Suggestion_LongCoachViewUIHelper", "doContinue coachView == null || handler == null");
            return;
        }
        if (z) {
            handler.sendEmptyMessageDelayed(259, 4000L);
        } else {
            handler.sendEmptyMessage(259);
        }
        if (longCoachView.ai()) {
            if (z) {
                handler.sendEmptyMessageDelayed(251, 4000L);
            } else {
                handler.sendEmptyMessage(251);
            }
        }
    }
}
