package defpackage;

/* loaded from: classes7.dex */
public class urh {

    /* renamed from: a, reason: collision with root package name */
    protected int f17514a;
    protected String b;
    protected byte[] c;
    protected String d;
    protected byte[] e;

    public urh() {
        this.b = null;
        this.d = "UTF-8";
        this.e = null;
        this.f17514a = 1000;
        this.c = null;
    }

    public urh(String str, String str2, byte[] bArr, int i) {
        this(str, str2, bArr, i, null);
    }

    public urh(String str, String str2, byte[] bArr, int i, byte[] bArr2) {
        this.b = str;
        this.d = str2;
        this.e = bArr;
        this.f17514a = i;
        this.c = bArr2;
    }

    public int e() {
        return this.f17514a;
    }

    public byte[] b() {
        return this.e;
    }

    public String a() {
        return this.b;
    }
}
