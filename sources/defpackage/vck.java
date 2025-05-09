package defpackage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.crypto.Mac;
import org.eclipse.californium.scandium.dtls.AlertMessage;
import org.eclipse.californium.scandium.dtls.CertificateType;
import org.eclipse.californium.scandium.dtls.CompressionMethod;
import org.eclipse.californium.scandium.dtls.HandshakeType;
import org.eclipse.californium.scandium.dtls.HelloExtension;
import org.eclipse.californium.scandium.dtls.HelloHandshakeMessage;
import org.eclipse.californium.scandium.dtls.SignatureAndHashAlgorithm;
import org.eclipse.californium.scandium.dtls.SupportedPointFormatsExtension;
import org.eclipse.californium.scandium.dtls.cipher.CipherSuite;
import org.eclipse.californium.scandium.dtls.cipher.XECDHECryptography;

/* loaded from: classes7.dex */
public final class vck extends HelloHandshakeMessage {

    /* renamed from: a, reason: collision with root package name */
    private final List<CompressionMethod> f17665a;
    private byte[] b;
    private final List<CipherSuite> e;

    public vck(vdu vduVar, List<CipherSuite> list, List<SignatureAndHashAlgorithm> list2, List<CertificateType> list3, List<CertificateType> list4, List<XECDHECryptography.SupportedGroup> list5) {
        this(vduVar, vej.a(), list, list2, list3, list4, list5);
    }

    public vck(vdu vduVar, vct vctVar, List<SignatureAndHashAlgorithm> list, List<CertificateType> list2, List<CertificateType> list3, List<XECDHECryptography.SupportedGroup> list4) {
        this(vduVar, vctVar.n(), Arrays.asList(vctVar.e()), list, list2, list3, list4);
        e(vctVar.c());
    }

    private vck(vdu vduVar, vej vejVar, List<CipherSuite> list, List<SignatureAndHashAlgorithm> list2, List<CertificateType> list3, List<CertificateType> list4, List<XECDHECryptography.SupportedGroup> list5) {
        super(vduVar, vejVar);
        this.b = vbj.c;
        ArrayList arrayList = new ArrayList();
        this.e = arrayList;
        if (list != null) {
            arrayList.addAll(list);
        }
        this.f17665a = new ArrayList();
        if (CipherSuite.containsEccBasedCipherSuite(list)) {
            addExtension(new ven(list5));
            addExtension(SupportedPointFormatsExtension.d);
        }
        if (!list2.isEmpty()) {
            if (e(list3) && e(list4)) {
                list2 = SignatureAndHashAlgorithm.a(list2, CipherSuite.getCertificateKeyAlgorithms(list));
            }
            addExtension(new vef(list2));
        }
        if (CipherSuite.containsCipherSuiteRequiringCertExchange(list)) {
            if (d(list3)) {
                addExtension(new vch(list3));
            }
            if (d(list4)) {
                addExtension(new vea(list4));
            }
        }
    }

    private vck(vbn vbnVar) throws vdb {
        super(vbnVar);
        this.b = vbnVar.h(8);
        this.e = CipherSuite.listFromReader(vbnVar.b(vbnVar.c(16)));
        this.f17665a = CompressionMethod.listFromReader(vbnVar.b(vbnVar.c(8)));
        this.extensions.a(vbnVar);
        veg serverNameExtension = getServerNameExtension();
        if (serverNameExtension != null && serverNameExtension.b() == null) {
            throw new vdb("ClientHello message contains empty ServerNameExtension", new AlertMessage(AlertMessage.AlertLevel.FATAL, AlertMessage.AlertDescription.DECODE_ERROR));
        }
    }

    private boolean d(List<CertificateType> list) {
        return (list == null || list.isEmpty() || (list.size() <= 1 && list.contains(CertificateType.X_509))) ? false : true;
    }

    private boolean e(List<CertificateType> list) {
        if (list == null || list.size() != 1) {
            return false;
        }
        return list.contains(CertificateType.RAW_PUBLIC_KEY);
    }

    @Override // org.eclipse.californium.scandium.dtls.HandshakeMessage
    public byte[] fragmentToByteArray() {
        vbo vboVar = new vbo();
        writeHeader(vboVar);
        vboVar.d(this.b, 8);
        vboVar.b((this.e.size() * 16) / 8, 16);
        CipherSuite.listToWriter(vboVar, this.e);
        vboVar.b(this.f17665a.size(), 8);
        CompressionMethod.listToWriter(vboVar, this.f17665a);
        this.extensions.a(vboVar);
        return vboVar.c();
    }

    public static vck a(vbn vbnVar) throws vdb {
        return new vck(vbnVar);
    }

    @Override // org.eclipse.californium.scandium.dtls.HelloHandshakeMessage, org.eclipse.californium.scandium.dtls.HandshakeMessage, org.eclipse.californium.scandium.dtls.DTLSMessage
    public String toString(int i) {
        StringBuilder sb = new StringBuilder(super.toString(i));
        int i2 = i + 1;
        String b = vcb.b(i2);
        String b2 = vcb.b(i + 2);
        sb.append(b);
        sb.append("Cookie Length: ");
        sb.append(this.b.length);
        sb.append(" bytes");
        sb.append(vcb.a());
        if (this.b.length > 0) {
            sb.append(b);
            sb.append("Cookie: ");
            sb.append(vcb.b(this.b));
            sb.append(vcb.a());
        }
        sb.append(b);
        sb.append("Cipher Suites (");
        sb.append(this.e.size());
        sb.append(" suites, ");
        sb.append((this.e.size() * 16) / 8);
        sb.append(" bytes)");
        sb.append(vcb.a());
        for (CipherSuite cipherSuite : this.e) {
            sb.append(b2);
            sb.append("Cipher Suite: ");
            sb.append(cipherSuite);
            sb.append(vcb.a());
        }
        sb.append(b);
        sb.append("Compression Methods (");
        sb.append(this.f17665a.size());
        sb.append(" methods, ");
        sb.append(this.f17665a.size());
        sb.append(" bytes)");
        sb.append(vcb.a());
        for (CompressionMethod compressionMethod : this.f17665a) {
            sb.append(b2);
            sb.append("Compression Method: ");
            sb.append(compressionMethod);
            sb.append(vcb.a());
        }
        sb.append(this.extensions.a(i2));
        return sb.toString();
    }

    @Override // org.eclipse.californium.scandium.dtls.HandshakeMessage
    public HandshakeType getMessageType() {
        return HandshakeType.CLIENT_HELLO;
    }

    @Override // org.eclipse.californium.scandium.dtls.HandshakeMessage
    public int getMessageLength() {
        return this.sessionId.b() + 39 + this.b.length + ((this.e.size() * 16) / 8) + this.f17665a.size() + this.extensions.a();
    }

    public byte[] b() {
        return this.b;
    }

    public boolean i() {
        return this.b.length > 0;
    }

    public void c(byte[] bArr) {
        if (bArr == null) {
            throw new NullPointerException("cookie must not be null!");
        }
        if (bArr.length == 0) {
            throw new IllegalArgumentException("cookie must not be empty!");
        }
        this.b = Arrays.copyOf(bArr, bArr.length);
        fragmentChanged();
    }

    public void d(Mac mac) {
        byte[] byteArray = toByteArray();
        int b = this.sessionId.b();
        int i = b + 35;
        int i2 = b + 48;
        byte[] bArr = this.b;
        if (bArr != null) {
            i2 += bArr.length;
        }
        mac.update(byteArray, 12, i);
        mac.update(byteArray, i2, byteArray.length - i2);
    }

    public List<CipherSuite> d() {
        return Collections.unmodifiableList(this.e);
    }

    public List<CipherSuite> a(List<CipherSuite> list) {
        ArrayList arrayList = new ArrayList();
        for (CipherSuite cipherSuite : this.e) {
            if (cipherSuite.isValidForNegotiation() && list.contains(cipherSuite)) {
                arrayList.add(cipherSuite);
            }
        }
        return arrayList;
    }

    public List<CompressionMethod> e() {
        return Collections.unmodifiableList(this.f17665a);
    }

    public void e(CompressionMethod compressionMethod) {
        vfb.a(this.f17665a, compressionMethod);
    }

    public vfe c() {
        veg serverNameExtension = getServerNameExtension();
        if (serverNameExtension == null) {
            return null;
        }
        return serverNameExtension.b();
    }

    public ven a() {
        return (ven) this.extensions.e(HelloExtension.ExtensionType.ELLIPTIC_CURVES);
    }
}
