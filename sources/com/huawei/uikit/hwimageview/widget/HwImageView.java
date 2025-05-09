package com.huawei.uikit.hwimageview.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.view.ViewTreeObserver;
import android.widget.RemoteViews;
import androidx.appcompat.widget.AppCompatImageView;
import com.huawei.health.R;
import com.huawei.uikit.hwdragdowntransition.anim.HwDragDownAnimationListener;
import com.huawei.uikit.hwdragdowntransition.anim.HwDragDownTransition;
import com.huawei.uikit.hwimageview.widget.drawable.HwAnimatedGradientDrawable;
import com.huawei.uikit.hwunifiedinteract.widget.HwCompoundEventDetector;
import defpackage.slc;
import defpackage.sls;
import java.lang.reflect.Method;

@RemoteViews.RemoteView
/* loaded from: classes9.dex */
public class HwImageView extends AppCompatImageView implements ViewTreeObserver.OnScrollChangedListener {

    /* renamed from: a, reason: collision with root package name */
    protected HwDragDownTransition f10674a;
    private float b;
    private float c;
    private float d;
    private boolean e;
    private HwCompoundEventDetector f;
    private HwParallaxStyle g;
    private int[] h;
    private int[] i;
    private HwCompoundEventDetector.OnZoomEventListener j;
    private boolean k;
    private LayerDrawable l;
    private int m;
    private Context n;
    private boolean o;
    private float t;

    class b extends ViewOutlineProvider {
        final /* synthetic */ Drawable b;

        b(Drawable drawable) {
            this.b = drawable;
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View view, Outline outline) {
            this.b.getOutline(outline);
        }
    }

    class e implements HwCompoundEventDetector.OnZoomEventListener {
        e() {
        }

        @Override // com.huawei.uikit.hwunifiedinteract.widget.HwCompoundEventDetector.OnZoomEventListener
        public boolean onZoom(float f, MotionEvent motionEvent) {
            if (!HwImageView.this.b(f, 0.0f)) {
                HwImageView.this.a(f > 0.0f, motionEvent);
            }
            return true;
        }
    }

    public HwImageView(Context context) {
        this(context, null);
    }

    private boolean e(int i) {
        return i == 0 || i == 1;
    }

    private void edK_(Context context, AttributeSet attributeSet) {
        d(context);
        edI_(context, attributeSet);
        this.n = context;
        this.f = c();
        this.j = e();
        setValueFromPlume(context);
    }

    private void setParallaxStyleEffect(int i) {
        HwParallaxStyle hwParallaxStyle = this.g;
        if (hwParallaxStyle != null) {
            hwParallaxStyle.onDetachedFromImageView(this);
            this.g = null;
        }
        if (i == 0 || i != 1) {
            return;
        }
        sls slsVar = new sls();
        this.g = slsVar;
        slsVar.onAttachedToImageView(this);
    }

    private void setValueFromPlume(Context context) {
        Method b2 = slc.b("getBoolean", new Class[]{Context.class, View.class, String.class, Boolean.TYPE}, "huawei.android.widget.HwPlume");
        if (b2 == null) {
            return;
        }
        Object c = slc.c((Object) null, b2, new Object[]{context, this, "zoomEnabled", false});
        if (c instanceof Boolean) {
            setOnZoomEnabled(((Boolean) c).booleanValue());
        }
    }

    protected HwCompoundEventDetector c() {
        return new HwCompoundEventDetector(this.n);
    }

    public boolean d() {
        HwCompoundEventDetector hwCompoundEventDetector = this.f;
        return (hwCompoundEventDetector == null || hwCompoundEventDetector.c() == null) ? false : true;
    }

    protected HwCompoundEventDetector.OnZoomEventListener e() {
        return new e();
    }

    @Override // android.view.View
    public Drawable getBackground() {
        LayerDrawable layerDrawable = this.l;
        if (layerDrawable == null) {
            return super.getBackground();
        }
        if (layerDrawable.getNumberOfLayers() == 1) {
            return null;
        }
        return this.l.getDrawable(0);
    }

    public HwCompoundEventDetector.OnZoomEventListener getOnZoomListener() {
        return this.j;
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.m == 0) {
            b();
        } else {
            a();
        }
        HwDragDownTransition hwDragDownTransition = this.f10674a;
        if (hwDragDownTransition != null && this.o) {
            hwDragDownTransition.b();
        }
        setOnZoomEnabled(d());
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDetachedFromWindow() {
        HwCompoundEventDetector hwCompoundEventDetector = this.f;
        if (hwCompoundEventDetector != null) {
            hwCompoundEventDetector.b();
        }
        b();
        super.onDetachedFromWindow();
    }

    @Override // android.widget.ImageView, android.view.View
    public void onDraw(Canvas canvas) {
        if (canvas == null) {
            return;
        }
        if (this.m == 0 || getDrawable() == null) {
            super.onDraw(canvas);
            return;
        }
        if (this.m == 1 && this.g != null) {
            getLocationInWindow(this.i);
            this.g.transform(this, canvas, this.i, this.h);
        }
        super.onDraw(canvas);
    }

    @Override // android.view.View
    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        HwCompoundEventDetector hwCompoundEventDetector = this.f;
        if (hwCompoundEventDetector == null || !hwCompoundEventDetector.eim_(motionEvent)) {
            return super.onGenericMotionEvent(motionEvent);
        }
        return true;
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        HwCompoundEventDetector hwCompoundEventDetector = this.f;
        if (hwCompoundEventDetector == null || !hwCompoundEventDetector.ein_(i, keyEvent)) {
            return super.onKeyDown(i, keyEvent);
        }
        return true;
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        HwCompoundEventDetector hwCompoundEventDetector = this.f;
        if (hwCompoundEventDetector == null || !hwCompoundEventDetector.ein_(i, keyEvent)) {
            return super.onKeyUp(i, keyEvent);
        }
        return true;
    }

    @Override // android.view.ViewTreeObserver.OnScrollChangedListener
    public void onScrollChanged() {
        if (this.m != 0) {
            invalidate();
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        HwDragDownTransition hwDragDownTransition = this.f10674a;
        if (hwDragDownTransition == null || !hwDragDownTransition.edb_(motionEvent)) {
            return super.onTouchEvent(motionEvent);
        }
        return true;
    }

    @Override // android.view.View
    public void setBackground(Drawable drawable) {
        if (this.l == null) {
            super.setBackground(drawable);
            return;
        }
        LayerDrawable edH_ = edH_(drawable);
        this.l = edH_;
        super.setBackground(edH_);
    }

    public void setDestRect(Rect rect) {
        HwDragDownTransition hwDragDownTransition = this.f10674a;
        if (hwDragDownTransition != null) {
            hwDragDownTransition.edc_(rect);
        }
    }

    public void setDragDownAnimationListener(HwDragDownAnimationListener hwDragDownAnimationListener) {
        HwDragDownTransition hwDragDownTransition = this.f10674a;
        if (hwDragDownTransition != null) {
            hwDragDownTransition.e(hwDragDownAnimationListener);
        }
    }

    public void setOnZoomEnabled(boolean z) {
        HwCompoundEventDetector hwCompoundEventDetector = this.f;
        if (hwCompoundEventDetector == null) {
            return;
        }
        if (z) {
            hwCompoundEventDetector.eip_(this, this.j);
        } else {
            hwCompoundEventDetector.eip_(this, null);
        }
    }

    public void setOnZoomListener(HwCompoundEventDetector.OnZoomEventListener onZoomEventListener) {
        HwCompoundEventDetector hwCompoundEventDetector = this.f;
        if (hwCompoundEventDetector == null) {
            Log.e("HwImageView", "setOnZoomListener: mHwCompoundEventDetector is null");
        } else {
            this.j = onZoomEventListener;
            hwCompoundEventDetector.eip_(this, onZoomEventListener);
        }
    }

    public void setParallaxStyle(int i) {
        if (!e(i)) {
            Log.e("HwImageView", "setParallaxStyle: Unsupported parallax style. Set HwImageView parallax style to 'none'.");
            this.m = 0;
            setParallaxStyleEffect(0);
            b();
            requestLayout();
            return;
        }
        if (i != this.m) {
            this.m = i;
            setParallaxStyleEffect(i);
            if (this.m == 0) {
                b();
            } else {
                a();
            }
            requestLayout();
        }
    }

    public HwImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.i = new int[2];
        this.h = new int[2];
        this.m = 0;
        this.k = false;
        this.o = false;
        this.l = null;
        this.t = 1.0f;
        this.c = 1.0f;
        this.e = false;
        this.b = 0.0f;
        this.d = 0.0f;
        this.f = null;
        edK_(context, attributeSet);
    }

    private void edI_(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131100256_res_0x7f060260, R.attr._2131100264_res_0x7f060268, R.attr._2131100425_res_0x7f060309});
        boolean z = obtainStyledAttributes.getBoolean(1, false);
        setClipToOutline(z);
        Drawable background = getBackground();
        if (obtainStyledAttributes.getBoolean(0, false)) {
            LayerDrawable edH_ = edH_(background);
            this.l = edH_;
            super.setBackground(edH_);
        }
        if (z && background != null) {
            setOutlineProvider(new b(background));
        }
        int integer = obtainStyledAttributes.getInteger(2, 0);
        this.m = integer;
        setParallaxStyleEffect(integer);
        obtainStyledAttributes.recycle();
    }

    private void b() {
        if (this.k) {
            getViewTreeObserver().removeOnScrollChangedListener(this);
            this.k = false;
        }
    }

    public HwImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.i = new int[2];
        this.h = new int[2];
        this.m = 0;
        this.k = false;
        this.o = false;
        this.l = null;
        this.t = 1.0f;
        this.c = 1.0f;
        this.e = false;
        this.b = 0.0f;
        this.d = 0.0f;
        this.f = null;
        edK_(context, attributeSet);
    }

    private void d(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (context instanceof Activity) {
            ((Activity) context).getWindowManager().getDefaultDisplay().getRealMetrics(displayMetrics);
        } else {
            displayMetrics = getResources().getDisplayMetrics();
        }
        int[] iArr = this.h;
        iArr[0] = displayMetrics.widthPixels;
        iArr[1] = displayMetrics.heightPixels;
    }

    private void a() {
        if (this.k) {
            return;
        }
        getViewTreeObserver().addOnScrollChangedListener(this);
        this.k = true;
    }

    private LayerDrawable edH_(Drawable drawable) {
        HwAnimatedGradientDrawable hwAnimatedGradientDrawable = new HwAnimatedGradientDrawable(getContext());
        if (drawable == null) {
            LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{hwAnimatedGradientDrawable});
            layerDrawable.setId(0, 1);
            return layerDrawable;
        }
        LayerDrawable layerDrawable2 = new LayerDrawable(new Drawable[]{drawable, hwAnimatedGradientDrawable});
        layerDrawable2.setId(0, 0);
        layerDrawable2.setId(1, 1);
        return layerDrawable2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z, MotionEvent motionEvent) {
        if (z) {
            float f = this.t * 1.1f;
            if (f > 2.6f) {
                return;
            } else {
                this.t = f;
            }
        } else {
            float f2 = this.t;
            this.t = f2 - (0.1f * f2);
        }
        float[] fArr = new float[9];
        getMatrix().getValues(fArr);
        setPivotX(0.0f);
        setPivotY(0.0f);
        float f3 = this.t;
        if (f3 >= 1.0f && !b(f3, 1.0f)) {
            float x = fArr[2] - (motionEvent.getX() * (this.t - fArr[0]));
            float y = fArr[5] - (motionEvent.getY() * (this.t - fArr[4]));
            int width = getWidth();
            int height = getHeight();
            int edG_ = edG_(x, y, fArr, motionEvent);
            if (edG_ == 1) {
                a(fArr, motionEvent, true);
                return;
            }
            if (edG_ == 2) {
                a(fArr, motionEvent, false);
                return;
            }
            if (edG_ == 3) {
                a(fArr, motionEvent, width, true);
                return;
            } else {
                if (edG_ == 4) {
                    a(fArr, motionEvent, height, false);
                    return;
                }
                this.c = 1.0f;
                this.e = false;
                c(this.t, x, y);
                return;
            }
        }
        this.t = 1.0f;
        c(1.0f, 0.0f, 0.0f);
        this.d = 0.0f;
        this.b = 0.0f;
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x00b1  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x004b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private int edG_(float r11, float r12, float[] r13, android.view.MotionEvent r14) {
        /*
            r10 = this;
            r0 = 0
            int r1 = (r11 > r0 ? 1 : (r11 == r0 ? 0 : -1))
            r2 = 2
            r3 = 0
            r4 = 1065353216(0x3f800000, float:1.0)
            if (r1 > 0) goto Lf
            boolean r1 = r10.b(r11, r0)
            if (r1 == 0) goto L27
        Lf:
            float r1 = r14.getX()
            int r1 = (r1 > r0 ? 1 : (r1 == r0 ? 0 : -1))
            if (r1 <= 0) goto L27
            r1 = r13[r2]
            float r5 = r14.getX()
            float r1 = r1 / r5
            r5 = r13[r3]
            float r1 = r1 + r5
            int r5 = (r1 > r4 ? 1 : (r1 == r4 ? 0 : -1))
            if (r5 <= 0) goto L27
            r5 = 1
            goto L29
        L27:
            r5 = r3
            r1 = r4
        L29:
            int r6 = (r12 > r0 ? 1 : (r12 == r0 ? 0 : -1))
            r7 = 5
            r8 = 4
            if (r6 > 0) goto L35
            boolean r6 = r10.b(r12, r0)
            if (r6 == 0) goto L4d
        L35:
            float r6 = r14.getY()
            int r6 = (r6 > r0 ? 1 : (r6 == r0 ? 0 : -1))
            if (r6 <= 0) goto L4d
            r6 = r13[r7]
            float r9 = r14.getY()
            float r6 = r6 / r9
            r9 = r13[r8]
            float r6 = r6 + r9
            int r9 = (r6 > r1 ? 1 : (r6 == r1 ? 0 : -1))
            if (r9 <= 0) goto L4d
            r5 = r2
            r1 = r6
        L4d:
            int r6 = r10.getWidth()
            float r9 = r10.t
            float r6 = (float) r6
            float r9 = r9 - r4
            float r9 = r9 * r6
            float r11 = r11 + r9
            int r9 = (r11 > r0 ? 1 : (r11 == r0 ? 0 : -1))
            if (r9 < 0) goto L61
            boolean r11 = r10.b(r11, r0)
            if (r11 == 0) goto L80
        L61:
            float r11 = r14.getX()
            int r11 = (r11 > r6 ? 1 : (r11 == r6 ? 0 : -1))
            if (r11 >= 0) goto L80
            r11 = r13[r2]
            float r2 = r14.getX()
            r3 = r13[r3]
            float r11 = r11 - r6
            float r2 = r2 * r3
            float r11 = r11 + r2
            float r2 = r14.getX()
            float r2 = r2 - r6
            float r11 = r11 / r2
            int r2 = (r11 > r1 ? 1 : (r11 == r1 ? 0 : -1))
            if (r2 <= 0) goto L80
            r5 = 3
            r1 = r11
        L80:
            int r11 = r10.getHeight()
            float r2 = r10.t
            float r11 = (float) r11
            float r2 = r2 - r4
            float r2 = r2 * r11
            float r12 = r12 + r2
            int r2 = (r12 > r0 ? 1 : (r12 == r0 ? 0 : -1))
            if (r2 < 0) goto L94
            boolean r12 = r10.b(r12, r0)
            if (r12 == 0) goto Lb3
        L94:
            float r12 = r14.getY()
            int r12 = (r12 > r11 ? 1 : (r12 == r11 ? 0 : -1))
            if (r12 >= 0) goto Lb3
            r12 = r13[r7]
            float r0 = r14.getY()
            r13 = r13[r8]
            float r12 = r12 - r11
            float r0 = r0 * r13
            float r12 = r12 + r0
            float r13 = r14.getY()
            float r13 = r13 - r11
            float r12 = r12 / r13
            int r11 = (r12 > r1 ? 1 : (r12 == r1 ? 0 : -1))
            if (r11 <= 0) goto Lb3
            r1 = r12
            goto Lb4
        Lb3:
            r8 = r5
        Lb4:
            r10.c = r1
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.uikit.hwimageview.widget.HwImageView.edG_(float, float, float[], android.view.MotionEvent):int");
    }

    private void a(float[] fArr, MotionEvent motionEvent, boolean z) {
        if (z) {
            if (!this.e) {
                float f = fArr[5];
                float y = motionEvent.getY();
                float f2 = this.c;
                float f3 = fArr[4];
                float f4 = f - (y * (f2 - f3));
                this.d = 0.0f;
                if (f2 > 1.0f) {
                    this.b = f4 / (1.0f - f2);
                } else if (f3 > 1.0f) {
                    this.b = fArr[5] / (1.0f - f3);
                } else {
                    this.b = 0.0f;
                }
                c(f2, 0.0f, f4);
                this.e = true;
            }
            float f5 = this.t;
            c(f5, 0.0f, this.b * (1.0f - f5));
            return;
        }
        if (!this.e) {
            float f6 = fArr[2];
            float x = motionEvent.getX();
            float f7 = this.c;
            float f8 = fArr[0];
            float f9 = f6 - (x * (f7 - f8));
            if (f7 > 1.0f) {
                this.d = f9 / (1.0f - f7);
            } else if (f8 > 1.0f) {
                this.d = fArr[2] / (1.0f - f8);
            } else {
                this.d = 0.0f;
            }
            this.b = 0.0f;
            c(f7, f9, 0.0f);
            this.e = true;
        }
        float f10 = this.t;
        c(f10, this.d * (1.0f - f10), 0.0f);
    }

    private void a(float[] fArr, MotionEvent motionEvent, int i, boolean z) {
        if (z) {
            if (!this.e) {
                float f = fArr[5];
                float y = motionEvent.getY();
                float f2 = this.c;
                float f3 = fArr[4];
                float f4 = f - (y * (f2 - f3));
                this.d = i;
                if (f2 > 1.0f) {
                    this.b = f4 / (1.0f - f2);
                } else if (f3 > 1.0f) {
                    this.b = fArr[5] / (1.0f - f3);
                } else {
                    this.b = getHeight();
                }
                c(this.c, fArr[2] - (motionEvent.getX() * (this.c - fArr[0])), f4);
                this.e = true;
            }
            float f5 = this.t;
            float f6 = 1.0f - f5;
            c(f5, this.d * f6, this.b * f6);
            return;
        }
        if (!this.e) {
            float f7 = fArr[2];
            float x = motionEvent.getX();
            float f8 = this.c;
            float f9 = fArr[0];
            float f10 = f7 - (x * (f8 - f9));
            if (f8 > 1.0f) {
                this.d = f10 / (1.0f - f8);
            } else if (f9 > 1.0f) {
                this.d = fArr[2] / (1.0f - f9);
            } else {
                this.d = getWidth();
            }
            this.b = i;
            c(this.c, f10, fArr[5] - (motionEvent.getX() * (this.c - fArr[4])));
            this.e = true;
        }
        float f11 = this.t;
        float f12 = 1.0f - f11;
        c(f11, this.d * f12, this.b * f12);
    }

    private void c(float f, float f2, float f3) {
        setScaleX(f);
        setScaleY(f);
        setTranslationX(f2);
        setTranslationY(f3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean b(float f, float f2) {
        return Math.abs(f - f2) < 1.0E-6f;
    }
}
