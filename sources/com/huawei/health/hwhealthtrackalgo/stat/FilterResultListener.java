package com.huawei.health.hwhealthtrackalgo.stat;

/* loaded from: classes3.dex */
public interface FilterResultListener {
    public static final int ACCURACY_FILTER = 2;
    public static final int NO_FILTER = 0;
    public static final int OTHER_FILTER = 199;
    public static final int OVER_SPEED_FILTER = 1;
    public static final int SHORT_DISTANCE_FILTER = 3;
    public static final int SHORT_TIME_FILTER = 4;

    void onFilterResult(int i, long j);
}
