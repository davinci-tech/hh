package defpackage;

import android.os.SystemClock;
import com.huawei.healthcloud.plugintrack.manager.inteface.ISportDistanceTimeCallback;
import com.huawei.healthcloud.plugintrack.manager.inteface.ISportStateChangeListener;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.operation.OpAnalyticsConstants;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Map;
import java.util.TreeMap;

/* loaded from: classes4.dex */
public class gvp implements ISportStateChangeListener, ISportDistanceTimeCallback {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f12962a = new Object();
    private Map<Double, Double> q = new TreeMap();
    private Map<Double, Double> c = new TreeMap();
    private Map<Integer, Float> x = new TreeMap();
    private Map<Integer, Float> ad = new TreeMap();
    private Map<Integer, Float> d = new TreeMap();
    private Map<Integer, Float> aa = new TreeMap();
    private Map<Integer, Float> y = new TreeMap();
    private Map<Integer, Float> ab = new TreeMap();
    private Map<Integer, Float> u = new TreeMap();
    private Map<Integer, Float> ac = new TreeMap();
    private long p = 0;
    private long e = 0;
    private long b = 0;
    private long j = 0;
    private long t = 0;
    private long o = 0;
    private long v = 0;
    private long l = 0;
    private long r = 0;
    private int m = 0;
    private int s = 0;
    private long w = 0;
    private float g = 0.3f;
    private boolean k = false;
    private boolean n = false;
    private long i = 0;
    private gvn f = new gvn();
    private gtr h = null;

    @Override // com.huawei.healthcloud.plugintrack.manager.inteface.ISportStateChangeListener, com.huawei.health.sportservice.SportLifecycle
    public void onStartSport() {
    }

    @Override // com.huawei.healthcloud.plugintrack.manager.inteface.ISportStateChangeListener, com.huawei.health.sportservice.SportLifecycle
    /* renamed from: onStopSport */
    public void m134x32b3e3a1() {
    }

    public void c(int i, int i2, boolean z) {
        if (this.l == 0) {
            long currentTimeMillis = System.currentTimeMillis();
            this.l = currentTimeMillis;
            this.t = currentTimeMillis - (this.i - (((long) b(0, this.q.size())) * 1000));
            this.o = this.l - (this.i - (((long) b(1, this.c.size())) * 1000));
            ReleaseLogUtil.d("Track_PaceManager", "No mLastLocatedTime yet , recovery it");
        }
        synchronized (f12962a) {
            TreeMap treeMap = new TreeMap();
            TreeMap treeMap2 = new TreeMap();
            TreeMap treeMap3 = new TreeMap();
            TreeMap treeMap4 = new TreeMap();
            if (z) {
                e(this.x, treeMap);
                e(this.ad, treeMap2);
                e(this.d, treeMap3);
                e(this.aa, treeMap4);
                d(i, this.l, i2);
                e(this.x, this.y);
                e(this.ad, this.ab);
                e(this.d, this.u);
                e(this.aa, this.ac);
                e(treeMap, this.x);
                e(treeMap2, this.ad);
                e(treeMap3, this.d);
                e(treeMap4, this.aa);
            } else {
                d(i, this.l, i2);
            }
        }
    }

    public boolean e(boolean z) {
        return !z || System.currentTimeMillis() - this.v > OpAnalyticsConstants.H5_LOADING_DELAY;
    }

    private void e(Map<Integer, Float> map, Map<Integer, Float> map2) {
        map2.clear();
        map2.putAll(map);
    }

    public void c(Map<Integer, Float> map, Map<Integer, Float> map2) {
        LogUtil.c("Track_PaceManager", this.x, " ", Long.valueOf(this.l));
        if (map != null) {
            this.x.clear();
            this.x.putAll(map);
            LogUtil.a("Track_PaceManager", "recoveryPaceMap paceMap");
        }
        if (map2 != null) {
            this.d.clear();
            this.d.putAll(map2);
            LogUtil.a("Track_PaceManager", "recoveryPaceMap mBritishPaceMap");
        }
        this.v = System.currentTimeMillis();
    }

    public void b(Map<Double, Double> map, Map<Double, Double> map2) {
        Map<Double, Double> map3 = this.q;
        if (map3 == null || this.c == null) {
            return;
        }
        if (map != null) {
            map3.clear();
            this.q.putAll(map);
            LogUtil.a("Track_PaceManager", "recoveryPartTimeMap mNormalPartTimeMap");
        }
        if (map2 != null) {
            this.c.clear();
            this.c.putAll(map2);
            LogUtil.a("Track_PaceManager", "recoveryPartTimeMap mBritishPartTimeMap size");
        }
        if (this.q.containsKey(Double.valueOf(42.195d)) && this.q.containsKey(Double.valueOf(21.0975d))) {
            this.m = this.q.size() - 2;
            this.s = this.c.size() - 2;
            this.n = true;
            this.k = true;
            return;
        }
        if (this.q.containsKey(Double.valueOf(21.0975d))) {
            this.m = this.q.size() - 1;
            this.s = this.c.size() - 1;
            this.k = true;
            this.n = false;
            return;
        }
        this.m = this.q.size();
        this.s = this.c.size();
        this.k = false;
        this.n = false;
    }

    public void d(long j) {
        if (j > 0) {
            this.i = j;
        }
        LogUtil.a("Track_PaceManager", "mCrashSportDuration = ", Long.valueOf(this.i));
    }

    private void e(double d, double d2) {
        LogUtil.a("Track_PaceManager", "before updateNormalPartTimeMap");
        if (this.q == null) {
            this.q = new TreeMap();
        }
        if (d != 21.0975d && d != 42.195d) {
            double d3 = this.m;
            double d4 = d - d3;
            if (d4 > 1.0d) {
                double b = b(0, d3);
                int i = this.m;
                double d5 = (d2 - b) / (d - i);
                double d6 = b + d5;
                while (true) {
                    i++;
                    double d7 = i;
                    if (d7 > d) {
                        break;
                    }
                    this.q.put(Double.valueOf(d7), Double.valueOf(d6));
                    d6 += d5;
                    this.m++;
                }
            } else if (d4 == 1.0d) {
                this.q.put(Double.valueOf(d), Double.valueOf(d2));
                this.m++;
            } else {
                LogUtil.a("Track_PaceManager", "updateNormalPartTimeMap less than 1km");
            }
        } else {
            this.q.put(Double.valueOf(d), Double.valueOf(d2));
        }
        LogUtil.a("Track_PaceManager", "after updateNormalPartTimeMap");
    }

    private void d(double d, double d2) {
        LogUtil.a("Track_PaceManager", "before updateBritishPartTimeMap");
        if (this.c == null) {
            this.c = new TreeMap();
        }
        if (d != 13.1099865d && d != 26.219973d) {
            double d3 = this.s;
            double d4 = d - d3;
            if (d4 > 1.0d) {
                double b = b(1, d3);
                int i = this.s;
                double d5 = d2 - (b / (d - i));
                double d6 = b + d5;
                int i2 = i + 1;
                while (true) {
                    double d7 = i2;
                    if (d7 > d) {
                        break;
                    }
                    this.c.put(Double.valueOf(d7), Double.valueOf(d6));
                    d6 += d5;
                    this.s++;
                    i2++;
                }
            } else if (d4 == 1.0d) {
                this.c.put(Double.valueOf(d), Double.valueOf(d2));
                this.s++;
            } else {
                LogUtil.a("Track_PaceManager", "updateBritishPartTimeMap less than 1km");
            }
        } else {
            this.c.put(Double.valueOf(d), Double.valueOf(d2));
        }
        LogUtil.a("Track_PaceManager", "after updateBritishPartTimeMap");
    }

    private void e(int i, float f, int i2) {
        synchronized (f12962a) {
            ReleaseLogUtil.e("Track_PaceManager", "before updatePaceMap", this.x, " duration=", Float.valueOf(f));
            int size = this.x.size();
            int i3 = i - size;
            if (i3 > 1) {
                float f2 = f / i3;
                for (int i4 = size + 1; i4 <= i; i4++) {
                    int b = b(i4 * 1000, i2);
                    if (b != -1) {
                        this.x.put(Integer.valueOf(b), Float.valueOf(f2));
                        this.ad.put(Integer.valueOf(b), Float.valueOf(f2));
                    }
                }
            } else if (i3 == 1) {
                int b2 = b(i * 1000, i2);
                if (b2 != -1) {
                    this.x.put(Integer.valueOf(b2), Float.valueOf(f));
                    this.ad.put(Integer.valueOf(b2), Float.valueOf(f));
                }
            } else {
                LogUtil.a("Track_PaceManager", "updatePaceMap less than 1km");
            }
            LogUtil.a("Track_PaceManager", "after updatePaceMap");
        }
    }

    private void d(int i, float f, int i2) {
        synchronized (f12962a) {
            LogUtil.a("Track_PaceManager", "before updateBritishPaceMap", Integer.valueOf(this.d.size()), " duration=", Long.valueOf((long) f));
            int size = this.d.size();
            int i3 = i - size;
            if (i3 > 1) {
                float f2 = f / i3;
                for (int i4 = size + 1; i4 <= i; i4++) {
                    int b = b(i4 * 1000, i2);
                    if (b != -1) {
                        this.d.put(Integer.valueOf(b), Float.valueOf(f2));
                        this.aa.put(Integer.valueOf(b), Float.valueOf(f2));
                    }
                }
            } else if (i3 == 1) {
                int b2 = b(i * 1000, i2);
                if (b2 != -1) {
                    this.d.put(Integer.valueOf(b2), Float.valueOf(f));
                    this.aa.put(Integer.valueOf(b2), Float.valueOf(f));
                }
            } else {
                LogUtil.a("Track_PaceManager", "updateBritishPaceMap less than 1km");
            }
            LogUtil.a("Track_PaceManager", "after updateBritishPaceMap");
        }
    }

    private void d(int i, long j, int i2) {
        LogUtil.a("Track_PaceManager", "before addZeroPace");
        int i3 = i % 10;
        int i4 = i3 >= 5 ? (i + 10) - i3 : i;
        int b = b(i4, i2);
        if (b != -1) {
            float size = i4 - (this.x.size() * 1000);
            float a2 = (float) gwg.a((j - this.t) - this.p);
            if (size >= 1.0f && a2 > 0.0f) {
                float a3 = size < 5.0f ? (a(this.m, 0) / 1000) + a2 : a2 / (size / 1000.0f);
                this.x.put(Integer.valueOf(b), Float.valueOf(a3));
                this.ad.put(Integer.valueOf(b), Float.valueOf(a3));
            }
        }
        int g = (int) gwg.g(i);
        int i5 = g % 10;
        if (i5 >= 5) {
            g = (g + 10) - i5;
        }
        int b2 = b(g, i2);
        if (b2 != -1) {
            float size2 = g - (this.d.size() * 1000);
            float a4 = (float) gwg.a((j - this.o) - this.e);
            if (size2 >= 1.0f && a4 > 0.0f) {
                float a5 = size2 < 5.0f ? (a(this.s, 1) / 1000) + a4 : a4 / (size2 / 1000.0f);
                this.d.put(Integer.valueOf(b2), Float.valueOf(a5));
                this.aa.put(Integer.valueOf(b2), Float.valueOf(a5));
            }
        }
        d(i, j);
        e(i, j);
        LogUtil.a("Track_PaceManager", "after addBritishZeroPace");
    }

    public long b(int i) {
        if (UnitUtil.h()) {
            return a((int) UnitUtil.e((i * 1.0d) / 1000.0d, 3), 1);
        }
        return a(i / 1000, 0);
    }

    public long a(int i, int i2) {
        double doubleValue;
        double doubleValue2;
        double doubleValue3;
        if (i2 == 1) {
            if (i > this.s) {
                LogUtil.h("Track_PaceManager", "The ", Integer.valueOf(i), "miles is not update yet");
                return 1000L;
            }
            Map<Double, Double> map = this.c;
            if (map != null && map.size() > 0) {
                if (this.c.size() == 1) {
                    doubleValue3 = this.c.get(Double.valueOf(i)).doubleValue();
                } else {
                    doubleValue = this.c.get(Double.valueOf(i)).doubleValue();
                    doubleValue2 = this.c.get(Double.valueOf(i - 1)).doubleValue();
                    doubleValue3 = doubleValue - doubleValue2;
                }
            }
            doubleValue3 = 0.0d;
        } else {
            if (i > this.m) {
                LogUtil.h("Track_PaceManager", "The ", Integer.valueOf(i), "kilos is not update yet");
                return 1000L;
            }
            Map<Double, Double> map2 = this.q;
            if (map2 != null && map2.size() > 0) {
                if (this.q.size() == 1) {
                    doubleValue3 = this.q.get(Double.valueOf(i)).doubleValue();
                } else {
                    doubleValue = this.q.get(Double.valueOf(i)).doubleValue();
                    doubleValue2 = this.q.get(Double.valueOf(i - 1)).doubleValue();
                    doubleValue3 = doubleValue - doubleValue2;
                }
            }
            doubleValue3 = 0.0d;
        }
        if (doubleValue3 < 1.0d) {
            return 1000L;
        }
        return (long) (doubleValue3 * 1000.0d);
    }

    public Map<Double, Double> i() {
        TreeMap treeMap;
        synchronized (f12962a) {
            treeMap = new TreeMap(this.q);
        }
        return treeMap;
    }

    public void d(Map<Double, Double> map) {
        this.q = map;
    }

    public Map<Double, Double> e() {
        TreeMap treeMap;
        synchronized (f12962a) {
            treeMap = new TreeMap(this.c);
        }
        return treeMap;
    }

    public void a(Map<Double, Double> map) {
        this.c = map;
    }

    public Map<Integer, Float> f() {
        TreeMap treeMap;
        synchronized (f12962a) {
            treeMap = new TreeMap(this.x);
        }
        return treeMap;
    }

    public void e(Map<Integer, Float> map) {
        this.x = map;
    }

    public void b(Map<Integer, Float> map) {
        this.d = map;
    }

    public Map<Integer, Float> c() {
        TreeMap treeMap;
        synchronized (f12962a) {
            treeMap = new TreeMap(this.d);
        }
        return treeMap;
    }

    public Map<Integer, Float> h() {
        TreeMap treeMap;
        synchronized (f12962a) {
            treeMap = new TreeMap(this.y);
        }
        return treeMap;
    }

    public Map<Integer, Float> j() {
        TreeMap treeMap;
        synchronized (f12962a) {
            treeMap = new TreeMap(this.u);
        }
        return treeMap;
    }

    public Map<Integer, Float> a(boolean z) {
        TreeMap treeMap = new TreeMap();
        Map<Integer, Float> map = this.ad;
        if (map != null) {
            treeMap.putAll(map);
            if (z) {
                this.ad.clear();
            }
        }
        return treeMap;
    }

    public Map<Integer, Float> b(boolean z) {
        TreeMap treeMap = new TreeMap();
        Map<Integer, Float> map = this.aa;
        if (map != null) {
            treeMap.putAll(map);
            if (z) {
                this.aa.clear();
            }
        }
        return treeMap;
    }

    private void a(double d, double d2) {
        if (d >= 21.0975d && !this.k) {
            LogUtil.a("Track_PaceManager", "update partTimeMap HALF_MARATHON distance: ", Integer.valueOf((int) d), " duration: ", Long.valueOf((long) d2));
            e(21.0975d, d2);
            d(13.1099865d, d2);
            this.k = true;
            return;
        }
        if (d < 42.195d || this.n) {
            return;
        }
        LogUtil.a("Track_PaceManager", "update partTimeMap FULL_MARATHON distance: ", Integer.valueOf((int) d), " duration: ", Long.valueOf((long) d2));
        e(42.195d, d2);
        d(26.219973d, d2);
        this.n = true;
    }

    private double b(int i, double d) {
        Map<Double, Double> map;
        Map<Double, Double> map2;
        double doubleValue = (i == 0 && (map2 = this.q) != null && map2.containsKey(Double.valueOf(d))) ? this.q.get(Double.valueOf(d)).doubleValue() : 0.0d;
        return (i == 1 && (map = this.c) != null && map.containsKey(Double.valueOf(d))) ? this.c.get(Double.valueOf(d)).doubleValue() : doubleValue;
    }

    private static int b(int i, int i2) {
        if (i / 1000 > 214) {
            ReleaseLogUtil.c("Track_PaceManager", " pace", " getPaceIndex UNIT_TYPE_NORMAL over pace value max! dis:", Integer.valueOf(i));
            return -1;
        }
        int i3 = i / 10;
        if (i2 == 0) {
            i2 = 0;
        }
        if (i2 <= 60000) {
            return (i3 * 100000) + i2;
        }
        LogUtil.b("Track_PaceManager", " pace", " updatePacesData over point value max! point:", Integer.valueOf(i2));
        return -1;
    }

    @Override // com.huawei.healthcloud.plugintrack.manager.inteface.ISportDistanceTimeCallback
    public void onDistanceChange(int i, long j, int i2) {
        this.f.d(i, j);
        if (c(j)) {
            this.r = j;
            return;
        }
        if (j - this.r > 8000) {
            ReleaseLogUtil.e("Track_PaceManager", "distance=", Integer.valueOf(i), " time=", Long.valueOf(j), " location=", Integer.valueOf(i2));
            this.r = j;
        }
        this.l = j;
        m();
        if (gwg.d(i) >= this.m + 1) {
            double a2 = gwg.a((j - this.t) - this.p);
            int d = gwg.d(i);
            double e = e(i / 1000.0f, this.m, a2, 0);
            e(d, b(0, this.m) + e);
            e(d, (float) e, i2);
            this.t = j - ((long) ((a2 - e) * 1000.0d));
            this.p = 0L;
        }
        if (gwg.b(i) >= this.s + 1) {
            double a3 = gwg.a((j - this.o) - this.e);
            double e2 = e(((float) gwg.g(i)) / 1000.0f, this.s, a3, 1);
            d(gwg.b(i), b(1, this.s) + e2);
            d(gwg.b(i), (float) e2, i2);
            this.o = j - ((long) ((a3 - e2) * 1000.0d));
            this.e = 0L;
        }
        if (this.k && this.n) {
            return;
        }
        a(i / 1000.0d, gwg.a((j - this.t) - this.p) + b(0, i / 1000));
    }

    private void d(int i, long j) {
        if (gwg.d(i) >= this.m + 1) {
            double a2 = gwg.a((j - this.t) - this.p);
            int d = gwg.d(i);
            double e = e(i / 1000.0f, this.m, a2, 0);
            e(d, b(0, this.m) + e);
            this.t = j - ((long) ((a2 - e) * 1000.0d));
            this.p = 0L;
        }
    }

    private void e(int i, long j) {
        if (gwg.b(i) >= this.s + 1) {
            double a2 = gwg.a((j - this.o) - this.e);
            double e = e(((float) gwg.g(i)) / 1000.0f, this.s, a2, 1);
            d(gwg.b(i), b(1, this.s) + e);
            this.o = j - ((long) ((a2 - e) * 1000.0d));
            this.e = 0L;
        }
    }

    private double e(float f, float f2, double d, int i) {
        double d2;
        if (d < 1.0d) {
            LogUtil.h("Track_PaceManager", "correctSportTime lastKiloTime < 1");
            d2 = a((int) f2, i) / 1000.0d;
            LogUtil.a("Track_PaceManager", "correctSportTime lastKiloTime is correct to ", Long.valueOf((long) d2));
        } else {
            d2 = d;
        }
        if (f - ((int) f) > this.g) {
            float f3 = f - f2;
            if (f3 > 0.0f) {
                d2 = (d2 * (r15 - f2)) / f3;
            }
        }
        LogUtil.a("Track_PaceManager", "correctSportTime: curDistance = ", Float.valueOf(f), " lastKiloTime = ", Double.valueOf(d), " duration = ", Double.valueOf(d2));
        return d2;
    }

    private boolean c(long j) {
        boolean z = false;
        if (this.j == 0) {
            this.j = j;
            boolean z2 = true;
            if (this.d.size() > 0) {
                this.o = Math.min(j - (this.i - ((long) (b(1, this.s) * 1000.0d))), j);
                z2 = false;
            } else {
                this.o = j - this.i;
            }
            if (this.x.size() > 0) {
                this.t = Math.min(j - (this.i - ((long) (b(0, this.m) * 1000.0d))), j);
            } else {
                this.t = j - this.i;
                z = z2;
            }
            LogUtil.a("Track_PaceManager", "CurTime = ", Long.valueOf(j), " lastNormalPartTime = ", Long.valueOf(this.t), " lastBritishPartTime = ", Long.valueOf(this.o));
        }
        return z;
    }

    @Override // com.huawei.healthcloud.plugintrack.manager.inteface.ISportStateChangeListener, com.huawei.health.sportservice.SportLifecycle
    public void onPauseSport() {
        this.w = SystemClock.elapsedRealtime();
    }

    @Override // com.huawei.healthcloud.plugintrack.manager.inteface.ISportStateChangeListener, com.huawei.health.sportservice.SportLifecycle
    public void onResumeSport() {
        if (this.t == 0) {
            ReleaseLogUtil.e("Track_PaceManager", "Pause-Resume before located, don't calculate the rest time");
            return;
        }
        long elapsedRealtime = SystemClock.elapsedRealtime() - this.w;
        LogUtil.a("Track_PaceManager", "onResumeSport ", Long.valueOf(elapsedRealtime), "pauseTime - resumeTime : ", Long.valueOf(this.w), Constants.LINK, Long.valueOf(SystemClock.elapsedRealtime()));
        this.b += elapsedRealtime;
    }

    private void m() {
        long j = this.b;
        if (j > 0) {
            this.p += j;
            this.e += j;
            this.b = 0L;
        }
    }

    public float a() {
        float e = this.f.e();
        LogUtil.c("Track_PaceManager", "old speed:" + e);
        gtr gtrVar = this.h;
        return (gtrVar == null || !gtrVar.h() || this.h.a()) ? e : this.h.c();
    }

    public float g() {
        return this.f.e();
    }

    public float d() {
        gtr gtrVar = this.h;
        if (gtrVar == null) {
            return 0.0f;
        }
        return gtrVar.c();
    }

    public void b() {
        this.f.c();
    }

    public void e(gtr gtrVar) {
        this.h = gtrVar;
    }
}
