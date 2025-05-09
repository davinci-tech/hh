package org.eclipse.californium.scandium.dtls;

import com.huawei.openalliance.ad.constant.Constants;
import defpackage.vbn;
import defpackage.vbo;
import defpackage.vcb;
import defpackage.vdb;
import java.security.Signature;
import java.security.SignatureException;
import java.util.Arrays;
import org.eclipse.californium.scandium.dtls.AlertMessage;
import org.eclipse.californium.scandium.dtls.cipher.XECDHECryptography;

/* loaded from: classes7.dex */
public abstract class ECDHServerKeyExchange extends ServerKeyExchange {
    private static final int CURVE_TYPE_BITS = 8;
    private static final int NAMED_CURVE = 3;
    private static final int NAMED_CURVE_BITS = 16;
    private static final int PUBLIC_LENGTH_BITS = 8;
    private final byte[] encodedPoint;
    private final XECDHECryptography.SupportedGroup supportedGroup;

    public ECDHServerKeyExchange(XECDHECryptography.SupportedGroup supportedGroup, byte[] bArr) {
        if (supportedGroup == null) {
            throw new NullPointerException("Supported group (curve) must not be null!");
        }
        if (bArr == null) {
            throw new NullPointerException("encoded point must not be null!");
        }
        this.supportedGroup = supportedGroup;
        this.encodedPoint = bArr;
    }

    protected int getNamedCurveLength() {
        return this.encodedPoint.length + 4;
    }

    protected void writeNamedCurve(vbo vboVar) {
        vboVar.b(3, 8);
        vboVar.b(this.supportedGroup.getId(), 16);
        vboVar.d(this.encodedPoint, 8);
    }

    public static c readNamedCurve(vbn vbnVar) throws vdb {
        int c2 = vbnVar.c(8);
        if (c2 != 3) {
            throw new vdb(String.format("Curve type [%s] received in ServerKeyExchange message is unsupported", Integer.valueOf(c2)), new AlertMessage(AlertMessage.AlertLevel.FATAL, AlertMessage.AlertDescription.HANDSHAKE_FAILURE));
        }
        int c3 = vbnVar.c(16);
        XECDHECryptography.SupportedGroup fromId = XECDHECryptography.SupportedGroup.fromId(c3);
        if (fromId == null || !fromId.isUsable()) {
            throw new vdb(String.format("Server used unsupported elliptic curve (%d) for ECDH", Integer.valueOf(c3)), new AlertMessage(AlertMessage.AlertLevel.FATAL, AlertMessage.AlertDescription.ILLEGAL_PARAMETER));
        }
        return new c(fromId, vbnVar.h(8));
    }

    protected void updateSignatureForNamedCurve(Signature signature) throws SignatureException {
        int id = getSupportedGroup().getId();
        signature.update((byte) 3);
        signature.update((byte) (id >> 8));
        signature.update((byte) id);
        signature.update((byte) this.encodedPoint.length);
        signature.update(this.encodedPoint);
    }

    public XECDHECryptography.SupportedGroup getSupportedGroup() {
        return this.supportedGroup;
    }

    public byte[] getEncodedPoint() {
        byte[] bArr = this.encodedPoint;
        return Arrays.copyOf(bArr, bArr.length);
    }

    @Override // org.eclipse.californium.scandium.dtls.HandshakeMessage, org.eclipse.californium.scandium.dtls.DTLSMessage
    public String toString(int i) {
        return super.toString(i) + vcb.b(i + 1) + "Diffie-Hellman public key: " + this.supportedGroup.name() + Constants.LINK + vcb.b(this.encodedPoint, (char) 0, 16) + vcb.a();
    }

    public static class c {
        public final XECDHECryptography.SupportedGroup b;
        public final byte[] d;

        c(XECDHECryptography.SupportedGroup supportedGroup, byte[] bArr) {
            this.b = supportedGroup;
            this.d = bArr;
        }
    }
}
