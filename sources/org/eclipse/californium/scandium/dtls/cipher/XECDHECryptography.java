package org.eclipse.californium.scandium.dtls.cipher;

import defpackage.vbj;
import defpackage.vbu;
import defpackage.vcb;
import defpackage.vep;
import defpackage.vet;
import defpackage.veu;
import defpackage.vev;
import defpackage.vfh;
import defpackage.vha;
import java.lang.reflect.Method;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.X509Certificate;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.EllipticCurve;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.crypto.KeyAgreement;
import javax.crypto.SecretKey;
import javax.security.auth.Destroyable;
import org.eclipse.californium.elements.util.JceNames;
import org.slf4j.Logger;

/* loaded from: classes7.dex */
public final class XECDHECryptography implements Destroyable {

    /* renamed from: a, reason: collision with root package name */
    protected static final Logger f15912a = vha.a((Class<?>) XECDHECryptography.class);
    private static final Map<Integer, SupportedGroup> b;
    private static final Map<EllipticCurve, SupportedGroup> c;
    public static final veu d;
    private static final vet e;
    private static final vev f;
    private static volatile XDHPublicKeyApi g;
    private static final vet h;
    private static final veu i;
    private static final vev j;
    private final PublicKey k;
    private final byte[] l;
    private PrivateKey m;
    private final SupportedGroup n;

    public interface XDHPublicKeyApi {
        String getCurveName(PublicKey publicKey) throws GeneralSecurityException;

        boolean isSupporting(PublicKey publicKey);
    }

    static {
        vbu.c();
        f = new vev(JceNames.EC);
        j = new vev("XDH");
        i = new veu(JceNames.EC);
        d = new veu("XDH");
        e = new vet("ECDH");
        h = new vet("XDH");
        g = a.c();
        b = new HashMap();
        c = new HashMap();
    }

    public XECDHECryptography(SupportedGroup supportedGroup) throws GeneralSecurityException {
        KeyPair generateKeyPair;
        if (supportedGroup.getAlgorithmName().equals(JceNames.EC)) {
            KeyPairGenerator d2 = f.d();
            d2.initialize(new ECGenParameterSpec(supportedGroup.name()), vep.d());
            generateKeyPair = d2.generateKeyPair();
        } else if (supportedGroup.getAlgorithmName().equals("XDH")) {
            KeyPairGenerator d3 = j.d();
            d3.initialize(new ECGenParameterSpec(supportedGroup.name()), vep.d());
            generateKeyPair = d3.generateKeyPair();
        } else {
            throw new GeneralSecurityException(supportedGroup.name() + " not supported by KeyPairGenerator!");
        }
        this.m = generateKeyPair.getPrivate();
        PublicKey publicKey = generateKeyPair.getPublic();
        this.k = publicKey;
        this.n = supportedGroup;
        byte[] encodedPoint = supportedGroup.encodedPoint(publicKey);
        this.l = encodedPoint;
        b("OUT: ", publicKey, encodedPoint);
    }

    public SupportedGroup i() {
        return this.n;
    }

    public byte[] g() {
        return this.l;
    }

    public SecretKey a(byte[] bArr) throws GeneralSecurityException {
        KeyAgreement d2;
        if (this.m == null) {
            throw new IllegalStateException("private key must not be destroyed");
        }
        PublicKey decodedPoint = this.n.decodedPoint(bArr);
        if (this.n.getAlgorithmName().equals(JceNames.EC)) {
            d2 = e.d();
        } else if (g != null && this.n.getAlgorithmName().equals("XDH")) {
            d2 = h.d();
        } else {
            throw new GeneralSecurityException(this.n.name() + " not supported by JCE!");
        }
        b("IN: ", decodedPoint, bArr);
        try {
            d2.init(this.m);
            d2.doPhase(decodedPoint, true);
            byte[] generateSecret = d2.generateSecret();
            SecretKey e2 = vfh.e(generateSecret, "TlsPremasterSecret");
            vbj.b(generateSecret);
            return e2;
        } catch (InvalidKeyException e3) {
            f15912a.warn("Fail: {} {}", this.n.name(), e3.getMessage());
            throw e3;
        }
    }

    @Override // javax.security.auth.Destroyable
    public void destroy() {
        this.m = null;
    }

    @Override // javax.security.auth.Destroyable
    public boolean isDestroyed() {
        return this.m == null;
    }

    private void b(String str, PublicKey publicKey, byte[] bArr) throws GeneralSecurityException {
        Logger logger = f15912a;
        if (logger.isDebugEnabled()) {
            byte[] encoded = publicKey.getEncoded();
            String e2 = vcb.e(encoded);
            String e3 = vcb.e(bArr);
            if (e3.length() < e2.length()) {
                e3 = String.format("%" + e2.length() + "s", e3);
            }
            logger.debug("{}ASN1 encoded '{}'", str, e2);
            logger.debug("{}DHE  encoded '{}'", str, e3);
            for (int i2 = 0; i2 < bArr.length; i2++) {
                if (bArr[(bArr.length - i2) - 1] != encoded[(encoded.length - i2) - 1]) {
                    throw new GeneralSecurityException("DHE: failed to encoded point! " + this.n.name() + ", position: " + i2);
                }
            }
        }
    }

    public enum SupportedGroup {
        sect163k1(1, false),
        sect163r1(2, false),
        sect163r2(3, false),
        sect193r1(4, false),
        sect193r2(5, false),
        sect233k1(6, false),
        sect233r1(7, false),
        sect239k1(8, false),
        sect283k1(9, false),
        sect283r1(10, false),
        sect409k1(11, false),
        sect409r1(12, false),
        sect571k1(13, false),
        sect571r1(14, false),
        secp160k1(15, false),
        secp160r1(16, false),
        secp160r2(17, false),
        secp192k1(18, false),
        secp192r1(19, false),
        secp224k1(20, false),
        secp224r1(21, false),
        secp256k1(22, false),
        secp256r1(23, true),
        secp384r1(24, true),
        secp521r1(25, false),
        brainpoolP256r1(26, false),
        brainpoolP384r1(27, false),
        brainpoolP512r1(28, false),
        X25519(29, 32, "XDH", true),
        X448(30, 56, "XDH", true),
        ffdhe2048(256, false),
        ffdhe3072(257, false),
        ffdhe4096(258, false),
        ffdhe6144(259, false),
        ffdhe8192(260, false),
        arbitrary_explicit_prime_curves(65281, false),
        arbitrary_explicit_char2_curves(65282, false);

        private final String algorithmName;
        private final byte[] asn1header;
        private final int encodedPointSizeInBytes;
        private final int id;
        private final veu keyFactory;
        private final int keySizeInBytes;
        private final boolean recommended;
        private final boolean usable;

        /* JADX WARN: Removed duplicated region for block: B:11:0x007d  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        SupportedGroup(int r10, boolean r11) {
            /*
                r7 = this;
                r7.<init>(r8, r9)
                r7.id = r10
                java.lang.String r8 = "EC"
                r7.algorithmName = r8
                r7.recommended = r11
                r8 = 0
                r9 = 0
                r11 = 1
                vev r0 = org.eclipse.californium.scandium.dtls.cipher.XECDHECryptography.a()     // Catch: java.lang.Throwable -> L5f
                java.lang.Object r0 = r0.d()     // Catch: java.lang.Throwable -> L5f
                java.security.KeyPairGenerator r0 = (java.security.KeyPairGenerator) r0     // Catch: java.lang.Throwable -> L5f
                java.security.spec.ECGenParameterSpec r1 = new java.security.spec.ECGenParameterSpec     // Catch: java.lang.Throwable -> L5f
                java.lang.String r2 = r7.name()     // Catch: java.lang.Throwable -> L5f
                r1.<init>(r2)     // Catch: java.lang.Throwable -> L5f
                java.security.SecureRandom r2 = defpackage.vep.d()     // Catch: java.lang.Throwable -> L5f
                r0.initialize(r1, r2)     // Catch: java.lang.Throwable -> L5f
                java.security.KeyPair r0 = r0.generateKeyPair()     // Catch: java.lang.Throwable -> L5f
                java.security.PublicKey r0 = r0.getPublic()     // Catch: java.lang.Throwable -> L5f
                java.security.interfaces.ECPublicKey r0 = (java.security.interfaces.ECPublicKey) r0     // Catch: java.lang.Throwable -> L5f
                java.security.spec.ECParameterSpec r1 = r0.getParams()     // Catch: java.lang.Throwable -> L5f
                java.security.spec.EllipticCurve r1 = r1.getCurve()     // Catch: java.lang.Throwable -> L5f
                java.security.spec.ECField r2 = r1.getField()     // Catch: java.lang.Throwable -> L5f
                int r2 = r2.getFieldSize()     // Catch: java.lang.Throwable -> L5f
                int r2 = r2 + 7
                int r2 = r2 / 8
                int r3 = r2 * 2
                int r3 = r3 + r11
                java.util.Map r4 = org.eclipse.californium.scandium.dtls.cipher.XECDHECryptography.b()     // Catch: java.lang.Throwable -> L5d
                r4.put(r1, r7)     // Catch: java.lang.Throwable -> L5d
                byte[] r0 = r0.getEncoded()     // Catch: java.lang.Throwable -> L5d
                int r4 = r0.length     // Catch: java.lang.Throwable -> L5b
                int r4 = r4 - r3
                byte[] r8 = java.util.Arrays.copyOf(r0, r4)     // Catch: java.lang.Throwable -> L5b
                goto L75
            L5b:
                r1 = move-exception
                goto L64
            L5d:
                r0 = move-exception
                goto L62
            L5f:
                r0 = move-exception
                r2 = r9
                r3 = r2
            L62:
                r1 = r0
                r0 = r8
            L64:
                org.slf4j.Logger r4 = org.eclipse.californium.scandium.dtls.cipher.XECDHECryptography.f15912a
                java.lang.String r5 = r7.name()
                java.lang.String r1 = r1.getMessage()
                java.lang.String r6 = "Group [{}] is not supported by JCE! {}"
                r4.trace(r6, r5, r1)
                r1 = r8
                r8 = r0
            L75:
                r7.keySizeInBytes = r2
                r7.encodedPointSizeInBytes = r3
                r7.asn1header = r8
                if (r1 == 0) goto L7e
                r9 = r11
            L7e:
                r7.usable = r9
                veu r8 = org.eclipse.californium.scandium.dtls.cipher.XECDHECryptography.d()
                r7.keyFactory = r8
                java.util.Map r8 = org.eclipse.californium.scandium.dtls.cipher.XECDHECryptography.e()
                java.lang.Integer r9 = java.lang.Integer.valueOf(r10)
                r8.put(r9, r7)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.eclipse.californium.scandium.dtls.cipher.XECDHECryptography.SupportedGroup.<init>(java.lang.String, int, int, boolean):void");
        }

        SupportedGroup(int i, int i2, String str, boolean z) {
            boolean z2;
            this.id = i;
            this.algorithmName = str;
            this.keySizeInBytes = i2;
            this.encodedPointSizeInBytes = i2;
            this.recommended = z;
            byte[] bArr = null;
            try {
                KeyPairGenerator d = XECDHECryptography.j.d();
                d.initialize(new ECGenParameterSpec(name()), vep.d());
                byte[] encoded = d.generateKeyPair().getPublic().getEncoded();
                bArr = Arrays.copyOf(encoded, encoded.length - i2);
                z2 = true;
            } catch (Throwable th) {
                XECDHECryptography.f15912a.trace("Group [{}] is not supported by JCE! {}", name(), th.getMessage());
                z2 = false;
            }
            this.usable = z2;
            this.asn1header = bArr;
            this.keyFactory = XECDHECryptography.d;
            XECDHECryptography.b.put(Integer.valueOf(i), this);
        }

        public int getId() {
            return this.id;
        }

        public String getAlgorithmName() {
            return this.algorithmName;
        }

        public byte[] encodedPoint(PublicKey publicKey) throws GeneralSecurityException {
            if (publicKey == null) {
                throw new NullPointerException("public key must not be null!");
            }
            byte[] encoded = publicKey.getEncoded();
            if (encoded == null) {
                throw new GeneralSecurityException(name() + " not supported!");
            }
            return Arrays.copyOfRange(encoded, this.asn1header.length, encoded.length);
        }

        public PublicKey decodedPoint(byte[] bArr) throws GeneralSecurityException {
            if (bArr == null) {
                throw new NullPointerException("encoded point must not be null!");
            }
            if (this.encodedPointSizeInBytes != bArr.length) {
                throw new IllegalArgumentException("encoded point must have " + this.encodedPointSizeInBytes + " bytes, not " + bArr.length + "!");
            }
            return this.keyFactory.d().generatePublic(new X509EncodedKeySpec(vbj.e(this.asn1header, bArr)));
        }

        public static SupportedGroup fromId(int i) {
            return (SupportedGroup) XECDHECryptography.b.get(Integer.valueOf(i));
        }

        public static SupportedGroup fromPublicKey(PublicKey publicKey) {
            if (publicKey != null) {
                if (!(publicKey instanceof ECPublicKey)) {
                    if (XECDHECryptography.g != null && XECDHECryptography.g.isSupporting(publicKey)) {
                        try {
                            return valueOf(XECDHECryptography.g.getCurveName(publicKey));
                        } catch (GeneralSecurityException unused) {
                        }
                    } else {
                        String a2 = vbu.a(publicKey.getAlgorithm(), null);
                        if (JceNames.OID_ED25519.equals(a2) || JceNames.EDDSA.equalsIgnoreCase(a2)) {
                            return X25519;
                        }
                        if (JceNames.OID_ED448.equals(a2)) {
                            return X448;
                        }
                        XECDHECryptography.f15912a.warn("No supported curve {}/{}", publicKey.getAlgorithm(), a2);
                    }
                } else {
                    return (SupportedGroup) XECDHECryptography.c.get(((ECPublicKey) publicKey).getParams().getCurve());
                }
            }
            return null;
        }

        public static boolean isEcPublicKey(PublicKey publicKey) {
            if (publicKey instanceof ECPublicKey) {
                return true;
            }
            return XECDHECryptography.g != null && XECDHECryptography.g.isSupporting(publicKey);
        }

        public static boolean isSupported(List<SupportedGroup> list, List<X509Certificate> list2) {
            SupportedGroup fromPublicKey;
            Iterator<X509Certificate> it = list2.iterator();
            while (it.hasNext()) {
                PublicKey publicKey = it.next().getPublicKey();
                if (isEcPublicKey(publicKey) && ((fromPublicKey = fromPublicKey(publicKey)) == null || !fromPublicKey.isUsable() || !list.contains(fromPublicKey))) {
                    return false;
                }
            }
            return true;
        }

        public int getKeySizeInBytes() {
            return this.keySizeInBytes;
        }

        public int getEncodedPointSizeInBytes() {
            return this.encodedPointSizeInBytes;
        }

        public boolean isUsable() {
            return this.usable;
        }

        public boolean isRecommended() {
            return this.recommended;
        }

        public static List<SupportedGroup> getUsableGroups() {
            return e.f15913a;
        }

        public static SupportedGroup[] getUsableGroupsArray() {
            return (SupportedGroup[]) e.f15913a.toArray(new SupportedGroup[e.f15913a.size()]);
        }

        public static List<SupportedGroup> getPreferredGroups() {
            return e.c;
        }
    }

    static class e {

        /* renamed from: a, reason: collision with root package name */
        private static final List<SupportedGroup> f15913a;
        private static final List<SupportedGroup> c;
        private static final SupportedGroup[] d = {SupportedGroup.secp256r1, SupportedGroup.X25519, SupportedGroup.X448, SupportedGroup.secp384r1};

        static {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            for (SupportedGroup supportedGroup : SupportedGroup.values()) {
                if (supportedGroup.isUsable()) {
                    arrayList.add(supportedGroup);
                }
            }
            for (SupportedGroup supportedGroup2 : d) {
                if (supportedGroup2.isUsable()) {
                    arrayList2.add(supportedGroup2);
                }
            }
            if (arrayList2.isEmpty() && !arrayList.isEmpty()) {
                arrayList2.add(arrayList.get(0));
            }
            f15913a = Collections.unmodifiableList(arrayList);
            c = Collections.unmodifiableList(arrayList2);
        }
    }

    static class a implements XDHPublicKeyApi {
        private final Method b;
        private final Method c;
        private final Class<?> e;

        private a(Class<?> cls) {
            if (cls == null) {
                throw new NullPointerException("XECPublicKeyClass must not be null!");
            }
            this.e = cls;
            this.b = null;
            this.c = null;
        }

        private a(Class<?> cls, Method method, Method method2) {
            if (cls == null) {
                throw new NullPointerException("XECPublicKeyClass must not be null!");
            }
            if (method == null) {
                throw new NullPointerException("XECPublicKeyGetParams must not be null!");
            }
            if (method2 == null) {
                throw new NullPointerException("NamedParameterSpecGetName must not be null!");
            }
            this.e = cls;
            this.b = method;
            this.c = method2;
        }

        @Override // org.eclipse.californium.scandium.dtls.cipher.XECDHECryptography.XDHPublicKeyApi
        public boolean isSupporting(PublicKey publicKey) {
            return this.e.isInstance(publicKey);
        }

        @Override // org.eclipse.californium.scandium.dtls.cipher.XECDHECryptography.XDHPublicKeyApi
        public String getCurveName(PublicKey publicKey) throws GeneralSecurityException {
            if (this.e.isInstance(publicKey)) {
                Method method = this.b;
                if (method != null && this.c != null) {
                    try {
                        return (String) this.c.invoke(method.invoke(publicKey, new Object[0]), new Object[0]);
                    } catch (Exception e) {
                        throw new GeneralSecurityException("X25519/X448 not supported by JRE!", e);
                    }
                }
                return publicKey.getAlgorithm();
            }
            throw new GeneralSecurityException(publicKey.getAlgorithm() + " not supported!");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static XDHPublicKeyApi c() {
            try {
                if (vbu.a()) {
                    return new a(Class.forName("org.bouncycastle.jcajce.provider.asymmetric.edec.BCXDHPublicKey"));
                }
                Method method = Class.forName("java.security.spec.NamedParameterSpec").getMethod("getName", new Class[0]);
                Class<?> cls = Class.forName("java.security.interfaces.XECPublicKey");
                return new a(cls, cls.getMethod("getParams", new Class[0]), method);
            } catch (Throwable unused) {
                XECDHECryptography.f15912a.info("X25519/X448 not supported!");
                return null;
            }
        }
    }
}
