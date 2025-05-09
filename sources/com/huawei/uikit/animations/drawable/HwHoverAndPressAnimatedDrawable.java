package com.huawei.uikit.animations.drawable;

import android.animation.ObjectAnimator;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.animation.Interpolator;
import com.huawei.health.R;
import defpackage.skd;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes9.dex */
public class HwHoverAndPressAnimatedDrawable extends GradientDrawable {
    private static final int DURATION_HOVER = 250;
    private static final int DURATION_PRESS = 100;
    private static final int INVALID_SIZE = -1;
    private ObjectAnimator mAlphaEnterAnim;
    private ObjectAnimator mAlphaExitAnim;
    private Interpolator mHoveredInterpolator;
    private boolean mIsHovered;
    private boolean mIsPressed;
    private Interpolator mPressedInterpolator;
    private d mState;

    static class d extends Drawable.ConstantState {

        /* renamed from: a, reason: collision with root package name */
        boolean f10585a;
        int b;
        int c;
        ColorStateList d;
        int e;
        int f;
        int g;
        float h;
        float[] i;
        int j;

        private static int dYI_(Resources resources, int i) {
            if (resources != null) {
                i = resources.getDisplayMetrics().densityDpi;
            }
            if (i == 0) {
                return 160;
            }
            return i;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return 0;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            return new HwHoverAndPressAnimatedDrawable(this);
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources) {
            return new HwHoverAndPressAnimatedDrawable(dYI_(resources, this.c) != this.c ? new d(this, resources) : this);
        }

        private d() {
            this.f10585a = false;
            this.c = 160;
            this.f = 0;
            this.h = 0.0f;
            this.i = null;
            this.j = -1;
            this.g = -1;
        }

        private d(d dVar, Resources resources) {
            this.f10585a = false;
            this.c = 160;
            this.f = 0;
            this.h = 0.0f;
            this.i = null;
            this.j = -1;
            this.g = -1;
            this.f = dVar.f;
            this.h = dVar.h;
            float[] fArr = dVar.i;
            if (fArr != null) {
                this.i = (float[]) fArr.clone();
            }
            this.d = dVar.d;
            this.j = dVar.j;
            this.g = dVar.g;
            this.b = dVar.b;
            this.e = dVar.e;
            this.f10585a = dVar.f10585a;
            this.c = dYI_(resources, dVar.c);
        }
    }

    private void clearEffect() {
        ObjectAnimator objectAnimator = this.mAlphaEnterAnim;
        if (objectAnimator != null && objectAnimator.isRunning()) {
            this.mAlphaEnterAnim.end();
        }
        ObjectAnimator objectAnimator2 = this.mAlphaExitAnim;
        if (objectAnimator2 != null && objectAnimator2.isRunning()) {
            this.mAlphaExitAnim.end();
        }
        this.mAlphaExitAnim = null;
        this.mAlphaEnterAnim = null;
        this.mIsHovered = false;
        this.mIsPressed = false;
        setAlpha(0);
        invalidateSelf();
    }

    private void preInflate(Resources resources, AttributeSet attributeSet, Resources.Theme theme) {
        TypedArray obtainAttributes = theme == null ? resources.obtainAttributes(attributeSet, new int[]{R.attr._2131100359_res_0x7f0602c7, R.attr._2131100432_res_0x7f060310}) : theme.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131100359_res_0x7f0602c7, R.attr._2131100432_res_0x7f060310}, 0, 0);
        int color = obtainAttributes.getColor(0, 0);
        int color2 = obtainAttributes.getColor(1, 0);
        if (color2 != 0) {
            this.mState.f10585a = true;
        }
        int alpha = Color.alpha(color);
        int alpha2 = Color.alpha(color2);
        this.mState.d = ColorStateList.valueOf(this.mState.f10585a ? Color.rgb(Color.red(color2), Color.green(color2), Color.blue(color2)) : Color.rgb(Color.red(color), Color.green(color), Color.blue(color)));
        d dVar = this.mState;
        dVar.b = alpha;
        dVar.e = alpha2;
        obtainAttributes.recycle();
    }

    private void setHoveredAnimation(boolean z) {
        if (this.mIsHovered != z) {
            this.mIsHovered = z;
            if (!z) {
                startExitAnim(0, 250, this.mHoveredInterpolator);
            } else {
                if (this.mIsPressed) {
                    return;
                }
                startEnterAnim(this.mState.b, 250, this.mHoveredInterpolator);
            }
        }
    }

    private void setPressedAnimation(boolean z) {
        if (this.mIsPressed != z) {
            this.mIsPressed = z;
            if (z) {
                startEnterAnim(this.mState.e, 100, this.mPressedInterpolator);
            } else {
                startExitAnim(this.mIsHovered ? this.mState.b : 0, 100, this.mPressedInterpolator);
            }
        }
    }

    private void startEnterAnim(int i, int i2, Interpolator interpolator) {
        ObjectAnimator ofInt = ObjectAnimator.ofInt(this, "alpha", i);
        this.mAlphaEnterAnim = ofInt;
        ofInt.setAutoCancel(true);
        this.mAlphaEnterAnim.setDuration(i2);
        this.mAlphaEnterAnim.setInterpolator(interpolator);
        this.mAlphaEnterAnim.start();
    }

    private void startExitAnim(int i, int i2, Interpolator interpolator) {
        ObjectAnimator ofInt = ObjectAnimator.ofInt(this, "alpha", i);
        this.mAlphaExitAnim = ofInt;
        ofInt.setAutoCancel(true);
        this.mAlphaExitAnim.setDuration(i2);
        this.mAlphaExitAnim.setInterpolator(interpolator);
        this.mAlphaExitAnim.start();
    }

    private void updateLocalStates() {
        this.mState.h = getCornerRadius();
        this.mState.f = getShape();
        this.mState.j = getIntrinsicWidth();
        this.mState.g = getIntrinsicHeight();
        try {
            this.mState.i = getCornerRadii();
        } catch (NullPointerException unused) {
            this.mState.i = null;
        }
    }

    @Override // android.graphics.drawable.GradientDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (getColor() != null && this.mState.d != null && getColor().getDefaultColor() != this.mState.d.getDefaultColor()) {
            setColor(this.mState.d);
        }
        super.draw(canvas);
    }

    @Override // android.graphics.drawable.GradientDrawable, android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        return this.mState;
    }

    @Override // android.graphics.drawable.GradientDrawable, android.graphics.drawable.Drawable
    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws IOException, XmlPullParserException {
        preInflate(resources, attributeSet, theme);
        super.inflate(resources, xmlPullParser, attributeSet, theme);
        setColor(this.mState.d);
        updateLocalStates();
    }

    @Override // android.graphics.drawable.GradientDrawable, android.graphics.drawable.Drawable
    public boolean isStateful() {
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.graphics.drawable.GradientDrawable, android.graphics.drawable.Drawable
    public Drawable mutate() {
        if (super.mutate() == this) {
            d dVar = new d(this.mState, null);
            this.mState = dVar;
            dVar.d = getColor();
            updateLocalStates();
        }
        return this;
    }

    @Override // android.graphics.drawable.GradientDrawable, android.graphics.drawable.Drawable
    protected boolean onStateChange(int[] iArr) {
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        for (int i : iArr) {
            if (i != 16842910) {
                if (i == 16842919) {
                    z3 = true;
                } else if (i == 16843623) {
                    z2 = true;
                } else if (i != 16842908) {
                }
            }
            z = true;
        }
        if (!z) {
            clearEffect();
            return true;
        }
        setHoveredAnimation(z2);
        if (this.mState.f10585a) {
            setPressedAnimation(z3);
        }
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z2) {
        boolean visible = super.setVisible(z, z2);
        if (!z) {
            clearEffect();
        }
        return visible;
    }

    public HwHoverAndPressAnimatedDrawable() {
        this(new d());
    }

    private HwHoverAndPressAnimatedDrawable(d dVar) {
        this.mHoveredInterpolator = skd.dYP_();
        this.mPressedInterpolator = skd.dYQ_();
        this.mIsHovered = false;
        this.mIsPressed = false;
        this.mState = dVar;
        setShape(dVar.f);
        setSize(dVar.j, dVar.g);
        setBounds(new Rect(0, 0, dVar.j, dVar.g));
        setColor(dVar.d);
        float[] fArr = dVar.i;
        if (this.mState.i == null) {
            setCornerRadius(dVar.h);
        } else {
            setCornerRadii(fArr);
        }
        setAlpha(0);
    }
}
