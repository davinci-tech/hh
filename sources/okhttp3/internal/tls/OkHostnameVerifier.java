package okhttp3.internal.tls;

import com.huawei.health.messagecenter.model.MessageConstant;
import com.huawei.hms.network.embedded.v6;
import com.huawei.hms.network.embedded.y;
import defpackage.ufe;
import defpackage.uhy;
import defpackage.ujy;
import java.security.cert.Certificate;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import kotlin.Metadata;
import okhttp3.internal.HostnamesKt;
import okhttp3.internal.Util;
import okio.Utf8;
import org.apache.commons.io.FilenameUtils;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\nJ\u001e\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\u0004H\u0002J\u0016\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0018\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\u001c\u0010\u0012\u001a\u00020\u000e2\b\u0010\u0013\u001a\u0004\u0018\u00010\b2\b\u0010\u0014\u001a\u0004\u0018\u00010\bH\u0002J\u0018\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\f\u0010\u0017\u001a\u00020\b*\u00020\bH\u0002J\f\u0010\u0018\u001a\u00020\u000e*\u00020\bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lokhttp3/internal/tls/OkHostnameVerifier;", "Ljavax/net/ssl/HostnameVerifier;", "()V", "ALT_DNS_NAME", "", "ALT_IPA_NAME", "allSubjectAltNames", "", "", MessageConstant.CERTIFICATE_TYPE, "Ljava/security/cert/X509Certificate;", "getSubjectAltNames", "type", "verify", "", "host", "session", "Ljavax/net/ssl/SSLSession;", "verifyHostname", "hostname", "pattern", "verifyIpAddress", "ipAddress", "asciiToLowercase", "isAscii", y.b.j}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class OkHostnameVerifier implements HostnameVerifier {
    private static final int ALT_DNS_NAME = 2;
    private static final int ALT_IPA_NAME = 7;
    public static final OkHostnameVerifier INSTANCE = new OkHostnameVerifier();

    private OkHostnameVerifier() {
    }

    @Override // javax.net.ssl.HostnameVerifier
    public boolean verify(String host, SSLSession session) {
        uhy.e((Object) host, "");
        uhy.e((Object) session, "");
        if (!isAscii(host)) {
            return false;
        }
        try {
            Certificate certificate = session.getPeerCertificates()[0];
            uhy.b(certificate, "");
            return verify(host, (X509Certificate) certificate);
        } catch (SSLException unused) {
            return false;
        }
    }

    public final boolean verify(String host, X509Certificate certificate) {
        uhy.e((Object) host, "");
        uhy.e((Object) certificate, "");
        return Util.canParseAsIpAddress(host) ? verifyIpAddress(host, certificate) : verifyHostname(host, certificate);
    }

    private final boolean verifyIpAddress(String ipAddress, X509Certificate certificate) {
        String canonicalHost = HostnamesKt.toCanonicalHost(ipAddress);
        List<String> subjectAltNames = getSubjectAltNames(certificate, 7);
        if (!(subjectAltNames instanceof Collection) || !subjectAltNames.isEmpty()) {
            Iterator<T> it = subjectAltNames.iterator();
            while (it.hasNext()) {
                if (uhy.e((Object) canonicalHost, (Object) HostnamesKt.toCanonicalHost((String) it.next()))) {
                    return true;
                }
            }
        }
        return false;
    }

    private final boolean verifyHostname(String hostname, X509Certificate certificate) {
        String asciiToLowercase = asciiToLowercase(hostname);
        List<String> subjectAltNames = getSubjectAltNames(certificate, 2);
        if (!(subjectAltNames instanceof Collection) || !subjectAltNames.isEmpty()) {
            Iterator<T> it = subjectAltNames.iterator();
            while (it.hasNext()) {
                if (INSTANCE.verifyHostname(asciiToLowercase, (String) it.next())) {
                    return true;
                }
            }
        }
        return false;
    }

    private final String asciiToLowercase(String str) {
        if (!isAscii(str)) {
            return str;
        }
        Locale locale = Locale.US;
        uhy.a(locale, "");
        String lowerCase = str.toLowerCase(locale);
        uhy.a(lowerCase, "");
        return lowerCase;
    }

    private final boolean isAscii(String str) {
        return str.length() == ((int) Utf8.size$default(str, 0, 0, 3, null));
    }

    private final boolean verifyHostname(String hostname, String pattern) {
        String str;
        String str2 = hostname;
        if (str2 != null && str2.length() != 0 && !ujy.c(hostname, ".", false, 2, (Object) null) && !ujy.b(hostname, "..", false, 2, (Object) null) && (str = pattern) != null && str.length() != 0 && !ujy.c(pattern, ".", false, 2, (Object) null) && !ujy.b(pattern, "..", false, 2, (Object) null)) {
            if (!ujy.b(hostname, ".", false, 2, (Object) null)) {
                hostname = hostname + FilenameUtils.EXTENSION_SEPARATOR;
            }
            if (!ujy.b(pattern, ".", false, 2, (Object) null)) {
                pattern = pattern + FilenameUtils.EXTENSION_SEPARATOR;
            }
            String asciiToLowercase = asciiToLowercase(pattern);
            String str3 = asciiToLowercase;
            if (!ujy.e((CharSequence) str3, (CharSequence) "*", false, 2, (Object) null)) {
                return uhy.e((Object) hostname, (Object) asciiToLowercase);
            }
            if (!ujy.c(asciiToLowercase, v6.b.e, false, 2, (Object) null) || ujy.a((CharSequence) str3, '*', 1, false, 4, (Object) null) != -1 || hostname.length() < asciiToLowercase.length() || uhy.e((Object) v6.b.e, (Object) asciiToLowercase)) {
                return false;
            }
            String substring = asciiToLowercase.substring(1);
            uhy.a(substring, "");
            if (!ujy.b(hostname, substring, false, 2, (Object) null)) {
                return false;
            }
            int length = hostname.length() - substring.length();
            return length <= 0 || ujy.c((CharSequence) hostname, FilenameUtils.EXTENSION_SEPARATOR, length + (-1), false, 4, (Object) null) == -1;
        }
        return false;
    }

    public final List<String> allSubjectAltNames(X509Certificate certificate) {
        uhy.e((Object) certificate, "");
        return ufe.c((Collection) getSubjectAltNames(certificate, 7), (Iterable) getSubjectAltNames(certificate, 2));
    }

    private final List<String> getSubjectAltNames(X509Certificate certificate, int type) {
        Object obj;
        try {
            Collection<List<?>> subjectAlternativeNames = certificate.getSubjectAlternativeNames();
            if (subjectAlternativeNames == null) {
                return ufe.b();
            }
            ArrayList arrayList = new ArrayList();
            for (List<?> list : subjectAlternativeNames) {
                if (list != null && list.size() >= 2 && uhy.e(list.get(0), Integer.valueOf(type)) && (obj = list.get(1)) != null) {
                    arrayList.add((String) obj);
                }
            }
            return arrayList;
        } catch (CertificateParsingException unused) {
            return ufe.b();
        }
    }
}
