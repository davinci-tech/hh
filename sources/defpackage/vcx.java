package defpackage;

import com.huawei.openalliance.ad.constant.Constants;
import java.security.GeneralSecurityException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import org.eclipse.californium.scandium.dtls.AlertMessage;
import org.eclipse.californium.scandium.dtls.ECDHServerKeyExchange;
import org.eclipse.californium.scandium.dtls.HandshakeMessage;
import org.eclipse.californium.scandium.dtls.SignatureAndHashAlgorithm;
import org.eclipse.californium.scandium.dtls.cipher.XECDHECryptography;
import org.slf4j.Logger;

/* loaded from: classes7.dex */
public final class vcx extends ECDHServerKeyExchange {
    private static final Logger e = vha.a((Class<?>) vcx.class);
    private final byte[] c;
    private final SignatureAndHashAlgorithm d;

    public vcx(SignatureAndHashAlgorithm signatureAndHashAlgorithm, XECDHECryptography xECDHECryptography, PrivateKey privateKey, vdv vdvVar, vdv vdvVar2) throws vdb {
        super(xECDHECryptography.i(), xECDHECryptography.g());
        if (signatureAndHashAlgorithm == null) {
            throw new NullPointerException("signature and hash algorithm cannot be null");
        }
        this.d = signatureAndHashAlgorithm;
        try {
            Signature d = signatureAndHashAlgorithm.b().d();
            d.initSign(privateKey, vep.d());
            e(d, vdvVar, vdvVar2);
            this.c = d.sign();
        } catch (GeneralSecurityException e2) {
            throw new vdb(String.format("Server failed to sign key exchange: %s", e2.getMessage()), new AlertMessage(AlertMessage.AlertLevel.FATAL, AlertMessage.AlertDescription.ILLEGAL_PARAMETER));
        }
    }

    private vcx(SignatureAndHashAlgorithm signatureAndHashAlgorithm, XECDHECryptography.SupportedGroup supportedGroup, byte[] bArr, byte[] bArr2) {
        super(supportedGroup, bArr);
        if (signatureAndHashAlgorithm == null && bArr2 != null) {
            throw new NullPointerException("signature and hash algorithm cannot be null");
        }
        if (signatureAndHashAlgorithm != null && bArr2 == null) {
            throw new NullPointerException("signature cannot be null");
        }
        this.d = signatureAndHashAlgorithm;
        this.c = bArr2;
    }

    @Override // org.eclipse.californium.scandium.dtls.HandshakeMessage
    public int getMessageLength() {
        byte[] bArr = this.c;
        return getNamedCurveLength() + (bArr == null ? 0 : bArr.length + 4);
    }

    @Override // org.eclipse.californium.scandium.dtls.HandshakeMessage
    public byte[] fragmentToByteArray() {
        vbo vboVar = new vbo();
        writeNamedCurve(vboVar);
        if (this.c != null) {
            vboVar.b(this.d.a().getCode(), 8);
            vboVar.b(this.d.e().getCode(), 8);
            vboVar.d(this.c, 16);
        }
        return vboVar.c();
    }

    public static HandshakeMessage b(vbn vbnVar) throws vdb {
        SignatureAndHashAlgorithm signatureAndHashAlgorithm;
        byte[] bArr;
        ECDHServerKeyExchange.c readNamedCurve = readNamedCurve(vbnVar);
        if (vbnVar.e()) {
            signatureAndHashAlgorithm = new SignatureAndHashAlgorithm(vbnVar.c(8), vbnVar.c(8));
            bArr = vbnVar.h(16);
        } else {
            signatureAndHashAlgorithm = null;
            bArr = null;
        }
        return new vcx(signatureAndHashAlgorithm, readNamedCurve.b, readNamedCurve.d, bArr);
    }

    public void c(PublicKey publicKey, vdv vdvVar, vdv vdvVar2) throws vdb {
        if (this.c == null) {
            throw new vdb("The server's ECDHE key exchange message has no signature.", new AlertMessage(AlertMessage.AlertLevel.FATAL, AlertMessage.AlertDescription.DECRYPT_ERROR));
        }
        try {
            Signature d = this.d.b().d();
            d.initVerify(publicKey);
            e(d, vdvVar, vdvVar2);
            if (d.verify(this.c)) {
                if (vbu.b() && this.d.e() == SignatureAndHashAlgorithm.SignatureAlgorithm.ECDSA) {
                    vbm.b(this.c, publicKey);
                    return;
                }
                return;
            }
        } catch (GeneralSecurityException e2) {
            e.error("Could not verify the server's signature.", (Throwable) e2);
        }
        throw new vdb("The server's ECDHE key exchange message's signature could not be verified.", new AlertMessage(AlertMessage.AlertLevel.FATAL, AlertMessage.AlertDescription.DECRYPT_ERROR));
    }

    private void e(Signature signature, vdv vdvVar, vdv vdvVar2) throws SignatureException {
        signature.update(vdvVar.c());
        signature.update(vdvVar2.c());
        updateSignatureForNamedCurve(signature);
    }

    @Override // org.eclipse.californium.scandium.dtls.ECDHServerKeyExchange, org.eclipse.californium.scandium.dtls.HandshakeMessage, org.eclipse.californium.scandium.dtls.DTLSMessage
    public String toString(int i) {
        String eCDHServerKeyExchange = super.toString(i);
        if (this.c == null) {
            return eCDHServerKeyExchange;
        }
        return eCDHServerKeyExchange + vcb.b(i + 1) + "Signature: " + this.d + Constants.LINK + vcb.b(this.c, (char) 0, 16) + vcb.a();
    }
}
