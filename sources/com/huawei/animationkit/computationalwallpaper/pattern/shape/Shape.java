package com.huawei.animationkit.computationalwallpaper.pattern.shape;

import android.graphics.Canvas;
import android.graphics.Rect;

/* loaded from: classes8.dex */
public interface Shape {
    void draw(Canvas canvas);

    void setColor(int i);

    void setLocation(Rect rect);

    void setRotate(float f);
}
