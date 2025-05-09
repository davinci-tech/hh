package com.amap.api.col.p0003sl;

import com.amap.api.col.p0003sl.jq;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;
import java.util.Iterator;
import java.util.List;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/* loaded from: classes2.dex */
public final class ke {
    public static void a(String str, byte[] bArr, kd kdVar) throws IOException, CertificateException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException, InvalidKeyException, InvalidKeySpecException {
        jq jqVar;
        OutputStream outputStream = null;
        try {
            if (a(kdVar.f1251a, str)) {
                return;
            }
            File file = new File(kdVar.f1251a);
            if (!file.exists()) {
                file.mkdirs();
            }
            jqVar = jq.a(file, kdVar.b);
            try {
                jqVar.a(kdVar.d);
                byte[] b = kdVar.e.b(bArr);
                jq.a b2 = jqVar.b(str);
                OutputStream a2 = b2.a();
                try {
                    a2.write(b);
                    b2.b();
                    jqVar.c();
                    if (a2 != null) {
                        try {
                            a2.close();
                        } catch (Throwable th) {
                            th.printStackTrace();
                        }
                    }
                    if (jqVar != null) {
                        try {
                            jqVar.close();
                        } catch (Throwable th2) {
                            th2.printStackTrace();
                        }
                    }
                } catch (Throwable th3) {
                    th = th3;
                    outputStream = a2;
                    if (outputStream != null) {
                        try {
                            outputStream.close();
                        } catch (Throwable th4) {
                            th4.printStackTrace();
                        }
                    }
                    if (jqVar != null) {
                        try {
                            jqVar.close();
                            throw th;
                        } catch (Throwable th5) {
                            th5.printStackTrace();
                            throw th;
                        }
                    }
                    throw th;
                }
            } catch (Throwable th6) {
                th = th6;
            }
        } catch (Throwable th7) {
            th = th7;
            jqVar = null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:36:0x0081, code lost:
    
        r3.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0085, code lost:
    
        r10 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0086, code lost:
    
        r10.printStackTrace();
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x009d, code lost:
    
        if (r2 == null) goto L48;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int a(com.amap.api.col.p0003sl.kd r10) {
        /*
            java.lang.String r0 = "code"
            r1 = -1
            r2 = 0
            com.amap.api.col.3sl.kx r3 = r10.f     // Catch: java.lang.Throwable -> L95
            boolean r3 = r3.d()     // Catch: java.lang.Throwable -> L95
            if (r3 == 0) goto L8d
            com.amap.api.col.3sl.kx r3 = r10.f     // Catch: java.lang.Throwable -> L95
            r4 = 1
            r3.a_(r4)     // Catch: java.lang.Throwable -> L95
            java.io.File r3 = new java.io.File     // Catch: java.lang.Throwable -> L95
            java.lang.String r5 = r10.f1251a     // Catch: java.lang.Throwable -> L95
            r3.<init>(r5)     // Catch: java.lang.Throwable -> L95
            long r5 = r10.b     // Catch: java.lang.Throwable -> L95
            com.amap.api.col.3sl.jq r3 = com.amap.api.col.p0003sl.jq.a(r3, r5)     // Catch: java.lang.Throwable -> L95
            java.util.ArrayList r5 = new java.util.ArrayList     // Catch: java.lang.Throwable -> L8a
            r5.<init>()     // Catch: java.lang.Throwable -> L8a
            byte[] r6 = a(r3, r10, r5)     // Catch: java.lang.Throwable -> L8a
            if (r6 == 0) goto L7f
            int r7 = r6.length     // Catch: java.lang.Throwable -> L8a
            if (r7 != 0) goto L2e
            goto L7f
        L2e:
            com.amap.api.col.3sl.iu r7 = new com.amap.api.col.3sl.iu     // Catch: java.lang.Throwable -> L8a
            java.lang.String r8 = r10.c     // Catch: java.lang.Throwable -> L8a
            r7.<init>(r6, r8)     // Catch: java.lang.Throwable -> L8a
            com.amap.api.col.p0003sl.jt.a()     // Catch: java.lang.Throwable -> L8a
            com.amap.api.col.3sl.kb r7 = com.amap.api.col.p0003sl.jt.a(r7)     // Catch: java.lang.Throwable -> L8a
            byte[] r7 = r7.f1250a     // Catch: java.lang.Throwable -> L8a
            org.json.JSONObject r8 = new org.json.JSONObject     // Catch: java.lang.Throwable -> L8a
            java.lang.String r9 = new java.lang.String     // Catch: java.lang.Throwable -> L8a
            r9.<init>(r7)     // Catch: java.lang.Throwable -> L8a
            r8.<init>(r9)     // Catch: java.lang.Throwable -> L8a
            boolean r7 = r8.has(r0)     // Catch: java.lang.Throwable -> L8a
            if (r7 == 0) goto L7d
            int r0 = r8.getInt(r0)     // Catch: java.lang.Throwable -> L8a
            if (r0 != r4) goto L7d
            com.amap.api.col.3sl.kx r0 = r10.f     // Catch: java.lang.Throwable -> L8a
            if (r0 == 0) goto L60
            if (r6 == 0) goto L60
            com.amap.api.col.3sl.kx r0 = r10.f     // Catch: java.lang.Throwable -> L8a
            int r4 = r6.length     // Catch: java.lang.Throwable -> L8a
            r0.a_(r4)     // Catch: java.lang.Throwable -> L8a
        L60:
            com.amap.api.col.3sl.kx r10 = r10.f     // Catch: java.lang.Throwable -> L8a
            int r10 = r10.a()     // Catch: java.lang.Throwable -> L8a
            r0 = 2147483647(0x7fffffff, float:NaN)
            if (r10 >= r0) goto L6f
            a(r3, r5)     // Catch: java.lang.Throwable -> L8a
            goto L7b
        L6f:
            r3.d()     // Catch: java.lang.Throwable -> L73
            goto L7b
        L73:
            r10 = move-exception
            java.lang.String r0 = "ofm"
            java.lang.String r4 = "dlo"
            com.amap.api.col.p0003sl.iv.c(r10, r0, r4)     // Catch: java.lang.Throwable -> L8a
        L7b:
            int r10 = r6.length     // Catch: java.lang.Throwable -> L95
            return r10
        L7d:
            r2 = r3
            goto L8d
        L7f:
            if (r3 == 0) goto L89
            r3.close()     // Catch: java.lang.Throwable -> L85
            goto L89
        L85:
            r10 = move-exception
            r10.printStackTrace()
        L89:
            return r1
        L8a:
            r10 = move-exception
            r2 = r3
            goto L96
        L8d:
            if (r2 == 0) goto La3
        L8f:
            r2.close()     // Catch: java.lang.Throwable -> L93
            goto La3
        L93:
            r10 = move-exception
            goto La0
        L95:
            r10 = move-exception
        L96:
            java.lang.String r0 = "leg"
            java.lang.String r3 = "uts"
            com.amap.api.col.p0003sl.iv.c(r10, r0, r3)     // Catch: java.lang.Throwable -> La4
            if (r2 == 0) goto La3
            goto L8f
        La0:
            r10.printStackTrace()
        La3:
            return r1
        La4:
            r10 = move-exception
            if (r2 == 0) goto Laf
            r2.close()     // Catch: java.lang.Throwable -> Lab
            goto Laf
        Lab:
            r0 = move-exception
            r0.printStackTrace()
        Laf:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.col.p0003sl.ke.a(com.amap.api.col.3sl.kd):int");
    }

    private static byte[] a(jq jqVar, kd kdVar, List<String> list) {
        try {
            File b = jqVar.b();
            if (b != null && b.exists()) {
                int i = 0;
                for (String str : b.list()) {
                    if (str.contains(".0")) {
                        String str2 = str.split("\\.")[0];
                        byte[] a2 = kk.a(jqVar, str2);
                        i += a2.length;
                        list.add(str2);
                        if (i > kdVar.f.a()) {
                            break;
                        }
                        kdVar.g.b(a2);
                    }
                }
                if (i <= 0) {
                    return null;
                }
                return kdVar.g.a();
            }
        } catch (Throwable th) {
            iv.c(th, "leg", "gCo");
        }
        return new byte[0];
    }

    private static void a(jq jqVar, List<String> list) {
        if (jqVar != null) {
            try {
                Iterator<String> it = list.iterator();
                while (it.hasNext()) {
                    jqVar.c(it.next());
                }
                jqVar.close();
            } catch (Throwable th) {
                iv.c(th, "ofm", "dlo");
            }
        }
    }

    private static boolean a(String str, String str2) {
        try {
            return new File(str, str2 + ".0").exists();
        } catch (Throwable th) {
            iv.c(th, "leg", "fet");
            return false;
        }
    }
}
