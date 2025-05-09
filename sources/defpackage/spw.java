package defpackage;

import com.huawei.health.h5pro.utils.GeneralUtil;
import com.huawei.hwlogsmodel.LogUtil;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/* loaded from: classes7.dex */
public class spw {
    private File e;

    public spw(String str) {
        if (!GeneralUtil.isSafePath(str)) {
            LogUtil.h("ZipCompressor", "ZipCompressor: untrusted -> " + str);
            return;
        }
        this.e = new File(str);
    }

    private void d(ZipOutputStream zipOutputStream) {
        if (zipOutputStream != null) {
            try {
                zipOutputStream.close();
            } catch (IOException unused) {
                LogUtil.b("ZipCompressor", "close streamClose error");
            }
        }
    }

    public boolean a(String... strArr) {
        ZipOutputStream zipOutputStream;
        if (this.e == null) {
            LogUtil.h("ZipCompressor", "compress: mZipFile is null");
            return false;
        }
        ZipOutputStream zipOutputStream2 = null;
        try {
            try {
                zipOutputStream = new ZipOutputStream(new CheckedOutputStream(new FileOutputStream(this.e), new CRC32()));
            } catch (Throwable th) {
                th = th;
                zipOutputStream = null;
            }
        } catch (IOException unused) {
        }
        try {
            for (String str : strArr) {
                if (GeneralUtil.isSafePath(str)) {
                    e(new File(str), zipOutputStream, "");
                } else {
                    LogUtil.h("ZipCompressor", "compress: untrusted -> " + str);
                }
            }
            d(zipOutputStream);
            return true;
        } catch (IOException unused2) {
            zipOutputStream2 = zipOutputStream;
            LogUtil.b("ZipCompressor", "close ZipOutputStream error IOException！");
            d(zipOutputStream2);
            return false;
        } catch (Throwable th2) {
            th = th2;
            d(zipOutputStream);
            throw th;
        }
    }

    private boolean e(File file, ZipOutputStream zipOutputStream, String str) {
        if (file.isDirectory()) {
            LogUtil.a("ZipCompressor", "zip directory：" + str + file.getName());
            return c(file, zipOutputStream, str);
        }
        LogUtil.a("ZipCompressor", "zip file：" + str + file.getName());
        return d(file, zipOutputStream, str);
    }

    private boolean c(File file, ZipOutputStream zipOutputStream, String str) {
        File[] listFiles;
        if (!file.exists() || (listFiles = file.listFiles()) == null) {
            return false;
        }
        for (File file2 : listFiles) {
            if (!e(file2, zipOutputStream, str + file.getName() + File.separator)) {
                return false;
            }
        }
        return true;
    }

    private boolean d(File file, ZipOutputStream zipOutputStream, String str) {
        BufferedInputStream bufferedInputStream;
        if (!file.exists()) {
            return false;
        }
        BufferedInputStream bufferedInputStream2 = null;
        try {
            try {
                bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            } catch (IOException unused) {
            }
        } catch (Throwable th) {
            th = th;
            bufferedInputStream = bufferedInputStream2;
        }
        try {
            zipOutputStream.putNextEntry(new ZipEntry(str + file.getName()));
            byte[] bArr = new byte[4096];
            while (true) {
                int read = bufferedInputStream.read(bArr, 0, 4096);
                if (read != -1) {
                    zipOutputStream.write(bArr, 0, read);
                } else {
                    a(bufferedInputStream);
                    return true;
                }
            }
        } catch (IOException unused2) {
            bufferedInputStream2 = bufferedInputStream;
            LogUtil.b("ZipCompressor", "compressFile error IOException！");
            a(bufferedInputStream2);
            return false;
        } catch (Throwable th2) {
            th = th2;
            a(bufferedInputStream);
            throw th;
        }
    }

    private void a(BufferedInputStream bufferedInputStream) {
        if (bufferedInputStream != null) {
            try {
                bufferedInputStream.close();
            } catch (IOException unused) {
                LogUtil.b("ZipCompressor", "fileInputStreamClose close error ");
            }
        }
    }
}
