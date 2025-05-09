package com.huawei.openalliance.ad.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.Toast;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.inter.data.VideoInfo;
import com.huawei.openalliance.ad.media.MediaPlayerAgent;
import com.huawei.openalliance.ad.media.MediaState;
import com.huawei.openalliance.ad.media.listener.MediaBufferListener;
import com.huawei.openalliance.ad.media.listener.MediaErrorListener;
import com.huawei.openalliance.ad.media.listener.MediaStateListener;
import com.huawei.openalliance.ad.utils.Cdo;
import com.huawei.openalliance.ad.utils.bv;
import com.huawei.openalliance.ad.utils.dd;
import com.huawei.openalliance.ad.utils.dj;
import com.huawei.openalliance.ad.views.BaseVideoView;

/* loaded from: classes5.dex */
public class ak implements MediaBufferListener, MediaErrorListener, MediaStateListener, BaseVideoView.SurfaceListener, NetworkChangeListener {

    /* renamed from: a, reason: collision with root package name */
    private VideoView f8036a;
    private com.huawei.openalliance.ad.views.interfaces.v b;
    private ImageView c;
    private ImageView d;
    private View e;
    private ImageView f;
    private View g;
    private View h;
    private View i;
    private boolean m;
    private int o;
    private boolean p;
    private View.OnClickListener q;
    private a r;
    private int s;
    private VideoInfo u;
    private String v;
    private int j = 10;
    private final String k = "hidePanelTask" + hashCode();
    private final String l = "autoPlayTask" + hashCode();
    private boolean n = true;
    private boolean t = false;
    private boolean w = true;
    private boolean x = false;
    private Runnable y = new Runnable() { // from class: com.huawei.openalliance.ad.views.ak.1
        @Override // java.lang.Runnable
        public void run() {
            if (ak.this.f8036a == null || !ak.this.m) {
                return;
            }
            ak.this.b(true);
        }
    };
    private View.OnClickListener z = new View.OnClickListener() { // from class: com.huawei.openalliance.ad.views.ak.5
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ak.this.x = true;
            ak.this.i(true ^ view.isSelected());
            ViewClickInstrumentation.clickOnView(view);
        }
    };
    private Runnable A = new Runnable() { // from class: com.huawei.openalliance.ad.views.ak.7
        @Override // java.lang.Runnable
        public void run() {
            ak.this.a(false, true);
        }
    };

    public interface a {
        void a();

        void a(boolean z);

        void a(boolean z, int i);

        void b(boolean z);

        void b(boolean z, int i);

        boolean b();
    }

    @Override // com.huawei.openalliance.ad.media.listener.MediaBufferListener
    public void onBufferUpdate(int i) {
    }

    @Override // com.huawei.openalliance.ad.views.BaseVideoView.SurfaceListener
    public void onSurfaceDestroyed() {
        k();
        j(false);
    }

    @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
    public void onProgress(int i, int i2) {
        VideoInfo videoInfo;
        if (i2 <= 0 || (videoInfo = this.u) == null) {
            return;
        }
        videoInfo.e(i2);
    }

    @Override // com.huawei.openalliance.ad.views.NetworkChangeListener
    public void onNetworkDisconnected() {
        z();
    }

    @Override // com.huawei.openalliance.ad.views.NetworkChangeListener
    public void onNetworkConnectedOrChanged(boolean z) {
        ho.a("VideoControlBridge", "onNetworkConnectedOrChanged, isWifi= %s", Boolean.valueOf(z));
        k(z);
    }

    @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
    public void onMediaStop(MediaPlayerAgent mediaPlayerAgent, int i) {
        a(i, false, false);
    }

    @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
    public void onMediaStart(MediaPlayerAgent mediaPlayerAgent, int i) {
        com.huawei.openalliance.ad.views.interfaces.v vVar;
        l(false);
        if (this.c != null && (vVar = this.b) != null && vVar.c() != 0) {
            this.c.setImageResource(this.b.c());
        }
        l();
        if (this.n) {
            a(false, false);
        } else {
            v();
        }
        j(true);
    }

    @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
    public void onMediaPause(MediaPlayerAgent mediaPlayerAgent, int i) {
        a(i, true, false);
    }

    @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
    public void onMediaCompletion(MediaPlayerAgent mediaPlayerAgent, int i) {
        a(i, false, true);
    }

    @Override // com.huawei.openalliance.ad.media.listener.MediaErrorListener
    public void onError(MediaPlayerAgent mediaPlayerAgent, int i, int i2, int i3) {
        a(i, false, false);
    }

    @Override // com.huawei.openalliance.ad.media.listener.MediaBufferListener
    public void onBufferingStart() {
        View view = this.e;
        if (view == null || view.getVisibility() == 0) {
            return;
        }
        this.e.setVisibility(0);
        ImageView imageView = this.c;
        if (imageView != null) {
            imageView.setVisibility(4);
        }
    }

    @Override // com.huawei.openalliance.ad.media.listener.MediaBufferListener
    public void onBufferingEnd() {
        View view = this.e;
        if (view == null || view.getVisibility() != 0) {
            return;
        }
        this.e.setVisibility(8);
    }

    public boolean h() {
        return this.x;
    }

    public void g(boolean z) {
        if (z) {
            a((String) null);
            a(0);
            b(0);
            a((Drawable) null);
        }
        k();
        d();
    }

    public void g() {
        VideoView videoView = this.f8036a;
        if (videoView != null) {
            videoView.pause();
        }
    }

    public void f(boolean z) {
        if (ho.a()) {
            ho.a("VideoControlBridge", "setPlayButtonEnabled: %s", Boolean.valueOf(z));
        }
        ImageView imageView = this.c;
        if (imageView != null) {
            imageView.setEnabled(z);
        }
    }

    public void f() {
        this.p = false;
        VideoView videoView = this.f8036a;
        if (videoView != null) {
            videoView.resumeView();
        }
    }

    public void e(boolean z) {
        ho.b("VideoControlBridge", "setMuteButtonState mute: " + z);
        ImageView d = this.b.d();
        if (d != null) {
            d.setSelected(!z);
        }
    }

    public void e() {
        this.p = true;
        VideoView videoView = this.f8036a;
        if (videoView != null) {
            videoView.pauseView();
        }
    }

    public void d(boolean z) {
        ho.b("VideoControlBridge", "toggleVideoMute mute: " + z);
        if (this.f8036a == null || this.b == null) {
            return;
        }
        e(z);
        if (z) {
            this.f8036a.mute();
        } else {
            this.f8036a.unmute();
        }
    }

    public void d() {
        a(true, false);
    }

    public void c(boolean z) {
        this.m = z;
    }

    public void c(int i) {
        this.s = i;
    }

    public void c() {
        ho.b("VideoControlBridge", "autoPlayByNetworkState");
        VideoView videoView = this.f8036a;
        if (videoView == null) {
            return;
        }
        Context context = videoView.getContext();
        if (!bv.e(context)) {
            this.f8036a.pause();
            return;
        }
        if (this.t || this.s == 1 || bv.c(context)) {
            b(true);
            return;
        }
        ho.b("VideoControlBridge", "autoPlayByNetworkState - in non wifi, show alert view");
        this.f8036a.pause();
        n();
        u();
    }

    public void b(boolean z) {
        if (this.f8036a != null) {
            h(z);
            this.f8036a.setPreferStartPlayTime(this.o);
            this.f8036a.play(z);
        }
    }

    public void b(int i) {
        VideoView videoView = this.f8036a;
        if (videoView != null) {
            videoView.setDefaultDuration(i);
        }
    }

    public void b() {
        VideoView videoView = this.f8036a;
        if (videoView != null) {
            videoView.stop();
        }
        m();
        j(false);
        d();
        k();
    }

    public void a(boolean z) {
        ho.b("VideoControlBridge", "setAutoPlayOnFirstShow: %s", Boolean.valueOf(z));
        this.w = z;
    }

    public void a(String str) {
        VideoView videoView;
        if (this.b == null || (videoView = this.f8036a) == null) {
            return;
        }
        this.v = str;
        videoView.setVideoFileUrl(str);
    }

    public void a(a aVar) {
        this.r = aVar;
    }

    public void a(VideoInfo videoInfo) {
        this.u = videoInfo;
    }

    public void a(View.OnClickListener onClickListener) {
        this.q = onClickListener;
    }

    public void a(Drawable drawable) {
        ImageView imageView = this.f;
        if (imageView != null) {
            imageView.setImageDrawable(drawable);
        }
    }

    public void a(long j) {
        ho.b("VideoControlBridge", "autoPlay - canAutoPlay: %s, autoPlayOnFirstShow: %s, delayMs: %d", Boolean.valueOf(this.m), Boolean.valueOf(this.w), Long.valueOf(j));
        dj.a(this.l);
        if (this.m && this.w) {
            if (this.f8036a.isPlaying()) {
                ho.a("VideoControlBridge", "autoPlay - video is playing");
                b(true);
                return;
            }
            ho.a("VideoControlBridge", "autoPlay - start delay runnable");
            if (!TextUtils.isEmpty(this.v) && !this.v.startsWith(Constants.LOCAL_HOST)) {
                this.f8036a.prefetch();
            }
            dj.a(this.y, this.l, j);
        }
    }

    public void a(int i) {
        this.o = i;
        if (this.f8036a != null) {
            ho.a("VideoControlBridge", "setPreferStartPlayTime %d", Integer.valueOf(i));
            this.f8036a.setPreferStartPlayTime(i);
        }
    }

    public void a() {
        dj.a(this.l);
    }

    private void z() {
        VideoView videoView = this.f8036a;
        if (videoView != null) {
            if (videoView.getCurrentState().isState(MediaState.State.PREPARING) || this.f8036a.isPlaying()) {
                this.f8036a.pause();
            }
        }
    }

    private boolean y() {
        return (this.f8036a.getCurrentState().isState(MediaState.State.PREPARING) || this.f8036a.isPlaying()) && !this.t;
    }

    private void x() {
        if (this.f8036a == null || !y() || this.s == 1) {
            return;
        }
        this.f8036a.stop();
        if (this.h != null) {
            n();
            u();
        }
    }

    private void w() {
        if (this.f8036a == null) {
            return;
        }
        m();
        if (!this.f8036a.getCurrentState().a()) {
            k();
        }
        a aVar = this.r;
        if (aVar != null) {
            this.m = aVar.b();
        }
        if (this.m && !this.p) {
            b(true);
        } else {
            if (this.f8036a.isPlaying()) {
                return;
            }
            d();
        }
    }

    private void v() {
        dj.a(this.k);
        dj.a(this.A, this.k, 3000L);
    }

    private void u() {
        a(false, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void t() {
        View.OnClickListener onClickListener;
        if (this.f8036a == null || (onClickListener = this.q) == null) {
            return;
        }
        onClickListener.onClick((NativeVideoControlPanel) this.b);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s() {
        View.OnClickListener onClickListener;
        VideoView videoView = this.f8036a;
        if (videoView == null || (onClickListener = this.q) == null) {
            return;
        }
        onClickListener.onClick(videoView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r() {
        if (this.f8036a == null) {
            return;
        }
        dj.a(this.l);
        if (this.f8036a.isPlaying()) {
            dj.a(this.k);
            this.f8036a.pause();
            l(true);
        } else {
            if (!bv.e(this.f8036a.getContext())) {
                Toast.makeText(this.f8036a.getContext(), R.string._2130851114_res_0x7f02352a, 0).show();
                return;
            }
            l(false);
            if (this.t || this.s == 1 || bv.c(this.f8036a.getContext())) {
                b(false);
                v();
            } else {
                ho.b("VideoControlBridge", "in non wifi, show alert view");
                this.f8036a.pause();
                n();
                u();
            }
        }
    }

    private void q() {
        com.huawei.openalliance.ad.views.interfaces.v vVar = this.b;
        if (vVar instanceof NativeVideoControlPanel) {
            ((NativeVideoControlPanel) vVar).setOnClickListener(new View.OnClickListener() { // from class: com.huawei.openalliance.ad.views.ak.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    ak.this.t();
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }
    }

    private void p() {
        VideoView videoView = this.f8036a;
        if (videoView != null) {
            videoView.addMediaStateListener(this);
            this.f8036a.addMediaBufferListener(this);
            this.f8036a.addMediaErrorListener(this);
            this.f8036a.addNetworkChangeListener(this);
            this.f8036a.setSurfaceListener(this);
            this.f8036a.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.openalliance.ad.views.ak.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    ak.this.s();
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }
    }

    private void o() {
        p();
        d(this.b);
        b(this.b);
        if (this.j == 10) {
            q();
        }
    }

    private void n(boolean z) {
        VideoView videoView;
        a aVar = this.r;
        if (aVar == null || (videoView = this.f8036a) == null) {
            return;
        }
        aVar.b(z, videoView.getCurrentState().getStateCode());
    }

    private void n() {
        View view = this.h;
        if (view != null) {
            view.setVisibility(0);
        }
    }

    private void m(boolean z) {
        VideoView videoView;
        a aVar = this.r;
        if (aVar == null || (videoView = this.f8036a) == null) {
            return;
        }
        aVar.a(z, videoView.getCurrentState().getStateCode());
    }

    private void m() {
        View view = this.h;
        if (view != null) {
            view.setVisibility(8);
        }
    }

    private void l(boolean z) {
        a aVar = this.r;
        if (aVar != null) {
            aVar.a(z);
        }
    }

    private void l() {
        VideoView videoView;
        if (ho.a()) {
            ho.a("VideoControlBridge", "hidePreviewView");
        }
        Cdo.a(this.f, 8, 300, 300);
        if (this.f == null || (videoView = this.f8036a) == null) {
            return;
        }
        videoView.setAlpha(1.0f);
    }

    private void k(boolean z) {
        if (this.f8036a == null) {
            return;
        }
        if (z || this.s == 1 || this.t) {
            w();
        } else {
            x();
        }
    }

    private void k() {
        if (this.f == null) {
            return;
        }
        if (ho.a()) {
            ho.a("VideoControlBridge", "showPreviewView");
        }
        Animation animation = this.f.getAnimation();
        if (animation != null) {
            animation.cancel();
        }
        dd.a((View) this.f, true);
        VideoView videoView = this.f8036a;
        if (videoView != null) {
            videoView.setAlpha(0.0f);
        }
    }

    private void j(boolean z) {
        this.n = !z;
        com.huawei.openalliance.ad.views.interfaces.v vVar = this.b;
        if (vVar != null) {
            vVar.a(z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        dj.a(this.l);
        m();
        if (this.j == 10) {
            t();
        }
        VideoView videoView = this.f8036a;
        if (videoView != null && !videoView.getCurrentState().a()) {
            k();
        }
        b(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i(boolean z) {
        ho.b("VideoControlBridge", "switchSound enableSound: " + z);
        VideoView videoView = this.f8036a;
        if (videoView == null) {
            return;
        }
        if (z) {
            videoView.unmute();
        } else {
            videoView.mute();
        }
        dj.a(this.k);
        if (this.f8036a.isPlaying()) {
            v();
        }
    }

    private void i() {
        com.huawei.openalliance.ad.views.interfaces.v vVar = this.b;
        if (vVar == null) {
            return;
        }
        this.e = vVar.e();
        this.g = this.b.i();
        View g = this.b.g();
        this.h = g;
        if (g != null) {
            g.setClickable(true);
        }
        this.f = this.b.f();
        c(this.b);
        o();
        m();
        j(false);
        d();
    }

    private void h(boolean z) {
        a aVar = this.r;
        if (aVar != null) {
            aVar.b(z);
        }
    }

    private void d(com.huawei.openalliance.ad.views.interfaces.v vVar) {
        ImageView a2 = vVar.a();
        this.c = a2;
        if (a2 != null) {
            a2.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.openalliance.ad.views.ak.6
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (ak.this.r != null) {
                        ak.this.r.a();
                    }
                    if (ak.this.j != 10) {
                        ak.this.r();
                    } else {
                        ho.a("VideoControlBridge", "linkedVideoMode is %d", Integer.valueOf(ak.this.j));
                        ak.this.t();
                    }
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            if (vVar.b() > 0) {
                this.c.setImageResource(vVar.b());
                dd.a(this.c);
            }
        }
    }

    private void c(com.huawei.openalliance.ad.views.interfaces.v vVar) {
        ImageView d = vVar.d();
        this.d = d;
        if (d != null) {
            d.setOnClickListener(this.z);
        }
    }

    private void b(com.huawei.openalliance.ad.views.interfaces.v vVar) {
        View h = vVar.h();
        this.i = h;
        if (h != null) {
            h.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.openalliance.ad.views.ak.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    ak.this.j();
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z, boolean z2) {
        boolean a2;
        View view = this.g;
        if (z2) {
            a2 = dd.a(view, z ? 0 : 8);
        } else {
            a2 = dd.a(view, z);
        }
        if (a2) {
            if (z) {
                m(z2);
            } else {
                n(z2);
            }
        }
    }

    private void a(com.huawei.openalliance.ad.views.interfaces.v vVar) {
        this.b = vVar;
        i();
    }

    private void a(VideoView videoView) {
        this.f8036a = videoView;
    }

    private void a(int i, boolean z, boolean z2) {
        com.huawei.openalliance.ad.views.interfaces.v vVar;
        a();
        if (z2) {
            i = 0;
        }
        this.o = i;
        dj.a(this.k);
        if (this.c != null && (vVar = this.b) != null && vVar.b() != 0) {
            this.c.setImageResource(this.b.b());
            dd.a(this.c);
        }
        if (!z) {
            k();
            j(false);
        }
        View view = this.h;
        if (view == null || view.getVisibility() != 0) {
            a(true, true);
        }
        ImageView imageView = this.c;
        if (imageView != null) {
            imageView.setVisibility(0);
        }
    }

    public ak(VideoView videoView, com.huawei.openalliance.ad.views.interfaces.v vVar) {
        a(videoView);
        a(vVar);
    }
}
