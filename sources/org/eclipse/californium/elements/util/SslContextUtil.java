package org.eclipse.californium.elements.util;

import defpackage.vbi;
import defpackage.vbm;
import defpackage.vbs;
import defpackage.vbu;
import defpackage.vha;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.Principal;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509ExtendedKeyManager;
import javax.net.ssl.X509ExtendedTrustManager;
import javax.net.ssl.X509TrustManager;
import org.slf4j.Logger;

/* loaded from: classes7.dex */
public class SslContextUtil {

    /* renamed from: a, reason: collision with root package name */
    private static final KeyManager f15886a;
    private static final TrustManager b;
    public static final Logger e = vha.a((Class<?>) SslContextUtil.class);
    private static final Map<String, d> d = new ConcurrentHashMap();
    private static final Map<String, InputStreamFactory> c = new ConcurrentHashMap();

    public interface InputStreamFactory {
        InputStream create(String str) throws IOException;
    }

    public interface SimpleKeyStore {
        c load(InputStream inputStream) throws GeneralSecurityException, IOException;
    }

    static {
        TrustManager fVar;
        f15886a = new b();
        vbu.c();
        c();
        try {
            fVar = new e();
        } catch (NoClassDefFoundError unused) {
            fVar = new f();
        }
        b = fVar;
    }

    public static void c() {
        Map<String, d> map = d;
        map.clear();
        map.put(".jks", new d("JKS"));
        map.put(".bks", new d("BKS"));
        map.put(".p12", new d("PKCS12"));
        d dVar = new d(new SimpleKeyStore() { // from class: org.eclipse.californium.elements.util.SslContextUtil.4
            @Override // org.eclipse.californium.elements.util.SslContextUtil.SimpleKeyStore
            public c load(InputStream inputStream) throws GeneralSecurityException, IOException {
                return SslContextUtil.d(inputStream);
            }
        });
        map.put(".pem", dVar);
        map.put(".crt", dVar);
        map.put("*", new d(KeyStore.getDefaultType()));
        Map<String, InputStreamFactory> map2 = c;
        map2.clear();
        map2.put("classpath://", new a());
    }

    public static c d(InputStream inputStream) throws GeneralSecurityException, IOException {
        vbs vbsVar = new vbs(inputStream);
        try {
            vbm.c cVar = new vbm.c();
            ArrayList<Certificate> arrayList = new ArrayList();
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            while (true) {
                String a2 = vbsVar.a();
                if (a2 == null) {
                    if (cVar.c() != null || cVar.d() != null) {
                        List<? extends Certificate> certificates = certificateFactory.generateCertPath(arrayList).getCertificates();
                        return new c(cVar.c(), cVar.d(), (X509Certificate[]) certificates.toArray(new X509Certificate[certificates.size()]));
                    }
                    if (arrayList.isEmpty()) {
                        return new c(null);
                    }
                    ArrayList arrayList2 = new ArrayList();
                    for (Certificate certificate : arrayList) {
                        if (!arrayList2.contains(certificate)) {
                            arrayList2.add(certificate);
                        }
                    }
                    if (arrayList2.size() == arrayList.size()) {
                        try {
                            List<? extends Certificate> certificates2 = certificateFactory.generateCertPath(arrayList).getCertificates();
                            return new c(null, null, (X509Certificate[]) certificates2.toArray(new X509Certificate[certificates2.size()]));
                        } catch (GeneralSecurityException unused) {
                        }
                    }
                    return new c((Certificate[]) arrayList2.toArray(new Certificate[arrayList2.size()]));
                }
                byte[] e2 = vbsVar.e();
                if (e2 != null) {
                    if (a2.contains("CERTIFICATE")) {
                        arrayList.add(certificateFactory.generateCertificate(new ByteArrayInputStream(e2)));
                    } else if (a2.contains("PRIVATE KEY")) {
                        vbm.c a3 = vbm.a(e2);
                        if (a3 == null) {
                            throw new GeneralSecurityException("private key type not supported!");
                        }
                        cVar.b(a3);
                    } else if (a2.contains("PUBLIC KEY")) {
                        PublicKey j = vbm.j(e2);
                        if (j == null) {
                            throw new GeneralSecurityException("public key type not supported!");
                        }
                        cVar.a(j);
                    } else {
                        e.warn("{} not supported!", a2);
                    }
                }
            }
        } finally {
            vbsVar.b();
        }
    }

    public static X509Certificate[] d(Certificate[] certificateArr) {
        if (certificateArr == null || certificateArr.length == 0) {
            throw new IllegalArgumentException("certificates missing!");
        }
        X509Certificate[] x509CertificateArr = new X509Certificate[certificateArr.length];
        for (int i = 0; certificateArr.length > i; i++) {
            Certificate certificate = certificateArr[i];
            if (certificate == null) {
                throw new IllegalArgumentException("[" + i + "] is null!");
            }
            try {
                x509CertificateArr[i] = (X509Certificate) certificate;
            } catch (ClassCastException unused) {
                throw new IllegalArgumentException("[" + i + "] is not a x509 certificate! Instead it's a " + certificateArr[i].getClass().getName());
            }
        }
        return x509CertificateArr;
    }

    public static void d(X509Certificate[] x509CertificateArr) {
        HashSet hashSet = new HashSet();
        for (X509Certificate x509Certificate : x509CertificateArr) {
            if (!hashSet.add(x509Certificate)) {
                throw new IllegalArgumentException("Truststore contains certificates duplicates with subject: " + x509Certificate.getSubjectX500Principal());
            }
        }
    }

    static class a implements InputStreamFactory {
        private a() {
        }

        @Override // org.eclipse.californium.elements.util.SslContextUtil.InputStreamFactory
        public InputStream create(String str) throws IOException {
            InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(str.substring(12));
            if (resourceAsStream != null) {
                return resourceAsStream;
            }
            throw new IOException("'" + str + "' not found!");
        }
    }

    /* loaded from: classes10.dex */
    public static class c {

        /* renamed from: a, reason: collision with root package name */
        private final Certificate[] f15887a;
        private final X509Certificate[] b;
        private final PublicKey c;
        private final PrivateKey d;

        public c(PrivateKey privateKey, PublicKey publicKey, X509Certificate[] x509CertificateArr) {
            if (x509CertificateArr != null) {
                if (x509CertificateArr.length == 0) {
                    x509CertificateArr = null;
                } else if (publicKey != null) {
                    if (!publicKey.equals(x509CertificateArr[0].getPublicKey())) {
                        throw new IllegalArgumentException("public key doesn't match certificate!");
                    }
                } else {
                    publicKey = x509CertificateArr[0].getPublicKey();
                }
            }
            this.d = privateKey;
            this.b = x509CertificateArr;
            this.c = publicKey;
            this.f15887a = null;
        }

        public c(Certificate[] certificateArr) {
            this.d = null;
            this.c = null;
            this.b = null;
            this.f15887a = certificateArr;
        }
    }

    public static class d {
        public final String b;
        public final SimpleKeyStore e;

        public d(String str) {
            if (str == null) {
                throw new NullPointerException("key store type must not be null!");
            }
            if (str.isEmpty()) {
                throw new IllegalArgumentException("key store type must not be empty!");
            }
            this.b = str;
            this.e = null;
        }

        public d(SimpleKeyStore simpleKeyStore) {
            if (simpleKeyStore == null) {
                throw new NullPointerException("simple key store must not be null!");
            }
            this.b = null;
            this.e = simpleKeyStore;
        }
    }

    static class b extends X509ExtendedKeyManager {
        @Override // javax.net.ssl.X509KeyManager
        public String chooseClientAlias(String[] strArr, Principal[] principalArr, Socket socket) {
            return null;
        }

        @Override // javax.net.ssl.X509ExtendedKeyManager
        public String chooseEngineClientAlias(String[] strArr, Principal[] principalArr, SSLEngine sSLEngine) {
            return null;
        }

        @Override // javax.net.ssl.X509ExtendedKeyManager
        public String chooseEngineServerAlias(String str, Principal[] principalArr, SSLEngine sSLEngine) {
            return null;
        }

        @Override // javax.net.ssl.X509KeyManager
        public String chooseServerAlias(String str, Principal[] principalArr, Socket socket) {
            return null;
        }

        @Override // javax.net.ssl.X509KeyManager
        public X509Certificate[] getCertificateChain(String str) {
            return null;
        }

        @Override // javax.net.ssl.X509KeyManager
        public String[] getClientAliases(String str, Principal[] principalArr) {
            return null;
        }

        @Override // javax.net.ssl.X509KeyManager
        public PrivateKey getPrivateKey(String str) {
            return null;
        }

        @Override // javax.net.ssl.X509KeyManager
        public String[] getServerAliases(String str, Principal[] principalArr) {
            return null;
        }

        private b() {
        }
    }

    static class f implements X509TrustManager {

        /* renamed from: a, reason: collision with root package name */
        private static final X509Certificate[] f15888a = new X509Certificate[0];

        private f() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void a(X509Certificate[] x509CertificateArr, boolean z) throws CertificateException {
            if (x509CertificateArr == null || x509CertificateArr.length <= 0) {
                return;
            }
            SslContextUtil.e.debug("check certificate {} for {}", x509CertificateArr[0].getSubjectX500Principal(), z ? "client" : "server");
            if (!vbi.b(x509CertificateArr[0], z)) {
                SslContextUtil.e.debug("check certificate {} for {} failed on key-usage!", x509CertificateArr[0].getSubjectX500Principal(), z ? "client" : "server");
                throw new CertificateException("Key usage not proper for ".concat(z ? "client" : "server"));
            }
            SslContextUtil.e.trace("check certificate {} for {} succeeded on key-usage!", x509CertificateArr[0].getSubjectX500Principal(), z ? "client" : "server");
            try {
                vbi.b(true, vbi.d(Arrays.asList(x509CertificateArr), null), f15888a);
                Logger logger = SslContextUtil.e;
                Object[] objArr = new Object[3];
                objArr[0] = x509CertificateArr[0].getSubjectX500Principal();
                objArr[1] = Integer.valueOf(x509CertificateArr.length);
                objArr[2] = z ? "client" : "server";
                logger.trace("check certificate {} [chain.length={}] for {} validated!", objArr);
            } catch (GeneralSecurityException e) {
                Logger logger2 = SslContextUtil.e;
                Object[] objArr2 = new Object[3];
                objArr2[0] = x509CertificateArr[0].getSubjectX500Principal();
                objArr2[1] = z ? "client" : "server";
                objArr2[2] = e.getMessage();
                logger2.debug("check certificate {} for {} failed on {}!", objArr2);
                if (e instanceof CertificateException) {
                    throw ((CertificateException) e);
                }
                throw new CertificateException(e);
            }
        }

        @Override // javax.net.ssl.X509TrustManager
        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
            a(x509CertificateArr, true);
        }

        @Override // javax.net.ssl.X509TrustManager
        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
            a(x509CertificateArr, false);
        }

        @Override // javax.net.ssl.X509TrustManager
        public X509Certificate[] getAcceptedIssuers() {
            return f15888a;
        }
    }

    static class e extends X509ExtendedTrustManager {
        private e() {
        }

        @Override // javax.net.ssl.X509TrustManager
        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
            f.a(x509CertificateArr, true);
        }

        @Override // javax.net.ssl.X509TrustManager
        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
            f.a(x509CertificateArr, false);
        }

        @Override // javax.net.ssl.X509TrustManager
        public X509Certificate[] getAcceptedIssuers() {
            return f.f15888a;
        }

        @Override // javax.net.ssl.X509ExtendedTrustManager
        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str, Socket socket) throws CertificateException {
            f.a(x509CertificateArr, true);
        }

        @Override // javax.net.ssl.X509ExtendedTrustManager
        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str, SSLEngine sSLEngine) throws CertificateException {
            f.a(x509CertificateArr, true);
        }

        @Override // javax.net.ssl.X509ExtendedTrustManager
        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str, Socket socket) throws CertificateException {
            f.a(x509CertificateArr, false);
        }

        @Override // javax.net.ssl.X509ExtendedTrustManager
        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str, SSLEngine sSLEngine) throws CertificateException {
            f.a(x509CertificateArr, false);
        }
    }
}
