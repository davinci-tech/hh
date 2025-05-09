package defpackage;

import java.io.ByteArrayOutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import net.lingala.zip4j.crypto.PBKDF2.PRF;

/* loaded from: classes7.dex */
public class urf implements PRF {

    /* renamed from: a, reason: collision with root package name */
    private Mac f17513a;
    private int b;
    private String c;
    private ByteArrayOutputStream d = new ByteArrayOutputStream(4096);

    public urf(String str) {
        this.c = str;
        try {
            Mac mac = Mac.getInstance(str);
            this.f17513a = mac;
            this.b = mac.getMacLength();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Override // net.lingala.zip4j.crypto.PBKDF2.PRF
    public byte[] doFinal(byte[] bArr) {
        if (this.d.size() > 0) {
            b(0);
        }
        return this.f17513a.doFinal(bArr);
    }

    public byte[] b() {
        return e(0);
    }

    public byte[] e(int i) {
        if (this.d.size() > 0) {
            b(i);
        }
        return this.f17513a.doFinal();
    }

    @Override // net.lingala.zip4j.crypto.PBKDF2.PRF
    public int getHLen() {
        return this.b;
    }

    @Override // net.lingala.zip4j.crypto.PBKDF2.PRF
    public void init(byte[] bArr) {
        try {
            this.f17513a.init(new SecretKeySpec(bArr, this.c));
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    public void a(byte[] bArr, int i, int i2) {
        try {
            if (this.d.size() + i2 > 4096) {
                b(0);
            }
            this.d.write(bArr, i, i2);
        } catch (IllegalStateException e) {
            throw new RuntimeException(e);
        }
    }

    private void b(int i) {
        byte[] byteArray = this.d.toByteArray();
        int length = byteArray.length - i;
        int i2 = 0;
        while (i2 < length) {
            int i3 = i2 + 16;
            this.f17513a.update(byteArray, i2, i3 <= length ? 16 : length - i2);
            i2 = i3;
        }
        this.d.reset();
    }
}
