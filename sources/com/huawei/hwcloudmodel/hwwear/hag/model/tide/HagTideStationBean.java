package com.huawei.hwcloudmodel.hwwear.hag.model.tide;

import java.util.List;

/* loaded from: classes5.dex */
public class HagTideStationBean {
    private List<HagTide> mHagTides;
    private String mName;

    public String getName() {
        return this.mName;
    }

    public void setName(String str) {
        this.mName = str;
    }

    public List<HagTide> getHagTides() {
        return this.mHagTides;
    }

    public void setHagTides(List<HagTide> list) {
        this.mHagTides = list;
    }

    public String toString() {
        return "HagTideStationBean{mName='" + this.mName + "', mHagTides=" + this.mHagTides + '}';
    }
}
