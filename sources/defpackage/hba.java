package defpackage;

import com.huawei.healthcloud.plugintrack.trackanimation.gpsutil.LatLong;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes4.dex */
public class hba {
    private double g = 1080.0d;
    private double h = 1920.0d;
    private double n = 0.0d;
    private LatLong d = null;
    private LatLong e = null;
    private double f = 1.0d;
    private int j = 10;
    private double b = 0.0d;
    private int k = 0;
    private int c = 0;

    /* renamed from: a, reason: collision with root package name */
    private double f13059a = 0.0d;
    private double i = 1.0d;

    private double a(double d, double d2) {
        if (d2 < 1.0E-6d) {
            return 1.0d;
        }
        return d / d2;
    }

    private int a(double d) {
        if (d < 800.0d) {
            return -50;
        }
        if (d < 4000.0d) {
            return 50;
        }
        if (d < 10000.0d) {
            return 100;
        }
        if (d < 20000.0d) {
            return 150;
        }
        if (d < 50000.0d) {
            return 200;
        }
        return d < 100000.0d ? 250 : 300;
    }

    public hba e(double d) {
        this.n = d;
        return this;
    }

    public hba e(LatLong latLong) {
        this.d = latLong;
        return this;
    }

    public hba c(LatLong latLong) {
        this.e = latLong;
        return this;
    }

    private void g() {
        this.j = hag.a(2.5f);
    }

    private void j() {
        this.g = hag.a();
        double d = hag.d();
        this.h = d;
        LatLong latLong = this.e;
        if (latLong == null || this.d == null) {
            double d2 = this.n;
            this.b = d2;
            this.i = d2 / (d + this.g);
            return;
        }
        double b = hau.b(new LatLong(latLong.getLatLng().b, this.e.getLatLng().d), new LatLong(this.e.getLatLng().b, this.d.getLatLng().d));
        double b2 = hau.b(new LatLong(this.e.getLatLng().b, this.e.getLatLng().d), new LatLong(this.d.getLatLng().b, this.e.getLatLng().d));
        this.b = (b2 + b) * 2.0d;
        double d3 = b / this.g;
        double d4 = b2 / this.h;
        if (d4 > d3) {
            d3 = d4;
        }
        this.i = d3;
    }

    private void h() {
        g();
        j();
    }

    public double e() {
        LatLong latLong;
        LatLong latLong2 = this.e;
        if (latLong2 == null || (latLong = this.d) == null) {
            return this.n;
        }
        return hau.b(latLong2, latLong);
    }

    public double f() {
        a();
        double d = this.n;
        if (this.j * d < 1.0E-6d) {
            this.f = 1.0d;
        } else {
            this.f = d / (this.k * r2);
        }
        return this.f;
    }

    public int a() {
        LogUtil.a("Track_TrackSegmentManager", "Go into obtainTotalSamples");
        h();
        this.c = c(this.n);
        this.f13059a = a(this.n, this.b);
        int a2 = this.c + a(this.n);
        this.k = a2;
        if (a2 > 900) {
            a2 = 900;
        }
        this.k = a2;
        LogUtil.a("Track_TrackSegmentManager", "Go out obtainTotalSamples");
        return this.k;
    }

    public int b() {
        LogUtil.a("Track_TrackSegmentManager", "Go into obtainSpinSamples");
        int i = (this.k * 2) / 10;
        LogUtil.a("Track_TrackSegmentManager", "Go out obtainSpinSamples");
        return i;
    }

    public int d() {
        LogUtil.a("Track_TrackSegmentManager", "Go into obtainSegmentSamples");
        int i = this.k / 10;
        LogUtil.a("Track_TrackSegmentManager", "Go out obtainSegmentSamples");
        return i;
    }

    public double c() {
        return this.f13059a;
    }

    private int c(double d) {
        double d2 = d / this.i;
        double max = Math.max(this.h, this.g);
        double d3 = this.h;
        double d4 = this.g;
        double sqrt = Math.sqrt((d3 * d3) + (d4 * d4));
        double d5 = (this.h + this.g) * 2.0d;
        double d6 = d5 * 2.0d;
        if (d2 < max) {
            return 300;
        }
        if (d2 < sqrt) {
            return 350;
        }
        if (d2 < d5) {
            return 400;
        }
        if (d2 < d6) {
            return 500;
        }
        return d2 < d6 * 2.0d ? 600 : 650;
    }
}
