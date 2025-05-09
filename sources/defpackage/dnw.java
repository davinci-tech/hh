package defpackage;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.R;
import com.huawei.health.functionsetcard.dailymoment.DailyMomentCardAdapter;
import com.huawei.health.functionsetcard.dailymoment.operation.IGetTitleCallback;
import com.huawei.health.marketing.datatype.SingleDailyMomentContent;
import com.huawei.health.messagecenter.model.CommonUtil;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcloudmodel.callback.GrsQueryCallback;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.utils.httputils.pluginoperation.HttpResCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.OpAnalyticsConstants;
import com.huawei.pluginachievement.manager.model.KakaConstants;
import com.huawei.ui.commonui.progressindicator.HealthProgressIndicator;
import com.huawei.watchface.videoedit.presenter.PresenterUtils;
import defpackage.dnw;
import health.compact.a.GRSManager;
import java.io.IOException;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class dnw {
    private Timer aa;
    private AudioAttributes f;
    private String g;
    private AudioManager i;
    private AudioManager.OnAudioFocusChangeListener j;
    private long k;
    private Context l;
    private DailyMomentCardAdapter m;
    private SingleDailyMomentContent n;
    private long o;
    private int p;
    private boolean s;
    private long t;
    private HealthProgressIndicator v;
    private boolean w;
    private long x;
    private ImageView y;
    private ValueAnimator z;
    private final Object b = new Object();
    private boolean h = false;

    /* renamed from: a, reason: collision with root package name */
    private boolean f11738a = false;
    private volatile boolean e = false;
    private volatile boolean c = false;
    private volatile boolean d = false;
    private boolean q = false;
    private Handler r = new Handler(Looper.getMainLooper()) { // from class: dnw.5
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what != 1) {
                return;
            }
            dnw.this.i();
        }
    };
    private MediaPlayer u = new MediaPlayer();

    private int e(int i, int i2) {
        if (i2 == 0) {
            return 0;
        }
        return (int) ((i / i2) * 100.0f);
    }

    public dnw(View view, SingleDailyMomentContent singleDailyMomentContent, int i, DailyMomentCardAdapter dailyMomentCardAdapter) {
        this.l = view.getContext();
        this.n = singleDailyMomentContent;
        this.g = singleDailyMomentContent.getButtonLinkValue();
        this.p = i;
        this.y = (ImageView) view.findViewById(R.id.daily_moment_media_button);
        Object systemService = this.l.getSystemService(PresenterUtils.AUDIO);
        if (systemService instanceof AudioManager) {
            this.i = (AudioManager) systemService;
        }
        this.m = dailyMomentCardAdapter;
        this.v = (HealthProgressIndicator) view.findViewById(R.id.daily_moment_media_indicator);
        this.s = false;
        e();
        a(singleDailyMomentContent);
        h();
    }

    public boolean d() {
        if (this.u == null) {
            return false;
        }
        LogUtil.a("DailyMomentMediaPlayer", "isAudioPlaying: " + this.d);
        return this.d;
    }

    private String b() {
        SingleDailyMomentContent singleDailyMomentContent = this.n;
        if (singleDailyMomentContent == null) {
            LogUtil.a("DailyMomentMediaPlayer", "getMusicTitle failed");
            return "";
        }
        if (singleDailyMomentContent.getThemeType() == 1) {
            return dnz.e(this.l, this.n, new IGetTitleCallback() { // from class: dnw.4
                @Override // com.huawei.health.functionsetcard.dailymoment.operation.IGetTitleCallback
                public void onFailure(int i, String str) {
                    LogUtil.a("DailyMomentMediaPlayer", "get title failed, errorCode: " + i);
                }

                @Override // com.huawei.health.functionsetcard.dailymoment.operation.IGetTitleCallback
                public void onSuccess(String str) {
                    if (str == null || str.length() <= 0) {
                        return;
                    }
                    LogUtil.a("DailyMomentMediaPlayer", "getMusicTitle success, title: " + str);
                    dob.b(str);
                }
            });
        }
        if (this.n.getThemeType() == 2) {
            return this.n.getCustomTheme();
        }
        LogUtil.h("DailyMomentMediaPlayer", "theme type is invalid");
        return "";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str) {
        final HashMap<String, String> a2 = doc.a(this.l);
        a2.put(CommonUtil.PAGE_TYPE, String.valueOf(1));
        a2.put("Id", str);
        final HashMap<String, String> b = doc.b(this.l);
        GRSManager.a(this.l).e("messageCenterUrl", new GrsQueryCallback() { // from class: dnw.9
            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackSuccess(String str2) {
                LogUtil.a("DailyMomentMediaPlayer", "message center key: " + str2);
                jei.d(str2 + "/messageCenter/getSleepAudioById", a2, b, new HttpResCallback() { // from class: dnw.9.4
                    @Override // com.huawei.hwcommonmodel.utils.httputils.pluginoperation.HttpResCallback
                    public void onFinished(int i, String str3) {
                        if (i != 200) {
                            dnw.this.n();
                            return;
                        }
                        LogUtil.a("DailyMomentMediaPlayer", "audio info: " + str3);
                        try {
                            String string = new JSONObject(str3).getJSONObject("sleepAudio").getString("audioUrl");
                            LogUtil.a("DailyMomentMediaPlayer", "audioUrl: " + string);
                            dnw.this.a(string);
                        } catch (JSONException unused) {
                            dnw.this.n();
                            LogUtil.a("DailyMomentMediaPlayer", "audio info has json exception");
                        }
                    }
                });
            }

            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackFail(int i) {
                LogUtil.a("DailyMomentMediaPlayer", "get message center url failed");
                dnw.this.n();
            }
        });
    }

    private void h() {
        ViewGroup.LayoutParams layoutParams = this.v.getLayoutParams();
        layoutParams.height = this.y.getDrawable().getIntrinsicHeight() + 12;
        layoutParams.width = this.y.getDrawable().getIntrinsicWidth() + 12;
        this.v.setLayoutParams(layoutParams);
        this.v.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        if (this.u == null || this.v == null) {
            return;
        }
        long currentPosition = this.o + r0.getCurrentPosition();
        if (currentPosition >= this.k && !this.q) {
            this.q = true;
            e(1.0f, 0.0f);
        }
        if (currentPosition >= this.k + OpAnalyticsConstants.H5_LOADING_DELAY) {
            LogUtil.a("DailyMomentMediaPlayer", "cycle play complete");
            this.u.seekTo(0);
            this.v.setProgress(0);
            a();
            return;
        }
        LogUtil.a("DailyMomentMediaPlayer", "currentPosition: " + this.u.getCurrentPosition() + "progress: " + this.v.getProgress() + " , TotalPlayDuration: " + currentPosition);
        this.v.setProgress(e(this.u.getCurrentPosition(), this.u.getDuration()));
    }

    private void e(final float f, float f2) {
        LogUtil.a("DailyMomentMediaPlayer", "beginFadeOut");
        ValueAnimator ofFloat = ValueAnimator.ofFloat(f, f2);
        this.z = ofFloat;
        ofFloat.setDuration(this.t);
        this.z.setInterpolator(new LinearInterpolator());
        this.z.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: dnw.10
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                Object animatedValue = valueAnimator.getAnimatedValue();
                if (!(animatedValue instanceof Float)) {
                    LogUtil.a("DailyMomentMediaPlayer", "getAnimatedValue failed");
                    return;
                }
                float floatValue = ((Float) animatedValue).floatValue();
                try {
                    if (dnw.this.u != null) {
                        dnw.this.u.setVolume(floatValue, floatValue);
                    }
                } catch (IllegalStateException unused) {
                    LogUtil.b("DailyMomentMediaPlayer", "set player volume exception");
                    dnw.this.z.cancel();
                }
            }
        });
        this.z.addListener(new Animator.AnimatorListener() { // from class: dnw.6
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                LogUtil.a("DailyMomentMediaPlayer", "onAnimationStart");
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                if (dnw.this.u != null) {
                    LogUtil.a("DailyMomentMediaPlayer", "onAnimationEnd");
                    MediaPlayer mediaPlayer = dnw.this.u;
                    float f3 = f;
                    mediaPlayer.setVolume(f3, f3);
                }
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
                if (dnw.this.u != null) {
                    LogUtil.a("DailyMomentMediaPlayer", "onAnimationCancel");
                    MediaPlayer mediaPlayer = dnw.this.u;
                    float f3 = f;
                    mediaPlayer.setVolume(f3, f3);
                }
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
                LogUtil.a("DailyMomentMediaPlayer", "onAnimationRepeat");
            }
        });
        this.z.start();
    }

    private void c(boolean z) {
        if (this.y == null) {
            return;
        }
        if (z) {
            this.d = true;
            this.y.setImageResource(R.drawable._2131430063_res_0x7f0b0aaf);
        } else {
            this.d = false;
            this.y.setImageResource(R.drawable._2131430045_res_0x7f0b0a9d);
        }
    }

    private void a(SingleDailyMomentContent singleDailyMomentContent) {
        if (this.y == null) {
            return;
        }
        c(false);
        this.y.setOnClickListener(new AnonymousClass8());
    }

    /* renamed from: dnw$8, reason: invalid class name */
    class AnonymousClass8 implements View.OnClickListener {
        static /* synthetic */ void a(int i, Object obj) {
        }

        AnonymousClass8() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            LogUtil.a("DailyMomentMediaPlayer", "mMediaButton is clicked");
            if (!dnw.this.s && dnw.this.u != null) {
                if (dnw.this.u.isPlaying()) {
                    dnw.this.i.abandonAudioFocus(dnw.this.j);
                    dnw.this.m.a();
                    dnw.this.f();
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                if (!LoginInit.getInstance(dnw.this.l).getIsLogined()) {
                    LoginInit.getInstance(dnw.this.l).browsingToLogin(new IBaseResponseCallback() { // from class: dny
                        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                        /* renamed from: onResponse */
                        public final void d(int i, Object obj) {
                            dnw.AnonymousClass8.a(i, obj);
                        }
                    }, "");
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                if (!dnw.this.e) {
                    if (!dnw.this.c) {
                        dnw.this.c = true;
                        dnw.this.m.d();
                        try {
                            dnw dnwVar = dnw.this;
                            dnwVar.c(dnwVar.g);
                        } catch (IllegalStateException unused) {
                            LogUtil.a("DailyMomentMediaPlayer", "mediaplayer prepareAsync failed!");
                            dnw.this.n();
                        }
                    }
                } else {
                    LogUtil.a("DailyMomentMediaPlayer", "audio is already prepared");
                    dnw.this.m.d();
                    dnw.this.i();
                    dnw.this.l();
                }
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        if (this.u == null) {
            return;
        }
        e(str);
        this.u.setOnErrorListener(new MediaPlayer.OnErrorListener() { // from class: dnw.7
            @Override // android.media.MediaPlayer.OnErrorListener
            public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                mediaPlayer.release();
                dnw.this.n();
                return true;
            }
        });
        this.u.setAudioAttributes(this.f);
        this.u.setOnPreparedListener(new MediaPlayer.OnPreparedListener() { // from class: dnw.12
            @Override // android.media.MediaPlayer.OnPreparedListener
            public void onPrepared(MediaPlayer mediaPlayer) {
                LogUtil.a("DailyMomentMediaPlayer", "prepared");
                dnw.this.e = true;
                if (dnw.this.m != null) {
                    dnw.this.m.b(dnw.this.p, dnw.this);
                }
                if (dnw.this.c) {
                    dnw.this.l();
                }
            }
        });
        this.u.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: dnw.13
            @Override // android.media.MediaPlayer.OnCompletionListener
            public void onCompletion(MediaPlayer mediaPlayer) {
                LogUtil.a("DailyMomentMediaPlayer", "complete");
                dnw.this.u.seekTo(0);
                dnw.this.i();
                dnw.this.o = SystemClock.elapsedRealtime() - dnw.this.x;
                if (!dnw.this.w || dnw.this.o >= 900000) {
                    dnw.this.a();
                    return;
                }
                LogUtil.a("DailyMomentMediaPlayer", "replay music, total play duration: " + dnw.this.o + " ms");
                dnw.this.u.start();
            }
        });
        this.u.prepareAsync();
    }

    private void e(String str) {
        try {
            this.u.setDataSource(str);
        } catch (IOException unused) {
            LogUtil.a("DailyMomentMediaPlayer", "initPlayer IOException");
            n();
        } catch (IllegalStateException unused2) {
            LogUtil.a("DailyMomentMediaPlayer", "initPlayer IllegalStateException");
            n();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        this.z.cancel();
        this.z.removeAllUpdateListeners();
        this.z.removeAllListeners();
        j();
        this.m.a();
        HashMap hashMap = new HashMap(16);
        hashMap.put("status", 2);
        hashMap.put(KakaConstants.SLEEP_MUSIC_DURATION, Long.valueOf(SystemClock.elapsedRealtime() - this.x));
        doa.e(this.l, AnalyticsValue.DAILY_MOMENT_11100308.value(), this.n, hashMap);
    }

    private void e() {
        this.f = new AudioAttributes.Builder().setUsage(1).setContentType(2).build();
        this.j = new AudioManager.OnAudioFocusChangeListener() { // from class: dnw.3
            @Override // android.media.AudioManager.OnAudioFocusChangeListener
            public void onAudioFocusChange(int i) {
                if (i == -3 || i == -2) {
                    synchronized (dnw.this.b) {
                        dnw.this.h = true;
                        dnw.this.f11738a = false;
                        dnw.this.f();
                    }
                    return;
                }
                if (i == -1) {
                    synchronized (dnw.this.b) {
                        dnw.this.h = false;
                        dnw.this.f11738a = false;
                        dnw.this.m.a();
                        dnw.this.f();
                    }
                    return;
                }
                if (i != 1) {
                    return;
                }
                if (dnw.this.h || dnw.this.f11738a) {
                    synchronized (dnw.this.b) {
                        dnw.this.h = false;
                        dnw.this.f11738a = false;
                        dnw.this.m();
                    }
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        AudioManager audioManager = this.i;
        if (audioManager == null) {
            return;
        }
        int requestAudioFocus = audioManager.requestAudioFocus(this.j, 3, 1);
        synchronized (this.b) {
            if (requestAudioFocus == 0) {
                this.m.a();
                LogUtil.c("DailyMomentMediaPlayer", "audiofocus request failed");
            } else if (requestAudioFocus == 1) {
                m();
            } else if (requestAudioFocus == 2) {
                this.m.a();
                this.f11738a = true;
            }
        }
    }

    private void k() {
        LogUtil.a("DailyMomentMediaPlayer", "startRefreshingProcess");
        Timer timer = new Timer("DailyMomentMediaPlayer");
        this.aa = timer;
        timer.schedule(new TimerTask() { // from class: dnw.2
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                Message obtain = Message.obtain();
                obtain.what = 1;
                dnw.this.r.sendMessage(obtain);
            }
        }, 0L, Math.max(1000, this.u.getDuration() / 100));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        this.s = true;
        if (this.m != null) {
            HandlerExecutor.e(new Runnable() { // from class: dnw.1
                @Override // java.lang.Runnable
                public void run() {
                    dnw.this.m.a();
                }
            });
        }
    }

    private void p() {
        LogUtil.a("DailyMomentMediaPlayer", "stopRefreshingProgress");
        Timer timer = this.aa;
        if (timer != null) {
            timer.cancel();
            this.aa.purge();
            this.aa = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        MediaPlayer mediaPlayer = this.u;
        if (mediaPlayer == null || mediaPlayer.isPlaying()) {
            return;
        }
        LogUtil.a("DailyMomentMediaPlayer", "start play");
        o();
        k();
        this.u.start();
        c(true);
        dob.b(b());
        this.x = SystemClock.elapsedRealtime();
        g();
        HashMap hashMap = new HashMap(16);
        hashMap.put("status", 0);
        doa.e(this.l, AnalyticsValue.DAILY_MOMENT_11100308.value(), this.n, hashMap);
    }

    private void g() {
        MediaPlayer mediaPlayer = this.u;
        if (mediaPlayer != null) {
            int duration = mediaPlayer.getDuration();
            if (duration == -1) {
                LogUtil.a("DailyMomentMediaPlayer", "get duration failed!");
                return;
            }
            this.o = 0L;
            long j = duration;
            boolean z = j - 300000 < 0;
            this.w = z;
            this.t = OpAnalyticsConstants.H5_LOADING_DELAY;
            if (z) {
                this.k = 870000L;
            } else {
                this.k = j - OpAnalyticsConstants.H5_LOADING_DELAY;
            }
            LogUtil.a("DailyMomentMediaPlayer", "duration: " + duration + ", mFadeOutBeginTime: " + this.k + ", mIsCyclePlay: " + this.w);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        MediaPlayer mediaPlayer = this.u;
        if (mediaPlayer == null || !mediaPlayer.isPlaying()) {
            return;
        }
        p();
        this.u.pause();
        c(false);
        dob.b();
        HashMap hashMap = new HashMap(16);
        hashMap.put("status", 1);
        hashMap.put(KakaConstants.SLEEP_MUSIC_DURATION, Long.valueOf(SystemClock.elapsedRealtime() - this.x));
        doa.e(this.l, AnalyticsValue.DAILY_MOMENT_11100308.value(), this.n, hashMap);
    }

    public void c() {
        ValueAnimator valueAnimator = this.z;
        if (valueAnimator != null) {
            valueAnimator.cancel();
            this.z.removeAllUpdateListeners();
            this.z.removeAllListeners();
            this.z = null;
        }
        try {
            j();
            this.u.release();
            this.u = null;
        } catch (IllegalStateException unused) {
            LogUtil.b("DailyMomentMediaPlayer", "media player is not prepared or has been released");
        }
    }

    private void j() {
        p();
        c(false);
        dob.b();
        MediaPlayer mediaPlayer = this.u;
        if (mediaPlayer == null) {
            return;
        }
        try {
            if (mediaPlayer.isPlaying()) {
                this.i.abandonAudioFocus(this.j);
                this.u.pause();
            }
        } catch (IllegalStateException unused) {
            LogUtil.b("DailyMomentMediaPlayer", "media player is not prepared or has been released");
        }
    }

    private void o() {
        LogUtil.a("DailyMomentMediaPlayer", "resetVolume");
        MediaPlayer mediaPlayer = this.u;
        if (mediaPlayer != null) {
            mediaPlayer.setVolume(1.0f, 1.0f);
            this.q = false;
        }
    }
}
