package defpackage;

import android.content.Context;
import com.huawei.agconnect.apms.instrument.URLConnectionInstrumentation;
import com.huawei.appgallery.marketinstallerservice.b.b.e.a;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.secure.android.common.ssl.SecureSSLSocketFactory;
import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import javax.net.ssl.HttpsURLConnection;

/* loaded from: classes3.dex */
public final class agx {

    /* renamed from: a, reason: collision with root package name */
    private URLConnection f199a;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v2, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v8, types: [java.io.Closeable, java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r1v9 */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r5v0, types: [agx] */
    public byte[] c(Context context, String str, String str2, String str3) {
        Throwable th;
        OutputStream outputStream;
        ags agsVar = new ags();
        ?? r1 = 0;
        r1 = 0;
        r1 = 0;
        r1 = 0;
        try {
            try {
                URLConnection openConnection = URLConnectionInstrumentation.openConnection(new URL(str).openConnection());
                this.f199a = openConnection;
                if (openConnection instanceof HttpURLConnection) {
                    HttpURLConnection httpURLConnection = (HttpURLConnection) openConnection;
                    httpURLConnection.setInstanceFollowRedirects(true);
                    httpURLConnection.setRequestMethod("POST");
                }
                URLConnection uRLConnection = this.f199a;
                if (uRLConnection instanceof HttpsURLConnection) {
                    HttpsURLConnection httpsURLConnection = (HttpsURLConnection) uRLConnection;
                    httpsURLConnection.setSSLSocketFactory(SecureSSLSocketFactory.getInstance(context));
                    httpsURLConnection.setHostnameVerifier(SecureSSLSocketFactory.STRICT_HOSTNAME_VERIFIER);
                }
                this.f199a.setConnectTimeout(5000);
                this.f199a.setDoInput(true);
                this.f199a.setDoOutput(true);
                this.f199a.setReadTimeout(10000);
                this.f199a.setDefaultUseCaches(false);
                this.f199a.setUseCaches(false);
                this.f199a.setRequestProperty("Content-Type", "application/x-gzip");
                this.f199a.setRequestProperty("Content-Encoding", Constants.GZIP);
                this.f199a.setRequestProperty("User-Agent", agz.b(context));
                outputStream = this.f199a.getOutputStream();
            } catch (Throwable th2) {
                th = th2;
                r1 = context;
                outputStream = null;
            }
            try {
                outputStream.write(a.a(str2.getBytes(str3)));
                outputStream.flush();
                if (this.f199a instanceof HttpURLConnection) {
                    agr.c("HTTPUtil", "ResponseCode:" + ((HttpURLConnection) this.f199a).getResponseCode());
                }
                r1 = this.f199a.getInputStream();
                b(this.f199a, r1, agsVar);
                if (r1 != 0) {
                    d(r1);
                }
                if (outputStream != null) {
                    d(outputStream);
                }
                URLConnection uRLConnection2 = this.f199a;
                if (uRLConnection2 instanceof HttpURLConnection) {
                    ((HttpURLConnection) uRLConnection2).disconnect();
                }
                return agsVar.a();
            } catch (IOException e) {
                throw e;
            } catch (Exception e2) {
                throw e2;
            } catch (Throwable th3) {
                th = th3;
                if (r1 != 0) {
                    d(r1);
                }
                if (outputStream != null) {
                    d(outputStream);
                }
                URLConnection uRLConnection3 = this.f199a;
                if (uRLConnection3 instanceof HttpURLConnection) {
                    ((HttpURLConnection) uRLConnection3).disconnect();
                }
                throw th;
            }
        } catch (IOException e3) {
            throw e3;
        } catch (Exception e4) {
            throw e4;
        } catch (Throwable th4) {
            th = th4;
            outputStream = null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0048 A[Catch: all -> 0x0064, IOException -> 0x0066, TryCatch #1 {IOException -> 0x0066, blocks: (B:3:0x0005, B:5:0x0022, B:8:0x002c, B:9:0x003c, B:10:0x0041, B:12:0x0048, B:15:0x0053, B:33:0x0032), top: B:2:0x0005, outer: #0 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void b(java.net.URLConnection r6, java.io.InputStream r7, defpackage.ags r8) {
        /*
            r5 = this;
            java.lang.String r0 = "close error"
            java.lang.String r1 = "HTTPUtil"
            r2 = 0
            java.lang.String r6 = r6.getContentEncoding()     // Catch: java.lang.Throwable -> L64 java.io.IOException -> L66
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L64 java.io.IOException -> L66
            java.lang.String r4 = "parseHttpResponse contentType ="
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L64 java.io.IOException -> L66
            r3.append(r6)     // Catch: java.lang.Throwable -> L64 java.io.IOException -> L66
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Throwable -> L64 java.io.IOException -> L66
            defpackage.agr.a(r1, r3)     // Catch: java.lang.Throwable -> L64 java.io.IOException -> L66
            java.lang.String r3 = "gzip"
            boolean r3 = r3.equals(r6)     // Catch: java.lang.Throwable -> L64 java.io.IOException -> L66
            if (r3 != 0) goto L32
            java.lang.String r3 = "z"
            boolean r6 = r3.equals(r6)     // Catch: java.lang.Throwable -> L64 java.io.IOException -> L66
            if (r6 == 0) goto L2c
            goto L32
        L2c:
            java.io.BufferedInputStream r6 = new java.io.BufferedInputStream     // Catch: java.lang.Throwable -> L64 java.io.IOException -> L66
            r6.<init>(r7)     // Catch: java.lang.Throwable -> L64 java.io.IOException -> L66
            goto L3c
        L32:
            java.io.BufferedInputStream r6 = new java.io.BufferedInputStream     // Catch: java.lang.Throwable -> L64 java.io.IOException -> L66
            java.util.zip.GZIPInputStream r3 = new java.util.zip.GZIPInputStream     // Catch: java.lang.Throwable -> L64 java.io.IOException -> L66
            r3.<init>(r7)     // Catch: java.lang.Throwable -> L64 java.io.IOException -> L66
            r6.<init>(r3)     // Catch: java.lang.Throwable -> L64 java.io.IOException -> L66
        L3c:
            r2 = r6
            r6 = 65536(0x10000, float:9.1835E-41)
            byte[] r6 = new byte[r6]     // Catch: java.lang.Throwable -> L64 java.io.IOException -> L66
        L41:
            int r7 = r2.read(r6)     // Catch: java.lang.Throwable -> L64 java.io.IOException -> L66
            r3 = -1
            if (r7 == r3) goto L6e
            r8.c(r6, r7)     // Catch: java.lang.Throwable -> L64 java.io.IOException -> L66
            int r7 = r8.e()     // Catch: java.lang.Throwable -> L64 java.io.IOException -> L66
            r3 = 4194304(0x400000, float:5.877472E-39)
            if (r7 <= r3) goto L41
            r8.d()     // Catch: java.lang.Throwable -> L64 java.io.IOException -> L66
            java.lang.String r6 = "File being unzipped is too big."
            defpackage.agr.e(r1, r6)     // Catch: java.lang.Throwable -> L64 java.io.IOException -> L66
            r2.close()     // Catch: java.io.IOException -> L5f
            goto L63
        L5f:
            r6 = move-exception
            defpackage.agr.a(r1, r0, r6)
        L63:
            return
        L64:
            r6 = move-exception
            goto L77
        L66:
            r6 = move-exception
            java.lang.String r7 = "parseHttpResponse error!"
            defpackage.agr.a(r1, r7, r6)     // Catch: java.lang.Throwable -> L64
            if (r2 == 0) goto L76
        L6e:
            r2.close()     // Catch: java.io.IOException -> L72
            goto L76
        L72:
            r6 = move-exception
            defpackage.agr.a(r1, r0, r6)
        L76:
            return
        L77:
            if (r2 == 0) goto L81
            r2.close()     // Catch: java.io.IOException -> L7d
            goto L81
        L7d:
            r7 = move-exception
            defpackage.agr.a(r1, r0, r7)
        L81:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.agx.b(java.net.URLConnection, java.io.InputStream, ags):void");
    }

    private void d(Closeable closeable) {
        StringBuilder sb;
        String exc;
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                sb = new StringBuilder("close IO IOException:");
                exc = e.toString();
                sb.append(exc);
                agr.e("IoUtils", sb.toString());
            } catch (Exception e2) {
                sb = new StringBuilder("close IO execption:");
                exc = e2.toString();
                sb.append(exc);
                agr.e("IoUtils", sb.toString());
            }
        }
    }
}
