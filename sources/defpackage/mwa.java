package defpackage;

import com.huawei.android.hicloud.sync.util.FileUtil;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.LogAnonymous;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.FileUtils;

/* loaded from: classes6.dex */
public class mwa {
    public static String c(String str) {
        String str2;
        FileInputStream fileInputStream;
        String c = CommonUtil.c(str);
        str2 = "";
        if (c == null) {
            LogUtil.b(FileUtil.TAG, "read file fail: path is empty");
            return "";
        }
        FileInputStream fileInputStream2 = null;
        try {
            try {
                fileInputStream = new FileInputStream(new File(c));
            } catch (Throwable th) {
                th = th;
                fileInputStream = null;
            }
        } catch (FileNotFoundException e) {
            e = e;
        } catch (IOException e2) {
            e = e2;
        }
        try {
            byte[] bArr = new byte[fileInputStream.available()];
            str2 = fileInputStream.read(bArr) > 0 ? new String(bArr, "UTF-8") : "";
            a(fileInputStream);
        } catch (FileNotFoundException e3) {
            e = e3;
            fileInputStream2 = fileInputStream;
            LogUtil.b(FileUtil.TAG, "file path is not exist.", LogAnonymous.b((Throwable) e));
            a(fileInputStream2);
            return str2;
        } catch (IOException e4) {
            e = e4;
            fileInputStream2 = fileInputStream;
            LogUtil.b(FileUtil.TAG, "io error.", LogAnonymous.b((Throwable) e));
            a(fileInputStream2);
            return str2;
        } catch (Throwable th2) {
            th = th2;
            a(fileInputStream);
            throw th;
        }
        return str2;
    }

    public static boolean b(String str, String str2) {
        String c = CommonUtil.c(str);
        if (c == null) {
            LogUtil.b(FileUtil.TAG, "write file fail: path is empty");
            return false;
        }
        File file = new File(c);
        if (file.getParentFile() != null && !file.getParentFile().exists()) {
            boolean mkdirs = file.getParentFile().mkdirs();
            LogUtil.c(FileUtil.TAG, "create directory result:", Boolean.valueOf(mkdirs));
            if (!mkdirs) {
                return false;
            }
        }
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = FileUtils.openOutputStream(file);
            fileOutputStream.write(str2.getBytes(StandardCharsets.UTF_8));
            return true;
        } catch (IOException e) {
            LogUtil.b(FileUtil.TAG, "io error.", LogAnonymous.b((Throwable) e));
            return false;
        } finally {
            a(fileOutputStream);
        }
    }

    private static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                LogUtil.b(FileUtil.TAG, "close stream error.", LogAnonymous.b((Throwable) e));
            }
        }
    }

    public static boolean a(String str) {
        if (CommonUtil.c(str) == null) {
            LogUtil.b(FileUtil.TAG, "delete file fail: path is empty");
            return false;
        }
        File file = new File(str);
        if (!file.isFile() || !file.exists() || file.delete()) {
            return true;
        }
        LogUtil.b(FileUtil.TAG, "delete file fail");
        return false;
    }

    public static boolean d(String str) {
        if (CommonUtil.c(str) == null) {
            LogUtil.b(FileUtil.TAG, "delete file fail: path is empty");
            return false;
        }
        File file = new File(str);
        return file.isFile() && file.exists();
    }
}
