package org.eclipse.californium.scandium.dtls;

import defpackage.vbo;
import defpackage.vcb;
import defpackage.vha;
import org.slf4j.Logger;

/* loaded from: classes7.dex */
public abstract class HelloExtension {
    public static final int LENGTH_BITS = 16;
    protected static final Logger LOGGER = vha.a((Class<?>) HelloExtension.class);
    public static final int TYPE_BITS = 16;
    private final ExtensionType type;

    protected abstract int getExtensionLength();

    protected abstract void writeExtensionTo(vbo vboVar);

    public HelloExtension(ExtensionType extensionType) {
        if (extensionType == null) {
            throw new NullPointerException("extension type must not be null!");
        }
        this.type = extensionType;
    }

    public String toString(int i) {
        return vcb.b(i) + "Extension: " + this.type + " (" + this.type.getId() + "), " + getExtensionLength() + " bytes" + vcb.a();
    }

    public String toString() {
        return toString(0);
    }

    public final ExtensionType getType() {
        return this.type;
    }

    public int getLength() {
        return getExtensionLength() + 4;
    }

    public void writeTo(vbo vboVar) {
        vboVar.b(getType().getId(), 16);
        vboVar.b(getExtensionLength(), 16);
        writeExtensionTo(vboVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0061  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static org.eclipse.californium.scandium.dtls.HelloExtension readFrom(defpackage.vbn r4) throws defpackage.vdb {
        /*
            r0 = 16
            int r1 = r4.c(r0)
            int r0 = r4.c(r0)
            vbn r4 = r4.b(r0)
            org.eclipse.californium.scandium.dtls.HelloExtension$ExtensionType r0 = org.eclipse.californium.scandium.dtls.HelloExtension.ExtensionType.getExtensionTypeById(r1)
            if (r0 == 0) goto L5e
            int[] r2 = org.eclipse.californium.scandium.dtls.HelloExtension.AnonymousClass3.f15905a
            int r3 = r0.ordinal()
            r2 = r2[r3]
            switch(r2) {
                case 1: goto L59;
                case 2: goto L54;
                case 3: goto L4f;
                case 4: goto L4a;
                case 5: goto L45;
                case 6: goto L40;
                case 7: goto L3b;
                case 8: goto L36;
                case 9: goto L31;
                case 10: goto L2c;
                default: goto L1f;
            }
        L1f:
            org.eclipse.californium.scandium.dtls.HelloExtension$ExtensionType r2 = org.eclipse.californium.scandium.dtls.HelloExtension.ExtensionType.access$000(r0)
            org.eclipse.californium.scandium.dtls.HelloExtension$ExtensionType r3 = org.eclipse.californium.scandium.dtls.HelloExtension.ExtensionType.CONNECTION_ID
            if (r2 != r3) goto L5e
            vcq r0 = defpackage.vcq.a(r4, r0)
            goto L5f
        L2c:
            vcq r0 = defpackage.vcq.a(r4, r0)
            goto L5f
        L31:
            vdf r0 = defpackage.vdf.c(r4)
            goto L5f
        L36:
            vdx r0 = defpackage.vdx.a(r4)
            goto L5f
        L3b:
            veg r0 = defpackage.veg.b(r4)
            goto L5f
        L40:
            org.eclipse.californium.scandium.dtls.MaxFragmentLengthExtension r0 = org.eclipse.californium.scandium.dtls.MaxFragmentLengthExtension.b(r4)
            goto L5f
        L45:
            vea r0 = defpackage.vea.b(r4)
            goto L5f
        L4a:
            vch r0 = defpackage.vch.b(r4)
            goto L5f
        L4f:
            vef r0 = defpackage.vef.d(r4)
            goto L5f
        L54:
            org.eclipse.californium.scandium.dtls.HelloExtension r0 = org.eclipse.californium.scandium.dtls.SupportedPointFormatsExtension.d(r4)
            goto L5f
        L59:
            ven r0 = defpackage.ven.a(r4)
            goto L5f
        L5e:
            r0 = 0
        L5f:
            if (r0 == 0) goto L8e
            boolean r2 = r4.e()
            if (r2 != 0) goto L68
            goto L91
        L68:
            byte[] r4 = r4.i()
            int r4 = r4.length
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            java.lang.Integer r0 = java.lang.Integer.valueOf(r1)
            java.lang.Object[] r4 = new java.lang.Object[]{r4, r0}
            vdb r0 = new vdb
            java.lang.String r1 = "Too many bytes, %d left, hello extension not completely parsed! hello extension type %d"
            java.lang.String r4 = java.lang.String.format(r1, r4)
            org.eclipse.californium.scandium.dtls.AlertMessage r1 = new org.eclipse.californium.scandium.dtls.AlertMessage
            org.eclipse.californium.scandium.dtls.AlertMessage$AlertLevel r2 = org.eclipse.californium.scandium.dtls.AlertMessage.AlertLevel.FATAL
            org.eclipse.californium.scandium.dtls.AlertMessage$AlertDescription r3 = org.eclipse.californium.scandium.dtls.AlertMessage.AlertDescription.DECODE_ERROR
            r1.<init>(r2, r3)
            r0.<init>(r4, r1)
            throw r0
        L8e:
            r4.b()
        L91:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.eclipse.californium.scandium.dtls.HelloExtension.readFrom(vbn):org.eclipse.californium.scandium.dtls.HelloExtension");
    }

    /* renamed from: org.eclipse.californium.scandium.dtls.HelloExtension$3, reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f15905a;

        static {
            int[] iArr = new int[ExtensionType.values().length];
            f15905a = iArr;
            try {
                iArr[ExtensionType.ELLIPTIC_CURVES.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f15905a[ExtensionType.EC_POINT_FORMATS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f15905a[ExtensionType.SIGNATURE_ALGORITHMS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f15905a[ExtensionType.CLIENT_CERT_TYPE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f15905a[ExtensionType.SERVER_CERT_TYPE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f15905a[ExtensionType.MAX_FRAGMENT_LENGTH.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f15905a[ExtensionType.SERVER_NAME.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f15905a[ExtensionType.RECORD_SIZE_LIMIT.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f15905a[ExtensionType.EXTENDED_MASTER_SECRET.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f15905a[ExtensionType.CONNECTION_ID.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }

    public enum ExtensionType {
        SERVER_NAME(0, "server_name"),
        MAX_FRAGMENT_LENGTH(1, "max_fragment_length"),
        CLIENT_CERTIFICATE_URL(2, "client_certificate_url"),
        TRUSTED_CA_KEYS(3, "trusted_ca_keys"),
        TRUNCATED_HMAC(4, "truncated_hmac"),
        STATUS_REQUEST(5, "status_request"),
        USER_MAPPING(6, "user_mapping"),
        CLIENT_AUTHZ(7, "client_authz"),
        SERVER_AUTHZ(8, "server_authz"),
        CERT_TYPE(9, "cert_type"),
        ELLIPTIC_CURVES(10, "elliptic_curves"),
        EC_POINT_FORMATS(11, "ec_point_formats"),
        SRP(12, "srp"),
        SIGNATURE_ALGORITHMS(13, "signature_algorithms"),
        USE_SRTP(14, "use_srtp"),
        HEARTBEAT(15, "heartbeat"),
        APPLICATION_LAYER_PROTOCOL_NEGOTIATION(16, "application_layer_protocol_negotiation"),
        STATUS_REQUEST_V2(17, "status_request_v2"),
        SIGNED_CERTIFICATE_TIMESTAMP(18, "signed_certificate_timestamp"),
        CLIENT_CERT_TYPE(19, "client_certificate_type"),
        SERVER_CERT_TYPE(20, "server_certificate_type"),
        ENCRYPT_THEN_MAC(22, "encrypt_then_mac"),
        EXTENDED_MASTER_SECRET(23, "extended_master_secret"),
        RECORD_SIZE_LIMIT(28, "record_size_limit"),
        SESSION_TICKET_TLS(35, "SessionTicket TLS"),
        CONNECTION_ID(54, "Connection ID"),
        CONNECTION_ID_DEPRECATED(53, "Connection ID (deprecated)", CONNECTION_ID),
        RENEGOTIATION_INFO(65281, "renegotiation_info");

        private int id;
        private String name;
        private ExtensionType replacement;

        ExtensionType(int i, String str) {
            this.id = i;
            this.name = str;
        }

        ExtensionType(int i, String str, ExtensionType extensionType) {
            this.id = i;
            this.name = str;
            this.replacement = extensionType;
        }

        public static ExtensionType getExtensionTypeById(int i) {
            for (ExtensionType extensionType : values()) {
                if (extensionType.getId() == i) {
                    return extensionType;
                }
            }
            return null;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.name;
        }

        public int getId() {
            return this.id;
        }

        public ExtensionType getReplacementType() {
            return this.replacement;
        }
    }
}
