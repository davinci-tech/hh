package defpackage;

import android.text.TextUtils;
import com.huawei.android.hicloud.sync.util.FileUtil;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.exception.ExceptionUtils;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/* loaded from: classes7.dex */
public final class sqd {
    public static String a(File file) {
        if (file == null) {
            ReleaseLogUtil.a(FileUtil.TAG, "getCanonicalPath file is null");
            return "";
        }
        try {
            return file.getCanonicalPath();
        } catch (IOException e) {
            ReleaseLogUtil.c(FileUtil.TAG, "getCanonicalPath exception ", ExceptionUtils.d(e));
            return "";
        }
    }

    public static String a() {
        return a(BaseApplication.e().getFilesDir());
    }

    public static String c() {
        return a(BaseApplication.e().getCacheDir());
    }

    private static boolean d(File file, String str, String str2, String str3, boolean z) {
        if (file == null) {
            LogUtil.a(FileUtil.TAG, "isValidPath file is null pathPrefix ", str, " pathSuffix ", str2, " namePrefix ", str3, " isDirectory ", Boolean.valueOf(z));
            return false;
        }
        if (z) {
            if (!file.isDirectory()) {
                LogUtil.a(FileUtil.TAG, "isValidPath isDirectory is false pathPrefix ", str, " pathSuffix ", str2, " namePrefix ", str3, " file ", file);
                return false;
            }
        } else {
            String name = file.getName();
            if (!TextUtils.isEmpty(name)) {
                if (!name.startsWith(str3)) {
                }
            }
            LogUtil.a(FileUtil.TAG, "isValidPath name ", name, " namePrefix ", str3, " pathPrefix ", str, " pathSuffix ", str2, " file ", file);
            return false;
        }
        try {
            String canonicalPath = file.getCanonicalPath();
            if (!TextUtils.isEmpty(canonicalPath) && canonicalPath.startsWith(str) && canonicalPath.endsWith(str2)) {
                return true;
            }
            LogUtil.a(FileUtil.TAG, "isValidPath canonicalPath ", canonicalPath, " pathPrefix ", str, " pathSuffix ", str2, " namePrefix ", str3, " isDirectory ", Boolean.valueOf(z), " file ", file);
            return false;
        } catch (IOException e) {
            ReleaseLogUtil.c(FileUtil.TAG, "isValidPath exception ", ExceptionUtils.d(e));
            return false;
        }
    }

    public static boolean b(File file) {
        String a2 = a();
        if (TextUtils.isEmpty(a2)) {
            ReleaseLogUtil.a(FileUtil.TAG, "isValidFile appCanonicalPath ", a2);
            return false;
        }
        return d(file, a2);
    }

    public static boolean d(File file, String str) {
        return a(file, str, "");
    }

    public static boolean a(File file, String str, String str2) {
        return b(file, str, str2, "");
    }

    public static boolean b(File file, String str, String str2, String str3) {
        return d(file, str, str2, str3, false);
    }

    public static boolean e(File file, String str) {
        String a2 = a();
        if (TextUtils.isEmpty(a2)) {
            ReleaseLogUtil.a(FileUtil.TAG, "isValidFileAndName appCanonicalPath ", a2);
            return false;
        }
        return e(file, a2, str);
    }

    public static boolean e(File file, String str, String str2) {
        return b(file, str, "", str2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 4, insn: 0x0059: MOVE (r1 I:??[OBJECT, ARRAY]) = (r4 I:??[OBJECT, ARRAY]), block:B:35:0x0059 */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v4, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r0v5 */
    public static byte[] c(File file) {
        ?? r0;
        ByteArrayOutputStream byteArrayOutputStream;
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2;
        FileInputStream fileInputStream3 = null;
        r1 = null;
        byte[] bArr = null;
        if (file == null) {
            LogUtil.a(FileUtil.TAG, "readFileToByte, file is null");
            return null;
        }
        try {
            try {
                fileInputStream = new FileInputStream(file);
            } catch (Throwable th) {
                fileInputStream3 = fileInputStream2;
                r0 = file;
                th = th;
            }
        } catch (FileNotFoundException unused) {
            byteArrayOutputStream = null;
            fileInputStream = null;
        } catch (IOException unused2) {
            byteArrayOutputStream = null;
            fileInputStream = null;
        } catch (Throwable th2) {
            th = th2;
            r0 = 0;
        }
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                byte[] bArr2 = new byte[256];
                while (true) {
                    int read = fileInputStream.read(bArr2);
                    if (read == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr2, 0, read);
                }
                bArr = byteArrayOutputStream.toByteArray();
            } catch (FileNotFoundException unused3) {
                LogUtil.a(FileUtil.TAG, "readFileToByte, FileNotFoundException:");
                c(fileInputStream);
                c(byteArrayOutputStream);
                return bArr;
            } catch (IOException unused4) {
                LogUtil.a(FileUtil.TAG, "readFileToByte, IOException:");
                c(fileInputStream);
                c(byteArrayOutputStream);
                return bArr;
            }
        } catch (FileNotFoundException unused5) {
            byteArrayOutputStream = null;
        } catch (IOException unused6) {
            byteArrayOutputStream = null;
        } catch (Throwable th3) {
            th = th3;
            r0 = 0;
            fileInputStream3 = fileInputStream;
            c(fileInputStream3);
            c((Closeable) r0);
            throw th;
        }
        c(fileInputStream);
        c(byteArrayOutputStream);
        return bArr;
    }

    private static void c(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
                LogUtil.e(FileUtil.TAG, "An exception occurred while closing the 'Closeable' object.");
            }
        }
    }
}
