package defpackage;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

/* loaded from: classes4.dex */
public class fiz {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("recommendDetail")
    private fiy f12536a;

    @SerializedName("planId")
    private String b;

    @SerializedName("mealType")
    private int c;

    @SerializedName("foodMatchConditions")
    private fit d;

    @SerializedName("recipesDate")
    private String e;

    @SerializedName("replaceType")
    private int g;

    public String b() {
        return this.b;
    }

    public void d(String str) {
        this.b = str;
    }

    public String c() {
        return this.e;
    }

    public void b(String str) {
        this.e = str;
    }

    public fit a() {
        return this.d;
    }

    public void a(fit fitVar) {
        this.d = fitVar;
    }

    public fiy e() {
        return this.f12536a;
    }

    public void d(fiy fiyVar) {
        this.f12536a = fiyVar;
    }

    public int j() {
        return this.g;
    }

    public void e(int i) {
        this.g = i;
    }

    public int d() {
        return this.c;
    }

    public void c(int i) {
        this.c = i;
    }

    public String toString() {
        return new Gson().toJson(this);
    }
}
