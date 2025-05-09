package com.huawei.basefitnessadvice.callback;

import android.os.Bundle;

/* loaded from: classes3.dex */
public interface FitnessCallback {
    public static final String BUNDLE_FITNESS_DURATION = "BUNDLE_FITNESS_DURATION";
    public static final int TYPE_DEFAULT = -1;
    public static final int TYPE_END = 1;
    public static final int TYPE_FINISH = 4;
    public static final int TYPE_LONG_START = 5;
    public static final int TYPE_QUIT = 3;
    public static final int TYPE_START = 2;

    void onChange(String str, int i, Bundle bundle);
}
