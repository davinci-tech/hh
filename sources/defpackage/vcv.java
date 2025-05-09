package defpackage;

import com.huawei.operation.utils.Constants;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import javax.crypto.SecretKey;
import javax.security.auth.DestroyFailedException;
import javax.security.auth.Destroyable;
import org.eclipse.californium.scandium.dtls.CompressionMethod;
import org.eclipse.californium.scandium.dtls.DTLSConnectionState;
import org.eclipse.californium.scandium.dtls.cipher.CipherSuite;
import org.slf4j.Logger;

/* loaded from: classes7.dex */
public class vcv extends DTLSConnectionState {
    private static final Logger d = vha.a((Class<?>) vcv.class);
    private final SecretKey b;
    private final vff c;

    public vcv(CipherSuite cipherSuite, CompressionMethod compressionMethod, SecretKey secretKey, vff vffVar) {
        super(cipherSuite, compressionMethod);
        if (secretKey == null) {
            throw new NullPointerException("Encryption key must not be null!");
        }
        if (vffVar == null) {
            throw new NullPointerException("IV must not be null!");
        }
        this.b = vfh.a(secretKey);
        this.c = vfh.a(vffVar);
    }

    @Override // javax.security.auth.Destroyable
    public void destroy() throws DestroyFailedException {
        vfh.e(this.b);
        vfh.a((Destroyable) this.c);
    }

    @Override // javax.security.auth.Destroyable
    public boolean isDestroyed() {
        return vfh.d(this.c) && vfh.b(this.b);
    }

    @Override // org.eclipse.californium.scandium.dtls.DTLSConnectionState
    public byte[] encrypt(vdz vdzVar, byte[] bArr) throws GeneralSecurityException {
        vbo vboVar = new vbo(12, true);
        this.c.a(vboVar);
        vdzVar.a(vboVar);
        byte[] c = vboVar.c();
        byte[] c2 = vdzVar.c(bArr.length);
        Logger logger = d;
        if (logger.isTraceEnabled()) {
            logger.trace("encrypt: {} bytes", Integer.valueOf(bArr.length));
            logger.trace("nonce: {}", vcb.b(c));
            logger.trace("adata: {}", vcb.b(c2));
        }
        byte[] e = vem.e(this.cipherSuite, this.b, c, c2, bArr);
        System.arraycopy(c, this.cipherSuite.getFixedIvLength(), e, 0, this.cipherSuite.getRecordIvLength());
        vbj.b(c);
        logger.trace("==> {} bytes", Integer.valueOf(e.length));
        return e;
    }

    @Override // org.eclipse.californium.scandium.dtls.DTLSConnectionState
    public byte[] decrypt(vdz vdzVar, byte[] bArr) throws GeneralSecurityException {
        if (bArr == null) {
            throw new NullPointerException("Ciphertext must not be null");
        }
        int recordIvLength = this.cipherSuite.getRecordIvLength();
        int length = (bArr.length - recordIvLength) - this.cipherSuite.getMacLength();
        if (length <= 0) {
            throw new GeneralSecurityException("Ciphertext too short!");
        }
        byte[] c = vdzVar.c(length);
        vbo vboVar = new vbo(12, true);
        this.c.a(vboVar);
        vboVar.e(bArr, 0, recordIvLength);
        byte[] c2 = vboVar.c();
        Logger logger = d;
        if (logger.isTraceEnabled()) {
            logger.trace("decrypt: {} bytes", Integer.valueOf(length));
            logger.trace("nonce: {}", vcb.b(c2));
            logger.trace("adata: {}", vcb.b(c));
        }
        if (logger.isDebugEnabled() && "AES/CCM/NoPadding".equals(this.cipherSuite.getTransformation())) {
            byte[] copyOf = Arrays.copyOf(bArr, recordIvLength);
            vdzVar.a(vboVar);
            byte[] c3 = vboVar.c();
            if (!Arrays.equals(c3, copyOf)) {
                logger.debug("The explicit nonce used by the sender does not match the values provided in the DTLS record" + vcb.a() + "Used    : " + vcb.b(copyOf) + vcb.a() + "Expected: " + vcb.b(c3));
            }
        }
        byte[] e = vem.e(this.cipherSuite, this.b, c2, c, bArr, recordIvLength, bArr.length - recordIvLength);
        vbj.b(c2);
        return e;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("DtlsAeadConnectionState:");
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
        sb.append("IV: ");
        sb.append(this.c == null ? Constants.NULL : "not null");
        sb.append(vcb.a());
        sb.append(b);
        sb.append("Encryption key: ");
        sb.append(this.b == null ? Constants.NULL : "not null");
        sb.append(vcb.a());
        return sb.toString();
    }

    @Override // org.eclipse.californium.scandium.dtls.DTLSConnectionState
    public void writeTo(vbo vboVar) {
        vfg.a(vboVar, this.b);
        vfg.d(vboVar, this.c);
    }

    public vcv(CipherSuite cipherSuite, CompressionMethod compressionMethod, vbn vbnVar) {
        super(cipherSuite, compressionMethod);
        this.b = vfg.e(vbnVar);
        this.c = vfg.d(vbnVar);
    }
}
