package com.amap.api.col.p0003sl;

import android.content.Context;
import android.graphics.PointF;
import android.view.MotionEvent;

/* loaded from: classes2.dex */
public final class ao extends am {

    /* renamed from: a, reason: collision with root package name */
    private static final PointF f897a = new PointF();
    private final a b;
    private PointF c;
    private PointF d;
    private PointF n;
    private PointF o;

    public interface a {
        boolean a(ao aoVar);

        boolean b(ao aoVar);

        void c(ao aoVar);
    }

    public ao(Context context, a aVar) {
        super(context);
        this.n = new PointF();
        this.o = new PointF();
        this.b = aVar;
    }

    @Override // com.amap.api.col.p0003sl.am
    protected final void a(int i, MotionEvent motionEvent, int i2, int i3) {
        if (i == 0) {
            a();
            this.g = MotionEvent.obtain(motionEvent);
            this.k = 0L;
            a(motionEvent);
            return;
        }
        if (i == 2) {
            this.f = this.b.b(this);
        } else {
            if (i != 5) {
                return;
            }
            if (this.g != null) {
                this.g.recycle();
            }
            this.g = MotionEvent.obtain(motionEvent);
            a(motionEvent);
        }
    }

    @Override // com.amap.api.col.p0003sl.am
    protected final void a(int i, MotionEvent motionEvent) {
        if (i != 1) {
            if (i == 2) {
                a(motionEvent);
                if (this.i / this.j <= 0.67f || motionEvent.getPointerCount() > 1 || !this.b.a(this)) {
                    return;
                }
                this.g.recycle();
                this.g = MotionEvent.obtain(motionEvent);
                return;
            }
            if (i != 3) {
                return;
            }
        }
        this.b.c(this);
        a();
    }

    @Override // com.amap.api.col.p0003sl.am
    protected final void a(MotionEvent motionEvent) {
        super.a(motionEvent);
        MotionEvent motionEvent2 = this.g;
        this.c = b(motionEvent);
        this.d = b(motionEvent2);
        boolean z = this.g.getPointerCount() != motionEvent.getPointerCount();
        this.o = z ? f897a : new PointF(this.c.x - this.d.x, this.c.y - this.d.y);
        if (z) {
            this.g.recycle();
            this.g = MotionEvent.obtain(motionEvent);
        }
        this.n.x += this.o.x;
        this.n.y += this.o.y;
    }

    public final PointF d() {
        return this.o;
    }
}
