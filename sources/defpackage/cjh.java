package defpackage;

/* loaded from: classes3.dex */
public class cjh {

    /* renamed from: a, reason: collision with root package name */
    private String f743a;
    private byte[] c;
    private String e;

    public cjh(byte[] bArr, String str, String str2) {
        if (bArr == null) {
            this.c = null;
        } else {
            this.c = (byte[]) bArr.clone();
        }
        this.e = str;
        this.f743a = str2;
    }

    public byte[] d() {
        byte[] bArr = this.c;
        if (bArr != null) {
            return (byte[]) bArr.clone();
        }
        return null;
    }

    public String e() {
        return this.f743a;
    }

    public String b() {
        return this.e;
    }
}
