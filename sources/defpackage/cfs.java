package defpackage;

/* loaded from: classes3.dex */
public class cfs {

    /* renamed from: a, reason: collision with root package name */
    private byte[] f689a;
    private int b;
    private int c;
    private int d;
    private int e;

    public int e() {
        return this.d;
    }

    public void a(int i) {
        this.d = i;
    }

    public int d() {
        return this.c;
    }

    public void c(int i) {
        this.c = i;
    }

    public int a() {
        return this.b;
    }

    public void d(int i) {
        this.b = i;
    }

    public byte[] c() {
        byte[] bArr = this.f689a;
        if (bArr != null) {
            return (byte[]) bArr.clone();
        }
        return null;
    }

    public void c(byte[] bArr) {
        if (bArr != null) {
            this.f689a = (byte[]) bArr.clone();
        }
    }

    public int b() {
        return this.e;
    }

    public void e(int i) {
        this.e = i;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("WspFileCommand{mServiceId=");
        stringBuffer.append(cvx.e(this.d));
        stringBuffer.append(", mCommandId=");
        stringBuffer.append(cvx.e(this.c));
        stringBuffer.append(", mDataLength=");
        stringBuffer.append(this.b);
        stringBuffer.append(", mDataContents=");
        stringBuffer.append(cvx.d(this.f689a));
        stringBuffer.append(", mErrorCode=");
        stringBuffer.append(this.e);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
