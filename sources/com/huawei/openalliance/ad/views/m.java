package com.huawei.openalliance.ad.views;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import com.huawei.openalliance.ad.beans.metadata.InteractCfg;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.fh;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.inter.data.MaterialClickInfo;
import com.huawei.openalliance.ad.jb;
import com.huawei.openalliance.ad.jh;
import com.huawei.openalliance.ad.lo;
import com.huawei.openalliance.ad.lz;
import com.huawei.openalliance.ad.mo;
import com.huawei.openalliance.ad.ob;
import com.huawei.openalliance.ad.os;
import com.huawei.openalliance.ad.ro;
import com.huawei.openalliance.ad.th;
import com.huawei.openalliance.ad.utils.Cdo;
import com.huawei.openalliance.ad.utils.dj;

/* loaded from: classes9.dex */
public abstract class m<P extends ob> extends RelativeLayout implements com.huawei.openalliance.ad.views.interfaces.n {

    /* renamed from: a, reason: collision with root package name */
    protected P f8116a;
    protected lz b;
    protected ContentRecord c;
    protected int d;
    protected jb e;
    private boolean f;
    private Long g;
    private View h;
    private ro i;
    private boolean j;
    private int k;
    private int l;
    private int m;
    private int n;
    private MaterialClickInfo o;
    private View.OnTouchListener p;
    private View.OnTouchListener q;
    private View.OnTouchListener r;
    private jh s;

    @Override // com.huawei.openalliance.ad.views.interfaces.n
    public boolean e() {
        return false;
    }

    protected void f() {
    }

    @Override // com.huawei.openalliance.ad.lm
    public View getOpenMeasureView() {
        return this;
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.IViewLifeCycle
    public void pauseView() {
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.IViewLifeCycle
    public void resumeView() {
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.n
    public void setAudioFocusType(int i) {
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.n
    public void setDisplayDuration(int i) {
        this.d = i;
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.n
    public void setAdMediator(jb jbVar) {
        this.e = jbVar;
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.n
    public void setAdContent(ContentRecord contentRecord) {
        this.c = contentRecord;
        if (contentRecord.aJ() == null) {
            this.k = fh.b(getContext()).J();
            this.m = fh.b(getContext()).M();
            this.l = fh.b(getContext()).L();
        } else {
            InteractCfg aJ = contentRecord.aJ();
            this.k = (aJ.b() == null || aJ.b().intValue() <= 0) ? fh.b(getContext()).J() : aJ.b().intValue();
            this.m = (aJ.c() == null || aJ.c().intValue() <= 0) ? fh.b(getContext()).M() : aJ.c().intValue();
            this.l = (aJ.d() == null || aJ.d().intValue() <= 0) ? fh.b(getContext()).L() : aJ.d().intValue();
            this.n = aJ.f().intValue();
        }
    }

    @Override // android.view.View
    protected void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        jh jhVar = this.s;
        if (jhVar != null) {
            jhVar.j();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ho.b("PPSBaseView", "detached from window");
        jh jhVar = this.s;
        if (jhVar != null) {
            jhVar.i();
        }
        this.b.b();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        jh jhVar = this.s;
        if (jhVar != null) {
            jhVar.h();
        }
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.n
    public boolean i() {
        int z = os.z(this.c.V());
        if (1 == z) {
            return this.f8116a.h();
        }
        if (2 == z) {
            return this.f8116a.h() && this.j;
        }
        return false;
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.n
    public void h() {
        P p = this.f8116a;
        if (p != null) {
            p.b(this.g);
        }
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.n
    public jb getAdMediator() {
        return this.e;
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.n
    public void g() {
        P p = this.f8116a;
        if (p != null) {
            p.a(this.g);
        }
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.IViewLifeCycle
    public void destroyView() {
        ro roVar = this.i;
        if (roVar != null) {
            roVar.b();
        }
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.n
    public void d() {
        this.e.s();
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.n
    public void c(int i) {
        this.e.f(i);
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.n
    public void c() {
        ho.b("PPSBaseView", "notifyAdLoaded");
        this.f = true;
        this.g = Long.valueOf(System.currentTimeMillis());
        this.e.a(this.c);
    }

    public void b(int i) {
        this.e.c(i);
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.n
    public void b() {
        ho.b("PPSBaseView", "show ad");
        this.f8116a.a(this.c);
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.n
    public void a(lz lzVar) {
        if (lzVar != null) {
            this.b = lzVar;
        }
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.n
    public void a(View view, Integer num) {
        this.h = view;
        if (view != null) {
            view.setOnTouchListener(this.p);
        }
        ContentRecord contentRecord = this.c;
        String V = contentRecord == null ? null : contentRecord.V();
        int n = os.n(V);
        if (ho.a()) {
            ho.a("PPSBaseView", "ctrlswitch:%s", V);
            ho.a("PPSBaseView", "splashpro mode:%d, splashInteractCfg: %s", Integer.valueOf(n), num);
        }
        if (n == 2) {
            setOnTouchListener(this.r);
            if (num == null) {
                return;
            }
            if (1 == num.intValue() || 4 == num.intValue()) {
                setOnTouchListener(this.q);
                if (this.h == null || 1 != num.intValue()) {
                    return;
                }
                this.h.setOnTouchListener(null);
                return;
            }
            if (2 == num.intValue() || 3 == num.intValue()) {
                setOnTouchListener(this.r);
                j();
                if (this.h == null || 2 != num.intValue()) {
                    return;
                }
                this.h.setOnTouchListener(null);
            }
        }
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.n
    public void a(int i, int i2) {
        ho.b("PPSBaseView", "user click skip button");
        this.f8116a.a(i, i2, this.g);
        this.b.j();
        this.b.b();
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.n
    public void a(int i) {
        this.e.b(i);
    }

    public void a() {
        this.e.B();
    }

    private void j() {
        ro roVar = new ro(getContext());
        this.i = roVar;
        roVar.a(new a());
        this.i.a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() != 0) {
            return true;
        }
        setOnTouchListener(null);
        view.setEnabled(false);
        float rawX = motionEvent.getRawX();
        float rawY = motionEvent.getRawY();
        if (ho.a()) {
            ho.a("PPSBaseView", "touch down image x=%f, y=%f", Float.valueOf(rawX), Float.valueOf(rawY));
        }
        MaterialClickInfo b = th.b(view, motionEvent);
        if (b != null) {
            b.c(0);
            b.a(Float.valueOf(com.huawei.openalliance.ad.utils.d.j(getContext())));
        }
        P p = this.f8116a;
        int i = (int) rawX;
        int i2 = (int) rawY;
        ContentRecord contentRecord = this.c;
        p.a(i, i2, contentRecord, this.g, b, 2 == os.n(contentRecord.V()) ? 17 : 7);
        this.b.a(mo.CLICK);
        return true;
    }

    class a implements ro.a {
        @Override // com.huawei.openalliance.ad.ro.a
        public void a(float f, float f2, float f3) {
            float sqrt = (float) Math.sqrt((f * f) + (f2 * f2) + (f3 * f3));
            if (ho.a()) {
                ho.a("PPSBaseView", "accLimitNew: %s, xAcc: %s yAcc: %s zAcc: %s, sqrtAcc: %s", Integer.valueOf(m.this.m), Float.valueOf(f), Float.valueOf(f2), Float.valueOf(f3), Float.valueOf(sqrt));
            }
            if (m.this.g == null || sqrt < m.this.m) {
                return;
            }
            ho.b("PPSBaseView", "meet, accLimitNew: %s, sqrtAcc: %s", Integer.valueOf(m.this.m), Float.valueOf(sqrt));
            m.this.i.b();
            m.this.f8116a.a(0, 0, m.this.c, m.this.g, new MaterialClickInfo.a().c((Integer) 2).b(Integer.valueOf(m.this.getWidth()) + "*" + Integer.valueOf(m.this.getHeight())).a(Float.valueOf(com.huawei.openalliance.ad.utils.d.j(m.this.getContext()))).a(), 19);
            m.this.b.a(mo.CLICK);
        }

        private a() {
        }
    }

    public m(Context context) {
        super(context);
        this.b = new lo();
        this.f = false;
        this.g = null;
        this.j = false;
        this.p = new View.OnTouchListener() { // from class: com.huawei.openalliance.ad.views.m.1
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return m.this.a(view, motionEvent);
            }
        };
        this.q = new View.OnTouchListener() { // from class: com.huawei.openalliance.ad.views.m.2
            private float b;
            private float c;

            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return a(motionEvent);
            }

            private boolean a(MotionEvent motionEvent) {
                if (motionEvent.getAction() == 0) {
                    this.b = motionEvent.getX();
                    this.c = motionEvent.getY();
                    if (ho.a()) {
                        ho.a("PPSBaseView", "startX = %s, startY = %s", Float.valueOf(this.b), Float.valueOf(this.c));
                    }
                    m mVar = m.this;
                    mVar.o = th.b(mVar, motionEvent);
                }
                if (2 == motionEvent.getAction()) {
                    m.this.j = true;
                    float x = motionEvent.getX();
                    float y = motionEvent.getY();
                    if (ho.a()) {
                        ho.a("PPSBaseView", " endX= %s, endY = %s, startX - endX= %s, startY - endY= %s", Float.valueOf(x), Float.valueOf(y), Float.valueOf(this.b - x), Float.valueOf(this.c - y));
                    }
                    if (Cdo.a(m.this.n, m.this.k, this.b - x, this.c - y)) {
                        th.b(m.this, motionEvent, 1, m.this.o);
                        m.this.setOnTouchListener(null);
                        m.this.f8116a.a(0, 0, m.this.c, m.this.g, m.this.o, 18);
                        m.this.o = null;
                        m.this.b.a(mo.CLICK);
                    }
                }
                return true;
            }
        };
        this.r = new View.OnTouchListener() { // from class: com.huawei.openalliance.ad.views.m.3
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 0) {
                    m.this.j = true;
                }
                return true;
            }
        };
        this.s = new jh(this) { // from class: com.huawei.openalliance.ad.views.m.4
            @Override // com.huawei.openalliance.ad.jh
            public void a(long j, int i) {
                m.this.f();
                if (m.this.g == null) {
                    ho.c("PPSBaseView", "onViewShowEnd - no adShowStartTime");
                    return;
                }
                long currentTimeMillis = System.currentTimeMillis();
                long longValue = m.this.g.longValue();
                if (m.this.f8116a != null) {
                    m.this.f8116a.a(m.this.c, currentTimeMillis - longValue, 100);
                    m.this.f8116a.c();
                }
                dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.m.4.1
                    @Override // java.lang.Runnable
                    public void run() {
                        m.this.b.b();
                    }
                }, 150L);
                m.this.g = null;
            }

            @Override // com.huawei.openalliance.ad.jh
            public void a() {
                if (m.this.e != null) {
                    m.this.e.i();
                }
            }
        };
        setOnTouchListener(this.p);
    }
}
