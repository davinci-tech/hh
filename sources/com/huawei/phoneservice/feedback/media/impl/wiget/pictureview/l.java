package com.huawei.phoneservice.feedback.media.impl.wiget.pictureview;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.OverScroller;

/* loaded from: classes9.dex */
public class l implements View.OnTouchListener, View.OnLayoutChangeListener {

    /* renamed from: a, reason: collision with root package name */
    private f f8261a;
    private h aa;
    private i ac;
    private j ad;
    private float d;
    private final com.huawei.phoneservice.feedback.media.impl.wiget.pictureview.c e;
    private com.huawei.phoneservice.feedback.media.impl.wiget.pictureview.a k;
    private GestureDetector m;
    private final ImageView o;
    private com.huawei.phoneservice.feedback.media.impl.wiget.pictureview.d u;
    private View.OnLongClickListener v;
    private com.huawei.phoneservice.feedback.media.impl.wiget.pictureview.f w;
    private g x;
    private com.huawei.phoneservice.feedback.media.impl.wiget.pictureview.e y;
    private View.OnClickListener z;
    private boolean h = true;
    private boolean j = false;
    private int g = 200;
    private float i = 1.0f;
    private float f = 1.75f;
    private float l = 3.0f;
    private Interpolator n = new AccelerateDecelerateInterpolator();
    private final float[] p = new float[9];
    private final Matrix s = new Matrix();
    private final Matrix t = new Matrix();
    private final Matrix r = new Matrix();
    private final RectF q = new RectF();
    private boolean ab = true;
    private ImageView.ScaleType ag = ImageView.ScaleType.FIT_CENTER;
    private int b = 2;
    private int c = 2;

    /* JADX WARN: Removed duplicated region for block: B:14:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00b6  */
    @Override // android.view.View.OnTouchListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onTouch(android.view.View r11, android.view.MotionEvent r12) {
        /*
            r10 = this;
            boolean r0 = r10.ab
            r1 = 0
            if (r0 == 0) goto Lc2
            r0 = r11
            android.widget.ImageView r0 = (android.widget.ImageView) r0
            boolean r0 = com.huawei.phoneservice.feedback.media.impl.wiget.pictureview.k.a(r0)
            if (r0 == 0) goto Lc2
            int r0 = r12.getAction()
            r2 = 1
            if (r0 == 0) goto L72
            if (r0 == r2) goto L22
            r3 = 3
            if (r0 == r3) goto L22
            java.lang.String r11 = "PictureViewAttaches"
            java.lang.String r0 = "MotionEvent default!!!"
            com.huawei.phoneservice.faq.base.util.i.c(r11, r0)
            goto L7e
        L22:
            float r0 = r10.f()
            float r3 = r10.i
            int r0 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r0 >= 0) goto L48
            android.graphics.RectF r0 = r10.ces_()
            if (r0 == 0) goto L7e
            com.huawei.phoneservice.feedback.media.impl.wiget.pictureview.l$b r9 = new com.huawei.phoneservice.feedback.media.impl.wiget.pictureview.l$b
            float r5 = r10.f()
            float r6 = r10.i
            float r7 = r0.centerX()
            float r8 = r0.centerY()
            r3 = r9
            r4 = r10
            r3.<init>(r5, r6, r7, r8)
            goto L6d
        L48:
            float r0 = r10.f()
            float r3 = r10.l
            int r0 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r0 <= 0) goto L7e
            android.graphics.RectF r0 = r10.ces_()
            if (r0 == 0) goto L7e
            com.huawei.phoneservice.feedback.media.impl.wiget.pictureview.l$b r9 = new com.huawei.phoneservice.feedback.media.impl.wiget.pictureview.l$b
            float r5 = r10.f()
            float r6 = r10.l
            float r7 = r0.centerX()
            float r8 = r0.centerY()
            r3 = r9
            r4 = r10
            r3.<init>(r5, r6, r7, r8)
        L6d:
            r11.post(r9)
            r11 = r2
            goto L7f
        L72:
            android.view.ViewParent r11 = r11.getParent()
            if (r11 == 0) goto L7b
            r11.requestDisallowInterceptTouchEvent(r2)
        L7b:
            r10.g()
        L7e:
            r11 = r1
        L7f:
            com.huawei.phoneservice.feedback.media.impl.wiget.pictureview.a r0 = r10.k
            if (r0 == 0) goto Lb6
            boolean r11 = r0.d()
            com.huawei.phoneservice.feedback.media.impl.wiget.pictureview.a r0 = r10.k
            boolean r0 = r0.a()
            com.huawei.phoneservice.feedback.media.impl.wiget.pictureview.a r3 = r10.k
            boolean r3 = r3.cec_(r12)
            if (r11 != 0) goto L9f
            com.huawei.phoneservice.feedback.media.impl.wiget.pictureview.a r11 = r10.k
            boolean r11 = r11.d()
            if (r11 != 0) goto L9f
            r11 = r2
            goto La0
        L9f:
            r11 = r1
        La0:
            if (r0 != 0) goto Lac
            com.huawei.phoneservice.feedback.media.impl.wiget.pictureview.a r0 = r10.k
            boolean r0 = r0.a()
            if (r0 != 0) goto Lac
            r0 = r2
            goto Lad
        Lac:
            r0 = r1
        Lad:
            if (r11 == 0) goto Lb2
            if (r0 == 0) goto Lb2
            r1 = r2
        Lb2:
            r10.j = r1
            r1 = r3
            goto Lb7
        Lb6:
            r1 = r11
        Lb7:
            android.view.GestureDetector r11 = r10.m
            if (r11 == 0) goto Lc2
            boolean r11 = r11.onTouchEvent(r12)
            if (r11 == 0) goto Lc2
            r1 = r2
        Lc2:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.phoneservice.feedback.media.impl.wiget.pictureview.l.onTouch(android.view.View, android.view.MotionEvent):boolean");
    }

    @Override // android.view.View.OnLayoutChangeListener
    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        if (i == i5 && i2 == i6 && i3 == i7 && i4 == i8) {
            return;
        }
        cef_(this.o.getDrawable());
    }

    public void j() {
        if (this.ab) {
            cef_(this.o.getDrawable());
        } else {
            m();
        }
    }

    public ImageView.ScaleType ceu_() {
        return this.ag;
    }

    public final float f() {
        return (float) Math.sqrt(((float) Math.pow(ced_(this.s, 0), 2.0d)) + ((float) Math.pow(ced_(this.s, 3), 2.0d)));
    }

    public float b() {
        return this.i;
    }

    public float d() {
        return this.f;
    }

    public float c() {
        return this.l;
    }

    public void j(float f2) {
        b(f2, false);
    }

    public Matrix cet_() {
        return this.r;
    }

    public void e(float f2) {
        this.s.setRotate(f2 % 360.0f);
        h();
    }

    public void a(float f2) {
        this.s.postRotate(f2 % 360.0f);
        h();
    }

    public RectF ces_() {
        k();
        return a(cel_());
    }

    public void c(float f2) {
        k.c(f2, this.f, this.l);
        this.i = f2;
    }

    public void b(boolean z) {
        this.ab = z;
        j();
    }

    public void d(float f2) {
        k.c(this.i, f2, this.l);
        this.f = f2;
    }

    public void c(boolean z) {
        this.h = z;
    }

    public void d(j jVar) {
        this.ad = jVar;
    }

    public void a(i iVar) {
        this.ac = iVar;
    }

    public void d(h hVar) {
        this.aa = hVar;
    }

    public void b(g gVar) {
        this.x = gVar;
    }

    public void e(com.huawei.phoneservice.feedback.media.impl.wiget.pictureview.f fVar) {
        this.w = fVar;
    }

    public void c(com.huawei.phoneservice.feedback.media.impl.wiget.pictureview.e eVar) {
        this.y = eVar;
    }

    public void e(com.huawei.phoneservice.feedback.media.impl.wiget.pictureview.d dVar) {
        this.u = dVar;
    }

    public void cer_(ImageView.ScaleType scaleType) {
        if (!k.cev_(scaleType) || scaleType == this.ag) {
            return;
        }
        this.ag = scaleType;
        j();
    }

    public void ceq_(View.OnLongClickListener onLongClickListener) {
        this.v = onLongClickListener;
    }

    public void cep_(View.OnClickListener onClickListener) {
        this.z = onClickListener;
    }

    public void ceo_(GestureDetector.OnDoubleTapListener onDoubleTapListener) {
        this.m.setOnDoubleTapListener(onDoubleTapListener);
    }

    public void d(int i) {
        this.g = i;
    }

    public void b(float f2, boolean z) {
        e(f2, this.o.getRight() / 2, this.o.getBottom() / 2, z);
    }

    public void e(float f2, float f3, float f4, boolean z) {
        if (f2 < this.i || f2 > this.l) {
            throw new IllegalArgumentException("Scale must be within the range of minScale and maxScale");
        }
        if (z) {
            this.o.post(new b(f(), f2, f3, f4));
        } else {
            this.s.setScale(f2, f2, f3, f4);
            h();
        }
    }

    public void b(float f2) {
        k.c(this.i, this.f, f2);
        this.l = f2;
    }

    private void m() {
        this.s.reset();
        a(this.d);
        cek_(cel_());
        k();
    }

    private void o() {
        this.m.setOnDoubleTapListener(new d());
    }

    private Matrix cel_() {
        this.r.set(this.t);
        this.r.postConcat(this.s);
        return this.r;
    }

    private boolean k() {
        float f2;
        float f3;
        RectF a2 = a(cel_());
        if (a2 == null) {
            return false;
        }
        float height = a2.height();
        float width = a2.width();
        float cee_ = cee_(this.o);
        if (height <= cee_) {
            int i = e.b[this.ag.ordinal()];
            if (i == 3) {
                f3 = cee_ - height;
            } else if (i != 4) {
                f3 = (cee_ - height) / 2.0f;
            } else {
                f2 = -a2.top;
                this.c = 2;
            }
            f2 = f3 - a2.top;
            this.c = 2;
        } else {
            float f4 = a2.top;
            if (f4 > 0.0f) {
                this.c = 0;
                f2 = -f4;
            } else {
                float f5 = a2.bottom;
                if (f5 < cee_) {
                    this.c = 1;
                    f2 = cee_ - f5;
                } else {
                    this.c = -1;
                    f2 = 0.0f;
                }
            }
        }
        return a(a2, width, 0.0f, f2);
    }

    private void cek_(Matrix matrix) {
        RectF a2;
        this.o.setImageMatrix(matrix);
        if (this.u == null || (a2 = a(matrix)) == null) {
            return;
        }
        this.u.a(a2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        if (k()) {
            cek_(cel_());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int cei_(ImageView imageView) {
        return (imageView.getWidth() - imageView.getPaddingLeft()) - imageView.getPaddingRight();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean ceg_(MotionEvent motionEvent) {
        View.OnClickListener onClickListener = this.z;
        if (onClickListener != null) {
            onClickListener.onClick(this.o);
        }
        RectF ces_ = ces_();
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        j jVar = this.ad;
        if (jVar != null) {
            jVar.a(this.o, x, y);
        }
        if (ces_ == null) {
            return false;
        }
        if (!ces_.contains(x, y)) {
            com.huawei.phoneservice.feedback.media.impl.wiget.pictureview.e eVar = this.y;
            if (eVar == null) {
                return false;
            }
            eVar.a(this.o);
            return false;
        }
        float width = (x - ces_.left) / ces_.width();
        float height = (y - ces_.top) / ces_.height();
        com.huawei.phoneservice.feedback.media.impl.wiget.pictureview.f fVar = this.w;
        if (fVar == null) {
            return true;
        }
        fVar.a(this.o, width, height);
        return true;
    }

    private boolean a(RectF rectF, float f2, float f3, float f4) {
        int i;
        float f5;
        float f6;
        float cei_ = cei_(this.o);
        if (f2 > cei_) {
            float f7 = rectF.left;
            if (f7 > 0.0f) {
                this.b = 0;
                f5 = -f7;
            } else {
                float f8 = rectF.right;
                if (f8 < cei_) {
                    f5 = cei_ - f8;
                    this.b = 1;
                } else {
                    i = -1;
                }
            }
            this.s.postTranslate(f5, f4);
            return true;
        }
        int i2 = e.b[this.ag.ordinal()];
        if (i2 == 3) {
            f6 = cei_ - f2;
        } else if (i2 != 4) {
            f6 = (cei_ - f2) / 2.0f;
        } else {
            f3 = -rectF.left;
            i = 2;
        }
        f3 = f6 - rectF.left;
        i = 2;
        this.b = i;
        f5 = f3;
        this.s.postTranslate(f5, f4);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean b(float f2, float f3) {
        if (!this.h || this.k.d() || this.j) {
            return true;
        }
        int i = this.b;
        boolean z = i != 2;
        if (i == 0 && f2 >= 1.0f) {
            z = false;
        }
        if (i == 1 && f2 <= -1.0f) {
            z = false;
        }
        int i2 = this.c;
        if (i2 == 0 && f3 >= 1.0f) {
            z = false;
        }
        if (i2 != 1 || f3 > -1.0f) {
            return z;
        }
        return false;
    }

    private void cef_(Drawable drawable) {
        Matrix matrix;
        Matrix.ScaleToFit scaleToFit;
        float min;
        if (drawable == null) {
            return;
        }
        float cei_ = cei_(this.o);
        float cee_ = cee_(this.o);
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        this.t.reset();
        float f2 = intrinsicWidth;
        float f3 = cei_ / f2;
        float f4 = intrinsicHeight;
        float f5 = cee_ / f4;
        ImageView.ScaleType scaleType = this.ag;
        if (scaleType == ImageView.ScaleType.CENTER) {
            this.t.postTranslate((cei_ - f2) / 2.0f, (cee_ - f4) / 2.0f);
        } else {
            if (scaleType == ImageView.ScaleType.CENTER_CROP) {
                min = Math.max(f3, f5);
            } else if (scaleType == ImageView.ScaleType.CENTER_INSIDE) {
                min = Math.min(1.0f, Math.min(f3, f5));
            } else {
                RectF rectF = new RectF(0.0f, 0.0f, f2, f4);
                RectF rectF2 = new RectF(0.0f, 0.0f, cei_, cee_);
                if (((int) this.d) % 180 != 0) {
                    rectF = new RectF(0.0f, 0.0f, f4, f2);
                }
                int i = e.b[this.ag.ordinal()];
                if (i == 1) {
                    matrix = this.t;
                    scaleToFit = Matrix.ScaleToFit.FILL;
                } else if (i == 2) {
                    matrix = this.t;
                    scaleToFit = Matrix.ScaleToFit.CENTER;
                } else if (i == 3) {
                    matrix = this.t;
                    scaleToFit = Matrix.ScaleToFit.END;
                } else if (i == 4) {
                    matrix = this.t;
                    scaleToFit = Matrix.ScaleToFit.START;
                }
                matrix.setRectToRect(rectF, rectF2, scaleToFit);
            }
            this.t.postScale(min, min);
            this.t.postTranslate((cei_ - (f2 * min)) / 2.0f, (cee_ - (f4 * min)) / 2.0f);
        }
        m();
    }

    private void g() {
        f fVar = this.f8261a;
        if (fVar != null) {
            fVar.e();
            this.f8261a = null;
        }
    }

    class a implements com.huawei.phoneservice.feedback.media.impl.wiget.pictureview.c {
        @Override // com.huawei.phoneservice.feedback.media.impl.wiget.pictureview.c
        public void a(float f, float f2, float f3, float f4, float f5) {
            if (l.this.f() < l.this.l || f < 1.0f) {
                if (l.this.x != null) {
                    l.this.x.a(f, f2, f3);
                }
                l.this.s.postScale(f, f, f2, f3);
                l.this.s.postTranslate(f4, f5);
                l.this.h();
            }
        }

        @Override // com.huawei.phoneservice.feedback.media.impl.wiget.pictureview.c
        public void a(float f, float f2, float f3, float f4) {
            l lVar = l.this;
            lVar.f8261a = lVar.new f(lVar.o.getContext());
            f fVar = l.this.f8261a;
            l lVar2 = l.this;
            int cei_ = lVar2.cei_(lVar2.o);
            l lVar3 = l.this;
            fVar.e(cei_, lVar3.cee_(lVar3.o), (int) f3, (int) f4);
            l.this.o.post(l.this.f8261a);
        }

        @Override // com.huawei.phoneservice.feedback.media.impl.wiget.pictureview.c
        public void a(float f, float f2, float f3) {
            a(f, f2, f3, 0.0f, 0.0f);
        }

        @Override // com.huawei.phoneservice.feedback.media.impl.wiget.pictureview.c
        public void a(float f, float f2) {
            if (l.this.k.d()) {
                return;
            }
            if (l.this.ac != null) {
                l.this.ac.a(f, f2);
            }
            l.this.s.postTranslate(f, f2);
            l.this.h();
            ViewParent parent = l.this.o.getParent();
            boolean b = l.this.b(f, f2);
            if (parent != null) {
                parent.requestDisallowInterceptTouchEvent(b);
            }
        }

        a() {
        }
    }

    class d implements GestureDetector.OnDoubleTapListener {
        @Override // android.view.GestureDetector.OnDoubleTapListener
        public boolean onDoubleTapEvent(MotionEvent motionEvent) {
            return false;
        }

        @Override // android.view.GestureDetector.OnDoubleTapListener
        public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
            return l.this.ceg_(motionEvent);
        }

        @Override // android.view.GestureDetector.OnDoubleTapListener
        public boolean onDoubleTap(MotionEvent motionEvent) {
            l lVar;
            float b;
            try {
                float f = l.this.f();
                float x = motionEvent.getX();
                float y = motionEvent.getY();
                if (f < l.this.d()) {
                    lVar = l.this;
                    b = lVar.d();
                } else if (f < l.this.d() || f >= l.this.c()) {
                    lVar = l.this;
                    b = lVar.b();
                } else {
                    lVar = l.this;
                    b = lVar.c();
                }
                lVar.e(b, x, y, true);
            } catch (ArrayIndexOutOfBoundsException unused) {
            }
            return true;
        }

        d() {
        }
    }

    class f implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        private final OverScroller f8264a;
        private int c;
        private int d;

        @Override // java.lang.Runnable
        public void run() {
            if (!this.f8264a.isFinished() && this.f8264a.computeScrollOffset()) {
                int currX = this.f8264a.getCurrX();
                int currY = this.f8264a.getCurrY();
                l.this.s.postTranslate(this.d - currX, this.c - currY);
                l.this.h();
                this.d = currX;
                this.c = currY;
                com.huawei.phoneservice.feedback.media.impl.wiget.pictureview.b.cdT_(l.this.o, this);
            }
        }

        public void e(int i, int i2, int i3, int i4) {
            int i5;
            int i6;
            int i7;
            int i8;
            RectF ces_ = l.this.ces_();
            if (ces_ == null) {
                return;
            }
            int round = Math.round(-ces_.left);
            float f = i;
            if (f < ces_.width()) {
                i6 = Math.round(ces_.width() - f);
                i5 = 0;
            } else {
                i5 = round;
                i6 = i5;
            }
            int round2 = Math.round(-ces_.top);
            float f2 = i2;
            if (f2 < ces_.height()) {
                i8 = Math.round(ces_.height() - f2);
                i7 = 0;
            } else {
                i7 = round2;
                i8 = i7;
            }
            this.d = round;
            this.c = round2;
            if (round == i6 && round2 == i8) {
                return;
            }
            this.f8264a.fling(round, round2, i3, i4, i5, i6, i7, i8, 0, 0);
        }

        public void e() {
            this.f8264a.forceFinished(true);
        }

        f(Context context) {
            this.f8264a = new OverScroller(context);
        }
    }

    private RectF a(Matrix matrix) {
        if (this.o.getDrawable() == null) {
            return null;
        }
        this.q.set(0.0f, 0.0f, r0.getIntrinsicWidth(), r0.getIntrinsicHeight());
        matrix.mapRect(this.q);
        return this.q;
    }

    class b implements Runnable {
        private final float b;
        private final long c = System.currentTimeMillis();
        private final float d;
        private final float e;
        private final float g;

        @Override // java.lang.Runnable
        public void run() {
            float b = b();
            float f = this.d;
            l.this.e.a((f + ((this.g - f) * b)) / l.this.f(), this.e, this.b);
            if (b < 1.0f) {
                com.huawei.phoneservice.feedback.media.impl.wiget.pictureview.b.cdT_(l.this.o, this);
            }
        }

        private float b() {
            return l.this.n.getInterpolation(Math.min(1.0f, ((System.currentTimeMillis() - this.c) * 1.0f) / l.this.g));
        }

        b(float f, float f2, float f3, float f4) {
            this.e = f3;
            this.b = f4;
            this.d = f;
            this.g = f2;
        }
    }

    class c extends GestureDetector.SimpleOnGestureListener {
        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public void onLongPress(MotionEvent motionEvent) {
            if (l.this.v != null) {
                l.this.v.onLongClick(l.this.o);
            }
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            if (l.this.aa == null || l.this.f() > 1.0f || motionEvent.getPointerCount() > 1 || motionEvent2.getPointerCount() > 1) {
                return false;
            }
            return l.this.aa.onFling(motionEvent, motionEvent2, f, f2);
        }

        c() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int cee_(ImageView imageView) {
        return (imageView.getHeight() - imageView.getPaddingTop()) - imageView.getPaddingBottom();
    }

    static /* synthetic */ class e {
        static final /* synthetic */ int[] b;

        static {
            int[] iArr = new int[ImageView.ScaleType.values().length];
            b = iArr;
            try {
                iArr[ImageView.ScaleType.FIT_XY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                b[ImageView.ScaleType.FIT_CENTER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                b[ImageView.ScaleType.FIT_END.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                b[ImageView.ScaleType.FIT_START.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private float ced_(Matrix matrix, int i) {
        matrix.getValues(this.p);
        return this.p[i];
    }

    public l(ImageView imageView) {
        a aVar = new a();
        this.e = aVar;
        this.o = imageView;
        imageView.setOnTouchListener(this);
        imageView.addOnLayoutChangeListener(this);
        if (imageView.isInEditMode()) {
            return;
        }
        this.d = 0.0f;
        this.k = new com.huawei.phoneservice.feedback.media.impl.wiget.pictureview.a(imageView.getContext(), aVar);
        this.m = new GestureDetector(imageView.getContext(), new c());
        o();
    }
}
