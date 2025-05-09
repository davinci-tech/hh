package defpackage;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes8.dex */
public class gmv {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("bodyMassIndex")
    private Float f12870a;

    @SerializedName("bloodPressureSource")
    private Integer b;

    @SerializedName("diastolicPressure")
    private Integer c;

    @SerializedName("bloodPressureTimestamp")
    private Long d;

    @SerializedName("bloodPressureGrade")
    private Integer e;

    @SerializedName("fatPercentage")
    private Float f;

    @SerializedName("fattyLiverTimestamp")
    private Long g;

    @SerializedName("fatPercentageTimestamp")
    private Long h;

    @SerializedName("fattyLiverRisk")
    private Integer i;

    @SerializedName("fattyLiverGrade")
    private Float j;

    @SerializedName("systolicPressure")
    private Integer k;

    @SerializedName("weight")
    private Float l;

    @SerializedName("sleepApneaTimestamp")
    private Long m;

    @SerializedName("sleepApneaGrade")
    private Integer n;

    @SerializedName("height")
    private Integer o;

    @SerializedName("weightTimestamp")
    private Long s;

    public Integer b() {
        Integer num = this.o;
        if (num == null) {
            return 0;
        }
        return num;
    }

    public void c(Integer num) {
        this.o = num;
    }

    public Float a() {
        Float f = this.l;
        return f == null ? Float.valueOf(0.0f) : f;
    }

    public void d(Float f) {
        this.l = f;
    }

    public void c(Long l) {
        this.s = l;
    }

    public void e(Float f) {
        this.f12870a = f;
    }

    public void f(Integer num) {
        this.k = num;
    }

    public void b(Integer num) {
        this.c = num;
    }

    public void a(Integer num) {
        this.e = num;
    }

    public void d(Integer num) {
        this.b = num;
    }

    public void d(Long l) {
        this.d = l;
    }

    public void b(Float f) {
        this.f = f;
    }

    public void b(Long l) {
        this.h = l;
    }

    public void e(Integer num) {
        this.i = num;
    }

    public void c(Float f) {
        this.j = f;
    }

    public void e(Long l) {
        this.g = l;
    }

    public void j(Integer num) {
        this.n = num;
    }

    public void a(Long l) {
        this.m = l;
    }

    public String toString() {
        return "HealthGlanceData{height=" + this.o + ", weight=" + this.l + ", weightTimestamp=" + this.s + ", bodyMassIndex=" + this.f12870a + ", systolicPressure=" + this.k + ", diastolicPressure=" + this.c + ", bloodPressureGrade=" + this.e + ", bloodPressureSource=" + this.b + ", bloodPressureTimestamp=" + this.d + ", fatPercentage=" + this.f + ", fatPercentageTimestamp=" + this.h + ", fattyLiverRisk=" + this.i + ", fattyLiverGrade=" + this.j + ", fattyLiverTimestamp=" + this.g + ", sleepApneaGrade=" + this.n + ", sleepApneaTimestamp=" + this.m + '}';
    }
}
