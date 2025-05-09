package defpackage;

import android.media.MediaPlayer;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes8.dex */
public class fek {
    private static fek d;
    private static final Object e = new Object();
    private MediaPlayer c;

    private fek() {
    }

    public static fek c() {
        fek fekVar;
        synchronized (e) {
            if (d == null) {
                d = new fek();
            }
            fekVar = d;
        }
        return fekVar;
    }

    public boolean d(int i) {
        return d(i, null);
    }

    private boolean d(final int i, final IBaseResponseCallback iBaseResponseCallback) {
        jdx.b(new Runnable() { // from class: fek.3
            @Override // java.lang.Runnable
            public void run() {
                fek.this.c = MediaPlayer.create(BaseApplication.e(), i);
                if (fek.this.c != null) {
                    try {
                        fek.this.c.start();
                    } catch (IllegalStateException e2) {
                        LogUtil.h("Track_VoicePlayManager", "playVoice IllegalStateException ", e2.getMessage());
                    }
                    fek.this.c.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: fek.3.3
                        @Override // android.media.MediaPlayer.OnCompletionListener
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            LogUtil.a("Track_VoicePlayManager", "playVoice onCompletion ");
                            if (fek.this.c != null) {
                                fek.this.c.stop();
                                fek.this.c.reset();
                                fek.this.c.release();
                            }
                            fek.this.c = null;
                            if (iBaseResponseCallback != null) {
                                iBaseResponseCallback.onResponse(0, new Object());
                            }
                        }
                    });
                    return;
                }
                LogUtil.h("Track_VoicePlayManager", "playVoice fail");
            }
        });
        return true;
    }
}
