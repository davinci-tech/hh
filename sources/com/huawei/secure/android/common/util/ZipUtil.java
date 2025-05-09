package com.huawei.secure.android.common.util;

import android.text.TextUtils;
import android.util.Log;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.phoneservice.feedbackcommon.network.FeedbackWebConstants;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

@Deprecated
/* loaded from: classes6.dex */
public class ZipUtil {

    /* renamed from: a, reason: collision with root package name */
    private static final String f8655a = "ZipUtil";
    private static final int b = 104857600;
    private static final int c = 100;
    private static final int d = 6000;
    private static final int e = 4096;
    private static final String[] f = {"\\..", "/..", "..\\", FeedbackWebConstants.INVALID_FILE_NAME_PRE, "./", ".\\.\\", "%00", "..%2F", "..%5C", ".%2F"};

    private static void a(boolean z, File file) {
        if (z && file.exists() && file.isFile()) {
            f(file);
        }
    }

    private static ZipFile b(boolean z, File file) throws IOException {
        if (!z) {
            return new ZipFile(file);
        }
        LogsUtil.i(f8655a, "not a utf8 zip file, use gbk open zip file : " + file);
        return new ZipFile(file, Charset.forName("GBK"));
    }

    private static boolean c(File file) {
        if (file == null) {
            return false;
        }
        if (file.exists()) {
            return file.isFile();
        }
        if (!b(file.getParentFile())) {
            return false;
        }
        try {
            return file.createNewFile();
        } catch (IOException unused) {
            Log.e(f8655a, "createOrExistsFile IOException ");
            return false;
        }
    }

    private static File d(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return c(str);
    }

    private static String e(String str) {
        int lastIndexOf;
        return (TextUtils.isEmpty(str) || (lastIndexOf = str.lastIndexOf(File.separator)) == -1) ? str : str.substring(lastIndexOf + 1);
    }

    private static String f(String str) {
        return str.replaceAll("\\\\", "/");
    }

    private static boolean g(String str) {
        if (TextUtils.isEmpty(str)) {
            Log.e(f8655a, "isContainInvalidStr: name is null");
            return false;
        }
        for (String str2 : f) {
            if (str.toUpperCase(Locale.ROOT).contains(str2)) {
                return true;
            }
        }
        return false;
    }

    private static String h(String str) {
        return (!str.endsWith(File.separator) || str.length() <= File.separator.length()) ? str : str.substring(0, str.length() - File.separator.length());
    }

    @Deprecated
    public static boolean unZip(String str, String str2, boolean z) throws SecurityCommonException {
        return unZip(str, str2, Constants.WEB_VIEW_CACHE_TOTAL_MAX_SIZE, 100, z);
    }

    public static List<File> unZipNew(String str, String str2, boolean z) throws SecurityCommonException {
        return unZipNew(str, str2, Constants.WEB_VIEW_CACHE_TOTAL_MAX_SIZE, 100, z);
    }

    public static List<File> unZipWithFilter(String str, String str2, boolean z, FilenameFilter filenameFilter) throws SecurityCommonException {
        return unZipWithFilter(str, str2, Constants.WEB_VIEW_CACHE_TOTAL_MAX_SIZE, 100, z, filenameFilter, 6000);
    }

    private static void d(File file) {
        if (file == null || file.delete()) {
            return;
        }
        LogsUtil.e(f8655a, "delete file error");
    }

    private static void f(File file) {
        if (file == null) {
            return;
        }
        if (file.isFile()) {
            d(file);
            return;
        }
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles == null || listFiles.length == 0) {
                d(file);
                return;
            }
            for (File file2 : listFiles) {
                f(file2);
            }
            d(file);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00b4  */
    /* JADX WARN: Type inference failed for: r12v0, types: [java.util.zip.ZipInputStream] */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v5, types: [java.util.zip.ZipInputStream] */
    /* JADX WARN: Type inference failed for: r1v6, types: [java.util.zip.ZipInputStream] */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r1v8 */
    @java.lang.Deprecated
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean unZip(java.lang.String r15, java.lang.String r16, long r17, int r19, boolean r20) throws com.huawei.secure.android.common.util.SecurityCommonException {
        /*
            r0 = r20
            r6 = 0
            r1 = r15
            r2 = r16
            r3 = r17
            r5 = r19
            r7 = r19
            boolean r1 = a(r1, r2, r3, r5, r6, r7)
            r2 = 0
            if (r1 != 0) goto L14
            return r2
        L14:
            java.lang.String r1 = h(r16)
            java.util.ArrayList r10 = new java.util.ArrayList
            r10.<init>()
            r3 = 0
            java.io.FileInputStream r11 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L90 java.io.IOException -> L93
            r4 = r15
            r11.<init>(r15)     // Catch: java.lang.Throwable -> L90 java.io.IOException -> L93
            java.util.zip.ZipInputStream r12 = new java.util.zip.ZipInputStream     // Catch: java.lang.Throwable -> L88 java.io.IOException -> L8c
            java.io.BufferedInputStream r4 = new java.io.BufferedInputStream     // Catch: java.lang.Throwable -> L88 java.io.IOException -> L8c
            r4.<init>(r11)     // Catch: java.lang.Throwable -> L88 java.io.IOException -> L8c
            r12.<init>(r4)     // Catch: java.lang.Throwable -> L88 java.io.IOException -> L8c
            r13 = 0
            r5 = r13
        L31:
            java.util.zip.ZipEntry r3 = r12.getNextEntry()     // Catch: java.lang.Throwable -> L82 java.io.IOException -> L85
            if (r3 == 0) goto L7c
            java.lang.String r4 = r3.getName()     // Catch: java.lang.Throwable -> L82 java.io.IOException -> L85
            java.lang.String r7 = "\\\\"
            java.lang.String r8 = "/"
            java.lang.String r4 = r4.replaceAll(r7, r8)     // Catch: java.lang.Throwable -> L82 java.io.IOException -> L85
            boolean r7 = a(r4)     // Catch: java.lang.Throwable -> L82 java.io.IOException -> L85
            if (r7 != 0) goto L4a
            goto L7e
        L4a:
            java.io.File r7 = new java.io.File     // Catch: java.lang.Throwable -> L82 java.io.IOException -> L85
            r7.<init>(r1, r4)     // Catch: java.lang.Throwable -> L82 java.io.IOException -> L85
            if (r0 != 0) goto L5e
            boolean r4 = r7.exists()     // Catch: java.lang.Throwable -> L82 java.io.IOException -> L85
            if (r4 == 0) goto L5e
            boolean r4 = r7.isFile()     // Catch: java.lang.Throwable -> L82 java.io.IOException -> L85
            if (r4 == 0) goto L5e
            goto L31
        L5e:
            a(r0, r7)     // Catch: java.lang.Throwable -> L82 java.io.IOException -> L85
            boolean r3 = a(r3, r7, r10)     // Catch: java.lang.Throwable -> L82 java.io.IOException -> L85
            if (r3 == 0) goto L68
            goto L31
        L68:
            a(r7)     // Catch: java.lang.Throwable -> L82 java.io.IOException -> L85
            r3 = r7
            r4 = r12
            r7 = r17
            r9 = r10
            long r5 = a(r3, r4, r5, r7, r9)     // Catch: java.lang.Throwable -> L82 java.io.IOException -> L85
            r12.closeEntry()     // Catch: java.lang.Throwable -> L82 java.io.IOException -> L85
            int r3 = (r5 > r13 ? 1 : (r5 == r13 ? 0 : -1))
            if (r3 >= 0) goto L31
            goto L7e
        L7c:
            r0 = 1
            r2 = r0
        L7e:
            a(r11, r12)
            goto Lb2
        L82:
            r0 = move-exception
            r3 = r12
            goto L89
        L85:
            r0 = move-exception
            r3 = r12
            goto L8d
        L88:
            r0 = move-exception
        L89:
            r1 = r3
            r3 = r11
            goto Lb9
        L8c:
            r0 = move-exception
        L8d:
            r1 = r3
            r3 = r11
            goto L95
        L90:
            r0 = move-exception
            r1 = r3
            goto Lb9
        L93:
            r0 = move-exception
            r1 = r3
        L95:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lb8
            r4.<init>()     // Catch: java.lang.Throwable -> Lb8
            java.lang.String r5 = "ZipUtil"
            java.lang.String r6 = "Unzip IOException : "
            r4.append(r6)     // Catch: java.lang.Throwable -> Lb8
            java.lang.String r0 = r0.getMessage()     // Catch: java.lang.Throwable -> Lb8
            r4.append(r0)     // Catch: java.lang.Throwable -> Lb8
            java.lang.String r0 = r4.toString()     // Catch: java.lang.Throwable -> Lb8
            com.huawei.secure.android.common.util.LogsUtil.e(r5, r0)     // Catch: java.lang.Throwable -> Lb8
            a(r3, r1)
        Lb2:
            if (r2 != 0) goto Lb7
            a(r10)
        Lb7:
            return r2
        Lb8:
            r0 = move-exception
        Lb9:
            a(r3, r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.secure.android.common.util.ZipUtil.unZip(java.lang.String, java.lang.String, long, int, boolean):boolean");
    }

    public static List<File> unZipNew(String str, String str2, long j, int i, boolean z) throws SecurityCommonException {
        return unZipWithFilter(str, str2, j, i, z, null, i);
    }

    public static List<File> unZipWithFilter(String str, String str2, long j, int i, boolean z, FilenameFilter filenameFilter, int i2) throws SecurityCommonException {
        if (!a(str, str2, j, i, filenameFilter, i2)) {
            return null;
        }
        if (str2.endsWith(File.separator) && str2.length() > File.separator.length()) {
            str2 = str2.substring(0, str2.length() - File.separator.length());
        }
        File d2 = d(str);
        File d3 = d(str2);
        if (d2 == null || d3 == null) {
            return null;
        }
        return a(d2, d3, j, z, false, filenameFilter);
    }

    private static void a(File file) {
        File parentFile = file.getParentFile();
        if (parentFile == null || parentFile.exists()) {
            return;
        }
        e(parentFile);
    }

    private static void b(String str) {
        if (TextUtils.isEmpty(str) || !g(str)) {
            return;
        }
        Log.e(f8655a, "IllegalArgumentException--path is not a standard path");
        throw new IllegalArgumentException("path is not a standard path");
    }

    private static boolean a(ZipEntry zipEntry, File file, List<File> list) {
        if (!zipEntry.isDirectory()) {
            return false;
        }
        e(file);
        list.add(file);
        return true;
    }

    private static void e(File file) {
        if (file == null || file.exists() || file.mkdirs()) {
            return;
        }
        LogsUtil.e(f8655a, "mkdirs error , files exists or IOException.");
    }

    private static boolean a(String str) {
        String normalize = Normalizer.normalize(str, Normalizer.Form.NFKC);
        if (!g(normalize)) {
            return true;
        }
        Log.e(f8655a, "zipPath is a invalid path: " + e(normalize));
        return false;
    }

    private static boolean b(File file) {
        return file != null && (!file.exists() ? !file.mkdirs() : !file.isDirectory());
    }

    private static File c(String str) {
        b(str);
        return new File(str);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static long a(File file, ZipInputStream zipInputStream, long j, long j2, List<File> list) throws IOException {
        FileOutputStream fileOutputStream;
        FileOutputStream fileOutputStream2;
        FileOutputStream fileOutputStream3;
        List<File> list2;
        byte[] bArr = new byte[4096];
        long j3 = -1;
        FileOutputStream fileOutputStream4 = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            try {
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
                long j4 = j;
                while (true) {
                    try {
                        int read = zipInputStream.read(bArr, 0, 4096);
                        if (read == -1) {
                            list2 = list;
                            break;
                        }
                        j4 += read;
                        if (j4 > j2) {
                            Log.e(f8655a, "unzip  over than top size");
                            list2 = list;
                            j4 = -1;
                            break;
                        }
                        bufferedOutputStream.write(bArr, 0, read);
                    } catch (IOException e2) {
                        e = e2;
                        fileOutputStream4 = bufferedOutputStream;
                        fileOutputStream2 = fileOutputStream4;
                        fileOutputStream4 = fileOutputStream;
                        try {
                            LogsUtil.e(f8655a, "Unzip IOException : " + e.getMessage());
                            fileOutputStream3 = fileOutputStream2;
                            fileOutputStream = fileOutputStream4;
                            IOUtil.closeSecure((OutputStream) fileOutputStream3);
                            IOUtil.closeSecure((OutputStream) fileOutputStream);
                            return j3;
                        } catch (Throwable th) {
                            th = th;
                            fileOutputStream = fileOutputStream4;
                            fileOutputStream4 = fileOutputStream2;
                            IOUtil.closeSecure((OutputStream) fileOutputStream4);
                            IOUtil.closeSecure((OutputStream) fileOutputStream);
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        fileOutputStream4 = bufferedOutputStream;
                        IOUtil.closeSecure((OutputStream) fileOutputStream4);
                        IOUtil.closeSecure((OutputStream) fileOutputStream);
                        throw th;
                    }
                }
                list2.add(file);
                bufferedOutputStream.flush();
                j3 = j4;
                fileOutputStream3 = bufferedOutputStream;
            } catch (IOException e3) {
                e = e3;
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (IOException e4) {
            e = e4;
            fileOutputStream2 = null;
        } catch (Throwable th4) {
            th = th4;
            fileOutputStream = null;
        }
        IOUtil.closeSecure((OutputStream) fileOutputStream3);
        IOUtil.closeSecure((OutputStream) fileOutputStream);
        return j3;
    }

    private static String a(ZipEntry zipEntry) {
        return Normalizer.normalize(zipEntry.getName(), Normalizer.Form.NFKC);
    }

    private static int a(ZipEntry zipEntry, List<File> list, File file, boolean z, FilenameFilter filenameFilter) {
        if (TextUtils.isEmpty(zipEntry.getName())) {
            return 1;
        }
        String a2 = a(zipEntry);
        if (g(a2)) {
            Log.e(f8655a, "zipPath is a invalid path: " + e(a2));
            return -1;
        }
        String f2 = f(a2);
        if (filenameFilter != null && !filenameFilter.accept(file, f2)) {
            return 1;
        }
        File file2 = new File(file, f2);
        if (!z && file2.exists() && file2.isFile()) {
            return 1;
        }
        if (z && file2.exists() && file2.isFile()) {
            f(file2);
        }
        list.add(file2);
        return 0;
    }

    private static long a(ZipEntry zipEntry, File file, long j, long j2, ZipFile zipFile) throws IOException {
        Throwable th;
        FileOutputStream fileOutputStream;
        BufferedOutputStream bufferedOutputStream;
        File file2 = new File(file, f(a(zipEntry)));
        if (zipEntry.isDirectory()) {
            if (!b(file2)) {
                return 1L;
            }
        } else {
            if (!c(file2)) {
                return 1L;
            }
            BufferedInputStream bufferedInputStream = null;
            try {
                BufferedInputStream bufferedInputStream2 = new BufferedInputStream(zipFile.getInputStream(zipEntry));
                try {
                    fileOutputStream = new FileOutputStream(file2);
                    try {
                        bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
                        try {
                            byte[] bArr = new byte[1024];
                            while (true) {
                                int read = bufferedInputStream2.read(bArr);
                                if (read == -1) {
                                    IOUtil.closeSecure((InputStream) bufferedInputStream2);
                                    IOUtil.closeSecure((OutputStream) bufferedOutputStream);
                                    IOUtil.closeSecure((OutputStream) fileOutputStream);
                                    break;
                                }
                                j += read;
                                if (j > j2) {
                                    Log.e(f8655a, "unzipFileNew: over than top size");
                                    IOUtil.closeSecure((InputStream) bufferedInputStream2);
                                    IOUtil.closeSecure((OutputStream) bufferedOutputStream);
                                    IOUtil.closeSecure((OutputStream) fileOutputStream);
                                    return -1L;
                                }
                                bufferedOutputStream.write(bArr, 0, read);
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            bufferedInputStream = bufferedInputStream2;
                            IOUtil.closeSecure((InputStream) bufferedInputStream);
                            IOUtil.closeSecure((OutputStream) bufferedOutputStream);
                            IOUtil.closeSecure((OutputStream) fileOutputStream);
                            throw th;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        bufferedOutputStream = null;
                    }
                } catch (Throwable th4) {
                    th = th4;
                    fileOutputStream = null;
                    bufferedOutputStream = null;
                }
            } catch (Throwable th5) {
                th = th5;
                fileOutputStream = null;
                bufferedOutputStream = null;
            }
        }
        return j;
    }

    private static void a(boolean z, List<File> list) {
        if (z) {
            return;
        }
        a(list);
        list.clear();
    }

    private static List<File> a(File file, File file2, long j, boolean z, boolean z2, FilenameFilter filenameFilter) {
        ZipFile b2;
        ArrayList arrayList = new ArrayList();
        boolean z3 = true;
        ZipFile zipFile = null;
        try {
            b2 = b(z2, file);
        } catch (IOException e2) {
            e = e2;
        } catch (Throwable th) {
            th = th;
        }
        try {
            Enumeration<? extends ZipEntry> entries = b2.entries();
            long j2 = 0;
            while (entries.hasMoreElements()) {
                try {
                    ZipEntry nextElement = entries.nextElement();
                    int a2 = a(nextElement, arrayList, file2, z, filenameFilter);
                    if (a2 != -1) {
                        if (a2 != 1) {
                            j2 = a(nextElement, file2, j2, j, b2);
                            if (j2 == 1) {
                                IOUtil.closeSecure(b2);
                                a(false, (List<File>) arrayList);
                                return null;
                            }
                            if (j2 == -1) {
                            }
                        }
                    }
                    z3 = false;
                    break;
                } catch (IllegalArgumentException e3) {
                    LogsUtil.i(f8655a, "not a utf8 zip file, IllegalArgumentException : " + e3.getMessage());
                    List<File> a3 = a(file, file2, j, z, true, filenameFilter);
                    IOUtil.closeSecure(b2);
                    a(true, (List<File>) arrayList);
                    return a3;
                }
            }
            IOUtil.closeSecure(b2);
            a(z3, arrayList);
        } catch (IOException e4) {
            e = e4;
            zipFile = b2;
            try {
                Log.e(f8655a, "unzip new IOException : " + e.getMessage());
                IOUtil.closeSecure(zipFile);
                a(false, (List<File>) arrayList);
                return arrayList;
            } catch (Throwable th2) {
                th = th2;
                z3 = false;
                IOUtil.closeSecure(zipFile);
                a(z3, arrayList);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            zipFile = b2;
            IOUtil.closeSecure(zipFile);
            a(z3, arrayList);
            throw th;
        }
        return arrayList;
    }

    private static boolean a(String str, File file, long j, int i, boolean z, FilenameFilter filenameFilter, int i2) {
        ZipFile zipFile;
        boolean z2;
        ZipFile zipFile2;
        ZipEntry nextElement;
        ZipFile zipFile3 = null;
        try {
            try {
                if (!z) {
                    zipFile2 = new ZipFile(str);
                } else {
                    LogsUtil.i(f8655a, "not a utf8 zip file, use gbk open zip file : " + str);
                    zipFile2 = new ZipFile(str, Charset.forName("GBK"));
                }
                zipFile = zipFile2;
            } catch (IOException e2) {
                e = e2;
            }
        } catch (Throwable th) {
            th = th;
            zipFile = zipFile3;
        }
        try {
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            long j2 = 0;
            int i3 = 0;
            int i4 = 0;
            do {
                if (!entries.hasMoreElements()) {
                    z2 = true;
                    break;
                }
                try {
                    nextElement = entries.nextElement();
                    long size = nextElement.getSize();
                    String name = nextElement.getName();
                    if (filenameFilter != null) {
                        if (filenameFilter.accept(file, name)) {
                        }
                        i4++;
                        if (!g(name) || i4 >= i2 || i3 >= i || j2 > j) {
                            break;
                            break;
                        }
                    }
                    j2 += size;
                    i3++;
                    i4++;
                    if (!g(name)) {
                        break;
                    }
                } catch (IllegalArgumentException e3) {
                    LogsUtil.i(f8655a, "not a utf8 zip file, IllegalArgumentException : " + e3.getMessage());
                    z2 = a(str, file, j, i, true, filenameFilter, i2);
                }
            } while (nextElement.getSize() != -1);
            LogsUtil.e(f8655a, "File name is invalid or too many files or too big");
            z2 = false;
            try {
                zipFile.close();
                return z2;
            } catch (IOException unused) {
                LogsUtil.e(f8655a, "close zipFile IOException ");
                return z2;
            }
        } catch (IOException e4) {
            e = e4;
            zipFile3 = zipFile;
            LogsUtil.e(f8655a, "not a valid zip file, IOException : " + e.getMessage());
            if (zipFile3 != null) {
                try {
                    zipFile3.close();
                } catch (IOException unused2) {
                    z2 = false;
                    LogsUtil.e(f8655a, "close zipFile IOException ");
                    return z2;
                }
            }
            return false;
        } catch (Throwable th2) {
            th = th2;
            if (zipFile != null) {
                try {
                    zipFile.close();
                } catch (IOException unused3) {
                    LogsUtil.e(f8655a, "close zipFile IOException ");
                }
            }
            throw th;
        }
    }

    private static boolean a(String str, String str2, long j, int i, FilenameFilter filenameFilter, int i2) throws SecurityCommonException {
        if (!TextUtils.isEmpty(str) && !g(str)) {
            if (!TextUtils.isEmpty(str2) && !g(str2)) {
                if (a(str, new File(str2), j, i, false, filenameFilter, i2)) {
                    return true;
                }
                LogsUtil.e(f8655a, "zip file contains valid chars or too many files");
                throw new SecurityCommonException("unsecure zipfile!");
            }
            LogsUtil.e(f8655a, "target directory is not valid");
            return false;
        }
        LogsUtil.e(f8655a, "zip file is not valid");
        return false;
    }

    private static boolean a(List<File> list) {
        try {
            Iterator<File> it = list.iterator();
            while (it.hasNext()) {
                f(it.next());
            }
            return true;
        } catch (Exception e2) {
            LogsUtil.e(f8655a, "unzip fail delete file failed" + e2.getMessage());
            return false;
        }
    }

    private static void a(FileInputStream fileInputStream, ZipInputStream zipInputStream) {
        IOUtil.closeSecure((InputStream) fileInputStream);
        IOUtil.closeSecure((InputStream) zipInputStream);
    }
}
