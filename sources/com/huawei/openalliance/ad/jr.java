package com.huawei.openalliance.ad;

import android.content.Context;
import android.view.View;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.media.MediaPlayerAgent;
import com.huawei.openalliance.ad.media.listener.MediaStateListener;
import com.huawei.openalliance.ad.processor.eventbeans.a;
import com.huawei.openalliance.ad.views.VideoView;
import com.huawei.operation.utils.Constants;
import java.lang.ref.WeakReference;

/* loaded from: classes5.dex */
public class jr implements jp {
    private static int o;
    private static int p;

    /* renamed from: a, reason: collision with root package name */
    private WeakReference<View> f7141a;
    private MediaStateListener b;
    private final String c;
    private final String d;
    private final ou f;
    private final ContentRecord g;
    private int k;
    private long l;
    private int m;
    private int n;
    private long q;
    private jh r;
    private VideoView s;
    private final boolean t;
    private final jl e = jl.a();
    private int h = 0;
    private final boolean[] i = new boolean[5];
    private boolean j = false;

    public void c() {
        ho.a(l(), "hasShowStart %s", Boolean.valueOf(this.j));
        this.q = com.huawei.openalliance.ad.utils.ao.c();
        if (f()) {
            a(this.f7141a.get().getContext(), e());
        }
        if (this.j) {
            i();
            if (this.t) {
                return;
            }
            g();
        }
    }

    @Override // com.huawei.openalliance.ad.jp
    public void b() {
        this.e.a(this.c);
        this.e.a(this.d);
        jh jhVar = this.r;
        if (jhVar != null) {
            jhVar.i();
            this.r = null;
        }
        VideoView videoView = this.s;
        if (videoView != null) {
            videoView.removeMediaStateListener(this.b);
            this.s = null;
            this.b = null;
        }
    }

    @Override // com.huawei.openalliance.ad.jp
    public void a(VideoView videoView) {
        if (videoView == null) {
            ho.c(l(), "video view is null.");
            return;
        }
        ho.b(l(), "set video view");
        this.s = videoView;
        MediaStateListener mediaStateListener = new MediaStateListener() { // from class: com.huawei.openalliance.ad.jr.1
            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onMediaPause(MediaPlayerAgent mediaPlayerAgent, int i) {
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onMediaStop(MediaPlayerAgent mediaPlayerAgent, int i) {
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onProgress(int i, int i2) {
                jr.this.a(i, i2);
                int unused = jr.o = i2;
                int unused2 = jr.p = i;
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onMediaStart(MediaPlayerAgent mediaPlayerAgent, int i) {
                jr.this.c();
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onMediaCompletion(MediaPlayerAgent mediaPlayerAgent, int i) {
                int unused = jr.p = 100;
            }
        };
        this.b = mediaStateListener;
        this.s.addMediaStateListener(mediaStateListener);
    }

    @Override // com.huawei.openalliance.ad.jp
    public void a(View view) {
        ho.a(l(), "view is %s", view.getClass().getSimpleName());
        this.f7141a = new WeakReference<>(view);
        if (view instanceof VideoView) {
            a((VideoView) view);
        }
        this.r = new jh(this.f7141a.get()) { // from class: com.huawei.openalliance.ad.jr.3
            @Override // com.huawei.openalliance.ad.jh
            protected void a(long j, int i) {
                super.a(j, i);
                ho.a(jr.this.l(), "onViewShowEnd, duration is %s, per %s", Long.valueOf(j), Integer.valueOf(i));
                if (i > jr.this.m) {
                    jr.this.m = i;
                }
                jr jrVar = jr.this;
                jrVar.a(j, jrVar.m);
            }

            @Override // com.huawei.openalliance.ad.jh
            protected void a(int i) {
                super.a(i);
                ho.a(jr.this.l(), "onUpdateViewShowArea, per %s", Integer.valueOf(i));
                if (i > jr.this.m) {
                    jr.this.m = i;
                }
            }

            @Override // com.huawei.openalliance.ad.jh
            protected void a() {
                super.a();
                ho.a(jr.this.l(), "onViewShowStart.");
                jr.this.j = true;
            }
        };
        this.f7141a.get().addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: com.huawei.openalliance.ad.jr.4
            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewDetachedFromWindow(View view2) {
                String l = jr.this.l();
                Object[] objArr = new Object[1];
                objArr[0] = jr.this.r == null ? Constants.NULL : "not null";
                ho.b(l, "onViewDetachedFromWindow, viewMonitor is %s", objArr);
                if (jr.this.r != null) {
                    jr.this.r.i();
                }
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewAttachedToWindow(View view2) {
                String l = jr.this.l();
                Object[] objArr = new Object[1];
                objArr[0] = jr.this.r == null ? Constants.NULL : "not null";
                ho.b(l, "onViewAttachedToWindow, viewMonitor is %s", objArr);
                if (jr.this.r != null) {
                    jr.this.r.h();
                }
            }
        });
        String l = l();
        Object[] objArr = new Object[1];
        objArr[0] = this.r == null ? Constants.NULL : "not null";
        ho.a(l, "viewMonitor is %s", objArr);
        jh jhVar = this.r;
        if (jhVar != null) {
            jhVar.h();
        }
    }

    public void a(int i, int i2) {
        ho.a(l(), "doOnProgress, maxShowArea %s, percentage %s, playTime is %s", Integer.valueOf(this.m), Integer.valueOf(i), Integer.valueOf(i2));
        o = i2;
        p = i;
        if (this.m >= this.k) {
            int i3 = this.h;
            if ((i3 == 2 || i2 <= this.l) && (i3 != 2 || i < this.n)) {
                return;
            }
            g();
            a(i2, this.m, -1);
        }
    }

    @Override // com.huawei.openalliance.ad.jp
    public void a() {
        g();
        h();
        a(o, this.m, 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String l() {
        return "VideoMzMonitor_" + hashCode();
    }

    private View k() {
        WeakReference<View> weakReference = this.f7141a;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    private String j() {
        if (k() instanceof jk) {
            return com.huawei.openalliance.ad.utils.dd.a((jk) k());
        }
        ho.c(l(), "getSlotPosition failed, view is not IView");
        return "";
    }

    private void i() {
        if (this.i[0]) {
            ho.a(l(), "hasReport showStart");
            return;
        }
        this.g.f(this.q);
        this.g.c(String.valueOf(this.q));
        this.f.a(this.g);
        ho.b(l(), "callHwShowStart");
        ou ouVar = this.f;
        if (ouVar != null) {
            ouVar.b();
            this.i[0] = true;
        }
    }

    private void h() {
        boolean[] zArr = this.i;
        if (zArr[4]) {
            return;
        }
        zArr[4] = true;
        ho.b(l(), "callMzClick");
        this.e.a(this.d, this.g);
    }

    private void g() {
        if (this.i[3]) {
            return;
        }
        ho.b(l(), "callMzOnImp");
        this.e.a(1, this.c, k(), this.g);
        this.i[3] = true;
    }

    private boolean f() {
        ContentRecord contentRecord = this.g;
        return com.huawei.openalliance.ad.utils.c.a(contentRecord == null ? null : contentRecord.aZ());
    }

    private String e() {
        return com.huawei.openalliance.ad.utils.ao.b("yyyyMMdd") + com.huawei.openalliance.ad.constant.Constants.LINK + this.g.m() + com.huawei.openalliance.ad.constant.Constants.LINK + this.g.n();
    }

    private void d() {
        ContentRecord contentRecord = this.g;
        if (contentRecord == null) {
            this.k = 50;
            this.l = 2000L;
            this.n = 50;
            return;
        }
        this.h = contentRecord.k();
        this.k = this.g.c();
        this.l = this.g.b();
        this.n = this.g.d();
        ho.b(l(), "exposeType %s, showRatio %s, showTime %s, percentage %s.", Integer.valueOf(this.h), Integer.valueOf(this.k), Long.valueOf(this.l), Integer.valueOf(this.n));
        if (this.k == 0) {
            this.k = 50;
        }
        if (this.l == 0) {
            this.l = 2000L;
        }
        if (this.n == 0) {
            this.n = 50;
        }
        if (this.k > 100) {
            this.k = 100;
        }
    }

    private boolean a(ContentRecord contentRecord) {
        return contentRecord != null && contentRecord.e() == 60;
    }

    private void a(final Context context, final String str) {
        com.huawei.openalliance.ad.utils.m.f(new Runnable() { // from class: com.huawei.openalliance.ad.jr.2
            @Override // java.lang.Runnable
            public void run() {
                new com.huawei.openalliance.ad.analysis.c(context).b(str, "task support mz but has impSmartCtrl", jr.this.g);
            }
        });
    }

    private void a(long j, int i, int i2) {
        if (this.i[1]) {
            ho.a(l(), "hasReport Imp");
            return;
        }
        ho.b(l(), "callHwShow, duration is %s, maxShowAreaPercentage is %s", Long.valueOf(j), Integer.valueOf(i));
        a.C0207a e = new a.C0207a().a(Integer.valueOf(i)).a(Long.valueOf(j)).a(com.huawei.openalliance.ad.utils.b.a(k())).d(j()).e(vd.b(k()));
        if (i2 != -1) {
            e.b(Integer.valueOf(i2));
        }
        ou ouVar = this.f;
        if (ouVar != null) {
            ouVar.a(e.a());
            this.i[1] = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(long j, int i) {
        if (this.i[2]) {
            ho.a(l(), "hasReport phyShow");
            return;
        }
        ho.b(l(), "callHwPhyShow, duration is %s, maxShowAreaPercentage is %s", Long.valueOf(j), Integer.valueOf(i));
        if (this.f != null) {
            ot otVar = new ot();
            otVar.a(Integer.valueOf(p));
            otVar.b(Integer.valueOf(o));
            this.f.a(j, i, otVar);
            this.i[2] = true;
        }
    }

    public jr(String str, String str2, ou ouVar, ContentRecord contentRecord) {
        this.c = str;
        this.d = str2;
        this.f = ouVar;
        this.g = contentRecord;
        d();
        this.t = a(contentRecord);
    }
}
