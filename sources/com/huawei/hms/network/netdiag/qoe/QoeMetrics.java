package com.huawei.hms.network.netdiag.qoe;

/* loaded from: classes9.dex */
public abstract class QoeMetrics {
    public abstract int getChannelIndex();

    public abstract int getChannelNum();

    public abstract int getDlBandwidth();

    public abstract int getDlRate();

    public abstract int getDlRtt();

    public abstract int getNetQoeLevel();

    public abstract int getUlBandwidth();

    public abstract int getUlPkgLossRate();

    public abstract int getUlRate();

    public abstract int getUlRtt();

    public abstract boolean isSuccess();
}
