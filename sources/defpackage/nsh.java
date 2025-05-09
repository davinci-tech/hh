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

/* loaded from: classes9.dex */
public class nsh {
    public static int d(String str, String str2) {
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
            } catch (IOException e) {
                e = e;
            }
        } catch (Throwable th) {
            th = th;
            zipInputStream = zipInputStream2;
        }
        try {
            int c2 = c(zipInputStream, str2, null, true);
            IoUtils.e(zipInputStream);
            return c2;
        } catch (IOException e2) {
            e = e2;
            zipInputStream2 = zipInputStream;
            LogUtil.b("UnzipUtils", "unzip IOException is； ", e.getMessage());
            c(c);
            IoUtils.e(zipInputStream2);
            return -1;
        } catch (Throwable th2) {
            th = th2;
            IoUtils.e(zipInputStream);
            throw th;
        }
    }

    private static int c(ZipInputStream zipInputStream, String str, String str2, boolean z) throws IOException {
        if (zipInputStream == null || TextUtils.isEmpty(str)) {
            LogUtil.h("UnzipUtils", "unzip zipInputStream or destFolder is empty.");
            return -1;
        }
        File b = b(str);
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
                    File a2 = a(nextEntry, b);
                    if (nextEntry.isDirectory()) {
                        LogUtil.c("UnzipUtils", "unzip zipEntry isDirectoryMade :", Boolean.valueOf(a2.mkdirs()));
                        nextEntry = zipInputStream.getNextEntry();
                    } else {
                        c(a2, false, true);
                        if (b(a2, zipInputStream, 0, new byte[4096]) > 104857600) {
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
        } catch (IOException e) {
            if (z) {
                c(z2, (File) null, b);
            }
            throw e;
        }
    }

    private static void c(String str) {
        File file = new File(str);
        if (file.exists()) {
            LogUtil.a("UnzipUtils", "deleteSourceFile sourceFile:", Boolean.valueOf(FileUtils.d(file)));
        }
    }

    private static File b(String str) throws IOException {
        String c = CommonUtil.c(str);
        if (TextUtils.isEmpty(c)) {
            LogUtil.h("UnzipUtils", "unzip dest folder path is empty.");
            throw new IOException("unzip this dest folder path is illegal.");
        }
        return new File(c);
    }

    private static File a(ZipEntry zipEntry, File file) throws IOException {
        String b = b(zipEntry.getName(), file);
        LogUtil.a("UnzipUtils", "getExtractFile:", b);
        String c = CommonUtil.c(b);
        if (TextUtils.isEmpty(c)) {
            LogUtil.h("UnzipUtils", "unzip this zip file dest path is illegal, unzip failure.");
            throw new IOException("unzip this zip file dest path is illegal.");
        }
        return new File(c);
    }

    private static String b(String str, File file) throws IOException {
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

    private static int b(File file, ZipInputStream zipInputStream, int i, byte[] bArr) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        while (true) {
            try {
                try {
                    int read = zipInputStream.read(bArr);
                    if (read <= -1 || (i = i + read) > 104857600) {
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

    private static void c(boolean z, File file, File file2) {
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
