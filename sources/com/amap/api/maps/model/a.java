package com.amap.api.maps.model;

import com.amap.api.col.p0003sl.de;
import com.autonavi.amap.mapcore.DPoint;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes8.dex */
final class a {

    /* renamed from: a, reason: collision with root package name */
    private final de f1438a;
    private final int b;
    private List<WeightedLatLng> c;
    private List<a> d;

    protected a(de deVar) {
        this(deVar, 0);
    }

    private a(double d, double d2, double d3, double d4, int i) {
        this(new de(d, d2, d3, d4), i);
    }

    private a(de deVar, int i) {
        this.d = null;
        this.f1438a = deVar;
        this.b = i;
    }

    protected final void a(WeightedLatLng weightedLatLng) {
        DPoint point = weightedLatLng.getPoint();
        if (this.f1438a.a(point.x, point.y)) {
            a(point.x, point.y, weightedLatLng);
        }
    }

    private void a(double d, double d2, WeightedLatLng weightedLatLng) {
        if (this.d == null) {
            if (this.c == null) {
                this.c = new ArrayList();
            }
            this.c.add(weightedLatLng);
            if (this.c.size() <= 50 || this.b >= 40) {
                return;
            }
            a();
            return;
        }
        if (d2 < this.f1438a.f) {
            if (d < this.f1438a.e) {
                this.d.get(0).a(d, d2, weightedLatLng);
                return;
            } else {
                this.d.get(1).a(d, d2, weightedLatLng);
                return;
            }
        }
        if (d < this.f1438a.e) {
            this.d.get(2).a(d, d2, weightedLatLng);
        } else {
            this.d.get(3).a(d, d2, weightedLatLng);
        }
    }

    private void a() {
        ArrayList arrayList = new ArrayList(4);
        this.d = arrayList;
        arrayList.add(new a(this.f1438a.f966a, this.f1438a.e, this.f1438a.b, this.f1438a.f, this.b + 1));
        this.d.add(new a(this.f1438a.e, this.f1438a.c, this.f1438a.b, this.f1438a.f, this.b + 1));
        this.d.add(new a(this.f1438a.f966a, this.f1438a.e, this.f1438a.f, this.f1438a.d, this.b + 1));
        this.d.add(new a(this.f1438a.e, this.f1438a.c, this.f1438a.f, this.f1438a.d, this.b + 1));
        List<WeightedLatLng> list = this.c;
        this.c = null;
        for (WeightedLatLng weightedLatLng : list) {
            a(weightedLatLng.getPoint().x, weightedLatLng.getPoint().y, weightedLatLng);
        }
    }

    protected final Collection<WeightedLatLng> a(de deVar) {
        ArrayList arrayList = new ArrayList();
        a(deVar, arrayList);
        return arrayList;
    }

    private void a(de deVar, Collection<WeightedLatLng> collection) {
        if (this.f1438a.a(deVar)) {
            List<a> list = this.d;
            if (list != null) {
                Iterator<a> it = list.iterator();
                while (it.hasNext()) {
                    it.next().a(deVar, collection);
                }
            } else if (this.c != null) {
                if (deVar.b(this.f1438a)) {
                    collection.addAll(this.c);
                    return;
                }
                for (WeightedLatLng weightedLatLng : this.c) {
                    if (deVar.a(weightedLatLng.getPoint())) {
                        collection.add(weightedLatLng);
                    }
                }
            }
        }
    }
}
