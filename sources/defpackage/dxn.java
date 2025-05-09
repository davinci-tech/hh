package defpackage;

/* loaded from: classes3.dex */
public class dxn {

    /* renamed from: a, reason: collision with root package name */
    private long f11886a;
    private String b;
    private String c;
    private long d;
    private int e;
    private String j;

    public dxn(long j, long j2, String str, String str2) {
        this.f11886a = j;
        this.d = j2;
        this.c = str;
        this.j = str2;
    }

    public long c() {
        return this.f11886a;
    }

    public long a() {
        return this.d;
    }

    public String h() {
        return this.j;
    }

    public String d() {
        return this.c;
    }

    public String b() {
        return this.b;
    }

    public void c(String str) {
        this.b = str;
    }

    public int e() {
        return this.e;
    }

    public void e(int i) {
        this.e = i;
    }

    public String toString() {
        return "RecordReadOption{startTime=" + this.f11886a + ", endTime=" + this.d + ", type=" + this.j + ", count = " + this.e + ", sortOrder=" + this.b + '}';
    }
}
