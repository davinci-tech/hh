package com.github.mikephil.charting.buffer;

import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

/* loaded from: classes2.dex */
public class HorizontalBarBuffer extends BarBuffer {
    public HorizontalBarBuffer(int i, int i2, boolean z) {
        super(i, i2, z);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.github.mikephil.charting.buffer.BarBuffer, com.github.mikephil.charting.buffer.AbstractBuffer
    public void feed(IBarDataSet iBarDataSet) {
        float f;
        float abs;
        float abs2;
        float f2;
        float entryCount = iBarDataSet.getEntryCount();
        float f3 = this.phaseX;
        float f4 = this.mBarWidth / 2.0f;
        for (int i = 0; i < entryCount * f3; i++) {
            BarEntry barEntry = (BarEntry) iBarDataSet.getEntryForIndex(i);
            if (barEntry != null) {
                float x = barEntry.getX();
                float y = barEntry.getY();
                float[] yVals = barEntry.getYVals();
                if (!this.mContainsStacks || yVals == null) {
                    if (this.mInverted) {
                        f = y >= 0.0f ? y : 0.0f;
                        if (y > 0.0f) {
                            y = 0.0f;
                        }
                    } else {
                        float f5 = y >= 0.0f ? y : 0.0f;
                        if (y > 0.0f) {
                            y = 0.0f;
                        }
                        float f6 = y;
                        y = f5;
                        f = f6;
                    }
                    if (y > 0.0f) {
                        y *= this.phaseY;
                    } else {
                        f *= this.phaseY;
                    }
                    addBar(f, x + f4, y, x - f4);
                } else {
                    float f7 = -barEntry.getNegativeSum();
                    float f8 = 0.0f;
                    int i2 = 0;
                    while (i2 < yVals.length) {
                        float f9 = yVals[i2];
                        if (f9 >= 0.0f) {
                            abs = f9 + f8;
                            abs2 = f7;
                            f7 = f8;
                            f8 = abs;
                        } else {
                            abs = Math.abs(f9) + f7;
                            abs2 = Math.abs(f9) + f7;
                        }
                        if (this.mInverted) {
                            f2 = f7 >= abs ? f7 : abs;
                            if (f7 > abs) {
                                f7 = abs;
                            }
                        } else {
                            float f10 = f7 >= abs ? f7 : abs;
                            if (f7 > abs) {
                                f7 = abs;
                            }
                            float f11 = f10;
                            f2 = f7;
                            f7 = f11;
                        }
                        addBar(f2 * this.phaseY, x + f4, f7 * this.phaseY, x - f4);
                        i2++;
                        f7 = abs2;
                    }
                }
            }
        }
        reset();
    }
}
