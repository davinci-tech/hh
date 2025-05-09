package defpackage;

import com.huawei.wisesecurity.kfs.crypto.signer.SignAlg;

/* loaded from: classes7.dex */
public class ttj {

    /* renamed from: a, reason: collision with root package name */
    private byte[] f17373a;
    private byte[] c;
    private SignAlg e = SignAlg.UNKNOWN;

    public byte[] c() {
        return tty.d(this.f17373a);
    }

    public void a(byte[] bArr) {
        this.f17373a = tty.d(bArr);
    }

    public byte[] d() {
        return tty.d(this.c);
    }

    public void d(byte[] bArr) {
        this.c = tty.d(bArr);
    }

    public SignAlg e() {
        return this.e;
    }

    public void d(SignAlg signAlg) {
        this.e = signAlg;
    }
}
