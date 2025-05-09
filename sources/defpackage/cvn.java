package defpackage;

/* loaded from: classes3.dex */
public class cvn {
    private int c;
    private String f;

    /* renamed from: a, reason: collision with root package name */
    private long f11503a = -1;
    private int d = -1;
    private byte[] e = null;
    private Long b = null;

    public long a() {
        return this.f11503a;
    }

    public void e(long j) {
        this.f11503a = j;
    }

    public int e() {
        return this.d;
    }

    public void d(int i) {
        this.d = i;
    }

    public void c(byte[] bArr) {
        this.e = bArr;
    }

    public byte[] b() {
        return this.e;
    }

    public Long c() {
        return this.b;
    }

    public void a(Long l) {
        this.b = l;
    }

    public int d() {
        return this.c;
    }

    public void c(int i) {
        this.c = i;
    }

    public String j() {
        return this.f;
    }

    public void d(String str) {
        this.f = str;
    }

    public String toString() {
        return "SampleConfigInfo{mConfigId=" + this.f11503a + ", mConfigAction=" + this.d + ", mByteConfigData=" + blt.b(blq.d(this.e)) + ", mConfigModifyTime=" + this.b + ", mSupportCapability=" + this.c + ", mType=" + this.f + '}';
    }
}
