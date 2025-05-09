package com.github.mikephil.charting.data;

import android.graphics.Color;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.Fill;
import com.huawei.hms.kit.awareness.barrier.internal.e.a;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes7.dex */
public class BarDataSet extends BarLineScatterCandleBubbleDataSet<BarEntry> implements IBarDataSet {
    private int mBarBorderColor;
    private float mBarBorderWidth;
    private int mBarShadowColor;
    private int mEntryCountStacks;
    protected List<Fill> mFills;
    private int mHighLightAlpha;
    private String[] mStackLabels;
    private int mStackSize;

    public BarDataSet(List<BarEntry> list, String str) {
        super(list, str);
        this.mStackSize = 1;
        this.mBarShadowColor = Color.rgb(a.N, a.N, a.N);
        this.mBarBorderWidth = 0.0f;
        this.mBarBorderColor = -16777216;
        this.mHighLightAlpha = 120;
        this.mEntryCountStacks = 0;
        this.mStackLabels = new String[0];
        this.mFills = null;
        this.mHighLightColor = Color.rgb(0, 0, 0);
        calcStackSize(list);
        calcEntryCountIncludingStacks(list);
    }

    @Override // com.github.mikephil.charting.data.DataSet
    public DataSet<BarEntry> copy() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < this.mEntries.size(); i++) {
            arrayList.add(((BarEntry) this.mEntries.get(i)).copy());
        }
        BarDataSet barDataSet = new BarDataSet(arrayList, getLabel());
        copy(barDataSet);
        return barDataSet;
    }

    protected void copy(BarDataSet barDataSet) {
        super.copy((BarLineScatterCandleBubbleDataSet) barDataSet);
        barDataSet.mStackSize = this.mStackSize;
        barDataSet.mBarShadowColor = this.mBarShadowColor;
        barDataSet.mBarBorderWidth = this.mBarBorderWidth;
        barDataSet.mStackLabels = this.mStackLabels;
        barDataSet.mHighLightAlpha = this.mHighLightAlpha;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IBarDataSet
    public List<Fill> getFills() {
        return this.mFills;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IBarDataSet
    public Fill getFill(int i) {
        List<Fill> list = this.mFills;
        return list.get(i % list.size());
    }

    @Deprecated
    public List<Fill> getGradients() {
        return this.mFills;
    }

    @Deprecated
    public Fill getGradient(int i) {
        return getFill(i);
    }

    public void setGradientColor(int i, int i2) {
        this.mFills.clear();
        this.mFills.add(new Fill(i, i2));
    }

    @Deprecated
    public void setGradientColors(List<Fill> list) {
        this.mFills = list;
    }

    public void setFills(List<Fill> list) {
        this.mFills = list;
    }

    private void calcEntryCountIncludingStacks(List<BarEntry> list) {
        this.mEntryCountStacks = 0;
        for (int i = 0; i < list.size(); i++) {
            float[] yVals = list.get(i).getYVals();
            if (yVals == null) {
                this.mEntryCountStacks++;
            } else {
                this.mEntryCountStacks += yVals.length;
            }
        }
    }

    private void calcStackSize(List<BarEntry> list) {
        for (int i = 0; i < list.size(); i++) {
            float[] yVals = list.get(i).getYVals();
            if (yVals != null && yVals.length > this.mStackSize) {
                this.mStackSize = yVals.length;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.github.mikephil.charting.data.DataSet
    public void calcMinMax(BarEntry barEntry) {
        if (barEntry == null || Float.isNaN(barEntry.getY())) {
            return;
        }
        if (barEntry.getYVals() == null) {
            if (barEntry.getY() < this.mYMin) {
                this.mYMin = barEntry.getY();
            }
            if (barEntry.getY() > this.mYMax) {
                this.mYMax = barEntry.getY();
            }
        } else {
            if ((-barEntry.getNegativeSum()) < this.mYMin) {
                this.mYMin = -barEntry.getNegativeSum();
            }
            if (barEntry.getPositiveSum() > this.mYMax) {
                this.mYMax = barEntry.getPositiveSum();
            }
        }
        calcMinMaxX(barEntry);
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IBarDataSet
    public int getStackSize() {
        return this.mStackSize;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IBarDataSet
    public boolean isStacked() {
        return this.mStackSize > 1;
    }

    public int getEntryCountStacks() {
        return this.mEntryCountStacks;
    }

    public void setBarShadowColor(int i) {
        this.mBarShadowColor = i;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IBarDataSet
    public int getBarShadowColor() {
        return this.mBarShadowColor;
    }

    public void setBarBorderWidth(float f) {
        this.mBarBorderWidth = f;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IBarDataSet
    public float getBarBorderWidth() {
        return this.mBarBorderWidth;
    }

    public void setBarBorderColor(int i) {
        this.mBarBorderColor = i;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IBarDataSet
    public int getBarBorderColor() {
        return this.mBarBorderColor;
    }

    public void setHighLightAlpha(int i) {
        this.mHighLightAlpha = i;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IBarDataSet
    public int getHighLightAlpha() {
        return this.mHighLightAlpha;
    }

    public void setStackLabels(String[] strArr) {
        this.mStackLabels = strArr;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IBarDataSet
    public String[] getStackLabels() {
        return this.mStackLabels;
    }
}
