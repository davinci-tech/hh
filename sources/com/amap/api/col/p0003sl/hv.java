package com.amap.api.col.p0003sl;

import android.text.TextUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* loaded from: classes2.dex */
public final class hv {
    public static String a(String str) {
        FileInputStream fileInputStream;
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            File file = new File(str);
            if (file.isFile() && file.exists()) {
                byte[] bArr = new byte[2048];
                MessageDigest messageDigest = MessageDigest.getInstance(ia.c("ETUQ1"));
                fileInputStream = new FileInputStream(file);
                while (true) {
                    try {
                        int read = fileInputStream.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        messageDigest.update(bArr, 0, read);
                    } catch (Throwable th) {
                        th = th;
                        try {
                            is.a(th, "MD5", "gfm");
                            return null;
                        } finally {
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                } catch (IOException e) {
                                    is.a(e, "MD5", "gfm");
                                }
                            }
                        }
                    }
                }
                String e2 = ia.e(messageDigest.digest());
                try {
                    fileInputStream.close();
                } catch (IOException e3) {
                    is.a(e3, "MD5", "gfm");
                }
                return e2;
            }
            return null;
        } catch (Throwable th2) {
            th = th2;
            fileInputStream = null;
        }
    }

    public static String b(String str) {
        if (str == null) {
            return null;
        }
        return ia.e(d(str));
    }

    public static String c(String str) {
        return ia.f(e(str));
    }

    public static byte[] a(byte[] bArr, String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(str);
            messageDigest.update(bArr);
            return messageDigest.digest();
        } catch (Throwable th) {
            is.a(th, "MD5", "gmb");
            return null;
        }
    }

    private static byte[] d(String str) {
        try {
            return f(str);
        } catch (Throwable th) {
            is.a(th, "MD5", "gmb");
            return new byte[0];
        }
    }

    private static byte[] e(String str) {
        try {
            return f(str);
        } catch (Throwable th) {
            th.printStackTrace();
            return new byte[0];
        }
    }

    private static byte[] f(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        if (str == null) {
            return null;
        }
        MessageDigest messageDigest = MessageDigest.getInstance(ia.c("ETUQ1"));
        messageDigest.update(ia.a(str));
        return messageDigest.digest();
    }

    public static String a(byte[] bArr) {
        return ia.e(a(bArr, ia.c("ETUQ1")));
    }
}
