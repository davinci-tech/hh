package com.huawei.ui.commonui.linechart.common;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import com.github.mikephil.charting.interfaces.datasets.ILineRadarDataSet;
import com.github.mikephil.charting.utils.Utils;
import com.huawei.hwcommonmodel.fitnessdatatype.FitnessSleepType;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseEntry;
import java.util.List;

/* loaded from: classes6.dex */
public abstract class HwHealthLineRadarDataSet<T extends HwHealthBaseEntry> extends HwHealthLineScatterCandleRadarDataSet<T> implements ILineRadarDataSet<T> {
    private int mFillAlpha;
    private int mFillColor;
    protected Drawable mFillDrawable;
    private boolean mIsDrawFilled;
    private float mLineWidth;

    public HwHealthLineRadarDataSet(List<T> list, String str) {
        super(list, str);
        this.mFillColor = Color.rgb(140, FitnessSleepType.HW_FITNESS_WAKE, 255);
        this.mFillAlpha = 85;
        this.mLineWidth = 2.5f;
        this.mIsDrawFilled = false;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.ILineRadarDataSet
    public int getFillColor() {
        return this.mFillColor;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.ILineRadarDataSet
    public Drawable getFillDrawable() {
        return this.mFillDrawable;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.ILineRadarDataSet
    public int getFillAlpha() {
        return this.mFillAlpha;
    }

    public void setFillAlpha(int i) {
        this.mFillAlpha = i;
    }

    public void setLineWidth(float f) {
        float f2 = f >= 0.0f ? f : 0.0f;
        if (f > 10.0f) {
            f2 = 10.0f;
        }
        this.mLineWidth = Utils.convertDpToPixel(f2);
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.ILineRadarDataSet
    public float getLineWidth() {
        return this.mLineWidth;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.ILineRadarDataSet
    public void setDrawFilled(boolean z) {
        this.mIsDrawFilled = z;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.ILineRadarDataSet
    public boolean isDrawFilledEnabled() {
        return this.mIsDrawFilled;
    }
}
