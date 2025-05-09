package com.huawei.hms.framework.common;

import android.content.Context;
import android.text.TextUtils;
import com.google.flatbuffers.reflection.BaseType;
import com.huawei.libcore.io.ExternalStorageFile;
import com.huawei.libcore.io.ExternalStorageFileInputStream;
import com.huawei.libcore.io.ExternalStorageFileOutputStream;
import com.huawei.libcore.io.ExternalStorageRandomAccessFile;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

/* loaded from: classes4.dex */
public class CreateFileUtil {
    private static final String EXTERNAL_FILE_NAME = "com.huawei.libcore.io.ExternalStorageFile";
    private static final String EXTERNAL_INPUTSTREAM_NAME = "com.huawei.libcore.io.ExternalStorageFileInputStream";
    private static final String EXTERNAL_OUTPUTSTREAM_NAME = "com.huawei.libcore.io.ExternalStorageFileOutputStream";
    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private static final String RANDOM_ACCESS_FILE_NAME = "com.huawei.libcore.io.ExternalStorageRandomAccessFile";
    private static final String TAG = "CreateFileUtil";

    public static String getCacheDirPath(Context context) {
        return context == null ? "" : ContextCompat.getProtectedStorageContext(context).getCacheDir().getPath();
    }

    public static File newFile(String str) {
        if (str == null) {
            return null;
        }
        if (EmuiUtil.isUpPVersion() && ReflectionUtils.checkCompatible(EXTERNAL_FILE_NAME)) {
            return new ExternalStorageFile(str);
        }
        return new File(str);
    }

    public static File newSafeFile(String str) {
        if (str == null) {
            return null;
        }
        try {
            File newFile = newFile(str);
            return !newFile.exists() ? new File(str) : newFile;
        } catch (RuntimeException unused) {
            Logger.w(TAG, "newFile is runtimeException");
            return new File(str);
        } catch (Throwable unused2) {
            Logger.w(TAG, "newFile is Throwable");
            return new File(str);
        }
    }

    public static String getCanonicalPath(String str) {
        try {
            return newFile(str).getCanonicalPath();
        } catch (IOException e) {
            Logger.w(TAG, "the canonicalPath has IOException", e);
            return str;
        } catch (SecurityException e2) {
            Logger.w(TAG, "the canonicalPath has securityException", e2);
            return str;
        } catch (Exception e3) {
            Logger.w(TAG, "the canonicalPath has other Exception", e3);
            return str;
        }
    }

    public static FileInputStream newFileInputStream(String str) throws FileNotFoundException {
        if (str == null) {
            Logger.w(TAG, "newFileInputStream  file is null");
            throw new FileNotFoundException("file is null");
        }
        if (EmuiUtil.isUpPVersion() && ReflectionUtils.checkCompatible(EXTERNAL_INPUTSTREAM_NAME)) {
            return new ExternalStorageFileInputStream(str);
        }
        return new FileInputStream(str);
    }

    public static FileInputStream newSafeFileInputStream(String str) throws FileNotFoundException {
        try {
            return newFileInputStream(str);
        } catch (FileNotFoundException unused) {
            Logger.w(TAG, "newFileInputStream is fileNotFoundException");
            return new FileInputStream(str);
        } catch (RuntimeException unused2) {
            Logger.w(TAG, "newFileInputStream is runtimeException");
            return new FileInputStream(str);
        } catch (Throwable unused3) {
            Logger.w(TAG, "newFileInputStream is Throwable");
            return new FileInputStream(str);
        }
    }

    public static FileOutputStream newFileOutputStream(File file) throws FileNotFoundException {
        if (file == null) {
            Logger.e(TAG, "newFileOutputStream  file is null");
            throw new FileNotFoundException("file is null");
        }
        if (EmuiUtil.isUpPVersion() && ReflectionUtils.checkCompatible(EXTERNAL_OUTPUTSTREAM_NAME)) {
            return new ExternalStorageFileOutputStream(file);
        }
        return new FileOutputStream(file);
    }

    public static FileOutputStream newSafeFileOutputStream(File file) throws FileNotFoundException {
        try {
            return newFileOutputStream(file);
        } catch (FileNotFoundException unused) {
            Logger.w(TAG, "newFileOutputStream is fileNotFoundException");
            return new FileOutputStream(file);
        } catch (RuntimeException unused2) {
            Logger.w(TAG, "newFileOutputStream is runtimeException");
            return new FileOutputStream(file);
        } catch (Throwable unused3) {
            Logger.w(TAG, "newFileOutputStream is Throwable");
            return new FileOutputStream(file);
        }
    }

    public static RandomAccessFile newRandomAccessFile(String str, String str2) throws FileNotFoundException {
        if (str == null) {
            Logger.w(TAG, "newFileOutputStream  file is null");
            throw new FileNotFoundException("file is null");
        }
        if (EmuiUtil.isUpPVersion() && ReflectionUtils.checkCompatible(RANDOM_ACCESS_FILE_NAME)) {
            return new ExternalStorageRandomAccessFile(str, str2);
        }
        return new RandomAccessFile(str, str2);
    }

    public static RandomAccessFile newSafeRandomAccessFile(String str, String str2) throws FileNotFoundException {
        if (str == null) {
            Logger.w(TAG, "newRandomAccessFile  file is null");
            throw new FileNotFoundException("file is null");
        }
        try {
            return newRandomAccessFile(str, str2);
        } catch (FileNotFoundException unused) {
            Logger.w(TAG, "newRandomAccessFile is fileNotFoundException");
            return new RandomAccessFile(str, str2);
        } catch (RuntimeException unused2) {
            Logger.w(TAG, "newRandomAccessFile is runtimeException");
            return new RandomAccessFile(str, str2);
        } catch (Throwable unused3) {
            Logger.w(TAG, "newRandomAccessFile is Throwable");
            return new RandomAccessFile(str, str2);
        }
    }

    public static void deleteSecure(File file) {
        if (file == null || !file.exists() || file.delete()) {
            return;
        }
        Logger.w(TAG, "deleteSecure exception");
    }

    public static void deleteSecure(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        deleteSecure(newFile(str));
    }

    @Deprecated
    public static boolean isPVersion() {
        return EmuiUtil.isUpPVersion();
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x002f, code lost:
    
        if (r10 != null) goto L66;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0075, code lost:
    
        if (r10 == null) goto L56;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0082 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1, types: [java.io.FileInputStream] */
    /* JADX WARN: Type inference failed for: r2v2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String getFileHashData(java.lang.String r10, java.lang.String r11) {
        /*
            java.lang.String r0 = "Close FileInputStream failed!"
            java.lang.String r1 = "CreateFileUtil"
            r2 = 0
            java.security.MessageDigest r11 = java.security.MessageDigest.getInstance(r11)     // Catch: java.lang.Throwable -> L3f java.lang.IndexOutOfBoundsException -> L41 java.lang.IllegalArgumentException -> L4c java.io.IOException -> L57 java.io.FileNotFoundException -> L62 java.security.NoSuchAlgorithmException -> L6d
            java.io.FileInputStream r10 = newSafeFileInputStream(r10)     // Catch: java.lang.Throwable -> L3f java.lang.IndexOutOfBoundsException -> L41 java.lang.IllegalArgumentException -> L4c java.io.IOException -> L57 java.io.FileNotFoundException -> L62 java.security.NoSuchAlgorithmException -> L6d
            r3 = 1024(0x400, float:1.435E-42)
            byte[] r3 = new byte[r3]     // Catch: java.lang.Throwable -> L32 java.lang.IndexOutOfBoundsException -> L35 java.lang.IllegalArgumentException -> L37 java.io.IOException -> L39 java.io.FileNotFoundException -> L3b java.security.NoSuchAlgorithmException -> L3d
            r4 = 0
            r6 = r4
        L14:
            int r8 = r10.read(r3)     // Catch: java.lang.Throwable -> L32 java.lang.IndexOutOfBoundsException -> L35 java.lang.IllegalArgumentException -> L37 java.io.IOException -> L39 java.io.FileNotFoundException -> L3b java.security.NoSuchAlgorithmException -> L3d
            r9 = -1
            if (r8 == r9) goto L22
            r9 = 0
            r11.update(r3, r9, r8)     // Catch: java.lang.Throwable -> L32 java.lang.IndexOutOfBoundsException -> L35 java.lang.IllegalArgumentException -> L37 java.io.IOException -> L39 java.io.FileNotFoundException -> L3b java.security.NoSuchAlgorithmException -> L3d
            long r8 = (long) r8     // Catch: java.lang.Throwable -> L32 java.lang.IndexOutOfBoundsException -> L35 java.lang.IllegalArgumentException -> L37 java.io.IOException -> L39 java.io.FileNotFoundException -> L3b java.security.NoSuchAlgorithmException -> L3d
            long r6 = r6 + r8
            goto L14
        L22:
            int r3 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r3 <= 0) goto L2f
            byte[] r11 = r11.digest()     // Catch: java.lang.Throwable -> L32 java.lang.IndexOutOfBoundsException -> L35 java.lang.IllegalArgumentException -> L37 java.io.IOException -> L39 java.io.FileNotFoundException -> L3b java.security.NoSuchAlgorithmException -> L3d
            java.lang.String r11 = byteArrayToHex(r11)     // Catch: java.lang.Throwable -> L32 java.lang.IndexOutOfBoundsException -> L35 java.lang.IllegalArgumentException -> L37 java.io.IOException -> L39 java.io.FileNotFoundException -> L3b java.security.NoSuchAlgorithmException -> L3d
            r2 = r11
        L2f:
            if (r10 == 0) goto L7e
            goto L77
        L32:
            r11 = move-exception
            r2 = r10
            goto L7f
        L35:
            r11 = move-exception
            goto L44
        L37:
            r11 = move-exception
            goto L4f
        L39:
            r11 = move-exception
            goto L5a
        L3b:
            r11 = move-exception
            goto L65
        L3d:
            r11 = move-exception
            goto L70
        L3f:
            r10 = move-exception
            goto L80
        L41:
            r10 = move-exception
            r11 = r10
            r10 = r2
        L44:
            java.lang.String r3 = "getFileHashData IndexOutOfBoundsException"
            com.huawei.hms.framework.common.Logger.e(r1, r3, r11)     // Catch: java.lang.Throwable -> L32
            if (r10 == 0) goto L7e
            goto L77
        L4c:
            r10 = move-exception
            r11 = r10
            r10 = r2
        L4f:
            java.lang.String r3 = "getFileHashData IllegalArgumentException"
            com.huawei.hms.framework.common.Logger.e(r1, r3, r11)     // Catch: java.lang.Throwable -> L32
            if (r10 == 0) goto L7e
            goto L77
        L57:
            r10 = move-exception
            r11 = r10
            r10 = r2
        L5a:
            java.lang.String r3 = "getFileHashData IOException"
            com.huawei.hms.framework.common.Logger.e(r1, r3, r11)     // Catch: java.lang.Throwable -> L32
            if (r10 == 0) goto L7e
            goto L77
        L62:
            r10 = move-exception
            r11 = r10
            r10 = r2
        L65:
            java.lang.String r3 = "getFileHashData FileNotFoundException"
            com.huawei.hms.framework.common.Logger.e(r1, r3, r11)     // Catch: java.lang.Throwable -> L32
            if (r10 == 0) goto L7e
            goto L77
        L6d:
            r10 = move-exception
            r11 = r10
            r10 = r2
        L70:
            java.lang.String r3 = "getFileHashData NoSuchAlgorithmException"
            com.huawei.hms.framework.common.Logger.e(r1, r3, r11)     // Catch: java.lang.Throwable -> L32
            if (r10 == 0) goto L7e
        L77:
            r10.close()     // Catch: java.io.IOException -> L7b
            goto L7e
        L7b:
            com.huawei.hms.framework.common.Logger.e(r1, r0)
        L7e:
            return r2
        L7f:
            r10 = r11
        L80:
            if (r2 == 0) goto L89
            r2.close()     // Catch: java.io.IOException -> L86
            goto L89
        L86:
            com.huawei.hms.framework.common.Logger.e(r1, r0)
        L89:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.framework.common.CreateFileUtil.getFileHashData(java.lang.String, java.lang.String):java.lang.String");
    }

    public static String byteArrayToHex(byte[] bArr) {
        char[] cArr = new char[bArr.length * 2];
        int i = 0;
        for (byte b : bArr) {
            int i2 = i + 1;
            char[] cArr2 = HEX_DIGITS;
            cArr[i] = cArr2[(b >>> 4) & 15];
            i += 2;
            cArr[i2] = cArr2[b & BaseType.Obj];
        }
        return new String(cArr);
    }
}
