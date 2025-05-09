package com.amap.api.col.p0003sl;

import android.content.Context;
import android.graphics.PointF;
import android.view.MotionEvent;

/* loaded from: classes2.dex */
public abstract class am {
    protected final Context e;
    protected boolean f;
    protected MotionEvent g;
    protected MotionEvent h;
    protected float i;
    protected float j;
    protected long k;
    protected int l = 0;
    protected int m = 0;

    protected abstract void a(int i, MotionEvent motionEvent);

    protected abstract void a(int i, MotionEvent motionEvent, int i2, int i3);

    public final void a(int i, int i2) {
        this.l = i;
        this.m = i2;
    }

    public am(Context context) {
        this.e = context;
    }

    public final boolean b(MotionEvent motionEvent, int i, int i2) {
        int action = motionEvent.getAction() & 255;
        if (!this.f) {
            a(action, motionEvent, i, i2);
            return true;
        }
        a(action, motionEvent);
        return true;
    }

    protected void a(MotionEvent motionEvent) {
        MotionEvent motionEvent2 = this.g;
        MotionEvent motionEvent3 = this.h;
        if (motionEvent3 != null) {
            motionEvent3.recycle();
            this.h = null;
        }
        this.h = MotionEvent.obtain(motionEvent);
        this.k = motionEvent.getEventTime() - motionEvent2.getEventTime();
        this.i = motionEvent.getPressure(motionEvent.getActionIndex());
        this.j = motionEvent2.getPressure(motionEvent2.getActionIndex());
    }

    protected void a() {
        MotionEvent motionEvent = this.g;
        if (motionEvent != null) {
            motionEvent.recycle();
            this.g = null;
        }
        MotionEvent motionEvent2 = this.h;
        if (motionEvent2 != null) {
            motionEvent2.recycle();
            this.h = null;
        }
        this.f = false;
    }

    public final long b() {
        return this.k;
    }

    public static PointF b(MotionEvent motionEvent) {
        int pointerCount = motionEvent.getPointerCount();
        float f = 0.0f;
        float f2 = 0.0f;
        for (int i = 0; i < pointerCount; i++) {
            f += motionEvent.getX(i);
            f2 += motionEvent.getY(i);
        }
        float f3 = pointerCount;
        return new PointF(f / f3, f2 / f3);
    }

    public final MotionEvent c() {
        return this.h;
    }
}
