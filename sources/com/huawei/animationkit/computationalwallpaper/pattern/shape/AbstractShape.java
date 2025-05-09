package com.huawei.animationkit.computationalwallpaper.pattern.shape;

import android.graphics.Rect;

/* loaded from: classes8.dex */
public abstract class AbstractShape implements Shape {
    private int mColor;
    private float mDegree;
    private final Rect mRect = new Rect();

    @Override // com.huawei.animationkit.computationalwallpaper.pattern.shape.Shape
    public void setLocation(Rect rect) {
        this.mRect.set(rect.left, rect.top, rect.right, rect.bottom);
    }

    @Override // com.huawei.animationkit.computationalwallpaper.pattern.shape.Shape
    public void setRotate(float f) {
        this.mDegree = f;
    }

    @Override // com.huawei.animationkit.computationalwallpaper.pattern.shape.Shape
    public void setColor(int i) {
        this.mColor = i;
    }

    protected Rect getLocation() {
        return this.mRect;
    }

    protected int getCenterX() {
        return this.mRect.centerX();
    }

    protected int getCenterY() {
        return this.mRect.centerY();
    }
}
