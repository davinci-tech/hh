package com.huawei.ui.commonui.linechart.common;

import android.graphics.DashPathEffect;
import com.github.mikephil.charting.interfaces.datasets.ILineScatterCandleRadarDataSet;
import com.github.mikephil.charting.utils.Utils;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseEntry;
import java.util.List;

/* loaded from: classes6.dex */
public abstract class HwHealthLineScatterCandleRadarDataSet<T extends HwHealthBaseEntry> extends HwHealthBaseBarLineDataSet<T> implements ILineScatterCandleRadarDataSet<T> {
    private DashPathEffect mHighlightDashPathEffect;
    private float mHighlightLineWidth;
    private boolean mIsDrawHorizontalHighlightIndicator;
    private boolean mIsDrawVerticalHighlightIndicator;

    public HwHealthLineScatterCandleRadarDataSet(List<T> list, String str) {
        super(list, str);
        this.mHighlightLineWidth = 0.5f;
        this.mHighlightDashPathEffect = null;
        this.mIsDrawVerticalHighlightIndicator = true;
        this.mIsDrawHorizontalHighlightIndicator = true;
        this.mHighlightLineWidth = Utils.convertDpToPixel(0.5f);
    }

    public void setDrawHorizontalHighlightIndicator(boolean z) {
        this.mIsDrawHorizontalHighlightIndicator = z;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.ILineScatterCandleRadarDataSet
    public boolean isVerticalHighlightIndicatorEnabled() {
        return this.mIsDrawVerticalHighlightIndicator;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.ILineScatterCandleRadarDataSet
    public boolean isHorizontalHighlightIndicatorEnabled() {
        return this.mIsDrawHorizontalHighlightIndicator;
    }

    public void setHighlightLineWidth(float f) {
        this.mHighlightLineWidth = Utils.convertDpToPixel(f);
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.ILineScatterCandleRadarDataSet
    public float getHighlightLineWidth() {
        return this.mHighlightLineWidth;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.ILineScatterCandleRadarDataSet
    public DashPathEffect getDashPathEffectHighlight() {
        return this.mHighlightDashPathEffect;
    }
}
