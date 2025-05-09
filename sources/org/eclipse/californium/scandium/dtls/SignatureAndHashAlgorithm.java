package org.eclipse.californium.scandium.dtls;

import defpackage.vbu;
import defpackage.vex;
import defpackage.vfb;
import java.security.InvalidKeyException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.eclipse.californium.elements.util.JceNames;
import org.eclipse.californium.scandium.dtls.cipher.CipherSuite;

/* loaded from: classes7.dex */
public final class SignatureAndHashAlgorithm {

    /* renamed from: a, reason: collision with root package name */
    public static final SignatureAndHashAlgorithm f15906a;
    public static final SignatureAndHashAlgorithm b;
    public static final List<SignatureAndHashAlgorithm> c;
    public static final SignatureAndHashAlgorithm d;
    public static final SignatureAndHashAlgorithm e;
    public static final SignatureAndHashAlgorithm g;
    public static final SignatureAndHashAlgorithm h;
    private final int f;
    private final String i;
    private final HashAlgorithm j;
    private final boolean k;
    private final int m;
    private final SignatureAlgorithm o;

    static {
        vbu.c();
        e = new SignatureAndHashAlgorithm(HashAlgorithm.SHA1, SignatureAlgorithm.ECDSA);
        SignatureAndHashAlgorithm signatureAndHashAlgorithm = new SignatureAndHashAlgorithm(HashAlgorithm.SHA256, SignatureAlgorithm.ECDSA);
        f15906a = signatureAndHashAlgorithm;
        h = new SignatureAndHashAlgorithm(HashAlgorithm.SHA384, SignatureAlgorithm.ECDSA);
        SignatureAndHashAlgorithm signatureAndHashAlgorithm2 = new SignatureAndHashAlgorithm(HashAlgorithm.SHA256, SignatureAlgorithm.RSA);
        g = signatureAndHashAlgorithm2;
        d = new SignatureAndHashAlgorithm(HashAlgorithm.INTRINSIC, SignatureAlgorithm.ED25519);
        b = new SignatureAndHashAlgorithm(HashAlgorithm.INTRINSIC, SignatureAlgorithm.ED448);
        c = Collections.unmodifiableList(Arrays.asList(signatureAndHashAlgorithm, signatureAndHashAlgorithm2));
    }

    public enum HashAlgorithm {
        NONE(0, false),
        MD5(1, false),
        SHA1(2, false),
        SHA224(3, false),
        SHA256(4, true),
        SHA384(5, true),
        SHA512(6, true),
        INTRINSIC(8, true);

        private final int code;
        private final boolean recommended;

        HashAlgorithm(int i, boolean z) {
            this.code = i;
            this.recommended = z;
        }

        public static HashAlgorithm getAlgorithmByCode(int i) {
            switch (i) {
                case 0:
                    return NONE;
                case 1:
                    return MD5;
                case 2:
                    return SHA1;
                case 3:
                    return SHA224;
                case 4:
                    return SHA256;
                case 5:
                    return SHA384;
                case 6:
                    return SHA512;
                case 7:
                default:
                    return null;
                case 8:
                    return INTRINSIC;
            }
        }

        public int getCode() {
            return this.code;
        }

        public boolean isRecommended() {
            return this.recommended;
        }
    }

    public enum SignatureAlgorithm {
        ANONYMOUS(0, null),
        RSA(1, CipherSuite.CertificateKeyAlgorithm.RSA),
        DSA(2, CipherSuite.CertificateKeyAlgorithm.DSA),
        ECDSA(3, CipherSuite.CertificateKeyAlgorithm.EC, JceNames.EC, false),
        ED25519(7, CipherSuite.CertificateKeyAlgorithm.EC, JceNames.OID_ED25519, true),
        ED448(8, CipherSuite.CertificateKeyAlgorithm.EC, JceNames.OID_ED448, true);

        private final CipherSuite.CertificateKeyAlgorithm certificateKeyAlgorithm;
        private final int code;
        private final boolean isIntrinsic;
        private final String keyAlgorithm;

        SignatureAlgorithm(int i, CipherSuite.CertificateKeyAlgorithm certificateKeyAlgorithm) {
            this.code = i;
            this.certificateKeyAlgorithm = certificateKeyAlgorithm;
            this.keyAlgorithm = name();
            this.isIntrinsic = false;
        }

        SignatureAlgorithm(int i, CipherSuite.CertificateKeyAlgorithm certificateKeyAlgorithm, String str, boolean z) {
            this.code = i;
            this.certificateKeyAlgorithm = certificateKeyAlgorithm;
            this.keyAlgorithm = str;
            this.isIntrinsic = z;
        }

        public static SignatureAlgorithm getAlgorithmByCode(int i) {
            if (i == 0) {
                return ANONYMOUS;
            }
            if (i == 1) {
                return RSA;
            }
            if (i == 2) {
                return DSA;
            }
            if (i == 3) {
                return ECDSA;
            }
            if (i == 7) {
                return ED25519;
            }
            if (i != 8) {
                return null;
            }
            return ED448;
        }

        public int getCode() {
            return this.code;
        }

        public boolean isSupported(String str) {
            String a2;
            if (this.keyAlgorithm.equalsIgnoreCase(str)) {
                return vbu.c(str);
            }
            SignatureAlgorithm signatureAlgorithm = ED25519;
            if ((signatureAlgorithm != this && ED448 != this) || (a2 = vbu.a(str, null)) == null) {
                return false;
            }
            if (signatureAlgorithm == this) {
                if (JceNames.OID_ED25519 == a2 || JceNames.EDDSA == a2) {
                    return vbu.c(JceNames.ED25519);
                }
                return false;
            }
            if (JceNames.OID_ED448 == a2 || JceNames.EDDSA == a2) {
                return vbu.c(JceNames.ED448);
            }
            return false;
        }

        public boolean isSupported(CipherSuite.CertificateKeyAlgorithm certificateKeyAlgorithm) {
            return this.certificateKeyAlgorithm == certificateKeyAlgorithm;
        }

        public boolean isIntrinsic() {
            return this.isIntrinsic;
        }

        public static SignatureAlgorithm intrinsicValueOf(String str) {
            String a2 = vbu.a(str, null);
            if (a2 != null) {
                for (SignatureAlgorithm signatureAlgorithm : values()) {
                    if (signatureAlgorithm.isIntrinsic && signatureAlgorithm.isSupported(a2)) {
                        return signatureAlgorithm;
                    }
                }
                throw new IllegalArgumentException(str + " is no supported intrinsic algorithm!");
            }
            throw new IllegalArgumentException(str + " is unknown intrinsic algorithm!");
        }
    }

    public static vex b(String str) {
        if (str == null) {
            str = "UNKNOWN";
        }
        return vex.e.a(str);
    }

    public static SignatureAndHashAlgorithm d(String str) {
        HashAlgorithm hashAlgorithm;
        SignatureAlgorithm intrinsicValueOf;
        int indexOf = str.indexOf("with");
        if (indexOf < 0) {
            indexOf = str.indexOf("WITH");
        }
        if (indexOf > 0) {
            String substring = str.substring(0, indexOf);
            String substring2 = str.substring(indexOf + 4, str.length());
            intrinsicValueOf = null;
            try {
                hashAlgorithm = HashAlgorithm.valueOf(substring);
            } catch (IllegalArgumentException unused) {
                hashAlgorithm = null;
            }
            try {
                intrinsicValueOf = SignatureAlgorithm.valueOf(substring2);
            } catch (IllegalArgumentException unused2) {
            }
            if (hashAlgorithm == null && intrinsicValueOf == null) {
                throw new IllegalArgumentException(str + " is unknown!");
            }
            if (hashAlgorithm == null) {
                throw new IllegalArgumentException(str + " uses a unknown hash-algorithm!");
            }
            if (intrinsicValueOf == null) {
                throw new IllegalArgumentException(str + " uses a unknown signature-algorithm!");
            }
        } else {
            hashAlgorithm = HashAlgorithm.INTRINSIC;
            intrinsicValueOf = SignatureAlgorithm.intrinsicValueOf(str);
        }
        return new SignatureAndHashAlgorithm(hashAlgorithm, intrinsicValueOf);
    }

    public static void d(List<SignatureAndHashAlgorithm> list, PublicKey publicKey) {
        if (publicKey == null) {
            throw new NullPointerException("Public key must not be null!");
        }
        SignatureAndHashAlgorithm c2 = c(c, publicKey);
        if (c2 != null) {
            vfb.a(list, c2);
            return;
        }
        if (list == null) {
            throw new NullPointerException("The defaults list must not be null!");
        }
        if (c(list, publicKey) != null) {
            return;
        }
        boolean z = false;
        for (SignatureAlgorithm signatureAlgorithm : SignatureAlgorithm.values()) {
            if (signatureAlgorithm.isSupported(publicKey.getAlgorithm())) {
                if (signatureAlgorithm.isIntrinsic()) {
                    SignatureAndHashAlgorithm signatureAndHashAlgorithm = new SignatureAndHashAlgorithm(HashAlgorithm.INTRINSIC, signatureAlgorithm);
                    if (signatureAndHashAlgorithm.b(publicKey)) {
                        vfb.a(list, signatureAndHashAlgorithm);
                        return;
                    }
                } else {
                    for (HashAlgorithm hashAlgorithm : HashAlgorithm.values()) {
                        if (hashAlgorithm != HashAlgorithm.INTRINSIC && hashAlgorithm.isRecommended()) {
                            SignatureAndHashAlgorithm signatureAndHashAlgorithm2 = new SignatureAndHashAlgorithm(hashAlgorithm, signatureAlgorithm);
                            if (signatureAndHashAlgorithm2.b(publicKey)) {
                                vfb.a(list, signatureAndHashAlgorithm2);
                                return;
                            }
                        }
                    }
                }
                z = true;
            }
        }
        if (z) {
            throw new IllegalArgumentException(publicKey.getAlgorithm() + " public key is not supported!");
        }
        throw new IllegalArgumentException(publicKey.getAlgorithm() + " is not supported!");
    }

    public static List<SignatureAndHashAlgorithm> e(List<SignatureAndHashAlgorithm> list, List<SignatureAndHashAlgorithm> list2) {
        ArrayList arrayList = new ArrayList();
        for (SignatureAndHashAlgorithm signatureAndHashAlgorithm : list) {
            if (list2.contains(signatureAndHashAlgorithm)) {
                vfb.a(arrayList, signatureAndHashAlgorithm);
            }
        }
        return arrayList;
    }

    public static SignatureAndHashAlgorithm c(List<SignatureAndHashAlgorithm> list, PublicKey publicKey) {
        if (publicKey == null) {
            throw new NullPointerException("Public key must not be null!");
        }
        for (SignatureAndHashAlgorithm signatureAndHashAlgorithm : list) {
            if (signatureAndHashAlgorithm.b(publicKey)) {
                return signatureAndHashAlgorithm;
            }
        }
        return null;
    }

    public static List<SignatureAndHashAlgorithm> a(List<SignatureAndHashAlgorithm> list, List<CipherSuite.CertificateKeyAlgorithm> list2) {
        ArrayList arrayList = new ArrayList();
        for (SignatureAndHashAlgorithm signatureAndHashAlgorithm : list) {
            Iterator<CipherSuite.CertificateKeyAlgorithm> it = list2.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                if (signatureAndHashAlgorithm.b(it.next())) {
                    arrayList.add(signatureAndHashAlgorithm);
                    break;
                }
            }
        }
        return arrayList;
    }

    public static boolean b(List<SignatureAndHashAlgorithm> list, CipherSuite.CertificateKeyAlgorithm certificateKeyAlgorithm) {
        Iterator<SignatureAndHashAlgorithm> it = list.iterator();
        while (it.hasNext()) {
            if (it.next().b(certificateKeyAlgorithm)) {
                return true;
            }
        }
        return false;
    }

    public static boolean d(List<SignatureAndHashAlgorithm> list, List<X509Certificate> list2) {
        Iterator<X509Certificate> it = list2.iterator();
        while (it.hasNext()) {
            if (!d(list, it.next())) {
                return false;
            }
        }
        return true;
    }

    public static boolean d(List<SignatureAndHashAlgorithm> list, X509Certificate x509Certificate) {
        String sigAlgName = x509Certificate.getSigAlgName();
        String a2 = vbu.a(sigAlgName, null);
        if (a2 != null) {
            if (SignatureAlgorithm.ED25519.isSupported(a2)) {
                return list.contains(d);
            }
            if (SignatureAlgorithm.ED448.isSupported(a2)) {
                return list.contains(b);
            }
            return false;
        }
        Iterator<SignatureAndHashAlgorithm> it = list.iterator();
        while (it.hasNext()) {
            if (sigAlgName.equalsIgnoreCase(it.next().c())) {
                return true;
            }
        }
        return false;
    }

    public SignatureAndHashAlgorithm(HashAlgorithm hashAlgorithm, SignatureAlgorithm signatureAlgorithm) {
        if (hashAlgorithm == null) {
            throw new NullPointerException("Hash Algorithm must not be null!");
        }
        if (signatureAlgorithm == null) {
            throw new NullPointerException("Signature Algorithm must not be null!");
        }
        this.j = hashAlgorithm;
        this.o = signatureAlgorithm;
        this.f = hashAlgorithm.getCode();
        this.m = signatureAlgorithm.getCode();
        String i = i();
        this.i = i;
        this.k = i != null && b(i).c();
    }

    public SignatureAndHashAlgorithm(int i, int i2) {
        this.f = i;
        this.m = i2;
        this.o = SignatureAlgorithm.getAlgorithmByCode(i2);
        this.j = HashAlgorithm.getAlgorithmByCode(i);
        String i3 = i();
        this.i = i3;
        this.k = i3 != null && b(i3).c();
    }

    private String i() {
        if (this.j == null || this.o == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        if (this.j != HashAlgorithm.INTRINSIC) {
            sb.append(this.j);
            sb.append("with");
        }
        sb.append(this.o);
        return sb.toString();
    }

    public SignatureAlgorithm e() {
        return this.o;
    }

    public HashAlgorithm a() {
        return this.j;
    }

    public String c() {
        return this.i;
    }

    public boolean d() {
        HashAlgorithm hashAlgorithm;
        return (this.o == null || (hashAlgorithm = this.j) == null || !hashAlgorithm.isRecommended()) ? false : true;
    }

    public boolean j() {
        return this.k;
    }

    public boolean b(CipherSuite.CertificateKeyAlgorithm certificateKeyAlgorithm) {
        if (this.k) {
            return this.o.isSupported(certificateKeyAlgorithm);
        }
        return false;
    }

    public boolean b(PublicKey publicKey) {
        Signature b2;
        if (!this.k || !this.o.isSupported(publicKey.getAlgorithm()) || (b2 = b().b()) == null) {
            return false;
        }
        try {
            b2.initVerify(publicKey);
            return true;
        } catch (InvalidKeyException unused) {
            return false;
        }
    }

    public String toString() {
        String str = this.i;
        if (str != null) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        HashAlgorithm hashAlgorithm = this.j;
        if (hashAlgorithm != null) {
            sb.append(hashAlgorithm);
        } else {
            sb.append(String.format("0x%02x", Integer.valueOf(this.f)));
        }
        sb.append("with");
        SignatureAlgorithm signatureAlgorithm = this.o;
        if (signatureAlgorithm != null) {
            sb.append(signatureAlgorithm);
        } else {
            sb.append(String.format("0x%02x", Integer.valueOf(this.m)));
        }
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SignatureAndHashAlgorithm signatureAndHashAlgorithm = (SignatureAndHashAlgorithm) obj;
        return this.m == signatureAndHashAlgorithm.m && this.f == signatureAndHashAlgorithm.f;
    }

    public int hashCode() {
        return (this.f * 256) + this.m;
    }

    public vex b() {
        return b(c());
    }
}
