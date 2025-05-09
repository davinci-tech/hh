package defpackage;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: classes.dex */
public class fdh {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("monthly_daily_tasks")
    private List<List<fcv>> f12459a;

    @SerializedName("monthly_impact_desc")
    private String b;

    @SerializedName("monthly_prob_desc")
    private String c;

    @SerializedName("monthly_major_reason")
    private List<Integer> d;

    @SerializedName("monthly_daily_advices")
    private List<fct> e;

    @SerializedName("monthly_prob_val")
    private List<Integer> f;

    @SerializedName("monthly_prob_level")
    private int g;

    @SerializedName("monthly_recomd")
    private fcu h;

    @SerializedName("monthly_tasks")
    private List<Integer> i;

    @SerializedName("monthly_problem")
    private int j;

    @SerializedName("rhythm")
    private int m;

    @SerializedName("rhythm_desc")
    private String n;

    @SerializedName("result_code")
    private int o;

    public int l() {
        return this.o;
    }

    public int o() {
        return this.m;
    }

    public int f() {
        return this.j;
    }

    public int g() {
        return this.g;
    }

    public String e() {
        return this.c;
    }

    public List<Integer> h() {
        return this.f;
    }

    public List<Integer> a() {
        return this.d;
    }

    public String d() {
        return this.b;
    }

    public fcu j() {
        return this.h;
    }

    public List<Integer> i() {
        return this.i;
    }

    public List<List<fcv>> b() {
        return this.f12459a;
    }

    public List<fct> c() {
        return this.e;
    }
}
