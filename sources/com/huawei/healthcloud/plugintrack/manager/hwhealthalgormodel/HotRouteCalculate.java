package com.huawei.healthcloud.plugintrack.manager.hwhealthalgormodel;

import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.trackprocess.model.GpsPoint;
import com.huawei.healthcloud.plugintrack.manager.hwhealthalgormodel.HotRouteCalculate;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.enc;
import defpackage.gvh;
import defpackage.gwe;
import defpackage.koq;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class HotRouteCalculate extends gvh {
    private List<GpsPoint> c;
    private List<GpsPoint> h;
    private d i;
    private double b = 111194.9375d;
    private double f = 2.2483037953054293E-4d;
    private double d = 2.2483037953054293E-4d;
    private double j = -1.0d;

    public interface CalculateAnchorPointInterface<T> {
        T calculateAnchorPoint(List<T> list, int i, double d);
    }

    public interface CalculateDistanceInterface<T> {
        double calculateDistance(T t, T t2);
    }

    private double c(double d2) {
        return d2 * 0.6d;
    }

    @Override // defpackage.gvh
    public void b(final enc encVar) {
        if (encVar == null) {
            LogUtil.b("Track_HotRouteCalculate", "hotPathDetailInfo is null in initParams");
            return;
        }
        super.b(encVar);
        this.h = encVar.k();
        this.j = encVar.r();
        ThreadPoolManager.d().execute(new Runnable() { // from class: gvc
            @Override // java.lang.Runnable
            public final void run() {
                HotRouteCalculate.this.e(encVar);
            }
        });
    }

    @Override // defpackage.gvh
    public boolean b(Map<Long, double[]> map, int i) {
        return e(map, i);
    }

    @Override // defpackage.gvh
    public void e() {
        super.e();
        List<GpsPoint> list = this.c;
        if (list != null) {
            list.clear();
        }
        List<GpsPoint> list2 = this.h;
        if (list2 != null) {
            list2.clear();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void e(enc encVar) {
        GpsPoint gpsPoint;
        if (encVar == null) {
            LogUtil.b("Track_HotRouteCalculate", "hotPathDetail info is null, return");
            return;
        }
        List<GpsPoint> k = encVar.k();
        if (encVar.o() != null) {
            gpsPoint = encVar.o();
        } else if (koq.c(encVar.k())) {
            gpsPoint = encVar.k().get(0);
        } else {
            LogUtil.b("Track_HotRouteCalculate", "hotPathDetailInfo is null && pathPoints is null");
            return;
        }
        c(gpsPoint);
        b(k, gpsPoint);
        this.c = a(k);
    }

    private void b(List<GpsPoint> list, GpsPoint gpsPoint) {
        this.i = new d(gpsPoint.getLatitude(), gpsPoint.getLatitude(), gpsPoint.getLongitude(), gpsPoint.getLongitude());
        if (koq.c(list)) {
            for (GpsPoint gpsPoint2 : list) {
                this.i.c(gpsPoint2.getLatitude(), gpsPoint2.getLongitude());
            }
        }
        this.i.e(this.d, this.f);
    }

    private void c(GpsPoint gpsPoint) {
        this.b = Math.cos(BigDecimal.valueOf(gpsPoint.getLatitude()).multiply(BigDecimal.valueOf(3.141592653589793d)).divide(BigDecimal.valueOf(180.0d), 7, 4).doubleValue()) * 111194.9375d;
        double sqrt = Math.sqrt(1525.0d);
        this.f = sqrt / this.b;
        this.d = sqrt / 111194.9375d;
    }

    private boolean e(Map<Long, double[]> map, double d2) {
        if (d2 < c(this.j)) {
            LogUtil.a("Track_HotRouteCalculate", "user distance is smaller than the hot path distance, return");
            return false;
        }
        if (map == null || map.size() == 0) {
            LogUtil.a("Track_HotRouteCalculate", "lbsData is empty, return false");
            return false;
        }
        if (koq.b(this.h)) {
            LogUtil.a("Track_HotRouteCalculate", "pathPoints is null, return false");
            return false;
        }
        long currentTimeMillis = System.currentTimeMillis();
        boolean c = c(map, this.h);
        double currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
        this.f12957a.put("totalTime", String.valueOf(currentTimeMillis2));
        LogUtil.a("Track_HotRouteCalculate", "match calculate time cost is ", Double.valueOf(currentTimeMillis2));
        return c;
    }

    private boolean c(Map<Long, double[]> map, List<GpsPoint> list) {
        List<GpsPoint> list2 = this.c;
        List<GpsPoint> list3 = (list2 == null || list2.isEmpty()) ? list : this.c;
        LogUtil.a("Track_HotRouteCalculate", "pathPoints size is ", Integer.valueOf(list.size()), "compress point size is ", Integer.valueOf(list3.size()));
        ArrayList<double[]> arrayList = new ArrayList<>((Collection<? extends double[]>) map.values());
        long currentTimeMillis = System.currentTimeMillis();
        List<double[]> e = e(arrayList);
        double currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
        LogUtil.a("Track_HotRouteCalculate", "compress user locations point cost is ", Double.valueOf(currentTimeMillis2), "userLocations count is ", Integer.valueOf(arrayList.size()), " compressLocations count is " + e.size());
        this.f12957a.put("trackLocationsCompressSize", String.valueOf(list3.size()));
        this.f12957a.put("trackLocationsOriginalSize", String.valueOf(list.size()));
        this.f12957a.put("userLocationsOriginalSize", String.valueOf(arrayList.size()));
        this.f12957a.put("userLocationsCompressSize", String.valueOf(e.size()));
        this.f12957a.put("userLocationsCompressTime", String.valueOf(currentTimeMillis2));
        int size = (int) (list3.size() * 0.57d);
        int i = 0;
        for (double[] dArr : e) {
            d dVar = this.i;
            if (dVar == null || dVar.b(dArr[0], dArr[1])) {
                i += a(dArr, list3);
                if (i >= size) {
                    LogUtil.a("Track_HotRouteCalculate", "match point threshold, return true");
                    return true;
                }
            }
        }
        this.f12957a.put("matchPointSize", String.valueOf(i));
        LogUtil.a("Track_HotRouteCalculate", "matchPoint count is ", Integer.valueOf(i), " point threshold is ", Integer.valueOf(size));
        return false;
    }

    private int a(double[] dArr, List<GpsPoint> list) {
        Iterator<GpsPoint> it = list.iterator();
        int i = 0;
        while (it.hasNext()) {
            GpsPoint next = it.next();
            if (c(dArr[0], dArr[1], next.getLatitude(), next.getLongitude())) {
                i++;
                it.remove();
            }
        }
        return i;
    }

    private List<double[]> e(ArrayList<double[]> arrayList) {
        c(arrayList);
        if (arrayList.size() <= 2000) {
            LogUtil.a("Track_HotRouteCalculate", "userLocation smaller than 2000, no need to compress");
            return arrayList;
        }
        List<Double> b = b(arrayList, new CalculateDistanceInterface() { // from class: gvi
            @Override // com.huawei.healthcloud.plugintrack.manager.hwhealthalgormodel.HotRouteCalculate.CalculateDistanceInterface
            public final double calculateDistance(Object obj, Object obj2) {
                return HotRouteCalculate.this.e((double[]) obj, (double[]) obj2);
            }
        });
        final double[] dArr = arrayList.get(0);
        return c(arrayList, b, 25.0d, new CalculateAnchorPointInterface() { // from class: gvg
            @Override // com.huawei.healthcloud.plugintrack.manager.hwhealthalgormodel.HotRouteCalculate.CalculateAnchorPointInterface
            public final Object calculateAnchorPoint(List list, int i, double d2) {
                return HotRouteCalculate.d(dArr, list, i, d2);
            }
        });
    }

    public /* synthetic */ double e(double[] dArr, double[] dArr2) {
        return a(dArr[1], dArr[0], dArr2[1], dArr2[0]);
    }

    public static /* synthetic */ double[] d(double[] dArr, List list, int i, double d2) {
        if (koq.b(list, i)) {
            LogUtil.b("Track_HotRouteCalculate", "userLocations out of bound, & index is ", Integer.valueOf(i));
            return dArr;
        }
        double[] dArr2 = (double[]) list.get(i);
        double[] dArr3 = (double[]) list.get(i - 1);
        double d3 = dArr2[0];
        double d4 = dArr3[0];
        double d5 = dArr2[1];
        double d6 = dArr3[1];
        return new double[]{((d3 - d4) * d2) + d4, ((d5 - d6) * d2) + d6};
    }

    private void c(List<double[]> list) {
        Iterator<double[]> it = list.iterator();
        while (it.hasNext()) {
            double[] next = it.next();
            if (gwe.d(next[0], next[1])) {
                it.remove();
            }
        }
    }

    private List<GpsPoint> a(List<GpsPoint> list) {
        if (koq.b(list)) {
            LogUtil.a("Track_HotRouteCalculate", "trackLocations is empty, return");
            return Collections.emptyList();
        }
        if (list.size() <= 2000) {
            LogUtil.a("Track_HotRouteCalculate", "trackLocation smaller than 2000, no need to compress");
            return list;
        }
        List<Double> b = b(list, new CalculateDistanceInterface() { // from class: gvk
            @Override // com.huawei.healthcloud.plugintrack.manager.hwhealthalgormodel.HotRouteCalculate.CalculateDistanceInterface
            public final double calculateDistance(Object obj, Object obj2) {
                return HotRouteCalculate.this.b((GpsPoint) obj, (GpsPoint) obj2);
            }
        });
        final GpsPoint gpsPoint = list.get(0);
        return c(list, b, 25.0d, new CalculateAnchorPointInterface() { // from class: gvj
            @Override // com.huawei.healthcloud.plugintrack.manager.hwhealthalgormodel.HotRouteCalculate.CalculateAnchorPointInterface
            public final Object calculateAnchorPoint(List list2, int i, double d2) {
                return HotRouteCalculate.c(GpsPoint.this, list2, i, d2);
            }
        });
    }

    public /* synthetic */ double b(GpsPoint gpsPoint, GpsPoint gpsPoint2) {
        return a(gpsPoint.getLongitude(), gpsPoint.getLatitude(), gpsPoint2.getLongitude(), gpsPoint2.getLatitude());
    }

    public static /* synthetic */ GpsPoint c(GpsPoint gpsPoint, List list, int i, double d2) {
        if (koq.b(list, i)) {
            LogUtil.b("Track_HotRouteCalculate", "trackLocation out of bound, & index is ", Integer.valueOf(i));
            return gpsPoint;
        }
        GpsPoint gpsPoint2 = (GpsPoint) list.get(i);
        GpsPoint gpsPoint3 = (GpsPoint) list.get(i - 1);
        return new GpsPoint(((gpsPoint2.getLatitude() - gpsPoint3.getLatitude()) * d2) + gpsPoint3.getLatitude(), ((gpsPoint2.getLongitude() - gpsPoint3.getLongitude()) * d2) + gpsPoint3.getLongitude());
    }

    private <T> List<T> c(List<T> list, List<Double> list2, double d2, CalculateAnchorPointInterface<T> calculateAnchorPointInterface) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(list.get(0));
        for (int i = 0; i < list.size(); i++) {
            if (list2.get(i).doubleValue() > arrayList.size() * d2) {
                int i2 = i - 1;
                arrayList.add(calculateAnchorPointInterface.calculateAnchorPoint(list, i, ((arrayList.size() * d2) - list2.get(i2).doubleValue()) / (list2.get(i).doubleValue() - list2.get(i2).doubleValue())));
            }
        }
        return arrayList;
    }

    private <T> List<Double> b(List<T> list, CalculateDistanceInterface<T> calculateDistanceInterface) {
        ArrayList arrayList = new ArrayList(list.size());
        for (int i = 0; i < list.size(); i++) {
            if (i == 0) {
                arrayList.add(Double.valueOf(0.0d));
            } else {
                int i2 = i - 1;
                arrayList.add(Double.valueOf(calculateDistanceInterface.calculateDistance(list.get(i), list.get(i2)) + ((Double) arrayList.get(i2)).doubleValue()));
            }
        }
        return arrayList;
    }

    private double a(double d2, double d3, double d4, double d5) {
        double abs = Math.abs(d2 - d4) * this.b;
        double abs2 = Math.abs(d3 - d5) * 111194.9375d;
        return Math.sqrt((abs * abs) + (abs2 * abs2));
    }

    private boolean c(double d2, double d3, double d4, double d5) {
        return Math.abs(d2 - d4) <= this.d && Math.abs(d3 - d5) <= this.f;
    }

    static class d {

        /* renamed from: a, reason: collision with root package name */
        private double f3521a;
        private double b;
        private double c;
        private double e;

        private d() {
            this(0.0d, 0.0d, 0.0d, 0.0d);
        }

        d(double d, double d2, double d3, double d4) {
            this.e = d;
            this.f3521a = d2;
            this.b = d3;
            this.c = d4;
        }

        public void c(double d, double d2) {
            this.c = Math.min(this.c, d2);
            this.b = Math.max(this.b, d2);
            this.f3521a = Math.min(this.f3521a, d);
            this.e = Math.max(this.e, d);
        }

        public boolean b(double d, double d2) {
            return this.f3521a <= d && d <= this.e && this.c <= d2 && d2 <= this.b;
        }

        public void e(double d, double d2) {
            this.c -= d2;
            this.f3521a -= d;
            this.b += d2;
            this.e += d;
        }
    }
}
