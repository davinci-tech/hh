package com.huawei.uikit.hwdragdowntransition.anim;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TimeInterpolator;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;

/* loaded from: classes9.dex */
public class HwDragDownTransition {
    private static final String c = "HwDragDownTransition";

    /* renamed from: a, reason: collision with root package name */
    private float f10658a;
    private float aa;
    private float ab;
    private float ac;
    private float ad;
    private float ae;
    private float af;
    private float ag;
    private float ah;
    private float ai;
    private float ak;
    private float b;
    private float d;
    private float e;
    private float f;
    private float g;
    private float h;
    private float i;
    private float j;
    private boolean k;
    private boolean l;
    private boolean m;
    private Rect n;
    private float o;
    private Animator p;
    private Animator q;
    private TimeInterpolator r;
    private a s;
    private Matrix t;
    private HwDragDownAnimationListener u;
    private float v;
    private View w;
    private float x;
    private PropertyValuesHolder y;
    private float z;

    class a extends View {
        @Override // android.view.View
        protected void onDraw(Canvas canvas) {
            if (canvas != null) {
                canvas.drawColor(-16777216);
            }
        }
    }

    class b implements Animator.AnimatorListener {
        b() {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            HwDragDownTransition.this.s.setVisibility(8);
            HwDragDownTransition.this.w.setVisibility(8);
            HwDragDownTransition.this.j();
            HwDragDownTransition.this.e(0.0f, 0.0f);
            if (HwDragDownTransition.this.u != null) {
                HwDragDownTransition.this.u.onAnimationEnd();
            }
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            if (HwDragDownTransition.this.u != null) {
                HwDragDownTransition.this.u.onAnimationStart();
            }
        }
    }

    class d implements Animator.AnimatorListener {
        d() {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            HwDragDownTransition.this.e(0.0f, 0.0f);
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
        }
    }

    private boolean e() {
        return (!this.k || this.w == null || this.n == null) ? false : true;
    }

    private void f() {
        if ((Math.abs(this.ad) > 8.0f || Math.abs(this.ac) > 8.0f) && this.m) {
            this.m = false;
            float f = this.ac;
            if (f <= 0.0f || Math.abs(f) <= Math.abs(this.ad)) {
                return;
            }
            this.l = true;
        }
    }

    private void g() {
        if (Math.abs(this.ac) > this.j) {
            h();
        } else {
            m();
        }
    }

    private void h() {
        if (c()) {
            return;
        }
        if (this.p == null) {
            PropertyValuesHolder ofFloat = PropertyValuesHolder.ofFloat("Progress", 0.0f, 1.0f);
            this.y = ofFloat;
            ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(this, ofFloat);
            this.p = ofPropertyValuesHolder;
            ofPropertyValuesHolder.addListener(new b());
            this.p.setDuration(200L);
            this.p.setInterpolator(this.r);
        }
        this.p.start();
    }

    private void i() {
        if (this.w == null) {
            return;
        }
        if (this.aa == 0.0f) {
            a();
        }
        float abs = this.g == 0.0f ? 0.0f : Math.abs(this.ac) / this.g;
        float f = this.ah;
        float f2 = f - ((abs * f) * 0.39999998f);
        float f3 = this.ae;
        float f4 = f3 - ((abs * f3) * 0.39999998f);
        this.w.setScaleX(f2);
        this.w.setScaleY(f4);
        this.t.reset();
        this.t.preScale(this.ah, this.ae, this.i, this.o);
        this.t.postTranslate(this.ai, this.ag);
        this.t.postScale(f2 / this.ah, f4 / this.ae, this.v, this.x);
        this.t.postTranslate(this.ad, this.ac);
        float[] fArr = new float[9];
        this.t.getValues(fArr);
        float f5 = fArr[2];
        float f6 = fArr[5];
        float f7 = fArr[0];
        float f8 = fArr[4];
        this.w.setPivotX(0.0f);
        this.w.setPivotY(0.0f);
        this.w.setScaleX(f7);
        this.w.setScaleY(f8);
        this.w.setTranslationX(f5);
        this.w.setTranslationY(f6);
        float f9 = 1.0f - (abs * 0.39999998f);
        this.s.setAlpha(f9);
        HwDragDownAnimationListener hwDragDownAnimationListener = this.u;
        if (hwDragDownAnimationListener != null) {
            hwDragDownAnimationListener.onAlphaChanged(f9);
        }
        this.d = f2;
        this.b = f9;
        this.e = f5;
        this.f10658a = f6;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        ViewParent parent = this.w.getParent();
        if (parent instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) parent;
            viewGroup.removeView(this.w);
            viewGroup.removeView(this.s);
        }
    }

    private void m() {
        if (c()) {
            return;
        }
        if (this.q == null) {
            PropertyValuesHolder ofFloat = PropertyValuesHolder.ofFloat("ResetProgress", 0.0f, 1.0f);
            this.y = ofFloat;
            ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(this, ofFloat);
            this.q = ofPropertyValuesHolder;
            ofPropertyValuesHolder.addListener(new d());
            this.q.setDuration(200L);
            this.q.setInterpolator(this.r);
        }
        this.q.start();
    }

    public void b() {
        ViewParent parent = this.w.getParent();
        ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
        if (viewGroup == null) {
            return;
        }
        this.s.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        int indexOfChild = viewGroup.indexOfChild(this.w);
        this.s.setAlpha(1.0f);
        viewGroup.addView(this.s, indexOfChild);
        this.s.setVisibility(0);
    }

    public void e(HwDragDownAnimationListener hwDragDownAnimationListener) {
        this.u = hwDragDownAnimationListener;
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0036, code lost:
    
        if (r3 != 6) goto L38;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean edb_(android.view.MotionEvent r8) {
        /*
            r7 = this;
            r0 = 0
            if (r8 != 0) goto Lb
            java.lang.String r8 = com.huawei.uikit.hwdragdowntransition.anim.HwDragDownTransition.c
            java.lang.String r1 = "onTouchEvent: motionEvent is null!"
            android.util.Log.w(r8, r1)
            return r0
        Lb:
            boolean r1 = r7.e()
            if (r1 != 0) goto L19
            java.lang.String r8 = com.huawei.uikit.hwdragdowntransition.anim.HwDragDownTransition.c
            java.lang.String r1 = "onTouchEvent: Not a valid Transition setting, do not handle!"
            android.util.Log.w(r8, r1)
            return r0
        L19:
            float r1 = r8.getRawX()
            float r2 = r8.getRawY()
            android.view.MotionEvent r8 = android.view.MotionEvent.obtain(r8)
            r8.setLocation(r1, r2)
            int r3 = r8.getActionMasked()
            r4 = 1
            if (r3 == 0) goto L7a
            r5 = 0
            if (r3 == r4) goto L61
            r6 = 2
            if (r3 == r6) goto L39
            r1 = 6
            if (r3 == r1) goto L61
            goto L7d
        L39:
            int r0 = r8.getActionIndex()
            int r0 = r8.getPointerId(r0)
            if (r0 == 0) goto L44
            goto L7d
        L44:
            float r0 = r7.v
            int r0 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r0 != 0) goto L53
            float r0 = r7.x
            int r0 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r0 != 0) goto L53
            r7.e(r1, r2)
        L53:
            r7.a(r1, r2)
            r7.f()
            boolean r0 = r7.l
            if (r0 == 0) goto L7d
            r7.i()
            goto L7d
        L61:
            int r1 = r8.getActionIndex()
            int r1 = r8.getPointerId(r1)
            if (r1 != 0) goto L7d
            boolean r1 = r7.l
            if (r1 != 0) goto L76
            r7.e(r5, r5)
            r8.recycle()
            return r0
        L76:
            r7.g()
            goto L7d
        L7a:
            r7.e(r1, r2)
        L7d:
            r8.recycle()
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.uikit.hwdragdowntransition.anim.HwDragDownTransition.edb_(android.view.MotionEvent):boolean");
    }

    public void edc_(Rect rect) {
        this.n = rect;
    }

    private void a(float f, float f2) {
        this.ac = f2 - this.x;
        this.ad = f - this.v;
        this.z = f;
        this.ab = f2;
    }

    private boolean c() {
        Animator animator;
        Animator animator2 = this.p;
        return (animator2 != null && animator2.isStarted()) || ((animator = this.q) != null && animator.isStarted());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(float f, float f2) {
        this.v = f;
        this.x = f2;
        this.z = f;
        this.ab = f2;
        d();
        this.m = true;
        this.l = false;
    }

    private void a() {
        float width;
        float height;
        int[] iArr = new int[2];
        this.w.getLocationOnScreen(iArr);
        int width2 = this.n.width();
        int height2 = this.n.height();
        View view = this.w;
        if (view instanceof ImageView) {
            RectF rectF = new RectF();
            Drawable drawable = ((ImageView) this.w).getDrawable();
            Matrix imageMatrix = ((ImageView) this.w).getImageMatrix();
            rectF.set(0.0f, 0.0f, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            imageMatrix.mapRect(rectF);
            width = rectF.right - rectF.left;
            height = rectF.bottom - rectF.top;
        } else {
            width = view.getWidth();
            height = this.w.getHeight();
        }
        float width3 = this.n.width() / width;
        float height3 = this.n.height() / height;
        this.aa = width3 > height3 ? width3 : height3;
        if (width3 < height3) {
            this.f = ((this.w.getWidth() * height3) - width2) * (-0.5f);
        } else {
            this.h = ((this.w.getHeight() * width3) - height2) * (-0.5f);
        }
        float f = this.ah;
        float width4 = this.w.getWidth();
        float f2 = iArr[0];
        float f3 = this.ai;
        float f4 = this.ae;
        float height4 = this.w.getHeight();
        float f5 = iArr[1];
        float f6 = this.ag;
        Rect rect = this.n;
        this.af = ((rect.left + rect.right) * 0.5f) - ((((f * width4) * 0.5f) + f2) - f3);
        this.ak = ((rect.top + rect.bottom) * 0.5f) - ((((f4 * height4) * 0.5f) + f5) - f6);
    }

    private void d() {
        this.ai = this.w.getTranslationX();
        this.ag = this.w.getTranslationY();
        this.ae = this.w.getScaleY();
        this.ah = this.w.getScaleX();
        this.i = this.w.getPivotX();
        this.o = this.w.getPivotY();
        this.b = 1.0f;
        this.d = this.ah;
        this.e = this.ai;
        this.f10658a = this.ag;
    }
}
