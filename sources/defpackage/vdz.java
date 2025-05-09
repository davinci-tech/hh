package defpackage;

import java.net.InetSocketAddress;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.eclipse.californium.scandium.dtls.AlertMessage;
import org.eclipse.californium.scandium.dtls.ChangeCipherSpecMessage;
import org.eclipse.californium.scandium.dtls.ConnectionIdGenerator;
import org.eclipse.californium.scandium.dtls.ContentType;
import org.eclipse.californium.scandium.dtls.DTLSConnectionState;
import org.eclipse.californium.scandium.dtls.DTLSMessage;
import org.eclipse.californium.scandium.dtls.HandshakeMessage;
import org.eclipse.californium.scandium.dtls.HandshakeType;
import org.slf4j.Logger;

/* loaded from: classes7.dex */
public class vdz {
    private final int c;
    private final boolean d;
    private vcp e;
    private final long f;
    private byte[] g;
    private InetSocketAddress h;
    private int i;
    private DTLSMessage j;
    private ContentType k;
    private InetSocketAddress l;
    private final vdu m;
    private boolean n;
    private final long o;
    private static final Logger b = vha.a((Class<?>) vdz.class);

    /* renamed from: a, reason: collision with root package name */
    private static final byte[] f17689a = {-1, -1, -1, -1, -1, -1, -1, -1};

    vdz(ContentType contentType, vdu vduVar, int i, long j, vcp vcpVar, byte[] bArr, long j2, boolean z) {
        this(vduVar, i, j, j2, z);
        if (contentType == null) {
            throw new NullPointerException("Type must not be null");
        }
        if (bArr == null) {
            throw new NullPointerException("Fragment bytes must not be null");
        }
        this.k = contentType;
        this.e = vcpVar;
        this.g = bArr;
    }

    public vdz(ContentType contentType, int i, DTLSMessage dTLSMessage, vcn vcnVar, boolean z, int i2) throws GeneralSecurityException {
        this(vdu.f17688a, i, (vcnVar == null || i < 0) ? 0L : vcnVar.e(i), 0L, false);
        if (dTLSMessage == null) {
            throw new NullPointerException("Fragment must not be null");
        }
        if (vcnVar == null) {
            throw new NullPointerException("Context must not be null");
        }
        b(contentType);
        if (z) {
            this.e = vcnVar.j();
            this.n = vcnVar.m();
            this.i = i2;
        }
        c(vcnVar.c(i), dTLSMessage);
        if (this.g == null) {
            throw new IllegalArgumentException("Fragment missing encoded bytes!");
        }
    }

    public vdz(ContentType contentType, vdu vduVar, long j, DTLSMessage dTLSMessage) {
        this(vduVar, 0, j, 0L, false);
        if (dTLSMessage == null) {
            throw new NullPointerException("Fragment must not be null");
        }
        b(contentType);
        this.j = dTLSMessage;
        byte[] byteArray = dTLSMessage.toByteArray();
        this.g = byteArray;
        if (byteArray == null) {
            throw new IllegalArgumentException("Fragment missing encoded bytes!");
        }
    }

    private vdz(vdu vduVar, int i, long j, long j2, boolean z) {
        if (j > 281474976710655L) {
            throw new IllegalArgumentException("Sequence number must be 48 bits only! " + j);
        }
        if (j < 0) {
            throw new IllegalArgumentException("Sequence number must not be less than 0! " + j);
        }
        if (i < 0) {
            throw new IllegalArgumentException("Epoch must not be less than 0! " + i);
        }
        if (vduVar == null) {
            throw new NullPointerException("Version must not be null");
        }
        this.m = vduVar;
        this.c = i;
        this.o = j;
        this.f = j2;
        this.d = z;
    }

    public void c(InetSocketAddress inetSocketAddress, InetSocketAddress inetSocketAddress2) {
        if (this.h != null) {
            throw new IllegalStateException("Peer's address already available!");
        }
        if (inetSocketAddress == null) {
            throw new NullPointerException("Peer's address must not be null!");
        }
        this.h = inetSocketAddress;
        this.l = inetSocketAddress2;
    }

    public byte[] n() {
        boolean t = t();
        int length = this.g.length + 13;
        if (t) {
            length += this.e.b();
        }
        vbo vboVar = new vbo(length);
        if (t) {
            vboVar.b(ContentType.TLS12_CID.getCode(), 8);
        } else {
            vboVar.b(this.k.getCode(), 8);
        }
        vboVar.b(this.m.c(), 8);
        vboVar.b(this.m.b(), 8);
        vboVar.b(this.c, 16);
        vboVar.c(this.o, 48);
        if (t) {
            vboVar.d(this.e.c());
        }
        vboVar.b(this.g.length, 16);
        vboVar.d(this.g);
        return vboVar.c();
    }

    public int o() {
        return (t() ? this.e.b() : 0) + 13 + b();
    }

    public static List<vdz> d(vbn vbnVar, ConnectionIdGenerator connectionIdGenerator, long j) {
        vcp vcpVar;
        if (vbnVar == null) {
            throw new NullPointerException("Reader must not be null");
        }
        int a2 = vbnVar.a() / 8;
        ArrayList arrayList = new ArrayList();
        while (vbnVar.e()) {
            if (vbnVar.a() < 104) {
                b.debug("Received truncated DTLS record(s). Discarding ...");
                return arrayList;
            }
            int c = vbnVar.c(8);
            vdu c2 = vdu.c(vbnVar.c(8), vbnVar.c(8));
            int c3 = vbnVar.c(16);
            long d = vbnVar.d(48);
            if (c != ContentType.TLS12_CID.getCode()) {
                vcpVar = null;
            } else {
                if (connectionIdGenerator == null) {
                    b.debug("Received TLS_CID record, but cid is not supported. Discarding ...");
                    return arrayList;
                }
                if (connectionIdGenerator.useConnectionId()) {
                    try {
                        vcpVar = connectionIdGenerator.read(vbnVar);
                        if (vcpVar == null) {
                            b.debug("Received TLS_CID record, but cid is not matching. Discarding ...");
                            return arrayList;
                        }
                    } catch (RuntimeException e) {
                        b.debug("Received TLS_CID record, failed to read cid. Discarding ...", (Throwable) e);
                        return arrayList;
                    }
                } else {
                    b.debug("Received TLS_CID record, but cid is not used. Discarding ...");
                    return arrayList;
                }
            }
            vcp vcpVar2 = vcpVar;
            int c4 = vbnVar.c(16);
            int a3 = vbnVar.a() / 8;
            if (a3 < c4) {
                b.debug("Received truncated DTLS record(s) ({} bytes, but only {} available). {} records, {} bytes. Discarding ...", Integer.valueOf(c4), Integer.valueOf(a3), Integer.valueOf(arrayList.size()), Integer.valueOf(a2));
                return arrayList;
            }
            byte[] a4 = vbnVar.a(c4);
            ContentType typeByValue = ContentType.getTypeByValue(c);
            if (typeByValue == null) {
                b.debug("Received DTLS record of unsupported type [{}]. Discarding ...", Integer.valueOf(c));
            } else {
                arrayList.add(new vdz(typeByValue, c2, c3, d, vcpVar2, a4, j, !arrayList.isEmpty()));
            }
        }
        return arrayList;
    }

    public static vcp e(vbn vbnVar, ConnectionIdGenerator connectionIdGenerator) {
        if (vbnVar == null) {
            throw new NullPointerException("Reader must not be null");
        }
        if (connectionIdGenerator == null) {
            throw new NullPointerException("CID generator must not be null");
        }
        if (!connectionIdGenerator.useConnectionId()) {
            throw new IllegalArgumentException("CID generator must use CID");
        }
        if (vbnVar.a() < 104) {
            throw new IllegalArgumentException("Record too small for DTLS header");
        }
        if (vbnVar.c(8) != ContentType.TLS12_CID.getCode()) {
            return null;
        }
        vbnVar.e(80L);
        vcp read = connectionIdGenerator.read(vbnVar);
        int c = vbnVar.c(16);
        if (vbnVar.a() / 8 >= c) {
            return read;
        }
        throw new IllegalArgumentException("Record too small for DTLS length " + c);
    }

    protected void a(vbo vboVar) {
        vboVar.b(this.c, 16);
        vboVar.c(this.o, 48);
    }

    protected byte[] c(int i) {
        if (!t()) {
            return a(i);
        }
        if (this.n) {
            return d(i);
        }
        return b(i);
    }

    protected byte[] b(int i) {
        vbo vboVar = new vbo(this.e.b() + 23);
        vboVar.d(f17689a);
        vboVar.b(ContentType.TLS12_CID.getCode(), 8);
        vboVar.b(this.e.b(), 8);
        vboVar.b(ContentType.TLS12_CID.getCode(), 8);
        vboVar.b(this.m.c(), 8);
        vboVar.b(this.m.b(), 8);
        vboVar.b(this.c, 16);
        vboVar.c(this.o, 48);
        vboVar.d(this.e.c());
        vboVar.b(i, 16);
        return vboVar.c();
    }

    protected byte[] d(int i) {
        vbo vboVar = new vbo(this.e.b() + 14);
        vboVar.b(this.c, 16);
        vboVar.c(this.o, 48);
        vboVar.b(ContentType.TLS12_CID.getCode(), 8);
        vboVar.b(this.m.c(), 8);
        vboVar.b(this.m.b(), 8);
        vboVar.d(this.e.c());
        vboVar.b(this.e.b(), 8);
        vboVar.b(i, 16);
        return vboVar.c();
    }

    protected byte[] a(int i) {
        vbo vboVar = new vbo(13);
        vboVar.b(this.c, 16);
        vboVar.c(this.o, 48);
        vboVar.b(this.k.getCode(), 8);
        vboVar.b(this.m.c(), 8);
        vboVar.b(this.m.b(), 8);
        vboVar.b(i, 16);
        return vboVar.c();
    }

    public boolean l() {
        return this.d;
    }

    public boolean k() {
        if (this.c > 0 || this.k != ContentType.HANDSHAKE) {
            return false;
        }
        byte[] bArr = this.g;
        return bArr.length != 0 && HandshakeType.getTypeByCode(bArr[0]) == HandshakeType.CLIENT_HELLO;
    }

    public boolean m() {
        return this.j != null;
    }

    public ContentType j() {
        return this.k;
    }

    public int e() {
        return this.c;
    }

    public long h() {
        return this.o;
    }

    public int b() {
        return this.g.length;
    }

    public InetSocketAddress i() {
        return this.h;
    }

    public InetSocketAddress g() {
        return this.l;
    }

    public vcp d() {
        return this.e;
    }

    public long f() {
        return this.f;
    }

    public byte[] a() {
        return this.g;
    }

    public DTLSMessage c() {
        DTLSMessage dTLSMessage = this.j;
        if (dTLSMessage != null) {
            return dTLSMessage;
        }
        throw new IllegalStateException("fragment not decoded!");
    }

    public void c(boolean z) {
        this.n = z;
    }

    public void a(DTLSConnectionState dTLSConnectionState) throws GeneralSecurityException, vdb {
        if (this.j != null) {
            b.error("DTLS read state already applied!");
            throw new IllegalArgumentException("DTLS read state already applied!");
        }
        ContentType contentType = this.k;
        byte[] decrypt = dTLSConnectionState.decrypt(this, this.g);
        if (ContentType.TLS12_CID == this.k) {
            int length = decrypt.length - 1;
            while (length >= 0 && decrypt[length] == 0) {
                length--;
            }
            if (length < 0) {
                throw new GeneralSecurityException("no inner type!");
            }
            byte b2 = decrypt[length];
            ContentType typeByValue = ContentType.getTypeByValue(b2);
            if (typeByValue == null) {
                throw new GeneralSecurityException("unknown inner type! " + ((int) b2));
            }
            decrypt = Arrays.copyOf(decrypt, length);
            contentType = typeByValue;
        }
        int i = AnonymousClass1.b[contentType.ordinal()];
        if (i == 1) {
            this.j = AlertMessage.d(decrypt);
        } else if (i == 2) {
            this.j = vcf.c(decrypt);
        } else if (i == 3) {
            this.j = ChangeCipherSpecMessage.a(decrypt);
        } else if (i == 4) {
            this.j = HandshakeMessage.fromByteArray(decrypt);
        } else {
            b.debug("Cannot decrypt message of unsupported type [{}]", this.k);
        }
        this.k = contentType;
    }

    /* renamed from: vdz$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] b;

        static {
            int[] iArr = new int[ContentType.values().length];
            b = iArr;
            try {
                iArr[ContentType.ALERT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                b[ContentType.APPLICATION_DATA.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                b[ContentType.CHANGE_CIPHER_SPEC.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                b[ContentType.HANDSHAKE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private void c(DTLSConnectionState dTLSConnectionState, DTLSMessage dTLSMessage) throws GeneralSecurityException {
        byte[] byteArray = dTLSMessage.toByteArray();
        if (byteArray == null) {
            throw new NullPointerException("fragment must not return null");
        }
        if (t()) {
            int length = byteArray.length;
            byteArray = Arrays.copyOf(byteArray, length + 1 + this.i);
            byteArray[length] = (byte) this.k.getCode();
        }
        this.g = dTLSConnectionState.encrypt(this, byteArray);
        this.j = dTLSMessage;
    }

    boolean t() {
        vcp vcpVar = this.e;
        return (vcpVar == null || vcpVar.d()) ? false : true;
    }

    private void b(ContentType contentType) {
        if (contentType == null) {
            throw new NullPointerException("Type must not be null");
        }
        int i = AnonymousClass1.b[contentType.ordinal()];
        if (i == 1 || i == 2 || i == 3 || i == 4) {
            this.k = contentType;
        } else {
            throw new IllegalArgumentException("Not supported content type: " + contentType);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("==[ DTLS Record ]==============================================");
        sb.append(vcb.a());
        sb.append("Content Type: ");
        sb.append(this.k);
        sb.append(vcb.a());
        sb.append("Peer address: ");
        sb.append(i());
        sb.append(vcb.a());
        sb.append("Version: ");
        sb.append(this.m.c());
        sb.append(", ");
        sb.append(this.m.b());
        sb.append(vcb.a());
        sb.append("Epoch: ");
        sb.append(this.c);
        sb.append(vcb.a());
        sb.append("Sequence Number: ");
        sb.append(this.o);
        sb.append(vcb.a());
        if (this.e != null) {
            sb.append("connection id: ");
            sb.append(this.e.e());
            sb.append(vcb.a());
        }
        sb.append("Length: ");
        sb.append(this.g.length);
        sb.append(" bytes");
        sb.append(vcb.a());
        sb.append("Fragment:");
        sb.append(vcb.a());
        DTLSMessage dTLSMessage = this.j;
        if (dTLSMessage != null) {
            sb.append(dTLSMessage.toString(1));
        } else {
            sb.append("fragment is not decrypted yet");
            sb.append(vcb.a());
        }
        sb.append("===============================================================");
        sb.append(vcb.a());
        return sb.toString();
    }
}
