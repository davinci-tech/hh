package com.github.mikephil.charting.model;

import com.github.mikephil.charting.utils.Fill;

@Deprecated
/* loaded from: classes8.dex */
public class GradientColor extends Fill {
    @Deprecated
    public int getStartColor() {
        return getGradientColors()[0];
    }

    @Deprecated
    public void setStartColor(int i) {
        int i2 = 0;
        if (getGradientColors() == null || getGradientColors().length != 2) {
            int[] iArr = new int[2];
            iArr[0] = i;
            if (getGradientColors() != null && getGradientColors().length > 1) {
                i2 = getGradientColors()[1];
            }
            iArr[1] = i2;
            setGradientColors(iArr);
            return;
        }
        getGradientColors()[0] = i;
    }

    @Deprecated
    public int getEndColor() {
        return getGradientColors()[1];
    }

    @Deprecated
    public void setEndColor(int i) {
        if (getGradientColors() == null || getGradientColors().length != 2) {
            int[] iArr = new int[2];
            iArr[0] = (getGradientColors() == null || getGradientColors().length <= 0) ? 0 : getGradientColors()[0];
            iArr[1] = i;
            setGradientColors(iArr);
            return;
        }
        getGradientColors()[1] = i;
    }
}
