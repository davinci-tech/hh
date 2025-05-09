package com.huawei.openalliance.ad;

import android.content.Context;
import android.view.View;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.processor.eventbeans.a;
import com.huawei.openalliance.ad.views.VideoView;
import java.lang.ref.WeakReference;

/* loaded from: classes5.dex */
public class jq implements jp {

    /* renamed from: a, reason: collision with root package name */
    private WeakReference<View> f7136a;
    private final String b;
    private final String c;
    private final ou e;
    private final ContentRecord f;
    private int h;
    private long i;
    private long j;
    private long k;
    private int l;
    private jh o;
    private final jl d = jl.a();
    private final boolean[] g = new boolean[5];
    private final String m = Constants.IMP_EVENT_MONITOR_ID + hashCode();
    private boolean n = false;

    @Override // com.huawei.openalliance.ad.jp
    public void a(VideoView videoView) {
    }

    @Override // com.huawei.openalliance.ad.jp
    public void b() {
        this.d.a(this.b);
        this.d.a(this.c);
        jh jhVar = this.o;
        if (jhVar != null) {
            jhVar.i();
            this.o = null;
        }
    }

    @Override // com.huawei.openalliance.ad.jp
    public void a(final View view) {
        ho.b(m(), "view is %s", view.getClass().getSimpleName());
        this.f7136a = new WeakReference<>(view);
        this.o = new jh(this.f7136a.get()) { // from class: com.huawei.openalliance.ad.jq.1
            @Override // com.huawei.openalliance.ad.jh
            protected void a(long j, int i) {
                super.a(j, i);
                ho.a(jq.this.m(), "onViewShowEnd, dur %s, maxPer %s", Long.valueOf(j), Integer.valueOf(i));
                com.huawei.openalliance.ad.utils.dj.a(jq.this.m);
                jq.this.a(j, i);
                if (j <= jq.this.i || i < jq.this.h) {
                    return;
                }
                jq.this.a(j, i, -1);
            }

            @Override // com.huawei.openalliance.ad.jh
            protected void a(int i) {
                super.a(i);
                ho.a(jq.this.m(), "onUpdateViewShowArea, per %s", Integer.valueOf(i));
                if (i > jq.this.l) {
                    jq.this.l = i;
                }
                if (i < jq.this.h) {
                    jq.this.f();
                } else {
                    jq.this.e();
                }
            }

            @Override // com.huawei.openalliance.ad.jh
            protected void a() {
                super.a();
                if (jq.this.j()) {
                    jq.this.a(view.getContext(), jq.this.d());
                }
                jq.this.k = com.huawei.openalliance.ad.utils.ao.c();
                jq.this.g();
                jq.this.i();
            }
        };
        this.f7136a.get().addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: com.huawei.openalliance.ad.jq.2
            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewDetachedFromWindow(View view2) {
                String m = jq.this.m();
                Object[] objArr = new Object[1];
                objArr[0] = jq.this.o == null ? "not null" : com.huawei.operation.utils.Constants.NULL;
                ho.b(m, "onViewDetachedFromWindow, viewMonitor is %s", objArr);
                if (jq.this.o != null) {
                    ho.a(jq.this.m(), "viewMonitor is not null, call onViewDetachedFromWindow");
                    jq.this.o.i();
                }
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewAttachedToWindow(View view2) {
                String m = jq.this.m();
                Object[] objArr = new Object[1];
                objArr[0] = jq.this.o == null ? "not null" : com.huawei.operation.utils.Constants.NULL;
                ho.b(m, "onViewAttachedToWindow, viewMonitor is %s", objArr);
                if (jq.this.o != null) {
                    jq.this.o.h();
                }
            }
        });
        if (this.o != null) {
            ho.a(m(), "viewMonitor is not null, call onViewAttachedToWindow");
            this.o.h();
        }
    }

    @Override // com.huawei.openalliance.ad.jp
    public void a() {
        g();
        h();
        a(com.huawei.openalliance.ad.utils.ao.c() - this.k, this.l, 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String m() {
        return "ImageMzMonitor_" + hashCode();
    }

    private View l() {
        WeakReference<View> weakReference = this.f7136a;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    private String k() {
        if (l() instanceof jk) {
            return com.huawei.openalliance.ad.utils.dd.a((jk) l());
        }
        ho.c(m(), "getSlotPosition failed, view is not IView");
        return "";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean j() {
        ContentRecord contentRecord = this.f;
        return com.huawei.openalliance.ad.utils.c.a(contentRecord == null ? null : contentRecord.aZ());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        if (this.g[0]) {
            return;
        }
        this.f.f(this.k);
        this.f.c(String.valueOf(this.k));
        this.e.a(this.f);
        ho.b(m(), "callHwShowStart");
        ou ouVar = this.e;
        if (ouVar != null) {
            ouVar.b();
            this.g[0] = true;
        }
    }

    private void h() {
        boolean[] zArr = this.g;
        if (zArr[4]) {
            return;
        }
        zArr[4] = true;
        ho.b(m(), "callMzClick");
        this.d.a(this.c, this.f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        boolean[] zArr = this.g;
        if (zArr[3]) {
            return;
        }
        zArr[3] = true;
        ho.b(m(), "callMzOnImp");
        this.d.a(this.b, l(), this.f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        int i;
        if (this.n) {
            this.n = false;
            com.huawei.openalliance.ad.utils.dj.a(this.m);
            long c = com.huawei.openalliance.ad.utils.ao.c() - this.j;
            if (c <= this.i || (i = this.l) < this.h) {
                return;
            }
            a(c, i, -1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        if (this.n) {
            return;
        }
        this.n = true;
        this.j = com.huawei.openalliance.ad.utils.ao.c();
        com.huawei.openalliance.ad.utils.dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.jq.4
            @Override // java.lang.Runnable
            public void run() {
                jq.this.a(com.huawei.openalliance.ad.utils.ao.c() - jq.this.j, jq.this.l, -1);
            }
        }, this.m, this.i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String d() {
        return com.huawei.openalliance.ad.utils.ao.b("yyyyMMdd") + Constants.LINK + this.f.m() + Constants.LINK + this.f.n();
    }

    private void c() {
        ContentRecord contentRecord = this.f;
        if (contentRecord == null) {
            this.h = 50;
            this.i = 1000L;
            return;
        }
        this.h = contentRecord.c();
        this.i = this.f.b();
        ho.b(m(), "showRatio %s, showTime %s.", Integer.valueOf(this.h), Long.valueOf(this.i));
        if (this.h == 0) {
            this.h = 50;
        }
        if (this.i == 0) {
            this.i = 1000L;
        }
        if (this.h > 100) {
            this.h = 100;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final Context context, final String str) {
        com.huawei.openalliance.ad.utils.m.f(new Runnable() { // from class: com.huawei.openalliance.ad.jq.3
            @Override // java.lang.Runnable
            public void run() {
                new com.huawei.openalliance.ad.analysis.c(context).b(str, "task support mz but has impSmartCtrl", jq.this.f);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(long j, int i, int i2) {
        if (this.g[1]) {
            return;
        }
        ho.b(m(), "callHwShow, duration is %s, maxShowAreaPercentage is %s", Long.valueOf(j), Integer.valueOf(i));
        a.C0207a a2 = new a.C0207a().a(Integer.valueOf(i)).a(Long.valueOf(j)).e(vd.b(l())).d(k()).a(com.huawei.openalliance.ad.utils.b.a(l()));
        if (i2 != -1) {
            a2.b(Integer.valueOf(i2));
        }
        ou ouVar = this.e;
        if (ouVar != null) {
            ouVar.a(a2.a());
            this.g[1] = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(long j, int i) {
        if (this.g[2]) {
            return;
        }
        ho.b(m(), "callHwPhyShow, duration is %s, maxShowAreaPercentage is %s", Long.valueOf(j), Integer.valueOf(i));
        ou ouVar = this.e;
        if (ouVar != null) {
            ouVar.a(j, i);
            this.g[2] = true;
        }
    }

    public jq(String str, String str2, ou ouVar, ContentRecord contentRecord) {
        this.b = str;
        this.c = str2;
        this.e = ouVar;
        this.f = contentRecord;
        c();
    }
}
