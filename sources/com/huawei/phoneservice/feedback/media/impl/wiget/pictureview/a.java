package com.huawei.phoneservice.feedback.media.impl.wiget.pictureview;

import android.content.Context;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;

/* loaded from: classes9.dex */
class a {

    /* renamed from: a, reason: collision with root package name */
    private int f8259a = -1;
    private int b = 0;
    private VelocityTracker c;
    private final ScaleGestureDetector d;
    private final float e;
    private float f;
    private final float g;
    private final c h;
    private boolean i;
    private float j;

    public boolean cec_(MotionEvent motionEvent) {
        try {
            this.d.onTouchEvent(motionEvent);
            return cdX_(motionEvent);
        } catch (IllegalArgumentException unused) {
            return true;
        }
    }

    public boolean d() {
        return this.d.isInProgress();
    }

    public boolean a() {
        return this.i;
    }

    private void ceb_(MotionEvent motionEvent) {
        this.f8259a = -1;
        if (this.i && this.c != null) {
            this.j = cdV_(motionEvent);
            this.f = cdW_(motionEvent);
            this.c.addMovement(motionEvent);
            this.c.computeCurrentVelocity(1000);
            float xVelocity = this.c.getXVelocity();
            float yVelocity = this.c.getYVelocity();
            if (Math.max(Math.abs(xVelocity), Math.abs(yVelocity)) >= this.g) {
                this.h.a(this.j, this.f, -xVelocity, -yVelocity);
            }
        }
        VelocityTracker velocityTracker = this.c;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.c = null;
        }
    }

    private void cea_(MotionEvent motionEvent) {
        int a2 = k.a(motionEvent.getAction());
        if (motionEvent.getPointerId(a2) == this.f8259a) {
            int i = a2 == 0 ? 1 : 0;
            this.f8259a = motionEvent.getPointerId(i);
            this.j = motionEvent.getX(i);
            this.f = motionEvent.getY(i);
        }
    }

    private void cdZ_(MotionEvent motionEvent) {
        float cdV_ = cdV_(motionEvent);
        float cdW_ = cdW_(motionEvent);
        float f = cdV_ - this.j;
        float f2 = cdW_ - this.f;
        if (!this.i) {
            this.i = Math.sqrt((double) ((f * f) + (f2 * f2))) >= ((double) this.e);
        }
        if (this.i) {
            this.h.a(f, f2);
            this.j = cdV_;
            this.f = cdW_;
            VelocityTracker velocityTracker = this.c;
            if (velocityTracker != null) {
                velocityTracker.addMovement(motionEvent);
            }
        }
    }

    private void cdY_(MotionEvent motionEvent) {
        this.f8259a = motionEvent.getPointerId(0);
        VelocityTracker obtain = VelocityTracker.obtain();
        this.c = obtain;
        if (obtain != null) {
            obtain.addMovement(motionEvent);
        }
        this.j = cdV_(motionEvent);
        this.f = cdW_(motionEvent);
        this.i = false;
    }

    private boolean cdX_(MotionEvent motionEvent) {
        int action = motionEvent.getAction() & 255;
        if (action == 0) {
            cdY_(motionEvent);
        } else if (action == 1) {
            ceb_(motionEvent);
        } else if (action == 2) {
            cdZ_(motionEvent);
        } else if (action == 3) {
            e();
        } else if (action != 6) {
            com.huawei.phoneservice.faq.base.util.i.c("GestureDetector", "processTouchEvent default!!!");
        } else {
            cea_(motionEvent);
        }
        int i = this.f8259a;
        if (i == -1) {
            i = 0;
        }
        this.b = motionEvent.findPointerIndex(i);
        return true;
    }

    private void e() {
        this.f8259a = -1;
        VelocityTracker velocityTracker = this.c;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.c = null;
        }
    }

    class d implements ScaleGestureDetector.OnScaleGestureListener {

        /* renamed from: a, reason: collision with root package name */
        private float f8260a;
        private float b = 0.0f;

        @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
        public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
        }

        @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
        public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
            this.f8260a = scaleGestureDetector.getFocusX();
            this.b = scaleGestureDetector.getFocusY();
            return true;
        }

        @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            float scaleFactor = scaleGestureDetector.getScaleFactor();
            if (Float.isNaN(scaleFactor) || Float.isInfinite(scaleFactor)) {
                return false;
            }
            if (scaleFactor < 0.0f) {
                return true;
            }
            a.this.h.a(scaleFactor, scaleGestureDetector.getFocusX(), scaleGestureDetector.getFocusY(), scaleGestureDetector.getFocusX() - this.f8260a, scaleGestureDetector.getFocusY() - this.b);
            this.f8260a = scaleGestureDetector.getFocusX();
            this.b = scaleGestureDetector.getFocusY();
            return true;
        }

        d() {
        }
    }

    private float cdW_(MotionEvent motionEvent) {
        try {
            return motionEvent.getY(this.b);
        } catch (Exception unused) {
            return motionEvent.getY();
        }
    }

    private float cdV_(MotionEvent motionEvent) {
        try {
            return motionEvent.getX(this.b);
        } catch (Exception unused) {
            return motionEvent.getX();
        }
    }

    a(Context context, c cVar) {
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.g = viewConfiguration.getScaledMinimumFlingVelocity();
        this.e = viewConfiguration.getScaledTouchSlop();
        this.h = cVar;
        this.d = new ScaleGestureDetector(context, new d());
    }
}
