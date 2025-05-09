package defpackage;

import com.huawei.operation.utils.Constants;
import java.security.GeneralSecurityException;
import javax.crypto.SecretKey;
import javax.security.auth.DestroyFailedException;
import org.eclipse.californium.scandium.dtls.CompressionMethod;
import org.eclipse.californium.scandium.dtls.DTLSConnectionState;
import org.eclipse.californium.scandium.dtls.cipher.CipherSuite;

/* loaded from: classes7.dex */
public class vcr extends DTLSConnectionState {

    /* renamed from: a, reason: collision with root package name */
    private final SecretKey f17670a;
    private final SecretKey e;

    public vcr(CipherSuite cipherSuite, CompressionMethod compressionMethod, SecretKey secretKey, SecretKey secretKey2) {
        super(cipherSuite, compressionMethod);
        if (secretKey == null) {
            throw new NullPointerException("Encryption key must not be null!");
        }
        if (secretKey2 == null) {
            throw new NullPointerException("MAC key must not be null!");
        }
        this.f17670a = vfh.a(secretKey);
        this.e = vfh.a(secretKey2);
    }

    @Override // javax.security.auth.Destroyable
    public void destroy() throws DestroyFailedException {
        vfh.e(this.f17670a);
        vfh.e(this.e);
    }

    @Override // javax.security.auth.Destroyable
    public boolean isDestroyed() {
        return vfh.b(this.e) && vfh.b(this.f17670a);
    }

    @Override // org.eclipse.californium.scandium.dtls.DTLSConnectionState
    public byte[] encrypt(vdz vdzVar, byte[] bArr) throws GeneralSecurityException {
        return vek.d(this.cipherSuite, this.f17670a, this.e, vdzVar.c(bArr.length), bArr);
    }

    @Override // org.eclipse.californium.scandium.dtls.DTLSConnectionState
    public byte[] decrypt(vdz vdzVar, byte[] bArr) throws GeneralSecurityException {
        if (bArr == null) {
            throw new NullPointerException("Ciphertext must not be null");
        }
        if (bArr.length % this.cipherSuite.getRecordIvLength() != 0) {
            throw new GeneralSecurityException("Ciphertext doesn't fit block size!");
        }
        if (bArr.length < this.cipherSuite.getRecordIvLength() + this.cipherSuite.getMacLength() + 1) {
            throw new GeneralSecurityException("Ciphertext too short!");
        }
        return vek.b(this.cipherSuite, this.f17670a, this.e, vdzVar.c(0), bArr);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("DtlsBlockConnectionState:");
        sb.append(vcb.a());
        String b = vcb.b(1);
        sb.append(b);
        sb.append("Cipher suite: ");
        sb.append(this.cipherSuite);
        sb.append(vcb.a());
        sb.append(b);
        sb.append("Compression method: ");
        sb.append(this.compressionMethod);
        sb.append(vcb.a());
        sb.append(b);
        sb.append("MAC key: ");
        sb.append(this.e == null ? Constants.NULL : "not null");
        sb.append(vcb.a());
        sb.append(b);
        sb.append("Encryption key: ");
        sb.append(this.f17670a == null ? Constants.NULL : "not null");
        sb.append(vcb.a());
        return sb.toString();
    }

    @Override // org.eclipse.californium.scandium.dtls.DTLSConnectionState
    public void writeTo(vbo vboVar) {
        vfg.a(vboVar, this.e);
        vfg.a(vboVar, this.f17670a);
    }

    public vcr(CipherSuite cipherSuite, CompressionMethod compressionMethod, vbn vbnVar) {
        super(cipherSuite, compressionMethod);
        this.e = vfg.e(vbnVar);
        this.f17670a = vfg.e(vbnVar);
    }
}
