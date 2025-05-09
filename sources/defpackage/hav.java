package defpackage;

import android.util.Pair;
import com.huawei.healthcloud.plugintrack.trackanimation.gpsutil.LatLong;
import com.huawei.healthcloud.plugintrack.trackanimation.gpsutil.ReTrackSimplify;
import com.huawei.hwfoundationmodel.trackmodel.MotionPath;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes4.dex */
public class hav {

    /* renamed from: a, reason: collision with root package name */
    private int f13053a;
    private int f;
    private MotionPathSimplify g;
    private MotionPath j;
    private ReTrackSimplify l;
    private Map<Integer, Float> i = null;
    private Map<Integer, Float> h = null;
    private hax e = new hax();
    private hbc d = new hbc();
    private ArrayList<Double> c = null;
    private int b = 0;

    public hav(MotionPath motionPath, MotionPathSimplify motionPathSimplify, ReTrackSimplify reTrackSimplify) {
        this.g = null;
        this.j = null;
        this.l = null;
        this.j = motionPath;
        this.g = motionPathSimplify;
        this.l = reTrackSimplify;
    }

    public hav c(Map<Integer, Float> map) {
        this.i = map;
        return this;
    }

    public hav a(Map<Integer, Float> map) {
        this.h = map;
        return this;
    }

    public ArrayList<LatLong> c() {
        LatLong e;
        LogUtil.a("Track_TrackDataFilter", "go into convertReTrackData");
        MotionPath motionPath = this.j;
        if (motionPath == null || motionPath.requestLbsDataMap() == null || this.g == null) {
            return new ArrayList<>(16);
        }
        this.d.c(this.j).b(this.g);
        ArrayList<Double> i = this.d.i();
        this.c = i;
        if (i == null || i.size() <= 0) {
            return new ArrayList<>(16);
        }
        this.e.d();
        ArrayList<LatLong> arrayList = new ArrayList<>(300);
        Map<Long, double[]> d = gwe.d(this.j.requestLbsDataMap(), this.f);
        this.b = this.j.requestLbsDataMap().size();
        LogUtil.a("Track_TrackDataFilter", "curve data size is " + this.c.size());
        LogUtil.a("Track_TrackDataFilter", "original gps data size is " + d.size());
        int i2 = -1;
        for (Map.Entry<Long, double[]> entry : d.entrySet()) {
            i2++;
            try {
                if (entry.getValue() != null && entry.getValue().length >= 2) {
                    if (hbb.c(entry.getValue()[0], entry.getValue()[1])) {
                        if (arrayList.size() > 0) {
                            arrayList.get(arrayList.size() - 1).setLineStatus(-1);
                        }
                    } else if (!hbb.e(entry.getValue()[0], entry.getValue()[1]) && (e = e(i2, entry)) != null) {
                        this.e.b(e);
                        arrayList.add(e);
                    }
                }
            } catch (IndexOutOfBoundsException unused) {
                LogUtil.h("Track_TrackDataFilter", "IndexOutOfBoundsException");
            }
        }
        if (arrayList.size() > 0 && this.c.size() > 0) {
            LatLong latLong = arrayList.get(arrayList.size() - 1);
            ArrayList<Double> arrayList2 = this.c;
            latLong.setMultiplexField(arrayList2.get(arrayList2.size() - 1).doubleValue());
        }
        LogUtil.a("Track_TrackDataFilter", "go out convertReTrackData");
        return arrayList;
    }

    private LatLong e(int i, Map.Entry<Long, double[]> entry) {
        if (i >= this.c.size() || this.c.get(i) == null) {
            return null;
        }
        return new LatLong(entry.getValue()[0], entry.getValue()[1], (Double.isInfinite(this.c.get(i).doubleValue()) || Double.isNaN(this.c.get(i).doubleValue())) ? 0.0d : this.c.get(i).doubleValue()).setLineStatus(1);
    }

    public void b(int i) {
        ReTrackSimplify reTrackSimplify;
        LogUtil.a("Track_TrackDataFilter", "go into keyDataFilling");
        if (this.g == null || (reTrackSimplify = this.l) == null) {
            LogUtil.h("Track_TrackDataFilter", "Motion Path Simplify data is null");
        } else {
            reTrackSimplify.setMaxBoundary(this.e.a()).setMinBoundary(this.e.e()).setCenterPoint(this.e.b()).setStartTimeStamp(this.g.requestStartTime()).setValidTotalDistance(this.g.requestTotalDistance()).setTotalTime(this.g.requestTotalTime()).setTotalCalories(this.g.requestTotalCalories()).setAvgSpeedField(h()).setMaxHeartRate(aYp_(i)).setMaxAltitude(aYo_(i)).setMaxSpeedField(aYq_(i)).setSportType(this.g.requestSportType()).setCurveType(this.d.d()).setMaxSpeedType(this.d.h()).setAvgSpeedType(this.d.a()).setDeviceId(this.f13053a).setProductId(this.g.requestProductId()).setIsTrackComplete(e());
            LogUtil.a("Track_TrackDataFilter", "go out keyDataFilling");
        }
    }

    public LatLong b() {
        return this.e.a();
    }

    public LatLong d() {
        return this.e.e();
    }

    public double a() {
        return this.e.c();
    }

    private Pair<Integer, Double> aYp_(int i) {
        if (this.d.b().c().intValue() != -1 && this.b - 1 > 0) {
            this.d.b().a(Integer.valueOf((this.d.b().c().intValue() * (i - 1)) / (this.b - 1)));
        }
        return new Pair<>(this.d.b().c(), this.d.b().a());
    }

    private void j() {
        MotionPathSimplify motionPathSimplify = this.g;
        if (motionPathSimplify != null && hji.c(motionPathSimplify.requestMinAltitude(), this.g.requestMaxAltitude())) {
            this.d.e().e(Double.valueOf(this.g.requestMaxAltitude()));
        }
    }

    private Pair<Integer, Double> aYo_(int i) {
        j();
        if (this.d.e().c().intValue() != -1 && this.b - 1 > 0) {
            this.d.e().a(Integer.valueOf((this.d.e().c().intValue() * (i - 1)) / (this.b - 1)));
        }
        if (this.d.e().a().doubleValue() >= 8440.0d) {
            this.d.e().a(-1);
        }
        return new Pair<>(this.d.e().c(), this.d.e().a());
    }

    private Pair<Integer, Double> aYq_(int i) {
        if (this.g == null) {
            LogUtil.h("Track_TrackDataFilter", "data is null");
            return new Pair<>(-1, Double.valueOf(0.0d));
        }
        i();
        if (this.d.c().c().intValue() != -1 && this.b - 1 > 0) {
            this.d.c().a(Integer.valueOf((this.d.c().c().intValue() * (i - 1)) / (this.b - 1)));
        }
        return new Pair<>(this.d.c().c(), this.d.c().a());
    }

    private double h() {
        if (this.g == null) {
            LogUtil.h("Track_TrackDataFilter", "data is null");
            return -1.0d;
        }
        if (this.d.a() == 18) {
            return this.g.requestAvgStepRate();
        }
        if (this.d.a() == 16) {
            return this.g.requestAvgPace();
        }
        return 3600.0d / this.g.requestAvgPace();
    }

    private boolean e() {
        Map<Integer, Float> map = this.h;
        if (map == null) {
            LogUtil.c("Track_TrackDataFilter", "paceMap is null");
            return true;
        }
        Iterator<Map.Entry<Integer, Float>> it = map.entrySet().iterator();
        boolean z = true;
        boolean z2 = true;
        int i = 0;
        int i2 = 0;
        while (it.hasNext()) {
            int intValue = it.next().getKey().intValue();
            if (intValue >= 100000) {
                int i3 = intValue / 100000;
                int i4 = intValue % 100000;
                if (i4 != i) {
                    z = true;
                } else if (i3 > i2 + 20) {
                    z = false;
                }
                if (z2 && i3 >= 100) {
                    if (i4 == 0) {
                        return false;
                    }
                    z2 = false;
                }
                i = i4;
                i2 = i3;
            }
        }
        return z;
    }

    private void i() {
        Map<Integer, Float> map = this.i;
        if (map == null || map.size() < 1) {
            if (this.d.h() == 16) {
                if (this.d.c().a().doubleValue() < 1.0E-6d) {
                    this.d.c().e(Double.valueOf((this.g.requestTotalTime() * 1.0d) / this.g.requestTotalDistance()));
                    return;
                } else {
                    this.d.c().e(Double.valueOf(3600.0d / this.d.c().a().doubleValue()));
                    return;
                }
            }
            return;
        }
        Float[] e = gvv.e(this.i);
        if (this.d.h() == 17 && this.d.f() == -1) {
            if (e[0].floatValue() < 1.0E-6d) {
                this.d.c().e(Double.valueOf((this.g.requestTotalTime() * 1.0d) / this.g.requestTotalDistance()));
            } else {
                double floatValue = 3600.0d / e[0].floatValue();
                if (UnitUtil.h()) {
                    floatValue = UnitUtil.d(floatValue, 3);
                }
                this.d.c().e(Double.valueOf(floatValue));
            }
        }
        if (this.d.h() == 16) {
            this.d.c().e(Double.valueOf(e[0].floatValue()));
        }
    }

    public hav e(int i) {
        this.f13053a = i;
        return this;
    }

    public hav a(int i) {
        this.f = i;
        return this;
    }
}
