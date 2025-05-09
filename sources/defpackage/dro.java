package defpackage;

import android.text.TextUtils;
import com.huawei.haf.common.os.FileUtils;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.IoUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/* loaded from: classes3.dex */
public class dro {
    public static int e(String str, String str2) {
        ZipInputStream zipInputStream;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.h("UnzipUtils", "unzip filePath or destFolder is empty.");
            return -1;
        }
        LogUtil.a("UnzipUtils", "unzip filePath :", str, ", destFolder :", str2);
        String c = CommonUtil.c(str);
        if (TextUtils.isEmpty(c)) {
            LogUtil.h("UnzipUtils", "unzip safeFilePath is empty.");
            return -1;
        }
        ZipInputStream zipInputStream2 = null;
        try {
            try {
                zipInputStream = new ZipInputStream(new FileInputStream(c));
            } catch (Throwable th) {
                th = th;
                zipInputStream = null;
            }
        } catch (IOException e) {
            e = e;
        }
        try {
            int d = d(zipInputStream, str2, null, true);
            IoUtils.e(zipInputStream);
            return d;
        } catch (IOException e2) {
            e = e2;
            zipInputStream2 = zipInputStream;
            LogUtil.b("UnzipUtils", "unzip IOException is； ", e.getMessage());
            a(c);
            IoUtils.e(zipInputStream2);
            return -1;
        } catch (Throwable th2) {
            th = th2;
            IoUtils.e(zipInputStream);
            throw th;
        }
    }

    private static int d(ZipInputStream zipInputStream, String str, String str2, boolean z) throws IOException {
        if (zipInputStream == null || TextUtils.isEmpty(str)) {
            LogUtil.h("UnzipUtils", "unzip zipInputStream or destFolder is empty.");
            return -1;
        }
        File e = e(str);
        boolean z2 = !TextUtils.isEmpty(str2);
        try {
            ZipEntry nextEntry = zipInputStream.getNextEntry();
            int i = 0;
            while (true) {
                if (nextEntry == null) {
                    break;
                }
                if (z2 && !str2.equals(nextEntry.getName())) {
                    nextEntry = zipInputStream.getNextEntry();
                } else {
                    File a2 = a(nextEntry, e);
                    if (nextEntry.isDirectory()) {
                        LogUtil.c("UnzipUtils", "unzip zipEntry isDirectoryMade :", Boolean.valueOf(a2.mkdirs()));
                        nextEntry = zipInputStream.getNextEntry();
                    } else {
                        c(a2, false, true);
                        if (a(a2, zipInputStream, 0, new byte[4096]) > 419430400) {
                            LogUtil.b("UnzipUtils", "unzip this zip file too big, unzip failure.");
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
            LogUtil.a("UnzipUtils", "unzip accomplished amount:", Integer.valueOf(i));
            return i;
        } catch (IOException e2) {
            if (z) {
                e(z2, null, e);
            }
            throw e2;
        }
    }

    private static void a(String str) {
        File file = new File(str);
        if (file.exists()) {
            LogUtil.a("UnzipUtils", "deleteSourceFile sourceFile:", Boolean.valueOf(FileUtils.d(file)));
        }
    }

    private static File e(String str) throws IOException {
        String c = CommonUtil.c(str);
        if (TextUtils.isEmpty(c)) {
            LogUtil.h("UnzipUtils", "unzip dest folder path is empty.");
            throw new IOException("unzip this dest folder path is illegal.");
        }
        return new File(c);
    }

    private static File a(ZipEntry zipEntry, File file) throws IOException {
        String c = c(zipEntry.getName(), file);
        LogUtil.a("UnzipUtils", "getExtractFile:", c);
        String c2 = CommonUtil.c(c);
        if (TextUtils.isEmpty(c2)) {
            LogUtil.h("UnzipUtils", "unzip this zip file dest path is illegal, unzip failure.");
            throw new IOException("unzip this zip file dest path is illegal.");
        }
        return new File(c2);
    }

    private static String c(String str, File file) throws IOException {
        String canonicalPath = new File(file, str).getCanonicalPath();
        if (canonicalPath.startsWith(file.getCanonicalPath())) {
            return canonicalPath;
        }
        throw new IOException("File is outside extraction target directory.");
    }

    private static void c(File file, boolean z, boolean z2) throws IOException, SecurityException {
        if (z && file.isDirectory()) {
            LogUtil.a("UnzipUtils", "initializeDestFile deleteDestFile as isDeleteFile :", Boolean.valueOf(FileUtils.d(file)));
        }
        if (z2 && file.exists()) {
            LogUtil.a("UnzipUtils", "initializeDestFile deleteDestFile isDeleteFile :", Boolean.valueOf(FileUtils.d(file)));
        }
        File parentFile = file.getParentFile();
        if (parentFile != null && !parentFile.exists()) {
            LogUtil.a("UnzipUtils", "initializeDestFile isMkdirFile :", Boolean.valueOf(parentFile.mkdirs()));
        }
        if (file.isFile()) {
            return;
        }
        LogUtil.c("UnzipUtils", "initializeDestFile isCreateNewFile :", Boolean.valueOf(file.createNewFile()));
    }

    private static int a(File file, ZipInputStream zipInputStream, int i, byte[] bArr) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        while (true) {
            try {
                try {
                    int read = zipInputStream.read(bArr);
                    if (read <= -1 || (i = i + read) > 419430400) {
                        break;
                    }
                    fileOutputStream.write(bArr, 0, read);
                } catch (IOException e) {
                    LogUtil.b("UnzipUtils", "unzipSingleFile IOException.");
                    throw e;
                }
            } finally {
                IoUtils.e(fileOutputStream);
            }
        }
        return i;
    }

    private static void e(boolean z, File file, File file2) {
        if (z) {
            if (file != null && file.exists() && file.isFile()) {
                FileUtils.b(file);
                return;
            }
            return;
        }
        if (file2.exists() && file2.isDirectory()) {
            FileUtils.b(file2);
        }
    }
}
