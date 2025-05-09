package defpackage;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

/* loaded from: classes7.dex */
public class quk {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("fat")
    private float f16595a;

    @SerializedName("addedTime")
    private long b;

    @SerializedName("foodId")
    private String c;

    @SerializedName("carbohydrate")
    private float d;

    @SerializedName("count")
    private float e;

    @SerializedName("gi")
    private float f;

    @SerializedName("protein")
    private float g;

    @SerializedName("provider")
    private int h = 1;

    @SerializedName("kiloCalorie")
    private float i;

    @SerializedName("foodName")
    private String j;

    @SerializedName("sodium")
    private float k;

    @SerializedName("unit")
    private String l;

    @SerializedName("unitId")
    private String m;

    @SerializedName("timeZone")
    private String n;

    public void b(String str) {
        this.n = str;
    }

    public quk(String str, long j) {
        this.c = str;
        this.b = j;
    }

    public void l() {
        if (String.valueOf(this.b).matches("\\d{13}")) {
            this.b /= 1000;
        }
    }

    public void d(float f) {
        this.e = f;
    }

    public float b() {
        return this.e;
    }

    public String n() {
        return this.l;
    }

    public void e(String str) {
        this.l = str;
    }

    public int j() {
        return this.h;
    }

    public void k() {
        if (String.valueOf(this.b).matches("\\d{10}")) {
            this.b *= 1000;
        }
    }

    public String a() {
        return this.c;
    }

    public float i() {
        return this.f;
    }

    public void a(float f) {
        this.f = f;
    }

    public float d() {
        return this.d;
    }

    public void c(float f) {
        this.d = f;
    }

    public String c() {
        return this.j;
    }

    public void a(String str) {
        this.j = str;
    }

    public float e() {
        return this.f16595a;
    }

    public void e(float f) {
        this.f16595a = f;
    }

    public float f() {
        return this.g;
    }

    public void h(float f) {
        this.g = f;
    }

    public float g() {
        return this.i;
    }

    public void b(float f) {
        this.i = f;
    }

    public float h() {
        return this.k;
    }

    public void f(float f) {
        this.k = f;
    }

    public String toString() {
        return new Gson().toJson(this);
    }
}
