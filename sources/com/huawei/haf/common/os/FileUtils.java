package com.huawei.haf.common.os;

import android.os.Build;
import com.huawei.haf.common.exception.ExceptionUtils;
import health.compact.a.LogUtil;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

/* loaded from: classes.dex */
public final class FileUtils {
    private FileUtils() {
    }

    public static long d(File file, File file2) throws IOException {
        h(file);
        d(file2, true);
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            try {
                long e = e(fileInputStream, fileOutputStream);
                fileOutputStream.close();
                fileInputStream.close();
                return e;
            } finally {
            }
        } catch (Throwable th) {
            try {
                fileInputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public static long a(InputStream inputStream, File file) throws IOException {
        d(file, true);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        try {
            long e = e(inputStream, fileOutputStream);
            fileOutputStream.close();
            return e;
        } catch (Throwable th) {
            try {
                fileOutputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public static long e(InputStream inputStream, OutputStream outputStream) throws IOException {
        long j;
        if (Build.VERSION.SDK_INT >= 29) {
            j = android.os.FileUtils.copy(inputStream, outputStream);
        } else {
            byte[] bArr = new byte[8192];
            long j2 = 0;
            while (true) {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                outputStream.write(bArr, 0, read);
                j2 += read;
            }
            j = j2;
        }
        outputStream.flush();
        if (outputStream instanceof FileOutputStream) {
            ((FileOutputStream) outputStream).getFD().sync();
        }
        return j;
    }

    public static void a(File[] fileArr, File file) throws IOException {
        d(file, true);
        FileInputStream fileInputStream = null;
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            try {
                byte[] bArr = new byte[8192];
                for (File file2 : fileArr) {
                    if (file2 == null) {
                        LogUtil.a("HAF_FileUtils", "mergeFiles, file is null");
                    } else {
                        h(file2);
                        FileInputStream fileInputStream2 = new FileInputStream(file2);
                        while (true) {
                            try {
                                int read = fileInputStream2.read(bArr);
                                if (read == -1) {
                                    break;
                                } else {
                                    fileOutputStream.write(bArr, 0, read);
                                }
                            } catch (Throwable th) {
                                th = th;
                                fileInputStream = fileInputStream2;
                                try {
                                    fileOutputStream.close();
                                } catch (Throwable th2) {
                                    th.addSuppressed(th2);
                                }
                                throw th;
                            }
                        }
                        d(fileInputStream2);
                    }
                }
                fileOutputStream.flush();
                fileOutputStream.getFD().sync();
                fileOutputStream.close();
            } catch (Throwable th3) {
                th = th3;
            }
        } finally {
            d(fileInputStream);
        }
    }

    public static void d(File file, File file2, boolean z) throws IOException {
        h(file);
        d(file2, z);
        if (file.renameTo(file2)) {
            return;
        }
        d(file, file2);
        if (d(file)) {
            return;
        }
        throw new IOException("Failed to delete original file '" + file + "' after copy to '" + file2 + "'");
    }

    private static void d(File file, boolean z) throws IOException {
        e(file, true, z);
        File parentFile = file.getParentFile();
        if (parentFile == null || parentFile.mkdirs() || parentFile.isDirectory()) {
            return;
        }
        throw new IOException("Destination '" + parentFile + "' directory cannot be created");
    }

    private static void h(File file) throws IOException {
        if (!file.exists()) {
            throw new IOException("Source file '" + file + "' is not exists");
        }
        if (file.isDirectory()) {
            throw new IOException("Source file '" + file + "' is directory");
        }
    }

    private static void e(File file, boolean z, boolean z2) throws IOException {
        String str;
        if (!z2 && file.exists()) {
            throw new IOException("Destination file '" + file + "' already exists");
        }
        if (file.isDirectory()) {
            if (z) {
                str = "' is directory";
            }
            str = null;
        } else {
            if (!z) {
                str = "' is file";
            }
            str = null;
        }
        if (str == null) {
            return;
        }
        throw new IOException("Destination file '" + file + str);
    }

    public static void d(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
                LogUtil.e("HAF_FileUtils", "An exception occurred while closing the 'Closeable' object.");
            }
        }
    }

    public static void c(AutoCloseable autoCloseable) {
        if (autoCloseable != null) {
            try {
                autoCloseable.close();
            } catch (Exception unused) {
                LogUtil.e("HAF_FileUtils", "An exception occurred while closing the 'AutoCloseable' object.");
            }
        }
    }

    public static String b(File file, long j) throws IOException {
        h(file);
        StringBuilder sb = new StringBuilder(2048);
        byte[] bArr = new byte[1024];
        int i = 0;
        while (true) {
            if (i >= 3) {
                break;
            }
            long length = file.length();
            if (length > j) {
                throw new IOException("file too length, length=" + length + ", maxLength=" + j);
            }
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                try {
                    e(fileInputStream, sb, bArr, j);
                    fileInputStream.close();
                    break;
                } finally {
                    try {
                        break;
                    } catch (Throwable th) {
                    }
                }
            } catch (IOException e) {
                if (i >= 2) {
                    throw e;
                }
                LogUtil.e("HAF_FileUtils", "readContentFromFile num=", Integer.valueOf(i), ", ex=", LogUtil.a(e));
                i++;
            }
        }
        return sb.toString();
    }

    public static String a(InputStream inputStream, long j) throws IOException {
        StringBuilder sb = new StringBuilder(2048);
        e(inputStream, sb, new byte[1024], j);
        return sb.toString();
    }

    private static void e(InputStream inputStream, StringBuilder sb, byte[] bArr, long j) throws IOException {
        sb.delete(0, sb.length());
        while (true) {
            int read = inputStream.read(bArr);
            if (read == -1) {
                return;
            }
            String str = new String(bArr, 0, read, StandardCharsets.UTF_8);
            int length = sb.length() + str.length();
            if (length > j) {
                throw new IOException("read data too length, length=" + length + ", maxLength=" + j);
            }
            sb.append(str);
        }
    }

    public static boolean e(File file, boolean z) {
        try {
            c(file, z);
        } catch (IOException e) {
            LogUtil.a("HAF_FileUtils", "createNewFile, ex=", LogUtil.a(e));
        }
        return file.exists();
    }

    public static void c(File file, boolean z) throws IOException {
        if (file == null || file.exists()) {
            if (z) {
                f(file);
                return;
            }
            return;
        }
        IOException e = null;
        int i = 0;
        boolean z2 = false;
        while (i < 3 && !z2) {
            i++;
            try {
                if (!file.createNewFile()) {
                    LogUtil.a("HAF_FileUtils", "File ", file.getPath(), " already exists");
                }
                if (z) {
                    f(file);
                }
                z2 = true;
            } catch (IOException e2) {
                e = e2;
                z2 = false;
            }
        }
        if (z2) {
            return;
        }
        throw new IOException("Failed to create file " + file.getPath(), e);
    }

    public static boolean d(File file) {
        if (file == null || !file.exists()) {
            return true;
        }
        int i = 0;
        boolean z = false;
        while (i < 3 && !z) {
            i++;
            if (file.delete()) {
                z = true;
            }
        }
        if (!z) {
            if (!file.exists()) {
                return true;
            }
            LogUtil.a("HAF_FileUtils", "Failed to delete file: ", file.getPath());
        }
        return z;
    }

    public static File a(File file) {
        if (!file.exists() && !file.mkdirs() && !file.exists()) {
            LogUtil.a("HAF_FileUtils", "mkdirs fail, dir=", file.getPath());
        }
        return file;
    }

    public static void e(File file) {
        b(file, true);
    }

    public static void b(File file, boolean z) {
        if (file != null && file.exists() && file.isDirectory()) {
            a(file, z);
        }
    }

    public static void b(File file) {
        if (file == null || !file.exists()) {
            return;
        }
        a(file, true);
    }

    public static void d(File file, FilenameFilter filenameFilter) {
        if (file != null && file.exists() && file.isDirectory()) {
            File[] listFiles = filenameFilter != null ? file.listFiles(filenameFilter) : file.listFiles();
            if (listFiles == null) {
                LogUtil.a("HAF_FileUtils", "list files is null");
                return;
            }
            for (File file2 : listFiles) {
                a(file2, true);
            }
        }
    }

    private static void a(File file, boolean z) {
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File file2 : listFiles) {
                    a(file2, true);
                }
                if (z) {
                    i(file);
                    return;
                }
                return;
            }
            return;
        }
        i(file);
    }

    public static boolean i(File file) {
        if (file == null || !file.exists()) {
            return true;
        }
        boolean delete = file.delete();
        if (!delete && file.exists()) {
            LogUtil.a("HAF_FileUtils", "Failed to delete file, try to delete when exit. path: ", file.getPath());
            file.deleteOnExit();
        }
        return delete;
    }

    public static boolean c(File file) {
        return file != null && file.exists() && file.canRead() && file.isFile() && file.length() > 0;
    }

    public static boolean f(File file) {
        boolean z = false;
        if (file == null || !file.exists()) {
            return false;
        }
        try {
            boolean readOnly = file.setReadOnly();
            if (readOnly) {
                return readOnly;
            }
            try {
                LogUtil.a("HAF_FileUtils", "setReadOnly failed. path:", file.getPath());
                return readOnly;
            } catch (SecurityException e) {
                e = e;
                z = readOnly;
                LogUtil.e("HAF_FileUtils", "setReadOnly ex=", LogUtil.a(e));
                return z;
            }
        } catch (SecurityException e2) {
            e = e2;
        }
    }

    public static long j(File file) {
        if (file == null || !file.exists()) {
            return 0L;
        }
        return l(file);
    }

    private static long l(File file) {
        if (!file.isDirectory()) {
            return file.length();
        }
        try {
            return FileVisitUtils.e(file.toPath());
        } catch (IOException e) {
            LogUtil.a("HAF_FileUtils", "sizeOfDirectory ", file.getPath(), " fail, ex=", LogUtil.a(e));
            return k(file);
        }
    }

    private static long k(File file) {
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            return 0L;
        }
        long j = 0;
        for (File file2 : listFiles) {
            if (!g(file2)) {
                j += l(file2);
                if (j < 0) {
                    break;
                }
            }
        }
        return j;
    }

    private static boolean g(File file) {
        return Files.isSymbolicLink(file.toPath());
    }

    public static File e(File file, String str) throws IOException {
        try {
            File canonicalFile = new File(file, str).getCanonicalFile();
            if (canonicalFile.getPath().startsWith(file.getCanonicalPath())) {
                return canonicalFile;
            }
            throw new IOException("Possible path traversa issues. path=" + str);
        } catch (IOException e) {
            LogUtil.e("HAF_FileUtils", "getSafeFile ex=", ExceptionUtils.d(e));
            throw e;
        }
    }

    public static boolean a(File file, String str) {
        File file2;
        try {
            file2 = e(file, str);
        } catch (IOException unused) {
            file2 = null;
        }
        return file2 != null;
    }
}
