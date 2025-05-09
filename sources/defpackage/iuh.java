package defpackage;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes7.dex */
public class iuh {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("relatedDataStartTime")
    private long f13614a;

    @SerializedName("relatedPackageName")
    private String b;

    @SerializedName("relatedDataType")
    private int c;

    @SerializedName("relatedDataDeviceUuid")
    private String d;

    @SerializedName("relatedDataEndTime")
    private long e;

    public String toString() {
        StringBuilder sb = new StringBuilder(16);
        sb.append("st=");
        sb.append(this.f13614a);
        sb.append(", et=");
        sb.append(this.e);
        sb.append(",type=");
        sb.append(this.c);
        sb.append(", duc=");
        sb.append(this.d);
        sb.append(", pn=");
        sb.append(this.b);
        return sb.toString();
    }

    public long c() {
        return this.f13614a;
    }

    public void e(long j) {
        this.f13614a = j;
    }

    public long e() {
        return this.e;
    }

    public void a(long j) {
        this.e = j;
    }

    public int a() {
        return this.c;
    }

    public void b(int i) {
        this.c = i;
    }

    public String d() {
        return this.d;
    }

    public void a(String str) {
        this.d = str;
    }

    public String b() {
        return this.b;
    }

    public void b(String str) {
        this.b = str;
    }
}
