package com.github.mikephil.charting.buffer;

import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

/* loaded from: classes2.dex */
public class BarBuffer extends AbstractBuffer<IBarDataSet> {
    protected float mBarWidth;
    protected boolean mContainsStacks;
    protected int mDataSetCount;
    protected int mDataSetIndex;
    protected boolean mInverted;

    public BarBuffer(int i, int i2, boolean z) {
        super(i);
        this.mDataSetIndex = 0;
        this.mInverted = false;
        this.mBarWidth = 1.0f;
        this.mDataSetCount = i2;
        this.mContainsStacks = z;
    }

    public void setBarWidth(float f) {
        this.mBarWidth = f;
    }

    public void setDataSet(int i) {
        this.mDataSetIndex = i;
    }

    public void setInverted(boolean z) {
        this.mInverted = z;
    }

    protected void addBar(float f, float f2, float f3, float f4) {
        float[] fArr = this.buffer;
        int i = this.index;
        this.index = i + 1;
        fArr[i] = f;
        float[] fArr2 = this.buffer;
        int i2 = this.index;
        this.index = i2 + 1;
        fArr2[i2] = f2;
        float[] fArr3 = this.buffer;
        int i3 = this.index;
        this.index = i3 + 1;
        fArr3[i3] = f3;
        float[] fArr4 = this.buffer;
        int i4 = this.index;
        this.index = i4 + 1;
        fArr4[i4] = f4;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.github.mikephil.charting.buffer.AbstractBuffer
    public void feed(IBarDataSet iBarDataSet) {
        float f;
        float f2;
        float abs;
        float abs2;
        float f3;
        float entryCount = iBarDataSet.getEntryCount();
        float f4 = this.phaseX;
        float f5 = this.mBarWidth / 2.0f;
        for (int i = 0; i < entryCount * f4; i++) {
            BarEntry barEntry = (BarEntry) iBarDataSet.getEntryForIndex(i);
            if (barEntry != null) {
                float x = barEntry.getX();
                float y = barEntry.getY();
                float[] yVals = barEntry.getYVals();
                float f6 = 0.0f;
                if (!this.mContainsStacks || yVals == null) {
                    if (this.mInverted) {
                        f = 0.0f;
                        f2 = y >= 0.0f ? y : 0.0f;
                        if (y > 0.0f) {
                            y = 0.0f;
                        }
                    } else {
                        f = 0.0f;
                        float f7 = y >= 0.0f ? y : 0.0f;
                        if (y > 0.0f) {
                            y = 0.0f;
                        }
                        float f8 = y;
                        y = f7;
                        f2 = f8;
                    }
                    if (y > f) {
                        y *= this.phaseY;
                    } else {
                        f2 *= this.phaseY;
                    }
                    addBar(x - f5, y, x + f5, f2);
                } else {
                    float f9 = -barEntry.getNegativeSum();
                    float f10 = 0.0f;
                    int i2 = 0;
                    while (i2 < yVals.length) {
                        float f11 = yVals[i2];
                        if (f11 == f6 && (f10 == f6 || f9 == f6)) {
                            abs = f11;
                            abs2 = f9;
                            f9 = abs;
                        } else if (f11 >= f6) {
                            abs = f11 + f10;
                            abs2 = f9;
                            f9 = f10;
                            f10 = abs;
                        } else {
                            abs = Math.abs(f11) + f9;
                            abs2 = Math.abs(f11) + f9;
                        }
                        if (this.mInverted) {
                            f3 = f9 >= abs ? f9 : abs;
                            if (f9 > abs) {
                                f9 = abs;
                            }
                        } else {
                            float f12 = f9 >= abs ? f9 : abs;
                            if (f9 > abs) {
                                f9 = abs;
                            }
                            float f13 = f12;
                            f3 = f9;
                            f9 = f13;
                        }
                        addBar(x - f5, f9 * this.phaseY, x + f5, f3 * this.phaseY);
                        i2++;
                        f9 = abs2;
                        f6 = 0.0f;
                    }
                }
            }
        }
        reset();
    }
}
