package com.huawei.health.suggestion.ui.fitness.module;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Property;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

/* loaded from: classes4.dex */
public class SwitchView extends View {

    /* renamed from: a, reason: collision with root package name */
    private int f3188a;
    private OnSwitchStateChangeListener aa;
    private int ab;
    private Property<SwitchView, Float> ac;
    private float ad;
    private RectF ae;
    private int af;
    private int ag;
    private Paint ah;
    private boolean ai;
    private int aj;
    private int am;
    private int an;
    private int b;
    private Property<SwitchView, Float> d;
    private int f;
    private float g;
    private GestureDetector.SimpleOnGestureListener h;
    private GestureDetector i;
    private int j;
    private float k;
    private float l;
    private RectF m;
    private ObjectAnimator n;
    private float o;
    private boolean p;
    private float q;
    private boolean r;
    private boolean s;
    private boolean t;
    private RectF u;
    private Property<SwitchView, Float> v;
    private ObjectAnimator w;
    private float x;
    private float y;
    private ObjectAnimator z;
    private static final int e = Color.parseColor("#95AAB3");
    private static final int c = Color.parseColor("#FFFFFF");

    public interface OnSwitchStateChangeListener {
        void onSwitchStateChange(View view, boolean z);
    }

    private int b(float f, int i, int i2) {
        return ((((i >> 16) & 255) + ((int) ((((i2 >> 16) & 255) - r0) * f))) << 16) | (-16777216) | ((((i >> 8) & 255) + ((int) ((((i2 >> 8) & 255) - r1) * f))) << 8) | ((i & 255) + ((int) (((i2 & 255) - r5) * f)));
    }

    public SwitchView(Context context) {
        this(context, null);
    }

    public SwitchView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SwitchView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.d = new Property<SwitchView, Float>(Float.class, "innerBound") { // from class: com.huawei.health.suggestion.ui.fitness.module.SwitchView.5
            @Override // android.util.Property
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public void set(SwitchView switchView, Float f) {
                if (switchView == null) {
                    return;
                }
                switchView.setInnerContentRate(f.floatValue());
            }

            @Override // android.util.Property
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public Float get(SwitchView switchView) {
                if (switchView == null) {
                    return null;
                }
                return Float.valueOf(switchView.getInnerContentRate());
            }
        };
        this.v = new Property<SwitchView, Float>(Float.class, "knobExpand") { // from class: com.huawei.health.suggestion.ui.fitness.module.SwitchView.4
            @Override // android.util.Property
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public void set(SwitchView switchView, Float f) {
                if (switchView == null) {
                    return;
                }
                switchView.setKnobExpandRate(f.floatValue());
            }

            @Override // android.util.Property
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public Float get(SwitchView switchView) {
                if (switchView == null) {
                    return Float.valueOf(0.0f);
                }
                return Float.valueOf(switchView.getKnobExpandRate());
            }
        };
        this.ac = new Property<SwitchView, Float>(Float.class, "knobMove") { // from class: com.huawei.health.suggestion.ui.fitness.module.SwitchView.2
            @Override // android.util.Property
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void set(SwitchView switchView, Float f) {
                switchView.setKnobMoveRate(f.floatValue());
            }

            @Override // android.util.Property
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public Float get(SwitchView switchView) {
                return Float.valueOf(switchView.getKnobMoveRate());
            }
        };
        this.h = new GestureDetector.SimpleOnGestureListener() { // from class: com.huawei.health.suggestion.ui.fitness.module.SwitchView.3
            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public void onShowPress(MotionEvent motionEvent) {
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onDown(MotionEvent motionEvent) {
                if (!SwitchView.this.isEnabled()) {
                    return false;
                }
                SwitchView switchView = SwitchView.this;
                switchView.ai = switchView.s;
                SwitchView.this.n.setFloatValues(SwitchView.this.l, 0.0f);
                SwitchView.this.n.start();
                SwitchView.this.w.setFloatValues(SwitchView.this.x, 1.0f);
                SwitchView.this.w.start();
                return true;
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onSingleTapUp(MotionEvent motionEvent) {
                if (motionEvent == null) {
                    return false;
                }
                super.onSingleTapUp(motionEvent);
                SwitchView switchView = SwitchView.this;
                switchView.s = switchView.p;
                if (SwitchView.this.ai == SwitchView.this.s) {
                    SwitchView.this.s = !r5.s;
                    SwitchView.this.p = !r5.p;
                }
                SwitchView switchView2 = SwitchView.this;
                switchView2.d(switchView2.p);
                SwitchView.this.w.setFloatValues(SwitchView.this.x, 0.0f);
                SwitchView.this.w.start();
                if (SwitchView.this.aa != null && SwitchView.this.s != SwitchView.this.ai) {
                    OnSwitchStateChangeListener onSwitchStateChangeListener = SwitchView.this.aa;
                    SwitchView switchView3 = SwitchView.this;
                    onSwitchStateChangeListener.onSwitchStateChange(switchView3, switchView3.s);
                }
                return true;
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
                if (motionEvent2 == null || motionEvent2.getX() <= SwitchView.this.b) {
                    if (SwitchView.this.p) {
                        SwitchView.this.p = false;
                        SwitchView.this.z.setFloatValues(SwitchView.this.ad, 0.0f);
                        SwitchView.this.z.start();
                    }
                } else if (!SwitchView.this.p) {
                    SwitchView.this.p = true;
                    SwitchView.this.z.setFloatValues(SwitchView.this.ad, 1.0f);
                    SwitchView.this.z.start();
                    SwitchView.this.n.setFloatValues(SwitchView.this.l, 0.0f);
                    SwitchView.this.n.start();
                }
                return true;
            }
        };
        this.l = 1.0f;
        int parseColor = Color.parseColor("#00CCFF");
        this.aj = parseColor;
        this.f = e;
        this.r = true;
        this.t = false;
        this.af = 4;
        this.am = parseColor;
        this.ag = 0;
        this.u = new RectF();
        this.m = new RectF();
        this.ae = new RectF();
        this.ah = new Paint(1);
        GestureDetector gestureDetector = new GestureDetector(context, this.h);
        this.i = gestureDetector;
        gestureDetector.setIsLongpressEnabled(false);
        setLayerType(1, null);
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, this.d, this.l, 1.0f);
        this.n = ofFloat;
        ofFloat.setDuration(300L);
        this.n.setInterpolator(new DecelerateInterpolator());
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this, this.v, this.x, 1.0f);
        this.w = ofFloat2;
        ofFloat2.setDuration(300L);
        this.w.setInterpolator(new DecelerateInterpolator());
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(this, this.ac, this.ad, 1.0f);
        this.z = ofFloat3;
        ofFloat3.setDuration(300L);
        this.z.setInterpolator(new DecelerateInterpolator());
    }

    public void setOnSwitchStateChangeListener(OnSwitchStateChangeListener onSwitchStateChangeListener) {
        this.aa = onSwitchStateChangeListener;
    }

    void setInnerContentRate(float f) {
        this.l = f;
        invalidate();
    }

    float getInnerContentRate() {
        return this.l;
    }

    void setKnobExpandRate(float f) {
        this.x = f;
        invalidate();
    }

    float getKnobExpandRate() {
        return this.x;
    }

    void setKnobMoveRate(float f) {
        this.ad = f;
        invalidate();
    }

    float getKnobMoveRate() {
        return this.ad;
    }

    @Override // android.view.View
    protected void onAttachedToWindow() {
        boolean z;
        super.onAttachedToWindow();
        this.t = true;
        if (this.r) {
            boolean z2 = this.s;
            this.p = z2;
            d(z2);
            this.w.setFloatValues(this.x, 0.0f);
            this.w.start();
            OnSwitchStateChangeListener onSwitchStateChangeListener = this.aa;
            if (onSwitchStateChangeListener != null && (z = this.s) != this.ai) {
                onSwitchStateChangeListener.onSwitchStateChange(this, z);
            }
            this.r = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(boolean z) {
        if (z) {
            this.z.setFloatValues(this.ad, 1.0f);
            this.z.start();
            this.n.setFloatValues(this.l, 0.0f);
            this.n.start();
            return;
        }
        this.z.setFloatValues(this.ad, 0.0f);
        this.z.start();
        this.n.setFloatValues(this.l, 1.0f);
        this.n.start();
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.t = false;
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.an = View.MeasureSpec.getSize(i);
        int size = View.MeasureSpec.getSize(i2);
        this.j = size;
        int i3 = this.an;
        float f = i3;
        if (size / f < 0.33333f) {
            this.j = (int) (f * 0.33333f);
            super.setMeasuredDimension(View.MeasureSpec.makeMeasureSpec(i3, View.MeasureSpec.getMode(i)), View.MeasureSpec.makeMeasureSpec(this.j, View.MeasureSpec.getMode(i2)));
        }
        this.b = this.an / 2;
        this.f3188a = this.j / 2;
        int i4 = this.ag;
        this.g = r5 - i4;
        this.m.left = this.ab + i4;
        this.m.top = this.ab + this.ag;
        this.m.right = (this.an - this.ab) - this.ag;
        this.m.bottom = (this.j - this.ab) - this.ag;
        this.k = this.m.width();
        this.o = this.m.height();
        this.u.left = this.af + this.ag;
        this.u.top = this.af + this.ag;
        this.u.right = (this.j - this.af) - this.ag;
        this.u.bottom = (this.j - this.af) - this.ag;
        this.q = this.u.height();
        float f2 = this.an * 0.7f;
        this.y = f2;
        if (f2 > this.u.width() * 1.25f) {
            this.y = this.u.width() * 1.25f;
        }
    }

    public SwitchView a(boolean z) {
        c(z, false);
        return this;
    }

    private void c(boolean z, boolean z2) {
        boolean z3;
        if (this.s == z) {
            return;
        }
        if (!this.t && z2) {
            this.r = true;
            this.s = z;
            return;
        }
        this.s = z;
        this.p = z;
        if (!z2) {
            if (z) {
                setKnobMoveRate(1.0f);
                setInnerContentRate(0.0f);
            } else {
                setKnobMoveRate(0.0f);
                setInnerContentRate(1.0f);
            }
            setKnobExpandRate(0.0f);
        } else {
            d(z);
            this.w.setFloatValues(this.x, 0.0f);
            this.w.start();
        }
        OnSwitchStateChangeListener onSwitchStateChangeListener = this.aa;
        if (onSwitchStateChangeListener == null || (z3 = this.s) == this.ai) {
            return;
        }
        onSwitchStateChangeListener.onSwitchStateChange(this, z3);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!isEnabled() || motionEvent == null) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action == 1 || action == 3) {
            if (!this.p) {
                ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, this.d, this.l, 1.0f);
                this.n = ofFloat;
                ofFloat.setDuration(300L);
                this.n.setInterpolator(new DecelerateInterpolator());
                this.n.start();
            }
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this, this.v, this.x, 0.0f);
            this.w = ofFloat2;
            ofFloat2.setDuration(300L);
            this.w.setInterpolator(new DecelerateInterpolator());
            this.w.start();
            boolean z = this.p;
            this.s = z;
            OnSwitchStateChangeListener onSwitchStateChangeListener = this.aa;
            if (onSwitchStateChangeListener != null && z != this.ai) {
                onSwitchStateChangeListener.onSwitchStateChange(this, z);
            }
        }
        return this.i.onTouchEvent(motionEvent);
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        if (z) {
            this.aj = this.am;
        } else {
            this.aj = b(0.5f, this.am, -1);
        }
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        if (canvas == null) {
            return;
        }
        super.onDraw(canvas);
        float f = this.k / 2.0f;
        float f2 = this.l;
        float f3 = f * f2;
        float f4 = (this.o / 2.0f) * f2;
        this.m.left = this.b - f3;
        this.m.top = this.f3188a - f4;
        this.m.right = this.b + f3;
        this.m.bottom = this.f3188a + f4;
        float f5 = this.q;
        float f6 = f5 + ((this.y - f5) * this.x);
        if (this.u.left + (this.u.width() / 2.0f) > this.b) {
            RectF rectF = this.u;
            rectF.left = rectF.right - f6;
        } else {
            RectF rectF2 = this.u;
            rectF2.right = rectF2.left + f6;
        }
        float width = this.u.width();
        float f7 = this.an;
        int i = this.ag + this.af;
        float f8 = this.ad;
        this.f = this.aj;
        this.u.left = i + (((f7 - width) - (i * 2)) * f8);
        RectF rectF3 = this.u;
        rectF3.right = rectF3.left + width;
        this.ah.setColor(this.f);
        this.ah.setStyle(Paint.Style.FILL);
        float f9 = this.ag;
        aEu_(f9, f9, this.an - r0, this.j - r0, this.g, canvas, this.ah);
        this.ah.setColor(e);
        RectF rectF4 = this.m;
        canvas.drawRoundRect(rectF4, rectF4.height() / 2.0f, this.m.height() / 2.0f, this.ah);
        this.ah.setShadowLayer(2.0f, 0.0f, this.ag / 2.0f, isEnabled() ? 536870912 : 268435456);
        this.ah.setColor(c);
        RectF rectF5 = this.u;
        float f10 = this.g - this.ab;
        canvas.drawRoundRect(rectF5, f10, f10, this.ah);
        this.ah.setShadowLayer(0.0f, 0.0f, 0.0f, 0);
    }

    private void aEu_(float f, float f2, float f3, float f4, float f5, Canvas canvas, Paint paint) {
        this.ae.left = f;
        this.ae.top = f2;
        this.ae.right = f3;
        this.ae.bottom = f4;
        canvas.drawRoundRect(this.ae, f5, f5, paint);
    }
}
