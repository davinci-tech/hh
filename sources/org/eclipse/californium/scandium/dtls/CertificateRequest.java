package org.eclipse.californium.scandium.dtls;

import defpackage.vbi;
import defpackage.vbn;
import defpackage.vbo;
import defpackage.vcb;
import defpackage.vfb;
import defpackage.vha;
import java.security.PublicKey;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.security.auth.x500.X500Principal;
import org.eclipse.californium.scandium.dtls.cipher.CipherSuite;
import org.slf4j.Logger;

/* loaded from: classes7.dex */
public final class CertificateRequest extends HandshakeMessage {
    private static final Logger d = vha.a((Class<?>) CertificateRequest.class);

    /* renamed from: a, reason: collision with root package name */
    private final List<ClientCertificateType> f15900a;
    private final List<SignatureAndHashAlgorithm> b;
    private final List<X500Principal> c;
    private int e;

    public CertificateRequest() {
        this.f15900a = new ArrayList();
        this.b = new ArrayList();
        this.c = new ArrayList();
        this.e = 0;
    }

    public CertificateRequest(List<ClientCertificateType> list, List<SignatureAndHashAlgorithm> list2, List<X500Principal> list3) {
        ArrayList arrayList = new ArrayList();
        this.f15900a = arrayList;
        ArrayList arrayList2 = new ArrayList();
        this.b = arrayList2;
        this.c = new ArrayList();
        this.e = 0;
        if (list != null) {
            arrayList.addAll(list);
        }
        if (!list2.isEmpty()) {
            arrayList2.addAll(list2);
        }
        if (list3 != null) {
            c(list3);
        }
    }

    @Override // org.eclipse.californium.scandium.dtls.HandshakeMessage
    public HandshakeType getMessageType() {
        return HandshakeType.CERTIFICATE_REQUEST;
    }

    @Override // org.eclipse.californium.scandium.dtls.HandshakeMessage
    public int getMessageLength() {
        return this.f15900a.size() + 3 + (this.b.size() * 2) + 2 + this.e;
    }

    @Override // org.eclipse.californium.scandium.dtls.HandshakeMessage, org.eclipse.californium.scandium.dtls.DTLSMessage
    public String toString(int i) {
        StringBuilder sb = new StringBuilder(super.toString(i));
        String b = vcb.b(i + 1);
        String b2 = vcb.b(i + 2);
        if (!this.f15900a.isEmpty()) {
            sb.append(b);
            sb.append("Client certificate type:");
            sb.append(vcb.a());
            for (ClientCertificateType clientCertificateType : this.f15900a) {
                sb.append(b2);
                sb.append(clientCertificateType);
                sb.append(vcb.a());
            }
        }
        if (!this.b.isEmpty()) {
            sb.append(b);
            sb.append("Signature and hash algorithm:");
            sb.append(vcb.a());
            for (SignatureAndHashAlgorithm signatureAndHashAlgorithm : this.b) {
                sb.append(b2);
                sb.append(signatureAndHashAlgorithm);
                sb.append(vcb.a());
            }
        }
        if (!this.c.isEmpty()) {
            sb.append(b);
            sb.append("Certificate authorities:");
            sb.append(vcb.a());
            for (X500Principal x500Principal : this.c) {
                sb.append(b2);
                sb.append(x500Principal.getName());
                sb.append(vcb.a());
            }
        }
        return sb.toString();
    }

    @Override // org.eclipse.californium.scandium.dtls.HandshakeMessage
    public byte[] fragmentToByteArray() {
        vbo vboVar = new vbo();
        vboVar.b(this.f15900a.size(), 8);
        Iterator<ClientCertificateType> it = this.f15900a.iterator();
        while (it.hasNext()) {
            vboVar.b(it.next().getCode(), 8);
        }
        vboVar.b(this.b.size() * 2, 16);
        for (SignatureAndHashAlgorithm signatureAndHashAlgorithm : this.b) {
            vboVar.b(signatureAndHashAlgorithm.a().getCode(), 8);
            vboVar.b(signatureAndHashAlgorithm.e().getCode(), 8);
        }
        vboVar.b(this.e, 16);
        Iterator<X500Principal> it2 = this.c.iterator();
        while (it2.hasNext()) {
            vboVar.d(it2.next().getEncoded(), 16);
        }
        return vboVar.c();
    }

    public static HandshakeMessage d(vbn vbnVar) {
        ArrayList arrayList = new ArrayList();
        vbn b = vbnVar.b(vbnVar.c(8));
        while (b.e()) {
            arrayList.add(ClientCertificateType.getTypeByCode(b.c(8)));
        }
        ArrayList arrayList2 = new ArrayList();
        vbn b2 = vbnVar.b(vbnVar.c(16));
        while (b2.e()) {
            arrayList2.add(new SignatureAndHashAlgorithm(b2.c(8), b2.c(8)));
        }
        ArrayList arrayList3 = new ArrayList();
        vbn b3 = vbnVar.b(vbnVar.c(16));
        while (b3.e()) {
            arrayList3.add(new X500Principal(b3.h(16)));
        }
        return new CertificateRequest(arrayList, arrayList2, arrayList3);
    }

    public enum ClientCertificateType {
        RSA_SIGN(1, CipherSuite.CertificateKeyAlgorithm.RSA),
        DSS_SIGN(2, CipherSuite.CertificateKeyAlgorithm.DSA),
        RSA_FIXED_DH(3, null),
        DSS_FIXED_DH(4, null),
        RSA_EPHEMERAL_DH_RESERVED(5, null),
        DSS_EPHEMERAL_DH_RESERVED(6, null),
        FORTEZZA_DMS_RESERVED(20, null),
        ECDSA_SIGN(64, CipherSuite.CertificateKeyAlgorithm.EC),
        RSA_FIXED_ECDH(65, null),
        ECDSA_FIXED_ECDH(66, null);

        private final int code;
        private final CipherSuite.CertificateKeyAlgorithm keyAlgorithm;

        ClientCertificateType(int i, CipherSuite.CertificateKeyAlgorithm certificateKeyAlgorithm) {
            this.code = i;
            this.keyAlgorithm = certificateKeyAlgorithm;
        }

        public int getCode() {
            return this.code;
        }

        public boolean requiresSigningCapability() {
            return this.keyAlgorithm != null;
        }

        public boolean isCompatibleWithKeyAlgorithm(String str) {
            CipherSuite.CertificateKeyAlgorithm certificateKeyAlgorithm = this.keyAlgorithm;
            if (certificateKeyAlgorithm == null) {
                return false;
            }
            return certificateKeyAlgorithm.isCompatible(str);
        }

        public CipherSuite.CertificateKeyAlgorithm getCertificateKeyAlgorithm() {
            return this.keyAlgorithm;
        }

        public static ClientCertificateType getTypeByCode(int i) {
            for (ClientCertificateType clientCertificateType : values()) {
                if (clientCertificateType.code == i) {
                    return clientCertificateType;
                }
            }
            return null;
        }
    }

    public void b(ClientCertificateType clientCertificateType) {
        this.f15900a.add(clientCertificateType);
    }

    public void e(CipherSuite.CertificateKeyAlgorithm certificateKeyAlgorithm) {
        for (ClientCertificateType clientCertificateType : ClientCertificateType.values()) {
            if (clientCertificateType.getCertificateKeyAlgorithm() == certificateKeyAlgorithm) {
                b(clientCertificateType);
            }
        }
    }

    public void d(List<SignatureAndHashAlgorithm> list) {
        this.b.addAll(list);
    }

    public boolean d(X500Principal x500Principal) {
        if (x500Principal == null) {
            throw new NullPointerException("authority must not be null");
        }
        int length = x500Principal.getEncoded().length + 2;
        if (this.e + length > 65535) {
            return false;
        }
        this.c.add(x500Principal);
        this.e += length;
        return true;
    }

    public boolean c(List<X500Principal> list) {
        Iterator<X500Principal> it = list.iterator();
        int i = 0;
        while (it.hasNext()) {
            if (!d(it.next())) {
                d.debug("could add only {} of {} certificate authorities, max length exceeded", Integer.valueOf(i), Integer.valueOf(list.size()));
                return false;
            }
            i++;
        }
        return true;
    }

    public List<CipherSuite.CertificateKeyAlgorithm> d() {
        ArrayList arrayList = new ArrayList();
        Iterator<ClientCertificateType> it = this.f15900a.iterator();
        while (it.hasNext()) {
            vfb.a(arrayList, it.next().getCertificateKeyAlgorithm());
        }
        return arrayList;
    }

    boolean a(PublicKey publicKey) {
        String algorithm = publicKey.getAlgorithm();
        Iterator<ClientCertificateType> it = this.f15900a.iterator();
        while (it.hasNext()) {
            if (it.next().isCompatibleWithKeyAlgorithm(algorithm)) {
                return true;
            }
        }
        return false;
    }

    boolean b(X509Certificate x509Certificate) {
        String algorithm = x509Certificate.getPublicKey().getAlgorithm();
        Boolean bool = null;
        for (ClientCertificateType clientCertificateType : this.f15900a) {
            if (!clientCertificateType.isCompatibleWithKeyAlgorithm(algorithm)) {
                d.debug("type: {}, is not compatible with KeyAlgorithm[{}]", clientCertificateType, algorithm);
            } else {
                if (clientCertificateType.requiresSigningCapability()) {
                    if (bool == null) {
                        bool = Boolean.valueOf(vbi.b(x509Certificate, true));
                    }
                    if (!bool.booleanValue()) {
                        d.error("type: {}, requires missing signing capability!", clientCertificateType);
                    }
                }
                d.debug("type: {}, is compatible with KeyAlgorithm[{}] and meets signing requirements", clientCertificateType, algorithm);
                return true;
            }
        }
        d.debug("certificate [{}] with public key {} is not of any supported type", x509Certificate, algorithm);
        return false;
    }

    public SignatureAndHashAlgorithm a(PublicKey publicKey, List<SignatureAndHashAlgorithm> list) {
        if (a(publicKey)) {
            return SignatureAndHashAlgorithm.c(SignatureAndHashAlgorithm.e(this.b, list), publicKey);
        }
        return null;
    }

    public SignatureAndHashAlgorithm d(List<X509Certificate> list, List<SignatureAndHashAlgorithm> list2) {
        SignatureAndHashAlgorithm c;
        X509Certificate x509Certificate = list.get(0);
        if (b(x509Certificate) && (c = SignatureAndHashAlgorithm.c(SignatureAndHashAlgorithm.e(this.b, list2), x509Certificate.getPublicKey())) != null && SignatureAndHashAlgorithm.d(this.b, list)) {
            return c;
        }
        return null;
    }

    public List<SignatureAndHashAlgorithm> c() {
        return Collections.unmodifiableList(this.b);
    }

    public List<X500Principal> a() {
        return Collections.unmodifiableList(this.c);
    }
}
