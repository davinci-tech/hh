package com.huawei.hwcloudmodel.model.unite;

/* loaded from: classes5.dex */
public class RouteExtendData {
    private double mCumulativeClimb;
    private double mCumulativeDecrease;
    private int mRouteType;
    private String routeId;

    public String getRouteId() {
        return this.routeId;
    }

    public void setRouteId(String str) {
        this.routeId = str;
    }

    public double getCumulativeClimb() {
        return this.mCumulativeClimb;
    }

    public void setCumulativeClimb(double d) {
        this.mCumulativeClimb = d;
    }

    public double getCumulativeDecrease() {
        return this.mCumulativeDecrease;
    }

    public void setCumulativeDecrease(double d) {
        this.mCumulativeDecrease = d;
    }

    public int getRouteType() {
        return this.mRouteType;
    }

    public void setRouteType(int i) {
        this.mRouteType = i;
    }
}
