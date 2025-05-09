package defpackage;

import com.huawei.healthcloud.plugintrack.trackanimation.gpsutil.LatLong;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
public class haq {

    /* renamed from: a, reason: collision with root package name */
    private c f13051a = new c();
    private ArrayList<LatLong> e;

    public void a() {
        LogUtil.a("Track_AreaProcess", "Go into handle");
        ArrayList<LatLong> arrayList = this.e;
        if (arrayList == null) {
            LogUtil.h("Track_AreaProcess", "GPS Data is null");
            this.e = new ArrayList<>(16);
        } else {
            this.e = b(arrayList);
            LogUtil.a("Track_AreaProcess", "Go out handle");
        }
    }

    public haq d(ArrayList<LatLong> arrayList) {
        this.e = arrayList;
        return this;
    }

    public ArrayList<LatLong> e() {
        return this.e;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private ArrayList<LatLong> b(ArrayList<LatLong> arrayList) {
        hjd latLng;
        if (arrayList == null) {
            return new ArrayList<>();
        }
        d dVar = null;
        c cVar = new c();
        ArrayList arrayList2 = new ArrayList();
        Iterator<LatLong> it = arrayList.iterator();
        while (it.hasNext()) {
            LatLong next = it.next();
            if (next != null && (latLng = next.getLatLng()) != null) {
                d dVar2 = new d(latLng.b, latLng.d);
                cVar.c(dVar2);
                d b = cVar.b();
                if (b != null) {
                    if (dVar == null) {
                        dVar = new d(b.d(), b.a());
                        arrayList2.add(Double.valueOf(0.0d));
                    } else {
                        d dVar3 = new d(b.d(), b.a());
                        double e = hau.e(dVar.d(), dVar.a(), dVar3.d(), dVar3.a());
                        arrayList2.add(Double.valueOf(e > 0.05d ? e : 0.0d));
                        this.f13051a.c(dVar2);
                        dVar = dVar3;
                    }
                }
            }
        }
        boolean[] zArr = new boolean[arrayList2.size()];
        for (int i = 0; i < arrayList2.size(); i++) {
            if (!zArr[i]) {
                zArr[i] = true;
                if (arrayList2.get(i).doubleValue() <= 1.0E-6d) {
                    a(arrayList2, arrayList, zArr, i);
                }
            }
        }
        return arrayList;
    }

    private void a(List<Double> list, ArrayList<LatLong> arrayList, boolean[] zArr, int i) {
        LatLong latLong;
        List<Integer> arrayList2 = new ArrayList<>();
        List<Integer> arrayList3 = new ArrayList<>();
        ArrayList arrayList4 = new ArrayList();
        c cVar = new c();
        c cVar2 = new c();
        c cVar3 = new c();
        for (int i2 = i; i2 < list.size(); i2++) {
            if (i2 < zArr.length) {
                zArr[i2] = true;
            }
            if (koq.b(arrayList, i2) || (latLong = arrayList.get(i2)) == null) {
                break;
            }
            hjd latLng = latLong.getLatLng();
            if (latLng != null) {
                d dVar = new d(latLng.b, latLng.d);
                if (list.get(i2).doubleValue() <= 1.0E-6d) {
                    arrayList2.add(Integer.valueOf(i2));
                    cVar.c(dVar);
                    if (arrayList4.size() > 0) {
                        arrayList3.addAll(arrayList4);
                        cVar2.c(cVar3.c());
                        cVar2.c(cVar3.d());
                        arrayList4 = new ArrayList();
                        cVar3 = new c();
                    }
                } else {
                    arrayList4.add(Integer.valueOf(i2));
                    cVar3.c(dVar);
                    if (arrayList4.size() + arrayList3.size() > arrayList2.size() / 5) {
                        break;
                    }
                }
            }
        }
        if (arrayList2.size() > arrayList.size() / 10 || arrayList2.size() >= 15) {
            a(arrayList, i, arrayList3, cVar, cVar2);
            b(arrayList, arrayList3, arrayList2, cVar2);
        }
    }

    private void a(ArrayList<LatLong> arrayList, int i, List<Integer> list, c cVar, c cVar2) {
        hjd latLng;
        while (true) {
            i--;
            if (i < 0) {
                return;
            }
            if (!koq.b(arrayList, i) && (latLng = arrayList.get(i).getLatLng()) != null) {
                d dVar = new d(latLng.b, latLng.d);
                list.add(Integer.valueOf(i));
                cVar2.c(dVar);
                if (a(cVar2, cVar)) {
                    return;
                }
            }
        }
    }

    private boolean a(c cVar, c cVar2) {
        return cVar.d().d() - cVar2.d().d() > -1.0E-6d && cVar.d().a() - cVar2.d().a() > -1.0E-6d && cVar2.c().d() - cVar.c().d() > -1.0E-6d && cVar2.c().a() - cVar.c().a() > -1.0E-6d;
    }

    private void b(ArrayList<LatLong> arrayList, List<Integer> list, List<Integer> list2, c cVar) {
        if (koq.b(list) || koq.b(list2)) {
            LogUtil.h("Track_AreaProcess", "setAreaToTrackData point list is empty");
            return;
        }
        hat hatVar = new hat(list.get(0).intValue(), list2.get(list2.size() - 1).intValue(), new hjd(cVar.c().d(), cVar.c().a()), new hjd(cVar.d().d(), cVar.d().a()));
        double a2 = a(hatVar);
        if (a2 >= 0.8d) {
            return;
        }
        b(arrayList, list, list2, hatVar, a2);
    }

    private void b(ArrayList<LatLong> arrayList, List<Integer> list, List<Integer> list2, hat hatVar, double d2) {
        boolean z = false;
        for (int size = list.size() - 1; size >= 0; size--) {
            int intValue = list.get(size).intValue();
            if (koq.b(arrayList, intValue)) {
                break;
            }
            LatLong latLong = arrayList.get(intValue);
            if (latLong != null) {
                if (!z) {
                    latLong.setAreaStart(true);
                    z = true;
                } else {
                    latLong.setAreaStart(false);
                    latLong.setAreaEnd(false);
                }
                latLong.setIsInArea(true);
                latLong.setLatLongArea(hatVar);
                latLong.setAreaFraction(d2);
            }
        }
        boolean z2 = false;
        for (int size2 = list2.size() - 1; size2 >= 0; size2--) {
            int intValue2 = list2.get(size2).intValue();
            if (koq.b(arrayList, intValue2)) {
                return;
            }
            LatLong latLong2 = arrayList.get(intValue2);
            if (latLong2 != null) {
                if (!z2) {
                    latLong2.setAreaEnd(true);
                    z2 = true;
                } else {
                    latLong2.setAreaStart(false);
                    latLong2.setAreaEnd(false);
                }
                latLong2.setIsInArea(true);
                latLong2.setLatLongArea(hatVar);
                latLong2.setAreaFraction(d2);
            }
        }
    }

    private double a(hat hatVar) {
        return (hau.e(hatVar.c().b, hatVar.c().d, hatVar.a().b, hatVar.c().d) + hau.e(hatVar.c().b, hatVar.c().d, hatVar.c().b, hatVar.a().d)) / (hau.e(this.f13051a.d().d(), this.f13051a.d().a(), this.f13051a.c().d(), this.f13051a.d().a()) + hau.e(this.f13051a.d().d(), this.f13051a.d().a(), this.f13051a.d().d(), this.f13051a.c().a()));
    }

    public int b() {
        int i = 0;
        if (koq.b(this.e)) {
            return 0;
        }
        Iterator<LatLong> it = this.e.iterator();
        boolean z = false;
        while (it.hasNext()) {
            LatLong next = it.next();
            if (next.getIsInArea() != z) {
                if (!z) {
                    i++;
                }
                z = next.getIsInArea();
            }
        }
        return i;
    }

    static class d {
        private double c;
        private double d;

        d(double d, double d2) {
            this.d = d;
            this.c = d2;
        }

        public void e(double d) {
            this.d = d;
        }

        public void d(double d) {
            this.c = d;
        }

        public double d() {
            return this.d;
        }

        public double a() {
            return this.c;
        }
    }

    static class c {
        private d b;
        private d d;
        private d e;

        private c() {
        }

        public d c() {
            return this.b;
        }

        public d d() {
            return this.d;
        }

        public d b() {
            return this.e;
        }

        public c c(d dVar) {
            if (dVar == null) {
                return this;
            }
            if (this.b == null) {
                this.b = new d(dVar.d(), dVar.a());
            }
            if (this.d == null) {
                this.d = new d(dVar.d(), dVar.a());
            }
            b(dVar);
            a();
            return this;
        }

        private void b(d dVar) {
            if (dVar.d() < this.b.d()) {
                this.b.e(dVar.d());
            } else if (dVar.d() > this.d.d()) {
                this.d.e(dVar.d());
            }
            if (dVar.a() < this.b.a()) {
                this.b.d(dVar.a());
            } else if (dVar.a() > this.d.a()) {
                this.d.d(dVar.a());
            }
        }

        private void a() {
            d dVar;
            if (this.b == null || (dVar = this.d) == null) {
                LogUtil.h("Track_AreaProcess", "calculateCenterPoint get null latlng");
            } else {
                this.e = new d(((dVar.d() - this.b.d()) / 2.0d) + this.b.d(), ((this.d.a() - this.b.a()) / 2.0d) + this.b.a());
            }
        }
    }
}
