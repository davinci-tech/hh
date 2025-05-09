package defpackage;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes7.dex */
public class iuf {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("metadata")
    private String f13612a;

    @SerializedName("startTime")
    private long b;

    @SerializedName("dataSource")
    private String c;

    @SerializedName("endTime")
    private long d;

    @SerializedName("serviceData")
    private String e;

    @SerializedName("timeZone")
    private String f;

    @SerializedName("version")
    private long i;

    @SerializedName("type")
    private int j;

    public long a() {
        return this.b;
    }

    public void b(long j) {
        this.b = j;
    }

    public long c() {
        return this.d;
    }

    public void d(long j) {
        this.d = j;
    }

    public int f() {
        return this.j;
    }

    public void d(int i) {
        this.j = i;
    }

    public String b() {
        return this.c;
    }

    public void e(String str) {
        this.c = str;
    }

    public String e() {
        return this.e;
    }

    public void b(String str) {
        this.e = str;
    }

    public String d() {
        return this.f;
    }

    public void a(String str) {
        this.f = str;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(16);
        sb.append("st=" + this.b);
        sb.append(",et=" + this.d);
        sb.append(",type=" + this.j);
        sb.append(",ds=" + this.c);
        sb.append(",sd=" + this.e);
        sb.append(",tz=" + this.f);
        sb.append(",ver=" + this.i);
        sb.append(",metadata=" + this.f13612a);
        return sb.toString();
    }
}
