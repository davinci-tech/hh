package com.google.android.material.button;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.ViewCompat;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.ripple.RippleUtils;

/* loaded from: classes8.dex */
class MaterialButtonHelper {
    private static final float CORNER_RADIUS_ADJUSTMENT = 1.0E-5f;
    private static final int DEFAULT_BACKGROUND_COLOR = -1;
    private static final boolean IS_LOLLIPOP = true;
    private GradientDrawable backgroundDrawableLollipop;
    private ColorStateList backgroundTint;
    private PorterDuff.Mode backgroundTintMode;
    private GradientDrawable colorableBackgroundDrawableCompat;
    private int cornerRadius;
    private int insetBottom;
    private int insetLeft;
    private int insetRight;
    private int insetTop;
    private GradientDrawable maskDrawableLollipop;
    private final MaterialButton materialButton;
    private ColorStateList rippleColor;
    private GradientDrawable rippleDrawableCompat;
    private ColorStateList strokeColor;
    private GradientDrawable strokeDrawableLollipop;
    private int strokeWidth;
    private Drawable tintableBackgroundDrawableCompat;
    private Drawable tintableRippleDrawableCompat;
    private final Paint buttonStrokePaint = new Paint(1);
    private final Rect bounds = new Rect();
    private final RectF rectF = new RectF();
    private boolean backgroundOverwritten = false;

    public MaterialButtonHelper(MaterialButton materialButton) {
        this.materialButton = materialButton;
    }

    public void loadFromAttributes(TypedArray typedArray) {
        this.insetLeft = typedArray.getDimensionPixelOffset(0, 0);
        this.insetRight = typedArray.getDimensionPixelOffset(1, 0);
        this.insetTop = typedArray.getDimensionPixelOffset(2, 0);
        this.insetBottom = typedArray.getDimensionPixelOffset(3, 0);
        this.cornerRadius = typedArray.getDimensionPixelSize(6, 0);
        this.strokeWidth = typedArray.getDimensionPixelSize(15, 0);
        this.backgroundTintMode = ViewUtils.parseTintMode(typedArray.getInt(5, -1), PorterDuff.Mode.SRC_IN);
        this.backgroundTint = MaterialResources.getColorStateList(this.materialButton.getContext(), typedArray, 4);
        this.strokeColor = MaterialResources.getColorStateList(this.materialButton.getContext(), typedArray, 14);
        this.rippleColor = MaterialResources.getColorStateList(this.materialButton.getContext(), typedArray, 13);
        this.buttonStrokePaint.setStyle(Paint.Style.STROKE);
        this.buttonStrokePaint.setStrokeWidth(this.strokeWidth);
        Paint paint = this.buttonStrokePaint;
        ColorStateList colorStateList = this.strokeColor;
        paint.setColor(colorStateList != null ? colorStateList.getColorForState(this.materialButton.getDrawableState(), 0) : 0);
        int paddingStart = ViewCompat.getPaddingStart(this.materialButton);
        int paddingTop = this.materialButton.getPaddingTop();
        int paddingEnd = ViewCompat.getPaddingEnd(this.materialButton);
        int paddingBottom = this.materialButton.getPaddingBottom();
        this.materialButton.setInternalBackground(IS_LOLLIPOP ? createBackgroundLollipop() : createBackgroundCompat());
        ViewCompat.setPaddingRelative(this.materialButton, paddingStart + this.insetLeft, paddingTop + this.insetTop, paddingEnd + this.insetRight, paddingBottom + this.insetBottom);
    }

    void setBackgroundOverwritten() {
        this.backgroundOverwritten = true;
        this.materialButton.setSupportBackgroundTintList(this.backgroundTint);
        this.materialButton.setSupportBackgroundTintMode(this.backgroundTintMode);
    }

    boolean isBackgroundOverwritten() {
        return this.backgroundOverwritten;
    }

    void drawStroke(Canvas canvas) {
        if (canvas == null || this.strokeColor == null || this.strokeWidth <= 0) {
            return;
        }
        this.bounds.set(this.materialButton.getBackground().getBounds());
        this.rectF.set(this.bounds.left + (this.strokeWidth / 2.0f) + this.insetLeft, this.bounds.top + (this.strokeWidth / 2.0f) + this.insetTop, (this.bounds.right - (this.strokeWidth / 2.0f)) - this.insetRight, (this.bounds.bottom - (this.strokeWidth / 2.0f)) - this.insetBottom);
        float f = this.cornerRadius - (this.strokeWidth / 2.0f);
        canvas.drawRoundRect(this.rectF, f, f, this.buttonStrokePaint);
    }

    private Drawable createBackgroundCompat() {
        GradientDrawable gradientDrawable = new GradientDrawable();
        this.colorableBackgroundDrawableCompat = gradientDrawable;
        gradientDrawable.setCornerRadius(this.cornerRadius + CORNER_RADIUS_ADJUSTMENT);
        this.colorableBackgroundDrawableCompat.setColor(-1);
        Drawable wrap = DrawableCompat.wrap(this.colorableBackgroundDrawableCompat);
        this.tintableBackgroundDrawableCompat = wrap;
        DrawableCompat.setTintList(wrap, this.backgroundTint);
        PorterDuff.Mode mode = this.backgroundTintMode;
        if (mode != null) {
            DrawableCompat.setTintMode(this.tintableBackgroundDrawableCompat, mode);
        }
        GradientDrawable gradientDrawable2 = new GradientDrawable();
        this.rippleDrawableCompat = gradientDrawable2;
        gradientDrawable2.setCornerRadius(this.cornerRadius + CORNER_RADIUS_ADJUSTMENT);
        this.rippleDrawableCompat.setColor(-1);
        Drawable wrap2 = DrawableCompat.wrap(this.rippleDrawableCompat);
        this.tintableRippleDrawableCompat = wrap2;
        DrawableCompat.setTintList(wrap2, this.rippleColor);
        return wrapDrawableWithInset(new LayerDrawable(new Drawable[]{this.tintableBackgroundDrawableCompat, this.tintableRippleDrawableCompat}));
    }

    private InsetDrawable wrapDrawableWithInset(Drawable drawable) {
        return new InsetDrawable(drawable, this.insetLeft, this.insetTop, this.insetRight, this.insetBottom);
    }

    void setSupportBackgroundTintList(ColorStateList colorStateList) {
        if (this.backgroundTint != colorStateList) {
            this.backgroundTint = colorStateList;
            if (IS_LOLLIPOP) {
                updateTintAndTintModeLollipop();
                return;
            }
            Drawable drawable = this.tintableBackgroundDrawableCompat;
            if (drawable != null) {
                DrawableCompat.setTintList(drawable, colorStateList);
            }
        }
    }

    ColorStateList getSupportBackgroundTintList() {
        return this.backgroundTint;
    }

    void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        if (this.backgroundTintMode != mode) {
            this.backgroundTintMode = mode;
            if (IS_LOLLIPOP) {
                updateTintAndTintModeLollipop();
                return;
            }
            Drawable drawable = this.tintableBackgroundDrawableCompat;
            if (drawable == null || mode == null) {
                return;
            }
            DrawableCompat.setTintMode(drawable, mode);
        }
    }

    PorterDuff.Mode getSupportBackgroundTintMode() {
        return this.backgroundTintMode;
    }

    private void updateTintAndTintModeLollipop() {
        GradientDrawable gradientDrawable = this.backgroundDrawableLollipop;
        if (gradientDrawable != null) {
            DrawableCompat.setTintList(gradientDrawable, this.backgroundTint);
            PorterDuff.Mode mode = this.backgroundTintMode;
            if (mode != null) {
                DrawableCompat.setTintMode(this.backgroundDrawableLollipop, mode);
            }
        }
    }

    private Drawable createBackgroundLollipop() {
        GradientDrawable gradientDrawable = new GradientDrawable();
        this.backgroundDrawableLollipop = gradientDrawable;
        gradientDrawable.setCornerRadius(this.cornerRadius + CORNER_RADIUS_ADJUSTMENT);
        this.backgroundDrawableLollipop.setColor(-1);
        updateTintAndTintModeLollipop();
        GradientDrawable gradientDrawable2 = new GradientDrawable();
        this.strokeDrawableLollipop = gradientDrawable2;
        gradientDrawable2.setCornerRadius(this.cornerRadius + CORNER_RADIUS_ADJUSTMENT);
        this.strokeDrawableLollipop.setColor(0);
        this.strokeDrawableLollipop.setStroke(this.strokeWidth, this.strokeColor);
        InsetDrawable wrapDrawableWithInset = wrapDrawableWithInset(new LayerDrawable(new Drawable[]{this.backgroundDrawableLollipop, this.strokeDrawableLollipop}));
        GradientDrawable gradientDrawable3 = new GradientDrawable();
        this.maskDrawableLollipop = gradientDrawable3;
        gradientDrawable3.setCornerRadius(this.cornerRadius + CORNER_RADIUS_ADJUSTMENT);
        this.maskDrawableLollipop.setColor(-1);
        return new MaterialButtonBackgroundDrawable(RippleUtils.convertToRippleDrawableColor(this.rippleColor), wrapDrawableWithInset, this.maskDrawableLollipop);
    }

    void updateMaskBounds(int i, int i2) {
        GradientDrawable gradientDrawable = this.maskDrawableLollipop;
        if (gradientDrawable != null) {
            gradientDrawable.setBounds(this.insetLeft, this.insetTop, i2 - this.insetRight, i - this.insetBottom);
        }
    }

    void setBackgroundColor(int i) {
        GradientDrawable gradientDrawable;
        GradientDrawable gradientDrawable2;
        boolean z = IS_LOLLIPOP;
        if (z && (gradientDrawable2 = this.backgroundDrawableLollipop) != null) {
            gradientDrawable2.setColor(i);
        } else {
            if (z || (gradientDrawable = this.colorableBackgroundDrawableCompat) == null) {
                return;
            }
            gradientDrawable.setColor(i);
        }
    }

    void setRippleColor(ColorStateList colorStateList) {
        Drawable drawable;
        if (this.rippleColor != colorStateList) {
            this.rippleColor = colorStateList;
            boolean z = IS_LOLLIPOP;
            if (z && (this.materialButton.getBackground() instanceof RippleDrawable)) {
                ((RippleDrawable) this.materialButton.getBackground()).setColor(colorStateList);
            } else {
                if (z || (drawable = this.tintableRippleDrawableCompat) == null) {
                    return;
                }
                DrawableCompat.setTintList(drawable, colorStateList);
            }
        }
    }

    ColorStateList getRippleColor() {
        return this.rippleColor;
    }

    void setStrokeColor(ColorStateList colorStateList) {
        if (this.strokeColor != colorStateList) {
            this.strokeColor = colorStateList;
            this.buttonStrokePaint.setColor(colorStateList != null ? colorStateList.getColorForState(this.materialButton.getDrawableState(), 0) : 0);
            updateStroke();
        }
    }

    ColorStateList getStrokeColor() {
        return this.strokeColor;
    }

    void setStrokeWidth(int i) {
        if (this.strokeWidth != i) {
            this.strokeWidth = i;
            this.buttonStrokePaint.setStrokeWidth(i);
            updateStroke();
        }
    }

    int getStrokeWidth() {
        return this.strokeWidth;
    }

    private void updateStroke() {
        boolean z = IS_LOLLIPOP;
        if (z && this.strokeDrawableLollipop != null) {
            this.materialButton.setInternalBackground(createBackgroundLollipop());
        } else {
            if (z) {
                return;
            }
            this.materialButton.invalidate();
        }
    }

    void setCornerRadius(int i) {
        GradientDrawable gradientDrawable;
        GradientDrawable gradientDrawable2;
        if (this.cornerRadius != i) {
            this.cornerRadius = i;
            boolean z = IS_LOLLIPOP;
            if (z && (gradientDrawable2 = this.backgroundDrawableLollipop) != null && this.strokeDrawableLollipop != null && this.maskDrawableLollipop != null) {
                float f = i + CORNER_RADIUS_ADJUSTMENT;
                gradientDrawable2.setCornerRadius(f);
                this.strokeDrawableLollipop.setCornerRadius(f);
                this.maskDrawableLollipop.setCornerRadius(f);
                return;
            }
            if (z || (gradientDrawable = this.colorableBackgroundDrawableCompat) == null || this.rippleDrawableCompat == null) {
                return;
            }
            float f2 = i + CORNER_RADIUS_ADJUSTMENT;
            gradientDrawable.setCornerRadius(f2);
            this.rippleDrawableCompat.setCornerRadius(f2);
            this.materialButton.invalidate();
        }
    }

    int getCornerRadius() {
        return this.cornerRadius;
    }

    private GradientDrawable unwrapStrokeDrawable() {
        if (!IS_LOLLIPOP || this.materialButton.getBackground() == null) {
            return null;
        }
        return (GradientDrawable) ((LayerDrawable) ((InsetDrawable) ((RippleDrawable) this.materialButton.getBackground()).getDrawable(0)).getDrawable()).getDrawable(1);
    }

    private GradientDrawable unwrapBackgroundDrawable() {
        if (!IS_LOLLIPOP || this.materialButton.getBackground() == null) {
            return null;
        }
        return (GradientDrawable) ((LayerDrawable) ((InsetDrawable) ((RippleDrawable) this.materialButton.getBackground()).getDrawable(0)).getDrawable()).getDrawable(0);
    }
}
