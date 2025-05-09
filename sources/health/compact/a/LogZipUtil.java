package health.compact.a;

import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.os.FileUtils;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/* loaded from: classes.dex */
public class LogZipUtil {
    private LogZipUtil() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v1 */
    /* JADX WARN: Type inference failed for: r12v11, types: [java.util.zip.ZipOutputStream] */
    /* JADX WARN: Type inference failed for: r12v17 */
    /* JADX WARN: Type inference failed for: r12v2 */
    /* JADX WARN: Type inference failed for: r12v5 */
    /* JADX WARN: Type inference failed for: r12v6, types: [java.util.zip.ZipOutputStream] */
    /* JADX WARN: Type inference failed for: r12v8, types: [java.util.zip.ZipOutputStream] */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v10 */
    /* JADX WARN: Type inference failed for: r4v14 */
    /* JADX WARN: Type inference failed for: r4v15 */
    /* JADX WARN: Type inference failed for: r4v16 */
    /* JADX WARN: Type inference failed for: r4v17 */
    /* JADX WARN: Type inference failed for: r4v6 */
    public static void e(File file, File file2) {
        ?? r12;
        ?? r122;
        String c;
        BufferedInputStream bufferedInputStream;
        com.huawei.hwlogsmodel.LogUtil.a("LogZipUtil", "enter zipSingleFile");
        if (file.isDirectory()) {
            com.huawei.hwlogsmodel.LogUtil.h("LogZipUtil", "zipFile.isDirectory()");
            return;
        }
        BufferedInputStream bufferedInputStream2 = 0;
        r4 = null;
        BufferedInputStream bufferedInputStream3 = null;
        BufferedInputStream bufferedInputStream4 = null;
        try {
            try {
                c = c(file.getCanonicalPath());
            } catch (Throwable th) {
                th = th;
                bufferedInputStream4 = bufferedInputStream2;
                r12 = file2;
            }
        } catch (IOException unused) {
            r122 = 0;
        } catch (Throwable th2) {
            th = th2;
            r12 = 0;
        }
        if (TextUtils.isEmpty(c)) {
            com.huawei.hwlogsmodel.LogUtil.h("LogZipUtil", "zipFiles safePath is empty");
            FileUtils.d((Closeable) null);
            return;
        }
        byte[] bArr = new byte[16384];
        r122 = new ZipOutputStream(new BufferedOutputStream(org.apache.commons.io.FileUtils.openOutputStream(file2), 16384));
        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream(c), 16384);
        } catch (IOException unused2) {
        }
        try {
            r122.putNextEntry(new ZipEntry(file.getName()));
            while (true) {
                int read = bufferedInputStream.read(bArr);
                bufferedInputStream2 = -1;
                bufferedInputStream2 = -1;
                if (read == -1) {
                    break;
                } else {
                    r122.write(bArr, 0, read);
                }
            }
            FileUtils.d(bufferedInputStream);
            try {
                r122.setComment(BaseApplication.d());
                r122.close();
                file2 = r122;
            } catch (IOException unused3) {
                com.huawei.hwlogsmodel.LogUtil.b("LogZipUtil", "IOException exception");
                file2 = r122;
            }
        } catch (IOException unused4) {
            bufferedInputStream3 = bufferedInputStream;
            com.huawei.hwlogsmodel.LogUtil.b("LogZipUtil", "zipFile IOException");
            FileUtils.d(bufferedInputStream3);
            bufferedInputStream2 = bufferedInputStream3;
            file2 = r122;
            if (r122 != 0) {
                try {
                    r122.setComment(BaseApplication.d());
                    r122.close();
                    bufferedInputStream2 = bufferedInputStream3;
                    file2 = r122;
                } catch (IOException unused5) {
                    com.huawei.hwlogsmodel.LogUtil.b("LogZipUtil", "IOException exception");
                    bufferedInputStream2 = bufferedInputStream3;
                    file2 = r122;
                }
            }
        } catch (Throwable th3) {
            th = th3;
            bufferedInputStream4 = bufferedInputStream;
            r12 = r122;
            FileUtils.d(bufferedInputStream4);
            if (r12 != 0) {
                try {
                    r12.setComment(BaseApplication.d());
                    r12.close();
                } catch (IOException unused6) {
                    com.huawei.hwlogsmodel.LogUtil.b("LogZipUtil", "IOException exception");
                }
            }
            throw th;
        }
    }

    public static void a(String str) {
        File file = new File(str);
        File[] listFiles = file.listFiles(new FilenameFilter() { // from class: health.compact.a.LogZipUtil.4
            @Override // java.io.FilenameFilter
            public boolean accept(File file2, String str2) {
                return str2.startsWith("uploaded_local_") && str2.endsWith(".zip");
            }
        });
        File[] listFiles2 = file.listFiles(new FilenameFilter() { // from class: health.compact.a.LogZipUtil.2
            @Override // java.io.FilenameFilter
            public boolean accept(File file2, String str2) {
                return str2.startsWith("local_") && str2.endsWith(".zip");
            }
        });
        int length = listFiles == null ? 0 : listFiles.length;
        if ((listFiles2 == null ? 0 : listFiles2.length) + length >= 5) {
            if (length > 0) {
                com.huawei.hwlogsmodel.LogUtil.a("LogZipUtil", "deleteOldestFile, isDelete :", Boolean.valueOf(listFiles[0].delete()));
            } else {
                Arrays.sort(listFiles2, new Comparator<File>() { // from class: health.compact.a.LogZipUtil.1
                    @Override // java.util.Comparator
                    /* renamed from: e, reason: merged with bridge method [inline-methods] */
                    public int compare(File file2, File file3) {
                        return LogZipUtil.d(file2, file3);
                    }
                });
                com.huawei.hwlogsmodel.LogUtil.a("LogZipUtil", "deleteOldestFile, isDelete :", Boolean.valueOf(listFiles2[0].delete()));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int d(File file, File file2) {
        boolean z = file == null || !file.exists();
        boolean z2 = file2 == null || !file2.exists();
        if (z && z2) {
            return 0;
        }
        if (z) {
            return -1;
        }
        if (z2) {
            return 1;
        }
        return Long.compare(file.lastModified(), file2.lastModified());
    }

    private static String e(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            if ("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890-=[];\\',./ ~!@#$%^&*()_+\"{}|:<>?".contains(String.valueOf(str.charAt(i)))) {
                stringBuffer.append(str.charAt(i));
            }
        }
        return stringBuffer.toString();
    }

    private static String c(String str) {
        String e = e(str);
        if (TextUtils.isEmpty(e)) {
            return null;
        }
        try {
            return new File(e).getCanonicalPath();
        } catch (IOException e2) {
            com.huawei.hwlogsmodel.LogUtil.h("LogZipUtil", "filterFilePath IOException :", e2.getMessage());
            return null;
        }
    }

    public static boolean d(String str, String str2, boolean z) {
        String c = c(str);
        String c2 = c(str2);
        if (TextUtils.isEmpty(c) || TextUtils.isEmpty(c2)) {
            com.huawei.hwlogsmodel.LogUtil.a("LogZipUtil", "copyFile source file is empty or destFileName is empty");
            return false;
        }
        File file = new File(c);
        if (e(file)) {
            return false;
        }
        File file2 = new File(c2);
        if (b(c2, file2, z)) {
            return false;
        }
        return a(file, file2);
    }

    private static boolean b(String str, File file, boolean z) {
        if (file.exists() && z) {
            try {
                return !new File(str).delete();
            } catch (SecurityException unused) {
                com.huawei.hwlogsmodel.LogUtil.a("LogZipUtil", "delete SecurityException");
                return false;
            } catch (Exception unused2) {
                com.huawei.hwlogsmodel.LogUtil.a("LogZipUtil", "delete exception");
                return false;
            }
        }
        if (file.getParentFile() == null || file.getParentFile().exists()) {
            return false;
        }
        return !file.getParentFile().mkdirs();
    }

    private static boolean e(File file) {
        if (!file.exists()) {
            com.huawei.hwlogsmodel.LogUtil.a("LogZipUtil", "source file is not exit");
            return true;
        }
        if (!file.isFile()) {
            com.huawei.hwlogsmodel.LogUtil.a("LogZipUtil", "srcFile is not a file");
            return true;
        }
        com.huawei.hwlogsmodel.LogUtil.a("LogZipUtil", "srcFile is exists");
        return false;
    }

    private static boolean a(File file, File file2) {
        Throwable th;
        FileInputStream fileInputStream;
        FileOutputStream fileOutputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            try {
                FileOutputStream fileOutputStream2 = new FileOutputStream(file2);
                try {
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int read = fileInputStream.read(bArr);
                        if (read != -1) {
                            fileOutputStream2.write(bArr, 0, read);
                        } else {
                            FileUtils.d(fileOutputStream2);
                            FileUtils.d(fileInputStream);
                            return true;
                        }
                    }
                } catch (IOException unused) {
                    fileOutputStream = fileOutputStream2;
                    FileUtils.d(fileOutputStream);
                    FileUtils.d(fileInputStream);
                    return false;
                } catch (Throwable th2) {
                    th = th2;
                    fileOutputStream = fileOutputStream2;
                    FileUtils.d(fileOutputStream);
                    FileUtils.d(fileInputStream);
                    throw th;
                }
            } catch (IOException unused2) {
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (IOException unused3) {
            fileInputStream = null;
        } catch (Throwable th4) {
            th = th4;
            fileInputStream = null;
        }
    }
}
