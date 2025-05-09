package com.amap.api.services.route;

import com.amap.api.services.core.LatLonPoint;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes8.dex */
public class ElecConsumeInfo {

    /* renamed from: a, reason: collision with root package name */
    private int f1543a;
    private int b;
    private LatLonPoint c;
    private List<Integer> d = new ArrayList();

    public int getConsumeEnergy() {
        return this.f1543a;
    }

    public void setConsumeEnergy(int i) {
        this.f1543a = i;
    }

    public int getRunOutStepIndex() {
        return this.b;
    }

    public void setRunOutStepIndex(int i) {
        this.b = i;
    }

    public LatLonPoint getRunOutPoint() {
        return this.c;
    }

    public void setRunOutPoint(LatLonPoint latLonPoint) {
        this.c = latLonPoint;
    }

    public List<Integer> getLeftEnergy() {
        return this.d;
    }

    public void setLeftEnergy(List<Integer> list) {
        this.d = list;
    }
}
