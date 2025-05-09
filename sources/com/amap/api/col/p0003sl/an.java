package com.amap.api.col.p0003sl;

import android.content.Context;
import android.graphics.PointF;
import android.view.MotionEvent;

/* loaded from: classes2.dex */
public final class an extends al {
    private static final PointF n = new PointF();
    private final a o;
    private boolean p;
    private PointF q;
    private PointF r;
    private PointF s;
    private PointF t;

    public interface a {
        boolean a(an anVar);

        boolean b(an anVar);

        void c(an anVar);
    }

    public an(Context context, a aVar) {
        super(context);
        this.s = new PointF();
        this.t = new PointF();
        this.o = aVar;
    }

    @Override // com.amap.api.col.p0003sl.am
    protected final void a(int i, MotionEvent motionEvent, int i2, int i3) {
        if (i == 2) {
            if (this.p) {
                boolean a2 = a(motionEvent, i2, i3);
                this.p = a2;
                if (a2) {
                    return;
                }
                this.f = this.o.b(this);
                return;
            }
            return;
        }
        if (i != 5) {
            return;
        }
        a();
        this.g = MotionEvent.obtain(motionEvent);
        this.k = 0L;
        a(motionEvent);
        boolean a3 = a(motionEvent, i2, i3);
        this.p = a3;
        if (a3) {
            return;
        }
        this.f = this.o.b(this);
    }

    @Override // com.amap.api.col.p0003sl.am
    protected final void a(int i, MotionEvent motionEvent) {
        if (i == 2) {
            a(motionEvent);
            if (this.i / this.j <= 0.67f || !this.o.a(this)) {
                return;
            }
            this.g.recycle();
            this.g = MotionEvent.obtain(motionEvent);
            return;
        }
        if (i == 3) {
            if (!this.p) {
                this.o.c(this);
            }
            a();
        } else {
            if (i != 6) {
                return;
            }
            a(motionEvent);
            if (!this.p) {
                this.o.c(this);
            }
            a();
        }
    }

    @Override // com.amap.api.col.p0003sl.am
    protected final void a() {
        super.a();
        this.p = false;
    }

    @Override // com.amap.api.col.p0003sl.al, com.amap.api.col.p0003sl.am
    protected final void a(MotionEvent motionEvent) {
        super.a(motionEvent);
        MotionEvent motionEvent2 = this.g;
        this.q = b(motionEvent);
        this.r = b(motionEvent2);
        this.t = this.g.getPointerCount() != motionEvent.getPointerCount() ? n : new PointF(this.q.x - this.r.x, this.q.y - this.r.y);
        this.s.x += this.t.x;
        this.s.y += this.t.y;
    }

    public final PointF d() {
        return this.t;
    }
}
