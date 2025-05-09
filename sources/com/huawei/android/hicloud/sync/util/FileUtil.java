package com.huawei.android.hicloud.sync.util;

import com.google.flatbuffers.reflection.BaseType;
import defpackage.abd;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* loaded from: classes8.dex */
public abstract class FileUtil {
    public static final String TAG = "FileUtil";

    public static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                abd.b(TAG, "Closeable exception" + e);
            }
        }
    }

    /* JADX WARN: Not initialized variable reg: 3, insn: 0x00da: MOVE (r2 I:??[OBJECT, ARRAY]) = (r3 I:??[OBJECT, ARRAY]), block:B:65:0x00da */
    public static String getFileHashData(String str, String str2) {
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2;
        MessageDigest messageDigest;
        FileInputStream fileInputStream3 = null;
        r2 = null;
        r2 = null;
        r2 = null;
        r2 = null;
        r2 = null;
        r2 = null;
        String str3 = null;
        try {
        } catch (Throwable th) {
            th = th;
            fileInputStream3 = fileInputStream;
        }
        try {
            try {
                messageDigest = MessageDigest.getInstance(str2);
                fileInputStream2 = new FileInputStream(str);
            } catch (IOException unused) {
                abd.b(TAG, "Close FileInputStream failed!");
            }
            try {
                byte[] bArr = new byte[1024];
                long j = 0;
                while (true) {
                    int read = fileInputStream2.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    messageDigest.update(bArr, 0, read);
                    j += read;
                }
                str3 = j > 0 ? a(messageDigest.digest()) : null;
                try {
                    fileInputStream2.close();
                } catch (IOException unused2) {
                    abd.b(TAG, "Close FileInputStream failed!");
                }
            } catch (FileNotFoundException e) {
                e = e;
                abd.b(TAG, "getFileHashData FileNotFoundException" + e);
                if (fileInputStream2 != null) {
                    fileInputStream2.close();
                }
                return str3;
            } catch (IOException e2) {
                e = e2;
                abd.b(TAG, "getFileHashData IOException" + e);
                if (fileInputStream2 != null) {
                    fileInputStream2.close();
                }
                return str3;
            } catch (IllegalArgumentException e3) {
                e = e3;
                abd.b(TAG, "getFileHashData IllegalArgumentException" + e);
                if (fileInputStream2 != null) {
                    fileInputStream2.close();
                }
                return str3;
            } catch (IndexOutOfBoundsException e4) {
                e = e4;
                abd.b(TAG, "getFileHashData IndexOutOfBoundsException" + e);
                if (fileInputStream2 != null) {
                    fileInputStream2.close();
                }
                return str3;
            } catch (NoSuchAlgorithmException e5) {
                e = e5;
                abd.b(TAG, "getFileHashData NoSuchAlgorithmException" + e);
                if (fileInputStream2 != null) {
                    fileInputStream2.close();
                }
                return str3;
            }
        } catch (FileNotFoundException e6) {
            e = e6;
            fileInputStream2 = null;
        } catch (IOException e7) {
            e = e7;
            fileInputStream2 = null;
        } catch (IllegalArgumentException e8) {
            e = e8;
            fileInputStream2 = null;
        } catch (IndexOutOfBoundsException e9) {
            e = e9;
            fileInputStream2 = null;
        } catch (NoSuchAlgorithmException e10) {
            e = e10;
            fileInputStream2 = null;
        } catch (Throwable th2) {
            th = th2;
            if (fileInputStream3 != null) {
                try {
                    fileInputStream3.close();
                } catch (IOException unused3) {
                    abd.b(TAG, "Close FileInputStream failed!");
                }
            }
            throw th;
        }
        return str3;
    }

    private static String a(byte[] bArr) {
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] cArr2 = new char[bArr.length * 2];
        int i = 0;
        for (byte b : bArr) {
            int i2 = i + 1;
            cArr2[i] = cArr[(b >>> 4) & 15];
            i += 2;
            cArr2[i2] = cArr[b & BaseType.Obj];
        }
        return new String(cArr2);
    }
}
