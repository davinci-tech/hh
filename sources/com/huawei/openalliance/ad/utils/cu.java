package com.huawei.openalliance.ad.utils;

import com.huawei.openalliance.ad.ho;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* loaded from: classes5.dex */
public abstract class cu {
    public static byte[] a(byte[] bArr) {
        try {
            return MessageDigest.getInstance("SHA-256").digest(bArr);
        } catch (NoSuchAlgorithmException unused) {
            ho.d("Sha256Util", "sha256 NoSuchAlgorithmException");
            return new byte[0];
        }
    }

    public static String a(String str) {
        if (cz.b(str)) {
            return "";
        }
        try {
            return an.a(a(str.getBytes("UTF-8")));
        } catch (UnsupportedEncodingException unused) {
            ho.c("Sha256Util", "digest UnsupportedEncodingException");
            return "";
        }
    }

    private static String a(InputStream inputStream) {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] bArr = new byte[8192];
        boolean z = false;
        while (true) {
            int read = inputStream.read(bArr);
            if (read <= 0) {
                break;
            }
            messageDigest.update(bArr, 0, read);
            z = true;
        }
        if (z) {
            return an.a(messageDigest.digest());
        }
        return null;
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [boolean] */
    public static String a(File file) {
        FileInputStream fileInputStream;
        Closeable closeable = null;
        if (file != null) {
            ?? d = ae.d(file);
            try {
                if (d != 0) {
                    try {
                        fileInputStream = new FileInputStream(file);
                        try {
                            String a2 = a(fileInputStream);
                            cy.a((Closeable) fileInputStream);
                            return a2;
                        } catch (FileNotFoundException | IOException | NoSuchAlgorithmException unused) {
                            ho.c("Sha256Util", "fail to get file sha256");
                            cy.a((Closeable) fileInputStream);
                            return null;
                        }
                    } catch (FileNotFoundException | IOException | NoSuchAlgorithmException unused2) {
                        fileInputStream = null;
                    } catch (Throwable th) {
                        th = th;
                        cy.a(closeable);
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                closeable = d;
            }
        }
        return null;
    }
}
