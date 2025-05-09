package com.huawei.healthcloud.plugintrack.ui.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.constraintlayout.motion.widget.Key;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.sportmusic.SportMusicController;
import com.huawei.healthcloud.plugintrack.ui.view.MusicControlLayout;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.gwg;
import defpackage.gww;
import defpackage.nrf;
import defpackage.nrr;
import defpackage.nrt;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.StorageParams;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes4.dex */
public class MusicControlLayout extends RelativeLayout implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private int f3783a;
    private Bitmap b;
    private Context c;
    private AutoTransition d;
    private ImageView e;
    private Handler f;
    private int g;
    private boolean h;
    private boolean i;
    private boolean j;
    private String k;
    private boolean l;
    private b m;
    private RelativeLayout n;
    private long o;
    private ImageView p;
    private ObjectAnimator q;
    private ImageView r;
    private ImageView s;
    private HealthTextView t;
    private ImageView u;
    private View v;
    private gww w;
    private int x;
    private SportMusicController y;

    public MusicControlLayout(Context context) {
        this(context, null);
    }

    public MusicControlLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MusicControlLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.l = false;
        this.h = false;
        this.f3783a = 1;
        this.c = BaseApplication.getContext();
        this.i = false;
        this.k = "";
        this.f = null;
        this.x = 0;
        this.m = new b();
        this.j = false;
        a(context);
        l();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        LogUtil.a("Track_MusicControlLayout", "onDetachedFromWindow");
        super.onDetachedFromWindow();
        b();
    }

    public void e(final int i) {
        this.g = this.w.f(this.x);
        if (a()) {
            this.y.e();
            b(new IBaseResponseCallback() { // from class: hlk
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i2, Object obj) {
                    MusicControlLayout.this.a(i, i2, obj);
                }
            });
        }
    }

    public /* synthetic */ void a(int i, int i2, Object obj) {
        if (this.y.d()) {
            this.y.b(i);
        }
    }

    public boolean a() {
        return gwg.i(this.c) && CommonUtil.bd() && this.g == 1;
    }

    private void f() {
        gww gwwVar = new gww(BaseApplication.getContext(), new StorageParams(1), Integer.toString(20002));
        this.w = gwwVar;
        this.g = gwwVar.f(this.x);
    }

    public gww getTrackSharedPreferenceUtil() {
        return this.w;
    }

    private void a(Context context) {
        f();
        if (context == null) {
            LogUtil.h("Track_MusicControlLayout", "context is  null");
            return;
        }
        View inflate = View.inflate(context, R.layout.music_content_layout, this);
        this.v = inflate;
        this.u = (ImageView) inflate.findViewById(R.id.music_previous);
        this.s = (ImageView) this.v.findViewById(R.id.play_or_pause);
        this.n = (RelativeLayout) this.v.findViewById(R.id.music_control_and_content);
        this.p = (ImageView) this.v.findViewById(R.id.music_next);
        this.e = (ImageView) this.v.findViewById(R.id.music_collect);
        this.r = (ImageView) this.v.findViewById(R.id.music_element);
        this.t = (HealthTextView) this.v.findViewById(R.id.music_content);
        this.u.setOnClickListener(this);
        this.s.setOnClickListener(this);
        this.p.setOnClickListener(this);
        this.e.setOnClickListener(this);
        this.v.setOnClickListener(this);
        this.y = SportMusicController.a();
        b((IBaseResponseCallback) null);
        r();
        if (gwg.i(this.c) && CommonUtil.bd() && this.g == 1) {
            this.n.setVisibility(0);
        } else {
            this.n.setVisibility(8);
        }
        o();
        if (this.j) {
            return;
        }
        setViewShowStatue(0);
    }

    private void b(IBaseResponseCallback iBaseResponseCallback) {
        this.y.e(iBaseResponseCallback);
        this.y.c(this.m);
        this.y.d(new e(this));
    }

    private void g() {
        LogUtil.a("Track_MusicControlLayout", "initControlLayout");
        k();
        n();
        if (LanguageUtil.bc(this.c)) {
            if (this.i) {
                e();
            } else {
                i();
            }
            this.t.setSpeed(3);
            return;
        }
        this.t.setSpeed(-3);
        if (this.i) {
            this.u.setImageDrawable(nrf.cJH_(getResources().getDrawable(R.drawable._2131431104_res_0x7f0b0ec0), this.c.getResources().getColor(R.color._2131297782_res_0x7f0905f6)));
            this.p.setImageDrawable(nrf.cJH_(getResources().getDrawable(R.drawable._2131430907_res_0x7f0b0dfb), this.c.getResources().getColor(R.color._2131297782_res_0x7f0905f6)));
        } else {
            this.u.setImageDrawable(nrf.cJH_(getResources().getDrawable(R.drawable._2131431104_res_0x7f0b0ec0), this.c.getResources().getColor(R.color._2131297781_res_0x7f0905f5)));
            this.p.setImageDrawable(nrf.cJH_(getResources().getDrawable(R.drawable._2131430907_res_0x7f0b0dfb), this.c.getResources().getColor(R.color._2131297781_res_0x7f0905f5)));
        }
    }

    private void i() {
        this.u.setImageDrawable(nrf.cJH_(getResources().getDrawable(R.drawable._2131430907_res_0x7f0b0dfb), this.c.getResources().getColor(R.color._2131297781_res_0x7f0905f5)));
        this.p.setImageDrawable(nrf.cJH_(getResources().getDrawable(R.drawable._2131431104_res_0x7f0b0ec0), this.c.getResources().getColor(R.color._2131297781_res_0x7f0905f5)));
    }

    private void e() {
        this.u.setImageDrawable(nrf.cJH_(getResources().getDrawable(R.drawable._2131430907_res_0x7f0b0dfb), this.c.getResources().getColor(R.color._2131297782_res_0x7f0905f6)));
        this.p.setImageDrawable(nrf.cJH_(getResources().getDrawable(R.drawable._2131431104_res_0x7f0b0ec0), this.c.getResources().getColor(R.color._2131297782_res_0x7f0905f6)));
    }

    public void setSportTypeDrawable(boolean z, int i) {
        this.x = i;
        this.i = z;
        if (this.n != null && z) {
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setColor(this.c.getResources().getColor(R.color._2131297211_res_0x7f0903bb));
            gradientDrawable.setCornerRadius(nsn.c(this.c, 24.0f));
            gradientDrawable.setGradientType(0);
            this.n.setBackground(gradientDrawable);
        }
        HealthTextView healthTextView = this.t;
        if (healthTextView != null && this.i) {
            healthTextView.setTextColor(this.c.getResources().getColor(R.color._2131299238_res_0x7f090ba6));
        }
        if (this.t != null) {
            g();
        }
    }

    private void o() {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.r, Key.ROTATION, 0.0f, 360.0f);
        this.q = ofFloat;
        ofFloat.setRepeatCount(-1);
        this.q.setDuration(PreConnectManager.CONNECT_INTERNAL);
        this.q.setInterpolator(new LinearInterpolator());
        this.q.setRepeatMode(1);
        if (this.l && this.f3783a == 1) {
            this.q.start();
            this.f3783a = 3;
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        boolean z = true;
        if (view.getId() == R.id.music_previous) {
            this.y.a(4, (String) null);
        } else if (view.getId() == R.id.play_or_pause) {
            if (this.l) {
                this.y.a(1, (String) null);
                SportMusicController.a().a(true);
            } else {
                this.y.a(2, (String) null);
                SportMusicController.a().a(false);
            }
        } else if (view.getId() == R.id.music_next) {
            this.y.a(3, (String) null);
        } else if (view.getId() == R.id.music_collect) {
            if (this.h) {
                this.y.a(5, "com.huawei.music.action.DISLIKE");
            } else {
                this.y.a(5, "com.huawei.music.action.LIKE");
            }
        } else if (j()) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        } else if (this.r.getVisibility() == 0 && this.t.getVisibility() == 8) {
            h();
            LogUtil.a("Track_MusicControlLayout", "initExpand");
        } else {
            d();
            LogUtil.a("Track_MusicControlLayout", "gotoMusic");
            z = false;
        }
        a(z);
        ViewClickInstrumentation.clickOnView(view);
    }

    public void d() {
        b(new HashMap(16));
    }

    private void b(Map<String, Object> map) {
        SportMusicController.a().d(this.w.d(this.x) == null ? 1 : 2, this.x, true);
        map.put("sportMusicType", 0);
    }

    public void c() {
        SportMusicController.a();
        SportMusicController sportMusicController = this.y;
        if (sportMusicController == null) {
            return;
        }
        sportMusicController.i();
        if (this.y.c() != null) {
            this.t.setText(this.y.c().getString(MediaMetadataCompat.METADATA_KEY_TITLE));
        }
        PlaybackStateCompat j = this.y.j();
        this.l = j != null && j.getState() == 3;
        s();
        r();
        this.g = this.w.f(this.x);
        if (gwg.i(this.c) && CommonUtil.bd() && this.g == 1) {
            this.n.setVisibility(0);
        } else {
            this.n.setVisibility(8);
        }
    }

    private void s() {
        if (this.l) {
            if (this.i) {
                this.s.setImageDrawable(nrf.cJH_(getResources().getDrawable(R.drawable._2131431078_res_0x7f0b0ea6), this.c.getResources().getColor(R.color._2131297782_res_0x7f0905f6)));
                return;
            } else {
                this.s.setImageDrawable(nrf.cJH_(getResources().getDrawable(R.drawable._2131431078_res_0x7f0b0ea6), this.c.getResources().getColor(R.color._2131297781_res_0x7f0905f5)));
                return;
            }
        }
        if (LanguageUtil.bc(this.c)) {
            if (this.i) {
                this.s.setImageDrawable(nrf.cJH_(getResources().getDrawable(R.drawable._2131430966_res_0x7f0b0e36), this.c.getResources().getColor(R.color._2131297782_res_0x7f0905f6)));
                return;
            } else {
                this.s.setImageDrawable(nrf.cJH_(getResources().getDrawable(R.drawable._2131430966_res_0x7f0b0e36), this.c.getResources().getColor(R.color._2131297781_res_0x7f0905f5)));
                return;
            }
        }
        if (this.i) {
            this.s.setImageDrawable(nrf.cJH_(getResources().getDrawable(R.drawable._2131430965_res_0x7f0b0e35), this.c.getResources().getColor(R.color._2131297782_res_0x7f0905f6)));
        } else {
            this.s.setImageDrawable(nrf.cJH_(getResources().getDrawable(R.drawable._2131430965_res_0x7f0b0e35), this.c.getResources().getColor(R.color._2131297781_res_0x7f0905f5)));
        }
    }

    private boolean j() {
        long currentTimeMillis = System.currentTimeMillis();
        if (1000 > currentTimeMillis - this.o) {
            LogUtil.a("Track_MusicControlLayout", "onClick", "click too fast");
            this.o = currentTimeMillis;
            return true;
        }
        this.o = currentTimeMillis;
        return false;
    }

    private void r() {
        nrf.b(this.w.d(this.x), new CustomTarget<Bitmap>() { // from class: com.huawei.healthcloud.plugintrack.ui.view.MusicControlLayout.2
            @Override // com.bumptech.glide.request.target.Target
            /* renamed from: bim_, reason: merged with bridge method [inline-methods] */
            public void onResourceReady(Bitmap bitmap, Transition<? super Bitmap> transition) {
                if (bitmap == null) {
                    if (nrt.a(MusicControlLayout.this.c)) {
                        MusicControlLayout.this.r.setImageDrawable(MusicControlLayout.this.getResources().getDrawable(R.drawable._2131430897_res_0x7f0b0df1));
                        return;
                    } else {
                        MusicControlLayout.this.r.setImageDrawable(MusicControlLayout.this.getResources().getDrawable(R.drawable._2131430898_res_0x7f0b0df2));
                        return;
                    }
                }
                MusicControlLayout.this.b = bitmap;
                new d().execute(new Void[0]);
            }

            @Override // com.bumptech.glide.request.target.Target
            public void onLoadCleared(Drawable drawable) {
                LogUtil.a("Track_MusicControlLayout", "updateMusicElement onLoadCleared");
            }
        });
    }

    public void b() {
        SportMusicController sportMusicController = this.y;
        if (sportMusicController != null) {
            sportMusicController.h();
            this.y.d(this.m);
        }
    }

    class d extends AsyncTask<Void, Void, Bitmap> {
        private d() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: bin_, reason: merged with bridge method [inline-methods] */
        public Bitmap doInBackground(Void... voidArr) {
            float c = nsn.c(MusicControlLayout.this.c, 40.0f) / Math.min(MusicControlLayout.this.b.getWidth(), MusicControlLayout.this.b.getHeight());
            Matrix matrix = new Matrix();
            matrix.postScale(c, c);
            return nrf.cHY_(Bitmap.createBitmap(MusicControlLayout.this.b, 0, 0, MusicControlLayout.this.b.getWidth(), MusicControlLayout.this.b.getHeight(), matrix, true), nsn.c(MusicControlLayout.this.c, 20.0f));
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: bio_, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Bitmap bitmap) {
            if (MusicControlLayout.this.r != null) {
                MusicControlLayout.this.r.setImageBitmap(bitmap);
            }
        }
    }

    class b extends MediaControllerCompat.Callback {
        private b() {
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.Callback
        public void onPlaybackStateChanged(PlaybackStateCompat playbackStateCompat) {
            if (playbackStateCompat == null) {
                LogUtil.a("Track_MusicControlLayout", "onPlaybackStateChanged playbackState is null");
                return;
            }
            MusicControlLayout.this.l = playbackStateCompat.getState() == 3;
            MusicControlLayout.this.k();
            if (MusicControlLayout.this.q == null) {
                LogUtil.a("Track_MusicControlLayout", "mObjectAnimator is null");
            } else {
                b();
            }
        }

        private void b() {
            if (MusicControlLayout.this.l) {
                if (MusicControlLayout.this.f3783a == 1) {
                    MusicControlLayout.this.q.start();
                    MusicControlLayout.this.f3783a = 3;
                    return;
                } else {
                    if (MusicControlLayout.this.f3783a == 2) {
                        MusicControlLayout.this.q.resume();
                        MusicControlLayout.this.t.e();
                        MusicControlLayout.this.f3783a = 3;
                        return;
                    }
                    return;
                }
            }
            if (MusicControlLayout.this.f3783a == 3) {
                MusicControlLayout.this.q.pause();
                MusicControlLayout.this.t.c();
                MusicControlLayout.this.f3783a = 2;
            }
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.Callback
        public void onMetadataChanged(MediaMetadataCompat mediaMetadataCompat) {
            if (mediaMetadataCompat == null) {
                return;
            }
            MusicControlLayout musicControlLayout = MusicControlLayout.this;
            musicControlLayout.k = musicControlLayout.k == null ? "" : MusicControlLayout.this.k;
            String string = mediaMetadataCompat.getString(MediaMetadataCompat.METADATA_KEY_TITLE);
            LogUtil.a("Track_MusicControlLayout", "onMetadataChanged, mLastSongName = ", MusicControlLayout.this.k, ", currentSongName = ", string);
            if (MusicControlLayout.this.k.equals(string)) {
                return;
            }
            MusicControlLayout.this.t.b();
            MusicControlLayout.this.k = string;
            MusicControlLayout.this.t.setText(string);
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.Callback
        public void onSessionEvent(String str, Bundle bundle) {
            if (bundle == null) {
                LogUtil.b("Track_MusicControlLayout", "extras is null");
                return;
            }
            if ("com.huawei.music.action.LIKE_STATUS".equals(str) || "favoriteStateChange".equals(str)) {
                int i = bundle.getInt("result", 0);
                MusicControlLayout.this.h = i == 1;
                MusicControlLayout.this.n();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        if (this.h) {
            this.e.setImageDrawable(getResources().getDrawable(R.drawable._2131430597_res_0x7f0b0cc5));
        } else if (this.i || nrt.a(this.c)) {
            this.e.setImageDrawable(nrf.cJH_(getResources().getDrawable(R.drawable._2131430619_res_0x7f0b0cdb), this.c.getResources().getColor(R.color._2131297782_res_0x7f0905f6)));
        } else {
            this.e.setImageDrawable(getResources().getDrawable(R.drawable._2131430619_res_0x7f0b0cdb));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        if (this.l) {
            if (this.i) {
                this.s.setImageDrawable(nrf.cJH_(getResources().getDrawable(R.drawable._2131431078_res_0x7f0b0ea6), getResources().getColor(R.color._2131297782_res_0x7f0905f6)));
                return;
            } else {
                this.s.setImageDrawable(nrf.cJH_(getResources().getDrawable(R.drawable._2131431078_res_0x7f0b0ea6), getResources().getColor(R.color._2131297781_res_0x7f0905f5)));
                return;
            }
        }
        if (LanguageUtil.bc(BaseApplication.getContext())) {
            if (this.i) {
                this.s.setImageDrawable(nrf.cJH_(getResources().getDrawable(R.drawable._2131430966_res_0x7f0b0e36), getResources().getColor(R.color._2131297782_res_0x7f0905f6)));
                return;
            } else {
                this.s.setImageDrawable(nrf.cJH_(getResources().getDrawable(R.drawable._2131430966_res_0x7f0b0e36), getResources().getColor(R.color._2131297781_res_0x7f0905f5)));
                return;
            }
        }
        if (this.i) {
            this.s.setImageDrawable(nrf.cJH_(getResources().getDrawable(R.drawable._2131430965_res_0x7f0b0e35), getResources().getColor(R.color._2131297782_res_0x7f0905f6)));
        } else {
            this.s.setImageDrawable(nrf.cJH_(getResources().getDrawable(R.drawable._2131430965_res_0x7f0b0e35), getResources().getColor(R.color._2131297781_res_0x7f0905f5)));
        }
    }

    private void l() {
        this.f = new Handler() { // from class: com.huawei.healthcloud.plugintrack.ui.view.MusicControlLayout.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message == null) {
                    LogUtil.b("Track_MusicControlLayout", "msg is null!");
                    return;
                }
                super.handleMessage(message);
                if (message.what != 100) {
                    return;
                }
                MusicControlLayout.this.m();
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        setViewShowStatue(8);
        RelativeLayout relativeLayout = this.n;
        if (relativeLayout instanceof RelativeLayout) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) relativeLayout.getLayoutParams();
            layoutParams.width = nrr.e(this.c, 48.0f);
            layoutParams.addRule(11);
            this.n.setLayoutParams(layoutParams);
        }
        bil_(this.n);
    }

    private void h() {
        setViewShowStatue(0);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, nrr.e(this.c, 48.0f));
        layoutParams.setMargins(0, 0, 0, 0);
        this.n.setPadding(0, 0, 0, 0);
        this.n.setLayoutParams(layoutParams);
        bil_(this.n);
    }

    private void setViewShowStatue(int i) {
        this.t.setVisibility(i);
        this.e.setVisibility(i);
        this.u.setVisibility(i);
        this.s.setVisibility(i);
        this.p.setVisibility(i);
    }

    private void bil_(ViewGroup viewGroup) {
        AutoTransition autoTransition = new AutoTransition();
        this.d = autoTransition;
        autoTransition.setDuration(500L);
        TransitionManager.beginDelayedTransition(viewGroup, this.d);
    }

    public void a(boolean z) {
        LogUtil.a("Track_MusicControlLayout", "setMsg isSend ", Boolean.valueOf(z));
        Handler handler = this.f;
        if (handler == null) {
            LogUtil.b("Track_MusicControlLayout", "sendAnimationMsg mHandler is null.");
            return;
        }
        handler.removeCallbacksAndMessages(null);
        LogUtil.a("Track_MusicControlLayout", "removeCallbacksAndMessages");
        if (z && this.j) {
            this.f.sendEmptyMessageDelayed(100, 5000L);
        }
    }

    public void setIsExpandAnimation(boolean z) {
        this.j = z;
        m();
    }

    public void setMusicPause() {
        PlaybackStateCompat j;
        SportMusicController sportMusicController = this.y;
        if (sportMusicController == null || (j = sportMusicController.j()) == null || j.getState() != 3) {
            return;
        }
        this.y.a(1, (String) null);
    }

    public void setMusicPlay() {
        PlaybackStateCompat j;
        SportMusicController sportMusicController = this.y;
        if (sportMusicController == null || (j = sportMusicController.j()) == null || j.getState() != 2) {
            return;
        }
        this.y.a(2, (String) null);
    }

    static class e implements IBaseResponseCallback {
        private WeakReference<MusicControlLayout> b;

        public e(MusicControlLayout musicControlLayout) {
            this.b = new WeakReference<>(musicControlLayout);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            MusicControlLayout musicControlLayout = this.b.get();
            if (musicControlLayout == null || musicControlLayout.y == null) {
                return;
            }
            musicControlLayout.y.e((IBaseResponseCallback) null);
        }
    }
}
