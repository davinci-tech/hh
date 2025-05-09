package defpackage;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.media.MediaPlayer;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.health.R;
import com.huawei.health.marketing.views.audition.AuditionPlayerListener;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.watchface.videoedit.presenter.PresenterUtils;
import health.compact.a.ReleaseLogUtil;
import java.io.IOException;
import java.util.List;

/* loaded from: classes3.dex */
public class ele {
    private static volatile ele e;

    /* renamed from: a, reason: collision with root package name */
    private AudioAttributes f12078a;
    private String b;
    private AudioManager c;
    private AuditionPlayerListener d;
    private int g;
    private boolean i;
    private AudioFocusRequest j;
    private List<String> l;
    private MediaPlayer m;
    private final AudioManager.OnAudioFocusChangeListener h = new b();
    private Context f = BaseApplication.e();

    private ele() {
        j();
    }

    public static ele c() {
        if (e == null) {
            synchronized (ele.class) {
                if (e == null) {
                    e = new ele();
                }
            }
        }
        return e;
    }

    public void d(AuditionPlayerListener auditionPlayerListener) {
        this.d = auditionPlayerListener;
    }

    public int e() {
        MediaPlayer mediaPlayer = this.m;
        if (mediaPlayer == null) {
            return 0;
        }
        return mediaPlayer.getCurrentPosition();
    }

    public void d(int i) {
        if (this.m == null) {
            return;
        }
        LogUtil.a("SleepAuditionHelper", "seekTo progress == ", Integer.valueOf(i));
        this.m.seekTo(i);
    }

    public boolean d() {
        MediaPlayer mediaPlayer = this.m;
        if (mediaPlayer == null) {
            return false;
        }
        return mediaPlayer.isPlaying();
    }

    public void c(String str, List<String> list) {
        this.b = str;
        this.l = list;
        LogUtil.a("SleepAuditionHelper", "resetPlayUrlListAndId mAuditionId == ", str);
        e(0);
        l();
    }

    public String b() {
        return this.b;
    }

    public void f() {
        LogUtil.a("SleepAuditionHelper", "stopAudio()");
        MediaPlayer mediaPlayer = this.m;
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            this.m.pause();
            this.m.stop();
        }
        g();
        this.b = "";
    }

    public void a() {
        if (this.m.isPlaying()) {
            this.m.pause();
            AuditionPlayerListener auditionPlayerListener = this.d;
            if (auditionPlayerListener != null) {
                auditionPlayerListener.onPlayerPause();
                return;
            }
            return;
        }
        this.m.start();
        l();
        AuditionPlayerListener auditionPlayerListener2 = this.d;
        if (auditionPlayerListener2 != null) {
            auditionPlayerListener2.onPlayerStart();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        if (this.m.isPlaying()) {
            this.m.pause();
            AuditionPlayerListener auditionPlayerListener = this.d;
            if (auditionPlayerListener != null) {
                auditionPlayerListener.onPlayerPause();
            }
        }
    }

    private void e(int i) {
        if (i < 0) {
            this.g = this.l.size() - 1;
        } else if (i > this.l.size() - 1) {
            this.b = "";
            return;
        }
        if (this.m == null) {
            MediaPlayer mediaPlayer = new MediaPlayer();
            this.m = mediaPlayer;
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: eld
                @Override // android.media.MediaPlayer.OnCompletionListener
                public final void onCompletion(MediaPlayer mediaPlayer2) {
                    ele.this.aqy_(mediaPlayer2);
                }
            });
            this.m.setAudioStreamType(3);
        }
        if (this.m.isPlaying()) {
            this.m.pause();
            this.m.stop();
        }
        try {
            this.m.reset();
            this.m.setDataSource(this.l.get(this.g));
            this.m.setOnPreparedListener(new MediaPlayer.OnPreparedListener() { // from class: elf
                @Override // android.media.MediaPlayer.OnPreparedListener
                public final void onPrepared(MediaPlayer mediaPlayer2) {
                    ele.this.aqz_(mediaPlayer2);
                }
            });
            this.m.prepare();
        } catch (IOException | IllegalStateException e2) {
            LogUtil.b("SleepAuditionHelper", "changeMusic err = ", ExceptionUtils.d(e2));
            nrh.d(this.f, "play error");
        }
    }

    /* synthetic */ void aqy_(MediaPlayer mediaPlayer) {
        LogUtil.a("SleepAuditionHelper", "onCompletion");
        if (this.g >= this.l.size() - 1) {
            this.b = "";
            AuditionPlayerListener auditionPlayerListener = this.d;
            if (auditionPlayerListener != null) {
                auditionPlayerListener.onCompletion(mediaPlayer);
            } else {
                ObserverManagerUtil.c("MARKETING_AUDITION_BTN_REFRESH", Integer.valueOf(R.drawable._2131430254_res_0x7f0b0b6e));
                ObserverManagerUtil.e("MARKETING_AUDITION_BTN_REFRESH");
                LogUtil.a("SleepAuditionHelper", "all completed, unregisterObserver");
            }
            LogUtil.a("SleepAuditionHelper", "all completed");
            return;
        }
        int i = this.g + 1;
        this.g = i;
        e(i);
    }

    /* synthetic */ void aqz_(MediaPlayer mediaPlayer) {
        AuditionPlayerListener auditionPlayerListener = this.d;
        if (auditionPlayerListener != null) {
            auditionPlayerListener.onPrepared(mediaPlayer);
        }
        this.m.start();
    }

    private void j() {
        Object systemService = this.f.getSystemService(PresenterUtils.AUDIO);
        if (!(systemService instanceof AudioManager)) {
            LogUtil.a("SleepAuditionHelper", "playSound object invalid type");
        } else {
            this.c = (AudioManager) systemService;
        }
    }

    private boolean l() {
        if (this.i) {
            return true;
        }
        if (this.c == null) {
            LogUtil.b("SleepAuditionHelper", "AudioManager is null, request system service failed...");
            return false;
        }
        if (this.j == null && this.f12078a == null) {
            this.f12078a = aqx_();
            this.j = new AudioFocusRequest.Builder(1).setAudioAttributes(this.f12078a).setWillPauseWhenDucked(true).setOnAudioFocusChangeListener(this.h).build();
        }
        int requestAudioFocus = this.c.requestAudioFocus(this.j);
        boolean z = requestAudioFocus == 1;
        if (z) {
            LogUtil.a("SleepAuditionHelper", "request audio focus success");
        } else {
            ReleaseLogUtil.a("SleepAuditionHelper", "request audio focus failed, result: " + requestAudioFocus);
        }
        return z;
    }

    private AudioAttributes aqx_() {
        return new AudioAttributes.Builder().setUsage(1).setContentType(2).build();
    }

    private void g() {
        LogUtil.a("SleepAuditionHelper", "abandonAudioFocus");
        AudioFocusRequest audioFocusRequest = this.j;
        if (audioFocusRequest != null) {
            this.c.abandonAudioFocusRequest(audioFocusRequest);
        } else {
            LogUtil.a("SleepAuditionHelper", "abandonAudioFocus has released before");
        }
    }

    class b implements AudioManager.OnAudioFocusChangeListener {
        private b() {
        }

        @Override // android.media.AudioManager.OnAudioFocusChangeListener
        public void onAudioFocusChange(int i) {
            ReleaseLogUtil.b("SleepAuditionHelper", "audio focus changed, status: " + i);
            if (i == -2) {
                ele.this.i = false;
                ele.this.i();
            } else if (i != -1) {
                if (i != 1) {
                    return;
                }
                ele.this.i = true;
            } else {
                ele.this.i = false;
                if (ele.this.m.isPlaying()) {
                    ObserverManagerUtil.c("MARKETING_AUDITION_BTN_REFRESH", Integer.valueOf(R.drawable._2131430254_res_0x7f0b0b6e));
                    ele.this.a();
                }
            }
        }
    }
}
