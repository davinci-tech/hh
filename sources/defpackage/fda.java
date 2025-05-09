package defpackage;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

/* loaded from: classes.dex */
public class fda implements Serializable {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("daily_factor")
    private List<Integer> f12452a;

    @SerializedName("daily_advice")
    private List<fci> b;

    @SerializedName("daily_factor_physio")
    private String d;

    @SerializedName("daily_factor_desc_input")
    private List<fco> e;

    @SerializedName("daily_major_reason")
    private int f;

    @SerializedName("daily_problem")
    private int g;

    @SerializedName("daily_problem_desc_input")
    private List<fcm> i;

    @SerializedName("daily_problem_physio")
    private String k;

    @SerializedName("daily_task")
    private List<fcn> m;

    @SerializedName("daily_quality")
    private int n;

    @SerializedName("daily_problem_level")
    private int o;

    @SerializedName("update")
    private boolean p;

    @SerializedName("generateTime")
    private long s;

    @SerializedName("result_code")
    private int t;

    @SerializedName("daily_quality_desc")
    private String l = "";

    @SerializedName("daily_problem_desc")
    private String j = "";

    @SerializedName("daily_factor_desc")
    private String c = "";

    @SerializedName("daily_problem_impact")
    private String h = "";

    public int k() {
        return this.t;
    }

    public void b(String str) {
        this.l = str;
    }

    public String n() {
        return this.l;
    }

    public void b(int i) {
        this.g = i;
    }

    public int h() {
        return this.g;
    }

    public void c(int i) {
        this.o = i;
    }

    public int j() {
        return this.o;
    }

    public void c(String str) {
        this.j = str;
    }

    public String g() {
        return this.j;
    }

    public void e(List<fcm> list) {
        this.i = list;
    }

    public List<fcm> i() {
        return this.i;
    }

    public void d(String str) {
        this.h = str;
    }

    public String f() {
        return this.h;
    }

    public void a(List<Integer> list) {
        this.f12452a = list;
    }

    public List<Integer> c() {
        return this.f12452a;
    }

    public List<fco> b() {
        return this.e;
    }

    public void d(List<fco> list) {
        this.e = list;
    }

    public String d() {
        return this.c;
    }

    public void a(String str) {
        this.c = str;
    }

    public void c(List<fci> list) {
        this.b = list;
    }

    public List<fci> e() {
        return this.b;
    }

    public void b(List<fcn> list) {
        this.m = list;
    }

    public List<fcn> l() {
        return this.m;
    }

    public boolean s() {
        return this.p;
    }

    public void d(boolean z) {
        this.p = z;
    }

    public long o() {
        return this.s;
    }

    public void e(long j) {
        this.s = j;
    }

    public String m() {
        return this.k;
    }

    public String a() {
        return this.d;
    }

    public String toString() {
        return "SleepDailyResultBean{resultCode=" + this.t + ", dailyQuality=" + this.n + ", dailyQualityDesc='" + this.l + "', dailyProblem=" + this.g + ", dailyProblemLevel=" + this.o + ", dailyProblemDesc='" + this.j + "', dailyFactorDesc='" + this.c + "', dailyProblemDescInput=" + this.i + ", dailyProblemImpact='" + this.h + "', dailyFactor=" + this.f12452a + ", dailyFactorDescInput=" + this.e + ", dailyMajorReason=" + this.f + ", dailyAdvice=" + this.b + ", dailyTask=" + this.m + ", isUpdated=" + this.p + ", generateTime=" + this.s + ", dailyProblemPhysio='" + this.k + "', dailyFactorPhysio='" + this.d + "'}";
    }
}
