package defpackage;

import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.LogAnonymous;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/* loaded from: classes4.dex */
public class ggo {
    public static boolean c(File file, File file2) throws IOException {
        if (e(file2)) {
            return d(file, file2);
        }
        return false;
    }

    private static boolean c(File file) {
        if (file == null) {
            return false;
        }
        try {
            String canonicalPath = file.getCanonicalPath();
            if (canonicalPath == null) {
                return false;
            }
            return canonicalPath.equals(CommonUtil.d(canonicalPath));
        } catch (IOException e) {
            LogUtil.b("Suggestion_FileMoveUtil", LogAnonymous.b((Throwable) e));
            return false;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r8v2 */
    /* JADX WARN: Type inference failed for: r8v3, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r8v8 */
    /* JADX WARN: Type inference failed for: r8v9, types: [java.io.FileOutputStream] */
    public static boolean d(File file, File file2) throws IOException {
        Throwable th;
        ?? r8;
        FileInputStream fileInputStream = null;
        try {
            if (c(file) && c(file2)) {
                FileInputStream fileInputStream2 = new FileInputStream(file);
                try {
                    ?? fileOutputStream = new FileOutputStream(file2);
                    try {
                        byte[] bArr = new byte[2097152];
                        for (int read = fileInputStream2.read(bArr); read != -1; read = fileInputStream2.read(bArr)) {
                            fileOutputStream.write(bArr, 0, read);
                        }
                        try {
                            fileInputStream2.close();
                        } catch (IOException e) {
                            LogUtil.b("Suggestion_FileMoveUtil", "in.close() = ", LogAnonymous.b((Throwable) e));
                        }
                        try {
                            fileOutputStream.flush();
                        } catch (IOException e2) {
                            LogUtil.b("Suggestion_FileMoveUtil", "out.flush() = ", LogAnonymous.b((Throwable) e2));
                        }
                        try {
                            fileOutputStream.close();
                            return true;
                        } catch (IOException e3) {
                            LogUtil.b("Suggestion_FileMoveUtil", "out.close() = ", LogAnonymous.b((Throwable) e3));
                            return true;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        fileInputStream = fileOutputStream;
                        r8 = fileInputStream;
                        fileInputStream = fileInputStream2;
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (IOException e4) {
                                LogUtil.b("Suggestion_FileMoveUtil", "in.close() = ", LogAnonymous.b((Throwable) e4));
                            }
                        }
                        if (r8 != 0) {
                            try {
                                r8.flush();
                            } catch (IOException e5) {
                                LogUtil.b("Suggestion_FileMoveUtil", "out.flush() = ", LogAnonymous.b((Throwable) e5));
                            }
                        }
                        if (r8 != 0) {
                            try {
                                r8.close();
                                throw th;
                            } catch (IOException e6) {
                                LogUtil.b("Suggestion_FileMoveUtil", "out.close() = ", LogAnonymous.b((Throwable) e6));
                                throw th;
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                }
            }
            return false;
        } catch (Throwable th4) {
            th = th4;
            r8 = 0;
        }
    }

    private static boolean e(File file) {
        if (file == null) {
            LogUtil.b("Suggestion_FileMoveUtil", "isFileExist, file is null");
            return false;
        }
        if (file.getParentFile() == null) {
            return false;
        }
        boolean exists = file.getParentFile().exists();
        return !exists ? file.getParentFile().mkdirs() : exists;
    }
}
