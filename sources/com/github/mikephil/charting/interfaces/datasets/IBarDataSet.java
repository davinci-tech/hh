package com.github.mikephil.charting.interfaces.datasets;

import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.Fill;
import java.util.List;

/* loaded from: classes2.dex */
public interface IBarDataSet extends IBarLineScatterCandleBubbleDataSet<BarEntry> {
    int getBarBorderColor();

    float getBarBorderWidth();

    int getBarShadowColor();

    Fill getFill(int i);

    List<Fill> getFills();

    int getHighLightAlpha();

    String[] getStackLabels();

    int getStackSize();

    boolean isStacked();
}
