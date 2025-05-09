package com.amap.api.col.p0003sl;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import com.amap.api.col.p0003sl.hz;
import com.huawei.operation.utils.CloudParamKeys;
import com.huawei.pluginachievement.manager.model.MedalConstants;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.zip.GZIPOutputStream;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public final class ia {

    /* renamed from: a, reason: collision with root package name */
    static String f1169a;
    private static final String[] b = {"arm64-v8a", "x86_64"};
    private static final String[] c = {"arm", "x86"};

    public static Method a(Class cls, String str, Class<?>... clsArr) {
        try {
            return cls.getDeclaredMethod(c(str), clsArr);
        } catch (Throwable unused) {
            return null;
        }
    }

    static {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 80; i++) {
            sb.append("=");
        }
        f1169a = sb.toString();
    }

    /* JADX WARN: Removed duplicated region for block: B:11:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0033 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x00a1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String a(android.content.Context r7) {
        /*
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 1
            java.lang.String r2 = "ut"
            r3 = 28
            if (r0 >= r3) goto L2d
            android.content.pm.ApplicationInfo r0 = r7.getApplicationInfo()     // Catch: java.lang.Throwable -> L27
            java.lang.Class<android.content.pm.ApplicationInfo> r4 = android.content.pm.ApplicationInfo.class
            java.lang.String r4 = r4.getName()     // Catch: java.lang.Throwable -> L27
            java.lang.Class r4 = java.lang.Class.forName(r4)     // Catch: java.lang.Throwable -> L27
            java.lang.String r5 = "primaryCpuAbi"
            java.lang.reflect.Field r4 = r4.getDeclaredField(r5)     // Catch: java.lang.Throwable -> L27
            r4.setAccessible(r1)     // Catch: java.lang.Throwable -> L27
            java.lang.Object r0 = r4.get(r0)     // Catch: java.lang.Throwable -> L27
            java.lang.String r0 = (java.lang.String) r0     // Catch: java.lang.Throwable -> L27
            goto L2f
        L27:
            r0 = move-exception
            java.lang.String r4 = "gct"
            com.amap.api.col.p0003sl.is.a(r0, r2, r4)
        L2d:
            java.lang.String r0 = ""
        L2f:
            int r4 = android.os.Build.VERSION.SDK_INT
            if (r4 < r3) goto L9b
            java.lang.Class<android.os.Build> r3 = android.os.Build.class
            java.lang.String r4 = "SUPPORTED_ABIS"
            java.lang.reflect.Field r3 = r3.getDeclaredField(r4)     // Catch: java.lang.Throwable -> L95
            r4 = 0
            java.lang.Object r3 = r3.get(r4)     // Catch: java.lang.Throwable -> L95
            java.lang.String[] r3 = (java.lang.String[]) r3     // Catch: java.lang.Throwable -> L95
            r5 = 0
            if (r3 == 0) goto L4a
            int r6 = r3.length     // Catch: java.lang.Throwable -> L95
            if (r6 <= 0) goto L4a
            r0 = r3[r5]     // Catch: java.lang.Throwable -> L95
        L4a:
            boolean r3 = android.text.TextUtils.isEmpty(r0)     // Catch: java.lang.Throwable -> L95
            if (r3 != 0) goto L9b
            java.lang.String[] r3 = com.amap.api.col.p0003sl.ia.b     // Catch: java.lang.Throwable -> L95
            java.util.List r3 = java.util.Arrays.asList(r3)     // Catch: java.lang.Throwable -> L95
            boolean r3 = r3.contains(r0)     // Catch: java.lang.Throwable -> L95
            if (r3 == 0) goto L9b
            android.content.pm.ApplicationInfo r7 = r7.getApplicationInfo()     // Catch: java.lang.Throwable -> L95
            java.lang.String r7 = r7.nativeLibraryDir     // Catch: java.lang.Throwable -> L95
            boolean r3 = android.text.TextUtils.isEmpty(r7)     // Catch: java.lang.Throwable -> L95
            if (r3 != 0) goto L9b
            java.lang.String r3 = java.io.File.separator     // Catch: java.lang.Throwable -> L95
            int r3 = r7.lastIndexOf(r3)     // Catch: java.lang.Throwable -> L95
            int r3 = r3 + r1
            java.lang.String r7 = r7.substring(r3)     // Catch: java.lang.Throwable -> L95
            java.lang.String[] r1 = com.amap.api.col.p0003sl.ia.c     // Catch: java.lang.Throwable -> L95
            java.util.List r1 = java.util.Arrays.asList(r1)     // Catch: java.lang.Throwable -> L95
            boolean r7 = r1.contains(r7)     // Catch: java.lang.Throwable -> L95
            if (r7 == 0) goto L9b
            java.lang.Class<android.os.Build> r7 = android.os.Build.class
            java.lang.String r1 = "SUPPORTED_32_BIT_ABIS"
            java.lang.reflect.Field r7 = r7.getDeclaredField(r1)     // Catch: java.lang.Throwable -> L95
            java.lang.Object r7 = r7.get(r4)     // Catch: java.lang.Throwable -> L95
            java.lang.String[] r7 = (java.lang.String[]) r7     // Catch: java.lang.Throwable -> L95
            if (r7 == 0) goto L9b
            int r1 = r7.length     // Catch: java.lang.Throwable -> L95
            if (r1 <= 0) goto L9b
            r0 = r7[r5]     // Catch: java.lang.Throwable -> L95
            goto L9b
        L95:
            r7 = move-exception
            java.lang.String r1 = "gct_p"
            com.amap.api.col.p0003sl.is.a(r7, r2, r1)
        L9b:
            boolean r7 = android.text.TextUtils.isEmpty(r0)
            if (r7 == 0) goto La3
            java.lang.String r0 = android.os.Build.CPU_ABI
        La3:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.col.p0003sl.ia.a(android.content.Context):java.lang.String");
    }

    public static boolean a(Context context, String str) {
        if (context == null || context.checkCallingOrSelfPermission(str) != 0) {
            return false;
        }
        if (context.getApplicationInfo().targetSdkVersion >= 23) {
            try {
                if (((Integer) context.getClass().getMethod("checkSelfPermission", String.class).invoke(context, str)).intValue() != 0) {
                    return false;
                }
            } catch (Throwable th) {
                is.a(th, "ut", "cpm");
            }
        }
        return true;
    }

    public static hz a() throws hm {
        return new hz.a("collection", "1.0", "AMap_collection_1.0").a(new String[]{"com.amap.api.collection"}).a();
    }

    public static hz b() throws hm {
        return new hz.a("co", "1.0.0", "AMap_co_1.0.0").a(new String[]{"com.amap.co", "com.amap.opensdk.co", "com.amap.location"}).a();
    }

    public static void a(ByteArrayOutputStream byteArrayOutputStream, String str) {
        if (TextUtils.isEmpty(str)) {
            try {
                byteArrayOutputStream.write(new byte[]{0});
                return;
            } catch (IOException e) {
                is.a(e, "ut", "wsf");
                return;
            }
        }
        int length = str.length();
        if (length > 255) {
            length = 255;
        }
        a(byteArrayOutputStream, (byte) length, a(str));
    }

    public static String a(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return "";
        }
        try {
            return new String(bArr, "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            return new String(bArr);
        }
    }

    public static byte[] a(String str) {
        if (TextUtils.isEmpty(str)) {
            return new byte[0];
        }
        try {
            return str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException unused) {
            return str.getBytes();
        }
    }

    public static void a(ByteArrayOutputStream byteArrayOutputStream, byte b2, byte[] bArr) {
        try {
            byteArrayOutputStream.write(new byte[]{b2});
            int i = b2 & 255;
            if (i < 255 && i > 0) {
                byteArrayOutputStream.write(bArr);
            } else if (i == 255) {
                byteArrayOutputStream.write(bArr, 0, 255);
            }
        } catch (IOException e) {
            is.a(e, "ut", "wFie");
        }
    }

    public static String b(String str) {
        if (str == null) {
            return null;
        }
        String c2 = hs.c(a(str));
        try {
            return ((char) ((c2.length() % 26) + 65)) + c2;
        } catch (Throwable th) {
            is.a(th, "ut", "tsfb64");
            return "";
        }
    }

    public static String c(String str) {
        return str.length() < 2 ? "" : hs.a(str.substring(1));
    }

    public static boolean a(JSONObject jSONObject, String str) {
        return jSONObject != null && jSONObject.has(str);
    }

    public static byte[] c() {
        try {
            String[] split = new StringBuffer("16,16,18,77,15,911,121,77,121,911,38,77,911,99,86,67,611,96,48,77,84,911,38,67,021,301,86,67,611,98,48,77,511,77,48,97,511,58,48,97,511,84,501,87,511,96,48,77,221,911,38,77,121,37,86,67,25,301,86,67,021,96,86,67,021,701,86,67,35,56,86,67,611,37,221,87").reverse().toString().split(",");
            byte[] bArr = new byte[split.length];
            for (int i = 0; i < split.length; i++) {
                bArr[i] = Byte.parseByte(split[i]);
            }
            String[] split2 = new StringBuffer(new String(hs.b(new String(bArr)))).reverse().toString().split(",");
            byte[] bArr2 = new byte[split2.length];
            for (int i2 = 0; i2 < split2.length; i2++) {
                bArr2[i2] = Byte.parseByte(split2[i2]);
            }
            return bArr2;
        } catch (Throwable th) {
            is.a(th, "ut", "gIV");
            return new byte[16];
        }
    }

    public static String a(Throwable th) {
        StringWriter stringWriter;
        PrintWriter printWriter;
        try {
            stringWriter = new StringWriter();
            try {
                printWriter = new PrintWriter(stringWriter);
                try {
                    th.printStackTrace(printWriter);
                    for (Throwable cause = th.getCause(); cause != null; cause = cause.getCause()) {
                        cause.printStackTrace(printWriter);
                    }
                    String obj = stringWriter.toString();
                    try {
                        stringWriter.close();
                    } catch (Throwable th2) {
                        th2.printStackTrace();
                    }
                    try {
                        printWriter.close();
                    } catch (Throwable th3) {
                        th3.printStackTrace();
                    }
                    return obj;
                } catch (Throwable th4) {
                    th = th4;
                    try {
                        th.printStackTrace();
                        if (stringWriter != null) {
                            try {
                                stringWriter.close();
                            } catch (Throwable th5) {
                                th5.printStackTrace();
                            }
                        }
                        if (printWriter != null) {
                            try {
                                printWriter.close();
                            } catch (Throwable th6) {
                                th6.printStackTrace();
                            }
                        }
                        return null;
                    } finally {
                    }
                }
            } catch (Throwable th7) {
                th = th7;
                printWriter = null;
            }
        } catch (Throwable th8) {
            th = th8;
            stringWriter = null;
            printWriter = null;
        }
    }

    public static String a(Map<String, String> map) {
        if (map.size() == 0) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        try {
            boolean z = true;
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (z) {
                    stringBuffer.append(entry.getKey()).append("=").append(entry.getValue());
                    z = false;
                } else {
                    stringBuffer.append("&").append(entry.getKey()).append("=").append(entry.getValue());
                }
            }
        } catch (Throwable th) {
            is.a(th, "ut", "abP");
        }
        return stringBuffer.toString();
    }

    private static String f(String str) {
        try {
        } catch (Throwable th) {
            is.a(th, "ut", "sPa");
        }
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        String[] split = str.split("&");
        Arrays.sort(split);
        StringBuffer stringBuffer = new StringBuffer();
        for (String str2 : split) {
            stringBuffer.append(str2);
            stringBuffer.append("&");
        }
        String stringBuffer2 = stringBuffer.toString();
        if (stringBuffer2.length() > 1) {
            return (String) stringBuffer2.subSequence(0, stringBuffer2.length() - 1);
        }
        return str;
    }

    public static byte[] b(byte[] bArr) {
        try {
            return h(bArr);
        } catch (Throwable th) {
            is.a(th, "ut", "gZp");
            return new byte[0];
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x004e, code lost:
    
        if (r4 == null) goto L31;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static byte[] c(byte[] r8) {
        /*
            java.lang.String r0 = "zp2"
            java.lang.String r1 = "zp1"
            java.lang.String r2 = "ut"
            r3 = 0
            if (r8 == 0) goto L6f
            int r4 = r8.length
            if (r4 != 0) goto Ld
            goto L6f
        Ld:
            java.io.ByteArrayOutputStream r4 = new java.io.ByteArrayOutputStream     // Catch: java.lang.Throwable -> L3c
            r4.<init>()     // Catch: java.lang.Throwable -> L3c
            java.util.zip.ZipOutputStream r5 = new java.util.zip.ZipOutputStream     // Catch: java.lang.Throwable -> L39
            r5.<init>(r4)     // Catch: java.lang.Throwable -> L39
            java.util.zip.ZipEntry r6 = new java.util.zip.ZipEntry     // Catch: java.lang.Throwable -> L37
            java.lang.String r7 = "log"
            r6.<init>(r7)     // Catch: java.lang.Throwable -> L37
            r5.putNextEntry(r6)     // Catch: java.lang.Throwable -> L37
            r5.write(r8)     // Catch: java.lang.Throwable -> L37
            r5.closeEntry()     // Catch: java.lang.Throwable -> L37
            r5.finish()     // Catch: java.lang.Throwable -> L37
            byte[] r3 = r4.toByteArray()     // Catch: java.lang.Throwable -> L37
            r5.close()     // Catch: java.lang.Throwable -> L32
            goto L50
        L32:
            r8 = move-exception
            com.amap.api.col.p0003sl.is.a(r8, r2, r1)
            goto L50
        L37:
            r8 = move-exception
            goto L3f
        L39:
            r8 = move-exception
            r5 = r3
            goto L3f
        L3c:
            r8 = move-exception
            r4 = r3
            r5 = r4
        L3f:
            java.lang.String r6 = "zp"
            com.amap.api.col.p0003sl.is.a(r8, r2, r6)     // Catch: java.lang.Throwable -> L59
            if (r5 == 0) goto L4e
            r5.close()     // Catch: java.lang.Throwable -> L4a
            goto L4e
        L4a:
            r8 = move-exception
            com.amap.api.col.p0003sl.is.a(r8, r2, r1)
        L4e:
            if (r4 == 0) goto L58
        L50:
            r4.close()     // Catch: java.lang.Throwable -> L54
            goto L58
        L54:
            r8 = move-exception
            com.amap.api.col.p0003sl.is.a(r8, r2, r0)
        L58:
            return r3
        L59:
            r8 = move-exception
            if (r5 == 0) goto L64
            r5.close()     // Catch: java.lang.Throwable -> L60
            goto L64
        L60:
            r3 = move-exception
            com.amap.api.col.p0003sl.is.a(r3, r2, r1)
        L64:
            if (r4 == 0) goto L6e
            r4.close()     // Catch: java.lang.Throwable -> L6a
            goto L6e
        L6a:
            r1 = move-exception
            com.amap.api.col.p0003sl.is.a(r1, r2, r0)
        L6e:
            throw r8
        L6f:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.col.p0003sl.ia.c(byte[]):byte[]");
    }

    public static byte[] a(int i) {
        return new byte[]{(byte) (i / 256), (byte) (i % 256)};
    }

    static PublicKey d() throws CertificateException, InvalidKeySpecException, NoSuchAlgorithmException, NullPointerException, IOException {
        ByteArrayInputStream byteArrayInputStream;
        try {
            byteArrayInputStream = new ByteArrayInputStream(hs.b("MIICnjCCAgegAwIBAgIJAJ0Pdzos7ZfYMA0GCSqGSIb3DQEBBQUAMGgxCzAJBgNVBAYTAkNOMRMwEQYDVQQIDApTb21lLVN0YXRlMRAwDgYDVQQHDAdCZWlqaW5nMREwDwYDVQQKDAhBdXRvbmF2aTEfMB0GA1UEAwwWY29tLmF1dG9uYXZpLmFwaXNlcnZlcjAeFw0xMzA4MTUwNzU2NTVaFw0yMzA4MTMwNzU2NTVaMGgxCzAJBgNVBAYTAkNOMRMwEQYDVQQIDApTb21lLVN0YXRlMRAwDgYDVQQHDAdCZWlqaW5nMREwDwYDVQQKDAhBdXRvbmF2aTEfMB0GA1UEAwwWY29tLmF1dG9uYXZpLmFwaXNlcnZlcjCBnzANBgkqhkiG9w0BAQEFAAOBjQAwgYkCgYEA8eWAyHbFPoFPfdx5AD+D4nYFq4dbJ1p7SIKt19Oz1oivF/6H43v5Fo7s50pD1UF8+Qu4JoUQxlAgOt8OCyQ8DYdkaeB74XKb1wxkIYg/foUwN1CMHPZ9O9ehgna6K4EJXZxR7Y7XVZnbjHZIVn3VpPU/Rdr2v37LjTw+qrABJxMCAwEAAaNQME4wHQYDVR0OBBYEFOM/MLGP8xpVFuVd+3qZkw7uBvOTMB8GA1UdIwQYMBaAFOM/MLGP8xpVFuVd+3qZkw7uBvOTMAwGA1UdEwQFMAMBAf8wDQYJKoZIhvcNAQEFBQADgYEA4LY3g8aAD8JkxAOqUXDDyLuCCGOc2pTIhn0TwMNaVdH4hZlpTeC/wuRD5LJ0z3j+IQ0vLvuQA5uDjVyEOlBrvVIGwSem/1XGUo13DfzgAJ5k1161S5l+sFUo5TxpHOXr8Z5nqJMjieXmhnE/I99GFyHpQmw4cC6rhYUhdhtg+Zk="));
        } catch (Throwable unused) {
            byteArrayInputStream = null;
        }
        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance(c("IWC41MDk"));
            KeyFactory keyFactory = KeyFactory.getInstance(c("EUlNB"));
            Certificate generateCertificate = certificateFactory.generateCertificate(byteArrayInputStream);
            if (generateCertificate != null && keyFactory != null) {
                PublicKey generatePublic = keyFactory.generatePublic(new X509EncodedKeySpec(generateCertificate.getPublicKey().getEncoded()));
                try {
                    byteArrayInputStream.close();
                } catch (Throwable th) {
                    th.printStackTrace();
                }
                return generatePublic;
            }
            try {
                byteArrayInputStream.close();
            } catch (Throwable th2) {
                th2.printStackTrace();
            }
            return null;
        } catch (Throwable unused2) {
            if (byteArrayInputStream != null) {
                try {
                    byteArrayInputStream.close();
                } catch (Throwable th3) {
                    th3.printStackTrace();
                }
            }
            return null;
        }
    }

    public static byte[] d(byte[] bArr) {
        try {
            return h(bArr);
        } catch (Throwable th) {
            th.printStackTrace();
            return new byte[0];
        }
    }

    static String e(byte[] bArr) {
        try {
            return g(bArr);
        } catch (Throwable th) {
            is.a(th, "ut", "h2s");
            return null;
        }
    }

    static String f(byte[] bArr) {
        try {
            return g(bArr);
        } catch (Throwable th) {
            is.a(th, "ut", "csb2h");
            return null;
        }
    }

    public static String g(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        if (bArr == null) {
            return null;
        }
        for (byte b2 : bArr) {
            String hexString = Integer.toHexString(b2 & 255);
            if (hexString.length() == 1) {
                hexString = "0".concat(String.valueOf(hexString));
            }
            sb.append(hexString);
        }
        return sb.toString();
    }

    public static byte[] d(String str) {
        if (str.length() % 2 != 0) {
            str = "0".concat(String.valueOf(str));
        }
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            bArr[i] = (byte) Integer.parseInt(str.substring(i2, i2 + 2), 16);
        }
        return bArr;
    }

    private static byte[] h(byte[] bArr) throws IOException, Throwable {
        ByteArrayOutputStream byteArrayOutputStream;
        GZIPOutputStream gZIPOutputStream;
        GZIPOutputStream gZIPOutputStream2 = null;
        if (bArr == null) {
            return null;
        }
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            } catch (Throwable th) {
                th = th;
            }
        } catch (Throwable th2) {
            th = th2;
            byteArrayOutputStream = null;
        }
        try {
            gZIPOutputStream.write(bArr);
            gZIPOutputStream.finish();
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            gZIPOutputStream.close();
            byteArrayOutputStream.close();
            return byteArray;
        } catch (Throwable th3) {
            th = th3;
            gZIPOutputStream2 = gZIPOutputStream;
            try {
                throw th;
            } catch (Throwable th4) {
                if (gZIPOutputStream2 != null) {
                    gZIPOutputStream2.close();
                }
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
                throw th4;
            }
        }
    }

    public static String a(long j) {
        return a(j, "yyyyMMdd HH:mm:ss:SSS");
    }

    public static String a(long j, String str) {
        try {
            return new SimpleDateFormat(str, Locale.CHINA).format(new Date(j));
        } catch (Throwable th) {
            is.a(th, "ut", "ctt");
            return null;
        }
    }

    public static boolean e(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            return hs.a(str).contains(Build.MODEL + Build.VERSION.SDK_INT);
        } catch (Throwable unused) {
            return false;
        }
    }

    public static void a(Context context, String str, String str2, JSONObject jSONObject) {
        String str3;
        String str4;
        String str5;
        String str6;
        str3 = "";
        String e = hn.e(context);
        String b2 = hv.b(e);
        String a2 = hn.a(context);
        try {
            if (jSONObject.has(CloudParamKeys.INFO)) {
                str5 = jSONObject.getString(CloudParamKeys.INFO);
                str6 = "请在高德开放平台官网中搜索\"" + str5 + "\"相关内容进行解决";
            } else {
                str5 = "";
                str6 = str5;
            }
            try {
            } catch (Throwable unused) {
                str3 = str6;
                str4 = str3;
                Log.i("authErrLog", f1169a);
                Log.i("authErrLog", "                                   鉴权错误信息                                  ");
                Log.i("authErrLog", f1169a);
                g("SHA1Package:".concat(String.valueOf(e)));
                g("key:".concat(String.valueOf(a2)));
                g("csid:".concat(String.valueOf(str)));
                g("gsid:".concat(String.valueOf(str2)));
                g("json:" + jSONObject.toString());
                Log.i("authErrLog", "                                                                               ");
                Log.i("authErrLog", str4);
                Log.i("authErrLog", f1169a);
            }
        } catch (Throwable unused2) {
        }
        if ("INVALID_USER_SCODE".equals(str5)) {
            String string = jSONObject.has("sec_code") ? jSONObject.getString("sec_code") : "";
            str3 = jSONObject.has("sec_code_debug") ? jSONObject.getString("sec_code_debug") : "";
            if (b2.equals(string) || b2.equals(str3)) {
                str4 = c("C6K+35Zyo6auY5b635byA5pS+5bmz5Y+w5a6Y572R5Lit5pCc57Si") + "\"请求内容过长导致业务调用失败\"相关内容进行解决";
                Log.i("authErrLog", f1169a);
                Log.i("authErrLog", "                                   鉴权错误信息                                  ");
                Log.i("authErrLog", f1169a);
                g("SHA1Package:".concat(String.valueOf(e)));
                g("key:".concat(String.valueOf(a2)));
                g("csid:".concat(String.valueOf(str)));
                g("gsid:".concat(String.valueOf(str2)));
                g("json:" + jSONObject.toString());
                Log.i("authErrLog", "                                                                               ");
                Log.i("authErrLog", str4);
                Log.i("authErrLog", f1169a);
            }
        } else if ("INVALID_USER_KEY".equals(str5)) {
            str3 = jSONObject.has(MedalConstants.EVENT_KEY) ? jSONObject.getString(MedalConstants.EVENT_KEY) : "";
            if (str3.length() > 0 && !a2.equals(str3)) {
                str6 = c("C6K+35Zyo6auY5b635byA5pS+5bmz5Y+w5a6Y572R5LiK5Y+R6LW35oqA5pyv5ZKo6K+i5bel5Y2V4oCUPui0puWPt+S4jktleemXrumimO+8jOWSqOivoklOVkFMSURfVVNFUl9LRVnlpoLkvZXop6PlhrM=");
            }
        }
        str4 = str6;
        Log.i("authErrLog", f1169a);
        Log.i("authErrLog", "                                   鉴权错误信息                                  ");
        Log.i("authErrLog", f1169a);
        g("SHA1Package:".concat(String.valueOf(e)));
        g("key:".concat(String.valueOf(a2)));
        g("csid:".concat(String.valueOf(str)));
        g("gsid:".concat(String.valueOf(str2)));
        g("json:" + jSONObject.toString());
        Log.i("authErrLog", "                                                                               ");
        Log.i("authErrLog", str4);
        Log.i("authErrLog", f1169a);
    }

    private static void g(String str) {
        int i;
        while (true) {
            if (str.length() < 78) {
                break;
            }
            Log.i("authErrLog", "|" + str.substring(0, 78) + "|");
            str = str.substring(78);
        }
        StringBuilder sb = new StringBuilder("|");
        sb.append(str);
        for (i = 0; i < 78 - str.length(); i++) {
            sb.append(" ");
        }
        sb.append("|");
        Log.i("authErrLog", sb.toString());
    }

    public static boolean b(Context context) {
        return ir.a(context);
    }

    public static Calendar a(String str, String str2) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str2, Locale.CHINA);
            Calendar calendar = Calendar.getInstance();
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(simpleDateFormat.parse(str));
            calendar.set(calendar.get(1), calendar.get(2), calendar.get(5), calendar2.get(11), calendar2.get(12), calendar2.get(13));
            return calendar;
        } catch (ParseException e) {
            is.a(e, "ut", "ctt");
            return null;
        }
    }

    public static String b(Map<String, String> map) {
        String str;
        if (map != null) {
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (sb.length() > 0) {
                    sb.append("&");
                }
                sb.append(entry.getKey());
                sb.append("=");
                sb.append(entry.getValue());
            }
            str = sb.toString();
        } else {
            str = null;
        }
        return f(str);
    }
}
