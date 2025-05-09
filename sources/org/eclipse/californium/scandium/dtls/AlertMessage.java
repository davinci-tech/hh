package org.eclipse.californium.scandium.dtls;

import defpackage.vbn;
import defpackage.vbo;
import defpackage.vcb;
import defpackage.vdb;
import defpackage.vdu;
import java.io.Serializable;

/* loaded from: classes7.dex */
public final class AlertMessage implements DTLSMessage, Serializable {
    private static final long serialVersionUID = 1;
    private final AlertDescription b;
    private final vdu c;
    private final AlertLevel e;

    @Override // org.eclipse.californium.scandium.dtls.DTLSMessage
    public int size() {
        return 2;
    }

    public AlertMessage(AlertLevel alertLevel, AlertDescription alertDescription) {
        this(alertLevel, alertDescription, null);
    }

    public AlertMessage(AlertLevel alertLevel, AlertDescription alertDescription, vdu vduVar) {
        if (alertLevel == null) {
            throw new NullPointerException("Level must not be null");
        }
        if (alertDescription == null) {
            throw new NullPointerException("Description must not be null");
        }
        if (vduVar != null && alertDescription != AlertDescription.PROTOCOL_VERSION) {
            throw new IllegalArgumentException("Protocol version is only supported for that specific alert!");
        }
        this.e = alertLevel;
        this.b = alertDescription;
        this.c = vduVar;
    }

    public enum AlertLevel {
        WARNING(1),
        FATAL(2);

        private byte code;

        AlertLevel(int i) {
            this.code = (byte) i;
        }

        public byte getCode() {
            return this.code;
        }

        public static AlertLevel getLevelByCode(int i) {
            if (i == 1) {
                return WARNING;
            }
            if (i != 2) {
                return null;
            }
            return FATAL;
        }
    }

    public enum AlertDescription {
        CLOSE_NOTIFY(0, "close_notify"),
        UNEXPECTED_MESSAGE(10, "unexpected_message"),
        BAD_RECORD_MAC(20, "bad_record_mac"),
        DECRYPTION_FAILED_RESERVED(21, "decryption_failed"),
        RECORD_OVERFLOW(22, "record_overflow"),
        DECOMPRESSION_FAILURE(30, "decompression_failure"),
        HANDSHAKE_FAILURE(40, "handshake_failure"),
        NO_CERTIFICATE_RESERVED(41, "no_certificate"),
        BAD_CERTIFICATE(42, "bad_certificate"),
        UNSUPPORTED_CERTIFICATE(43, "unsupported_certificate"),
        CERTIFICATE_REVOKED(44, "certificate_revoked"),
        CERTIFICATE_EXPIRED(45, "certificate_expired"),
        CERTIFICATE_UNKNOWN(46, "certificate_unknown"),
        ILLEGAL_PARAMETER(47, "illegal_parameter"),
        UNKNOWN_CA(48, "unknown_ca"),
        ACCESS_DENIED(49, "access_denied"),
        DECODE_ERROR(50, "decode_error"),
        DECRYPT_ERROR(51, "decrypt_error"),
        EXPORT_RESTRICTION_RESERVED(60, "export_restriction"),
        PROTOCOL_VERSION(70, "protocol_version"),
        INSUFFICIENT_SECURITY(71, "insufficient_security"),
        INTERNAL_ERROR(80, "internal_error"),
        USER_CANCELED(90, "user_canceled"),
        NO_RENEGOTIATION(100, "no_negotiation"),
        UNSUPPORTED_EXTENSION(110, "unsupported_extension"),
        UNKNOWN_PSK_IDENTITY(115, "unknown_psk_identity");

        private byte code;
        private String description;

        AlertDescription(int i, String str) {
            this.code = (byte) i;
            this.description = str;
        }

        public byte getCode() {
            return this.code;
        }

        public String getDescription() {
            return this.description;
        }

        public static AlertDescription getDescriptionByCode(int i) {
            for (AlertDescription alertDescription : values()) {
                if (alertDescription.code == ((byte) i)) {
                    return alertDescription;
                }
            }
            return null;
        }
    }

    @Override // org.eclipse.californium.scandium.dtls.DTLSMessage
    public ContentType getContentType() {
        return ContentType.ALERT;
    }

    @Override // org.eclipse.californium.scandium.dtls.DTLSMessage
    public String toString(int i) {
        StringBuilder sb = new StringBuilder();
        String b = vcb.b(i);
        sb.append(b);
        sb.append("Alert Protocol");
        sb.append(vcb.a());
        sb.append(b);
        sb.append("Level: ");
        sb.append(this.e);
        sb.append(vcb.a());
        sb.append(b);
        sb.append("Description: ");
        sb.append(this.b);
        sb.append(vcb.a());
        if (this.c != null) {
            sb.append(b);
            sb.append("Protocol Version: ");
            sb.append(this.c);
            sb.append(vcb.a());
        }
        return sb.toString();
    }

    public String toString() {
        return toString(0);
    }

    @Override // org.eclipse.californium.scandium.dtls.DTLSMessage
    public byte[] toByteArray() {
        vbo vboVar = new vbo(2);
        vboVar.b(this.e.getCode(), 8);
        vboVar.b(this.b.getCode(), 8);
        return vboVar.c();
    }

    public static AlertMessage d(byte[] bArr) throws vdb {
        vbn vbnVar = new vbn(bArr);
        byte c = vbnVar.c();
        byte c2 = vbnVar.c();
        AlertLevel levelByCode = AlertLevel.getLevelByCode(c);
        AlertDescription descriptionByCode = AlertDescription.getDescriptionByCode(c2);
        if (levelByCode == null) {
            throw new vdb(String.format("Unknown alert level code [%d]", Byte.valueOf(c)), new AlertMessage(AlertLevel.FATAL, AlertDescription.DECODE_ERROR));
        }
        if (descriptionByCode == null) {
            throw new vdb(String.format("Unknown alert description code [%d]", Byte.valueOf(c2)), new AlertMessage(AlertLevel.FATAL, AlertDescription.DECODE_ERROR));
        }
        return new AlertMessage(levelByCode, descriptionByCode);
    }

    public vdu a() {
        return this.c;
    }

    public AlertLevel e() {
        return this.e;
    }

    public AlertDescription b() {
        return this.b;
    }

    public int hashCode() {
        return (this.e.code + (this.b.code & 255)) << 8;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AlertMessage alertMessage = (AlertMessage) obj;
        return this.b == alertMessage.b && this.e == alertMessage.e;
    }
}
