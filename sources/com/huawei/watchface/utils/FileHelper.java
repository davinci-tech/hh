package com.huawei.watchface.utils;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import com.huawei.operation.utils.Constants;
import com.huawei.secure.android.common.encrypt.hash.FileSHA256;
import com.huawei.watchface.mvp.model.crypt.PversionSdUtils;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/* loaded from: classes7.dex */
public class FileHelper {
    private FileHelper() {
    }

    private static void a(File file, boolean z, boolean z2) throws IOException, SecurityException {
        if (z && file.isDirectory()) {
            HwLog.i("FileHelper", "initializeDestFile deleteDestFile as isDeleteFile :" + file.delete());
        }
        if (z2 && file.exists()) {
            HwLog.i("FileHelper", "initializeDestFile deleteDestFile isDeleteFile :" + file.delete());
        }
        if (file.getParentFile() != null && !file.getParentFile().exists()) {
            HwLog.i("FileHelper", "initializeDestFile isMkdirFile :" + file.getParentFile().mkdirs());
        }
        if (file.isFile()) {
            return;
        }
        boolean createNewFile = file.createNewFile();
        HwSfpPolicyManagerHelper.setDefaultCeLabel(file);
        HwLog.i("FileHelper", "initializeDestFile isCreateNewFile :" + createNewFile);
    }

    public static int a(String str, String str2) {
        ZipInputStream zipInputStream;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            HwLog.w("FileHelper", "unzip filename or destDirectory is empty.");
            return -1;
        }
        String filterFilePath = CommonUtils.filterFilePath(str);
        if (TextUtils.isEmpty(filterFilePath)) {
            HwLog.w("FileHelper", "unzip safeFilePath is empty.");
            return -1;
        }
        ZipInputStream zipInputStream2 = null;
        try {
            try {
                zipInputStream = new ZipInputStream(new FileInputStream(filterFilePath));
            } catch (Throwable th) {
                th = th;
                zipInputStream = null;
            }
        } catch (IOException unused) {
        }
        try {
            int a2 = a(zipInputStream, str2, (String) null, true);
            a(zipInputStream);
            return a2;
        } catch (IOException unused2) {
            zipInputStream2 = zipInputStream;
            HwLog.e("FileHelper", "unzip IOException.");
            f(filterFilePath);
            a(zipInputStream2);
            return -1;
        } catch (Throwable th2) {
            th = th2;
            a(zipInputStream);
            throw th;
        }
    }

    public static int a(ZipInputStream zipInputStream, String str, String str2, boolean z) throws IOException {
        if (zipInputStream == null || TextUtils.isEmpty(str)) {
            HwLog.w("FileHelper", "unzip zipInputStream or destDirectory is empty.");
            return -1;
        }
        File g = g(str);
        boolean z2 = !TextUtils.isEmpty(str2);
        try {
            ZipEntry nextEntry = zipInputStream.getNextEntry();
            byte[] bArr = new byte[2048];
            int i = 0;
            int i2 = 0;
            while (true) {
                if (nextEntry == null) {
                    break;
                }
                if (z2 && !str2.equals(nextEntry.getName())) {
                    nextEntry = zipInputStream.getNextEntry();
                } else {
                    File a2 = a(nextEntry, g);
                    if (nextEntry.isDirectory()) {
                        HwLog.d("FileHelper", "unzip zipEntry isDirectoryMade :" + a2.mkdirs());
                        nextEntry = zipInputStream.getNextEntry();
                    } else {
                        a(a2, false, true);
                        i2 = a(a2, zipInputStream, i2, bArr);
                        if (i2 > 52428800) {
                            HwLog.e("FileHelper", "unzip this zip file too big, unzip failure.");
                            throw new IOException("unzip this zip file too big.");
                        }
                        i++;
                        if (z2) {
                            zipInputStream.closeEntry();
                            break;
                        }
                        nextEntry = zipInputStream.getNextEntry();
                    }
                }
            }
            HwLog.i("FileHelper", "unzip accomplished amount:" + i);
            return i;
        } catch (IOException e) {
            if (z) {
                a(z2, (File) null, g);
            }
            throw e;
        }
    }

    private static void f(String str) {
        File file = new File(str);
        if (file.exists()) {
            HwLog.i("FileHelper", "deleteUnzipSourceFile zipFile:" + file.delete());
        }
    }

    private static void a(boolean z, File file, File file2) {
        if (z) {
            if (file != null && file.exists() && file.isFile()) {
                HwLog.i("FileHelper", "deleteUnzipFailFile isDelete:" + a(file));
                return;
            }
            return;
        }
        if (file2.exists() && file2.isDirectory()) {
            HwLog.i("FileHelper", "deleteUnzipFailFile dir isDelete:" + a(file2));
        }
    }

    private static File g(String str) throws IOException {
        String filterFilePath = CommonUtils.filterFilePath(str);
        if (TextUtils.isEmpty(filterFilePath)) {
            HwLog.w("FileHelper", "unzip dest dir path is empty.");
            throw new IOException("unzip this dest dir path is illegal.");
        }
        return new File(filterFilePath);
    }

    private static File a(ZipEntry zipEntry, File file) throws IOException {
        String a2 = a(zipEntry.getName(), file);
        HwLog.iBetaLog("FileHelper", "getExtractFile:" + a2);
        String filterFilePath = CommonUtils.filterFilePath(a2);
        if (TextUtils.isEmpty(filterFilePath)) {
            HwLog.w("FileHelper", "unzip this zip file dest path is illegal, unzip failure.");
            throw new IOException("unzip this zip file dest path is illegal.");
        }
        return new File(filterFilePath);
    }

    public static String sha256File(String str) {
        try {
            String fileSHA256Encrypt = FileSHA256.fileSHA256Encrypt(new File(str));
            if (TextUtils.isEmpty(fileSHA256Encrypt)) {
                return "";
            }
            return fileSHA256Encrypt.toUpperCase(Locale.US).trim();
        } catch (Exception unused) {
            HwLog.e("FileHelper", "sha256File Exception.");
            return "";
        }
    }

    private static int a(File file, ZipInputStream zipInputStream, int i, byte[] bArr) throws IOException {
        int i2;
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        while (true) {
            try {
                try {
                    int read = zipInputStream.read(bArr);
                    if (read <= -1 || (i2 = i + read) > 52428800) {
                        break;
                    }
                    fileOutputStream.write(bArr, 0, read);
                    i = i2;
                } catch (IOException e) {
                    HwLog.e("FileHelper", "unzipSingleFile IOException.");
                    throw e;
                }
            } finally {
                a(fileOutputStream);
            }
        }
        return i;
    }

    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
                HwLog.e("FileHelper", "closeStream IOException.");
            }
        }
    }

    public static boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            HwLog.w("FileHelper", "deleteFile path is empty");
            return false;
        }
        return a(PversionSdUtils.getFile(str));
    }

    public static boolean a(File file) {
        File[] listFiles;
        boolean z = false;
        if (file == null) {
            HwLog.w("FileHelper", "file is null");
            return false;
        }
        if (file.isDirectory() && (listFiles = file.listFiles()) != null) {
            for (File file2 : listFiles) {
                a(file2);
            }
        }
        try {
            z = file.delete();
            if (!z) {
                StringBuilder sb = new StringBuilder();
                sb.append("delete fail, ");
                sb.append(file.isDirectory() ? "dir is " : "file is ");
                sb.append(file.getName());
                HwLog.w("FileHelper", sb.toString());
            }
        } catch (SecurityException unused) {
            HwLog.e("FileHelper", "delete file SecurityException, " + file.getName());
        }
        return z;
    }

    private static String a(String str, File file) throws IOException {
        String canonicalPath = new File(file, str).getCanonicalPath();
        if (canonicalPath.startsWith(file.getCanonicalPath())) {
            return canonicalPath;
        }
        throw new IOException("File is outside extraction target directory.");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v1, types: [java.lang.CharSequence, java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v12, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r5v18 */
    /* JADX WARN: Type inference failed for: r5v21, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r5v23 */
    /* JADX WARN: Type inference failed for: r5v24 */
    /* JADX WARN: Type inference failed for: r5v25 */
    /* JADX WARN: Type inference failed for: r5v3 */
    /* JADX WARN: Type inference failed for: r5v8 */
    /* JADX WARN: Type inference failed for: r5v9, types: [java.io.Closeable] */
    public static void copyFileToParsingDir(String str, String str2) {
        Throwable th;
        IOException e;
        FileInputStream fileInputStream;
        FileNotFoundException e2;
        ?? fileOutputStream;
        ?? filterFilePath = CommonUtils.filterFilePath(str);
        String filterFilePath2 = CommonUtils.filterFilePath(str2);
        if (TextUtils.isEmpty(filterFilePath)) {
            HwLog.w("FileHelper", "copyFileToParsingDirectory oldWatchFacePackagePath is empty.");
            return;
        }
        if (TextUtils.isEmpty(filterFilePath2)) {
            HwLog.w("FileHelper", "copyFileToParsingDirectory newWatchFacePackagePath is empty.");
            return;
        }
        FileInputStream fileInputStream2 = null;
        try {
            try {
                if (new File((String) filterFilePath).exists()) {
                    FileInputStream fileInputStream3 = new FileInputStream((String) filterFilePath);
                    try {
                        fileOutputStream = new FileOutputStream(filterFilePath2);
                    } catch (FileNotFoundException e3) {
                        e2 = e3;
                    } catch (IOException e4) {
                        e = e4;
                    } catch (Throwable th2) {
                        th = th2;
                    }
                    try {
                        byte[] bArr = new byte[2048];
                        for (int read = fileInputStream3.read(bArr); read != -1; read = fileInputStream3.read(bArr)) {
                            fileOutputStream.write(bArr, 0, read);
                        }
                        fileInputStream2 = fileInputStream3;
                        fileInputStream = fileOutputStream;
                    } catch (FileNotFoundException e5) {
                        e2 = e5;
                        fileInputStream2 = fileOutputStream;
                        fileInputStream = fileInputStream2;
                        fileInputStream2 = fileInputStream3;
                        HwLog.e("FileHelper", "copyFileToParsingDir FileNotFoundException." + HwLog.printException((Exception) e2));
                        filterFilePath = fileInputStream;
                        a(fileInputStream2);
                        a((Closeable) filterFilePath);
                    } catch (IOException e6) {
                        e = e6;
                        fileInputStream2 = fileOutputStream;
                        fileInputStream = fileInputStream2;
                        fileInputStream2 = fileInputStream3;
                        HwLog.e("FileHelper", "copyFileToParsingDir IOException." + HwLog.printException((Exception) e));
                        filterFilePath = fileInputStream;
                        a(fileInputStream2);
                        a((Closeable) filterFilePath);
                    } catch (Throwable th3) {
                        th = th3;
                        fileInputStream2 = fileOutputStream;
                        filterFilePath = fileInputStream2;
                        fileInputStream2 = fileInputStream3;
                        a(fileInputStream2);
                        a((Closeable) filterFilePath);
                        throw th;
                    }
                } else {
                    fileInputStream = null;
                }
                try {
                    HwSfpPolicyManagerHelper.setDefaultCeLabel(filterFilePath2);
                    filterFilePath = fileInputStream;
                } catch (FileNotFoundException e7) {
                    e2 = e7;
                    HwLog.e("FileHelper", "copyFileToParsingDir FileNotFoundException." + HwLog.printException((Exception) e2));
                    filterFilePath = fileInputStream;
                    a(fileInputStream2);
                    a((Closeable) filterFilePath);
                } catch (IOException e8) {
                    e = e8;
                    HwLog.e("FileHelper", "copyFileToParsingDir IOException." + HwLog.printException((Exception) e));
                    filterFilePath = fileInputStream;
                    a(fileInputStream2);
                    a((Closeable) filterFilePath);
                }
            } catch (Throwable th4) {
                th = th4;
            }
        } catch (FileNotFoundException e9) {
            e2 = e9;
            fileInputStream = null;
        } catch (IOException e10) {
            e = e10;
            fileInputStream = null;
        } catch (Throwable th5) {
            th = th5;
            filterFilePath = 0;
        }
        a(fileInputStream2);
        a((Closeable) filterFilePath);
    }

    /* JADX WARN: Not initialized variable reg: 4, insn: 0x00cc: MOVE (r2 I:??[OBJECT, ARRAY]) = (r4 I:??[OBJECT, ARRAY]), block:B:42:0x00cb */
    public static int b(String str, String str2) {
        FileOutputStream fileOutputStream;
        FileInputStream fileInputStream;
        ZipInputStream zipInputStream;
        ZipInputStream zipInputStream2;
        ZipInputStream zipInputStream3 = null;
        FileOutputStream fileOutputStream2 = null;
        zipInputStream3 = null;
        try {
            try {
                fileInputStream = new FileInputStream(str);
            } catch (Throwable th) {
                th = th;
                fileOutputStream = null;
                zipInputStream3 = zipInputStream2;
            }
            try {
                byte[] bArr = new byte[2048];
                zipInputStream = new ZipInputStream(fileInputStream);
                try {
                    ZipEntry nextEntry = zipInputStream.getNextEntry();
                    int i = 0;
                    int i2 = 0;
                    while (nextEntry != null) {
                        String a2 = a(nextEntry.getName(), new File(str2));
                        HwLog.iBetaLog("FileHelper", "sanitzeFilename is : " + a2);
                        File file = new File(a2);
                        if (nextEntry.isDirectory()) {
                            HwLog.i("FileHelper", "mkdirFileSuc is = " + file.mkdirs());
                            nextEntry = zipInputStream.getNextEntry();
                        } else {
                            g(file);
                            fileOutputStream2 = openOutputStream(file, false);
                            i2 = a(i2, zipInputStream, fileOutputStream2, bArr);
                            zipInputStream.closeEntry();
                            nextEntry = zipInputStream.getNextEntry();
                            i++;
                            if (i2 > 52428800) {
                                HwLog.i("FileHelper", "this zip file too big ,unzip failure");
                                a(zipInputStream, fileInputStream, fileOutputStream2);
                                return -1;
                            }
                        }
                    }
                    HwLog.i("FileHelper", "unzip is complish" + i);
                    a(zipInputStream, fileInputStream, fileOutputStream2);
                    return i;
                } catch (IOException e) {
                    e = e;
                    HwLog.i("FileHelper", "unzipForLocal IOException: " + HwLog.printException((Exception) e));
                    a(zipInputStream, fileInputStream, (FileOutputStream) null);
                    return -1;
                }
            } catch (IOException e2) {
                e = e2;
                zipInputStream = null;
            } catch (Throwable th2) {
                th = th2;
                fileOutputStream = null;
                a(zipInputStream3, fileInputStream, fileOutputStream);
                throw th;
            }
        } catch (IOException e3) {
            e = e3;
            fileInputStream = null;
            zipInputStream = null;
        } catch (Throwable th3) {
            th = th3;
            fileOutputStream = null;
            fileInputStream = null;
        }
    }

    private static void g(File file) throws IOException {
        if (file.exists()) {
            HwLog.i("FileHelper", "deleteFile is = " + file.delete());
        }
        if (!file.getParentFile().exists()) {
            HwLog.i("FileHelper", "mkdirFile is = " + file.getParentFile().mkdirs());
        }
        if (file.isFile()) {
            return;
        }
        boolean createNewFile = file.createNewFile();
        HwSfpPolicyManagerHelper.setDefaultCeLabel(file);
        HwLog.i("FileHelper", "createNewFile is = " + createNewFile);
    }

    private static int a(int i, ZipInputStream zipInputStream, FileOutputStream fileOutputStream, byte[] bArr) throws IOException {
        int read;
        int i2 = i;
        while (i + 2048 <= 52428800 && (read = zipInputStream.read(bArr, 0, 2048)) > -1) {
            fileOutputStream.write(bArr, 0, read);
            i2 += read;
        }
        return i2;
    }

    private static void a(ZipInputStream zipInputStream, InputStream inputStream, FileOutputStream fileOutputStream) {
        a(zipInputStream);
        a(inputStream);
        a(fileOutputStream);
    }

    public static FileOutputStream openOutputStream(File file, boolean z) throws IOException {
        if (file.exists()) {
            if (file.isDirectory()) {
                throw new IOException("File '" + file + "' exists but is a directory");
            }
            if (!file.canWrite()) {
                throw new IOException("File '" + file + "' cannot be written to");
            }
        } else {
            File parentFile = file.getParentFile();
            if (parentFile != null && !parentFile.mkdirs() && !parentFile.isDirectory()) {
                throw new IOException("Directory '" + parentFile + "' could not be created");
            }
        }
        return new FileOutputStream(file, z);
    }

    public static FileInputStream b(File file) throws IOException {
        if (file != null && file.exists()) {
            if (file.isDirectory()) {
                throw new IOException("File '" + file + "' exists but is a directory");
            }
            if (!file.canRead()) {
                throw new IOException("File '" + file + "' cannot be read");
            }
            return new FileInputStream(file);
        }
        throw new FileNotFoundException("File '" + file + "' does not exist");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v1 */
    /* JADX WARN: Type inference failed for: r12v11 */
    /* JADX WARN: Type inference failed for: r12v12 */
    /* JADX WARN: Type inference failed for: r12v3, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4, types: [java.util.zip.ZipInputStream] */
    public static int a(Context context, String str, Uri uri, String str2) {
        ?? r12;
        FileOutputStream fileOutputStream;
        FileInputStream fileInputStream;
        ZipInputStream zipInputStream;
        if (b(str) || b(str2)) {
            return -1;
        }
        ?? r1 = 0;
        r1 = null;
        r1 = null;
        r1 = null;
        r1 = 0;
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
                if (Build.VERSION.SDK_INT > 28) {
                    ParcelFileDescriptor openFileDescriptor = context.getContentResolver().openFileDescriptor(uri, "r");
                    fileInputStream = openFileDescriptor != null ? new FileInputStream(openFileDescriptor.getFileDescriptor()) : null;
                } else {
                    fileInputStream = new FileInputStream(str);
                }
            } catch (Throwable th) {
                th = th;
                r1 = uri;
                fileOutputStream = null;
                r12 = str;
            }
            try {
                if (fileInputStream == null) {
                    HwLog.e("FileHelper", "unzipForAndroidQ() zipFileStream is null.");
                    a((ZipInputStream) null, fileInputStream, (FileOutputStream) null);
                    return -1;
                }
                byte[] bArr = new byte[2048];
                zipInputStream = new ZipInputStream(fileInputStream);
                try {
                    ZipEntry nextEntry = zipInputStream.getNextEntry();
                    int i = 0;
                    int i2 = 0;
                    while (nextEntry != null) {
                        String c = c(nextEntry.getName(), str2);
                        HwLog.iBetaLog("FileHelper", "unzipForAndroidQ() sanitzeFilename is: " + c);
                        File file = new File(c);
                        if (nextEntry.isDirectory()) {
                            HwLog.i("FileHelper", "unzipForAndroidQ() mkdirFileSuc: " + file.mkdirs());
                            nextEntry = zipInputStream.getNextEntry();
                        } else {
                            g(file);
                            fileOutputStream2 = openOutputStream(file, false);
                            i2 = a(i2, zipInputStream, fileOutputStream2, bArr);
                            zipInputStream.closeEntry();
                            nextEntry = zipInputStream.getNextEntry();
                            i++;
                            if (i2 > 52428800) {
                                HwLog.i("FileHelper", "unzipForAndroidQ() this zip file too big ,unzip failure");
                                a(zipInputStream, fileInputStream, fileOutputStream2);
                                return -1;
                            }
                        }
                    }
                    a(zipInputStream, fileInputStream, fileOutputStream2);
                    return i;
                } catch (IOException unused) {
                    HwLog.e("FileHelper", "unzipForAndroidQ() IOException occured.");
                    a(zipInputStream, fileInputStream, fileOutputStream2);
                    return -1;
                }
            } catch (IOException unused2) {
                zipInputStream = null;
            } catch (Throwable th2) {
                th = th2;
                fileOutputStream = null;
                r12 = fileInputStream;
                a((ZipInputStream) r1, (InputStream) r12, fileOutputStream);
                throw th;
            }
        } catch (IOException unused3) {
            fileInputStream = null;
            zipInputStream = null;
        } catch (Throwable th3) {
            th = th3;
            r12 = 0;
            fileOutputStream = null;
        }
    }

    private static String c(String str, String str2) throws IOException {
        String canonicalPath = new File(str2, str).getCanonicalPath();
        if (canonicalPath.startsWith(new File(str2).getCanonicalPath())) {
            return canonicalPath;
        }
        throw new IllegalStateException("File is outside extraction target directory.");
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0059 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean b(java.lang.String r5) {
        /*
            boolean r0 = android.text.TextUtils.isEmpty(r5)
            r1 = 1
            java.lang.String r2 = "FileHelper"
            if (r0 == 0) goto Lf
            java.lang.String r5 = "isIllegalPath path is empty"
            com.huawei.watchface.utils.HwLog.e(r2, r5)
            return r1
        Lf:
            r0 = 0
            java.io.File r3 = new java.io.File     // Catch: java.lang.Exception -> L1f java.io.IOException -> L36
            r3.<init>(r5)     // Catch: java.lang.Exception -> L1f java.io.IOException -> L36
            java.lang.String r0 = r3.getCanonicalPath()     // Catch: java.lang.Exception -> L1f java.io.IOException -> L36
            boolean r5 = com.huawei.secure.android.common.util.FileUtil.filePathIsValidNew(r0)     // Catch: java.lang.Exception -> L1f java.io.IOException -> L36
            r5 = r5 ^ r1
            goto L4d
        L1f:
            r5 = move-exception
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "isIllegalPath Exception "
            r3.<init>(r4)
            java.lang.String r5 = com.huawei.watchface.utils.HwLog.printException(r5)
            r3.append(r5)
            java.lang.String r5 = r3.toString()
            com.huawei.watchface.utils.HwLog.e(r2, r5)
            goto L4c
        L36:
            r5 = move-exception
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "isIllegalPath IOException "
            r3.<init>(r4)
            java.lang.String r5 = com.huawei.watchface.utils.HwLog.printException(r5)
            r3.append(r5)
            java.lang.String r5 = r3.toString()
            com.huawei.watchface.utils.HwLog.e(r2, r5)
        L4c:
            r5 = r1
        L4d:
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L59
            java.lang.String r5 = "isIllegalPath canonicalPath is empty"
            com.huawei.watchface.utils.HwLog.e(r2, r5)
            return r1
        L59:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.watchface.utils.FileHelper.b(java.lang.String):boolean");
    }

    public static File a(String... strArr) {
        if (strArr == null) {
            throw new NullPointerException("names must not be null.");
        }
        File file = null;
        for (String str : strArr) {
            if (file == null) {
                file = new File(str);
            } else {
                file = new File(file, str);
            }
        }
        return file;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v11, types: [java.io.Closeable[]] */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.io.Closeable[]] */
    /* JADX WARN: Type inference failed for: r0v6, types: [java.io.Closeable[]] */
    /* JADX WARN: Type inference failed for: r0v9, types: [java.io.Closeable[]] */
    /* JADX WARN: Type inference failed for: r10v0, types: [java.io.File] */
    /* JADX WARN: Type inference failed for: r10v2 */
    /* JADX WARN: Type inference failed for: r10v3 */
    /* JADX WARN: Type inference failed for: r10v5 */
    /* JADX WARN: Type inference failed for: r10v7 */
    /* JADX WARN: Type inference failed for: r10v8 */
    /* JADX WARN: Type inference failed for: r10v9, types: [java.io.FileInputStream] */
    public static byte[] c(File file) {
        Object obj;
        ByteArrayOutputStream byteArrayOutputStream;
        byte[] bArr = null;
        try {
            try {
                file = PversionSdUtils.b(file);
            } catch (Throwable th) {
                th = th;
            }
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    byte[] bArr2 = new byte[1024];
                    while (true) {
                        int read = file.read(bArr2);
                        if (read == -1) {
                            break;
                        }
                        byteArrayOutputStream.write(bArr2, 0, read);
                    }
                    bArr = byteArrayOutputStream.toByteArray();
                    PversionSdUtils.a((Closeable[]) new Closeable[]{file, byteArrayOutputStream});
                } catch (FileNotFoundException e) {
                    e = e;
                    HwLog.e("FileHelper", "fileToByte error " + HwLog.printException((Exception) e));
                    PversionSdUtils.a((Closeable[]) new Closeable[]{file, byteArrayOutputStream});
                    return bArr;
                } catch (IOException e2) {
                    e = e2;
                    HwLog.e("FileHelper", "fileToByte error " + HwLog.printException((Exception) e));
                    PversionSdUtils.a((Closeable[]) new Closeable[]{file, byteArrayOutputStream});
                    return bArr;
                }
            } catch (FileNotFoundException e3) {
                e = e3;
                byteArrayOutputStream = null;
            } catch (IOException e4) {
                e = e4;
                byteArrayOutputStream = null;
            } catch (Throwable th2) {
                th = th2;
                obj = null;
                bArr = file;
                th = th;
                PversionSdUtils.a((Closeable[]) new Closeable[]{bArr, obj});
                throw th;
            }
        } catch (FileNotFoundException e5) {
            e = e5;
            file = 0;
            byteArrayOutputStream = null;
        } catch (IOException e6) {
            e = e6;
            file = 0;
            byteArrayOutputStream = null;
        } catch (Throwable th3) {
            th = th3;
            obj = null;
            PversionSdUtils.a((Closeable[]) new Closeable[]{bArr, obj});
            throw th;
        }
        return bArr;
    }

    public static void a(byte[] bArr, File file) {
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            try {
                fileOutputStream.write(bArr);
                fileOutputStream.close();
            } finally {
            }
        } catch (IOException e) {
            HwLog.e("FileHelper", "fileToByte error " + HwLog.printException((Exception) e));
        }
    }

    public static String c(String str) {
        String str2;
        if (TextUtils.isEmpty(str) || !str.startsWith(Constants.PREFIX_FILE)) {
            return str;
        }
        try {
            str2 = new File(new URL(str).getPath()).getCanonicalPath();
        } catch (MalformedURLException e) {
            HwLog.e("FileHelper", "getCheckFileUrlPath MalformedURLException:" + HwLog.printException((Exception) e));
            str2 = null;
            return Constants.PREFIX_FILE + str2;
        } catch (IOException e2) {
            HwLog.e("FileHelper", "getCheckFileUrlPath IOException:" + HwLog.printException((Exception) e2));
            str2 = null;
            return Constants.PREFIX_FILE + str2;
        } catch (Exception e3) {
            HwLog.e("FileHelper", "getCheckFileUrlPath Exception:" + HwLog.printException(e3));
            str2 = null;
            return Constants.PREFIX_FILE + str2;
        }
        return Constants.PREFIX_FILE + str2;
    }

    public static boolean d(File file) {
        return e(file) && file.isDirectory();
    }

    public static boolean d(String str) {
        return d(e(str));
    }

    public static boolean e(File file) {
        return file != null && file.exists();
    }

    public static File e(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return PversionSdUtils.getFile(str);
    }

    public static String f(File file) {
        if (file == null) {
            return "";
        }
        try {
            return file.getCanonicalPath();
        } catch (IOException e) {
            HwLog.e("FileHelper", "getCanonicalPath error!" + HwLog.printException((Exception) e));
            return "";
        } catch (Exception e2) {
            HwLog.e("FileHelper", "getCanonicalPath Exception!" + HwLog.printException(e2));
            return "";
        }
    }
}
