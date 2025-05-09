package com.huawei.openalliance.ad;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.openalliance.ad.activity.PPSActivity;
import com.huawei.openalliance.ad.linked.view.LinkedNativeViewControlPanel;
import com.huawei.openalliance.ad.linked.view.LinkedWifiAlertPlayButton;
import com.huawei.openalliance.ad.media.MediaPlayerAgent;
import com.huawei.openalliance.ad.media.MediaState;
import com.huawei.openalliance.ad.media.listener.MediaBufferListener;
import com.huawei.openalliance.ad.media.listener.MediaErrorListener;
import com.huawei.openalliance.ad.media.listener.MediaStateListener;
import com.huawei.openalliance.ad.utils.Cdo;
import com.huawei.openalliance.ad.views.BaseVideoView;
import com.huawei.openalliance.ad.views.NetworkChangeListener;
import com.huawei.openalliance.ad.views.VideoView;
import java.util.Locale;

/* loaded from: classes9.dex */
public class hd implements MediaBufferListener, MediaErrorListener, MediaStateListener, BaseVideoView.SurfaceListener, NetworkChangeListener {
    private int B;
    private View.OnClickListener H;
    private a I;
    private PPSActivity.b N;
    private boolean S;
    private VideoView d;
    private SeekBar e;
    private LinkedNativeViewControlPanel f;
    private ImageView g;
    private ImageView h;
    private ImageView i;
    private ImageView j;
    private TextView k;
    private TextView l;
    private Context m;
    private int n;
    private View o;
    private ImageView p;
    private View q;
    private LinkedWifiAlertPlayButton r;
    private gy s;
    private boolean t;
    private int v;
    private boolean w;
    private int x;
    private int z;

    /* renamed from: a, reason: collision with root package name */
    private final String f6899a = "hPlT" + hashCode();
    private final String b = "hBPlT" + hashCode();
    private final String c = "aPT" + hashCode();
    private boolean u = true;
    private boolean y = true;
    private int A = 0;
    private int C = 0;
    private String D = "n";
    private boolean E = false;
    private boolean F = false;
    private final Runnable G = new Runnable() { // from class: com.huawei.openalliance.ad.hd.1
        @Override // java.lang.Runnable
        public void run() {
            if (hd.this.d == null || !hd.this.t) {
                return;
            }
            hd hdVar = hd.this;
            hdVar.g(hdVar.s.a(2, hd.this.y));
        }
    };
    private hc J = new hc();
    private final Runnable K = new Runnable() { // from class: com.huawei.openalliance.ad.hd.4
        @Override // java.lang.Runnable
        public void run() {
            hd.this.v();
        }
    };
    private final Runnable L = new Runnable() { // from class: com.huawei.openalliance.ad.hd.5
        @Override // java.lang.Runnable
        public void run() {
            hd.this.m();
        }
    };
    private final View.OnClickListener M = new View.OnClickListener() { // from class: com.huawei.openalliance.ad.hd.6
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            hd.this.k(!view.isSelected());
            ViewClickInstrumentation.clickOnView(view);
        }
    };
    private boolean O = false;
    private final View.OnClickListener P = new View.OnClickListener() { // from class: com.huawei.openalliance.ad.hd.7
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            hd.this.O = !r0.O;
            if (hd.this.N != null) {
                hd.this.N.a(hd.this.O);
            }
            ViewClickInstrumentation.clickOnView(view);
        }
    };
    private final View.OnClickListener Q = new View.OnClickListener() { // from class: com.huawei.openalliance.ad.hd.8
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (hd.this.J != null && hd.this.J.d()) {
                hd.this.J.c();
            }
            hd.this.c();
            hd.this.n();
            hd.this.w();
            int i = hd.this.n;
            if (i == 0) {
                hd.this.I.b();
            } else if (i != 1) {
                hd.this.I.d();
            } else {
                hd.this.I.c();
            }
            hd.this.F();
            ViewClickInstrumentation.clickOnView(view);
        }
    };
    private SeekBar.OnSeekBarChangeListener R = new SeekBar.OnSeekBarChangeListener() { // from class: com.huawei.openalliance.ad.hd.3
        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStopTrackingTouch(SeekBar seekBar) {
            hd.this.S = false;
            ViewClickInstrumentation.clickOnView(seekBar);
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStartTrackingTouch(SeekBar seekBar) {
            hd.this.S = true;
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            if (z) {
                ho.a("LinkedVideoControlBridge", "onProgressChanged %d", Integer.valueOf(i));
                hd.this.d.seekTo(i);
            }
        }
    };

    public interface a {
        void a();

        void a(boolean z);

        void b();

        void c();

        void d();
    }

    @Override // com.huawei.openalliance.ad.media.listener.MediaBufferListener
    public void onBufferUpdate(int i) {
    }

    public void y() {
        u();
        n();
    }

    public void x() {
        ho.a("LinkedVideoControlBridge", "hideAllControlPanelDirectly");
        v();
        com.huawei.openalliance.ad.utils.dj.a(this.b);
        m();
    }

    public void w() {
        com.huawei.openalliance.ad.utils.dj.a(this.b);
        com.huawei.openalliance.ad.utils.dj.a(this.L, this.b, 3000L);
    }

    public void v() {
        ho.a("LinkedVideoControlBridge", "hidePlayButton");
        a(this.g);
    }

    public void u() {
        ho.a("LinkedVideoControlBridge", "showPlayButton");
        View view = this.q;
        if (view != null && view.getVisibility() == 0) {
            v();
            return;
        }
        View view2 = this.o;
        if (view2 != null && view2.getVisibility() == 0) {
            v();
        } else if (this.J.d()) {
            v();
        } else {
            b(this.g);
        }
    }

    public void t() {
        a(this.h);
    }

    public void s() {
        b(this.h);
    }

    public void r() {
        b(this.j);
    }

    public void q() {
        a(this.j);
    }

    public void p() {
        b(this.e);
        b(this.k);
        b(this.l);
    }

    @Override // com.huawei.openalliance.ad.views.BaseVideoView.SurfaceListener
    public void onSurfaceDestroyed() {
        A();
        l(false);
    }

    @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
    public void onProgress(int i, int i2) {
        SeekBar seekBar;
        if (!this.S && (seekBar = this.e) != null && i2 > 0) {
            seekBar.setProgress(i);
            a(this.k, i2);
        }
        if (i2 > 0) {
            this.x = i;
            this.C += 200;
            a(i2, this.D, false);
        }
    }

    @Override // com.huawei.openalliance.ad.views.NetworkChangeListener
    public void onNetworkDisconnected() {
        hc hcVar;
        gy gyVar;
        if (this.d == null || this.E || (hcVar = this.J) == null || hcVar.d() || (gyVar = this.s) == null) {
            return;
        }
        g(gyVar.a());
    }

    @Override // com.huawei.openalliance.ad.views.NetworkChangeListener
    public void onNetworkConnectedOrChanged(boolean z) {
        hc hcVar;
        if (this.s == null || this.d == null || this.E || (hcVar = this.J) == null || hcVar.d()) {
            return;
        }
        g(this.s.a(z, this.y));
    }

    @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
    public void onMediaStop(MediaPlayerAgent mediaPlayerAgent, int i) {
        ho.a("LinkedVideoControlBridge", "onMediaStop playtime is %d", Integer.valueOf(i));
        a(i, false, false);
    }

    @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
    public void onMediaStart(MediaPlayerAgent mediaPlayerAgent, int i) {
        if (this.g != null && this.f != null && LinkedNativeViewControlPanel.b() != 0) {
            this.g.setImageResource(LinkedNativeViewControlPanel.b());
        }
        this.E = false;
        v();
        f();
        e(1);
        if (this.u) {
            v();
        } else {
            I();
        }
        l(true);
        n();
        w();
        a(i, this.D, true);
    }

    @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
    public void onMediaPause(MediaPlayerAgent mediaPlayerAgent, int i) {
        a(i, true, false);
    }

    @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
    public void onMediaCompletion(MediaPlayerAgent mediaPlayerAgent, int i) {
        ho.a("LinkedVideoControlBridge", "onMediaCompletion");
        this.E = true;
        a(i, false, true);
        if (this.J.b()) {
            b();
            this.J.a();
            e(2);
            n();
        } else {
            c();
            e(2);
        }
        a(i, this.D, true);
        this.x = 0;
        i(false);
        gy gyVar = this.s;
        if (gyVar != null) {
            gyVar.b();
        }
    }

    @Override // com.huawei.openalliance.ad.media.listener.MediaErrorListener
    public void onError(MediaPlayerAgent mediaPlayerAgent, int i, int i2, int i3) {
        a(i, false, false);
    }

    @Override // com.huawei.openalliance.ad.media.listener.MediaBufferListener
    public void onBufferingStart() {
        View view = this.o;
        if (view == null || view.getVisibility() == 0) {
            return;
        }
        this.o.setVisibility(0);
        v();
    }

    @Override // com.huawei.openalliance.ad.media.listener.MediaBufferListener
    public void onBufferingEnd() {
        View view = this.o;
        if (view == null || view.getVisibility() != 0) {
            return;
        }
        this.o.setVisibility(8);
    }

    public void o() {
        ho.a("LinkedVideoControlBridge", "showProgressControlPanel: ");
        a(this.e);
        a(this.k);
        a(this.l);
    }

    public void n() {
        View view = this.q;
        if (view != null && view.getVisibility() == 0) {
            m();
            return;
        }
        if (this.n != 2) {
            p();
        }
        r();
        s();
    }

    public void m() {
        o();
        q();
        t();
        v();
    }

    public void l() {
        this.w = false;
        VideoView videoView = this.d;
        if (videoView != null) {
            videoView.resumeView();
        }
    }

    public void k() {
        this.w = true;
        VideoView videoView = this.d;
        if (videoView != null) {
            videoView.pauseView();
        }
    }

    public void j() {
        VideoView videoView = this.d;
        if (videoView != null) {
            videoView.pause();
        }
    }

    public void i(boolean z) {
        SeekBar seekBar = this.e;
        if (seekBar != null) {
            if (z && seekBar.getVisibility() != 0) {
                p();
            } else {
                if (z || this.e.getVisibility() == 8) {
                    return;
                }
                o();
            }
        }
    }

    public void i() {
        if (this.J.d()) {
            this.J.a();
            return;
        }
        if (this.n == 1) {
            e(0);
        }
        this.f.setNonWifiAlertMsg(R.string._2130851112_res_0x7f023528);
        this.r.setText(R.string._2130851042_res_0x7f0234e2);
        x();
        b(this.q);
    }

    public void h(boolean z) {
        this.y = z;
    }

    public void h() {
        e(0);
        if (this.q != null) {
            String B = B();
            this.f.setNonWifiAlertMsg(B != null ? this.m.getResources().getString(R.string._2130851054_res_0x7f0234ee, B) : this.m.getResources().getString(R.string._2130851053_res_0x7f0234ed));
            this.r.setText(R.string._2130851060_res_0x7f0234f4);
            x();
            this.J.c();
            b(this.q);
            this.d.h();
        }
    }

    public void g(boolean z) {
        if (z) {
            a((String) null);
            a(0);
            c(0);
            a((Bitmap) null);
        }
        A();
        u();
    }

    public void g() {
        a(this.q);
    }

    public void f(boolean z) {
        if (ho.a()) {
            ho.a("LinkedVideoControlBridge", "setPlayBtn: %s", Boolean.valueOf(z));
        }
        if (this.g == null) {
            return;
        }
        ho.a("LinkedVideoControlBridge", "isDetailViewVisible %s", Boolean.valueOf(this.J.d()));
        if (this.J.d()) {
            v();
        } else {
            this.g.setEnabled(z);
        }
    }

    public void f() {
        VideoView videoView;
        Cdo.a(this.p, 8, 300, 300);
        if (this.p == null || (videoView = this.d) == null) {
            return;
        }
        videoView.setAlpha(1.0f);
    }

    public void e(boolean z) {
        ho.b("LinkedVideoControlBridge", "setMuteBtn: " + z);
        ImageView f = this.f.f();
        if (f != null) {
            f.setImageResource(com.huawei.openalliance.ad.utils.dd.a(true, z));
            f.setSelected(!z);
            com.huawei.openalliance.ad.utils.dd.a(f);
        }
    }

    public void e(int i) {
        int a2;
        ho.a("LinkedVideoControlBridge", "updateButtonState: %d", Integer.valueOf(i));
        this.n = i;
        ImageView imageView = this.j;
        if (imageView == null) {
            return;
        }
        if (i == 0) {
            a2 = LinkedNativeViewControlPanel.a();
        } else if (i == 1) {
            imageView.setImageResource(LinkedNativeViewControlPanel.b());
            return;
        } else if (i != 2) {
            return;
        } else {
            a2 = LinkedNativeViewControlPanel.c();
        }
        imageView.setImageResource(a2);
        com.huawei.openalliance.ad.utils.dd.a(this.j);
    }

    public void e() {
        VideoView videoView = this.d;
        if (videoView != null) {
            videoView.stop();
        }
        g();
        u();
        A();
    }

    public void d(boolean z) {
        String str;
        ho.b("LinkedVideoControlBridge", "toggleMute: " + z);
        if (this.d == null || this.f == null) {
            return;
        }
        e(z);
        if (z) {
            this.d.mute();
            str = "n";
        } else {
            this.d.unmute();
            str = "y";
        }
        this.D = str;
    }

    public void d(int i) {
        this.B = i;
    }

    public void d() {
        com.huawei.openalliance.ad.utils.dj.a(this.c);
    }

    public void c(boolean z) {
        ho.a("LinkedVideoControlBridge", "setCanAutoPlay %s", Boolean.valueOf(z));
        this.t = z;
    }

    public void c(int i) {
        this.z = i;
        VideoView videoView = this.d;
        if (videoView != null) {
            videoView.setDefaultDuration(i);
            a(this.l, i);
        }
    }

    public void c() {
        View view = this.q;
        if (view == null || view.getVisibility() != 0) {
            u();
        }
        v();
        if (this.e != null) {
            i(true);
        }
    }

    public void b(boolean z) {
        if (this.d != null) {
            j(z);
            if (this.n == 2 || this.x == 0) {
                this.v = 0;
                this.x = 0;
                SeekBar seekBar = this.e;
                if (seekBar != null) {
                    seekBar.setProgress(0);
                    this.d.seekTo(this.x);
                }
            }
            this.J.c();
            this.d.setPreferStartPlayTime(this.v);
            this.d.play(z);
        }
    }

    public void b(View view) {
        if (view != null) {
            view.setVisibility(0);
        }
    }

    public void b(int i) {
        this.A = i;
    }

    public void b() {
        l(false);
        v();
    }

    public void a(boolean z) {
        this.F = z;
    }

    public void a(String str) {
        VideoView videoView;
        if (this.f == null || (videoView = this.d) == null) {
            return;
        }
        videoView.setVideoFileUrl(str);
    }

    public void a(a aVar) {
        this.I = aVar;
    }

    public void a(hc hcVar) {
        this.J = hcVar;
    }

    public void a(gy gyVar) {
        this.s = gyVar;
    }

    public void a(PPSActivity.b bVar) {
        this.N = bVar;
    }

    public void a(TextView textView, int i) {
        if (textView == null) {
            return;
        }
        int i2 = i / 1000;
        textView.setText(String.format(Locale.ENGLISH, "%02d:%02d", Integer.valueOf((i2 % 3600) / 60), Integer.valueOf(i2 % 60)));
    }

    public void a(View view) {
        if (view != null) {
            view.setVisibility(8);
        }
    }

    public void a(View.OnClickListener onClickListener) {
        this.H = onClickListener;
    }

    public void a(Drawable drawable) {
        ImageView imageView = this.p;
        if (imageView != null) {
            imageView.setImageDrawable(drawable);
        }
    }

    public void a(Bitmap bitmap) {
        ImageView imageView = this.p;
        if (imageView != null) {
            imageView.setImageBitmap(bitmap);
        }
    }

    public void a(long j) {
        VideoView videoView;
        ho.b("LinkedVideoControlBridge", "autoPlay - delayMs: %d", Long.valueOf(j));
        com.huawei.openalliance.ad.utils.dj.a(this.c);
        if (!this.t || (videoView = this.d) == null) {
            return;
        }
        if (!videoView.isPlaying()) {
            ho.a("LinkedVideoControlBridge", "autoPlay - start delay runnable");
            com.huawei.openalliance.ad.utils.dj.a(this.G, this.c, j);
            return;
        }
        ho.a("LinkedVideoControlBridge", "autoPlay - video is playing");
        gy gyVar = this.s;
        if (gyVar != null) {
            g(gyVar.a(2, this.y));
        }
    }

    public void a(int i) {
        ho.a("LinkedVideoControlBridge", "setPreferStartPlayTime %d", Integer.valueOf(i));
        this.v = i;
        f(i);
        VideoView videoView = this.d;
        if (videoView != null) {
            videoView.setPreferStartPlayTime(i);
        }
    }

    public void a() {
        ho.a("LinkedVideoControlBridge", "setForImageOnly");
        a((VideoView) null);
        i(false);
        v();
        l(false);
    }

    private void z() {
        if (this.f == null) {
            return;
        }
        x();
        this.o = this.f.l();
        View n = this.f.n();
        this.q = n;
        if (n != null) {
            n.setClickable(true);
        }
        ImageView m = this.f.m();
        this.p = m;
        if (m != null) {
            m.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.openalliance.ad.hd.9
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    hd.this.G();
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }
        VideoView videoView = this.d;
        if (videoView != null) {
            videoView.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.openalliance.ad.hd.10
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    hd.this.H();
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }
        e(this.f);
        c(this.f);
        E();
        d(this.f);
        D();
        C();
        b(this.f);
        g();
        l(false);
        u();
    }

    private void m(boolean z) {
        VideoView videoView = this.d;
        if (videoView == null) {
            return;
        }
        MediaState currentState = videoView.getCurrentState();
        ho.a("LinkedVideoControlBridge", "currentState %s", currentState.toString());
        if (currentState.isState(MediaState.State.PLAYING)) {
            this.d.pause();
        } else if (currentState.isState(MediaState.State.PREPARING)) {
            this.d.stop();
            A();
        }
        g();
        h();
    }

    private void l(boolean z) {
        this.u = !z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k(boolean z) {
        String str;
        ho.b("LinkedVideoControlBridge", "switchSound: " + z);
        VideoView videoView = this.d;
        if (videoView == null) {
            return;
        }
        if (z) {
            videoView.unmute();
            str = "y";
        } else {
            videoView.mute();
            str = "n";
        }
        this.D = str;
        b(str);
        com.huawei.openalliance.ad.utils.dj.a(this.f6899a);
        if (this.d.isPlaying()) {
            I();
        }
    }

    private void j(boolean z) {
        a aVar = this.I;
        if (aVar != null) {
            aVar.a(z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g(int i) {
        ho.b("LinkedVideoControlBridge", "strategyMode is %d", Integer.valueOf(i));
        if (i == 1) {
            K();
            return;
        }
        if (i == 101) {
            a(false, true);
            return;
        }
        if (i == 102) {
            a(true, false);
        } else if (i == 201) {
            m(true);
        } else {
            if (i != 202) {
                return;
            }
            m(false);
        }
    }

    private void f(int i) {
        int i2 = this.z;
        if (i2 != 0) {
            int i3 = (i * 100) / i2;
            this.x = i3;
            ho.a("LinkedVideoControlBridge", " currentProgress is %d", Integer.valueOf(i3));
        } else {
            int i4 = this.A;
            if (i4 != 0) {
                ho.a("LinkedVideoControlBridge", "calculateCurrentProgress defaultVideoDuration %d", Integer.valueOf(i4));
                int i5 = (i * 100) / this.A;
                this.x = i5;
                ho.a("LinkedVideoControlBridge", " currentProgress is %d", Integer.valueOf(i5));
            }
        }
        if (this.x >= 100) {
            ho.b("LinkedVideoControlBridge", "progress bigger than 100, play from start.");
            this.x = 0;
        }
    }

    private void e(LinkedNativeViewControlPanel linkedNativeViewControlPanel) {
        ImageView e = linkedNativeViewControlPanel.e();
        this.g = e;
        if (e != null) {
            e.setClickable(true);
            this.g.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.openalliance.ad.hd.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    ho.a("LinkedVideoControlBridge", "onClick, currentState %s", hd.this.d.getCurrentState().toString());
                    if (hd.this.I != null) {
                        if (hd.this.d.isPlaying()) {
                            hd.this.I.c();
                        } else {
                            hd.this.I.b();
                        }
                    }
                    hd.this.F();
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            if (LinkedNativeViewControlPanel.a() > 0) {
                this.g.setImageResource(LinkedNativeViewControlPanel.a());
                com.huawei.openalliance.ad.utils.dd.a(this.g);
            }
            u();
        }
    }

    private void d(LinkedNativeViewControlPanel linkedNativeViewControlPanel) {
        SeekBar h = linkedNativeViewControlPanel.h();
        this.e = h;
        if (h != null) {
            p();
            this.e.setOnSeekBarChangeListener(this.R);
        }
        this.k = linkedNativeViewControlPanel.j();
        this.l = linkedNativeViewControlPanel.k();
    }

    private void c(LinkedNativeViewControlPanel linkedNativeViewControlPanel) {
        ImageView f = linkedNativeViewControlPanel.f();
        this.h = f;
        if (f != null) {
            s();
            this.h.setOnClickListener(this.M);
        }
    }

    private void b(String str) {
        ha.a(str);
    }

    private void b(LinkedNativeViewControlPanel linkedNativeViewControlPanel) {
        LinkedWifiAlertPlayButton o = linkedNativeViewControlPanel.o();
        this.r = o;
        if (o != null) {
            o.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.openalliance.ad.hd.11
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    CharSequence text = hd.this.r.getText();
                    String string = hd.this.m != null ? hd.this.m.getResources().getString(R.string._2130851060_res_0x7f0234f4) : null;
                    if (string != null && string.equals(text)) {
                        hd.this.y = false;
                        if (hd.this.I != null) {
                            hd.this.I.a();
                        }
                    }
                    if (hd.this.s != null) {
                        hd hdVar = hd.this;
                        hdVar.g(hdVar.s.a(1, hd.this.y));
                    }
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }
    }

    private void a(boolean z, boolean z2) {
        VideoView videoView = this.d;
        if (videoView == null || videoView.isPlaying()) {
            return;
        }
        g();
        MediaState currentState = this.d.getCurrentState();
        ho.a("LinkedVideoControlBridge", "currentState %s", currentState.toString());
        if (z2 || !currentState.isState(MediaState.State.PLAYBACK_COMPLETED)) {
            if (!currentState.a()) {
                A();
            }
            if (!z) {
                com.huawei.openalliance.ad.utils.dj.a(this.c);
            }
            ho.a("LinkedVideoControlBridge", "playVideo, viewPaused is %s", Boolean.valueOf(this.w));
            if ((this.t || z2) && !this.w) {
                b(z);
                e(1);
            } else if (this.d.isPlaying()) {
                return;
            } else {
                u();
            }
            n();
            w();
        }
    }

    private void a(VideoView videoView) {
        this.d = videoView;
    }

    private void a(LinkedNativeViewControlPanel linkedNativeViewControlPanel) {
        this.f = linkedNativeViewControlPanel;
        z();
    }

    private void a(int i, boolean z, boolean z2) {
        if (z2 || this.J.d()) {
            e(2);
        } else {
            e(0);
        }
        f(i);
        d();
        if (z2) {
            i = 0;
        }
        this.v = i;
        com.huawei.openalliance.ad.utils.dj.a(this.f6899a);
        if (this.g != null && LinkedNativeViewControlPanel.a() != 0) {
            this.g.setImageResource(LinkedNativeViewControlPanel.a());
            com.huawei.openalliance.ad.utils.dd.a(this.g);
        }
        if (!z) {
            A();
            l(false);
        }
        if (!z2) {
            u();
        }
        n();
    }

    private void a(int i, String str, boolean z) {
        int i2 = this.C;
        if (i2 >= 1000 || i2 == 0 || z) {
            ho.b("LinkedVideoControlBridge", "set progress from linked view, progress: %s, soundSwitch: %s ", Integer.valueOf(i), str);
            gz gzVar = new gz();
            gzVar.b(true);
            gzVar.a(i);
            gzVar.a(str);
            ha.a(gzVar);
            this.C = 0;
            if (this.F) {
                a(i, str);
            }
        }
    }

    private void a(int i, String str) {
        ho.b("LinkedVideoControlBridge", "sendLinkedAdChangeBroadcast");
        Intent intent = new Intent("com.huawei.hms.pps.action.LANDING_AD_STATUS_CHANGED");
        intent.putExtra("linked_ad_play_progress", i);
        intent.putExtra("linked_ad_sound_switch", str);
        intent.setPackage(this.m.getPackageName());
        this.m.sendBroadcast(intent);
    }

    private void K() {
        J();
    }

    private void J() {
        VideoView videoView = this.d;
        if (videoView == null) {
            return;
        }
        if (videoView.getCurrentState().a()) {
            this.d.pause();
        }
        g();
        i();
    }

    private void I() {
        com.huawei.openalliance.ad.utils.dj.a(this.f6899a);
        com.huawei.openalliance.ad.utils.dj.a(this.K, this.f6899a, 200L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void H() {
        VideoView videoView = this.d;
        if (videoView != null) {
            this.H.onClick(videoView);
            y();
            w();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void G() {
        View.OnClickListener onClickListener = this.H;
        if (onClickListener != null) {
            onClickListener.onClick(this.p);
        }
        y();
        w();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void F() {
        if (this.d == null) {
            return;
        }
        i(true);
        com.huawei.openalliance.ad.utils.dj.a(this.c);
        if (this.d.isPlaying()) {
            com.huawei.openalliance.ad.utils.dj.a(this.f6899a);
            this.d.pause();
            e(0);
        } else {
            gy gyVar = this.s;
            if (gyVar != null) {
                g(gyVar.a(1, this.y));
            }
        }
    }

    private void E() {
        LinkedNativeViewControlPanel linkedNativeViewControlPanel = this.f;
        if (linkedNativeViewControlPanel == null) {
            return;
        }
        ImageView g = linkedNativeViewControlPanel.g();
        this.i = g;
        if (g != null) {
            g.setOnClickListener(this.P);
        }
    }

    private void D() {
        ImageView i = this.f.i();
        this.j = i;
        if (i != null) {
            i.setOnClickListener(this.Q);
        }
    }

    private void C() {
        VideoView videoView = this.d;
        if (videoView != null) {
            videoView.addMediaStateListener(this);
            this.d.addMediaBufferListener(this);
            this.d.addMediaErrorListener(this);
            this.d.addNetworkChangeListener(this);
            this.d.setSurfaceListener(this);
        }
    }

    private String B() {
        int i = this.B;
        if (i == 0) {
            return null;
        }
        long j = ((100 - this.x) * i) / 100;
        ho.a("LinkedVideoControlBridge", " left data is %d", Long.valueOf(j));
        if (j == 0) {
            return null;
        }
        return com.huawei.openalliance.ad.utils.ae.a(this.m, j);
    }

    private void A() {
        if (this.p == null) {
            return;
        }
        ho.a("LinkedVideoControlBridge", "showPreviewView");
        Animation animation = this.p.getAnimation();
        if (animation != null) {
            animation.cancel();
        }
        Cdo.a((View) this.p, true);
        VideoView videoView = this.d;
        if (videoView != null) {
            videoView.setAlpha(0.0f);
        }
    }

    public hd(Context context, VideoView videoView, LinkedNativeViewControlPanel linkedNativeViewControlPanel) {
        this.m = context;
        a(videoView);
        a(linkedNativeViewControlPanel);
    }
}
