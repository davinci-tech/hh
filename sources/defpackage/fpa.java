package defpackage;

import android.content.Context;
import android.media.AudioManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.watchface.videoedit.presenter.PresenterUtils;

/* loaded from: classes4.dex */
public class fpa {
    public static int c() {
        return 5894;
    }

    public static boolean c(boolean z) {
        Context context = BaseApplication.getContext();
        boolean z2 = false;
        if (context == null) {
            LogUtil.h("Suggestion_LongCoachSoundHelper", "isMuteAudioFocus() context is null.");
            return false;
        }
        if (context.getSystemService(PresenterUtils.AUDIO) instanceof AudioManager) {
            AudioManager audioManager = (AudioManager) context.getSystemService(PresenterUtils.AUDIO);
            if (!z ? audioManager.abandonAudioFocus(null) == 1 : audioManager.requestAudioFocus(null, 3, 1) == 1) {
                z2 = true;
            }
        }
        LogUtil.a("Suggestion_LongCoachSoundHelper", "isMuteAudioFocus() pauseMusic bMute=", Boolean.valueOf(z), " result=", Boolean.valueOf(z2));
        return z2;
    }
}
