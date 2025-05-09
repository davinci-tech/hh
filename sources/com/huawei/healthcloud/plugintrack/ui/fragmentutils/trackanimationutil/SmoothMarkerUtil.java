package com.huawei.healthcloud.plugintrack.ui.fragmentutils.trackanimationutil;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.Polyline;
import com.amap.api.maps.model.PolylineOptions;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.profile.profile.ProfileExtendConstants;
import defpackage.gwe;
import defpackage.hkf;
import defpackage.koq;
import defpackage.nrn;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
public class SmoothMarkerUtil {

    /* renamed from: a, reason: collision with root package name */
    private AMap f3768a;
    private boolean d;
    private SmoothMarkerListner g;
    private Marker h;
    private int i;
    private double n;
    private List<hkf> c = new ArrayList(16);
    private long e = 5000;
    private List<List<b>> k = new ArrayList(16);
    private List<Polyline> l = new ArrayList(16);
    private List<List<List<LatLng>>> o = new ArrayList(16);
    private List<List<Long>> m = new ArrayList(16);
    private List<List<LatLng>> j = new ArrayList(16);
    private int f = -1;
    private a b = new a(this);

    public interface SmoothMarkerListner {
        void onFinished();
    }

    private int a(int i, int i2, double d) {
        int i3 = (i >> 24) & 255;
        int i4 = (i >> 16) & 255;
        int i5 = (i >> 8) & 255;
        return ((i & 255) + ((int) (d * ((i2 & 255) - r9)))) | ((i3 + ((int) ((((i2 >> 24) & 255) - i3) * d))) << 24) | ((i4 + ((int) ((((i2 >> 16) & 255) - i4) * d))) << 16) | ((i5 + ((int) ((((i2 >> 8) & 255) - i5) * d))) << 8);
    }

    private long c(double d, float f) {
        double d2 = f;
        if (d < d2) {
            return ProfileExtendConstants.TIME_OUT;
        }
        double d3 = d / d2;
        if (d3 > 7.0d) {
            return 7500L;
        }
        return ((long) ((d3 - 1.0d) * 1000.0d)) + ProfileExtendConstants.TIME_OUT;
    }

    static /* synthetic */ int i(SmoothMarkerUtil smoothMarkerUtil) {
        int i = smoothMarkerUtil.i + 1;
        smoothMarkerUtil.i = i;
        return i;
    }

    public SmoothMarkerUtil(AMap aMap, LatLng latLng) {
        if (aMap == null || latLng == null) {
            throw new RuntimeException("SmoothMarkerUtil invalid params in constructor");
        }
        this.f3768a = aMap;
        this.h = aMap.addMarker(new MarkerOptions().draggable(false).belowMaskLayer(true).position(latLng).icon(null).title("").anchor(0.5f, 0.5f).visible(false));
    }

    public void c(List<hkf> list, float f) {
        if (koq.b(list)) {
            LogUtil.a("Track_SmoothMarkerUtil", "saveData is Empty");
            return;
        }
        this.n = 0.0d;
        this.l.clear();
        this.j.clear();
        this.o.clear();
        this.m.clear();
        this.c.clear();
        this.k.clear();
        e(list);
        if (this.n == 0.0d) {
            this.n = 1.0d;
        }
        this.e = c(this.n, f);
        a(list);
    }

    private void e(List<hkf> list) {
        PolylineOptions visible;
        for (hkf hkfVar : list) {
            int a2 = hkfVar.a();
            if (a2 == 0) {
                visible = new PolylineOptions().width(15.0f).zIndex(10.0f).visible(true);
            } else if (a2 != 1) {
                visible = a2 != 2 ? null : new PolylineOptions().color(nrn.d(R.color._2131298897_res_0x7f090a51)).width(15.0f).zIndex(10.0f).setDottedLine(true).visible(gwe.d());
            } else {
                visible = new PolylineOptions().width(15.0f).zIndex(10.0f).visible(true).color(hkfVar.b());
            }
            if (visible == null) {
                LogUtil.a("Track_SmoothMarkerUtil", "wrong type");
            } else {
                this.c.add(hkfVar);
                this.l.add(this.f3768a.addPolyline(visible));
                ArrayList arrayList = new ArrayList(16);
                List<LatLng> c = hkfVar.c();
                int i = 0;
                while (i < c.size() - 1) {
                    LatLng latLng = c.get(i);
                    i++;
                    double calculateLineDistance = AMapUtils.calculateLineDistance(latLng, c.get(i));
                    arrayList.add(new b(this.n, calculateLineDistance));
                    this.n += calculateLineDistance;
                }
                this.k.add(arrayList);
                this.j.add(new ArrayList(16));
            }
        }
    }

    private void a(List<hkf> list) {
        int i = 0;
        for (hkf hkfVar : list) {
            int a2 = hkfVar.a();
            if (a2 == 0) {
                e(hkfVar, i);
            } else if (a2 == 1) {
                d(hkfVar, i);
            } else if (a2 == 2) {
                c(hkfVar, i);
            }
            i++;
        }
    }

    private void d(hkf hkfVar, int i) {
        ArrayList arrayList = new ArrayList(16);
        ArrayList arrayList2 = new ArrayList(16);
        if (koq.b(this.k, i)) {
            LogUtil.b("Track_SmoothMarkerUtil", "mTimesUtils is out of bound");
            return;
        }
        List<b> list = this.k.get(i);
        List<LatLng> c = hkfVar.c();
        ArrayList arrayList3 = new ArrayList(16);
        arrayList3.add(c.get(0));
        LatLng latLng = c.get(0);
        if (c.size() == 1) {
            arrayList.add(1L);
            arrayList2.add(arrayList3);
        } else {
            c(arrayList, arrayList2, list, c, latLng);
        }
        this.m.add(arrayList);
        this.o.add(arrayList2);
    }

    private void c(List<Long> list, List<List<LatLng>> list2, List<b> list3, List<LatLng> list4, LatLng latLng) {
        int i;
        char c;
        List<b> list5 = list3;
        List<LatLng> list6 = list4;
        ArrayList arrayList = new ArrayList(16);
        arrayList.add(list6.get(0));
        int i2 = 0;
        double d = -1.0d;
        ArrayList arrayList2 = arrayList;
        LatLng latLng2 = latLng;
        while (true) {
            int i3 = 1;
            if (i2 >= list4.size() - 1) {
                break;
            }
            double d2 = d(list5.get(i2));
            int i4 = i2 + 1;
            LatLng latLng3 = list6.get(i4);
            double d3 = 1.0d + d;
            if (d3 >= 1.0E-6d || d2 <= 30.0d) {
                LatLng latLng4 = latLng2;
                double d4 = d;
                i = i4;
                if (d3 >= 1.0E-6d || d2 > 30.0d) {
                    if (d3 > 1.0E-6d && d2 > 30.0d) {
                        list2.add(arrayList2);
                        list.add(Long.valueOf((long) d4));
                        ArrayList arrayList3 = new ArrayList(16);
                        int i5 = ((int) (d2 / 30.0d)) + 1;
                        while (i3 <= i5) {
                            arrayList3.add(e(latLng4, latLng3, b(i3, i5, list5.get(i2))));
                            list2.add(arrayList3);
                            list.add(Long.valueOf(((long) d2) / i5));
                            arrayList3 = new ArrayList(16);
                            i3++;
                            list5 = list3;
                        }
                        arrayList2 = arrayList3;
                        c = 16;
                        d = -1.0d;
                    } else if (d3 > 1.0E-6d && d4 + d2 > 30.0d) {
                        list2.add(arrayList2);
                        list.add(Long.valueOf((long) d4));
                        c = 16;
                        ArrayList arrayList4 = new ArrayList(16);
                        arrayList4.add(latLng3);
                        arrayList2 = arrayList4;
                        d = d2;
                    } else {
                        c = 16;
                        arrayList2.add(latLng3);
                        d = d4 + d2;
                    }
                    list5 = list3;
                    i2 = i;
                    latLng2 = latLng3;
                    list6 = list4;
                } else {
                    arrayList2.add(latLng3);
                    d = d2;
                }
            } else {
                double d5 = d;
                int i6 = ((int) (d2 / 30.0d)) + 1;
                while (i3 <= i6) {
                    arrayList2.add(e(latLng2, latLng3, b(i3, i6, list5.get(i2))));
                    list2.add(arrayList2);
                    list.add(Long.valueOf(((long) d2) / i6));
                    arrayList2 = new ArrayList(16);
                    i3++;
                    i4 = i4;
                    latLng2 = latLng2;
                }
                i = i4;
                d = d5;
            }
            c = 16;
            list5 = list3;
            i2 = i;
            latLng2 = latLng3;
            list6 = list4;
        }
        if (d + 1.0d > 1.0E-6d) {
            list2.add(arrayList2);
            list.add(Long.valueOf((long) d));
        }
    }

    private void c(hkf hkfVar, int i) {
        ArrayList arrayList = new ArrayList(16);
        ArrayList arrayList2 = new ArrayList(16);
        if (koq.b(this.k, i)) {
            LogUtil.b("Track_SmoothMarkerUtil", "mTimesUtils is out of bounds");
            return;
        }
        List<b> list = this.k.get(i);
        LatLng latLng = hkfVar.c().get(0);
        LatLng latLng2 = hkfVar.c().get(1);
        b bVar = list.get(0);
        double d = d(bVar);
        ArrayList arrayList3 = new ArrayList(16);
        arrayList3.add(latLng);
        if (d <= 30.0d) {
            arrayList3.add(latLng2);
            arrayList2.add(arrayList3);
            arrayList.add(Long.valueOf((long) d));
        } else {
            int i2 = ((int) (d / 30.0d)) + 1;
            for (int i3 = 1; i3 <= i2; i3++) {
                arrayList3.add(e(latLng, latLng2, b(i3, i2, bVar)));
                arrayList2.add(arrayList3);
                arrayList.add(Long.valueOf(((long) d) / i2));
                arrayList3 = new ArrayList(16);
                arrayList3.add(latLng);
            }
        }
        this.m.add(arrayList);
        this.o.add(arrayList2);
    }

    private double d(b bVar) {
        bVar.e = e(bVar.c / this.n) * this.e;
        bVar.b = e((bVar.c + bVar.d) / this.n) * this.e;
        return bVar.b - bVar.e;
    }

    private void e(hkf hkfVar, int i) {
        ArrayList arrayList = new ArrayList(16);
        ArrayList arrayList2 = new ArrayList(16);
        if (koq.b(this.k, i)) {
            LogUtil.b("Track_SmoothMarkerUtil", "mTimesUtils is out of bound");
            return;
        }
        List<Integer> c = c(hkfVar, arrayList, arrayList2, this.k.get(i), hkfVar.c());
        if (koq.b(this.l, i)) {
            LogUtil.b("Track_SmoothMarkerUtil", "preColorsLineAnimation:mPolylines is out of bound");
            return;
        }
        Polyline polyline = this.l.get(i);
        polyline.setOptions(polyline.getOptions().colorValues(c));
        this.m.add(arrayList);
        this.o.add(arrayList2);
    }

    private List<Integer> c(hkf hkfVar, List<Long> list, List<List<LatLng>> list2, List<b> list3, List<LatLng> list4) {
        int i;
        List<List<LatLng>> list5;
        ArrayList arrayList;
        char c;
        List<List<LatLng>> list6 = list2;
        List<b> list7 = list3;
        List<LatLng> list8 = list4;
        ArrayList arrayList2 = new ArrayList(16);
        List<Integer> e = hkfVar.e();
        int i2 = 0;
        arrayList2.add(e.get(0));
        ArrayList arrayList3 = new ArrayList(16);
        arrayList3.add(list8.get(0));
        LatLng latLng = list8.get(0);
        int intValue = e.get(0).intValue();
        double d = -1.0d;
        while (i2 < list4.size() - 1) {
            double d2 = d(list7.get(i2));
            int i3 = i2 + 1;
            List<Integer> list9 = e;
            int intValue2 = e.get(i3).intValue();
            LatLng latLng2 = list8.get(i3);
            double d3 = d + 1.0d;
            if (d3 >= 1.0E-6d || d2 <= 30.0d) {
                i = i3;
                LatLng latLng3 = latLng;
                double d4 = d;
                if (d3 >= 1.0E-6d || d2 > 30.0d) {
                    if (d3 <= 1.0E-6d || d2 <= 30.0d) {
                        list5 = list2;
                        arrayList = arrayList2;
                        if (d3 > 1.0E-6d && d4 + d2 > 30.0d) {
                            list5.add(arrayList3);
                            list.add(Long.valueOf((long) d4));
                            c = 16;
                            ArrayList arrayList4 = new ArrayList(16);
                            arrayList4.add(latLng2);
                            arrayList.add(Integer.valueOf(intValue2));
                            arrayList3 = arrayList4;
                            d = d2;
                        } else {
                            c = 16;
                            arrayList3.add(latLng2);
                            d = d4 + d2;
                            arrayList.add(Integer.valueOf(intValue2));
                        }
                    } else {
                        list5 = list2;
                        list5.add(arrayList3);
                        list.add(Long.valueOf((long) d4));
                        ArrayList arrayList5 = new ArrayList(16);
                        int i4 = ((int) (d2 / 30.0d)) + 1;
                        int i5 = 1;
                        while (i5 <= i4) {
                            double b2 = b(i5, i4, list3.get(i2));
                            arrayList5.add(e(latLng3, latLng2, b2));
                            arrayList2.add(Integer.valueOf(a(intValue, intValue2, b2)));
                            list5.add(arrayList5);
                            list.add(Long.valueOf(((long) d2) / i4));
                            arrayList5 = new ArrayList(16);
                            i5++;
                            arrayList2 = arrayList2;
                            i2 = i2;
                            latLng3 = latLng3;
                        }
                        arrayList = arrayList2;
                        arrayList3 = arrayList5;
                        c = 16;
                        d = -1.0d;
                    }
                    list6 = list5;
                    latLng = latLng2;
                    intValue = intValue2;
                    arrayList2 = arrayList;
                    i2 = i;
                    e = list9;
                    list7 = list3;
                    list8 = list4;
                } else {
                    arrayList3.add(latLng2);
                    arrayList2.add(Integer.valueOf(intValue2));
                    d = d2;
                }
            } else {
                double d5 = d;
                int i6 = ((int) (d2 / 30.0d)) + 1;
                int i7 = 1;
                while (i7 <= i6) {
                    double d6 = d2;
                    double b3 = b(i7, i6, list7.get(i2));
                    arrayList3.add(e(latLng, latLng2, b3));
                    arrayList2.add(Integer.valueOf(a(intValue, intValue2, b3)));
                    list6.add(arrayList3);
                    d2 = d6;
                    list.add(Long.valueOf(((long) d2) / i6));
                    arrayList3 = new ArrayList(16);
                    i7++;
                    list6 = list2;
                    list7 = list3;
                    latLng = latLng;
                    i3 = i3;
                }
                i = i3;
                d = d5;
            }
            list5 = list2;
            arrayList = arrayList2;
            c = 16;
            list6 = list5;
            latLng = latLng2;
            intValue = intValue2;
            arrayList2 = arrayList;
            i2 = i;
            e = list9;
            list7 = list3;
            list8 = list4;
        }
        List<List<LatLng>> list10 = list6;
        ArrayList arrayList6 = arrayList2;
        if (d + 1.0d > 1.0E-6d) {
            list10.add(arrayList3);
            list.add(Long.valueOf((long) d));
        }
        return arrayList6;
    }

    private double b(int i, int i2, b bVar) {
        if (i == i2) {
            return 1.0d;
        }
        return bVar.d < 1.0E-7d ? (i * 1.0d) / i2 : ((d((bVar.e + (((bVar.b - bVar.e) * i) / i2)) / this.e) * this.n) - bVar.c) / bVar.d;
    }

    private LatLng e(LatLng latLng, LatLng latLng2, double d) {
        return new LatLng(latLng.latitude + ((latLng2.latitude - latLng.latitude) * d), latLng.longitude + ((latLng2.longitude - latLng.longitude) * d));
    }

    public void c(BitmapDescriptor bitmapDescriptor) {
        if (bitmapDescriptor == null) {
            return;
        }
        this.h.setIcon(bitmapDescriptor);
    }

    public void b() {
        if (koq.b(this.c)) {
            LogUtil.a("Track_SmoothMarkerUtil", "startSmoothMove data is Empty");
            return;
        }
        this.h.setVisible(true);
        this.f = -1;
        d();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        this.i = 0;
        int i = this.f + 1;
        this.f = i;
        if (i >= this.l.size()) {
            this.h.setVisible(false);
            SmoothMarkerListner smoothMarkerListner = this.g;
            if (smoothMarkerListner != null) {
                smoothMarkerListner.onFinished();
                return;
            }
            return;
        }
        if (this.c.get(this.f).a() == 2) {
            b(1);
        } else {
            b(2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i) {
        if (koq.b(this.m, this.f) || koq.b(this.m.get(this.f), this.i)) {
            LogUtil.b("Track_SmoothMarkerUtil", "mTimeList is out of bound");
        } else {
            this.b.sendEmptyMessageDelayed(i, this.m.get(this.f).get(this.i).longValue());
        }
    }

    static class a extends Handler {

        /* renamed from: a, reason: collision with root package name */
        WeakReference<SmoothMarkerUtil> f3769a;

        a(SmoothMarkerUtil smoothMarkerUtil) {
            super(Looper.getMainLooper());
            this.f3769a = new WeakReference<>(smoothMarkerUtil);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message == null) {
                return;
            }
            super.handleMessage(message);
            SmoothMarkerUtil smoothMarkerUtil = this.f3769a.get();
            if (smoothMarkerUtil == null || smoothMarkerUtil.d) {
                return;
            }
            if (!koq.b(smoothMarkerUtil.l, smoothMarkerUtil.f) && !koq.b(smoothMarkerUtil.o, smoothMarkerUtil.f)) {
                Polyline polyline = (Polyline) smoothMarkerUtil.l.get(smoothMarkerUtil.f);
                List list = (List) smoothMarkerUtil.o.get(smoothMarkerUtil.f);
                int size = list.size();
                List<LatLng> list2 = (List) list.get(smoothMarkerUtil.i);
                LatLng latLng = list2.get(list2.size() - 1);
                int i = message.what;
                if (i == 1) {
                    polyline.setPoints(list2);
                    smoothMarkerUtil.h.setPosition(latLng);
                    if (SmoothMarkerUtil.i(smoothMarkerUtil) >= size) {
                        smoothMarkerUtil.d();
                        return;
                    } else {
                        smoothMarkerUtil.b(1);
                        return;
                    }
                }
                if (i == 2) {
                    if (!koq.b(smoothMarkerUtil.j, smoothMarkerUtil.f)) {
                        List<LatLng> list3 = (List) smoothMarkerUtil.j.get(smoothMarkerUtil.f);
                        list3.addAll(list2);
                        polyline.setPoints(list3);
                        smoothMarkerUtil.h.setPosition(latLng);
                        if (SmoothMarkerUtil.i(smoothMarkerUtil) >= size) {
                            smoothMarkerUtil.d();
                            return;
                        } else {
                            smoothMarkerUtil.b(2);
                            return;
                        }
                    }
                    LogUtil.b("Track_SmoothMarkerUtil", "mPoints is out of bound");
                    return;
                }
                LogUtil.a("Track_SmoothMarkerUtil", "wrong handleMessage type");
                return;
            }
            LogUtil.b("Track_SmoothMarkerUtil", "mPolylines or list is out of bound");
        }
    }

    public void e() {
        this.d = true;
        this.g = null;
        this.b.removeCallbacksAndMessages(null);
        this.g = null;
    }

    public void c() {
        this.d = true;
        this.h.remove();
        Iterator<Polyline> it = this.l.iterator();
        while (it.hasNext()) {
            it.next().remove();
        }
        this.b.removeCallbacksAndMessages(null);
        this.g = null;
    }

    public void e(SmoothMarkerListner smoothMarkerListner) {
        this.g = smoothMarkerListner;
    }

    private double d(double d) {
        return ((float) (Math.cos((d + 1.0d) * 3.141592653589793d) / 2.0d)) + 0.5f;
    }

    private double e(double d) {
        return (float) (((6.283185307179586d - Math.acos((d - 0.5d) * 2.0d)) / 3.141592653589793d) - 1.0d);
    }

    static class b {
        double b;
        double c;
        double d;
        double e;

        b(double d, double d2) {
            this.c = d;
            this.d = d2;
        }
    }

    public List<Polyline> a() {
        return this.l;
    }
}
