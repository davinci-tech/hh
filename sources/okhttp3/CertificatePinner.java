package okhttp3;

import com.huawei.health.messagecenter.model.MessageConstant;
import com.huawei.hms.network.embedded.v6;
import com.huawei.hms.network.embedded.y;
import com.huawei.hms.support.hwid.common.constants.CommonConstant;
import com.huawei.watchface.utils.constants.WatchFaceConstant;
import defpackage.uez;
import defpackage.ufe;
import defpackage.uhy;
import defpackage.uib;
import defpackage.uii;
import defpackage.ujy;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.net.ssl.SSLPeerUnverifiedException;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import okhttp3.internal.HostnamesKt;
import okhttp3.internal.tls.CertificateChainCleaner;
import okio.ByteString;
import org.apache.commons.io.FilenameUtils;

@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\u0018\u0000 \"2\u00020\u0001:\u0003!\"#B!\b\u0000\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J)\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0012\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120\u0011H\u0000¢\u0006\u0002\b\u0014J)\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0012\u0010\u0015\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00170\u0016\"\u00020\u0017H\u0007¢\u0006\u0002\u0010\u0018J\u001c\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0012J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\u0014\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00040\u00122\u0006\u0010\u000e\u001a\u00020\u000fJ\b\u0010\u001d\u001a\u00020\u001eH\u0016J\u0015\u0010\u001f\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0006H\u0000¢\u0006\u0002\b R\u0016\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006$"}, d2 = {"Lokhttp3/CertificatePinner;", "", "pins", "", "Lokhttp3/CertificatePinner$Pin;", "certificateChainCleaner", "Lokhttp3/internal/tls/CertificateChainCleaner;", "(Ljava/util/Set;Lokhttp3/internal/tls/CertificateChainCleaner;)V", "getCertificateChainCleaner$okhttp", "()Lokhttp3/internal/tls/CertificateChainCleaner;", "getPins", "()Ljava/util/Set;", "check", "", "hostname", "", "cleanedPeerCertificatesFn", "Lkotlin/Function0;", "", "Ljava/security/cert/X509Certificate;", "check$okhttp", "peerCertificates", "", "Ljava/security/cert/Certificate;", "(Ljava/lang/String;[Ljava/security/cert/Certificate;)V", "equals", "", "other", "findMatchingPins", WatchFaceConstant.HASHCODE, "", "withCertificateChainCleaner", "withCertificateChainCleaner$okhttp", "Builder", "Companion", "Pin", y.b.j}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class CertificatePinner {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final CertificatePinner DEFAULT = new Builder().build();
    private final CertificateChainCleaner certificateChainCleaner;
    private final Set<Pin> pins;

    public CertificatePinner(Set<Pin> set, CertificateChainCleaner certificateChainCleaner) {
        uhy.e((Object) set, "");
        this.pins = set;
        this.certificateChainCleaner = certificateChainCleaner;
    }

    public /* synthetic */ CertificatePinner(Set set, CertificateChainCleaner certificateChainCleaner, int i, uib uibVar) {
        this(set, (i & 2) != 0 ? null : certificateChainCleaner);
    }

    public final Set<Pin> getPins() {
        return this.pins;
    }

    /* renamed from: getCertificateChainCleaner$okhttp, reason: from getter */
    public final CertificateChainCleaner getCertificateChainCleaner() {
        return this.certificateChainCleaner;
    }

    public final void check(final String hostname, final List<? extends Certificate> peerCertificates) throws SSLPeerUnverifiedException {
        uhy.e((Object) hostname, "");
        uhy.e((Object) peerCertificates, "");
        check$okhttp(hostname, new Function0<List<? extends X509Certificate>>() { // from class: okhttp3.CertificatePinner$check$1
            @Override // kotlin.jvm.functions.Function0
            public final List<? extends X509Certificate> invoke() {
                List<Certificate> list;
                CertificateChainCleaner certificateChainCleaner = CertificatePinner.this.getCertificateChainCleaner();
                if (certificateChainCleaner == null || (list = certificateChainCleaner.clean(peerCertificates, hostname)) == null) {
                    list = peerCertificates;
                }
                List<Certificate> list2 = list;
                ArrayList arrayList = new ArrayList(ufe.d(list2, 10));
                for (Certificate certificate : list2) {
                    uhy.b(certificate, "");
                    arrayList.add((X509Certificate) certificate);
                }
                return arrayList;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(0);
            }
        });
    }

    public final void check$okhttp(String hostname, Function0<? extends List<? extends X509Certificate>> cleanedPeerCertificatesFn) {
        uhy.e((Object) hostname, "");
        uhy.e((Object) cleanedPeerCertificatesFn, "");
        List<Pin> findMatchingPins = findMatchingPins(hostname);
        if (findMatchingPins.isEmpty()) {
            return;
        }
        List<? extends X509Certificate> invoke = cleanedPeerCertificatesFn.invoke();
        for (X509Certificate x509Certificate : invoke) {
            ByteString byteString = null;
            ByteString byteString2 = null;
            for (Pin pin : findMatchingPins) {
                String hashAlgorithm = pin.getHashAlgorithm();
                if (uhy.e((Object) hashAlgorithm, (Object) "sha256")) {
                    if (byteString == null) {
                        byteString = INSTANCE.sha256Hash(x509Certificate);
                    }
                    if (uhy.e(pin.getHash(), byteString)) {
                        return;
                    }
                } else if (uhy.e((Object) hashAlgorithm, (Object) "sha1")) {
                    if (byteString2 == null) {
                        byteString2 = INSTANCE.sha1Hash(x509Certificate);
                    }
                    if (uhy.e(pin.getHash(), byteString2)) {
                        return;
                    }
                } else {
                    throw new AssertionError("unsupported hashAlgorithm: " + pin.getHashAlgorithm());
                }
            }
        }
        StringBuilder sb = new StringBuilder("Certificate pinning failure!\n  Peer certificate chain:");
        for (X509Certificate x509Certificate2 : invoke) {
            sb.append("\n    ");
            sb.append(INSTANCE.pin(x509Certificate2));
            sb.append(": ");
            sb.append(x509Certificate2.getSubjectDN().getName());
        }
        sb.append("\n  Pinned certificates for ");
        sb.append(hostname);
        sb.append(":");
        for (Pin pin2 : findMatchingPins) {
            sb.append("\n    ");
            sb.append(pin2);
        }
        String sb2 = sb.toString();
        uhy.a(sb2, "");
        throw new SSLPeerUnverifiedException(sb2);
    }

    @Deprecated(message = "replaced with {@link #check(String, List)}.", replaceWith = @ReplaceWith(expression = "check(hostname, peerCertificates.toList())", imports = {}))
    public final void check(String hostname, Certificate... peerCertificates) throws SSLPeerUnverifiedException {
        uhy.e((Object) hostname, "");
        uhy.e((Object) peerCertificates, "");
        check(hostname, uez.e(peerCertificates));
    }

    public final List<Pin> findMatchingPins(String hostname) {
        uhy.e((Object) hostname, "");
        Set<Pin> set = this.pins;
        ArrayList b = ufe.b();
        for (Object obj : set) {
            if (((Pin) obj).matchesHostname(hostname)) {
                if (b.isEmpty()) {
                    b = new ArrayList();
                }
                uhy.b(b, "");
                uii.c(b).add(obj);
            }
        }
        return b;
    }

    public final CertificatePinner withCertificateChainCleaner$okhttp(CertificateChainCleaner certificateChainCleaner) {
        uhy.e((Object) certificateChainCleaner, "");
        return uhy.e(this.certificateChainCleaner, certificateChainCleaner) ? this : new CertificatePinner(this.pins, certificateChainCleaner);
    }

    public boolean equals(Object other) {
        if (other instanceof CertificatePinner) {
            CertificatePinner certificatePinner = (CertificatePinner) other;
            if (uhy.e(certificatePinner.pins, this.pins) && uhy.e(certificatePinner.certificateChainCleaner, this.certificateChainCleaner)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int hashCode = this.pins.hashCode();
        CertificateChainCleaner certificateChainCleaner = this.certificateChainCleaner;
        return ((hashCode + 1517) * 41) + (certificateChainCleaner != null ? certificateChainCleaner.hashCode() : 0);
    }

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\u000e\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u0015J\u000e\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\u0003J\b\u0010\u0018\u001a\u00020\u0003H\u0016R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\f¨\u0006\u0019"}, d2 = {"Lokhttp3/CertificatePinner$Pin;", "", "pattern", "", "pin", "(Ljava/lang/String;Ljava/lang/String;)V", "hash", "Lokio/ByteString;", "getHash", "()Lokio/ByteString;", "hashAlgorithm", "getHashAlgorithm", "()Ljava/lang/String;", "getPattern", "equals", "", "other", WatchFaceConstant.HASHCODE, "", "matchesCertificate", MessageConstant.CERTIFICATE_TYPE, "Ljava/security/cert/X509Certificate;", "matchesHostname", "hostname", "toString", y.b.j}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Pin {
        private final ByteString hash;
        private final String hashAlgorithm;
        private final String pattern;

        public Pin(String str, String str2) {
            uhy.e((Object) str, "");
            uhy.e((Object) str2, "");
            if ((!ujy.c(str, v6.b.e, false, 2, (Object) null) || ujy.c((CharSequence) str, "*", 1, false, 4, (Object) null) != -1) && ((!ujy.c(str, "**.", false, 2, (Object) null) || ujy.c((CharSequence) str, "*", 2, false, 4, (Object) null) != -1) && ujy.c((CharSequence) str, "*", 0, false, 6, (Object) null) != -1)) {
                throw new IllegalArgumentException(("Unexpected pattern: " + str).toString());
            }
            String canonicalHost = HostnamesKt.toCanonicalHost(str);
            if (canonicalHost != null) {
                this.pattern = canonicalHost;
                if (ujy.c(str2, "sha1/", false, 2, (Object) null)) {
                    this.hashAlgorithm = "sha1";
                    ByteString.Companion companion = ByteString.INSTANCE;
                    String substring = str2.substring(5);
                    uhy.a(substring, "");
                    ByteString decodeBase64 = companion.decodeBase64(substring);
                    if (decodeBase64 != null) {
                        this.hash = decodeBase64;
                        return;
                    } else {
                        throw new IllegalArgumentException("Invalid pin hash: " + str2);
                    }
                }
                if (ujy.c(str2, "sha256/", false, 2, (Object) null)) {
                    this.hashAlgorithm = "sha256";
                    ByteString.Companion companion2 = ByteString.INSTANCE;
                    String substring2 = str2.substring(7);
                    uhy.a(substring2, "");
                    ByteString decodeBase642 = companion2.decodeBase64(substring2);
                    if (decodeBase642 != null) {
                        this.hash = decodeBase642;
                        return;
                    } else {
                        throw new IllegalArgumentException("Invalid pin hash: " + str2);
                    }
                }
                throw new IllegalArgumentException("pins must start with 'sha256/' or 'sha1/': " + str2);
            }
            throw new IllegalArgumentException("Invalid pattern: " + str);
        }

        public final String getPattern() {
            return this.pattern;
        }

        public final String getHashAlgorithm() {
            return this.hashAlgorithm;
        }

        public final ByteString getHash() {
            return this.hash;
        }

        public final boolean matchesHostname(String hostname) {
            uhy.e((Object) hostname, "");
            if (ujy.c(this.pattern, "**.", false, 2, (Object) null)) {
                int length = this.pattern.length() - 3;
                int length2 = hostname.length() - length;
                if (!ujy.c(hostname, hostname.length() - length, this.pattern, 3, length, false, 16, null)) {
                    return false;
                }
                if (length2 != 0 && hostname.charAt(length2 - 1) != '.') {
                    return false;
                }
            } else if (ujy.c(this.pattern, v6.b.e, false, 2, (Object) null)) {
                int length3 = this.pattern.length() - 1;
                int length4 = hostname.length();
                if (!ujy.c(hostname, hostname.length() - length3, this.pattern, 1, length3, false, 16, null) || ujy.c((CharSequence) hostname, FilenameUtils.EXTENSION_SEPARATOR, (length4 - length3) - 1, false, 4, (Object) null) != -1) {
                    return false;
                }
            } else {
                return uhy.e((Object) hostname, (Object) this.pattern);
            }
            return true;
        }

        public final boolean matchesCertificate(X509Certificate certificate) {
            uhy.e((Object) certificate, "");
            String str = this.hashAlgorithm;
            if (uhy.e((Object) str, (Object) "sha256")) {
                return uhy.e(this.hash, CertificatePinner.INSTANCE.sha256Hash(certificate));
            }
            if (uhy.e((Object) str, (Object) "sha1")) {
                return uhy.e(this.hash, CertificatePinner.INSTANCE.sha1Hash(certificate));
            }
            return false;
        }

        public String toString() {
            return this.hashAlgorithm + '/' + this.hash.base64();
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Pin)) {
                return false;
            }
            Pin pin = (Pin) other;
            return uhy.e((Object) this.pattern, (Object) pin.pattern) && uhy.e((Object) this.hashAlgorithm, (Object) pin.hashAlgorithm) && uhy.e(this.hash, pin.hash);
        }

        public int hashCode() {
            return (((this.pattern.hashCode() * 31) + this.hashAlgorithm.hashCode()) * 31) + this.hash.hashCode();
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J'\u0010\b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\n2\u0012\u0010\u0003\u001a\n\u0012\u0006\b\u0001\u0012\u00020\n0\u000b\"\u00020\n¢\u0006\u0002\u0010\fJ\u0006\u0010\r\u001a\u00020\u000eR\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000f"}, d2 = {"Lokhttp3/CertificatePinner$Builder;", "", "()V", "pins", "", "Lokhttp3/CertificatePinner$Pin;", "getPins", "()Ljava/util/List;", "add", "pattern", "", "", "(Ljava/lang/String;[Ljava/lang/String;)Lokhttp3/CertificatePinner$Builder;", "build", "Lokhttp3/CertificatePinner;", y.b.j}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Builder {
        private final List<Pin> pins = new ArrayList();

        public final List<Pin> getPins() {
            return this.pins;
        }

        public final Builder add(String pattern, String... pins) {
            uhy.e((Object) pattern, "");
            uhy.e((Object) pins, "");
            for (String str : pins) {
                this.pins.add(new Pin(pattern, str));
            }
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final CertificatePinner build() {
            return new CertificatePinner(ufe.i((Iterable) this.pins), null, 2, 0 == true ? 1 : 0);
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\f\u0010\t\u001a\u00020\n*\u00020\u000bH\u0007J\f\u0010\f\u001a\u00020\n*\u00020\u000bH\u0007R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lokhttp3/CertificatePinner$Companion;", "", "()V", CommonConstant.StartQrLoginQrValue.QRSCENE_DEFAULT, "Lokhttp3/CertificatePinner;", "pin", "", MessageConstant.CERTIFICATE_TYPE, "Ljava/security/cert/Certificate;", "sha1Hash", "Lokio/ByteString;", "Ljava/security/cert/X509Certificate;", "sha256Hash", y.b.j}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        public final ByteString sha1Hash(X509Certificate x509Certificate) {
            uhy.e((Object) x509Certificate, "");
            ByteString.Companion companion = ByteString.INSTANCE;
            byte[] encoded = x509Certificate.getPublicKey().getEncoded();
            uhy.a(encoded, "");
            return ByteString.Companion.of$default(companion, encoded, 0, 0, 3, null).sha1();
        }

        @JvmStatic
        public final ByteString sha256Hash(X509Certificate x509Certificate) {
            uhy.e((Object) x509Certificate, "");
            ByteString.Companion companion = ByteString.INSTANCE;
            byte[] encoded = x509Certificate.getPublicKey().getEncoded();
            uhy.a(encoded, "");
            return ByteString.Companion.of$default(companion, encoded, 0, 0, 3, null).sha256();
        }

        @JvmStatic
        public final String pin(Certificate certificate) {
            uhy.e((Object) certificate, "");
            if (!(certificate instanceof X509Certificate)) {
                throw new IllegalArgumentException("Certificate pinning requires X509 certificates".toString());
            }
            return "sha256/" + sha256Hash((X509Certificate) certificate).base64();
        }

        public /* synthetic */ Companion(uib uibVar) {
            this();
        }
    }

    @JvmStatic
    public static final ByteString sha256Hash(X509Certificate x509Certificate) {
        return INSTANCE.sha256Hash(x509Certificate);
    }

    @JvmStatic
    public static final ByteString sha1Hash(X509Certificate x509Certificate) {
        return INSTANCE.sha1Hash(x509Certificate);
    }

    @JvmStatic
    public static final String pin(Certificate certificate) {
        return INSTANCE.pin(certificate);
    }
}
