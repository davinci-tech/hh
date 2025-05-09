package com.huawei.featureuserprofile.route.bean;

import com.huawei.featureuserprofile.route.bean.KomootRouteBean;

/* loaded from: classes3.dex */
public class KomootRouteData {
    public static final int TYPE_DATA = 0;
    public static final int TYPE_FOOTER = 1;
    private KomootRouteBean.Tour mData;
    private int mType;

    public KomootRouteData(int i) {
        this(i, null);
    }

    public KomootRouteData(int i, KomootRouteBean.Tour tour) {
        this.mType = i;
        this.mData = tour;
    }

    public int getType() {
        return this.mType;
    }

    public void setType(int i) {
        this.mType = i;
    }

    public KomootRouteBean.Tour getData() {
        return this.mData;
    }

    public void setData(KomootRouteBean.Tour tour) {
        this.mData = tour;
    }
}
