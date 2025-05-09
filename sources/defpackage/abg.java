package defpackage;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.text.TextUtils;
import com.google.flatbuffers.reflection.BaseType;
import com.huawei.health.R;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.InputStream;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

/* loaded from: classes8.dex */
public class abg {
    public static String a(Context context) {
        if (context == null) {
            abd.b("KeyUtil", "getHiCloudSignForEMUI11: context is null");
            return "";
        }
        return a(context, R.string._2130850983_res_0x7f0234a7) + d(context, R.xml._2132082697_res_0x7f150009) + c(context, "HiCloudSign");
    }

    public static String e(Context context) {
        if (context == null) {
            abd.b("KeyUtil", "getHiCloudSign: context is null");
            return "";
        }
        return a(context, R.string._2130850771_res_0x7f0233d3) + d(context, R.xml._2132082688_res_0x7f150000) + c(context, "ApplicationSign");
    }

    public static String b(Context context, String str) {
        if (context != null && str != null) {
            try {
                PackageManager packageManager = context.getPackageManager();
                if (packageManager == null) {
                    abd.b("KeyUtil", "getSignForPkgName: packageManager is null");
                    return "";
                }
                PackageInfo packageInfo = packageManager.getPackageInfo(str, 64);
                if (packageInfo == null) {
                    abd.b("KeyUtil", "getSignForPkgName: packageInfo is null");
                    return "";
                }
                Signature[] signatureArr = packageInfo.signatures;
                if (signatureArr != null && signatureArr.length >= 1) {
                    Signature signature = signatureArr[0];
                    if (signature == null) {
                        abd.b("KeyUtil", "getSignForPkgName: sign[0] is null");
                        return "";
                    }
                    X509Certificate x509Certificate = (X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(signature.toByteArray()));
                    if (x509Certificate == null) {
                        abd.b("KeyUtil", "getSignForPkgName: cert is null");
                        return "";
                    }
                    PublicKey publicKey = x509Certificate.getPublicKey();
                    if (publicKey == null) {
                        abd.b("KeyUtil", "getSignForPkgName: publicKey is null");
                        return "";
                    }
                    return c(publicKey.getEncoded());
                }
                abd.b("KeyUtil", "getSignForPkgName: signs is null or signs.length < 1");
                return "";
            } catch (PackageManager.NameNotFoundException unused) {
                abd.b("KeyUtil", "getSignForPkgName: name found error");
                return "";
            } catch (CertificateException e) {
                abd.b("KeyUtil", "getSignForPkgName: CertificateException = " + e.toString());
                return "";
            }
        }
        abd.b("KeyUtil", "getSignForPkgName: context or pkgName is null");
        return "";
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x002f, code lost:
    
        r2 = r4.getAttributeValue(null, "value");
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:48:0x006a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String d(android.content.Context r4, int r5) {
        /*
            java.lang.String r0 = "close xml Exception, ignore."
            java.lang.String r1 = "KeyUtil"
            if (r4 != 0) goto Le
            java.lang.String r4 = "getSignFromXml: context is null"
            defpackage.abd.b(r1, r4)
            java.lang.String r4 = ""
            return r4
        Le:
            r2 = 0
            android.content.res.Resources r4 = r4.getResources()     // Catch: java.lang.Throwable -> L49 java.io.IOException -> L4b org.xmlpull.v1.XmlPullParserException -> L57
            android.content.res.XmlResourceParser r4 = r4.getXml(r5)     // Catch: java.lang.Throwable -> L49 java.io.IOException -> L4b org.xmlpull.v1.XmlPullParserException -> L57
            if (r4 == 0) goto L3f
            int r5 = r4.next()     // Catch: java.lang.Throwable -> L3c java.io.IOException -> L4c org.xmlpull.v1.XmlPullParserException -> L58
        L1d:
            r3 = 1
            if (r5 == r3) goto L3f
            r3 = 2
            if (r5 != r3) goto L37
            java.lang.String r5 = "config"
            java.lang.String r3 = r4.getName()     // Catch: java.lang.Throwable -> L3c java.io.IOException -> L4c org.xmlpull.v1.XmlPullParserException -> L58
            boolean r5 = r5.equals(r3)     // Catch: java.lang.Throwable -> L3c java.io.IOException -> L4c org.xmlpull.v1.XmlPullParserException -> L58
            if (r5 == 0) goto L37
            java.lang.String r5 = "value"
            java.lang.String r5 = r4.getAttributeValue(r2, r5)     // Catch: java.lang.Throwable -> L3c java.io.IOException -> L4c org.xmlpull.v1.XmlPullParserException -> L58
            r2 = r5
            goto L3f
        L37:
            int r5 = r4.next()     // Catch: java.lang.Throwable -> L3c java.io.IOException -> L4c org.xmlpull.v1.XmlPullParserException -> L58
            goto L1d
        L3c:
            r5 = move-exception
            r2 = r4
            goto L67
        L3f:
            if (r4 == 0) goto L66
            r4.close()     // Catch: java.lang.Exception -> L45
            goto L66
        L45:
            defpackage.abd.d(r1, r0)
            goto L66
        L49:
            r4 = move-exception
            goto L68
        L4b:
            r4 = r2
        L4c:
            java.lang.String r5 = "IOException getComponentFromXml"
            defpackage.abd.b(r1, r5)     // Catch: java.lang.Throwable -> L3c
            if (r4 == 0) goto L66
            r4.close()     // Catch: java.lang.Exception -> L63
            goto L66
        L57:
            r4 = r2
        L58:
            java.lang.String r5 = "XmlPullParserException getComponentFromXml"
            defpackage.abd.b(r1, r5)     // Catch: java.lang.Throwable -> L3c
            if (r4 == 0) goto L66
            r4.close()     // Catch: java.lang.Exception -> L63
            goto L66
        L63:
            defpackage.abd.d(r1, r0)
        L66:
            return r2
        L67:
            r4 = r5
        L68:
            if (r2 == 0) goto L71
            r2.close()     // Catch: java.lang.Exception -> L6e
            goto L71
        L6e:
            defpackage.abd.d(r1, r0)
        L71:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.abg.d(android.content.Context, int):java.lang.String");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v10 */
    /* JADX WARN: Type inference failed for: r7v2 */
    /* JADX WARN: Type inference failed for: r7v3 */
    /* JADX WARN: Type inference failed for: r7v4 */
    /* JADX WARN: Type inference failed for: r7v5, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r7v8 */
    /* JADX WARN: Type inference failed for: r7v9, types: [java.io.ByteArrayOutputStream, java.io.Closeable] */
    private static String c(Context context, String str) {
        Throwable th;
        ?? r7;
        Exception e;
        InputStream inputStream;
        String str2 = "";
        if (context != null && !TextUtils.isEmpty(str)) {
            InputStream inputStream2 = null;
            try {
                inputStream = context.getAssets().open(str);
                try {
                    r7 = new ByteArrayOutputStream();
                } catch (Exception e2) {
                    e = e2;
                    r7 = 0;
                } catch (Throwable th2) {
                    th = th2;
                    d(inputStream);
                    d(inputStream2);
                    throw th;
                }
            } catch (Exception e3) {
                r7 = 0;
                e = e3;
                inputStream = null;
            } catch (Throwable th3) {
                th = th3;
                r7 = 0;
                inputStream = inputStream2;
                inputStream2 = r7;
                d(inputStream);
                d(inputStream2);
                throw th;
            }
            try {
                byte[] bArr = new byte[40];
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read <= 0) {
                        break;
                    }
                    r7.write(bArr, 0, read);
                }
                str2 = r7.toString("utf-8");
                d(inputStream);
                d(r7);
            } catch (Exception e4) {
                e = e4;
                try {
                    abd.b("KeyUtil", "Assets Exception2" + e.getMessage());
                    d(inputStream);
                    d(r7);
                    return str2;
                } catch (Throwable th4) {
                    th = th4;
                    inputStream2 = inputStream;
                    r7 = r7;
                    inputStream = inputStream2;
                    inputStream2 = r7;
                    d(inputStream);
                    d(inputStream2);
                    throw th;
                }
            } catch (Throwable th5) {
                th = th5;
                inputStream2 = r7;
                d(inputStream);
                d(inputStream2);
                throw th;
            }
            return str2;
        }
        abd.b("KeyUtil", "getSignFromAssets: context or fileName is null");
        return "";
    }

    private static String a(Context context, int i) {
        if (context == null) {
            abd.b("KeyUtil", "getSignFromString: context is null");
            return "";
        }
        return context.getString(i);
    }

    private static void d(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
                abd.b("KeyUtil", "closeStream exception :" + e.getMessage());
            }
        }
    }

    private static String c(byte[] bArr) {
        if (bArr == null) {
            return "";
        }
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        char[] cArr2 = new char[bArr.length * 2];
        for (int i = 0; i < bArr.length; i++) {
            byte b = bArr[i];
            int i2 = i * 2;
            cArr2[i2] = cArr[(b >>> 4) & 15];
            cArr2[i2 + 1] = cArr[b & BaseType.Obj];
        }
        return new String(cArr2);
    }

    public static String d() {
        return "com.huawei.hidisk";
    }
}
