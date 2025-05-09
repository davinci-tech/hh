package defpackage;

import com.huawei.healthcloud.plugintrack.trackanimation.gpsutil.LatLong;
import com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.utils.PhotoModel;
import com.huawei.hwfoundationmodel.trackmodel.MotionPath;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/* loaded from: classes4.dex */
public class hcf {
    private int f;
    private MotionPath g;
    private MotionPathSimplify h;
    private List<PhotoModel> i;
    private HashMap<Integer, List<PhotoModel>> e = new HashMap<>();
    private HashMap<PhotoModel, Double> d = new HashMap<>();
    private HashMap<PhotoModel, Double> j = new HashMap<>();
    private double b = 0.0d;

    /* renamed from: a, reason: collision with root package name */
    private int f13087a = 0;
    private LatLong c = null;

    public hcf(List<PhotoModel> list, MotionPath motionPath, MotionPathSimplify motionPathSimplify, int i) {
        this.g = null;
        this.h = null;
        this.g = motionPath;
        this.h = motionPathSimplify;
        this.f = i;
        this.i = list;
    }

    public HashMap<Integer, List<PhotoModel>> e() {
        c();
        a();
        return this.e;
    }

    private void c() {
        List<PhotoModel> list;
        this.d.clear();
        if (this.g == null || (list = this.i) == null || list.size() == 0) {
            return;
        }
        long d = d();
        while (this.f13087a < this.i.size() && this.i.get(this.f13087a).getTime() - d < 20000) {
            this.d.put(this.i.get(this.f13087a), Double.valueOf(this.b));
            this.f13087a++;
        }
        c(this.b);
    }

    private long d() {
        boolean z;
        long j;
        int i;
        double d;
        char c = 0;
        boolean z2 = false;
        long j2 = 0;
        for (Map.Entry<Long, double[]> entry : this.g.requestLbsDataMap().entrySet()) {
            if (entry.getValue() != null && entry.getValue().length >= 2) {
                int i2 = 1;
                if (hbb.c(entry.getValue()[c], entry.getValue()[1])) {
                    b(j2, this.b, entry);
                    z2 = true;
                } else {
                    long c2 = c(entry.getKey().longValue(), entry.getValue());
                    if (!hbb.e(entry.getValue()[c], entry.getValue()[1]) && c2 >= 0) {
                        LatLong latLong = new LatLong(entry.getValue()[0], entry.getValue()[1]);
                        if (this.c == null) {
                            this.c = latLong;
                            while (this.f13087a < this.i.size() && c2 > this.i.get(this.f13087a).getTime()) {
                                this.f13087a++;
                            }
                            j2 = c2;
                        }
                        boolean z3 = z2 ? false : z2;
                        double b = hau.b(this.c, latLong);
                        while (this.f13087a < this.i.size() && c2 >= this.i.get(this.f13087a).getTime()) {
                            if (!z2) {
                                long j3 = c2 - j2;
                                if (j3 <= 20000 || b <= 20.0d) {
                                    d = b;
                                    z = z2;
                                    j = c2;
                                    this.d.put(this.i.get(this.f13087a), Double.valueOf(this.b + (((this.i.get(this.f13087a).getTime() - j2) * d) / j3)));
                                    i = 1;
                                    this.f13087a++;
                                    i2 = i;
                                    z2 = z;
                                    b = d;
                                    c2 = j;
                                }
                            }
                            z = z2;
                            j = c2;
                            i = i2;
                            d = b;
                            this.f13087a += i;
                            i2 = i;
                            z2 = z;
                            b = d;
                            c2 = j;
                        }
                        this.b += b;
                        this.c = latLong;
                        z2 = z3;
                        j2 = c2;
                        c = 0;
                    }
                }
            }
            z2 = z2;
            c = 0;
        }
        return j2;
    }

    private void b(long j, double d, Map.Entry<Long, double[]> entry) {
        long c = c(entry.getKey().longValue(), entry.getValue());
        while (this.f13087a < this.i.size() && c >= this.i.get(this.f13087a).getTime() && Math.abs(this.i.get(this.f13087a).getTime() - j) < 20000) {
            this.d.put(this.i.get(this.f13087a), Double.valueOf(d));
            this.f13087a++;
        }
    }

    private long c(long j, double[] dArr) {
        if (dArr.length == 4) {
            j = (long) dArr[3];
        }
        if (String.valueOf(j).length() == 10) {
            return j * 1000;
        }
        if (String.valueOf(j).length() == 13) {
            return j;
        }
        return -1L;
    }

    private void c(double d) {
        this.j.clear();
        for (Map.Entry<PhotoModel, Double> entry : this.d.entrySet()) {
            this.j.put(entry.getKey(), Double.valueOf(entry.getValue().doubleValue() / d));
        }
    }

    private void a() {
        int i;
        this.e.clear();
        TreeMap treeMap = new TreeMap();
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<PhotoModel, Double> entry : this.j.entrySet()) {
            int round = Math.round(entry.getValue().floatValue() * (this.f - 1));
            if (round >= 0) {
                if (treeMap.containsKey(Integer.valueOf(round))) {
                    ((List) treeMap.get(Integer.valueOf(round))).add(entry.getKey());
                } else {
                    ArrayList arrayList2 = new ArrayList();
                    arrayList2.add(entry.getKey());
                    treeMap.put(Integer.valueOf(round), arrayList2);
                }
                if (!arrayList.contains(Integer.valueOf(round))) {
                    arrayList.add(Integer.valueOf(round));
                }
            }
        }
        Collections.sort(arrayList);
        double b = b();
        TreeMap treeMap2 = new TreeMap();
        for (int i2 = 0; i2 < arrayList.size(); i2 = i) {
            int intValue = ((Integer) arrayList.get(i2)).intValue();
            treeMap2.put(Integer.valueOf(intValue), (List) treeMap.get(Integer.valueOf(intValue)));
            i = i2 + 1;
            while (i < arrayList.size() && (((Integer) arrayList.get(i)).intValue() - ((Integer) arrayList.get(i2)).intValue()) * b < 25.0d) {
                treeMap2.get(Integer.valueOf(intValue)).addAll((Collection) treeMap.get(Integer.valueOf(((Integer) arrayList.get(i)).intValue())));
                i++;
            }
        }
        c(treeMap2);
    }

    private void c(Map<Integer, List<PhotoModel>> map) {
        int i = 0;
        for (Map.Entry<Integer, List<PhotoModel>> entry : map.entrySet()) {
            List<PhotoModel> value = entry.getValue();
            Collections.sort(value, new Comparator<PhotoModel>() { // from class: hcf.4
                @Override // java.util.Comparator
                /* renamed from: e, reason: merged with bridge method [inline-methods] */
                public int compare(PhotoModel photoModel, PhotoModel photoModel2) {
                    if (photoModel.getRowId() > photoModel2.getRowId()) {
                        return 1;
                    }
                    return photoModel.getRowId() < photoModel2.getRowId() ? -1 : 0;
                }
            });
            ArrayList arrayList = new ArrayList();
            for (int i2 = 0; i2 < value.size() && i2 < 3; i2++) {
                arrayList.add(value.get(i2));
                i++;
                if (i == 9) {
                    break;
                }
            }
            if (arrayList.size() > 0) {
                this.e.put(entry.getKey(), arrayList);
            }
            if (i == 9) {
                return;
            }
        }
    }

    private double b() {
        return (Math.round(Math.abs(this.h.requestTotalDistance()) / 10.0d) * 10.0d) / this.f;
    }
}
