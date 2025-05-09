package defpackage;

import android.util.Pair;
import com.huawei.healthcloud.plugintrack.trackanimation.gpsutil.LatLong;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.LinkedList;

/* loaded from: classes4.dex */
public class haw {
    private e c;
    private e d;
    private ArrayList<LatLong> b = null;

    /* renamed from: a, reason: collision with root package name */
    private double f13054a = 0.0d;
    private double h = 1.0d;
    private int e = 0;

    public haw() {
        this.d = new e();
        this.c = new e();
    }

    public void c() {
        LogUtil.a("Track_MotionTrackProcess", "Go into handle");
        ArrayList<LatLong> arrayList = this.b;
        if (arrayList == null) {
            LogUtil.h("Track_MotionTrackProcess", "GPS Data is null");
            this.b = new ArrayList<>(16);
            return;
        }
        ArrayList<LatLong> b = b(arrayList);
        this.b = b;
        ArrayList<LatLong> c = c(b);
        this.b = c;
        ArrayList<LatLong> a2 = a(c);
        this.b = a2;
        this.b = e(a2);
        LogUtil.a("Track_MotionTrackProcess", "Go out handle");
    }

    public ArrayList<LatLong> e() {
        return this.b;
    }

    public double b() {
        return this.f13054a;
    }

    public haw d(ArrayList<LatLong> arrayList) {
        this.b = arrayList;
        return this;
    }

    public haw a(double d) {
        this.h = d;
        return this;
    }

    public haw a(int i) {
        this.e = i;
        return this;
    }

    public Pair<Integer, Double> aYl_() {
        return new Pair<>(Integer.valueOf(this.d.b), Double.valueOf(this.d.e));
    }

    public Pair<Integer, Double> aYm_() {
        return new Pair<>(Integer.valueOf(this.d.d), Double.valueOf(this.d.f13055a));
    }

    private ArrayList<LatLong> b(ArrayList<LatLong> arrayList) {
        String str;
        int i;
        int i2;
        ArrayList<LatLong> arrayList2 = arrayList;
        String str2 = "Track_MotionTrackProcess";
        LogUtil.a("Track_MotionTrackProcess", "Go into gpsDataUpSample");
        ArrayList<LatLong> arrayList3 = new ArrayList<>(200);
        this.c = new e();
        if (arrayList2 == null || arrayList.size() < 2) {
            LogUtil.h("Track_MotionTrackProcess", "GPS Data is null");
            return arrayList3;
        }
        arrayList3.add(arrayList2.get(0));
        int i3 = 1;
        this.c.e(arrayList3.size() - 1, arrayList3.get(arrayList3.size() - 1).getMultiplexField());
        this.f13054a = 0.0d;
        LatLong latLong = arrayList2.get(0);
        arrayList2.get(0);
        int i4 = 1;
        while (i4 < arrayList.size()) {
            LatLong latLong2 = arrayList2.get(i4);
            hau.e(latLong, latLong2);
            if (latLong.getDistance() < this.h) {
                latLong.setLineStatus(latLong2.getLineStatus());
                str = str2;
                i = i4;
                i2 = i3;
            } else {
                this.f13054a += latLong.getDistance();
                int distance = (int) (latLong.getDistance() / this.h);
                double d = distance;
                double d2 = (latLong2.getLatLng().b - latLong.getLatLng().b) / d;
                double d3 = (latLong2.getLatLng().d - latLong.getLatLng().d) / d;
                double multiplexField = (latLong2.getMultiplexField() - latLong.getMultiplexField()) / d;
                int i5 = distance;
                int i6 = 1;
                while (i6 < i5) {
                    int i7 = i4;
                    double d4 = i6;
                    arrayList3.add(new LatLong(latLong.getLatLng().b + (d2 * d4), latLong.getLatLng().d + (d3 * d4), latLong.getMultiplexField() + (d4 * multiplexField)).setLineStatus(latLong.getLineStatus()));
                    this.c.e(arrayList3.size() - 1, arrayList3.get(arrayList3.size() - 1).getMultiplexField());
                    i6++;
                    i5 = i5;
                    str2 = str2;
                    i4 = i7;
                    latLong2 = latLong2;
                    d = d;
                }
                str = str2;
                i = i4;
                double d5 = d;
                LatLong lineStatus = new LatLong(latLong.getLatLng().b + (d2 * d5), latLong.getLatLng().d + (d3 * d5), latLong.getMultiplexField() + (multiplexField * d5)).setLineStatus(latLong2.getLineStatus());
                arrayList3.add(lineStatus);
                i2 = 1;
                this.c.e(arrayList3.size() - 1, arrayList3.get(arrayList3.size() - 1).getMultiplexField());
                latLong = lineStatus;
            }
            i3 = i2;
            str2 = str;
            i4 = i + 1;
            arrayList2 = arrayList;
        }
        Object[] objArr = new Object[i3];
        objArr[0] = "Go out gpsDataUpSample";
        LogUtil.a(str2, objArr);
        return arrayList3;
    }

    private ArrayList<LatLong> c(ArrayList<LatLong> arrayList) {
        haw hawVar = this;
        ArrayList<LatLong> arrayList2 = arrayList;
        String str = "Track_MotionTrackProcess";
        LogUtil.a("Track_MotionTrackProcess", "Go into gpsDataDownSample");
        if (arrayList2 != null) {
            int i = 1;
            if (arrayList.size() >= 1) {
                int size = arrayList.size();
                LogUtil.a("Track_MotionTrackProcess", "original data size is " + size);
                if (size < hawVar.e) {
                    return arrayList2;
                }
                int i2 = size - 1;
                ArrayList<LatLong> arrayList3 = new ArrayList<>(200);
                arrayList3.add(arrayList2.get(0));
                double d = (i2 * 1.0d) / (r5 - 1);
                double d2 = 0.0d;
                int i3 = 1;
                while (i3 < hawVar.e - i) {
                    double d3 = d2 + d;
                    int i4 = (int) d3;
                    int i5 = i4 + 1;
                    double d4 = d;
                    int i6 = i2;
                    double d5 = d3 - i4;
                    arrayList3.add(new LatLong(arrayList2.get(i4).getLatLng().b + ((arrayList2.get(i5).getLatLng().b - arrayList2.get(i4).getLatLng().b) * d5), arrayList2.get(i4).getLatLng().d + ((arrayList2.get(i5).getLatLng().d - arrayList2.get(i4).getLatLng().d) * d5), arrayList.get(i4).getMultiplexField() + ((arrayList2.get(i5).getMultiplexField() - arrayList2.get(i4).getMultiplexField()) * d5)).setLineStatus(arrayList.get(i4).getLineStatus()));
                    i3++;
                    arrayList2 = arrayList;
                    d = d4;
                    str = str;
                    i2 = i6;
                    d2 = d3;
                    i = 1;
                    hawVar = this;
                }
                String str2 = str;
                arrayList3.add(arrayList2.get(i2));
                e eVar = this.c;
                eVar.b = (eVar.b * (arrayList3.size() - 1)) / (arrayList.size() - 1);
                if (this.c.b >= 0 && this.c.b < arrayList3.size()) {
                    arrayList3.get(this.c.b).setMultiplexField(this.c.e);
                }
                e eVar2 = this.c;
                eVar2.d = (eVar2.d * (arrayList3.size() - 1)) / (arrayList.size() - 1);
                LogUtil.a(str2, "data size is " + arrayList3.size());
                LogUtil.a(str2, "Go out gpsDataDownSample");
                return arrayList3;
            }
        }
        LogUtil.h("Track_MotionTrackProcess", "GPS Down Sample Data is null");
        return new ArrayList<>(16);
    }

    private ArrayList<LatLong> a(ArrayList<LatLong> arrayList) {
        LogUtil.a("Track_MotionTrackProcess", "go into gpsBearAnalysis");
        if (arrayList == null || arrayList.size() < 1) {
            LogUtil.h("Track_MotionTrackProcess", "up sample data is null");
            return new ArrayList<>(16);
        }
        int i = 0;
        while (i < arrayList.size() - 1) {
            arrayList.get(i).setIndex(i);
            LatLong latLong = arrayList.get(i);
            i++;
            hau.e(latLong, arrayList.get(i));
        }
        arrayList.get(arrayList.size() - 1).setIndex(arrayList.size() - 1);
        arrayList.get(arrayList.size() - 1).setAngle(0.0d);
        arrayList.get(arrayList.size() - 1).setDistance(0.0d);
        arrayList.get(arrayList.size() - 1).setLineStatus(1);
        LogUtil.a("Track_MotionTrackProcess", "go out gpsBearAnalysis");
        return arrayList;
    }

    private ArrayList<LatLong> e(ArrayList<LatLong> arrayList) {
        double multiplexField;
        double multiplexField2;
        LogUtil.a("Track_MotionTrackProcess", "go into smoothMultiplexField");
        if (arrayList != null) {
            int i = 1;
            if (arrayList.size() >= 1) {
                LinkedList linkedList = new LinkedList();
                hbd hbdVar = new hbd();
                double d = 0.0d;
                for (int i2 = -3; i2 <= 3; i2++) {
                    if (i2 < 0) {
                        multiplexField2 = arrayList.get(0).getMultiplexField();
                    } else if (i2 >= arrayList.size()) {
                        multiplexField2 = arrayList.get(arrayList.size() - 1).getMultiplexField();
                    } else {
                        multiplexField2 = arrayList.get(i2).getMultiplexField();
                    }
                    double a2 = hbdVar.a(multiplexField2);
                    d += a2;
                    linkedList.offer(Double.valueOf(a2));
                }
                double d2 = 7;
                e(arrayList, 0, d / d2);
                this.d.e(0, arrayList.get(0).getMultiplexField());
                int i3 = 4;
                while (i3 < arrayList.size() + 3) {
                    if (i3 >= arrayList.size()) {
                        multiplexField = arrayList.get(arrayList.size() - i).getMultiplexField();
                    } else {
                        multiplexField = arrayList.get(i3).getMultiplexField();
                    }
                    double a3 = hbdVar.a(multiplexField);
                    d = (d - ((Double) linkedList.remove()).doubleValue()) + a3;
                    linkedList.offer(Double.valueOf(a3));
                    int i4 = i3 - 3;
                    e(arrayList, i4, d / d2);
                    this.d.e(i4, arrayList.get(i4).getMultiplexField());
                    i3++;
                    hbdVar = hbdVar;
                    i = 1;
                }
                LogUtil.a("Track_MotionTrackProcess", "data size is " + arrayList.size());
                LogUtil.a("Track_MotionTrackProcess", "go into smoothMultiplexField");
                return arrayList;
            }
        }
        LogUtil.h("Track_MotionTrackProcess", "gps data is null");
        return new ArrayList<>(16);
    }

    private void e(ArrayList<LatLong> arrayList, int i, double d) {
        int i2 = this.c.b;
        if (i2 < 0 || i2 >= arrayList.size()) {
            arrayList.get(i).setMultiplexField(d);
            return;
        }
        double multiplexField = arrayList.get(i).getMultiplexField();
        if (i < i2 - 3 || i > i2 + 3 || Double.isNaN(multiplexField) || Double.isInfinite(multiplexField)) {
            arrayList.get(i).setMultiplexField(d);
        }
    }

    static class e {

        /* renamed from: a, reason: collision with root package name */
        private double f13055a;
        private int b;
        private int d;
        private double e;

        private e() {
            this.b = 0;
            this.e = -1.7976931348623157E308d;
            this.d = 0;
            this.f13055a = Double.MAX_VALUE;
        }

        public void e(int i, double d) {
            if (d > this.e) {
                this.b = i;
                this.e = d;
            }
            if (d < this.f13055a) {
                this.d = i;
                this.f13055a = d;
            }
        }
    }
}
