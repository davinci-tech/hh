package defpackage;

import android.content.Context;
import android.text.TextUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* loaded from: classes5.dex */
public class lor {
    private static String b;

    public static String b(Context context) {
        if (b == null) {
            String d = d(context, "encodehwmultisim");
            if (TextUtils.isEmpty(d)) {
                byte[] b2 = lon.b();
                if (b2.length == 0) {
                    return null;
                }
                String c = lon.c(b2);
                b(context, c, "encodehwmultisim");
                b = c;
            } else {
                b = d;
            }
        }
        return b;
    }

    public static void b(Context context, String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        int[] b2 = b(str, str2);
        d(context, b2, str2);
        a(context, e(str, b2), str2);
    }

    public static String d(Context context, String str) {
        StringBuffer stringBuffer = new StringBuffer("");
        int[] c = c(context, str);
        if (c.length <= 0) {
            return stringBuffer.toString();
        }
        int length = c.length;
        for (int i = 0; i < length; i++) {
            String b2 = b(a(context, d(str + i)));
            if (TextUtils.isEmpty(b2)) {
                return "";
            }
            if (i == length / 2) {
                stringBuffer.append(String.copyValueOf(e(b2.toCharArray(), false)));
            } else {
                stringBuffer.append(b2);
            }
        }
        return stringBuffer.toString();
    }

    private static String[] e(String str, int[] iArr) {
        String[] strArr = new String[iArr.length];
        if (!TextUtils.isEmpty(str) && iArr.length > 0) {
            int i = 0;
            for (int i2 = 0; i2 < iArr.length; i2++) {
                try {
                    if (i2 == iArr.length / 2) {
                        strArr[i2] = String.copyValueOf(e(str.substring(i, iArr[i2] + i).toCharArray(), true));
                    } else {
                        strArr[i2] = str.substring(i, iArr[i2] + i);
                    }
                    i += iArr[i2];
                } catch (IndexOutOfBoundsException unused) {
                }
            }
        }
        return strArr;
    }

    private static char[] e(char[] cArr, boolean z) {
        char[] cArr2 = new char[cArr.length];
        int i = 0;
        if (z) {
            int length = cArr.length;
            int i2 = 0;
            while (i < length) {
                cArr2[i2] = (char) (cArr[i] + 2);
                i2++;
                i++;
            }
        } else {
            int length2 = cArr.length;
            int i3 = 0;
            while (i < length2) {
                cArr2[i3] = (char) (cArr[i] - 2);
                i3++;
                i++;
            }
        }
        return cArr2;
    }

    private static void d(Context context, int[] iArr, String str) {
        if (iArr == null || iArr.length <= 0 || TextUtils.isEmpty(str)) {
            return;
        }
        StringBuffer stringBuffer = new StringBuffer("");
        for (int i : iArr) {
            stringBuffer.append(i);
            stringBuffer.append("/");
        }
        try {
            a(a(context, d(str)), stringBuffer.substring(0, stringBuffer.length() - 1));
        } catch (IOException e) {
            loq.b("EncryptFileUtils", "saveIndexFile->saveStrs " + e.toString());
        }
    }

    private static int[] c(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return new int[0];
        }
        String[] split = b(a(context, d(str))).split("/");
        if (split.length <= 0) {
            return new int[0];
        }
        int[] iArr = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            try {
                iArr[i] = Integer.parseInt(split[i]);
            } catch (NumberFormatException unused) {
                return new int[0];
            }
        }
        return iArr;
    }

    private static String b(String str) {
        return (TextUtils.isEmpty(str) || !new File(str).exists()) ? "" : b(new File(str)).toString();
    }

    private static void a(Context context, String[] strArr, String str) {
        if (strArr == null || strArr.length <= 0 || TextUtils.isEmpty(str) || context == null) {
            return;
        }
        for (int i = 0; i < strArr.length; i++) {
            try {
                a(a(context, d(str + i)), strArr[i]);
            } catch (IOException e) {
                loq.b("EncryptFileUtils", "saveStrsToFile->saveStrs " + e.toString());
            }
        }
    }

    private static String a(Context context, String str) {
        if (TextUtils.isEmpty(str) || context == null) {
            return "";
        }
        return context.getFilesDir() + File.separator + str;
    }

    public static int[] b(String str, String str2) {
        return (TextUtils.isEmpty(str) || str.length() < 16 || TextUtils.isEmpty(str2)) ? new int[0] : new int[]{3, 4, 4, 3, str.length() - 14};
    }

    private static String d(String str) {
        return c(str, "SHA-256");
    }

    public static String c(String str, String str2) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(str2);
            messageDigest.update(str.getBytes("UTF-8"));
            return a(messageDigest.digest());
        } catch (NoSuchAlgorithmException unused) {
            return String.valueOf(str.hashCode());
        } catch (Exception unused2) {
            return "";
        }
    }

    private static String a(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (byte b2 : bArr) {
            String hexString = Integer.toHexString(b2 & 255);
            if (hexString.length() == 1) {
                sb.append('0');
            }
            sb.append(hexString);
        }
        return sb.toString();
    }

    private static void a(String str, String str2) throws IOException {
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(str, true), "UTF-8");
        try {
            try {
                outputStreamWriter.write(str2);
            } catch (IOException unused) {
                loq.c("EncryptFileUtils", "IOException when write");
            }
            try {
                outputStreamWriter.close();
            } catch (IOException unused2) {
                loq.c("EncryptFileUtils", "IOException when close");
            }
        } catch (Throwable th) {
            try {
                outputStreamWriter.close();
            } catch (IOException unused3) {
                loq.c("EncryptFileUtils", "IOException when close");
            }
            throw th;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:39:0x00a2, code lost:
    
        if (r8 == null) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x0084, code lost:
    
        if (r8 == null) goto L68;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00c7 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:61:? A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00be A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00b5 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r12v12 */
    /* JADX WARN: Type inference failed for: r12v2 */
    /* JADX WARN: Type inference failed for: r12v8, types: [java.io.InputStreamReader] */
    /* JADX WARN: Type inference failed for: r6v0 */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v10 */
    /* JADX WARN: Type inference failed for: r6v2, types: [java.io.InputStreamReader] */
    /* JADX WARN: Type inference failed for: r6v3, types: [java.io.InputStreamReader] */
    /* JADX WARN: Type inference failed for: r6v4 */
    /* JADX WARN: Type inference failed for: r6v5 */
    /* JADX WARN: Type inference failed for: r6v8 */
    /* JADX WARN: Type inference failed for: r6v9 */
    /* JADX WARN: Type inference failed for: r7v1, types: [java.io.InputStreamReader, java.io.Reader] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.StringBuffer b(java.io.File r12) {
        /*
            Method dump skipped, instructions count: 207
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.lor.b(java.io.File):java.lang.StringBuffer");
    }
}
