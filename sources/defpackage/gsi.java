package defpackage;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/* loaded from: classes.dex */
public class gsi implements Serializable {
    private static final long serialVersionUID = 468976053049501463L;

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("caloricDeficitGoal")
    private int f12906a;

    @SerializedName("weightInit")
    private float b;

    @SerializedName("onePointFull")
    private gsh c;

    @SerializedName("fatLossRatio")
    private gsb d;

    @SerializedName("activityCalGoal")
    private int e;

    @SerializedName("proportionMeal")
    private gsf h;

    @SerializedName("weightManagerType")
    private int i;

    public int g() {
        return this.i;
    }

    public void e(int i) {
        this.i = i;
    }

    public float d() {
        return this.b;
    }

    public void c(float f) {
        this.b = f;
    }

    public int c() {
        return this.f12906a;
    }

    public void b(int i) {
        this.f12906a = i;
    }

    public int a() {
        return this.e;
    }

    public void c(int i) {
        this.e = i;
    }

    public void b(gsf gsfVar) {
        this.h = gsfVar;
    }

    public gsb b() {
        return this.d;
    }

    public void b(gsb gsbVar) {
        this.d = gsbVar;
    }

    public gsh e() {
        return this.c;
    }

    public void e(gsh gshVar) {
        this.c = gshVar;
    }

    public String toString() {
        return "WeightManager{mWeightManagerType=" + this.i + ", mInitWeight=" + this.b + ", mCaloricDeficitGoal=" + this.f12906a + ", mActivityCalGoal=" + this.e + ", mProportionMeal=" + this.h + ", mFatLossRatio=" + this.d + ", mOnePointFull=" + this.c + '}';
    }
}
