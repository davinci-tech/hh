package com.huawei.featureuserprofile.route.hwtcxmodel;

import com.huawei.featureuserprofile.route.hwgpxmodel.xmlparser.Tag;

@Tag("Position")
/* loaded from: classes3.dex */
public class Position {

    @Tag("LatitudeDegrees")
    private double mLatitudeDegrees;

    @Tag("LongitudeDegrees")
    private double mLongitudeDegrees;

    public void setLatitudeDegrees(double d) {
        this.mLatitudeDegrees = d;
    }

    public double getLatitudeDegrees() {
        return this.mLatitudeDegrees;
    }

    public void setLongitudeDegrees(double d) {
        this.mLongitudeDegrees = d;
    }

    public double getLongitudeDegrees() {
        return this.mLongitudeDegrees;
    }
}
