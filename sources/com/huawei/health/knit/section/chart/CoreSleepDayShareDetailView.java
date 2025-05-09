package com.huawei.health.knit.section.chart;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import defpackage.een;

/* loaded from: classes8.dex */
public class CoreSleepDayShareDetailView extends BaseSleepDayDetailView {
    public CoreSleepDayShareDetailView(Context context) {
        this(context, null);
        setBaseContext(context);
    }

    public CoreSleepDayShareDetailView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setBaseContext(context);
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        setCursorHeight(getTransparentHeight());
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        setViewWidth(getDefaultSize(getSuggestedMinimumWidth(), i));
        setViewHeight(getDefaultSize(getSuggestedMinimumHeight(), i2));
        setDiagramWidth(getViewWidth() - een.e(72.0f));
        if (getIsScienceSleep()) {
            setDiagramHeight(een.e(138.0f));
        } else {
            setDiagramHeight(een.e(176.0f));
        }
        setTransparentHeight(getViewHeight());
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        getTimeLabel();
        drawSleepRect(canvas);
        drawMaxSleepVerticalLine(canvas);
        drawCursorText(canvas);
    }
}
