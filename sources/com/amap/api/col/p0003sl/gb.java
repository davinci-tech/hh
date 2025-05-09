package com.amap.api.col.p0003sl;

import com.amap.api.col.p0003sl.fz;
import com.amap.api.services.core.LatLonPoint;
import java.util.Iterator;
import java.util.LinkedHashMap;

/* loaded from: classes2.dex */
final class gb extends ga {

    /* renamed from: a, reason: collision with root package name */
    private double f1064a;

    public gb(String... strArr) {
        super(strArr);
        this.f1064a = 0.0d;
    }

    @Override // com.amap.api.col.p0003sl.ga
    protected final boolean a(LinkedHashMap<fz.b, Object> linkedHashMap, fz.b bVar) {
        if (linkedHashMap == null || bVar == null) {
            return false;
        }
        if (bVar.b == null) {
            return super.a(linkedHashMap, bVar);
        }
        for (fz.b bVar2 : linkedHashMap.keySet()) {
            if (bVar2 != null && bVar2.f1059a != null && bVar2.f1059a.equals(bVar.f1059a) && (bVar2.b instanceof a) && ((a) bVar2.b).a(bVar.b)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.amap.api.col.p0003sl.ga
    protected final Object b(LinkedHashMap<fz.b, Object> linkedHashMap, fz.b bVar) {
        if (linkedHashMap == null || bVar == null) {
            return null;
        }
        if (bVar.b == null) {
            return super.b(linkedHashMap, bVar);
        }
        for (fz.b bVar2 : linkedHashMap.keySet()) {
            if (bVar2 != null && bVar2.f1059a != null && bVar2.f1059a.equals(bVar.f1059a) && (bVar2.b instanceof a) && ((a) bVar2.b).a(bVar.b)) {
                return linkedHashMap.get(bVar2);
            }
        }
        return null;
    }

    @Override // com.amap.api.col.p0003sl.ga
    protected final Object c(LinkedHashMap<fz.b, Object> linkedHashMap, fz.b bVar) {
        fz.b bVar2;
        if (linkedHashMap != null && bVar != null) {
            if (bVar.b == null) {
                return super.c(linkedHashMap, bVar);
            }
            Iterator<fz.b> it = linkedHashMap.keySet().iterator();
            while (true) {
                if (!it.hasNext()) {
                    bVar2 = null;
                    break;
                }
                bVar2 = it.next();
                if (bVar2 != null && bVar2.f1059a != null && bVar2.f1059a.equals(bVar.f1059a) && (bVar2.b instanceof a) && ((a) bVar2.b).a(bVar.b)) {
                    break;
                }
            }
            if (bVar2 != null) {
                return linkedHashMap.remove(bVar2);
            }
        }
        return null;
    }

    public final double a() {
        return this.f1064a;
    }

    @Override // com.amap.api.col.p0003sl.ga
    public final void a(fz.a aVar) {
        super.a(aVar);
        if (aVar != null) {
            this.f1064a = aVar.d();
        }
    }

    static final class a {

        /* renamed from: a, reason: collision with root package name */
        LatLonPoint f1065a;
        double b;

        public a(double d, double d2, double d3) {
            this.f1065a = null;
            this.b = 0.0d;
            this.f1065a = new LatLonPoint(d, d2);
            this.b = d3;
        }

        public final boolean a(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            LatLonPoint latLonPoint = this.f1065a;
            a aVar = (a) obj;
            LatLonPoint latLonPoint2 = aVar.f1065a;
            if (latLonPoint == latLonPoint2) {
                return true;
            }
            return latLonPoint != null && ((double) fd.a(latLonPoint, latLonPoint2)) <= aVar.b;
        }
    }
}
