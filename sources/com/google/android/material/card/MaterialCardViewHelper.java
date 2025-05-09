package com.google.android.material.card;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;

/* loaded from: classes8.dex */
class MaterialCardViewHelper {
    private static final int DEFAULT_STROKE_VALUE = -1;
    private final MaterialCardView materialCardView;
    private int strokeColor;
    private int strokeWidth;

    public MaterialCardViewHelper(MaterialCardView materialCardView) {
        this.materialCardView = materialCardView;
    }

    public void loadFromAttributes(TypedArray typedArray) {
        this.strokeColor = typedArray.getColor(0, -1);
        this.strokeWidth = typedArray.getDimensionPixelSize(1, 0);
        updateForeground();
        adjustContentPadding();
    }

    void setStrokeColor(int i) {
        this.strokeColor = i;
        updateForeground();
    }

    int getStrokeColor() {
        return this.strokeColor;
    }

    void setStrokeWidth(int i) {
        this.strokeWidth = i;
        updateForeground();
        adjustContentPadding();
    }

    int getStrokeWidth() {
        return this.strokeWidth;
    }

    void updateForeground() {
        this.materialCardView.setForeground(createForegroundDrawable());
    }

    private Drawable createForegroundDrawable() {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(this.materialCardView.getRadius());
        int i = this.strokeColor;
        if (i != -1) {
            gradientDrawable.setStroke(this.strokeWidth, i);
        }
        return gradientDrawable;
    }

    private void adjustContentPadding() {
        this.materialCardView.setContentPadding(this.materialCardView.getContentPaddingLeft() + this.strokeWidth, this.materialCardView.getContentPaddingTop() + this.strokeWidth, this.materialCardView.getContentPaddingRight() + this.strokeWidth, this.materialCardView.getContentPaddingBottom() + this.strokeWidth);
    }
}
