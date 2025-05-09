package defpackage;

/* loaded from: classes5.dex */
public class jzv {

    /* renamed from: a, reason: collision with root package name */
    private long f14230a;
    private int b;
    private int c;
    private String d;
    private String e;

    public jzv(String str, String str2, int i, int i2, long j) {
        this.e = str;
        this.d = str2;
        this.c = i;
        this.b = i2;
        this.f14230a = j;
    }

    public String d() {
        return this.e;
    }

    public String b() {
        return this.d;
    }

    public int a() {
        return this.c;
    }

    public void d(int i) {
        this.c = i;
    }

    public long c() {
        return this.f14230a;
    }

    public void d(long j) {
        this.f14230a = j;
    }

    public int e() {
        return this.b;
    }

    public String toString() {
        return "DeviceSyncStateBean{deviceId='" + this.e + "', tableName='" + this.d + "', syncAllFlag=" + this.c + ", syncState=" + this.b + ", lastSyncTimestamp=" + this.f14230a + '}';
    }
}
