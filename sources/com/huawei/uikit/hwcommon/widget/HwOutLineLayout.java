package com.huawei.uikit.hwcommon.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;

/* loaded from: classes9.dex */
public class HwOutLineLayout extends FrameLayout {

    public interface OutLineCallback {
        void onParentDraw(Canvas canvas);
    }

    public HwOutLineLayout(Context context) {
        this(context, null);
    }

    private void e() {
        setClipChildren(false);
        setClipToPadding(false);
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        if (getChildCount() > 0) {
            return;
        }
        super.addView(view, i, layoutParams);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.view.ViewGroup
    protected boolean drawChild(Canvas canvas, View view, long j) {
        if (canvas == null || view == 0) {
            return false;
        }
        boolean drawChild = super.drawChild(canvas, view, j);
        if (view instanceof OutLineCallback) {
            canvas.save();
            canvas.translate(view.getLeft(), view.getTop());
            ((OutLineCallback) view).onParentDraw(canvas);
            canvas.restore();
        }
        return drawChild;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        a(getParent());
    }

    public HwOutLineLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public HwOutLineLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet);
        e();
    }

    private void a(ViewParent viewParent) {
        if (viewParent != null && (viewParent instanceof ViewGroup)) {
            ViewGroup viewGroup = (ViewGroup) viewParent;
            viewGroup.setClipChildren(false);
            viewGroup.setClipToPadding(false);
        }
    }
}
