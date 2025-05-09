package defpackage;

import com.huawei.health.trackprocess.model.GpsPoint;
import java.util.List;

/* loaded from: classes8.dex */
public class gkn {

    /* renamed from: a, reason: collision with root package name */
    private double f12840a;
    private int b;
    private String c;
    private String d;
    private List<Integer> e;
    private String f;
    private GpsPoint g;
    private List<GpsPoint> h;
    private gkp i;
    private double j;

    public String d() {
        return this.c;
    }

    public String i() {
        return this.f;
    }

    public gkp h() {
        return this.i;
    }

    public List<GpsPoint> f() {
        return this.h;
    }

    public double j() {
        return this.j;
    }

    public GpsPoint g() {
        return this.g;
    }

    public List<Integer> c() {
        return this.e;
    }

    public double b() {
        return this.f12840a;
    }

    public int a() {
        return this.b;
    }

    public String e() {
        return this.d;
    }

    public String toString() {
        return "MotionPathInfo{geoHash='" + this.c + "', pathId='" + this.f + "', pathRange=" + this.i + ", pathPoints=" + this.h + ", totalDistance=" + this.j + ", pathLocation=" + this.g + ", heat=" + this.e + ", confidence=" + this.f12840a + ", flag=" + this.b + ", executionTime='" + this.d + "'}";
    }
}
