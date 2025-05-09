package com.huawei.health.device.fatscale.datatypes;

/* loaded from: classes3.dex */
public interface BaseViewBeanApi {
    double[] getDoubleArrayLevelByType(int i);

    double getDoubleOrIntLevelByType(int i);

    int getFractionDigitByType(int i);

    String[] getStringArrayLevelByType(int i);

    String getStringLevelByType(int i);

    String getWeightScaleProductId();

    boolean isNewScaleType();

    boolean isVisible(int i, boolean z);
}
