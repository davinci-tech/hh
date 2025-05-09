package org.eclipse.californium.scandium.dtls;

import defpackage.vbn;
import defpackage.vbo;
import defpackage.vcb;
import defpackage.vce;
import defpackage.vcj;
import defpackage.vck;
import defpackage.vcx;
import defpackage.vcy;
import defpackage.vcz;
import defpackage.vda;
import defpackage.vdb;
import defpackage.vdc;
import defpackage.vdd;
import defpackage.vde;
import defpackage.vdi;
import defpackage.vdk;
import defpackage.vdp;
import defpackage.vdr;
import defpackage.vds;
import defpackage.vee;
import defpackage.veh;
import defpackage.vha;
import java.util.Arrays;
import org.eclipse.californium.scandium.dtls.AlertMessage;
import org.eclipse.californium.scandium.dtls.cipher.CipherSuite;
import org.slf4j.Logger;

/* loaded from: classes7.dex */
public abstract class HandshakeMessage implements DTLSMessage {
    public static final int FRAGMENT_LENGTH_BITS = 24;
    public static final int FRAGMENT_OFFSET_BITS = 24;
    private static final Logger LOGGER = vha.a((Class<?>) HandshakeMessage.class);
    public static final int MESSAGE_HEADER_LENGTH_BYTES = 12;
    public static final int MESSAGE_LENGTH_BITS = 24;
    public static final int MESSAGE_SEQ_BITS = 16;
    public static final int MESSAGE_TYPE_BITS = 8;
    private byte[] byteArray;
    private int messageSeq;
    private HandshakeMessage nextHandshakeMessage;
    private byte[] rawMessage;

    public abstract byte[] fragmentToByteArray();

    public int getFragmentOffset() {
        return 0;
    }

    public abstract int getMessageLength();

    public abstract HandshakeType getMessageType();

    @Override // org.eclipse.californium.scandium.dtls.DTLSMessage
    public int size() {
        return getFragmentLength() + 12;
    }

    @Override // org.eclipse.californium.scandium.dtls.DTLSMessage
    public final ContentType getContentType() {
        return ContentType.HANDSHAKE;
    }

    @Override // org.eclipse.californium.scandium.dtls.DTLSMessage
    public String toString(int i) {
        StringBuilder sb = new StringBuilder();
        String b = vcb.b(i);
        sb.append(b);
        sb.append(getImplementationTypePrefix());
        sb.append("Handshake Message");
        sb.append(vcb.a());
        sb.append(b);
        sb.append("Type: ");
        sb.append(getMessageType());
        sb.append(vcb.a());
        sb.append(b);
        sb.append("Message Sequence No: ");
        sb.append(this.messageSeq);
        sb.append(vcb.a());
        sb.append(b);
        sb.append("Length: ");
        sb.append(getMessageLength());
        sb.append(" bytes");
        sb.append(vcb.a());
        return sb.toString();
    }

    public String toString() {
        return toString(0);
    }

    @Override // org.eclipse.californium.scandium.dtls.DTLSMessage
    public byte[] toByteArray() {
        byte[] bArr = this.rawMessage;
        if (bArr != null) {
            return bArr;
        }
        if (this.byteArray == null) {
            vbo vboVar = new vbo(getFragmentLength() + 12);
            writeTo(vboVar);
            this.byteArray = vboVar.c();
        }
        return this.byteArray;
    }

    public void writeTo(vbo vboVar) {
        vboVar.b(getMessageType().getCode(), 8);
        vboVar.b(getMessageLength(), 24);
        vboVar.b(this.messageSeq, 16);
        vboVar.b(getFragmentOffset(), 24);
        vboVar.b(getFragmentLength(), 24);
        vboVar.d(fragmentToByteArray());
    }

    protected void fragmentChanged() {
        this.byteArray = null;
    }

    public static HandshakeMessage fromByteArray(byte[] bArr) throws vdb {
        HandshakeMessage c;
        try {
            vbn vbnVar = new vbn(bArr, false);
            int i = 0;
            HandshakeMessage handshakeMessage = null;
            HandshakeMessage handshakeMessage2 = null;
            while (true) {
                int c2 = vbnVar.c(8);
                HandshakeType typeByCode = HandshakeType.getTypeByCode(c2);
                if (typeByCode == null) {
                    throw new vdb(String.format("Cannot parse unsupported message type %d", Integer.valueOf(c2)), new AlertMessage(AlertMessage.AlertLevel.FATAL, AlertMessage.AlertDescription.UNEXPECTED_MESSAGE));
                }
                LOGGER.trace("Parsing HANDSHAKE message of type [{}]", typeByCode);
                int c3 = vbnVar.c(24);
                int c4 = vbnVar.c(16);
                int c5 = vbnVar.c(24);
                int c6 = vbnVar.c(24);
                int a2 = vbnVar.a() / 8;
                if (c6 > a2) {
                    throw new vdb(String.format("Message %s fragment length %d exceeds available data %d", typeByCode, Integer.valueOf(c6), Integer.valueOf(a2)), new AlertMessage(AlertMessage.AlertLevel.FATAL, AlertMessage.AlertDescription.DECODE_ERROR));
                }
                vbn b = vbnVar.b(c6);
                int length = bArr.length - (vbnVar.a() / 8);
                if (c3 != c6) {
                    int i2 = c6 + c5;
                    if (i2 > c3) {
                        throw new vdb(String.format("Message %s fragment %d exceeds overall length %d", typeByCode, Integer.valueOf(i2), Integer.valueOf(c3)), new AlertMessage(AlertMessage.AlertLevel.FATAL, AlertMessage.AlertDescription.DECODE_ERROR));
                    }
                    c = new vdc(typeByCode, c3, c4, c5, b.i());
                } else {
                    if (c5 != 0) {
                        throw new vdb(String.format("Message %s unexpected fragment offset", typeByCode), new AlertMessage(AlertMessage.AlertLevel.FATAL, AlertMessage.AlertDescription.DECODE_ERROR));
                    }
                    try {
                        c = fromReader(typeByCode, b, null);
                    } catch (a unused) {
                        c = vdd.c(typeByCode);
                    }
                    c.rawMessage = Arrays.copyOfRange(bArr, i, length);
                    c.setMessageSeq(c4);
                }
                if (handshakeMessage == null) {
                    handshakeMessage = c;
                } else {
                    handshakeMessage2.setNextHandshakeMessage(c);
                }
                if (!vbnVar.e()) {
                    return handshakeMessage;
                }
                handshakeMessage2 = c;
                i = length;
            }
        } catch (IllegalArgumentException e) {
            LOGGER.debug("Handshake message malformed", (Throwable) e);
            throw new vdb("Handshake message malformed, " + e.getMessage(), new AlertMessage(AlertMessage.AlertLevel.FATAL, AlertMessage.AlertDescription.DECODE_ERROR));
        }
    }

    public static HandshakeMessage fromGenericHandshakeMessage(vdd vddVar, vdi vdiVar) throws vdb {
        try {
            HandshakeType messageType = vddVar.getMessageType();
            LOGGER.trace("Parsing HANDSHAKE message of type [{}]", messageType);
            byte[] byteArray = vddVar.toByteArray();
            HandshakeMessage fromReader = fromReader(messageType, new vbn(vddVar.fragmentToByteArray(), false), vdiVar);
            fromReader.rawMessage = byteArray;
            fromReader.setMessageSeq(vddVar.getMessageSeq());
            fromReader.setNextHandshakeMessage(vddVar.getNextHandshakeMessage());
            return fromReader;
        } catch (IllegalArgumentException e) {
            LOGGER.debug("Handshake message malformed", (Throwable) e);
            throw new vdb("Handshake message malformed, " + e.getMessage(), new AlertMessage(AlertMessage.AlertLevel.FATAL, AlertMessage.AlertDescription.DECODE_ERROR));
        }
    }

    private static HandshakeMessage fromReader(HandshakeType handshakeType, vbn vbnVar, vdi vdiVar) throws vdb {
        HandshakeMessage vdkVar;
        AnonymousClass2 anonymousClass2 = null;
        String str = "HandshakeParameter must not be null!";
        switch (AnonymousClass2.b[handshakeType.ordinal()]) {
            case 1:
                vdkVar = new vdk();
                break;
            case 2:
                vdkVar = vck.a(vbnVar);
                break;
            case 3:
                vdkVar = vee.d(vbnVar);
                break;
            case 4:
                vdkVar = vdp.d(vbnVar);
                break;
            case 5:
                if (vdiVar == null) {
                    throw new a(str, anonymousClass2);
                }
                vdkVar = vce.e(vbnVar, vdiVar.d());
                break;
            case 6:
                if (vdiVar == null) {
                    throw new a(str, anonymousClass2);
                }
                vdkVar = readServerKeyExchange(vbnVar, vdiVar.a());
                break;
            case 7:
                vdkVar = CertificateRequest.d(vbnVar);
                break;
            case 8:
                vdkVar = new veh();
                break;
            case 9:
                vdkVar = vcj.c(vbnVar);
                break;
            case 10:
                if (vdiVar == null) {
                    throw new a(str, anonymousClass2);
                }
                vdkVar = readClientKeyExchange(vbnVar, vdiVar.a());
                break;
            case 11:
                vdkVar = vde.c(vbnVar);
                break;
            default:
                throw new vdb(String.format("Cannot parse unsupported message type %s", handshakeType), new AlertMessage(AlertMessage.AlertLevel.FATAL, AlertMessage.AlertDescription.UNEXPECTED_MESSAGE));
        }
        if (vbnVar.e()) {
            throw new vdb(String.format("Too many bytes, %d left, message not completely parsed! message type %s", Integer.valueOf(vbnVar.a() / 8), handshakeType), new AlertMessage(AlertMessage.AlertLevel.FATAL, AlertMessage.AlertDescription.DECODE_ERROR));
        }
        return vdkVar;
    }

    /* renamed from: org.eclipse.californium.scandium.dtls.HandshakeMessage$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f15901a;
        static final /* synthetic */ int[] b;

        static {
            int[] iArr = new int[CipherSuite.KeyExchangeAlgorithm.values().length];
            f15901a = iArr;
            try {
                iArr[CipherSuite.KeyExchangeAlgorithm.EC_DIFFIE_HELLMAN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f15901a[CipherSuite.KeyExchangeAlgorithm.PSK.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f15901a[CipherSuite.KeyExchangeAlgorithm.ECDHE_PSK.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[HandshakeType.values().length];
            b = iArr2;
            try {
                iArr2[HandshakeType.HELLO_REQUEST.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                b[HandshakeType.CLIENT_HELLO.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                b[HandshakeType.SERVER_HELLO.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                b[HandshakeType.HELLO_VERIFY_REQUEST.ordinal()] = 4;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                b[HandshakeType.CERTIFICATE.ordinal()] = 5;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                b[HandshakeType.SERVER_KEY_EXCHANGE.ordinal()] = 6;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                b[HandshakeType.CERTIFICATE_REQUEST.ordinal()] = 7;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                b[HandshakeType.SERVER_HELLO_DONE.ordinal()] = 8;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                b[HandshakeType.CERTIFICATE_VERIFY.ordinal()] = 9;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                b[HandshakeType.CLIENT_KEY_EXCHANGE.ordinal()] = 10;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                b[HandshakeType.FINISHED.ordinal()] = 11;
            } catch (NoSuchFieldError unused14) {
            }
        }
    }

    private static HandshakeMessage readServerKeyExchange(vbn vbnVar, CipherSuite.KeyExchangeAlgorithm keyExchangeAlgorithm) throws vdb {
        int i = AnonymousClass2.f15901a[keyExchangeAlgorithm.ordinal()];
        if (i == 1) {
            return vcx.b(vbnVar);
        }
        if (i == 2) {
            return vdr.d(vbnVar);
        }
        if (i == 3) {
            return vda.b(vbnVar);
        }
        throw new vdb("Unsupported key exchange algorithm", new AlertMessage(AlertMessage.AlertLevel.FATAL, AlertMessage.AlertDescription.ILLEGAL_PARAMETER));
    }

    private static HandshakeMessage readClientKeyExchange(vbn vbnVar, CipherSuite.KeyExchangeAlgorithm keyExchangeAlgorithm) throws vdb {
        int i = AnonymousClass2.f15901a[keyExchangeAlgorithm.ordinal()];
        if (i == 1) {
            return vcy.b(vbnVar);
        }
        if (i == 2) {
            return vds.b(vbnVar);
        }
        if (i == 3) {
            return vcz.e(vbnVar);
        }
        throw new vdb("Unknown key exchange algorithm", new AlertMessage(AlertMessage.AlertLevel.FATAL, AlertMessage.AlertDescription.ILLEGAL_PARAMETER));
    }

    public int getFragmentLength() {
        return getMessageLength();
    }

    public int getMessageSeq() {
        return this.messageSeq;
    }

    public void setMessageSeq(int i) {
        if (this.byteArray != null) {
            throw new IllegalStateException("message is already serialized!");
        }
        if (i < 0 || i > 65535) {
            throw new IllegalArgumentException("Handshake message sequence number " + i + " out of range [0...65535]!");
        }
        this.messageSeq = i;
    }

    public void setNextHandshakeMessage(HandshakeMessage handshakeMessage) {
        this.nextHandshakeMessage = handshakeMessage;
    }

    public HandshakeMessage getNextHandshakeMessage() {
        return this.nextHandshakeMessage;
    }

    protected final byte[] getRawMessage() {
        return this.rawMessage;
    }

    static class a extends IllegalArgumentException {
        private static final long serialVersionUID = -5365688530126068164L;

        /* synthetic */ a(String str, AnonymousClass2 anonymousClass2) {
            this(str);
        }

        private a(String str) {
            super(str);
        }
    }

    protected String getImplementationTypePrefix() {
        return "";
    }
}
