package defpackage;

import com.huawei.hwlogsmodel.LogUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* loaded from: classes5.dex */
public class jsa {
    /* JADX WARN: Removed duplicated region for block: B:11:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0055  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String d(java.lang.String r6, java.lang.String r7, java.lang.String r8) throws java.io.UnsupportedEncodingException {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r1 = 512(0x200, float:7.17E-43)
            r0.<init>(r1)
            java.lang.String r1 = "?"
            boolean r2 = r6.contains(r1)
            r3 = 0
            if (r2 == 0) goto L3f
            r2 = 63
            int r2 = r6.indexOf(r2)
            r4 = 0
            java.lang.String r2 = r6.substring(r4, r2)
            int r4 = r6.indexOf(r1)
            java.lang.String r4 = r6.substring(r4)
            int r4 = r4.length()
            r5 = 1
            if (r4 <= r5) goto L39
            jrv r3 = new jrv
            int r1 = r6.indexOf(r1)
            int r1 = r1 + r5
            java.lang.String r1 = r6.substring(r1)
            r3.<init>(r1)
            goto L46
        L39:
            jrv r1 = new jrv
            r1.<init>(r3)
            goto L45
        L3f:
            jrv r1 = new jrv
            r1.<init>(r3)
            r2 = r6
        L45:
            r3 = r1
        L46:
            java.lang.String r1 = "POST&"
            r0.append(r1)
            java.lang.String r1 = "/platform"
            boolean r6 = r6.contains(r1)
            java.lang.String r1 = "&"
            if (r6 == 0) goto L95
            java.lang.String r6 = "channelID"
            java.lang.String r6 = r3.b(r6)
            long r4 = java.lang.System.currentTimeMillis()
            java.lang.String r4 = java.lang.String.valueOf(r4)
            r0.append(r2)
            r0.append(r1)
            r0.append(r3)
            r0.append(r1)
            r0.append(r7)
            java.lang.String r7 = "&channelID="
            r0.append(r7)
            r0.append(r6)
            java.lang.String r7 = "&timestamp="
            r0.append(r7)
            r0.append(r4)
            java.lang.String r7 = r0.toString()
            java.lang.String r7 = defpackage.jrz.e(r7, r8)
            java.lang.Object[] r6 = new java.lang.Object[]{r6, r4, r7}
            java.lang.String r7 = "HMAC-SHA256 channelID={0}, timestamp={1}, signature=\"{2}\""
            java.lang.String r6 = java.text.MessageFormat.format(r7, r6)
            return r6
        L95:
            java.lang.String r6 = "appID"
            java.lang.String r6 = r3.b(r6)
            java.lang.String r4 = "LogUpload Service HeaderUtils appid is "
            java.lang.Object[] r4 = new java.lang.Object[]{r4, r6}
            java.lang.String r5 = "HeaderUtils"
            com.huawei.hwlogsmodel.LogUtil.a(r5, r4)
            java.lang.String r4 = "/"
            int r4 = r2.indexOf(r4)
            java.lang.String r2 = r2.substring(r4)
            r0.append(r2)
            r0.append(r1)
            r0.append(r3)
            r0.append(r1)
            r0.append(r7)
            java.lang.String r7 = "&appID="
            r0.append(r7)
            r0.append(r6)
            java.lang.String r7 = r0.toString()
            java.lang.String r7 = defpackage.jrz.e(r7, r8)
            java.lang.String r8 = "LogUpload Service HeaderUtils is auth is "
            java.lang.Object[] r8 = new java.lang.Object[]{r8, r7}
            com.huawei.hwlogsmodel.LogUtil.a(r5, r8)
            java.lang.Object[] r6 = new java.lang.Object[]{r6, r7}
            java.lang.String r7 = "HMAC-SHA256 appID={0}, signature=\"{1}\""
            java.lang.String r6 = java.text.MessageFormat.format(r7, r6)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.jsa.d(java.lang.String, java.lang.String, java.lang.String):java.lang.String");
    }

    public static String b(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(bArr);
            return e(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            LogUtil.b("HeaderUtils", "LogUpload Service shaByte", "Exception e = " + e.getMessage());
            return null;
        }
    }

    public static String e(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() == 1) {
                stringBuffer.append("0");
            }
            stringBuffer.append(hexString);
        }
        return stringBuffer.toString();
    }

    public static long e(File file) {
        FileInputStream fileInputStream;
        long j = 0;
        if (file == null) {
            return 0L;
        }
        if (file.exists()) {
            FileInputStream fileInputStream2 = null;
            try {
                try {
                    fileInputStream = new FileInputStream(file);
                } catch (Throwable th) {
                    th = th;
                    fileInputStream = fileInputStream2;
                }
            } catch (FileNotFoundException e) {
                e = e;
            } catch (IOException e2) {
                e = e2;
            }
            try {
                j = fileInputStream.available();
                try {
                    fileInputStream.close();
                } catch (IOException e3) {
                    LogUtil.b("HeaderUtils", "IOException ", e3.getMessage());
                }
            } catch (FileNotFoundException e4) {
                e = e4;
                fileInputStream2 = fileInputStream;
                LogUtil.b("HeaderUtils", "FileNotFoundException ", e.getMessage());
                if (fileInputStream2 != null) {
                    try {
                        fileInputStream2.close();
                    } catch (IOException e5) {
                        LogUtil.b("HeaderUtils", "IOException ", e5.getMessage());
                    }
                }
                return j;
            } catch (IOException e6) {
                e = e6;
                fileInputStream2 = fileInputStream;
                LogUtil.b("HeaderUtils", "IOException ", e.getMessage());
                if (fileInputStream2 != null) {
                    try {
                        fileInputStream2.close();
                    } catch (IOException e7) {
                        LogUtil.b("HeaderUtils", "IOException ", e7.getMessage());
                    }
                }
                return j;
            } catch (Throwable th2) {
                th = th2;
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e8) {
                        LogUtil.b("HeaderUtils", "IOException ", e8.getMessage());
                    }
                }
                throw th;
            }
        }
        return j;
    }
}
