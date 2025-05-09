package defpackage;

import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.IoUtils;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.apache.commons.io.FileUtils;

/* loaded from: classes3.dex */
public class cpk {
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v10, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v11, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v15 */
    /* JADX WARN: Type inference failed for: r1v16 */
    /* JADX WARN: Type inference failed for: r1v8 */
    public static void a(Collection<File> collection, File file, String str) throws IOException {
        ZipOutputStream zipOutputStream;
        if (collection == null || file == null) {
            LogUtil.h("ZipUtil", "zipFileList or zipFileParam is null");
            return;
        }
        LogUtil.a("ZipUtil", "zipFiles size: ", Integer.valueOf(collection.size()));
        ZipOutputStream zipOutputStream2 = null;
        ?? r1 = 0;
        ZipOutputStream zipOutputStream3 = null;
        try {
            try {
                zipOutputStream = new ZipOutputStream(new BufferedOutputStream(FileUtils.openOutputStream(file), 1048576));
            } catch (Throwable th) {
                th = th;
                zipOutputStream = zipOutputStream2;
            }
        } catch (IOException unused) {
        }
        try {
            for (File file2 : collection) {
                String name = file2.getName();
                try {
                    r1 = new String(name.getBytes("8859_1"), "GB2312");
                } catch (UnsupportedEncodingException e) {
                    LogUtil.b("ZipUtil", "UnsupportedEncodingException e: ", e.getMessage());
                    r1 = name;
                }
                if (file2.isDirectory()) {
                    File[] listFiles = file2.listFiles();
                    if (listFiles != null && listFiles.length != 0) {
                        for (File file3 : listFiles) {
                            b(file3, zipOutputStream, r1, str);
                        }
                    }
                    r1 = "fileLists is null";
                    LogUtil.c("ZipUtil", "fileLists is null");
                } else {
                    e(zipOutputStream, file2);
                }
            }
            b(zipOutputStream);
            zipOutputStream2 = r1;
        } catch (IOException unused2) {
            zipOutputStream3 = zipOutputStream;
            LogUtil.b("ZipUtil", "zipFiles IOException exception");
            b(zipOutputStream3);
            zipOutputStream2 = zipOutputStream3;
        } catch (Throwable th2) {
            th = th2;
            b(zipOutputStream);
            throw th;
        }
    }

    private static void b(ZipOutputStream zipOutputStream) {
        if (zipOutputStream != null) {
            try {
                try {
                    zipOutputStream.setComment("com.huawei.health");
                    zipOutputStream.close();
                } catch (IOException unused) {
                    LogUtil.b("ZipUtil", "IOException exception");
                    if (zipOutputStream != null) {
                        try {
                            zipOutputStream.close();
                            return;
                        } catch (IOException unused2) {
                            LogUtil.b("ZipUtil", "IOException exception");
                            return;
                        }
                    }
                    return;
                }
            } catch (Throwable th) {
                if (zipOutputStream != null) {
                    try {
                        zipOutputStream.close();
                    } catch (IOException unused3) {
                        LogUtil.b("ZipUtil", "IOException exception");
                    }
                }
                throw th;
            }
        }
        if (zipOutputStream != null) {
            try {
                zipOutputStream.close();
            } catch (IOException unused4) {
                LogUtil.b("ZipUtil", "IOException exception");
            }
        }
    }

    private static void e(ZipOutputStream zipOutputStream, File file) {
        BufferedInputStream bufferedInputStream;
        LogUtil.a("ZipUtil", "processFile start");
        BufferedInputStream bufferedInputStream2 = null;
        try {
            try {
                byte[] bArr = new byte[1048576];
                String c = CommonUtil.c(file.getCanonicalPath());
                if (TextUtils.isEmpty(c)) {
                    LogUtil.h("ZipUtil", "zipFiles safePath is empty");
                    IoUtils.e((Closeable) null);
                    return;
                }
                bufferedInputStream = new BufferedInputStream(new FileInputStream(c), 1048576);
                try {
                    zipOutputStream.putNextEntry(new ZipEntry(file.getName()));
                    while (true) {
                        int read = bufferedInputStream.read(bArr);
                        if (read != -1) {
                            zipOutputStream.write(bArr, 0, read);
                        } else {
                            LogUtil.a("ZipUtil", "zip ok");
                            IoUtils.e(bufferedInputStream);
                            return;
                        }
                    }
                } catch (IOException e) {
                    e = e;
                    bufferedInputStream2 = bufferedInputStream;
                    LogUtil.b("ZipUtil", "processFile IOException", e.getMessage());
                    IoUtils.e(bufferedInputStream2);
                } catch (Throwable th) {
                    th = th;
                    IoUtils.e(bufferedInputStream);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                bufferedInputStream = null;
            }
        } catch (IOException e2) {
            e = e2;
        }
    }

    private static void b(File file, ZipOutputStream zipOutputStream, String str, String str2) {
        BufferedInputStream bufferedInputStream;
        byte[] bArr;
        LogUtil.a("ZipUtil", "zipFile");
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(str.trim().length() == 0 ? "" : File.separator);
        sb.append(file.getName());
        try {
            String str3 = new String(sb.toString().getBytes("8859_1"), "GB2312");
            LogUtil.a("ZipUtil", "rootPathResult: ", str3);
            if (file.isDirectory()) {
                File[] listFiles = file.listFiles();
                if (listFiles == null || listFiles.length <= 0) {
                    return;
                }
                a(file, zipOutputStream, str2, str3, listFiles);
                return;
            }
            BufferedInputStream bufferedInputStream2 = null;
            try {
                try {
                    bArr = new byte[1048576];
                    bufferedInputStream = new BufferedInputStream(FileUtils.openInputStream(file), 1048576);
                } catch (Throwable th) {
                    th = th;
                    bufferedInputStream = bufferedInputStream2;
                }
            } catch (IOException unused) {
            }
            try {
                zipOutputStream.putNextEntry(new ZipEntry(str3));
                while (true) {
                    int read = bufferedInputStream.read(bArr);
                    if (read == -1) {
                        break;
                    } else {
                        zipOutputStream.write(bArr, 0, read);
                    }
                }
                IoUtils.e(bufferedInputStream);
            } catch (IOException unused2) {
                bufferedInputStream2 = bufferedInputStream;
                LogUtil.b("ZipUtil", "zipFile IOException");
                IoUtils.e(bufferedInputStream2);
            } catch (Throwable th2) {
                th = th2;
                IoUtils.e(bufferedInputStream);
                throw th;
            }
        } catch (UnsupportedEncodingException e) {
            LogUtil.b("ZipUtil", "UnsupportedEncodingException e: ", e.getMessage());
        }
    }

    private static void a(File file, ZipOutputStream zipOutputStream, String str, String str2, File[] fileArr) {
        if ("com.huawei.health_PhoneService".equals(file.getName()) && "HWFEEDBACKAPI_ZIP_COMMENT_KEY".equals(str)) {
            File file2 = fileArr[0];
            if (file2 != null) {
                b(file2, zipOutputStream, str2, str);
                return;
            }
            return;
        }
        for (File file3 : fileArr) {
            b(file3, zipOutputStream, str2, str);
        }
    }
}
