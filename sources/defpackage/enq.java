package defpackage;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/* loaded from: classes3.dex */
public class enq implements Serializable {
    private static final long serialVersionUID = 2010112020200105L;

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("deeplink")
    private String f12112a;

    @SerializedName("duration")
    private long b;

    @SerializedName("description")
    private String c;

    @SerializedName("category")
    private String d;

    @SerializedName("date")
    private String e;

    @SerializedName("mediaUrl")
    private String f;

    @SerializedName("iconUrl")
    private String g;

    @SerializedName("minibarDeeplink")
    private String h;

    @SerializedName("mediaId")
    private String i;

    @SerializedName("lastPosition")
    private int j;

    @SerializedName("title")
    private String k;

    @SerializedName("subtitle")
    private String m;

    public String n() {
        return this.k;
    }

    public void h(String str) {
        this.k = str;
    }

    public String h() {
        return this.i;
    }

    public void j(String str) {
        this.i = str;
    }

    public String f() {
        return this.m;
    }

    public void f(String str) {
        this.m = str;
    }

    public void d(String str) {
        this.c = str;
    }

    public String g() {
        return this.f;
    }

    public void g(String str) {
        this.f = str;
    }

    public String e() {
        return this.g;
    }

    public void b(String str) {
        this.g = str;
    }

    public String d() {
        return this.e;
    }

    public void e(String str) {
        this.e = str;
    }

    public long a() {
        return this.b;
    }

    public void d(long j) {
        this.b = j;
    }

    public int j() {
        return this.j;
    }

    public void b(int i) {
        this.j = i;
    }

    public void a(String str) {
        this.f12112a = str;
    }

    public String b() {
        return this.f12112a;
    }

    public String i() {
        return this.h;
    }

    public void c(String str) {
        this.d = str;
    }

    public String c() {
        return this.d;
    }

    public int hashCode() {
        return this.i.hashCode();
    }

    public boolean equals(Object obj) {
        String str;
        if ((obj instanceof enq) && (str = this.i) != null && this.f != null) {
            enq enqVar = (enq) obj;
            if (str.equals(enqVar.h()) && this.f.equals(enqVar.g())) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return "{\"title\":\"" + this.k + "\",\"mediaId\":\"" + this.i + "\",\"subtitle\":\"" + this.m + "\",\"description\":\"" + this.c + "\",\"duration\":" + this.b + ",\"mediaUrl\":\"" + this.f + "\",\"iconUrl\":\"" + this.g + "\",\"date\":\"" + this.e + "\",\"lastPosition\":\"" + this.j + "\",\"deeplink\":\"" + this.f12112a + "\",\"minibarDeeplink\":\"" + this.h + "\",\"category\":\"" + this.d + "\"}";
    }
}
