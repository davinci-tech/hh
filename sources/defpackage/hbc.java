package defpackage;

import com.huawei.healthcloud.plugintrack.trackanimation.gpsutil.LatLong;
import com.huawei.healthcloud.plugintrack.trackanimation.preprocess.realtimedata.RealTimeDataParser;
import com.huawei.hwfoundationmodel.trackmodel.MotionPath;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes4.dex */
public class hbc {
    private d b;
    private RealTimeDataParser e;
    private e f;
    private RealTimeDataParser g;
    private RealTimeDataParser k;
    private RealTimeDataParser l;
    private int m = 258;
    private int c = 33;

    /* renamed from: a, reason: collision with root package name */
    private int f13060a = 17;
    private int i = 17;
    private ArrayList<Double> d = null;
    private MotionPath j = null;
    private long n = 0;
    private long h = 0;
    private long q = 0;
    private int o = 1;

    public hbc() {
        this.b = new d();
        this.f = new e();
        this.g = new hbh().setMaxDataCallBack(this.b.b);
        this.l = new hbj().setMaxDataCallBack(this.b.d);
        this.k = new hbf().setMaxDataCallBack(this.b.e);
        this.e = new hbi().setMaxDataCallBack(this.b.c);
    }

    public hbc c(MotionPath motionPath) {
        this.j = motionPath;
        return this;
    }

    public hbc b(MotionPathSimplify motionPathSimplify) {
        this.m = motionPathSimplify.requestSportType();
        this.n = motionPathSimplify.requestStartTime();
        this.q = motionPathSimplify.requestTotalTime();
        this.h = motionPathSimplify.requestEndTime();
        return this;
    }

    public int f() {
        return this.o;
    }

    public ArrayList<Double> i() {
        LogUtil.a("Track_DynamicDataParser", "go into obtainMultiplexField");
        if (this.f.h()) {
            ArrayList<Double> e2 = e(this.j);
            this.d = e2;
            this.c = 34;
            this.f13060a = 18;
            this.i = 18;
            if (e2.size() < 1) {
                this.d = d(this.j);
                this.c = 32;
                this.f13060a = 17;
                this.i = 17;
            }
        } else if (this.f.b()) {
            this.d = d(this.j);
            this.c = 32;
            this.f13060a = 16;
            this.i = 17;
        } else {
            this.d = d(this.j);
            this.c = 32;
            this.f13060a = 17;
            this.i = 17;
        }
        if (this.f.a()) {
            ArrayList<Double> b = b(this.j);
            if (b.size() > 0 && this.f.c()) {
                this.d = b;
                this.c = 33;
            }
        }
        if (this.f.e()) {
            ArrayList<Double> a2 = a(this.j);
            if (a2.size() > 0 && this.f.d()) {
                this.d = a2;
                this.c = 36;
            }
        }
        LogUtil.a("Track_DynamicDataParser", "go out obtainMultiplexField");
        return this.d;
    }

    public int d() {
        return this.c;
    }

    public int a() {
        return this.f13060a;
    }

    public int h() {
        return this.i;
    }

    public hbe<Integer, Double> e() {
        return this.b.c;
    }

    public hbe<Integer, Double> b() {
        return this.b.b;
    }

    public hbe<Integer, Double> c() {
        return this.i == 18 ? this.b.d : this.b.e;
    }

    private ArrayList<Double> a(MotionPath motionPath) {
        return this.b.d(motionPath, this.g);
    }

    private ArrayList<Double> e(MotionPath motionPath) {
        return this.b.d(motionPath, this.l);
    }

    private ArrayList<Double> d(MotionPath motionPath) {
        ArrayList<Double> d2 = this.b.d(motionPath, this.k);
        this.o = 1;
        if (d2.size() >= 1) {
            return d2;
        }
        ArrayList<Double> c = this.b.c(motionPath);
        this.o = -1;
        return c;
    }

    private ArrayList<Double> b(MotionPath motionPath) {
        return this.b.d(motionPath, this.e);
    }

    class e {
        /* JADX INFO: Access modifiers changed from: private */
        public boolean d() {
            return false;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean e() {
            return true;
        }

        private e() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean h() {
            return hbc.this.m == 257;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean a() {
            return hbc.this.m == 257 || hbc.this.m == 258 || hbc.this.m == 259 || hbc.this.m == 282 || hbc.this.m == 260 || hbc.this.m == 280 || hbc.this.m == 222;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean c() {
            return hbc.this.m == 282 || hbc.this.m == 260 || hbc.this.m == 280 || hbc.this.m == 222;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean b() {
            return hbc.this.m == 258;
        }
    }

    class d {
        private hbe<Integer, Double> b;
        private hbe<Integer, Double> c;
        private hbe<Integer, Double> d;
        private hbe<Integer, Double> e;
        private double f;

        private d() {
            Double valueOf = Double.valueOf(-1.7976931348623157E308d);
            this.c = new hbe<>(-1, valueOf);
            this.e = new hbe<>(-1, valueOf);
            this.d = new hbe<>(-1, valueOf);
            this.b = new hbe<>(-1, valueOf);
            this.f = 0.0d;
        }

        private void e(hbe<Integer, Double> hbeVar, int i, double d) {
            if (d > hbeVar.a().doubleValue()) {
                hbeVar.e(Double.valueOf(d));
                hbeVar.a(Integer.valueOf(i));
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public ArrayList<Double> d(MotionPath motionPath, RealTimeDataParser realTimeDataParser) {
            realTimeDataParser.setMotionPath(motionPath).setStartTime(hbc.this.n).setEndTime(hbc.this.h).setTotalTime(hbc.this.q);
            int size = realTimeDataParser.getSize();
            if (size < 1) {
                return new ArrayList<>(16);
            }
            ArrayList<Double> arrayList = new ArrayList<>(300);
            if (!realTimeDataParser.init()) {
                return new ArrayList<>(16);
            }
            for (int i = 0; i < size; i++) {
                arrayList.add(Double.valueOf(realTimeDataParser.getNextData()));
            }
            return arrayList;
        }

        private void d() {
            this.f = 0.0d;
        }

        private double a(double d, double d2) {
            if (d2 >= 1.0E-6d) {
                if (hbc.this.m == 260) {
                    this.f = (d * 3.6d) / d2;
                } else {
                    this.f = (d * 3600.0d) / d2;
                }
                return this.f;
            }
            return this.f;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public ArrayList<Double> c(MotionPath motionPath) {
            Iterator<Map.Entry<Long, double[]>> it;
            LogUtil.a("Track_DynamicDataParser", "go int countSpeedList");
            if (motionPath == null || motionPath.requestLbsDataMap() == null) {
                return new ArrayList<>();
            }
            ArrayList<Double> arrayList = new ArrayList<>(300);
            d();
            Iterator<Map.Entry<Long, double[]>> it2 = motionPath.requestLbsDataMap().entrySet().iterator();
            LatLong latLong = null;
            int i = -1;
            double d = 0.0d;
            while (it2.hasNext()) {
                Map.Entry<Long, double[]> next = it2.next();
                i++;
                try {
                } catch (IndexOutOfBoundsException unused) {
                    it = it2;
                }
                if (next.getValue() != null && next.getValue().length >= 4) {
                    if (hbb.c(next.getValue()[0], next.getValue()[1])) {
                        it = it2;
                    } else {
                        it = it2;
                        if (!hbb.e(next.getValue()[0], next.getValue()[1])) {
                            if (latLong == null) {
                                arrayList.add(Double.valueOf(0.0d));
                                LatLong latLong2 = new LatLong(next.getValue()[0], next.getValue()[1]);
                                try {
                                    d = next.getValue()[3];
                                    latLong = latLong2;
                                } catch (IndexOutOfBoundsException unused2) {
                                    latLong = latLong2;
                                    LogUtil.a("Track_DynamicDataParser", "Index Out Of Bounds Exception.");
                                    it2 = it;
                                }
                            } else {
                                LatLong latLong3 = new LatLong(next.getValue()[0], next.getValue()[1]);
                                double a2 = a(hau.b(latLong, latLong3), next.getValue()[3] - d);
                                arrayList.add(Double.valueOf(a2));
                                e(this.e, i, a2);
                                try {
                                    d = next.getValue()[3];
                                    latLong = latLong3;
                                } catch (IndexOutOfBoundsException unused3) {
                                    latLong = latLong3;
                                    LogUtil.a("Track_DynamicDataParser", "Index Out Of Bounds Exception.");
                                    it2 = it;
                                }
                            }
                            it2 = it;
                        }
                    }
                    arrayList.add(Double.valueOf(this.f));
                    it2 = it;
                }
                it = it2;
                arrayList.add(Double.valueOf(this.f));
                it2 = it;
            }
            LogUtil.a("Track_DynamicDataParser", "go out countSpeedList");
            return arrayList;
        }
    }
}
