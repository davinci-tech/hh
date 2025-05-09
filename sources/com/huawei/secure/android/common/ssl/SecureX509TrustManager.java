package com.huawei.secure.android.common.ssl;

import android.content.Context;
import com.huawei.secure.android.common.ssl.util.ContextUtil;
import com.huawei.secure.android.common.ssl.util.d;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

/* loaded from: classes6.dex */
public class SecureX509TrustManager implements X509TrustManager {
    private static final String c = "SX509TM";
    public static final String d = "hmsrootcas.bks";
    private static final String e = "";
    private static final String f = "X509";
    private static final String g = "bks";
    private static final String h = "AndroidCAStore";
    private static final List<String> i = Arrays.asList("7116473896668257942", "5339133492510690512");

    /* renamed from: a, reason: collision with root package name */
    protected List<X509TrustManager> f8622a;
    private X509Certificate[] b;

    public SecureX509TrustManager(Context context) throws IOException, NoSuchAlgorithmException, CertificateException, KeyStoreException, IllegalArgumentException {
        this(context, false);
    }

    private void a() {
        d.c(c, "loadSystemCA");
        long currentTimeMillis = System.currentTimeMillis();
        try {
            KeyStore keyStore = KeyStore.getInstance(h);
            keyStore.load(null, null);
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(f);
            trustManagerFactory.init(keyStore);
            TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
            for (TrustManager trustManager : trustManagers) {
                if (trustManager instanceof X509TrustManager) {
                    this.f8622a.add((X509TrustManager) trustManager);
                }
            }
        } catch (IOException | NegativeArraySizeException | OutOfMemoryError | KeyStoreException | NoSuchAlgorithmException | CertificateException e2) {
            d.b(c, "loadSystemCA: exception : " + e2.getMessage());
        }
        d.a(c, "loadSystemCA: cost : " + (System.currentTimeMillis() - currentTimeMillis) + " ms");
    }

    @Override // javax.net.ssl.X509TrustManager
    public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        d.c(c, "checkClientTrusted: ");
        Iterator<X509TrustManager> it = this.f8622a.iterator();
        while (it.hasNext()) {
            try {
                it.next().checkServerTrusted(x509CertificateArr, str);
                return;
            } catch (CertificateException e2) {
                d.b(c, "checkServerTrusted CertificateException" + e2.getMessage());
            }
        }
        throw new CertificateException("checkServerTrusted CertificateException");
    }

    @Override // javax.net.ssl.X509TrustManager
    public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        setChain(x509CertificateArr);
        d.c(c, "checkServerTrusted begin,size=" + x509CertificateArr.length + ",authType=" + str);
        long currentTimeMillis = System.currentTimeMillis();
        int size = this.f8622a.size();
        for (int i2 = 0; i2 < size; i2++) {
            try {
                d.c(c, "check server i=" + i2);
                X509TrustManager x509TrustManager = this.f8622a.get(i2);
                X509Certificate[] acceptedIssuers = x509TrustManager.getAcceptedIssuers();
                if (acceptedIssuers != null) {
                    d.c(c, "client root ca size=" + acceptedIssuers.length);
                }
                x509TrustManager.checkServerTrusted(x509CertificateArr, str);
                d.c(c, "checkServerTrusted end, " + x509CertificateArr[x509CertificateArr.length - 1].getIssuerDN());
                return;
            } catch (CertificateException e2) {
                d.b(c, "checkServerTrusted error :" + e2.getMessage() + " , time : " + i2);
                if (i2 == size - 1) {
                    if (x509CertificateArr != null && x509CertificateArr.length > 0) {
                        d.b(c, "root ca issuer : " + x509CertificateArr[x509CertificateArr.length - 1].getIssuerDN());
                    }
                    throw e2;
                }
            }
        }
        d.a(c, "checkServerTrusted: cost : " + (System.currentTimeMillis() - currentTimeMillis) + " ms");
    }

    @Override // javax.net.ssl.X509TrustManager
    public X509Certificate[] getAcceptedIssuers() {
        try {
            ArrayList arrayList = new ArrayList();
            Iterator<X509TrustManager> it = this.f8622a.iterator();
            while (it.hasNext()) {
                arrayList.addAll(Arrays.asList(it.next().getAcceptedIssuers()));
            }
            return (X509Certificate[]) arrayList.toArray(new X509Certificate[arrayList.size()]);
        } catch (Exception e2) {
            d.b(c, "getAcceptedIssuers exception : " + e2.getMessage());
            return new X509Certificate[0];
        }
    }

    public X509Certificate[] getChain() {
        return this.b;
    }

    public List<X509TrustManager> getX509TrustManagers() {
        return this.f8622a;
    }

    public void setChain(X509Certificate[] x509CertificateArr) {
        this.b = x509CertificateArr;
    }

    public void setX509TrustManagers(List<X509TrustManager> list) {
        this.f8622a = list;
    }

    public SecureX509TrustManager(Context context, boolean z) throws IOException, NoSuchAlgorithmException, CertificateException, KeyStoreException, IllegalArgumentException {
        this.f8622a = new ArrayList();
        if (context == null) {
            throw new IllegalArgumentException("context is null");
        }
        ContextUtil.setContext(context);
        a(context);
        if (z) {
            a();
        }
        if (this.f8622a.isEmpty()) {
            throw new CertificateException("X509TrustManager is empty");
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:3:0x0039, code lost:
    
        if (r0 == null) goto L17;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void a(android.content.Context r7) throws java.security.cert.CertificateException, java.security.NoSuchAlgorithmException, java.security.KeyStoreException, java.io.IOException {
        /*
            r6 = this;
            java.lang.String r0 = "loadBksCA"
            java.lang.String r1 = "SX509TM"
            com.huawei.secure.android.common.ssl.util.d.c(r1, r0)
            long r2 = java.lang.System.currentTimeMillis()
            java.io.InputStream r0 = com.huawei.secure.android.common.ssl.util.BksUtil.getFilesBksIS(r7)
            if (r0 == 0) goto L39
            java.lang.String r4 = "get bks not from assets"
            com.huawei.secure.android.common.ssl.util.d.c(r1, r4)     // Catch: java.io.IOException -> L1a java.security.cert.CertificateException -> L1c java.security.KeyStoreException -> L1e java.security.NoSuchAlgorithmException -> L20 java.lang.OutOfMemoryError -> L22
            r6.a(r0)     // Catch: java.io.IOException -> L1a java.security.cert.CertificateException -> L1c java.security.KeyStoreException -> L1e java.security.NoSuchAlgorithmException -> L20 java.lang.OutOfMemoryError -> L22
            goto L39
        L1a:
            r0 = move-exception
            goto L23
        L1c:
            r0 = move-exception
            goto L23
        L1e:
            r0 = move-exception
            goto L23
        L20:
            r0 = move-exception
            goto L23
        L22:
            r0 = move-exception
        L23:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "loadBksCA: exception : "
            r4.<init>(r5)
            java.lang.String r0 = r0.getMessage()
            r4.append(r0)
            java.lang.String r0 = r4.toString()
            com.huawei.secure.android.common.ssl.util.d.b(r1, r0)
            goto L3b
        L39:
            if (r0 != 0) goto L4d
        L3b:
            java.lang.String r0 = " get bks from assets "
            com.huawei.secure.android.common.ssl.util.d.c(r1, r0)
            android.content.res.AssetManager r7 = r7.getAssets()
            java.lang.String r0 = "hmsrootcas.bks"
            java.io.InputStream r7 = r7.open(r0)
            r6.a(r7)
        L4d:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r0 = "loadBksCA: cost : "
            r7.<init>(r0)
            long r4 = java.lang.System.currentTimeMillis()
            long r4 = r4 - r2
            r7.append(r4)
            java.lang.String r0 = " ms"
            r7.append(r0)
            java.lang.String r7 = r7.toString()
            com.huawei.secure.android.common.ssl.util.d.a(r1, r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.secure.android.common.ssl.SecureX509TrustManager.a(android.content.Context):void");
    }

    private void a(InputStream inputStream) throws NoSuchAlgorithmException, KeyStoreException, CertificateException, IOException {
        try {
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(f);
            KeyStore keyStore = KeyStore.getInstance("bks");
            keyStore.load(inputStream, "".toCharArray());
            a(keyStore);
            trustManagerFactory.init(keyStore);
            for (TrustManager trustManager : trustManagerFactory.getTrustManagers()) {
                if (trustManager instanceof X509TrustManager) {
                    this.f8622a.add((X509TrustManager) trustManager);
                }
            }
        } finally {
            com.huawei.secure.android.common.ssl.util.c.a(inputStream);
        }
    }

    public SecureX509TrustManager(InputStream inputStream, String str) throws IllegalArgumentException {
        this.f8622a = new ArrayList();
        a(inputStream, str);
    }

    private void a(InputStream inputStream, String str) {
        if (inputStream != null && str != null) {
            long currentTimeMillis = System.currentTimeMillis();
            try {
                try {
                    TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(f);
                    KeyStore keyStore = KeyStore.getInstance("bks");
                    keyStore.load(inputStream, str.toCharArray());
                    a(keyStore);
                    trustManagerFactory.init(keyStore);
                    TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
                    for (TrustManager trustManager : trustManagers) {
                        if (trustManager instanceof X509TrustManager) {
                            this.f8622a.add((X509TrustManager) trustManager);
                        }
                    }
                    com.huawei.secure.android.common.ssl.util.c.a(inputStream);
                } finally {
                    com.huawei.secure.android.common.ssl.util.c.a(inputStream);
                }
            } catch (IOException | NegativeArraySizeException | OutOfMemoryError | KeyStoreException | NoSuchAlgorithmException | CertificateException e2) {
                d.b(c, "loadInputStream: exception : " + e2.getMessage());
            }
            d.a(c, "loadInputStream: cost : " + (System.currentTimeMillis() - currentTimeMillis) + " ms");
            return;
        }
        throw new IllegalArgumentException("inputstream or trustPwd is null");
    }

    private static void a(KeyStore keyStore) throws KeyStoreException {
        Enumeration<String> aliases = keyStore.aliases();
        while (aliases.hasMoreElements()) {
            String nextElement = aliases.nextElement();
            Certificate certificate = keyStore.getCertificate(nextElement);
            if (certificate instanceof X509Certificate) {
                if (i.contains(((X509Certificate) certificate).getSerialNumber().toString(10))) {
                    d.c(c, "filter serial number.");
                    keyStore.deleteEntry(nextElement);
                }
            }
        }
    }

    public SecureX509TrustManager(String str) throws IllegalArgumentException, FileNotFoundException {
        this(str, false);
    }

    public SecureX509TrustManager(String str, boolean z) throws IllegalArgumentException, FileNotFoundException {
        FileInputStream fileInputStream;
        this.f8622a = new ArrayList();
        try {
            fileInputStream = new FileInputStream(str);
            try {
                a(fileInputStream, "");
                com.huawei.secure.android.common.ssl.util.c.a((InputStream) fileInputStream);
                if (z) {
                    a();
                }
            } catch (Throwable th) {
                th = th;
                com.huawei.secure.android.common.ssl.util.c.a((InputStream) fileInputStream);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            fileInputStream = null;
        }
    }

    public SecureX509TrustManager(InputStream inputStream, String str, boolean z) throws IllegalArgumentException {
        this.f8622a = new ArrayList();
        a(inputStream, str);
        if (z) {
            a();
        }
    }
}
