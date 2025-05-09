package com.huawei.ui.openservice.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.ImageView;

/* loaded from: classes7.dex */
public class GridImage extends ImageView {
    private boolean isPressed;

    public GridImage(Context context) {
        super(context);
    }

    public GridImage(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.isPressed) {
            canvas.drawColor(Color.parseColor("#14C0C0C0"));
        }
    }

    @Override // android.view.View
    protected void dispatchSetPressed(boolean z) {
        super.dispatchSetPressed(z);
        this.isPressed = z;
        invalidate();
    }
}
