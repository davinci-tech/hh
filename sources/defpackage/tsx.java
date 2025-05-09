package defpackage;

import com.huawei.wisesecurity.kfs.crypto.cipher.CipherAlg;

/* loaded from: classes7.dex */
public class tsx {

    /* renamed from: a, reason: collision with root package name */
    private CipherAlg f17361a = CipherAlg.UNKNOWN;
    private byte[] c;
    private byte[] e;

    public byte[] e() {
        return tty.d(this.e);
    }

    public void a(byte[] bArr) {
        this.e = tty.d(bArr);
    }

    public byte[] c() {
        return tty.d(this.c);
    }

    public void b(byte[] bArr) {
        this.c = tty.d(bArr);
    }

    public CipherAlg d() {
        return this.f17361a;
    }

    public void b(CipherAlg cipherAlg) {
        this.f17361a = cipherAlg;
    }
}
