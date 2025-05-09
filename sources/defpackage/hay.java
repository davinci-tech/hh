package defpackage;

import com.huawei.healthcloud.plugintrack.trackanimation.gpsutil.LatLong;
import com.huawei.healthcloud.plugintrack.trackanimation.gpsutil.LenLatLong;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/* loaded from: classes4.dex */
public class hay {
    private int e = 60;
    private int b = 60;
    private double c = 0.0d;
    private double d = 0.0d;

    /* renamed from: a, reason: collision with root package name */
    private ArrayList<LenLatLong> f13057a = null;

    public void c(ArrayList<LatLong> arrayList) {
        LogUtil.a("Track_LensTrackProcess", "Go into Handle");
        if (arrayList == null || arrayList.size() < 1) {
            LogUtil.h("Track_LensTrackProcess", "latLongs is null");
            this.f13057a = new ArrayList<>(16);
        } else {
            this.f13057a = e(e(d(e(arrayList, this.e), arrayList, 160.0d), 60.0d, this.b));
            LogUtil.a("Track_LensTrackProcess", "Go out Handle");
        }
    }

    public ArrayList<LenLatLong> e() {
        return this.f13057a;
    }

    public double c() {
        return this.d;
    }

    public hay e(int i) {
        this.e = i;
        return this;
    }

    public hay b(int i) {
        this.b = i;
        return this;
    }

    public hay d(double d) {
        this.c = d * 0.25d;
        return this;
    }

    private ArrayList<LenLatLong> e(ArrayList<LatLong> arrayList, int i) {
        LogUtil.a("Track_LensTrackProcess", "Go into meanFilter");
        if (arrayList == null || arrayList.size() < 2) {
            LogUtil.b("Track_LensTrackProcess", "in meanFilter,the gps data is null");
            return new ArrayList<>(16);
        }
        int i2 = i / 2;
        ArrayList<LenLatLong> arrayList2 = new ArrayList<>(30);
        int i3 = 0;
        arrayList2.add(new LenLatLong(arrayList.get(0)));
        if (arrayList.size() < i2) {
            arrayList2.add(new LenLatLong(arrayList.get(arrayList.size() - 1)));
            return arrayList2;
        }
        LinkedList linkedList = new LinkedList();
        int i4 = 1;
        while (true) {
            if (i4 >= arrayList.size()) {
                i4 = 0;
                break;
            }
            linkedList.offer(arrayList.get(i4));
            if (linkedList.size() == i2) {
                break;
            }
            i4++;
        }
        LinkedList linkedList2 = new LinkedList();
        for (int i5 = i4 + 1; i5 < arrayList.size(); i5++) {
            linkedList2.offer(arrayList.get(i5));
            if (linkedList2.size() == i2) {
                i3 += i2;
                arrayList2.add(a(i2, linkedList, linkedList2, i3));
            }
        }
        arrayList2.add(new LenLatLong(arrayList.get(arrayList.size() - 1)));
        LogUtil.a("Track_LensTrackProcess", "the data size is " + arrayList2.size());
        LogUtil.a("Track_LensTrackProcess", "Go out meanFilter");
        return arrayList2;
    }

    private LenLatLong a(int i, Queue<LatLong> queue, Queue<LatLong> queue2, int i2) {
        LatLong poll;
        double d = 0.0d;
        double d2 = 0.0d;
        double d3 = 0.0d;
        double d4 = 0.0d;
        while (!queue2.isEmpty()) {
            LatLong poll2 = queue2.poll();
            if (poll2 != null) {
                d2 += poll2.getLatLng().b;
                d4 += poll2.getLatLng().d;
                queue.offer(poll2);
                if (queue.size() > i && (poll = queue.poll()) != null) {
                    d += poll.getLatLng().b;
                    d3 += poll.getLatLng().d;
                }
            }
        }
        double d5 = i + i;
        return new LenLatLong((d + d2) / d5, (d3 + d4) / d5, i2);
    }

    private ArrayList<LenLatLong> d(ArrayList<LenLatLong> arrayList, ArrayList<LatLong> arrayList2, double d) {
        LogUtil.a("Track_LensTrackProcess", "Go into smoothCenterPoints");
        if (arrayList == null || arrayList.size() < 2) {
            LogUtil.b("Track_LensTrackProcess", "in smoothCenterPoints,the centerPoint data is null");
            return new ArrayList<>(16);
        }
        ArrayList<LenLatLong> arrayList3 = new ArrayList<>(30);
        int i = 0;
        hau.c(arrayList.get(0), arrayList.get(1), arrayList2);
        if (arrayList.size() > 0) {
            arrayList3.add(arrayList.get(0));
        }
        int i2 = 1;
        while (i2 < arrayList.size() - 1) {
            int i3 = i2 + 1;
            hau.c(arrayList.get(i2), arrayList.get(i3), arrayList2);
            if (Math.abs(180.0d - Math.abs(arrayList.get(i).getAngle() - arrayList.get(i2).getAngle())) >= d) {
                hau.c(arrayList.get(i), arrayList.get(i3), arrayList2);
            } else {
                arrayList3.add(arrayList.get(i2));
                i = i2;
            }
            i2 = i3;
        }
        if (i < arrayList.size()) {
            hau.c(arrayList.get(i), arrayList.get(arrayList.size() - 1), arrayList2);
        }
        arrayList3.add(arrayList.get(arrayList.size() - 1));
        LogUtil.a("Track_LensTrackProcess", "data size is " + arrayList3.size());
        LogUtil.a("Track_LensTrackProcess", "Go out smoothCenterPoints");
        return arrayList3;
    }

    private ArrayList<LenLatLong> e(ArrayList<LenLatLong> arrayList, double d, double d2) {
        int i;
        LogUtil.a("Track_LensTrackProcess", "Go into weakenRotation");
        if (arrayList == null || arrayList.size() < 1) {
            LogUtil.b("Track_LensTrackProcess", "in weakenRotation,the smooth centerPoint data is null");
            return new ArrayList<>(16);
        }
        double angle = arrayList.get(0).getAngle();
        double d3 = 180.0d;
        if (angle >= 180.0d) {
            angle = 360.0d - angle;
        }
        if (angle > 45.0d) {
            arrayList.get(0).setState(-2);
            i = 0;
        } else {
            arrayList.get(0).setState(1);
            i = -1;
        }
        int i2 = 1;
        while (i2 < arrayList.size() - 1) {
            double abs = Math.abs((i >= 0 ? arrayList.get(i).getAngle() : 0.0d) - arrayList.get(i2).getAngle());
            if (abs >= d3) {
                abs = 360.0d - abs;
            }
            int i3 = i2 + 1;
            double abs2 = Math.abs(arrayList.get(i2).getIndex() - arrayList.get(i3).getIndex());
            double distance = arrayList.get(i2).getDistance();
            if ((abs < d || abs2 < d2) && distance <= this.c) {
                arrayList.get(i2).setState(1);
                i2 = i3;
                d3 = 180.0d;
            }
            arrayList.get(i2).setState(-2);
            i = i2;
            i2 = i3;
            d3 = 180.0d;
        }
        arrayList.get(arrayList.size() - 1).setState(0);
        LogUtil.a("Track_LensTrackProcess", "Go out weakenRotation");
        return arrayList;
    }

    private ArrayList<LenLatLong> e(ArrayList<LenLatLong> arrayList) {
        LogUtil.a("Track_LensTrackProcess", "Go into updateViewPoints");
        if (arrayList == null || arrayList.size() < 2) {
            LogUtil.b("Track_LensTrackProcess", "in updateViewPoints,the weaken Rotation data is null");
            return new ArrayList<>(16);
        }
        this.d = 0.0d;
        for (int i = 0; i < arrayList.size() - 1; i++) {
            arrayList.get(i).setIndex(i);
            this.d += arrayList.get(i).getDistance();
        }
        arrayList.get(arrayList.size() - 1).setDistance(0.0d);
        arrayList.get(arrayList.size() - 1).setIndex(arrayList.size() - 1);
        LogUtil.a("Track_LensTrackProcess", "data size is " + arrayList);
        LogUtil.a("Track_LensTrackProcess", "Go out updateViewPoints");
        return arrayList;
    }
}
