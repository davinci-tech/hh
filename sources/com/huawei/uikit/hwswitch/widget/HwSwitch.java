package com.huawei.uikit.hwswitch.widget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Insets;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.util.AttributeSet;
import android.util.FloatProperty;
import android.util.Log;
import android.util.Property;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import android.widget.Switch;
import com.huawei.health.R;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.uikit.hwcommon.anim.HwCubicBezierInterpolator;
import defpackage.slc;
import defpackage.smr;
import java.util.Locale;

/* loaded from: classes7.dex */
public class HwSwitch extends Switch {
    private static final Property<HwSwitch, Float> b = new d("thumbPos");

    /* renamed from: a, reason: collision with root package name */
    private int f10763a;
    private int c;
    public Drawable d;
    private int e;
    private int f;
    private float g;
    private int h;
    private float i;
    private float j;
    private final Rect k;
    private VelocityTracker l;
    private int m;
    private ObjectAnimator n;
    private int o;
    private int p;
    private int q;
    private int r;
    private int s;
    private Drawable t;
    private int w;

    static final class d extends FloatProperty<HwSwitch> {
        d(String str) {
            super(str);
        }

        @Override // android.util.Property
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public Float get(HwSwitch hwSwitch) {
            if (hwSwitch == null) {
                return null;
            }
            return Float.valueOf(hwSwitch.g);
        }

        @Override // android.util.FloatProperty
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public void setValue(HwSwitch hwSwitch, float f) {
            if (hwSwitch == null) {
                Log.w("HwSwitch", "setValue: HwSwitch object is null!");
            } else {
                hwSwitch.setThumbPosition(f);
            }
        }
    }

    public HwSwitch(Context context) {
        this(context, null);
    }

    private void a() {
        if (this.t == null) {
            Object b2 = slc.b(this, "mThumbDrawable", (Class<?>) Switch.class);
            if (b2 instanceof Drawable) {
                this.t = (Drawable) b2;
            }
        }
        if (this.d == null) {
            Object b3 = slc.b(this, "mTrackDrawable", (Class<?>) Switch.class);
            if (b3 instanceof Drawable) {
                this.d = (Drawable) b3;
            }
        }
        Object b4 = slc.b(this, "mThumbWidth", (Class<?>) Switch.class);
        if (b4 instanceof Integer) {
            this.p = ((Integer) b4).intValue();
        }
    }

    private float c(float f, float f2, float f3) {
        return f < f2 ? f2 : f > f3 ? f3 : f;
    }

    private void c() {
        ObjectAnimator objectAnimator = this.n;
        if (objectAnimator != null) {
            objectAnimator.cancel();
        }
    }

    private void d() {
        Drawable.Callback callback = this.t.getCallback();
        this.t.setCallback(null);
        this.t.setBounds(0, 0, 0, 0);
        this.t.setCallback(callback);
    }

    private void e() {
        Object b2 = slc.b(this, "mSwitchWidth", (Class<?>) Switch.class);
        if (b2 instanceof Integer) {
            this.r = ((Integer) b2).intValue();
        }
        Object b3 = slc.b(this, "mSwitchHeight", (Class<?>) Switch.class);
        if (b3 instanceof Integer) {
            this.m = ((Integer) b3).intValue();
        }
    }

    private boolean ehV_(MotionEvent motionEvent) {
        float x = motionEvent.getX();
        int thumbScrollRange = getThumbScrollRange();
        float f = x - this.j;
        float f2 = thumbScrollRange != 0 ? f / thumbScrollRange : f > 0.0f ? 1.0f : -1.0f;
        if (i()) {
            f2 = -f2;
        }
        float c = c(this.g + f2, 0.0f, 1.0f);
        if (c == this.g) {
            return true;
        }
        this.j = x;
        setThumbPosition(c);
        return true;
    }

    private boolean f() {
        return this.g > 0.5f;
    }

    private int getThumbOffset() {
        return (int) (((!h() ? this.g : 1.0f - this.g) * getThumbScrollRange()) + 0.5f);
    }

    private int getThumbScrollRange() {
        Insets insets;
        Drawable drawable = this.d;
        if (drawable == null) {
            return 0;
        }
        Rect rect = this.k;
        drawable.getPadding(rect);
        Drawable drawable2 = this.t;
        if (drawable2 != null) {
            Object c = slc.c(drawable2, "getOpticalInsets", null, null, DrawableContainer.class);
            insets = c instanceof Insets ? (Insets) c : Insets.NONE;
        } else {
            insets = Insets.NONE;
        }
        Object b2 = slc.b(this, "mSwitchWidth", (Class<?>) Switch.class);
        if (b2 instanceof Integer) {
            this.r = ((Integer) b2).intValue();
        }
        int i = this.r;
        int i2 = this.p;
        int i3 = rect.left;
        int i4 = rect.right;
        int i5 = insets.left;
        int i6 = insets.right;
        int i7 = this.f10763a;
        return (((((i - i2) - i3) - i4) - i5) - i6) - (i7 + i7);
    }

    private boolean h() {
        String language = Locale.getDefault().getLanguage();
        return (language.contains("ar") || language.contains("fa") || language.contains("iw")) || (language.contains("ug") || language.contains(Constants.URDU_LANG) || i());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setThumbPosition(float f) {
        this.g = f;
        invalidate();
    }

    public int getFocusedPathColor() {
        return this.o;
    }

    @Override // android.widget.Switch, android.widget.CompoundButton, android.widget.TextView, android.view.View
    public void onDraw(Canvas canvas) {
        a();
        ehQ_(canvas);
        super.onDraw(canvas);
        d();
    }

    @Override // android.widget.Switch, android.widget.TextView, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5;
        super.onLayout(z, i, i2, i3, i4);
        a();
        e();
        if (this.t != null) {
            Rect rect = this.k;
            Drawable drawable = this.d;
            if (drawable != null) {
                drawable.getPadding(rect);
            } else {
                rect.setEmpty();
            }
            Object c = slc.c(this.t, "getOpticalInsets", null, null, DrawableContainer.class);
            if (c instanceof Insets) {
                Insets insets = (Insets) c;
                int i6 = insets.left - rect.left;
                if (i6 <= 0) {
                    i6 = 0;
                }
                int i7 = insets.right - rect.right;
                i5 = i7 > 0 ? i7 : 0;
                r3 = i6;
                b(r3, i5);
            }
        }
        i5 = 0;
        b(r3, i5);
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x001d, code lost:
    
        if (r1 != 3) goto L23;
     */
    @Override // android.widget.Switch, android.widget.TextView, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onTouchEvent(android.view.MotionEvent r6) {
        /*
            r5 = this;
            r0 = 0
            if (r6 != 0) goto Lb
            java.lang.String r6 = "HwSwitch"
            java.lang.String r1 = "onTouchEvent: MotionEvent motionEvent is null!"
            android.util.Log.w(r6, r1)
            return r0
        Lb:
            android.view.VelocityTracker r1 = r5.l
            r1.addMovement(r6)
            int r1 = r6.getActionMasked()
            if (r1 == 0) goto L3a
            r2 = 2
            r3 = 1
            if (r1 == r3) goto L27
            if (r1 == r2) goto L20
            r4 = 3
            if (r1 == r4) goto L27
            goto L3d
        L20:
            boolean r0 = r5.b(r6)
            if (r0 == 0) goto L3d
            return r3
        L27:
            int r1 = r5.c
            if (r1 != r2) goto L32
            r5.ehT_(r6)
            super.onTouchEvent(r6)
            return r3
        L32:
            r5.c = r0
            android.view.VelocityTracker r0 = r5.l
            r0.clear()
            goto L3d
        L3a:
            r5.ehR_(r6)
        L3d:
            boolean r6 = super.onTouchEvent(r6)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.uikit.hwswitch.widget.HwSwitch.onTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.widget.Switch, android.widget.CompoundButton, android.widget.Checkable
    public void setChecked(boolean z) {
        super.setChecked(z);
        boolean isChecked = isChecked();
        if (isAttachedToWindow() && isLaidOut()) {
            c(isChecked);
        } else {
            c();
            setThumbPosition(isChecked ? 1.0f : 0.0f);
        }
    }

    public void setFocusedPathColor(int i) {
        this.o = i;
    }

    protected void setSwitchWidth(int i) {
        this.r = i;
    }

    public HwSwitch(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr._2131100566_res_0x7f060396);
    }

    public HwSwitch(Context context, AttributeSet attributeSet, int i) {
        super(e(context, i), attributeSet, i);
        this.r = 0;
        this.p = 0;
        this.q = 0;
        this.s = 0;
        this.w = 0;
        this.e = 0;
        this.f10763a = 0;
        this.c = 0;
        this.f = 0;
        this.h = 0;
        this.l = VelocityTracker.obtain();
        this.k = new Rect();
        ehP_(getContext(), attributeSet, i);
    }

    private boolean b(MotionEvent motionEvent) {
        int i = this.c;
        if (i == 0) {
            return false;
        }
        if (i == 1) {
            return ehU_(motionEvent);
        }
        if (i != 2) {
            return false;
        }
        ehV_(motionEvent);
        return true;
    }

    private static Context e(Context context, int i) {
        return smr.b(context, i, R.style.Theme_Emui_HwSwitch);
    }

    private void ehP_(Context context, AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131100338_res_0x7f0602b2, R.attr._2131100394_res_0x7f0602ea}, i, R.style.Widget_Emui_HwSwitch);
        this.f10763a = (int) obtainStyledAttributes.getDimension(1, (getResources().getDisplayMetrics().density * 2.0f) + 0.5f);
        this.o = obtainStyledAttributes.getColor(0, -14331913);
        obtainStyledAttributes.recycle();
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        if (viewConfiguration != null) {
            this.f = viewConfiguration.getScaledTouchSlop();
            this.h = viewConfiguration.getScaledMinimumFlingVelocity();
        }
    }

    private void ehS_(MotionEvent motionEvent) {
        MotionEvent obtain = MotionEvent.obtain(motionEvent);
        obtain.setAction(3);
        super.onTouchEvent(obtain);
        obtain.recycle();
    }

    private boolean ehU_(MotionEvent motionEvent) {
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        Object b2 = slc.b(this, "mTouchSlop", (Class<?>) Switch.class);
        if (b2 instanceof Integer) {
            this.f = ((Integer) b2).intValue();
        }
        if (Math.abs(x - this.j) <= this.f && Math.abs(y - this.i) <= this.f) {
            return false;
        }
        this.c = 2;
        getParent().requestDisallowInterceptTouchEvent(true);
        this.j = x;
        this.i = y;
        return true;
    }

    private boolean i() {
        return getLayoutDirection() == 1;
    }

    private void ehT_(MotionEvent motionEvent) {
        this.c = 0;
        boolean z = true;
        boolean z2 = motionEvent.getAction() == 1 && isEnabled();
        boolean isChecked = isChecked();
        if (z2) {
            this.l.computeCurrentVelocity(1000);
            float xVelocity = this.l.getXVelocity();
            Object b2 = slc.b(this, "mMinFlingVelocity", (Class<?>) Switch.class);
            if (b2 instanceof Integer) {
                this.h = ((Integer) b2).intValue();
            }
            if (Math.abs(xVelocity) > this.h) {
                if (!h() ? xVelocity <= 0.0f : xVelocity >= 0.0f) {
                    z = false;
                }
            } else {
                z = f();
            }
        } else {
            z = isChecked;
        }
        if (z != isChecked) {
            playSoundEffect(0);
        }
        setChecked(z);
        ehS_(motionEvent);
    }

    private void ehQ_(Canvas canvas) {
        Rect rect = this.k;
        int i = this.q;
        int i2 = this.s;
        int i3 = this.e;
        int thumbOffset = i + getThumbOffset() + this.f10763a;
        b();
        Drawable drawable = this.t;
        if (drawable != null) {
            drawable.getPadding(rect);
            int i4 = thumbOffset - rect.left;
            int i5 = thumbOffset + this.p + rect.right;
            Drawable.Callback callback = this.t.getCallback();
            this.t.setCallback(null);
            this.t.setBounds(i4, i2, i5, i3);
            this.t.setCallback(callback);
            Drawable background = getBackground();
            if (background != null) {
                background.setHotspotBounds(i4, i2, i5, i3);
            }
        }
    }

    private void b() {
        Insets insets;
        Rect rect = this.k;
        int i = this.q;
        int i2 = this.w;
        int i3 = this.s;
        int i4 = this.e;
        Drawable drawable = this.t;
        if (drawable != null) {
            Object c = slc.c(drawable, "getOpticalInsets", null, null, DrawableContainer.class);
            if (c instanceof Insets) {
                insets = (Insets) c;
            } else {
                insets = Insets.NONE;
            }
        } else {
            insets = Insets.NONE;
        }
        Drawable drawable2 = this.d;
        if (drawable2 != null) {
            drawable2.getPadding(rect);
            if (insets != Insets.NONE) {
                int i5 = insets.left;
                int i6 = rect.left;
                if (i5 > i6) {
                    i += i5 - i6;
                }
                int i7 = insets.top;
                int i8 = rect.top;
                if (i7 > i8) {
                    i3 += i7 - i8;
                }
                int i9 = insets.right;
                int i10 = rect.right;
                if (i9 > i10) {
                    i2 -= i9 - i10;
                }
                int i11 = insets.bottom;
                int i12 = rect.bottom;
                if (i11 > i12) {
                    i4 -= i11 - i12;
                }
            }
            this.d.setBounds(i, i3, i2, i4);
        }
    }

    private void b(int i, int i2) {
        if (i()) {
            int paddingLeft = getPaddingLeft() + i;
            this.q = paddingLeft;
            this.w = ((paddingLeft + this.r) - i) - i2;
        } else {
            int width = (getWidth() - getPaddingRight()) - i2;
            this.w = width;
            this.q = (width - this.r) + i + i2;
        }
        int gravity = getGravity() & 112;
        if (gravity == 16) {
            int paddingTop = ((getPaddingTop() + getHeight()) - getPaddingBottom()) / 2;
            int i3 = this.m;
            int i4 = paddingTop - (i3 / 2);
            this.s = i4;
            this.e = i4 + i3;
            return;
        }
        if (gravity == 48) {
            int paddingTop2 = getPaddingTop();
            this.s = paddingTop2;
            this.e = paddingTop2 + this.m;
        } else if (gravity != 80) {
            this.s = 0;
            this.e = 0;
        } else {
            int height = getHeight() - getPaddingBottom();
            this.e = height;
            this.s = height - this.m;
        }
    }

    private void c(boolean z) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, b, z ? 1.0f : 0.0f);
        this.n = ofFloat;
        ofFloat.setDuration(200L);
        this.n.setInterpolator(new HwCubicBezierInterpolator(0.1f, 1.0f, 0.9f, 1.0f));
        this.n.setAutoCancel(true);
        this.n.start();
    }

    private boolean e(float f, float f2) {
        if (this.t == null) {
            return false;
        }
        int thumbOffset = getThumbOffset();
        this.t.getPadding(this.k);
        Object b2 = slc.b(this, "mTouchSlop", (Class<?>) Switch.class);
        if (b2 instanceof Integer) {
            this.f = ((Integer) b2).intValue();
        }
        int i = this.s;
        int i2 = this.f;
        int i3 = (this.q + thumbOffset) - i2;
        int i4 = this.p;
        Rect rect = this.k;
        return f > ((float) i3) && f < ((float) ((((i4 + i3) + rect.left) + rect.right) + i2)) && f2 > ((float) (i - i2)) && f2 < ((float) (this.e + i2));
    }

    private void ehR_(MotionEvent motionEvent) {
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        if (isEnabled() && e(x, y)) {
            this.c = 1;
            this.j = x;
            this.i = y;
        }
    }
}
