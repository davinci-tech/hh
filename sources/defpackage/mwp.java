package defpackage;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: classes6.dex */
public class mwp {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("brandMode")
    private String f15221a;

    @SerializedName("brandAllowList")
    private List<String> b;

    @SerializedName("mtkCpuAllowList")
    private List<String> c;

    @SerializedName("cpuAllowList")
    private List<String> d;

    @SerializedName("mtkBrandAllowList")
    private List<String> e;

    @SerializedName("shieldingStatus")
    private String g;

    @SerializedName("deviceModel")
    private List<String> h;

    public List<String> b() {
        return this.c;
    }

    public List<String> d() {
        return this.d;
    }

    public List<String> a() {
        return this.b;
    }

    public List<String> e() {
        return this.e;
    }

    public String c() {
        return this.f15221a;
    }

    public String f() {
        return this.g;
    }

    public List<String> g() {
        return this.h;
    }

    public String toString() {
        return new Gson().toJson(this);
    }
}
