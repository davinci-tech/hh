package defpackage;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: classes6.dex */
public class mnx {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("realLastWeekDuration")
    private int f15072a;

    @SerializedName("coachComments")
    private String b;

    @SerializedName("lastWeekCalorie")
    private float c;

    @SerializedName("lastWeekDistance")
    private double d;

    @SerializedName("realIntensityZone")
    private List<Integer> e;

    @SerializedName("targetWeekDuration")
    private int f;

    @SerializedName("targetIntensityZone")
    private List<Integer> h;

    public double c() {
        return this.d;
    }

    public void b(double d) {
        this.d = d;
    }

    public int a() {
        return this.f15072a;
    }

    public void d(int i) {
        this.f15072a = i;
    }

    public float e() {
        return this.c;
    }

    public void c(float f) {
        this.c = f;
    }

    public List<Integer> b() {
        return this.h;
    }

    public void c(List<Integer> list) {
        this.h = list;
    }

    public List<Integer> d() {
        return this.e;
    }

    public void d(List<Integer> list) {
        this.e = list;
    }
}
