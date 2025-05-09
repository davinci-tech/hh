package com.huawei.healthcloud.plugintrack.manager;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Pair;
import com.huawei.healthcloud.plugintrack.PluginSportTrackAdapter;
import com.huawei.healthcloud.plugintrack.manager.TrackAltitudeMgr;
import com.huawei.healthcloud.plugintrack.manager.inteface.ISportStateChangeListener;
import com.huawei.healthcloud.plugintrack.manager.inteface.LocalPressureCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.gso;
import defpackage.ixx;
import defpackage.jdx;
import defpackage.knz;
import defpackage.koq;
import defpackage.kvq;
import health.compact.a.CommonUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes4.dex */
public class TrackAltitudeMgr implements ISportStateChangeListener {
    private Sensor g;
    private SensorManager o;
    private List<knz> e = new ArrayList(10);
    private List<knz> d = new ArrayList(10);
    private long l = 0;
    private boolean j = false;
    private double c = -1.0d;
    private double i = 0.0d;

    /* renamed from: a, reason: collision with root package name */
    private double f3518a = 0.0d;
    private double b = 0.0d;
    private float f = 0.0f;
    private float h = 0.0f;
    private d n = new d();
    private LocalPressureCallback m = new LocalPressureCallback() { // from class: com.huawei.healthcloud.plugintrack.manager.TrackAltitudeMgr.5
        @Override // com.huawei.healthcloud.plugintrack.manager.inteface.LocalPressureCallback
        public void onUpdateLocalPressure(double d2) {
            LogUtil.a("Track_TrackAltitudeMgr", "onUpdateLocalPressure= ", Double.valueOf(d2));
            if (d2 > 0.0d) {
                TrackAltitudeMgr.this.c = d2;
            } else {
                LogUtil.h("Track_TrackAltitudeMgr", "exception localPressure= ", Double.valueOf(d2));
                TrackAltitudeMgr.this.l();
            }
        }

        @Override // com.huawei.healthcloud.plugintrack.manager.inteface.LocalPressureCallback
        public void onFailed(String str) {
            TrackAltitudeMgr.this.l();
            ReleaseLogUtil.d("R_Track_TrackAltitudeMgr", "exception localPressure= ", str);
        }
    };

    @Override // com.huawei.healthcloud.plugintrack.manager.inteface.ISportStateChangeListener, com.huawei.health.sportservice.SportLifecycle
    public void onStartSport() {
    }

    @Override // com.huawei.healthcloud.plugintrack.manager.inteface.ISportStateChangeListener, com.huawei.health.sportservice.SportLifecycle
    /* renamed from: onStopSport */
    public void m134x32b3e3a1() {
    }

    public TrackAltitudeMgr(Context context) {
        this.o = null;
        this.g = null;
        if (context == null) {
            throw new RuntimeException("Track_TrackAltitudeMgrsystem error with null context");
        }
        PluginSportTrackAdapter c = gso.e().c();
        if (c != null) {
            c.requestLocalPressure(this.m, 0);
        }
        LogUtil.a("Track_TrackAltitudeMgr", "new TrackAltitudeMgr");
        SensorManager xD_ = CommonUtil.xD_();
        this.o = xD_;
        if (xD_ != null) {
            this.g = xD_.getDefaultSensor(6);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        this.c = 1013.25d;
    }

    public void c(List<knz> list) {
        ReleaseLogUtil.e("Track_TrackAltitudeMgr", "recoveryAltitudeList");
        this.e.clear();
        this.d.clear();
        this.f3518a = 0.0d;
        this.b = 0.0d;
        if (koq.b(list)) {
            return;
        }
        this.e.addAll(list);
        ReleaseLogUtil.e("Track_TrackAltitudeMgr", "recovery AltitudeList size", Integer.valueOf(list.size()));
        h();
    }

    public void g() {
        LogUtil.a("Track_TrackAltitudeMgr", "startAltitudeMgr");
        o();
        this.e.clear();
    }

    public void i() {
        LogUtil.a("Track_TrackAltitudeMgr", "stopAltitudeMgr mAltitudeList", Integer.valueOf(this.e.size()));
        h();
        k();
    }

    public void h() {
        Pair<Double, Double> aUj_ = aUj_();
        double doubleValue = ((Double) aUj_.first).doubleValue();
        double doubleValue2 = ((Double) aUj_.second).doubleValue();
        if (this.f3518a < doubleValue) {
            this.f3518a = doubleValue;
        }
        if (this.b < doubleValue2) {
            this.b = doubleValue2;
        }
    }

    public ArrayList<knz> e(boolean z) {
        ArrayList<knz> arrayList = new ArrayList<>(this.d.size());
        if (!this.d.isEmpty()) {
            arrayList.addAll(this.d);
            if (z) {
                this.d.clear();
            }
        }
        return arrayList;
    }

    public double d() {
        LogUtil.a("Track_TrackAltitudeMgr", "CW = ", Double.valueOf(this.f3518a));
        return this.f3518a;
    }

    public double c() {
        LogUtil.a("Track_TrackAltitudeMgr", "DW = ", Double.valueOf(this.b));
        return this.b;
    }

    @Override // com.huawei.healthcloud.plugintrack.manager.inteface.ISportStateChangeListener, com.huawei.health.sportservice.SportLifecycle
    public void onPauseSport() {
        k();
    }

    @Override // com.huawei.healthcloud.plugintrack.manager.inteface.ISportStateChangeListener, com.huawei.health.sportservice.SportLifecycle
    public void onResumeSport() {
        o();
    }

    public float e() {
        return this.h;
    }

    private void o() {
        ReleaseLogUtil.e("Track_TrackAltitudeMgr", "registerPressureSensor");
        if (this.g == null || this.o == null) {
            ReleaseLogUtil.d("Track_TrackAltitudeMgr", "registerPressureSensor mPressure or mSensorManager is null");
            return;
        }
        LogUtil.a("Track_TrackAltitudeMgr", "use Sensor");
        SensorManager sensorManager = this.o;
        d dVar = this.n;
        sensorManager.registerListener(dVar, this.g, dVar.e());
    }

    private void k() {
        SensorManager sensorManager;
        ReleaseLogUtil.e("Track_TrackAltitudeMgr", "unregisterPressureSensor");
        if (this.g == null || (sensorManager = this.o) == null) {
            ReleaseLogUtil.d("Track_TrackAltitudeMgr", "unregisterPressureSensor mPressure or mSensorManager is null");
        } else {
            sensorManager.unregisterListener(this.n);
        }
    }

    private Pair<Double, Double> aUj_() {
        return aUk_(f(), 0.6d);
    }

    class d implements SensorEventListener {

        /* renamed from: a, reason: collision with root package name */
        private long f3519a;
        private int c;
        private float[] d;

        /* JADX INFO: Access modifiers changed from: private */
        public int e() {
            return 100000;
        }

        @Override // android.hardware.SensorEventListener
        public void onAccuracyChanged(Sensor sensor, int i) {
        }

        private d() {
            this.d = new float[51];
            this.f3519a = 0L;
        }

        private double a() {
            if (this.c <= 0) {
                LogUtil.h("Track_TrackAltitudeMgr", "Sensor error,Pressure not report");
                return Double.MIN_VALUE;
            }
            float f = 0.0f;
            float f2 = Float.MIN_VALUE;
            float f3 = Float.MAX_VALUE;
            int i = 0;
            while (true) {
                if (i >= this.c) {
                    break;
                }
                float f4 = this.d[i];
                if (f2 < f4) {
                    f2 = f4;
                }
                if (f3 > f4) {
                    f3 = f4;
                }
                f += f4;
                i++;
            }
            float f5 = f2 - f3;
            if (f5 < 15.0f) {
                return (1.0d - Math.pow((f / r7) / TrackAltitudeMgr.this.c, 0.19029495120048523d)) * 44330.0d;
            }
            LogUtil.h("Track_TrackAltitudeMgr", "Pressure change over thres = ", LogAnonymous.b((int) f5));
            return Double.MIN_VALUE;
        }

        @Override // android.hardware.SensorEventListener
        public void onSensorChanged(SensorEvent sensorEvent) {
            if (sensorEvent == null || sensorEvent.values == null) {
                LogUtil.h("Track_TrackAltitudeMgr", "onSensorChanged SensorEvent is invalid.");
                return;
            }
            if (sensorEvent.values.length == 0) {
                return;
            }
            TrackAltitudeMgr.this.f = sensorEvent.values[0];
            long currentTimeMillis = System.currentTimeMillis();
            if (TrackAltitudeMgr.this.i == 0.0d) {
                TrackAltitudeMgr.this.i = currentTimeMillis;
            } else if (TrackAltitudeMgr.this.c == -1.0d && currentTimeMillis - TrackAltitudeMgr.this.i > 60000.0d) {
                TrackAltitudeMgr.this.l();
            }
            if (TrackAltitudeMgr.this.c == -1.0d) {
                return;
            }
            if (TrackAltitudeMgr.this.f < TrackAltitudeMgr.this.c && TrackAltitudeMgr.this.f != 0.0f) {
                TrackAltitudeMgr.this.h = (float) ((1.0d - Math.pow(r4.f / TrackAltitudeMgr.this.c, 0.19029495120048523d)) * 44330.0d);
            }
            if (this.f3519a == 0) {
                this.f3519a = currentTimeMillis;
            }
            if (currentTimeMillis - this.f3519a >= 5000) {
                double a2 = a();
                if (Math.abs(a2 - Double.MIN_VALUE) < 1.0E-6d) {
                    if (TrackAltitudeMgr.this.e.size() != 0) {
                        a2 = ((knz) TrackAltitudeMgr.this.e.get(TrackAltitudeMgr.this.e.size() - 1)).c();
                    } else {
                        this.c = 0;
                        this.f3519a = currentTimeMillis;
                        return;
                    }
                }
                LogUtil.a("Track_TrackAltitudeMgr", "h = ", Double.valueOf(a2));
                knz knzVar = new knz(currentTimeMillis, a2);
                TrackAltitudeMgr.this.e.add(knzVar);
                TrackAltitudeMgr.this.d.add(knzVar);
                TrackAltitudeMgr.this.h();
                this.c = 0;
                this.f3519a = currentTimeMillis;
                return;
            }
            int i = this.c;
            if (i < 51) {
                float[] fArr = this.d;
                this.c = i + 1;
                fArr[i] = sensorEvent.values[0];
            }
        }
    }

    private double[] f() {
        ArrayList arrayList = new ArrayList(this.e);
        if (arrayList.size() == 0) {
            return new double[0];
        }
        double[] dArr = new double[arrayList.size() / 2];
        for (int i = 0; i < arrayList.size() - 1; i += 2) {
            dArr[i / 2] = (((knz) arrayList.get(i)).c() + ((knz) arrayList.get(i + 1)).c()) / 2.0d;
        }
        return dArr;
    }

    private Pair<Double, Double> aUk_(double[] dArr, double d2) {
        int i;
        double d3 = 0.0d;
        Double valueOf = Double.valueOf(0.0d);
        if (dArr != null) {
            if (dArr.length > 1) {
                int length = dArr.length;
                double d4 = dArr[0];
                int i2 = 0;
                int i3 = 0;
                while (i2 < length - 1) {
                    double d5 = dArr[i2];
                    int i4 = i2 + 1;
                    if (dArr[i4] - d5 < d3) {
                        int b = b(dArr, i2);
                        double d6 = dArr[b];
                        if (d5 - d6 > d2 || d6 < d4) {
                            i = length;
                            LogUtil.a("Track_TrackAltitudeMgr", "upS = ", Integer.valueOf(i3), " upE = ", Integer.valueOf(i2), " downS = ", Integer.valueOf(i2), " downE = ", Integer.valueOf(b), " creepingWave = ", valueOf);
                            d4 = dArr[b];
                            i2 = b;
                            i3 = i2;
                        } else {
                            while (i4 <= b) {
                                dArr[i4] = d5;
                                i4++;
                            }
                            i2 = b;
                            i = length;
                        }
                    } else {
                        i = length;
                        i3 = i2;
                        i2 = c(dArr, i2);
                    }
                    length = i;
                    d3 = 0.0d;
                }
                int i5 = length;
                double d7 = 0.0d;
                double d8 = 0.0d;
                for (int i6 = 1; i6 < i5; i6++) {
                    double d9 = dArr[i6];
                    double d10 = dArr[i6 - 1];
                    if (d9 >= d10) {
                        d7 += d9 - d10;
                    } else {
                        d8 += d10 - d9;
                    }
                }
                ArrayList arrayList = new ArrayList();
                for (double d11 : dArr) {
                    arrayList.add(Double.valueOf(d11));
                }
                LogUtil.a("Track_TrackAltitudeMgr", "getCreepingWave = ", Double.valueOf(d7), " getDescentWave = ", Double.valueOf(d8), " heightList = ", arrayList);
                return new Pair<>(Double.valueOf(d7), Double.valueOf(d8));
            }
        }
        return new Pair<>(valueOf, valueOf);
    }

    private int c(double[] dArr, int i) {
        int length = dArr.length;
        while (true) {
            int i2 = length - 1;
            if (i >= i2) {
                return i2;
            }
            int i3 = i + 1;
            if (dArr[i] > dArr[i3]) {
                return i;
            }
            i = i3;
        }
    }

    private int b(double[] dArr, int i) {
        int length = dArr.length;
        while (true) {
            int i2 = length - 1;
            if (i >= i2) {
                return i2;
            }
            int i3 = i + 1;
            if (dArr[i] < dArr[i3]) {
                return i;
            }
            i = i3;
        }
    }

    public boolean b() {
        return this.g != null;
    }

    private void c(String str, String str2) {
        HashMap hashMap = new HashMap(1);
        hashMap.put("query", str);
        ixx.d().d(BaseApplication.getContext(), str2, hashMap, 0);
    }

    public void c(kvq kvqVar) {
        if (kvqVar == null) {
            LogUtil.h("Track_TrackAltitudeMgr", " reportData == null");
            return;
        }
        float e = kvqVar.e() / 10.0f;
        this.f3518a = kvqVar.q() / 10.0d;
        this.b = kvqVar.t() / 10.0d;
        if (!this.j) {
            this.j = true;
            jdx.b(new Runnable() { // from class: gun
                @Override // java.lang.Runnable
                public final void run() {
                    TrackAltitudeMgr.this.a();
                }
            });
        }
        this.h = e;
        long m = kvqVar.m();
        if (this.l == 0) {
            this.l = m;
        }
        if (m - this.l >= 5000) {
            double d2 = e;
            if (Math.abs(d2 - Double.MIN_VALUE) < 1.0E-6d) {
                if (this.e.size() == 0) {
                    this.l = m;
                    return;
                } else {
                    List<knz> list = this.e;
                    d2 = list.get(list.size() - 1).c();
                }
            }
            LogUtil.a("Track_TrackAltitudeMgr", "h = ", Double.valueOf(d2), "creepWave= ", Double.valueOf(this.f3518a), "descentWave= ", Double.valueOf(this.b));
            knz knzVar = new knz(m, d2);
            this.e.add(knzVar);
            this.d.add(knzVar);
            this.l = m;
        }
    }

    public /* synthetic */ void a() {
        LogUtil.a("Track_TrackAltitudeMgr", "use reportData");
        c("query_devices_altitude", AnalyticsValue.HEALTH_QUARE_DEVICS_ALTITUDE_1040062.value());
    }
}
