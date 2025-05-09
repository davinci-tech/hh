package defpackage;

import com.google.flatbuffers.reflection.BaseType;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* loaded from: classes7.dex */
public final class trl {
    private static final char[] b = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static final char[] c = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    private static char[] a(byte[] bArr, boolean z) {
        return b(bArr, z ? c : b);
    }

    private static char[] b(byte[] bArr, char[] cArr) {
        char[] cArr2 = new char[0];
        if (bArr == null) {
            tov.d("HexUtil", "encodeHex dataByte is null");
            return cArr2;
        }
        if (cArr == null) {
            tov.d("HexUtil", "encodeHex toDigits is null");
            return cArr2;
        }
        if (bArr.length > 65535) {
            tov.d("HexUtil", "encodeHex dataByte's length is too large");
            return cArr2;
        }
        char[] cArr3 = new char[bArr.length << 1];
        int i = 0;
        for (byte b2 : bArr) {
            int i2 = i + 1;
            cArr3[i] = cArr[(b2 & 240) >>> 4];
            i += 2;
            cArr3[i2] = cArr[b2 & BaseType.Obj];
        }
        return cArr3;
    }

    public static String d(byte[] bArr, boolean z) {
        return new String(a(bArr, z));
    }

    public static String a(File file) {
        FileInputStream fileInputStream;
        if (file == null) {
            tov.d("HexUtil", "getFileShaHex file is null");
            return null;
        }
        if (!file.exists()) {
            tov.d("HexUtil", "getFileShaHex file not exit");
            return null;
        }
        try {
            fileInputStream = new FileInputStream(file);
        } catch (FileNotFoundException unused) {
            tov.d("HexUtil", "getFileShaHex FileNotFoundException");
            fileInputStream = null;
        }
        try {
            if (fileInputStream == null) {
                return null;
            }
            try {
                MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
                byte[] bArr = new byte[4096];
                int read = fileInputStream.read(bArr);
                if (read == -1) {
                    messageDigest.update(bArr, 0, 0);
                } else {
                    messageDigest.update(bArr, 0, read);
                    while (true) {
                        int read2 = fileInputStream.read(bArr);
                        if (read2 == -1) {
                            break;
                        }
                        messageDigest.update(bArr, 0, read2);
                    }
                }
                return d(messageDigest.digest(), true);
            } catch (IOException unused2) {
                tov.d("HexUtil", "getFileShaHex IOException");
                try {
                    fileInputStream.close();
                    return "";
                } catch (IOException unused3) {
                    tov.d("HexUtil", "getFileShaHex IOException");
                    return "";
                }
            } catch (NoSuchAlgorithmException unused4) {
                tov.d("HexUtil", "getFileShaHex NoSuchAlgorithmException");
                fileInputStream.close();
                return "";
            }
        } finally {
            try {
                fileInputStream.close();
            } catch (IOException unused5) {
                tov.d("HexUtil", "getFileShaHex IOException");
            }
        }
    }
}
