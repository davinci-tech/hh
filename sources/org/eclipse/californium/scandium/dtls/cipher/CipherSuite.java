package org.eclipse.californium.scandium.dtls.cipher;

import defpackage.vbm;
import defpackage.vbn;
import defpackage.vbo;
import defpackage.vem;
import defpackage.ves;
import defpackage.vew;
import defpackage.vey;
import defpackage.vfb;
import defpackage.vha;
import java.security.MessageDigest;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.Mac;
import org.slf4j.Logger;

/* loaded from: classes7.dex */
public enum CipherSuite {
    TLS_ECDHE_PSK_WITH_AES_128_GCM_SHA256(53249, CertificateKeyAlgorithm.NONE, KeyExchangeAlgorithm.ECDHE_PSK, CipherSpec.AES_128_GCM, true),
    TLS_ECDHE_PSK_WITH_AES_256_GCM_SHA378(53250, CertificateKeyAlgorithm.NONE, KeyExchangeAlgorithm.ECDHE_PSK, CipherSpec.AES_256_GCM, true, PRFAlgorithm.TLS_PRF_SHA384),
    TLS_ECDHE_PSK_WITH_AES_128_CCM_8_SHA256(53251, CertificateKeyAlgorithm.NONE, KeyExchangeAlgorithm.ECDHE_PSK, CipherSpec.AES_128_CCM_8, true),
    TLS_ECDHE_PSK_WITH_AES_128_CCM_SHA256(53253, CertificateKeyAlgorithm.NONE, KeyExchangeAlgorithm.ECDHE_PSK, CipherSpec.AES_128_CCM, true),
    TLS_PSK_WITH_AES_128_GCM_SHA256(168, CertificateKeyAlgorithm.NONE, KeyExchangeAlgorithm.PSK, CipherSpec.AES_128_GCM, true),
    TLS_PSK_WITH_AES_256_GCM_SHA378(169, CertificateKeyAlgorithm.NONE, KeyExchangeAlgorithm.PSK, CipherSpec.AES_256_GCM, true, PRFAlgorithm.TLS_PRF_SHA384),
    TLS_PSK_WITH_AES_128_CCM_8(49320, CertificateKeyAlgorithm.NONE, KeyExchangeAlgorithm.PSK, CipherSpec.AES_128_CCM_8, true),
    TLS_PSK_WITH_AES_256_CCM_8(49321, CertificateKeyAlgorithm.NONE, KeyExchangeAlgorithm.PSK, CipherSpec.AES_256_CCM_8, true),
    TLS_PSK_WITH_AES_128_CCM(49316, CertificateKeyAlgorithm.NONE, KeyExchangeAlgorithm.PSK, CipherSpec.AES_128_CCM, true),
    TLS_PSK_WITH_AES_256_CCM(49317, CertificateKeyAlgorithm.NONE, KeyExchangeAlgorithm.PSK, CipherSpec.AES_256_CCM, true),
    TLS_ECDHE_PSK_WITH_AES_128_CBC_SHA256(49207, CertificateKeyAlgorithm.NONE, KeyExchangeAlgorithm.ECDHE_PSK, CipherSpec.AES_128_CBC, MACAlgorithm.HMAC_SHA256, false),
    TLS_PSK_WITH_AES_128_CBC_SHA256(174, CertificateKeyAlgorithm.NONE, KeyExchangeAlgorithm.PSK, CipherSpec.AES_128_CBC, MACAlgorithm.HMAC_SHA256, false),
    TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256(49195, CertificateKeyAlgorithm.EC, KeyExchangeAlgorithm.EC_DIFFIE_HELLMAN, CipherSpec.AES_128_GCM, true),
    TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384(49196, CertificateKeyAlgorithm.EC, KeyExchangeAlgorithm.EC_DIFFIE_HELLMAN, CipherSpec.AES_256_GCM, true, PRFAlgorithm.TLS_PRF_SHA384),
    TLS_ECDHE_ECDSA_WITH_AES_128_CCM_8(49326, CertificateKeyAlgorithm.EC, KeyExchangeAlgorithm.EC_DIFFIE_HELLMAN, CipherSpec.AES_128_CCM_8, true),
    TLS_ECDHE_ECDSA_WITH_AES_256_CCM_8(49327, CertificateKeyAlgorithm.EC, KeyExchangeAlgorithm.EC_DIFFIE_HELLMAN, CipherSpec.AES_256_CCM_8, true),
    TLS_ECDHE_ECDSA_WITH_AES_128_CCM(49324, CertificateKeyAlgorithm.EC, KeyExchangeAlgorithm.EC_DIFFIE_HELLMAN, CipherSpec.AES_128_CCM, true),
    TLS_ECDHE_ECDSA_WITH_AES_256_CCM(49325, CertificateKeyAlgorithm.EC, KeyExchangeAlgorithm.EC_DIFFIE_HELLMAN, CipherSpec.AES_256_CCM, true),
    TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256(49187, CertificateKeyAlgorithm.EC, KeyExchangeAlgorithm.EC_DIFFIE_HELLMAN, CipherSpec.AES_128_CBC, MACAlgorithm.HMAC_SHA256, false),
    TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384(49188, CertificateKeyAlgorithm.EC, KeyExchangeAlgorithm.EC_DIFFIE_HELLMAN, CipherSpec.AES_256_CBC, MACAlgorithm.HMAC_SHA384, false, PRFAlgorithm.TLS_PRF_SHA384),
    TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA(49162, CertificateKeyAlgorithm.EC, KeyExchangeAlgorithm.EC_DIFFIE_HELLMAN, CipherSpec.AES_256_CBC, MACAlgorithm.HMAC_SHA1, false),
    TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256(49199, CertificateKeyAlgorithm.RSA, KeyExchangeAlgorithm.EC_DIFFIE_HELLMAN, CipherSpec.AES_128_GCM, true),
    TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384(49200, CertificateKeyAlgorithm.RSA, KeyExchangeAlgorithm.EC_DIFFIE_HELLMAN, CipherSpec.AES_256_GCM, true, PRFAlgorithm.TLS_PRF_SHA384),
    TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256(49191, CertificateKeyAlgorithm.RSA, KeyExchangeAlgorithm.EC_DIFFIE_HELLMAN, CipherSpec.AES_128_CBC, MACAlgorithm.HMAC_SHA256, false),
    TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384(49192, CertificateKeyAlgorithm.RSA, KeyExchangeAlgorithm.EC_DIFFIE_HELLMAN, CipherSpec.AES_256_CBC, MACAlgorithm.HMAC_SHA384, false, PRFAlgorithm.TLS_PRF_SHA384),
    TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA(49172, CertificateKeyAlgorithm.RSA, KeyExchangeAlgorithm.EC_DIFFIE_HELLMAN, CipherSpec.AES_256_CBC, MACAlgorithm.HMAC_SHA1, false),
    TLS_NULL_WITH_NULL_NULL(0),
    TLS_EMPTY_RENEGOTIATION_INFO_SCSV(255);

    public static final int CIPHER_SUITE_BITS = 16;
    private static final Logger LOGGER;
    public static final List<CipherSuite> STRONG_ENCRYPTION_PREFERENCE;
    private static int overallMaxCipherTextExpansion;
    private final CertificateKeyAlgorithm certificateKeyAlgorithm;
    private final CipherSpec cipher;
    private final int code;
    private final KeyExchangeAlgorithm keyExchange;
    private final MACAlgorithm macAlgorithm;
    private final int maxCipherTextExpansion;
    private final PRFAlgorithm pseudoRandomFunction;
    private final boolean recommendedCipherSuite;
    private final boolean validForNegotiation;

    public enum CipherType {
        NULL,
        STREAM,
        BLOCK,
        AEAD
    }

    public enum KeyExchangeAlgorithm {
        NULL,
        DHE_DSS,
        DHE_RSA,
        DH_ANON,
        RSA,
        DH_DSS,
        DH_RSA,
        PSK,
        ECDHE_PSK,
        EC_DIFFIE_HELLMAN
    }

    static {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(getCipherSuitesByKeyExchangeAlgorithm(false, KeyExchangeAlgorithm.EC_DIFFIE_HELLMAN));
        arrayList.addAll(getCipherSuitesByKeyExchangeAlgorithm(false, KeyExchangeAlgorithm.ECDHE_PSK));
        arrayList.addAll(getCipherSuitesByKeyExchangeAlgorithm(false, KeyExchangeAlgorithm.PSK));
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            CipherSuite cipherSuite = (CipherSuite) it.next();
            if (cipherSuite.getMacLength() < 16) {
                arrayList2.add(cipherSuite);
                it.remove();
            }
        }
        arrayList.addAll(arrayList2);
        STRONG_ENCRYPTION_PREFERENCE = Collections.unmodifiableList(arrayList);
        LOGGER = vha.a((Class<?>) CipherSuite.class);
        overallMaxCipherTextExpansion = 0;
    }

    CipherSuite(int i) {
        this.code = i;
        this.validForNegotiation = false;
        this.certificateKeyAlgorithm = CertificateKeyAlgorithm.NONE;
        this.keyExchange = KeyExchangeAlgorithm.NULL;
        this.cipher = CipherSpec.NULL;
        this.macAlgorithm = MACAlgorithm.NULL;
        this.recommendedCipherSuite = false;
        this.pseudoRandomFunction = PRFAlgorithm.TLS_PRF_SHA256;
        this.maxCipherTextExpansion = 0;
    }

    CipherSuite(int i, CertificateKeyAlgorithm certificateKeyAlgorithm, KeyExchangeAlgorithm keyExchangeAlgorithm, CipherSpec cipherSpec, boolean z) {
        this(i, certificateKeyAlgorithm, keyExchangeAlgorithm, cipherSpec, MACAlgorithm.INTRINSIC, z, PRFAlgorithm.TLS_PRF_SHA256);
    }

    CipherSuite(int i, CertificateKeyAlgorithm certificateKeyAlgorithm, KeyExchangeAlgorithm keyExchangeAlgorithm, CipherSpec cipherSpec, MACAlgorithm mACAlgorithm, boolean z) {
        this(i, certificateKeyAlgorithm, keyExchangeAlgorithm, cipherSpec, mACAlgorithm, z, PRFAlgorithm.TLS_PRF_SHA256);
    }

    CipherSuite(int i, CertificateKeyAlgorithm certificateKeyAlgorithm, KeyExchangeAlgorithm keyExchangeAlgorithm, CipherSpec cipherSpec, boolean z, PRFAlgorithm pRFAlgorithm) {
        this(i, certificateKeyAlgorithm, keyExchangeAlgorithm, cipherSpec, MACAlgorithm.INTRINSIC, z, pRFAlgorithm);
    }

    CipherSuite(int i, CertificateKeyAlgorithm certificateKeyAlgorithm, KeyExchangeAlgorithm keyExchangeAlgorithm, CipherSpec cipherSpec, MACAlgorithm mACAlgorithm, boolean z, PRFAlgorithm pRFAlgorithm) {
        this.code = i;
        this.validForNegotiation = true;
        this.certificateKeyAlgorithm = certificateKeyAlgorithm;
        this.keyExchange = keyExchangeAlgorithm;
        this.cipher = cipherSpec;
        this.macAlgorithm = mACAlgorithm;
        this.recommendedCipherSuite = z;
        this.pseudoRandomFunction = pRFAlgorithm;
        int i2 = AnonymousClass1.f15909a[cipherSpec.getType().ordinal()];
        if (i2 == 1) {
            this.maxCipherTextExpansion = cipherSpec.getRecordIvLength() + mACAlgorithm.getOutputLength() + cipherSpec.getRecordIvLength() + 1;
        } else if (i2 != 2) {
            this.maxCipherTextExpansion = 0;
        } else {
            this.maxCipherTextExpansion = cipherSpec.getRecordIvLength() + cipherSpec.getMacLength();
        }
    }

    /* renamed from: org.eclipse.californium.scandium.dtls.cipher.CipherSuite$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f15909a;

        static {
            int[] iArr = new int[CipherType.values().length];
            f15909a = iArr;
            try {
                iArr[CipherType.BLOCK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f15909a[CipherType.AEAD.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public int getMaxCiphertextExpansion() {
        return this.maxCipherTextExpansion;
    }

    public String getTransformation() {
        return this.cipher.getTransformation();
    }

    public Cipher getThreadLocalCipher() {
        return this.cipher.getCipher();
    }

    public int getCode() {
        return this.code;
    }

    public CertificateKeyAlgorithm getCertificateKeyAlgorithm() {
        return this.certificateKeyAlgorithm;
    }

    public KeyExchangeAlgorithm getKeyExchange() {
        return this.keyExchange;
    }

    public boolean requiresServerCertificateMessage() {
        return !CertificateKeyAlgorithm.NONE.equals(this.certificateKeyAlgorithm);
    }

    public boolean isPskBased() {
        return KeyExchangeAlgorithm.PSK.equals(this.keyExchange) || KeyExchangeAlgorithm.ECDHE_PSK.equals(this.keyExchange);
    }

    public boolean isEccBased() {
        return CertificateKeyAlgorithm.EC.equals(this.certificateKeyAlgorithm) || KeyExchangeAlgorithm.ECDHE_PSK.equals(this.keyExchange) || KeyExchangeAlgorithm.EC_DIFFIE_HELLMAN.equals(this.keyExchange);
    }

    public boolean isSupported() {
        return this.pseudoRandomFunction.getMacAlgorithm().isSupported() && this.macAlgorithm.isSupported() && this.cipher.isSupported();
    }

    public boolean isRecommended() {
        return this.recommendedCipherSuite;
    }

    public boolean isValidForNegotiation() {
        return this.validForNegotiation;
    }

    public String getMacName() {
        return this.macAlgorithm.getName();
    }

    public String getMessageDigestName() {
        return this.macAlgorithm.getMessageDigestName();
    }

    public Mac getThreadLocalMac() {
        return this.macAlgorithm.getMac();
    }

    public MessageDigest getThreadLocalMacMessageDigest() {
        return this.macAlgorithm.getMessageDigest();
    }

    public int getMacLength() {
        if (this.macAlgorithm != MACAlgorithm.INTRINSIC) {
            return this.macAlgorithm.getOutputLength();
        }
        return this.cipher.getMacLength();
    }

    public int getMacKeyLength() {
        return this.macAlgorithm.getKeyLength();
    }

    public int getMacMessageBlockLength() {
        return this.macAlgorithm.getMessageBlockLength();
    }

    public int getMacMessageLengthBytes() {
        return this.macAlgorithm.getMessageLengthBytes();
    }

    public int getRecordIvLength() {
        return this.cipher.getRecordIvLength();
    }

    public int getFixedIvLength() {
        return this.cipher.getFixedIvLength();
    }

    public String getPseudoRandomFunctionMacName() {
        return this.pseudoRandomFunction.getMacAlgorithm().getName();
    }

    public String getPseudoRandomFunctionMessageDigestName() {
        return this.pseudoRandomFunction.getMacAlgorithm().getMessageDigestName();
    }

    public Mac getThreadLocalPseudoRandomFunctionMac() {
        return this.pseudoRandomFunction.getMacAlgorithm().getMac();
    }

    public MessageDigest getThreadLocalPseudoRandomFunctionMessageDigest() {
        return this.pseudoRandomFunction.getMacAlgorithm().getMessageDigest();
    }

    public CipherType getCipherType() {
        return this.cipher.getType();
    }

    public int getEncKeyLength() {
        return this.cipher.getKeyLength();
    }

    public static int getOverallMaxCiphertextExpansion() {
        if (overallMaxCipherTextExpansion == 0) {
            int i = 0;
            for (CipherSuite cipherSuite : values()) {
                if (cipherSuite.isSupported()) {
                    i = Math.max(i, cipherSuite.getMaxCiphertextExpansion());
                }
            }
            overallMaxCipherTextExpansion = i;
        }
        return overallMaxCipherTextExpansion;
    }

    public static CipherSuite[] getCipherSuites(boolean z, boolean z2) {
        ArrayList arrayList = new ArrayList();
        for (CipherSuite cipherSuite : values()) {
            if (cipherSuite.isValidForNegotiation() && ((!z2 || cipherSuite.isSupported()) && (!z || cipherSuite.isRecommended()))) {
                arrayList.add(cipherSuite);
            }
        }
        return (CipherSuite[]) arrayList.toArray(new CipherSuite[arrayList.size()]);
    }

    public static List<CipherSuite> getCipherSuitesByKeyExchangeAlgorithm(boolean z, KeyExchangeAlgorithm... keyExchangeAlgorithmArr) {
        if (keyExchangeAlgorithmArr == null) {
            throw new NullPointerException("KeyExchangeAlgorithms must not be null!");
        }
        if (keyExchangeAlgorithmArr.length == 0) {
            throw new IllegalArgumentException("KeyExchangeAlgorithms must not be empty!");
        }
        return getCipherSuitesByKeyExchangeAlgorithm(z, false, Arrays.asList(keyExchangeAlgorithmArr));
    }

    public static List<CipherSuite> getCipherSuitesByKeyExchangeAlgorithm(boolean z, boolean z2, List<KeyExchangeAlgorithm> list) {
        if (list == null) {
            throw new NullPointerException("KeyExchangeAlgorithms must not be null!");
        }
        if (list.isEmpty()) {
            throw new IllegalArgumentException("KeyExchangeAlgorithms must not be empty!");
        }
        ArrayList arrayList = new ArrayList();
        if (z2) {
            for (KeyExchangeAlgorithm keyExchangeAlgorithm : list) {
                for (CipherSuite cipherSuite : values()) {
                    if ((!z || cipherSuite.recommendedCipherSuite) && cipherSuite.isSupported() && keyExchangeAlgorithm.equals(cipherSuite.keyExchange)) {
                        arrayList.add(cipherSuite);
                    }
                }
            }
        } else {
            for (CipherSuite cipherSuite2 : values()) {
                if ((!z || cipherSuite2.recommendedCipherSuite) && cipherSuite2.isSupported() && list.contains(cipherSuite2.keyExchange)) {
                    arrayList.add(cipherSuite2);
                }
            }
        }
        return arrayList;
    }

    public static List<CipherSuite> getCertificateCipherSuites(boolean z, PublicKey publicKey) {
        if (publicKey == null) {
            throw new NullPointerException("Public key must not be null!");
        }
        return getCertificateCipherSuites(z, (List<CertificateKeyAlgorithm>) Arrays.asList(CertificateKeyAlgorithm.getAlgorithm(publicKey)));
    }

    public static List<CipherSuite> getCertificateCipherSuites(boolean z, CertificateKeyAlgorithm... certificateKeyAlgorithmArr) {
        if (certificateKeyAlgorithmArr == null) {
            throw new NullPointerException("Certificate key algorithms must not be null!");
        }
        if (certificateKeyAlgorithmArr.length == 0) {
            throw new IllegalArgumentException("Certificate key algorithms must not be empty!");
        }
        return getCertificateCipherSuites(z, (List<CertificateKeyAlgorithm>) Arrays.asList(certificateKeyAlgorithmArr));
    }

    public static List<CipherSuite> getCertificateCipherSuites(boolean z, List<CertificateKeyAlgorithm> list) {
        if (list == null) {
            throw new NullPointerException("Certificate key algorithms must not be null!");
        }
        if (list.isEmpty()) {
            throw new IllegalArgumentException("Certificate key algorithms must not be empty!");
        }
        ArrayList arrayList = new ArrayList();
        for (CipherSuite cipherSuite : values()) {
            if (cipherSuite.isSupported() && ((!z || cipherSuite.recommendedCipherSuite) && list.contains(cipherSuite.certificateKeyAlgorithm))) {
                arrayList.add(cipherSuite);
            }
        }
        return arrayList;
    }

    public static List<CertificateKeyAlgorithm> getCertificateKeyAlgorithms(List<CipherSuite> list) {
        ArrayList arrayList = new ArrayList();
        for (CipherSuite cipherSuite : list) {
            if (cipherSuite.getCertificateKeyAlgorithm() != CertificateKeyAlgorithm.NONE) {
                vfb.a(arrayList, cipherSuite.getCertificateKeyAlgorithm());
            }
        }
        return arrayList;
    }

    public static CipherSuite getTypeByCode(int i) {
        for (CipherSuite cipherSuite : values()) {
            if (cipherSuite.code == i) {
                return cipherSuite;
            }
        }
        Logger logger = LOGGER;
        if (!logger.isTraceEnabled()) {
            return null;
        }
        logger.trace("Cannot resolve cipher suite code [{}]", Integer.toHexString(i));
        return null;
    }

    public static CipherSuite getTypeByName(String str) {
        for (CipherSuite cipherSuite : values()) {
            if (cipherSuite.name().equals(str)) {
                return cipherSuite;
            }
        }
        Logger logger = LOGGER;
        if (!logger.isTraceEnabled()) {
            return null;
        }
        logger.trace("Cannot resolve cipher suite code [{}]", str);
        return null;
    }

    public static List<CipherSuite> getTypesByNames(String... strArr) {
        ArrayList arrayList = new ArrayList(strArr.length);
        for (int i = 0; i < strArr.length; i++) {
            CipherSuite typeByName = getTypeByName(strArr[i]);
            if (typeByName != null) {
                arrayList.add(typeByName);
            } else {
                throw new IllegalArgumentException(String.format("Cipher suite [%s] is not (yet) supported", strArr[i]));
            }
        }
        return arrayList;
    }

    public static boolean containsPskBasedCipherSuite(List<CipherSuite> list) {
        if (list == null) {
            return false;
        }
        Iterator<CipherSuite> it = list.iterator();
        while (it.hasNext()) {
            if (it.next().isPskBased()) {
                return true;
            }
        }
        return false;
    }

    public static boolean containsEccBasedCipherSuite(List<CipherSuite> list) {
        if (list == null) {
            return false;
        }
        Iterator<CipherSuite> it = list.iterator();
        while (it.hasNext()) {
            if (it.next().isEccBased()) {
                return true;
            }
        }
        return false;
    }

    public static boolean containsCipherSuiteRequiringCertExchange(List<CipherSuite> list) {
        if (list == null) {
            return false;
        }
        Iterator<CipherSuite> it = list.iterator();
        while (it.hasNext()) {
            if (it.next().requiresServerCertificateMessage()) {
                return true;
            }
        }
        return false;
    }

    public static List<CipherSuite> preselectCipherSuites(List<CipherSuite> list, List<CipherSuite> list2) {
        if (list == null) {
            throw new NullPointerException("The cipher-suites must not be null!");
        }
        if (list2 == null) {
            throw new NullPointerException("The preselected cipher-suites must not be null!");
        }
        if (list.isEmpty()) {
            throw new IllegalArgumentException("The cipher-suites must not be empty");
        }
        if (list2.isEmpty()) {
            throw new IllegalArgumentException("The preselected cipher-suites must not be empty!");
        }
        ArrayList arrayList = new ArrayList();
        for (CipherSuite cipherSuite : list2) {
            if (list.contains(cipherSuite)) {
                arrayList.add(cipherSuite);
            }
        }
        return arrayList;
    }

    public static void listToWriter(vbo vboVar, List<CipherSuite> list) {
        Iterator<CipherSuite> it = list.iterator();
        while (it.hasNext()) {
            vboVar.b(it.next().getCode(), 16);
        }
    }

    public static List<CipherSuite> listFromReader(vbn vbnVar) {
        ArrayList arrayList = new ArrayList();
        while (vbnVar.e()) {
            CipherSuite typeByCode = getTypeByCode(vbnVar.c(16));
            if (typeByCode != null) {
                arrayList.add(typeByCode);
            }
        }
        return arrayList;
    }

    enum MACAlgorithm {
        NULL(null, null, 0, 0, 0),
        INTRINSIC(null, null, 0, 0, 0),
        HMAC_SHA1("HmacSHA1", "SHA-1", 20, 8, 64),
        HMAC_SHA256("HmacSHA256", "SHA-256", 32, 8, 64),
        HMAC_SHA384("HmacSHA384", "SHA-384", 48, 16, 128),
        HMAC_SHA512("HmacSHA512", "SHA-512", 64, 16, 128);

        private final vey mac;
        private final vew md;
        private final String mdName;
        private final int messageBlockLength;
        private final int messageLengthBytes;
        private final String name;
        private final int outputLength;
        private final boolean supported;

        MACAlgorithm(String str, String str2, int i, int i2, int i3) {
            this.name = str;
            this.mdName = str2;
            this.outputLength = i;
            this.messageLengthBytes = i2;
            this.messageBlockLength = i3;
            if (str == null && str2 == null) {
                this.supported = true;
                this.mac = null;
                this.md = null;
            } else {
                vey veyVar = new vey(str);
                this.mac = veyVar;
                vew vewVar = new vew(str2);
                this.md = vewVar;
                this.supported = veyVar.c() && vewVar.c();
            }
        }

        public String getName() {
            return this.name;
        }

        public String getMessageDigestName() {
            return this.mdName;
        }

        public int getOutputLength() {
            return this.outputLength;
        }

        public int getKeyLength() {
            return this.outputLength;
        }

        public int getMessageBlockLength() {
            return this.messageBlockLength;
        }

        public int getMessageLengthBytes() {
            return this.messageLengthBytes;
        }

        public boolean isSupported() {
            return this.supported;
        }

        public Mac getMac() {
            vey veyVar = this.mac;
            if (veyVar != null) {
                return veyVar.b();
            }
            return null;
        }

        public MessageDigest getMessageDigest() {
            vew vewVar = this.md;
            if (vewVar == null) {
                return null;
            }
            MessageDigest b = vewVar.b();
            b.reset();
            return b;
        }
    }

    enum CipherSpec {
        NULL("NULL", CipherType.NULL, 0, 0, 0),
        AES_128_CBC("AES/CBC/NoPadding", CipherType.BLOCK, 16, 0, 16),
        AES_256_CBC("AES/CBC/NoPadding", CipherType.BLOCK, 32, 0, 16),
        AES_128_CCM_8("AES/CCM/NoPadding", CipherType.AEAD, 16, 4, 8, 8),
        AES_256_CCM_8("AES/CCM/NoPadding", CipherType.AEAD, 32, 4, 8, 8),
        AES_128_CCM("AES/CCM/NoPadding", CipherType.AEAD, 16, 4, 8, 16),
        AES_256_CCM("AES/CCM/NoPadding", CipherType.AEAD, 32, 4, 8, 16),
        AES_128_GCM("AES/GCM/NoPadding", CipherType.AEAD, 16, 4, 8, 16),
        AES_256_GCM("AES/GCM/NoPadding", CipherType.AEAD, 32, 4, 8, 16);

        private final ves cipher;
        private final int fixedIvLength;
        private final int keyLength;
        private final int macLength;
        private final int recordIvLength;
        private final boolean supported;
        private final String transformation;
        private final CipherType type;

        CipherSpec(String str, CipherType cipherType, int i, int i2, int i3) {
            this(str, cipherType, i, i2, i3, 0);
        }

        CipherSpec(String str, CipherType cipherType, int i, int i2, int i3, int i4) {
            this.transformation = str;
            this.type = cipherType;
            this.keyLength = i;
            this.fixedIvLength = i2;
            this.recordIvLength = i3;
            this.macLength = i4;
            boolean d = (cipherType == CipherType.AEAD || cipherType == CipherType.BLOCK) ? vem.d(str, i) : true;
            if ("AES/CCM/NoPadding".equals(str)) {
                this.cipher = null;
                this.supported = d;
            } else {
                ves vesVar = d ? new ves(str) : null;
                this.cipher = vesVar;
                this.supported = vesVar == null ? false : vesVar.c();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public String getTransformation() {
            return this.transformation;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public CipherType getType() {
            return this.type;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int getKeyLength() {
            return this.keyLength;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int getFixedIvLength() {
            return this.fixedIvLength;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int getRecordIvLength() {
            return this.recordIvLength;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int getMacLength() {
            return this.macLength;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isSupported() {
            return this.supported;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Cipher getCipher() {
            ves vesVar = this.cipher;
            if (vesVar == null) {
                return null;
            }
            return vesVar.b();
        }
    }

    enum PRFAlgorithm {
        TLS_PRF_SHA256(MACAlgorithm.HMAC_SHA256),
        TLS_PRF_SHA384(MACAlgorithm.HMAC_SHA384);

        private final MACAlgorithm mac;

        PRFAlgorithm(MACAlgorithm mACAlgorithm) {
            this.mac = mACAlgorithm;
        }

        public MACAlgorithm getMacAlgorithm() {
            return this.mac;
        }
    }

    public enum CertificateKeyAlgorithm {
        NONE,
        DSA,
        RSA,
        EC;

        public boolean isCompatible(PublicKey publicKey) {
            if (this == NONE) {
                return publicKey == null;
            }
            if (publicKey == null) {
                return false;
            }
            return isCompatible(publicKey.getAlgorithm());
        }

        public boolean isCompatible(String str) {
            if (str.equalsIgnoreCase(name())) {
                return true;
            }
            if (this == EC) {
                return vbm.d(str);
            }
            return false;
        }

        public boolean isCompatible(List<String> list) {
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                if (isCompatible(it.next())) {
                    return true;
                }
            }
            return false;
        }

        public static CertificateKeyAlgorithm getAlgorithm(PublicKey publicKey) {
            for (CertificateKeyAlgorithm certificateKeyAlgorithm : values()) {
                if (certificateKeyAlgorithm.isCompatible(publicKey)) {
                    return certificateKeyAlgorithm;
                }
            }
            return null;
        }
    }
}
