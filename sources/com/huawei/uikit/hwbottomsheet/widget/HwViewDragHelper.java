package com.huawei.uikit.hwbottomsheet.widget;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import com.huawei.operation.utils.Constants;
import defpackage.skw;
import java.util.Arrays;

/* loaded from: classes7.dex */
class HwViewDragHelper {

    /* renamed from: a, reason: collision with root package name */
    private float[] f10604a;
    private int[] b;
    private float[] c;
    private float[] d;
    private float[] e;
    private float f;
    private int[] g;
    private int[] h;
    private float i;
    private int j;
    private int l;
    private View m;
    private int n;
    private int o;
    private VelocityTracker p;
    private int r;
    private final Callback s;
    private final ViewGroup t;
    private a v;
    private boolean w;
    private int k = -1;
    private final Runnable q = new c();
    private final Interpolator x = new d();
    private volatile boolean ad = false;
    private skw y = new skw();
    private skw u = new skw();

    public static abstract class Callback {
        public int clampViewPositionHorizontal(View view, int i, int i2) {
            return 0;
        }

        public int clampViewPositionVertical(View view, int i, int i2) {
            return 0;
        }

        public int getOrderedChildIndex(int i) {
            return i;
        }

        public int getViewHorizontalDragRange(View view) {
            return 0;
        }

        public int getViewVerticalDragRange(View view) {
            return 0;
        }

        public void onEdgeDragStarted(int i, int i2) {
        }

        public boolean onEdgeLock(int i) {
            return false;
        }

        public void onEdgeTouched(int i, int i2) {
        }

        public void onViewCaptured(View view, int i) {
        }

        public void onViewDragStateChanged(int i) {
        }

        public void onViewPositionChanged(View view, int i, int i2, int i3, int i4) {
        }

        public void onViewReleased(View view, float f, float f2) {
        }

        public abstract boolean tryCaptureView(View view, int i);
    }

    interface a {
        void a(int i, int i2);
    }

    class c implements Runnable {
        c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            HwViewDragHelper.this.e(0);
        }
    }

    class d implements Interpolator {
        d() {
        }

        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f) {
            float f2 = f - 1.0f;
            return (f2 * f2 * f2 * f2 * f2) + 1.0f;
        }
    }

    private HwViewDragHelper(Context context, ViewGroup viewGroup, Callback callback) {
        this.y.c(3.5f);
        this.r = ViewConfiguration.get(context).getScaledTouchSlop();
        this.i = r0.getScaledMaximumFlingVelocity();
        this.f = r0.getScaledMinimumFlingVelocity();
        this.t = viewGroup;
        this.s = callback;
        this.l = (int) ((context.getResources().getDisplayMetrics().density * 20.0f) + 0.5f);
    }

    public static HwViewDragHelper eat_(ViewGroup viewGroup, Callback callback) {
        return new HwViewDragHelper(viewGroup.getContext(), viewGroup, callback);
    }

    private void j() {
        this.ad = true;
        this.p.computeCurrentVelocity(1000, this.i);
        c(a(this.p.getXVelocity(this.k), this.f, this.i), a(this.p.getYVelocity(this.k), this.f, this.i));
    }

    public int b() {
        return this.j;
    }

    protected void b(boolean z) {
        this.ad = z;
    }

    protected boolean c() {
        return this.ad;
    }

    public int e() {
        return this.r;
    }

    void e(int i) {
        if (this.j != i) {
            this.j = i;
            if (i == 0) {
                this.m = null;
            }
            this.s.onViewDragStateChanged(i);
        }
        this.t.removeCallbacks(this.q);
    }

    private void b(int i, int i2, int i3, int i4) {
        if (i3 == 0 && i4 == 0) {
            return;
        }
        if (i4 != 0) {
            this.m.offsetTopAndBottom(i4);
        }
        if (i3 != 0) {
            this.m.offsetLeftAndRight(i3);
        }
        this.s.onViewPositionChanged(this.m, i, i2, i3, i4);
    }

    private int d(int i, int i2) {
        int i3 = i < this.t.getLeft() + this.l ? 1 : 0;
        if (i > this.t.getRight() - this.l) {
            i3 |= 2;
        }
        if (i2 < this.t.getTop() + this.l) {
            i3 |= 4;
        }
        return i2 > this.t.getBottom() - this.l ? i3 | 8 : i3;
    }

    private void d(int i) {
        int i2;
        if (i >= 0) {
            int[] iArr = this.b;
            if (i >= iArr.length || (i2 = iArr[i] & this.n) == 0) {
                return;
            }
            this.s.onEdgeTouched(i2, i);
        }
    }

    private void eaA_(MotionEvent motionEvent) {
        int pointerId = motionEvent.getPointerId(0);
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        b(x, y, pointerId);
        View eaE_ = eaE_((int) x, (int) y);
        if (this.j == 2 && eaE_ == this.m) {
            b(eaE_, pointerId);
        }
        d(pointerId);
    }

    private void eaB_(MotionEvent motionEvent) {
        if (this.e == null || this.f10604a == null) {
            return;
        }
        int pointerCount = motionEvent.getPointerCount();
        for (int i = 0; i < pointerCount; i++) {
            int pointerId = motionEvent.getPointerId(i);
            if (!e(pointerId, this.f10604a, this.e)) {
                return;
            }
            float x = motionEvent.getX(i);
            float y = motionEvent.getY(i);
            float f = x - this.f10604a[pointerId];
            float f2 = y - this.e[pointerId];
            View eaE_ = eaE_((int) x, (int) y);
            boolean eau_ = eau_(eaE_, f, f2);
            if (eau_) {
                int top = eaE_.getTop();
                int i2 = (int) f2;
                int clampViewPositionVertical = this.s.clampViewPositionVertical(eaE_, top + i2, i2);
                int left = eaE_.getLeft();
                int i3 = (int) f;
                int clampViewPositionHorizontal = this.s.clampViewPositionHorizontal(eaE_, left + i3, i3);
                int viewHorizontalDragRange = this.s.getViewHorizontalDragRange(eaE_);
                int viewVerticalDragRange = this.s.getViewVerticalDragRange(eaE_);
                boolean z = clampViewPositionHorizontal != left && viewHorizontalDragRange >= 0;
                boolean z2 = clampViewPositionVertical != top && viewVerticalDragRange >= 0;
                if (!z && !z2) {
                    break;
                }
            }
            c(f, f2, pointerId);
            if (this.j == 1 || (eau_ && b(eaE_, pointerId))) {
                break;
            }
        }
        eaF_(motionEvent);
    }

    public static HwViewDragHelper eas_(ViewGroup viewGroup, float f, Callback callback) {
        HwViewDragHelper eat_ = eat_(viewGroup, callback);
        eat_.r = (int) (eat_.r * (Float.compare(f, 0.0f) != 0 ? 1.0f / f : 1.0f));
        return eat_;
    }

    private void eaz_(MotionEvent motionEvent) {
        if (this.p == null) {
            this.p = VelocityTracker.obtain();
        }
        this.p.addMovement(motionEvent);
    }

    public boolean c(int i, int i2) {
        if (this.w) {
            return d(i, i2, (int) this.p.getXVelocity(this.k), (int) this.p.getYVelocity(this.k));
        }
        Log.w("HwViewDragHelper", "Cannot settleCapturedViewAt outside of a call to Callback#onViewReleased");
        return false;
    }

    public void d() {
        VelocityTracker velocityTracker = this.p;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.p = null;
        }
        float[] fArr = this.f10604a;
        if (fArr == null) {
            return;
        }
        Arrays.fill(fArr, 0.0f);
        Arrays.fill(this.e, 0.0f);
        Arrays.fill(this.d, 0.0f);
        Arrays.fill(this.c, 0.0f);
        Arrays.fill(this.b, 0);
        Arrays.fill(this.h, 0);
        Arrays.fill(this.g, 0);
        this.o = 0;
        this.k = -1;
    }

    private void a(int i) {
        if (e(i, this.f10604a, this.e, this.d, this.c) && e(i, this.b, this.h, this.g)) {
            this.f10604a[i] = 0.0f;
            this.e[i] = 0.0f;
            this.d[i] = 0.0f;
            this.c[i] = 0.0f;
            this.b[i] = 0;
            this.h[i] = 0;
            this.g[i] = 0;
        }
    }

    private boolean eay_(MotionEvent motionEvent) {
        int findPointerIndex = motionEvent.findPointerIndex(this.k);
        if (findPointerIndex == -1) {
            return false;
        }
        float x = motionEvent.getX(findPointerIndex);
        float y = motionEvent.getY(findPointerIndex);
        if (!e(this.k, this.d, this.c)) {
            return false;
        }
        float[] fArr = this.d;
        int i = this.k;
        int i2 = (int) (x - fArr[i]);
        int i3 = (int) (y - this.c[i]);
        c(this.m.getLeft() + i2, this.m.getTop() + i3, 0, i3);
        eaF_(motionEvent);
        return true;
    }

    public void eaD_(View view, int i) {
        if (view != null) {
            if (view.getParent() != this.t) {
                Log.w("HwViewDragHelper", "captureChildView: Parameter must be a descendant of the HwViewDragHelper's tracked parent view (" + this.t + Constants.RIGHT_BRACKET_ONLY);
                return;
            }
            this.k = i;
            this.s.onViewCaptured(view, i);
            this.m = view;
            e(1);
            return;
        }
        Log.w("HwViewDragHelper", "captureChildView: Parameter childView is null");
    }

    public boolean eaH_(MotionEvent motionEvent) {
        if (motionEvent == null) {
            Log.e("HwViewDragHelper", "shouldInterceptTouchEvent: Motion event is null!");
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            d();
        }
        eaz_(motionEvent);
        int actionIndex = motionEvent.getActionIndex();
        if (actionMasked != 0) {
            if (actionMasked != 1) {
                if (actionMasked == 2) {
                    eaB_(motionEvent);
                } else if (actionMasked != 3) {
                    if (actionMasked == 5) {
                        c(motionEvent, actionIndex);
                    } else if (actionMasked == 6) {
                        a(motionEvent.getPointerId(actionIndex));
                    }
                }
            }
            d();
        } else {
            eaA_(motionEvent);
        }
        return this.j == 1;
    }

    private void eaw_(MotionEvent motionEvent) {
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        int pointerId = motionEvent.getPointerId(0);
        View eaE_ = eaE_((int) x, (int) y);
        b(x, y, pointerId);
        b(eaE_, pointerId);
        d(pointerId);
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x003a, code lost:
    
        if (r12 <= 2000) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0043, code lost:
    
        r5 = r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0041, code lost:
    
        if (r12 >= (-2000)) goto L23;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean d(int r9, int r10, int r11, int r12) {
        /*
            r8 = this;
            android.view.View r11 = r8.m
            r0 = 0
            if (r11 != 0) goto Ld
            java.lang.String r9 = "HwViewDragHelper"
            java.lang.String r10 = "slideViewAt: mCapturedView is null!"
            android.util.Log.w(r9, r10)
            return r0
        Ld:
            int r11 = r11.getLeft()
            android.view.View r1 = r8.m
            int r1 = r1.getTop()
            int r10 = r10 - r1
            int r9 = r9 - r11
            if (r9 != 0) goto L2b
            if (r10 != 0) goto L2b
            r8.e(r0)
            skw r9 = r8.u
            r9.d()
            skw r9 = r8.y
            r9.d()
            return r0
        L2b:
            if (r10 == 0) goto L9c
            if (r12 != 0) goto L36
            if (r10 >= 0) goto L34
            r12 = -9000(0xffffffffffffdcd8, float:NaN)
            goto L36
        L34:
            r12 = 9000(0x2328, float:1.2612E-41)
        L36:
            if (r12 <= 0) goto L3d
            r9 = 2000(0x7d0, float:2.803E-42)
            if (r12 > r9) goto L3d
            goto L43
        L3d:
            if (r12 >= 0) goto L45
            r9 = -2000(0xfffffffffffff830, float:NaN)
            if (r12 < r9) goto L45
        L43:
            r5 = r9
            goto L46
        L45:
            r5 = r12
        L46:
            if (r10 <= 0) goto L57
            if (r5 <= 0) goto L57
            skw r2 = r8.y
            r3 = 0
            r6 = -2147483648(0xffffffff80000000, float:-0.0)
            r7 = 2147483647(0x7fffffff, float:NaN)
            r4 = r1
            r2.ebr_(r3, r4, r5, r6, r7)
            goto L85
        L57:
            if (r10 >= 0) goto L69
            if (r5 <= 0) goto L69
            skw r2 = r8.y
            r3 = 0
            int r5 = -r5
            r6 = -2147483648(0xffffffff80000000, float:-0.0)
            r7 = 2147483647(0x7fffffff, float:NaN)
            r4 = r1
            r2.ebr_(r3, r4, r5, r6, r7)
            goto L85
        L69:
            if (r10 >= 0) goto L78
            skw r2 = r8.y
            r3 = 0
            r6 = -2147483648(0xffffffff80000000, float:-0.0)
            r7 = 2147483647(0x7fffffff, float:NaN)
            r4 = r1
            r2.ebr_(r3, r4, r5, r6, r7)
            goto L85
        L78:
            skw r2 = r8.y
            r3 = 0
            int r5 = -r5
            r6 = -2147483648(0xffffffff80000000, float:-0.0)
            r7 = 2147483647(0x7fffffff, float:NaN)
            r4 = r1
            r2.ebr_(r3, r4, r5, r6, r7)
        L85:
            skw r9 = r8.y
            int r9 = r9.a()
            int r9 = r9 - r1
            double r11 = (double) r9
            int r9 = java.lang.Math.abs(r10)
            double r9 = (double) r9
            double r11 = java.lang.Math.abs(r11)
            double r9 = r9 / r11
            skw r11 = r8.y
            r11.e(r9)
        L9c:
            r9 = 2
            r8.e(r9)
            r9 = 1
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.uikit.hwbottomsheet.widget.HwViewDragHelper.d(int, int, int, int):boolean");
    }

    public void a() {
        d();
        if (this.j == 2) {
            int b = this.u.b();
            int b2 = this.y.b();
            this.u.d();
            this.y.d();
            int b3 = this.u.b();
            int b4 = this.y.b();
            this.s.onViewPositionChanged(this.m, b3, b4, b3 - b, b4 - b2);
        }
        e(0);
    }

    private void eax_(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        int actionIndex = motionEvent.getActionIndex();
        if (actionMasked == 0) {
            eaw_(motionEvent);
            this.ad = true;
            return;
        }
        if (actionMasked == 1) {
            if (this.j == 1) {
                j();
            }
            d();
            return;
        }
        if (actionMasked == 2) {
            eav_(motionEvent);
            this.ad = true;
            return;
        }
        if (actionMasked == 3) {
            if (this.j == 1) {
                c(0.0f, 0.0f);
            }
            d();
        } else if (actionMasked == 5) {
            a(motionEvent, actionIndex);
            this.ad = true;
        } else {
            if (actionMasked != 6) {
                return;
            }
            b(motionEvent, actionIndex);
        }
    }

    public boolean eaG_(View view, int i, int i2) {
        this.k = -1;
        this.m = view;
        boolean d2 = d(i, i2, 0, 0);
        if (!d2 && this.j == 0 && this.m != null) {
            this.m = null;
        }
        return d2;
    }

    private void c(MotionEvent motionEvent, int i) {
        int pointerId = motionEvent.getPointerId(i);
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        b(x, y, pointerId);
        int i2 = this.j;
        if (i2 == 2) {
            View eaE_ = eaE_((int) x, (int) y);
            if (eaE_ == this.m) {
                b(eaE_, pointerId);
                return;
            }
            return;
        }
        if (i2 == 0) {
            d(pointerId);
        } else {
            Log.w("HwViewDragHelper", "interceptActionPointerDown: Wrong drag view state!");
        }
    }

    protected void eaF_(MotionEvent motionEvent) {
        int pointerCount = motionEvent.getPointerCount();
        for (int i = 0; i < pointerCount; i++) {
            float x = motionEvent.getX(i);
            float y = motionEvent.getY(i);
            int pointerId = motionEvent.getPointerId(i);
            if (!e(pointerId, this.d, this.c)) {
                return;
            }
            this.d[pointerId] = x;
            this.c[pointerId] = y;
        }
    }

    private void eav_(MotionEvent motionEvent) {
        if (this.j == 1) {
            eay_(motionEvent);
            return;
        }
        for (int i = 0; i < motionEvent.getPointerCount(); i++) {
            int pointerId = motionEvent.getPointerId(i);
            float x = motionEvent.getX(i);
            float y = motionEvent.getY(i);
            if (!e(pointerId, this.d, this.c)) {
                return;
            }
            float f = x - this.f10604a[pointerId];
            float f2 = y - this.e[pointerId];
            c(f, f2, pointerId);
            if (this.j == 1) {
                return;
            }
            View eaE_ = eaE_((int) x, (int) y);
            if (eau_(eaE_, f, f2) && b(eaE_, pointerId)) {
                return;
            }
        }
    }

    boolean b(View view, int i) {
        if (view == this.m && this.k == i) {
            return true;
        }
        if (view == null || !this.s.tryCaptureView(view, i)) {
            return false;
        }
        this.k = i;
        eaD_(view, i);
        return true;
    }

    private void b(float f, float f2, int i) {
        float[] fArr = this.f10604a;
        if (fArr == null || fArr.length <= i) {
            int i2 = i + 1;
            float[] fArr2 = new float[i2];
            float[] fArr3 = new float[i2];
            float[] fArr4 = new float[i2];
            float[] fArr5 = new float[i2];
            int[] iArr = new int[i2];
            int[] iArr2 = new int[i2];
            int[] iArr3 = new int[i2];
            if (fArr != null) {
                System.arraycopy(fArr, 0, fArr2, 0, fArr.length);
                float[] fArr6 = this.e;
                System.arraycopy(fArr6, 0, fArr3, 0, fArr6.length);
                float[] fArr7 = this.d;
                System.arraycopy(fArr7, 0, fArr4, 0, fArr7.length);
                float[] fArr8 = this.c;
                System.arraycopy(fArr8, 0, fArr5, 0, fArr8.length);
                int[] iArr4 = this.g;
                System.arraycopy(iArr4, 0, iArr3, 0, iArr4.length);
                int[] iArr5 = this.b;
                System.arraycopy(iArr5, 0, iArr, 0, iArr5.length);
                int[] iArr6 = this.h;
                System.arraycopy(iArr6, 0, iArr2, 0, iArr6.length);
            }
            this.f10604a = fArr2;
            this.e = fArr3;
            this.d = fArr4;
            this.c = fArr5;
            this.g = iArr3;
            this.b = iArr;
            this.h = iArr2;
        }
        if (e(i, this.f10604a, this.e, this.d, this.c) && e(i, this.b, this.h, this.g)) {
            this.f10604a[i] = f;
            this.d[i] = f;
            this.e[i] = f2;
            this.c[i] = f2;
            this.b[i] = d((int) f, (int) f2);
            this.o |= 1 << i;
        }
    }

    private float a(float f, float f2, float f3) {
        float abs = Math.abs(f);
        if (abs > f3) {
            return f >= 0.0f ? f3 : -f3;
        }
        if (abs < f2) {
            return 0.0f;
        }
        return f;
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x0076, code lost:
    
        if (r7.y.h() != false) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x007b, code lost:
    
        if (r8 != false) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x007d, code lost:
    
        e(0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0081, code lost:
    
        r7.t.post(r7.q);
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0079, code lost:
    
        if (r0 == false) goto L35;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean c(boolean r8) {
        /*
            r7 = this;
            android.view.View r0 = r7.m
            r1 = 0
            if (r0 != 0) goto L6
            return r1
        L6:
            int r0 = r7.j
            r2 = 2
            if (r0 == r2) goto Lc
            return r1
        Lc:
            skw r0 = r7.u
            boolean r0 = r0.c()
            r3 = 1
            if (r0 != 0) goto L20
            skw r0 = r7.y
            boolean r0 = r0.c()
            if (r0 == 0) goto L1e
            goto L20
        L1e:
            r0 = r1
            goto L21
        L20:
            r0 = r3
        L21:
            skw r4 = r7.u
            int r4 = r4.b()
            skw r5 = r7.y
            int r5 = r5.b()
            android.view.View r6 = r7.m
            r6.getLeft()
            android.view.View r6 = r7.m
            int r6 = r6.getTop()
            int r6 = r5 - r6
            r7.b(r4, r5, r1, r6)
            skw r6 = r7.u
            int r6 = r6.a()
            if (r4 != r6) goto L47
            r4 = r3
            goto L48
        L47:
            r4 = r1
        L48:
            skw r6 = r7.y
            int r6 = r6.a()
            if (r5 != r6) goto L52
            r5 = r3
            goto L53
        L52:
            r5 = r1
        L53:
            if (r4 == 0) goto L59
            if (r5 == 0) goto L59
            r4 = r3
            goto L5a
        L59:
            r4 = r1
        L5a:
            if (r0 == 0) goto L79
            if (r4 == 0) goto L79
            skw r0 = r7.u
            r0.d()
            skw r0 = r7.y
            r0.d()
            skw r0 = r7.u
            boolean r0 = r0.h()
            if (r0 == 0) goto L88
            skw r0 = r7.y
            boolean r0 = r0.h()
            if (r0 != 0) goto L7b
            goto L88
        L79:
            if (r0 != 0) goto L88
        L7b:
            if (r8 != 0) goto L81
            r7.e(r1)
            goto L88
        L81:
            android.view.ViewGroup r8 = r7.t
            java.lang.Runnable r0 = r7.q
            r8.post(r0)
        L88:
            int r8 = r7.j
            if (r8 != r2) goto L8d
            r1 = r3
        L8d:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.uikit.hwbottomsheet.widget.HwViewDragHelper.c(boolean):boolean");
    }

    private void c(int i, int i2, int i3, int i4) {
        int left = this.m.getLeft();
        int top = this.m.getTop();
        if (i4 != 0) {
            i2 = this.s.clampViewPositionVertical(this.m, i2, i4);
            this.m.offsetTopAndBottom(i2 - top);
        }
        int i5 = i2;
        if (i3 != 0) {
            i = this.s.clampViewPositionHorizontal(this.m, i, i3);
            this.m.offsetLeftAndRight(i - left);
        }
        int i6 = i;
        if (i3 == 0 && i4 == 0) {
            return;
        }
        this.s.onViewPositionChanged(this.m, i6, i5, i6 - left, i5 - top);
        a aVar = this.v;
        if (aVar != null) {
            aVar.a(i3, i4);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v18 */
    /* JADX WARN: Type inference failed for: r0v19 */
    /* JADX WARN: Type inference failed for: r0v7, types: [int] */
    /* JADX WARN: Type inference failed for: r0v9 */
    /* JADX WARN: Type inference failed for: r3v2, types: [com.huawei.uikit.hwbottomsheet.widget.HwViewDragHelper$Callback] */
    private void c(float f, float f2, int i) {
        if (e(i, this.h)) {
            boolean c2 = c(f, f2, i, 1);
            boolean z = c2;
            if (c(f, f2, i, 2)) {
                z = (c2 ? 1 : 0) | 2;
            }
            boolean z2 = z;
            if (c(f2, f, i, 4)) {
                z2 = (z ? 1 : 0) | 4;
            }
            ?? r0 = z2;
            if (c(f2, f, i, 8)) {
                r0 = (z2 ? 1 : 0) | 8;
            }
            if (r0 != 0) {
                this.s.onEdgeDragStarted(r0, i);
                int[] iArr = this.h;
                iArr[i] = iArr[i] | r0;
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0039, code lost:
    
        if (r5.k == (-1)) goto L20;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void b(android.view.MotionEvent r6, int r7) {
        /*
            r5 = this;
            int r7 = r6.getPointerId(r7)
            int r0 = r5.k
            if (r7 != r0) goto L46
            int r0 = r5.j
            r1 = 1
            if (r0 != r1) goto Le
            goto L46
        Le:
            int r0 = r6.getPointerCount()
            r1 = 0
        L13:
            if (r1 >= r0) goto L3f
            int r2 = r6.getPointerId(r1)
            int r3 = r5.k
            if (r2 != r3) goto L1e
            goto L3c
        L1e:
            float r3 = r6.getX(r1)
            int r3 = (int) r3
            float r4 = r6.getY(r1)
            int r4 = (int) r4
            android.view.View r3 = r5.eaE_(r3, r4)
            android.view.View r4 = r5.m
            if (r3 != r4) goto L3c
            boolean r2 = r5.b(r4, r2)
            if (r2 == 0) goto L3c
            int r6 = r5.k
            r0 = -1
            if (r6 != r0) goto L42
            goto L3f
        L3c:
            int r1 = r1 + 1
            goto L13
        L3f:
            r5.j()
        L42:
            r5.a(r7)
            goto L49
        L46:
            r5.a(r7)
        L49:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.uikit.hwbottomsheet.widget.HwViewDragHelper.b(android.view.MotionEvent, int):void");
    }

    private boolean c(float f, float f2, int i, int i2) {
        if (!e(i, this.b, this.g, this.h)) {
            return false;
        }
        float abs = Math.abs(f);
        float abs2 = Math.abs(f2);
        float f3 = this.r;
        boolean z = abs > f3 || abs2 > f3;
        boolean z2 = ((this.b[i] & i2) == i2 || (this.n & i2) != 0) && !((this.h[i] & i2) == i2 || (this.g[i] & i2) == i2);
        if (!z || !z2) {
            return false;
        }
        if (abs >= abs2 * 0.5f || !this.s.onEdgeLock(i2)) {
            return abs > ((float) this.r) && (this.h[i] & i2) == 0;
        }
        int[] iArr = this.g;
        iArr[i] = iArr[i] | i2;
        return false;
    }

    public View eaE_(int i, int i2) {
        int childCount = this.t.getChildCount();
        while (true) {
            childCount--;
            if (childCount < 0) {
                return null;
            }
            View childAt = this.t.getChildAt(this.s.getOrderedChildIndex(childCount));
            if (childAt == null) {
                Log.w("HwViewDragHelper", "findTopChildUnder: Child view is null！");
                return null;
            }
            if (i >= childAt.getLeft() && i < childAt.getRight() && i2 >= childAt.getTop() && i2 < childAt.getBottom()) {
                return childAt;
            }
        }
    }

    private boolean eau_(View view, float f, float f2) {
        if (view == null) {
            return false;
        }
        boolean z = this.s.getViewVerticalDragRange(view) > 0;
        boolean z2 = this.s.getViewHorizontalDragRange(view) > 0;
        if (!z2 || !z) {
            return z2 ? Math.abs(f) > ((float) this.r) : z && Math.abs(f2) > ((float) this.r);
        }
        int i = this.r;
        return (f2 * f2) + (f * f) > ((float) (i * i));
    }

    private boolean e(int i, float[]... fArr) {
        if (fArr == null) {
            return false;
        }
        for (float[] fArr2 : fArr) {
            if (fArr2 == null || i > fArr2.length - 1 || i < 0) {
                return false;
            }
        }
        return true;
    }

    private boolean e(int i, int[]... iArr) {
        if (iArr == null) {
            return false;
        }
        for (int[] iArr2 : iArr) {
            if (iArr2 == null || i > iArr2.length - 1 || i < 0) {
                return false;
            }
        }
        return true;
    }

    public void eaC_(MotionEvent motionEvent) {
        if (motionEvent == null) {
            Log.w("HwViewDragHelper", "processTouchEvent: Parameter motionEvent is null");
            return;
        }
        if (motionEvent.getActionMasked() == 0) {
            d();
        }
        if (this.p == null) {
            this.p = VelocityTracker.obtain();
        }
        this.p.addMovement(motionEvent);
        eax_(motionEvent);
    }

    private void a(MotionEvent motionEvent, int i) {
        float x = motionEvent.getX(i);
        float y = motionEvent.getY(i);
        int pointerId = motionEvent.getPointerId(i);
        b(x, y, pointerId);
        if (this.j == 0) {
            b(eaE_((int) x, (int) y), pointerId);
            d(pointerId);
        } else if (a(this.m, (int) x, (int) y)) {
            b(this.m, pointerId);
        } else {
            Log.w("HwViewDragHelper", "doActionPointerDown: Wrong State");
        }
    }

    private void c(float f, float f2) {
        this.w = true;
        this.ad = true;
        this.s.onViewReleased(this.m, f, f2);
        this.w = false;
        if (this.j == 1) {
            e(0);
        }
    }

    public boolean a(View view, int i, int i2) {
        if (view == null) {
            return false;
        }
        return (i >= view.getLeft() && i < view.getRight()) && (i2 >= view.getTop() && i2 < view.getBottom());
    }

    void a(a aVar) {
        this.v = aVar;
    }
}
