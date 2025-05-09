package org.eclipse.californium.scandium.dtls;

import defpackage.vbn;
import defpackage.vbo;
import defpackage.vcb;
import defpackage.vcr;
import defpackage.vcv;
import defpackage.vdz;
import defpackage.vff;
import java.security.GeneralSecurityException;
import javax.crypto.SecretKey;
import javax.security.auth.DestroyFailedException;
import javax.security.auth.Destroyable;
import org.eclipse.californium.scandium.dtls.cipher.CipherSuite;

/* loaded from: classes7.dex */
public abstract class DTLSConnectionState implements Destroyable {
    public static final DTLSConnectionState NULL = new DTLSConnectionState(CipherSuite.TLS_NULL_WITH_NULL_NULL, CompressionMethod.NULL) { // from class: org.eclipse.californium.scandium.dtls.DTLSConnectionState.4
        @Override // org.eclipse.californium.scandium.dtls.DTLSConnectionState
        public byte[] decrypt(vdz vdzVar, byte[] bArr) {
            return bArr;
        }

        @Override // javax.security.auth.Destroyable
        public void destroy() throws DestroyFailedException {
        }

        @Override // org.eclipse.californium.scandium.dtls.DTLSConnectionState
        public byte[] encrypt(vdz vdzVar, byte[] bArr) {
            return bArr;
        }

        @Override // javax.security.auth.Destroyable
        public boolean isDestroyed() {
            return false;
        }

        public final String toString() {
            return "DtlsNullConnectionState:" + vcb.a() + "\tCipher suite: " + this.cipherSuite + vcb.a() + "\tCompression method: " + this.compressionMethod;
        }

        @Override // org.eclipse.californium.scandium.dtls.DTLSConnectionState
        public void writeTo(vbo vboVar) {
            throw new RuntimeException("Not suppported!");
        }
    };
    protected final CipherSuite cipherSuite;
    protected final CompressionMethod compressionMethod;

    public abstract byte[] decrypt(vdz vdzVar, byte[] bArr) throws GeneralSecurityException;

    public abstract byte[] encrypt(vdz vdzVar, byte[] bArr) throws GeneralSecurityException;

    public abstract void writeTo(vbo vboVar);

    /* renamed from: org.eclipse.californium.scandium.dtls.DTLSConnectionState$3, reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] b;

        static {
            int[] iArr = new int[CipherSuite.CipherType.values().length];
            b = iArr;
            try {
                iArr[CipherSuite.CipherType.NULL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                b[CipherSuite.CipherType.BLOCK.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                b[CipherSuite.CipherType.AEAD.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public static DTLSConnectionState create(CipherSuite cipherSuite, CompressionMethod compressionMethod, SecretKey secretKey, vff vffVar, SecretKey secretKey2) {
        int i = AnonymousClass3.b[cipherSuite.getCipherType().ordinal()];
        if (i == 1) {
            return NULL;
        }
        if (i == 2) {
            return new vcr(cipherSuite, compressionMethod, secretKey, secretKey2);
        }
        if (i == 3) {
            return new vcv(cipherSuite, compressionMethod, secretKey, vffVar);
        }
        throw new IllegalArgumentException("cipher type " + cipherSuite.getCipherType() + " not supported!");
    }

    public static DTLSConnectionState fromReader(CipherSuite cipherSuite, CompressionMethod compressionMethod, vbn vbnVar) {
        int i = AnonymousClass3.b[cipherSuite.getCipherType().ordinal()];
        if (i == 2) {
            return new vcr(cipherSuite, compressionMethod, vbnVar);
        }
        if (i == 3) {
            return new vcv(cipherSuite, compressionMethod, vbnVar);
        }
        throw new IllegalArgumentException("cipher type " + cipherSuite.getCipherType() + " not supported!");
    }

    public DTLSConnectionState(CipherSuite cipherSuite, CompressionMethod compressionMethod) {
        if (cipherSuite == null) {
            throw new NullPointerException("Cipher suite must not be null");
        }
        if (compressionMethod == null) {
            throw new NullPointerException("Compression method must not be null");
        }
        this.cipherSuite = cipherSuite;
        this.compressionMethod = compressionMethod;
    }

    CipherSuite getCipherSuite() {
        return this.cipherSuite;
    }

    CompressionMethod getCompressionMethod() {
        return this.compressionMethod;
    }

    public boolean hasValidCipherSuite() {
        return this.cipherSuite.isValidForNegotiation();
    }
}
