package defpackage;

/* loaded from: classes5.dex */
public class jzt {

    /* renamed from: a, reason: collision with root package name */
    private String f14226a;
    private int b;
    private String d;

    public jzt(int i, String str, String str2) {
        this.b = i;
        this.f14226a = str;
        this.d = str2;
    }

    public String c() {
        return this.f14226a;
    }

    public String e() {
        return this.d;
    }

    public int a() {
        return this.b;
    }

    public String toString() {
        return "SimpleContactBean{rawContactId=" + this.b + ", rawContactUid='" + this.f14226a + "', rawContactFeature='" + this.d + "'}";
    }
}
