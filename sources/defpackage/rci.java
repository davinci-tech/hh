package defpackage;

import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes7.dex */
public class rci {

    /* renamed from: a, reason: collision with root package name */
    private int f16700a;
    private double b;
    private double c;
    private double d;
    private String e = "Track_AllSportRankItem";
    private double h;

    public rci(int i, double d, double d2, double d3) {
        this.f16700a = i;
        this.d = d;
        this.h = d2;
        this.c = d3;
    }

    public int d() {
        return this.f16700a;
    }

    public void e(double d) {
        this.d = d;
    }

    public double c() {
        return this.d;
    }

    public void d(double d) {
        this.h = d;
    }

    public double e() {
        return this.h;
    }

    public void b(double d) {
        this.c = d;
    }

    public double a() {
        return this.c;
    }

    public void a(double d) {
        this.b = d;
    }

    public double b() {
        return this.b;
    }

    public double e(String str) {
        if (str.equals("Track_Duration_Sum")) {
            return c();
        }
        if (str.equals("Track_Count_Sum")) {
            return e();
        }
        if (str.equals("Track_Calorie_Sum")) {
            return a();
        }
        LogUtil.a(this.e, "invalid type");
        return 0.0d;
    }
}
