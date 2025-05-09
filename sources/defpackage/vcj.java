package defpackage;

import com.huawei.openalliance.ad.constant.Constants;
import java.security.GeneralSecurityException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.util.List;
import org.eclipse.californium.scandium.dtls.AlertMessage;
import org.eclipse.californium.scandium.dtls.HandshakeMessage;
import org.eclipse.californium.scandium.dtls.HandshakeType;
import org.eclipse.californium.scandium.dtls.SignatureAndHashAlgorithm;
import org.slf4j.Logger;

/* loaded from: classes7.dex */
public final class vcj extends HandshakeMessage {
    private static final Logger c = vha.a((Class<?>) vcj.class);
    private final byte[] d;
    private final SignatureAndHashAlgorithm e;

    public vcj(SignatureAndHashAlgorithm signatureAndHashAlgorithm, PrivateKey privateKey, List<HandshakeMessage> list) {
        this.e = signatureAndHashAlgorithm;
        this.d = d(signatureAndHashAlgorithm, privateKey, list);
    }

    private vcj(SignatureAndHashAlgorithm signatureAndHashAlgorithm, byte[] bArr) {
        this.e = signatureAndHashAlgorithm;
        this.d = bArr;
    }

    @Override // org.eclipse.californium.scandium.dtls.HandshakeMessage
    public HandshakeType getMessageType() {
        return HandshakeType.CERTIFICATE_VERIFY;
    }

    @Override // org.eclipse.californium.scandium.dtls.HandshakeMessage
    public int getMessageLength() {
        return this.d.length + 4;
    }

    @Override // org.eclipse.californium.scandium.dtls.HandshakeMessage
    public byte[] fragmentToByteArray() {
        vbo vboVar = new vbo(this.d.length + 4);
        vboVar.b(this.e.a().getCode(), 8);
        vboVar.b(this.e.e().getCode(), 8);
        vboVar.d(this.d, 16);
        return vboVar.c();
    }

    @Override // org.eclipse.californium.scandium.dtls.HandshakeMessage, org.eclipse.californium.scandium.dtls.DTLSMessage
    public String toString(int i) {
        return super.toString(i) + vcb.b(i + 1) + "Signature: " + this.e + Constants.LINK + vcb.b(this.d, (char) 0, 16) + vcb.a();
    }

    public static HandshakeMessage c(vbn vbnVar) {
        return new vcj(new SignatureAndHashAlgorithm(vbnVar.c(8), vbnVar.c(8)), vbnVar.h(16));
    }

    private static byte[] d(SignatureAndHashAlgorithm signatureAndHashAlgorithm, PrivateKey privateKey, List<HandshakeMessage> list) {
        byte[] bArr = vbj.c;
        try {
            Signature d = signatureAndHashAlgorithm.b().d();
            d.initSign(privateKey, vep.d());
            int i = 0;
            for (HandshakeMessage handshakeMessage : list) {
                d.update(handshakeMessage.toByteArray());
                c.trace("  [{}] - {}", Integer.valueOf(i), handshakeMessage.getMessageType());
                i++;
            }
            return d.sign();
        } catch (Exception e) {
            c.error("Could not create signature.", (Throwable) e);
            return bArr;
        }
    }

    public void c(PublicKey publicKey, List<HandshakeMessage> list) throws vdb {
        try {
            Signature d = this.e.b().d();
            d.initVerify(publicKey);
            int i = 0;
            for (HandshakeMessage handshakeMessage : list) {
                d.update(handshakeMessage.toByteArray());
                c.trace("  [{}] - {}", Integer.valueOf(i), handshakeMessage.getMessageType());
                i++;
            }
            if (d.verify(this.d)) {
                if (vbu.b() && this.e.e() == SignatureAndHashAlgorithm.SignatureAlgorithm.ECDSA) {
                    vbm.b(this.d, publicKey);
                    return;
                }
                return;
            }
        } catch (GeneralSecurityException e) {
            c.error("Could not verify the client's signature.", (Throwable) e);
        }
        throw new vdb("The client's CertificateVerify message could not be verified.", new AlertMessage(AlertMessage.AlertLevel.FATAL, AlertMessage.AlertDescription.DECRYPT_ERROR));
    }
}
