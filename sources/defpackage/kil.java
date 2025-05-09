package defpackage;

/* loaded from: classes5.dex */
public class kil {

    /* renamed from: a, reason: collision with root package name */
    private int f14396a;
    private int b;
    private int c;
    private String d;
    private String e;
    private long f;
    private int g;
    private String h;
    private String i;
    private int j;
    private int o;

    public kil() {
        this.o = -1;
        this.c = 0;
    }

    public kil(String str, int i) {
        this.c = 0;
        this.i = str;
        this.o = i;
    }

    public kil(String str, String str2, String str3, int i, int i2, int i3, int i4, int i5, long j) {
        this.g = i;
        this.e = str;
        this.b = i2;
        this.j = i4;
        this.i = str2;
        this.o = i3;
        this.h = str3;
        this.c = i5;
        this.f = j;
    }

    public String d() {
        return this.i;
    }

    public int j() {
        return this.o;
    }

    public int b() {
        return this.f14396a;
    }

    public void b(int i) {
        this.f14396a = i;
    }

    public int e() {
        return this.j;
    }

    public void e(int i) {
        this.j = i;
    }

    public String a() {
        return this.d;
    }

    public void a(String str) {
        this.d = str;
    }

    public String c() {
        return this.h;
    }

    public void d(String str) {
        this.h = str;
    }

    public long h() {
        return this.f;
    }

    public void e(long j) {
        this.f = j;
    }

    public String toString() {
        return "smsType(1-inbox 2-sent 3-draft56 4-outbox 5-failed 6-queued) is: " + this.j + ",sub_id is: " + this.o + ",isRead(0-unRead,1-read) is: " + this.c + ",receivedTime is: " + this.f + ",creator is: " + this.e + ",error_code is: " + this.b + ",sender mobile last 3 number is: " + khs.a(this.i);
    }
}
